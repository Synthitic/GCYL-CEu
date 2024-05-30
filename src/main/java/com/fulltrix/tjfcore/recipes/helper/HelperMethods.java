package com.fulltrix.tjfcore.recipes.helper;

import com.fulltrix.tjfcore.recipes.TJFRecipeMaps;
import gregtech.api.GTValues;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.ore.OrePrefix;
import mezz.jei.plugins.vanilla.furnace.FuelRecipe;
import net.minecraftforge.fluids.FluidStack;

public class HelperMethods {

    /* TODO: implement
    public static void registerPlasmaFuel(FluidStack fuelStack, int duration, int tier) {
        RecipeMaps.PLASMA_GENERATOR_FUELS.addRecipe(new FuelRecipe(fuelStack, duration, GAValues.V[tier]));
    }

    public static void registerDieselGeneratorFuel(FluidStack fuelStack, int duration, int tier) {
        RecipeMaps.COMBUSTION_GENERATOR_FUELS.addRecipe(new FuelRecipe(fuelStack, duration, GAValues.V[tier]));
    }

    public static void registerSteamGeneratorFuel(FluidStack fuelStack, int duration, int tier) {
        RecipeMaps.STEAM_TURBINE_FUELS.addRecipe(new FuelRecipe(fuelStack, duration, GAValues.V[tier]));
    }

    public static void registerGasGeneratorFuel(FluidStack fuelStack, int duration, int tier) {
        RecipeMaps.GAS_TURBINE_FUELS.addRecipe(new FuelRecipe(fuelStack, duration, GAValues.V[tier]));
    }

    public static void registerSemiFluidGeneratorFuel(FluidStack fuelStack, int duration, int tier) {
        RecipeMaps.SEMI_FLUID_GENERATOR_FUELS.addRecipe(new FuelRecipe(fuelStack, duration, GAValues.V[tier]));
    }


    public static void registerNaquadahReactorFuel(FluidStack fuelStack, int duration, int tier) {
        TJFRecipeMaps.NAQUADAH_REACTOR_FUELS.addRecipe(new FuelRecipe(fuelStack, duration, GAValues.V[tier]));
    }

    public static void registerRocketFuel(FluidStack fuelStack, int duration, int tier) {
        TJFRecipeMaps.ROCKET_FUEL_RECIPES.addRecipe(new FuelRecipe(fuelStack, duration, GAValues.V[tier]));
    }

    public static void registerHotCoolantTurbineFuel(FluidStack input, FluidMaterial output, int duration, int tier) {
        TJFRecipeMaps.HOT_COOLANT_TURBINE_FUELS.addRecipe(new HotCoolantRecipe(input, duration, GAValues.V[tier], output.getFluid(input.amount)));
    }

     */

    public static void registerHyperReactorFuel(FluidStack fuelStack, int duration, int tier) {
        TJFRecipeMaps.HYPER_REACTOR_FUELS.recipeBuilder()
                .fluidInputs(fuelStack)
                .duration(duration)
                .EUt((int) GTValues.V[tier])
                .buildAndRegister();
    }

}
