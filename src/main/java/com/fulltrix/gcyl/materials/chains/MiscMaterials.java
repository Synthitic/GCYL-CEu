package com.fulltrix.gcyl.materials.chains;

import static com.fulltrix.gcyl.materials.GCYLMaterials.id;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.unification.material.properties.BlastProperty;

import static com.fulltrix.gcyl.api.GCYLUtility.gcylId;
import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;
import static gregtech.api.unification.material.info.MaterialFlags.GENERATE_FINE_WIRE;

public class MiscMaterials {

    public static Material AmmoniumHydroxide;
    public static Material PalmOil;
    public static Material OctanicAcid;
    public static Material Irirutan;

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

        Irirutan = new Material.Builder(++id, gcylId("irirutan"))
                .color((Ruthenium.getMaterialRGB() + Iridium.getMaterialRGB() * 2 + Tantalum.getMaterialRGB() * 3) / 6)
                .iconSet(MaterialIconSet.METALLIC)
                .ingot()
                .flags(GENERATE_FINE_WIRE)
                .fluid()
                .blast(b -> b
                        .temp(5500, BlastProperty.GasTier.HIGH)
                        .blastStats(VA[IV], 1600)
                        .vacuumStats(VA[EV], 300))
                .components(Ruthenium, 1, Iridium, 2, Tantalum, 3)
                .build();
    }
}
