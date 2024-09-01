package com.fulltrix.gcyl.recipes.categories;

import gregicality.multiblocks.api.fluids.GCYMFluidStorageKeys;
import gregtech.api.GTValues;
import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.chance.output.ChancedOutputLogic;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.stack.UnificationEntry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static com.fulltrix.gcyl.api.GCYLUtility.getAssLineResearchBuilder;
import static com.fulltrix.gcyl.api.GCYLUtility.getPolymerByTier;
import static com.fulltrix.gcyl.item.GCYLCoreItems.*;
import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static com.fulltrix.gcyl.materials.GCYLNuclearMaterials.*;
import static com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps.DECAY_CHAMBERS_RECIPES;
import static gregicality.multiblocks.api.recipes.GCYMRecipeMaps.ALLOY_BLAST_RECIPES;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.Materials.Bismuth;
import static gregtech.api.unification.material.Materials.Plutonium;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;
import static gregtech.common.metatileentities.MetaTileEntities.*;
import static gregtech.loaders.recipe.MetaTileEntityLoader.registerMachineRecipe;
import static kono.ceu.materialreplication.api.unification.materials.MRMaterials.NeutralMatter;

public class MiscRecipes {
    public static void init() {
        initSolars();
highTierVoltageCoils();

        //TODO: FINISH NUCLEAR AND REMOVE
        temporaryNuclearRecipes();

        //eglin steel dust and base dust
        MIXER_RECIPES.recipeBuilder().duration(20 * 10).EUt(120)
                .input(dust, Iron, 4)
                .input(dust, Kanthal)
                .input(dust, Invar, 5)
                .circuitMeta(3)
                .output(dust, EglinSteelBase, 10)
                .buildAndRegister();


        //Babbitt alloy
        MIXER_RECIPES.recipeBuilder().duration(50 * 20).EUt(GTValues.VA[ZPM])
                .input(dust, Tin, 5)
                .input(dust, Lead, 36)
                .input(dust, Antimony)
                .input(dust, Arsenic)
                .circuitMeta(4)
                .output(dust, BabbittAlloy, 50)
                .buildAndRegister();

        //Ender Item Link
        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, EnderPearl, 9)
                .input(plateDouble, StainlessSteel)
                .input(SENSOR_HV)
                .input(EMITTER_HV)
                .input(ELECTRIC_PISTON_HV)
                .fluidInputs(Polyethylene.getFluid(L * 2))
                .output(COVER_ENDER_ITEM_LINK)
                .EUt(VA[HV]).duration(320)
                .buildAndRegister();

        //Rock breaker recipes for netherrack and endstone
        ROCK_BREAKER_RECIPES.recipeBuilder()
                .notConsumable(new ItemStack(Blocks.NETHERRACK))
                .duration(16)
                .EUt(960)
                .outputs(new ItemStack(Blocks.NETHERRACK))
                .buildAndRegister();

        ROCK_BREAKER_RECIPES.recipeBuilder()
                .notConsumable(new ItemStack(Blocks.END_STONE))
                .duration(16)
                .EUt(3840)
                .dimension(1)
                .outputs(new ItemStack(Blocks.END_STONE))
                .buildAndRegister();

        //liquid nitrogen
        VACUUM_RECIPES.recipeBuilder().EUt(1920).duration(200)
                .fluidInputs(Nitrogen.getFluid(1000))
                .fluidOutputs(Nitrogen.getFluid(FluidStorageKeys.LIQUID, 1000))
                .buildAndRegister();

        //liquid helium-3
        VACUUM_RECIPES.recipeBuilder().EUt(30720).duration(200)
                .fluidInputs(Helium3.getFluid(1000))
                .fluidOutputs(Helium3.getFluid(FluidStorageKeys.LIQUID, 1000))
                .buildAndRegister();

        //carbon fibers from gcyl polymers
        AUTOCLAVE_RECIPES.recipeBuilder().EUt(GTValues.VA[IV]).duration(37)
                .fluidInputs(Polyetheretherketone.getFluid(9))
                .input(dust, Carbon, 8)
                .outputs(CARBON_FIBERS.getStackForm(32))
                .buildAndRegister();

        AUTOCLAVE_RECIPES.recipeBuilder().EUt(GTValues.VA[LuV]).duration(37)
                .fluidInputs(Zylon.getFluid(9))
                .input(dust, Carbon, 8)
                .outputs(CARBON_FIBERS.getStackForm(64))
                .buildAndRegister();

        AUTOCLAVE_RECIPES.recipeBuilder().EUt(GTValues.VA[ZPM]).duration(37)
                .fluidInputs(FullerenePolymerMatrix.getFluid(9))
                .input(dust, Carbon, 4)
                .outputs(CARBON_FIBERS.getStackForm(64))
                .buildAndRegister();

        //WIRELESS BATTERY
        ASSEMBLY_LINE_RECIPES.recipeBuilder().EUt(GTValues.VA[GTValues.UV]).duration(1200)
                .outputs(WIRELESS_BATTERY_UV.getStackForm())
                .inputs(ENERGY_CLUSTER.getStackForm())
                .input(circuit, MarkerMaterials.Tier.UV, 16)
                .inputs(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64))
                .inputs(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64))
                .inputs(SENSOR_UV.getStackForm(6))
                .inputs(FIELD_GENERATOR_UV.getStackForm(3))
                .input(wireGtDouble, UVSuperconductor, 64)
                .input(wireGtDouble, UVSuperconductor, 64)
                .fluidInputs(Indalloy140.getFluid( L * 32))
                .fluidInputs(getPolymerByTier(UV).getFluid(L * 32))
                .fluidInputs(Seaborgium.getFluid( L * 32))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        //magnetic fluids
        POLARIZER_RECIPES.recipeBuilder().EUt(256).duration(144)
                .fluidInputs(Neodymium.getFluid(144))
                .fluidOutputs(NeodymiumMagnetic.getFluid(144))
                .buildAndRegister();

        POLARIZER_RECIPES.recipeBuilder().EUt(4096).duration(150)
                .fluidInputs(Samarium.getFluid(144))
                .fluidOutputs(SamariumMagnetic.getFluid(144))
                .buildAndRegister();

        // Ductile A356
        ALLOY_BLAST_RECIPES.recipeBuilder().EUt(VA[ZPM]).duration(300)
                .fluidInputs(Argon.getFluid(1000))
                .circuitMeta(5)
                .input(dust, Aluminium, 33)
                .input(dust, Silicon, 2)
                .input(dust, Magnesium)
                .input(dust, Iron)
                .input(dust, Europium)
                .blastFurnaceTemp(2300)
                .fluidOutputs(DuctileA356.getFluid(GCYMFluidStorageKeys.MOLTEN,L * 38))
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder().EUt(VA[LuV]).duration(150)
                .fluidInputs(DuctileA356.getFluid(GCYMFluidStorageKeys.MOLTEN, 144))
                .fluidOutputs(DuctileA356.getFluid(144))
                .buildAndRegister();

        //sodium hydroxide loop
        /*
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(100)
                .circuitMeta(1)
                .fluidInputs(Water.getFluid(1000))
                .input(dust, Sodium)
                .output(dust, SodiumHydroxide,3)
                .fluidOutputs(Hydrogen.getFluid(1000))
                .buildAndRegister();

         */

    }

    public static void initSolars() {

        //TODO: add additional recipes to these
        //TODO: add max solar panel

        ModHandler.addShapedRecipe("gcyl_solar_panel_lv", COVER_SOLAR_PANEL_LV.getStackForm(),
                "SGS", "CHC", "WBW",
                'S', SENSOR_EV,
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.EV),
                'W', new UnificationEntry(cableGtSingle, Aluminium),
                'B', BATTERY_LV_CADMIUM,
                'G', new UnificationEntry(gemExquisite, Diamond),
                'H', HULL[GTValues.EV].getStackForm());

        ModHandler.addShapedRecipe("gcyl_solar_panel_mv", COVER_SOLAR_PANEL_MV.getStackForm(),
                "SGS", "CHC", "WBW",
                'S', SENSOR_IV,
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.IV),
                'W', new UnificationEntry(cableGtSingle, Tungsten),
                'B', BATTERY_MV_CADMIUM,
                'G', new UnificationEntry(gemExquisite, Ruby),
                'H', HULL[GTValues.IV].getStackForm());

        ModHandler.addShapedRecipe("gcyl_solar_panel_hv", COVER_SOLAR_PANEL_HV.getStackForm(),
                "SGS", "CHC", "WBW",
                'S', SENSOR_LuV,
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.LuV),
                'W', new UnificationEntry(cableGtSingle, YttriumBariumCuprate),
                'B', BATTERY_HV_CADMIUM,
                'G', new UnificationEntry(gemExquisite, Ruby),
                'H', HULL[GTValues.LuV].getStackForm());

        ModHandler.addShapedRecipe("gcyl_solar_panel_ev", COVER_SOLAR_PANEL_EV.getStackForm(),
                "SGS", "CHC", "WBW",
                'S', SENSOR_ZPM,
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.ZPM),
                'W', new UnificationEntry(cableGtSingle, Naquadah),
                'B', BATTERY_NIMH,
                'G', new UnificationEntry(gemExquisite, Emerald),
                'H', HULL[GTValues.ZPM].getStackForm());

        ModHandler.addShapedRecipe("gcyl_solar_panel_iv", COVER_SOLAR_PANEL_IV.getStackForm(),
                "SGS", "CHC", "WBW",
                'S', SENSOR_UV,
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.UV),
                'W', new UnificationEntry(cableGtSingle, Duranium),
                'B', BATTERY_SMALL_LITHIUM_ION,
                'G', new UnificationEntry(gemExquisite, BlueTopaz),
                'H', HULL[GTValues.UV].getStackForm());

        ModHandler.addShapedRecipe("gcyl_solar_panel_luv", COVER_SOLAR_PANEL_LUV.getStackForm(),
                "SGS", "CHC", "WBW",
                'S', SENSOR_UHV,
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.UHV),
                'W', new UnificationEntry(cableGtSingle, AbyssalAlloy),
                'B', BATTERY_MEDIUM_LITHIUM_ION,
                'G', new UnificationEntry(gemExquisite, RhodiumSalt),
                'H', HULL[GTValues.UHV].getStackForm());

        ModHandler.addShapedRecipe("gcyl_solar_panel_zpm", COVER_SOLAR_PANEL_ZPM.getStackForm(),
                "SGS", "CHC", "WBW",
                'S', SENSOR_UEV,
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.UEV),
                'W', new UnificationEntry(cableGtSingle, TitanSteel),
                'B', BATTERY_LARGE_LITHIUM_ION,
                'G', new UnificationEntry(gemExquisite, CubicZirconia),
                'H', HULL[GTValues.UEV].getStackForm());

        ModHandler.addShapedRecipe("gcyl_solar_panel_uv", COVER_SOLAR_PANEL_UV.getStackForm(),
                "SGS", "CHC", "WBW",
                'S', SENSOR_UIV,
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.UIV),
                'W', new UnificationEntry(cableGtSingle, BlackTitanium),
                'B', BATTERY_SMALL_LIS,
                'G', new UnificationEntry(gemExquisite, LeadZirconateTitanate),
                'H', HULL[GTValues.UIV].getStackForm());


    }

    public static void temporaryNuclearRecipes() {


        //alpha decay

        //beta decay
        DECAY_CHAMBERS_RECIPES.recipeBuilder().EUt(1920).duration(300)
                .input(dust, Bismuth210, 1)
                .chancedOutputLogic(ChancedOutputLogic.XOR)
                .chancedOutput(dust, Polonium, 5000, 0)
                .chancedOutput(dust, Bismuth, 10000, 0)
                .buildAndRegister();

        DECAY_CHAMBERS_RECIPES.recipeBuilder().EUt(1920).duration(300)
                .input(dust, Uranium238, 1)
                .fluidInputs(NeutralMatter.getFluid(200))
                .chancedOutputLogic(ChancedOutputLogic.XOR)
                .chancedOutput(dust, Plutonium, 2000, 0)
                .chancedOutput(dust, Uranium, 10000, 0)
                .buildAndRegister();


        DECAY_CHAMBERS_RECIPES.recipeBuilder().EUt(GTValues.VA[GTValues.IV]).duration(600)
                .input(dust, Plutonium241)
                .chancedOutputLogic(ChancedOutputLogic.XOR)
                .chancedOutput(dust, Americium, 2000, 0)
                .chancedOutput(dust, Plutonium, 10000, 0)
                .buildAndRegister();

        //isotopes
        DECAY_CHAMBERS_RECIPES.recipeBuilder().EUt(1920).duration(300)
                .input(dust, Bismuth, 1)
                .fluidInputs(NeutralMatter.getFluid(100))
                .chancedOutputLogic(ChancedOutputLogic.XOR)
                .chancedOutput(dust, Bismuth210, 2500, 0)
                .chancedOutput(dust, Bismuth, 10000, 0)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().EUt(320).duration(1600)
                .input(dust, Uranium)
                .chancedOutputLogic(ChancedOutputLogic.XOR)
                .chancedOutput(dust, Uranium235, 100,0)
                .chancedOutput(dust,Uranium238, 10000,0)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().EUt(320).duration(1600)
                .input(dust, Plutonium)
                .chancedOutputLogic(ChancedOutputLogic.XOR)
                .chancedOutput(dust, Plutonium244, 2500,0)
                .chancedOutput(dust,Plutonium241, 5000,0)
                .chancedOutput(dust,Plutonium239, 10000,0)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().EUt(320).duration(1600)
                .input(dust, Plutonium241)
                .chancedOutput(dust, Plutonium244, 2000, 300)
                .chancedOutput(dustTiny, Uranium238, 3000, 450)
                .buildAndRegister();

        //NUCLEAR STAR
        AUTOCLAVE_RECIPES.recipeBuilder().EUt(GTValues.VA[GTValues.UIV]).duration(600)
                .inputs(UNSTABLE_STAR.getStackForm(16))
                .fluidInputs(HeavyQuarkDegenerateMatter.getFluid(L * 8))
                .outputs(NUCLEAR_STAR.getStackForm())
                .buildAndRegister();

        //liquid deep overworld and nether gas
        VACUUM_RECIPES.recipeBuilder().EUt(GTValues.VA[GTValues.EV]).duration(400)
                .fluidInputs(DeepOverworldGas.getFluid(4000))
                .fluidOutputs(DeepOverworldGas.getFluid(FluidStorageKeys.LIQUID, 4000))
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder().EUt(GTValues.VA[GTValues.IV]).duration(400)
                .fluidInputs(DeepNetherGas.getFluid(4000))
                .fluidOutputs(DeepNetherGas.getFluid(FluidStorageKeys.LIQUID, 4000))
                .buildAndRegister();

        //distillation of deep gases
        DISTILLATION_RECIPES.recipeBuilder().EUt(GTValues.VA[GTValues.EV]).duration(2000)
                .fluidInputs(DeepOverworldGas.getFluid(FluidStorageKeys.LIQUID, 128000))
                .fluidOutputs(SaltWater.getFluid(64000))
                .fluidOutputs(Chlorine.getFluid(32000))
                .fluidOutputs(Fluorine.getFluid(32000))
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder().EUt(GTValues.VA[GTValues.IV]).duration(2000)
                .fluidInputs(DeepNetherGas.getFluid(FluidStorageKeys.LIQUID, 256000))
                .fluidOutputs(Oil.getFluid(128000))
                .fluidOutputs(IodizedOil.getFluid(64000))
                .fluidOutputs(CharcoalByproducts.getFluid(32000))
                .fluidOutputs(Toluene.getFluid(16000))
                .fluidOutputs(Octane.getFluid(16000))
                .buildAndRegister();

        // Silicon Carbide
        BLAST_RECIPES.recipeBuilder().EUt(120).duration(3000).blastFurnaceTemp(2500)
                .input(dust, Silicon)
                .input(dust, Carbon)
                .notConsumable(new IntCircuitIngredient(2))
                .notConsumable(Argon.getFluid(1))
                .output(dust, SiliconCarbide, 2)
                .buildAndRegister();

        // Snow Dust
        MACERATOR_RECIPES.recipeBuilder().EUt(2).duration(60)
                .inputs(new ItemStack(Blocks.SNOW))
                .output(dust, Snow, 4)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder().EUt(2).duration(15)
                .inputs(new ItemStack(Items.SNOWBALL))
                .output(dust, Snow)
                .buildAndRegister();
    }

    private static void highTierVoltageCoils() {
        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(VA[UHV])
                .circuitMeta(1)
                .input(stick, SamariumMagnetic)
                .input(wireFine, Seaborgium, 16)
                .outputs(VOLTAGE_COIL_UHV.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(VA[UEV])
                .circuitMeta(1)
                .input(stick, SamariumMagnetic)
                .input(wireFine, Bohrium, 16)
                .outputs(VOLTAGE_COIL_UEV.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(VA[UIV])
                .circuitMeta(1)
                .input(stick, SamariumMagnetic)
                .input(wireFine, MetastableHassium, 16)
                .outputs(VOLTAGE_COIL_UIV.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(VA[UXV])
                .circuitMeta(1)
                .input(stick, SamariumMagnetic)
                .input(wireFine, HeavyQuarkDegenerateMatter, 16)
                .outputs(VOLTAGE_COIL_UXV.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(VA[OpV])
                .circuitMeta(1)
                .input(stick, NaquadriaticTaranium)
                .input(wireFine, Neutronium, 16)
                .outputs(VOLTAGE_COIL_OpV.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(VA[MAX])
                .circuitMeta(1)
                .input(stick, NaquadriaticTaranium)
                .input(wireFine, CosmicNeutronium, 16)
                .outputs(VOLTAGE_COIL_MAX.getStackForm())
                .buildAndRegister();

    }
}
