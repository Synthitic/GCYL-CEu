package com.fulltrix.tjfcore;

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
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtech.api.unification.material.info.MaterialIconSet.*;
import static gregtech.integration.crafttweaker.material.MaterialPropertyExpansion.*;
import static gregicality.multiblocks.api.unification.GCYMMaterials.*;

public class TJFMaterials {


    public static final List<MaterialFlag> CORE_METAL = new ArrayList<>();

    static {
        CORE_METAL.addAll(EXT2_METAL);
        CORE_METAL.addAll(Arrays.asList(GENERATE_RING, GENERATE_FRAME, GENERATE_ROTOR, GENERATE_SMALL_GEAR, GENERATE_DENSE, GENERATE_FINE_WIRE));
    }

    public static int id = 24000;
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

    public static Material Plutonium;

    public static Material Prasiolite;
    public static Material BismuthTellurite;
    public static Material CubicZirconia;
    public static Material MagnetoResonatic;
    public static Material Jasper;
    public static Material Americium241;
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

    public static Material Titan_Steel;

    public static Material Black_Titanium;

    public static Material Cosmic_Neutronium;

    public static Material Infinity;

    public static Material Eternity;


    public static void register() {

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
                .flags(NO_SMASHING, GENERATE_FOIL, GENERATE_FINE_WIRE, DISABLE_DECOMPOSITION)
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


        Plutonium = new Material.Builder(id++, tjfId("plutonium_generic"))
                .ingot(3)
                .color(0xF03232)
                .iconSet(METALLIC)
                .flags(EXT_METAL)
                .element(Elements.Pu)
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

        Americium241 = new Material.Builder(id++, tjfId("americium_241"))
                .ingot()
                .color(13158600)
                .iconSet(METALLIC)
                .flags(STD_METAL)
                .build()
                .setFormula("SiC", true);

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

        /*
        MATERIAL = new Material.Builder(id++, tjfId("material"))
                .ingot()
                .color()
                .iconSet()
                .flags()
                .components()
                .blast()
                .build();
         */

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
    }

    public static void materialChanges() {

        addDust(Germanium, 1, 0);
        addFluid(Duranium);

        Germanium.addFlags(GENERATE_PLATE);

        List<Material> mats = new ArrayList<>();
        Collections.addAll(mats, Bohrium, Dubnium);

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