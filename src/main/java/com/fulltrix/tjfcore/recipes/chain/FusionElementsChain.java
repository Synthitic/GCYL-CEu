package com.fulltrix.tjfcore.recipes.chain;

import static com.fulltrix.tjfcore.recipes.TJFRecipeMaps.ADV_FUSION_RECIPES;
import static gregtech.api.unification.material.Materials.Carbon;
import static gregtech.api.unification.material.Materials.Helium;

public class FusionElementsChain {
    public static void init() {
        advFusionRecipes();
    }

    private static void advFusionRecipes() {
        // Alpha Process
        ADV_FUSION_RECIPES.recipeBuilder().duration(384).EUt(7000).EUToStart(1200000000).AdvCoilTier(1).EUReturn(100)
                .fluidInputs(Helium.getFluid(375 * 2))
                .fluidInputs(Helium.getFluid(375 * 2))
                .fluidOutputs(Carbon.getPlasma(250 * 2))
                .buildAndRegister();
    }
}
