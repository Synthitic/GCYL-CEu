package com.fulltrix.gcyl.api.multi;

import com.fulltrix.gcyl.api.worldgen.VirtualOreVeinHandler;
import com.fulltrix.gcyl.machines.multi.miner.virtual.MetaTileEntityVirtualMiner;
import gregtech.api.GTValues;
import gregtech.api.capability.GregtechDataCodes;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.util.GTTransferUtils;
import gregtech.common.ConfigHolder;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectList;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//TODO finish implementing
public class VirtualMinerLogic {

    public static final int MAX_PROGRESS = 20;

    private int progressTime = 0;

    private final MetaTileEntityVirtualMiner metaTileEntity;

    private boolean isActive;
    private boolean isWorkingEnabled = true;
    private boolean wasActiveAndNeedsUpdate;
    private boolean isDone = false;
    protected boolean isInventoryFull;

    private boolean hasNotEnoughEnergy;

    private final Object2ObjectMap<Integer, Material> vein = new Object2ObjectOpenHashMap<>();

    public VirtualMinerLogic(MetaTileEntityVirtualMiner metaTileEntity) {
        this.metaTileEntity = metaTileEntity;
    }

    public void performDrilling() {
        if (metaTileEntity.getWorld().isRemote) return;

        ObjectList<Integer> layers = metaTileEntity.getLayers();

        // if we have no fluid, try to get a new one
        if (vein.isEmpty())
            if (!acquireNewMaterials(layers))
                return; // stop if we still have no fluid

        // drills that cannot work do nothing
        if (!this.isWorkingEnabled)
            return;

        // check if drilling is possible
        if (!checkCanDrain())
            return;

        for(int i = 0; i < layers.size(); i++) {
            int amount = getProduce(i);
            if(amount > 0) {
                break;
            }

            if(i == layers.size() - 1) {
                if (this.isActive)
                    setActive(false);
                return;
            }
        }

        // if the inventory is not full, drain energy etc. from the drill
        // the storages have already been checked earlier
        if (!isInventoryFull) {
            // actually drain the energy
            if(metaTileEntity.getTier() > 0)
                consumeEnergy(false);
            else if(metaTileEntity.getFuelBurnTimeLeft() == 0) {
                consumeFuel(false);
            }

            // since energy is being consumed the rig is now active
            if (!this.isActive)
                setActive(true);
        } else {
            // the rig cannot drain, therefore it is inactive
            if (this.isActive)
                setActive(false);
            return;
        }


        // increase progress
        progressTime++;
        if(metaTileEntity.getTier() == 0) {
            metaTileEntity.removeBurnTime();
        }
        if (progressTime % MAX_PROGRESS != 0)
            return;
        progressTime = 0;

        useDrill(false);

        List<ItemStack> stacks = new ArrayList<>();
        for(int i : layers) {
            int amount = getProduce(i);
            if(amount > 0) {
                stacks.add(OreDictUnifier.get(metaTileEntity.getTier() == 0 ? OrePrefix.crushed : OrePrefix.ore, vein.get(i)));
                depleteVein(i, amount);
            }
        }

        if (GTTransferUtils.addItemsToItemHandler(metaTileEntity.getExportItems(), true, stacks)) {
            GTTransferUtils.addItemsToItemHandler(metaTileEntity.getExportItems(), false, stacks);
        } else {
            isInventoryFull = true;
            setActive(false);
            setWasActiveAndNeedsUpdate(true);
        }
    }

    protected void depleteVein(int layer, int amount) {
        int chance = metaTileEntity.getDepletionChance();

        // chance to deplete based on the rig
        if (chance == 1 || GTValues.RNG.nextInt(chance) == 0)
            VirtualOreVeinHandler.depleteVein(metaTileEntity.getWorld(), getChunkX(), getChunkZ(), amount, layer, false);
    }

    protected boolean consumeEnergy(boolean simulate) {
        return metaTileEntity.drainEnergy(simulate);
    }

    protected boolean consumeFuel(boolean simulate) {
        return metaTileEntity.consumeFuel(simulate);
    }

    protected boolean useDrill(boolean simulate) {
        return metaTileEntity.useDrill(simulate);
    }

    private boolean acquireNewMaterials(ObjectList<Integer> layers) {

        for(int i : layers) {
            if(this.vein.get(i) == null) {
                List<Material> materials = VirtualOreVeinHandler.getMaterialsInChunk(metaTileEntity.getWorld(), getChunkX(), getChunkZ());

                if(materials != null && !materials.isEmpty()) {
                    this.vein.put(i, materials.get(i));
                }
            }
        }

        return !this.vein.isEmpty();
    }

    private void clearVein() {
        this.vein.clear();
    }

    public int getProduce(int layer) {

        int remainingOperations = VirtualOreVeinHandler.getOperationsRemaining(metaTileEntity.getWorld(), getChunkX(), getChunkZ()).get(layer);

        int produced = 0;

        if(remainingOperations > 0) {
            produced = metaTileEntity.getRigMultiplier();
            // Overclocks produce 50% more
            if (isOverclocked()) {
                produced = produced * 3 / 2;
            }
        }

        return produced;
    }

    protected boolean checkCanDrain() {
        if(metaTileEntity.getTier() != 0) {
            if (!consumeEnergy(true)) {
                if (progressTime >= 2) {
                    if (ConfigHolder.machines.recipeProgressLowEnergy)
                        this.progressTime = 1;
                    else
                        this.progressTime = Math.max(1, progressTime - 2);

                    hasNotEnoughEnergy = true;
                }
                return false;
            }
        } else {
            if (!consumeFuel(true)) {
                return false;
            }
        }

        if(!useDrill(true)) {
            return false;
        }

        if (this.hasNotEnoughEnergy && metaTileEntity.getTier() != 0 &&
                metaTileEntity.getEnergyInputPerSecond() > 19L * GTValues.VA[metaTileEntity.getEnergyTier()]) {
            this.hasNotEnoughEnergy = false;
        }

        if (GTTransferUtils.addItemsToItemHandler(metaTileEntity.getExportItems(), true, new ArrayList<>(Collections.singletonList(new ItemStack(Blocks.COBBLESTONE, 64))))) {
            this.isInventoryFull = false;
            return true;
        }
        this.isInventoryFull = true;

        if (isActive()) {
            setActive(false);
            setWasActiveAndNeedsUpdate(true);
        }
        return false;
    }

    public int getChunkX() {
        return Math.floorDiv(metaTileEntity.getPos().getX(), 16);
    }

    public int getChunkZ() {
        return Math.floorDiv(metaTileEntity.getPos().getZ(), 16);
    }

    public boolean isActive() {
        return this.isActive;
    }


    public void setActive(boolean active) {
        if (this.isActive != active) {
            this.isActive = active;
            this.metaTileEntity.markDirty();
            if (metaTileEntity.getWorld() != null && !metaTileEntity.getWorld().isRemote) {
                this.metaTileEntity.writeCustomData(GregtechDataCodes.WORKABLE_ACTIVE, buf -> buf.writeBoolean(active));
            }
        }
    }
    public void setWorkingEnabled(boolean isWorkingEnabled) {
        if (this.isWorkingEnabled != isWorkingEnabled) {
            this.isWorkingEnabled = isWorkingEnabled;
            metaTileEntity.markDirty();
            if (metaTileEntity.getWorld() != null && !metaTileEntity.getWorld().isRemote) {
                this.metaTileEntity.writeCustomData(GregtechDataCodes.WORKING_ENABLED,
                        buf -> buf.writeBoolean(isWorkingEnabled));
            }
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

    public double getProgressPercent() {
        return getProgressTime() * 1.0 / MAX_PROGRESS;
    }

    protected boolean isOverclocked() {
        return metaTileEntity.getEnergyTier() > metaTileEntity.getTier();
    }

    public boolean isInventoryFull() {
        return this.isInventoryFull;
    }

    public NBTTagCompound writeToNBT(@NotNull NBTTagCompound data) {
        data.setBoolean("isActive", this.isActive);
        data.setBoolean("isWorkingEnabled", this.isWorkingEnabled);
        data.setBoolean("wasActiveAndNeedsUpdate", this.wasActiveAndNeedsUpdate);
        data.setBoolean("isDone", isDone);
        data.setInteger("progressTime", progressTime);
        data.setBoolean("isInventoryFull", isInventoryFull);
        return data;
    }

    public void readFromNBT(@NotNull NBTTagCompound data) {
        this.isActive = data.getBoolean("isActive");
        this.isWorkingEnabled = data.getBoolean("isWorkingEnabled");
        this.wasActiveAndNeedsUpdate = data.getBoolean("wasActiveAndNeedsUpdate");
        this.isDone = data.getBoolean("isDone");
        this.progressTime = data.getInteger("progressTime");
        this.isInventoryFull = data.getBoolean("isInventoryFull");
    }

    public void writeInitialSyncData(@NotNull PacketBuffer buf) {
        buf.writeBoolean(this.isActive);
        buf.writeBoolean(this.isWorkingEnabled);
        buf.writeBoolean(this.wasActiveAndNeedsUpdate);
        buf.writeInt(this.progressTime);
        buf.writeBoolean(this.isInventoryFull);
    }

    public void receiveInitialSyncData(@NotNull PacketBuffer buf) {
        setActive(buf.readBoolean());
        setWorkingEnabled(buf.readBoolean());
        setWasActiveAndNeedsUpdate(buf.readBoolean());
        this.progressTime = buf.readInt();
        this.isInventoryFull = buf.readBoolean();
    }

    public void receiveCustomData(int dataId, PacketBuffer buf) {
        if (dataId == GregtechDataCodes.WORKABLE_ACTIVE) {
            this.isActive = buf.readBoolean();
            metaTileEntity.scheduleRenderUpdate();
        } else if (dataId == GregtechDataCodes.WORKING_ENABLED) {
            this.isWorkingEnabled = buf.readBoolean();
            metaTileEntity.scheduleRenderUpdate();
        }
    }

    public boolean wasActiveAndNeedsUpdate() {
        return this.wasActiveAndNeedsUpdate;
    }

    public void setWasActiveAndNeedsUpdate(boolean wasActiveAndNeedsUpdate) {
        this.wasActiveAndNeedsUpdate = wasActiveAndNeedsUpdate;
    }
}
