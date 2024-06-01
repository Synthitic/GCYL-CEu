package com.fulltrix.gcyl.recipes.helper;

import gregtech.api.unification.stack.UnificationEntry;

import static com.fulltrix.gcyl.GCYLMaterials.*;
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


