package com.fulltrix.gcyl.materials;

import gregtech.api.unification.Elements;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.BlastProperty;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.fulltrix.gcyl.GCYLElements.*;
import static com.fulltrix.gcyl.api.GCYLUtility.gcylId;
import static com.fulltrix.gcyl.api.recipes.GCYLMaterialFlags.NO_MIXER_RECIPE;
import static gregtech.api.unification.Elements.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.Materials.STD_METAL;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;
import static com.fulltrix.gcyl.materials.GCYLMaterials.id;
import static gregtech.api.unification.material.info.MaterialFlags.GENERATE_DENSE;
import static gregtech.api.unification.material.info.MaterialIconSet.*;
import static kono.ceu.materialreplication.api.unification.materials.flags.MRMaterialFlags.DISABLE_REPLICATION;

public class GCYLNuclearMaterials {

    //NEEDED FOR RECIPES
    public static Material ReactorSteel;
    public static Material Plutonium;
    public static Material Americium241;
    public static Material Americium243;
    public static Material Curium247;
    public static Material Curium250;
    public static Material Californium252;
    public static Material Californium253;
    public static Material Einsteinium253;
    public static Material Fermium258;
    public static Material Fermium259;
    public static Material Mendelevium261;

    //TODO: REMOVE AFTER IMPLEMENTING NUCLEAR
    public static Material Bismuth210;

    //HEXAFLUORIDES, OXIDES, NITRITES, HEXACHLORIDES
    public static Map<String, Material> GenericRadioactives = new Object2ObjectOpenHashMap<>();

    public final static Material[] radioactiveMats = {Americium, Curium};
    private final static String[] types = {"nitrite", "dioxide", "hexachloride", "hexafluoride"};

    public static List<String> GenericRadioactivesString = new ArrayList<>();

    //DECAY CHAMBER NUCLEAR MATERIALS
    public static Material Plutonium243;

    /*
    public static Material LEN236;
    public static Material HighEnrichedNeptuniumDioxide;
    public static Material DepletedNeptuniumDioxide;

     */
    public static void registerRequiredNuclear() {

        ReactorSteel = new Material.Builder(++id, gcylId("reactor_steel"))
                .ingot(2).liquid()
                .color(0xB4B3B0)
                .iconSet(SHINY)
                .flags(GENERATE_DENSE, DISABLE_DECOMPOSITION, DISABLE_REPLICATION, NO_MIXER_RECIPE)
                .components(Iron,15,Niobium,1,Vanadium,4,Carbon,2)
                .blast(b->b.temp(3800, BlastProperty.GasTier.HIGH))
                .build();

        Plutonium = new Material.Builder(++id, gcylId("plutonium_generic"))
                .ingot(3).liquid()
                .color(0xF03232)
                .iconSet(METALLIC)
                .flags(EXT_METAL)
                .element(Elements.Pu)
                .build();

        Americium241 = new Material.Builder(++id, gcylId("americium_241"))
                .ingot().liquid()
                .color(Americium.getMaterialRGB())
                .iconSet(METALLIC)
                .element(Am241)
                .flags(STD_METAL)
                .build()
                .setFormula("Am-241", true);

        Fermium258 = new Material.Builder(++id, gcylId("fermium_258"))
                .ingot().liquid()
                .color(0x984ACF)
                .iconSet(METALLIC)
                .element(Fm258)
                .flags(STD_METAL)
                .build()
                .setFormula("Fm-258", true);

        Californium252 = new Material.Builder(++id, gcylId("californium_252"))
                .ingot().liquid()
                .color(0xA85A12)
                .iconSet(METALLIC)
                .flags(STD_METAL)
                .element(Cf252)
                .build()
                .setFormula("Cf-252", true);

        Mendelevium261 = new Material.Builder(++id, gcylId("mendelevium_261"))
                .ingot().liquid()
                .color(Mendelevium.getMaterialRGB())
                .iconSet(METALLIC)
                .flags(STD_METAL)
                .element(Md261)
                .build()
                .setFormula("Md-261", true);

        Americium243 = new Material.Builder(++id, gcylId("americium_243"))
                .ingot().liquid()
                .color(Americium.getMaterialRGB())
                .iconSet(METALLIC)
                .flags(STD_METAL)
                .element(Am243)
                .build()
                .setFormula("Am-243", true);

        Curium247 = new Material.Builder(++id, gcylId("curium_247"))
                .ingot().liquid()
                .color(Curium.getMaterialRGB())
                .iconSet(METALLIC)
                .flags(STD_METAL)
                .element(Cm247)
                .build()
                .setFormula("Cm-247", true);

        Californium253 = new Material.Builder(++id, gcylId("californium_253"))
                .ingot().liquid()
                .color(Californium.getMaterialRGB())
                .iconSet(METALLIC)
                .flags(STD_METAL)
                .element(Cf253)
                .build()
                .setFormula("Cf-253", true);

        Fermium259 = new Material.Builder(++id, gcylId("fermium_259"))
                .ingot().liquid()
                .color(Fermium.getMaterialRGB())
                .iconSet(METALLIC)
                .flags(STD_METAL)
                .element(Fm259)
                .build()
                .setFormula("Fm-259", true);

        Curium250 = new Material.Builder(++id, gcylId("curium_250"))
                .ingot().liquid()
                .color(Curium.getMaterialRGB())
                .iconSet(METALLIC)
                .flags(STD_METAL)
                .element(Cm250)
                .build()
                .setFormula("Cm-250", true);

        Einsteinium253 = new Material.Builder(++id, gcylId("einsteinium_253"))
                .ingot().liquid()
                .color(Einsteinium.getMaterialRGB())
                .iconSet(METALLIC)
                .flags(STD_METAL)
                .element(Es253)
                .build()
                .setFormula("Es-253", true);

        registerRadioactiveStuff();

        registerDecayMats();

        //TODO: FINISH NUCLEAR AND REMOVE

        /*
        HighEnrichedNeptuniumDioxide = new Material.Builder(++id, gcylId("high_enriched_neptunium_dioxide"))
                .dust(3)
                .color(0x53E353).iconSet(DULL)
                .flags(DISABLE_DECOMPOSITION)
                .components(Neptunium236, 1, Oxygen, 2)
                .build()
                .setFormula("NpO2", true);

        DepletedNeptuniumDioxide = new Material.Builder(++id, gcylId("depleted_neptunium_dioxide"))
                .dust(3)
                .color(0x335323).iconSet(DULL)
                .flags(DISABLE_DECOMPOSITION)
                .components(Neptunium, 1, Oxygen, 2)
                .build()
                .setFormula("NpO2", true);

        LEN236 = new Material.Builder(++id, gcylId("len_236"))
                .dust(3)
                .color(Neptunium.getMaterialRGB())
                .flags(DISABLE_DECOMPOSITION, DISABLE_REPLICATION)
                .components(HighEnrichedNeptuniumDioxide, 1, DepletedNeptuniumDioxide, 20)
                .fissionFuelProperties(2000,1500,35,25,2000,25)
                .build()
                .setFormula("NpO2", true);

         */
    }

    private static void registerRadioactiveStuff() {

        /* //TODO: ???
        for(Material mats : radioactiveMats) {
            for (String s : types) {
                if (s.equals("hexafluoride")) {
                    GenericRadioactives.put(mats.getName() + "_" + s, new Material.Builder(++id, gcylId(mats.getName() + "_" + s))
                            .liquid()
                            .color(mats.getMaterialRGB())
                            .iconSet(FLUID)
                            .flags(DISABLE_DECOMPOSITION, DISABLE_REPLICATION)
                            .components(mats, 1, Fluorine, 6)
                            .build());

                    GenericRadioactivesString.add(mats.getName()+"_"+s);

                } else if(s.equals("hexachloride")) {
                    GenericRadioactives.put(mats.getName() + "_" + s, new Material.Builder(++id, gcylId(mats.getName() + "_" + s))
                            .liquid()
                            .color(mats.getMaterialRGB())
                            .iconSet(FLUID)
                            .flags(DISABLE_DECOMPOSITION, DISABLE_REPLICATION)
                            .components(mats, 1, Chlorine, 6)
                            .build());
                    GenericRadioactivesString.add(mats.getName()+"_"+s);
                }
                else if (s.equals("nitrite")) {
                    GenericRadioactives.put(mats.getName() + "_" + s, new Material.Builder(++id, gcylId(mats.getName() + "_" + s))
                            .dust()
                            .color(mats.getMaterialRGB())
                            .iconSet(METALLIC)
                            .flags(DISABLE_DECOMPOSITION, DISABLE_REPLICATION)
                            .components(mats,1)
                            .build());
                    GenericRadioactivesString.add(mats.getName()+"_"+s);
                } else {
                    GenericRadioactives.put(mats.getName() + "_" + s, new Material.Builder(++id, gcylId(mats.getName() + "_" + s))
                            .dust()
                            .color(mats.getMaterialRGB())
                            .iconSet(METALLIC)
                            .flags(DISABLE_DECOMPOSITION, DISABLE_REPLICATION)
                            .components(mats,1)
                            .build());
                    GenericRadioactivesString.add(mats.getName()+"_"+s);
                }
            }
        }

         */

    }

    private static void registerDecayMats() {

    }

    public static int idSecondary;

    public static Material Np237Breeder;

    public static void registerSecondaryNuclear() {


        Bismuth210 = new Material.Builder(++idSecondary, gcylId("bismuth_210"))
                .dust()
                .color(Bismuth.getMaterialRGB())
                .iconSet(Bismuth.getMaterialIconSet())
                .element(Bi210)
                .build()
                .setFormula("Bi-210", true);

        Np237Breeder = new Material.Builder(++idSecondary, gcylId("np_237_breeder"))
                .dust()
                .components(Neptunium237, 1, Aluminium, 1)
                .color(Neptunium.getMaterialRGB())
                .iconSet(DULL)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .fissionFuelProperties(2000, 1000, 1000, 0, 100, 10, 3.5)
                .build();

    }
}
