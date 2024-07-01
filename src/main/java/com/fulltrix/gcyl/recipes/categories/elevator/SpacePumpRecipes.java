package com.fulltrix.gcyl.recipes.categories.elevator;

import com.fulltrix.gcyl.GCYLConfig;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraftforge.fluids.FluidStack;

import static gregtech.api.unification.material.Materials.*;

public class SpacePumpRecipes {

    public static Object2ObjectMap<String, FluidStack> GAS_SIPHON_RECIPES = new Object2ObjectOpenHashMap<>();

    public static Object2ObjectMap<Integer, String> PLANET_ID_TO_PLANET_NAME_MAP = new Object2ObjectOpenHashMap<>();

    public static void init() {
        initSpacePumpPlanets();
        gasSiphon();
    }

    private static void gasSiphon() {
        addSpacePumpRecipe(1,2, Hydrogen.getFluid(1000000));
        addSpacePumpRecipe(2,1, Helium3.getFluid(32000));
        addSpacePumpRecipe(3,4, Radon.getFluid(8000));
        addSpacePumpRecipe(4,3, Nitrogen.getFluid(1000000));
    }

    private static void initSpacePumpPlanets() {
        for(int i = 0; i < GCYLConfig.space.planetNames.length; i++) {
            PLANET_ID_TO_PLANET_NAME_MAP.put(i+1, GCYLConfig.space.planetNames[i]);
        }
    }

    private static void addSpacePumpRecipe(int planetID, int fluidID, FluidStack fluidStack) {
        try {
            if (planetID < 1 || fluidID < 1) {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new RuntimeException("Planet ID or Fluid ID is less than 1! This is not allowed!");
        }

        GAS_SIPHON_RECIPES.put(planetID + "," + fluidID, fluidStack);
    }

    public static String getPlanetNameByID(int id) {
        return PLANET_ID_TO_PLANET_NAME_MAP.get(id);
    }
}
