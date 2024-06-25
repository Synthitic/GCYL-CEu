package com.fulltrix.gcyl.api.multi;

import com.fulltrix.gcyl.Tags;
import com.fulltrix.gcyl.api.recipes.CachedRecipes;
import com.fulltrix.gcyl.machines.multi.advance.MetaTileEntityOreFactory;
import gregtech.api.capability.GregtechDataCodes;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.multiblock.IMaintenance;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.util.GTLog;
import gregtech.api.util.GTTransferUtils;
import gregtech.api.util.GTUtility;
import gregtech.common.ConfigHolder;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static gregtech.api.recipes.RecipeMaps.*;

public class OreFactoryLogic {
    private static final int MAX_PARALLEL = 2048;
    private static final String MAX_PARALLEL_STRING = "UXV";
    private final MetaTileEntity metaTileEntity;
    private final boolean hasMaintenance;
    private boolean isActive;
    private boolean isWorkingEnabled = true;
    private boolean wasActiveAndNeedsUpdate;
    private boolean hasNotEnoughEnergy;
    private int maxProgress = 0;
    private int progressTime = 0;
    private int configuration = 0;

    private List<ItemStack> input = new ArrayList<>();

    public OreFactoryLogic(MetaTileEntityOreFactory metaTileEntity) {
        this.metaTileEntity = metaTileEntity;
        this.hasMaintenance = ConfigHolder.machines.enableMaintenance &&
                ((IMaintenance) metaTileEntity).hasMaintenanceMechanics();
    }

    protected void simulation(List<ItemStack> drops, int fortuneLevel, RecipeMap<?> map, int tier, List<ItemStack> itemStack, List<ItemStack> finalItemStack) {
        if(itemStack != null) {
            for (ItemStack item : itemStack) {
                OrePrefix orePrefix = OreDictUnifier.getPrefix(item);
                //Recipe recipe = map.findRecipe(32, Collections.singletonList(item), Collections.emptyList());
                Recipe recipe = null;
                String metadata = item.getMetadata() + item.getItem().getCreatorModId(item);
                if(map == THERMAL_CENTRIFUGE_RECIPES) {
                    if(orePrefix == OrePrefix.crushed) {
                        recipe = CachedRecipes.cachedThermalCentrifugeCrushedRecipes.get(metadata);
                    } else {
                        recipe = CachedRecipes.cachedThermalCentrifugeCrushedPurifiedRecipes.get(metadata);
                    }
                }
                else if(map == MACERATOR_RECIPES) {
                    if(orePrefix == OrePrefix.crushed) {
                        recipe = CachedRecipes.cachedMaceratorCrushedRecipes.get(metadata);
                    } else if(orePrefix == OrePrefix.crushedPurified) {
                        recipe = CachedRecipes.cachedMaceratorCrushedPurifiedRecipes.get(metadata);
                    } else {
                        recipe = CachedRecipes.cachedMaceratorCrushedCentrifugedRecipes.get(metadata);
                    }
                }
                else if(map == CENTRIFUGE_RECIPES) {
                    if(orePrefix == OrePrefix.dustPure) {
                        recipe = CachedRecipes.cachedCentrifugePureRecipes.get(metadata);
                    } else {
                        recipe = CachedRecipes.cachedCentrifugeImpureRecipes.get(metadata);
                    }
                }
                if (recipe != null && !recipe.getOutputs().isEmpty()) {
                    //for(int x = 0; x < item.getCount(); x++) {
                        for (ItemStack outputStack : recipe.getResultItemOutputs(GTUtility.getTierByVoltage(recipe.getEUt()), tier, map)) {
                            outputStack = outputStack.copy();
                            if (fortuneLevel > 1) {
                                outputStack.setCount(outputStack.getCount() * fortuneLevel);
                            }
                            //if (OreDictUnifier.getPrefix(outputStack) == OrePrefix.dust || OreDictUnifier.getPrefix(outputStack) == OrePrefix.gemExquisite || OreDictUnifier.getPrefix(outputStack) == OrePrefix.gemFlawless || OreDictUnifier.getPrefix(outputStack) == OrePrefix.gem) {
                            if (OreDictUnifier.getPrefix(outputStack) == OrePrefix.dust) {
                                outputStack.setCount(outputStack.getCount() * item.getCount());
                                finalItemStack.add(outputStack);
                            } else {
                                outputStack.setCount(outputStack.getCount() * item.getCount());
                                drops.add(outputStack);
                            }
                        }
                    //}
                }
            }
        }
    }

    protected void simulationFirst(List<ItemStack> drops, int fortuneLevel, int tier, List<ItemStack> itemStack, List<ItemStack> finalItemStack) {
        if(itemStack != null) {
            for (ItemStack item : itemStack) {
                OrePrefix orePrefix = OreDictUnifier.getPrefix(item);
                if(orePrefix == OrePrefix.ore || orePrefix == OrePrefix.oreNetherrack || orePrefix == OrePrefix.oreEndstone
                        || orePrefix == OrePrefix.oreGranite
                        || orePrefix == OrePrefix.oreAndesite
                        || orePrefix == OrePrefix.oreBlackgranite
                        || orePrefix == OrePrefix.oreRedgranite
                        || orePrefix == OrePrefix.oreBasalt
                        || orePrefix == OrePrefix.oreDiorite
                        || orePrefix == OrePrefix.oreMarble
                        || orePrefix == OrePrefix.oreSand
                        || orePrefix == OrePrefix.oreRedSand) {
                    //Recipe recipe = MACERATOR_RECIPES.findRecipe(32, Collections.singletonList(item), Collections.emptyList());
                    Recipe recipe = CachedRecipes.cachedMaceratorOreRecipes.get(item.getItem());
                    if (recipe != null && !recipe.getOutputs().isEmpty()) {
                        //for(int x = 0; x < item.getCount(); x++) {
                        for (ItemStack outputStack : recipe.getResultItemOutputs(GTUtility.getTierByVoltage(recipe.getEUt()), tier, MACERATOR_RECIPES)) {
                            outputStack = outputStack.copy();
                            if (fortuneLevel > 1) {
                                outputStack.setCount(outputStack.getCount() * fortuneLevel);
                            }
                            //if (OreDictUnifier.getPrefix(outputStack) == OrePrefix.dust || OreDictUnifier.getPrefix(outputStack) == OrePrefix.gemExquisite || OreDictUnifier.getPrefix(outputStack) == OrePrefix.gemFlawless || OreDictUnifier.getPrefix(outputStack) == OrePrefix.gem) {
                            if (OreDictUnifier.getPrefix(outputStack) == OrePrefix.dust) {
                                outputStack.setCount(outputStack.getCount() * item.getCount());
                                finalItemStack.add(outputStack);
                            } else {
                                outputStack.setCount(outputStack.getCount() * item.getCount());
                                drops.add(outputStack);
                            }
                        }
                        //}
                    }
                }
                else {
                    if (fortuneLevel > 1) {
                        item.setCount(item.getCount() * fortuneLevel);
                    }
                    drops.add(item);
                }
            }
        }
    }

    protected void simulationWash(List<ItemStack> drops, int fortuneLevel, int tier, List<ItemStack> itemStack, List<ItemStack> finalItemStack) {
        if(itemStack != null) {
            for (ItemStack item : itemStack) {
                //Recipe recipe = ORE_WASHER_RECIPES.findRecipe(32, Collections.singletonList(item), Collections.singletonList(Materials.DistilledWater.getFluid(100)));
                String metadata = item.getMetadata() + item.getItem().getCreatorModId(item);
                Recipe recipe = CachedRecipes.cachedWasherRecipes.get(metadata);
                if (recipe != null && !recipe.getOutputs().isEmpty()) {
                    //for(int x = 0; x < item.getCount(); x++) {
                        for (ItemStack outputStack : recipe.getResultItemOutputs(GTUtility.getTierByVoltage(recipe.getEUt()), tier, ORE_WASHER_RECIPES)) {
                            outputStack = outputStack.copy();
                            if (fortuneLevel > 1) {
                                outputStack.setCount(outputStack.getCount() * fortuneLevel);
                            }
                            if (OreDictUnifier.getPrefix(outputStack) == OrePrefix.dust) {
                                outputStack.setCount(outputStack.getCount() * item.getCount());
                                finalItemStack.add(outputStack);
                            } else {
                                outputStack.setCount(outputStack.getCount() * item.getCount());
                                drops.add(outputStack);
                            }
                        }
                    //}
                }
            }
        }
    }

    public void updateLogic() {
        if(!this.isWorkingEnabled) {
            return;
        }

        if (hasMaintenance && ((IMaintenance) metaTileEntity).getNumMaintenanceProblems() > 1) return;

        if(!consumeEnergy(true)) {
            if (progressTime >= 2) {
                if (ConfigHolder.machines.recipeProgressLowEnergy) this.progressTime = 1;
                else this.progressTime = Math.max(1, progressTime - 2);
            }
            return;
        }


        if(!checkInput() && progressTime == 0) {
            setActive(false);
            return;
        }


        if(progressTime == 0 && !checkFluidInput(getInputInventory())) {
            setActive(false);
            return;
        }


            setActive(true);

            consumeEnergy(false);

            if (progressTime == 0) {
                input = consumeGetInput(getInputInventory());
            }

            progressTime++;
            if (progressTime % getMaxProgress() != 0) return;
            progressTime = 0;

            List<ItemStack> drops = NonNullList.create();
            List<ItemStack> drops1 = NonNullList.create();
            List<ItemStack> drops2 = NonNullList.create();
            List<ItemStack> drops3 = NonNullList.create();
            List<ItemStack> finalOutput = NonNullList.create();
            NonNullList<ItemStack> finalOutput2 = NonNullList.create();

            if (configuration == 0) {
                simulationFirst(drops, calculateFortune(), getVoltageTier(), input, finalOutput);
                simulationWash(drops1, 0, getVoltageTier(), drops, finalOutput);
                simulation(drops2, 0, THERMAL_CENTRIFUGE_RECIPES, getVoltageTier(), drops1, finalOutput);
                simulation(drops3, 0, MACERATOR_RECIPES, getVoltageTier(), drops2, finalOutput);
            }

            if (configuration == 1) {
                simulationFirst(drops, calculateFortune(), getVoltageTier(), input, finalOutput);
                simulationWash(drops1, 0, getVoltageTier(), drops, finalOutput);
                simulation(drops2, 0, MACERATOR_RECIPES, getVoltageTier(), drops1, finalOutput);
                simulation(drops3, 0, CENTRIFUGE_RECIPES, getVoltageTier(), drops2, finalOutput);
            }

            /*
            if (configuration == 2) {
                simulation(drops, 0, RecipeMap.getByName("macerator"), getVoltageTier(), input, finalOutput);
                simulationWash(drops1, 0, RecipeMap.getByName("ore_washer"), getVoltageTier(), drops, finalOutput);
                simulation(drops2, 0, RecipeMap.getByName("sifter"), getVoltageTier(), drops1, finalOutput);
            }
             */

            if (configuration == 3) {
                simulationFirst(drops, calculateFortune(), getVoltageTier(), input, finalOutput);
                simulation(drops1, 0, MACERATOR_RECIPES, getVoltageTier(), drops, finalOutput);
                simulation(drops2, 0, CENTRIFUGE_RECIPES, getVoltageTier(), drops1, finalOutput);
            }

            if (configuration == 4) {
                simulationFirst(drops, calculateFortune(), getVoltageTier(), input, finalOutput);
                simulation(drops1, 0, THERMAL_CENTRIFUGE_RECIPES, getVoltageTier(), drops, finalOutput);
                simulation(drops2, 0, MACERATOR_RECIPES, getVoltageTier(), drops1, finalOutput);
            }


                finalOutput2.addAll(finalOutput);


            insertItems(finalOutput2);


    }

    private void insertItems(NonNullList<ItemStack> itemDrops) {

            GTTransferUtils.addItemsToItemHandler(getOutputInventory(), false, itemDrops);

    }

    public boolean checkFluidInput(IItemHandlerModifiable handler) {
        int x = 0;
        //int comparator = ORES_PER_VOTAGE_TIER * getVoltageTier();
        int comparator = getComparator();
        for (int i = 0; i < handler.getSlots(); i++) {
            ItemStack itemStack = handler.getStackInSlot(i);
            OrePrefix orePrefix = OreDictUnifier.getPrefix(itemStack);
            if (!itemStack.isEmpty() && (orePrefix == OrePrefix.ore || orePrefix == OrePrefix.crushed || orePrefix == OrePrefix.oreNetherrack || orePrefix == OrePrefix.oreEndstone
                    || orePrefix == OrePrefix.oreGranite
                    || orePrefix == OrePrefix.oreAndesite
                    || orePrefix == OrePrefix.oreBlackgranite
                    || orePrefix == OrePrefix.oreRedgranite
                    || orePrefix == OrePrefix.oreBasalt
                    || orePrefix == OrePrefix.oreDiorite
                    || orePrefix == OrePrefix.oreMarble
                    || orePrefix == OrePrefix.oreSand
                    || orePrefix == OrePrefix.oreRedSand)) {
                if (x < comparator) {
                    if (x + itemStack.getCount() >= comparator) {
                        x = comparator;
                    } else {
                        x += itemStack.getCount();
                    }
                }
            }
        }

        FluidStack canDrainDistilledWater = getImportFluidHandler().drain(Materials.DistilledWater.getFluid(100 * x), false);
        FluidStack canDrainLubricant = getImportFluidHandler().drain(Materials.Lubricant.getFluid(x * 10), false);

        if(canDrainLubricant != null && canDrainDistilledWater != null ) {
            return canDrainLubricant.isFluidStackIdentical(Materials.Lubricant.getFluid(x * 10))
                    && canDrainDistilledWater.isFluidStackIdentical(Materials.DistilledWater.getFluid(100 * x));
        }
        return false;
    }

    public boolean checkInput() {

        for(int i = 0; i < getInputInventory().getSlots(); i++) {
            ItemStack itemStack = getInputInventory().getStackInSlot(i);
            OrePrefix orePrefix = OreDictUnifier.getPrefix(itemStack);
            if (!itemStack.isEmpty() && (orePrefix == OrePrefix.ore || orePrefix == OrePrefix.crushed || orePrefix == OrePrefix.oreNetherrack || orePrefix == OrePrefix.oreEndstone
                    || orePrefix == OrePrefix.oreGranite
                    || orePrefix == OrePrefix.oreAndesite
                    || orePrefix == OrePrefix.oreBlackgranite
                    || orePrefix == OrePrefix.oreRedgranite
                    || orePrefix == OrePrefix.oreBasalt
                    || orePrefix == OrePrefix.oreDiorite
                    || orePrefix == OrePrefix.oreMarble
                    || orePrefix == OrePrefix.oreSand
                    || orePrefix == OrePrefix.oreRedSand)) {
                return true;
            }
        }
        return false;
    }

    public List<ItemStack> consumeGetInput(IItemHandlerModifiable handler) {
        List<ItemStack> itemStacks = new ArrayList<>();
        int x = 0;
        //int comparator = ORES_PER_VOTAGE_TIER * getVoltageTier();
        int comparator = getComparator();
        for (int i = 0; i < handler.getSlots(); i++) {
            OrePrefix orePrefix = OreDictUnifier.getPrefix(handler.getStackInSlot(i));
            if(orePrefix == OrePrefix.ore || orePrefix == OrePrefix.crushed || orePrefix == OrePrefix.oreNetherrack || orePrefix == OrePrefix.oreEndstone
                    || orePrefix == OrePrefix.oreGranite
                    || orePrefix == OrePrefix.oreAndesite
                    || orePrefix == OrePrefix.oreBlackgranite
                    || orePrefix == OrePrefix.oreRedgranite
                    || orePrefix == OrePrefix.oreBasalt
                    || orePrefix == OrePrefix.oreDiorite
                    || orePrefix == OrePrefix.oreMarble
                    || orePrefix == OrePrefix.oreSand
                    || orePrefix == OrePrefix.oreRedSand) {
                if (x < comparator) {
                    if (x + handler.getStackInSlot(i).getCount() >= comparator) {
                        itemStacks.add(handler.extractItem(i, comparator - x, false));
                        x += comparator - x;
                    } else {
                        x += handler.getStackInSlot(i).getCount();
                        itemStacks.add(handler.extractItem(i, handler.getStackInSlot(i).getCount(), false));
                    }
                }
            }
        }

        getImportFluidHandler().drain(Materials.DistilledWater.getFluid(100 * x), true);
        getImportFluidHandler().drain(Materials.Lubricant.getFluid(x * 10), true);

        return itemStacks;
    }

    public void setWorkingEnabled(boolean workingEnabled) {
        this.isWorkingEnabled = workingEnabled;
        this.metaTileEntity.markDirty();
        World world = this.metaTileEntity.getWorld();
        if (world != null && !world.isRemote) {
            this.metaTileEntity.writeCustomData(GregtechDataCodes.WORKING_ENABLED,
                    buf -> buf.writeBoolean(workingEnabled));
        }
    }

    protected boolean consumeEnergy(boolean simulate) {
        return ((IOreFactoryProvider) metaTileEntity).drainEnergy(simulate);
    }

    protected IMultipleTankHandler getImportFluidHandler() {
        return ((IOreFactoryProvider) metaTileEntity).getImportFluidHandler();
    }

    protected IItemHandlerModifiable getInputInventory() {
        return ((IOreFactoryProvider) metaTileEntity).getInputInventory();
    }

    protected IItemHandlerModifiable getOutputInventory() {
        return ((IOreFactoryProvider) metaTileEntity).getOutputInventory();
    }

    public void setConfiguration(int config) {
        this.configuration = config;
    }

    public int getConfiguration() {
        return this.configuration;
    }

    protected int getVoltageTier() {
        return ((IOreFactoryProvider) metaTileEntity).getVoltageTier();
    }

    public int getComparator() {
        return (int) Math.min(Math.max(Math.pow(2, getVoltageTier() - 1) , 1), MAX_PARALLEL);
    }

    public int calculateFortune() {
        if(getVoltageTier() > 12 && !metaTileEntity.getWorld().isRemote) {
            int r = metaTileEntity.getWorld().rand.nextInt(100);
            if(r > 49) {
                return (int) ((getVoltageTier() - 11) / 2.0) + 1;
            }
            else {
                return (int) ((getVoltageTier() - 12) / 2.0) + 1;
            }
        }
        else return 0;
    }

    public void invalidate() {
        this.progressTime = 0;
        this.maxProgress = 0;
        this.input.clear();
        setActive(false);
    }

    public boolean isActive() {
        return this.isActive && isWorkingEnabled();
    }

    public void setActive(boolean active) {
        if (this.isActive != active) {
            this.isActive = active;
            this.metaTileEntity.markDirty();
            World world = this.metaTileEntity.getWorld();
            if (world != null && !world.isRemote) {
                this.metaTileEntity.writeCustomData(GregtechDataCodes.WORKABLE_ACTIVE, buf -> buf.writeBoolean(active));
            }
        }
    }

    public boolean wasActiveAndNeedsUpdate() {
        return this.wasActiveAndNeedsUpdate;
    }

    public void setWasActiveAndNeedsUpdate(boolean wasActiveAndNeedsUpdate) {
        this.wasActiveAndNeedsUpdate = wasActiveAndNeedsUpdate;
    }

    public boolean isWorkingEnabled() {
        return isWorkingEnabled;
    }

    public boolean isWorking() {
        return isActive && !hasNotEnoughEnergy && isWorkingEnabled;
    }

    public int getProgressTime() {
        return this.progressTime;
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    public int getMaxProgress() {
        return this.maxProgress;
    }

    public int getProgressPercent() {
        return (int) ((1.0F * getProgressTime() / getMaxProgress()) * 100);
    }

    public int getMaxParallel() {
        return MAX_PARALLEL;
    }

    public String maxParallelTier() {
        return MAX_PARALLEL_STRING;
    }

    public NBTTagCompound writeToNBT(@NotNull NBTTagCompound data) {
        data.setBoolean("isActive", this.isActive);
        data.setBoolean("isWorkingEnabled", this.isWorkingEnabled);
        data.setBoolean("wasActiveAndNeedsUpdate", this.wasActiveAndNeedsUpdate);
        data.setInteger("progressTime", progressTime);
        data.setInteger("maxProgress", maxProgress);
        data.setInteger("configuration", this.configuration);
        return data;
    }

    public void readFromNBT(@NotNull NBTTagCompound data) {
        this.isActive = data.getBoolean("isActive");
        this.isWorkingEnabled = data.getBoolean("isWorkingEnabled");
        this.wasActiveAndNeedsUpdate = data.getBoolean("wasActiveAndNeedsUpdate");
        this.progressTime = data.getInteger("progressTime");
        this.maxProgress = data.getInteger("maxProgress");
        this.configuration = data.getInteger("configuration");
    }

    public void writeInitialSyncData(@NotNull PacketBuffer buf) {
        buf.writeBoolean(this.isActive);
        buf.writeBoolean(this.isWorkingEnabled);
        buf.writeBoolean(this.wasActiveAndNeedsUpdate);
        buf.writeInt(this.progressTime);
        buf.writeInt(this.maxProgress);
        buf.writeInt(this.configuration);
    }

    public void receiveInitialSyncData(@NotNull PacketBuffer buf) {
        setActive(buf.readBoolean());
        setWorkingEnabled(buf.readBoolean());
        setWasActiveAndNeedsUpdate(buf.readBoolean());
        this.progressTime = buf.readInt();
        this.maxProgress = buf.readInt();
        this.configuration = buf.readInt();
    }

    public void receiveCustomData(int dataId, PacketBuffer buf) {
        if (dataId == GregtechDataCodes.IS_WORKING) {
            setActive(buf.readBoolean());
            metaTileEntity.scheduleRenderUpdate();
        }
    }
}
