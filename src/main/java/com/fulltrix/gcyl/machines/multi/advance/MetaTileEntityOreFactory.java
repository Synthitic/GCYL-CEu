package com.fulltrix.gcyl.machines.multi.advance;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
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

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

//TODO: multiblock pattern. proper tiering. different processing durations. fluid input requirements. make sifting work. fix problems with input (does not take items from beyond the first slot)

public class MetaTileEntityOreFactory extends MultiblockWithDisplayBase implements IControllable {

    protected IMultipleTankHandler inputFluidInventory;

    private IItemHandlerModifiable inputInventory;

    protected IItemHandlerModifiable outputInventory;

    private IEnergyContainer energyContainer;

    protected final MetaTileEntity metaTileEntity;

    private boolean isWorkingEnabled = true;

    private boolean isActive = false;

    private int voltageTier;

    private int configuration = 0;


    public MetaTileEntityOreFactory(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
        this.metaTileEntity = this;
        this.reinitializeStructurePattern();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityOreFactory(metaTileEntityId);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle(" CCC ", " CCC ", " CCC ", " CCC ")
                .aisle("CCCCC", "CDDDC", "C###C", "CGGGC")
                .aisle("CCCCC", "CDDDC", "C###C", "CGGGC")
                .aisle("CCCCC", "CDDDC", "C###C", "CGGGC")
                .aisle(" CCC ", " CSC ", " CCC ", " CCC ")
                .where('S', selfPredicate())
                .where('G', states(MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.TEMPERED_GLASS)))
                .where('D', states(Blocks.DIRT.getDefaultState(), Blocks.GRASS.getDefaultState()))
                .where('C', states(MetaBlocks.MACHINE_CASING.getState(BlockMachineCasing.MachineCasingType.ULV))
                .setMinGlobalLimited(42)
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
        return Textures.VOLTAGE_CASINGS[9];
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        if (this.isStructureFormed() && !this.hasMaintenanceProblems()) {
            if (energyContainer != null && energyContainer.getEnergyCapacity() > 0) {
                long maxVoltage = energyContainer.getInputVoltage();
                String voltageName = GTValues.VN[GTUtility.getTierByVoltage(maxVoltage)];
                textList.add(new TextComponentTranslation("gregtech.multiblock.max_energy_per_tick", maxVoltage, voltageName));
            }
            textList.add(new TextComponentTranslation("gregtech.multiblock.universal.energy_used", voltageTier));
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

        if (this.isActive())
            this.setActive(false);
    }

    @Override
    public boolean isActive() {
        return super.isActive() && this.isWorkingEnabled;
    }

    private void resetTileAbilities() {
        this.inputInventory = new ItemStackHandler(0);
        this.inputFluidInventory = new FluidTankList(true);
        this.outputInventory = new ItemStackHandler(0);
        this.energyContainer = new EnergyContainerList(Lists.newArrayList());
    }

    private void insertItems(NonNullList<ItemStack> itemDrops) {
        if (GTTransferUtils.addItemsToItemHandler(metaTileEntity.getExportItems(), true, itemDrops)) {
            GTTransferUtils.addItemsToItemHandler(metaTileEntity.getExportItems(), false, itemDrops);
        }
    }

    @Override
    public boolean isWorkingEnabled() {
        return isWorkingEnabled;
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

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing side) {
        if (capability == GregtechTileCapabilities.CAPABILITY_CONTROLLABLE) {
            return GregtechTileCapabilities.CAPABILITY_CONTROLLABLE.cast(this);
        }
        return super.getCapability(capability, side);
    }

    protected void setActive(boolean active) {
        if (this.isActive != active) {
            this.isActive = active;
            markDirty();
            World world = getWorld();
            if (world != null && !world.isRemote) {
                writeCustomData(GregtechDataCodes.WORKABLE_ACTIVE, buf -> buf.writeBoolean(active));
            }
        }
    }

    protected boolean drainStorages(boolean simulate) {
        return drainEnergy(simulate) && consumeInput(simulate);
    }


    public boolean drainEnergy(boolean simulate) {
        long energyToDrain = GTValues.VA[getEnergyTier()];
        long resultEnergy = energyContainer.getEnergyStored() - energyToDrain;
        if (resultEnergy >= 0L && resultEnergy <= energyContainer.getEnergyCapacity()) {
            if (!simulate)
                energyContainer.changeEnergy(-energyToDrain);
            return true;
        }
        return false;
    }

    public int getEnergyTier() {
        return GTUtility.getFloorTierByVoltage(energyContainer.getInputVoltage());
    }

    public boolean consumeInput(boolean simulate) {

        ItemStack itemStack = inputInventory.getStackInSlot(0);

        if(itemStack != null) {
            if(!simulate) {
                inputInventory.extractItem(0, inputInventory.getStackInSlot(0).getCount(), false);
            }
            return true;
        }

        return false;
    }

    @Override
    public boolean onScrewdriverClick(EntityPlayer playerIn, EnumHand hand, EnumFacing facing,
                                      CuboidRayTraceResult hitResult) {
        if(!getWorld().isRemote) {
            if(configuration == 0) {
                configuration = 1;
                playerIn.sendStatusMessage(
                        new TextComponentTranslation("gcyl.machine.ore_factory.config.1"), false);
            }
            else if(configuration == 1) {
                configuration = 2;
                playerIn.sendStatusMessage(
                        new TextComponentTranslation("gcyl.machine.ore_factory.config.2"), false);
            }
            else if(configuration == 2) {
                configuration = 3;
                playerIn.sendStatusMessage(
                        new TextComponentTranslation("gcyl.machine.ore_factory.config.3"), false);
            }
            else if(configuration == 3) {
                configuration = 4;
                playerIn.sendStatusMessage(
                        new TextComponentTranslation("gcyl.machine.ore_factory.config.4"), false);
            }
            else {
                configuration = 0;
                playerIn.sendStatusMessage(
                        new TextComponentTranslation("gcyl.machine.ore_factory.config.0"), false);
            }
        }
        return true;
    }

    protected void simulation(List<ItemStack> drops, int fortuneLevel, RecipeMap<?> map, int tier, List<ItemStack> itemStack, List<ItemStack> finalItemStack) {
        if(itemStack != null) {
            for (ItemStack item : itemStack) {
                Recipe recipe = map.findRecipe(Long.MAX_VALUE, Collections.singletonList(item), Collections.emptyList());
                if (recipe != null && !recipe.getOutputs().isEmpty()) {
                    for (ItemStack outputStack : recipe.getResultItemOutputs(GTUtility.getTierByVoltage(recipe.getEUt()), tier, map)) {
                        outputStack = outputStack.copy();
                        if (fortuneLevel > 0) {
                            outputStack.grow(outputStack.getCount() * fortuneLevel);
                        }
                        if (OreDictUnifier.getPrefix(outputStack) == OrePrefix.dust || OreDictUnifier.getPrefix(outputStack) == OrePrefix.gemExquisite) {
                            finalItemStack.add(outputStack);
                        }
                        else {
                            drops.add(outputStack);
                        }
                    }
                }
            }
        }
    }

    protected void simulationWash(List<ItemStack> drops, int fortuneLevel, RecipeMap<?> map, int tier, List<ItemStack> itemStack, List<ItemStack> finalItemStack) {
        if(itemStack != null) {
            for (ItemStack item : itemStack) {
                Recipe recipe = map.findRecipe(Long.MAX_VALUE, Collections.singletonList(item), Collections.singletonList(Materials.DistilledWater.getFluid(100)));
                if (recipe != null && !recipe.getOutputs().isEmpty()) {
                    for (ItemStack outputStack : recipe.getResultItemOutputs(GTUtility.getTierByVoltage(recipe.getEUt()), tier, map)) {
                        outputStack = outputStack.copy();
                        if (fortuneLevel > 0) {
                            outputStack.grow(outputStack.getCount() * fortuneLevel);
                        }
                        if (OreDictUnifier.getPrefix(outputStack) == OrePrefix.dust) {
                            finalItemStack.add(outputStack);
                        } else {
                            drops.add(outputStack);
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void updateFormedValid() {
        if (!getWorld().isRemote && this.getNumMaintenanceProblems() < 1 && isWorkingEnabled) {

            for (int i = 0; i < inputInventory.getSlots(); i++) {
                if(!inputInventory.getStackInSlot(i).isEmpty()) {
                    break;
                }
                if(i == inputInventory.getSlots() - 1) {
                    setActive(false);
                    return;
                }
            }

            if(!drainEnergy(true)) {
                if(isActive)
                    setActive(false);
                return;
            }

            setActive(true);

            List<ItemStack> drops = NonNullList.create();
            List<ItemStack> drops1 = NonNullList.create();
            List<ItemStack> drops2 = NonNullList.create();
            List<ItemStack> drops3 = NonNullList.create();
            List<ItemStack> finalOutput = NonNullList.create();
            NonNullList<ItemStack> finalOutput2 = NonNullList.create();

            if (configuration == 0) {
                simulation(drops, 0, RecipeMap.getByName("macerator"), voltageTier, GTUtility.itemHandlerToList(metaTileEntity.getImportItems()), finalOutput);
                simulationWash(drops1, 0, RecipeMap.getByName("ore_washer"), voltageTier, drops, finalOutput);
                simulation(drops2, 0, RecipeMap.getByName("thermal_centrifuge"), voltageTier, drops1, finalOutput);
                simulation(drops3, 0, RecipeMap.getByName("macerator"), voltageTier, drops2, finalOutput);
            }

            if (configuration == 1) {
                simulation(drops, 0, RecipeMap.getByName("macerator"), voltageTier, GTUtility.itemHandlerToList(metaTileEntity.getImportItems()), finalOutput);
                simulation(drops1, 0, RecipeMap.getByName("ore_washer"), voltageTier, drops, finalOutput);
                simulation(drops2, 0, RecipeMap.getByName("macerator"), voltageTier, drops1, finalOutput);
                simulation(drops3, 0, RecipeMap.getByName("centrifuge"), voltageTier, drops2, finalOutput);
            }

            if (configuration == 2) {
                simulation(drops, 0, RecipeMap.getByName("macerator"), voltageTier, GTUtility.itemHandlerToList(metaTileEntity.getImportItems()), finalOutput);
                simulationWash(drops1, 0, RecipeMap.getByName("ore_washer"), voltageTier, drops, finalOutput);
                simulation(drops2, 0, RecipeMap.getByName("sifter"), voltageTier, drops1, finalOutput);
            }

            if (configuration == 3) {
                simulation(drops, 0, RecipeMap.getByName("macerator"), voltageTier, GTUtility.itemHandlerToList(metaTileEntity.getImportItems()), finalOutput);
                simulation(drops1, 0, RecipeMap.getByName("macerator"), voltageTier, drops, finalOutput);
                simulation(drops2, 0, RecipeMap.getByName("centrifuge"), voltageTier, drops1, finalOutput);
            }

            if (configuration == 4) {
                simulation(drops, 0, RecipeMap.getByName("macerator"), voltageTier, GTUtility.itemHandlerToList(metaTileEntity.getImportItems()), finalOutput);
                simulation(drops1, 0, RecipeMap.getByName("thermal_centrifuge"), voltageTier, drops, finalOutput);
                simulation(drops2, 0, RecipeMap.getByName("macerator"), voltageTier, drops1, finalOutput);
            }

            //simulationPackager(finalOutput2, dustTiny)

            this.metaTileEntity.addNotifiedInput(this.inputInventory);

            if(configuration == 0 || configuration == 1) {
                for (ItemStack outputStack : drops3) {
                    outputStack = outputStack.copy();
                    outputStack.grow(outputStack.getCount() + 2 * inputInventory.getStackInSlot(0).getCount() - 2);
                    finalOutput2.add(outputStack);
                }
            }
            else {
                for (ItemStack outputStack : drops2) {
                    outputStack = outputStack.copy();
                    outputStack.grow(outputStack.getCount() + 2 * inputInventory.getStackInSlot(0).getCount() - 2);
                    finalOutput2.add(outputStack);
                }
            }


            for (ItemStack outputStack : finalOutput) {
                outputStack = outputStack.copy();
                outputStack.grow(outputStack.getCount() + 2*inputInventory.getStackInSlot(0).getCount() - 2);
                finalOutput2.add(outputStack);
            }

            insertItems(finalOutput2);
            drainEnergy(false);
            consumeInput(false);


        }
        else
            setActive(false);
    }

    @SideOnly(Side.CLIENT)
    @Override
    protected @NotNull ICubeRenderer getFrontOverlay() {
        return Textures.LARGE_MINER_OVERLAY_ADVANCED_2;
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeBoolean(isActive);
        buf.writeBoolean(this.isWorkingEnabled);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.isActive = buf.readBoolean();
        this.isWorkingEnabled = buf.readBoolean();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setBoolean("isActive", this.isActive);
        data.setBoolean("isWorkingEnabled", this.isWorkingEnabled);
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.isActive = data.getBoolean("isActive");
        this.isWorkingEnabled = data.getBoolean("isWorkingEnabled");
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        getFrontOverlay().renderOrientedState(renderState, translation, pipeline, getFrontFacing(), isActive, isWorkingEnabled);
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        if (dataId == GregtechDataCodes.WORKABLE_ACTIVE) {
            this.isActive = buf.readBoolean();
            scheduleRenderUpdate();
        } else if (dataId == GregtechDataCodes.WORKING_ENABLED) {
            this.isWorkingEnabled = buf.readBoolean();
            scheduleRenderUpdate();
        }
    }


}
