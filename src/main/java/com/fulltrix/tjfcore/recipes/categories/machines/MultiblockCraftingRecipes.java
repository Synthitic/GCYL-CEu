package com.fulltrix.tjfcore.recipes.categories.machines;

import com.fulltrix.tjfcore.item.TJFMetaBlocks;
import gregtech.api.GTValues;

import static com.fulltrix.tjfcore.TJFMaterials.*;
import static com.fulltrix.tjfcore.item.TJFCoreItems.*;
import static com.fulltrix.tjfcore.item.fusion.TJFFusionCasing.CasingType.ADV_FUSION_COIL_3;
import static com.fulltrix.tjfcore.machines.TJFTileEntities.*;
import static gregicality.multiblocks.api.unification.GCYMMaterials.*;
import static gregtech.api.GTValues.L;
import static gregtech.api.GTValues.VA;
import static gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES;
import static gregtech.api.unification.material.MarkerMaterials.Tier.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;
import static gregtech.common.metatileentities.MetaTileEntities.*;

public class MultiblockCraftingRecipes { //TODO: finish this, add research
    public static void init() {
        otherMultiblockInit();
        //multiblockOverride();
    }

    private static void otherMultiblockInit() {

        /*
            // Large Rocket Engine
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(1200).EUt(30720)
                    .fluidInputs(Lubricant.getFluid(L * 16))
                    .fluidInputs(SiliconeRubber.getFluid(L * 64))
                    .fluidInputs(SolderingAlloy.getFluid(L * 10))
                    .inputs(HULL[6].getStackForm(4))
                    .input(circuit, Master)
                    .input(circuit, Master)
                    .input(circuit, Master)
                    .input(circuit, Master)
                    .input(wireGtSingle, LuVSuperconductor, 64)
                    .input(gear, IncoloyMA956, 32)
                    .inputs(ELECTRIC_PISTON_LUV.getStackForm(16))
                    .inputs(EMITTER_LUV.getStackForm(2))
                    .outputs(LARGE_ROCKET_ENGINE.getStackForm())
                    .buildAndRegister();

         */

            /*
            // Alloy Blast Furnace
            ModHandler.addShapedRecipe("ga_alloy_blast_furnace", ALLOY_BLAST_FURNACE.getStackForm(),
                    "ZSZ", "CBC", "TST",
                    'Z', OreDictUnifier.get(plateDense, ZirconiumCarbide),
                    'S', OreDictUnifier.get(plateDense, Staballoy),
                    'C', new UnificationEntry(circuit, Extreme),
                    'B', GATileEntities.ELECTRIC_BLAST_FURNACE.getStackForm(),
                    'T', OreDictUnifier.get(gear, TungstenCarbide));

             */

        // Void Miner Mk1
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(260000)
                .fluidInputs(HastelloyN.getFluid(L * 8))
                .fluidInputs(Polybenzimidazole.getFluid(L * 32))
                .input(gear, Staballoy, 4)
                .input(screw, Seaborgium, 16)
                .input(bolt, Tritanium, 24)
                .inputs(ELECTRIC_MOTOR_UV.getStackForm(4))
                .inputs(SENSOR_UV.getStackForm(2))
                .input(wireGtSingle, UVSuperconductor, 64)
                .input(wireGtSingle, UVSuperconductor, 64)
                .input(wireGtSingle, UVSuperconductor, 64)
                .input(wireGtSingle, UVSuperconductor, 64)
                .inputs(BASIC_LARGE_MINER.getStackForm(8))
                .inputs(LARGE_MINER.getStackForm(8))
                .inputs(ADVANCED_LARGE_MINER.getStackForm(8))
                .input(circuit, UHV, 4)
                .outputs(VOID_MINER[0].getStackForm())
                .stationResearch(b -> b
                        .researchStack(ADVANCED_LARGE_MINER.getStackForm())
                        .CWUt(128)
                        .EUt(VA[GTValues.UHV]))
                .buildAndRegister();

        // Void Miner Mk2
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(1000000)
                .fluidInputs(SolderingAlloy.getFluid(L * 27))
                .fluidInputs(Polyetheretherketone.getFluid(2592))
                .inputs(SENSOR_UHV.getStackForm(2))
                .inputs(ELECTRIC_MOTOR_UHV.getStackForm(8))
                .input(wireGtSingle, UHVSuperconductor, 64)
                .input(wireGtSingle, UHVSuperconductor, 64)
                .input(wireGtSingle, UHVSuperconductor, 64)
                .input(wireGtSingle, UHVSuperconductor, 64)
                .inputs(VOID_MINER[0].getStackForm())
                .input(circuit, UEV, 4)
                .input(gear, Incoloy813, 4)
                .input(screw, Incoloy813, 64)
                .input(screw, Incoloy813, 64)
                .input(plate, EnrichedNaquadahAlloy, 8)
                .input(plate, Ruridit, 8)
                .input(stick, EnrichedNaquadahAlloy, 16)
                .stationResearch(b -> b
                        .researchStack(VOID_MINER[0].getStackForm())
                        .CWUt(256)
                        .EUt(VA[GTValues.UEV]))
                .outputs(VOID_MINER[1].getStackForm())
                .buildAndRegister();

        // Void Miner Mk3
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(4000000)
                .fluidInputs(SolderingAlloy.getFluid(L * 54))
                .fluidInputs(Zylon.getFluid(L * 18))
                .inputs(SENSOR_UEV.getStackForm(2))
                .inputs(ELECTRIC_MOTOR_UEV.getStackForm(8))
                .input(wireGtSingle, UEVSuperconductor, 64)
                .input(wireGtSingle, UEVSuperconductor, 64)
                .input(wireGtSingle, UEVSuperconductor, 64)
                .input(wireGtSingle, UEVSuperconductor, 64)
                .inputs(VOID_MINER[1].getStackForm())
                .input(circuit, UIV, 4)
                .input(gear, HastelloyX78, 4)
                .input(screw, Lafium, 64)
                .input(screw, Pikyonium, 64)
                .input(plate, HastelloyK243, 8)
                .input(stick, TitanSteel, 16)
                .outputs(VOID_MINER[2].getStackForm())
                .stationResearch(b -> b
                        .researchStack(VOID_MINER[1].getStackForm())
                        .CWUt(512)
                        .EUt(VA[GTValues.UIV]))
                .buildAndRegister();

        // Bio Reactor
        ASSEMBLY_LINE_RECIPES.recipeBuilder().EUt(30720).duration(500)
                .fluidInputs(SolderingAlloy.getFluid(L * 9))
                .inputs(SENSOR_LuV.getStackForm(2))
                .inputs(ELECTRIC_PUMP_LuV.getStackForm(2))
                .inputs(ROBOT_ARM_LuV.getStackForm(2))
                .inputs(EMITTER_LuV.getStackForm(2))
                .input(plate, HSSS, 8)
                .input(screw, NaquadahEnriched, 16)
                .input(circuit, UV, 8)
                .input(gear, HastelloyN, 8)
                .input(bolt, Enderium, 32)
                .input(screw, IncoloyMA956, 32)
                .input(plate, Nitinol60, 16)
                .outputs(BIO_REACTOR.getStackForm())
                .buildAndRegister();

        // Hyper Reactor Mk1
        /*
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(500).EUt(2000000)
                .fluidInputs(SolderingAlloy.getFluid(L * 9))
                .input(plate, Tritanium, 8)
                .input(plate, Naquadria, 32)
                .input(screw, Naquadria, 64)
                .input(screw, Dubnium, 64)
                .input(foil, Polyetheretherketone, 64)
                .inputs(LARGE_NAQUADAH_REACTOR.getStackForm())
                .inputs(UHPIC.getStackForm(16))
                .inputs(ELECTRIC_PUMP_UIV.getStackForm(2))
                .inputs(FIELD_GENERATOR_UIV.getStackForm(2))
                .input(circuit, UIV, 4)
                .outputs(HYPER_REACTOR_I.getStackForm())
                .buildAndRegister();

         */

        // Hyper Reactor Mk2
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(500).EUt(8000000)
                .fluidInputs(SolderingAlloy.getFluid(L * 36))
                .input(plate, Incoloy813, 32)
                .input(plate, EnrichedNaquadahAlloy, 32)
                .input(screw, Ruridit, 64)
                .input(stick, AbyssalAlloy, 16)
                .input(gear, TungstenTitaniumCarbide, 8)
                .input(circuit, UXV, 4)
                .input(foil, Zylon, 64)
                .inputs(FIELD_GENERATOR_UXV.getStackForm(2))
                .inputs(ELECTRIC_PUMP_UXV.getStackForm(2))
                .inputs(HYPER_REACTOR[0].getStackForm())
                .outputs(HYPER_REACTOR[1].getStackForm())
                .buildAndRegister();

        // Hyper Reactor Mk3
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(500).EUt(32000000)
                .fluidInputs(SolderingAlloy.getFluid(L * 144))
                .input(plate, HastelloyX78, 32)
                .input(plate, HastelloyK243, 32)
                .input(screw, Zeron100, 64)
                .input(stick, TitanSteel, 16)
                .input(gear, Pikyonium, 8)
                .input(circuit, UXV, 4)
                .inputs(DEGENERATE_RHENIUM_PLATE.getStackForm(4))
                .input(foil, Zylon, 64)
                .input(foil, Zylon, 64)
                .inputs(FIELD_GENERATOR_UXV.getStackForm(2))
                .inputs(ELECTRIC_PUMP_UXV.getStackForm(2))
                .inputs(HYPER_REACTOR[1].getStackForm())
                .outputs(HYPER_REACTOR[2].getStackForm())
                .buildAndRegister();

        // Stellar Forge
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(500).EUt(2000000)
                .fluidInputs(SolderingAlloy.getFluid(L * 144))
                .input(plate, Trinium, 32)
                .input(stick, HDCS, 16)
                .input(gear, TungstenTitaniumCarbide, 16)
                .input(screw, Incoloy813, 32)
                .input(bolt, EnrichedNaquadahAlloy, 64)
                .input(circuit, UIV, 2)
                .inputs(SENSOR_UHV.getStackForm(4))
                .inputs(EMITTER_UHV.getStackForm(4))
                .inputs(FIELD_GENERATOR_UHV.getStackForm(2))
                .outputs(STELLAR_FORGE.getStackForm())
                .buildAndRegister();

        // Plasma Condenser
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(250).EUt(7680)
                .fluidInputs(SolderingAlloy.getFluid(L * 10))
                .inputs(SENSOR_LuV.getStackForm())
                .inputs(ELECTRIC_MOTOR_LuV.getStackForm(2))
                .inputs(ELECTRIC_PUMP_LuV.getStackForm(2))
                .input(plate, RhodiumPlatedPalladium, 8)
                .input(gear, TungstenCarbide, 4)
                .input(screw, Inconel792, 16)
                .input(circuit, ZPM, 2)
                .outputs(PLASMA_CONDENSER.getStackForm())
                .buildAndRegister();

        // Cosmic Ray Detector
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(410).EUt(24000000)
                .fluidInputs(Cinobite.getFluid(L * 6))
                .fluidInputs(SolderingAlloy.getFluid(L * 9))
                .input(gear, Quantum, 12)
                .input(plateDense, TitanSteel, 8)
                .input(plate, Adamantium, 24)
                .input(foil, FullerenePolymerMatrix, 6)
                .inputs(SENSOR_UIV.getStackForm(4))
                .inputs(SCINTILLATOR.getStackForm(2))
                .inputs(LEPTON_TRAP_CRYSTAL.getStackForm(4))
                .inputs(TJFMetaBlocks.FUSION_CASING.getItemVariant(ADV_FUSION_COIL_3, 2))
                .outputs(COSMIC_RAY_DETECTOR.getStackForm())
                .buildAndRegister();

            /*
            // Large Laser Engraver
            ASSEMBLY_LINE_RECIPES.recipeBuilder().EUt(30720).duration(500)
                    .fluidInputs(SolderingAlloy.getFluid(L * 9))
                    .inputs(SENSOR_LUV.getStackForm(2))
                    .inputs(CONVEYOR_MODULE_LUV.getStackForm(2))
                    .inputs(ROBOT_ARM_LUV.getStackForm(2))
                    .inputs(EMITTER_LUV.getStackForm(2))
                    .input(plate, Naquadah, 16)
                    .input(gear, RhodiumPlatedPalladium, 8)
                    .input(screw, ZirconiumCarbide, 32)
                    .input(gear, HSSS, 8)
                    .input(screw, Grisium, 32)
                    .input(circuit, Master, 4)
                    .outputs(LARGE_LASER_ENGRAVER.getStackForm())
                    .buildAndRegister();



            // Naquadah Reactor
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(1000).EUt(90000)
                    .fluidInputs(SolderingAlloy.getFluid(L * 20))
                    .fluidInputs(Einsteinium253.getMaterial().getFluid(L * 2))
                    .input(plate, Tritanium, 8)
                    .input(foil, NaquadahAlloy, 24)
                    .input(gear, Duranium, 16)
                    .input(plateDense, Naquadria, 4)
                    .inputs(FIELD_GENERATOR_UHV.getStackForm(1))
                    .inputs(ELECTRIC_PUMP_UHV.   getStackForm(1))
                    .inputs(ELECTRIC_PISTON_UHV.getStackForm(2))
                    .inputs(HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(32))
                    .input(wireGtSingle, ZPMSuperconductor, 32)
                    .input(circuit, Infinite)
                    .input(circuit, Infinite)
                    .input(circuit, Infinite)
                    .input(circuit, Infinite)
                    .outputs(LARGE_NAQUADAH_REACTOR.getStackForm())
                    .buildAndRegister();

            // Fusion Reactor Mk1
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(1000).EUt(30000)
                    .fluidInputs(SolderingAlloy.getFluid(L * 20))
                    .inputs(MetaBlocks.WIRE_COIL.getItemVariant(FUSION_COIL))
                    .input(plate, Americium, 4)
                    .input(plate, NetherStar, 4)
                    .inputs(FIELD_GENERATOR_IV.getStackForm(2))
                    .inputs(HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(32))
                    .input(wireGtSingle, LuVSuperconductor, 32)
                    .input(circuit, Ultimate)
                    .input(circuit, Ultimate)
                    .input(circuit, Ultimate)
                    .input(circuit, Ultimate)
                    .outputs(FUSION_REACTOR[0].getStackForm())
                    .buildAndRegister();

            // Fusion Reactor Mk2
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(1000).EUt(60000)
                    .fluidInputs(SolderingAlloy.getFluid(L * 20))
                    .inputs(GAMetaBlocks.FUSION_CASING.getItemVariant(FUSION_COIL_2))
                    .input(plate, Rutherfordium, 4)
                    .input(plate, Curium.getMaterial(), 4)
                    .inputs(FIELD_GENERATOR_LUV.getStackForm(2))
                    .inputs(HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(48))
                    .input(wireGtDouble, ZPMSuperconductor, 32)
                    .input(circuit, Superconductor)
                    .input(circuit, Superconductor)
                    .input(circuit, Superconductor)
                    .input(circuit, Superconductor)
                    .outputs(GATileEntities.FUSION_REACTOR[1].getStackForm())
                    .buildAndRegister();

            // Fusion Reactor Mk3
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(1000).EUt(90000)
                    .fluidInputs(SolderingAlloy.getFluid(L * 20))
                    .inputs(GAMetaBlocks.FUSION_CASING.getItemVariant(FUSION_COIL_3))
                    .input(plate, Dubnium, 4)
                    .input(plate, Fermium.getMaterial(), 4)
                    .inputs(FIELD_GENERATOR_ZPM.getStackForm(2))
                    .inputs(HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64))
                    .input(wireGtQuadruple, UVSuperconductor, 32)
                    .input(circuit, Infinite)
                    .input(circuit, Infinite)
                    .input(circuit, Infinite)
                    .input(circuit, Infinite)
                    .outputs(GATileEntities.FUSION_REACTOR[2].getStackForm())
                    .buildAndRegister();



            // Fluid Drilling Rig I
            ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(120)
                    .inputs(HULL[GAValues.MV].getStackForm())
                    .input(frameGt, Steel)
                    .input(circuit, Good)
                    .inputs(ELECTRIC_MOTOR_MV.getStackForm(4))
                    .inputs(ELECTRIC_PUMP_MV.getStackForm(4))
                    .input(gear, Cobalt, 4)
                    .circuitMeta(2)
                    .outputs(FLUID_DRILLING_PLANT[0].getStackForm())
                    .buildAndRegister();

            // Fluid Drilling Rig II
            ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(480)
                    .inputs(FLUID_DRILLING_PLANT[0].getStackForm())
                    .input(frameGt, Titanium)
                    .input(circuit, Advanced)
                    .inputs(ELECTRIC_MOTOR_HV.getStackForm(4))
                    .inputs(ELECTRIC_PUMP_HV.getStackForm(4))
                    .input(gear, BlueSteel, 4)
                    .circuitMeta(2)
                    .outputs(FLUID_DRILLING_PLANT[1].getStackForm())
                    .buildAndRegister();

            // Fluid Drilling Rig III
            ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(1920)
                    .inputs(FLUID_DRILLING_PLANT[1].getStackForm())
                    .input(frameGt, TungstenSteel)
                    .input(circuit, Extreme)
                    .inputs(ELECTRIC_MOTOR_EV.getStackForm(4))
                    .inputs(ELECTRIC_PUMP_EV.getStackForm(4))
                    .input(gear, TungstenSteel, 4)
                    .circuitMeta(2)
                    .outputs(FLUID_DRILLING_PLANT[2].getStackForm())
                    .buildAndRegister();

            // Solar Fluid Sampler
            ModHandler.addShapedRecipe("ga_solar_sampler", SOLAR_FLUID_SAMPLER.getStackForm(),
                    "GGG", "PCP", "EDE",
                    'G', new ItemStack(Blocks.GLASS),
                    'P', new UnificationEntry(plate, StainlessSteel),
                    'C', HULL[HV].getStackForm(),
                    'D', new UnificationEntry(toolHeadDrill, StainlessSteel),
                    'E', new UnificationEntry(cableGtSingle, Gold));

            // Qubit Computer
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(1000).EUt(8000000)
                    .fluidInputs(SolderingAlloy.getFluid(L * 20))
                    .fluidInputs(Zylon.getFluid(L * 20))
                    .input(frameGt, Bohrium, 16)
                    .input(plate, Bohrium, 16)
                    .input(gearSmall, Bohrium, 16)
                    .input(GAConfig.GT6.addRounds ? round : screw, Bohrium, 16)
                    .inputs(GAMetaBlocks.QUANTUM_CASING.getItemVariant(COMPUTER, 4))
                    .inputs(GAMetaBlocks.QUANTUM_CASING.getItemVariant(COMPUTER, 4))
                    .inputs(GAMetaBlocks.QUANTUM_CASING.getItemVariant(COMPUTER, 4))
                    .inputs(GAMetaBlocks.QUANTUM_CASING.getItemVariant(COMPUTER, 4))
                    .inputs(ROBOT_ARM_UEV.getStackForm(2))
                    .inputs(FIELD_GENERATOR_UEV.getStackForm(2))
                    .input(wireGtHex, BlackTitanium, 16)
                    .input(circuit, UEV)
                    .input(circuit, UEV)
                    .input(circuit, UEV)
                    .input(circuit, UEV)
                    .outputs(QUBIT_COMPUTER.getStackForm()).buildAndRegister();

            // Assembly Line
            ModHandler.addShapedRecipe("ga_assline", ASSEMBLY_LINE.getStackForm(),
                    "CRC", "SAS", "CRC",
                    'A', HULL[IV].getStackForm(),
                    'R', ROBOT_ARM_IV,
                    'C', MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(ASSEMBLER_CASING),
                    'S', new UnificationEntry(circuit, Elite));

            // Nuclear Reactor
            ModHandler.addShapedRecipe("ga_boiling_water_thorium_reactor", NUCLEAR_REACTOR.getStackForm(),
                    "GCG", "IHI", "PCP",
                    'H', HULL[EV].getStackForm(),
                    'C', new UnificationEntry(plateDense, Thorium),
                    'P', new UnificationEntry(plate, HastelloyN),
                    'G', new UnificationEntry(gear, HastelloyN),
                    'I', MetaItems.ROBOT_ARM_EV);

            // Nuclear Breeder Reactor
            ModHandler.addShapedRecipe("ga_boiling_water_uranium_reactor", NUCLEAR_BREEDER.getStackForm(),
                    "GCG", "IHI", "PCP",
                    'H', HULL[IV].getStackForm(),
                    'C', new UnificationEntry(plateDense, Uranium235),
                    'P', new UnificationEntry(plate, HastelloyN),
                    'G', new UnificationEntry(gear, HastelloyN),
                    'I', ROBOT_ARM_IV);

            // Large Miner Mk1
            ModHandler.addShapedRecipe("ga_large_miner.basic", BASIC_LARGE_MINER.getStackForm(),
                    "GCG", "IHI", "SCS",
                    'H', HULL[EV].getStackForm(),
                    'C', new UnificationEntry(circuit, Extreme),
                    'G', new UnificationEntry(gear, BlackSteel),
                    'I', COMPONENT_GRINDER_DIAMOND,
                    'S', SENSOR_EV);

            // Large Miner Mk2
            ModHandler.addShapedRecipe("ga_large_miner.large", LARGE_MINER.getStackForm(),
                    "GCG", "IHI", "SCS",
                    'H', HULL[IV].getStackForm(),
                    'C', new UnificationEntry(circuit, Elite),
                    'G', new UnificationEntry(gear, HSSG),
                    'I', COMPONENT_GRINDER_TUNGSTEN,
                    'S', SENSOR_IV);

            // Large Miner Mk3
            ModHandler.addShapedRecipe("ga_large_miner.advance", ADVANCED_LARGE_MINER.getStackForm(),
                    "GCG", "IHI", "SCS",
                    'H', HULL[LuV].getStackForm(),
                    'C', new UnificationEntry(circuit, Master),
                    'G', new UnificationEntry(gear, HSSS),
                    'I', COMPONENT_GRINDER_TUNGSTEN,
                    'S', SENSOR_LUV);

            // Industrial Primitive Blast Furnace (IPBF)
            ModHandler.addShapedRecipe("ga_industrial_primitive_blast_furnace", INDUSTRIAL_PRIMITIVE_BLAST_FURNACE.getStackForm(),
                    "PFP", "FOF", "PFP",
                    'P', MetaBlocks.METAL_CASING.getItemVariant(PRIMITIVE_BRICKS),
                    'F', Blocks.FURNACE,
                    'O', MetaTileEntities.PRIMITIVE_BLAST_FURNACE.getStackForm());

            // Advanced Distillation Tower
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(1200).EUt(30720)
                    .fluidInputs(Kanthal.getFluid(L * 16))
                    .fluidInputs(Bronze.getFluid(L * 64))
                    .fluidInputs(BabbittAlloy.getFluid(L * 16))
                    .fluidInputs(SolderingAlloy.getFluid(L * 10))
                    .input(circuit, Elite)
                    .input(circuit, Elite)
                    .input(gear, Nitinol60, 16)
                    .inputs(GATileEntities.DISTILLATION_TOWER.getStackForm())
                    .outputs(ADVANCED_DISTILLATION_TOWER.getStackForm())
                    .buildAndRegister();

            // Advanced Chemical Reactor
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(1200).EUt(7680)
                    .fluidInputs(SolderingAlloy.getFluid(L * 16))
                    .fluidInputs(Polytetrafluoroethylene.getFluid(L * 16))
                    .inputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(PTFE_PIPE, 4))
                    .inputs(GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(CHEMICALLY_INERT, 4))
                    .input(pipeLarge, Polytetrafluoroethylene, 8)
                    .input(foil, Polybenzimidazole, 32)
                    .input(plate, Grisium, 32)
                    .input(circuit, Elite, 4)
                    .input(stickLong, Cupronickel, 32)
                    .inputs(ELECTRIC_MOTOR_IV.getStackForm(4))
                    .outputs(CHEMICAL_PLANT.getStackForm())
                    .buildAndRegister();

            // Battery Tower
            ModHandler.addShapedRecipe("ga_battery_tower", BATTERY_TOWER.getStackForm(),
                    "PCP", "CHC", "PCP",
                    'H', HULL[HV].getStackForm(),
                    'C', new UnificationEntry(circuit, Extreme),
                    'P', GAMetaBlocks.METAL_CASING_1.getItemVariant(MetalCasing1.CasingType.TALONITE));

            // Gas Centrifuge
            ModHandler.addShapedRecipe("ga_gas_centrifuge", GAS_CENTRIFUGE.getStackForm(),
                    "PCP", "CHC", "PCP",
                    'H', HULL[EV].getStackForm(),
                    'C', ELECTRIC_MOTOR_EV.getStackForm(),
                    'P', ELECTRIC_PUMP_EV);

            // Hot Coolant Turbine
            ModHandler.addShapedRecipe("ga_large_hot_coolant_turbine", GATileEntities.HOT_COOLANT_TURBINE.getStackForm(),
                    "PSP", "SAS", "CSC",
                    'S', new UnificationEntry(gear, Stellite),
                    'P', new UnificationEntry(circuit, Advanced),
                    'A', HULL[EV].getStackForm(),
                    'C', new UnificationEntry(pipeLarge, Ultimet));

            if (GAConfig.multis.steamMultis.useSteelMultis) {
                // Steam Grinder
                ModHandler.addShapedRecipe("ga_steam_grinder", STEAM_GRINDER.getStackForm(),
                        "CGC", "CFC", "CGC",
                        'G', new UnificationEntry(gear, Potin),
                        'F', MetaTileEntities.STEAM_MACERATOR_STEEL.getStackForm(),
                        'C', MetaBlocks.METAL_CASING.getItemVariant(STEEL_SOLID));

                // Steam Oven
                ModHandler.addShapedRecipe("ga_steam_oven", STEAM_OVEN.getStackForm(),
                        "CGC", "FMF", "CGC",
                        'F', MetaBlocks.BOILER_FIREBOX_CASING.getItemVariant(STEEL_FIREBOX),
                        'C', MetaBlocks.METAL_CASING.getItemVariant(STEEL_SOLID),
                        'M', MetaTileEntities.STEAM_FURNACE_STEEL.getStackForm(),
                        'G', new UnificationEntry(gear, Invar));
            } else {
                // Steam Grinder
                ModHandler.addShapedRecipe("ga_steam_grinder", STEAM_GRINDER.getStackForm(),
                        "CGC", "CFC", "CGC",
                        'G', new UnificationEntry(gear, Potin),
                        'F', MetaTileEntities.STEAM_MACERATOR_BRONZE.getStackForm(),
                        'C', MetaBlocks.METAL_CASING.getItemVariant(BRONZE_BRICKS));

                // Steam Oven
                ModHandler.addShapedRecipe("ga_steam_oven", STEAM_OVEN.getStackForm(),
                        "CGC", "FMF", "CGC",
                        'F', MetaBlocks.BOILER_FIREBOX_CASING.getItemVariant(BRONZE_FIREBOX),
                        'C', MetaBlocks.METAL_CASING.getItemVariant(BRONZE_BRICKS),
                        'M', MetaTileEntities.STEAM_FURNACE_BRONZE.getStackForm(),
                        'G', new UnificationEntry(gear, Invar));
            }

            // Electric Implosion Compressor
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(48000).EUt(491520)
                    .fluidInputs(SolderingAlloy.getFluid(L * 10))
                    .fluidInputs(Osmium.getFluid(L * 10))
                    .fluidInputs(Americium.getFluid(L * 10))
                    .inputs(GATileEntities.IMPLOSION_COMPRESSOR.getStackForm())
                    .input(block, IncoloyMA956, 2)
                    .input(stickLong, Osmium, 64)
                    .input(ring, Osmium, 64)
                    .input(wireGtSingle, UVSuperconductor, 16)
                    .inputs(ELECTRIC_PISTON_UV.getStackForm(16))
                    .outputs(ELECTRIC_IMPLOSION.getStackForm())
                    .buildAndRegister();

             */
    }

        /*
        private static void multiblockOverride() {

            // Distillation Tower
            removeRecipeByName("gregtech:distillation_tower");
            ModHandler.addShapedRecipe("ga_distillation_tower", GATileEntities.DISTILLATION_TOWER.getStackForm(),
                    "CBC", "FMF", "CBC",
                    'M', HULL[EV].getStackForm(),
                    'B', new UnificationEntry(pipeLarge, StainlessSteel),
                    'C', new UnificationEntry(circuit, Extreme),
                    'F', MetaItems.ELECTRIC_PUMP_EV);

            // Cracker Unit
            removeRecipeByName("gregtech:cracking_unit");
            ModHandler.addShapedRecipe("ga_cracking_unit", GATileEntities.CRACKER.getStackForm(),
                    "CEC", "PHP", "CEC",
                    'C', MetaBlocks.WIRE_COIL.getItemVariant(CUPRONICKEL),
                    'E', MetaItems.ELECTRIC_PUMP_HV,
                    'P', new UnificationEntry(circuit, Advanced),
                    'H', HULL[HV].getStackForm());

            // Primitive Blast Furnace (PBF)
            removeRecipeByName("gregtech:bronze_primitive_blast_furnace");
            ModHandler.addShapedRecipe("ga_primitive_blast_furnace", MetaTileEntities.PRIMITIVE_BLAST_FURNACE.getStackForm(),
                    "hRS", "PBR", "dRS",
                    'R', new UnificationEntry(stick, Iron),
                    'S', new UnificationEntry(screw, Iron),
                    'P', new UnificationEntry(plate, Iron),
                    'B', MetaBlocks.METAL_CASING.getItemVariant(PRIMITIVE_BRICKS));

            // Electric Blast Furnace (EBF)
            removeRecipeByName("gregtech:electric_blast_furnace");
            ModHandler.addShapedRecipe("ga_electric_blast_furnace", GATileEntities.ELECTRIC_BLAST_FURNACE.getStackForm(),
                    "FFF", "CMC", "WCW",
                    'M', MetaBlocks.METAL_CASING.getItemVariant(INVAR_HEATPROOF),
                    'F', Blocks.FURNACE,
                    'C', new UnificationEntry(circuit, Basic),
                    'W', new UnificationEntry(cableGtSingle, Tin));

            // Vacuum Freezer
            removeRecipeByName("gregtech:vacuum_freezer");
            ModHandler.addShapedRecipe("ga_vacuum_freezer", GATileEntities.VACUUM_FREEZER.getStackForm(),
                    "PPP", "CMC", "WCW",
                    'M', MetaBlocks.METAL_CASING.getItemVariant(ALUMINIUM_FROSTPROOF),
                    'P', MetaItems.ELECTRIC_PUMP_HV,
                    'C', new UnificationEntry(circuit, Advanced),
                    'W', new UnificationEntry(cableGtSingle, Gold));

            // Implosion Compressor
            removeRecipeByName("gregtech:implosion_compressor");
            ModHandler.addShapedRecipe("ga_implosion_compressor", GATileEntities.IMPLOSION_COMPRESSOR.getStackForm(),
                    "OOO", "CMC", "WCW",
                    'M', MetaBlocks.METAL_CASING.getItemVariant(STEEL_SOLID),
                    'O', new UnificationEntry(stone, Obsidian),
                    'C', new UnificationEntry(circuit, Advanced),
                    'W', new UnificationEntry(cableGtSingle, Aluminium));

            // Pyrolyse Oven
            removeRecipeByName("gregtech:pyrolyse_oven");
            ModHandler.addShapedRecipe("ga_pyrolyse_oven", GATileEntities.PYROLYSE_OVEN.getStackForm(),
                    "WEP", "EME", "WCP",
                    'M', HULL[MV].getStackForm(),
                    'W', MetaItems.ELECTRIC_PISTON_MV,
                    'P', new UnificationEntry(wireGtQuadruple, Cupronickel),
                    'E', new UnificationEntry(circuit, Good),
                    'C', MetaItems.ELECTRIC_PUMP_MV);

            // Large Diesel Engine
            removeRecipeByName("gregtech:diesel_engine");
            ModHandler.addShapedRecipe("ga_diesel_engine", GATileEntities.LARGE_COMBUSTION_ENGINE[0].getStackForm(),
                    "PCP", "EME", "GWG",
                    'M', HULL[EV].getStackForm(),
                    'P', MetaItems.ELECTRIC_PISTON_EV,
                    'E', MetaItems.ELECTRIC_MOTOR_EV,
                    'C', new UnificationEntry(circuit, Elite),
                    'W', new UnificationEntry(cableGtSingle, TungstenSteel),
                    'G', new UnificationEntry(gear, Titanium));
            //Extreme Diesel engine
            ModHandler.addShapedRecipe( "extreme_combustion_engine", EXTREME_DIESEL_ENGINE.getStackForm(),
                    "PCP", "EME", "GWG",
                    'M', MetaTileEntities.HULL[GTValues.IV].getStackForm(),
                    'P', MetaItems.ELECTRIC_PISTON_IV.getStackForm(),
                    'E', MetaItems.ELECTRIC_MOTOR_IV.getStackForm(),
                    'C', new UnificationEntry(OrePrefix.circuit, Elite),
                    'W', new UnificationEntry(OrePrefix.cableGtSingle, Materials.HSSG),
                    'G', new UnificationEntry(OrePrefix.gear, Materials.TungstenSteel));


            // Multi-Smelter
            removeRecipeByName("gregtech:multi_furnace");
            ModHandler.addShapedRecipe("ga_multi_furnace", GATileEntities.MULTI_FURNACE.getStackForm(),
                    "PPP", "ASA", "CAC",
                    'P', Blocks.FURNACE,
                    'A', new UnificationEntry(circuit, Advanced),
                    'S', MetaBlocks.METAL_CASING.getItemVariant(INVAR_HEATPROOF),
                    'C', new UnificationEntry(cableGtSingle, AnnealedCopper));

            // Large Steam Turbine

            removeRecipeByName("gregtech:large_steam_turbine");
            ModHandler.addShapedRecipe("ga_large_steam_turbine", GATileEntities.LARGE_STEAM_TURBINE.getStackForm(),
                    "PSP", "SAS", "CSC",
                    'S', new UnificationEntry(gear, Steel),
                    'P', new UnificationEntry(circuit, Advanced),
                    'A', MetaTileEntities.HULL[HV].getStackForm(),
                    'C', new UnificationEntry(pipeLarge, Steel));

            // Large Gas Turbine
            removeRecipeByName("gregtech:large_gas_turbine");
            ModHandler.addShapedRecipe("ga_large_gas_turbine", GATileEntities.LARGE_GAS_TURBINE.getStackForm(),
                    "PSP", "SAS", "CSC",
                    'S', new UnificationEntry(gear, StainlessSteel),
                    'P', new UnificationEntry(circuit, Extreme),
                    'A', MetaTileEntities.HULL[EV].getStackForm(),
                    'C', new UnificationEntry(pipeLarge, StainlessSteel));

            // Large Plasma Turbine
            removeRecipeByName("gregtech:large_plasma_turbine");
            ModHandler.addShapedRecipe("ga_large_plasma_turbine", GATileEntities.LARGE_PLASMA_TURBINE.getStackForm(),
                    "PSP", "SAS", "CSC",
                    'S', new UnificationEntry(gear, TungstenSteel),
                    'P', new UnificationEntry(circuit, Master),
                    'A', MetaTileEntities.HULL[LuV].getStackForm(),
                    'C', new UnificationEntry(pipeLarge, TungstenSteel));

            // Large Bronze Boiler
            removeRecipeByName("gregtech:large_bronze_boiler");
            ModHandler.addShapedRecipe("ga_large_bronze_boiler", MetaTileEntities.LARGE_BRONZE_BOILER.getStackForm(),
                    "PSP", "SAS", "PSP",
                    'P', new UnificationEntry(cableGtSingle, Tin),
                    'S', new UnificationEntry(circuit, Basic),
                    'A', MetaBlocks.METAL_CASING.getItemVariant(BRONZE_BRICKS));

            // Large Steel Boiler
            removeRecipeByName("gregtech:large_steel_boiler");
            ModHandler.addShapedRecipe("ga_large_steel_boiler", MetaTileEntities.LARGE_STEEL_BOILER.getStackForm(),
                    "PSP", "SAS", "PSP",
                    'P', new UnificationEntry(cableGtSingle, Copper),
                    'S', new UnificationEntry(circuit, Advanced),
                    'A', MetaBlocks.METAL_CASING.getItemVariant(STEEL_SOLID));

            // Large Titanium Boiler
            removeRecipeByName("gregtech:large_titanium_boiler");
            ModHandler.addShapedRecipe("ga_large_titanium_boiler", MetaTileEntities.LARGE_TITANIUM_BOILER.getStackForm(),
                    "PSP", "SAS", "PSP",
                    'P', new UnificationEntry(cableGtSingle, Gold),
                    'S', new UnificationEntry(circuit, Elite),
                    'A', MetaBlocks.METAL_CASING.getItemVariant(TITANIUM_STABLE));

            // Large Tungstensteel Boiler
            removeRecipeByName("gregtech:large_tungstensteel_boiler");
            ModHandler.addShapedRecipe("ga_large_tungstensteel_boiler", MetaTileEntities.LARGE_TUNGSTENSTEEL_BOILER.getStackForm(),
                    "PSP", "SAS", "PSP",
                    'P', new UnificationEntry(cableGtSingle, Aluminium),
                    'S', new UnificationEntry(circuit, Master),
                    'A', MetaBlocks.METAL_CASING.getItemVariant(TUNGSTENSTEEL_ROBUST));

            // Recipes added to convert from GTCE to Gregicality versions of overridden multiblocks ========================
            ModHandler.addShapelessRecipe("ga_cracking_unit_compatibility", GATileEntities.CRACKER.getStackForm(), MetaTileEntities.CRACKER.getStackForm());
            ModHandler.addShapelessRecipe("ga_electric_blast_furnace_compatibility", GATileEntities.ELECTRIC_BLAST_FURNACE.getStackForm(), MetaTileEntities.ELECTRIC_BLAST_FURNACE.getStackForm());
            ModHandler.addShapelessRecipe("ga_vacuum_freezer_compatibility", GATileEntities.VACUUM_FREEZER.getStackForm(), MetaTileEntities.VACUUM_FREEZER.getStackForm());
            ModHandler.addShapelessRecipe("ga_implosion_compressor_compatibility", GATileEntities.IMPLOSION_COMPRESSOR.getStackForm(), MetaTileEntities.IMPLOSION_COMPRESSOR.getStackForm());
            ModHandler.addShapelessRecipe("ga_diesel_engine_compatibility", GATileEntities.LARGE_COMBUSTION_ENGINE[0].getStackForm(), MetaTileEntities.DIESEL_ENGINE.getStackForm());
            ModHandler.addShapelessRecipe("ga_multi_furnace_compatibility", GATileEntities.MULTI_FURNACE.getStackForm(), MetaTileEntities.MULTI_FURNACE.getStackForm());
            ModHandler.addShapelessRecipe("ga_large_steam_turbine_compatibility", GATileEntities.LARGE_STEAM_TURBINE.getStackForm(), MetaTileEntities.LARGE_STEAM_TURBINE.getStackForm());
            ModHandler.addShapelessRecipe("ga_large_gas_turbine_compatibility", GATileEntities.LARGE_GAS_TURBINE.getStackForm(), MetaTileEntities.LARGE_GAS_TURBINE.getStackForm());
            ModHandler.addShapelessRecipe("ga_large_plasma_turbine_compatibility", GATileEntities.LARGE_PLASMA_TURBINE.getStackForm(), MetaTileEntities.LARGE_PLASMA_TURBINE.getStackForm());
            ModHandler.addShapelessRecipe("ga_pyrolyse_oven_compatibility", GATileEntities.PYROLYSE_OVEN.getStackForm(), MetaTileEntities.PYROLYSE_OVEN.getStackForm());
            ModHandler.addShapelessRecipe("ga_distillation_tower_compatibility", GATileEntities.DISTILLATION_TOWER.getStackForm(), MetaTileEntities.DISTILLATION_TOWER.getStackForm());
        }


    }

         */

}
