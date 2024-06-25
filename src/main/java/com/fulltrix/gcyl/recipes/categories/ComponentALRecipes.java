package com.fulltrix.gcyl.recipes.categories;

import gregtech.api.GTValues;
import gregtech.api.unification.material.MarkerMaterials;

import static com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps.COMPONENT_AL_RECIPES;
import static com.fulltrix.gcyl.materials.GCYLMaterials.LuVSuperconductor;
import static com.fulltrix.gcyl.materials.GCYLMaterials.ZincSelenide;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;
import static gregtech.api.GTValues.L;

public class ComponentALRecipes {
    public static void init() {

        COMPONENT_AL_RECIPES.recipeBuilder().EUt(GTValues.VA[GTValues.LV]).duration(600)
                .CasingTier(GTValues.LV)
                .input(stickLong, Iron, 48)
                .input(cableGtHex, Tin, 6)
                .input(wireGtHex, Copper, 12)
                .input(stickLong, IronMagnetic, 24)
                .outputs(ELECTRIC_MOTOR_LV.getStackForm(64))
                .buildAndRegister();

        luv();

    }

    private static void luv() {

        COMPONENT_AL_RECIPES.recipeBuilder().EUt(GTValues.VA[GTValues.LuV]).duration(28800)
                .outputs(ELECTRIC_MOTOR_LuV.getStackForm(64))
                .CasingTier(GTValues.LuV)
                .circuitMeta(1)
                .input(stickLong, NeodymiumMagnetic, 48)
                .input(cableGtHex, YttriumBariumCuprate, 6)
                .fluidInputs(SolderingAlloy.getFluid(L * 48))
                .fluidInputs(Lubricant.getFluid(12000))
                .fluidInputs(Ruridit.getFluid(L * 1536))
                .fluidInputs(HSSS.getFluid(20352))
                .buildAndRegister();

        COMPONENT_AL_RECIPES.recipeBuilder().EUt(GTValues.VA[GTValues.LuV]).duration(28800)
                .outputs(ELECTRIC_PISTON_LUV.getStackForm(64))
                .CasingTier(GTValues.LuV)
                .circuitMeta(2)
                .input(plateDense, HSSS, 21)
                .inputs(ELECTRIC_MOTOR_LuV.getStackForm(48))
                .input(cableGtHex, YttriumBariumCuprate, 6)
                .fluidInputs(SolderingAlloy.getFluid(L * 48))
                .fluidInputs(Lubricant.getFluid(12000))
                .fluidInputs(HSSS.getFluid(74544))
                .buildAndRegister();

        COMPONENT_AL_RECIPES.recipeBuilder().EUt(GTValues.VA[GTValues.LuV]).duration(28800)
                .outputs(ELECTRIC_PUMP_LuV.getStackForm(64))
                .CasingTier(GTValues.LuV)
                .circuitMeta(3)
                .input(cableGtHex, YttriumBariumCuprate, 6)
                .input(plateDense, HSSS, 10)
                .inputs(ELECTRIC_MOTOR_LuV.getStackForm(48))
                .fluidInputs(SolderingAlloy.getFluid(L * 48))
                .fluidInputs(Lubricant.getFluid(12000))
                .fluidInputs(NiobiumTitanium.getFluid(L * 96))
                .fluidInputs(HSSS.getFluid(47616))
                .fluidInputs(SiliconeRubber.getFluid(L * 48))
                .buildAndRegister();

        COMPONENT_AL_RECIPES.recipeBuilder().EUt(GTValues.VA[GTValues.LuV]).duration(28800)
                .outputs(ROBOT_ARM_LuV.getStackForm(64))
                .CasingTier(GTValues.LuV)
                .circuitMeta(4)
                .input(circuit, MarkerMaterials.Tier.LuV, 48)
                .input(circuit, MarkerMaterials.Tier.IV, 96)
                .input(circuit, MarkerMaterials.Tier.EV, 192)
                .input(cableGtHex, YttriumBariumCuprate, 18)
                .inputs(ELECTRIC_PISTON_LUV.getStackForm(48))
                .inputs(ELECTRIC_MOTOR_LuV.getStackForm(96))
                .fluidInputs(SolderingAlloy.getFluid(192 * L))
                .fluidInputs(Lubricant.getFluid(12000))
                .fluidInputs(HSSS.getFluid(528 * L))
                .buildAndRegister();

        COMPONENT_AL_RECIPES.recipeBuilder().EUt(GTValues.VA[GTValues.LuV]).duration(28800)
                .outputs(EMITTER_LuV.getStackForm(64))
                .CasingTier(GTValues.LuV)
                .circuitMeta(6)
                .input(cableGtHex, YttriumBariumCuprate, 24)
                .inputs(ELECTRIC_MOTOR_LuV.getStackForm(48))
                .input(gemExquisite, Ruby, 96)
                .input(frameGt, HSSS, 48)
                .input(circuit, MarkerMaterials.Tier.LuV, 96)
                .inputs(QUANTUM_STAR.getStackForm(48))
                .fluidInputs(SolderingAlloy.getFluid(L * 96))
                .fluidInputs(Ruridit.getFluid(L * 96))
                .fluidInputs(Palladium.getFluid(1536 * L))
                .fluidInputs(ZincSelenide.getFluid(768 * L))
                .buildAndRegister();


        COMPONENT_AL_RECIPES.recipeBuilder().EUt(GTValues.VA[GTValues.LuV]).duration(28800)
                .outputs(SENSOR_LuV.getStackForm(64))
                .CasingTier(GTValues.LuV)
                .circuitMeta(7)
                .input(cableGtHex, YttriumBariumCuprate, 24)
                .inputs(ELECTRIC_MOTOR_LuV.getStackForm(48))
                .input(gemExquisite, Ruby, 96)
                .input(plateDense, Ruridit, 21)
                .input(frameGt, HSSS, 48)
                .input(circuit, MarkerMaterials.Tier.LuV, 96)
                .inputs(QUANTUM_STAR.getStackForm(48))
                .fluidInputs(SolderingAlloy.getFluid(L * 96))
                .fluidInputs(Palladium.getFluid(1536 * L))
                .fluidInputs(Germanium.getFluid(768 * L))
                .buildAndRegister();

        COMPONENT_AL_RECIPES.recipeBuilder().EUt(GTValues.VA[GTValues.LuV]).duration(28800)
                .outputs(FIELD_GENERATOR_LuV.getStackForm(64))
                .CasingTier(GTValues.LuV)
                .circuitMeta(8)
                .inputs(QUANTUM_STAR.getStackForm(48))
                .input(frameGt, HSSS, 48)
                .input(plateDense, HSSS, 32)
                .input(cableGtHex, YttriumBariumCuprate, 48)
                .inputs(EMITTER_LuV.getStackForm(96))
                .input(circuit, MarkerMaterials.Tier.LuV, 384)
                .fluidInputs(SolderingAlloy.getFluid(L * 192))
                .fluidInputs(LuVSuperconductor.getFluid(768 * L))
                .buildAndRegister();
    }

    private static void zpm() {

    }
}
