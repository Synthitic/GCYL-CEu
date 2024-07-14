package com.fulltrix.gcyl.materials.chains;

import static com.fulltrix.gcyl.materials.GCYLMaterials.id;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static com.fulltrix.gcyl.api.GCYLUtility.gcylId;
import static gregtech.api.unification.material.Materials.Ammonia;
import static gregtech.api.unification.material.Materials.Water;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;

public class MiscMaterials {

    public static Material AmmoniumHydroxide;
    public static Material PalmOil;
    public static Material OctanicAcid;

    public static void init() {

        AmmoniumHydroxide = new Material.Builder(++id, gcylId("ammonium_hydroxide"))
                .color(Ammonia.getMaterialRGB() + 10)
                .iconSet(MaterialIconSet.DULL)
                .flags()
                .components(Ammonia, 1, Water, 1)
                .fluid()
                .build();

        PalmOil = new Material.Builder(++id, gcylId("palm_oil"))
                .color(0xfafcd2)
                .iconSet(MaterialIconSet.DULL)
                .flags(DISABLE_DECOMPOSITION)
                .fluid()
                .build()
                .setFormula("?", true);

        OctanicAcid = new Material.Builder(++id, gcylId("octanic_acid"))
                .color(PalmOil.getMaterialRGB() + 10)
                .iconSet(MaterialIconSet.DULL)
                .flags(DISABLE_DECOMPOSITION)
                .fluid()
                .build()
                .setFormula("?", true);
    }
}
