package com.fulltrix.gcyl.recipes.categories;

import com.fulltrix.gcyl.api.recipes.builders.ComponentALRecipeBuilder;
import gregtech.api.GTValues;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.unification.material.MarkerMaterials;

import static com.fulltrix.gcyl.api.GCYLUtility.*;
import static com.fulltrix.gcyl.api.GCYLUtility.getMainComponentMaterialByTier;
import static com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps.COMPONENT_AL_RECIPES;
import static com.fulltrix.gcyl.item.GCYLCoreItems.*;
import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static com.fulltrix.gcyl.materials.chains.MiscMaterials.Irirutan;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;
import static gregtech.api.GTValues.L;

public class ComponentALRecipes {
    public static void init() {

        luv();
        components();
        max();

    }

    private static void luv() {

        COMPONENT_AL_RECIPES.recipeBuilder().EUt(GTValues.VA[GTValues.LuV - 1]).duration(28800)
                .CWUt(getCWUt(GTValues.LuV))
                .outputs(ELECTRIC_MOTOR_LuV.getStackForm(64))
                .CasingTier(GTValues.LuV)
                .circuitMeta(1)
                .input(stickLong, NeodymiumMagnetic, 48)
                .input(cableGtHex, YttriumBariumCuprate, 6)
                .fluidInputs(SolderingAlloy.getFluid(L * 48))
                .fluidInputs(Lubricant.getFluid(12000))
                .fluidInputs(Irirutan.getFluid(L * 1536))
                .fluidInputs(HSSS.getFluid(20352))
                .buildAndRegister();

        COMPONENT_AL_RECIPES.recipeBuilder().EUt(GTValues.VA[GTValues.LuV - 1]).duration(28800)
                .CWUt(getCWUt(GTValues.LuV))
                .outputs(ELECTRIC_PISTON_LUV.getStackForm(64))
                .CasingTier(GTValues.LuV)
                .circuitMeta(2)
                .input(plateDense, HSSS, 21)
                .inputs(ELECTRIC_MOTOR_LuV.getStackForm(48))
                .input(cableGtHex, YttriumBariumCuprate, 6)
                .fluidInputs(SolderingAlloy.getFluid(L * 48))
                .fluidInputs(Lubricant.getFluid(12000))
                .fluidInputs(HSSS.getFluid(74544))
                .buildAndRegister();

        COMPONENT_AL_RECIPES.recipeBuilder().EUt(GTValues.VA[GTValues.LuV - 1]).duration(28800)
                .CWUt(getCWUt(GTValues.LuV))
                .outputs(ELECTRIC_PUMP_LuV.getStackForm(64))
                .CasingTier(GTValues.LuV)
                .circuitMeta(3)
                .input(cableGtHex, YttriumBariumCuprate, 6)
                .input(plateDense, HSSS, 10)
                .inputs(ELECTRIC_MOTOR_LuV.getStackForm(48))
                .fluidInputs(SolderingAlloy.getFluid(L * 48))
                .fluidInputs(Lubricant.getFluid(12000))
                .fluidInputs(NiobiumTitanium.getFluid(L * 96))
                .fluidInputs(HSSS.getFluid(47616))
                .fluidInputs(SiliconeRubber.getFluid(L * 48))
                .buildAndRegister();

        COMPONENT_AL_RECIPES.recipeBuilder().EUt(GTValues.VA[GTValues.LuV - 1]).duration(28800)
                .CWUt(getCWUt(GTValues.LuV))
                .outputs(ROBOT_ARM_LuV.getStackForm(64))
                .CasingTier(GTValues.LuV)
                .circuitMeta(4)
                .input(circuit, MarkerMaterials.Tier.LuV, 48)
                .input(circuit, MarkerMaterials.Tier.IV, 96)
                .input(circuit, MarkerMaterials.Tier.EV, 192)
                .input(cableGtHex, YttriumBariumCuprate, 18)
                .inputs(ELECTRIC_PISTON_LUV.getStackForm(48))
                .inputs(ELECTRIC_MOTOR_LuV.getStackForm(96))
                .fluidInputs(SolderingAlloy.getFluid(192 * L))
                .fluidInputs(Lubricant.getFluid(12000))
                .fluidInputs(HSSS.getFluid(528 * L))
                .buildAndRegister();


        COMPONENT_AL_RECIPES.recipeBuilder().EUt(GTValues.VA[GTValues.LuV - 1]).duration(28800)
                .CWUt(getCWUt(GTValues.LuV))
                .outputs(CONVEYOR_MODULE_LuV.getStackForm(64))
                .CasingTier(GTValues.LuV)
                .circuitMeta(5)
                .input(plateDense, HSSS, 10)
                .input(cableGtHex, YttriumBariumCuprate, 6)
                .inputs(ELECTRIC_MOTOR_LuV.getStackForm(96))
                .fluidInputs(SolderingAlloy.getFluid(L * 48))
                .fluidInputs(Lubricant.getFluid(12000))
                .fluidInputs(StyreneButadieneRubber.getFluid(L * 8 * 48))
                .fluidInputs(HSSS.getFluid(23136))
                .buildAndRegister();

        COMPONENT_AL_RECIPES.recipeBuilder().EUt(GTValues.VA[GTValues.LuV - 1]).duration(28800)
                .CWUt(getCWUt(GTValues.LuV))
                .outputs(EMITTER_LuV.getStackForm(64))
                .CasingTier(GTValues.LuV)
                .circuitMeta(6)
                .input(cableGtHex, YttriumBariumCuprate, 24)
                .inputs(ELECTRIC_MOTOR_LuV.getStackForm(48))
                .input(gemExquisite, Ruby, 96)
                .input(frameGt, HSSS, 48)
                .input(circuit, MarkerMaterials.Tier.LuV, 96)
                .inputs(QUANTUM_STAR.getStackForm(48))
                .fluidInputs(SolderingAlloy.getFluid(L * 96))
                .fluidInputs(Ruridit.getFluid(L * 96))
                .fluidInputs(Palladium.getFluid(1536 * L))
                .fluidInputs(ZincSelenide.getFluid(768 * L))
                .buildAndRegister();


        COMPONENT_AL_RECIPES.recipeBuilder().EUt(GTValues.VA[GTValues.LuV - 1]).duration(28800)
                .CWUt(getCWUt(GTValues.LuV))
                .outputs(SENSOR_LuV.getStackForm(64))
                .CasingTier(GTValues.LuV)
                .circuitMeta(7)
                .input(cableGtHex, YttriumBariumCuprate, 24)
                .inputs(ELECTRIC_MOTOR_LuV.getStackForm(48))
                .input(gemExquisite, Ruby, 96)
                .input(plateDense, Ruridit, 21)
                .input(frameGt, HSSS, 48)
                .input(circuit, MarkerMaterials.Tier.LuV, 96)
                .inputs(QUANTUM_STAR.getStackForm(48))
                .fluidInputs(SolderingAlloy.getFluid(L * 96))
                .fluidInputs(Palladium.getFluid(1536 * L))
                .fluidInputs(Germanium.getFluid(768 * L))
                .buildAndRegister();

        COMPONENT_AL_RECIPES.recipeBuilder().EUt(GTValues.VA[GTValues.LuV - 1]).duration(28800)
                .CWUt(getCWUt(GTValues.LuV))
                .outputs(FIELD_GENERATOR_LuV.getStackForm(64))
                .CasingTier(GTValues.LuV)
                .circuitMeta(8)
                .inputs(QUANTUM_STAR.getStackForm(48))
                .input(frameGt, HSSS, 48)
                .input(plateDense, HSSS, 32)
                .input(cableGtHex, YttriumBariumCuprate, 48)
                .inputs(EMITTER_LuV.getStackForm(96))
                .input(circuit, MarkerMaterials.Tier.LuV, 384)
                .fluidInputs(SolderingAlloy.getFluid(L * 192))
                .fluidInputs(LuVSuperconductor.getFluid(768 * L))
                .buildAndRegister();
    }

    private static void components() {
        for(int i = GTValues.LV; i < GTValues.LuV; i++) {
            getComponentALLowRecipe(i, 1).buildAndRegister();
            getComponentALLowRecipe(i, 2).buildAndRegister();
            if(i < GTValues.IV) {
                getComponentALLowRecipe(i, 3).fluidInputs(Rubber.getFluid(3456)).buildAndRegister();
                getComponentALLowRecipe(i, 5).input(plateDense, Rubber, 32).buildAndRegister();
            }
            getComponentALLowRecipe(i, 3).fluidInputs(SiliconeRubber.getFluid(3456)).buildAndRegister();
            getComponentALLowRecipe(i, 3).fluidInputs(StyreneButadieneRubber.getFluid(3456)).buildAndRegister();
            getComponentALLowRecipe(i, 5).input(plateDense, SiliconeRubber, 32).buildAndRegister();
            getComponentALLowRecipe(i, 5).input(plateDense, StyreneButadieneRubber, 32).buildAndRegister();
            getComponentALLowRecipe(i,4).buildAndRegister();

            if(i < GTValues.EV) {
                getComponentALLowRecipe(i, 6).input(i == GTValues.MV ? gemFlawless : gem, getLowEmitterSensorStarMaterial(i), 48).buildAndRegister();
                getComponentALLowRecipe(i, 7).input(i == GTValues.MV ? gemFlawless : gem, getLowEmitterSensorStarMaterial(i), 48).buildAndRegister();
            }
            else {
                getComponentALLowRecipe(i, 6).input(getStarByTier(i), 48).buildAndRegister();
                getComponentALLowRecipe(i, 7).input(getStarByTier(i), 48).buildAndRegister();
            }

            switch (i) {
                case(1) -> getComponentALLowRecipe(i, 8).input(gem, EnderPearl, 48).buildAndRegister();
                case(2) -> getComponentALLowRecipe(i, 8).input(gem, EnderEye, 48).buildAndRegister();
                case(3) -> getComponentALLowRecipe(i, 8).inputs(QUANTUM_EYE.getStackForm(48)).buildAndRegister();
                case(4) -> getComponentALLowRecipe(i, 8).input(gem, NetherStar, 48).buildAndRegister();
                case(5) -> getComponentALLowRecipe(i, 8).inputs(QUANTUM_STAR.getStackForm(48)).buildAndRegister();
            }


        }

        for(int i = GTValues.ZPM ; i < GTValues.MAX; i++) {
            getComponentALHighRecipe(i, 1).buildAndRegister();
            getComponentALHighRecipe(i, 2).buildAndRegister();
            getComponentALHighRecipe(i, 3).buildAndRegister();
            getComponentALHighRecipe(i, 4).buildAndRegister();
            getComponentALHighRecipe(i, 5).buildAndRegister();
            getComponentALHighRecipe(i, 6).buildAndRegister();
            getComponentALHighRecipe(i, 7).buildAndRegister();
            getComponentALHighRecipe(i, 8).buildAndRegister();
        }
    }

    private static void max() {

    }

    private static RecipeBuilder<ComponentALRecipeBuilder> getComponentALLowRecipe(int tier, int type) {
        ComponentALRecipeBuilder builder = COMPONENT_AL_RECIPES.recipeBuilder().EUt(GTValues.VA[tier - 1]).duration(600)
                .CasingTier(tier)
                .CWUt(getCWUt(tier))
                .circuitMeta(type);

        return switch (type) {
            case(2) -> builder
                    .outputs(getPistonByTier(tier).getStackForm(64))
                    .input(gear, getMaterialByTier(tier), 12)
                    .input(stickLong, getMaterialByTier(tier), 48)
                    .inputs(getMotorByTier(tier).getStackForm(48))
                    .input(cableGtHex, getCableByTier(tier),6)
                    .input(plateDense, getMaterialByTier(tier), 16);

            case(3) -> builder
                    .outputs(getPumpByTier(tier).getStackForm(64))
                    .input(screw, getFluidPipeMaterialByTier(tier == 5 ? tier : tier - 1), 48)
                    .input(rotor, getFluidPipeMaterialByTier(tier == 5 ? tier : tier - 1), 48)
                    .input(pipeNormalFluid, getFluidPipeMaterialByTier(tier), 48)
                    .input(cableGtHex, getCableByTier(tier), 3)
                    .inputs(getMotorByTier(tier).getStackForm(48));

            case(4) -> builder
                    .outputs(getRobotArmByTier(tier).getStackForm(64))
                    .input(circuit, getMarkerMaterialByTier(tier), 48)
                    .input(stickLong, getMaterialByTier(tier), 48)
                    .input(cableGtHex, getCableByTier(tier), 9)
                    .inputs(getPistonByTier(tier).getStackForm(48))
                    .inputs(getMotorByTier(tier).getStackForm(96));

            case(5) -> builder
                    .outputs(getConveyorByTier(tier).getStackForm(64))
                    .inputs(getMotorByTier(tier).getStackForm(96))
                    .input(cableGtHex, getCableByTier(tier), 3);

            case(6) -> builder
                    .outputs(getEmitterByTier(tier).getStackForm(64))
                    .input(circuit, getMarkerMaterialByTier(tier), 96)
                    .input(cableGtHex, getCableByTier(tier),6)
                    .fluidInputs(getLowEmitterSensorRodMaterial(tier).getFluid(13824));

            case(7) -> builder
                    .outputs(getSensorByTier(tier).getStackForm(64))
                    .input(plateDense, getMaterialByTier(tier), 21)
                    .input(stickLong, getLowEmitterSensorRodMaterial(tier), 24)
                    .input(circuit, getMarkerMaterialByTier(tier), 48);

            case(8) -> builder
                    .outputs(getFieldGeneratorByTier(tier).getStackForm(64))
                    .input(circuit, getMarkerMaterialByTier(tier), 96)
                    .input(plateDense, getMaterialByTier(tier), tier > 3 ? 21 : 11)
                    .fluidInputs(getSuperconductorByTier(tier).getFluid(384 * L));



            default -> builder
                    .outputs(getMotorByTier(tier).getStackForm(64))
                    .input(stickLong, tier == 1 ? Iron : getMaterialByTier(tier), 48)
                    .input(cableGtHex, getCableByTier(tier), tier > 2 ? 12 : 6)
                    .input(wireGtHex, getFineWireByTier(tier), tier > 1 ? 24 * (tier == 5 ? 2 : 1) : 12)
                    .input(stickLong, getMagneticMaterialFluidByTier(tier), 24);
        };
    }

    private static RecipeBuilder<ComponentALRecipeBuilder> getComponentALHighRecipe(int tier, int type) {
        ComponentALRecipeBuilder builder = COMPONENT_AL_RECIPES.recipeBuilder().EUt(GTValues.VA[tier - 1]).duration(28800)
                .CasingTier(tier)
                .CWUt(getCWUt(tier))
                .circuitMeta(type);

        if(type == 6) {
            if(tier > 10) {
                builder.inputs(getEmitterCrystalByTier(tier).getStackForm(192));
            }
            if(tier == 13) {
                builder.inputs(ULTRASHORT_PULSE_LASER.getStackForm(48));
            }
        }

        if(type == 7 && tier > 8) {
            builder.inputs(BOSE_EINSTEIN_COOLING_CONTAINER.getStackForm((int) Math.pow(2, tier - 9) * 48));
        }

        if(type == 8 && tier == 13) {
            builder
                    .inputs(BATTERY_LARGE_FLUORIDE.getStackForm(48))
                    .inputs(CONTAINED_REISSNER_NORDSTROM_SINGULARITY.getStackForm(48))
                    .inputs(CRYOGENIC_INTERFACE.getStackForm(48));
        }

        return switch (type) {
            case(2) -> builder
                    .outputs(getPistonByTier(tier).getStackForm(64))
                    .input(plateDense, getMainComponentMaterialByTier(tier), 21)
                    .inputs(getMotorByTier(tier).getStackForm(48))
                    .input(cableGtHex, getCableByTier(tier), 6)
                    .fluidInputs(getSolderingAlloyByTier(tier).getFluid((int) (48 * L * Math.pow(2, tier - 6))))
                    .fluidInputs(Lubricant.getFluid((int) (48 * 250 * Math.pow(2, tier - 6))))
                    .fluidInputs(tier < 8 ? null : getFluidMaterialByTier(tier).getFluid((int) (48 * L * 4 * Math.pow(2, tier - 9))));

            case(3) -> builder
                    .outputs(getPumpByTier(tier).getStackForm(64))
                    .input(cableGtHex, getCableByTier(tier), 6)
                    .input(plateDense, getMainComponentMaterialByTier(tier), 10)
                    .inputs(getMotorByTier(tier).getStackForm(48))
                    .fluidInputs(getSolderingAlloyByTier(tier).getFluid((int) (48 * L * Math.pow(2, tier - 6))))
                    .fluidInputs(Lubricant.getFluid((int) (48 * 250 * Math.pow(2, tier - 6))))
                    .fluidInputs(getFluidPipeMaterialByTier(tier).getFluid(tier < 9 ? (int) (L * 288 * Math.pow(2, tier - 7)) : L * 4608))
                    .fluidInputs(getMainComponentMaterialByTier(tier).getFluid(47616))
                    .fluidInputs(tier < 8 ? null : getFluidMaterialByTier(tier).getFluid((int) (48 * L * 4 * Math.pow(2, tier - 9))))
                    .fluidInputs(SiliconeRubber.getFluid(tier < 8 ? (int) (L * 48 * Math.pow(2, tier - 6)) : L * 48 * 4));

            case(4) -> builder
                    .outputs(getRobotArmByTier(tier).getStackForm(64))
                    .input(circuit, getMarkerMaterialByTier(tier), 48)
                    .input(circuit, getMarkerMaterialByTier(tier - 1), 96)
                    .input(circuit, getMarkerMaterialByTier(tier - 2), 192)
                    .input(cableGtHex, getCableByTier(tier), 18)
                    .inputs(getPistonByTier(tier).getStackForm(48))
                    .inputs(getMotorByTier(tier).getStackForm(96))
                    .fluidInputs(getSolderingAlloyByTier(tier).getFluid((int) (192 * L * Math.pow(2, tier - 6))))
                    .fluidInputs(Lubricant.getFluid((int) (48 * 250 * Math.pow(2, tier - 6))))
                    .fluidInputs(getMainComponentMaterialByTier(tier).getFluid(528 * L))
                    .fluidInputs(tier < 8 ? null : getFluidMaterialByTier(tier).getFluid((int) (48 * L * 8 * Math.pow(2, tier - 9))));

            case(5) -> builder
                    .outputs(getConveyorByTier(tier).getStackForm(64))
                    .input(plateDense, getMainComponentMaterialByTier(tier), 10)
                    .input(cableGtHex, getCableByTier(tier), 6)
                    .inputs(getMotorByTier(tier).getStackForm(96))
                    .fluidInputs(getSolderingAlloyByTier(tier).getFluid((int) (L * 48 * Math.pow(2, tier - 6))))
                    .fluidInputs(Lubricant.getFluid((int) (48 * 250 * Math.pow(2, tier - 6))))
                    .fluidInputs(tier < 9 ? StyreneButadieneRubber.getFluid((int) (L * 8 * 48 * Math.pow(2, tier - 6))) : getPolymerByTier(tier).getFluid((int) (L * 8 * 48 * Math.pow(2, tier - 6))))
                    .fluidInputs(getMainComponentMaterialByTier(tier).getFluid(23136))
                    .fluidInputs(tier < 8 ? null : getFluidMaterialByTier(tier).getFluid((int) (48 * L * 4 * Math.pow(2, tier - 9))));

            case(6) -> builder
                    .outputs(getEmitterByTier(tier).getStackForm(64))
                    .input(cableGtHex, getCableByTier(tier), 24)
                    .inputs(getMotorByTier(tier).getStackForm(48))
                    .input(gemExquisite, getGemByTier(tier), 96)
                    .input(frameGt, getMainComponentMaterialByTier(tier), 48)
                    .input(circuit, getMarkerMaterialByTier(tier), 96)
                    .inputs(getStarByTier(tier).getStackForm(tier % 2 != 0 ? 96 * (tier > 9 ? 2 : 1) : 48 * (tier > 9 ? 2 : 1)))
                    .fluidInputs(getSolderingAlloyByTier(tier).getFluid((int) (L * 96 * Math.pow(2, tier - 6))))
                    .fluidInputs(tier < 8 ? null : getFluidMaterialByTier(tier).getFluid((int) (48 * L * 8 * Math.pow(2, tier - 9))))
                    .fluidInputs(tier == 7 ? Osmiridium.getFluid(L * 96) : getMainComponentMaterialByTier(tier).getFluid(L * 96))
                    .fluidInputs(tier == 13 ? Bohrium.getFluid(1536 * L) : getSensorEmitterFoil(tier).getFluid(1536 * L))
                    .fluidInputs(getEmitterDustByTier(tier) == null ? null : getEmitterDustByTier(tier).getFluid(768 * L));

            case(7) -> builder
                    .outputs(getSensorByTier(tier).getStackForm(64))
                    .input(cableGtHex, getCableByTier(tier), 24)
                    .inputs(getMotorByTier(tier).getStackForm(48))
                    .input(gemExquisite, getGemByTier(tier), 96)
                    .input(plateDense, tier == 7 ? Osmiridium : getMainComponentMaterialByTier(tier), 21)
                    .input(frameGt, getMainComponentMaterialByTier(tier), 48)
                    .input(circuit, getMarkerMaterialByTier(tier), 96)
                    .inputs(getStarByTier(tier).getStackForm(tier % 2 != 0 ? 96 : 48))
                    .fluidInputs(getSolderingAlloyByTier(tier).getFluid((int) (L * 96 * Math.pow(2, tier - 6))))
                    .fluidInputs(tier < 8 ? null : getFluidMaterialByTier(tier).getFluid((int) (48 * L * 8 * Math.pow(2, tier - 9))))
                    .fluidInputs(tier == 7 ? Osmiridium.getFluid(L * 96) : getMainComponentMaterialByTier(tier).getFluid(L * 96))
                    .fluidInputs(tier == 13 ? MetastableOganesson.getFluid(768 * L) : getSensorEmitterFoil(tier).getFluid(1536 * L))
                    .fluidInputs(tier == 13 ? FullerenePolymerMatrix.getFluid(768 * L) : null)
                    .fluidInputs(getSensorDustByTier(tier) == null ? null : getSensorDustByTier(tier).getFluid(768 * L));


            case(8) -> builder
                    .outputs(getFieldGeneratorByTier(tier).getStackForm(64))
                    .inputs(getStarByTier(tier).getStackForm(tier % 2 != 0 ? 96 * (tier > 8 ? 2 : 1) : 48 * (tier >= 8 ? 2 : 1)))
                    .input(frameGt, getMainComponentMaterialByTier(tier), 48)
                    .input(plateDense, getMainComponentMaterialByTier(tier), 32)
                    .input(cableGtHex, getCableByTier(tier), 48)
                    .inputs(getEmitterByTier(tier).getStackForm(96))
                    .input(circuit, getMarkerMaterialByTier(tier), 384)
                    .fluidInputs(getSolderingAlloyByTier(tier).getFluid(tier == 7 ? L * 192 * 2 : (int) (L * 192 * 3 * Math.pow(2, tier - 8))))
                    .fluidInputs(getSuperconductorByTier(tier).getFluid(768 * L))
                    .fluidInputs(tier < 8 ? null : getFluidMaterialByTier(tier).getFluid((int) (48 * L * 8 * Math.pow(2, tier - 9))));

            default -> builder
                    .outputs(getMotorByTier(tier).getStackForm(64))
                    .input(cableGtHex, getCableByTier(tier), 6)
                    .fluidInputs(getSolderingAlloyByTier(tier).getFluid((int) (48 * L * Math.pow(2, tier - 6))))
                    .fluidInputs(Lubricant.getFluid((int) (48 * 250 * Math.pow(2, tier - 6))))
                    .fluidInputs(getMagneticMaterialFluidByTier(tier).getFluid((int) (tier < 10 ? 48 * L * Math.pow(2, tier - 6) : 48 * L * 8 * Math.pow(2, tier - 10))))
                    .fluidInputs(getMainComponentMaterialByTier(tier).getFluid(tier == 6 ? 20352 : 20352 * 2))
                    .fluidInputs(getFineWireByTier(tier).getFluid(L * 1536))
                    .fluidInputs(tier < 8 ? null : getFluidMaterialByTier(tier).getFluid((int) (48 * L * 4 * Math.pow(2, tier - 9))));
        };
    }
}
