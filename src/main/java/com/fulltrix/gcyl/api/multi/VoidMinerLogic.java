package com.fulltrix.gcyl.api.multi;

import com.fulltrix.gcyl.machines.multi.miner.MetaTileEntityVoidMiner;
import com.fulltrix.gcyl.materials.GCYLMaterials;
import gregtech.api.capability.GregtechDataCodes;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.multiblock.ICleanroomProvider;
import gregtech.api.metatileentity.multiblock.IMaintenance;
import gregtech.common.ConfigHolder;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

import static com.fulltrix.gcyl.recipes.categories.handlers.VoidMinerHandler.*;
import static gregtech.api.util.GTTransferUtils.addItemsToItemHandler;

public class VoidMinerLogic {
    private static final int CONSUME_START = 100;
    private static final float MUD_MULTIPLIER = 1.22F;
    private final MetaTileEntity metaTileEntity;
    private final int minEnergyTier;
    private final boolean hasMaintenance;
    private boolean fluidOutputFull;
    private boolean isActive;
    private boolean isWorkingEnabled = true;
    private boolean wasActiveAndNeedsUpdate;
    private boolean hasNotEnoughEnergy;
    private boolean overheat = false;
    private final int maxTemperature;
    private int maxProgress = 0;
    private int progressTime = 0;
    private int temperature = 0;
    private boolean usingPyrotheum = true;
    private boolean hasConsume = false;

    private int currentDrillingFluid = CONSUME_START;

    public VoidMinerLogic(MetaTileEntityVoidMiner metaTileEntity, int tier, int temp) {
        this.metaTileEntity = metaTileEntity;
        this.minEnergyTier = tier;
        this.hasMaintenance = ConfigHolder.machines.enableMaintenance &&
                ((IMaintenance) metaTileEntity).hasMaintenanceMechanics();
        this.maxTemperature = temp;
    }

    public void updateLogic() {
        if (!this.isWorkingEnabled) {
            if(temperature > 0) {
                temperature--;
            }
            return;
        }

        // all maintenance problems not fixed means the machine does not run
        if (hasMaintenance && ((IMaintenance) metaTileEntity).getNumMaintenanceProblems() > 1) return;

        // drain the energy
        if (overheat || !consumeEnergy(true) || fluidOutputFull) {
            if (temperature > 0) {
                temperature -= (int) (currentDrillingFluid / 100.0);
            }
            if (temperature == 0) {
                overheat = false;
            }

            FluidStack usedDrillingMudFluid = GCYLMaterials.UsedDrillingMud.getFluid((int) currentDrillingFluid);
            int canFillUsedDrillingMud = getExportFluidHandler().fill(usedDrillingMudFluid, false);
            if(canFillUsedDrillingMud != 0 && canFillUsedDrillingMud == (int) currentDrillingFluid) {
                fluidOutputFull = false;
            }

            if (currentDrillingFluid > CONSUME_START) {
                currentDrillingFluid = (int) (currentDrillingFluid / MUD_MULTIPLIER);
            }
            if (currentDrillingFluid < CONSUME_START) {
                currentDrillingFluid = CONSUME_START;
            }

            if (progressTime >= 2) {
                if (ConfigHolder.machines.recipeProgressLowEnergy) this.progressTime = 1;
                else this.progressTime = Math.max(1, progressTime - 2);
            }
            hasNotEnoughEnergy = true;

            if (isActive)
                setActive(false);
        } else {
            if (temperature > maxTemperature) {
                overheat = true;
                currentDrillingFluid = CONSUME_START;
                return;
            }

            FluidStack pyrotheumFluid = GCYLMaterials.Pyrotheum.getFluid((int) currentDrillingFluid);
            FluidStack cryotheumFluid = GCYLMaterials.Cryotheum.getFluid((int) currentDrillingFluid);
            FluidStack drillingMudFluid = GCYLMaterials.DrillingMud.getFluid((int) currentDrillingFluid);
            FluidStack usedDrillingMudFluid = GCYLMaterials.UsedDrillingMud.getFluid((int) currentDrillingFluid);
            FluidStack canDrainPyrotheum = getImportFluidHandler().drain(pyrotheumFluid, false);
            FluidStack canDrainCryotheum = getImportFluidHandler().drain(cryotheumFluid, false);
            FluidStack canDrainDrillingMud = getImportFluidHandler().drain(drillingMudFluid, false);
            int canFillUsedDrillingMud = getExportFluidHandler().fill(usedDrillingMudFluid, false);
            //consume fluid
            if (canDrainDrillingMud != null && canDrainDrillingMud.amount == (int) currentDrillingFluid) {
                if(canFillUsedDrillingMud != 0 && canFillUsedDrillingMud == (int) currentDrillingFluid) {
                    fluidOutputFull = false;
                } else {
                    setActive(false);
                    fluidOutputFull = true;
                }
            } else {
                setActive(false);
                return;
            }

            if (!isActive)
                setActive(true);

            consumeEnergy(false);

            progressTime++;
            if (progressTime % getMaxProgress() != 0) return;
            progressTime = 0;

            getImportFluidHandler().drain(drillingMudFluid, true);
            getExportFluidHandler().fill(usedDrillingMudFluid, true);

            if (usingPyrotheum && canDrainPyrotheum != null && canDrainPyrotheum.amount == (int) currentDrillingFluid) {
                getImportFluidHandler().drain(pyrotheumFluid, true);
                temperature += (int) (currentDrillingFluid / 100.0);
                currentDrillingFluid = (int) (currentDrillingFluid * MUD_MULTIPLIER);
                hasConsume = true;
            } else if (temperature > 0 && canDrainCryotheum != null && canDrainCryotheum.amount == (int) currentDrillingFluid) {
                getImportFluidHandler().drain(cryotheumFluid, true);
                currentDrillingFluid = (int) (currentDrillingFluid / MUD_MULTIPLIER);
                temperature -= (int) (currentDrillingFluid / 100.0);
            }
            if (temperature < 0) {
                temperature = 0;
            }
            if (currentDrillingFluid < CONSUME_START) {
                currentDrillingFluid = CONSUME_START;
            }

            if(!hasConsume && usingPyrotheum) {
                float v = (float) (((MUD_MULTIPLIER - Math.floor(MUD_MULTIPLIER)) / 2.0) + Math.floor(MUD_MULTIPLIER));
                temperature -= (int) (currentDrillingFluid / 200.0);
                currentDrillingFluid = (int) (currentDrillingFluid / v);
                if (temperature < 0) {
                    temperature = 0;
                }
                if (currentDrillingFluid < CONSUME_START) {
                    currentDrillingFluid = CONSUME_START;
                }
                return;
            }

            usingPyrotheum = !usingPyrotheum;
            int nbOres = temperature / 1000;
            if (nbOres == 0) {
                return;
            }

            List<ItemStack> ores = getOres();
            Collections.shuffle(ores);
            ores.stream().limit(10).peek(itemStack -> itemStack.setCount(metaTileEntity.getWorld().rand.nextInt(nbOres * nbOres) + 1)).forEach(itemStack -> addItemsToItemHandler(getOutputInventory(), false, Collections.singletonList(itemStack)));


        }
    }

    protected IMultipleTankHandler getImportFluidHandler() {
        return ((IVoidMinerProvider) metaTileEntity).getImportFluidHandler();
    }

    protected IMultipleTankHandler getExportFluidHandler() {
        return ((IVoidMinerProvider) metaTileEntity).getExportFluidHandler();
    }

    protected IItemHandlerModifiable getOutputInventory() {
        return ((IVoidMinerProvider) metaTileEntity).getOutputInventory();
    }

    List<ItemStack> getOres() {
        return switch (this.minEnergyTier) {
            case 8 -> ORES;
            case 9 -> ORES_2;
            default -> ORES_3;
        };
    }


    protected boolean consumeEnergy(boolean simulate) {
        return ((IVoidMinerProvider) metaTileEntity).drainEnergy(simulate);
    }

    public void invalidate() {
        this.progressTime = 0;
        this.maxProgress = 0;
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

    public void setWorkingEnabled(boolean workingEnabled) {
        this.isWorkingEnabled = workingEnabled;
        this.metaTileEntity.markDirty();
        World world = this.metaTileEntity.getWorld();
        if (world != null && !world.isRemote) {
            this.metaTileEntity.writeCustomData(GregtechDataCodes.WORKING_ENABLED,
                    buf -> buf.writeBoolean(workingEnabled));
        }
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
        data.setTag("temperature", new NBTTagInt(temperature));
        data.setTag("currentDrillingFluid", new NBTTagDouble(currentDrillingFluid));
        data.setTag("overheat", new NBTTagInt(overheat ? 1 : 0));
        data.setBoolean("isActive", this.isActive);
        data.setBoolean("isWorkingEnabled", this.isWorkingEnabled);
        data.setBoolean("wasActiveAndNeedsUpdate", this.wasActiveAndNeedsUpdate);
        data.setBoolean("fluidOutputFull", this.fluidOutputFull);
        data.setBoolean("usingPyrotheum", this.usingPyrotheum);
        data.setBoolean("hasConsume", this.hasConsume);
        data.setInteger("progressTime", progressTime);
        data.setInteger("maxProgress", maxProgress);
        return data;
    }

    public void readFromNBT(@NotNull NBTTagCompound data) {
        this.temperature = data.getInteger("temperature");
        this.currentDrillingFluid = data.getInteger("currentDrillingFluid");
        this.overheat = data.getInteger("overheat") != 0;
        this.isActive = data.getBoolean("isActive");
        this.isWorkingEnabled = data.getBoolean("isWorkingEnabled");
        this.wasActiveAndNeedsUpdate = data.getBoolean("wasActiveAndNeedsUpdate");
        this.usingPyrotheum = data.getBoolean("usingPyrotheum");
        this.fluidOutputFull = data.getBoolean("fluidOutputFull");
        this.hasConsume = data.getBoolean("hasConsume");
        this.progressTime = data.getInteger("progressTime");
        this.maxProgress = data.getInteger("maxProgress");
    }

    public void writeInitialSyncData(@NotNull PacketBuffer buf) {
        buf.writeBoolean(this.isActive);
        buf.writeBoolean(this.isWorkingEnabled);
        buf.writeBoolean(this.wasActiveAndNeedsUpdate);
        buf.writeBoolean(this.overheat);
        buf.writeBoolean(this.fluidOutputFull);
        buf.writeBoolean(this.usingPyrotheum);
        buf.writeBoolean(this.hasConsume);
        buf.writeInt(this.progressTime);
        buf.writeInt(this.maxProgress);
        buf.writeInt(this.temperature);
        buf.writeInt(this.currentDrillingFluid);
    }

    public void receiveInitialSyncData(@NotNull PacketBuffer buf) {
        setActive(buf.readBoolean());
        setWorkingEnabled(buf.readBoolean());
        setWasActiveAndNeedsUpdate(buf.readBoolean());
        this.overheat = buf.readBoolean();
        this.fluidOutputFull = buf.readBoolean();
        this.usingPyrotheum = buf.readBoolean();
        this.hasConsume = buf.readBoolean();
        this.progressTime = buf.readInt();
        this.maxProgress = buf.readInt();
        this.temperature = buf.readInt();
        this.currentDrillingFluid = buf.readInt();
    }

    public void receiveCustomData(int dataId, PacketBuffer buf) {
        if (dataId == GregtechDataCodes.IS_WORKING) {
            setActive(buf.readBoolean());
            metaTileEntity.scheduleRenderUpdate();
        }
    }

    public boolean wasActiveAndNeedsUpdate() {
        return this.wasActiveAndNeedsUpdate;
    }

    public void setWasActiveAndNeedsUpdate(boolean wasActiveAndNeedsUpdate) {
        this.wasActiveAndNeedsUpdate = wasActiveAndNeedsUpdate;
    }

    public boolean isOverheat() {
        return this.overheat;
    }

    public boolean isFluidOutputFull() {
        return this.fluidOutputFull;
    }

    public int getTemperature() {
        return this.temperature;
    }

    public int getMaxTemperature() {
        return this.maxTemperature;
    }

    public int getCurrentDrillingFluid() {
        return this.currentDrillingFluid;
    }
}
