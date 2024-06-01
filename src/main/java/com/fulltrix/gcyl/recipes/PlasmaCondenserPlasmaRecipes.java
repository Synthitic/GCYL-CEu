package com.fulltrix.gcyl.recipes;

import gregtech.api.GTValues;
import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.material.Materials;

import static com.fulltrix.gcyl.GCYLMaterials.*;
import static com.fulltrix.gcyl.recipes.GCYLRecipeMaps.PLASMA_CONDENSER_RECIPES;
import static gregtech.api.unification.material.Materials.*;

public class PlasmaCondenserPlasmaRecipes {
    public static void init() {
        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .fluidInputs(Materials.Argon.getPlasma(100))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 100))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(Materials.Argon.getFluid(100))
                .fluidOutputs(Materials.Helium.getFluid(100))
                .duration((int) Materials.Argon.getMass()).EUt(960).buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .fluidInputs(Materials.Helium.getPlasma(100))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 100))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(Materials.Helium.getFluid(100))
                .fluidOutputs(Materials.Helium.getFluid(100))
                .duration((int) Materials.Helium.getMass()).EUt(960).buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .fluidInputs(Materials.Nitrogen.getPlasma(100))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 100))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(Materials.Nitrogen.getFluid(100))
                .fluidOutputs(Materials.Helium.getFluid(100))
                .duration((int) Materials.Nitrogen.getMass()).EUt(960).buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .fluidInputs(Materials.Oxygen.getPlasma(100))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 100))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(Materials.Oxygen.getFluid(100))
                .fluidOutputs(Materials.Helium.getFluid(100))
                .duration((int) Materials.Oxygen.getMass()).EUt(960).buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .fluidInputs(Materials.Radon.getPlasma(100))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 100))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(Materials.Radon.getFluid(100))
                .fluidOutputs(Materials.Helium.getFluid(100))
                .duration((int) Materials.Radon.getMass()).EUt(960).buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .fluidInputs(Krypton.getPlasma(100))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 100))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(Krypton.getFluid(100))
                .fluidOutputs(Materials.Helium.getFluid(100))
                .duration((int) Krypton.getMass()).EUt(960).buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .fluidInputs(Neon.getPlasma(100))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 100))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(Neon.getFluid(100))
                .fluidOutputs(Materials.Helium.getFluid(100))
                .duration((int) Neon.getMass()).EUt(960).buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .fluidInputs(HeliumCNO.getPlasma(100))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 100))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(HeliumCNO.getFluid(100))
                .fluidOutputs(Materials.Helium.getFluid(100))
                .duration((int) Materials.Helium.getMass()).EUt(960).buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .fluidInputs(Iron52.getPlasma(GTValues.L))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 100))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(Iron52.getFluid(GTValues.L))
                .fluidOutputs(Materials.Helium.getFluid(100))
                .duration((int) Materials.Iron.getMass()).EUt(960).buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .fluidInputs(Chromium48.getPlasma(GTValues.L))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 100))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(Chromium48.getFluid(GTValues.L))
                .fluidOutputs(Materials.Helium.getFluid(100))
                .duration((int) Materials.Chrome.getMass()).EUt(960).buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .fluidInputs(Titanium44.getPlasma(GTValues.L))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 100))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(Titanium44.getFluid(GTValues.L))
                .fluidOutputs(Materials.Helium.getFluid(100))
                .duration((int) Materials.Titanium.getMass()).EUt(960).buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .fluidInputs(Nickel56.getPlasma(GTValues.L))
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 100))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(Nickel56.getFluid(GTValues.L))
                .fluidOutputs(Materials.Helium.getFluid(100))
                .duration((int) Materials.Titanium.getMass()).EUt(960).buildAndRegister();
    }
}
