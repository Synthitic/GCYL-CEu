package com.fulltrix.tjfcore.recipes.helper;

import com.fulltrix.tjfcore.recipes.TJFRecipeMaps;
import gregtech.api.GTValues;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityEnergyHatch;
import mezz.jei.plugins.vanilla.furnace.FuelRecipe;
import net.minecraftforge.fluids.FluidStack;

import java.util.Arrays;
import java.util.List;

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

    public static void registerNaquadahReactorFuel(FluidStack fuelStack, int duration, int tier) {
        TJFRecipeMaps.NAQUADAH_REACTOR_FUELS.recipeBuilder()
                .fluidInputs(fuelStack)
                .duration(duration)
                .EUt((int) GTValues.V[tier])
                .buildAndRegister();
    }

    public static void registerRocketFuel(FluidStack fuelStack, int duration, int tier) {
        TJFRecipeMaps.ROCKET_FUEL_RECIPES.recipeBuilder()
                .fluidInputs(fuelStack)
                .duration(duration)
                .EUt((int) GTValues.V[tier])
                .buildAndRegister();
    }

    // Don't mind the extra "s" on the method name, just Java not recognizing
    // 2 Lists with different generic types as different parameters for overloading.
    public static void registerMachineRecipes(List<MetaTileEntityEnergyHatch> metaTileEntities, Object... recipe) {
        for (MetaTileEntityEnergyHatch mte : metaTileEntities) {
            ModHandler.addShapedRecipe(String.format("gcyl_%s", mte.getMetaName()), mte.getStackForm(),
                    prepareRecipe(mte.getTier(), Arrays.copyOf(recipe, recipe.length)));
        }
    }

    private static Object[] prepareRecipe(int tier, Object... recipe) {
        //Don't assume that the recipes are all 3x3. Some recipes are 1x3 shaped, which will fail if the 3x3 assumption is made
        for (int i = 0; i < recipe.length; i++) {
            if (recipe[i] instanceof GCYLCraftingComponents) {
                recipe[i] = ((GCYLCraftingComponents) recipe[i]).getIngredient(tier);
            }
        }
        return recipe;
    }

}
