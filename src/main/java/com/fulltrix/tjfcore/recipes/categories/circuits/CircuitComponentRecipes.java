package com.fulltrix.tjfcore.recipes.categories.circuits;

import com.fulltrix.tjfcore.recipes.categories.circuits.components.*;

import static com.fulltrix.tjfcore.TJFMaterials.*;
import static com.fulltrix.tjfcore.item.TJFCoreItems.*;
import static gregtech.api.GTValues.L;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.NEURO_PROCESSOR;
import static gregtech.common.items.MetaItems.STEM_CELLS;

public class CircuitComponentRecipes {

    public static void init() { // TODO

        // primitiveSMD();
        //  refinedSMD();
        // microSMD();
        // nanoSMD();
        //quantumSMD();
        //CrystalComponents.init();
        circuitBoards();
        WetwareComponents.init();
        biowareSMD();
        OpticalComponents.init();
        ExoticComponents.init();
        CosmicComponents.init();
        SupraCausalComponents.init();
    }

    private static void biowareSMD() {

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(30720 * 4)
                .input(wireFine, Dubnium, 8)
                .input(plate, GermaniumTungstenNitride, 4)
                .fluidInputs(Polyimide.getFluid(L * 2))
                .outputs(SMD_TRANSISTOR_BIOWARE.getStackForm(32))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(30720 * 4)
                .input(wireFine, PEDOT, 8)
                .input(foil, Polytetrafluoroethylene, 4)
                .input(foil, BariumTitanate, 4)
                .fluidInputs(Polyimide.getFluid(L * 2))
                .outputs(SMD_CAPACITOR_BIOWARE.getStackForm(32))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(30720 * 4)
                .input(wireFine, Osmiridium, 8)
                .input(dust, AluminiumComplex)
                .input(dust, CopperGalliumIndiumSelenide)
                .fluidInputs(Polyimide.getFluid(L * 2))
                .outputs(SMD_DIODE_BIOWARE.getStackForm(32))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(30720 * 4)
                .input(wireFine, NaquadahAlloy, 6)
                .input(plate, BismuthRuthenate)
                .input(plate, BismuthIridiate)
                .fluidInputs(Polyimide.getFluid(L * 2))
                .outputs(SMD_RESISTOR_BIOWARE.getStackForm(24))
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
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(500).EUt(240)
                .inputs(KAPTON_BOARD.getStackForm())
                .input(foil, VanadiumGallium, 24)
                .fluidInputs(Iron3Chloride.getFluid(3000))
                .outputs(KAPTON_CIRCUIT_BOARD.getStackForm())
                .buildAndRegister();

        //TODO Red circuit board from GTNH
    }
}