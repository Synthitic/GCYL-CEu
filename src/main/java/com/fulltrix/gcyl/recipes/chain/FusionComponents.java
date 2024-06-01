package com.fulltrix.gcyl.recipes.chain;

import com.fulltrix.gcyl.item.GCYLMetaBlocks;
import com.fulltrix.gcyl.item.fusion.GCYLCryostatCasing;
import com.fulltrix.gcyl.item.fusion.GCYLDivertorCasing;
import com.fulltrix.gcyl.item.fusion.GCYLFusionCasing;
import com.fulltrix.gcyl.item.fusion.GCYLVacuumCasing;
import com.fulltrix.gcyl.machines.GCYLTileEntities;
import gregtech.api.GTValues;
import gregtech.api.unification.OreDictUnifier;
import gregtech.common.blocks.BlockFusionCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;

import static com.fulltrix.gcyl.GCYLMaterials.*;
import static gregicality.multiblocks.api.unification.GCYMMaterials.Zeron100;
import static gregtech.api.GTValues.VA;
import static gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES;
import static gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES;
import static gregtech.api.unification.material.MarkerMaterials.Tier.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

public class FusionComponents {
    public static void init() {


        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(1000).EUt(500000)
                .inputs(GCYLMetaBlocks.FUSION_CASING.getItemVariant(GCYLFusionCasing.CasingType.ADV_FUSION_COIL_1))
                .inputs(OreDictUnifier.get(plate, TantalumHafniumSeaborgiumCarbide, 8))
                .inputs(OreDictUnifier.get(plate, Einsteinium, 8))
                .inputs(FIELD_GENERATOR_UV.getStackForm(2))
                .inputs(HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64))
                .inputs(HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64))
                .inputs(OreDictUnifier.get(wireGtDouble, UVSuperconductor, 64))
                .input(circuit, UEV, 4)
                .input(screw, Trinium, 16)
                .fluidInputs(SolderingAlloy.getFluid(2880 * 2))
                .outputs(GCYLTileEntities.ADVANCED_FUSION_REACTOR[0].getStackForm())
                .stationResearch(b -> b
                        .researchStack(MetaTileEntities.FUSION_REACTOR[2].getStackForm())
                        .CWUt(128)
                        .EUt(VA[GTValues.UHV]))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(500000)
                .input(wireGtOctal, UVSuperconductor, 4)
                .input(plate, TantalumHafniumSeaborgiumCarbide, 2)
                .input(plate, Einsteinium, 4)
                .inputs(NEUTRON_REFLECTOR.getStackForm(4))
                .input(circuit, UHV)
                .inputs(FIELD_GENERATOR_UV.getStackForm())
                .fluidInputs(SolderingAlloy.getFluid(144))
                .outputs(GCYLMetaBlocks.FUSION_CASING.getItemVariant(GCYLFusionCasing.CasingType.ADV_FUSION_COIL_1, 4))
                .stationResearch(b -> b
                        .researchStack(MetaBlocks.FUSION_CASING.getItemVariant(BlockFusionCasing.CasingType.FUSION_COIL))
                        .CWUt(128)
                        .EUt(VA[GTValues.UHV]))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(2000000)
                .input(wireGtOctal, UHVSuperconductor, 4)
                .input(plate, Bohrium, 2)
                .input(plate, Fermium, 4)
                .inputs(NEUTRON_REFLECTOR.getStackForm(6))
                .input(circuit, UEV)
                .inputs(FIELD_GENERATOR_UHV.getStackForm())
                .fluidInputs(SolderingAlloy.getFluid(288))
                .outputs(GCYLMetaBlocks.FUSION_CASING.getItemVariant(GCYLFusionCasing.CasingType.ADV_FUSION_COIL_2, 4))
                .stationResearch(b -> b
                        .researchStack(GCYLMetaBlocks.FUSION_CASING.getItemVariant(GCYLFusionCasing.CasingType.ADV_FUSION_COIL_1))
                        .CWUt(256)
                        .EUt(VA[GTValues.UEV]))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(8000000)
                .input(wireGtOctal, UEVSuperconductor, 4)
                .input(plate, Vibranium, 2)
                .input(plate, Mendelevium, 4)
                .inputs(NEUTRON_REFLECTOR.getStackForm(8))
                .input(circuit, UIV)
                .inputs(FIELD_GENERATOR_UEV.getStackForm())
                .fluidInputs(SolderingAlloy.getFluid(576))
                .outputs(GCYLMetaBlocks.FUSION_CASING.getItemVariant(GCYLFusionCasing.CasingType.ADV_FUSION_COIL_3, 4))
                .stationResearch(b -> b
                        .researchStack(GCYLMetaBlocks.FUSION_CASING.getItemVariant(GCYLFusionCasing.CasingType.ADV_FUSION_COIL_2))
                        .CWUt(512)
                        .EUt(VA[GTValues.UIV]))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(32000000)
                .input(wireGtOctal, UIVSuperconductor, 4)
                .input(plate, HeavyQuarkDegenerateMatter, 2)
                .input(plate, MetastableFlerovium, 4)
                .inputs(NEUTRON_REFLECTOR.getStackForm(10))
                .input(circuit, UXV)
                .inputs(FIELD_GENERATOR_UIV.getStackForm())
                .fluidInputs(SolderingAlloy.getFluid(1152))
                .outputs(GCYLMetaBlocks.FUSION_CASING.getItemVariant(GCYLFusionCasing.CasingType.ADV_FUSION_COIL_4, 4))
                .stationResearch(b -> b
                        .researchStack(GCYLMetaBlocks.FUSION_CASING.getItemVariant(GCYLFusionCasing.CasingType.ADV_FUSION_COIL_3))
                        .CWUt(1024)
                        .EUt(VA[GTValues.UXV]))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(128000000)
                .input(wireGtOctal, UXVSuperconductor, 4)
                .input(plate, QCDMatter, 2)
                .input(plate, SuperheavyLAlloy, 4)
                .inputs(NEUTRON_REFLECTOR.getStackForm(14))
                .input(circuit, OpV)
                .inputs(FIELD_GENERATOR_UXV.getStackForm())
                .fluidInputs(SolderingAlloy.getFluid(2304))
                .outputs(GCYLMetaBlocks.FUSION_CASING.getItemVariant(GCYLFusionCasing.CasingType.ADV_FUSION_COIL_5, 4))
                .stationResearch(b -> b
                        .researchStack(GCYLMetaBlocks.FUSION_CASING.getItemVariant(GCYLFusionCasing.CasingType.ADV_FUSION_COIL_4))
                        .CWUt(2048)
                        .EUt(VA[GTValues.OpV]))
                .buildAndRegister();


        //========================================================================

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(500000)
                .input(frameGt, TungstenSteel, 4)
                .input(plate, TungstenSteel, 6)
                .input(plate, Tungsten, 32)
                .input(screw, LithiumTitanate, 16)
                .inputs(ELECTRIC_PUMP_UHV.getStackForm())
                .inputs(SENSOR_UHV.getStackForm())
                .inputs(GCYLMetaBlocks.FUSION_CASING.getItemVariant(GCYLFusionCasing.CasingType.ADV_FUSION_CASING))
                .fluidInputs(SolderingAlloy.getFluid(144))
                .outputs(GCYLMetaBlocks.DIVERTOR_CASING.getItemVariant(GCYLDivertorCasing.CasingType.DIVERTOR_1, 4))
                .stationResearch(b -> b
                        .researchStack(MetaBlocks.FUSION_CASING.getItemVariant(BlockFusionCasing.CasingType.FUSION_CASING))
                        .CWUt(128)
                        .EUt(VA[GTValues.UHV]))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(2000000)
                .input(frameGt, Iridium, 6)
                .input(plate, Iridium, 8)
                .input(plate, TungstenTitaniumCarbide, 16)
                .input(screw, LithiumTitanate, 32)
                .inputs(ELECTRIC_PUMP_UEV.getStackForm())
                .inputs(SENSOR_UEV.getStackForm())
                .inputs(GCYLMetaBlocks.FUSION_CASING.getItemVariant(GCYLFusionCasing.CasingType.ADV_FUSION_CASING))
                .fluidInputs(SolderingAlloy.getFluid(288))
                .outputs(GCYLMetaBlocks.DIVERTOR_CASING.getItemVariant(GCYLDivertorCasing.CasingType.DIVERTOR_2, 4))
                .stationResearch(b -> b
                        .researchStack(GCYLMetaBlocks.DIVERTOR_CASING.getItemVariant(GCYLDivertorCasing.CasingType.DIVERTOR_1))
                        .CWUt(256)
                        .EUt(VA[GTValues.UEV]))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(8000000)
                .input(frameGt, Osmiridium, 8)
                .input(plate, Osmiridium, 10)
                .input(plate, TitanSteel, 16)
                .input(screw, LithiumTitanate, 64)
                .inputs(ELECTRIC_PUMP_UIV.getStackForm())
                .inputs(SENSOR_UIV.getStackForm())
                .inputs(GCYLMetaBlocks.FUSION_CASING.getItemVariant(GCYLFusionCasing.CasingType.ADV_FUSION_CASING, 2))
                .fluidInputs(SolderingAlloy.getFluid(576))
                .outputs(GCYLMetaBlocks.DIVERTOR_CASING.getItemVariant(GCYLDivertorCasing.CasingType.DIVERTOR_3, 4))
                .stationResearch(b -> b
                        .researchStack(GCYLMetaBlocks.DIVERTOR_CASING.getItemVariant(GCYLDivertorCasing.CasingType.DIVERTOR_2))
                        .CWUt(512)
                        .EUt(VA[GTValues.UIV]))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(32000000)
                .input(frameGt, Naquadria, 12)
                .input(plate, Naquadria, 14)
                .input(plate, BlackTitanium, 16)
                .input(screw, TriniumTitanium, 64)
                .inputs(ELECTRIC_PUMP_UXV.getStackForm())
                .inputs(SENSOR_UXV.getStackForm())
                .inputs(GCYLMetaBlocks.FUSION_CASING.getItemVariant(GCYLFusionCasing.CasingType.ADV_FUSION_CASING, 4))
                .fluidInputs(SolderingAlloy.getFluid(1152))
                .outputs(GCYLMetaBlocks.DIVERTOR_CASING.getItemVariant(GCYLDivertorCasing.CasingType.DIVERTOR_4, 4))
                .stationResearch(b -> b
                        .researchStack(GCYLMetaBlocks.DIVERTOR_CASING.getItemVariant(GCYLDivertorCasing.CasingType.DIVERTOR_3))
                        .CWUt(1024)
                        .EUt(VA[GTValues.UXV]))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(128000000)
                .input(frameGt, EnrichedNaquadahAlloy, 16)
                .input(plate, EnrichedNaquadahAlloy, 16)
                .input(plate, NaquadriaticTaranium, 16)
                .input(screw, MetastableHassium, 64)
                .inputs(ELECTRIC_PUMP_OpV.getStackForm())
                .inputs(SENSOR_OpV.getStackForm())
                .inputs(GCYLMetaBlocks.FUSION_CASING.getItemVariant(GCYLFusionCasing.CasingType.ADV_FUSION_CASING, 8))
                .fluidInputs(SolderingAlloy.getFluid(2304))
                .outputs(GCYLMetaBlocks.DIVERTOR_CASING.getItemVariant(GCYLDivertorCasing.CasingType.DIVERTOR_5, 4))
                .stationResearch(b -> b
                        .researchStack(GCYLMetaBlocks.DIVERTOR_CASING.getItemVariant(GCYLDivertorCasing.CasingType.DIVERTOR_4))
                        .CWUt(2048)
                        .EUt(VA[GTValues.OpV]))
                .buildAndRegister();

        //====================================================================================

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(500000)
                .input(frameGt, StainlessSteel, 8)
                .input(plate, StainlessSteel, 8)
                .input(pipeTinyFluid, Copper, 64)
                .input(screw, LithiumTitanate, 16)
                .inputs(ELECTRIC_PUMP_UHV.getStackForm())
                .inputs(SENSOR_UHV.getStackForm())
                .inputs(GCYLMetaBlocks.FUSION_CASING.getItemVariant(GCYLFusionCasing.CasingType.ADV_FUSION_CASING))
                .fluidInputs(SolderingAlloy.getFluid(144))
                .outputs(GCYLMetaBlocks.VACUUM_CASING.getItemVariant(GCYLVacuumCasing.CasingType.VACUUM_1, 4))
                .stationResearch(b -> b
                        .researchStack(MetaBlocks.FUSION_CASING.getItemVariant(BlockFusionCasing.CasingType.FUSION_CASING))
                        .CWUt(128)
                        .EUt(VA[GTValues.UHV]))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(2000000)
                .input(frameGt, TungstenSteel, 16)
                .input(plate, TungstenSteel, 16)
                .input(pipeTinyFluid, StainlessSteel, 64)
                .input(screw, LithiumTitanate, 32)
                .inputs(ELECTRIC_PUMP_UEV.getStackForm())
                .inputs(SENSOR_UEV.getStackForm())
                .inputs(GCYLMetaBlocks.FUSION_CASING.getItemVariant(GCYLFusionCasing.CasingType.ADV_FUSION_CASING))
                .fluidInputs(SolderingAlloy.getFluid(288))
                .outputs(GCYLMetaBlocks.VACUUM_CASING.getItemVariant(GCYLVacuumCasing.CasingType.VACUUM_2, 4))
                .stationResearch(b -> b
                        .researchStack(GCYLMetaBlocks.VACUUM_CASING.getItemVariant(GCYLVacuumCasing.CasingType.VACUUM_1))
                        .CWUt(256)
                        .EUt(VA[GTValues.UEV]))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(8000000)
                .input(frameGt, Iridium, 32)
                .input(plate, Iridium, 32)
                .input(pipeTinyFluid, TungstenSteel, 64)
                .input(screw, LithiumTitanate, 64)
                .inputs(ELECTRIC_PUMP_UIV.getStackForm())
                .inputs(SENSOR_UIV.getStackForm())
                .inputs(GCYLMetaBlocks.FUSION_CASING.getItemVariant(GCYLFusionCasing.CasingType.ADV_FUSION_CASING))
                .fluidInputs(SolderingAlloy.getFluid(576))
                .outputs(GCYLMetaBlocks.VACUUM_CASING.getItemVariant(GCYLVacuumCasing.CasingType.VACUUM_3, 4))
                .stationResearch(b -> b
                        .researchStack(GCYLMetaBlocks.VACUUM_CASING.getItemVariant(GCYLVacuumCasing.CasingType.VACUUM_2))
                        .CWUt(512)
                        .EUt(VA[GTValues.UIV]))
                .buildAndRegister();

        //TODO: change pipe to fluid pipe?
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(32000000)
                .input(frameGt, Osmiridium, 48)
                .input(plate, Osmiridium, 48)
                .input(pipeSmallItem, Ultimet, 32)
                .input(screw, TriniumTitanium, 64)
                .inputs(ELECTRIC_PUMP_UXV.getStackForm())
                .inputs(SENSOR_UXV.getStackForm())
                .inputs(GCYLMetaBlocks.FUSION_CASING.getItemVariant(GCYLFusionCasing.CasingType.ADV_FUSION_CASING))
                .fluidInputs(SolderingAlloy.getFluid(1152))
                .outputs(GCYLMetaBlocks.VACUUM_CASING.getItemVariant(GCYLVacuumCasing.CasingType.VACUUM_4, 4))
                .stationResearch(b -> b
                        .researchStack(GCYLMetaBlocks.VACUUM_CASING.getItemVariant(GCYLVacuumCasing.CasingType.VACUUM_3))
                        .CWUt(1024)
                        .EUt(VA[GTValues.UXV]))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(128000000)
                .input(frameGt, Naquadria, 64)
                .input(plate, Naquadria, 64)
                .input(pipeTinyFluid, Lafium, 64)
                .input(screw, MetastableHassium, 64)
                .inputs(ELECTRIC_PUMP_OpV.getStackForm())
                .inputs(SENSOR_OpV.getStackForm())
                .inputs(GCYLMetaBlocks.FUSION_CASING.getItemVariant(GCYLFusionCasing.CasingType.ADV_FUSION_CASING))
                .fluidInputs(SolderingAlloy.getFluid(2304))
                .outputs(GCYLMetaBlocks.VACUUM_CASING.getItemVariant(GCYLVacuumCasing.CasingType.VACUUM_5, 4))
                .stationResearch(b -> b
                        .researchStack(GCYLMetaBlocks.VACUUM_CASING.getItemVariant(GCYLVacuumCasing.CasingType.VACUUM_4))
                        .CWUt(2048)
                        .EUt(VA[GTValues.OpV]))
                .buildAndRegister();

        //========================================================================

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(500000)
                .input(frameGt, StainlessSteel, 8)
                .input(plate, Titanium, 4)
                .input(pipeTinyFluid, Copper, 64)
                .input(screw, LithiumTitanate, 16)
                .inputs(ELECTRIC_PUMP_UHV.getStackForm())
                .inputs(SENSOR_UHV.getStackForm())
                .inputs(GCYLMetaBlocks.FUSION_CASING.getItemVariant(GCYLFusionCasing.CasingType.ADV_FUSION_CASING))
                .fluidInputs(SolderingAlloy.getFluid(144))
                .outputs(GCYLMetaBlocks.CRYOSTAT_CASING.getItemVariant(GCYLCryostatCasing.CasingType.CRYOSTAT_1, 4))
                .stationResearch(b -> b
                        .researchStack(MetaBlocks.FUSION_CASING.getItemVariant(BlockFusionCasing.CasingType.FUSION_CASING))
                        .CWUt(128)
                        .EUt(VA[GTValues.UHV]))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(2000000)
                .input(frameGt, TungstenSteel, 16)
                .input(plate, Nitinol60, 4)
                .input(pipeTinyFluid, StainlessSteel, 64)
                .input(screw, LithiumTitanate, 32)
                .inputs(ELECTRIC_PUMP_UEV.getStackForm())
                .inputs(SENSOR_UEV.getStackForm())
                .inputs(GCYLMetaBlocks.FUSION_CASING.getItemVariant(GCYLFusionCasing.CasingType.ADV_FUSION_CASING))
                .fluidInputs(SolderingAlloy.getFluid(288))
                .outputs(GCYLMetaBlocks.CRYOSTAT_CASING.getItemVariant(GCYLCryostatCasing.CasingType.CRYOSTAT_2, 4))
                .stationResearch(b -> b
                        .researchStack(GCYLMetaBlocks.CRYOSTAT_CASING.getItemVariant(GCYLCryostatCasing.CasingType.CRYOSTAT_1))
                        .CWUt(256)
                        .EUt(VA[GTValues.UEV]))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(8000000)
                .input(frameGt, Iridium, 32)
                .input(plate, TungstenTitaniumCarbide, 16)
                .input(pipeTinyFluid, TungstenSteel, 64)
                .input(screw, LithiumTitanate, 64)
                .inputs(ELECTRIC_PUMP_UIV.getStackForm())
                .inputs(SENSOR_UIV.getStackForm())
                .inputs(GCYLMetaBlocks.FUSION_CASING.getItemVariant(GCYLFusionCasing.CasingType.ADV_FUSION_CASING))
                .fluidInputs(SolderingAlloy.getFluid(576))
                .outputs(GCYLMetaBlocks.CRYOSTAT_CASING.getItemVariant(GCYLCryostatCasing.CasingType.CRYOSTAT_3, 4))
                .stationResearch(b -> b
                        .researchStack(GCYLMetaBlocks.CRYOSTAT_CASING.getItemVariant(GCYLCryostatCasing.CasingType.CRYOSTAT_2))
                        .CWUt(512)
                        .EUt(VA[GTValues.UIV]))
                .buildAndRegister();

        //TODO: change pipe to fluid pipe?
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(32000000)
                .input(frameGt, Osmiridium, 48)
                .input(plate, TitanSteel, 16)
                .input(pipeSmallItem, Ultimet, 32)
                .input(screw, TriniumTitanium, 64)
                .inputs(ELECTRIC_PUMP_UXV.getStackForm())
                .inputs(SENSOR_UXV.getStackForm())
                .inputs(GCYLMetaBlocks.FUSION_CASING.getItemVariant(GCYLFusionCasing.CasingType.ADV_FUSION_CASING))
                .fluidInputs(SolderingAlloy.getFluid(1152))
                .outputs(GCYLMetaBlocks.CRYOSTAT_CASING.getItemVariant(GCYLCryostatCasing.CasingType.CRYOSTAT_4, 4))
                .stationResearch(b -> b
                        .researchStack(GCYLMetaBlocks.CRYOSTAT_CASING.getItemVariant(GCYLCryostatCasing.CasingType.CRYOSTAT_3))
                        .CWUt(1024)
                        .EUt(VA[GTValues.UXV]))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(128000000)
                .input(frameGt, Naquadria, 64)
                .input(plate, BlackTitanium, 16)
                .input(pipeTinyFluid, Lafium, 64)
                .input(screw, MetastableHassium, 64)
                .inputs(ELECTRIC_PUMP_OpV.getStackForm())
                .inputs(SENSOR_OpV.getStackForm())
                .inputs(GCYLMetaBlocks.FUSION_CASING.getItemVariant(GCYLFusionCasing.CasingType.ADV_FUSION_CASING))
                .fluidInputs(SolderingAlloy.getFluid(2304))
                .outputs(GCYLMetaBlocks.CRYOSTAT_CASING.getItemVariant(GCYLCryostatCasing.CasingType.CRYOSTAT_5, 4))
                .stationResearch(b -> b
                        .researchStack(GCYLMetaBlocks.CRYOSTAT_CASING.getItemVariant(GCYLCryostatCasing.CasingType.CRYOSTAT_4))
                        .CWUt(2048)
                        .EUt(VA[GTValues.OpV]))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(500000)
                .inputs(MetaBlocks.FUSION_CASING.getItemVariant(BlockFusionCasing.CasingType.FUSION_CASING_MK3))
                .input(plate, TantalumHafniumSeaborgiumCarbide, 6)
                .outputs(GCYLMetaBlocks.FUSION_CASING.getItemVariant(GCYLFusionCasing.CasingType.ADV_FUSION_CASING))
                .duration(50)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(500000).duration(50)
                .fluidInputs(SolderingAlloy.getFluid(576))
                .input(plate, TungstenTitaniumCarbide, 4)
                .input(frameGt, TungstenTitaniumCarbide)
                .input(pipeSmallFluid, Zeron100, 4)
                .outputs(GCYLMetaBlocks.FUSION_CASING.getItemVariant(GCYLFusionCasing.CasingType.BLANKET_BASE))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(500000).duration(50)
                .fluidInputs(SolderingAlloy.getFluid(288))
                .input(plate, Beryllium, 16)
                .input(plateDense, Copper, 2)
                .input(plateDense, StainlessSteel, 2)
                .inputs(GCYLMetaBlocks.FUSION_CASING.getItemVariant(GCYLFusionCasing.CasingType.BLANKET_BASE))
                .outputs(GCYLMetaBlocks.FUSION_CASING.getItemVariant(GCYLFusionCasing.CasingType.FUSION_BLANKET))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(500000).duration(50)
                .fluidInputs(SolderingAlloy.getFluid(288))
                .input(plate, LithiumTitanate, 4)
                .input(plateDense, Copper, 2)
                .input(plateDense, StainlessSteel, 2)
                .inputs(GCYLMetaBlocks.FUSION_CASING.getItemVariant(GCYLFusionCasing.CasingType.BLANKET_BASE))
                .outputs(GCYLMetaBlocks.FUSION_CASING.getItemVariant(GCYLFusionCasing.CasingType.BREEDING_BLANKET))
                .buildAndRegister();
    }
}
