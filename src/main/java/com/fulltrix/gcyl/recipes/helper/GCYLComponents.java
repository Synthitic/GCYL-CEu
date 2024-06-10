package com.fulltrix.gcyl.recipes.helper;

import gregtech.api.GTValues;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.loaders.recipe.CraftingComponent;

import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.loaders.recipe.CraftingComponent.Component;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GCYLComponents {
    public static Component PLATE_DENSE;
    public static Component CABLE_DOUBLE;
    public static Component GEAR;

    public static void initializeComponents() {
        PLATE_DENSE = new Component(Stream.of(new Object[][]{

                {1, new UnificationEntry(plateDense, Steel)},
                {2, new UnificationEntry(plateDense, Aluminium)},
                {3, new UnificationEntry(plateDense, StainlessSteel)},
                {4, new UnificationEntry(plateDense, Titanium)},
                {5, new UnificationEntry(plateDense, TungstenSteel)},
                {6, new UnificationEntry(plateDense, RhodiumPlatedPalladium)},
                {7, new UnificationEntry(plateDense, HSSS)},
                {8, new UnificationEntry(plateDense, Tritanium)},
                {9, new UnificationEntry(plateDense, Seaborgium)},
                {10, new UnificationEntry(plateDense, Bohrium)},
                {11, new UnificationEntry(plateDense, Quantum)},
                {12, new UnificationEntry(plateDense, Neutronium)}

        }).collect(Collectors.toMap(data -> (Integer) data[0], data -> data[1])));

        CABLE_DOUBLE = new Component(Stream.of(new Object[][]{

                {0, new UnificationEntry(cableGtDouble, Lead)},
                {1, new UnificationEntry(cableGtDouble, Tin)},
                {2, new UnificationEntry(cableGtDouble, Copper)},
                {3, new UnificationEntry(cableGtDouble, Gold)},
                {4, new UnificationEntry(cableGtDouble, Aluminium)},
                {5, new UnificationEntry(cableGtDouble, Platinum)},
                {6, new UnificationEntry(cableGtDouble, NiobiumTitanium)},
                {7, new UnificationEntry(cableGtDouble, Naquadah)},
                {8, new UnificationEntry(cableGtDouble, NaquadahAlloy)},
                {9, new UnificationEntry(cableGtDouble, AbyssalAlloy)},
                {10, new UnificationEntry(cableGtDouble, TitanSteel)},
                {11, new UnificationEntry(cableGtDouble, BlackTitanium)},
                {12, new UnificationEntry(cableGtDouble, NaquadriaticTaranium)},
                {13, new UnificationEntry(cableGtDouble, CosmicNeutronium)},
                {14, new UnificationEntry(cableGtDouble, MAXSuperconductor)}

        }).collect(Collectors.toMap(data -> (Integer) data[0], data -> data[1])));

        GEAR = new Component(Stream.of(new Object[][]{

                {1, new UnificationEntry(gear, Steel)},
                {2, new UnificationEntry(gear, Aluminium)},
                {3, new UnificationEntry(gear, StainlessSteel)},
                {4, new UnificationEntry(gear, Titanium)},
                {5, new UnificationEntry(gear, TungstenSteel)},
                {6, new UnificationEntry(gear, RhodiumPlatedPalladium)},
                {7, new UnificationEntry(gear, HSSS)},
                {8, new UnificationEntry(gear, Tritanium)},
                {9, new UnificationEntry(gear, Seaborgium)},
                {10, new UnificationEntry(gear, Bohrium)},
                {11, new UnificationEntry(gear, Quantum)},
                {12, new UnificationEntry(gear, Neutronium)}

        }).collect(Collectors.toMap(data -> (Integer) data[0], data -> data[1])));

        CraftingComponent.CABLE = new Component(Stream.of(new Object[][] {

                { 0, new UnificationEntry(OrePrefix.cableGtSingle, Materials.RedAlloy) },
                { 1, new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tin) },
                { 2, new UnificationEntry(OrePrefix.cableGtSingle, Materials.Copper) },
                { 3, new UnificationEntry(OrePrefix.cableGtSingle, Materials.Gold) },
                { 4, new UnificationEntry(OrePrefix.cableGtSingle, Materials.Aluminium) },
                { 5, new UnificationEntry(OrePrefix.cableGtSingle, Platinum) },
                { 6, new UnificationEntry(OrePrefix.cableGtSingle, Materials.NiobiumTitanium) },
                { 7, new UnificationEntry(OrePrefix.cableGtSingle, Materials.Naquadah) },
                { 8, new UnificationEntry(OrePrefix.cableGtSingle, Materials.NaquadahAlloy) },
                { 9, new UnificationEntry(OrePrefix.cableGtSingle, TungstenTitaniumCarbide) },

        }).collect(Collectors.toMap(data -> (Integer) data[0], data -> data[1])));

        CraftingComponent.CABLE_QUAD = new Component(Stream.of(new Object[][] {

                { 0, new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.RedAlloy) },
                { 1, new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.Tin) },
                { 2, new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.Copper) },
                { 3, new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.Gold) },
                { 4, new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.Aluminium) },
                { 5, new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.Platinum) },
                { 6, new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.NiobiumTitanium) },
                { 7, new UnificationEntry(OrePrefix.cableGtQuadruple, Naquadah) },
                { 8, new UnificationEntry(OrePrefix.cableGtQuadruple, NaquadahAlloy) },
                { 9, new UnificationEntry(OrePrefix.cableGtQuadruple, TungstenTitaniumCarbide) },

        }).collect(Collectors.toMap(data -> (Integer) data[0], data -> data[1])));

        CraftingComponent.CABLE_OCT = new Component(Stream.of(new Object[][] {

                { 0, new UnificationEntry(OrePrefix.cableGtOctal, Materials.RedAlloy) },
                { 1, new UnificationEntry(OrePrefix.cableGtOctal, Materials.Tin) },
                { 2, new UnificationEntry(OrePrefix.cableGtOctal, Materials.Copper) },
                { 3, new UnificationEntry(OrePrefix.cableGtOctal, Materials.Gold) },
                { 4, new UnificationEntry(OrePrefix.cableGtOctal, Materials.Aluminium) },
                { 5, new UnificationEntry(OrePrefix.cableGtOctal, Materials.Platinum) },
                { 6, new UnificationEntry(OrePrefix.cableGtOctal, Materials.NiobiumTitanium) },
                { 7, new UnificationEntry(OrePrefix.cableGtOctal, Naquadah) },
                { 8, new UnificationEntry(OrePrefix.cableGtOctal, NaquadahAlloy) },
                { 9, new UnificationEntry(OrePrefix.cableGtOctal, TungstenTitaniumCarbide) },

        }).collect(Collectors.toMap(data -> (Integer) data[0], data -> data[1])));

        CraftingComponent.CABLE_HEX = new Component(Stream.of(new Object[][] {

                { 0, new UnificationEntry(OrePrefix.cableGtHex, Materials.RedAlloy) },
                { 1, new UnificationEntry(OrePrefix.cableGtHex, Materials.Tin) },
                { 2, new UnificationEntry(OrePrefix.cableGtHex, Materials.Copper) },
                { 3, new UnificationEntry(OrePrefix.cableGtHex, Materials.Gold) },
                { 4, new UnificationEntry(OrePrefix.cableGtHex, Materials.Aluminium) },
                { 5, new UnificationEntry(OrePrefix.cableGtHex, Materials.Platinum) },
                { 6, new UnificationEntry(OrePrefix.cableGtHex, Materials.NiobiumTitanium) },
                { 7, new UnificationEntry(OrePrefix.cableGtHex, Naquadah) },
                { 8, new UnificationEntry(OrePrefix.cableGtHex, NaquadahAlloy) },
                { 9, new UnificationEntry(OrePrefix.cableGtHex, TungstenTitaniumCarbide) },

        }).collect(Collectors.toMap(data -> (Integer) data[0], data -> data[1])));

        CraftingComponent.CABLE_TIER_UP = new Component(Stream.of(new Object[][] {

                { 0, new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tin) },
                { 1, new UnificationEntry(OrePrefix.cableGtSingle, Materials.Copper) },
                { 2, new UnificationEntry(OrePrefix.cableGtSingle, Materials.Gold) },
                { 3, new UnificationEntry(OrePrefix.cableGtSingle, Materials.Aluminium) },
                { 4, new UnificationEntry(OrePrefix.cableGtSingle, Materials.Platinum) },
                { 5, new UnificationEntry(OrePrefix.cableGtSingle, Materials.NiobiumTitanium) },
                { 6, new UnificationEntry(OrePrefix.cableGtSingle, Naquadah) },
                { 7, new UnificationEntry(OrePrefix.cableGtSingle, NaquadahAlloy) },
                { 8, new UnificationEntry(OrePrefix.cableGtSingle, TungstenTitaniumCarbide) },
                { GTValues.FALLBACK, new UnificationEntry(OrePrefix.cableGtSingle, Materials.Europium) },

        }).collect(Collectors.toMap(data -> (Integer) data[0], data -> data[1])));

        CraftingComponent.CABLE_QUAD_TIER_UP = new Component(Stream.of(new Object[][] {

                { 0, new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.Tin) },
                { 1, new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.Copper) },
                { 2, new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.Gold) },
                { 3, new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.Aluminium) },
                { 4, new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.Platinum) },
                { 5, new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.NiobiumTitanium) },
                { 6, new UnificationEntry(OrePrefix.cableGtQuadruple, Naquadah) },
                { 7, new UnificationEntry(OrePrefix.cableGtQuadruple, NaquadahAlloy) },
                { 8, new UnificationEntry(OrePrefix.cableGtQuadruple, TungstenTitaniumCarbide) },
                { GTValues.FALLBACK, new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.Europium) },

        }).collect(Collectors.toMap(data -> (Integer) data[0], data -> data[1])));

    }

}



    /*
    COMPONENT = new Component(Stream.of(new Object[][] {

                {0,)},
                {1,)},
                {2,)},
                {3,)},
                {4,)},
                {5,)},
                {6,)},
                {7,)},
                {8,)},
                {9,)},
                {10,)},
                {11,)},
                {12,)},
                {13,)},
                {14,)},

     }).collect(Collectors.toMap(data -> (Integer) data[0], data -> data[1])));
     */


