package com.fulltrix.gcyl.recipes.categories.circuits;

import gregtech.api.GTValues;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.unification.material.MarkerMaterials;

import static com.fulltrix.gcyl.api.GCYLUtility.getMarkerMaterialByTier;
import static com.fulltrix.gcyl.item.GCYLCoreItems.*;
import static gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES;
import static gregtech.api.unification.ore.OrePrefix.circuit;

public class GenericUnification {
    public static void init() {

        for(int i = 0; i < 15; i++) {
            ASSEMBLER_RECIPES.recipeBuilder().EUt(8).duration(20)
                    .circuitMeta(29)
                    .input(circuit, getMarkerMaterialByTier(i), 16)
                    .outputs(getGenericCircuitByTier(i).getStackForm(16))
                    .buildAndRegister();
        }

    }

    private static MetaItem<?>.MetaValueItem getGenericCircuitByTier(int tier) {
        return switch (tier) {
            case (0) -> GENERIC_CIRCUIT_ULV;
            case (1) -> GENERIC_CIRCUIT_LV;
            case (2) -> GENERIC_CIRCUIT_MV;
            case (3) -> GENERIC_CIRCUIT_HV;
            case (4) -> GENERIC_CIRCUIT_EV;
            case (5) -> GENERIC_CIRCUIT_IV;
            case (6) -> GENERIC_CIRCUIT_LuV;
            case (7) -> GENERIC_CIRCUIT_ZPM;
            case (8) -> GENERIC_CIRCUIT_UV;
            case (9) -> GENERIC_CIRCUIT_UHV;
            case (10) -> GENERIC_CIRCUIT_UEV;
            case (11) -> GENERIC_CIRCUIT_UIV;
            case (12) -> GENERIC_CIRCUIT_UXV;
            case (13) -> GENERIC_CIRCUIT_OpV;
            case (14) -> GENERIC_CIRCUIT_MAX;
            default -> null;
        };
    }
}
