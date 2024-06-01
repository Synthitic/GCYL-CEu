package com.fulltrix.gcyl.recipes.categories;

import static com.fulltrix.gcyl.GCYLMaterials.*;
import static com.fulltrix.gcyl.recipes.GCYLRecipeMaps.ADVANCED_MIXER_RECIPES;
import static gregicality.multiblocks.api.unification.GCYMMaterials.Stellite100;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.Materials.Bismuth;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class MiscRecipes {
    public static void init() {
        // Quantum Dust
        ADVANCED_MIXER_RECIPES.recipeBuilder().duration(10500).EUt(30)
                .input(dust, Stellite100, 15)
                .input(dust, Jasper, 5)
                .input(dust, Gallium, 5)
                .input(dust, Americium241, 5)
                .input(dust, Palladium, 5)
                .input(dust, Bismuth, 5)
                .input(dust, Germanium, 5)
                .input(dust,SiliconCarbide,5)
                .output(dust, Quantum, 50)
                .buildAndRegister();
    }
}
