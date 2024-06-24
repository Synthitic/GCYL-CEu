package com.fulltrix.gcyl.recipes.chain;

import com.fulltrix.gcyl.item.GCYLExplosive;
import com.fulltrix.gcyl.item.GCYLMetaBlocks;
import gregtech.api.fluids.store.FluidStorageKeys;

import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static com.fulltrix.gcyl.item.GCYLCoreItems.*;
import static com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps.PLASMA_CONDENSER_RECIPES;
import static com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps.STELLAR_FORGE_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

public class WormholeGeneratorChain { //TODO research
    public static void init() {

        PLASMA_CONDENSER_RECIPES.recipeBuilder().duration(500).EUt(400000)
                .inputs(NEUTRON_PLASMA_CONTAINMENT_CELL.getStackForm())
                .notConsumable(SPHERE_FIELD_SHAPE.getStackForm())
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 32000))
                .outputs(NEUTRONIUM_SPHERE.getStackForm(4))
                .outputs(PLASMA_CONTAINMENT_CELL.getStackForm())
                .fluidOutputs(Helium.getFluid(32000))
                .buildAndRegister();

        POLARIZER_RECIPES.recipeBuilder().duration(200).EUt(500000)
                .inputs(NEUTRONIUM_SPHERE.getStackForm())
                .outputs(TRIPLET_NEUTRONIUM_SPHERE.getStackForm())
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(40).EUt(100000)
                .notConsumable(SEPARATION_ELECTROMAGNET.getStackForm())
                .fluidInputs(Helium.getPlasma(1000))
                .fluidOutputs(FreeAlphaGas.getFluid(1000))
                .fluidOutputs(FreeElectronGas.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(200).EUt(50000)
                .inputs(TRIPLET_NEUTRONIUM_SPHERE.getStackForm())
                .fluidInputs(FreeAlphaGas.getFluid(1000))
                .outputs(CHARGED_TRIPLET_NEUTRONIUM_SPHERE.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(100000)
                .fluidInputs(Tritanium.getFluid(144))
                .input(stick, NaquadahAlloy, 4)
                .input(wireGtSingle, UVSuperconductor, 2)
                .inputs(DEGENERATE_RHENIUM_PLATE.getStackForm(4))
                .inputs(EMITTER_UV.getStackForm(2))
                .outputs(TIME_DILATION_CONTAINMENT_UNIT.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(800000)
                .fluidInputs(Tritanium.getFluid(144))
                .input(stick, NaquadahAlloy, 4)
                .input(wireGtSingle, UHVSuperconductor, 2)
                .inputs(DEGENERATE_RHENIUM_PLATE.getStackForm(4))
                .inputs(EMITTER_UHV.getStackForm(2))
                .outputs(TIME_DILATION_CONTAINMENT_UNIT.getStackForm(4))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(12800000)
                .fluidInputs(Tritanium.getFluid(144))
                .input(stick, NaquadahAlloy, 4)
                .input(wireGtSingle, UEVSuperconductor, 2)
                .inputs(QCD_PROTECTIVE_PLATING.getStackForm())
                .inputs(FIELD_GENERATOR_UHV.getStackForm(2))
                .inputs(CHARGED_LEPTON_TRAP_CRYSTAL.getStackForm(2))
                .outputs(TIME_DILATION_CONTAINMENT_UNIT.getStackForm(16))
                .buildAndRegister();

        STELLAR_FORGE_RECIPES.recipeBuilder().duration(200).EUt(288566)
                .inputs(GCYLMetaBlocks.EXPLOSIVE.getItemVariant(GCYLExplosive.ExplosiveType.TARANIUM_CHARGE))
                .inputs(TIME_DILATION_CONTAINMENT_UNIT.getStackForm(64))
                .inputs(CHARGED_TRIPLET_NEUTRONIUM_SPHERE.getStackForm(64))
                .outputs(CONTAINED_REISSNER_NORDSTROM_SINGULARITY.getStackForm(64))
                .buildAndRegister();

        STELLAR_FORGE_RECIPES.recipeBuilder().duration(600).EUt(6000000)
                .inputs(CONTAINED_REISSNER_NORDSTROM_SINGULARITY.getStackForm(64))
                .outputs(CONTAINED_KERR_NEWMANN_SINGULARITY.getStackForm())
                .outputs(TIME_DILATION_CONTAINMENT_UNIT.getStackForm(63))
                .buildAndRegister();

        CANNER_RECIPES.recipeBuilder().duration(40).EUt(100000)
                .fluidInputs(FreeElectronGas.getFluid(1000))
                .inputs(CONTAINED_KERR_NEWMANN_SINGULARITY.getStackForm())
                .outputs(CONTAINED_KERR_SINGULARITY.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(100000)
                .fluidInputs(FreeElectronGas.getFluid(1000))
                .input(plate, MetastableOganesson, 1)
                .input(plate, Vibranium, 1)
                .input(plate, Trinium)
                .input(dustSmall, Mendelevium, 1)
                .inputs(GCYLMetaBlocks.EXPLOSIVE.getItemVariant(GCYLExplosive.ExplosiveType.TARANIUM_CHARGE))
                .outputs(GCYLMetaBlocks.EXPLOSIVE.getItemVariant(GCYLExplosive.ExplosiveType.LEPTONIC_CHARGE))
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(100).EUt(100000)
                .fluidInputs(Tritanium.getFluid(576))
                .inputs(EMITTER_ZPM.getStackForm(2))
                .inputs(FIELD_GENERATOR_ZPM.getStackForm())
                .inputs(CONTAINED_KERR_SINGULARITY.getStackForm())
                .input(wireGtSingle, UVSuperconductor, 2)
                .input(plate, Neutronium, 2)
                .outputs(MICROWORMHOLE_GENERATOR.getStackForm())
                .buildAndRegister();

        STELLAR_FORGE_RECIPES.recipeBuilder().duration(400).EUt(1000000)
                .inputs(NEUTRONIUM_SPHERE.getStackForm())
                .inputs(TIME_DILATION_CONTAINMENT_UNIT.getStackForm())
                .outputs(CONTAINED_HIGH_DENSITY_PROTONIC_MATTER.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(200).EUt(1000000)
                .fluidInputs(NaquadahAlloy.getFluid(576))
                .inputs(CONTAINED_HIGH_DENSITY_PROTONIC_MATTER.getStackForm())
                .inputs(MICROWORMHOLE_GENERATOR.getStackForm())
                .inputs(CONTAINED_KERR_SINGULARITY.getStackForm())
                .inputs(EMITTER_UV.getStackForm(4))
                .inputs(FIELD_GENERATOR_UV.getStackForm(2))
                .inputs(SENSOR_UV.getStackForm(4))
                .inputs(BATTERY_SMALL_LIS.getStackForm())
                .outputs(MACROWORMHOLE_GENERATOR.getStackForm())
                .buildAndRegister();

        STELLAR_FORGE_RECIPES.recipeBuilder().duration(600).EUt(5000000)
                .inputs(DEGENERATE_RHENIUM_DUST.getStackForm())
                .inputs(GCYLMetaBlocks.EXPLOSIVE.getItemVariant(GCYLExplosive.ExplosiveType.LEPTONIC_CHARGE))
                .inputs(CONTAINED_HIGH_DENSITY_PROTONIC_MATTER.getStackForm())
                .outputs(CONTAINED_EXOTIC_MATTER.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(400).EUt(6000000)
                .fluidInputs(Naquadria.getFluid(288))
                .fluidInputs(EnrichedNaquadahAlloy.getFluid(144))
                .fluidInputs(Neutronium.getFluid(144))
                .fluidInputs(Taranium.getFluid(72))
                .inputs(EMITTER_UHV.getStackForm(2))
                .inputs(FIELD_GENERATOR_UHV.getStackForm(2))
                .inputs(SENSOR_UHV.getStackForm(2))
                .inputs(BATTERY_SMALL_FLUORIDE.getStackForm())
                .inputs(CONTAINED_EXOTIC_MATTER.getStackForm())
                .inputs(MACROWORMHOLE_GENERATOR.getStackForm())
                .outputs(STABILIZED_WORMHOLE_GENERATOR.getStackForm())
                .buildAndRegister();
    }
}
