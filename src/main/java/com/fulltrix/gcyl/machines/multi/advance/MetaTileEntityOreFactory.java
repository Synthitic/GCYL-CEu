package com.fulltrix.gcyl.machines.multi.advance;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import com.fulltrix.gcyl.api.multi.IOreFactoryProvider;
import com.fulltrix.gcyl.api.multi.OreFactoryLogic;
import com.fulltrix.gcyl.blocks.GCYLMetaBlocks;
import com.fulltrix.gcyl.blocks.metal.MetalCasing2;
import com.google.common.collect.Lists;
import gregtech.api.capability.*;
import gregtech.api.capability.impl.EnergyContainerList;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.capability.impl.ItemHandlerList;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockDisplayText;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.util.GTUtility;
import gregtech.api.util.TextComponentUtil;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.utils.TooltipHelper;
import gregtech.common.blocks.*;
import gregtech.core.sound.GTSoundEvents;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static gregtech.api.unification.material.Materials.TungstenSteel;
import static gregtech.api.util.RelativeDirection.*;

//TODO: make sifting work. separate base textures so hatches can go elsewhere

public class MetaTileEntityOreFactory extends MultiblockWithDisplayBase implements IWorkable, IOreFactoryProvider {

    private IMultipleTankHandler inputFluidInventory;

    private IItemHandlerModifiable inputInventory;

    private IItemHandlerModifiable outputInventory;

    private IEnergyContainer energyContainer;

    protected final MetaTileEntity metaTileEntity;

    private int voltageTier;
    private int tier;

    private long energyToDrain;

    private OreFactoryLogic oreFactoryLogic;


    public MetaTileEntityOreFactory(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
        this.metaTileEntity = this;
        this.oreFactoryLogic = new OreFactoryLogic(this);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityOreFactory(metaTileEntityId);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public SoundEvent getSound() {
        return GTSoundEvents.MACERATOR;
    }

    //EXAMPLE CONVERSION FROM GTNH STRUCTURE PATTERN TO CEU
    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(LEFT, FRONT, DOWN)
                .aisle("           ", "           ", "       WWW ", "       WWW ", "           ", "           ")
                .aisle("           ", "       sss ", "      sppps", "      sppps", "       sss ", "           ")
                .aisle("           ", "       sss ", "      s   s", "      s   s", "       sss ", "           ")
                .aisle("           ", "       sss ", "      sppps", "      sppps", "       sss ", "           ")
                .aisle("           ", "       sss ", "      s   s", "      s   s", "       sss ", "           ")
                .aisle("           ", "       sss ", "      sppps", "      sppps", "       sss ", "           ")
                .aisle("iiiiii     ", "iiiiiiisssi", "iiiiiis   s", "iiiiiis   s", "iiiiiiisssi", "iiiiii     ")
                .aisle("iggggi     ", "gt  t isssi", "g xx  sppps", "g xx  sppps", "gt  t isssi", "iggggi     ")
                .aisle("iggggi     ", "gt  t isssi", "g xx  s   s", "g xx  s   s", "gt  t isssi", "iggggi     ")
                .aisle("iggggi     ", "gt  t is~si", "g xx  sppps", "g xx  sppps", "gt  t isssi", "iggggi     ")
                .aisle("iggggi     ", "gt  t isssi", "g xx  s   s", "g xx  s   s", "gt  t isssi", "iggggi     ")
                .aisle("iiiiii     ", "iiiiiiiiiii", "iiiiiiiiiii", "iiiiiiiiiii", "iiiiiiiiiii", "iiiiii     ")
                .where(' ', any())
                .where('i', states(GCYLMetaBlocks.METAL_CASING_2.getState(MetalCasing2.CasingType.IRIDIUM)))
                .where('x', states(MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.STEEL_GEARBOX)))
                .where('t', frames(TungstenSteel))
                .where('s', states(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN)).setMinGlobalLimited(90)
                        .or(autoAbilities(false, true, true, true, true, false ,false)
                        .or(abilities(MultiblockAbility.INPUT_ENERGY, MultiblockAbility.INPUT_LASER).setExactLimit(1))))
                .where('W', states(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN)).or(abilities(MultiblockAbility.MUFFLER_HATCH).setExactLimit(1)))
                .where('p', states(MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE)))
                .where('~', selfPredicate())
                .where('g', states(MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.FUSION_GLASS)))
                .build();
    }

    @Override
    public boolean hasMufflerMechanics() {
        return true;
    }

    public TraceabilityPredicate autoAbilities(boolean checkEnergyIn,
                                               boolean checkMaintenance,
                                               boolean checkItemIn,
                                               boolean checkItemOut,
                                               boolean checkFluidIn,
                                               boolean checkFluidOut,
                                               boolean checkMuffler) {
        TraceabilityPredicate predicate = super.autoAbilities(checkMaintenance, checkMuffler);

        if (checkEnergyIn) {
            predicate = predicate.or(abilities(MultiblockAbility.INPUT_ENERGY).setMinGlobalLimited(1)
                    .setMaxGlobalLimited(2)
                    .setPreviewCount(1));
        }

        if (checkItemIn) {
            predicate = predicate.or(abilities(MultiblockAbility.IMPORT_ITEMS).setPreviewCount(1));
        }
        if (checkItemOut) {
            predicate = predicate.or(abilities(MultiblockAbility.EXPORT_ITEMS).setPreviewCount(1));
        }
        if (checkFluidIn) {
            predicate = predicate.or(abilities(MultiblockAbility.IMPORT_FLUIDS).setPreviewCount(1));
        }
        if (checkFluidOut) {
            predicate = predicate.or(abilities(MultiblockAbility.EXPORT_FLUIDS).setPreviewCount(1));
        }
        return predicate;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.CLEAN_STAINLESS_STEEL_CASING;
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        MultiblockDisplayText.builder(textList, isStructureFormed())
                .addEnergyUsageLine(this.energyContainer)
                .setWorkingStatus(oreFactoryLogic.isWorkingEnabled(), oreFactoryLogic.isActive())
                .addWorkingStatusLine()
                .addCustom(tl -> {
                    tl.add(TextComponentUtil.translationWithColor(TextFormatting.YELLOW, "gcyl.machine.ore_factory.current_mode"));

                    if(this.oreFactoryLogic.getConfiguration() == 0)
                            tl.add(TextComponentUtil.translationWithColor(TextFormatting.YELLOW, "gcyl.machine.ore_factory.config.0"));
                    if(this.oreFactoryLogic.getConfiguration() == 1)
                            tl.add(TextComponentUtil.translationWithColor(TextFormatting.YELLOW, "gcyl.machine.ore_factory.config.1"));
                    if(this.oreFactoryLogic.getConfiguration() == 2)
                            tl.add(TextComponentUtil.translationWithColor(TextFormatting.YELLOW, "gcyl.machine.ore_factory.config.2"));
                    if(this.oreFactoryLogic.getConfiguration() == 3)
                            tl.add(TextComponentUtil.translationWithColor(TextFormatting.YELLOW, "gcyl.machine.ore_factory.config.3"));
                    if(this.oreFactoryLogic.getConfiguration() == 4)
                            tl.add(TextComponentUtil.translationWithColor(TextFormatting.YELLOW, "gcyl.machine.ore_factory.config.4"));

                    tl.add(TextComponentUtil.translationWithColor(TextFormatting.AQUA, "gcyl.machine.ore_factory.max_amount", this.oreFactoryLogic.getComparator()));
                })
                .addProgressLine(getProgressPercent() / 100.0);
    }

    @Override
    public IItemHandlerModifiable getImportItems() {
        return this.inputInventory;
    }

    @Override
    protected boolean shouldShowVoidingModeButton() {
        return false;
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        initializeAbilities();
        if(this.oreFactoryLogic.getConfiguration() == 0) {
            this.oreFactoryLogic.setMaxProgress(20);
        }
        else if(this.oreFactoryLogic.getConfiguration() == 1) {
            this.oreFactoryLogic.setMaxProgress(15);
        }
        /*else if(this.oreFactoryLogic.getConfiguration() == 2) {
            this.oreFactoryLogic.setMaxProgress(1200);
        }*/
        else if(this.oreFactoryLogic.getConfiguration() == 3) {
            this.oreFactoryLogic.setMaxProgress(12);
        }
        else {
            this.oreFactoryLogic.setMaxProgress(17);
        }

        if(this.energyContainer.getInputVoltage() % 2147483647 == 0) {
            long fix = this.energyContainer.getInputVoltage() / 2147483647;
            this.tier = (int) (Math.log((this.energyContainer.getInputVoltage() + fix) * 2) / Math.log(4) - 2);
        }
        else {
            this.tier = (int) (Math.log(this.energyContainer.getInputVoltage() * 2) / Math.log(4) - 2);
        }

        this.energyToDrain = (long) (Math.pow(4, this.tier + 2) / 2.0);
    }

    public void setVoltageTier(int tier) {
        this.voltageTier = tier;
    }

    @Override
    public IItemHandlerModifiable getExportItems() {
        return this.outputInventory;
    }

    private void initializeAbilities() {
        this.inputFluidInventory = new FluidTankList(false, getAbilities(MultiblockAbility.IMPORT_FLUIDS));
        this.inputInventory = new ItemHandlerList(getAbilities(MultiblockAbility.IMPORT_ITEMS));
        this.outputInventory = new ItemHandlerList(getAbilities(MultiblockAbility.EXPORT_ITEMS));
        List<IEnergyContainer> powerInput = new ArrayList<>(getAbilities(MultiblockAbility.INPUT_ENERGY));
        powerInput.addAll(getAbilities(MultiblockAbility.INPUT_LASER));
        this.energyContainer = new EnergyContainerList(powerInput);
        this.setVoltageTier(GTUtility.getTierByVoltage(this.energyContainer.getInputVoltage()));
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        resetTileAbilities();
        this.oreFactoryLogic.invalidate();
    }

    @Override
    public int getProgress() {
        return oreFactoryLogic.getProgressTime();
    }

    @Override
    public int getMaxProgress() {
        return oreFactoryLogic.getMaxProgress();
    }

    public int getProgressPercent() {return oreFactoryLogic.getProgressPercent();}

    @Override
    public boolean isActive() {
        return super.isActive() && this.oreFactoryLogic.isActive();
    }

    private void resetTileAbilities() {
        this.inputInventory = new ItemStackHandler(0);
        this.inputFluidInventory = new FluidTankList(true);
        this.outputInventory = new ItemStackHandler(0);
        this.energyContainer = new EnergyContainerList(Lists.newArrayList());
    }

    @Override
    public boolean isWorkingEnabled() {
        return this.oreFactoryLogic.isWorkingEnabled();
    }

    @Override
    public void setWorkingEnabled(boolean isWorkingAllowed) {
        this.oreFactoryLogic.setWorkingEnabled(isWorkingAllowed);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing side) {
        if (capability == GregtechTileCapabilities.CAPABILITY_WORKABLE)
            return GregtechTileCapabilities.CAPABILITY_WORKABLE.cast(this);
        if (capability == GregtechTileCapabilities.CAPABILITY_CONTROLLABLE)
            return GregtechTileCapabilities.CAPABILITY_CONTROLLABLE.cast(this);
        return super.getCapability(capability, side);
    }


    public boolean drainEnergy(boolean simulate) {
        long resultEnergy = energyContainer.getEnergyStored() - this.energyToDrain;
        if (resultEnergy >= 0L && resultEnergy <= energyContainer.getEnergyCapacity()) {
            if (!simulate)
                energyContainer.changeEnergy(-this.energyToDrain);
            return true;
        }
        return false;
    }

    @Override
    public IMultipleTankHandler getImportFluidHandler() {
        return this.inputFluidInventory;
    }

    @Override
    public IItemHandlerModifiable getOutputInventory() {
        return this.outputInventory;
    }

    @Override
    public IItemHandlerModifiable getInputInventory() {
        return this.inputInventory;
    }

    @Override
    public int getVoltageTier() {
        return this.tier;
    }

    @Override
    public boolean onScrewdriverClick(EntityPlayer playerIn, EnumHand hand, EnumFacing facing,
                                      CuboidRayTraceResult hitResult) {
        if(!getWorld().isRemote) {
            if(this.oreFactoryLogic.getConfiguration() == 0 && !this.oreFactoryLogic.isActive()) {
                this.oreFactoryLogic.setConfiguration(1);
                invalidateStructure();
                playerIn.sendStatusMessage(
                        new TextComponentTranslation("gcyl.machine.ore_factory.config.1"), false);
            }
            /*
            else if(this.oreFactoryLogic.getConfiguration() == 1 && !this.oreFactoryLogic.isActive()) {
                this.oreFactoryLogic.setConfiguration(2);
                invalidateStructure();
                playerIn.sendStatusMessage(
                        new TextComponentTranslation("gcyl.machine.ore_factory.config.2"), false);
            }
             */
            else if(this.oreFactoryLogic.getConfiguration() == 1 && !this.oreFactoryLogic.isActive()) {
                this.oreFactoryLogic.setConfiguration(3);
                invalidateStructure();
                playerIn.sendStatusMessage(
                        new TextComponentTranslation("gcyl.machine.ore_factory.config.3"), false);
            }
            else if(this.oreFactoryLogic.getConfiguration() == 3 && !this.oreFactoryLogic.isActive()) {
                this.oreFactoryLogic.setConfiguration(4);
                invalidateStructure();
                playerIn.sendStatusMessage(
                        new TextComponentTranslation("gcyl.machine.ore_factory.config.4"), false);
            }
            else if(!this.oreFactoryLogic.isActive()){
                this.oreFactoryLogic.setConfiguration(0);
                invalidateStructure();
                playerIn.sendStatusMessage(
                        new TextComponentTranslation("gcyl.machine.ore_factory.config.0"), false);
            }
        }
        return true;
    }

    @Override
    protected void updateFormedValid() {
        if (!getWorld().isRemote) {
            this.oreFactoryLogic.updateLogic();
            if(this.oreFactoryLogic.wasActiveAndNeedsUpdate()) {
                this.oreFactoryLogic.setWasActiveAndNeedsUpdate(false);
                this.oreFactoryLogic.setActive(false);
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("gcyl.multiblock.opf.description.1", this.oreFactoryLogic.getMaxParallel(), this.oreFactoryLogic.maxParallelTier()));
        tooltip.add(TooltipHelper.RAINBOW_FAST + I18n.format("gcyl.multiblock.opf.description.10"));
        tooltip.add(I18n.format("gcyl.multiblock.opf.description.6"));
        tooltip.add(I18n.format("gcyl.multiblock.opf.description.2"));
        tooltip.add(I18n.format("gcyl.multiblock.opf.description.9"));
        tooltip.add(I18n.format("gcyl.multiblock.opf.description.7"));
        tooltip.add(I18n.format("gcyl.multiblock.opf.description.8"));
        tooltip.add(I18n.format("gcyl.multiblock.opf.description.3"));
        //tooltip.add(I18n.format("gcyl.multiblock.opf.description.4"));
        tooltip.add(I18n.format("gcyl.multiblock.opf.description.5"));
        tooltip.add(I18n.format("gregtech.machine.power_substation.tooltip6") + TooltipHelper.RAINBOW_SLOW +
                I18n.format("gregtech.machine.power_substation.tooltip6.5"));
    }

    @SideOnly(Side.CLIENT)
    @Override
    protected @NotNull ICubeRenderer getFrontOverlay() {
        return Textures.LARGE_MINER_OVERLAY_BASIC;
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        this.oreFactoryLogic.writeInitialSyncData(buf);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.oreFactoryLogic.receiveInitialSyncData(buf);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        return this.oreFactoryLogic.writeToNBT(data);
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.oreFactoryLogic.readFromNBT(data);
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        getFrontOverlay().renderOrientedState(renderState, translation, pipeline, getFrontFacing(), this.oreFactoryLogic.isActive(), this.oreFactoryLogic.isWorkingEnabled());
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        if (dataId == GregtechDataCodes.WORKABLE_ACTIVE) {
            this.oreFactoryLogic.setActive(buf.readBoolean());
            scheduleRenderUpdate();
        } else if (dataId == GregtechDataCodes.WORKING_ENABLED) {
            this.oreFactoryLogic.setWorkingEnabled(buf.readBoolean());
            scheduleRenderUpdate();
        }

    }


}
