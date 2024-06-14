package com.fulltrix.gcyl.recipes.categories.machines;

import com.fulltrix.gcyl.item.GCYLMetaBlocks;
import com.fulltrix.gcyl.item.GCYLMultiblockCasing2;
import com.fulltrix.gcyl.machines.GCYLTileEntities;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;

import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static com.fulltrix.gcyl.item.GCYLCoreItems.*;
import static com.fulltrix.gcyl.item.fusion.GCYLFusionCasing.CasingType.ADV_FUSION_COIL_3;
import static com.fulltrix.gcyl.machines.GCYLTileEntities.*;
import static com.fulltrix.gcyl.materials.GCYLNuclearMaterials.Einsteinium253;
import static gregicality.multiblocks.api.unification.GCYMMaterials.*;
import static gregtech.api.GTValues.IV;
import static gregtech.api.GTValues.L;
import static gregtech.api.GTValues.VA;
import static gregtech.api.recipes.ModHandler.removeRecipeByName;
import static gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES;
import static gregtech.api.unification.material.MarkerMaterials.Tier.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;
import static gregtech.common.metatileentities.MetaTileEntities.*;

public class MultiblockCraftingRecipes { //TODO: finish this, add research
    public static void init() {
        otherMultiblockInit();
        multiblockOverride();
    }

    public static void multiblockOverride() {
        removeRecipeByName("gcym:large_mixer");
        ModHandler.addShapedRecipe("large_mixer", ADVANCED_MIXER.getStackForm(),
        "LCL", "RSR", "MWM",
                'L', new UnificationEntry(pipeNormalFluid, Polybenzimidazole),
                'R', new UnificationEntry(rotor, Iridium),
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.IV),
                'S', MetaTileEntities.MIXER[IV].getStackForm(),
                'M', MetaItems.ELECTRIC_MOTOR_IV.getStackForm(),
                'W', new UnificationEntry(cableGtSingle, Platinum));
        /* Backup recipe
        ModHandler.addShapedRecipe("large_mixer", ADVANCED_MIXER.getStackForm(),
                "DED", "CHC", "DED",
                'C', new UnificationEntry(plate, MaragingSteel300),
                'E', new UnificationEntry(circuit, IV),
                'H', MIXER[EV].getStackForm(),
                'D', new UnificationEntry(plate, HastelloyX));

         */


        removeRecipeByName("gcym:large_centrifuge");
        ModHandler.addShapedRecipe("large_centrifuge", ADVANCED_CENTRIFUGE.getStackForm(),
        "HPH", "RCT", "MWM",
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.IV),
                'H', new UnificationEntry(spring, MolybdenumDisilicide),
                'P', new UnificationEntry(pipeHugeFluid, StainlessSteel),
                'R', MetaTileEntities.CENTRIFUGE[IV].getStackForm(),
                'T', MetaTileEntities.THERMAL_CENTRIFUGE[IV].getStackForm(),
                'M', MetaItems.ELECTRIC_MOTOR_IV.getStackForm(),
                'W', new UnificationEntry(cableGtSingle, Platinum));
        /* Backup recipe
        ModHandler.addShapedRecipe("large_centrifuge", ADVANCED_CENTRIFUGE.getStackForm(),
                "CBC", "RHR", "DED",
                'H', CENTRIFUGE[EV].getStackForm(),
                'E', new UnificationEntry(plateDense, IncoloyMA956),
                'C', new UnificationEntry(circuit, IV),
                'B', new UnificationEntry(pipeLargeFluid, TungstenSteel),
                'D', new UnificationEntry(plate, IncoloyMA956),
                'R', new UnificationEntry(gear, Titanium));

         */
    }

    private static void otherMultiblockInit() {

            // Large Rocket Engine
            ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(1200).EUt(30720)
                    .fluidInputs(Lubricant.getFluid(L * 16))
                    .fluidInputs(SiliconeRubber.getFluid(L * 64))
                    .fluidInputs(SolderingAlloy.getFluid(L * 10))
                    .inputs(HULL[6].getStackForm(4))
                    .input(circuit, ZPM)
                    .input(circuit, ZPM)
                    .input(circuit, ZPM)
                    .input(circuit, ZPM)
                    .input(wireGtSingle, LuVSuperconductor, 64)
                    .input(gear, IncoloyMA956, 32)
                    .inputs(ELECTRIC_PISTON_LUV.getStackForm(16))
                    .inputs(EMITTER_LuV.getStackForm(2))
                    .outputs(LARGE_ROCKET_ENGINE.getStackForm())
                    .scannerResearch(ROCKET_GENERATOR[5].getStackForm())
                    .buildAndRegister();


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

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(500).EUt(2000000)
                .fluidInputs(SolderingAlloy.getFluid(L * 9))
                .input(plate, Tritanium, 8)
                .input(plate, Naquadria, 32)
                .input(screw, Naquadria, 64)
                .input(screw, Dubnium, 64)
                .input(foil, Polyetheretherketone, 64)
                .inputs(LARGE_NAQUADAH_REACTOR.getStackForm())
                .inputs(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(16))
                .inputs(ELECTRIC_PUMP_UIV.getStackForm(2))
                .inputs(FIELD_GENERATOR_UIV.getStackForm(2))
                .input(circuit, UIV, 4)
                .outputs(HYPER_REACTOR[0].getStackForm())
                .buildAndRegister();



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
                .inputs(GCYLMetaBlocks.FUSION_CASING.getItemVariant(ADV_FUSION_COIL_3, 2))
                .outputs(COSMIC_RAY_DETECTOR.getStackForm())
                .buildAndRegister();

        // Electric Implosion Compressor
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(48000).EUt(491520)
                .fluidInputs(SolderingAlloy.getFluid(L * 10))
                .fluidInputs(Osmium.getFluid(L * 10))
                .fluidInputs(Americium.getFluid(L * 10))
                .inputs(IMPLOSION_COMPRESSOR.getStackForm(64))
                .input(block, IncoloyMA956, 2)
                .input(stickLong, Osmium, 64)
                .input(ring, Osmium, 64)
                .input(wireGtSingle, UVSuperconductor, 16)
                .inputs(ELECTRIC_PISTON_UV.getStackForm(16))
                .outputs(ELECTRIC_IMPLOSION.getStackForm())
                .buildAndRegister();

        // Naquadah Reactor
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(1000).EUt(90000)
                .fluidInputs(SolderingAlloy.getFluid(L * 20))
                .fluidInputs(Einsteinium253.getFluid(L * 2))
                .input(plate, Tritanium, 8)
                .input(foil, NaquadahAlloy, 24)
                .input(gear, Duranium, 16)
                .input(plateDense, Naquadria, 4)
                .inputs(FIELD_GENERATOR_UHV.getStackForm(1))
                .inputs(ELECTRIC_PUMP_UHV.   getStackForm(1))
                .inputs(ELECTRIC_PISTON_UHV.getStackForm(2))
                .inputs(HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(32))
                .input(wireGtSingle, ZPMSuperconductor, 32)
                .input(circuit, UHV)
                .input(circuit, UHV)
                .input(circuit, UHV)
                .input(circuit, UHV)
                .outputs(LARGE_NAQUADAH_REACTOR.getStackForm())
                .buildAndRegister();

        // Advanced Chemical Reactor
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(1200).EUt(GTValues.VA[GTValues.UHV])
                .fluidInputs(SolderingAlloy.getFluid(L * 16))
                .fluidInputs(Polytetrafluoroethylene.getFluid(L * 16))
                .inputs(MetaBlocks.BOILER_CASING.getItemVariant(BlockBoilerCasing.BoilerCasingType.POLYTETRAFLUOROETHYLENE_PIPE, 4))
                .inputs(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.PTFE_INERT_CASING, 4))
                .input(pipeLargeFluid, Polytetrafluoroethylene, 8)
                .input(foil, Polybenzimidazole, 32)
                .input(plate, WatertightSteel, 32)
                .input(circuit, UHV, 4)
                .input(stickLong, Cupronickel, 32)
                .inputs(ELECTRIC_MOTOR_UHV.getStackForm(4))
                .inputs(ELECTRIC_PUMP_UHV.getStackForm(4))
                .outputs(CHEMICAL_PLANT.getStackForm())
                .stationResearch(b -> b
                        .researchStack(LARGE_CHEMICAL_REACTOR.getStackForm())
                        .CWUt(128)
                        .EUt(VA[GTValues.UHV]))
                .buildAndRegister();

        //Decay chamber
        ModHandler.addShapedRecipe("gcyl_decay_chamber", DECAY_CHAMBER.getStackForm(),
                "RCR", "FAF","WCW",
                'R', new UnificationEntry(stick, Plutonium239),
                'F', FIELD_GENERATOR_EV,
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.IV),
                'A', HULL[GTValues.EV].getStackForm(),
                'W', new UnificationEntry(cableGtDouble, Tungsten));

        //Greenhouses
        ModHandler.addShapedRecipe("gcyl_greenhouse_mv", GREEN_HOUSE[0].getStackForm(),
                "RCR", "FAF", "WCW",
                'R', ROBOT_ARM_MV,
                'F', ELECTRIC_PUMP_MV,
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.MV),
                'A', HULL[GTValues.MV].getStackForm(),
                'W', new UnificationEntry(cableGtSingle, Copper));

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(1000).EUt(GTValues.VA[GTValues.UV])
                .outputs(GREEN_HOUSE[1].getStackForm())
                .inputs(GREEN_HOUSE[0].getStackForm(4))
                .inputs(ROBOT_ARM_UV.getStackForm(4))
                .inputs(ELECTRIC_PUMP_UV.getStackForm(4))
                .input(circuit, UV, 4)
                .input(HULL[GTValues.UV])
                .input(cableGtDouble, Duranium, 4)
                .fluidInputs(Indalloy140.getFluid(L * 8))
                .stationResearch(b-> b
                        .researchStack(GREEN_HOUSE[0].getStackForm())
                        .CWUt(32)
                        .EUt(VA[GTValues.UV]))
                .buildAndRegister();

        //DEEP MINER
        ModHandler.addShapedRecipe("gcyl_deep_miner", DEEP_MINER.getStackForm(),
                "SCS", "AHB", "SGS",
                'S', SENSOR_HV,
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.IV),
                'A', ELECTRIC_PISTON_HV,
                'B', ELECTRIC_PUMP_HV,
                'G', new UnificationEntry(gear, StainlessSteel),
                'H', HULL[GTValues.HV].getStackForm());

        //MEGA CLEANROOM
        ASSEMBLY_LINE_RECIPES.recipeBuilder().EUt(GTValues.VA[GTValues.ZPM]).duration(400)
                .inputs(CLEANROOM.getStackForm(4))
                .input(HULL[GTValues.ZPM])
                .input(rotor, HSSE, 8)
                .inputs(ELECTRIC_MOTOR_ZPM.getStackForm(8))
                .input(circuit, MarkerMaterials.Tier.ZPM, 4)
                .fluidInputs(SolderingAlloy.getFluid(L * 8))
                .fluidInputs(Lubricant.getFluid(L * 16))
                .outputs(MEGA_CLEANROOM.getStackForm())
                .scannerResearch(b->b
                        .researchStack(CLEANROOM.getStackForm())
                        .EUt(GTValues.VA[GTValues.ZPM]))
                .buildAndRegister();

        //WIRELESS PSS
        ASSEMBLY_LINE_RECIPES.recipeBuilder().EUt(GTValues.VA[GTValues.UV]).duration(1200)
                .outputs(WIRELESS_PSS.getStackForm())
                .inputs(POWER_SUBSTATION.getStackForm())
                .inputs(GCYLMetaBlocks.MULTIBLOCK_CASING2.getItemVariant(GCYLMultiblockCasing2.CasingType.SEABORGIUM_SUBSTATION, 4))
                .input(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 64)
                .input(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 64)
                .input(MetaItems.ENERGY_CLUSTER, 16)
                .input(circuit, UEV)
                .input(circuit, UEV)
                .input(circuit, UEV)
                .input(circuit, UEV)
                .input(wireGtSingle, UVSuperconductor, 64)
                .input(wireGtSingle, UVSuperconductor, 64)
                .input(wireGtSingle, UVSuperconductor, 64)
                .input(wireGtSingle, UVSuperconductor, 64)
                .fluidInputs(Indalloy140.getFluid(L * 16))
                .cleanroom(CleanroomType.CLEANROOM)
                .stationResearch(b-> b
                        .researchStack(POWER_SUBSTATION.getStackForm())
                        .CWUt(128)
                        .EUt(GTValues.VA[GTValues.UV]))
                .buildAndRegister();


        /*



            // Hot Coolant Turbine
            ModHandler.addShapedRecipe("ga_large_hot_coolant_turbine", GATileEntities.HOT_COOLANT_TURBINE.getStackForm(),
                    "PSP", "SAS", "CSC",
                    'S', new UnificationEntry(gear, Stellite),
                    'P', new UnificationEntry(circuit, Advanced),
                    'A', HULL[EV].getStackForm(),
                    'C', new UnificationEntry(pipeLarge, Ultimet));

            }



             */
    }

}
