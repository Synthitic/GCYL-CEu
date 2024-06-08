package com.fulltrix.gcyl.recipes.categories.circuits;

import com.fulltrix.gcyl.recipes.categories.circuits.components.*;
import gregtech.api.metatileentity.multiblock.CleanroomType;

import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static com.fulltrix.gcyl.item.GCYLCoreItems.*;
import static com.fulltrix.gcyl.materials.GCYLNuclearMaterials.Plutonium;
import static gregtech.api.GTValues.L;
import static gregtech.api.recipes.GTRecipeHandler.removeRecipesByInputs;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

public class CircuitComponentRecipes {

    public static void init() { // TODO

        //primitiveSMD();
        refinedSMD();
        nanoSMD();
        quantumSMD();
        CrystalComponents.init();
        circuitBoards();
        WetwareComponents.init();
        biowareSMD();
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

    private static void biowareSMD() {

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(30720 * 4)
                .input(wireFine, Dubnium, 8)
                .input(plate, GermaniumTungstenNitride, 4)
                .fluidInputs(Polyimide.getFluid(L * 2))
                .outputs(SMD_TRANSISTOR_BIOWARE.getStackForm(32))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(30720 * 4)
                .input(wireFine, PEDOT, 8)
                .input(foil, Polytetrafluoroethylene, 4)
                .input(foil, BariumTitanate, 4)
                .fluidInputs(Polyimide.getFluid(L * 2))
                .outputs(SMD_CAPACITOR_BIOWARE.getStackForm(32))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(30720 * 4)
                .input(wireFine, Osmiridium, 8)
                .input(dust, AluminiumComplex)
                .input(dust, CopperGalliumIndiumSelenide)
                .fluidInputs(Polyimide.getFluid(L * 2))
                .outputs(SMD_DIODE_BIOWARE.getStackForm(32))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(30720 * 4)
                .input(wireFine, NaquadahAlloy, 6)
                .input(plate, BismuthRuthenate)
                .input(plate, BismuthIridiate)
                .fluidInputs(Polyimide.getFluid(L * 2))
                .outputs(SMD_RESISTOR_BIOWARE.getStackForm(24))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(foil, SiliconeRubber, 32)
                .input(wireFine, NaquadahAlloy, 16)
                .inputs(SMD_TRANSISTOR_BIOWARE.getStackForm(16))
                .inputs(SMD_RESISTOR_BIOWARE.getStackForm(16))
                .inputs(SMD_DIODE_BIOWARE.getStackForm(16))
                .inputs(SMD_CAPACITOR_BIOWARE.getStackForm(16))
                .inputs(CYBER_PROCESSING_UNIT.getStackForm())
                .inputs(STEM_CELLS.getStackForm(4))
                .fluidInputs(Tritanium.getFluid(144))
                .outputs(NEURO_PROCESSOR.getStackForm(4))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .EUt(30720 * 16)
                .duration(150)
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

        //TODO Red circuit board from GTNH
    }
}