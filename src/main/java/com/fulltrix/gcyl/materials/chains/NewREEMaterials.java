package com.fulltrix.gcyl.materials.chains;

import gregtech.api.unification.material.Material;

import static com.fulltrix.gcyl.api.GCYLUtility.gcylId;
import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static gregtech.api.unification.material.Materials.*;

public class NewREEMaterials {

    public static Material CeriumRichLREESulfateSolution;
    public static Material CeriumRichLREEHydroxides;
    public static Material MonaziteHydroxideSolution;
    public static Material TrisodiumPhosphate;
    public static Material ThoriumNitratePentahydrate;
    public static Material ThoriumDioxide;
    public static Material IrradiatedAir;
    public static Material DEHPA;
    public static Material AmmoniumThiocyanate;
    public static Material CarbonDisulfide;
    public static Material Xenotime;
    public static Material XenotimeSulfateSolution;
    public static Material XenotimeHydroxideSolution;
    public static Material LREENitrateSolution;
    public static Material LREEOxalates;
    public static Material LREEOxides;
    public static Material LREEPentachlorides;
    public static Material LREEChlorides;
    public static Material MREENitrateSolution;
    public static Material MREEOxalates;
    public static Material MREEOxides;
    public static Material MREEPentachlorides;
    public static Material MREEChlorides;
    public static Material HREENitrateSolution;
    public static Material HREEOxalates;
    public static Material HREEOxides;
    public static Material HREEPentachlorides;
    public static Material HREEChlorides;

    public static void init() {
        CeriumRichLREESulfateSolution = new Material.Builder(++id, gcylId("cerium_rich_lree_sulfate"))
                .color((Bastnasite.getMaterialRGB() + Cerium.getMaterialRGB()) / 2)
                .fluid()
                .build()
                .setFormula("Ce2(SO4)3", true);
        CeriumRichLREEHydroxides = new Material.Builder(++id, gcylId("cerium_rich_lree_hydroxide"))
                .color(CeriumRichLREESulfateSolution.getMaterialRGB() + 10)
                .fluid()
                .build()
                .setFormula("Ce(OH)3", true);
        MonaziteHydroxideSolution = new Material.Builder(++id, gcylId("monazite_hydroxide_solution"))
                .color(Monazite.getMaterialRGB() + 10)
                .fluid()
                .build()
                .setFormula("Ln(OH)3", true);
        TrisodiumPhosphate = new Material.Builder(++id, gcylId("trisodium_phosphate"))
                .color((Sodium.getMaterialRGB() + Phosphorus.getMaterialRGB()) / 2)
                .dust()
                .build()
                .setFormula("Na3PO4", true);
        ThoriumNitratePentahydrate = new Material.Builder(++id, gcylId("thorium_nitrate_pentahydrate"))
                .color((Thorium.getMaterialRGB() + Nitrogen.getMaterialRGB()) / 2)
                .dust()
                .build()
                .setFormula("(Th(NO3)4)(H2O)5", true);
        ThoriumDioxide = new Material.Builder(++id, gcylId("thorium_dioxide"))
                .color((Thorium.getMaterialRGB() + Oxygen.getMaterialRGB()) / 2)
                .dust()
                .build()
                .setFormula("ThO2", true);
        IrradiatedAir = new Material.Builder(++id, gcylId("irradiated_air"))
                .color((Radium.getMaterialRGB() + Oxygen.getMaterialRGB()) / 2)
                .fluid()
                .build()
                .setFormula("Ra???", true);
        DEHPA = new Material.Builder(++id, gcylId("dehpa"))
                .color((Ethylhexanol.getMaterialRGB() + SodiumHydroxide.getMaterialRGB()) / 2)
                .fluid()
                .build()
                .setFormula("(C8H17O)2PO2H", true);
        AmmoniumThiocyanate = new Material.Builder(++id, gcylId("ammonium_thiocyanate"))
                .color(0xfcfc30)
                .fluid()
                .build()
                .setFormula("(NH4)(SCN)", true);
        CarbonDisulfide = new Material.Builder(++id, gcylId("carbon_disulfide"))
                .color(Sulfur.getMaterialRGB() - 10)
                .dust()
                .build()
                .setFormula("CS2", true);
        Xenotime = new Material.Builder(++id, gcylId("xenotime"))
                .color(0x606141)
                .ore()
                .dust()
                .build()
                .setFormula("YPO4", true);
        XenotimeSulfateSolution = new Material.Builder(++id, gcylId("xenotime_sulfate_solution"))
                .color(Xenotime.getMaterialRGB() - 10)
                .fluid()
                .build()
                .setFormula("Y2(SO4)3", true);
        XenotimeHydroxideSolution = new Material.Builder(++id, gcylId("xenotime_hydroxide_solution"))
                .color(Xenotime.getMaterialRGB() + 10)
                .fluid()
                .build()
                .setFormula("Y(OH)3", true);

        LREENitrateSolution = new Material.Builder(++id, gcylId("lree_nitrate_solution"))
                .color((Cerium.getMaterialRGB() + NitricAcid.getMaterialRGB()) / 2)
                .fluid()
                .build()
                .setFormula("Ce(NO3)3", true);
        LREEOxalates = new Material.Builder(++id, gcylId("lree_oxalates"))
                .color((Cerium.getMaterialRGB() + OxalicAcid.getMaterialRGB()) / 2)
                .dust()
                .build()
                .setFormula("Ce2(C2O4)3", true);
        LREEOxides = new Material.Builder(++id, gcylId("lree_oxides"))
                .color((Cerium.getMaterialRGB() + Oxygen.getMaterialRGB()) / 2)
                .dust()
                .build()
                .setFormula("Ce2O3", true);
        LREEPentachlorides = new Material.Builder(++id, gcylId("lree_pentachlorides"))
                .color((Cerium.getMaterialRGB() + Chlorine.getMaterialRGB() * 2) / 3)
                .dust()
                .build()
                .setFormula("(NH4)2(CeCl5)", true);
        LREEChlorides = new Material.Builder(++id, gcylId("lree_chlorides"))
                .color((Cerium.getMaterialRGB() + Chlorine.getMaterialRGB()) / 2)
                .dust()
                .build()
                .setFormula("CeCl3", true);
        MREENitrateSolution = new Material.Builder(++id, gcylId("mree_nitrate_solution"))
                .color((Samarium.getMaterialRGB() + NitricAcid.getMaterialRGB()) / 2)
                .fluid()
                .build()
                .setFormula("Sm(NO3)3", true);
        MREEOxalates = new Material.Builder(++id, gcylId("mree_oxalates"))
                .color((Samarium.getMaterialRGB() + OxalicAcid.getMaterialRGB()) / 2)
                .dust()
                .build()
                .setFormula("Sm2(C2O4)3", true);
        MREEOxides = new Material.Builder(++id, gcylId("mree_oxides"))
                .color((Samarium.getMaterialRGB() + Oxygen.getMaterialRGB()) / 2)
                .dust()
                .build()
                .setFormula("Sm2O3", true);
        MREEPentachlorides = new Material.Builder(++id, gcylId("mree_pentachlorides"))
                .color((Samarium.getMaterialRGB() + Chlorine.getMaterialRGB() * 2) / 3)
                .dust()
                .build()
                .setFormula("(NH4)2(SmCl5)", true);
        MREEChlorides = new Material.Builder(++id, gcylId("mree_chlorides"))
                .color((Samarium.getMaterialRGB() + Chlorine.getMaterialRGB()) / 2)
                .dust()
                .build()
                .setFormula("SmCl3", true);
        HREENitrateSolution = new Material.Builder(++id, gcylId("hree_nitrate_solution"))
                .color((Yttrium.getMaterialRGB() + NitricAcid.getMaterialRGB()) / 2)
                .fluid()
                .build()
                .setFormula("Y(NO3)3", true);
        HREEOxalates = new Material.Builder(++id, gcylId("hree_oxalates"))
                .color((Yttrium.getMaterialRGB() + OxalicAcid.getMaterialRGB()) / 2)
                .dust()
                .build()
                .setFormula("Y2(C2O4)3", true);
        HREEOxides = new Material.Builder(++id, gcylId("hree_oxides"))
                .color((Yttrium.getMaterialRGB() + Oxygen.getMaterialRGB()) / 2)
                .dust()
                .build()
                .setFormula("Y2O3", true);
        HREEPentachlorides = new Material.Builder(++id, gcylId("hree_pentachlorides"))
                .color((Yttrium.getMaterialRGB() + Chlorine.getMaterialRGB() * 2) / 3)
                .dust()
                .build()
                .setFormula("(NH4)2(YCl5)", true);
        HREEChlorides = new Material.Builder(++id, gcylId("hree_chlorides"))
                .color((Yttrium.getMaterialRGB() + Chlorine.getMaterialRGB()) / 2)
                .dust()
                .build()
                .setFormula("YCl3", true);

    }
}
