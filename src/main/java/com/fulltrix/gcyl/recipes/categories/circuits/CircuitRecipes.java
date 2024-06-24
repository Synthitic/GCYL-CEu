package com.fulltrix.gcyl.recipes.categories.circuits;


import com.fulltrix.gcyl.GCYLConfig;
import com.fulltrix.gcyl.api.multi.GCYLCleanroomType;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.unification.stack.UnificationEntry;
import net.minecraft.world.gen.structure.StructureMineshaftPieces;

import static com.fulltrix.gcyl.api.GCYLUtility.*;
import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static com.fulltrix.gcyl.item.GCYLCoreItems.*;
import static gregtech.api.GTValues.L;
import static gregtech.api.recipes.ModHandler.removeRecipeByName;
import static gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES;
import static gregtech.api.recipes.RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;


public class CircuitRecipes {

    //TODO: higher tier soldering alloys
    //TODO: move cosmic+ circuits to circuit assembly line
    //TODO: make smd ore prefixes to condense recipes

    public static int circuitsPerCraft = GCYLConfig.recipes.circuitsPerCraft;
    public static void init() {

        primitiveCircuits();
        electronicCircuits();
        refinedCircuits();
        microCircuits();
        nanoCircuits();
        quantumCircuits();
        crystalCircuits();

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

    private static void primitiveCircuits() {

        // Primitive Circuit (Integrated Logic Circuit in game)
        removeRecipeByName("gregtech:electronic_circuit_lv");
        ModHandler.addShapedRecipe("primitive_processor", ELECTRONIC_CIRCUIT_LV.getStackForm(),
                "RPR", "TBT", "CCC",
                'R', RESISTOR,
                'P', new UnificationEntry(plate, WroughtIron),
                'T', VACUUM_TUBE,
                'B', BASIC_CIRCUIT_BOARD,
                'C', new UnificationEntry(cableGtSingle, RedAlloy));

        // Primitive Assembly
        removeRecipeByName("gregtech:electronic_circuit_mv");
        ModHandler.addShapedRecipe("primitive_assembly", ELECTRONIC_CIRCUIT_MV.getStackForm(),
                "PCT", "CDC", "TCP",
                'C', ELECTRONIC_CIRCUIT_LV,
                'P', new UnificationEntry(plate, WroughtIron),
                'D', DIODE,
                'T', new UnificationEntry(cableGtSingle, RedAlloy));
    }

    private static void electronicCircuits() {

        // Basic Electronic Circuit
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(16)
                .inputs(RESISTOR.getStackForm(8))
                .inputs(CAPACITOR.getStackForm(8))
                .inputs(GOOD_CIRCUIT_BOARD.getStackForm())
                .input(wireFine, Copper, 4)
                .outputs(INTEGRATED_CIRCUIT_LV.getStackForm(circuitsPerCraft))
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(16)
                .inputs(SMD_RESISTOR_REFINED.getStackForm(4))
                .inputs(SMD_CAPACITOR_REFINED.getStackForm(4))
                .inputs(GOOD_CIRCUIT_BOARD.getStackForm())
                .input(wireFine, Copper, 4)
                .outputs(INTEGRATED_CIRCUIT_LV.getStackForm(circuitsPerCraft))
                .buildAndRegister();

        // Electronic Assembly
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(16)
                .inputs(INTEGRATED_CIRCUIT_LV.getStackForm(3))
                .inputs(TRANSISTOR.getStackForm(2))
                .inputs(RESISTOR.getStackForm(8))
                .input(plate, Electrum)
                .outputs(INTEGRATED_CIRCUIT_MV.getStackForm())
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(16)
                .inputs(INTEGRATED_CIRCUIT_LV.getStackForm(3))
                .inputs(SMD_TRANSISTOR_REFINED.getStackForm())
                .inputs(SMD_RESISTOR_REFINED.getStackForm(4))
                .input(plate, Electrum)
                .outputs(INTEGRATED_CIRCUIT_MV.getStackForm())
                .buildAndRegister();

        // Electronic Computer
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(16)
                .inputs(INTEGRATED_CIRCUIT_MV.getStackForm(4))
                .inputs(CAPACITOR.getStackForm(4))
                .inputs(RESISTOR.getStackForm(4))
                .inputs(INTEGRATED_LOGIC_CIRCUIT.getStackForm(2))
                .input(plate, Aluminium, 2)
                .input(wireGtSingle, LVSuperconductor, 4)
                .outputs(INTEGRATED_CIRCUIT_HV.getStackForm())
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(16)
                .inputs(INTEGRATED_CIRCUIT_MV.getStackForm(4))
                .inputs(SMD_CAPACITOR_REFINED.getStackForm(2))
                .inputs(SMD_RESISTOR_REFINED.getStackForm(2))
                .inputs(INTEGRATED_LOGIC_CIRCUIT.getStackForm(2))
                .input(plate, Aluminium, 2)
                .input(wireGtSingle, LVSuperconductor, 4)
                .outputs(INTEGRATED_CIRCUIT_HV.getStackForm())
                .buildAndRegister();
    }

    private static void refinedCircuits() {

        // Refined Processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(60)
                .inputs(RESISTOR.getStackForm(8))
                .inputs(TRANSISTOR.getStackForm(8))
                .inputs(CAPACITOR.getStackForm(8))
                .inputs(PLASTIC_CIRCUIT_BOARD.getStackForm())
                .inputs(CENTRAL_PROCESSING_UNIT.getStackForm())
                .input(wireFine, TinAlloy, 2)
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .outputs(REFINED_PROCESSOR.getStackForm(circuitsPerCraft))
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(60)
                .inputs(SMD_RESISTOR_REFINED.getStackForm(4))
                .inputs(SMD_TRANSISTOR_REFINED.getStackForm(4))
                .inputs(SMD_CAPACITOR_REFINED.getStackForm(4))
                .inputs(PLASTIC_CIRCUIT_BOARD.getStackForm())
                .inputs(CENTRAL_PROCESSING_UNIT.getStackForm())
                .input(wireFine, TinAlloy, 2)
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .outputs(REFINED_PROCESSOR.getStackForm(circuitsPerCraft))
                .buildAndRegister();

        // SoC Recipe
        for(RecipeBuilder<?> recipeBuilder : buildHigherYieldCleanroomRecipes(CIRCUIT_ASSEMBLER_RECIPES, REFINED_PROCESSOR, circuitsPerCraft, 1, 600)) {
            recipeBuilder
                    .duration(50)
                    .inputs(PLASTIC_CIRCUIT_BOARD.getStackForm())
                    .inputs(SYSTEM_ON_CHIP.getStackForm())
                    .input(wireFine, TinAlloy, 8)
                    .fluidInputs(SolderingAlloy.getFluid(L / 2))
                    .buildAndRegister();
        }

        /*
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(600)
                .inputs(PLASTIC_CIRCUIT_BOARD.getStackForm())
                .inputs(SYSTEM_ON_CHIP.getStackForm())
                .input(wireFine, TinAlloy, 8)
                .outputs(REFINED_PROCESSOR.getStackForm(circuitsPerCraft))
                .buildAndRegister();
         */

        // Refined Assembly
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(60)
                .inputs(REFINED_PROCESSOR.getStackForm(3))
                .inputs(RESISTOR.getStackForm(8))
                .inputs(TRANSISTOR.getStackForm(8))
                .inputs(CAPACITOR.getStackForm(8))
                .inputs(PLASTIC_CIRCUIT_BOARD.getStackForm())
                .input(plate, StainlessSteel)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(REFINED_ASSEMBLY.getStackForm())
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(60)
                .inputs(REFINED_PROCESSOR.getStackForm(3))
                .inputs(SMD_RESISTOR_REFINED.getStackForm(2))
                .inputs(SMD_TRANSISTOR_REFINED.getStackForm(2))
                .inputs(SMD_CAPACITOR_REFINED.getStackForm(2))
                .inputs(PLASTIC_CIRCUIT_BOARD.getStackForm())
                .input(plate, StainlessSteel)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(REFINED_ASSEMBLY.getStackForm())
                .buildAndRegister();

        // Refined Computer
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(90)
                .inputs(REFINED_ASSEMBLY.getStackForm(4))
                .inputs(RESISTOR.getStackForm(8))
                .inputs(TRANSISTOR.getStackForm(8))
                .inputs(RANDOM_ACCESS_MEMORY.getStackForm(2))
                .inputs(PLASTIC_CIRCUIT_BOARD.getStackForm())
                .input(wireGtSingle, MVSuperconductor)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .outputs(REFINED_COMPUTER.getStackForm())
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(90)
                .inputs(REFINED_ASSEMBLY.getStackForm(4))
                .inputs(SMD_RESISTOR_REFINED.getStackForm(2))
                .inputs(SMD_TRANSISTOR_REFINED.getStackForm(2))
                .inputs(RANDOM_ACCESS_MEMORY.getStackForm(2))
                .inputs(PLASTIC_CIRCUIT_BOARD.getStackForm())
                .input(wireGtSingle, MVSuperconductor)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .outputs(REFINED_COMPUTER.getStackForm())
                .buildAndRegister();

        // Refined Mainframe
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(110)
                .inputs(REFINED_COMPUTER.getStackForm(2))
                .inputs(RESISTOR.getStackForm(32))
                .inputs(TRANSISTOR.getStackForm(16))
                .inputs(DIODE.getStackForm(8))
                .inputs(RANDOM_ACCESS_MEMORY.getStackForm(4))
                .input(frameGt, StainlessSteel, 4)
                .outputs(REFINED_MAINFRAME.getStackForm())
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(110)
                .inputs(REFINED_COMPUTER.getStackForm(2))
                .inputs(SMD_RESISTOR_REFINED.getStackForm(16))
                .inputs(SMD_TRANSISTOR_REFINED.getStackForm(8))
                .inputs(SMD_DIODE_REFINED.getStackForm(4))
                .inputs(RANDOM_ACCESS_MEMORY.getStackForm(4))
                .input(frameGt, StainlessSteel, 4)
                .outputs(REFINED_MAINFRAME.getStackForm())
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .buildAndRegister();
    }

    private static void microCircuits() {

        // Micro Processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(400)
                .inputs(SMD_RESISTOR.getStackForm(4))
                .inputs(SMD_TRANSISTOR.getStackForm(4))
                .inputs(SMD_CAPACITOR.getStackForm(4))
                .inputs(ADVANCED_CIRCUIT_BOARD.getStackForm())
                .inputs(CENTRAL_PROCESSING_UNIT.getStackForm(2))
                .input(wireFine, RedAlloy, 2)
                .outputs(MICRO_PROCESSOR.getStackForm(circuitsPerCraft))
                .cleanroom(CleanroomType.CLEANROOM)
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .buildAndRegister();

        // SoC Recipe
        for(RecipeBuilder<?> recipeBuilder : buildHigherYieldCleanroomRecipes(CIRCUIT_ASSEMBLER_RECIPES, MICRO_PROCESSOR, circuitsPerCraft, 1, 2400)) {
            recipeBuilder
                    .duration(50)
                    .inputs(ADVANCED_CIRCUIT_BOARD.getStackForm())
                    .inputs(SYSTEM_ON_CHIP.getStackForm())
                    .input(wireFine, RedAlloy, 8)
                    .fluidInputs(SolderingAlloy.getFluid(L / 2))
                    .buildAndRegister();
        }

        /*
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(2400)
                .inputs(ADVANCED_CIRCUIT_BOARD.getStackForm())
                .inputs(SYSTEM_ON_CHIP.getStackForm())
                .input(wireFine, RedAlloy, 8)
                .outputs(MICRO_PROCESSOR.getStackForm(circuitsPerCraft))
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

         */

        // Micro Assembly
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(350)
                .inputs(MICRO_PROCESSOR.getStackForm(3))
                .inputs(SMD_CAPACITOR.getStackForm(2))
                .inputs(SMD_RESISTOR.getStackForm(4))
                .inputs(RANDOM_ACCESS_MEMORY.getStackForm(2))
                .inputs(ADVANCED_CIRCUIT_BOARD.getStackForm())
                .input(plate, Titanium)
                .outputs(PROCESSOR_ASSEMBLY_HV.getStackForm())
                .fluidInputs(SolderingAlloy.getFluid(L))
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

        // Micro Computer
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(425)
                .inputs(PROCESSOR_ASSEMBLY_HV.getStackForm(4))
                .inputs(SMD_RESISTOR.getStackForm(4))
                .inputs(SMD_TRANSISTOR.getStackForm(4))
                .inputs(RANDOM_ACCESS_MEMORY.getStackForm(8))
                .inputs(ADVANCED_CIRCUIT_BOARD.getStackForm())
                .input(wireGtSingle, HVSuperconductor)
                .outputs(MICRO_COMPUTER.getStackForm())
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

        // Micro Mainframe
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(500)
                .inputs(MICRO_COMPUTER.getStackForm(2))
                .inputs(SMD_RESISTOR.getStackForm(20))
                .inputs(SMD_TRANSISTOR.getStackForm(10))
                .inputs(SMD_DIODE.getStackForm(5))
                .inputs(RANDOM_ACCESS_MEMORY.getStackForm(8))
                .input(frameGt, Titanium, 4)
                .outputs(MAINFRAME_IV.getStackForm())
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();
    }

    private static void nanoCircuits() {

        // Nano Processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(2000)
                .outputs(NANO_PROCESSOR_HV.getStackForm(circuitsPerCraft))
                .inputs(SMD_RESISTOR_NANO.getStackForm(4))
                .inputs(SMD_TRANSISTOR_NANO.getStackForm(4))
                .inputs(SMD_CAPACITOR_NANO.getStackForm(4))
                .inputs(EXTREME_CIRCUIT_BOARD.getStackForm())
                .inputs(NANO_CENTRAL_PROCESSING_UNIT.getStackForm(12))
                .input(wireFine, Aluminium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

        // SoC Recipe
        for(RecipeBuilder<?> recipeBuilder : buildHigherYieldCleanroomRecipes(CIRCUIT_ASSEMBLER_RECIPES, NANO_PROCESSOR_HV, circuitsPerCraft, 1, 9600)) {
            recipeBuilder
                    .duration(50)
                    .inputs(EXTREME_CIRCUIT_BOARD.getStackForm())
                    .inputs(ADVANCED_SYSTEM_ON_CHIP.getStackForm())
                    .input(wireFine, Aluminium, 8)
                    .fluidInputs(SolderingAlloy.getFluid(L))
                    .buildAndRegister();
        }

        /*
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(9600)
                .inputs(EXTREME_CIRCUIT_BOARD.getStackForm())
                .inputs(SYSTEM_ON_CHIP.getStackForm())
                .input(wireFine, Aluminium, 8)
                .outputs(NANO_PROCESSOR_HV.getStackForm(circuitsPerCraft))
                .fluidInputs(SolderingAlloy.getFluid(L))
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

         */

        // Nano Processor Assembly
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(2000)
                .inputs(NANO_PROCESSOR_HV.getStackForm(3))
                .inputs(SMD_CAPACITOR_NANO.getStackForm(4))
                .inputs(SMD_RESISTOR_NANO.getStackForm(4))
                .inputs(NANO_CENTRAL_PROCESSING_UNIT.getStackForm(2))
                .inputs(EXTREME_CIRCUIT_BOARD.getStackForm())
                .input(plate, TungstenSteel)
                .outputs(NANO_PROCESSOR_ASSEMBLY_EV.getStackForm())
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

        // Nano Computer
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(2000)
                .outputs(NANO_COMPUTER_IV.getStackForm())
                .inputs(NANO_PROCESSOR_ASSEMBLY_EV.getStackForm(4))
                .inputs(SMD_RESISTOR_NANO.getStackForm(4))
                .inputs(SMD_TRANSISTOR_NANO.getStackForm(4))
                .inputs(RANDOM_ACCESS_MEMORY.getStackForm(8))
                .inputs(EXTREME_CIRCUIT_BOARD.getStackForm())
                .input(wireGtSingle, EVSuperconductor)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

        // Nano Mainframe
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(2000)
                .inputs(NANO_COMPUTER_IV.getStackForm(2))
                .inputs(SMD_RESISTOR_NANO.getStackForm(24))
                .inputs(SMD_TRANSISTOR_NANO.getStackForm(12))
                .inputs(SMD_DIODE_NANO.getStackForm(6))
                .inputs(RANDOM_ACCESS_MEMORY.getStackForm(12))
                .input(frameGt, TungstenSteel, 4)
                .outputs(NANO_MAINFRAME_LUV.getStackForm())
                .fluidInputs(SolderingAlloy.getFluid(L * 8))
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();
    }

    private static void quantumCircuits() {

        // Quantum Processor

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(3000)
                .inputs(QUBIT_CENTRAL_PROCESSING_UNIT.getStackForm())
                .inputs(SMD_TRANSISTOR_QUANTUM.getStackForm(4))
                .inputs(SMD_CAPACITOR_QUANTUM.getStackForm(4))
                .inputs(ELITE_CIRCUIT_BOARD.getStackForm())
                .inputs(NANO_CENTRAL_PROCESSING_UNIT.getStackForm())
                .input(wireFine, Platinum, 2)
                .outputs(QUANTUM_PROCESSOR_EV.getStackForm(circuitsPerCraft))
                .fluidInputs(SolderingAlloy.getFluid(L))
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

        // ASoC Recipe
        for(RecipeBuilder<?> recipeBuilder : buildHigherYieldCleanroomRecipes(CIRCUIT_ASSEMBLER_RECIPES, QUANTUM_PROCESSOR_EV, circuitsPerCraft, 1, 36000)) {
            recipeBuilder
                    .duration(50)
                    .inputs(ELITE_CIRCUIT_BOARD.getStackForm())
                    .inputs(ADVANCED_SYSTEM_ON_CHIP.getStackForm())
                    .input(wireFine, Platinum, 8)
                    .fluidInputs(SolderingAlloy.getFluid(L))
                    .buildAndRegister();
        }

        /*
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(36000)
                .inputs(ELITE_CIRCUIT_BOARD.getStackForm())
                .inputs(ADVANCED_SYSTEM_ON_CHIP.getStackForm())
                .input(wireFine, Platinum, 8)
                .outputs(QUANTUM_PROCESSOR_EV.getStackForm(circuitsPerCraft))
                .fluidInputs(SolderingAlloy.getFluid(L))
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

         */


        // Quantum Assembly
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(4000)
                .inputs(QUANTUM_PROCESSOR_EV.getStackForm(3))
                .inputs(SMD_CAPACITOR_QUANTUM.getStackForm(4))
                .inputs(SMD_RESISTOR_QUANTUM.getStackForm(4))
                .inputs(QUBIT_CENTRAL_PROCESSING_UNIT.getStackForm(2))
                .inputs(ELITE_CIRCUIT_BOARD.getStackForm())
                .input(plate, Osmium)
                .outputs(QUANTUM_ASSEMBLY_IV.getStackForm())
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

        // Quantum Computer
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(6000)
                .inputs(QUANTUM_ASSEMBLY_IV.getStackForm(4))
                .inputs(SMD_DIODE_QUANTUM.getStackForm(8))
                .inputs(QUANTUM_EYE.getStackForm())
                .inputs(POWER_INTEGRATED_CIRCUIT.getStackForm(8))
                .inputs(ELITE_CIRCUIT_BOARD.getStackForm())
                .input(wireGtSingle, IVSuperconductor, 2)
                .outputs(QUANTUM_COMPUTER_LUV.getStackForm())
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

        // Quantum Mainframe
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(500).EUt(8000)
                .inputs(QUANTUM_COMPUTER_LUV.getStackForm(2))
                .inputs(SMD_RESISTOR_QUANTUM.getStackForm(64))
                .inputs(SMD_TRANSISTOR_QUANTUM.getStackForm(64))
                .inputs(SMD_CAPACITOR_QUANTUM.getStackForm(64))
                .inputs(SMD_DIODE_QUANTUM.getStackForm(64))
                .inputs(POWER_INTEGRATED_CIRCUIT.getStackForm(16))
                .inputs(QUANTUM_STAR.getStackForm())
                .input(frameGt, Ruridit, 4)
                .input(wireGtSingle, IVSuperconductor, 16)
                .outputs(QUANTUM_MAINFRAME_ZPM.getStackForm())
                .fluidInputs(SolderingAlloy.getFluid(L * 8))
                .fluidInputs(Cryotheum.getFluid(2500))
                .cleanroom(CleanroomType.CLEANROOM)
                .scannerResearch(b -> b
                        .researchStack(QUANTUM_COMPUTER_LUV.getStackForm())
                        .EUt(GTValues.VA[GTValues.IV]))
                .buildAndRegister();
    }

    private static void crystalCircuits() {

        // Crystal Processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(10000)
                .inputs(CRYSTAL_CENTRAL_PROCESSING_UNIT.getStackForm())
                .inputs(SMD_TRANSISTOR_CRYSTAL.getStackForm(8))
                .inputs(SMD_CAPACITOR_CRYSTAL.getStackForm(4))
                .inputs(KAPTON_CIRCUIT_BOARD.getStackForm())
                .inputs(NANO_CENTRAL_PROCESSING_UNIT.getStackForm())
                .input(wireFine, NiobiumTitanium, 2)
                .outputs(CRYSTAL_PROCESSOR_IV.getStackForm(circuitsPerCraft))
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

        // Crystal SoC Recipe
        for(RecipeBuilder<?> recipeBuilder : buildHigherYieldCleanroomRecipes(CIRCUIT_ASSEMBLER_RECIPES, CRYSTAL_PROCESSOR_IV, circuitsPerCraft, 1, 86000)) {
            recipeBuilder
                    .duration(50)
                    .inputs(KAPTON_CIRCUIT_BOARD.getStackForm())
                    .inputs(CRYSTAL_SYSTEM_ON_CHIP.getStackForm())
                    .input(wireFine, NiobiumTitanium, 8)
                    .fluidInputs(SolderingAlloy.getFluid(L * 2))
                    .buildAndRegister();
        }

        /*
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(86000)
                .inputs(KAPTON_CIRCUIT_BOARD.getStackForm())
                .inputs(CRYSTAL_SYSTEM_ON_CHIP.getStackForm())
                .input(wireFine, NiobiumTitanium, 8)
                .outputs(CRYSTAL_PROCESSOR_IV.getStackForm(4))
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

         */

        // Crystal Processor Assembly
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(20000)
                .inputs(CRYSTAL_PROCESSOR_IV.getStackForm(3))
                .inputs(CENTRAL_PROCESSING_UNIT.getStackForm(64))
                .inputs(SMD_RESISTOR_CRYSTAL.getStackForm(4))
                .inputs(QUBIT_CENTRAL_PROCESSING_UNIT.getStackForm())
                .inputs(KAPTON_CIRCUIT_BOARD.getStackForm())
                .input(wireGtSingle, LuVSuperconductor, 4)
                .outputs(CRYSTAL_ASSEMBLY_LUV.getStackForm())
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

        // Crystal Computer
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(300).EUt(30000)
                .inputs(CRYSTAL_ASSEMBLY_LUV.getStackForm(4))
                .inputs(SMD_DIODE_CRYSTAL.getStackForm(32))
                .inputs(SMD_RESISTOR_CRYSTAL.getStackForm(32))
                .inputs(SMD_TRANSISTOR_CRYSTAL.getStackForm(32))
                .inputs(SMD_CAPACITOR_CRYSTAL.getStackForm(32))
                .inputs(QUANTUM_EYE.getStackForm())
                .inputs(HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(8))
                .inputs(KAPTON_CIRCUIT_BOARD.getStackForm())
                .input(plate, RhodiumPlatedPalladium, 2)
                .input(wireGtSingle, LuVSuperconductor, 16)
                .outputs(CRYSTAL_COMPUTER_ZPM.getStackForm())
                .fluidInputs(SolderingAlloy.getFluid(L * 8))
                .cleanroom(CleanroomType.CLEANROOM)
                .scannerResearch(b -> b
                        .researchStack(CRYSTAL_ASSEMBLY_LUV.getStackForm())
                        .EUt(GTValues.VA[GTValues.LuV]))
                .buildAndRegister();

        // Crystal Mainframe
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(1200).EUt(30000)
                .inputs(CRYSTAL_COMPUTER_ZPM.getStackForm(2))
                .inputs(SMD_RESISTOR_CRYSTAL.getStackForm(64))
                .inputs(SMD_TRANSISTOR_CRYSTAL.getStackForm(64))
                .inputs(SMD_CAPACITOR_CRYSTAL.getStackForm(64))
                .inputs(SMD_DIODE_CRYSTAL.getStackForm(64))
                .inputs(HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(16))
                .inputs(QUANTUM_STAR.getStackForm(4))
                .input(frameGt, HSSE, 4)
                .input(wireGtSingle, LuVSuperconductor, 32)
                .outputs(CRYSTAL_MAINFRAME_UV.getStackForm())
                .fluidInputs(SolderingAlloy.getFluid(L * 16))
                .fluidInputs(Cryotheum.getFluid(5000))
                .scannerResearch(b -> b
                        .researchStack(CRYSTAL_ASSEMBLY_LUV.getStackForm())
                        .EUt(GTValues.VA[GTValues.LuV]))
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();
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
                .outputs(WETWARE_PROCESSOR_LUV.getStackForm(circuitsPerCraft))
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        // SoC Recipe
        for(RecipeBuilder<?> recipeBuilder : buildHigherYieldCleanroomRecipes(CIRCUIT_ASSEMBLER_RECIPES, WETWARE_PROCESSOR_LUV, circuitsPerCraft, 2, 614400)) {
            recipeBuilder
                    .duration(200)
                    .inputs(CYBER_PROCESSING_UNIT.getStackForm())
                    .inputs(LIVING_SOC.getStackForm(1))
                    .input(wireFine, Vibranium, 16)
                    .fluidInputs(Indalloy140.getFluid(L / 2))
                    .buildAndRegister();
        }

        /*
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(480000)
                .inputs(CYBER_PROCESSING_UNIT.getStackForm())
                .inputs(ADVANCED_SYSTEM_ON_CHIP.getStackForm(4))
                .input(wireFine, NaquadahAlloy, 8)
                .outputs(WETWARE_PROCESSOR_LUV.getStackForm(4))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

         */

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
                .fluidInputs(SterileGrowthMedium.getFluid(1000))
                .outputs(WETWARE_PROCESSOR_ASSEMBLY_ZPM.getStackForm())
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .fluidInputs(Cryotheum.getFluid(2500))
                .stationResearch(b -> b
                        .researchStack(WETWARE_PROCESSOR_LUV.getStackForm())
                        .CWUt(32)
                        .EUt(GTValues.VA[GTValues.ZPM]))
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
                .inputs(QUANTUM_STAR.getStackForm(4))
                .input(plate, Rutherfordium, 2)
                .input(wireGtSingle, ZPMSuperconductor, 16)
                .input(foil, SiliconeRubber, 32)
                .fluidInputs(SterileGrowthMedium.getFluid(2000))
                .outputs(WETWARE_SUPER_COMPUTER_UV.getStackForm())
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .fluidInputs(SolderingAlloy.getFluid(L * 8))
                .fluidInputs(Cryotheum.getFluid(5000))
                .stationResearch(b -> b
                        .researchStack(WETWARE_PROCESSOR_ASSEMBLY_ZPM.getStackForm())
                        .CWUt(48)
                        .EUt(GTValues.VA[GTValues.ZPM]))
                .buildAndRegister();

        // Wetware Mainframe
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(1200).EUt(300000)
                .inputs(WETWARE_SUPER_COMPUTER_UV.getStackForm(2))
                .inputs(SMD_RESISTOR_WETWARE.getStackForm(64))
                .inputs(SMD_TRANSISTOR_WETWARE.getStackForm(64))
                .inputs(SMD_CAPACITOR_WETWARE.getStackForm(64))
                .inputs(SMD_DIODE_WETWARE.getStackForm(64))
                .inputs(HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(32))
                .inputs(ARAM.getStackForm(16))
                .inputs(GRAVI_STAR.getStackForm(8))
                .input(frameGt, Tritanium, 4)
                .input(plate, Duranium, 4)
                .input(wireGtSingle, ZPMSuperconductor, 32)
                .input(foil, Polytetrafluoroethylene, 64)
                .fluidInputs(SterileGrowthMedium.getFluid(4000))
                .fluidInputs(UUMatter.getFluid(1000))
                .fluidInputs(Cryotheum.getFluid(10000))
                .outputs(WETWARE_MAINFRAME_UHV.getStackForm())
                .fluidInputs(SolderingAlloy.getFluid(L * 16))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .stationResearch(b -> b
                        .researchStack(WETWARE_SUPER_COMPUTER_UV.getStackForm())
                        .CWUt(64)
                        .EUt(GTValues.VA[GTValues.ZPM]))
                .buildAndRegister();
    }

    private static void biowareCircuits() {

        // Bioware Processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(240000)
                .inputs(NEURO_PROCESSOR.getStackForm())
                .inputs(QUBIT_CENTRAL_PROCESSING_UNIT.getStackForm(4))
                .inputs(SMD_TRANSISTOR_BIOWARE.getStackForm(8))
                .inputs(SMD_CAPACITOR_BIOWARE.getStackForm(4))
                .inputs(ADVANCED_CRYSTAL_SOC.getStackForm())
                .input(wireFine, NaquadahAlloy, 4)
                .outputs(BIOWARE_PROCESSOR.getStackForm(circuitsPerCraft))
                .fluidInputs(Indalloy140.getFluid(L))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();


        //SOC recipe
        for(RecipeBuilder<?> recipeBuilder : buildHigherYieldCleanroomRecipes(CIRCUIT_ASSEMBLER_RECIPES, BIOWARE_PROCESSOR, circuitsPerCraft, 2, 7864320)) {
            recipeBuilder
                    .duration(200)
                    .inputs(NEURO_PROCESSOR.getStackForm())
                    .inputs(LIVING_BIO_SOC.getStackForm(1))
                    .input(wireFine, HeavyQuarkDegenerateMatter, 16)
                    .fluidInputs(Indalloy140.getFluid(L))
                    .buildAndRegister();
        }


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
                .fluidInputs(SterileBioGrowthMedium.getFluid(1000))
                .fluidInputs(Titanium.getFluid(L * 9))
                .fluidInputs(Polyethylene.getFluid(L * 18))
                .fluidInputs(NaquadahEnriched.getFluid(L * 9))
                .outputs(BIOWARE_ASSEMBLY.getStackForm())
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .stationResearch(b -> b
                        .researchStack(BIOWARE_PROCESSOR.getStackForm())
                        .CWUt(64)
                        .EUt(GTValues.VA[GTValues.UV]))
                .buildAndRegister();

        // Bioware Computer
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(480000)
                .inputs(BIOWARE_ASSEMBLY.getStackForm(4))
                .inputs(SMD_CAPACITOR_BIOWARE.getStackForm(32))
                .inputs(SMD_TRANSISTOR_BIOWARE.getStackForm(32))
                .inputs(SMD_DIODE_BIOWARE.getStackForm(32))
                .inputs(SMD_RESISTOR_BIOWARE.getStackForm(32))
                .input(wireGtSingle, UVSuperconductor, 16)
                .inputs(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(16))
                .inputs(ARAM.getStackForm(16))
                .input(plate, Seaborgium, 2)
                .input(foil, Polybenzimidazole, 16)
                .inputs(GRAVI_STAR.getStackForm(8))
                .fluidInputs(SterileBioGrowthMedium.getFluid(2000))
                .fluidInputs(Tritanium.getFluid(L * 2))
                .fluidInputs(Polybenzimidazole.getFluid(L * 9))
                .fluidInputs(NaquadahEnriched.getFluid(L * 9))
                .outputs(BIOWARE_COMPUTER.getStackForm())
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .stationResearch(b -> b
                        .researchStack(BIOWARE_ASSEMBLY.getStackForm())
                        .CWUt(96)
                        .EUt(GTValues.VA[GTValues.UV]))
                .buildAndRegister();

        // Bioware Mainframe
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(1200).EUt(1920000)
                .inputs(BIOWARE_COMPUTER.getStackForm(2))
                .inputs(SMD_CAPACITOR_BIOWARE.getStackForm(64))
                .inputs(SMD_TRANSISTOR_BIOWARE.getStackForm(64))
                .inputs(SMD_DIODE_BIOWARE.getStackForm(64))
                .inputs(SMD_RESISTOR_BIOWARE.getStackForm(64))
                .input(wireGtSingle, UVSuperconductor, 32)
                .inputs(getPowerIC(GTValues.UHV).getStackForm(32))
                .inputs(ARAM.getStackForm(32))
                .inputs(GRAVI_STAR.getStackForm(16))
                .input(foil, Tritanium, 16)
                .inputs(BIO_CELLS.getStackForm(32))
                .input(plate, Naquadria, 8)
                .input(foil, Polybenzimidazole, 64)
                .input(frameGt, Seaborgium, 4)
                .fluidInputs(SterileBioGrowthMedium.getFluid(4000))
                .fluidInputs(Tritanium.getFluid(L * 9))
                .fluidInputs(Polybenzimidazole.getFluid(L * 18))
                .fluidInputs(Naquadria.getFluid(L * 9))
                .outputs(BIOWARE_MAINFRAME.getStackForm())
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .stationResearch(b -> b
                        .researchStack(BIOWARE_COMPUTER.getStackForm())
                        .CWUt(128)
                        .EUt(GTValues.VA[GTValues.UHV]))
                .buildAndRegister();
    }

    private static void opticalCircuits() {

        // Optical Processor

        for(RecipeBuilder<?> recipeBuilder : buildHigherYieldCleanroomRecipes(CIRCUIT_ASSEMBLER_RECIPES, OPTICAL_PROCESSOR, circuitsPerCraft, 3, 960000)) {
            recipeBuilder
                .duration(200)
                .inputs(QUBIT_CENTRAL_PROCESSING_UNIT.getStackForm(4))
                .inputs(SMD_TRANSISTOR_OPTICAL.getStackForm(8))
                .inputs(SMD_CAPACITOR_OPTICAL.getStackForm(4))
                .inputs(OPTICAL_PROCESSING_CORE.getStackForm())
                .inputs(OPTICAL_SOC.getStackForm(4))
                .input(wireFine, Pikyonium, 4)
                .fluidInputs(Indalloy140.getFluid(L * 2))
                    .buildAndRegister();
        }

        //.solderMultiplier(4);

        // Optical Assembly
        getAssLineResearchBuilder(GTValues.UHV, 400, OPTICAL_PROCESSOR.getStackForm(), false, true, GCYLCleanroomType.ISO3, 960000)
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

        /*
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
                .cleanroom(GCYLCleanroomType.ISO3)
                .buildAndRegister();

         */

        // Optical Computer
        getAssLineResearchBuilder(GTValues.UHV, 600, OPTICAL_ASSEMBLY.getStackForm(), false, true, GCYLCleanroomType.ISO3, 1920000)
                .inputs(OPTICAL_ASSEMBLY.getStackForm(4))
                .inputs(SMD_CAPACITOR_OPTICAL.getStackForm(32))
                .inputs(SMD_TRANSISTOR_OPTICAL.getStackForm(32))
                .inputs(SMD_DIODE_OPTICAL.getStackForm(32))
                .inputs(SMD_RESISTOR_OPTICAL.getStackForm(32))
                .inputs(OPTICAL_SOC.getStackForm(4))
                .input(wireGtSingle, UHVSuperconductor, 16)
                .inputs(CLADDED_OPTICAL_FIBER_CORE.getStackForm(16))
                .inputs(getPowerIC(GTValues.UHV).getStackForm(16))
                .inputs(ARAM.getStackForm(32))
                .input(plate, Quantum, 2)
                .input(foil, Polybenzimidazole, 16)
                .inputs(GRAVI_STAR.getStackForm(16))
                .fluidInputs(Tritanium.getFluid(L * 9))
                .fluidInputs(Polyetheretherketone.getFluid(L * 9))
                .fluidInputs(Adamantium.getFluid(L * 2))
                .outputs(OPTICAL_COMPUTER.getStackForm())
                        .buildAndRegister();

        /*
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
                .cleanroom(GCYLCleanroomType.ISO3)
                .buildAndRegister();

         */

        // Optical Mainframe
        getAssLineResearchBuilder(GTValues.UEV, 1200, OPTICAL_COMPUTER.getStackForm(), false, true, GCYLCleanroomType.ISO3, 6000000)
                .inputs(OPTICAL_COMPUTER.getStackForm(2))
                .inputs(SMD_CAPACITOR_OPTICAL.getStackForm(64))
                .inputs(SMD_TRANSISTOR_OPTICAL.getStackForm(64))
                .inputs(SMD_DIODE_OPTICAL.getStackForm(64))
                .inputs(SMD_RESISTOR_OPTICAL.getStackForm(64))
                .input(wireGtSingle, UHVSuperconductor, 32)
                .inputs(getPowerIC(GTValues.UEV).getStackForm(32))
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

        /*
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
                .cleanroom(GCYLCleanroomType.ISO3)
                .buildAndRegister();

         */
    }

    private static void exoticCircuits() {

        //Exotic Processor
        for(RecipeBuilder<?> recipeBuilder : buildHigherYieldCleanroomRecipes(CIRCUIT_ASSEMBLER_RECIPES, EXOTIC_PROCESSOR, circuitsPerCraft, 4, (int) 4E+6)) {
            recipeBuilder
                    .duration(200)
                    .inputs(SMD_CAPACITOR_EXOTIC.getStackForm(8))
                    .inputs(SMD_TRANSISTOR_EXOTIC.getStackForm(8))
                    .inputs(EXOTIC_PROCESSING_CORE.getStackForm())
                    .input(wireFine, Cinobite, 4)
                    .inputs(QUBIT_CENTRAL_PROCESSING_UNIT.getStackForm(4))
                    .inputs(EXOTIC_CHIP.getStackForm(4))
                    .fluidInputs(Indalloy140.getFluid(L * 4))
                    .buildAndRegister();
        }

        /*
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt((int)4E+6)
                .inputs(SMD_CAPACITOR_EXOTIC.getStackForm(8))
                .inputs(SMD_TRANSISTOR_EXOTIC.getStackForm(8))
                .inputs(EXOTIC_PROCESSING_CORE.getStackForm())
                .input(wireFine, Cinobite, 4)
                .inputs(QUBIT_CENTRAL_PROCESSING_UNIT.getStackForm(4))
                .inputs(EXOTIC_CHIP.getStackForm(4))
                .solderMultiplier(4)
                .outputs(EXOTIC_PROCESSOR.getStackForm(4))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

         */

        //Exotic Assembly
        getAssLineResearchBuilder(GTValues.UEV, 400, EXOTIC_PROCESSOR.getStackForm(), false, true, GCYLCleanroomType.ISO2, (int) 4E+6)
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

        /*
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
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();
         */

        //Exotic Computer
        getAssLineResearchBuilder(GTValues.UEV, 600, EXOTIC_ASSEMBLY.getStackForm(), false, true, GCYLCleanroomType.ISO2, (int) 4E+6)
                .inputs(SMD_DIODE_EXOTIC.getStackForm(32))
                .inputs(SMD_RESISTOR_EXOTIC.getStackForm(32))
                .inputs(SMD_TRANSISTOR_EXOTIC.getStackForm(32))
                .inputs(SMD_CAPACITOR_EXOTIC.getStackForm(32))
                .inputs(EXOTIC_ASSEMBLY.getStackForm(4))
                .input(foil, Zylon, 32)
                .inputs(EXOTIC_CHIP.getStackForm(8))
                .inputs(getPowerIC(GTValues.UEV).getStackForm(32))
                .inputs(ARAM.getStackForm(64))
                .input(plate, EnrichedNaquadahAlloy, 4)
                .inputs(UNSTABLE_STAR.getStackForm(8))
                .input(wireGtSingle, UEVSuperconductor, 16)
                .fluidInputs(Polyetheretherketone.getFluid(L * 18))
                .fluidInputs(Vibranium.getFluid(L * 2))
                .fluidInputs(EnrichedNaquadahAlloy.getFluid(L * 9))
                .fluidInputs(TriniumTitanium.getFluid(L * 4))
                .outputs(EXOTIC_COMPUTER.getStackForm())
                .buildAndRegister();
        /*
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
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

         */

        //Exotic Mainframe
        getAssLineResearchBuilder(GTValues.UIV, 1200, EXOTIC_COMPUTER.getStackForm(), false, true, GCYLCleanroomType.ISO2, (int) 1E+7)
                .inputs(SMD_RESISTOR_EXOTIC.getStackForm(64))
                .inputs(SMD_DIODE_EXOTIC.getStackForm(64))
                .inputs(SMD_TRANSISTOR_EXOTIC.getStackForm(64))
                .inputs(SMD_CAPACITOR_EXOTIC.getStackForm(64))
                .inputs(EXOTIC_COMPUTER.getStackForm(2))
                .input(foil, FullerenePolymerMatrix, 16)
                .inputs(QUBIT_CENTRAL_PROCESSING_UNIT.getStackForm(4))
                .inputs(UHASOC.getStackForm(32))
                .input(frameGt, Vibranium, 4)
                .inputs(getPowerIC(GTValues.UIV).getStackForm(64))
                .inputs(ARAM.getStackForm(64))
                .inputs(ARAM.getStackForm(64))
                .input(plate, MetastableFlerovium, 4)
                .inputs(UNSTABLE_STAR.getStackForm(16))
                .input(wireGtSingle, UEVSuperconductor, 32)
                .fluidInputs(LiquidEnrichedHelium.getFluid(1000))
                .fluidInputs(FullerenePolymerMatrix.getFluid(L * 4))
                .fluidInputs(Quantum.getFluid(L * 9))
                .fluidInputs(Naquadria.getFluid(L * 9))
                .outputs(EXOTIC_MAINFRAME.getStackForm())
                .buildAndRegister();

        /*
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
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

         */
    }

    private static void cosmicCircuits() {

        // Cosmic Processor

        for(RecipeBuilder<?> recipeBuilder : buildHigherYieldCleanroomRecipes(CIRCUIT_ASSEMBLER_RECIPES, COSMIC_PROCESSOR, circuitsPerCraft, 5, 30_720_000)) {
            recipeBuilder
                    .duration(200)
                    .inputs(QUBIT_CENTRAL_PROCESSING_UNIT.getStackForm(4))
                    .inputs(SMD_TRANSISTOR_COSMIC.getStackForm(8))
                    .inputs(SMD_CAPACITOR_COSMIC.getStackForm(16))
                    .inputs(COSMIC_PROCESSING_CORE.getStackForm())
                    .inputs(UHASOC.getStackForm(4))
                    .input(wireFine, AbyssalAlloy, 4)
                    .fluidInputs(Indalloy140.getFluid(L * 8))
                    .buildAndRegister();
        }

        /*
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(30_720_000)
                .inputs(QUBIT_CENTRAL_PROCESSING_UNIT.getStackForm(4))
                .inputs(SMD_TRANSISTOR_COSMIC.getStackForm(8))
                .inputs(SMD_CAPACITOR_COSMIC.getStackForm(16))
                .inputs(COSMIC_PROCESSING_CORE.getStackForm())
                .inputs(UHASOC.getStackForm(4))
                .input(wireFine, AbyssalAlloy, 4)
                .outputs(COSMIC_PROCESSOR.getStackForm(8))
                .solderMultiplier(4)
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

         */

        // Cosmic Assembly
        getAssLineResearchBuilder(GTValues.UIV, 400, COSMIC_PROCESSOR.getStackForm(), false, true, GCYLCleanroomType.ISO1, 30_720_000)
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

        /*
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
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

         */

        // Cosmic Computer
        getAssLineResearchBuilder(GTValues.UIV, 600, COSMIC_ASSEMBLY.getStackForm(), false, true, GCYLCleanroomType.ISO1, 30_720_000)
                .inputs(COSMIC_ASSEMBLY.getStackForm(4))
                .inputs(SMD_CAPACITOR_COSMIC.getStackForm(48))
                .inputs(SMD_TRANSISTOR_COSMIC.getStackForm(48))
                .inputs(SMD_DIODE_COSMIC.getStackForm(48))
                .inputs(SMD_RESISTOR_COSMIC.getStackForm(48))
                .input(wireGtSingle, UIVSuperconductor, 16)
                .inputs(getPowerIC(GTValues.UIV).getStackForm(64))
                .inputs(ARAM.getStackForm(64))
                .inputs(ARAM.getStackForm(64))
                .inputs(OPTICAL_SOC.getStackForm(16))
                .input(plate, HastelloyK243, 4)
                .input(foil, FullerenePolymerMatrix, 12)
                .inputs(UNSTABLE_STAR.getStackForm(16))
                .fluidInputs(CosmicComputingMix.getFluid(4000))
                .fluidInputs(Tritanium.getFluid(L * 9))
                .fluidInputs(Zylon.getFluid(L * 18))
                .fluidInputs(Naquadria.getFluid(L * 9))
                .outputs(COSMIC_COMPUTER.getStackForm())
                .buildAndRegister();

        /*
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

         */

        // Cosmic Mainframe
        getAssLineResearchBuilder(GTValues.UXV, 1200, COSMIC_COMPUTER.getStackForm(), false, true, GCYLCleanroomType.ISO1, 30_720_000 * 2)
                .inputs(COSMIC_COMPUTER.getStackForm(2))
                .inputs(SMD_CAPACITOR_COSMIC.getStackForm(64))
                .inputs(SMD_TRANSISTOR_COSMIC.getStackForm(64))
                .inputs(SMD_DIODE_COSMIC.getStackForm(64))
                .inputs(SMD_RESISTOR_COSMIC.getStackForm(64))
                .inputs(COSMIC_COMPUTE_UNIT.getStackForm(16))
                .input(wireGtSingle, UIVSuperconductor, 32)
                .inputs(UHASOC.getStackForm(64))
                .inputs(getPowerICStack(GTValues.UXV))
                .inputs(PHOTOCOATED_HASSIUM_WAFER.getStackForm(16))
                .input(frameGt, HeavyQuarkDegenerateMatter, 4)
                .input(plate, HastelloyK243, 8)
                .input(foil, FullerenePolymerMatrix, 64)
                .inputs(NUCLEAR_STAR.getStackForm(4))
                .fluidInputs(Taranium.getFluid(L * 2))
                .fluidInputs(TriniumTitanium.getFluid(L * 9))
                .fluidInputs(Zylon.getFluid(L * 18))
                .fluidInputs(Vibranium.getFluid(L * 6))
                .outputs(COSMIC_MAINFRAME.getStackForm())
                .buildAndRegister();

        /*
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

         */
    }


    private static void supracausalCircuits() {

        // Supracausal Processor
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(122_880_000)
                .inputs(COSMIC_COMPUTE_UNIT.getStackForm(16))
                .inputs(MANIFOLD_OSCILLATORY_POWER_CELL.getStackForm())
                .inputs(MICROWORMHOLE_GENERATOR.getStackForm())
                .inputs(SUPRACAUSAL_PROCESSING_CORE.getStackForm())
                .input(plate, SuperheavyHAlloy)
                .input(wireGtSingle, UXVSuperconductor, 2)
                .outputs(SUPRACAUSAL_PROCESSOR.getStackForm(circuitsPerCraft))
                .fluidInputs(Indalloy140.getFluid(L * 16))
                .cleanroom(GCYLCleanroomType.ISO0)
                .buildAndRegister();

        // Supracausal Assembly
        getAssLineResearchBuilder(GTValues.OpV, 400, SUPRACAUSAL_PROCESSOR.getStackForm(), false, true, GCYLCleanroomType.ISO0, 122_880_000)
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

        /*

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

         */

        // Supracausal Computer
        getAssLineResearchBuilder(GTValues.OpV, 600, SUPRACAUSAL_ASSEMBLY.getStackForm(), false, true, GCYLCleanroomType.ISO0, 491_520_000)
                .inputs(SMD_CAPACITOR_SUPRACAUSAL.getStackForm(32))
                .inputs(SMD_DIODE_SUPRACAUSAL.getStackForm(32))
                .inputs(SMD_TRANSISTOR_SUPRACAUSAL.getStackForm(32))
                .inputs(SMD_RESISTOR_SUPRACAUSAL.getStackForm(32))
                .inputs(SUPRACAUSAL_ASSEMBLY.getStackForm(4))
                .inputs(EXOTIC_CHIP.getStackForm(32))
                .inputs(COSMIC_COMPUTE_UNIT.getStackForm(16))
                .inputs(EIGENFOLDED_KERR_MANIFOLD.getStackForm())
                .inputs(getPowerIC(GTValues.OpV).getStackForm(32))
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

        /*
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

         */

        // Supracausal Mainframe
        getAssLineResearchBuilder(GTValues.MAX, 1200, SUPRACAUSAL_COMPUTER.getStackForm(), false, true, GCYLCleanroomType.ISO0, Integer.MAX_VALUE)
                .inputs(SMD_CAPACITOR_SUPRACAUSAL.getStackForm(64))
                .inputs(SMD_DIODE_SUPRACAUSAL.getStackForm(64))
                .inputs(SMD_TRANSISTOR_SUPRACAUSAL.getStackForm(64))
                .inputs(SMD_RESISTOR_SUPRACAUSAL.getStackForm(64))
                .inputs(SUPRACAUSAL_COMPUTER.getStackForm(2))
                .inputs(ULTRASHORT_PULSE_LASER.getStackForm(8))
                .inputs(COSMIC_COMPUTE_UNIT.getStackForm(32))
                .inputs(CTC_COMPUTATIONAL_UNIT.getStackForm())
                .inputs(getPowerICStack(GTValues.MAX))
                .inputs(COSMIC_MESH.getStackForm(64))
                .input(frameGt, QCDMatter, 4)
                .input(wireGtSingle, OpVSuperconductor, 64)
                .inputs(COSMIC_FABRIC.getStackForm(64))
                .input(plate, QCDMatter, 64)
                .inputs(NUCLEAR_STAR.getStackForm(16))
                .fluidInputs(Taranium.getFluid(L * 64))
                .fluidInputs(TriniumTitanium.getFluid(L * 64))
                .fluidInputs(ProtoAdamantium.getFluid(L * 64))
                .fluidInputs(FullerenePolymerMatrix.getFluid(L * 64))
                .outputs(SUPRACAUSAL_MAINFRAME.getStackForm())
                .buildAndRegister();

        /*
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

         */
    }
}
