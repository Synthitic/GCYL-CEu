package com.fulltrix.gcyl.api.worldgen;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gregtech.api.unification.material.Material;
import gregtech.api.util.GTLog;
import gregtech.api.util.LocalizationUtils;
import gregtech.api.worldgen.config.IWorldgenDefinition;
import gregtech.api.worldgen.config.OreDepositDefinition;
import gregtech.api.worldgen.config.WorldConfigUtils;
import gregtech.core.unification.material.internal.MaterialRegistryManager;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class VirtualOreDepositDefinition implements IWorldgenDefinition {

    private final String depositName;

    private int weight; // weight value for determining which vein will appear
    private String assignedName; // vein name for JEI display
    private String description; // vein description for JEI display
    private final int[] yields = new int[2]; // the [minimum, maximum) yields
    private final List<Material> storedMaterials = new ArrayList<>(); // the materials which the vein contains
    private final ObjectList<Float> multipliers = new ObjectArrayList<>();
    private Function<Biome, Integer> biomeWeightModifier = OreDepositDefinition.NO_BIOME_INFLUENCE;
    private Predicate<WorldProvider> dimensionFilter = OreDepositDefinition.PREDICATE_SURFACE_WORLD;

    public VirtualOreDepositDefinition(String depositName) {
        this.depositName = depositName;
    }

    @Override
    public boolean initializeFromConfig(@NotNull JsonObject configRoot) {
        // the weight value for determining which vein will appear
        this.weight = configRoot.get("weight").getAsInt();
        // the [minimum, maximum) yield of the vein
        this.yields[0] = configRoot.get("yield").getAsJsonObject().get("min").getAsInt();
        this.yields[1] = configRoot.get("yield").getAsJsonObject().get("max").getAsInt();

        // the fluid which the vein contains
        JsonArray jsonMaterialList = configRoot.get("materials").getAsJsonArray();

        for(JsonElement jsonElement : jsonMaterialList) {
            Material material = MaterialRegistryManager.getInstance().getMaterial(jsonElement.getAsString());
            if (material != null) {
                this.storedMaterials.add(material);
            } else {
                GTLog.logger.error("Virtual Ore Vein {} cannot have a null material!", this.depositName,
                        new RuntimeException());
                return false;
            }
        }

        JsonArray jsonMultiplierList = configRoot.get("multiplier").getAsJsonArray();

        for(JsonElement jsonElement : jsonMultiplierList) {
            if (jsonElement.getAsFloat() > 0) {
                this.multipliers.add(jsonElement.getAsFloat());
            } else {
                GTLog.logger.error("Multiplier cannot be less than or equal to 0!", this.depositName,
                        new RuntimeException());
                return false;
            }
        }

        // vein name for JEI display
        if (configRoot.has("name")) {
            this.assignedName = LocalizationUtils.format(configRoot.get("name").getAsString());
        }
        // vein description for JEI display
        if (configRoot.has("description")) {
            this.description = configRoot.get("description").getAsString();
        }
        // additional weighting changes determined by biomes
        if (configRoot.has("biome_modifier")) {
            this.biomeWeightModifier = WorldConfigUtils.createBiomeWeightModifier(configRoot.get("biome_modifier"));
        }
        // filtering of dimensions to determine where the vein can generate
        if (configRoot.has("dimension_filter")) {
            this.dimensionFilter = WorldConfigUtils.createWorldPredicate(configRoot.get("dimension_filter"));
        }
        VirtualOreVeinHandler.addVirtualOreDeposit(this);
        return true;
    }

    @Override
    public String getDepositName() {
        return depositName;
    }

    public String getAssignedName() {
        return assignedName;
    }

    public String getDescription() {
        return description;
    }

    public int getWeight() {
        return weight;
    }

    @SuppressWarnings("unused")
    public int[] getYields() {
        return yields;
    }

    public int getMinimumYield() {
        return yields[0];
    }

    public int getMaximumYield() {
        return yields[1];
    }

    public List<Material> getStoredMaterials() {
        return storedMaterials;
    }

    public ObjectList<Float> getMultipliers() {
        return multipliers;
    }

    public Function<Biome, Integer> getBiomeWeightModifier() {
        return biomeWeightModifier;
    }

    public Predicate<WorldProvider> getDimensionFilter() {
        return dimensionFilter;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof VirtualOreDepositDefinition))
            return false;

        VirtualOreDepositDefinition objDeposit = (VirtualOreDepositDefinition) obj;
        if (this.weight != objDeposit.getWeight())
            return false;
        if (this.getMinimumYield() != objDeposit.getMinimumYield())
            return false;
        if (this.getMaximumYield() != objDeposit.getMaximumYield())
            return false;
        if (!this.storedMaterials.equals(objDeposit.getStoredMaterials()))
            return false;
        if (!this.multipliers.equals(objDeposit.getMultipliers()))
            return false;
        if ((this.assignedName == null && objDeposit.getAssignedName() != null) ||
                (this.assignedName != null && objDeposit.getAssignedName() == null) ||
                (this.assignedName != null && objDeposit.getAssignedName() != null &&
                        !this.assignedName.equals(objDeposit.getAssignedName())))
            return false;
        if ((this.description == null && objDeposit.getDescription() != null) ||
                (this.description != null && objDeposit.getDescription() == null) ||
                (this.description != null && objDeposit.getDescription() != null &&
                        !this.description.equals(objDeposit.getDescription())))
            return false;
        if ((this.biomeWeightModifier == null && objDeposit.getBiomeWeightModifier() != null) ||
                (this.biomeWeightModifier != null && objDeposit.getBiomeWeightModifier() == null) ||
                (this.biomeWeightModifier != null && objDeposit.getBiomeWeightModifier() != null &&
                        !this.biomeWeightModifier.equals(objDeposit.getBiomeWeightModifier())))
            return false;
        if ((this.dimensionFilter == null && objDeposit.getDimensionFilter() != null) ||
                (this.dimensionFilter != null && objDeposit.getDimensionFilter() == null) ||
                (this.dimensionFilter != null && objDeposit.getDimensionFilter() != null &&
                        !this.dimensionFilter.equals(objDeposit.getDimensionFilter())))
            return false;

        return super.equals(obj);
    }
}
