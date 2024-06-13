package com.fulltrix.gcyl.recipes.categories.machines;

import com.fulltrix.gcyl.api.multi.GCYLCleanroomType;
import com.fulltrix.gcyl.item.GCYLMetaBlocks;
import com.fulltrix.gcyl.item.metal.GCYLCleanroomCasing;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.common.blocks.BlockCleanroomCasing;
import gregtech.common.blocks.MetaBlocks;

import static com.fulltrix.gcyl.api.GCYLUtility.*;
import static com.fulltrix.gcyl.item.GCYLCoreItems.UVA_HALIDE_LAMP;
import static com.fulltrix.gcyl.machines.GCYLTileEntities.*;
import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.api.GTValues.L;
import static gregtech.common.items.MetaItems.*;
import static gregtech.common.metatileentities.MetaTileEntities.*;

public class MultiblockPartCraftingRecipes {
    public static void init() {
        wirelessEnergyHatches();
        cleanroomFilterCasings();
    }

    private static void cleanroomFilterCasings() {
        getAssLineResearchBuilder(GTValues.UHV, 100, MetaBlocks.CLEANROOM_CASING.getItemVariant(BlockCleanroomCasing.CasingType.FILTER_CASING_STERILE), false, false, CleanroomType.STERILE_CLEANROOM)
                .input(frameGt, HDCS)
                .inputs(ELECTRIC_MOTOR_UHV.getStackForm(2))
                .inputs(EMITTER_UHV.getStackForm(2))
                .inputs(UVA_HALIDE_LAMP.getStackForm(2))
                .inputs(ITEM_FILTER.getStackForm(4))
                .input(rotor, HDCS, 2)
                .input(wireGtSingle, UHVSuperconductor, 64)
                .fluidInputs(Indalloy140.getFluid(L * 4))
                .outputs(GCYLMetaBlocks.GCYL_CLEANROOM_CASING.getItemVariant(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO3, 2))
                .buildAndRegister();

        getAssLineResearchBuilder(GTValues.UEV, 100, GCYLMetaBlocks.GCYL_CLEANROOM_CASING.getItemVariant(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO3), false, false, GCYLCleanroomType.ISO3)
                .input(frameGt, EnrichedNaquadahAlloy)
                .inputs(ELECTRIC_MOTOR_UEV.getStackForm(2))
                .inputs(EMITTER_UEV.getStackForm(2))
                .inputs(UVA_HALIDE_LAMP.getStackForm(4))
                .inputs(ITEM_FILTER.getStackForm(8))
                .input(rotor, EnrichedNaquadahAlloy, 2)
                .input(wireGtSingle, UEVSuperconductor, 64)
                .fluidInputs(Indalloy140.getFluid(L * 8))
                .outputs(GCYLMetaBlocks.GCYL_CLEANROOM_CASING.getItemVariant(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO2, 2))
                .buildAndRegister();

        getAssLineResearchBuilder(GTValues.UIV, 100, GCYLMetaBlocks.GCYL_CLEANROOM_CASING.getItemVariant(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO2), false, true, GCYLCleanroomType.ISO2)
                .input(frameGt, HastelloyX78)
                .inputs(ELECTRIC_MOTOR_UIV.getStackForm(2))
                .inputs(EMITTER_UIV.getStackForm(2))
                .inputs(UVA_HALIDE_LAMP.getStackForm(8))
                .inputs(ITEM_FILTER.getStackForm(16))
                .input(rotor, HastelloyX78, 2)
                .input(wireGtSingle, UIVSuperconductor, 64)
                .fluidInputs(Indalloy140.getFluid(L * 16))
                .outputs(GCYLMetaBlocks.GCYL_CLEANROOM_CASING.getItemVariant(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO1, 2))
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
                    .fluidInputs(SuperfluidHelium.getFluid((int) (10 * Math.pow(2, i))))
                    .fluidInputs(Seaborgium.getFluid(L * i * 2))
                    .fluidInputs(Indalloy140.getFluid(L * i * 4))
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
                getAssLineResearchBuilder(i, 400, i == 5 ? ENERGY_INPUT_HATCH_16A[i - 5].getStackForm() : WIRELESS_ENERGY_HATCH_INPUT[i - 5].getStackForm(), true, true)
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
