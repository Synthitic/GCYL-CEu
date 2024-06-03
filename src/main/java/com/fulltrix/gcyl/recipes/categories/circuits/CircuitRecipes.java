package com.fulltrix.gcyl.recipes.categories.circuits;


import gregtech.api.GTValues;

import static com.fulltrix.gcyl.GCYLMaterials.*;
import static com.fulltrix.gcyl.item.GCYLCoreItems.*;
import static gregtech.api.GTValues.L;
import static gregtech.api.GTValues.VA;
import static gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES;
import static gregtech.api.recipes.RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;


public class CircuitRecipes {

    //TODO: higher tier soldering alloys

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
        */
        MagnetoRecipes.init();
        wetwareCircuits();
        biowareCircuits();
        opticalCircuits();
        exoticCircuits();
        cosmicCircuits();
        supracausalCircuits();

        CircuitComponentRecipes.init();
        WaferRecipes.init();
    }

    private static void wetwareCircuits() {

        // Wetware Processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(56000)
                .inputs(CRYSTAL_SYSTEM_ON_CHIP.getStackForm())
                .inputs(SMD_TRANSISTOR_WETWARE.getStackForm(8))
                .inputs(SMD_CAPACITOR_WETWARE.getStackForm(4))
                .inputs(CYBER_PROCESSING_UNIT.getStackForm())
                .inputs(QUBIT_CENTRAL_PROCESSING_UNIT.getStackForm())
                .input(wireFine, YttriumBariumCuprate, 2)
                .outputs(WETWARE_PROCESSOR_LUV.getStackForm(4))
                .buildAndRegister();

        // ASoC Recipe
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(480000)
                .inputs(CYBER_PROCESSING_UNIT.getStackForm())
                .inputs(ADVANCED_SYSTEM_ON_CHIP.getStackForm(4))
                .input(wireFine, NaquadahAlloy, 8)
                .outputs(WETWARE_PROCESSOR_LUV.getStackForm(4))
                .buildAndRegister();

        // Wetware Assembly
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(400).EUt(120000)
                .inputs(WETWARE_PROCESSOR_LUV.getStackForm(3))
                .inputs(SMD_RESISTOR_WETWARE.getStackForm(16))
                .inputs(SMD_TRANSISTOR_WETWARE.getStackForm(16))
                .inputs(SMD_CAPACITOR_WETWARE.getStackForm(16))
                .inputs(SMD_DIODE_WETWARE.getStackForm(16))
                .inputs(ARAM.getStackForm(4))
                .inputs(QUANTUM_EYE.getStackForm(4))
                .input(wireGtSingle, ZPMSuperconductor, 4)
                .input(wireFine, NaquadahAlloy, 64)
                .input(foil, SiliconeRubber, 16)
                .fluidInputs(SterileGrowthMedium.getFluid(2000))
                .outputs(WETWARE_PROCESSOR_ASSEMBLY_ZPM.getStackForm())
                .buildAndRegister();

        // Wetware Supercomputer
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(122880)
                .inputs(WETWARE_PROCESSOR_ASSEMBLY_ZPM.getStackForm(4))
                .inputs(SMD_RESISTOR_WETWARE.getStackForm(32))
                .inputs(SMD_TRANSISTOR_WETWARE.getStackForm(32))
                .inputs(SMD_CAPACITOR_WETWARE.getStackForm(32))
                .inputs(SMD_DIODE_WETWARE.getStackForm(32))
                .inputs(QUANTUM_STAR.getStackForm(4))
                .inputs(ARAM.getStackForm(8))
                .input(plate, Rutherfordium, 2)
                .input(wireGtSingle, ZPMSuperconductor, 16)
                .input(foil, SiliconeRubber, 32)
                .fluidInputs(SterileGrowthMedium.getFluid(2000))
                .outputs(WETWARE_SUPER_COMPUTER_UV.getStackForm())
                .buildAndRegister();

        // Wetware Mainframe
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(2000).EUt(300000)
                .inputs(WETWARE_SUPER_COMPUTER_UV.getStackForm(2))
                .inputs(SMD_RESISTOR_WETWARE.getStackForm(64))
                .inputs(SMD_TRANSISTOR_WETWARE.getStackForm(64))
                .inputs(SMD_CAPACITOR_WETWARE.getStackForm(64))
                .inputs(SMD_DIODE_WETWARE.getStackForm(64))
                .inputs(HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(8))
                .inputs(ARAM.getStackForm(16))
                .inputs(GRAVI_STAR.getStackForm(4))
                .input(frameGt, Tritanium, 4)
                .input(plate, Duranium, 4)
                .input(wireGtSingle, ZPMSuperconductor, 32)
                .input(foil, Polytetrafluoroethylene, 64)
                .fluidInputs(SterileGrowthMedium.getFluid(2000))
                .fluidInputs(UUMatter.getFluid(100))
                .outputs(WETWARE_MAINFRAME_UHV.getStackForm())
                .buildAndRegister();
    }

    private static void biowareCircuits() {

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
                .input(wireGtSingle, UHVSuperconductor, 4)
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
                .input(wireGtSingle, UHVSuperconductor, 16)
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
                .input(wireGtSingle, UHVSuperconductor, 32)
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

    private static void exoticCircuits() {

        //Exotic Processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt((int)4E+6)
                .inputs(SMD_CAPACITOR_EXOTIC.getStackForm(8))
                .inputs(SMD_TRANSISTOR_EXOTIC.getStackForm(8))
                .inputs(EXOTIC_PROCESSING_CORE.getStackForm())
                .input(wireFine, Cinobite, 4)
                .inputs(QUBIT_CENTRAL_PROCESSING_UNIT.getStackForm(4))
                .inputs(EXOTIC_CHIP.getStackForm(4))
                .solderMultiplier(4)
                .outputs(EXOTIC_PROCESSOR.getStackForm(4))
                .buildAndRegister();

        //Exotic Assembly
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(200).EUt((int)4E+6)
                .inputs(SMD_RESISTOR_EXOTIC.getStackForm(16))
                .inputs(SMD_DIODE_EXOTIC.getStackForm(16))
                .inputs(SMD_TRANSISTOR_EXOTIC.getStackForm(16))
                .inputs(SMD_CAPACITOR_EXOTIC.getStackForm(16))
                .inputs(EXOTIC_PROCESSOR.getStackForm(3))
                .input(wireFine, Cinobite, 64)
                .input(foil, Zylon, 4)
                .inputs(QUBIT_CENTRAL_PROCESSING_UNIT.getStackForm(64))
                .input(plate, EnrichedNaquadahAlloy, 2)
                .inputs(ARAM.getStackForm(32))
                .input(wireGtSingle, UEVSuperconductor, 4)
                .fluidInputs(EnrichedNaquadahAlloy.getFluid(L * 6))
                .fluidInputs(TriniumTitanium.getFluid(L * 4))
                .fluidInputs(Quantum.getFluid(L * 6))
                .fluidInputs(QuantumDots.getFluid(250))
                .outputs(EXOTIC_ASSEMBLY.getStackForm())
                .buildAndRegister();

        //Exotic Computer
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(300).EUt((int)4E+6)
                .inputs(SMD_DIODE_EXOTIC.getStackForm(32))
                .inputs(SMD_RESISTOR_EXOTIC.getStackForm(32))
                .inputs(SMD_TRANSISTOR_EXOTIC.getStackForm(32))
                .inputs(SMD_CAPACITOR_EXOTIC.getStackForm(32))
                .inputs(EXOTIC_ASSEMBLY.getStackForm(4))
                .input(foil, Zylon, 32)
                .inputs(EXOTIC_CHIP.getStackForm(8))
                .inputs(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(32))
                .inputs(ARAM.getStackForm(64))
                .input(plate, EnrichedNaquadahAlloy, 4)
                .input(wireGtSingle, UEVSuperconductor, 16)
                .fluidInputs(Polyetheretherketone.getFluid(L * 18))
                .fluidInputs(Vibranium.getFluid(L * 2))
                .fluidInputs(EnrichedNaquadahAlloy.getFluid(L * 9))
                .fluidInputs(TriniumTitanium.getFluid(L * 4))
                .outputs(EXOTIC_COMPUTER.getStackForm())
                .buildAndRegister();

        //Exotic Mainframe
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(400).EUt((int)1E+7)
                .inputs(SMD_RESISTOR_EXOTIC.getStackForm(64))
                .inputs(SMD_DIODE_EXOTIC.getStackForm(64))
                .inputs(SMD_TRANSISTOR_EXOTIC.getStackForm(64))
                .inputs(SMD_CAPACITOR_EXOTIC.getStackForm(64))
                .inputs(EXOTIC_COMPUTER.getStackForm(2))
                .input(foil, FullerenePolymerMatrix, 16)
                //.inputs(QBIT_CENTRAL_PROCESSING_UNIT.getStackForm(4))
                .inputs(UHASOC.getStackForm(32))
                .input(frameGt, Vibranium, 4)
                .inputs(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64))
                .inputs(ARAM.getStackForm(64))
                .inputs(ARAM.getStackForm(64))
                .input(plate, MetastableFlerovium, 4)
                .input(wireGtSingle, UEVSuperconductor, 32)
                .fluidInputs(LiquidEnrichedHelium.getFluid(1000))
                .fluidInputs(FullerenePolymerMatrix.getFluid(L * 4))
                .fluidInputs(Quantum.getFluid(L * 9))
                .fluidInputs(Naquadria.getFluid(L * 9))
                .outputs(EXOTIC_MAINFRAME.getStackForm())
                .buildAndRegister();
    }

    private static void cosmicCircuits() {

        // Cosmic Processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(30_720_000)
                .inputs(QUBIT_CENTRAL_PROCESSING_UNIT.getStackForm(4))
                .inputs(SMD_TRANSISTOR_COSMIC.getStackForm(8))
                .inputs(SMD_CAPACITOR_COSMIC.getStackForm(16))
                .inputs(COSMIC_PROCESSING_CORE.getStackForm())
                .inputs(UHASOC.getStackForm(4))
                .input(wireFine, AbyssalAlloy, 4)
                .outputs(COSMIC_PROCESSOR.getStackForm(8))
                .solderMultiplier(4)
                .buildAndRegister();

        // Cosmic Assembly
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(30_720_000)
                .inputs(COSMIC_PROCESSOR.getStackForm(3))
                .inputs(SMD_CAPACITOR_COSMIC.getStackForm(32))
                .inputs(SMD_TRANSISTOR_COSMIC.getStackForm(32))
                .inputs(SMD_DIODE_COSMIC.getStackForm(32))
                .inputs(SMD_RESISTOR_COSMIC.getStackForm(32))
                .input(wireGtSingle, UIVSuperconductor, 4)
                .input(wireFine, MetastableOganesson, 64)
                .input(wireFine, MetastableHassium, 64)
                .input(wireFine, MetastableFlerovium, 64)
                .inputs(ARAM.getStackForm(64))
                .input(plate, Quantum, 2)
                .input(foil, Zylon, 16)
                .fluidInputs(Tritanium.getFluid(L * 18))
                .fluidInputs(Polyetheretherketone.getFluid(L * 18))
                .fluidInputs(NaquadahEnriched.getFluid(L * 9))
                .outputs(COSMIC_ASSEMBLY.getStackForm())
                .buildAndRegister();

        // Cosmic Computer
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(30_720_000)
                .inputs(COSMIC_ASSEMBLY.getStackForm(4))
                .inputs(SMD_CAPACITOR_COSMIC.getStackForm(48))
                .inputs(SMD_TRANSISTOR_COSMIC.getStackForm(48))
                .inputs(SMD_DIODE_COSMIC.getStackForm(48))
                .inputs(SMD_RESISTOR_COSMIC.getStackForm(48))
                .input(wireGtSingle, UIVSuperconductor, 16)
                .inputs(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64))
                .inputs(ARAM.getStackForm(64))
                .inputs(ARAM.getStackForm(64))
                .inputs(OPTICAL_SOC.getStackForm(16))
                .input(plate, HastelloyK243, 4)
                .input(foil, FullerenePolymerMatrix, 12)
                .inputs(GRAVI_STAR.getStackForm(32))
                .fluidInputs(CosmicComputingMix.getFluid(4000))
                .fluidInputs(Tritanium.getFluid(L * 9))
                .fluidInputs(Zylon.getFluid(L * 18))
                .fluidInputs(Naquadria.getFluid(L * 9))
                .outputs(COSMIC_COMPUTER.getStackForm())
                .buildAndRegister();

        // Cosmic Mainframe
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(200).EUt(30_720_000)
                .inputs(COSMIC_COMPUTER.getStackForm(2))
                .inputs(SMD_CAPACITOR_COSMIC.getStackForm(64))
                .inputs(SMD_TRANSISTOR_COSMIC.getStackForm(64))
                .inputs(SMD_DIODE_COSMIC.getStackForm(64))
                .inputs(SMD_RESISTOR_COSMIC.getStackForm(64))
                .inputs(COSMIC_COMPUTE_UNIT.getStackForm(16))
                .input(wireGtSingle, UIVSuperconductor, 32)
                .inputs(UHASOC.getStackForm(64))
                .inputs(PHOTOCOATED_HASSIUM_WAFER.getStackForm(16))
                .input(frameGt, HeavyQuarkDegenerateMatter, 4)
                .input(plate, HastelloyK243, 8)
                .input(foil, FullerenePolymerMatrix, 64)
                .inputs(UNSTABLE_STAR.getStackForm(4))
                .fluidInputs(Taranium.getFluid(L * 2))
                .fluidInputs(TriniumTitanium.getFluid(L * 9))
                .fluidInputs(Zylon.getFluid(L * 18))
                .fluidInputs(Vibranium.getFluid(L * 6))
                .outputs(COSMIC_MAINFRAME.getStackForm())
                .buildAndRegister();
    }


    private static void supracausalCircuits() {

        // Supracausal Processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(122_880_000)
                .inputs(COSMIC_COMPUTE_UNIT.getStackForm(16))
                .inputs(MANIFOLD_OSCILLATORY_POWER_CELL.getStackForm())
                .inputs(MICROWORMHOLE_GENERATOR.getStackForm())
                .inputs(SUPRACAUSAL_PROCESSING_CORE.getStackForm())
                .input(plate, SuperheavyHAlloy)
                .input(wireGtSingle, UXVSuperconductor, 2)
                .outputs(SUPRACAUSAL_PROCESSOR.getStackForm(4))
                .solderMultiplier(4)
                .buildAndRegister();

        // Supracausal Assembly
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(50).EUt(122_880_000)
                .inputs(SMD_CAPACITOR_SUPRACAUSAL.getStackForm(16))
                .inputs(SMD_DIODE_SUPRACAUSAL.getStackForm(16))
                .inputs(SMD_TRANSISTOR_SUPRACAUSAL.getStackForm(16))
                .inputs(SMD_RESISTOR_SUPRACAUSAL.getStackForm(16))
                .inputs(SUPRACAUSAL_PROCESSOR.getStackForm(3))
                .inputs(ARAM.getStackForm(64))
                .inputs(ARAM.getStackForm(64))
                .inputs(RECURSIVELY_FOLDED_NEGATIVE_SPACE.getStackForm())
                .inputs(CONTAINED_EXOTIC_MATTER.getStackForm())
                .input(plate, TriniumTitanium, 16)
                .input(foil, FullerenePolymerMatrix, 24)
                .input(wireGtSingle, UXVSuperconductor, 8)
                .input(wireFine, HeavyQuarkDegenerateMatter, 64)
                .fluidInputs(Taranium.getFluid(L * 3))
                .fluidInputs(TriniumTitanium.getFluid(L * 9))
                .fluidInputs(ProtoAdamantium.getFluid(L * 4))
                .fluidInputs(FullerenePolymerMatrix.getFluid(L * 16))
                .outputs(SUPRACAUSAL_ASSEMBLY.getStackForm())
                .buildAndRegister();

        // Supracausal Computer
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(491_520_000)
                .inputs(SMD_CAPACITOR_SUPRACAUSAL.getStackForm(32))
                .inputs(SMD_DIODE_SUPRACAUSAL.getStackForm(32))
                .inputs(SMD_TRANSISTOR_SUPRACAUSAL.getStackForm(32))
                .inputs(SMD_RESISTOR_SUPRACAUSAL.getStackForm(32))
                .inputs(SUPRACAUSAL_ASSEMBLY.getStackForm(4))
                .inputs(EXOTIC_CHIP.getStackForm(32))
                .inputs(COSMIC_COMPUTE_UNIT.getStackForm(16))
                .inputs(EIGENFOLDED_KERR_MANIFOLD.getStackForm())
                .input(plate, TriniumTitanium, 32)
                .input(plate, MetastableFlerovium, 16)
                .input(plate, Neutronium, 4)
                .input(plate, CosmicNeutronium, 3)
                .input(wireGtSingle, UXVSuperconductor, 64)
                .inputs(PHOTOCOATED_HASSIUM_WAFER.getStackForm(16))
                .fluidInputs(Taranium.getFluid(L * 2))
                .fluidInputs(TriniumTitanium.getFluid(L * 9))
                .fluidInputs(ProtoAdamantium.getFluid(L * 3))
                .fluidInputs(FullerenePolymerMatrix.getFluid(L * 6))
                .outputs(SUPRACAUSAL_COMPUTER.getStackForm())
                .buildAndRegister();

        // Supracausal Mainframe
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(Integer.MAX_VALUE)
                .inputs(SMD_CAPACITOR_SUPRACAUSAL.getStackForm(64))
                .inputs(SMD_DIODE_SUPRACAUSAL.getStackForm(64))
                .inputs(SMD_TRANSISTOR_SUPRACAUSAL.getStackForm(64))
                .inputs(SMD_RESISTOR_SUPRACAUSAL.getStackForm(64))
                .inputs(SUPRACAUSAL_COMPUTER.getStackForm(2))
                .inputs(ULTRASHORT_PULSE_LASER.getStackForm(8))
                .inputs(COSMIC_COMPUTE_UNIT.getStackForm(32))
                .inputs(CTC_COMPUTATIONAL_UNIT.getStackForm())
                .inputs(COSMIC_MESH.getStackForm(64))
                .input(frameGt, QCDMatter, 4)
                .input(wireGtSingle, OpVSuperconductor, 64)
                .inputs(COSMIC_FABRIC.getStackForm(64))
                .input(plate, QCDMatter, 64)
                .fluidInputs(Taranium.getFluid(L * 64))
                .fluidInputs(TriniumTitanium.getFluid(L * 64))
                .fluidInputs(ProtoAdamantium.getFluid(L * 64))
                .fluidInputs(FullerenePolymerMatrix.getFluid(L * 64))
                .outputs(SUPRACAUSAL_MAINFRAME.getStackForm())
                .buildAndRegister();
    }
}
