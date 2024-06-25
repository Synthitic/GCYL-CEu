package com.fulltrix.gcyl.recipes.categories;

import gregtech.api.GTValues;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.api.unification.stack.UnificationEntry;

import static com.fulltrix.gcyl.api.GCYLUtility.*;
import static com.fulltrix.gcyl.api.GCYLUtility.getMainComponentMaterialByTier;
import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static com.fulltrix.gcyl.item.GCYLCoreItems.*;
import static gregicality.multiblocks.api.unification.GCYMMaterials.Zeron100;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.Materials.Lubricant;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

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
    }

    //TODO: add research to recipes and change to be more in line with CEu
    //TODO: add higher tiers of soldering alloy
    //TODO: add higher tier data storage and require it
//TODO: nerf consumable macrowormhole generators

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
                .input(gemExquisite, Ruby, 2)
                .input(circuit, MarkerMaterials.Tier.LuV, 2)
                .input(stickLong, Ruridit,4)
                .inputs(QUANTUM_STAR.getStackForm())
                .input(dust,ZincSelenide,16)
                .input(foil, Palladium, 64)
                .input(foil, Palladium, 64)
                .input(cableGtDouble, YttriumBariumCuprate, 8)
                .fluidInputs(SolderingAlloy.getFluid(L*2))
                .outputs(EMITTER_LuV.getStackForm())
                .scannerResearch(b -> b
                        .researchStack(EMITTER_IV.getStackForm())
                        .duration(2400))
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

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(frameGt, HSSE)
                .input(ELECTRIC_MOTOR_ZPM)
                .input(dust,Fluorescein,16)
                .input(gemExquisite, Emerald, 2)
                .input(stickLong, Osmiridium, 4)
                .input(QUANTUM_STAR, 2)
                .input(circuit, MarkerMaterials.Tier.ZPM, 2)
                .input(foil, Platinum, 64)
                .input(foil, Platinum, 64)
                .input(cableGtDouble, Naquadah, 8)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .output(EMITTER_ZPM)
                .stationResearch(b -> b
                        .researchStack(EMITTER_LuV.getStackForm())
                        .CWUt(8))
                .duration(600).EUt(61440).buildAndRegister();


        /* ORIGINAL UV EMITTER
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

         */
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(frameGt, Tritanium)
                .input(ELECTRIC_MOTOR_UV)
                .input(dust,Stilbene,16)
                .input(gemExquisite, Diamond, 2)
                .input(stickLong, Tritanium, 4)
                .input(GRAVI_STAR)
                .input(circuit, MarkerMaterials.Tier.UV, 2)
                .input(foil, Osmiridium, 64)
                .input(foil, Osmiridium, 64)
                .input(cableGtDouble, Duranium, 8)
                .fluidInputs(SolderingAlloy.getFluid(L * 8))
                .fluidInputs(Naquadria.getFluid(L * 4))
                .output(EMITTER_UV)
                .stationResearch(b -> b
                        .researchStack(EMITTER_ZPM.getStackForm())
                        .CWUt(48)
                        .EUt(VA[GTValues.UV]))
                .duration(600).EUt(245760).buildAndRegister();



        for(int i = 9; i < 15; i++) {
            if (i == 14) {
                getAssLineResearchBuilder(i, 600, getEmitterByTier(i - 1).getStackForm(), false, false, (int) V[MAX])
                        .outputs(getEmitterByTier(i).getStackForm())
                        .input(frameGt, getMainComponentMaterialByTier(i))
                        .inputs(getMotorByTier(i).getStackForm())
                        .input(dust, RheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate, 64)
                        .input(stickLong, Mendelevium, 64)
                        .inputs(getStarByTier(i).getStackForm(8))
                        .input(circuit, getMarkerMaterialByTier(i), 2)
                        .inputs(RECURSIVELY_FOLDED_NEGATIVE_SPACE.getStackForm(2))
                        .inputs(MANIFOLD_OSCILLATORY_POWER_CELL.getStackForm())
                        .inputs(STABILIZED_WORMHOLE_GENERATOR.getStackForm()) //TODO: microwormhole
                        .input(foil, Bohrium, 64)
                        .input(foil, Bohrium, 64)
                        .input(cableGtDouble, getCableByTier(i), 8)
                        .fluidInputs(Indalloy140.getFluid((int) (L * 8 * Math.pow(2, i - 8))))
                        //.fluidInputs(getFluidMaterialByTier(i).getFluid(L * 4 * (i - 7)))
                        .fluidInputs(getFluidMaterialByTier(i).getFluid((int) (L * 4 * (Math.pow(2, i - 8)))))
                        .fluidInputs(HighEnergyQGP.getFluid(2000))
                        .buildAndRegister();

            } else if (i == 9) {
                getAssLineResearchBuilder(i, 600, getEmitterByTier(i - 1).getStackForm(), false, false, (int) (983040 * Math.pow(2, i - 9)))
                        .outputs(getEmitterByTier(i).getStackForm())
                        .input(frameGt, getMainComponentMaterialByTier(i))
                        .inputs(getMotorByTier(i).getStackForm())
                        .input(dust,FranciumCaesiumCadmiumBromide,16)
                        .input(gemExquisite, Diamond, 2)
                        .input(stickLong, getMainComponentMaterialByTier(i), 4)
                        .inputs(getStarByTier(i).getStackForm(2))
                        .input(circuit, getMarkerMaterialByTier(i), 2)
                        .input(foil, getMainComponentMaterialByTier(i), 64)
                        .input(foil, getMainComponentMaterialByTier(i), 64)
                        .input(cableGtDouble, getCableByTier(i), 8)
                        .fluidInputs(Indalloy140.getFluid((int) (L * 8 * Math.pow(2, i - 8))))
                        //.fluidInputs(getFluidMaterialByTier(i).getFluid(L * 4 * (i - 7)))
                        .fluidInputs(getFluidMaterialByTier(i).getFluid((int) (L * 4 * (Math.pow(2, i - 8)))))
                        .buildAndRegister();
            } else if (i == 10) {
                getAssLineResearchBuilder(i, 600, getEmitterByTier(i - 1).getStackForm(), false, false, (int) (983040 * Math.pow(2, i - 9)))
                        .outputs(getEmitterByTier(i).getStackForm())
                        .input(frameGt, getMainComponentMaterialByTier(i))
                        .inputs(getMotorByTier(i).getStackForm())
                        .input(dust,RhodamineB,16)
                        .input(gemExquisite, Diamond, 2)
                        .input(stickLong, getMainComponentMaterialByTier(i), 4)
                        .inputs(getStarByTier(i).getStackForm(2))
                        .input(circuit, getMarkerMaterialByTier(i), 2)
                        .input(foil, getMainComponentMaterialByTier(i), 64)
                        .input(foil, getMainComponentMaterialByTier(i), 64)
                        .input(cableGtDouble, getCableByTier(i), 8)
                        .fluidInputs(Indalloy140.getFluid((int) (L * 8 * Math.pow(2, i - 8))))
                        //.fluidInputs(getFluidMaterialByTier(i).getFluid(L * 4 * (i - 7)))
                        .fluidInputs(getFluidMaterialByTier(i).getFluid((int) (L * 4 * (Math.pow(2, i - 8)))))
                        .buildAndRegister();
            } else if (i == 11) {
                getAssLineResearchBuilder(i, 600, getEmitterByTier(i - 1).getStackForm(), false, false, (int) (983040 * Math.pow(2, i - 9)))
                        .outputs(getEmitterByTier(i).getStackForm())
                        .input(frameGt, getMainComponentMaterialByTier(i))
                        .inputs(getMotorByTier(i).getStackForm())
                        .inputs(LEPTON_TRAP_CRYSTAL.getStackForm(4))
                        .input(gemExquisite, Diamond, 2)
                        .input(stickLong, getMainComponentMaterialByTier(i), 4)
                        .inputs(getStarByTier(i).getStackForm(4))
                        .input(circuit, getMarkerMaterialByTier(i), 2)
                        .input(foil, TriniumTitanium, 64)
                        .input(foil, TriniumTitanium, 64)
                        .input(cableGtDouble, getCableByTier(i), 8)
                        .fluidInputs(Indalloy140.getFluid((int) (L * 8 * Math.pow(2, i - 8))))
                        //.fluidInputs(getFluidMaterialByTier(i).getFluid(L * 4 * (i - 7)))
                        .fluidInputs(getFluidMaterialByTier(i).getFluid((int) (L * 4 * (Math.pow(2, i - 8)))))
                        .buildAndRegister();
            } else if (i == 12) {
                getAssLineResearchBuilder(i, 600, getEmitterByTier(i - 1).getStackForm(), false, false, (int) (983040 * Math.pow(2, i - 9)))
                        .outputs(getEmitterByTier(i).getStackForm())
                        .input(frameGt, getMainComponentMaterialByTier(i))
                        .inputs(getMotorByTier(i).getStackForm())
                        .inputs(CHARGED_LEPTON_TRAP_CRYSTAL.getStackForm(4))
                        .input(gemExquisite, Diamond, 2)
                        .input(stickLong, getMainComponentMaterialByTier(i), 4)
                        .inputs(getStarByTier(i).getStackForm(2))
                        .input(circuit, getMarkerMaterialByTier(i), 2)
                        .input(foil, ProtoAdamantium, 64)
                        .input(foil, ProtoAdamantium, 64)
                        .input(cableGtDouble, getCableByTier(i), 8)
                        .fluidInputs(Indalloy140.getFluid((int) (L * 8 * Math.pow(2, i - 8))))
                        //.fluidInputs(getFluidMaterialByTier(i).getFluid(L * 4 * (i - 7)))
                        .fluidInputs(getFluidMaterialByTier(i).getFluid((int) (L * 4 * (Math.pow(2, i - 8)))))
                        .buildAndRegister();
            } else {
            getAssLineResearchBuilder(i, 600, getEmitterByTier(i - 1).getStackForm(), false, false, (int) (983040 * Math.pow(2, i - 9)))
                    .outputs(getEmitterByTier(i).getStackForm())
                    .input(frameGt, getMainComponentMaterialByTier(i))
                    .inputs(getMotorByTier(i).getStackForm())
                    .input(dust, ChargedCaesiumCeriumCobaltIndium, 64)
                    .inputs(SCINTILLATOR_CRYSTAL.getStackForm(4))
                    .inputs(ULTRASHORT_PULSE_LASER.getStackForm())
                    .input(gemExquisite, Jasper, 2)
                    .input(stickLong, getMainComponentMaterialByTier(i), 4)
                    .inputs(getStarByTier(i).getStackForm(4))
                    .input(circuit, getMarkerMaterialByTier(i), 2)
                    .input(foil, Bohrium, 64)
                    .input(foil, Bohrium, 64)
                    .input(cableGtDouble, getCableByTier(i), 8)
                    .fluidInputs(Indalloy140.getFluid((int) (L * 8 * Math.pow(2, i - 8))))
                    //.fluidInputs(getFluidMaterialByTier(i).getFluid(L * 4 * (i - 7)))
                    .fluidInputs(getFluidMaterialByTier(i).getFluid((int) (L * 4 * (Math.pow(2, i - 8)))))
                    .fluidInputs(QuarkGluonPlasma.getFluid(2000))
                    .buildAndRegister();
            }
        }

        /*
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(983040)
                .input(frameGt, HDCS, 1)
                .input(dust,FranciumCaesiumCadmiumBromide,16)
                .input(foil, Osmiridium, 64)
                .input(cableGtSingle, TungstenTitaniumCarbide, 8)
                .input(gemExquisite, Diamond, 2)
                .input(circuit, MarkerMaterials.Tier.UHV, 2)
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
                .input(circuit, MarkerMaterials.Tier.UEV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .outputs(EMITTER_UEV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(7864320)
                .input(frameGt, HastelloyX78)
                .inputs(LEPTON_TRAP_CRYSTAL.getStackForm(4))
                .input(foil, TriniumTitanium, 64)
                .input(cableGtSingle, BlackTitanium, 8)
                .input(gemExquisite, Diamond, 2)
                .input(circuit, MarkerMaterials.Tier.UIV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 6))
                .outputs(EMITTER_UIV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15728640)
                .input(frameGt, HastelloyK243)
                .inputs(CHARGED_LEPTON_TRAP_CRYSTAL.getStackForm(4))
                .input(foil, ProtoAdamantium, 64)
                .input(cableGtSingle, NaquadriaticTaranium, 8)
                .input(gemExquisite, Diamond, 2)
                .input(circuit, MarkerMaterials.Tier.UXV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 8))
                .outputs(EMITTER_UXV.getStackForm())
                .buildAndRegister();

         */
    }

    private static void sensorInit() {
/*  Original luv sensor
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

 */
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(frameGt, HSSS)
                .input(ELECTRIC_MOTOR_LuV)
                .input(dust, Germanium, 16)
                .input(gemExquisite, Ruby, 2)
                .input(plate, Ruridit, 4)
                .input(QUANTUM_STAR)
                .input(circuit, MarkerMaterials.Tier.LuV, 2)
                .input(foil, Palladium, 64)
                .input(foil, Palladium, 64)
                .input(cableGtDouble, YttriumBariumCuprate, 8)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .output(SENSOR_LuV)
                .scannerResearch(b -> b
                        .researchStack(SENSOR_IV.getStackForm())
                        .duration(2400))
                .duration(600).EUt(15360).buildAndRegister();

/*Original zpm sensor
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

 */

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(frameGt, HSSE)
                .input(ELECTRIC_MOTOR_ZPM)
                .input(dust,LeadSenenide,16)
                .input(gemExquisite, Emerald, 2)
                .input(plate, Osmiridium, 4)
                .input(QUANTUM_STAR, 2)
                .input(circuit, MarkerMaterials.Tier.ZPM, 2)
                .input(foil, Platinum, 64)
                .input(foil, Platinum, 64)
                .input(cableGtDouble, Naquadah, 8)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .output(SENSOR_ZPM)
                .stationResearch(b -> b
                        .researchStack(SENSOR_LuV.getStackForm())
                        .CWUt(4))
                .duration(600).EUt(61440).buildAndRegister();

    /*
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

     */
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(frameGt, Tritanium)
                .input(ELECTRIC_MOTOR_UV)
                .input(dust,BariumStrontiumTitanate,16)
                .input(gemExquisite, Diamond, 2)
                .input(plate, Tritanium, 4)
                .input(GRAVI_STAR)
                .input(circuit, MarkerMaterials.Tier.UV, 2)
                .input(foil, Osmiridium, 64)
                .input(foil, Osmiridium, 64)
                .input(cableGtDouble, Duranium, 8)
                .fluidInputs(SolderingAlloy.getFluid(L * 8))
                .fluidInputs(Naquadria.getFluid(L * 4))
                .output(SENSOR_UV)
                .stationResearch(b -> b
                        .researchStack(SENSOR_ZPM.getStackForm())
                        .CWUt(48)
                        .EUt(VA[GTValues.UV]))
                .duration(600).EUt(100000).buildAndRegister();


        for(int i = 9; i < 15; i++) {
            if (i == 14) {
                getAssLineResearchBuilder(i, 600, getSensorByTier(i - 1).getStackForm(), false, false, (int) V[MAX])
                        .outputs(getSensorByTier(i).getStackForm())
                        .input(frameGt, getMainComponentMaterialByTier(i))
                        .inputs(getMotorByTier(i).getStackForm())
                        .inputs(STABILIZED_WORMHOLE_GENERATOR.getStackForm()) //TODO: microwormhole generator
                        .input(plate, getMainComponentMaterialByTier(i), 4)
                        .inputs(getStarByTier(i).getStackForm(4))
                        .input(circuit,getMarkerMaterialByTier(i), 2)
                        .input(foil, getMainComponentMaterialByTier(i), 64)
                        .input(foil, FullerenePolymerMatrix, 64)
                        .inputs(COSMIC_FABRIC.getStackForm(64))
                        .inputs(BOSE_EINSTEIN_COOLING_CONTAINER.getStackForm(32))
                        .input(cableGtDouble, getCableByTier(i), 8)
                        .fluidInputs(Indalloy140.getFluid((int) (L * 8 * Math.pow(2, i - 8))))
                        //.fluidInputs(getFluidMaterialByTier(i).getFluid(L * 4 * (i - 7)))
                        .fluidInputs(getFluidMaterialByTier(i).getFluid((int) (L * 4 * (Math.pow(2, i - 8)))))
                        .fluidInputs(HighEnergyQGP.getFluid(2000))
                        .buildAndRegister();
            }
            else if (i == 9){
                getAssLineResearchBuilder(i, 600, getSensorByTier(i - 1).getStackForm(), false, false, (int) (983040 * Math.pow(2, i - 9)))
                        .outputs(getSensorByTier(i).getStackForm())
                        .input(frameGt, getMainComponentMaterialByTier(i))
                        .inputs(getMotorByTier(i).getStackForm())
                        .input(dust,BariumStrontiumTitanate,16)
                        .input(gemExquisite, Diamond, 2)
                        .input(plate, getMainComponentMaterialByTier(i), 4)
                        .inputs(getStarByTier(i).getStackForm(2))
                        .input(circuit,getMarkerMaterialByTier(i), 2)
                        .input(foil, getMainComponentMaterialByTier(i), 64)
                        .input(foil, getMainComponentMaterialByTier(i), 64)
                        .inputs(BOSE_EINSTEIN_COOLING_CONTAINER.getStackForm())
                        .input(cableGtDouble, getCableByTier(i), 8)
                        .fluidInputs(Indalloy140.getFluid((int) (L * 8 * Math.pow(2, i - 8))))
                        //.fluidInputs(getFluidMaterialByTier(i).getFluid(L * 4 * (i - 7)))
                        .fluidInputs(getFluidMaterialByTier(i).getFluid((int) (L * 4 * (Math.pow(2, i - 8)))))
                        .buildAndRegister();
            }
            else if (i == 10) {
                getAssLineResearchBuilder(i, 600, getSensorByTier(i - 1).getStackForm(), false, false, (int) (983040 * Math.pow(2, i - 9)))
                        .outputs(getSensorByTier(i).getStackForm())
                        .input(frameGt, getMainComponentMaterialByTier(i))
                        .inputs(getMotorByTier(i).getStackForm())
                        .input(dust,MagnetorestrictiveAlloy,16)
                        .input(gemExquisite, Diamond, 2)
                        .input(plate, getMainComponentMaterialByTier(i), 4)
                        .inputs(getStarByTier(i).getStackForm(1))
                        .input(circuit,getMarkerMaterialByTier(i), 2)
                        .input(foil, getMainComponentMaterialByTier(i), 64)
                        .input(foil, getMainComponentMaterialByTier(i), 64)
                        .inputs(BOSE_EINSTEIN_COOLING_CONTAINER.getStackForm(2))
                        .input(cableGtDouble, getCableByTier(i), 8)
                        .fluidInputs(Indalloy140.getFluid((int) (L * 8 * Math.pow(2, i - 8))))
                        //.fluidInputs(getFluidMaterialByTier(i).getFluid(L * 4 * (i - 7)))
                        .fluidInputs(getFluidMaterialByTier(i).getFluid((int) (L * 4 * (Math.pow(2, i - 8)))))
                        .buildAndRegister();
            }
            else if (i == 11) {
                getAssLineResearchBuilder(i, 600, getSensorByTier(i - 1).getStackForm(), false, false, (int) (983040 * Math.pow(2, i - 9)))
                        .outputs(getSensorByTier(i).getStackForm())
                        .input(frameGt, getMainComponentMaterialByTier(i))
                        .inputs(getMotorByTier(i).getStackForm())
                        .input(gemExquisite, Diamond, 2)
                        .input(plate, getMainComponentMaterialByTier(i), 4)
                        .inputs(getStarByTier(i).getStackForm(2))
                        .input(circuit,getMarkerMaterialByTier(i), 2)
                        .input(foil, TriniumTitanium, 64)
                        .input(foil, TriniumTitanium, 64)
                        .inputs(BOSE_EINSTEIN_COOLING_CONTAINER.getStackForm(4))
                        .input(cableGtDouble, getCableByTier(i), 8)
                        .fluidInputs(Indalloy140.getFluid((int) (L * 8 * Math.pow(2, i - 8))))
                        //.fluidInputs(getFluidMaterialByTier(i).getFluid(L * 4 * (i - 7)))
                        .fluidInputs(getFluidMaterialByTier(i).getFluid((int) (L * 4 * (Math.pow(2, i - 8)))))
                        .fluidInputs(LiquidCrystalDetector.getFluid(2500))
                        .buildAndRegister();
            }
            else if (i == 12) {
                getAssLineResearchBuilder(i, 600, getSensorByTier(i - 1).getStackForm(), false, false, (int) (983040 * Math.pow(2, i - 9)))
                        .outputs(getSensorByTier(i).getStackForm())
                        .input(frameGt, getMainComponentMaterialByTier(i))
                        .inputs(getMotorByTier(i).getStackForm())
                        .input(gemExquisite, Diamond, 2)
                        .input(plate, getMainComponentMaterialByTier(i), 4)
                        .inputs(getStarByTier(i).getStackForm(1))
                        .input(circuit,getMarkerMaterialByTier(i), 2)
                        .input(foil, ProtoAdamantium, 64)
                        .input(foil, ProtoAdamantium, 64)
                        .inputs(BOSE_EINSTEIN_COOLING_CONTAINER.getStackForm(8))
                        .input(cableGtDouble, getCableByTier(i), 8)
                        .fluidInputs(Indalloy140.getFluid((int) (L * 8 * Math.pow(2, i - 8))))
                        //.fluidInputs(getFluidMaterialByTier(i).getFluid(L * 4 * (i - 7)))
                        .fluidInputs(getFluidMaterialByTier(i).getFluid((int) (L * 4 * (Math.pow(2, i - 8)))))
                        .fluidInputs(FreeElectronGas.getFluid(2500))
                        .buildAndRegister();
            }
            else {
                getAssLineResearchBuilder(i, 600, getSensorByTier(i - 1).getStackForm(), false, false, (int) (983040 * Math.pow(2, i - 9)))
                        .outputs(getSensorByTier(i).getStackForm())
                        .input(frameGt, getMainComponentMaterialByTier(i))
                        .inputs(getMotorByTier(i).getStackForm())
                        .input(gemExquisite, Jasper, 2)
                        .input(plate, getMainComponentMaterialByTier(i), 4)
                        .inputs(getStarByTier(i).getStackForm(2))
                        .input(circuit,getMarkerMaterialByTier(i), 2)
                        .input(foil, MetastableOganesson, 64)
                        .input(foil, FullerenePolymerMatrix, 64)
                        .inputs(BOSE_EINSTEIN_COOLING_CONTAINER.getStackForm(16))
                        .input(cableGtDouble, getCableByTier(i), 8)
                        .fluidInputs(Indalloy140.getFluid((int) (L * 8 * Math.pow(2, i - 8))))
                        //.fluidInputs(getFluidMaterialByTier(i).getFluid(L * 4 * (i - 7)))
                        .fluidInputs(getFluidMaterialByTier(i).getFluid((int) (L * 4 * (Math.pow(2, i - 8)))))
                        .fluidInputs(QuarkGluonPlasma.getFluid(2000))
                        .buildAndRegister();
            }
        }

        /*

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(983040)
                .input(frameGt, HDCS)
                .input(dust,LeadScandiumTantalate,16)
                .input(foil, Osmiridium, 64)
                .input(cableGtSingle, TungstenTitaniumCarbide, 8)
                .input(gemExquisite, Diamond, 2)
                .inputs(BOSE_EINSTEIN_COOLING_CONTAINER.getStackForm())
                .input(circuit, MarkerMaterials.Tier.UHV, 2)
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
                .input(circuit, MarkerMaterials.Tier.UEV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .outputs(SENSOR_UEV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(7864320)
                .input(frameGt, HastelloyX78)
                .input(foil, TriniumTitanium, 64)
                .input(cableGtSingle, BlackTitanium, 8)
                .input(gemExquisite, Diamond, 2)
                .inputs(BOSE_EINSTEIN_COOLING_CONTAINER.getStackForm(8))
                .input(circuit, MarkerMaterials.Tier.UIV, 2)
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
                .input(circuit, MarkerMaterials.Tier.UXV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 6))
                .fluidInputs(FreeElectronGas.getFluid(2500))
                .outputs(SENSOR_UXV.getStackForm())
                .buildAndRegister();

         */
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
                .duration(100).EUt(VA[GTValues.LV]).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(gem, EnderEye)
                .input(plate, Aluminium, 2)
                .input(circuit, MarkerMaterials.Tier.MV, 2)
                .input(wireGtQuadruple, MVSuperconductor, 4)
                .outputs(FIELD_GENERATOR_MV.getStackForm())
                .duration(100).EUt(VA[GTValues.LV]).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(QUANTUM_EYE)
                .input(plate, StainlessSteel, 2)
                .input(circuit, MarkerMaterials.Tier.HV, 2)
                .input(wireGtQuadruple, HVSuperconductor, 4)
                .outputs(FIELD_GENERATOR_HV.getStackForm())
                .duration(100).EUt(VA[GTValues.LV]).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(gem, NetherStar)
                .input(plateDouble, Titanium, 2)
                .input(circuit, MarkerMaterials.Tier.EV, 2)
                .input(wireGtQuadruple, EVSuperconductor, 4)
                .outputs(FIELD_GENERATOR_EV.getStackForm())
                .duration(100).EUt(VA[GTValues.LV]).buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(QUANTUM_STAR)
                .input(plateDouble, TungstenSteel, 2)
                .input(circuit, MarkerMaterials.Tier.IV, 2)
                .input(wireGtQuadruple, IVSuperconductor, 4)
                .outputs(FIELD_GENERATOR_IV.getStackForm())
                .duration(100).EUt(VA[GTValues.LV]).buildAndRegister();


        /* Original luv field gen
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(30720)
                .input(frameGt, HSSG)
                .inputs(QUANTUM_STAR.getStackForm())
                .input(wireGtQuadruple, LuVSuperconductor, 8)
                .input(cableGtOctal, YttriumBariumCuprate, 4)
                .input(circuit, LuV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(FIELD_GENERATOR_LuV.getStackForm())
                .buildAndRegister();
         */

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(frameGt, HSSS)
                .input(plate, HSSS, 6)
                .input(QUANTUM_STAR)
                .input(EMITTER_LuV, 2)
                .input(circuit, MarkerMaterials.Tier.LuV, 8)
                .input(wireFine, LuVSuperconductor, 64)
                .input(wireFine, LuVSuperconductor, 64)
                .input(cableGtOctal, YttriumBariumCuprate, 4)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .output(FIELD_GENERATOR_LuV)
                .scannerResearch(b -> b
                        .researchStack(FIELD_GENERATOR_IV.getStackForm())
                        .duration(2400))
                .duration(600).EUt(VA[GTValues.LuV]).buildAndRegister();


        /* original zpm field gen
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(122880)
                .input(frameGt, HSSE)
                .inputs(QUANTUM_STAR.getStackForm())
                .input(wireGtQuadruple, ZPMSuperconductor, 8)
                .input(cableGtOctal, Naquadah, 4)
                .input(circuit, ZPM, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(FIELD_GENERATOR_ZPM.getStackForm())
                .buildAndRegister();
         */
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(frameGt, NaquadahAlloy)
                .input(plate, NaquadahAlloy, 6)
                .input(QUANTUM_STAR)
                .input(EMITTER_ZPM, 2)
                .input(circuit, MarkerMaterials.Tier.ZPM, 8)
                .input(wireFine, ZPMSuperconductor, 64)
                .input(wireFine, ZPMSuperconductor, 64)
                .input(cableGtOctal, Naquadah, 4)
                .fluidInputs(SolderingAlloy.getFluid(L * 8))
                .output(FIELD_GENERATOR_ZPM)
                .stationResearch(b -> b
                        .researchStack(FIELD_GENERATOR_LuV.getStackForm())
                        .CWUt(4)
                        .EUt(VA[GTValues.ZPM]))
                .duration(600).EUt(VA[GTValues.ZPM]).buildAndRegister();

        /* original uv field gen
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(491520)
                .input(frameGt, Tritanium)
                .inputs(GRAVI_STAR.getStackForm())
                .input(wireGtQuadruple, UVSuperconductor, 8)
                .input(cableGtOctal, Duranium, 4)
                .input(circuit, UV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(FIELD_GENERATOR_UV.getStackForm())
                .buildAndRegister();
         */

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(frameGt, Tritanium)
                .input(plate, Tritanium, 6)
                .input(GRAVI_STAR)
                .input(EMITTER_UV, 2)
                .input(circuit, MarkerMaterials.Tier.UV, 8)
                .input(wireFine, UVSuperconductor, 64)
                .input(wireFine, UVSuperconductor, 64)
                .input(cableGtOctal, Duranium, 4)
                .fluidInputs(SolderingAlloy.getFluid(L * 12))
                .fluidInputs(Naquadria.getFluid(L * 4))
                .output(FIELD_GENERATOR_UV)
                .stationResearch(b -> b
                        .researchStack(FIELD_GENERATOR_ZPM.getStackForm())
                        .CWUt(48)
                        .EUt(VA[GTValues.UV]))
                .duration(600).EUt(491520).buildAndRegister();


        for(int i = 9; i < 15; i++) {
            if (i == 14) {
                //TODO change to use chaos later
                getAssLineResearchBuilder(i, 600, getFieldGeneratorByTier(i - 1).getStackForm(), false, false, (int) V[MAX])
                        .outputs(getFieldGeneratorByTier(i).getStackForm())
                        .input(frameGt, CosmicNeutronium)
                        .inputs(COSMIC_MESH.getStackForm(6))
                        .inputs(QCD_PROTECTIVE_PLATING.getStackForm(6))
                        .inputs(getStarByTier(i).getStackForm(8))
                        .inputs(getEmitterByTier(i).getStackForm(2))
                        .input(circuit, getMarkerMaterialByTier(i), 8)
                        .input(wireFine, getSuperconductorByTier(i), 64)
                        .input(wireFine, getSuperconductorByTier(i), 64)
                        .input(cableGtOctal, getCableByTier(i), 4)
                        .inputs(CONTAINED_EXOTIC_MATTER.getStackForm())
                        .inputs(STABILIZED_WORMHOLE_GENERATOR.getStackForm())
                        .fluidInputs(Indalloy140.getFluid((int) (L * 12 * Math.pow(2, i - 8))))
                        //.fluidInputs(getFluidMaterialByTier(i).getFluid(L * 4 * (i - 7)))
                        .fluidInputs(getFluidMaterialByTier(i).getFluid((int) (L * 4 * (Math.pow(2, i - 8)))))
                        .fluidInputs(HighEnergyQGP.getFluid(2000))
                        .buildAndRegister();
            } else if(i == 13) {
                getAssLineResearchBuilder(i, 600, getFieldGeneratorByTier(i - 1).getStackForm(), false, false, (int) VA[i])
                        .outputs(getFieldGeneratorByTier(i).getStackForm())
                        .input(frameGt, getMainComponentMaterialByTier(i))
                        .input(plate, getMainComponentMaterialByTier(i), 6)
                        .inputs(getStarByTier(i).getStackForm(i % 2 == 0 ? 2 : 4))
                        .inputs(getEmitterByTier(i).getStackForm(2))
                        .input(circuit, getMarkerMaterialByTier(i), 8)
                        .input(wireFine, getSuperconductorByTier(i), 64)
                        .input(wireFine, getSuperconductorByTier(i), 64)
                        .input(cableGtOctal, getCableByTier(i), 4)
                        .inputs(BATTERY_LARGE_FLUORIDE.getStackForm())
                        .inputs(CONTAINED_REISSNER_NORDSTROM_SINGULARITY.getStackForm())
                        .inputs(CRYOGENIC_INTERFACE.getStackForm())
                        .fluidInputs(Indalloy140.getFluid((int) (L * 12 * Math.pow(2, i - 8))))
                        //.fluidInputs(getFluidMaterialByTier(i).getFluid(L * 4 * (i - 7)))
                        .fluidInputs(getFluidMaterialByTier(i).getFluid((int) (L * 4 * (Math.pow(2, i - 8)))))
                        .fluidInputs(QuarkGluonPlasma.getFluid(2000))
                        .buildAndRegister();

            } else {
                getAssLineResearchBuilder(i, 600, getFieldGeneratorByTier(i - 1).getStackForm(), false, false, (int) VA[i])
                        .outputs(getFieldGeneratorByTier(i).getStackForm())
                        .input(frameGt, getMainComponentMaterialByTier(i))
                        .input(plate, getMainComponentMaterialByTier(i), 6)
                        .inputs(getStarByTier(i).getStackForm(i % 2 == 0 ? 2 : 4))
                        .inputs(getEmitterByTier(i).getStackForm(2))
                        .input(circuit, getMarkerMaterialByTier(i), 8)
                        .input(wireFine, getSuperconductorByTier(i), 64)
                        .input(wireFine, getSuperconductorByTier(i), 64)
                        .input(cableGtOctal, getCableByTier(i), 4)
                        .fluidInputs(Indalloy140.getFluid((int) (L * 12 * Math.pow(2, i - 8))))
                        //.fluidInputs(getFluidMaterialByTier(i).getFluid(L * 4 * (i - 7)))
                        .fluidInputs(getFluidMaterialByTier(i).getFluid((int) (L * 4 * (Math.pow(2, i - 8)))))
                        .buildAndRegister();
            }
        }

/*

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(1966080)
                .input(frameGt, Seaborgium)
                .inputs(GRAVI_STAR.getStackForm())
                .input(wireGtQuadruple, UHVSuperconductor, 8)
                .input(cableGtSingle, TungstenTitaniumCarbide, 4)
                .input(circuit, MarkerMaterials.Tier.UHV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .outputs(FIELD_GENERATOR_UHV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(7864320)
                .input(frameGt, Bohrium)
                .inputs(UNSTABLE_STAR.getStackForm())
                .input(wireGtQuadruple, UEVSuperconductor, 8)
                .input(wireFine, Seaborgium, 64)
                .input(cableGtSingle, Pikyonium, 4)
                .input(circuit, MarkerMaterials.Tier.UEV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .outputs(FIELD_GENERATOR_UEV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(7864320)
                .input(frameGt, Trinium)
                .inputs(UNSTABLE_STAR.getStackForm())
                .input(wireFine, Trinium, 64)
                .input(wireGtQuadruple, UIVSuperconductor, 8)
                .input(cableGtSingle, BlackTitanium, 4)
                .input(circuit, MarkerMaterials.Tier.UIV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 6))
                .outputs(FIELD_GENERATOR_UIV.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(15728640)
                .input(frameGt, Adamantium)
                .inputs(NUCLEAR_STAR.getStackForm())
                .input(wireFine, Adamantium, 64)
                .input(wireGtQuadruple, UXVSuperconductor, 8)
                .input(cableGtSingle, NaquadriaticTaranium, 4)
                .input(circuit, MarkerMaterials.Tier.UXV, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 6))
                .outputs(FIELD_GENERATOR_UXV.getStackForm())
                .buildAndRegister();

 */
    }

    private static void robotArmInit() {

        /* original luv robot arm
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

         */

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(stickLong, HSSS, 4)
                .input(gear, HSSS)
                .input(gearSmall, HSSS, 3)
                .input(ELECTRIC_MOTOR_LuV, 2)
                .input(ELECTRIC_PISTON_LUV)
                .input(circuit, MarkerMaterials.Tier.LuV)
                .input(circuit, MarkerMaterials.Tier.IV, 2)
                .input(circuit, MarkerMaterials.Tier.EV, 4)
                .input(cableGtDouble, YttriumBariumCuprate, 6)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .fluidInputs(Lubricant.getFluid(250))
                .output(ROBOT_ARM_LuV)
                .scannerResearch(ROBOT_ARM_IV.getStackForm())
                .duration(600).EUt(20480).buildAndRegister();


        /* original zpm robot arm
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

         */

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(stickLong, HSSE, 4)
                .input(gear, HSSE)
                .input(gearSmall, HSSE, 3)
                .input(ELECTRIC_MOTOR_ZPM, 2)
                .input(ELECTRIC_PISTON_ZPM)
                .input(circuit, MarkerMaterials.Tier.ZPM)
                .input(circuit, MarkerMaterials.Tier.LuV, 2)
                .input(circuit, MarkerMaterials.Tier.IV, 4)
                .input(cableGtDouble, Naquadah, 6)
                .fluidInputs(SolderingAlloy.getFluid(L * 8))
                .fluidInputs(Lubricant.getFluid(500))
                .output(ROBOT_ARM_ZPM)
                .scannerResearch(b -> b
                        .researchStack(ROBOT_ARM_LuV.getStackForm())
                        .duration(1200)
                        .EUt(VA[GTValues.IV]))
                .duration(600).EUt(81920).buildAndRegister();

        /*
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

         */

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(stickLong, Tritanium, 4)
                .input(gear, Tritanium)
                .input(gearSmall, Tritanium, 3)
                .input(ELECTRIC_MOTOR_UV, 2)
                .input(ELECTRIC_PISTON_UV)
                .input(circuit, MarkerMaterials.Tier.UV)
                .input(circuit, MarkerMaterials.Tier.ZPM, 2)
                .input(circuit, MarkerMaterials.Tier.LuV, 4)
                .input(cableGtSingle, Duranium, 6)
                .fluidInputs(SolderingAlloy.getFluid(L * 12))
                .fluidInputs(Lubricant.getFluid(1000))
                .fluidInputs(Naquadria.getFluid(L * 4))
                .output(ROBOT_ARM_UV)
                .stationResearch(b -> b
                        .researchStack(ROBOT_ARM_ZPM.getStackForm())
                        .CWUt(32)
                        .EUt(VA[GTValues.UV]))
                .duration(600).EUt(327680).buildAndRegister();


        for(int i = 9; i < 15; i++) {
            if (i == 14) {
                getAssLineResearchBuilder(i, 600, getRobotArmByTier(i - 1).getStackForm(), false, false, (int) V[MAX])
                        .outputs(getRobotArmByTier(i).getStackForm())
                        .input(stickLong, getMainComponentMaterialByTier(i), 4)
                        .input(gear, getMainComponentMaterialByTier(i+1))
                        .input(gearSmall, getMainComponentMaterialByTier(i+1), 3)
                        .input(getMotorByTier(i), 2)
                        .input(getPistonByTier(i))
                        .input(circuit, getMarkerMaterialByTier(i))
                        .input(circuit, getMarkerMaterialByTier(i-1), 2)
                        .input(circuit, getMarkerMaterialByTier(i-2), 4)
                        .input(cableGtSingle, getCableByTier(i), 6)
                        .fluidInputs(Indalloy140.getFluid((int) (L * 12 * Math.pow(2, i - 8))))
                        .fluidInputs(Lubricant.getFluid((int) (1000 * Math.pow(2, i - 8))))
                        //.fluidInputs(getFluidMaterialByTier(i).getFluid(L * 4 * (i - 7)))
                        .fluidInputs(getFluidMaterialByTier(i).getFluid((int) (L * 4 * (Math.pow(2, i - 8)))))
                        .buildAndRegister();
            } else {
                getAssLineResearchBuilder(i, 600, getRobotArmByTier(i - 1).getStackForm(), false, false, (int) (1310720 * Math.pow(2, i - 9)))
                        .outputs(getRobotArmByTier(i).getStackForm())
                        .input(stickLong, getMainComponentMaterialByTier(i), 4)
                        .input(gear, getMainComponentMaterialByTier(i))
                        .input(gearSmall, getMainComponentMaterialByTier(i), 3)
                        .input(getMotorByTier(i), 2)
                        .input(getPistonByTier(i))
                        .input(circuit, getMarkerMaterialByTier(i))
                        .input(circuit, getMarkerMaterialByTier(i-1), 2)
                        .input(circuit, getMarkerMaterialByTier(i-2), 4)
                        .input(cableGtSingle, getCableByTier(i), 6)
                        .fluidInputs(Indalloy140.getFluid((int) (L * 12 * Math.pow(2, i - 8))))
                        .fluidInputs(Lubricant.getFluid((int) (1000 * Math.pow(2, i - 8))))
                        //.fluidInputs(getFluidMaterialByTier(i).getFluid(L * 4 * (i - 7)))
                        .fluidInputs(getFluidMaterialByTier(i).getFluid((int) (L * 4 * (Math.pow(2, i - 8)))))
                        .buildAndRegister();
            }
        }


        /*

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(1310720)
                .input(cableGtDouble, TungstenTitaniumCarbide, 16)
                .input(screw, HDCS, 16)
                .input(stick, HDCS, 16)
                .input(ingot, HDCS)
                .inputs(ELECTRIC_MOTOR_UHV.getStackForm(2))
                .inputs(ELECTRIC_PISTON_UHV.getStackForm())
                .input(circuit, MarkerMaterials.Tier.UHV, 8)
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
                .input(circuit, MarkerMaterials.Tier.UEV, 8)
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
                .input(circuit, MarkerMaterials.Tier.UIV, 8)
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
                .input(circuit, MarkerMaterials.Tier.UXV, 8)
                .fluidInputs(SolderingAlloy.getFluid(L * 6))
                .fluidInputs(Lubricant.getFluid(2000))
                .outputs(ROBOT_ARM_UXV.getStackForm())
                .buildAndRegister();

         */
    }

    private static void conveyorInit() {

        /* original luv conveyor
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
         */
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(ELECTRIC_MOTOR_LuV, 2)
                .input(plate, HSSS, 2)
                .input(ring, HSSS, 4)
                .input(round, HSSS, 16)
                .input(screw, HSSS, 4)
                .input(cableGtSingle, YttriumBariumCuprate, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .fluidInputs(Lubricant.getFluid(250))
                .fluidInputs(StyreneButadieneRubber.getFluid(L * 8))
                .output(CONVEYOR_MODULE_LuV)
                .scannerResearch(CONVEYOR_MODULE_IV.getStackForm())
                .duration(600).EUt(15360).buildAndRegister();

/* original zpm conveyor
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
 */
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(ELECTRIC_MOTOR_ZPM, 2)
                .input(plate, HSSE, 2)
                .input(ring, HSSE, 4)
                .input(round, HSSE, 16)
                .input(screw, HSSE, 4)
                .input(cableGtSingle, Naquadah, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .fluidInputs(Lubricant.getFluid(500))
                .fluidInputs(StyreneButadieneRubber.getFluid(L * 16))
                .output(CONVEYOR_MODULE_ZPM)
                .scannerResearch(b -> b
                        .researchStack(CONVEYOR_MODULE_LuV.getStackForm())
                        .duration(1200)
                        .EUt(VA[GTValues.IV]))
                .duration(600).EUt(61440).buildAndRegister();

/* original uv conveyor
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

 */
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(ELECTRIC_MOTOR_UV, 2)
                .input(plate, Tritanium, 2)
                .input(ring, Tritanium, 4)
                .input(round, Tritanium, 16)
                .input(screw, Tritanium, 4)
                .input(cableGtSingle, Duranium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .fluidInputs(Lubricant.getFluid(1000))
                .fluidInputs(StyreneButadieneRubber.getFluid(L * 24))
                .fluidInputs(Naquadria.getFluid(L * 4))
                .output(CONVEYOR_MODULE_UV)
                .stationResearch(b -> b
                        .researchStack(CONVEYOR_MODULE_ZPM.getStackForm())
                        .CWUt(32)
                        .EUt(VA[GTValues.UV]))
                .duration(600).EUt(245760).buildAndRegister();


        for(int i = 9; i < 15; i++) {
            if(i == 14) {
                getAssLineResearchBuilder(i, 600, getConveyorByTier(i - 1).getStackForm(), false, false, (int) V[MAX])
                        .output(getConveyorByTier(i))
                        .inputs(getMotorByTier(i).getStackForm(2))
                        .input(plate, getMainComponentMaterialByTier(i), 2)
                        .input(ring, getMainComponentMaterialByTier(i), 4)
                        .input(round, getMainComponentMaterialByTier(i+1), 16)
                        .input(screw, getMainComponentMaterialByTier(i+1), 4)
                        .input(cableGtSingle, getCableByTier(i), 2)
                        .inputs(MACROWORMHOLE_GENERATOR.getStackForm())
                        .fluidInputs(Indalloy140.getFluid((int) (L * 4 * Math.pow(2, i - 8))))
                        .fluidInputs(Lubricant.getFluid((int) (1000 * Math.pow(2, i - 8))))
                        .fluidInputs(getPolymerByTier(i).getFluid(L * 24 * (i - 7)))
                        //.fluidInputs(getFluidMaterialByTier(i).getFluid(L * 4 * (i - 7)))
                        .fluidInputs(getFluidMaterialByTier(i).getFluid((int) (L * 4 * (Math.pow(2, i - 8)))))
                        .buildAndRegister();
            }
            else {
                getAssLineResearchBuilder(i, 600, getConveyorByTier(i - 1).getStackForm(), false, false,  (int) (983040 * Math.pow(2, i - 9)))
                        .output(getConveyorByTier(i))
                        .inputs(getMotorByTier(i).getStackForm(2))
                        .input(plate, getMainComponentMaterialByTier(i), 2)
                        .input(ring, getMainComponentMaterialByTier(i), 4)
                        .input(round, getMainComponentMaterialByTier(i), 16)
                        .input(screw, getMainComponentMaterialByTier(i), 4)
                        .input(cableGtSingle, getCableByTier(i), 2)
                        .fluidInputs(Indalloy140.getFluid((int) (L * 4 * Math.pow(2, i - 8))))
                        .fluidInputs(Lubricant.getFluid((int) (1000 * Math.pow(2, i - 8))))
                        .fluidInputs(getPolymerByTier(i).getFluid(L * 24 * (i - 7)))
                        //.fluidInputs(getFluidMaterialByTier(i).getFluid(L * 4 * (i - 7)))
                        .fluidInputs(getFluidMaterialByTier(i).getFluid((int) (L * 4 * (Math.pow(2, i - 8)))))
                        .buildAndRegister();
            }
        }


        /*
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

         */
    }

    private static void pistonInit() {

        /* original piston luv
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

         */

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(ELECTRIC_MOTOR_LuV)
                .input(plate, HSSS, 4)
                .input(ring, HSSS, 4)
                .input(round, HSSS, 16)
                .input(stick, HSSS, 4)
                .input(gear, HSSS)
                .input(gearSmall, HSSS, 2)
                .input(cableGtSingle, YttriumBariumCuprate, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .fluidInputs(Lubricant.getFluid(250))
                .output(ELECTRIC_PISTON_LUV)
                .scannerResearch(ELECTRIC_PISTON_IV.getStackForm())
                .duration(600).EUt(15360).buildAndRegister();

/* original piston zpm
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

         */

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(ELECTRIC_MOTOR_ZPM)
                .input(plate, HSSE, 4)
                .input(ring, HSSE, 4)
                .input(round, HSSE, 16)
                .input(stick, HSSE, 4)
                .input(gear, HSSE)
                .input(gearSmall, HSSE, 2)
                .input(cableGtSingle, Naquadah, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .fluidInputs(Lubricant.getFluid(500))
                .output(ELECTRIC_PISTON_ZPM)
                .scannerResearch(b -> b
                        .researchStack(ELECTRIC_PISTON_LUV.getStackForm())
                        .duration(1200)
                        .EUt(VA[GTValues.IV]))
                .duration(600).EUt(61440).buildAndRegister();

/* original piston uv
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

         */

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(ELECTRIC_MOTOR_UV)
                .input(plate, Tritanium, 4)
                .input(ring, Tritanium, 4)
                .input(round, Tritanium, 16)
                .input(stick, Tritanium, 4)
                .input(gear, Tritanium)
                .input(gearSmall, Tritanium, 2)
                .input(cableGtSingle, Duranium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .fluidInputs(Lubricant.getFluid(1000))
                .fluidInputs(Naquadria.getFluid(L * 4))
                .output(ELECTRIC_PISTON_UV)
                .stationResearch(b -> b
                        .researchStack(ELECTRIC_PISTON_ZPM.getStackForm())
                        .CWUt(32)
                        .EUt(VA[GTValues.UV]))
                .duration(600).EUt(245760).buildAndRegister();


        for(int i = 9; i < 15; i++) {
            if (i == 14) {
                getAssLineResearchBuilder(i, 600, getPistonByTier(i - 1).getStackForm(), false, false, (int) V[MAX])
                        .outputs(getPistonByTier(i).getStackForm())
                        .inputs(getMotorByTier(i).getStackForm())
                        .input(plate, getMainComponentMaterialByTier(i), 4)
                        .input(ring, getMainComponentMaterialByTier(i), 4)
                        .input(round, getMainComponentMaterialByTier(i), 16)
                        .input(stick, getMainComponentMaterialByTier(i), 4)
                        .input(gear, getMainComponentMaterialByTier(i+1))
                        .input(gearSmall, getMainComponentMaterialByTier(i+1), 2)
                        .input(cableGtSingle, getCableByTier(i), 2)
                        .fluidInputs(Indalloy140.getFluid((int) (L * 4 * Math.pow(2, i - 8))))
                        .fluidInputs(Lubricant.getFluid((int) (1000 * Math.pow(2, i - 8))))
                        //.fluidInputs(getFluidMaterialByTier(i).getFluid(L * 4 * (i - 7)))
                        .fluidInputs(getFluidMaterialByTier(i).getFluid((int) (L * 4 * (Math.pow(2, i - 8)))))
                        .buildAndRegister();
            } else {
                getAssLineResearchBuilder(i, 600, getPistonByTier(i - 1).getStackForm(), false, false, (int) (983040 * Math.pow(2, i)))
                        .outputs(getPistonByTier(i).getStackForm())
                        .inputs(getMotorByTier(i).getStackForm())
                        .input(plate, getMainComponentMaterialByTier(i), 4)
                        .input(ring, getMainComponentMaterialByTier(i), 4)
                        .input(round, getMainComponentMaterialByTier(i), 16)
                        .input(stick, getMainComponentMaterialByTier(i), 4)
                        .input(gear, getMainComponentMaterialByTier(i))
                        .input(gearSmall, getMainComponentMaterialByTier(i), 2)
                        .input(cableGtSingle, getCableByTier(i), 2)
                        .fluidInputs(Indalloy140.getFluid((int) (L * 4 * Math.pow(2, i - 8))))
                        .fluidInputs(Lubricant.getFluid((int) (1000 * Math.pow(2, i - 8))))
                        //.fluidInputs(getFluidMaterialByTier(i).getFluid(L * 4 * (i - 7)))
                        .fluidInputs(getFluidMaterialByTier(i).getFluid((int) (L * 4 * (Math.pow(2, i - 8)))))
                        .buildAndRegister();
            }
        }

        /*
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

         */
    }

    private static void motorInit() {

        /* original motors
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

         */

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(stickLong, NeodymiumMagnetic)
                .input(stickLong, HSSS, 2)
                .input(ring, HSSS, 2)
                .input(round, HSSS, 4)
                .input(wireFine, Ruridit, 64)
                .input(wireFine, Ruridit, 64)
                .input(wireFine, Ruridit, 64)
                .input(wireFine, Ruridit, 64)
                .input(cableGtSingle, YttriumBariumCuprate, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .fluidInputs(Lubricant.getFluid(250))
                .output(ELECTRIC_MOTOR_LuV)
                .scannerResearch(ELECTRIC_MOTOR_IV.getStackForm())
                .duration(600).EUt(10240).buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(stickLong, NeodymiumMagnetic,16)
                .input(stickLong, HSSE, 4)
                .input(ring, HSSE, 4)
                .input(round, HSSE, 8)
                .input(wireFine, Europium, 64)
                .input(wireFine, Europium, 64)
                .input(wireFine, Europium, 64)
                .input(wireFine, Europium, 64)
                .input(cableGtSingle, Naquadah, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .fluidInputs(Lubricant.getFluid(500))
                .output(ELECTRIC_MOTOR_ZPM)
                .scannerResearch(b -> b
                        .researchStack(ELECTRIC_MOTOR_LuV.getStackForm())
                        .duration(1200)
                        .EUt(VA[GTValues.ZPM]))
                .duration(600).EUt(40960).buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(stickLong, NeodymiumMagnetic,64)
                .input(stickLong, Tritanium, 4)
                .input(ring, Tritanium, 4)
                .input(round, Tritanium, 8)
                .input(wireFine, Duranium, 64)
                .input(wireFine, Duranium, 64)
                .input(wireFine, Duranium, 64)
                .input(wireFine, Duranium, 64)
                .input(cableGtSingle, Duranium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .fluidInputs(Lubricant.getFluid(1000))
                .fluidInputs(Naquadria.getFluid(L * 4))
                .output(ELECTRIC_MOTOR_UV)
                .stationResearch(b -> b
                        .researchStack(ELECTRIC_MOTOR_ZPM.getStackForm())
                        .CWUt(32)
                        .EUt(VA[GTValues.UV]))
                .duration(600).EUt(163840).buildAndRegister();




        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(655360)
                .outputs(ELECTRIC_MOTOR_UHV.getStackForm())
                .input(stickLong, NeodymiumMagnetic, 64)
                .input(stickLong, NeodymiumMagnetic, 64)
                .input(stickLong, HDCS, 4)
                .input(ring, HDCS, 4)
                .input(round, HDCS, 8)
                .input(wireFine, TungstenTitaniumCarbide, 64)
                .input(wireFine, TungstenTitaniumCarbide, 64)
                .input(wireFine, TungstenTitaniumCarbide, 64)
                .input(wireFine, TungstenTitaniumCarbide, 64)
                .input(cableGtSingle, TungstenTitaniumCarbide, 2)
                .fluidInputs(Indalloy140.getFluid(L * 8))
                .fluidInputs(Lubricant.getFluid(2000))
                //.fluidInputs(Seaborgium.getFluid(L * 4 * 2))
                .fluidInputs(Seaborgium.getFluid(L * 4))
                .stationResearch(b -> b
                        .researchStack(ELECTRIC_MOTOR_UV.getStackForm())
                        .CWUt(128)
                        .EUt(VA[GTValues.UHV]))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(2621440)
                .outputs(ELECTRIC_MOTOR_UEV.getStackForm())
                .input(stickLong, SamariumMagnetic, 64)
                .input(stickLong, SamariumMagnetic, 64)
                .input(stickLong, EnrichedNaquadahAlloy, 4)
                .input(ring, EnrichedNaquadahAlloy, 4)
                .input(round, EnrichedNaquadahAlloy, 8)
                .input(wireFine, Pikyonium, 64)
                .input(wireFine, Pikyonium, 64)
                .input(wireFine, Pikyonium, 64)
                .input(wireFine, Pikyonium, 64)
                .input(cableGtSingle, Pikyonium, 2)
                .fluidInputs(Indalloy140.getFluid(L * 16))
                .fluidInputs(Lubricant.getFluid(4000))
               // .fluidInputs(Bohrium.getFluid(L * 4 * 3))
                .fluidInputs(Bohrium.getFluid(L * 8))

                .stationResearch(b -> b
                        .researchStack(ELECTRIC_MOTOR_UHV.getStackForm())
                        .CWUt(256)
                        .dataStack(TOOL_DATA_MODULE_CLUSTER.getStackForm())
                        .EUt(VA[GTValues.UEV]))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(10485760)
                .outputs(ELECTRIC_MOTOR_UIV.getStackForm())
                .input(stickLong, SamariumMagnetic, 64)
                .input(stickLong, SamariumMagnetic, 64)
                .input(stickLong, HastelloyX78, 4)
                .input(ring, HastelloyX78, 4)
                .input(round, HastelloyX78, 8)
                .input(wireFine, CarbonNanotubes, 64)
                .input(wireFine, CarbonNanotubes, 64)
                .input(wireFine, CarbonNanotubes, 64)
                .input(wireFine, CarbonNanotubes, 64)
                .input(cableGtSingle, BlackTitanium, 2)
                .fluidInputs(Indalloy140.getFluid(L * 32))
                .fluidInputs(Lubricant.getFluid(8000))
                //.fluidInputs(MetastableHassium.getFluid(L * 4 * 4))
                .fluidInputs(MetastableHassium.getFluid(L * 16))
                .stationResearch(b -> b
                        .researchStack(ELECTRIC_MOTOR_UEV.getStackForm())
                        .CWUt(512)
                        .dataStack(TOOL_DATA_MODULE_CLUSTER.getStackForm())
                        .EUt(VA[GTValues.UIV]))
                .buildAndRegister();

        //TODO: Change this from carbon nanotubes
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(41943040)
                .outputs(ELECTRIC_MOTOR_UXV.getStackForm())
                .input(stickLong, SamariumMagnetic, 64)
                .input(stickLong, SamariumMagnetic, 64)
                .input(stickLong, HastelloyK243, 4)
                .input(ring, HastelloyK243, 4)
                .input(round, HastelloyK243, 8)
                .input(wireFine, CarbonNanotubes, 64)
                .input(wireFine, CarbonNanotubes, 64)
                .input(wireFine, CarbonNanotubes, 64)
                .input(wireFine, CarbonNanotubes, 64)
                .input(wireFine, CarbonNanotubes, 64)
                .input(wireFine, CarbonNanotubes, 64)
                .input(wireFine, CarbonNanotubes, 64)
                .input(wireFine, CarbonNanotubes, 64)
                .input(cableGtSingle, NaquadriaticTaranium, 2)
                .fluidInputs(Indalloy140.getFluid(L * 64))
                .fluidInputs(Lubricant.getFluid(16000))
              //  .fluidInputs(HeavyQuarkDegenerateMatter.getFluid(L * 4 * 5))
                .fluidInputs(HeavyQuarkDegenerateMatter.getFluid(L * 32))
                .stationResearch(b -> b
                        .researchStack(ELECTRIC_MOTOR_UIV.getStackForm())
                        .CWUt(1024)
                        .dataStack(TOOL_DATA_ULTIMATE.getStackForm())
                        .EUt(VA[GTValues.UXV]))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt(536870900)
                .outputs(ELECTRIC_MOTOR_OpV.getStackForm())
                .input(stickLong, NaquadriaticTaranium, 64)
                .input(stickLong, NaquadriaticTaranium, 32)
                .input(stickLong, Neutronium, 4)
                .input(ring, Neutronium, 4)
                .input(round, Neutronium, 8)
                .input(wireFine, MetastableOganesson, 64)
                .input(wireFine, MetastableOganesson, 64)
                .input(wireFine, MetastableOganesson, 64)
                .input(wireFine, MetastableOganesson, 64)
                .input(cableGtSingle, Neutronium, 2)
                .fluidInputs(Indalloy140.getFluid(L * 128))
                .fluidInputs(Lubricant.getFluid(32000))
                //.fluidInputs(SuperheavyMix.getFluid(L * 4 * 6))
                .fluidInputs(SuperheavyMix.getFluid(L * 64))
                .stationResearch(b -> b
                        .researchStack(ELECTRIC_MOTOR_UXV.getStackForm())
                        .CWUt(2048)
                        .dataStack(TOOL_DATA_ULTIMATE.getStackForm())
                        .EUt(VA[GTValues.OpV]))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(600).EUt((int) GTValues.V[GTValues.MAX])
                .outputs(ELECTRIC_MOTOR_MAX.getStackForm())
                .input(stickLong, NaquadriaticTaranium, 64)
                .input(stickLong, NaquadriaticTaranium, 64)
                .input(stickLong, NaquadriaticTaranium, 64)
                .input(wireFine, HeavyQuarkDegenerateMatter, 64)
                .input(wireFine, HeavyQuarkDegenerateMatter, 64)
                .input(wireFine, HeavyQuarkDegenerateMatter, 64)
                .input(wireFine, HeavyQuarkDegenerateMatter, 64)
                .input(stickLong, CosmicNeutronium, 4)
                .input(ring, CosmicNeutronium, 4)
                .input(round, CosmicNeutronium, 8)
                .input(COSMIC_FABRIC, 64)
                .input(cableGtSingle, CosmicNeutronium, 2)
                .inputs(MACROWORMHOLE_GENERATOR.getStackForm())
                .fluidInputs(Indalloy140.getFluid(L * 256))
                .fluidInputs(Lubricant.getFluid(64000))
              //  .fluidInputs(Neutronium.getFluid(L * 4 * 7))
                .fluidInputs(Neutronium.getFluid(L * 128))
                .stationResearch(b -> b
                        .researchStack(ELECTRIC_MOTOR_OpV.getStackForm())
                        .CWUt(4096)
                        .dataStack(TOOL_DATA_SUPRACAUSAL.getStackForm())
                        .EUt((int) V[GTValues.MAX]))
                .buildAndRegister();
    }

    private static void pumpInit() {

        /* original pump recipes
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
         */

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(ELECTRIC_MOTOR_LuV)
                .input(pipeSmallFluid, NiobiumTitanium,2)
                .input(plate, HSSS, 2)
                .input(screw, HSSS, 8)
                .input(ring, SiliconeRubber, 4)
                .input(rotor, HSSS)
                .input(cableGtSingle, YttriumBariumCuprate, 2)
                .fluidInputs(SolderingAlloy.getFluid(L))
                .fluidInputs(Lubricant.getFluid(250))
                .output(ELECTRIC_PUMP_LuV)
                .scannerResearch(ELECTRIC_PUMP_IV.getStackForm())
                .duration(600).EUt(15360).buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(ELECTRIC_MOTOR_ZPM)
                .input(pipeNormalFluid, Polybenzimidazole,2)
                .input(plate, HSSE, 2)
                .input(screw, HSSE, 8)
                .input(ring, SiliconeRubber, 8)
                .input(rotor, HSSE)
                .input(cableGtSingle, Naquadah, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .fluidInputs(Lubricant.getFluid(500))
                .output(ELECTRIC_PUMP_ZPM)
                .scannerResearch(b -> b
                        .researchStack(ELECTRIC_PUMP_LuV.getStackForm())
                        .duration(1200)
                        .EUt(VA[GTValues.IV]))
                .duration(600).EUt(61440).buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(ELECTRIC_MOTOR_UV)
                .input(pipeLargeFluid, Naquadah,2)
                .input(plate, Tritanium, 2)
                .input(screw, Tritanium, 8)
                .input(ring, SiliconeRubber, 16)
                .input(rotor, Tritanium)
                .input(cableGtSingle, Duranium, 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .fluidInputs(Lubricant.getFluid(1000))
                .fluidInputs(Naquadria.getFluid(L * 4))
                .output(ELECTRIC_PUMP_UV)
                .stationResearch(b -> b
                        .researchStack(ELECTRIC_PUMP_ZPM.getStackForm())
                        .CWUt(32)
                        .EUt(VA[GTValues.UV]))
                .duration(600).EUt(245760).buildAndRegister();

        for(int i = 9; i < 15; i++) {
            if (i == 14) {
                getAssLineResearchBuilder(i, 600, getPumpByTier(i - 1).getStackForm(), false, false, (int) V[MAX])
                        .outputs(getPumpByTier(i).getStackForm())
                        .input(getMotorByTier(i))
                        .input(pipeLargeFluid, getFluidPipeMaterialByTier(i), 32)
                        .input(plate, getMainComponentMaterialByTier(i), 2)
                        .input(screw, getMainComponentMaterialByTier(i), 8)
                        .input(ring, SiliconeRubber, 16)
                        .input(rotor, getMainComponentMaterialByTier(i))
                        .input(cableGtSingle, getCableByTier(i), 2)
                        .inputs(MACROWORMHOLE_GENERATOR.getStackForm())
                        .fluidInputs(Indalloy140.getFluid((int) (L * 4 * Math.pow(2, i - 8))))
                        .fluidInputs(Lubricant.getFluid((int) (1000 * Math.pow(2, i - 8))))
                        //.fluidInputs(getFluidMaterialByTier(i).getFluid(L * 4 * (i - 7)))
                        .fluidInputs(getFluidMaterialByTier(i).getFluid((int) (L * 4 * (Math.pow(2, i - 8)))))
                        .buildAndRegister();
            } else {
                getAssLineResearchBuilder(i, 600, getPumpByTier(i - 1).getStackForm(), false, false, (int) (983040 * Math.pow(2, i)))
                        .outputs(getPumpByTier(i).getStackForm())
                        .input(getMotorByTier(i))
                        .input(pipeLargeFluid, getFluidPipeMaterialByTier(i), 32)
                        .input(plate, getMainComponentMaterialByTier(i), 2)
                        .input(screw, getMainComponentMaterialByTier(i), 8)
                        .input(ring, SiliconeRubber, 16)
                        .input(rotor, getMainComponentMaterialByTier(i))
                        .input(cableGtSingle, getCableByTier(i), 2)
                        .fluidInputs(Indalloy140.getFluid((int) (L * 4 * Math.pow(2, i - 8))))
                        .fluidInputs(Lubricant.getFluid((int) (1000 * Math.pow(2, i - 8))))
                        //.fluidInputs(getFluidMaterialByTier(i).getFluid(L * 4 * (i - 7)))
                        .fluidInputs(getFluidMaterialByTier(i).getFluid((int) (L * 4 * (Math.pow(2, i - 8)))))
                        .buildAndRegister();
            }
        }

        /*

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

         */
    }

}
