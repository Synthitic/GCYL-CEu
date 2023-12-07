package com.fulltrix.tjfcore;

//import com.fulltrix.tjfcore.materials.IsotopeMaterial;
//import com.fulltrix.tjfcore.materials.RadioactiveMaterial;

import gregtech.api.GTValues;
import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.Elements;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialFlag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.fulltrix.tjfcore.TJFElements.*;
import static com.fulltrix.tjfcore.TJFUtility.tjfId;
import static gregicality.multiblocks.api.unification.GCYMMaterialFlags.NO_ALLOY_BLAST_RECIPES;
import static gregicality.multiblocks.api.unification.GCYMMaterials.Stellite100;
import static gregicality.multiblocks.api.unification.GCYMMaterials.Zeron100;
import static gregtech.api.fluids.attribute.FluidAttributes.ACID;
import static gregtech.api.unification.Elements.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtech.api.unification.material.info.MaterialIconSet.*;
import static gregtech.integration.crafttweaker.material.MaterialPropertyExpansion.*;

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

    public static Material PreciousMetal;
    public static Material GoldAlloy;
    public static Material GoldLeach;
    public static Material CopperLeach;
    public static Material ChloroauricAcid;
    public static Material PotassiumMetabisulfite;

    public static Material OrthoXylene;
    public static Material Durene;
    public static Material PyromelliticDianhydride;
    public static Material Oxydianiline;
    public static Material PolyamicAcid;
    public static Material Hexafluoropropylene;
    public static Material FluorinatedEthylenePropylene;

    public static Material Taranium;
    public static Material NaquadriaticTaranium;
    public static Material BlackTitanium;
    public static Material FullerenePolymerMatrix;
    public static Material Zylon;

    public static Material SupercriticalSteam;
    public static Material SodiumPotassiumAlloy;
    public static Material SupercriticalSodiumPotassiumAlloy;
    public static Material FLiNaK;
    public static Material SupercriticalFLiNaK;
    public static Material FLiBe;
    public static Material SupercriticalFLiBe;
    public static Material LeadBismuthEutectic;
    public static Material SupercriticalLeadBismuthEutectic;

    public static Material HastelloyN;

    public static Material TantalumHafniumSeaborgiumCarbide;
    public static Material Vibranium;
    public static Material HeavyQuarkDegenerateMatter;
    public static Material MetastableFlerovium;
    public static Material MetastableHassium;
    public static Material QCDMatter;
    public static Material SuperheavyLAlloy;
    public static Material Lafium;
    public static Material TriniumTitanium;
    public static Material Nitinol60;

    public static Material ActiniumSuperhydride;
    public static Material BETSPerrhenate;
    public static Material BorocarbideDust;
    public static Material FullereneSuperconductiveDust;
    public static Material MetastableOganesson;
    public static Material ProtoAdamantium;
    public static Material SuperheavyHAlloy;
    public static Material ChargedCaesiumCeriumCobaltIndium;
    public static Material RheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate;
    public static Material Legendarium;
    public static Material LanthanumFullereneMix;
    public static Material LanthanumFullereneNanotubes;

    public static Material HexanitroHexaazaisowurtzitane;
    public static Material Glyceryl;
    public static Material ElectronDegenerateRheniumPlasma;
    public static Material SuperheavyMix;

    public static Material NeutronPlasma;
    public static Material FreeAlphaGas;
    public static Material FreeElectronGas;
    //COILS
    public static Material TitanSteel;

    public static Material CosmicNeutronium;

    public static Material Infinity;

    public static Material Eternity;

    //superconductors
    public static Material LVSuperconductor = ManganesePhosphide;
    public static Material MVSuperconductor = MagnesiumDiboride;
    public static Material HVSuperconductor = MercuryBariumCalciumCuprate;
    public static Material EVSuperconductor = UraniumTriplatinum;
    public static Material IVSuperconductor = SamariumIronArsenicOxide;
    public static Material LuVSuperconductor = IndiumTinBariumTitaniumCuprate;
    public static Material ZPMSuperconductor = UraniumRhodiumDinaquadide;
    public static Material UVSuperconductor = EnrichedNaquadahTriniumEuropiumDuranide;
    public static Material UHVSuperconductor = RutheniumTriniumAmericiumNeutronate;

    public static Material UEVSuperconductor;

    public static Material UIVSuperconductor;

    public static Material UXVSuperconductor;

    public static Material OpVSuperconductor;


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
                .cableProperties(GTValues.V[GTValues.UEV], 4, 32)
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
                .cableProperties(GTValues.V[GTValues.UHV], 4, 16)
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
                .element(Ad)
                .blast(10850)
                .build();

        Cinobite = new Material.Builder(id++, tjfId("cinobite"))
                .ingot(5).fluid()
                .color(0x010101)
                .iconSet(SHINY)
                .flags(CORE_METAL, DISABLE_DECOMPOSITION)
                .components(Zeron100, 8, Naquadria, 4, Gadolinium, 3, Aluminium, 2, Mercury, 1, Tin, 1, Titanium, 6, Osmiridium, 1)
                .cableProperties(GTValues.V[GTValues.UIV], 4, 64)
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

        Sarcosine = new Material.Builder(id++, tjfId("sarcosine"))
                .dust()
                .color((Glycine.getMaterialRGB() + Oxygen.getMaterialRGB()) / 2)
                .iconSet(SHINY)
                .build()
                .setFormula("C3H7NO2", true);

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

        PreciousMetal= new Material.Builder(id++, tjfId("precious_metal"))
                .ingot(2).ore()
                .color(0xB99023)
                .iconSet(SHINY)
                .build()
                .setFormula("Au?", true);

                GoldAlloy= new Material.Builder(id++, tjfId("gold_alloy"))
                        .ingot(2)
                        .color(0xBBA52B)
                        .iconSet(SHINY)
                        .build()
                        .setFormula("Cu3Au?",true);

        GoldLeach= new Material.Builder(id++, tjfId("gold_leach"))
                .dust()
                .color(0xBBA52B)
                .iconSet(ROUGH)
                .build()
                .setFormula("Cu3Au?",true);

                CopperLeach= new Material.Builder(id++, tjfId("copper_leach"))
                        .dust()
                        .color(0x765A30)
                        .iconSet(SHINY)
                        .build()
                        .setFormula("Cu3?",true);

        ChloroauricAcid = new Material.Builder(id++, tjfId("chloroauric_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0xDFD11F)
                .iconSet(FLUID)
                .build()
                .setFormula("HAuCl?", true);

        PotassiumMetabisulfite = new Material.Builder(id++, tjfId("potassium_metabisulfite"))
                .dust()
                .color(0xFFFFFF)
                .iconSet(DULL)
                .components(Potassium, 2, Sulfur, 2, Oxygen, 5)
                .build();

        OrthoXylene = new Material.Builder(id++, tjfId("ortho_xylene"))
                .fluid()
                .color(0xB9575E)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H4(CH3)2", true);

        Durene = new Material.Builder(id++, tjfId("durene"))
                .dust()
                .color(0xA39C95)
                .iconSet(ROUGH)
                .build()
                .setFormula("C6H2(CH3)4", true);

        PyromelliticDianhydride = new Material.Builder(id++, tjfId("pyromellitic_dianhydride"))
                .dust()
                .color(0xF0EAD6)
                .iconSet(SHINY)
                .build()
                .setFormula("C6H2(C2O3)2", true);

        Oxydianiline = new Material.Builder(id++, tjfId("oxydianiline"))
                .fluid()
                .color(0xF0E130)
                .iconSet(FLUID)
                .build()
                .setFormula("C12H12N2O", true);
        PolyamicAcid = new Material.Builder(id++, tjfId("polyamic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0xFFAE42)
                .iconSet(FLUID)
                .build()
                .setFormula("C22H14N2O7", true);

        Hexafluoropropylene = new Material.Builder(id++, tjfId("hexafluoropropylene"))
                .fluid()
                .color(0x111111)
                .iconSet(FLUID)
                .build()
                .setFormula("C3F6", true);

        FluorinatedEthylenePropylene = new Material.Builder(id++, tjfId("fluorinated_ethylene_propylene"))
                .ingot().fluid()
                .color(0xC8C8C8)
                .iconSet(DULL)
                .flags(GENERATE_PLATE, FLAMMABLE, NO_SMASHING, DISABLE_DECOMPOSITION, NO_ALLOY_BLAST_RECIPES)
                .components(Carbon, 5, Fluorine, 10)
                .build();

        Taranium = new Material.Builder(id++, tjfId("taranium"))
                .ingot(7).fluid()
                .color(0x0c0c0d)
                .iconSet(SHINY)
                .flags(CORE_METAL)
                .element(Tn)
                .blast(10000)
                .build();

        NaquadriaticTaranium = new Material.Builder(id++, tjfId("naquadriatic_taranium"))
                .ingot()
                .color((Naquadria.getMaterialRGB() + Taranium.getMaterialRGB()) / 2)
                .iconSet(SHINY)
                .flags(STD_METAL, GENERATE_LONG_ROD)
                .components(Naquadria, 1, Taranium, 1)
                .cableProperties(GTValues.V[GTValues.UXV], 2, 32)
                .blast(11200)
                .build();

        BlackTitanium = new Material.Builder(id++, tjfId("black_titanium"))
                .ingot()
                .color(0x6C003B)
                .iconSet(SHINY)
                .flags(CORE_METAL, DISABLE_DECOMPOSITION)
                .components(Titanium, 26, Lanthanum, 6, Tungsten, 4, Cobalt, 3, Manganese, 2, Phosphorus, 2, Palladium, 2, Niobium, 1, Argon, 5)
                .cableProperties(GTValues.V[GTValues.UIV], 2, 32)
                .blast(11500)
                .build();

        FullerenePolymerMatrix = new Material.Builder(id++, tjfId("fullerene_polymer_matrix"))
                .polymer(2).fluid()
                .color(0x403e37)
                .iconSet(DULL)
                .flags(EXCLUDE_BLOCK_CRAFTING_RECIPES, GENERATE_FOIL, NO_SMASHING, DISABLE_DECOMPOSITION)
                .components(Palladium, 1, Iron, 1, Carbon, 153, Hydrogen, 36, Nitrogen, 1, Oxygen, 2)
                .build();

        Zylon = new Material.Builder(id++, tjfId("zylon"))
                .polymer(2).fluid()
                .color(0xFFE000)
                .iconSet(SHINY)
                .flags(EXCLUDE_BLOCK_CRAFTING_RECIPES, GENERATE_FOIL, NO_SMASHING, DISABLE_DECOMPOSITION)
                .components(Carbon, 14, Hydrogen, 6, Nitrogen, 2, Oxygen, 2)
                .build();

        SupercriticalSteam = new Material.Builder(id++, tjfId("supercritical_steam"))
                .fluid()
                .color(Steam.getMaterialRGB())
                .iconSet(FLUID)
                .build()
                .setFormula("H2O", true);

        SodiumPotassiumAlloy = new Material.Builder(id++, tjfId("sodium_potassium_alloy"))
                .dust(2).fluid()
                .color(0x252525)
                .iconSet(SHINY)
                .components(Sodium, 7, Potassium, 3)
                .build();

        SupercriticalSodiumPotassiumAlloy = new Material.Builder(id++, tjfId("supercritical_sodium_potassium_alloy"))
                .fluid()
                .color(SodiumPotassiumAlloy.getMaterialRGB())
                .iconSet(FLUID)
                .components(SodiumPotassiumAlloy, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("", true);

        FLiNaK = new Material.Builder(id++, tjfId("flinak"))
                .dust(2).fluid()
                .color(0x252525)
                .iconSet(DULL)
                .components(Fluorine, 3, Lithium, 1, Sodium, 1, Potassium, 1)
                .build();

        SupercriticalFLiNaK = new Material.Builder(id++, tjfId("supercritical_flinak"))
                .fluid()
                .color(FLiNaK.getMaterialRGB())
                .iconSet(FLUID)
                .components(FLiNaK, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        FLiBe = new Material.Builder(id++, tjfId("flibe"))
                .dust(2).fluid()
                .color(0x252525)
                .iconSet(DULL)
                .components(Fluorine, 3, Lithium, 1, Beryllium, 1)
                .build();

        SupercriticalFLiBe = new Material.Builder(id++, tjfId("supercritical_flibe"))
                .fluid()
                .color(FLiBe.getMaterialRGB())
                .components(FLiBe, 1)
                .flags(DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .build();

        LeadBismuthEutectic = new Material.Builder(id++, tjfId("lead_bismuth_eutatic"))
                .ingot().fluid()
                .color(0x757575)
                .iconSet(SHINY)
                .components(Lead, 3, Bismuth, 7)
                .build();

        SupercriticalLeadBismuthEutectic = new Material.Builder(id++, tjfId("supercritical_lead_bismuth_eutatic"))
                .fluid()
                .color(LeadBismuthEutectic.getMaterialRGB())
                .iconSet(FLUID)
                .flags(DISABLE_DECOMPOSITION)
                .components(LeadBismuthEutectic, 1)
                .build();

        HastelloyN = new Material.Builder(id++, tjfId("hastelloy_n"))
                .ingot(6)
                .color(0xDDDDDD)
                .iconSet(METALLIC)
                .flags(EXT2_METAL, GENERATE_FRAME, GENERATE_DENSE)
                .components(Yttrium, 2, Molybdenum, 4, Chrome, 2, Titanium, 2, Nickel, 15)
                .blast(4350)
                .build();

        Lafium = new Material.Builder(id++, tjfId("lafium"))
                .ingot(7)
                .color(0x0d0d60)
                .iconSet(SHINY)
                .flags(CORE_METAL, DISABLE_DECOMPOSITION)
                .fluidPipeProperties(23000, 2000, true, true, true, true)
                .components(HastelloyN, 8, Naquadah, 4, Samarium, 2, Tungsten, 4, Argon, 2, Aluminium, 6, Nickel, 8, Carbon, 2)
                .blast(9865)
                .build();

        HeavyQuarkDegenerateMatter = new Material.Builder(id++, tjfId("heavy_quark_degenerate_matter"))
                .ingot(6)
                .color(0x5dbd3a)
                .iconSet(SHINY)
                .flags(CORE_METAL)
                .blast(13000)
                .build()
                .setFormula("?");

        MetastableFlerovium = new Material.Builder(id++, tjfId("metastable_flerovium"))
                .ingot(6)
                .color(0x521973)
                .iconSet(SHINY)
                .flags(CORE_METAL)
                .element(Fl)
                .blast(11240)
                .build();

        QCDMatter = new Material.Builder(id++, tjfId("qcd_confined_matter"))
                .ingot(7)
                .color(0xeb9e3f)
                .iconSet(SHINY)
                .flags(GENERATE_PLATE, DISABLE_REPLICATION, NO_WORKING, NO_SMELTING, NO_SMASHING, GENERATE_FRAME, GENERATE_ROD)
                .components()
                .blast(13100)
                .build()
                .setFormula("?");

        TitanSteel = new Material.Builder(id++, tjfId("titan_steel"))
                .ingot(7)
                .color(0xAA0d0d)
                .iconSet(SHINY)
                .flags(CORE_METAL, DISABLE_DECOMPOSITION)
                .components(TungstenTitaniumCarbide, 3, Jasper, 3)
                .cableProperties(GTValues.V[GTValues.UEV], 2, 16)
                .blast(9200)
                .build();

        MetastableHassium = new Material.Builder(id++, tjfId("metastable_hassium"))
                .ingot(6)
                .color(0x2d3a9d)
                .iconSet(SHINY)
                .flags(CORE_METAL)
                .element(Hs)
                .blast(11240)
                .build();

        Vibranium = new Material.Builder(id++, tjfId("vibranium"))
                .ingot(7)
                .color(0x828aad)
                .iconSet(SHINY)
                .flags(CORE_METAL)
                .element(Vb)
                .blast(11220)
                .build();

        TriniumTitanium = new Material.Builder(id++, tjfId("trinium_titanium"))
                .ingot(7)
                .color(0x9986a3)
                .iconSet(SHINY)
                .flags(CORE_METAL)
                .components(Trinium, 2, Titanium, 1)
                .blast(11000)
                .build();

        TantalumHafniumSeaborgiumCarbide = new Material.Builder(id++, tjfId("tantalum_hafnium_seaborgium_carbide"))
                .ingot(6)
                .color(0x2c2c2c)
                .iconSet(SHINY)
                .flags(GENERATE_PLATE, EXCLUDE_BLOCK_CRAFTING_RECIPES, DISABLE_DECOMPOSITION)
                .components(Tantalum, 12, Hafnium, 3, Seaborgium, 1, Carbon, 16)
                .blast(5200)
                .build();

        Nitinol60 = new Material.Builder(id++, tjfId("nitinol_a"))
                .ingot(4)
                .color(0xCCB0EC)
                .iconSet(METALLIC)
                .flags(EXT2_METAL, GENERATE_FRAME)
                .components(Nickel, 2, Titanium, 3)
                .blast(Titanium.getBlastTemperature())
                .build();

        ActiniumSuperhydride = new Material.Builder(id++, tjfId("actinium_superhydride"))
                .dust()
                .color(Actinium.getMaterialRGB() * 9 / 8)
                .iconSet(SHINY)
                .build()
                .setFormula("AcH12", true);

        BETSPerrhenate = new Material.Builder(id++, tjfId("bets_perrhenate"))
                .dust()
                .color(0x7ada00)
                .iconSet(SHINY)
                .build()
                .setFormula("ReC10H8S4Se4O4", true);

        BorocarbideDust = new Material.Builder(id++, tjfId("borocarbide_dust"))
                .dust()
                .color(0x9a9a2a)
                .iconSet(SHINY)
                .build()
                .setFormula("B4C7Fr4At6Ho2Th2Fl2Cn2", true);

        FullereneSuperconductiveDust = new Material.Builder(id++, tjfId("fullerene_superconductor_dust"))
                .dust()
                .color(0x99cc00)
                .iconSet(SHINY)
                .build()
                .setFormula("LaCsRb(C60)2", true);

        MetastableOganesson = new Material.Builder(id++, tjfId("metastable_oganesson"))
                .ingot(7)
                .color(0xE61C24)
                .iconSet(SHINY)
                .flags(CORE_METAL)
                .element(Og)
                .blast(10380)
                .build();

        ProtoAdamantium = new Material.Builder(id++, tjfId("proto_adamantium"))
                .ingot(7)
                .color(0x4662d4)
                .iconSet(SHINY)
                .flags(CORE_METAL)
                .components(Adamantium, 3, Promethium, 2)
                .blast(11244)
                .build();

        SuperheavyHAlloy = new Material.Builder(id++, tjfId("superheavy_h_alloy"))
                .ingot(6)
                .color(0xE84B36)
                .iconSet(SHINY)
                .flags(GENERATE_PLATE, DISABLE_DECOMPOSITION)
                .components(Copernicium, 1, Nihonium, 1, MetastableFlerovium, 1, Moscovium, 1, Livermorium, 1, Tennessine, 1, MetastableOganesson, 1)
                .blast(10600)
                .build();

        ChargedCaesiumCeriumCobaltIndium = new Material.Builder(id++, tjfId("charged_caesium_cerium_cobalt_indium"))
                .dust()
                .color(0x52ad25)
                .iconSet(SHINY)
                .build()
                .setFormula("CsCeCo2In10", true);

        RheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate = new Material.Builder(id++, tjfId("rhenium_hassium_thallium_isophtaloylbisdiethylthiourea"))
                .dust()
                .color(0xa26a8b)
                .iconSet(SHINY)
                .build()
                .setFormula("ReHsTlC60PN12H84S6O12F6", true);

        Legendarium = new Material.Builder(id++, tjfId("legendarium"))
                .dust()
                .color(0xffffff)
                .iconSet(SHINY)
                .build()
                .setFormula("NqNq+*Nq*DrTrKeTnAdVb", true);

        LanthanumFullereneMix = new Material.Builder(id++, tjfId("lanthanum_fullerene_mix"))
                .dust()
                .color(0xdfcafa)
                .iconSet(SHINY)
                .build()
                .setFormula("(C60)2La2?", true);

        LanthanumFullereneNanotubes = new Material.Builder(id++, tjfId("lanthanum_fullerene_nanotubes"))
                .dust()
                .color(LanthanumFullereneMix.getMaterialRGB() * 3 / 5)
                .iconSet(SHINY)
                .build()
                .setFormula("La2(C60)2CNT", true);

        UEVSuperconductor = new Material.Builder(id++, tjfId("uev_superconductor"))
                .ingot(1)
                .color(0x954fe0)
                .iconSet(SHINY)
                .flags(STD_METAL, DISABLE_DECOMPOSITION)
                .components(ActiniumSuperhydride, 1, BETSPerrhenate, 1, TriniumTitanium, 2, Quantum, 1, Vibranium, 2)
                .cableProperties(GTValues.V[GTValues.UEV], 4, 0, true)
                .blast(11600)
                .build();

        UIVSuperconductor = new Material.Builder(id++, tjfId("uiv_superconductor"))
                .ingot(1)
                .color(0x8bf743)
                .iconSet(SHINY)
                .flags(STD_METAL, DISABLE_DECOMPOSITION)
                .components(BorocarbideDust, 2, FullereneSuperconductiveDust, 1, MetastableOganesson, 2, ProtoAdamantium, 2)
                .cableProperties(GTValues.V[GTValues.UIV], 4, 0, true)
                .blast(11600)
                .build();

        UXVSuperconductor = new Material.Builder(id++, tjfId("uxv_superconductor"))
                .ingot(1)
                .color(0x883afc)
                .iconSet(SHINY)
                .flags(STD_METAL, DISABLE_DECOMPOSITION)
                .components(BlackTitanium, 3, SuperheavyHAlloy, 2, ChargedCaesiumCeriumCobaltIndium, 3, RheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate, 6)
                .cableProperties(GTValues.V[GTValues.UXV], 4, 0, true)
                .blast(12000)
                .build();

        OpVSuperconductor = new Material.Builder(id++, tjfId("opv_superconductor"))
                .ingot(1)
                .color(0xe34b5a)
                .iconSet(SHINY)
                .flags(STD_METAL, DISABLE_DECOMPOSITION)
                .components(Neutronium, 4, Legendarium, 5, ActiniumSuperhydride, 5, LanthanumFullereneNanotubes, 4, RheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate, 12)
                .cableProperties(GTValues.V[GTValues.OpV], 4, 0, true)
                .blast(14000)
                .build();

        SuperheavyLAlloy = new Material.Builder(id++, tjfId("superheavy_l_alloy"))
                .ingot(6)
                .color(0x2B45DF)
                .iconSet(SHINY)
                .flags(GENERATE_PLATE, DISABLE_DECOMPOSITION)
                .components(Rutherfordium, 1, Dubnium, 1, Seaborgium, 1, Bohrium, 1, MetastableHassium, 1, Meitnerium, 1, Roentgenium, 1)
                .blast(10600)
                .build();

        HexanitroHexaazaisowurtzitane = new Material.Builder(id++, tjfId("hexanitrohexaazaisowurtzitane"))
                .dust()
                .color(0x414a4f)
                .iconSet(SHINY)
                .build()
                .setFormula("C6H6N12O12", true);

        Glyceryl = new Material.Builder(id++, tjfId("glyceryl"))
                .fluid()
                .color(38550)
                .iconSet(FLUID)
                .flags(FLAMMABLE, EXPLOSIVE, NO_SMASHING)
                .components(Carbon, 3, Hydrogen, 5, Nitrogen, 3, Oxygen, 9)
                .build()
                .setFormula("", true);

        ElectronDegenerateRheniumPlasma = new Material.Builder(id++, tjfId("degenerate_rhenium_plasma"))
                .plasma()
                .color(0x6666FF)
                .iconSet(FLUID)
                .build()
                .setFormula("Rh", true);

        SuperheavyMix = new Material.Builder(id++, tjfId("superheavy_mix"))
                .fluid()
                .color(0x403737)
                .iconSet(FLUID)
                .build()
                .setFormula("SgBhRfDb", true);

        NeutronPlasma = new Material.Builder(id++, tjfId("neutron_plasma"))
                .plasma()
                .color(0xf0e9e9)
                .iconSet(FLUID)
                .build()
                .setFormula("n", true);

        FreeAlphaGas = new Material.Builder(id++, tjfId("free_alpha_gas"))
                .fluid()
                .color(0xe0d407)
                .iconSet(FLUID)
                .build()
                .setFormula("a", true);

        FreeElectronGas = new Material.Builder(id++, tjfId("free_electron_gas"))
                .fluid()
                .color(0x044c4c)
                .iconSet(FLUID)
                .build()
                .setFormula("e-", true);

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

        //NUCLEAR STUFF
        List<Material> nuclearMats = new ArrayList<>();
        Collections.addAll(nuclearMats, Einsteinium, Fermium, Mendelevium);

        for (Material mat : nuclearMats) {
            addDust(mat, 1, 0);
            addFluid(mat);
            mat.addFlags(GENERATE_PLATE);
        }

        Germanium.addFlags(GENERATE_PLATE);

        //CORE METAL ADDITIONS
        List<Material> mats = new ArrayList<>();
        Collections.addAll(mats, Bohrium, Dubnium, Duranium, Seaborgium, Rhenium, Rutherfordium);

        for (Material mat : mats) {
            for (MaterialFlag flag : CORE_METAL) {
                addIngot(mat);
                addFluid(mat);
                mat.addFlags(flag);
            }
        }

        //SPECIFIC CASES

        Bohrium.addFlags(GENERATE_FRAME, GENERATE_ROUND);
        NaquadahAlloy.addFlags(GENERATE_FINE_WIRE);

        Copper.addFlags(GENERATE_DENSE);
        StainlessSteel.addFlags(GENERATE_DENSE);

        Naquadria.addFlags(GENERATE_FRAME);
        Osmiridium.addFlags(GENERATE_FRAME);

        addFluidPipes(Zeron100, 15000, 1750, true);

        addFluid(Carbon, "plasma", false);

    }
}