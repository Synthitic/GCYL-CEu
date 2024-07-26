package com.fulltrix.gcyl.materials.chains;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

import static com.fulltrix.gcyl.api.GCYLUtility.gcylId;
import static com.fulltrix.gcyl.materials.GCYLMaterials.PalladiumChloride;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.DISABLE_DECOMPOSITION;
import static com.fulltrix.gcyl.materials.GCYLMaterials.id;
import static gregtech.api.unification.material.info.MaterialFlags.GENERATE_PLATE;

public class NewPlatinumGroupMaterials {

    public static Material AcidicOsmiumTetroxideSolution;
    public static Material OsmiumChlorideSolution;
    public static Material PGMSolution1;
    public static Material PGMSolution1IR;
    public static Material PGMSolution1Plat;
    public static Material PGMSolution1Iri;
    public static Material PGMSolution1IriOxide;
    public static Material PGMSolution1Pall;
    public static Material PGMSolution1Rho;
    public static Material PGMSolution1Ruth;
    public static Material PGMSolution1InertMetals;
    public static Material PotassiumOsmate;
    public static Material AmmoniumHexachloroplatinate;
    public static Material AmmoniumHexachloroiridate;
    public static Material PalladiumDimethylglyoxime;
    public static Material AmmoniumHexachloropalladate;
    public static Material TetraaminePalladiumChloride;
    public static Material DiamineDichloropalladium;
    public static Material PotassiumHexachlororuthenate;
    public static Material PotassiumHexanitrorhodate;
    public static Material PotassiumHexachlororhodateSolution;
    public static Material AmmoniumHexachlororhodate;
    public static Material ChlorinatedRutheniumTetroxideSolution;
    public static Material BasicRutheniumTetroxideSolution;
    public static Material PotassiumPersulfate;
    public static Material PotassiumBisulfate;
    public static Material PotassiumAcetate;
    public static Material SodiumMethoxide;
    public static Material TrimethylBorate;
    public static Material MineralOil;
    public static Material FerrousChloride;
    public static Material Dimethylglyoxime;
    public static Material Butanone;
    public static Material EthylNitrite;
    public static Material DiacetylMonoxime;
    public static Material AmmoniumNitrite;
    public static Material PotassiumNitrate;
    public static Material LeadOxide;
    public static Material ZincBronze;
    public static Material PotassiumChlorideSolution;
    public static Material GoldChloride;

    public static void init() {

        OsmiumChlorideSolution = new Material.Builder(++id, gcylId("osmium_chloride_solution"))
                .color((Osmium.getMaterialRGB() + RareEarth.getMaterialRGB()) / 2)
                .flags(DISABLE_DECOMPOSITION)
                .fluid()
                .build()
                .setFormula("H2OsCl6", true);

        PGMSolution1 = new Material.Builder(++id, gcylId("pgm_solution_one"))
                .color(PlatinumGroupSludge.getMaterialRGB() + 20)
                .flags(DISABLE_DECOMPOSITION)
                .fluid()
                .build()
                .setFormula("(Pt?)(Pd?)(Ru?)", true);

        PGMSolution1IR = new Material.Builder(++id, gcylId("pgm_solution_one_ir"))
                .color(PGMSolution1.getMaterialRGB() + 10)
                .flags(DISABLE_DECOMPOSITION)
                .fluid()
                .build()
                .setFormula("(Pt?)(Pd?)(Ru?)", true);

        PGMSolution1Plat = new Material.Builder(++id, gcylId("pgm_solution_one_plat"))
                .color(PGMSolution1.getMaterialRGB() + 20)
                .flags(DISABLE_DECOMPOSITION)
                .fluid()
                .build()
                .setFormula("(Pt?)(Pd?)(Ru?)", true);

        PGMSolution1Iri = new Material.Builder(++id, gcylId("pgm_solution_one_iri"))
                .color(PGMSolution1.getMaterialRGB() + 30)
                .flags(DISABLE_DECOMPOSITION)
                .fluid()
                .build()
                .setFormula("(Ir?)(Pd?)(Ru?)", true);

        PGMSolution1IriOxide = new Material.Builder(++id, gcylId("pgm_solution_one_irioxide"))
                .color(PGMSolution1.getMaterialRGB() + 0x000a00)
                .flags(DISABLE_DECOMPOSITION)
                .fluid()
                .build()
                .setFormula("(Ir?)(Pd?)(Ru?)", true);

        PGMSolution1Pall = new Material.Builder(++id, gcylId("pgm_solution_one_pall"))
                .color(PGMSolution1.getMaterialRGB() + 0x000a0a)
                .flags(DISABLE_DECOMPOSITION)
                .fluid()
                .build()
                .setFormula("(Pd?)(Rh?)(Ru?)", true);

        PGMSolution1Rho = new Material.Builder(++id, gcylId("pgm_solution_one_rho"))
                .color(PGMSolution1Pall.getMaterialRGB() + 0x000a14)
                .flags(DISABLE_DECOMPOSITION)
                .fluid()
                .build()
                .setFormula("(Rh?)(Ru?)(Au?)", true);

        PGMSolution1Ruth = new Material.Builder(++id, gcylId("pgm_solution_one_ruth"))
                .color(PGMSolution1Pall.getMaterialRGB() + 0x000a1e)
                .flags(DISABLE_DECOMPOSITION)
                .fluid()
                .build()
                .setFormula("(Ru?)(Au?)(Ag?)", true);

        PGMSolution1InertMetals = new Material.Builder(++id, gcylId("pgm_solution_one_inert"))
                .color(PGMSolution1Pall.getMaterialRGB() + 0x001400)
                .flags(DISABLE_DECOMPOSITION)
                .fluid()
                .build()
                .setFormula("(Au?)(Ag?)", true);

        AcidicOsmiumTetroxideSolution = new Material.Builder(++id, gcylId("acidic_osmium_tetroxide_solution"))
                .color((Osmium.getMaterialRGB() + Potassium.getMaterialRGB()) / 2)
                .flags(DISABLE_DECOMPOSITION)
                .fluid()
                .build()
                .setFormula("(KHSO4)4(OsO4)(HCl)4", true);

        PotassiumOsmate = new Material.Builder(++id, gcylId("potassium_osmate"))
                .color((Osmium.getMaterialRGB() + Potassium.getMaterialRGB()) / 2)
                .iconSet(MaterialIconSet.SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .dust()
                .build()
                .setFormula("K2OsO2(OH)4", true);
        AmmoniumHexachloroplatinate = new Material.Builder(++id, gcylId("ammonium_hexachloroplatinate"))
                .color(0xfcf403)
                .iconSet(MaterialIconSet.DULL)
                .flags(DISABLE_DECOMPOSITION)
                .dust()
                .build()
                .setFormula("(NH4)2PtCl6", true);
        AmmoniumHexachloroiridate = new Material.Builder(++id, gcylId("ammonium_hexachloroiridate"))
                .color(0x100310)
                .iconSet(MaterialIconSet.DULL)
                .flags(DISABLE_DECOMPOSITION)
                .dust()
                .build()
                .setFormula("(NH4)2IrCl6", true);
        PalladiumDimethylglyoxime = new Material.Builder(++id, gcylId("palladium_dimethylglyoxime"))
                .color((Palladium.getMaterialRGB() + 0xfff8f7) / 2)
                .iconSet(MaterialIconSet.DULL)
                .flags(DISABLE_DECOMPOSITION)
                .dust()
                .build()
                .setFormula("(NH4)2IrCl6", true);
        AmmoniumHexachloropalladate = new Material.Builder(++id, gcylId("ammonium_hexachloropalladate"))
                .color((PalladiumChloride.getMaterialRGB() + Ammonia.getMaterialRGB()) / 2)
                .iconSet(MaterialIconSet.DULL)
                .flags(DISABLE_DECOMPOSITION)
                .dust()
                .build()
                .setFormula("(NH4)2PdCl6", true);
        TetraaminePalladiumChloride = new Material.Builder(++id, gcylId("tetraamine_palladium_chloride"))
                .color(AmmoniumHexachloropalladate.getMaterialRGB() + 10)
                .iconSet(MaterialIconSet.SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .dust()
                .build()
                .setFormula("Pd(NH3)4Cl2", true);
        DiamineDichloropalladium = new Material.Builder(++id, gcylId("diamine_dichloropalladium"))
                .color(AmmoniumHexachloropalladate.getMaterialRGB() - 10)
                .iconSet(MaterialIconSet.DULL)
                .flags(DISABLE_DECOMPOSITION)
                .dust()
                .build()
                .setFormula("Pd(NH3)2Cl2", true);
        PotassiumHexanitrorhodate = new Material.Builder(++id, gcylId("potassium_hexanitrorhodate"))
                .color((Potassium.getMaterialRGB() + Rhodium.getMaterialRGB()) / 2)
                .flags(DISABLE_DECOMPOSITION)
                .dust()
                .build()
                .setFormula("K3Rh(NO2)6", true);
        PotassiumHexachlororhodateSolution = new Material.Builder(++id,
                gcylId("potassium_hexachlororhodate_solution"))
                .color((PotassiumHexanitrorhodate.getMaterialRGB() + Chlorine.getMaterialRGB()) / 2)
                .flags(DISABLE_DECOMPOSITION)
                .fluid()
                .build()
                .setFormula("(K3RuCl6)(H2O)", true);
        AmmoniumHexachlororhodate = new Material.Builder(++id, gcylId("ammonium_hexachlororhodate"))
                .color((Ammonia.getMaterialRGB() + Rhodium.getMaterialRGB()) / 2)
                .iconSet(MaterialIconSet.DULL)
                .flags(DISABLE_DECOMPOSITION)
                .dust()
                .build()
                .setFormula("(NH4)3RhCl6", true);
        PotassiumHexachlororuthenate = new Material.Builder(++id, gcylId("potassium_hexachlororuthenate"))
                .color((Ruthenium.getMaterialRGB() + Potassium.getMaterialRGB()) / 2)
                .flags(DISABLE_DECOMPOSITION)
                .fluid()
                .build()
                .setFormula("(K3RuCl6)(H2O)", true);
        ChlorinatedRutheniumTetroxideSolution = new Material.Builder(++id,
                gcylId("chlorinated_ruthenium_tetroxide_solution"))
                .color((Ruthenium.getMaterialRGB() + Chlorine.getMaterialRGB()) / 2)
                .flags(DISABLE_DECOMPOSITION)
                .fluid()
                .build()
                .setFormula("(RuO4)2(H2O)2(KCl)6(Cl2)3", true);
        BasicRutheniumTetroxideSolution = new Material.Builder(++id, gcylId("basic_ruthenium_tetroxide_solution"))
                .color((Ruthenium.getMaterialRGB() + Oxygen.getMaterialRGB()) / 2)
                .flags(DISABLE_DECOMPOSITION)
                .fluid()
                .build()
                .setFormula("(NaHRuO5)(H2O)", true);

        PotassiumPersulfate = new Material.Builder(++id, gcylId("potassium_persulfate"))
                .color((Potassium.getMaterialRGB() + SulfuricAcid.getMaterialRGB()) / 2)
                .iconSet(MaterialIconSet.SAND)
                .flags(DISABLE_DECOMPOSITION)
                .dust()
                .build()
                .setFormula("K2S2O8", true);
        PotassiumBisulfate = new Material.Builder(++id, gcylId("potassium_bisulfate"))
                .color(PotassiumPersulfate.getMaterialRGB() + 10)
                .iconSet(MaterialIconSet.SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .dust()
                .fluid()
                .build()
                .setFormula("KHSO4", true);
        PotassiumAcetate = new Material.Builder(++id, gcylId("potassium_acetate"))
                .color((Potassium.getMaterialRGB() + Acetone.getMaterialRGB()) / 2)
                .iconSet(MaterialIconSet.FLINT)
                .flags(DISABLE_DECOMPOSITION)
                .dust()
                .build()
                .setFormula("KCH3CO2", true);
        SodiumMethoxide = new Material.Builder(++id, gcylId("sodium_methoxide"))
                .color((Sodium.getMaterialRGB() + Methanol.getMaterialRGB()) / 2)
                .iconSet(MaterialIconSet.FLUID)
                .flags(DISABLE_DECOMPOSITION)
                .dust()
                .build()
                .setFormula("NaCH3O", true);
        TrimethylBorate = new Material.Builder(++id, gcylId("trimethyl_borate"))
                .color((Boron.getMaterialRGB() + Methanol.getMaterialRGB()) / 2)
                .iconSet(MaterialIconSet.FLUID)
                .flags(DISABLE_DECOMPOSITION)
                .fluid()
                .build()
                .setFormula("(CH3)3BO3", true);
        MineralOil = new Material.Builder(++id, gcylId("mineral_oil"))
                .color(0xd9ca9a)
                .iconSet(MaterialIconSet.DULL)
                .flags(DISABLE_DECOMPOSITION)
                .fluid()
                .build()
                .setFormula("???", true);

        FerrousChloride = new Material.Builder(++id, gcylId("ferrous_chloride"))
                .color(Iron3Chloride.getMaterialRGB() + 20)
                .iconSet(MaterialIconSet.SHINY)
                .components(Iron, 1, Chlorine, 2)
                .fluid()
                .build();
        Dimethylglyoxime = new Material.Builder(++id, gcylId("dimethylglyoxime"))
                .color(0xfff8f7)
                .iconSet(MaterialIconSet.FLUID)
                .flags(DISABLE_DECOMPOSITION)
                .dust()
                .build()
                .setFormula("C4H8N2O2", true);
        Butanone = new Material.Builder(++id, gcylId("butanone"))
                .color(0xf7f6f5)
                .iconSet(MaterialIconSet.FLUID)
                .flags(DISABLE_DECOMPOSITION)
                .fluid()
                .build()
                .setFormula("C4H8O", true);

        EthylNitrite = new Material.Builder(++id, gcylId("ethyl_nitrite"))
                .color((Ethanol.getMaterialRGB() + NitricAcid.getMaterialRGB()) / 2)
                .iconSet(MaterialIconSet.FLUID)
                .flags(DISABLE_DECOMPOSITION)
                .fluid()
                .build()
                .setFormula("C2H5NO2", true);
        DiacetylMonoxime = new Material.Builder(++id, gcylId("diacetyl_monoxime"))
                .color((Butanone.getMaterialRGB() + EthylNitrite.getMaterialRGB()) / 2)
                .iconSet(MaterialIconSet.FLUID)
                .flags(DISABLE_DECOMPOSITION)
                .dust()
                .build()
                .setFormula("C4H7NO2", true);
        AmmoniumNitrite = new Material.Builder(++id, gcylId("ammonium_nitrite"))
                .color((Ammonia.getMaterialRGB() + NitricAcid.getMaterialRGB()) / 2)
                .iconSet(MaterialIconSet.FLUID)
                .flags(DISABLE_DECOMPOSITION)
                .fluid()
                .build()
                .setFormula("NH4NO2", true);


        PotassiumNitrate = new Material.Builder(++id, gcylId("potassium_nitrate"))
                .color((Potassium.getMaterialRGB() + NitricAcid.getMaterialRGB()) / 2)
                .iconSet(MaterialIconSet.FLUID)
                .flags(DISABLE_DECOMPOSITION)
                .fluid()
                .build()
                .setFormula("KNO3", true);

        LeadOxide = new Material.Builder(++id, gcylId("lead_oxide"))
                .color((Lead.getMaterialRGB() + Oxygen.getMaterialRGB()) / 2)
                .iconSet(MaterialIconSet.SHINY)
                //.flags(DISABLE_DECOMPOSITION)
                .components(Lead, 1, Oxygen, 1)
                .dust()
                .build()
                .setFormula("PbO", true);
        ZincBronze = new Material.Builder(++id, gcylId("zinc_bronze"))
                .color(Bronze.getMaterialRGB() + 10)
                .iconSet(MaterialIconSet.SHINY)
                .flags(GENERATE_PLATE)
                .ingot()
                .build()
                .setFormula("(CuSn3)Zn", true);
        PotassiumChlorideSolution = new Material.Builder(++id, gcylId("potassium_chloride_solution"))
                .color((Potassium.getMaterialRGB() + Chlorine.getMaterialRGB()) / 2)
                .iconSet(MaterialIconSet.FLUID)
                .flags(DISABLE_DECOMPOSITION)
                .fluid()
                .build()
                .setFormula("(KCl)(H2O)", true);

        GoldChloride = new Material.Builder(++id, gcylId("gold_chloride"))
                .color((Gold.getMaterialRGB() + Chlorine.getMaterialRGB()) / 2)
                .iconSet(MaterialIconSet.FLUID)
                .flags(DISABLE_DECOMPOSITION)
                .fluid()
                .build()
                .setFormula("AuCl3", true);




    }
}
