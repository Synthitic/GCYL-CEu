package com.fulltrix.tjfcore.recipes.categories.circuits;


import static com.fulltrix.tjfcore.TJFMaterials.*;
import static com.fulltrix.tjfcore.item.TJFCoreItems.*;
import static gregtech.api.GTValues.L;
import static gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES;
import static gregtech.api.recipes.RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

public class CircuitRecipes {
    public static void init() {

        /*
        removeGTCECircuitRecipes();

        primitiveCircuits();
        electronicCircuits();
        refinedCircuits();
        microCircuits();
        nanoCircuits();
        quantumCircuits();
        crystalCircuits();
        wetwareCircuits();


        exoticCircuits(); // TBD
        cosmicCircuits();
        supracausalCircuits();

        MagnetoRecipes.init();
        */
        biowareCircuits();
        opticalCircuits();
        CircuitComponentRecipes.init();
        WaferRecipes.init();
    }

    private static void biowareCircuits() {

        //TODO Change these

        // Bioware Processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(240000)
                .inputs(QUBIT_CENTRAL_PROCESSING_UNIT.getStackForm(4))
                .inputs(SMD_TRANSISTOR_BIOWARE.getStackForm(8))
                .inputs(SMD_CAPACITOR_BIOWARE.getStackForm(4))
                .inputs(NEURO_PROCESSOR.getStackForm())
                .inputs(HIGHLY_ADVANCED_SOC.getStackForm(4))
                .input(wireFine, NaquadahAlloy, 4)
                .outputs(BIOWARE_PROCESSOR.getStackForm(4))
                .solderMultiplier(4)
                .buildAndRegister();

        // Bioware Assembly
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(400).EUt(480000)
                .inputs(BIOWARE_PROCESSOR.getStackForm(3))
                .inputs(SMD_CAPACITOR_BIOWARE.getStackForm(16))
                .inputs(SMD_TRANSISTOR_BIOWARE.getStackForm(16))
                .inputs(SMD_DIODE_BIOWARE.getStackForm(16))
                .inputs(SMD_RESISTOR_BIOWARE.getStackForm(16))
                .input(wireGtSingle, UVSuperconductor, 4)
                .input(wireFine, PEDOT, 64)
                .inputs(ARAM.getStackForm(8))
                .input(plate, Duranium, 2)
                .input(foil, Polybenzimidazole, 16)
                .fluidInputs(SterileGrowthMedium.getFluid(1000))
                .fluidInputs(Titanium.getFluid(L * 9))
                .fluidInputs(Polyethylene.getFluid(L * 18))
                .fluidInputs(NaquadahEnriched.getFluid(L * 9))
                .outputs(BIOWARE_ASSEMBLY.getStackForm())
                .buildAndRegister();

        // Bioware Computer
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(480000)
                .inputs(BIOWARE_ASSEMBLY.getStackForm(4))
                .inputs(SMD_CAPACITOR_BIOWARE.getStackForm(32))
                .inputs(SMD_TRANSISTOR_BIOWARE.getStackForm(32))
                .inputs(SMD_DIODE_BIOWARE.getStackForm(32))
                .inputs(SMD_RESISTOR_BIOWARE.getStackForm(32))
                .input(wireGtSingle, UVSuperconductor, 16)
                .inputs(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(8))
                .inputs(ARAM.getStackForm(16))
                .input(plate, Seaborgium, 2)
                .input(foil, Polybenzimidazole, 16)
                .inputs(GRAVI_STAR.getStackForm(2))
                .fluidInputs(SterileGrowthMedium.getFluid(1000))
                .fluidInputs(Tritanium.getFluid(L * 2))
                .fluidInputs(Polybenzimidazole.getFluid(L * 9))
                .fluidInputs(NaquadahEnriched.getFluid(L * 9))
                .outputs(BIOWARE_COMPUTER.getStackForm())
                .buildAndRegister();

        // Bioware Mainframe
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(800).EUt(1920000)
                .inputs(BIOWARE_COMPUTER.getStackForm(2))
                .inputs(SMD_CAPACITOR_BIOWARE.getStackForm(64))
                .inputs(SMD_TRANSISTOR_BIOWARE.getStackForm(64))
                .inputs(SMD_DIODE_BIOWARE.getStackForm(64))
                .inputs(SMD_RESISTOR_BIOWARE.getStackForm(64))
                .input(wireGtSingle, UVSuperconductor, 32)
                .inputs(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(16))
                .inputs(ARAM.getStackForm(32))
                .input(foil, Tritanium, 16)
                .inputs(STEM_CELLS.getStackForm(32))
                .input(plate, Naquadria, 8)
                .input(foil, Polybenzimidazole, 64)
                .input(frameGt, Seaborgium, 4)
                .fluidInputs(SterileGrowthMedium.getFluid(1000))
                .fluidInputs(Tritanium.getFluid(L * 9))
                .fluidInputs(Polybenzimidazole.getFluid(L * 18))
                .fluidInputs(Naquadria.getFluid(L * 9))
                .outputs(BIOWARE_MAINFRAME.getStackForm())
                .buildAndRegister();
    }

    private static void opticalCircuits() {

        //TODO change these

        // Optical Processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(960000)
                .inputs(QUBIT_CENTRAL_PROCESSING_UNIT.getStackForm(4))
                .inputs(SMD_TRANSISTOR_OPTICAL.getStackForm(8))
                .inputs(SMD_CAPACITOR_OPTICAL.getStackForm(4))
                .inputs(OPTICAL_PROCESSING_CORE.getStackForm())
                .inputs(OPTICAL_SOC.getStackForm(4))
                .input(wireFine, Pikyonium, 4)
                .outputs(OPTICAL_PROCESSOR.getStackForm(4))
                .solderMultiplier(4)
                .buildAndRegister();

        // Optical Assembly
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(400).EUt(960000)
                .inputs(OPTICAL_PROCESSOR.getStackForm(3))
                .inputs(SMD_CAPACITOR_OPTICAL.getStackForm(16))
                .inputs(SMD_TRANSISTOR_OPTICAL.getStackForm(16))
                .inputs(SMD_DIODE_OPTICAL.getStackForm(16))
                .inputs(SMD_RESISTOR_OPTICAL.getStackForm(16))
                .input(wireGtSingle, RutheniumTriniumAmericiumNeutronate, 4)
                .input(wireFine, TungstenTitaniumCarbide, 64)
                .inputs(ARAM.getStackForm(16))
                .inputs(NANO_CENTRAL_PROCESSING_UNIT.getStackForm(64))
                .input(plate, HDCS, 2)
                .input(foil, Polyetheretherketone, 32)
                .fluidInputs(Duranium.getFluid(L * 9))
                .fluidInputs(Polytetrafluoroethylene.getFluid(L * 18))
                .fluidInputs(NaquadahEnriched.getFluid(L * 9))
                .outputs(OPTICAL_ASSEMBLY.getStackForm())
                .buildAndRegister();

        // Optical Computer
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(1920000)
                .inputs(OPTICAL_ASSEMBLY.getStackForm(4))
                .inputs(SMD_CAPACITOR_OPTICAL.getStackForm(32))
                .inputs(SMD_TRANSISTOR_OPTICAL.getStackForm(32))
                .inputs(SMD_DIODE_OPTICAL.getStackForm(32))
                .inputs(SMD_RESISTOR_OPTICAL.getStackForm(32))
                .inputs(OPTICAL_SOC.getStackForm(4))
                .input(wireGtSingle, RutheniumTriniumAmericiumNeutronate, 16)
                .inputs(CLADDED_OPTICAL_FIBER_CORE.getStackForm(16))
                .inputs(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(16))
                .inputs(ARAM.getStackForm(32))
                .input(plate, Quantum, 2)
                .input(foil, Polybenzimidazole, 16)
                .inputs(GRAVI_STAR.getStackForm(4))
                .fluidInputs(Tritanium.getFluid(L * 9))
                .fluidInputs(Polyetheretherketone.getFluid(L * 9))
                .fluidInputs(Adamantium.getFluid(L * 2))
                .outputs(OPTICAL_COMPUTER.getStackForm())
                .buildAndRegister();

        // Optical Mainframe
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(800).EUt(6000000)
                .inputs(OPTICAL_COMPUTER.getStackForm(2))
                .inputs(SMD_CAPACITOR_OPTICAL.getStackForm(64))
                .inputs(SMD_TRANSISTOR_OPTICAL.getStackForm(64))
                .inputs(SMD_DIODE_OPTICAL.getStackForm(64))
                .inputs(SMD_RESISTOR_OPTICAL.getStackForm(64))
                .input(wireGtSingle, RutheniumTriniumAmericiumNeutronate, 32)
                .inputs(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(32))
                .inputs(UHASOC.getStackForm(16))
                .inputs(ARAM.getStackForm(64))
                .input(plate, EnrichedNaquadahAlloy, 4)
                .input(frameGt, Bohrium, 4)
                .input(foil, Polyetheretherketone, 32)
                .inputs(UNSTABLE_STAR.getStackForm(8))
                .inputs(CLADDED_OPTICAL_FIBER_CORE.getStackForm(48))
                .fluidInputs(Cinobite.getFluid(L * 6))
                .fluidInputs(Adamantium.getFluid(L * 3))
                .fluidInputs(Polyetheretherketone.getFluid(L * 18))
                .fluidInputs(Naquadria.getFluid(L * 9))
                .outputs(OPTICAL_MAINFRAME.getStackForm())
                .buildAndRegister();
    }
}
