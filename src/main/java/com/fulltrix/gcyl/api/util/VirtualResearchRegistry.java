package com.fulltrix.gcyl.api.util;

import gregtech.api.GTValues;
import gregtech.api.util.GTLog;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nonnull;
import java.util.*;

public class VirtualResearchRegistry extends WorldSavedData {

    private static final String DATA_ID = GTValues.MODID + ".research_data";

    protected static Map<UUID, Map<String, VirtualResearchContainer>> containerMap = new HashMap<>();

    public VirtualResearchRegistry() {super(DATA_ID);}

    public VirtualResearchRegistry(String name) {super(name);}

    public static void clearMaps() {
        containerMap.clear();
    }


    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        if (nbt.hasKey("Public")) {
            NBTTagCompound publicContainers = nbt.getCompoundTag("Public");
            for (String key : publicContainers.getKeySet()) {
                NBTTagCompound containerCompound = publicContainers.getCompoundTag(key);
                VirtualResearchRegistry.getContainerCreate(key, null).deserializeNBT(containerCompound);
            }
        }
        if (nbt.hasKey("Private")) {
            NBTTagCompound privateContainerUUIDs = nbt.getCompoundTag("Private");
            for (String uuidStr : privateContainerUUIDs.getKeySet()) {
                UUID uuid = UUID.fromString(uuidStr);
                NBTTagCompound privateContainers = privateContainerUUIDs.getCompoundTag(uuidStr);
                for (String key : privateContainers.getKeySet()) {
                    NBTTagCompound containerCompound = privateContainers.getCompoundTag(key);
                    VirtualResearchRegistry.getContainerCreate(key, uuid).deserializeNBT(containerCompound);
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

    public static VirtualResearchRegistry.VirtualResearchContainer getContainer(String key, UUID uuid) {
        return containerMap.get(uuid).get(key);
    }

    public static Map<UUID, Map<String, VirtualResearchRegistry.VirtualResearchContainer>> getContainerMap() {
        return containerMap;
    }

    public static VirtualResearchRegistry.VirtualResearchContainer getContainerCreate(String key, UUID uuid) {
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
        containerMap.get(uuid).put(key, new VirtualResearchRegistry.VirtualResearchContainer());
    }

    public static void delContainer(String key, UUID uuid, boolean removeContainer) {
        if (containerMap.containsKey(uuid) && containerMap.get(uuid).containsKey(key)) {
            boolean isEmpty = true;
            IResearchContainer container = containerMap.get(uuid).get(key);

            //TODO: Clear research?

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
        VirtualResearchRegistry instance = (VirtualResearchRegistry) storage.getOrLoadData(VirtualResearchRegistry.class, DATA_ID);

        if (instance == null) {
            instance = new VirtualResearchRegistry();
            storage.setData(DATA_ID, instance);
        }
    }

    public static class VirtualResearchContainer implements IResearchContainer, INBTSerializable<NBTTagCompound> {

        private List<String> researchIds = new ArrayList<>();

        public VirtualResearchContainer() {}

        @Override
        public void setResearchId(List<String> inputResearchIds) {
            researchIds = inputResearchIds;
        }

        @Override
        public List<String> getResearchIds() {
            return researchIds;
        }

        @Override
        public void clearResearch() {
            researchIds.clear();
        }

        @Override
        public NBTTagCompound serializeNBT() {
            NBTTagList nbtTagList = new NBTTagList();
            for(int i = 0; i < researchIds.size(); i++) {
                if(!researchIds.get(i).isEmpty()) {
                    NBTTagCompound researchTag = new NBTTagCompound();
                    researchTag.setString("ResearchSlot", researchIds.get(i));
                    nbtTagList.appendTag(researchTag);
                }
            }
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setTag("Research", nbtTagList);
            return nbt;
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {
            NBTTagList tagList = nbt.getTagList("Research", Constants.NBT.TAG_COMPOUND);
            for(int i = 0; i < tagList.tagCount(); i++) {
                NBTTagCompound researchTags = tagList.getCompoundTagAt(i);
                String slot = researchTags.getString("ResearchSlot");
                researchIds.set(i, slot);
            }
        }

    }
}
