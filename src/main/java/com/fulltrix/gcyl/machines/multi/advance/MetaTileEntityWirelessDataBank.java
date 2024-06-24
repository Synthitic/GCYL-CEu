package com.fulltrix.gcyl.machines.multi.advance;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import com.fulltrix.gcyl.api.util.VirtualResearchRegistry;
import com.fulltrix.gcyl.client.ClientHandler;
import gregtech.api.GTValues;
import gregtech.api.capability.*;
import gregtech.api.capability.impl.EnergyContainerList;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockDisplayText;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.util.AssemblyLineManager;
import gregtech.api.util.TextComponentUtil;
import gregtech.api.util.TextFormattingUtil;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.utils.TooltipHelper;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockComputerCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityDataBank;
import gregtech.core.sound.GTSoundEvents;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_FLUIDS;
import static gregtech.api.metatileentity.multiblock.MultiblockAbility.IMPORT_ITEMS;
import static gregtech.api.unification.material.Materials.PCBCoolant;

//TODO fix sound even after disabling
public class MetaTileEntityWirelessDataBank extends MultiblockWithDisplayBase implements IControllable {

    private static final int EUT_PER_HATCH = GTValues.VA[GTValues.UEV];
    private static final int COOLANT_PER_HATCH = 100;
    private IEnergyContainer energyContainer;

    private boolean isActive = false;
    private boolean isWorkingEnabled = true;
    protected boolean hasNotEnoughEnergy;
    protected boolean hasNotEnoughCoolant;
    private int energyUsage = 0;
    private UUID playerUUID = null;

    private int coolantUsage = 0;

    private VirtualResearchRegistry.VirtualResearchContainer researchContainerWireless;

    public boolean initialize = true;


    public MetaTileEntityWirelessDataBank(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
        this.energyContainer = new EnergyContainerList(new ArrayList<>());
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityWirelessDataBank(metaTileEntityId);
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        this.energyContainer = new EnergyContainerList(getAbilities(MultiblockAbility.INPUT_ENERGY));
        this.energyUsage = calculateEnergyUsage();
        this.coolantUsage = calculateCoolantUsage();
    }

    protected int calculateEnergyUsage() {
        return EUT_PER_HATCH * this.getAbilities(IMPORT_ITEMS).size();
    }

    protected int calculateCoolantUsage() {
        return COOLANT_PER_HATCH * this.getAbilities(IMPORT_ITEMS).size();
    }

    @NotNull
    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XDDDX", "XDDDX", "XDDDX")
                .aisle("XDDDX", "XAAAX", "XDDDX")
                .aisle("XCCCX", "XCSCX", "XCCCX")
                .where('S', selfPredicate())
                .where('X', states(getOuterState()))
                .where('D', states(getInnerState()).setMinGlobalLimited(3)
                        .or(metaTileEntities(MetaTileEntities.ITEM_IMPORT_BUS[GTValues.HV]).setMinGlobalLimited(1,1).setMaxGlobalLimited(8))
                        .or(abilities(IMPORT_FLUIDS).setExactLimit(1)))
                .where('A', states(getInnerState()))
                .where('C', states(getFrontState())
                        .setMinGlobalLimited(4)
                        .or(autoAbilities())
                        .or(abilities(MultiblockAbility.INPUT_ENERGY)
                                .setMinGlobalLimited(1).setMaxGlobalLimited(2).setPreviewCount(1)))
                .build();
    }

    @NotNull
    private static IBlockState getOuterState() {
        return MetaBlocks.COMPUTER_CASING.getState(BlockComputerCasing.CasingType.COMPUTER_HEAT_VENT);
    }

    @NotNull
    private static IBlockState getInnerState() {
        return MetaBlocks.COMPUTER_CASING.getState(BlockComputerCasing.CasingType.ADVANCED_COMPUTER_CASING);
    }

    private static IBlockState getFrontState() {
        return MetaBlocks.COMPUTER_CASING.getState(BlockComputerCasing.CasingType.HIGH_POWER_CASING);
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        if(this.researchContainerWireless != null) {
            this.researchContainerWireless.clearResearch();
        }
        this.energyContainer = new EnergyContainerList(new ArrayList<>());
        this.energyUsage = 0;
        this.coolantUsage = 0;
    }

    protected int getEnergyUsage() {
        return energyUsage;
    }
    protected int getCoolantUsage() {return coolantUsage;}

    @Override
    public boolean isActive() {
        return super.isActive() && this.isActive;
    }

    public void setActive(boolean active) {
        if (this.isActive != active) {
            this.isActive = active;
            markDirty();
            World world = getWorld();
            if (world != null && !world.isRemote) {
                writeCustomData(GregtechDataCodes.WORKABLE_ACTIVE, buf -> buf.writeBoolean(active));
            }
        }
    }

    @Override
    public boolean isWorkingEnabled() {
        return this.isWorkingEnabled;
    }

    @Override
    public void setWorkingEnabled(boolean isWorkingAllowed) {
        this.isWorkingEnabled = isWorkingAllowed;
        markDirty();
        World world = getWorld();
        if (world != null && !world.isRemote) {
            writeCustomData(GregtechDataCodes.WORKING_ENABLED, buf -> buf.writeBoolean(isWorkingEnabled));
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        if (sourcePart != null) {
            // part rendering
            if (sourcePart instanceof IDataAccessHatch) {
                return Textures.COMPUTER_CASING;
            } else {
                return Textures.HIGH_POWER_CASING;
            }
        } else {
            // controller rendering
            if (isStructureFormed()) {
                return Textures.HIGH_POWER_CASING;
            } else {
                return Textures.COMPUTER_CASING;
            }
        }
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        getFrontOverlay().renderOrientedState(renderState, translation, pipeline, getFrontFacing(), this.isActive(),
                this.isWorkingEnabled());
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.DATA_BANK_OVERLAY;
    }

    @Override
    protected boolean shouldShowVoidingModeButton() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public SoundEvent getSound() {
        return GTSoundEvents.COMPUTATION;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, @NotNull List<String> tooltip,
                               boolean advanced) {
        super.addInformation(stack, world, tooltip, advanced);
        tooltip.add(TextFormatting.LIGHT_PURPLE + I18n.format("gcyl.machine.wireless_data_bank.tooltip.1"));
        tooltip.add(TooltipHelper.BLINKING_RED + I18n.format("gcyl.machine.wireless_data_bank.tooltip.2"));
        tooltip.add(TextFormatting.LIGHT_PURPLE + I18n.format("gcyl.machine.wireless_data_bank.tooltip.3"));
        tooltip.add(TooltipHelper.BLINKING_RED + I18n.format("gcyl.machine.wireless_data_bank.tooltip.6"));
        tooltip.add(I18n.format("gregtech.machine.data_bank.tooltip.1"));
        tooltip.add(I18n.format("gcyl.machine.wireless_data_bank.tooltip.4"));
        tooltip.add(
                I18n.format("gcyl.machine.wireless_data_bank.tooltip.5", TextFormattingUtil.formatNumbers(EUT_PER_HATCH)));
        tooltip.add(
                I18n.format("gcyl.machine.wireless_data_bank.tooltip.7", TextFormattingUtil.formatNumbers(COOLANT_PER_HATCH)));
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        MultiblockDisplayText.builder(textList, isStructureFormed())
                .setWorkingStatus(true, isActive() && isWorkingEnabled()) // transform into two-state system for display
                .setWorkingStatusKeys(
                        "gregtech.multiblock.idling",
                        "gregtech.multiblock.idling",
                        "gregtech.multiblock.data_bank.providing")
                .addEnergyUsageExactLine(getEnergyUsage())
                .addWorkingStatusLine()
                .addCustom(tl -> {
                    if(this.initialize) {
                        tl.add(new TextComponentTranslation("gcyl.multiblock.wireless_pss.not_initialized"));
                    } else {
                        try {
                            tl.add(TextComponentUtil.translationWithColor(TextFormatting.LIGHT_PURPLE, "gcyl.multiblock.wireless_pss.private", Objects.requireNonNull(this.getWorld().getPlayerEntityByUUID(this.playerUUID)).getName()));
                        } catch (NullPointerException e) {
                            if (Objects.equals(this.playerUUID, new UUID(0, 0))) {
                                tl.add(TextComponentUtil.translationWithColor(TextFormatting.LIGHT_PURPLE, "gcyl.multiblock.wireless_pss.public"));
                            } else {
                                tl.add(TextComponentUtil.translationWithColor(TextFormatting.RED, "gcyl.multiblock.wireless_pss.player_offline"));
                            }
                        }
                    }
                });
    }

    @Override
    protected void addWarningText(List<ITextComponent> textList) {
        MultiblockDisplayText.builder(textList, isStructureFormed(), false)
                .addLowPowerLine(hasNotEnoughEnergy)
                .addCustom(tl -> {
                    if(hasNotEnoughCoolant) {
                        tl.add(TextComponentUtil.translationWithColor(TextFormatting.RED, "gcyl.multiblock.wireless_pss.not_enough_coolant"));
                    }
                })
                .addMaintenanceProblemLines(getMaintenanceProblems());
    }

    private boolean checkCoolant() {
        IFluidTank inputTank = this.getAbilities(IMPORT_FLUIDS).get(0);
        if (inputTank.getFluid() != null)
            return (inputTank.getFluid().amount >= getCoolantUsage()) && inputTank.getFluid().containsFluid(PCBCoolant.getFluid(1));
        else
            return false;
    }

    @Override
    public void updateFormedValid() {

        if(isWorkingEnabled()) {

            int energyToConsume = this.getEnergyUsage();
            boolean hasMaintenance = ConfigHolder.machines.enableMaintenance && hasMaintenanceMechanics();
            if (hasMaintenance) {
                // 10% more energy per maintenance problem
                energyToConsume += getNumMaintenanceProblems() * energyToConsume / 10;
            }

            if (this.hasNotEnoughEnergy && energyContainer.getInputPerSec() > 19L * energyToConsume) {
                this.hasNotEnoughEnergy = false;
            }

            if (this.hasNotEnoughCoolant && this.checkCoolant()) {
                this.hasNotEnoughCoolant = false;
            }

            if (this.energyContainer.getEnergyStored() >= energyToConsume) {
                if (!this.hasNotEnoughEnergy && !this.hasNotEnoughCoolant) {
                    long consumed = this.energyContainer.removeEnergy(energyToConsume);
                    if (consumed == -energyToConsume) {
                        setActive(true);
                    } else {
                        this.hasNotEnoughEnergy = true;
                        if (this.researchContainerWireless != null)
                            this.researchContainerWireless.clearResearch();
                        setActive(false);
                    }
                }
            } else {
                this.hasNotEnoughEnergy = true;
                if (this.researchContainerWireless != null)
                    this.researchContainerWireless.clearResearch();
                setActive(false);
            }

            if (this.checkCoolant()) {
                if (!this.hasNotEnoughCoolant  && !this.hasNotEnoughEnergy) {
                    IFluidTank inputTank = this.getAbilities(IMPORT_FLUIDS).get(0);
                    if(inputTank.drain(this.getCoolantUsage(), false) != null) {
                        if (inputTank.drain(this.getCoolantUsage(), false).isFluidStackIdentical(PCBCoolant.getFluid(this.getCoolantUsage()))) {
                            inputTank.drain(this.getCoolantUsage(), true);
                            setActive(true);
                        }
                    }else {
                        this.hasNotEnoughCoolant = true;
                        if (this.researchContainerWireless != null)
                            this.researchContainerWireless.clearResearch();
                        setActive(false);
                    }
                }
            } else {
                this.hasNotEnoughCoolant = true;
                if (this.researchContainerWireless != null)
                    this.researchContainerWireless.clearResearch();
                setActive(false);
            }

            if (!this.initialize && !this.getWorld().isRemote && this.getOffsetTimer() % 1200 == 0 && isActive()) {

                List<String> researchStrings = new ArrayList<>();
                for (int x = 0; x < this.getAbilities(IMPORT_ITEMS).size(); x++) {
                    for (int i = 0; i < this.getAbilities(IMPORT_ITEMS).get(x).getSlots(); i++) {
                        ItemStack stack = this.getAbilities(IMPORT_ITEMS).get(x).getStackInSlot(i);
                        String researchId = AssemblyLineManager.readResearchId(stack);
                        boolean isValid = AssemblyLineManager.isStackDataItem(stack, true);
                        if (researchId != null && isValid) {
                            researchStrings.add(researchId);
                        }
                    }
                }

                this.researchContainerWireless.setResearchId(researchStrings);
            }
        }
    }

    @Override
    public boolean onScrewdriverClick(EntityPlayer playerIn, EnumHand hand, EnumFacing facing,
                                      CuboidRayTraceResult hitResult) {
        if(!getWorld().isRemote) {
            if (this.initialize) {
                if(playerIn.isSneaking()) {
                    this.playerUUID = playerIn.getUniqueID();
                    this.researchContainerWireless = VirtualResearchRegistry.getContainerCreate(makeResearchContainerName(), this.playerUUID);
                    this.initialize = false;
                    playerIn.sendStatusMessage(new TextComponentTranslation("gcyl.wireless_initialized_private"), false);
                }
                else {
                    this.playerUUID = new UUID(0,0);
                    this.researchContainerWireless = VirtualResearchRegistry.getContainerCreate(makeResearchContainerName(), this.playerUUID);
                    this.initialize = false;
                    playerIn.sendStatusMessage(new TextComponentTranslation("gcyl.wireless_initialized_public"), false);
                }
            }
        }
        return true;
    }

    @Override
    public void receiveCustomData(int dataId, @NotNull PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        if (dataId == GregtechDataCodes.WORKABLE_ACTIVE) {
            this.isActive = buf.readBoolean();
            scheduleRenderUpdate();
        } else if (dataId == GregtechDataCodes.WORKING_ENABLED) {
            this.isWorkingEnabled = buf.readBoolean();
            scheduleRenderUpdate();
        }
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing side) {
        if (capability == GregtechTileCapabilities.CAPABILITY_CONTROLLABLE) {
            return GregtechTileCapabilities.CAPABILITY_CONTROLLABLE.cast(this);
        }
        return super.getCapability(capability, side);
    }

    @Override
    public void writeInitialSyncData(PacketBuffer packetBuffer) {
        super.writeInitialSyncData(packetBuffer);
        packetBuffer.writeBoolean(this.isActive);
        packetBuffer.writeBoolean(this.isWorkingEnabled);
        packetBuffer.writeBoolean(this.initialize);
        packetBuffer.writeString(this.playerUUID == null ? "null" : this.playerUUID.toString());
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer packetBuffer) {
        super.receiveInitialSyncData(packetBuffer);
        setActive(packetBuffer.readBoolean());
        setWorkingEnabled(packetBuffer.readBoolean());
        this.initialize = packetBuffer.readBoolean();
        String uuidStr = packetBuffer.readString(36);
        this.playerUUID = uuidStr.equals("null") ? null : UUID.fromString(uuidStr);
        updateResearchLink();
    }


    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setBoolean("isActive", this.isActive);
        data.setBoolean("isWorkingEnabled", this.isWorkingEnabled);
        data.setBoolean("Initialized", initialize);
        data.setString("PlacedUUID", playerUUID == null ? "null" : playerUUID.toString());
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        setActive(data.getBoolean("isActive"));
        setWorkingEnabled(data.getBoolean("isWorkingEnabled"));
        String uuidStr = data.getString("PlacedUUID");
        this.playerUUID = uuidStr.equals("null") ? null : UUID.fromString(uuidStr);
        this.initialize = data.getBoolean("Initialized");
        updateResearchLink();
    }

    protected void updateResearchLink() {
        this.researchContainerWireless = VirtualResearchRegistry.getContainerCreate(makeResearchContainerName(), this.playerUUID);
        this.markDirty();
    }

    private String makeResearchContainerName() {return "RContainer#";}
}
