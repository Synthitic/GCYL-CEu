package com.fulltrix.gcyl.recipes.categories.machines;

import gregtech.api.GTValues;
import gregtech.api.recipes.ingredients.GTRecipeInput;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.stack.UnificationEntry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static com.fulltrix.gcyl.GCYLUtility.*;
import static com.fulltrix.gcyl.machines.GCYLTileEntities.*;
import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static com.fulltrix.gcyl.recipes.helper.GCYLComponents.SUPERCONDUCTOR_WIRE;
import static gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.api.GTValues.L;
import static gregtech.common.items.MetaItems.*;
import static gregtech.common.metatileentities.MetaTileEntities.*;
import static gregtech.loaders.recipe.CraftingComponent.FIELD_GENERATOR;

public class MultiblockPartCraftingRecipes {
    public static void init() {
        wirelessEnergyHatches();
    }

    private static void wirelessEnergyHatches() {

        /*
        for(int i = 0; i < WIRELESS_ENERGY_HATCH_INPUT.length - 1; i++) {
            int finalI = i;
            ASSEMBLY_LINE_RECIPES.recipeBuilder().EUt(GTValues.VA[i + 1]).duration(400)
                    .outputs(WIRELESS_ENERGY_HATCH_INPUT[i].getStackForm())
                    .inputs(ENERGY_INPUT_HATCH[i].getStackForm())
                    .inputs(getPowerIC(i))
                    .inputs(getPowerIC(i))
                    .inputs()
                    .stationResearch(b -> b
                            .researchStack(finalI == 0 ? ENERGY_INPUT_HATCH[0].getStackForm() : WIRELESS_ENERGY_HATCH_INPUT[finalI - 1].getStackForm())
                            .CWUt(Math.min(32, Math.pow(2, finalI-1))
                            .EUt(GTValues.VA[finalI +1]))




        }

         */
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