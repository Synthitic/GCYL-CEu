package com.fulltrix.gcyl.recipes.categories.circuits;

import com.fulltrix.gcyl.GCYLConfig;
import com.fulltrix.gcyl.recipes.categories.circuits.components.*;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.unification.material.MarkerMaterials;

import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static com.fulltrix.gcyl.item.GCYLCoreItems.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.GTRecipeHandler.removeRecipesByInputs;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

public class CircuitComponentRecipes {

    public static void init() { // TODO

        //primitiveSMD();
        refinedSMD();
        microSMD();
        nanoSMD();
        quantumSMD();
        CrystalComponents.init();
        circuitBoards();
        WetwareComponents.init();
        BioWareComponents.init();
        OpticalComponents.init();
        ExoticComponents.init();
        CosmicComponents.init();
        SupraCausalComponents.init();
    }

    private static void refinedSMD() {

        // Transistor
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(96)
                .input(wireFine, Copper, 6)
                .input(plate, Silver)
                .fluidInputs(Polyethylene.getFluid(L))
                .outputs(SMD_TRANSISTOR_REFINED.getStackForm(32))
                .buildAndRegister();

        // Resistor
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(96)
                .input(wireFine, Copper, 4)
                .input(dust, Carbon)
                .fluidInputs(Polyethylene.getFluid(L))
                .outputs(SMD_RESISTOR_REFINED.getStackForm(24))
                .buildAndRegister();

        // Capacitor
        ASSEMBLER_RECIPES.recipeBuilder().duration(60).EUt(120)
                .input(foil, Rubber, 4)
                .input(foil, Steel)
                .fluidInputs(Polyethylene.getFluid(L))
                .outputs(SMD_CAPACITOR_REFINED.getStackForm(16))
                .buildAndRegister();

        // Diode
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30)
                .input(wireFine, Gold, 8)
                .input(dust, Lithium)
                .fluidInputs(Polyethylene.getFluid(L))
                .outputs(SMD_DIODE_REFINED.getStackForm(32))
                .buildAndRegister();
    }

    private static void microSMD() {
        // SMD Resistor
        ASSEMBLER_RECIPES.recipeBuilder()
                .input(dust, Carbon)
                .input(wireFine, Electrum, 4)
                .fluidInputs(Polyethylene.getFluid(L * 2))
                .output(SMD_RESISTOR, 16)
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(160).EUt(VA[MV]).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(dust, Carbon)
                .input(wireFine, Tantalum, 4)
                .fluidInputs(Polyethylene.getFluid(L * 2))
                .output(SMD_RESISTOR, 32)
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(160).EUt(VA[MV]).buildAndRegister();

        // SMD Diode
        ASSEMBLER_RECIPES.recipeBuilder()
                .input(dust, GalliumArsenide)
                .input(wireFine, Platinum, 8)
                .fluidInputs(Polyethylene.getFluid(L * 2))
                .output(SMD_DIODE, 32)
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(200).EUt(VA[MV]).buildAndRegister();

        // SMD Transistor
        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, Gallium)
                .input(wireFine, AnnealedCopper, 8)
                .fluidInputs(Polyethylene.getFluid(L))
                .output(SMD_TRANSISTOR, 16)
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(160).EUt(VA[MV]).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, Gallium)
                .input(wireFine, Tantalum, 8)
                .fluidInputs(Polyethylene.getFluid(L))
                .output(SMD_TRANSISTOR, 32)
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(160).EUt(VA[MV]).buildAndRegister();

        // SMD Capacitor
        ASSEMBLER_RECIPES.recipeBuilder()
                .input(foil, SiliconeRubber)
                .input(foil, Aluminium)
                .fluidInputs(Polyethylene.getFluid(L / 2))
                .output(SMD_CAPACITOR, 8)
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(80).EUt(VA[MV]).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(foil, PolyvinylChloride, 2)
                .input(foil, Aluminium)
                .fluidInputs(Polyethylene.getFluid(L / 2))
                .output(SMD_CAPACITOR, 12)
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(80).EUt(VA[MV]).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(foil, SiliconeRubber)
                .input(foil, Tantalum)
                .fluidInputs(Polyethylene.getFluid(L / 2))
                .output(SMD_CAPACITOR, 16)
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(120).EUt(VA[MV]).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(foil, PolyvinylChloride, 2)
                .input(foil, Tantalum)
                .fluidInputs(Polyethylene.getFluid(L / 2))
                .output(SMD_CAPACITOR, 24)
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(120).EUt(VA[MV]).buildAndRegister();
    }

    private static void nanoSMD() {

        // Transistor
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(494)
                .input(wireFine, Palladium, 12)
                .input(plate, Magnalium)
                .fluidInputs(Polyethylene.getFluid(L))
                .outputs(SMD_TRANSISTOR_NANO.getStackForm(32))
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

        // Resistor
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(494)
                .input(wireFine, Cerium, 8)
                .input(dust, Graphite)
                .fluidInputs(Polyethylene.getFluid(L))
                .outputs(SMD_RESISTOR_NANO.getStackForm(24))
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

        // Capacitor
        ASSEMBLER_RECIPES.recipeBuilder().duration(60).EUt(480)
                .input(foil, Silicon, 4)
                .input(foil, Titanium)
                .fluidInputs(Polyethylene.getFluid(L))
                .outputs(SMD_CAPACITOR_NANO.getStackForm(16))
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

        // Diode
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(120)
                .input(wireFine, ReinforcedEpoxyResin, 8)
                .input(dust, Caesium)
                .fluidInputs(Polyethylene.getFluid(L))
                .outputs(SMD_DIODE_NANO.getStackForm(32))
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();
    }

    private static void quantumSMD() {

        // Transistor
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(1976)
                .input(wireFine, Plutonium, 12)
                .input(plate, Americium)
                .fluidInputs(Polyethylene.getFluid(L))
                .outputs(SMD_TRANSISTOR_QUANTUM.getStackForm(32))
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

        // Resistor
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(1976)
                .input(wireFine, Ruthenium, 8).input(plate, Graphene)
                .fluidInputs(Polyethylene.getFluid(L))
                .outputs(SMD_RESISTOR_QUANTUM.getStackForm(24))
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

        // Capacitor
        ASSEMBLER_RECIPES.recipeBuilder().duration(60).EUt(1920)
                .input(foil, SiliconeRubber, 4)
                .input(foil, Tungsten)
                .fluidInputs(Polyethylene.getFluid(L))
                .outputs(SMD_CAPACITOR_QUANTUM.getStackForm(16))
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

        // Diode
        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(480)
                .input(wireFine, HSSG, 8)
                .input(dust, Polonium)
                .fluidInputs(Polyethylene.getFluid(L))
                .outputs(SMD_DIODE_QUANTUM.getStackForm(32))
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();
    }


    private static void circuitBoards() {

        // Kapton Circuit Board
        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(300).EUt(240)
                .input(plate, Polyimide)
                .fluidInputs(FluorinatedEthylenePropylene.getFluid(L / 2))
                .outputs(KAPTON_BOARD.getStackForm())
                .buildAndRegister();

        // Insane Circuit Board
        CHEMICAL_RECIPES.recipeBuilder().duration(500).EUt(240)
                .inputs(KAPTON_BOARD.getStackForm())
                .input(foil, VanadiumGallium, 24)
                .fluidInputs(SodiumPersulfate.getFluid(6000))
                .outputs(KAPTON_CIRCUIT_BOARD.getStackForm())
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(500).EUt(240)
                .inputs(KAPTON_BOARD.getStackForm())
                .input(foil, VanadiumGallium, 24)
                .fluidInputs(Iron3Chloride.getFluid(3000))
                .outputs(KAPTON_CIRCUIT_BOARD.getStackForm())
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

        //BIO BOARD
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(1200).EUt(GTValues.VA[GTValues.UV])
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .inputs(WETWARE_BOARD.getStackForm(32))
                .inputs(PETRI_DISH.getStackForm(8))
                .inputs(ELECTRIC_PUMP_ZPM.getStackForm())
                .inputs(SENSOR_LuV.getStackForm(2))
                .input(circuit, GCYLConfig.recipes.hardMode ? MarkerMaterials.Tier.UV : MarkerMaterials.Tier.LuV)
                .input(foil, TitanSteel, 32)
                .fluidInputs(SterileBioGrowthMedium.getFluid(16000))
                .outputs(BIO_BOARD.getStackForm(32))
                .buildAndRegister();


        //BIO CIRCUIT BOARD
        CHEMICAL_RECIPES.recipeBuilder().duration(2400).EUt(GTValues.VA[GTValues.EV])
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .inputs(BIO_BOARD.getStackForm(1))
                .input(foil, TitanSteel, 64)
                .fluidInputs(Iron3Chloride.getFluid(7500))
                .outputs(BIO_CIRCUIT_BOARD.getStackForm())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(2400).EUt(GTValues.VA[GTValues.EV])
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .inputs(BIO_BOARD.getStackForm(1))
                .input(foil, TitanSteel, 64)
                .fluidInputs(SodiumPersulfate.getFluid(7500 * 2))
                .outputs(BIO_CIRCUIT_BOARD.getStackForm())
                .buildAndRegister();

    }
}