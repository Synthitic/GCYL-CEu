package com.fulltrix.gcyl.mixin;

import gregtech.api.capability.IGhostSlotConfigurable;
import gregtech.api.capability.impl.GhostCircuitItemStackHandler;
import gregtech.api.capability.impl.ItemHandlerList;
import gregtech.api.capability.impl.RecipeLogicSteam;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.GhostCircuitSlotWidget;
import gregtech.api.gui.widgets.ImageWidget;
import gregtech.api.gui.widgets.SlotWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.SteamMetaTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.*;

import java.util.Arrays;

@Mixin(value = SteamMetaTileEntity.class, remap = false)
public abstract class MixinSteamMetaTileEntity extends MetaTileEntity implements IGhostSlotConfigurable {

    @Unique
    protected GhostCircuitItemStackHandler gcyl$circuitInventory;
    @Unique
    private IItemHandlerModifiable gcyl$actualImportItems;

    @Shadow
    protected RecipeLogicSteam workableHandler;
    @Shadow
    @Final
    protected boolean isHighPressure;

    protected MixinSteamMetaTileEntity(@NotNull ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
    }

    @Override
    public IItemHandlerModifiable getImportItems() {
        if (this.gcyl$actualImportItems == null) {
            this.gcyl$actualImportItems = this.gcyl$circuitInventory == null ?
                    super.getImportItems() :
                    new ItemHandlerList(Arrays.asList(super.getImportItems(), this.gcyl$circuitInventory));
        }
        return this.gcyl$actualImportItems;
    }

    @Override
    protected void initializeInventory() {
        super.initializeInventory();
        if (this.hasGhostCircuitInventory()) {
            this.gcyl$circuitInventory = new GhostCircuitItemStackHandler(this);
            this.gcyl$circuitInventory.addNotifiableMetaTileEntity(this);
        }
        this.gcyl$actualImportItems = null;
    }

    @Override
    public boolean hasGhostCircuitInventory() {
        return true;
    }

    @Override
    public void setGhostCircuitConfig(int config) {
        if (this.gcyl$circuitInventory == null || this.gcyl$circuitInventory.getCircuitValue() == config) {
            return;
        }
        this.gcyl$circuitInventory.setCircuitValue(config);
        if (!getWorld().isRemote) {
            markDirty();
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        if (this.gcyl$circuitInventory != null) {
            this.gcyl$circuitInventory.write(data);
        }
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        if (this.gcyl$circuitInventory != null) {
            this.gcyl$circuitInventory.read(data);
        }
    }

    /**
     * @author Irgendwer01
     * @reason Add a programmed circuit slot to steam machines as well
     */
    @Overwrite
    public ModularUI.Builder createUITemplate(EntityPlayer player) {
        ModularUI.Builder builder = ModularUI.builder(GuiTextures.BACKGROUND_STEAM.get(isHighPressure), 176, 166)
                .label(6, 6, getMetaFullName()).shouldColor(false)
                .widget(new ImageWidget(79, 42, 18, 18, GuiTextures.INDICATOR_NO_STEAM.get(isHighPressure))
                        .setPredicate(() -> workableHandler.isHasNotEnoughEnergy()))
                .bindPlayerInventory(player.inventory, GuiTextures.SLOT_STEAM.get(isHighPressure), 0);
        if (this.gcyl$circuitInventory != null) {
            SlotWidget circuitSlot = new GhostCircuitSlotWidget(gcyl$circuitInventory, 0, 124, 62)
                    .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.INT_CIRCUIT_OVERLAY);
            builder.widget(circuitSlot.setConsumer(this::gcyl$getCircuitSlotTooltip));
        }
        return builder;
    }

    @Unique
    protected void gcyl$getCircuitSlotTooltip(SlotWidget widget) {
        String configString;
        if (gcyl$circuitInventory == null || gcyl$circuitInventory.getCircuitValue() == GhostCircuitItemStackHandler.NO_CONFIG) {
            configString = new TextComponentTranslation("gregtech.gui.configurator_slot.no_value").getFormattedText();
        } else {
            configString = String.valueOf(gcyl$circuitInventory.getCircuitValue());
        }

        widget.setTooltipText("gregtech.gui.configurator_slot.tooltip", configString);
    }
}
