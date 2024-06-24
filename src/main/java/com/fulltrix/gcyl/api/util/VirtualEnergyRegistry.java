package com.fulltrix.gcyl.api.util;

import gregtech.api.GTValues;
import gregtech.api.util.GTLog;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class VirtualEnergyRegistry extends WorldSavedData {

    private static final String DATA_ID = GTValues.MODID + ".energy_data";

    protected static Map<UUID, Map<String, VirtualEnergyRegistry.VirtualEnergyContainer>> containerMap = new HashMap<>();


    public VirtualEnergyRegistry() {
        super(DATA_ID);
    }

    public VirtualEnergyRegistry(String name) {super(name);}

    public static void clearMaps() {
        containerMap.clear();
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        if (nbt.hasKey("Public")) {
            NBTTagCompound publicContainers = nbt.getCompoundTag("Public");
            for (String key : publicContainers.getKeySet()) {
                NBTTagCompound containerCompound = publicContainers.getCompoundTag(key);
                VirtualEnergyRegistry.getContainerCreate(key, null).deserializeNBT(containerCompound);
            }
        }
        if (nbt.hasKey("Private")) {
            NBTTagCompound privateContainerUUIDs = nbt.getCompoundTag("Private");
            for (String uuidStr : privateContainerUUIDs.getKeySet()) {
                UUID uuid = UUID.fromString(uuidStr);
                NBTTagCompound privateContainers = privateContainerUUIDs.getCompoundTag(uuidStr);
                for (String key : privateContainers.getKeySet()) {
                    NBTTagCompound containerCompound = privateContainers.getCompoundTag(key);
                    VirtualEnergyRegistry.getContainerCreate(key, uuid).deserializeNBT(containerCompound);
                }
            }
        }
    }

    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("Private", new NBTTagCompound());
        containerMap.forEach( (uuid, map) -> {
            NBTTagCompound mapCompound = new NBTTagCompound();
            map.forEach( (key, container) -> {
                NBTTagCompound containerCompound = container.serializeNBT();
                mapCompound.setTag(key, containerCompound);
            });
            if (mapCompound.getSize() > 0) {
                if (uuid == null) {
                    compound.setTag("Public", mapCompound);
                } else {
                    compound.getCompoundTag("Private").setTag(uuid.toString(), mapCompound);
                }
            }
        });
        return compound;
    }

    @Override
    public boolean isDirty() {
        // can't think of a good way to mark dirty other than always
        return true;
    }

    public static VirtualEnergyContainer getContainer(String key, UUID uuid) {
        return containerMap.get(uuid).get(key);
    }

    public static Map<UUID, Map<String, VirtualEnergyContainer>> getContainerMap() {
        return containerMap;
    }

    public static VirtualEnergyContainer getContainerCreate(String key, UUID uuid) {
        if (!containerMap.containsKey(uuid) || !containerMap.get(uuid).containsKey(key)) {
            addContainer(key, uuid);
        }
        return getContainer(key, uuid);
    }

    public static void addContainer(String key, UUID uuid) {
        if (containerMap.containsKey(uuid) && containerMap.get(uuid).containsKey(key)) {
            GTLog.logger.warn("Overwriting virtual container " + key + "/" + (uuid == null ? "null" :uuid.toString()) + ", this might cause item loss!");
        } else if (!containerMap.containsKey(uuid)) {
            containerMap.put(uuid, new HashMap<>());
        }
        containerMap.get(uuid).put(key, new VirtualEnergyContainer());
    }

    public static void delContainer(String key, UUID uuid, boolean removeContainer) {
        if (containerMap.containsKey(uuid) && containerMap.get(uuid).containsKey(key)) {
            boolean isEmpty = true;
            IWirelessEnergyContainer container = containerMap.get(uuid).get(key);

            //TODO: SET ENERGY TO ZERO?

            if (removeContainer || isEmpty) {
                containerMap.get(uuid).remove(key);
                if (containerMap.get(uuid).size() == 0) {
                    containerMap.remove(uuid);
                }
            }
        } else {
            GTLog.logger.warn("Attempted to delete container " + key + "/" + (uuid == null ? "null" :uuid.toString()) + ", which does not exist!");
        }
    }

    public static void initializeStorage(World world) {
        MapStorage storage = world.getMapStorage();
        VirtualEnergyRegistry instance = (VirtualEnergyRegistry) storage.getOrLoadData(VirtualEnergyRegistry.class, DATA_ID);

        if (instance == null) {
            instance = new VirtualEnergyRegistry();
            storage.setData(DATA_ID, instance);
        }
    }


    public static class VirtualEnergyContainer implements IWirelessEnergyContainer,INBTSerializable<NBTTagCompound>  {

        private AtomicLong totalWirelessEnergy = new AtomicLong(0);

        public VirtualEnergyContainer() {}

        @Override
        public long changeEnergy(long l) {
            AtomicLong temp = new AtomicLong(totalWirelessEnergy.get());
            temp.addAndGet(l);

            if (temp.get() < totalWirelessEnergy.get() && l > 0) {
                totalWirelessEnergy.set(Long.MAX_VALUE);
                return Long.MAX_VALUE;
            } else
                return totalWirelessEnergy.addAndGet(l);
        }

        @Override
        public long getEnergyStored() {
            return totalWirelessEnergy.get();
        }

        @Override
        public NBTTagCompound serializeNBT() {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setLong("Energy", totalWirelessEnergy.longValue());
            return nbt;
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {
            totalWirelessEnergy.set(nbt.getLong("Energy"));
        }
    }
}
