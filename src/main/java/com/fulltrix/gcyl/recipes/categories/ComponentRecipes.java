package com.fulltrix.gcyl.recipes.categories;

import gregtech.api.GTValues;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.api.unification.stack.UnificationEntry;

import static com.fulltrix.gcyl.GCYLMaterials.*;
import static com.fulltrix.gcyl.item.GCYLCoreItems.*;
import static gregicality.multiblocks.api.unification.GCYMMaterials.Zeron100;
import static gregtech.api.GTValues.L;
import static gregtech.api.GTValues.LV;
import static gregtech.api.GTValues.VA;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.MarkerMaterials.Tier.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.Materials.Lubricant;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;
import static gregtech.common.metatileentities.MetaTileEntities.ADVANCED_LARGE_MINER;

public class ComponentRecipes {
    private static final MaterialStack[] cableFluids = {
            new MaterialStack(Rubber, 144),
            new MaterialStack(StyreneButadieneRubber, 108),
            new MaterialStack(SiliconeRubber, 72)

    };

    public static void init() {
        pumpInit();
        motorInit();
        pistonInit();
        conveyorInit();
        robotArmInit();
        fieldGenInit();
        sensorInit();
        emitterInit();
        opvComponents();
        maxComponents();
    }

    //TODO: add research to recipes and change to be more in line with CEu

    private static void emitterInit() {

        /*
        Original LuV Emitter
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15360)
                .input(frameGt, HSSG)
                .input(dust,ZincSelenide,16)
                .input(foil, Electrum, 64)
                .input(wireGtDouble, YttriumBariumCuprate, 8)
                .input(gemExquisite, Ruby, 2)
                .input(circuit, LuV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(EMITTER_LuV.getStackForm())
                .buildAndRegister();

         */

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15360)
                .input(frameGt, HSSS)
                .inputs(ELECTRIC_MOTOR_LuV.getStackForm())
                .input(stickLong, Ruridit,4)
                .inputs(QUANTUM_STAR.getStackForm())
                .input(dust,ZincSelenide,16)
                .input(wireGtDouble, YttriumBariumCuprate, 8)
                .input(gemExquisite, Ruby, 2)
                .input(circuit, LuV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L*2))
                .outputs(EMITTER_LuV.getStackForm())
                .scannerResearch(EMITTER_IV.getStackForm())
                .buildAndRegister();

        /*
        Original ZPM Emitter
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(61440)
                .input(frameGt, HSSE)
                .input(dust,Fluorescein,16)
                .input(foil, Platinum, 64)
                .input(wireGtDouble, Naquadah, 8)
                .input(gemExquisite, Emerald, 2)
                .input(circuit, ZPM, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(EMITTER_ZPM.getStackForm())
                .buildAndRegister();
         */

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(61440)
                .input(frameGt, HSSE)
                .input(dust,Fluorescein,16)
                .input(foil, Platinum, 64)
                .input(wireGtDouble, Naquadah, 8)
                .input(gemExquisite, Emerald, 2)
                .input(circuit, ZPM, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(EMITTER_ZPM.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(245760)
                .input(frameGt, Tritanium)
                .input(dust,Stilbene,16)
                .input(foil, Osmiridium, 32)
                .input(wireGtDouble, Duranium, 8)
                .input(gemExquisite, Diamond, 2)
                .input(circuit, UV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(EMITTER_UV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(983040)
                .input(frameGt, HDCS, 1)
                .input(dust,FranciumCaesiumCadmiumBromide,16)
                .input(foil, Osmiridium, 64)
                .input(cableGtSingle, TungstenTitaniumCarbide, 8)
                .input(gemExquisite, Diamond, 2)
                .input(circuit, UHV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .outputs(EMITTER_UHV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(3932160)
                .input(frameGt, EnrichedNaquadahAlloy)
                .input(dust,RhodamineB,16)
                .input(foil, Osmiridium, 64)
                .input(foil, Osmiridium, 64)
                .input(cableGtSingle, Pikyonium, 8)
                .input(gemExquisite, Diamond, 2)
                .input(circuit, UEV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .outputs(EMITTER_UEV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(7864320)
                .input(frameGt, HastelloyX78)
                .inputs(LEPTON_TRAP_CRYSTAL.getStackForm(4))
                .input(foil, TriniumTitanium, 64)
                .input(cableGtSingle, BlackTitanium, 8)
                .input(gemExquisite, Diamond, 2)
                .input(circuit, UIV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 6))
                .outputs(EMITTER_UIV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15728640)
                .input(frameGt, HastelloyK243)
                .inputs(CHARGED_LEPTON_TRAP_CRYSTAL.getStackForm(4))
                .input(foil, ProtoAdamantium, 64)
                .input(cableGtSingle, NaquadriaticTaranium, 8)
                .input(gemExquisite, Diamond, 2)
                .input(circuit, UXV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 8))
                .outputs(EMITTER_UXV.getStackForm())
                .buildAndRegister();
    }

    private static void sensorInit() {

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15360)
                .input(frameGt, HSSG)
                .input(dust, Germanium, 16)
                .input(foil, Electrum, 64)
                .input(wireGtDouble, YttriumBariumCuprate, 8)
                .input(gemExquisite, Ruby, 2)
                .input(circuit, LuV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(SENSOR_LuV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(61440)
                .input(frameGt, HSSE)
                .input(dust,LeadSenenide,16)
                .input(foil, Platinum, 64)
                .input(wireGtDouble, Naquadah, 8)
                .input(gemExquisite, Emerald, 2)
                .input(circuit, ZPM, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(SENSOR_ZPM.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(245760)
                .input(frameGt, Tritanium, 1)
                .input(dust,BariumStrontiumTitanate,16)
                .input(foil, Osmiridium, 32)
                .input(wireGtDouble, Duranium, 8)
                .input(gemExquisite, Diamond, 2)
                .input(circuit, UV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(SENSOR_UV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(983040)
                .input(frameGt, HDCS)
                .input(dust,LeadScandiumTantalate,16)
                .input(foil, Osmiridium, 64)
                .input(cableGtSingle, TungstenTitaniumCarbide, 8)
                .input(gemExquisite, Diamond, 2)
                .inputs(BOSE_EINSTEIN_COOLING_CONTAINER.getStackForm())
                .input(circuit, UHV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .outputs(SENSOR_UHV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(3932160)
                .input(frameGt, EnrichedNaquadahAlloy)
                .input(dust,MagnetorestrictiveAlloy,16)
                .input(foil, Osmiridium, 64)
                .input(foil, Osmiridium, 64)
                .input(cableGtSingle, Pikyonium, 8)
                .input(gemExquisite, Diamond, 2)
                .inputs(BOSE_EINSTEIN_COOLING_CONTAINER.getStackForm(2))
                .input(circuit, UEV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .outputs(SENSOR_UEV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(7864320)
                .input(frameGt, HastelloyX78)
                .input(foil, TriniumTitanium, 64)
                .input(cableGtSingle, BlackTitanium, 8)
                .input(gemExquisite, Diamond, 2)
                .inputs(BOSE_EINSTEIN_COOLING_CONTAINER.getStackForm(8))
                .input(circuit, UIV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 6))
                .fluidInputs(LiquidCrystalDetector.getFluid(2500))
                .outputs(SENSOR_UIV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15728640)
                .input(frameGt, HastelloyK243)
                .input(foil, ProtoAdamantium, 64)
                .input(cableGtSingle, NaquadriaticTaranium, 8)
                .input(gemExquisite, Diamond, 2)
                .inputs(BOSE_EINSTEIN_COOLING_CONTAINER.getStackForm(8))
                .input(circuit, UXV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 6))
                .fluidInputs(FreeElectronGas.getFluid(2500))
                .outputs(SENSOR_UXV.getStackForm())
                .buildAndRegister();
    }

    private static void fieldGenInit() {
        ModHandler.addShapedRecipe(true, "field_generator_lv", FIELD_GENERATOR_LV.getStackForm(), "WPW", "XGX", "WPW",
                'W', new UnificationEntry(wireGtQuadruple, LVSuperconductor), 'P', new UnificationEntry(plate, Steel),
                'G', new UnificationEntry(gem, EnderPearl), 'X', new UnificationEntry(circuit, MarkerMaterials.Tier.LV));
        ModHandler.addShapedRecipe(true, "field_generator_mv", FIELD_GENERATOR_MV.getStackForm(), "WPW", "XGX", "WPW",
                'W', new UnificationEntry(wireGtQuadruple, MVSuperconductor), 'P',
                new UnificationEntry(plate, Aluminium), 'G', new UnificationEntry(gem, EnderEye), 'X',
                new UnificationEntry(circuit, MarkerMaterials.Tier.MV));
        ModHandler.addShapedRecipe(true, "field_generator_hv", FIELD_GENERATOR_HV.getStackForm(), "WPW", "XGX", "WPW",
                'W', new UnificationEntry(wireGtQuadruple, HVSuperconductor), 'P',
                new UnificationEntry(plate, StainlessSteel), 'G', QUANTUM_EYE.getStackForm(), 'X',
                new UnificationEntry(circuit, MarkerMaterials.Tier.HV));
        ModHandler.addShapedRecipe(true, "field_generator_ev", FIELD_GENERATOR_EV.getStackForm(), "WPW", "XGX", "WPW",
                'W', new UnificationEntry(wireGtQuadruple, EVSuperconductor), 'P',
                new UnificationEntry(plateDouble, Titanium), 'G', new UnificationEntry(gem, NetherStar), 'X',
                new UnificationEntry(circuit, MarkerMaterials.Tier.EV));
        ModHandler.addShapedRecipe(true, "field_generator_iv", FIELD_GENERATOR_IV.getStackForm(), "WPW", "XGX", "WPW",
                'W', new UnificationEntry(wireGtQuadruple, IVSuperconductor), 'P',
                new UnificationEntry(plateDouble, TungstenSteel), 'G', QUANTUM_STAR.getStackForm(), 'X',
                new UnificationEntry(circuit, MarkerMaterials.Tier.IV));

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(gem, EnderPearl)
                .input(plate, Steel, 2)
                .input(circuit, MarkerMaterials.Tier.LV, 2)
                .input(wireGtQuadruple, LVSuperconductor, 4)
                .outputs(FIELD_GENERATOR_LV.getStackForm())
                .duration(100).EUt(VA[LV]).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(gem, EnderEye)
                .input(plate, Aluminium, 2)
                .input(circuit, MarkerMaterials.Tier.MV, 2)
                .input(wireGtQuadruple, MVSuperconductor, 4)
                .outputs(FIELD_GENERATOR_MV.getStackForm())
                .duration(100).EUt(VA[LV]).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(QUANTUM_EYE)
                .input(plate, StainlessSteel, 2)
                .input(circuit, MarkerMaterials.Tier.HV, 2)
                .input(wireGtQuadruple, HVSuperconductor, 4)
                .outputs(FIELD_GENERATOR_HV.getStackForm())
                .duration(100).EUt(VA[LV]).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(gem, NetherStar)
                .input(plateDouble, Titanium, 2)
                .input(circuit, MarkerMaterials.Tier.EV, 2)
                .input(wireGtQuadruple, EVSuperconductor, 4)
                .outputs(FIELD_GENERATOR_EV.getStackForm())
                .duration(100).EUt(VA[LV]).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(QUANTUM_STAR)
                .input(plateDouble, TungstenSteel, 2)
                .input(circuit, MarkerMaterials.Tier.IV, 2)
                .input(wireGtQuadruple, IVSuperconductor, 4)
                .outputs(FIELD_GENERATOR_IV.getStackForm())
                .duration(100).EUt(VA[LV]).buildAndRegister();


        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(30720)
                .input(frameGt, HSSG)
                .inputs(QUANTUM_STAR.getStackForm())
                .input(wireGtQuadruple, LuVSuperconductor, 8)
                .input(cableGtOctal, YttriumBariumCuprate, 4)
                .input(circuit, LuV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(FIELD_GENERATOR_LuV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(122880)
                .input(frameGt, HSSE)
                .inputs(QUANTUM_STAR.getStackForm())
                .input(wireGtQuadruple, ZPMSuperconductor, 8)
                .input(cableGtOctal, Naquadah, 4)
                .input(circuit, ZPM, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(FIELD_GENERATOR_ZPM.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(491520)
                .input(frameGt, Tritanium)
                .inputs(GRAVI_STAR.getStackForm())
                .input(wireGtQuadruple, UVSuperconductor, 8)
                .input(cableGtOctal, Duranium, 4)
                .input(circuit, UV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(FIELD_GENERATOR_UV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(1966080)
                .input(frameGt, Seaborgium)
                .inputs(GRAVI_STAR.getStackForm())
                .input(wireGtQuadruple, UHVSuperconductor, 8)
                .input(cableGtSingle, TungstenTitaniumCarbide, 4)
                .input(circuit, UHV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .outputs(FIELD_GENERATOR_UHV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(7864320)
                .input(frameGt, Bohrium)
                .inputs(UNSTABLE_STAR.getStackForm())
                .input(wireGtQuadruple, UEVSuperconductor, 8)
                .input(wireFine, Seaborgium, 64)
                .input(cableGtSingle, Pikyonium, 4)
                .input(circuit, UEV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .outputs(FIELD_GENERATOR_UEV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(7864320)
                .input(frameGt, Trinium)
                .inputs(UNSTABLE_STAR.getStackForm())
                .input(wireFine, Trinium, 64)
                .input(wireGtQuadruple, UIVSuperconductor, 8)
                .input(cableGtSingle, BlackTitanium, 4)
                .input(circuit, UIV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 6))
                .outputs(FIELD_GENERATOR_UIV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15728640)
                .input(frameGt, Adamantium)
                .inputs(UNSTABLE_STAR.getStackForm())
                .input(wireFine, Adamantium, 64)
                .input(wireGtQuadruple, UXVSuperconductor, 8)
                .input(cableGtSingle, NaquadriaticTaranium, 4)
                .input(circuit, UXV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 6))
                .outputs(FIELD_GENERATOR_UXV.getStackForm())
                .buildAndRegister();
    }

    private static void robotArmInit() {

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(20480)
                .input(cableGtDouble, YttriumBariumCuprate, 16)
                .input(screw, HSSG, 16)
                .input(stick, HSSG, 16)
                .input(ingot, HSSG)
                .inputs(ELECTRIC_MOTOR_LuV.getStackForm(2))
                .inputs(ELECTRIC_PISTON_LUV.getStackForm())
                .input(circuit, EV, 8)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .fluidInputs(Lubricant.getFluid(250))
                .outputs(ROBOT_ARM_LuV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(81920)
                .input(cableGtDouble, Naquadah, 16)
                .input(screw, HSSE, 16)
                .input(stick, HSSE, 16)
                .input(ingot, HSSE)
                .inputs(ELECTRIC_MOTOR_ZPM.getStackForm(2))
                .inputs(ELECTRIC_PISTON_ZPM.getStackForm())
                .input(circuit, IV, 8)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .fluidInputs(Lubricant.getFluid(750))
                .outputs(ROBOT_ARM_ZPM.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(327680)
                .input(cableGtDouble, Duranium, 16)
                .input(screw, Tritanium, 16)
                .input(stick, Tritanium, 16)
                .input(ingot, Tritanium)
                .inputs(ELECTRIC_MOTOR_UV.getStackForm(2))
                .inputs(ELECTRIC_PISTON_UV.getStackForm())
                .input(circuit, LuV, 8)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .fluidInputs(Lubricant.getFluid(2000))
                .outputs(ROBOT_ARM_UV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(1310720)
                .input(cableGtDouble, TungstenTitaniumCarbide, 16)
                .input(screw, HDCS, 16)
                .input(stick, HDCS, 16)
                .input(ingot, HDCS)
                .inputs(ELECTRIC_MOTOR_UHV.getStackForm(2))
                .inputs(ELECTRIC_PISTON_UHV.getStackForm())
                .input(circuit, ZPM, 8)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .fluidInputs(Lubricant.getFluid(2000))
                .outputs(ROBOT_ARM_UHV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(5242880)
                .input(cableGtDouble, Pikyonium, 16)
                .input(screw, EnrichedNaquadahAlloy, 16)
                .input(stick, EnrichedNaquadahAlloy, 16)
                .input(ingot, EnrichedNaquadahAlloy)
                .inputs(ELECTRIC_MOTOR_UEV.getStackForm(2))
                .inputs(ELECTRIC_PISTON_UEV.getStackForm())
                .input(circuit, UV, 8)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .fluidInputs(Lubricant.getFluid(2000))
                .outputs(ROBOT_ARM_UEV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(7864320)
                .input(cableGtDouble, BlackTitanium, 16)
                .input(screw, HastelloyX78, 16)
                .input(stick, HastelloyX78, 16)
                .input(ingot, HastelloyX78)
                .inputs(ELECTRIC_MOTOR_UIV.getStackForm(2))
                .inputs(ELECTRIC_PISTON_UIV.getStackForm())
                .input(circuit, UHV, 8)
                .fluidInputs(SolderingAlloy.getFluid(L * 6))
                .fluidInputs(Lubricant.getFluid(2000))
                .outputs(ROBOT_ARM_UIV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15728640)
                .input(cableGtDouble, NaquadriaticTaranium, 16)
                .input(screw, HastelloyK243, 16)
                .input(stick, HastelloyK243, 16)
                .input(ingot, HastelloyK243)
                .inputs(ELECTRIC_MOTOR_UXV.getStackForm(2))
                .inputs(ELECTRIC_PISTON_UXV.getStackForm())
                .input(circuit, UEV, 8)
                .fluidInputs(SolderingAlloy.getFluid(L * 6))
                .fluidInputs(Lubricant.getFluid(2000))
                .outputs(ROBOT_ARM_UXV.getStackForm())
                .buildAndRegister();
    }

    private static void conveyorInit() {

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15360)
                .inputs(ELECTRIC_MOTOR_LuV.getStackForm(2))
                .input(plate, HSSG, 8)
                .input(gear, HSSG, 4)
                .input(stick, HSSG, 4)
                .input(ingot, HSSG, 2)
                .input(cableGtSingle, YttriumBariumCuprate, 2)
                .fluidInputs(StyreneButadieneRubber.getFluid(L * 5))
                .fluidInputs(Lubricant.getFluid(250))
                .outputs(CONVEYOR_MODULE_LuV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(61440)
                .inputs(ELECTRIC_MOTOR_ZPM.getStackForm(2))
                .input(plate, HSSE, 8)
                .input(gear, HSSE, 4)
                .input(stick, HSSE, 4)
                .input(ingot, HSSE, 2)
                .input(cableGtSingle, Naquadah, 2)
                .fluidInputs(StyreneButadieneRubber.getFluid(L * 10))
                .fluidInputs(Lubricant.getFluid(750))
                .outputs(CONVEYOR_MODULE_ZPM.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(245760)
                .inputs(ELECTRIC_MOTOR_UV.getStackForm(2))
                .input(plate, Tritanium, 8)
                .input(gear, Tritanium, 4)
                .input(stick, Tritanium, 4)
                .input(ingot, Tritanium, 2)
                .input(cableGtSingle, Duranium, 2)
                .fluidInputs(StyreneButadieneRubber.getFluid(L * 10))
                .fluidInputs(Lubricant.getFluid(2000))
                .outputs(CONVEYOR_MODULE_UV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(983040)
                .inputs(ELECTRIC_MOTOR_UHV.getStackForm(2))
                .input(plate, HDCS, 8)
                .input(gear, HDCS, 4)
                .input(stick, HDCS, 4)
                .input(ingot, HDCS, 2)
                .input(cableGtSingle, TungstenTitaniumCarbide, 2)
                .fluidInputs(StyreneButadieneRubber.getFluid(L * 10))
                .fluidInputs(Lubricant.getFluid(2000))
                .outputs(CONVEYOR_MODULE_UHV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(3932160)
                .inputs(ELECTRIC_MOTOR_UEV.getStackForm(2))
                .input(plate, EnrichedNaquadahAlloy, 8)
                .input(gear, EnrichedNaquadahAlloy, 4)
                .input(stick, EnrichedNaquadahAlloy, 4)
                .input(ingot, EnrichedNaquadahAlloy, 2)
                .input(cableGtSingle, Pikyonium, 2)
                .fluidInputs(Zylon.getFluid(L * 10))
                .fluidInputs(Lubricant.getFluid(2000))
                .outputs(CONVEYOR_MODULE_UEV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(7864320)
                .inputs(ELECTRIC_MOTOR_UIV.getStackForm(2))
                .input(plate, HastelloyX78, 8)
                .input(gear, HastelloyX78, 4)
                .input(stick, HastelloyX78, 4)
                .input(ingot, HastelloyX78, 2)
                .input(cableGtSingle, BlackTitanium, 2)
                .fluidInputs(Zylon.getFluid(L * 10))
                .fluidInputs(Lubricant.getFluid(2000))
                .outputs(CONVEYOR_MODULE_UIV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15728640)
                .inputs(ELECTRIC_MOTOR_UXV.getStackForm(2))
                .input(plate, HastelloyK243, 8)
                .input(gear, HastelloyK243, 4)
                .input(stick, HastelloyK243, 4)
                .input(ingot, HastelloyK243, 2)
                .input(cableGtSingle, NaquadriaticTaranium, 2)
                .fluidInputs(Zylon.getFluid(L * 10))
                .fluidInputs(Lubricant.getFluid(2000))
                .outputs(CONVEYOR_MODULE_UXV.getStackForm())
                .buildAndRegister();
    }

    private static void pistonInit() {

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15360)
                .inputs(ELECTRIC_MOTOR_LuV.getStackForm())
                .input(plate, HSSG, 8)
                .input(gearSmall, HSSG, 8)
                .input(stick, HSSG, 4)
                .input(ingot, HSSG, 2)
                .input(cableGtSingle, YttriumBariumCuprate, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .fluidInputs(Lubricant.getFluid(250))
                .outputs(ELECTRIC_PISTON_LUV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(61440)
                .inputs(ELECTRIC_MOTOR_ZPM.getStackForm())
                .input(plate, HSSE, 8)
                .input(gearSmall, HSSE, 8)
                .input(stick, HSSE, 4)
                .input(ingot, HSSE, 2)
                .input(cableGtSingle, Naquadah, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .fluidInputs(Lubricant.getFluid(750))
                .outputs(ELECTRIC_PISTON_ZPM.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(245760)
                .inputs(ELECTRIC_MOTOR_UV.getStackForm())
                .input(plate, Tritanium, 8)
                .input(gearSmall, Tritanium, 8)
                .input(stick, Tritanium, 4)
                .input(ingot, Tritanium, 2)
                .input(cableGtSingle, Duranium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .fluidInputs(Lubricant.getFluid(2000))
                .outputs(ELECTRIC_PISTON_UV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(983040)
                .inputs(ELECTRIC_MOTOR_UHV.getStackForm())
                .input(plate, HDCS, 8)
                .input(gearSmall, HDCS, 8)
                .input(stick, HDCS, 4)
                .input(ingot, HDCS, 2)
                .input(cableGtSingle, TungstenTitaniumCarbide, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .fluidInputs(Lubricant.getFluid(2000))
                .outputs(ELECTRIC_PISTON_UHV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(3932160)
                .inputs(ELECTRIC_MOTOR_UEV.getStackForm())
                .input(plate, EnrichedNaquadahAlloy, 8)
                .input(gearSmall, EnrichedNaquadahAlloy, 8)
                .input(stick, EnrichedNaquadahAlloy, 4)
                .input(ingot, EnrichedNaquadahAlloy, 2)
                .input(cableGtSingle, Pikyonium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .fluidInputs(Lubricant.getFluid(2000))
                .outputs(ELECTRIC_PISTON_UEV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(7864320)
                .inputs(ELECTRIC_MOTOR_UIV.getStackForm())
                .input(plate, HastelloyX78, 8)
                .input(gearSmall, HastelloyX78, 8)
                .input(stick, HastelloyX78, 4)
                .input(ingot, HastelloyX78, 2)
                .input(cableGtSingle, BlackTitanium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .fluidInputs(Lubricant.getFluid(2000))
                .outputs(ELECTRIC_PISTON_UIV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15728640)
                .inputs(ELECTRIC_MOTOR_UXV.getStackForm())
                .input(plate, HastelloyK243, 8)
                .input(gearSmall, HastelloyK243, 8)
                .input(stick, HastelloyK243, 4)
                .input(ingot, HastelloyK243, 2)
                .input(cableGtSingle, NaquadriaticTaranium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .fluidInputs(Lubricant.getFluid(2000))
                .outputs(ELECTRIC_PISTON_UXV.getStackForm())
                .buildAndRegister();
    }

    private static void motorInit() {

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(10240)
                .outputs(ELECTRIC_MOTOR_LuV.getStackForm())
                .input(stickLong, NeodymiumMagnetic)
                .input(stickLong, HSSG, 2)
                .input(ring, HSSG, 4)
                .input(round, HSSG, 16)
                .input(wireFine, AnnealedCopper, 64)
                .input(wireFine, AnnealedCopper, 64)
                .input(wireFine, AnnealedCopper, 64)
                .input(wireFine, AnnealedCopper, 64)
                .input(cableGtSingle, YttriumBariumCuprate, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .fluidInputs(Lubricant.getFluid(250))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(40960)
                .outputs(ELECTRIC_MOTOR_ZPM.getStackForm())
                .input(stickLong, NeodymiumMagnetic, 16)
                .input(stickLong, HSSE, 2)
                .input(ring, HSSE, 4)
                .input(round, HSSE, 16)
                .input(wireFine, Platinum, 64)
                .input(wireFine, Platinum, 64)
                .input(wireFine, Platinum, 64)
                .input(wireFine, Platinum, 64)
                .input(cableGtQuadruple, Naquadah, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .fluidInputs(Lubricant.getFluid(750))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(163840)
                .outputs(ELECTRIC_MOTOR_UV.getStackForm())
                .input(stickLong, NeodymiumMagnetic, 64)
                .input(stickLong, Tritanium, 2)
                .input(ring, Tritanium, 4)
                .input(round, Tritanium, 16)
                .input(wireFine, Duranium, 64)
                .input(wireFine, Duranium, 64)
                .input(wireFine, Duranium, 64)
                .input(wireFine, Duranium, 64)
                .input(cableGtQuadruple, Duranium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .fluidInputs(Lubricant.getFluid(2000))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(655360)
                .outputs(ELECTRIC_MOTOR_UHV.getStackForm())
                .input(stickLong, NeodymiumMagnetic, 64)
                .input(stickLong, NeodymiumMagnetic, 64)
                .input(stickLong, HDCS, 2)
                .input(ring, HDCS, 4)
                .input(round, HDCS, 16)
                .input(wireFine, TungstenTitaniumCarbide, 64)
                .input(wireFine, TungstenTitaniumCarbide, 64)
                .input(wireFine, TungstenTitaniumCarbide, 64)
                .input(wireFine, TungstenTitaniumCarbide, 64)
                .input(cableGtQuadruple, TungstenTitaniumCarbide, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .fluidInputs(Lubricant.getFluid(3000))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(2621440)
                .outputs(ELECTRIC_MOTOR_UEV.getStackForm())
                .input(stickLong, SamariumMagnetic, 64)
                .input(stickLong, SamariumMagnetic, 64)
                .input(stickLong, EnrichedNaquadahAlloy, 2)
                .input(ring, EnrichedNaquadahAlloy, 4)
                .input(round, EnrichedNaquadahAlloy, 16)
                .input(wireFine, Pikyonium, 64)
                .input(wireFine, Pikyonium, 64)
                .input(wireFine, Pikyonium, 64)
                .input(wireFine, Pikyonium, 64)
                .input(cableGtQuadruple, Pikyonium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .fluidInputs(Lubricant.getFluid(3000))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(7864320)
                .outputs(ELECTRIC_MOTOR_UIV.getStackForm())
                .input(stickLong, SamariumMagnetic, 64)
                .input(stickLong, SamariumMagnetic, 64)
                .input(stickLong, HastelloyX78, 2)
                .input(ring, HastelloyX78, 4)
                .input(round, HastelloyX78, 16)
                .input(wireFine, CarbonNanotubes, 64)
                .input(wireFine, CarbonNanotubes, 64)
                .input(wireFine, CarbonNanotubes, 64)
                .input(wireFine, CarbonNanotubes, 64)
                .input(cableGtQuadruple, BlackTitanium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 6))
                .fluidInputs(Lubricant.getFluid(3000))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15728640)
                .outputs(ELECTRIC_MOTOR_UXV.getStackForm())
                .input(stickLong, SamariumMagnetic, 64)
                .input(stickLong, SamariumMagnetic, 64)
                .input(stickLong, HastelloyK243, 2)
                .input(ring, HastelloyK243, 4)
                .input(round, HastelloyK243, 16)
                .input(wireFine, CarbonNanotubes, 64)
                .input(wireFine, CarbonNanotubes, 64)
                .input(wireFine, CarbonNanotubes, 64)
                .input(wireFine, CarbonNanotubes, 64)
                .input(cableGtQuadruple, NaquadriaticTaranium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 6))
                .fluidInputs(Lubricant.getFluid(3000))
                .buildAndRegister();
    }

    private static void pumpInit() {

        //TODO: change pipe to fluid pipe
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15360)
                .outputs(ELECTRIC_PUMP_LuV.getStackForm())
                .inputs(ELECTRIC_MOTOR_LuV.getStackForm())
                .input(pipeSmallItem, Ultimet, 2)
                .input(screw, HSSG, 8)
                .input(ring, SiliconeRubber, 16)
                .input(rotor, HSSG, 2)
                .input(cableGtSingle, YttriumBariumCuprate, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .fluidInputs(Lubricant.getFluid(250))
                .buildAndRegister();

        //TODO: Change pipe to fluid pipe
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(61440)
                .outputs(ELECTRIC_PUMP_ZPM.getStackForm())
                .inputs(ELECTRIC_MOTOR_ZPM.getStackForm())
                .input(pipeNormalItem, Ultimet, 2)
                .input(screw, HSSE, 8)
                .input(ring, SiliconeRubber, 16)
                .input(rotor, HSSE, 2)
                .input(cableGtSingle, Naquadah, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .fluidInputs(Lubricant.getFluid(750))
                .buildAndRegister();

        //TODO: change pipe to fluid pipe
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(245760)
                .outputs(ELECTRIC_PUMP_UV.getStackForm())
                .inputs(ELECTRIC_MOTOR_UV.getStackForm())
                .input(pipeLargeItem, Ultimet, 2)
                .input(screw, Tritanium, 8)
                .input(ring, SiliconeRubber, 16)
                .input(rotor, Tritanium, 2)
                .input(cableGtSingle, Duranium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .fluidInputs(Lubricant.getFluid(2000))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(983040)
                .outputs(ELECTRIC_PUMP_UHV.getStackForm())
                .inputs(ELECTRIC_MOTOR_UHV.getStackForm())
                .input(pipeLargeFluid, Zeron100, 32)
                .input(screw, HDCS, 8)
                .input(ring, SiliconeRubber, 16)
                .input(rotor, HDCS, 2)
                .input(cableGtSingle, TungstenTitaniumCarbide, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .fluidInputs(Lubricant.getFluid(2000))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(3932160)
                .outputs(ELECTRIC_PUMP_UEV.getStackForm())
                .inputs(ELECTRIC_MOTOR_UEV.getStackForm())
                .input(pipeLargeFluid, Lafium, 64)
                .input(screw, EnrichedNaquadahAlloy, 8)
                .input(ring, SiliconeRubber, 16)
                .input(rotor, EnrichedNaquadahAlloy, 2)
                .input(cableGtSingle, Pikyonium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .fluidInputs(Lubricant.getFluid(2000))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(7864320)
                .outputs(ELECTRIC_PUMP_UIV.getStackForm())
                .inputs(ELECTRIC_MOTOR_UIV.getStackForm())
                .input(pipeLargeFluid, TantalumHafniumSeaborgiumCarbide, 64)
                .input(screw, HastelloyX78, 8)
                .input(ring, SiliconeRubber, 16)
                .input(rotor, HastelloyX78, 2)
                .input(cableGtSingle, BlackTitanium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .fluidInputs(Lubricant.getFluid(2000))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15728640)
                .outputs(ELECTRIC_PUMP_UXV.getStackForm())
                .inputs(ELECTRIC_MOTOR_UXV.getStackForm())
                .input(pipeLargeFluid, EnrichedNaquadahAlloy, 64)
                .input(screw, HastelloyK243, 8)
                .input(ring, SiliconeRubber, 16)
                .input(rotor, HastelloyK243, 2)
                .input(cableGtSingle, NaquadriaticTaranium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .fluidInputs(Lubricant.getFluid(2000))
                .buildAndRegister();
    }

    public static void opvComponents() {

    }

    public static void maxComponents() {

    }
}
