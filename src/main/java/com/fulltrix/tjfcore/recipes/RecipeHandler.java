package com.fulltrix.tjfcore.recipes;

import com.fulltrix.tjfcore.recipes.categories.circuits.CircuitRecipes;

public class RecipeHandler {
    public static void initRecipes() {

        /*
        RecipeOverride.init();

        MachineCraftingRecipes.init();
        ComponentRecipes.init();
        MetaItemRecipes.init();
        CasingRecipes.init();
        SuperconductorRecipes.init();
        MiscRecipes.init();
        StagedRemovalRecipes.init();
        MetalCasingRecipes.init();
        PlasmaCondenserPlasmaRecipes.init();
         */

        CircuitRecipes.init();
    }
}