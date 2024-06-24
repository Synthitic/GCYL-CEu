package com.fulltrix.gcyl.api.multi;

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
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class OreFactoryLogic {
    private static final int ORES_PER_VOTAGE_TIER = 64;
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
                Recipe recipe = map.findRecipe(32, Collections.singletonList(item), Collections.emptyList());
                if (recipe != null && !recipe.getOutputs().isEmpty()) {
                    //for(int x = 0; x < item.getCount(); x++) {
                        for (ItemStack outputStack : recipe.getResultItemOutputs(GTUtility.getTierByVoltage(recipe.getEUt()), tier, map)) {
                            outputStack = outputStack.copy();
                            if (fortuneLevel > 0) {
                                outputStack.grow(outputStack.getCount() * fortuneLevel);
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

    protected void simulationWash(List<ItemStack> drops, int fortuneLevel, RecipeMap<?> map, int tier, List<ItemStack> itemStack, List<ItemStack> finalItemStack) {
        if(itemStack != null) {
            for (ItemStack item : itemStack) {
                Recipe recipe = map.findRecipe(32, Collections.singletonList(item), Collections.singletonList(Materials.DistilledWater.getFluid(100)));
                if (recipe != null && !recipe.getOutputs().isEmpty()) {
                    //for(int x = 0; x < item.getCount(); x++) {
                        for (ItemStack outputStack : recipe.getResultItemOutputs(GTUtility.getTierByVoltage(recipe.getEUt()), tier, map)) {
                            outputStack = outputStack.copy();
                            if (fortuneLevel > 0) {
                                outputStack.grow(outputStack.getCount() * fortuneLevel);
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
                simulation(drops, 0, RecipeMap.getByName("macerator"), getVoltageTier(), input, finalOutput);
                simulationWash(drops1, 0, RecipeMap.getByName("ore_washer"), getVoltageTier(), drops, finalOutput);
                simulation(drops2, 0, RecipeMap.getByName("thermal_centrifuge"), getVoltageTier(), drops1, finalOutput);
                simulation(drops3, 0, RecipeMap.getByName("macerator"), getVoltageTier(), drops2, finalOutput);
            }

            if (configuration == 1) {
                simulation(drops, 0, RecipeMap.getByName("macerator"), getVoltageTier(), input, finalOutput);
                simulationWash(drops1, 0, RecipeMap.getByName("ore_washer"), getVoltageTier(), drops, finalOutput);
                simulation(drops2, 0, RecipeMap.getByName("macerator"), getVoltageTier(), drops1, finalOutput);
                simulation(drops3, 0, RecipeMap.getByName("centrifuge"), getVoltageTier(), drops2, finalOutput);
            }

            /*
            if (configuration == 2) {
                simulation(drops, 0, RecipeMap.getByName("macerator"), getVoltageTier(), input, finalOutput);
                simulationWash(drops1, 0, RecipeMap.getByName("ore_washer"), getVoltageTier(), drops, finalOutput);
                simulation(drops2, 0, RecipeMap.getByName("sifter"), getVoltageTier(), drops1, finalOutput);
            }
             */

            if (configuration == 3) {
                simulation(drops, 0, RecipeMap.getByName("macerator"), getVoltageTier(), input, finalOutput);
                simulation(drops1, 0, RecipeMap.getByName("macerator"), getVoltageTier(), drops, finalOutput);
                simulation(drops2, 0, RecipeMap.getByName("centrifuge"), getVoltageTier(), drops1, finalOutput);
            }

            if (configuration == 4) {
                simulation(drops, 0, RecipeMap.getByName("macerator"), getVoltageTier(), input, finalOutput);
                simulation(drops1, 0, RecipeMap.getByName("thermal_centrifuge"), getVoltageTier(), drops, finalOutput);
                simulation(drops2, 0, RecipeMap.getByName("macerator"), getVoltageTier(), drops1, finalOutput);
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
        int comparator = (int) Math.pow(2, getVoltageTier());
        for (int i = 0; i < handler.getSlots(); i++) {
            if (x < comparator) {
                if (x + handler.getStackInSlot(i).getCount() >= comparator) {
                    x += handler.extractItem(i, comparator - x, true).getCount();
                } else {
                    x += handler.extractItem(i, handler.getStackInSlot(i).getCount(), true).getCount();
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
            if (!itemStack.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public List<ItemStack> consumeGetInput(IItemHandlerModifiable handler) {
        List<ItemStack> itemStacks = new ArrayList<>();
        int x = 0;
        //int comparator = ORES_PER_VOTAGE_TIER * getVoltageTier();
        int comparator = (int) Math.pow(2, getVoltageTier());
        for (int i = 0; i < handler.getSlots(); i++) {
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
