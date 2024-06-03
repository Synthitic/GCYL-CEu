package com.fulltrix.gcyl.recipes.chain;

import com.fulltrix.gcyl.item.GCYLExplosive;
import com.fulltrix.gcyl.item.GCYLMetaBlocks;
import com.fulltrix.gcyl.item.GCYLSimpleBlock;
import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.common.items.MetaItems;

import static com.fulltrix.gcyl.GCYLMaterials.*;
import static com.fulltrix.gcyl.item.GCYLCoreItems.*;
import static com.fulltrix.gcyl.recipes.GCYLRecipeMaps.PLASMA_CONDENSER_RECIPES;
import static com.fulltrix.gcyl.recipes.GCYLRecipeMaps.STELLAR_FORGE_RECIPES;
import static gregicality.multiblocks.api.recipes.GCYMRecipeMaps.ALLOY_BLAST_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.GELLED_TOLUENE;

public class UHVMaterials {
    public static void init() {

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(dust, Naquadria)
                .inputs(GELLED_TOLUENE.getStackForm(2))
                .input(plate, Uranium238, 1)
                .input(bolt, Osmium, 1)
                .input(bolt, Titanium, 4)
                .input(dust, HexanitroHexaazaisowurtzitane)
                .fluidInputs(GlycerylTrinitrate.getFluid(1000))
                .outputs(GCYLMetaBlocks.EXPLOSIVE.getItemVariant(GCYLExplosive.ExplosiveType.NAQUADRIA_CHARGE))
                .EUt(1966080)
                .duration(100)
                .buildAndRegister();

        STELLAR_FORGE_RECIPES.recipeBuilder()
                .input(ingot, Rhenium)
                .inputs(GCYLMetaBlocks.EXPLOSIVE.getItemVariant(GCYLExplosive.ExplosiveType.NAQUADRIA_CHARGE))
                .fluidOutputs(ElectronDegenerateRheniumPlasma.getPlasma(1000))
                .EUt(1966080)
                .duration(20)
                .buildAndRegister();

        STELLAR_FORGE_RECIPES.recipeBuilder()
                .input(plateDense, Rhenium)
                .inputs(GCYLMetaBlocks.EXPLOSIVE.getItemVariant(GCYLExplosive.ExplosiveType.NAQUADRIA_CHARGE))
                .fluidOutputs(ElectronDegenerateRheniumPlasma.getPlasma(10000))
                .EUt(1966080 * 4)
                .duration(100)
                .buildAndRegister();

        CANNER_RECIPES.recipeBuilder()
                .inputs(PLASMA_CONTAINMENT_CELL.getStackForm())
                .fluidInputs(ElectronDegenerateRheniumPlasma.getPlasma(1000))
                .outputs(RHENIUM_PLASMA_CONTAINMENT_CELL.getStackForm())
                .EUt(30720)
                .duration(20)
                .buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .inputs(RHENIUM_PLASMA_CONTAINMENT_CELL.getStackForm())
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 16000))
                .fluidOutputs(Helium.getFluid(16000))
                .notConsumable(PLATE_FIELD_SHAPE.getStackForm())
                .outputs(DEGENERATE_RHENIUM_PLATE.getStackForm())
                .outputs(PLASMA_CONTAINMENT_CELL.getStackForm())
                .EUt(122880)
                .duration(250)
                .buildAndRegister();

        //TODO: change pipe to fluid pipe?
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(stickLong, NeodymiumMagnetic, 2)
                .input(wireGtSingle, UVSuperconductor, 16)
                .input(pipeLargeItem, Ultimet, 4)
                .input(plate, NaquadahAlloy, 8)
                .fluidInputs(Titanium.getFluid(2592))
                .fluidInputs(NaquadahEnriched.getFluid(1584))
                .outputs(PLASMA_CONTAINMENT_CELL.getStackForm())
                .EUt(983040)
                .duration(50)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder()
                .inputs(DEGENERATE_RHENIUM_PLATE.getStackForm())
                .outputs(DEGENERATE_RHENIUM_DUST.getStackForm(4))
                .duration(100)
                .EUt(62_914_560)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, Mendelevium)
                .inputs(GELLED_TOLUENE.getStackForm(4))
                .input(stickLong, NaquadriaticTaranium)
                .input(dust, Taranium)
                .input(plate, Tritanium)
                .inputs(DEGENERATE_RHENIUM_DUST.getStackForm())
                .fluidInputs(GlycerylTrinitrate.getFluid(2500))
                .outputs(GCYLMetaBlocks.EXPLOSIVE.getItemVariant(GCYLExplosive.ExplosiveType.TARANIUM_CHARGE))
                .EUt(7_864_320)
                .duration(20)
                .buildAndRegister();

        ALLOY_BLAST_RECIPES.recipeBuilder()
                .input(dust, Seaborgium)
                .input(dust, Bohrium)
                .input(dust, Rutherfordium)
                .input(dust, Dubnium)
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(SuperheavyMix.getFluid(4000))
                .EUt(25_000_000)
                .duration(40)
                .blastFurnaceTemp(11000)
                .buildAndRegister();

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .notConsumable(MetaItems.SHAPE_MOLD_BLOCK)
                .fluidInputs(SuperheavyMix.getFluid(1000))
                .outputs(GCYLMetaBlocks.SIMPLE_BLOCK.getItemVariant(GCYLSimpleBlock.BlockType.SUPERHEAVY_BLOCK))
                .EUt(100000000)
                .duration(40)
                .buildAndRegister();

        STELLAR_FORGE_RECIPES.recipeBuilder()
                .inputs(GCYLMetaBlocks.SIMPLE_BLOCK.getItemVariant(GCYLSimpleBlock.BlockType.SUPERHEAVY_BLOCK))
                .inputs(GCYLMetaBlocks.EXPLOSIVE.getItemVariant(GCYLExplosive.ExplosiveType.TARANIUM_CHARGE))
                .fluidOutputs(NeutronPlasma.getPlasma(1000))
                .EUt(100000000)
                .duration(10)
                .buildAndRegister();

        CANNER_RECIPES.recipeBuilder()
                .inputs(PLASMA_CONTAINMENT_CELL.getStackForm())
                .fluidInputs(NeutronPlasma.getPlasma(1000))
                .outputs(NEUTRON_PLASMA_CONTAINMENT_CELL.getStackForm())
                .EUt(25000000)
                .duration(80)
                .buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .inputs(NEUTRON_PLASMA_CONTAINMENT_CELL.getStackForm())
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 32000))
                .fluidOutputs(Helium.getFluid(32000))
                .notConsumable(INGOT_FIELD_SHAPE.getStackForm())
                .output(ingot, Neutronium)
                .outputs(PLASMA_CONTAINMENT_CELL.getStackForm())
                .EUt(10000000)
                .duration(500)
                .buildAndRegister();
    }
}
