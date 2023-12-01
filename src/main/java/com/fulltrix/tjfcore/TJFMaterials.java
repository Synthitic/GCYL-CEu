package com.fulltrix.tjfcore;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialFlag;
import gregtech.api.unification.material.materials.*;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.integration.crafttweaker.material.MaterialPropertyExpansion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.fulltrix.tjfcore.TJFUtility.tjfId;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtech.api.unification.material.info.MaterialIconSet.*;
import static gregtech.integration.crafttweaker.material.MaterialPropertyExpansion.*;

public class TJFMaterials {


    public static final List<MaterialFlag> CORE_METAL = new ArrayList<>();

    static {
        CORE_METAL.addAll(EXT2_METAL);
        CORE_METAL.addAll(Arrays.asList(GENERATE_RING, GENERATE_FRAME , GENERATE_ROTOR, GENERATE_SMALL_GEAR, GENERATE_DENSE, GENERATE_FINE_WIRE));
    }
    public static int id = 24000;
    public static Material Pikyonium;

    public static Material Inconel792;

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



    public static void register() {

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
                .polymer(2)
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
                .color((Sodium.getMaterialRGB()+Fluorine.getMaterialRGB())/2)
                .iconSet(DULL)
                .components(Sodium, 1, Fluorine, 1)
                .build();
    }

    public static void materialChanges() {

        addDust(Germanium, 1, 0);
        Germanium.addFlags(GENERATE_PLATE);
    }
}