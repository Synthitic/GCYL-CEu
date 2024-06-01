package com.fulltrix.gcyl.recipes.categories.machines;

import com.fulltrix.gcyl.machines.GCYLTileEntities;

import static com.fulltrix.gcyl.recipes.helper.GCYLComponents.*;
import static gregtech.loaders.recipe.CraftingComponent.*;
import static gregtech.loaders.recipe.MetaTileEntityLoader.registerMachineRecipe;


public class SingleblockCraftingRecipes {

    public static void init() {
        newMachines();
        highTierMachines();
    }

    private static void newMachines() {

        registerMachineRecipe(GCYLTileEntities.ROCKET_GENERATOR,
                "PCP", "MHM", "GAG",
                'C', CIRCUIT,
                'M', MOTOR,
                'H', HULL,
                'G', PLATE_DENSE,
                'A', CABLE_DOUBLE,
                'P', PISTON);

        registerMachineRecipe(GCYLTileEntities.DEHYDRATOR,
                "WCW", "MHM", "GAG",
                'C', CIRCUIT,
                'M', CABLE_QUAD,
                'H', HULL,
                'G', GEAR,
                'A', ROBOT_ARM,
                'W', COIL_HEATING_DOUBLE);


        registerMachineRecipe(GCYLTileEntities.NAQUADAH_REACTOR,
                "RCR", "FMF", "QCQ",
                'M', HULL,
                'Q', CABLE_QUAD,
                'C', BETTER_CIRCUIT,
                'F', FIELD_GENERATOR,
                'R', STICK_RADIOACTIVE);


    }

    private static void highTierMachines() {

    }
}
