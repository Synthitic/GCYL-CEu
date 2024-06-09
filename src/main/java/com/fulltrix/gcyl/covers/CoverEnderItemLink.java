package com.fulltrix.gcyl.covers;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Matrix4;
import com.cleanroommc.modularui.api.drawable.IKey;
import com.cleanroommc.modularui.api.widget.IWidget;
import com.cleanroommc.modularui.drawable.DynamicDrawable;
import com.cleanroommc.modularui.drawable.Rectangle;
import com.cleanroommc.modularui.factory.SidedPosGuiData;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.utils.Color;
import com.cleanroommc.modularui.value.sync.*;
import com.cleanroommc.modularui.widgets.FluidSlot;
import com.cleanroommc.modularui.widgets.ItemSlot;
import com.cleanroommc.modularui.widgets.ToggleButton;
import com.cleanroommc.modularui.widgets.layout.Column;
import com.cleanroommc.modularui.widgets.layout.Grid;
import com.cleanroommc.modularui.widgets.layout.Row;
import com.cleanroommc.modularui.widgets.textfield.TextFieldWidget;
import com.fulltrix.gcyl.CommonProxy;
import com.fulltrix.gcyl.api.util.ItemContainerSwitchShim;
import com.fulltrix.gcyl.api.util.VirtualContainerRegistry;
import com.fulltrix.gcyl.client.ClientHandler;
import gregtech.api.capability.GregtechTileCapabilities;
import gregtech.api.capability.IControllable;
import gregtech.api.cover.CoverBase;
import gregtech.api.cover.CoverDefinition;
import gregtech.api.cover.CoverWithUI;
import gregtech.api.cover.CoverableView;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.*;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.mui.GTGuiTextures;
import gregtech.api.mui.GTGuis;
import gregtech.api.util.GTTransferUtils;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.covers.CoverConveyor;
import gregtech.common.covers.CoverPump;
import gregtech.common.covers.filter.ItemFilterContainer;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.*;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

//TODO: get itemslots to sync properly so that itemslots can be used with mui
//TODO: implement integration with terminal?
public class CoverEnderItemLink extends CoverBase implements CoverWithUI, ITickable, IControllable {
    public static final int TRANSFER_RATE = 1000;

    private static final Pattern COLOR_INPUT_PATTERN = Pattern.compile("[0-9a-fA-F]*");

    protected CoverConveyor.ConveyorMode conveyorMode = CoverConveyor.ConveyorMode.IMPORT;

    private int color = 0xFFFFFFFF;
    private UUID playerUUID = null;
    private boolean isPrivate = false;
    private boolean workingEnabled = true;
    private boolean ioEnabled = false;
    private String tempColorStr;
    private boolean isColorTemp;
    protected int itemsLeftToTransferLastSecond;

    private final ItemContainerSwitchShim linkedShim;

    protected final ItemFilterContainer itemFilter;


    protected CoverEnderItemLink(@NotNull CoverDefinition definition, @NotNull CoverableView coverableView,
                                 @NotNull EnumFacing attachedSide) {
        super(definition, coverableView, attachedSide);
        this.linkedShim = new ItemContainerSwitchShim(VirtualContainerRegistry.getContainerCreate(makeChestName(), null));
        itemFilter = new ItemFilterContainer(this);
    }

    private String makeChestName() {
        return "EILink#" + Integer.toHexString(this.color).toUpperCase();
    }

    private UUID getChestUUID() { return isPrivate ? playerUUID : null;}

    public ItemFilterContainer getItemFilterContainer() {return this.itemFilter;}

    public boolean isIOEnabled() {return this.ioEnabled;}

    @Override
    public boolean canAttach(@NotNull CoverableView coverable, @NotNull EnumFacing side) {
        return coverable.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side);
    }

    //TODO: change to item link texture
    @Override
    public void renderCover(@NotNull CCRenderState renderState, @NotNull Matrix4 translation,
                            IVertexOperation[] pipeline, @NotNull Cuboid6 plateBox, @NotNull BlockRenderLayer layer) {
        ClientHandler.ENDER_ITEM_LINK.renderSided(getAttachedSide(), plateBox, renderState, pipeline, translation);
    }

    @Override
    public @NotNull EnumActionResult onScrewdriverClick(@NotNull EntityPlayer playerIn, @NotNull EnumHand hand,
                                                        @NotNull CuboidRayTraceResult hitResult) {
        if (!getWorld().isRemote) {
            openUI((EntityPlayerMP) playerIn);
        }
        return EnumActionResult.SUCCESS;
    }

    @Override
    public void onAttachment(@NotNull CoverableView coverableView, @NotNull EnumFacing side,
                             @Nullable EntityPlayer player, @NotNull ItemStack itemStack) {
        super.onAttachment(coverableView, side, player, itemStack);
        if (player != null) {
            this.playerUUID = player.getUniqueID();
        }
    }

    @Override
    public void onRemoval() {
        NonNullList<ItemStack> drops = NonNullList.create();
        MetaTileEntity.clearInventory(drops, itemFilter);
        for (ItemStack itemStack : drops) {
            Block.spawnAsEntity(this.getWorld(), this.getPos(), itemStack);
        }
    }

    @Override
    public void update() {
        long timer = this.getOffsetTimer();
        IItemHandler targetInventory = getCoverableView().getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, getAttachedSide());
        if (workingEnabled && ioEnabled && itemsLeftToTransferLastSecond > 0 && timer % 5 == 0) {
            int totalTransferred = doTransferItemsAny(targetInventory, linkedShim, itemsLeftToTransferLastSecond);
            this.itemsLeftToTransferLastSecond -= totalTransferred;
        }

        if (timer % 20 == 0){
            this.itemsLeftToTransferLastSecond = TRANSFER_RATE;
        }
    }

    protected int doTransferItemsAny(IItemHandler otherHandler, IItemHandler enderHandler, int maxTransferAmount) {
        if (conveyorMode == CoverConveyor.ConveyorMode.IMPORT) {
            return moveInventoryItems(otherHandler, enderHandler, maxTransferAmount);
        } else if (conveyorMode == CoverConveyor.ConveyorMode.EXPORT) {
            return moveInventoryItems(enderHandler, otherHandler, maxTransferAmount);
        }
        return 0;
    }


    protected int moveInventoryItems(IItemHandler sourceInventory, IItemHandler targetInventory, int maxTransferAmount) {
        int itemsLeftToTransfer = maxTransferAmount;
        for (int srcIndex = 0; srcIndex < sourceInventory.getSlots(); srcIndex++) {
            ItemStack sourceStack = sourceInventory.extractItem(srcIndex, itemsLeftToTransfer, true);
            if (sourceStack.isEmpty()) {
                continue;
            }
            if (!itemFilter.test(sourceStack)) {
                continue;
            }
            ItemStack remainder = GTTransferUtils.insertItem(targetInventory, sourceStack, true);
            int amountToInsert = sourceStack.getCount() - remainder.getCount();

            if (amountToInsert > 0) {
                sourceStack = sourceInventory.extractItem(srcIndex, amountToInsert, false);
                if (!sourceStack.isEmpty()) {
                    GTTransferUtils.insertItem(targetInventory, sourceStack, false);
                    itemsLeftToTransfer -= sourceStack.getCount();

                    if (itemsLeftToTransfer == 0) {
                        break;
                    }
                }
            }
        }
        return maxTransferAmount - itemsLeftToTransfer;
    }

    public void setConveyorMode(CoverConveyor.ConveyorMode mode) {
        conveyorMode = mode;
        this.markDirty();
    }

    public CoverConveyor.ConveyorMode getConveyorMode() {
        return conveyorMode;
    }

    @Override
    public void writeInitialSyncData(PacketBuffer packetBuffer) {
        packetBuffer.writeInt(this.color);
        packetBuffer.writeString(this.playerUUID == null ? "null" : this.playerUUID.toString());
    }

    @Override
    public void readInitialSyncData(PacketBuffer packetBuffer) {
        this.color = packetBuffer.readInt();
        String uuidStr = packetBuffer.readString(36);
        this.playerUUID = uuidStr.equals("null") ? null : UUID.fromString(uuidStr);
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        tagCompound.setInteger("ConveyorMode", conveyorMode.ordinal());
        tagCompound.setTag("Filter", itemFilter.serializeNBT());
        tagCompound.setBoolean("WorkingAllowed", workingEnabled);
        tagCompound.setBoolean("IOAllowed", ioEnabled);
        tagCompound.setBoolean("Private", isPrivate);
        tagCompound.setString("PlacedUUID", playerUUID.toString());
        tagCompound.setInteger("Frequency", color);
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        this.conveyorMode = CoverConveyor.ConveyorMode.values()[tagCompound.getInteger("ConveyorMode")];
        this.itemFilter.deserializeNBT(tagCompound.getCompoundTag("Filter"));
        this.workingEnabled = tagCompound.getBoolean("WorkingAllowed");
        this.ioEnabled = tagCompound.getBoolean("IOAllowed");
        this.isPrivate = tagCompound.getBoolean("Private");
        this.playerUUID = UUID.fromString(tagCompound.getString("PlacedUUID"));
        this.color = tagCompound.getInteger("Frequency");
        updateChestLink();
    }

    protected void updateChestLink() {
        this.linkedShim.changeInventory(VirtualContainerRegistry.getContainerCreate(makeChestName(), getChestUUID()));
        this.markDirty();
    }

    public <T> T getCapability(Capability<T> capability, T defaultValue) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast((IItemHandler) linkedShim);
        }
        if (capability == GregtechTileCapabilities.CAPABILITY_CONTROLLABLE) {
            return GregtechTileCapabilities.CAPABILITY_CONTROLLABLE.cast(this);
        }
        return defaultValue;
    }

    private boolean isPrivate() {
        return isPrivate;
    }

    private void setPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
        updateChestLink();
    }

    private void setIoEnabled(boolean ioEnabled) {
        this.ioEnabled = ioEnabled;
    }

    @Override
    public boolean isWorkingEnabled() {
        return workingEnabled;
    }

    @Override
    public void setWorkingEnabled(boolean isActivationAllowed) {
        this.workingEnabled = isActivationAllowed;
    }

    @Override
    public void openUI(EntityPlayerMP player) {
        CoverWithUI.super.openUI(player);
        isColorTemp = false;
    }

    @Override
    public boolean usesMui2() {
        return true;
    }
    @Override
    public ModularPanel buildUI(SidedPosGuiData guiData, GuiSyncManager guiSyncManager) {
        var panel = GTGuis.createPanel(this, 176, 208);

        getItemFilterContainer().setMaxTransferSize(1);

        return panel.child(CoverWithUI.createTitleRow(getPickItem()))
                .bindPlayerInventory()
                .child(createWidgets(panel, guiSyncManager));

    }

    protected Column createWidgets(ModularPanel panel, GuiSyncManager syncManager) {
        var isPrivate = new BooleanSyncValue(this::isPrivate, this::setPrivate);
        isPrivate.updateCacheFromSource(true);

        var color = new StringSyncValue(this::getColorStr, this::updateColor);
        color.updateCacheFromSource(true);

        var conveyorMode = new EnumSyncValue<>(CoverConveyor.ConveyorMode.class, this::getConveyorMode, this::setConveyorMode);
        syncManager.syncValue("conveyor_mode", conveyorMode);
        conveyorMode.updateCacheFromSource(true);

        var ioEnabled = new BooleanSyncValue(this::isIOEnabled, this::setIoEnabled);

        syncManager.registerSlotGroup("item_inv", this.linkedShim.getSlots());

        int rowSize = this.linkedShim.getSlots();
        List<ItemSlot> itemSlots = new ArrayList<>();
        for (int i = 0; i < rowSize; i++) {
            itemSlots.add(new ItemSlot().slot(SyncHandlers.itemSlot(this.linkedShim, i).slotGroup("item_inv")));
            itemSlots.get(i).setEnabled(false); //disable faulty itemslots
        }

        List<List<IWidget>> widgets = new ArrayList<>();
        widgets.add(new ArrayList<>());
            widgets.get(0).addAll(itemSlots);


        return new Column().coverChildrenHeight().top(24)
                .margin(7, 0).widthRel(1f)
                .child(new Row().marginBottom(2)
                        .coverChildrenHeight()
                        .child(new ToggleButton()
                                .tooltip(tooltip -> tooltip.setAutoUpdate(true))
                                .background(GTGuiTextures.PRIVATE_MODE_BUTTON[0])
                                .hoverBackground(GTGuiTextures.PRIVATE_MODE_BUTTON[0])
                                .selectedBackground(GTGuiTextures.PRIVATE_MODE_BUTTON[1])
                                .selectedHoverBackground(GTGuiTextures.PRIVATE_MODE_BUTTON[1])
                                .tooltipBuilder(tooltip -> tooltip.addLine(IKey.lang(this.isPrivate ?
                                        "cover.ender_fluid_link.private.tooltip.enabled" :
                                        "cover.ender_fluid_link.private.tooltip.disabled")))
                                .marginRight(2)
                                .value(isPrivate))
                        .child(new DynamicDrawable(() -> new Rectangle()
                                .setColor(this.color)
                                .asIcon().size(16))
                                .asWidget()
                                .background(GTGuiTextures.SLOT)
                                .size(18).marginRight(2))
                        .child(new TextFieldWidget().height(18)
                                .value(color)
                                .setValidator(s -> {
                                    if (s.length() != 8) {
                                        return color.getStringValue();
                                    }
                                    return s;
                                })
                                .setPattern(COLOR_INPUT_PATTERN)
                                .widthRel(0.5f).marginRight(2)))
                        .child(new Row().marginBottom(2)
                                .coverChildrenHeight()
                                .child(new Grid()
                                        .height(18)
                                        .minElementMargin(0, 0)
                                        .minColWidth(18).minRowHeight(18)
                                        .matrix(widgets)))
                .child(new Row().marginBottom(2)
                        .coverChildrenHeight()
                        .child(new ToggleButton()
                                .value(ioEnabled)
                                .overlay(IKey.dynamic(() -> IKey.lang(this.ioEnabled ?
                                                "behaviour.soft_hammer.enabled" :
                                                "behaviour.soft_hammer.disabled").get())
                                        .color(Color.WHITE.darker(1)))
                                .widthRel(0.6f)
                                .left(0)))
                .child(getItemFilterContainer().initUI(panel, syncManager))
                .child(new EnumRowBuilder<>(CoverConveyor.ConveyorMode.class)
                        .value(conveyorMode)
                        .overlay(GTGuiTextures.CONVEYOR_MODE_OVERLAY)
                        .lang("cover.pump.mode")
                        .build());
    }

    public String getColorStr() {
        return isColorTemp ? tempColorStr : Integer.toHexString(this.color).toUpperCase();
    }

    public void updateColor(String str) {
        if (str.length() == 8) {
            isColorTemp = false;
            long tmp = Long.parseLong(str, 16);
            if (tmp > 0x7FFFFFFF) {
                tmp -= 0x100000000L;
            }
            this.color = (int) tmp;
            updateChestLink();
        } else {
            tempColorStr = str;
            isColorTemp = true;
        }
    }
}
