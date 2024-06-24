package com.fulltrix.gcyl.recipes.categories;

import gregtech.api.GTValues;

import static com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps.COSMIC_RAY_DETECTOR_RECIPES;
import static com.fulltrix.gcyl.materials.GCYLMaterials.HeavyLeptonMix;
import static gregtech.api.GTValues.UHV;

public class CosmicRayDetectorRecipes {
    public static void init() {

        COSMIC_RAY_DETECTOR_RECIPES.recipeBuilder().EUt((int) GTValues.V[UHV]).duration(984)
                .circuitMeta(1)
                .fluidOutputs(HeavyLeptonMix.getFluid(200))
                .CWUt(64)
                .buildAndRegister();
    }
}
