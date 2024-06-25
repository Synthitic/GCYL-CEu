package com.fulltrix.gcyl.api.recipes;

import com.fulltrix.gcyl.Tags;
import gregtech.api.recipes.Recipe;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.properties.OreProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.ConfigHolder;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.*;

import static gregtech.api.recipes.RecipeMaps.*;

public class CachedRecipes {

    private static List<ItemStack> ores = new ArrayList<>();
    private static List<ItemStack> crushed = new ArrayList<>();
    private static List<ItemStack> crushedPurified = new ArrayList<>();
    private static List<ItemStack> crushedCentrifuged = new ArrayList<>();
    private static List<ItemStack> dustImpure = new ArrayList<>();
    private static List<ItemStack> dustPure = new ArrayList<>();

    public static Object2ObjectMap<Item, Recipe> cachedMaceratorOreRecipes = new Object2ObjectOpenHashMap<>();
    public static Object2ObjectMap<String, Recipe> cachedMaceratorCrushedRecipes = new Object2ObjectOpenHashMap<>();
    public static Object2ObjectMap<String, Recipe> cachedMaceratorCrushedPurifiedRecipes = new Object2ObjectOpenHashMap<>();
    public static Object2ObjectMap<String, Recipe> cachedMaceratorCrushedCentrifugedRecipes = new Object2ObjectOpenHashMap<>();
    public static Object2ObjectMap<String, Recipe> cachedWasherRecipes = new Object2ObjectOpenHashMap<>();
    public static Object2ObjectMap<String, Recipe> cachedThermalCentrifugeCrushedRecipes = new Object2ObjectOpenHashMap<>();
    public static Object2ObjectMap<String, Recipe> cachedThermalCentrifugeCrushedPurifiedRecipes = new Object2ObjectOpenHashMap<>();
    public static Object2ObjectMap<String, Recipe> cachedCentrifugePureRecipes = new Object2ObjectOpenHashMap<>();
    public static Object2ObjectMap<String, Recipe> cachedCentrifugeImpureRecipes = new Object2ObjectOpenHashMap<>();


    public static void register() {
        OrePrefix.ore.addProcessingHandler(PropertyKey.ORE, CachedRecipes::processOre);
        OrePrefix.crushed.addProcessingHandler(PropertyKey.ORE, CachedRecipes::processCrushed);
        OrePrefix.crushedPurified.addProcessingHandler(PropertyKey.ORE, CachedRecipes::processPurified);
        OrePrefix.crushedCentrifuged.addProcessingHandler(PropertyKey.ORE, CachedRecipes::processCrushedCentrifuged);
        OrePrefix.dustImpure.addProcessingHandler(PropertyKey.ORE, CachedRecipes::processDustImpure);
        OrePrefix.dustPure.addProcessingHandler(PropertyKey.ORE, CachedRecipes::processDustPure);
    }

    private static void processOre(OrePrefix orePrefix, Material material, OreProperty property) {

        OrePrefix currentOre = OrePrefix.ore;

        if (ConfigHolder.worldgen.allUniqueStoneTypes) {

        }
        else
            ores.add(OreDictUnifier.get(new UnificationEntry(currentOre, material)));

    }

    private static void processCrushed(OrePrefix orePrefix, Material material, OreProperty property) {
        OrePrefix currentOre = OrePrefix.crushed;

        crushed.add(OreDictUnifier.get(currentOre, material));
    }

    private static void processPurified(OrePrefix orePrefix, Material material, OreProperty property) {
        OrePrefix currentOre = OrePrefix.crushedPurified;

        crushedPurified.add(OreDictUnifier.get(currentOre, material));
    }

    private static void processCrushedCentrifuged(OrePrefix orePrefix, Material material, OreProperty property) {
        OrePrefix currentOre = OrePrefix.crushedCentrifuged;

        crushedCentrifuged.add(OreDictUnifier.get(currentOre, material));
    }

    private static void processDustImpure(OrePrefix orePrefix, Material material, OreProperty property) {
        OrePrefix currentOre = OrePrefix.dustImpure;

        dustImpure.add(OreDictUnifier.get(currentOre, material));
    }

    private static void processDustPure(OrePrefix orePrefix, Material material, OreProperty property) {
        OrePrefix currentOre = OrePrefix.dustPure;

        dustPure.add(OreDictUnifier.get(currentOre, material));
    }


    public static void cacheRecipesForOPF() {
        cacheMaceratorRecipes();
        cacheWasherRecipes();
        cacheThermalCentrifuge();
        cacheCentrifugeRecipes();
    }

    private static void cacheMaceratorRecipes() {
        for(ItemStack item : ores) {
            Recipe recipe = MACERATOR_RECIPES.findRecipe(32, Collections.singletonList(item), Collections.emptyList());
            cachedMaceratorOreRecipes.put(item.getItem(), recipe);
        }

        for(ItemStack item : crushed) {
            Recipe recipe = MACERATOR_RECIPES.findRecipe(32, Collections.singletonList(item), Collections.emptyList());

                cachedMaceratorCrushedRecipes.put(item.getMetadata() + item.getItem().getCreatorModId(item), recipe);
        }

        for(ItemStack item : crushedPurified) {
            Recipe recipe = MACERATOR_RECIPES.findRecipe(32, Collections.singletonList(item), Collections.emptyList());

                cachedMaceratorCrushedPurifiedRecipes.put(item.getMetadata() + item.getItem().getCreatorModId(item), recipe);

        }

        for(ItemStack item : crushedCentrifuged) {
            Recipe recipe = MACERATOR_RECIPES.findRecipe(32, Collections.singletonList(item), Collections.emptyList());


                cachedMaceratorCrushedCentrifugedRecipes.put(item.getMetadata() + item.getItem().getCreatorModId(item), recipe);

        }
    }

    private static void cacheWasherRecipes() {
        for(ItemStack item : crushed) {
            Recipe recipe = ORE_WASHER_RECIPES.findRecipe(32, Collections.singletonList(item), Collections.singletonList(Materials.DistilledWater.getFluid(100)));

                cachedWasherRecipes.put(item.getMetadata() + item.getItem().getCreatorModId(item), recipe);

        }
    }

    private static void cacheThermalCentrifuge() {
        for(ItemStack item : crushedPurified) {
            Recipe recipe = THERMAL_CENTRIFUGE_RECIPES.findRecipe(32, Collections.singletonList(item), Collections.emptyList());

                cachedThermalCentrifugeCrushedPurifiedRecipes.put(item.getMetadata() + item.getItem().getCreatorModId(item), recipe);

        }

        for(ItemStack item : crushed) {
            Recipe recipe = THERMAL_CENTRIFUGE_RECIPES.findRecipe(32, Collections.singletonList(item), Collections.emptyList());

                cachedThermalCentrifugeCrushedRecipes.put(item.getMetadata() + item.getItem().getCreatorModId(item), recipe);

        }
    }

    private static void cacheCentrifugeRecipes() {
        for(ItemStack item : dustImpure) {
            Recipe recipe = CENTRIFUGE_RECIPES.findRecipe(32, Collections.singletonList(item), Collections.emptyList());

                cachedCentrifugeImpureRecipes.put(item.getMetadata() + item.getItem().getCreatorModId(item), recipe);

        }

        for(ItemStack item : dustPure) {
            Recipe recipe = CENTRIFUGE_RECIPES.findRecipe(32, Collections.singletonList(item), Collections.emptyList());

                cachedCentrifugePureRecipes.put(item.getMetadata() + item.getItem().getCreatorModId(item), recipe);

        }
    }

}
