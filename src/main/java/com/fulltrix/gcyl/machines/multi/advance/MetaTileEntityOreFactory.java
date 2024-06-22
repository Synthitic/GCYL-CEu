package com.fulltrix.gcyl.machines.multi.advance;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import com.fulltrix.gcyl.api.multi.IOreFactoryProvider;
import com.fulltrix.gcyl.api.multi.OreFactoryLogic;
import com.google.common.collect.Lists;
import gregtech.api.GTValues;
import gregtech.api.capability.*;
import gregtech.api.capability.impl.EnergyContainerList;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.capability.impl.ItemHandlerList;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.util.GTTransferUtils;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.BlockMachineCasing;
import gregtech.common.blocks.MetaBlocks;
import groovy.transform.NullCheck;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
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
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

//TODO: multiblock pattern. proper tiering. different processing durations. fluid input requirements. make sifting work. fix problems with input (does not take items from beyond the first slot)

public class MetaTileEntityOreFactory extends MultiblockWithDisplayBase implements IWorkable, IOreFactoryProvider {

    private IMultipleTankHandler inputFluidInventory;

    private IItemHandlerModifiable inputInventory;

    private IItemHandlerModifiable outputInventory;

    private IEnergyContainer energyContainer;

    protected final MetaTileEntity metaTileEntity;

    private int voltageTier;

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

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("CCC", "CCC", "CCC")
                .aisle("CCC", "C#C", "CCC")
                .aisle("CCC", "CSC", "CCC")
                .where('S', selfPredicate())
                .where('C', states(MetaBlocks.MACHINE_CASING.getState(BlockMachineCasing.MachineCasingType.ULV))
                .setMinGlobalLimited(10)
                .or(autoAbilities(true, true, true, true, true, true ,false)))
                .where('#', air())
                .build();
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
        return Textures.VOLTAGE_CASINGS[0];
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        if (this.isStructureFormed() && !this.hasMaintenanceProblems()) {
            if (energyContainer != null && energyContainer.getEnergyCapacity() > 0) {
                long maxVoltage = energyContainer.getInputVoltage();
                String voltageName = GTValues.VN[GTUtility.getTierByVoltage(maxVoltage)];
                textList.add(new TextComponentTranslation("gregtech.multiblock.max_energy_per_tick", maxVoltage, voltageName));
            }
            textList.add(new TextComponentTranslation("gregtech.multiblock.universal.energy_used", GTValues.V[this.voltageTier]));
        }

        super.addDisplayText(textList);
    }

    @Override
    public IItemHandlerModifiable getImportItems() {
        return this.inputInventory;
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        initializeAbilities();
        this.oreFactoryLogic.setMaxProgress(100);
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
        this.energyContainer = new EnergyContainerList(getAbilities(MultiblockAbility.INPUT_ENERGY));
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
        long energyToDrain = GTValues.V[getVoltageTier()];
        long resultEnergy = energyContainer.getEnergyStored() - energyToDrain;
        if (resultEnergy >= 0L && resultEnergy <= energyContainer.getEnergyCapacity()) {
            if (!simulate)
                energyContainer.changeEnergy(-energyToDrain);
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
        return GTUtility.getFloorTierByVoltage(energyContainer.getInputVoltage());
    }

    @Override
    public boolean onScrewdriverClick(EntityPlayer playerIn, EnumHand hand, EnumFacing facing,
                                      CuboidRayTraceResult hitResult) {
        if(!getWorld().isRemote) {
            if(this.oreFactoryLogic.getConfiguration() == 0) {
                this.oreFactoryLogic.setConfiguration(1);
                playerIn.sendStatusMessage(
                        new TextComponentTranslation("gcyl.machine.ore_factory.config.1"), false);
            }
            /*
            else if(this.oreFactoryLogic.getConfiguration() == 1) {
                this.oreFactoryLogic.setConfiguration(2);
                playerIn.sendStatusMessage(
                        new TextComponentTranslation("gcyl.machine.ore_factory.config.2"), false);
            }
             */
            else if(this.oreFactoryLogic.getConfiguration() == 1) {
                this.oreFactoryLogic.setConfiguration(3);
                playerIn.sendStatusMessage(
                        new TextComponentTranslation("gcyl.machine.ore_factory.config.3"), false);
            }
            else if(this.oreFactoryLogic.getConfiguration() == 3) {
                this.oreFactoryLogic.setConfiguration(4);
                playerIn.sendStatusMessage(
                        new TextComponentTranslation("gcyl.machine.ore_factory.config.4"), false);
            }
            else {
                this.oreFactoryLogic.setConfiguration(0);
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
        tooltip.add(I18n.format("gcyl.multiblock.opf.description.1"));
        tooltip.add(I18n.format("gcyl.multiblock.opf.description.2"));
        tooltip.add(I18n.format("gcyl.multiblock.opf.description.3"));
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
