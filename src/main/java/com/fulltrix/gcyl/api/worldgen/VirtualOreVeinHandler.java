package com.fulltrix.gcyl.api.worldgen;

import gregtech.api.GregTechAPI;
import gregtech.api.unification.material.Material;
import gregtech.api.util.FileUtility;
import gregtech.api.util.GTLog;
import gregtech.api.util.XSTR;
import gregtech.api.worldgen.bedrockFluids.ChunkPosDimension;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import scala.Int;

import java.util.*;
import java.util.stream.Collectors;

public class VirtualOreVeinHandler {

    public static final LinkedHashMap<VirtualOreDepositDefinition, Integer> virtualOreVeinList = new LinkedHashMap<>();
    private final static Map<Integer, HashMap<Integer, Integer>> totalWeightMap = new HashMap<>();
    public static HashMap<ChunkPosDimension, VirtualOreVeinWorldEntry> veinCache = new HashMap<>();

    public static int saveDataVersion;

    public static final int MAX_VIRTUAL_ORE_SAVE_DATA_VERSION = 2;

    public static final int VEIN_CHUNK_SIZE = 3; // veins are 8x8 chunk squares


    @Nullable
    public static VirtualOreVeinHandler.VirtualOreVeinWorldEntry getVirtualOreVeinWorldEntry(@NotNull World world, int chunkX, int chunkZ) {
        if (world.isRemote)
            return null;

        ChunkPosDimension coords = new ChunkPosDimension(world.provider.getDimension(), getVeinCoord(chunkX),
                getVeinCoord(chunkZ));

        VirtualOreVeinHandler.VirtualOreVeinWorldEntry worldEntry = veinCache.get(coords);
        if (worldEntry == null) {
            VirtualOreDepositDefinition definition = null;

            int query = world.getChunk(getVeinCoord(chunkX), getVeinCoord(chunkZ)).getRandomWithSeed(90210).nextInt();

            Biome biome = world.getBiomeForCoordsBody(new BlockPos(chunkX << 4, 64, chunkZ << 4));
            int totalWeight = getTotalWeight(world.provider, biome);
            if (totalWeight > 0) {
                int weight = Math.abs(query % totalWeight);
                for (Map.Entry<VirtualOreDepositDefinition, Integer> entry : virtualOreVeinList.entrySet()) {
                    int veinWeight = entry.getValue() + entry.getKey().getBiomeWeightModifier().apply(biome);
                    if (veinWeight > 0 && entry.getKey().getDimensionFilter().test(world.provider)) {
                        weight -= veinWeight;
                        if (weight < 0) {
                            definition = entry.getKey();
                            break;
                        }
                    }
                }
            }

            ObjectList<Integer> maximumYields = new ObjectArrayList<>();

            for(float i : definition.getMultipliers()) {

                Random random = new XSTR(31L * 31 * chunkX + chunkZ * 31L + Long.hashCode(world.getSeed()));

                int maximumYield = 0;
                if (definition != null) {
                    if (definition.getMaximumYield() * i - definition.getMinimumYield() <= 0) {
                        maximumYield = definition.getMinimumYield();
                    } else {
                        maximumYield = random.nextInt((int) (definition.getMaximumYield() * i - definition.getMinimumYield())) +
                                definition.getMinimumYield();
                    }
                    maximumYield = (int) Math.min(maximumYield, definition.getMaximumYield() * i);
                }

                maximumYields.add(maximumYield);
            }

            worldEntry = new VirtualOreVeinHandler.VirtualOreVeinWorldEntry(definition, maximumYields, maximumYields);
            veinCache.put(coords, worldEntry);
        }
        return worldEntry;
    }

    public static int getTotalWeight(@NotNull WorldProvider provider, Biome biome) {
        int dim = provider.getDimension();
        if (!totalWeightMap.containsKey(dim)) {
            totalWeightMap.put(dim, new HashMap<>());
        }

        Map<Integer, Integer> dimMap = totalWeightMap.get(dim);
        int biomeID = Biome.getIdForBiome(biome);

        if (dimMap.containsKey(biomeID)) {
            return dimMap.get(biomeID);
        }

        int totalWeight = 0;
        for (Map.Entry<VirtualOreDepositDefinition, Integer> entry : virtualOreVeinList.entrySet()) {
            if (entry.getKey().getDimensionFilter().test(provider)) {
                totalWeight += entry.getKey().getBiomeWeightModifier().apply(biome);
                totalWeight += entry.getKey().getWeight();
            }
        }

        // make sure the vein can generate if no biome weighting is added
        if (totalWeight == 0 && !virtualOreVeinList.isEmpty())
            GTLog.logger.error("Virtual Ore Vein weight was 0 in biome {}", biome.biomeName);

        dimMap.put(biomeID, totalWeight);
        return totalWeight;
    }

    public static void addVirtualOreDeposit(VirtualOreDepositDefinition definition) {
        virtualOreVeinList.put(definition, definition.getWeight());
    }

    public static void recalculateChances(boolean mutePackets) {
        totalWeightMap.clear();
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER && !mutePackets) {
            HashMap<VirtualOreVeinHandler.VirtualOreVeinWorldEntry, Integer> packetMap = new HashMap<>();
            for (Map.Entry<ChunkPosDimension, VirtualOreVeinHandler.VirtualOreVeinWorldEntry> entry : VirtualOreVeinHandler.veinCache
                    .entrySet()) {
                if (entry.getKey() != null && entry.getValue() != null)
                    packetMap.put(entry.getValue(), entry.getValue().getDefinition().getWeight());
            }
            GregTechAPI.networkHandler.sendToAll(new PacketVirtualOreVeinList(packetMap));
        }
    }

    public static ObjectList<Integer> getYield(World world, int chunkX, int chunkZ) {
        VirtualOreVeinHandler.VirtualOreVeinWorldEntry info = getVirtualOreVeinWorldEntry(world, chunkX, chunkZ);
        if (info == null) return null;
        return info.getYield();
    }

    public static ObjectList<Integer> getOperationsRemaining(World world, int chunkX, int chunkZ) {
        VirtualOreVeinHandler.VirtualOreVeinWorldEntry info = getVirtualOreVeinWorldEntry(world, chunkX, chunkZ);
        if (info == null) return null;
        return info.getOperationsRemaining();
    }

    /*
    public static ObjectList<Float> getMultipliers(World world, int chunkX, int chunkZ) {
        VirtualOreVeinHandler.VirtualOreVeinWorldEntry info = getVirtualOreVeinWorldEntry(world, chunkX, chunkZ);
        if(info == null) return 0;
        return info.getMultipliers
    }

     */

    @Nullable
    public static List<Material> getMaterialsInChunk(World world, int chunkX, int chunkZ) {
        VirtualOreVeinHandler.VirtualOreVeinWorldEntry info = getVirtualOreVeinWorldEntry(world, chunkX, chunkZ);
        if (info == null || info.getDefinition() == null) return null;
        return info.getDefinition().getStoredMaterials();
    }

    public static void depleteVein(World world, int chunkX, int chunkZ, int amount, int layer, boolean ignoreVeinStats) {
        VirtualOreVeinHandler.VirtualOreVeinWorldEntry info = getVirtualOreVeinWorldEntry(world, chunkX, chunkZ);
        if (info == null) return;

        //TODO: what is this
        if (ignoreVeinStats) {
            info.decreaseOperations(amount, layer);
            return;
        }

        VirtualOreDepositDefinition definition = info.getDefinition();

        // prevent division by zero, veins that never deplete don't need updating
        if (definition == null)
            return;

        info.decreaseOperations(1, layer);
        VirtualOreVeinSaveData.setDirty();
    }

    public static int getVeinCoord(int chunkCoord) {
        if (saveDataVersion >= 2) {
            return Math.floorDiv(chunkCoord, VEIN_CHUNK_SIZE);
        }
        return chunkCoord / VEIN_CHUNK_SIZE;
    }


    public static class VirtualOreVeinWorldEntry {
        private VirtualOreDepositDefinition vein;
        private ObjectList<Integer>  yield;

        private ObjectList<Integer> operationsRemaining;

        public VirtualOreVeinWorldEntry(VirtualOreDepositDefinition vein, ObjectList<Integer> yield, ObjectList<Integer> operationsRemaining) {
            this.vein = vein;
            this.yield = yield;
            this.operationsRemaining = operationsRemaining;
        }

        private VirtualOreVeinWorldEntry() {}

        public VirtualOreDepositDefinition getDefinition() {return this.vein;}

        public ObjectList<Integer>  getYield() {
            return this.yield;
        }

        public ObjectList<Integer> getOperationsRemaining() {
            return this.operationsRemaining;
        }

        public void setOperationsRemaining(int operationsRemaining, int layer) {
            this.operationsRemaining.set(layer, operationsRemaining);
        }

        public void decreaseOperations(int amount, int layer) {
            operationsRemaining.set(layer, Math.max(0, operationsRemaining.get(layer) - amount));
        }

        public NBTTagCompound writeToNBT() {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setIntArray("yield", yield.stream().mapToInt(Integer::intValue).toArray());
            tag.setIntArray("operationsRemaining", operationsRemaining.stream().mapToInt(Integer::intValue).toArray());
            if (vein != null) {
                tag.setString("vein", vein.getDepositName());
            }
            return tag;
        }

        @NotNull
        public static VirtualOreVeinHandler.VirtualOreVeinWorldEntry readFromNBT(@NotNull NBTTagCompound tag) {
            VirtualOreVeinHandler.VirtualOreVeinWorldEntry info = new VirtualOreVeinHandler.VirtualOreVeinWorldEntry();
            info.yield = (ObjectList<Integer>) Arrays.stream(tag.getIntArray("yield")).boxed().collect(Collectors.toList());
            info.operationsRemaining = (ObjectList<Integer>) Arrays.stream(tag.getIntArray("operationsRemaining")).boxed().collect(Collectors.toList());

            if (tag.hasKey("vein")) {
                String s = tag.getString("vein");
                for (VirtualOreDepositDefinition definition : virtualOreVeinList.keySet()) {
                    // old save data can have deposit names with native separators, get rid of those
                    if (FileUtility.nativeSepToSlash(s).equalsIgnoreCase(definition.getDepositName()))
                        info.vein = definition;
                }
            }

            return info;
        }
    }
}
