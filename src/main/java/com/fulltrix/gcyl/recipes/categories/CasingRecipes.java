package com.fulltrix.gcyl.recipes.categories;

import com.fulltrix.gcyl.item.GCYLMetaBlocks;
import com.fulltrix.gcyl.item.GCYLMultiblockCasing2;
import gregicality.multiblocks.common.metatileentities.GCYMMetaTileEntities;
import gregtech.api.GTValues;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;

import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static com.fulltrix.gcyl.item.GCYLCoreItems.*;
import static com.fulltrix.gcyl.item.GCYLHeatingCoil.CoilType.*;
import static com.fulltrix.gcyl.item.GCYLMultiblockCasing2.CasingType.*;
import static com.fulltrix.gcyl.item.GCYLReactorCasing.CasingType.*;
import static com.fulltrix.gcyl.materials.GCYLNuclearMaterials.ReactorSteel;
import static gregicality.multiblocks.api.unification.GCYMMaterials.*;
import static gregtech.api.GTValues.L;
import static gregtech.api.recipes.GTRecipeHandler.removeRecipesByInputs;
import static gregtech.api.recipes.ModHandler.removeRecipeByName;
import static gregtech.api.recipes.ModHandler.removeTieredRecipeByName;
import static gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES;
import static gregtech.api.recipes.RecipeMaps.FORMING_PRESS_RECIPES;
import static gregtech.api.unification.material.MarkerMaterials.Tier.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.blocks.BlockWireCoil.CoilType.*;
import static gregtech.common.items.MetaItems.*;
import static gregtech.common.items.MetaItems.EMITTER_UV;
import static gregtech.common.metatileentities.MetaTileEntities.HULL;

public class CasingRecipes {

    public static void init() {
        //componentCasings();
        multiblockCasings();
        coilCasings();
        tieredCasings();
        tieredGlass();
    }

    private static void tieredCasings() {

        removeTieredRecipeByName("gcym:gcym.machine.tiered_hatch.", GTValues.ULV, GTValues.UV);


        // Integral Frameworks
        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(7)
                .input(circuit, ULV, 2)
                .input(gear, Potin, 2)
                .input(plate, Potin, 2)
                .input(cableGtOctal, Tin)
                .inputs(MetaTileEntities.HULL[GTValues.ULV].getStackForm())
                .fluidInputs(Steel.getFluid(L * 2))
                .outputs(GCYMMetaTileEntities.TIERED_HATCH[0].getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(30)
                .input(circuit, LV, 2)
                .input(gear, Magnalium, 2)
                .input(plate, Magnalium, 2)
                .input(cableGtOctal, Cobalt)
                .inputs(MetaTileEntities.HULL[GTValues.LV].getStackForm())
                .fluidInputs(Silicon.getFluid(L * 2))
                .outputs(GCYMMetaTileEntities.TIERED_HATCH[1].getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(120)
                .input(circuit, MV, 2)
                .input(gear, EglinSteel, 2)
                .input(plate, EglinSteel, 2)
                .input(cableGtOctal, AnnealedCopper)
                .inputs(MetaTileEntities.HULL[GTValues.MV].getStackForm())
                .fluidInputs(BabbittAlloy.getFluid(L * 2))
                .outputs(GCYMMetaTileEntities.TIERED_HATCH[2].getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(480)
                .input(circuit, HV, 2)
                .input(gear, Inconel625, 2)
                .input(plate, Inconel625, 2)
                .input(cableGtOctal, Gold)
                .inputs(MetaTileEntities.HULL[GTValues.HV].getStackForm())
                .fluidInputs(Inconel625.getFluid(L * 2))
                .outputs(GCYMMetaTileEntities.TIERED_HATCH[3].getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(1920)
                .input(circuit, EV, 2)
                .input(gear, TungstenCarbide, 4)
                .input(plate, TungstenCarbide, 4)
                .input(cableGtOctal, Titanium)
                .inputs(MetaTileEntities.HULL[GTValues.EV].getStackForm())
                .fluidInputs(Stellite100.getFluid(L * 2))
                .outputs(GCYMMetaTileEntities.TIERED_HATCH[4].getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(7680)
                .input(circuit, IV, 2)
                .input(gear, Nitinol60, 4)
                .input(plate, Nitinol60, 4)
                .input(cableGtOctal, Nichrome)
                .inputs(MetaTileEntities.HULL[GTValues.IV].getStackForm())
                .fluidInputs(Thorium.getFluid(L * 2))
                .outputs(GCYMMetaTileEntities.TIERED_HATCH[5].getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(30720)
                .input(circuit, LuV, 2)
                .input(gear, IncoloyMA956, 4)
                .input(plate, IncoloyMA956, 4)
                .input(cableGtOctal, Platinum)
                .inputs(MetaTileEntities.HULL[GTValues.LuV].getStackForm())
                .fluidInputs(Uranium238.getFluid(L * 2))
                .outputs(GCYMMetaTileEntities.TIERED_HATCH[6].getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(30720 * 4)
                .input(circuit, ZPM, 2)
                .input(gear, BabbittAlloy, 4)
                .input(plate, BabbittAlloy, 4)
                .input(cableGtOctal, NiobiumTitanium)
                .inputs(MetaTileEntities.HULL[GTValues.ZPM].getStackForm())
                .fluidInputs(Plutonium244.getFluid(L * 2))
                .outputs(GCYMMetaTileEntities.TIERED_HATCH[7].getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(30720 * 16)
                .input(circuit, UV, 2)
                .input(gear, HG1223, 4)
                .input(plate, HG1223, 4)
                .input(cableGtOctal, YttriumBariumCuprate)
                .inputs(MetaTileEntities.HULL[GTValues.UV].getStackForm())
                .fluidInputs(NaquadahEnriched.getFluid(L * 2))
                .outputs(GCYMMetaTileEntities.TIERED_HATCH[8].getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(30720 * 64)
                .input(circuit, UHV, 2)
                .input(gear, AbyssalAlloy, 8)
                .input(plate, AbyssalAlloy, 8)
                .input(cableGtOctal, TungstenTitaniumCarbide)
                .inputs(MetaTileEntities.HULL[GTValues.UHV].getStackForm())
                .fluidInputs(Naquadria.getFluid(L * 2))
                .outputs(GCYMMetaTileEntities.TIERED_HATCH[9].getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(30720 * 256)
                .input(circuit, UEV, 2)
                .input(gear, TitanSteel, 8)
                .input(plate, TitanSteel, 8)
                .input(cableGtOctal, Pikyonium)
                .inputs(MetaTileEntities.HULL[GTValues.UEV].getStackForm())
                .fluidInputs(Naquadria.getFluid(L * 2))
                .outputs(GCYMMetaTileEntities.TIERED_HATCH[10].getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(30720 * 1024)
                .input(circuit, UIV, 2)
                .input(gear, BlackTitanium, 8)
                .input(plate, BlackTitanium, 8)
                .input(cableGtOctal, Cinobite)
                .inputs(MetaTileEntities.HULL[GTValues.UIV].getStackForm())
                .fluidInputs(Naquadria.getFluid(L * 2))
                .outputs(GCYMMetaTileEntities.TIERED_HATCH[11].getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(30720 * 4096)
                .input(circuit, UXV, 2)
                .input(gear, Vibranium, 8)
                .input(plate, Vibranium, 8)
                .input(cableGtOctal, NaquadriaticTaranium)
                .inputs(MetaTileEntities.HULL[GTValues.UXV].getStackForm())
                .fluidInputs(Naquadria.getFluid(L * 2))
                .outputs(GCYMMetaTileEntities.TIERED_HATCH[12].getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(30720 * 8192)
                .input(circuit, OpV, 2)
                .input(gear, QCDMatter, 8)
                .input(plate, QCDMatter, 8)
                .input(cableGtOctal, NaquadriaticTaranium)
                .inputs(MetaTileEntities.HULL[GTValues.OpV].getStackForm())
                .fluidInputs(EnrichedNaquadahAlloy.getFluid(L * 2))
                .outputs(GCYMMetaTileEntities.TIERED_HATCH[13].getStackForm())
                .buildAndRegister();

        //TODO: finish MAX tiered hatch
        /*
        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(30720 * 16384)
                .input(circuit, MAX, 4)
                .input(gear, Neutronium, 8)
                .input(plate, Neutronium, 8)
                .input(cableGtOctal, ChaosAlloy)
                .inputs(MetaTileEntities.HULL[GTValues.MAX].getStackForm())
                .fluidInputs(RefinedChaos.getFluid(L * 2))
                .outputs(GCYMMetaTileEntities.TIERED_HATCH[13].getStackForm())
                .buildAndRegister();

         */

        /*
        // Battery Tower Cells
        ASSEMBLER_RECIPES.recipeBuilder().EUt(240).duration(1200)
                .input(plateDouble, Lead, 4)
                .fluidInputs(Oxygen.getFluid(16000))
                .inputs(MetaBlocks.METAL_CASING.getItemVariant(STEEL_SOLID))
                .outputs(TJFMetaBlocks.CELL_CASING.getItemVariant(CELL_HV))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(960).duration(1200)
                .input(plateDouble, Titanium, 4)
                .fluidInputs(Nitrogen.getFluid(16000))
                .inputs(TJFMetaBlocks.CELL_CASING.getItemVariant(CELL_HV))
                .outputs(TJFMetaBlocks.CELL_CASING.getItemVariant(CELL_EV))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(3840).duration(1200)
                .input(plateDouble, TungstenSteel, 4)
                .fluidInputs(Helium.getFluid(8000))
                .inputs(TJFMetaBlocks.CELL_CASING.getItemVariant(CELL_EV))
                .outputs(TJFMetaBlocks.CELL_CASING.getItemVariant(CELL_IV))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(15360).duration(1200)
                .input(plateDouble, Iridium, 4)
                .fluidInputs(Argon.getFluid(4000))
                .inputs(TJFMetaBlocks.CELL_CASING.getItemVariant(CELL_IV))
                .outputs(TJFMetaBlocks.CELL_CASING.getItemVariant(CELL_LUV))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(61440).duration(1200)
                .input(plateDouble, NaquadahAlloy, 4)
                .fluidInputs(Radon.getFluid(4000))
                .inputs(TJFMetaBlocks.CELL_CASING.getItemVariant(CELL_LUV))
                .outputs(TJFMetaBlocks.CELL_CASING.getItemVariant(CELL_ZPM))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(245760).duration(1200)
                .input(plateDouble, Tritanium, 4)
                .fluidInputs(Krypton.getFluid(4000))
                .inputs(TJFMetaBlocks.CELL_CASING.getItemVariant(CELL_ZPM))
                .outputs(TJFMetaBlocks.CELL_CASING.getItemVariant(CELL_UV))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(983040).duration(1200)
                .input(plateDouble, Seaborgium, 4)
                .fluidInputs(Xenon.getFluid(4000))
                .inputs(TJFMetaBlocks.CELL_CASING.getItemVariant(CELL_UV))
                .outputs(TJFMetaBlocks.CELL_CASING.getItemVariant(CELL_UHV))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(3932160).duration(1200)
                .input(plateDouble, Bohrium, 4)
                .fluidInputs(FreeAlphaGas.getFluid(4000))
                .inputs(TJFMetaBlocks.CELL_CASING.getItemVariant(CELL_UHV))
                .outputs(TJFMetaBlocks.CELL_CASING.getItemVariant(CELL_UEV))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(3932160 * 4).duration(1200)
                .input(plateDouble, Adamantium, 4)
                .fluidInputs(LiquidCrystalDetector.getFluid(4000))
                .inputs(TJFMetaBlocks.CELL_CASING.getItemVariant(CELL_UEV))
                .outputs(TJFMetaBlocks.CELL_CASING.getItemVariant(CELL_UIV))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(3932160 * 16).duration(1200)
                .input(plateDouble, Vibranium, 4)
                .fluidInputs(HeavyLeptonMix.getFluid(4000))
                .inputs(TJFMetaBlocks.CELL_CASING.getItemVariant(CELL_UIV))
                .outputs(TJFMetaBlocks.CELL_CASING.getItemVariant(CELL_UMV))
                .buildAndRegister();

        */
    }

    private static void tieredGlass() {

        final MaterialStack[] firstMetal = {
                new MaterialStack(Iron, 1),
                new MaterialStack(Nickel, 1),
                new MaterialStack(Invar, 2),
                new MaterialStack(Steel, 2),
                new MaterialStack(StainlessSteel, 3),
                new MaterialStack(Titanium, 3),
                new MaterialStack(Tungsten, 4),
                new MaterialStack(TungstenSteel, 5)
        };

        final MaterialStack[] lastMetal = {
                new MaterialStack(Tin, 0),
                new MaterialStack(Zinc, 0),
                new MaterialStack(Aluminium, 1)
        };

        // Reinforced Glass

        //TODO: use this or ceu glass?
        /*
        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(400).EUt(16)
                .inputs(TJFMetaBlocks.TRANSPARENT_CASING.getItemVariant(REINFORCED_GLASS))
                .fluidInputs(BorosilicateGlass.getFluid(L))
                .outputs(TJFMetaBlocks.TRANSPARENT_CASING.getItemVariant(BOROSILICATE_GLASS))
                .buildAndRegister();

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(400).EUt(64)
                .inputs(TJFMetaBlocks.TRANSPARENT_CASING.getItemVariant(BOROSILICATE_GLASS))
                .fluidInputs(Nickel.getFluid(L))
                .outputs(TJFMetaBlocks.TRANSPARENT_CASING.getItemVariant(NICKEL_GLASS))
                .buildAndRegister();

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(400).EUt(256)
                .inputs(TJFMetaBlocks.TRANSPARENT_CASING.getItemVariant(NICKEL_GLASS))
                .fluidInputs(Chrome.getFluid(L))
                .outputs(TJFMetaBlocks.TRANSPARENT_CASING.getItemVariant(CHROME_GLASS))
                .buildAndRegister();

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(400).EUt(1024)
                .inputs(TJFMetaBlocks.TRANSPARENT_CASING.getItemVariant(CHROME_GLASS))
                .fluidInputs(Tungsten.getFluid(L))
                .outputs(TJFMetaBlocks.TRANSPARENT_CASING.getItemVariant(TUNGSTEN_GLASS))
                .buildAndRegister();

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(400).EUt(4096)
                .inputs(TJFMetaBlocks.TRANSPARENT_CASING.getItemVariant(TUNGSTEN_GLASS))
                .fluidInputs(Iridium.getFluid(L))
                .outputs(TJFMetaBlocks.TRANSPARENT_CASING.getItemVariant(IRIDIUM_GLASS))
                .buildAndRegister();

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(400).EUt(16384)
                .inputs(TJFMetaBlocks.TRANSPARENT_CASING.getItemVariant(IRIDIUM_GLASS))
                .fluidInputs(Osmiridium.getFluid(L))
                .outputs(TJFMetaBlocks.TRANSPARENT_CASING.getItemVariant(OSMIRIDIUM_GLASS))
                .buildAndRegister();

         */
    }

    private static void coilCasings() {

        /*
        // Fusion Coil Recipes
        ASSEMBLY_LINE_RECIPES.recipeBuilder().EUt(30720).duration(400)
                .inputs(NEUTRON_REFLECTOR.getStackForm(2))
                .inputs(FIELD_GENERATOR_LuV.getStackForm())
                .input(wireGtQuadruple, LuVSuperconductor, 4)
                .input(plate, Osmiridium, 2)
                .input(circuit, IV)
                .fluidInputs(Helium.getFluid(4000))
                .outputs(MetaBlocks.FUSION_CASING.getItemVariant(BlockFusionCasing.CasingType.FUSION_COIL))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().EUt(122880).duration(400)
                .inputs(NEUTRON_REFLECTOR.getStackForm(4))
                .inputs(FIELD_GENERATOR_ZPM.getStackForm())
                .input(wireGtQuadruple, ZPMSuperconductor, 4)
                .input(plate, Rutherfordium, 2)
                .input(circuit, LuV)
                .fluidInputs(Helium.getFluid(4000))
                .outputs(GCYLMetaBlocks.FUSION_CASING.getItemVariant(FUSION_COIL_2))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().EUt(491520).duration(400)
                .inputs(NEUTRON_REFLECTOR.getStackForm(6))
                .inputs(FIELD_GENERATOR_ZPM.getStackForm(2))
                .input(wireGtQuadruple, UVSuperconductor, 4)
                .input(plate, Tritanium, 2)
                .input(circuit, ZPM)
                .fluidInputs(Helium.getFluid(4000))
                .outputs(GCYLMetaBlocks.FUSION_CASING.getItemVariant(FUSION_COIL_3))
                .buildAndRegister();

         */

        // Standard Coils
        /*
        removeRecipeByName("gregtech:heating_coil_cupronickel");
        removeRecipeByName("gregtech:heating_coil_kanthal");
        removeRecipeByName("gregtech:heating_coil_nichrome");
        removeRecipeByName("gregtech:heating_coil_tungstensteel");
        removeRecipeByName("gregtech:heating_coil_hss_g");
        removeRecipeByName("gregtech:heating_coil_naquadah");
        removeRecipeByName("gregtech:heating_coil_naquadah_alloy");
        removeRecipeByName("gregtech:heating_coil_superconductor");
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(wireGtDouble, Cupronickel, 8), getIntegratedCircuit(8));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(wireGtDouble, Kanthal, 8), getIntegratedCircuit(8));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(wireGtDouble, Nichrome, 8), getIntegratedCircuit(8));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(wireGtDouble, TungstenSteel, 8), getIntegratedCircuit(8));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(wireGtDouble, HSSG, 8), getIntegratedCircuit(8));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(wireGtDouble, Naquadah, 8), getIntegratedCircuit(8));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(wireGtDouble, NaquadahAlloy, 8), getIntegratedCircuit(8));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(wireGtDouble, Superconductor, 8), getIntegratedCircuit(8));

         */


        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(8)
                .input(wireGtDouble, Cupronickel, 8)
                .input(dust, AluminoSilicateWool, 12)
                .fluidInputs(Tin.getFluid(L))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(CUPRONICKEL))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(8)
                .input(wireGtDouble, Cupronickel, 8)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(8))
                .fluidInputs(Tin.getFluid(L))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(CUPRONICKEL))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(300).EUt(30)
                .input(wireGtDouble, Kanthal, 8)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(8))
                .fluidInputs(Copper.getFluid(L))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(KANTHAL))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(120)
                .input(wireGtDouble, Nichrome, 8)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(8))
                .fluidInputs(Aluminium.getFluid(L))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(NICHROME))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(480)
                .input(wireGtDouble, RTMAlloy, 8)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(8))
                .fluidInputs(Nichrome.getFluid(L))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(RTM_ALLOY))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(1920)
                .input(wireGtDouble, HSSG, 8)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(16))
                .fluidInputs(Tungsten.getFluid(L))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(HSS_G))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(700).EUt(4096)
                .input(wireGtDouble, Naquadah, 8)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(16))
                .fluidInputs(HSSG.getFluid(L))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(NAQUADAH))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(800).EUt(7680)
                .input(wireGtDouble, NaquadahAlloy, 8)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(16))
                .fluidInputs(Naquadah.getFluid(L))
                .outputs(GCYLMetaBlocks.HEATING_COIL.getItemVariant(NAQUADAH_ALLOY_COIL))
                .buildAndRegister();


        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(500000)
                .input(wireGtDouble, TitanSteel, 8)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(32))
                .fluidInputs(Tritanium.getFluid(L))
                .outputs(GCYLMetaBlocks.HEATING_COIL.getItemVariant(TITAN_STEEL_COIL))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(2000000)
                .input(wireGtDouble, Pikyonium, 8)
                .inputs(HIGHLY_INSULATING_FOIL.getStackForm(8))
                .fluidInputs(Adamantium.getFluid(L))
                .outputs(GCYLMetaBlocks.HEATING_COIL.getItemVariant(PIKYONIUM_COIL))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(8000000)
                .input(wireGtDouble, BlackTitanium, 8)
                .inputs(HIGHLY_INSULATING_FOIL.getStackForm(8))
                .fluidInputs(Vibranium.getFluid(L))
                .outputs(GCYLMetaBlocks.HEATING_COIL.getItemVariant(BLACK_TITANIUM_COIL))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(32000000)
                .input(wireGtDouble, Neutronium, 8)
                .inputs(HIGHLY_INSULATING_FOIL.getStackForm(16))
                .fluidInputs(Cinobite.getFluid(L))
                .outputs(GCYLMetaBlocks.HEATING_COIL.getItemVariant(NEUTRONIUM_COIL))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(32000000)
                .input(wireGtDouble, CosmicNeutronium, 8)
                .inputs(HIGHLY_INSULATING_FOIL.getStackForm(32))
                .fluidInputs(Neutronium.getFluid(L))
                .outputs(GCYLMetaBlocks.HEATING_COIL.getItemVariant(COSMIC_NEUTRONIUM_COIL))
                .buildAndRegister();

        //TODO: eternity and infinity coils?

        /*
        ASSEMBLER_RECIPES.recipeBuilder().duration(1000).EUt(9001)
                .input(wireGtDouble, LuVSuperconductor, 64)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(64))
                .fluidInputs(NaquadahAlloy.getFluid(L * 8))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(SUPERCONDUCTOR))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(1000).EUt(9001)
                .input(wireGtDouble, ZPMSuperconductor, 32)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(32))
                .fluidInputs(NaquadahAlloy.getFluid(L * 4))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(SUPERCONDUCTOR))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(1000).EUt(9001)
                .input(wireGtDouble, UVSuperconductor, 16)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(16))
                .fluidInputs(NaquadahAlloy.getFluid(L * 2))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(SUPERCONDUCTOR))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(1000).EUt(9001)
                .input(wireGtDouble, Superconductor, 8)
                .inputs(MICA_INSULATOR_FOIL.getStackForm(8))
                .fluidInputs(NaquadahAlloy.getFluid(L))
                .outputs(MetaBlocks.WIRE_COIL.getItemVariant(SUPERCONDUCTOR))
                .buildAndRegister();

         */
    }

    private static void multiblockCasings() {


        // Reactor Casing
        FORMING_PRESS_RECIPES.recipeBuilder().duration(1500).EUt(500)
                .input(plateDense, Lead, 9)
                .input(plateDense, Lead, 9)
                .input(plateDense, ReactorSteel, 4)
                .input(plateDense, StainlessSteel, 2)
                .outputs(GCYLMetaBlocks.REACTOR_CASING.getItemVariant(CLADDED_REACTOR_CASING, 4))
                .buildAndRegister();


        //TODO: Precise assembler?

        // Large Assembler Casing
        /*
        ASSEMBLER_RECIPES.recipeBuilder().EUt(8000).duration(600)
                .fluidInputs(HastelloyN.getFluid(L * 4))
                .inputs(TJFMetaBlocks.METAL_CASING_2.getItemVariant(MetalCasing2.CasingType.STABALLOY, 2))
                .input(circuit, Advanced)
                .outputs(TJFMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(LARGE_ASSEMBLER, 2))
                .buildAndRegister();

         */

        //TODO: Advanced laser engraver?

        // Laser-Safe Casing
        /*
        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(7680)
                .fluidInputs(Polybenzimidazole.getFluid(144))
                .input(frameGt, Zeron100)
                .input(plate, HG1223, 6)
                .input("dyeBlack", 6)
                .outputs(TJFMetaBlocks.MUTLIBLOCK_CASING2.getItemVariant(LASER_ENGRAVER, 2))
                .buildAndRegister();

         */


        // Stellar Containment Casing
        ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(500000)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .input(frameGt, Trinium)
                .input(plate, HDCS, 6)
                .input(stick, EnrichedNaquadahAlloy, 4)
                .input(screw, Trinium, 8)
                .outputs(GCYLMetaBlocks.MULTIBLOCK_CASING2.getItemVariant(STELLAR_CONTAINMENT, 4))
                .buildAndRegister();

        // Hyper Reactor Casing
        ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(500000)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .input(frameGt, Naquadria)
                .input(screw, EnrichedNaquadahAlloy, 16)
                .input(plate, Incoloy813, 8)
                .outputs(GCYLMetaBlocks.REACTOR_CASING.getItemVariant(HYPER_CASING, 4))
                .buildAndRegister();

        // Hyper Reactor Casing 2
        ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(2000000)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .inputs(GCYLMetaBlocks.REACTOR_CASING.getItemVariant(HYPER_CASING))
                .input(screw, Pikyonium, 16)
                .input(plate, TitanSteel, 8)
                .outputs(GCYLMetaBlocks.REACTOR_CASING.getItemVariant(HYPER_CASING_2, 4))
                .buildAndRegister();

        // Hyper Core
        ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(500000)
                .inputs(FIELD_GENERATOR_UV.getStackForm(2))
                .inputs(SENSOR_UV.getStackForm())
                .inputs(EMITTER_UV.getStackForm())
                .input(frameGt, Naquadria, 4)
                .input(screw, Dubnium, 16)
                .input(plate, Naquadria, 4)
                .input(circuit, UV)
                .outputs(GCYLMetaBlocks.REACTOR_CASING.getItemVariant(HYPER_CORE, 4))
                .buildAndRegister();

        // Hyper Core 2
        ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(2000000)
                .inputs(FIELD_GENERATOR_UHV.getStackForm(2))
                .inputs(SENSOR_UHV.getStackForm())
                .inputs(EMITTER_UHV.getStackForm())
                .inputs(GCYLMetaBlocks.REACTOR_CASING.getItemVariant(HYPER_CASING, 2))
                .input(screw, Rutherfordium, 16)
                .input(plate, TungstenTitaniumCarbide, 4)
                .input(circuit, UHV)
                .outputs(GCYLMetaBlocks.REACTOR_CASING.getItemVariant(HYPER_CORE_2, 4))
                .buildAndRegister();

        // Hyper Core 3
        ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8000000)
                .inputs(FIELD_GENERATOR_UEV.getStackForm(2))
                .inputs(SENSOR_UEV.getStackForm())
                .inputs(EMITTER_UEV.getStackForm())
                .inputs(GCYLMetaBlocks.REACTOR_CASING.getItemVariant(HYPER_CASING_2, 2))
                .input(screw, TriniumTitanium, 16)
                .input(plate, TitanSteel, 4)
                .input(circuit, UEV)
                .outputs(GCYLMetaBlocks.REACTOR_CASING.getItemVariant(HYPER_CORE_3, 4))
                .buildAndRegister();

        // Bio Reactor Casing
        ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(120000)
                .input(frameGt, HSSS)
                .input(plate, NaquadahAlloy, 4)
                .input(screw, Dubnium, 4)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(GCYLMetaBlocks.MULTIBLOCK_CASING2.getItemVariant(GCYLMultiblockCasing2.CasingType.BIO_REACTOR, 2))
                .buildAndRegister();

        //WIRELESS PSS CASING
        ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(GTValues.VA[GTValues.UV])
                .input(plate, Enderium, 6)
                .input(frameGt, Seaborgium)
                .inputs(FIELD_GENERATOR_UV.getStackForm())
                .circuitMeta(6)
                .fluidInputs(Indalloy140.getFluid(L * 2))
                .outputs(GCYLMetaBlocks.MULTIBLOCK_CASING2.getItemVariant(GCYLMultiblockCasing2.CasingType.SEABORGIUM_SUBSTATION, ConfigHolder.recipes.casingsPerCraft))
                .buildAndRegister();

    }

    //TODO: component casings?

    /*
    private static void componentCasings() {

        Arrays.stream(EmitterCasing.CasingType.values()).forEach(casing ->
                registerComponentBlockRecipe(casing.getTier(), casing, GACraftingComponents.EMITTER, TJFMetaBlocks.EMITTER_CASING));

        Arrays.stream(MotorCasing.CasingType.values()).forEach(casing ->
                registerComponentBlockRecipe(casing.getTier(), casing, GACraftingComponents.MOTOR, TJFMetaBlocks.MOTOR_CASING));

        Arrays.stream(PistonCasing.CasingType.values()).forEach(casing ->
                registerComponentBlockRecipe(casing.getTier(), casing, GACraftingComponents.PISTON, TJFMetaBlocks.PISTON_CASING));

        Arrays.stream(SensorCasing.CasingType.values()).forEach(casing ->
                registerComponentBlockRecipe(casing.getTier(), casing, GACraftingComponents.SENSOR, TJFMetaBlocks.SENSOR_CASING));

        Arrays.stream(FieldGenCasing.CasingType.values()).forEach(casing ->
                registerComponentBlockRecipe(casing.getTier(), casing, GACraftingComponents.FIELD_GENERATOR, TJFMetaBlocks.FIELD_GEN_CASING));

        Arrays.stream(PumpCasing.CasingType.values()).forEach(casing ->
                registerComponentBlockRecipe(casing.getTier(), casing, GACraftingComponents.PUMP, TJFMetaBlocks.PUMP_CASING));

        Arrays.stream(ConveyorCasing.CasingType.values()).forEach(casing ->
                registerComponentBlockRecipe(casing.getTier(), casing, GACraftingComponents.CONVEYOR, TJFMetaBlocks.CONVEYOR_CASING));

        Arrays.stream(RobotArmCasing.CasingType.values()).forEach(casing ->
                registerComponentBlockRecipe(casing.getTier(), casing, GACraftingComponents.ROBOT_ARM, TJFMetaBlocks.ROBOT_ARM_CASING));
    }


    private static <T extends Enum<T> & IStringSerializable> void registerComponentBlockRecipe(int tier, T inputComponent, GACraftingComponents inputStack, VariantBlock<T> outputCasing) {

        ItemStack stack = ((MetaItem<?>.MetaValueItem) inputStack.getIngredient(tier)).getStackForm(2);
        ItemStack hull = (ItemStack) GACraftingComponents.HULL.getIngredient(tier);
        UnificationEntry cable = (UnificationEntry) GACraftingComponents.CABLE_SINGLE.getIngredient(tier);

        ASSEMBLER_RECIPES.recipeBuilder().EUt((int) (30 * Math.pow(4, tier - 1))).duration(200)
                .inputs(stack)
                .inputs(hull)
                .input(cableGtSingle, cable.material, 8)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .outputs(outputCasing.getItemVariant(inputComponent))
                .buildAndRegister();
    }

     */

}
