package com.fulltrix.gcyl.recipes.categories.machines;

import com.fulltrix.gcyl.api.multi.GCYLCleanroomType;
import com.fulltrix.gcyl.item.GCYLMetaBlocks;
import com.fulltrix.gcyl.item.metal.GCYLCleanroomCasing;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.common.blocks.BlockCleanroomCasing;
import gregtech.common.blocks.MetaBlocks;

import static com.fulltrix.gcyl.api.GCYLUtility.*;
import static com.fulltrix.gcyl.item.GCYLCoreItems.UVA_HALIDE_LAMP;
import static com.fulltrix.gcyl.machines.GCYLTileEntities.*;
import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static gregicality.multiblocks.common.metatileentities.GCYMMetaTileEntities.PARALLEL_HATCH;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES;
import static gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;
import static gregtech.common.metatileentities.MetaTileEntities.*;

public class MultiblockPartCraftingRecipes {
    public static void init() {
        wirelessEnergyHatches();
        cleanroomFilterCasings();
        cleanroomMaintenanceHatch();
        uhvPlusEnergyHatches();
        parallelHatches();
        computerHatches();
    }

    private static void computerHatches() {
        for (int i = 0; i < 7; i++) {
            ASSEMBLER_RECIPES.recipeBuilder().EUt(GTValues.VA[i+8]).duration(200)
                    .outputs(HPCA_COMPUTATION_PLUS[i].getStackForm())
                    .inputs(i == 0 ? HPCA_ADVANCED_COMPUTATION_COMPONENT.getStackForm(2) : HPCA_COMPUTATION_PLUS[i - 1].getStackForm(2))
                    .input(circuit, getMarkerMaterialByTier(i+9), i == 6 ? 8 : 4)
                    .inputs(getFieldGeneratorByTier(i+8).getStackForm(i < 3 ? 2 : 4))
                    .fluidInputs(PCBCoolant.getFluid((int) (2000 * Math.pow(2, i))))
                    .cleanroom(getCleanroomTypeByTierNotV(i + 1))
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().EUt(GTValues.VA[i+6]).duration(200)
                    .outputs(HPCA_COOLING_PLUS[i].getStackForm())
                    .inputs(i == 0 ? HPCA_ACTIVE_COOLER_COMPONENT.getStackForm(2) : HPCA_COOLING_PLUS[i - 1].getStackForm(2))
                    .input(circuit, getMarkerMaterialByTier(i+8), 2)
                    .input(getPumpByTier(i+8), 2)
                    .input(getEmitterByTier(i + 8))
                    .fluidInputs(PCBCoolant.getFluid((int) (2000 * Math.pow(2, i))))
                    .cleanroom(getCleanroomTypeByTierNotV(i + 1))
                    .buildAndRegister();
        }
    }

    private static void parallelHatches() {

        for (int i = 0; i < 5; i+=2) {
            getAssLineResearchBuilder(i+10, 1000, i == 0 ? PARALLEL_HATCH[3].getStackForm() : GCYL_PARALLEL_HATCH[i / 2 - 1].getStackForm(), false, false)
                    .outputs(GCYL_PARALLEL_HATCH[i / 2].getStackForm())
                    .inputs(HULL[i+10].getStackForm())
                    .inputs(i == 0 ? PARALLEL_HATCH[3].getStackForm(3) : GCYL_PARALLEL_HATCH[i / 2 - 1].getStackForm(3))
                    .input(circuit, getMarkerMaterialByTier(i+11), i == 4 ? 32 : 16)
                    .inputs(getSensorByTier(i+10).getStackForm(4))
                    .inputs(getEmitterByTier(i+10).getStackForm(4))
                    .input(cableGtDouble, getCableByTier(i+10), 2)
                    .fluidInputs(Indalloy140.getFluid((int) (L * 8 * Math.pow(2, i))))
                    .fluidInputs(getPolymerByTier(i+10).getFluid((int) (L * 16 * Math.pow(2, i))))
                    .buildAndRegister();
        }

    }

    //TODO finish for max hatches
    private static void uhvPlusEnergyHatches() {
        // energy input
        for(int i = 9; i < 14; i++) {
            getAssLineResearchBuilder(i, 1000, ENERGY_INPUT_HATCH[i-1].getStackForm(), false, false)
                    .outputs(ENERGY_INPUT_HATCH[i].getStackForm())
                    .inputs(HULL[i].getStackForm())
                    .input(cableGtSingle, getCableByTier(i), 4)
                    .inputs(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm((int) (4 * Math.pow(2, i - 9))))
                    .inputs(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm((int) (4 * Math.pow(2, i - 9))))
                    .input(circuit, getMarkerMaterialByTier(i), (int) (2 * Math.pow(2, i - 9)))
                    .input(wireGtSingle, getSuperconductorByTier(i),(int) (4 * Math.pow(2, i - 9)))
                    .inputs(getVoltageCoilByTier(i).getStackForm(2))
                    .fluidInputs(i == 9 ? SodiumPotassium.getFluid(12000) : SupercooledCryotheum.getFluid((int) (12000 * Math.pow(2, i - 9))))
                    .fluidInputs(Indalloy140.getFluid((int) (5760 * Math.pow(2, i-9))))
                    .buildAndRegister();
        }

        // energy output
        for(int i = 9; i < 14; i++) {
            getAssLineResearchBuilder(i, 1000, ENERGY_OUTPUT_HATCH[i-1].getStackForm(), false, false)
                    .outputs(ENERGY_OUTPUT_HATCH[i].getStackForm())
                    .inputs(HULL[i].getStackForm())
                    .input(spring, getCableByTier(i), 4)
                    .inputs(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm((int) (4 * Math.pow(2, i - 9))))
                    .inputs(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm((int) (4 * Math.pow(2, i - 9))))
                    .input(circuit, getMarkerMaterialByTier(i), (int) (2 * Math.pow(2, i - 9)))
                    .input(wireGtSingle, getSuperconductorByTier(i),(int) (4 * Math.pow(2, i - 9)))
                    .inputs(getVoltageCoilByTier(i).getStackForm(2))
                    .fluidInputs(i == 9 ? SodiumPotassium.getFluid(12000) : SupercooledCryotheum.getFluid((int) (12000 * Math.pow(2, i - 9))))
                    .fluidInputs(Indalloy140.getFluid((int) (5760 * Math.pow(2, i-9))))
                    .buildAndRegister();
        }
    }

    private static void cleanroomMaintenanceHatch() {
        //STERILE FILTRATION MAINTENANCE HATCH
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .outputs(STERILE_CLEANING_MAINTENANCE_HATCH.getStackForm())
                .inputs(CLEANING_MAINTENANCE_HATCH.getStackForm())
                .inputs(ROBOT_ARM_UHV.getStackForm(4))
                .input(circuit, MarkerMaterials.Tier.UHV, 6)
                .inputs(HULL[GTValues.UHV].getStackForm())
                .inputs(EMITTER_UHV.getStackForm(2))
                .inputs(UVA_HALIDE_LAMP.getStackForm(4))
                .fluidInputs(Indalloy140.getFluid(L * 8))
                .fluidInputs(Lubricant.getFluid(L * 16))
                .fluidInputs(Polyetheretherketone.getFluid(L * 4))
                .stationResearch(b -> b
                        .researchStack(CLEANING_MAINTENANCE_HATCH.getStackForm())
                        .CWUt(128)
                        .EUt(VA[GTValues.UHV]))
                .duration(300).EUt(GTValues.VA[GTValues.UHV])
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        getAssLineResearchBuilder(GTValues.UEV, 300, STERILE_CLEANING_MAINTENANCE_HATCH.getStackForm(), false, false, GCYLCleanroomType.ISO3)
                .outputs(ISO3_CLEANING_MAINTENANCE_HATCH.getStackForm())
                .inputs(STERILE_CLEANING_MAINTENANCE_HATCH.getStackForm())
                .inputs(HULL[GTValues.UEV].getStackForm())
                .inputs(ROBOT_ARM_UEV.getStackForm(4))
                .inputs(EMITTER_UEV.getStackForm(2))
                .input(circuit, MarkerMaterials.Tier.UEV, 6)
                .inputs(GCYLMetaBlocks.GCYL_CLEANROOM_CASING.getItemVariant(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO3))
                .inputs(GCYLMetaBlocks.GCYL_CLEANROOM_CASING.getItemVariant(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO3))
                .inputs(GCYLMetaBlocks.GCYL_CLEANROOM_CASING.getItemVariant(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO3))
                .inputs(GCYLMetaBlocks.GCYL_CLEANROOM_CASING.getItemVariant(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO3))
                .input(wireGtSingle, UEVSuperconductor, 64)
                .fluidInputs(Indalloy140.getFluid(L * 16))
                .fluidInputs(Lubricant.getFluid(L * 32))
                .fluidInputs(Polyetheretherketone.getFluid(L * 8))
                .buildAndRegister();

        getAssLineResearchBuilder(GTValues.UIV, 300, ISO3_CLEANING_MAINTENANCE_HATCH.getStackForm(), false, false, GCYLCleanroomType.ISO2)
                .outputs(ISO2_CLEANING_MAINTENANCE_HATCH.getStackForm())
                .inputs(ISO3_CLEANING_MAINTENANCE_HATCH.getStackForm())
                .inputs(HULL[GTValues.UIV].getStackForm())
                .inputs(ROBOT_ARM_UIV.getStackForm(4))
                .inputs(EMITTER_UIV.getStackForm(2))
                .input(circuit, MarkerMaterials.Tier.UIV, 6)
                .inputs(GCYLMetaBlocks.GCYL_CLEANROOM_CASING.getItemVariant(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO2))
                .inputs(GCYLMetaBlocks.GCYL_CLEANROOM_CASING.getItemVariant(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO2))
                .inputs(GCYLMetaBlocks.GCYL_CLEANROOM_CASING.getItemVariant(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO2))
                .inputs(GCYLMetaBlocks.GCYL_CLEANROOM_CASING.getItemVariant(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO2))
                .input(wireGtSingle, UIVSuperconductor, 64)
                .fluidInputs(Indalloy140.getFluid(L * 32))
                .fluidInputs(Lubricant.getFluid(L * 64))
                .fluidInputs(Zylon.getFluid(L * 16))
                .buildAndRegister();

        getAssLineResearchBuilder(GTValues.UXV, 300, ISO2_CLEANING_MAINTENANCE_HATCH.getStackForm(), false, false, GCYLCleanroomType.ISO1)
                .outputs(ISO1_CLEANING_MAINTENANCE_HATCH.getStackForm())
                .inputs(ISO2_CLEANING_MAINTENANCE_HATCH.getStackForm())
                .inputs(HULL[GTValues.UXV].getStackForm())
                .inputs(ROBOT_ARM_UXV.getStackForm(4))
                .inputs(EMITTER_UXV.getStackForm(2))
                .input(circuit, MarkerMaterials.Tier.UXV, 6)
                .inputs(GCYLMetaBlocks.GCYL_CLEANROOM_CASING.getItemVariant(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO1))
                .inputs(GCYLMetaBlocks.GCYL_CLEANROOM_CASING.getItemVariant(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO1))
                .inputs(GCYLMetaBlocks.GCYL_CLEANROOM_CASING.getItemVariant(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO1))
                .inputs(GCYLMetaBlocks.GCYL_CLEANROOM_CASING.getItemVariant(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO1))
                .input(wireGtSingle, UXVSuperconductor, 64)
                .fluidInputs(Indalloy140.getFluid(L * 64))
                .fluidInputs(Lubricant.getFluid(L * 128))
                .fluidInputs(Zylon.getFluid(L * 32))
                .buildAndRegister();


    }

    private static void cleanroomFilterCasings() {
        getAssLineResearchBuilder(GTValues.UHV, 100, MetaBlocks.CLEANROOM_CASING.getItemVariant(BlockCleanroomCasing.CasingType.FILTER_CASING_STERILE), false, false, CleanroomType.STERILE_CLEANROOM)
                .input(frameGt, HDCS)
                .inputs(ELECTRIC_MOTOR_UHV.getStackForm(2))
                .inputs(EMITTER_UHV.getStackForm(2))
                .inputs(UVA_HALIDE_LAMP.getStackForm(2))
                .inputs(ITEM_FILTER.getStackForm(4))
                .input(rotor, HDCS, 2)
                .input(wireFine, UHVSuperconductor, 64)
                .fluidInputs(Indalloy140.getFluid(L * 4))
                .fluidInputs(Lubricant.getFluid(L * 8))
                .outputs(GCYLMetaBlocks.GCYL_CLEANROOM_CASING.getItemVariant(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO3, 2))
                .buildAndRegister();

        getAssLineResearchBuilder(GTValues.UEV, 100, GCYLMetaBlocks.GCYL_CLEANROOM_CASING.getItemVariant(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO3), false, false, GCYLCleanroomType.ISO3)
                .input(frameGt, EnrichedNaquadahAlloy)
                .inputs(ELECTRIC_MOTOR_UEV.getStackForm(2))
                .inputs(EMITTER_UEV.getStackForm(2))
                .inputs(UVA_HALIDE_LAMP.getStackForm(4))
                .inputs(ITEM_FILTER.getStackForm(8))
                .input(rotor, EnrichedNaquadahAlloy, 2)
                .input(wireFine, UEVSuperconductor, 64)
                .fluidInputs(Indalloy140.getFluid(L * 8))
                .fluidInputs(Lubricant.getFluid(L * 16))
                .outputs(GCYLMetaBlocks.GCYL_CLEANROOM_CASING.getItemVariant(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO2, 2))
                .buildAndRegister();

        getAssLineResearchBuilder(GTValues.UIV, 100, GCYLMetaBlocks.GCYL_CLEANROOM_CASING.getItemVariant(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO2), false, false, GCYLCleanroomType.ISO2)
                .input(frameGt, HastelloyX78)
                .inputs(ELECTRIC_MOTOR_UIV.getStackForm(2))
                .inputs(EMITTER_UIV.getStackForm(2))
                .inputs(UVA_HALIDE_LAMP.getStackForm(8))
                .inputs(ITEM_FILTER.getStackForm(16))
                .input(rotor, HastelloyX78, 2)
                .input(wireFine, UIVSuperconductor, 64)
                .fluidInputs(Indalloy140.getFluid(L * 16))
                .fluidInputs(Lubricant.getFluid(L * 32))
                .outputs(GCYLMetaBlocks.GCYL_CLEANROOM_CASING.getItemVariant(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO1, 2))
                .buildAndRegister();

        getAssLineResearchBuilder(GTValues.UXV, 100, GCYLMetaBlocks.GCYL_CLEANROOM_CASING.getItemVariant(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO1), false, false, GCYLCleanroomType.ISO1)
                .input(frameGt, HastelloyK243)
                .inputs(ELECTRIC_MOTOR_UXV.getStackForm(2))
                .inputs(EMITTER_UXV.getStackForm(2))
                .inputs(UVA_HALIDE_LAMP.getStackForm(16))
                .inputs(ITEM_FILTER.getStackForm(32))
                .input(rotor, HastelloyK243, 2)
                .input(wireFine, UXVSuperconductor, 64)
                .fluidInputs(Indalloy140.getFluid(L * 16))
                .fluidInputs(Lubricant.getFluid(L * 64))
                .outputs(GCYLMetaBlocks.GCYL_CLEANROOM_CASING.getItemVariant(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO0, 1))
                .buildAndRegister();

    }

    private static void wirelessEnergyHatches() {

        for(int i = 0; i < WIRELESS_ENERGY_HATCH_INPUT.length - 1; i++) {
            getAssLineResearchBuilder(i, 400, i == 0 ? ENERGY_INPUT_HATCH[0].getStackForm() : WIRELESS_ENERGY_HATCH_INPUT[i - 1].getStackForm(), true, true)
                    .outputs(WIRELESS_ENERGY_HATCH_INPUT[i].getStackForm())
                    .inputs(ENERGY_INPUT_HATCH[i].getStackForm())
                    .inputs(getPowerIC(i))
                    .inputs(getPowerIC(i))
                    .inputs(getSensorByTier(i+1).getStackForm(6))
                    .input(circuit, getMarkerMaterialByTier(i+1), 4)
                    .input(wireGtSingle, getSuperconductorByTier(i), 64)
                    .input(wireGtSingle, getSuperconductorByTier(i), 64)
                    .fluidInputs(SuperfluidHelium.getFluid((int) (100 * Math.pow(2, i))))
                    .fluidInputs(Seaborgium.getFluid(i == 0 ? L : L * i * 2))
                    .fluidInputs(Indalloy140.getFluid(i == 0 ? L * 2: L * i * 4))
                    .buildAndRegister();

            if(i > 3 && i < 10) { //TODO change to higher value when high tier 4A hatches are implemented

                getAssLineResearchBuilder(i, 400, i == 4 ? ENERGY_INPUT_HATCH_4A[i - 4].getStackForm() : WIRELESS_ENERGY_HATCH_INPUT_4A[i - 4].getStackForm(), true, true)
                        .outputs(WIRELESS_ENERGY_HATCH_INPUT_4A[i - 4].getStackForm())
                        .inputs(ENERGY_INPUT_HATCH_4A[i - 4].getStackForm())
                        .inputs(getPowerIC(i))
                        .inputs(getPowerIC(i))
                        .inputs(getSensorByTier(i + 1).getStackForm(6))
                        .input(circuit, getMarkerMaterialByTier(i + 1), 4)
                        .input(wireGtQuadruple, getSuperconductorByTier(i), 64)
                        .input(wireGtQuadruple, getSuperconductorByTier(i), 64)
                        .fluidInputs(SuperfluidHelium.getFluid((int) (10 * Math.pow(2, i))))
                        .fluidInputs(HeavyQuarkDegenerateMatter.getFluid(L * i * 2))
                        .fluidInputs(Indalloy140.getFluid(L * i * 4))
                        .buildAndRegister();

            }

            if(i > 4 && i < 10) {
                getAssLineResearchBuilder(i, 400, i == 5 ? ENERGY_INPUT_HATCH_16A[i - 5].getStackForm() : WIRELESS_ENERGY_HATCH_INPUT_16A[i - 5].getStackForm(), true, true)
                        .outputs(WIRELESS_ENERGY_HATCH_INPUT_16A[i - 5].getStackForm())
                        .inputs(ENERGY_INPUT_HATCH_16A[i - 5].getStackForm())
                        .inputs(getPowerIC(i))
                        .inputs(getPowerIC(i))
                        .inputs(getSensorByTier(i + 1).getStackForm(6))
                        .input(circuit, getMarkerMaterialByTier(i + 1), 4)
                        .input(wireGtHex, getSuperconductorByTier(i), 64)
                        .input(wireGtHex, getSuperconductorByTier(i), 64)
                        .fluidInputs(SuperfluidHelium.getFluid((int) (10 * Math.pow(2, i))))
                        .fluidInputs(Neutronium.getFluid(L * i * 2))
                        .fluidInputs(Indalloy140.getFluid(L * i * 4))
                        .buildAndRegister();
            }

            //TODO Add 64A Wireless recipes use Cosmic Neutronium as the fluid


            //TODO Add MAX Wireless recipes
        }

    }


}
