package com.fulltrix.tjfcore;

//import com.fulltrix.tjfcore.materials.IsotopeMaterial;
//import com.fulltrix.tjfcore.materials.RadioactiveMaterial;
import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.Elements;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialFlag;
import gregtech.api.unification.material.materials.*;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.integration.crafttweaker.material.MaterialPropertyExpansion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.fulltrix.tjfcore.TJFUtility.tjfId;
import static gregtech.api.fluids.attribute.FluidAttributes.*;
import static gregtech.api.unification.Element.*;
import static gregtech.api.unification.Elements.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtech.api.unification.material.info.MaterialIconSet.*;
import static gregtech.integration.crafttweaker.material.MaterialPropertyExpansion.*;
import static gregicality.multiblocks.api.unification.GCYMMaterials.*;
import static gregicality.multiblocks.api.unification.GCYMMaterialFlags.*;
import static org.codehaus.groovy.runtime.InvokerHelper.asList;

public class TJFMaterials {

    public static final MaterialFlag GENERATE_NUCLEAR_COMPOUND = new MaterialFlag.Builder("generate_nuclear_compound").build();

    public static final MaterialFlag GENERATE_ISOTOPES_COMPOUND = new MaterialFlag.Builder("generate_isotopes_compound").build();

    public static final MaterialFlag DISABLE_REPLICATION = new MaterialFlag.Builder("disable_replication").build();
    public static final List<MaterialFlag> CORE_METAL = new ArrayList<>();

    static {
        CORE_METAL.addAll(EXT2_METAL);
        CORE_METAL.addAll(Arrays.asList(GENERATE_RING, GENERATE_FRAME, GENERATE_ROTOR, GENERATE_SMALL_GEAR, GENERATE_DENSE, GENERATE_FINE_WIRE));
    }

    public static int id = 24000;

    //NUCLEAR_MARK
    public static Material Americium241;

    public static Material Plutonium;

    /////////////////////////////////////////
    public static Material Pikyonium;

    public static Material Inconel792;

    public static Material HDCS;

    public static Material EglinSteel;

    public static Material EglinSteelBase;

    public static Material LithiumNiobate;

    public static Material Polyetheretherketone;

    public static Material LeadZirconateTitanate;

    public static Material LithiumTitanate;

    public static Material CarbonNanotubes;

    public static Material SodiumSeaborgate;

    public static Material TBCCODust;

    public static Material EnrichedNaquadahAlloy;

    public static Material FullereneDopedNanotubes;

    public static Material LithiumNiobateNanoparticles;

    public static Material ZirconiumTetrafluoride;

    public static Material BariumDifluoride;

    public static Material LanthanumTrifluoride;

    public static Material AluminiumTrifluoride;

    public static Material SodiumFluoride;

    public static Material ZBLANDust;

    public static Material ErbiumTrifluoride;

    public static Material ErbiumDopedZBLANDust;

    public static Material TungstenTitaniumCarbide;

    public static Material Prasiolite;
    public static Material BismuthTellurite;
    public static Material CubicZirconia;
    public static Material MagnetoResonatic;
    public static Material Jasper;

    public static Material SiliconCarbide;
    public static Material Quantum;

    public static Material Adamantium;

    public static Material Cinobite;

    public static Material Polyimide;

    public static Material GermaniumTungstenNitride;

    public static Material BariumTitanate;

    public static Material PEDOT;

    public static Material AluminiumComplex;

    public static Material CopperGalliumIndiumMix;
    public static Material CopperGalliumIndiumSelenide;

    public static Material BismuthRuthenate;

    public static Material BismuthIridiate;

    public static Material RutheniumDioxide;

    public static Material LanthanumCalciumManganate;

    public static Material Polystyrene;
    public static Material Shewanella;

    public static Material GreenAlgae;

    public static Material RedAlgae;

    public static Material BrownAlgae;

    public static Material DryRedAlgae;

    public static Material RedAlgaePowder;

    public static Material PreFreezeAgar;

    public static Material FrozenAgarCrystals;

    public static Material WaterAgarMix;

    public static Material BacterialGrowthMedium;

    public static Material DepletedGrowthMedium;

    public static Material Hydrazine;

    public static Material RP1;

    public static Material TributylPhosphate;

    public static Material BoricAcid;

    public static Material FluoroBoricAcid;

    public static Material Acetylene;

    public static Material CoAcABCatalyst;

    public static Material SodiumNitrate;

    public static Material SodiumNitrateSolution;

    public static Material SodiumNitrite;

    public static Material Aniline;

    public static Material BenzenediazoniumTetrafluoroborate;

    public static Material BoronFluoride;

    public static Material FluoroBenzene;

    public static Material Fluorotoluene;

    public static Material ZnFeAlClCatalyst;

    public static Material Difluorobenzophenone;

    public static Material Hydroquinone;

    public static Material Resorcinol;

    public static Material MgClBromide;

    public static Material SiliconFluoride;
    public static Material FluorosilicicAcid;
    public static Material AmmoniumFluoride;
    public static Material AmmoniumBifluoride;
    public static Material AmmoniumBifluorideSolution;
    public static Material AmmoniumVanadate;
    public static Material SodiumVanadate;
    public static Material PureSodiumVanadate;
    public static Material SodiumOxide;
    public static Material YttriumOxide;
    public static Material LutetiumOxide;
    public static Material ThuliumOxide;
    public static Material LuTmYChlorideSolution;

    public static Material SodiumMetavanadate;
    public static Material Urea;
    public static Material LuTmYVOPrecipitate;
    public static Material Ethanol100;
    public static Material LuTmYVONanoparticles;
    public static Material AmmoniumCarbonate;
    public static Material SodiumHydride;
    public static Material CalciumHydroxide;
    public static Material MagnesiumFluoride;
    public static Material TantalumOxide;
    public static Material DielectricMirrorFormationMix;
    public static Material NiobiumChloride;

    public static Material LithiumAluminiumHydride;
    public static Material LithiumHydride;
    public static Material AluminiumHydride;
    public static Material HydrogenPeroxide;
    public static Material NiobiumHydroxide;
    public static Material OxalicAcid;
    public static Material AmmoniumNiobiumOxalateSolution;
    public static Material IndiumPhospide;
    public static Material AmmoniumNitrate;
    public static Material AmmoniumSulfate;
    public static Material Ethanolamine;
    public static Material Ethylenediamine;
    public static Material Formaldehyde;
    public static Material SodiumCyanide;
    public static Material EDTASolution;
    public static Material EDTA;
    public static Material Glycine;
    public static Material CaesiumHydroxide;
    public static Material CaesiumBromideSolution;
    public static Material Sarcosine;
    public static Material PraseodymiumOxide;
    public static Material HolmiumOxide;
    public static Material NeodymiumOxide;
    public static Material PrYHoNitrateSolution;
    public static Material CetaneTrimethylAmmoniumBromide;
    public static Material PrHoYLFNanoparticles;
    public static Material BerylliumFluoride;
    public static Material ChlorinatedSolvents;
    public static Material Dichloromethane;
    public static Material CarbonTetrachloride;
    public static Material Butanol;
    public static Material ButanolGas;
    public static Material Tributylamine;
    public static Material Alumina;
    public static Material AluminiumNitrate;
    public static Material CrudeAluminaSolution;
    public static Material HydrogenCyanide;
    public static Material PotassiumPermanganate;
    public static Material ManganeseSulfate;
    public static Material PotassiumSulfate;
    public static Material AmmoniumCyanate;
    public static Material PotassiumManganate;
    public static Material PotassiumHydroxide;

    public static Material LithiumFluoride;

    public static Material AluminaSolution;
    //COILS
    public static Material Titan_Steel;

    public static Material Black_Titanium;

    public static Material Cosmic_Neutronium;

    public static Material Infinity;

    public static Material Eternity;

    ///////////////////////////////////////////

    public static void register() {

        //NUCLEAR_MARK
        Plutonium = new Material.Builder(id++, tjfId("plutonium_generic"))
                .ingot(3)
                .color(0xF03232)
                .iconSet(METALLIC)
                .flags(EXT_METAL)
                .element(Elements.Pu)
                .build();


        Americium241 = new Material.Builder(id++, tjfId("americium_241"))
                .ingot()
                .color(13158600)
                .iconSet(METALLIC)
                .flags(STD_METAL)
                .build()
                .setFormula("Am_241", true);

        //////////////////////

        EnrichedNaquadahAlloy = new Material.Builder(id++, tjfId("enriched_naquadah_alloy"))
                .ingot(2)
                .fluid()
                .color(0x403f3d)
                .iconSet(SHINY)
                .flags(EXT2_METAL, DISABLE_DECOMPOSITION, GENERATE_FRAME, GENERATE_SMALL_GEAR, GENERATE_ROUND, GENERATE_RING, GENERATE_ROTOR)
                .components(NaquadahEnriched, 4, Rhodium, 2, Ruthenium, 2, Dubnium, 1, Rubidium, 2, Einsteinium, 1)
                .blast(10000)
                .build();

        Inconel792 = new Material.Builder(id++, tjfId("inconel_b"))
                .ingot(5)
                .color(0x6CF076)
                .iconSet(SHINY)
                .flags(CORE_METAL)
                .components(Nickel, 2, Niobium, 1, Aluminium, 2, Nichrome, 1)
                .blast(6200)
                .build();

        EglinSteelBase = new Material.Builder(id++, tjfId("eglin_steel_base"))
                .dust(6)
                .color(0x8B4513)
                .iconSet(SAND)
                .components(Iron, 4, Kanthal, 1, Invar, 5)
                .build();

        EglinSteel = new Material.Builder(id++, tjfId("eglin_steel"))
                .ingot(6)
                .color(0x8B4513)
                .iconSet(METALLIC)
                .flags(EXT2_METAL, GENERATE_FRAME)
                .components(EglinSteelBase, 10, Sulfur, 1, Silicon, 1, Carbon, 1)
                .blast(1048)
                .build();

        Pikyonium = new Material.Builder(id++, tjfId("pikyonium"))
                .ingot(7)
                .color(0x3467BA)
                .flags(CORE_METAL)
                .iconSet(SHINY)
                .components(Inconel792, 8, EglinSteel, 5, NaquadahEnriched, 4, Cerium, 3, Antimony, 2, Platinum, 2, Ytterbium, 1, TungstenSteel, 4)
                .blast(11500)
                .build();

        LithiumNiobate = new Material.Builder(id++, tjfId("lithium_niobate"))
                .ingot()
                .color(0xcfcf3a)
                .iconSet(SHINY)
                .flags(GENERATE_PLATE)
                .components(Lithium, 1, Niobium, 1, Oxygen, 4)
                .blast(6700)
                .build();

        Polyetheretherketone = new Material.Builder(id++, tjfId("polyetheretherketone"))
                .polymer(2).fluid()
                .color(0x403e37)
                .iconSet(DULL)
                .flags(NO_SMASHING, GENERATE_FOIL, GENERATE_FINE_WIRE, NO_ALLOY_BLAST_RECIPES)
                .components(Carbon, 20, Hydrogen, 12, Oxygen, 3)
                .blast(6700)
                .build();

        LeadZirconateTitanate = new Material.Builder(id++, tjfId("lead_zirconate_titanate"))
                .gem(3)
                .color(0x359ade)
                .iconSet(OPAL)
                .flags(GENERATE_PLATE)
                .components(Lead, 1, Zirconium, 1, Titanium, 1, Oxygen, 3)
                .build();

        LithiumTitanate = new Material.Builder(id++, tjfId("lithium_titanate"))
                .ingot(5)
                .color(0xfe71a9)
                .iconSet(SHINY)
                .flags(CORE_METAL, GENERATE_PLATE)
                .components(Lithium, 2, Titanium, 1, Oxygen, 3)
                .build();

        CarbonNanotubes = new Material.Builder(id++, tjfId("carbon_nanotubes"))
                .ingot()
                .color(0x2c2c2c)
                .iconSet(SHINY)
                .flags(EXCLUDE_BLOCK_CRAFTING_RECIPES, GENERATE_FOIL, DISABLE_DECOMPOSITION, GENERATE_FINE_WIRE)
                .components(Carbon, 1)
                .build();

        SodiumSeaborgate = new Material.Builder(id++, tjfId("sodium_seaborgate"))
                .ingot()
                .color(0x55bbd4)
                .iconSet(SHINY)
                .components(Sodium, 2, Seaborgium, 1, Oxygen, 4)
                .build();

        TBCCODust = new Material.Builder(id++, tjfId("TBCCODust"))
                .dust()
                .color(0x669900)
                .iconSet(SHINY)
                .components(Thallium, 1, Barium, 2, Calcium, 2, Copper, 3, Oxygen, 10)
                .build();

        FullereneDopedNanotubes = new Material.Builder(id++, tjfId("fullerene_doped_nanotubes"))
                .fluid()
                .color(0x6c2c6c)
                .components()
                .build()
                .setFormula("C60CNT",true);

        LithiumNiobateNanoparticles = new Material.Builder(id++, tjfId("lithium_niobate_nanoparticles"))
                .dust()
                .color(LithiumNiobate.getMaterialRGB()-10)
                .iconSet(SHINY)
                .build()
                .setFormula("LiNbO4",true);

        ZirconiumTetrafluoride = new Material.Builder(id++, tjfId("zirconium_tetrafluoride"))
                .dust()
                .color((Zirconium.getMaterialRGB()+Fluorine.getMaterialRGB())/2)
                .iconSet(SHINY)
                .build()
                .setFormula("ZrF4", true);

        BariumDifluoride = new Material.Builder(id++, tjfId("barium_difluoride"))
                .dust()
                .color((Barium.getMaterialRGB()+Fluorine.getMaterialRGB())/2)
                .iconSet(METALLIC)
                .build()
                .setFormula("BaF2", true);

        LanthanumTrifluoride = new Material.Builder(id++, tjfId("lanthanum_trifluoride"))
                .dust()
                .color((Lanthanum.getMaterialRGB()+Fluorine.getMaterialRGB())/2)
                .iconSet(METALLIC)
                .build()
                .setFormula("LaF3", true);

        AluminiumTrifluoride = new Material.Builder(id++, tjfId("aluminium_trifluoride"))
                .dust()
                .color((Aluminium.getMaterialRGB()+Fluorine.getMaterialRGB())/2)
                .iconSet(ROUGH)
                .build()
                .setFormula("AlF3", true);

        ErbiumTrifluoride = new Material.Builder(id++, tjfId("erbium_trifluoride"))
                .dust()
                .color((Erbium.getMaterialRGB()+Fluorine.getMaterialRGB())/2)
                .iconSet(ROUGH)
                .build()
                .setFormula("ErF3", true);

        ZBLANDust = new Material.Builder(id++, tjfId("zblan_dust"))
                .dust()
                .color((ZirconiumTetrafluoride.getMaterialRGB()+BariumDifluoride.getMaterialRGB()+LanthanumTrifluoride.getMaterialRGB()+AluminiumTrifluoride.getMaterialRGB())/4)
                .iconSet(SHINY)
                .build()
                .setFormula("(ZrF4)18(BaF2)7(LaF3)2(AlF3)(NaF)7", true);

        ErbiumDopedZBLANDust = new Material.Builder(id++, tjfId("erbium_doped_zblan_dust"))
                .dust()
                .color((ZBLANDust.getMaterialRGB()+ErbiumTrifluoride.getMaterialRGB())/2)
                .iconSet(SHINY)
                .build()
                .setFormula("(ErF3)(ZrF4)18(BaF2)7(LaF3)2(AlF3)(NaF)7", true);

        SodiumFluoride = new Material.Builder(id++, tjfId("sodium_fluoride"))
                .dust(2)
                .color((Sodium.getMaterialRGB() + Fluorine.getMaterialRGB()) / 2)
                .iconSet(DULL)
                .components(Sodium, 1, Fluorine, 1)
                .build();

        TungstenTitaniumCarbide = new Material.Builder(id++, tjfId("tungsten_titanium_carbide"))
                .ingot(7)
                .color(0x800d0d)
                .iconSet(SHINY)
                .flags(CORE_METAL, DISABLE_DECOMPOSITION)
                .components(TungstenCarbide, 7, Titanium, 3)
                .blast(4422)
                .build();



        Prasiolite = new Material.Builder(id++, tjfId("prasiolite"))
                .gem(2).ore()
                .color(0x9EB749)
                .iconSet(QUARTZ)
                .flags(GENERATE_LENS)
                .components(Silicon, 5, Oxygen, 10, Iron, 1)
                .build();

        BismuthTellurite = new Material.Builder(id++, tjfId("bismuth_tellurite"))
                .dust(2)
                .color(0x006B38)
                .iconSet(SAND)
                .components(Bismuth, 2, Tellurium, 3)
                .build();

        CubicZirconia = new Material.Builder(id++, tjfId("cubic_zirconia"))
                .gem(6)
                .color(0xFFDFE2)
                .iconSet(DIAMOND)
                .flags(NO_SMELTING, GENERATE_LENS)
                .components(Zirconium, 1, Oxygen, 2)
                .build();

        MagnetoResonatic = new Material.Builder(id++, tjfId("magneto_resonatic"))
                .gem(2)
                .color(0xFF97FF)
                .iconSet(MAGNETIC)
                .flags(DISABLE_DECOMPOSITION, FLAMMABLE, HIGH_SIFTER_OUTPUT, NO_SMELTING, GENERATE_LENS)
                .components(Prasiolite, 3, BismuthTellurite, 6, CubicZirconia, 1, SteelMagnetic, 1)
                .build();

        HDCS = new Material.Builder(id++, tjfId("hdcs"))
                .ingot(5)
                .color(0x334433)
                .iconSet(SHINY)
                .flags(CORE_METAL, DISABLE_DECOMPOSITION)
                .components(TungstenSteel, 12, HSSS, 9, HSSG, 6, Ruridit, 3, MagnetoResonatic, 2, Plutonium, 1)
                .blast(6100)
                .build();


        Jasper = new Material.Builder(id++, tjfId("jasper"))
                .gem(2)
                .color(13127760)
                .iconSet(EMERALD)
                .flags(NO_SMELTING, HIGH_SIFTER_OUTPUT)
                .build();



        SiliconCarbide = new Material.Builder(id++, tjfId("silicon_carbide"))
                .dust()
                .color((Silicon.getMaterialRGB() + Carbon.getMaterialRGB()) / 2)
                .iconSet(SHINY)
                .build()
                .setFormula("SiC", true);

        Quantum = new Material.Builder(id++, tjfId("quantum"))
                .ingot(7)
                .color(0x0f0f0f)
                .iconSet(SHINY)
                .flags(CORE_METAL, DISABLE_DECOMPOSITION)
                .components(Stellite100, 15, Jasper, 5, Gallium, 5, Americium241, 5, Palladium, 5, Bismuth, 5, Germanium, 5, SiliconCarbide, 5)
                .blast(11400)
                .build();

        Adamantium = new Material.Builder(id++, tjfId("adamantium"))
                .ingot(7).fluid()
                .color(0x2d365c)
                .iconSet(SHINY)
                .flags(CORE_METAL)
                .element(TJFElements.Ad)
                .blast(10850)
                .build();

        Cinobite = new Material.Builder(id++, tjfId("cinobite"))
                .ingot(5).fluid()
                .color(0x010101)
                .iconSet(SHINY)
                .flags(CORE_METAL, DISABLE_DECOMPOSITION)
                .components(Zeron100, 8, Naquadria, 4, Gadolinium, 3, Aluminium, 2, Mercury, 1, Tin, 1, Titanium, 6, Osmiridium, 1)
                .blast(11465)
                .build();


        Polyimide = new Material.Builder(id++, tjfId("polyimide"))
                .ingot(1).fluid()
                .color(0xFF7F50)
                .iconSet(DULL)
                .flags(DISABLE_DECOMPOSITION, GENERATE_PLATE, FLAMMABLE, NO_SMASHING)
                .components(Carbon, 22, Hydrogen, 12, Nitrogen, 2, Oxygen, 6)
                .build();

        GermaniumTungstenNitride = new Material.Builder(id++, tjfId("germanium_tungsten_nitride"))
                .ingot(2)
                .color(0x8f8fcf)
                .iconSet(DULL)
                .flags(GENERATE_PLATE)
                .components(Germanium, 3, Tungsten, 3, Nitrogen, 10)
                .blast(5400)
                .build();

        BariumTitanate = new Material.Builder(id++, tjfId("barium_titanate"))
                .ingot(2)
                .color(0x99FF99)
                .iconSet(SHINY)
                .flags(GENERATE_FOIL, DISABLE_DECOMPOSITION)
                .components(Barium, 1, Titanium, 1, Oxygen, 3)
                .build();

        PEDOT = new Material.Builder(id++, tjfId("pedot"))
                .ingot(22)
                .color(0x5cef20)
                .iconSet(DULL)
                .flags(GENERATE_FINE_WIRE, DISABLE_DECOMPOSITION)
                .components(Carbon, 6, Hydrogen, 6, Oxygen, 2, Sulfur, 1)
                .build();

        AluminiumComplex = new Material.Builder(id++, tjfId("aluminium_complex"))
                .dust()
                .color(0x3f5a9f)
                .iconSet(SHINY)
                .build()
                .setFormula("AlC9H7NO", true);

        CopperGalliumIndiumMix = new Material.Builder(id++, tjfId("copper_gallium_indium_mix"))
                .dust()
                .color((Indium.getMaterialRGB() + Copper.getMaterialRGB() + Gallium.getMaterialRGB()) / 3)
                .iconSet(ROUGH)
                .build()
                .setFormula("CuGaIn", true);

        CopperGalliumIndiumSelenide = new Material.Builder(id++, tjfId("copper_gallium_indium_selenide"))
                .dust()
                .color((CopperGalliumIndiumMix.getMaterialRGB() + Selenium.getMaterialRGB()) / 2)
                .iconSet(SHINY)
                .build()
                .setFormula("CuGaInSe2", true);

        BismuthRuthenate = new Material.Builder(id++, tjfId("bismuth_ruthenate"))
                .ingot(2)
                .color(0x94cf5c)
                .iconSet(DULL)
                .flags(GENERATE_PLATE, DISABLE_DECOMPOSITION)
                .components(Bismuth, 2, Ruthenium, 2, Oxygen, 7)
                .build();

        BismuthIridiate = new Material.Builder(id++, tjfId("bismuth_iridiate"))
                .ingot(22)
                .color(0x478a6b)
                .iconSet(DULL)
                .flags(GENERATE_PLATE, DISABLE_DECOMPOSITION)
                .components(Bismuth, 2, Iridium, 2, Oxygen, 7)
                .build();

        RutheniumDioxide = new Material.Builder(id++, tjfId("ruthenium_dioxide"))
                .ingot(2)
                .color(RutheniumTetroxide.getMaterialRGB())
                .iconSet(DULL)
                .flags(GENERATE_PLATE, DISABLE_DECOMPOSITION)
                .components(Ruthenium, 1, Oxygen, 2)
                .build();

        LanthanumCalciumManganate = new Material.Builder(id++, tjfId("lanthanum_gallium_manganate"))
                .dust()
                .color(0x8aa07b)
                .iconSet(SHINY)
                .build()
                .setFormula("LaCaMnO3", true);

        Polystyrene = new Material.Builder(id++, tjfId("polystyrene"))
                .polymer(1).fluid()
                .color(8945785)
                .iconSet(DULL)
                .flags(GENERATE_FOIL, DISABLE_DECOMPOSITION)
                .components(Carbon, 8, Hydrogen, 8)
                .build();

        Shewanella = new Material.Builder(id++, tjfId("shewanella"))
                .dust()
                .color(0x8752ab)
                .iconSet(METALLIC)
                .build()
                .setFormula("Bacteria", true);

        GreenAlgae = new Material.Builder(id++, tjfId("green_algae"))
                .dust()
                .color(0x228b22)
                .iconSet(METALLIC)
                .build()
                .setFormula("An Algae", true);

        BrownAlgae = new Material.Builder(id++, tjfId("brown_algae"))
                .dust()
                .color(0xa52a2a)
                .iconSet(METALLIC)
                .build()
                .setFormula("An Algae", true);

        RedAlgae = new Material.Builder(id++, tjfId("red_algae"))
                .dust()
                .color(0xf08080)
                .iconSet(METALLIC)
                .build()
                .setFormula("An Algae", true);

        DryRedAlgae = new Material.Builder(id++, tjfId("dry_red_algae"))
                .dust()
                .color(0xff7f50)
                .iconSet(ROUGH)
                .build()
                .setFormula("A Dry Algae", true);

        RedAlgaePowder = new Material.Builder(id++, tjfId("red_algae_powder"))
                .dust()
                .color(0xcc2f2f)
                .iconSet(ROUGH)
                .build()
                .setFormula("A Powdered Algae", true);

        PreFreezeAgar = new Material.Builder(id++, tjfId("pre_freeze_agar"))
                .dust()
                .color(0x132b0d)
                .iconSet(ROUGH)
                .build()
                .setFormula("Warm Agar", true);

        FrozenAgarCrystals = new Material.Builder(id++, tjfId("frozen_agar_crystals"))
                .dust()
                .color(0x68db4b)
                .iconSet(SHINY)
                .build()
                .setFormula("Cold Agar", true);

        WaterAgarMix = new Material.Builder(id++, tjfId("water_agar_mix"))
                .fluid()
                .color(0x48dbbe)
                .build()
                .setFormula("H2O?", true);

        BacterialGrowthMedium = new Material.Builder(id++, tjfId("bacterial_growth_medium"))
                .fluid()
                .color(0x0b2e12)
                .build()
                .setFormula("For Bacteria", true);

        DepletedGrowthMedium = new Material.Builder(id++, tjfId("depleted_growth_medium"))
                .fluid()
                .color(0x071209)
                .build()
                .setFormula("Depleted", true);


        BoricAcid = new Material.Builder(id++, tjfId("boric_acid"))
                .fluid()
                .color(0xD5D2D7)
                .iconSet(FLUID)
                .build()
                .setFormula("H3BO3", true);


        FluoroBoricAcid = new Material.Builder(id++, tjfId("fluoroboric_acid"))
                .fluid()
                .color(0xD5D2D7)
                .iconSet(FLUID)
                .build()
                .setFormula("HBF4", true);

        Acetylene = new Material.Builder(id++, tjfId("acetylene"))
                .fluid()
                .color(0x959c60)
                .iconSet(FLUID)
                .build()
                .setFormula("C2H2", true);

        CoAcABCatalyst = new Material.Builder(id++, tjfId("coacab_catalyst"))
                .dust()
                .color(0x755f30)
                .iconSet(FINE)
                .build()
                .setFormula("Co/AC-AB", true);

        SodiumNitrate = new Material.Builder(id++, tjfId("sodium_nitrate"))
                .dust()
                .color(0x846684)
                .iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Sodium, 1, Nitrogen, 1, Oxygen, 3)
                .build();

        SodiumNitrateSolution = new Material.Builder(id++, tjfId("sodium_nitrate_solution"))
                .fluid()
                .color(0xA09ED7)
                .iconSet(FLUID)
                .build()
                .setFormula("H20NaNO3", true);

        SodiumNitrite = new Material.Builder(id++, tjfId("sodium_nitrite"))
                .dust()
                .color((Sodium.getMaterialRGB()+Nitrogen.getMaterialRGB())/2)
                .iconSet(ROUGH)
                .build()
                .setFormula("NaNO2",true);

        Aniline = new Material.Builder(id++, tjfId("aniline"))
                .fluid()
                .color(0x4c911d)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H5NH2", true);

        BenzenediazoniumTetrafluoroborate = new Material.Builder(id++, tjfId("benzenediazonium_tetrafluoroborate"))
                .fluid()
                .color(0xD5D2D7)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H5BF4N2",true);

        BoronFluoride = new Material.Builder(id++, tjfId("boron_fluoride"))
                .fluid()
                .color(0xD5D2D7)
                .iconSet(FLUID)
                .build()
                .setFormula("BF3",true);

        FluoroBenzene = new Material.Builder(id++, tjfId("fluoro_benzene"))
                .fluid()
                .color(0xD5D2D7)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H5F", true);

        Fluorotoluene = new Material.Builder(id++, tjfId("fluorotoluene"))
                        .fluid()
                        .color(0xE0DA99)
                        .iconSet(FLUID)
                        .build()
                .setFormula("C7H7F", true);

        ZnFeAlClCatalyst = new Material.Builder(id++, tjfId("znfealcl_catalyst"))
                .dust()
                .color((Zinc.getMaterialRGB()+Iron.getMaterialRGB()+Aluminium.getMaterialRGB()+Chlorine.getMaterialRGB())/4)
                .iconSet(METALLIC)
                .build()
                .setFormula("ZnFeAlCl", true);

        Difluorobenzophenone= new Material.Builder(id++, tjfId("difluorobenzophenone"))
                        .dust()
                        .color((FluoroBenzene.getMaterialRGB()+Fluorotoluene.getMaterialRGB())/2)
                        .iconSet(SHINY)
                        .build()
                        .setFormula("(FC6H4)2CO", true);

        Hydroquinone = new Material.Builder(id++, tjfId("hydroquinone"))
                .fluid()
                .color((Oxygen.getMaterialRGB()+Propene.getMaterialRGB())/2)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H4(OH)2", true);

        Resorcinol = new Material.Builder(id++, tjfId("resorcinol"))
                .fluid()
                .color(0xD5DDBE)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H6O2", true);

        MgClBromide = new Material.Builder(id++, tjfId("mgcl_bromide"))
                .dust()
                .color((MagnesiumChloride.getMaterialRGB()+Bromine.getMaterialRGB())/2)
                .iconSet(ROUGH)
                .build()
                .setFormula("MgClBr", true);

        SiliconFluoride = new Material.Builder(id++, tjfId("silicon_fluoride"))
                .fluid()
                .color(0xB2B4B4)
                .iconSet(FLUID)
                .build()
                .setFormula("SiF4",true);

        FluorosilicicAcid = new Material.Builder(id++, tjfId("fluorosilicic_acid"))
                .fluid()
                .color(0x2ccf2a)
                .iconSet(FLUID)
                .build()
                .setFormula("H2SiF6",true);

        AmmoniumFluoride = new Material.Builder(id++, tjfId("ammonium_fluoride"))
                .fluid()
                .color(AmmoniumChloride.getMaterialRGB())
                .iconSet(FLUID)
                .build()
                .setFormula("NH4F",true);

        AmmoniumBifluoride = new Material.Builder(id++, tjfId("ammonium_bifluoride"))
                .dust()
                .color(0x20cfcf)
                .iconSet(ROUGH)
                .build()
                .setFormula("NH4HF2", true);

        AmmoniumBifluorideSolution = new Material.Builder(id++, tjfId("ammonium_bifluoride_solution"))
                .fluid()
                .color((Ammonia.getMaterialRGB()+Fluorine.getMaterialRGB())/2)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)NH4FHF",true);

        AmmoniumVanadate = new Material.Builder(id++, tjfId("ammonium_vanadate"))
                .dust()
                .color(0xf2ff1c)
                .iconSet(FINE)
                .build()
                .setFormula("NH4VO3", true);

        SodiumOxide = new Material.Builder(id++, tjfId("sodium_oxide"))
                .dust()
                .color(0x0373fc)
                .iconSet(SHINY)
                .build()
                .setFormula("Na2O",true);

        SodiumVanadate = new Material.Builder(id++, tjfId("sodium_vanadate"))
                .dust()
                .color(0xf2df1d)
                .iconSet(ROUGH)
                .build()
                .setFormula("Na3VO4",true);

        PureSodiumVanadate = new Material.Builder(id++, tjfId("pure_sodium_vanadate"))
                .dust()
                .color(SodiumVanadate.getMaterialRGB())
                .iconSet(SHINY)
                .build()
                .setFormula("Na3VO4",true);

        YttriumOxide = new Material.Builder(id++, tjfId("yttrium_oxide"))
                .dust()
                .color(0xC6EBB3)
                .iconSet(SAND)
                .components(Yttrium, 2, Oxygen, 3)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        LutetiumOxide = new Material.Builder(id++, tjfId("lutetium_oxide"))
                .dust()
                .color(Lutetium.getMaterialRGB())
                .iconSet(Lutetium.getMaterialIconSet())
                .build()
                .setFormula("Lu2O3",true);

        ThuliumOxide = new Material.Builder(id++, tjfId("thulium_oxide"))
                .dust()
                .color(Thulium.getMaterialRGB())
                .iconSet(Thulium.getMaterialIconSet())
                .build()
                .setFormula("Tm2O3",true);

        LuTmYChlorideSolution = new Material.Builder(id++, tjfId("lutmy_chloride_solution"))
                .fluid()
                .color(0x00f2b2)
                .iconSet(FLUID)
                .build()
                .setFormula("(YCl3)6(LuCl3)2(TmCl3)2(H2O)15",true);

        SodiumMetavanadate = new Material.Builder(id++, tjfId("sodium_metavanadate"))
                .dust()
                .color(SodaAsh.getMaterialRGB())
                .iconSet(FINE)
                .build()
                .setFormula("NaVO3",true);

        Urea = new Material.Builder(id++, tjfId("urea"))
                .dust()
                .color(0x30cf20)
                .iconSet(ROUGH)
                .build()
                .setFormula("CH4N2O",true);

        LuTmYVOPrecipitate = new Material.Builder(id++, tjfId("lutm_yvo_precipitate"))
                .dust()
                .color(0xcf8acf)
                .iconSet(DULL)
                .build()
                .setFormula("Lu/Tm:YVO?",true);

        Ethanol100 = new Material.Builder(id++, tjfId("ethanol_100"))
                .fluid()
                .color(Ethanol.getMaterialRGB())
                .iconSet(FLUID)
                .build()
                .setFormula("C2H5OH",true);

        LuTmYVONanoparticles = new Material.Builder(id++, tjfId("lutm_yvo_nanoparticles"))
                .dust()
                .color(0x206faf)
                .iconSet(SHINY)
                .build()
                .setFormula("Lu/Tm:YVO",true);

        AmmoniumSulfate = new Material.Builder(id++, tjfId("ammonium_sulfate"))
                .fluid()
                .color(0x6464f5)
                .iconSet(FLUID)
                .build()
                .setFormula("(NH4)2SO4",true);

        AmmoniumCarbonate = new Material.Builder(id++, tjfId("ammonium_carbonate"))
                .dust()
                .color(AmmoniumSulfate.getMaterialRGB())
                .iconSet(DULL)
                .build()
                .setFormula("(NH4)2CO3",true);

                SodiumHydride= new Material.Builder(id++, tjfId("sodium_hydride"))
                        .dust()
                        .color(0xcacac8)
                        .iconSet(ROUGH)
                        .build()
                        .setFormula("NaH",true);

        CalciumHydroxide= new Material.Builder(id++, tjfId("calcium_hydroxide"))
                .dust()
                .color(0x5f8764)
                .iconSet(DULL)
                .build()
                .setFormula("Ca(OH)2",true);

                MagnesiumFluoride= new Material.Builder(id++, tjfId("magnesium_fluoride"))
                        .dust()
                        .color(0xcfcfcf)
                        .iconSet(SHINY)
                        .build()
                        .setFormula("MgF2",true);

        TantalumOxide= new Material.Builder(id++, tjfId("tantalum_oxide"))
                .dust()
                .color((Tantalum.getMaterialRGB()+Oxygen.getMaterialRGB())/2)
                .iconSet(SHINY)
                .build()
                .setFormula("Ta2O5",true);

        DielectricMirrorFormationMix = new Material.Builder(id++, tjfId("dielectric_mirror_formation_mix"))
                        .fluid()
                        .color(0xff992c)
                        .iconSet(FLUID)
                        .build()
                        .setFormula("MgF2ZnSTa2Ti(C2H6O8)",true);

        NiobiumChloride= new Material.Builder(id++, tjfId("niobium_chloride"))
                .dust()
                .color(Niobium.getMaterialRGB())
                .iconSet(SHINY)
                .build()
                .setFormula("NbCl5",true);

                LithiumAluminiumHydride= new Material.Builder(id++, tjfId("lithium_aluminium_hydride"))
                        .dust()
                        .color(0xc0defc)
                        .iconSet(ROUGH)
                        .build()
                        .setFormula("LiAlH4",true);

        LithiumHydride= new Material.Builder(id++, tjfId("lithium_hydride"))
                .dust()
                .color((Lithium.getMaterialRGB()+Hydrogen.getMaterialRGB())/2)
                .iconSet(ROUGH)
                .build()
                .setFormula("LiH",true);

                AluminiumHydride= new Material.Builder(id++, tjfId("aluminium_hydride"))
                        .dust()
                        .color(0x0b585c)
                        .iconSet(ROUGH)
                        .build()
                        .setFormula("AlH3",true);

        HydrogenPeroxide= new Material.Builder(id++, tjfId("hydrogen_peroxide"))
                .fluid()
                .color(0xD1FFFF)
                .iconSet(FLUID)
                .components(Hydrogen, 2, Oxygen, 2)
                .flags(DISABLE_DECOMPOSITION)
                .build();

                NiobiumHydroxide= new Material.Builder(id++, tjfId("niobium_hydroxide"))
                        .dust()
                        .color(0x7c7c7c)
                        .iconSet(ROUGH)
                        .build()
                        .setFormula("Nb(OH)5",true);

        OxalicAcid= new Material.Builder(id++, tjfId("oxalic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0x4aaae2)
                .iconSet(FLUID)
                .build()
                .setFormula("HOOCCOOH",true);

                AmmoniumNiobiumOxalateSolution= new Material.Builder(id++, tjfId("ammonium_niobium_oxalate_solution"))
                        .fluid()
                        .color(0x6c6cac)
                        .iconSet(FLUID)
                        .build()
                        .setFormula("(NH4)C10Nb2O20",true);

        IndiumPhospide= new Material.Builder(id++, tjfId("indium_phosphide"))
                .dust()
                .color(0x5c9c9c)
                .iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Indium, 1, Phosphorus, 1)
                .build();

                AmmoniumNitrate= new Material.Builder(id++, tjfId("ammonium_nitrate"))
                        .fluid()
                        .color(Ammonia.getMaterialRGB())
                        .iconSet(FLUID)
                        .build()
                        .setFormula("NH4NO3",true);

        Ethanolamine= new Material.Builder(id++, tjfId("ethanolamine"))
                .fluid()
                .color(0x6f7d87)
                .iconSet(FLUID)
                .build()
                .setFormula("HOCH2CH2NH2",true);

        Ethylenediamine= new Material.Builder(id++, tjfId("ethylenediamine"))
                        .fluid()
                        .color(Ethanolamine.getMaterialRGB())
                        .iconSet(FLUID)
                        .build()
                        .setFormula("C2H4(NH2)2",true);

        Formaldehyde= new Material.Builder(id++, tjfId("formaldehyde"))
                .fluid()
                .color(0x95a13a)
                .iconSet(FLUID)
                .build()
                .setFormula("CH2O",true);

                SodiumCyanide= new Material.Builder(id++, tjfId("sodium_cyanide"))
                        .fluid()
                        .color(0x5f7c8c)
                        .iconSet(FLUID)
                        .build()
                        .setFormula("NaCN",true);
        EDTASolution= new Material.Builder(id++, tjfId("edta_solution"))
                .fluid()
                .color(0x0026d9)
                .iconSet(FLUID)
                .build()
                .setFormula("(C10H16N2O8)3(C2H8N2)O2",true);

                EDTA= new Material.Builder(id++, tjfId("edta"))
                        .fluid()
                        .color(0x0026d9)
                        .iconSet(FLUID)
                        .build()
                        .setFormula("C10H16N2O8",true);
        Glycine = new Material.Builder(id++, tjfId("glycine"))
                .fluid()
                .color((Ethylenediamine.getMaterialRGB()+Formaldehyde.getMaterialRGB())/2)
                .iconSet(FLUID)
                .build()
                .setFormula("NH2CH2COOH",true);

                CaesiumHydroxide= new Material.Builder(id++, tjfId("caesium_hydroxide"))
                        .dust()
                        .color(Caesium.getMaterialRGB()-10)
                        .iconSet(ROUGH)
                        .build()
                        .setFormula("CsOH",true);

        CaesiumBromideSolution= new Material.Builder(id++, tjfId("caesium_bromide_solution"))
                .fluid()
                .color((Caesium.getMaterialRGB()-10+SaltWater.getMaterialRGB())/2)
                .iconSet(FLUID)
                .build()
                .setFormula("CsBr(H2O)",true);

        Sarcosine= new Material.Builder(id++, tjfId("material"))
                .dust()
                .color((Glycine.getMaterialRGB()+Oxygen.getMaterialRGB())/2)
                .iconSet(SHINY)
                .build()
                .setFormula("C3H7NO2",true);

        PraseodymiumOxide = new Material.Builder(id++, tjfId("praseodymium_oxide"))
                .dust()
                .color(Praseodymium.getMaterialRGB())
                .iconSet(Praseodymium.getMaterialIconSet())
                .build()
                .setFormula("Pr2O3",true);

                HolmiumOxide = new Material.Builder(id++, tjfId("holmium_oxide"))
                        .dust()
                        .color(Holmium.getMaterialRGB())
                        .iconSet(Holmium.getMaterialIconSet())
                        .build()
                        .setFormula("Ho2O3",true);

        NeodymiumOxide = new Material.Builder(id++, tjfId("neodymium_oxide"))
                        .dust()
                        .color(Neodymium.getMaterialRGB())
                        .iconSet(Neodymium.getMaterialIconSet())
                        .build()
                        .setFormula("Nd2O3",true);

                PrYHoNitrateSolution = new Material.Builder(id++, tjfId("pryho_nitrate_solution"))
                        .fluid()
                        .color(0x00f2b2)
                        .iconSet(FLUID)
                        .build()
                        .setFormula("(Y(NO3)3)6(Pr(NO3)3)2(Nd(NO3)3)2(H2O)15",true);

        CetaneTrimethylAmmoniumBromide = new Material.Builder(id++, tjfId("cetane_trimethyl_ammonium_bromide"))
                .fluid()
                .color(0xb9c1c9)
                .iconSet(FLUID)
                .build()
                .setFormula("C19H42BrN",true);



                PrHoYLFNanoparticles= new Material.Builder(id++, tjfId("prho_ylf_nanoparticles"))
                        .dust()
                        .color(0xcf8acf)
                        .iconSet(SHINY)
                        .build()
                        .setFormula("Pr/Ho:YLF",true);

        BerylliumFluoride= new Material.Builder(id++, tjfId("beryllium_fluoride"))
                .ingot(2)
                .color(0x757575)
                .iconSet(SHINY)
                .components(Beryllium, 1, Fluorine, 2)
                .build();

                ChlorinatedSolvents = new Material.Builder(id++, tjfId("chlorinated_solvents"))
                        .fluid()
                        .color(0x40804c)
                        .iconSet(FLUID)
                        .build()
                        .setFormula("(CH4)2Cl5",true);

        Dichloromethane= new Material.Builder(id++, tjfId("dichloromethane"))
                .fluid()
                .color(Chloromethane.getMaterialRGB()-10)
                .iconSet(FLUID)
                .build()
                .setFormula("CH2Cl2",true);

                CarbonTetrachloride = new Material.Builder(id++, tjfId("carbon_tetrachloride"))
                        .fluid()
                        .color(0x2d8020)
                        .iconSet(FLUID)
                        .build()
                        .setFormula("CCl4",true);

        Butanol= new Material.Builder(id++, tjfId("butanol"))
                .fluid()
                .color((FermentedBiomass.getMaterialRGB()+20))
                .iconSet(FLUID)
                .build()
                .setFormula("C4H9OH",true);

                ButanolGas = new Material.Builder(id++, tjfId("butanol_gas"))
                        .gas()
                        .color(Butanol.getMaterialRGB()+20)
                        .iconSet(FLUID)
                        .build()
                        .setFormula("C4H9OH",true);

        Tributylamine = new Material.Builder(id++, tjfId("tributylamine"))
                .fluid()
                .color(0x801a80)
                .iconSet(FLUID)
                .build()
                .setFormula("(C4H9)3N",true);

                Alumina= new Material.Builder(id++, tjfId("alumina"))
                        .dust()
                        .color(0x0b585c)
                        .iconSet(ROUGH)
                        .build()
                        .setFormula("Al2O3",true);

        AluminiumNitrate= new Material.Builder(id++, tjfId("aluminium_nitrate"))
                .dust()
                .color(Alumina.getMaterialRGB())
                .iconSet(SHINY)
                .build()
                .setFormula("Al(NO3)3",true);

                CrudeAluminaSolution= new Material.Builder(id++, tjfId("crude_alumina_solution"))
                        .fluid()
                        .color(Aluminium.getMaterialRGB()-30)
                        .iconSet(FLUID)
                        .build()
                        .setFormula("Al(NO3)3)2(CH2Cl2)(C12H27N)",true);

        HydrogenCyanide= new Material.Builder(id++, tjfId("hydrogen_cyanide"))
                .fluid()
                .color(0xb6d1ae)
                .iconSet(FLUID)
                .build()
                .setFormula("HCN",true);

        PotassiumManganate= new Material.Builder(id++, tjfId("potassium_manganate"))
                .dust()
                .color(0xaf20af)
                .iconSet(DULL)
                .build()
                .setFormula("K2MnO4",true);

                PotassiumPermanganate= new Material.Builder(id++, tjfId("potassium_permanganate"))
                        .dust()
                        .color(PotassiumManganate.getMaterialRGB()-15)
                        .iconSet(ROUGH)
                        .build()
                        .setFormula("KMnO4",true);

        ManganeseSulfate= new Material.Builder(id++, tjfId("manganese_sulfate"))
                .dust()
                .color((Manganese.getMaterialRGB()+Sulfur.getMaterialRGB())/2)
                .iconSet(ROUGH)
                .build()
                .setFormula("MnSO4",true);

                PotassiumSulfate= new Material.Builder(id++, tjfId("potassium_sulfate"))
                        .dust()
                        .color((Potassium.getMaterialRGB()+Sulfur.getMaterialRGB())/2)
                        .iconSet(FINE)
                        .build()
                        .setFormula("K2SO4",true);

        AmmoniumCyanate= new Material.Builder(id++, tjfId("ammonium_cyanate"))
                .fluid()
                .color(0x3a5dcf)
                .iconSet(FLUID)
                .build()
                .setFormula("NH4CNO",true);

        PotassiumHydroxide= new Material.Builder(id++, tjfId("potassium_hydroxide"))
                .fluid()
                .color((Potassium.getMaterialRGB()+Hydrogen.getMaterialRGB()+Oxygen.getMaterialRGB())/3)
                .iconSet(FLUID)
                .build()
                .setFormula("KOH",true);

        LithiumFluoride = new Material.Builder(id++, tjfId("lithium_fluoride"))
                .ingot()
                .color(0x757575)
                .iconSet(SHINY)
                .components(Lithium, 1, Fluorine, 1)
                .build();

        AluminaSolution = new Material.Builder(id++, tjfId("alumina_solution"))
                .fluid()
                .color((Aluminium.getMaterialRGB()-15))
                .iconSet(FLUID)
                .build()
                .setFormula("(Al2O3)(CH2Cl2)(C12H27N)2",true);

        /*
        MATERIAL = new Material.Builder(id++, tjfId("material"))
                .ingot()
                .color()
                .iconSet()
                .flags()
                .components()
                .blast()
                .build();

        MATERIAL = new Material.Builder(id++, tjfId("material"))
                .fluid()
                .color()
                .iconSet(FLUID)
                .build()
                .setFormula("",true);

        MATERIAL = new Material.Builder(id++, tjfId("material"))
                .dust()
                .color()
                .iconSet()
                .build()
                .setFormula("",true);
         */
    }


    public static void materialChanges() {

        addDust(Germanium, 1, 0);
        addFluid(Bromine);
        addFluid(AmmoniumChloride);

        Germanium.addFlags(GENERATE_PLATE);

        List<Material> mats = new ArrayList<>();
        Collections.addAll(mats, Bohrium, Dubnium, Duranium);

        for (MaterialFlag flag : CORE_METAL) {
            Bohrium.addFlags(flag);
            Dubnium.addFlags(flag);
        }

        for (Material mat : mats) {
            addIngot(mat);
            addFluid(mat);
        }

        Bohrium.addFlags(GENERATE_FRAME, GENERATE_ROUND);
        NaquadahAlloy.addFlags(GENERATE_FINE_WIRE);
    }
}