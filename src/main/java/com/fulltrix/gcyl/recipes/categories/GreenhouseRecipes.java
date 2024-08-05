package com.fulltrix.gcyl.recipes.categories;

import gregtech.common.blocks.MetaBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps.GREENHOUSE_RECIPES;
import static com.fulltrix.gcyl.materials.GCYLMaterials.OrganicFertilizer;
import static gregtech.api.unification.material.Materials.CarbonDioxide;
import static gregtech.api.unification.material.Materials.Water;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.common.items.MetaItems.FERTILIZER;
import static gregtech.common.items.MetaItems.STICKY_RESIN;

public class GreenhouseRecipes {
    public static void init() {

        //oak tree apples

        GREENHOUSE_RECIPES.recipeBuilder().EUt(120).duration(600)
                .circuitMeta(1)
                .fluidInputs(Water.getFluid(64000))
                .inputs(new ItemStack(Blocks.SAPLING, 8))
                .output(new ItemStack(Blocks.LOG).getItem(), 32)
                .outputs(new ItemStack(Blocks.SAPLING, 16))
                .chancedOutput(new ItemStack(Items.APPLE, 32), 2500,0)
                .buildAndRegister();

        GREENHOUSE_RECIPES.recipeBuilder().EUt(480).duration(300)
                .circuitMeta(2)
                .fluidInputs(Water.getFluid(128000))
                .inputs(new ItemStack(Blocks.SAPLING, 8))
                .fluidInputs(CarbonDioxide.getFluid(16000))
                .output(new ItemStack(Blocks.LOG).getItem(), 64)
                .outputs(new ItemStack(Blocks.SAPLING, 32))
                .chancedOutput(new ItemStack(Items.APPLE, 32), 5000, 250)
                .buildAndRegister();

        GREENHOUSE_RECIPES.recipeBuilder().EUt(1920).duration(150)
                .circuitMeta(3)
                .fluidInputs(Water.getFluid(256000))
                .inputs(new ItemStack(Blocks.SAPLING, 8))
                .inputs(FERTILIZER.getStackForm(64))
                .fluidInputs(CarbonDioxide.getFluid(32000))
                .output(new ItemStack(Blocks.LOG).getItem(), 128)
                .outputs(new ItemStack(Blocks.SAPLING, 64))
                .chancedOutput(new ItemStack(Items.APPLE, 64), 7500, 500)
                .buildAndRegister();

        GREENHOUSE_RECIPES.recipeBuilder().EUt(1920).duration(150)
                .circuitMeta(3)
                .fluidInputs(Water.getFluid(256000))
                .inputs(new ItemStack(Blocks.SAPLING, 8))
                .input(dust, OrganicFertilizer, 64)
                .fluidInputs(CarbonDioxide.getFluid(32000))
                .output(new ItemStack(Blocks.LOG).getItem(), 128)
                .outputs(new ItemStack(Blocks.SAPLING, 64))
                .chancedOutput(new ItemStack(Items.APPLE, 64), 7500, 500)
                .buildAndRegister();

        //LOGS

        for(int i = 1; i < 6; i++) {
            GREENHOUSE_RECIPES.recipeBuilder().EUt(120).duration(600)
                    .circuitMeta(1)
                    .fluidInputs(Water.getFluid(64000))
                    .inputs(new ItemStack(Blocks.SAPLING, 8, i))
                    .outputs(i < 4 ? new ItemStack(Blocks.LOG, 32, i) : new ItemStack(Blocks.LOG2, 32, i - 4))
                    .outputs(new ItemStack(Blocks.SAPLING, 16, i))
                    .buildAndRegister();

            GREENHOUSE_RECIPES.recipeBuilder().EUt(480).duration(300)
                    .circuitMeta(2)
                    .fluidInputs(Water.getFluid(128000))
                    .inputs(new ItemStack(Blocks.SAPLING, 8))
                    .fluidInputs(CarbonDioxide.getFluid(16000))
                    .outputs(i < 4 ? new ItemStack(Blocks.LOG, 64, i) : new ItemStack(Blocks.LOG2, 64, i - 4))
                    .outputs(new ItemStack(Blocks.SAPLING, 32))
                    .buildAndRegister();

            GREENHOUSE_RECIPES.recipeBuilder().EUt(1920).duration(150)
                    .circuitMeta(3)
                    .fluidInputs(Water.getFluid(256000))
                    .inputs(new ItemStack(Blocks.SAPLING, 8))
                    .inputs(FERTILIZER.getStackForm(64))
                    .fluidInputs(CarbonDioxide.getFluid(32000))
                    .outputs(i < 4 ? new ItemStack(Blocks.LOG, 128, i) : new ItemStack(Blocks.LOG2, 128, i - 4))
                    .outputs(new ItemStack(Blocks.SAPLING, 64))
                    .buildAndRegister();

            GREENHOUSE_RECIPES.recipeBuilder().EUt(1920).duration(150)
                    .circuitMeta(3)
                    .fluidInputs(Water.getFluid(256000))
                    .inputs(new ItemStack(Blocks.SAPLING, 8))
                    .input(dust, OrganicFertilizer, 64)
                    .fluidInputs(CarbonDioxide.getFluid(32000))
                    .outputs(i < 4 ? new ItemStack(Blocks.LOG, 128, i) : new ItemStack(Blocks.LOG2, 128, i - 4))
                    .outputs(new ItemStack(Blocks.SAPLING, 64))
                    .buildAndRegister();
        }

        //RUBBER TREE

        GREENHOUSE_RECIPES.recipeBuilder().EUt(120).duration(600)
                .circuitMeta(1)
                .fluidInputs(Water.getFluid(64000))
                .inputs(new ItemStack(MetaBlocks.RUBBER_SAPLING, 8))
                .output(new ItemStack(MetaBlocks.RUBBER_LOG).getItem(), 32)
                .outputs(new ItemStack(MetaBlocks.RUBBER_SAPLING, 16))
                .chancedOutput(STICKY_RESIN, 32, 2500,0)
                .buildAndRegister();

        GREENHOUSE_RECIPES.recipeBuilder().EUt(480).duration(300)
                .circuitMeta(2)
                .fluidInputs(Water.getFluid(128000))
                .inputs(new ItemStack(MetaBlocks.RUBBER_SAPLING, 8))
                .fluidInputs(CarbonDioxide.getFluid(16000))
                .output(new ItemStack(MetaBlocks.RUBBER_LOG).getItem(), 64)
                .outputs(new ItemStack(MetaBlocks.RUBBER_SAPLING, 32))
                .chancedOutput(STICKY_RESIN, 32, 5000, 250)
                .buildAndRegister();

        GREENHOUSE_RECIPES.recipeBuilder().EUt(1920).duration(150)
                .circuitMeta(3)
                .fluidInputs(Water.getFluid(256000))
                .inputs(new ItemStack(MetaBlocks.RUBBER_SAPLING, 8))
                .inputs(FERTILIZER.getStackForm(64))
                .fluidInputs(CarbonDioxide.getFluid(32000))
                .output(new ItemStack(MetaBlocks.RUBBER_LOG).getItem(), 128)
                .outputs(new ItemStack(MetaBlocks.RUBBER_SAPLING, 64))
                .chancedOutput(STICKY_RESIN, 64, 7500, 500)
                .buildAndRegister();

        GREENHOUSE_RECIPES.recipeBuilder().EUt(1920).duration(150)
                .circuitMeta(3)
                .fluidInputs(Water.getFluid(256000))
                .inputs(new ItemStack(MetaBlocks.RUBBER_SAPLING, 8))
                .input(dust, OrganicFertilizer, 64)
                .fluidInputs(CarbonDioxide.getFluid(32000))
                .output(new ItemStack(MetaBlocks.RUBBER_LOG).getItem(), 128)
                .outputs(new ItemStack(MetaBlocks.RUBBER_SAPLING, 64))
                .chancedOutput(STICKY_RESIN, 64, 7500, 500)
                .buildAndRegister();


        //NO SEED PLANTS
        registerGreenhouseRecipes(Items.REEDS, 120, 600);
        registerGreenhouseRecipes(Items.CARROT, 120, 600);
        registerGreenhouseRecipes(Items.POTATO, 120, 600);

        //SEED PLANTS
        registerGreenhouseRecipesSeed(Items.WHEAT_SEEDS, Items.WHEAT, 120, 600, 1, true);
        registerGreenhouseRecipesSeed(Items.BEETROOT_SEEDS, Items.BEETROOT, 120, 600, 1, true);
        registerGreenhouseRecipesSeed(Items.MELON_SEEDS, Item.getItemFromBlock(Blocks.MELON_BLOCK), 120, 600, 8, false);
        registerGreenhouseRecipesSeed(Items.PUMPKIN_SEEDS, Item.getItemFromBlock(Blocks.PUMPKIN), 120, 600, 8, false);

    }

    private static void registerGreenhouseRecipes(Item input, int baseEUt, int baseDuration) {

        GREENHOUSE_RECIPES.recipeBuilder().EUt(baseEUt).duration(baseDuration)
                .circuitMeta(1)
                .fluidInputs(Water.getFluid(64000))
                .inputs(new ItemStack(input, 8))
                .outputs(new ItemStack(input, 32))
                .buildAndRegister();

        GREENHOUSE_RECIPES.recipeBuilder().EUt(baseEUt * 4).duration(baseDuration / 2)
                .circuitMeta(2)
                .fluidInputs(Water.getFluid(128000))
                .fluidInputs(CarbonDioxide.getFluid(16000))
                .inputs(new ItemStack(input, 8))
                .outputs(new ItemStack(input, 64))
                .buildAndRegister();

        GREENHOUSE_RECIPES.recipeBuilder().EUt(baseEUt * 16).duration(baseDuration / 4)
                .circuitMeta(3)
                .fluidInputs(Water.getFluid(256000))
                .fluidInputs(CarbonDioxide.getFluid(32000))
                .inputs(new ItemStack(input, 8))
                .inputs(FERTILIZER.getStackForm(64))
                .outputs(new ItemStack(input, 128))
                .buildAndRegister();

        GREENHOUSE_RECIPES.recipeBuilder().EUt(baseEUt * 16).duration(baseDuration / 4)
                .circuitMeta(3)
                .fluidInputs(Water.getFluid(256000))
                .fluidInputs(CarbonDioxide.getFluid(32000))
                .inputs(new ItemStack(input, 8))
                .input(dust, OrganicFertilizer, 64)
                .outputs(new ItemStack(input, 128))
                .buildAndRegister();
    }

    private static void registerGreenhouseRecipesSeed(Item input, Item output, int baseEUt, int baseDuration, int outputDivisor, boolean outputSeeds) {

        if(outputSeeds) {
            GREENHOUSE_RECIPES.recipeBuilder().EUt(baseEUt).duration(baseDuration)
                    .circuitMeta(1)
                    .fluidInputs(Water.getFluid(64000))
                    .inputs(new ItemStack(input, 8))
                    .outputs(new ItemStack(output, 32 / outputDivisor))
                    .outputs(new ItemStack(input, 16))
                    .buildAndRegister();

            GREENHOUSE_RECIPES.recipeBuilder().EUt(baseEUt * 4).duration(baseDuration / 2)
                    .circuitMeta(2)
                    .fluidInputs(Water.getFluid(128000))
                    .fluidInputs(CarbonDioxide.getFluid(16000))
                    .inputs(new ItemStack(input, 8))
                    .outputs(new ItemStack(output, 64 / outputDivisor))
                    .outputs(new ItemStack(input, 32))
                    .buildAndRegister();

            GREENHOUSE_RECIPES.recipeBuilder().EUt(baseEUt * 16).duration(baseDuration / 4)
                    .circuitMeta(3)
                    .fluidInputs(Water.getFluid(256000))
                    .fluidInputs(CarbonDioxide.getFluid(32000))
                    .inputs(new ItemStack(input, 8))
                    .inputs(FERTILIZER.getStackForm(64))
                    .outputs(new ItemStack(output, 128 / outputDivisor))
                    .outputs(new ItemStack(input, 64))
                    .buildAndRegister();

            GREENHOUSE_RECIPES.recipeBuilder().EUt(baseEUt * 16).duration(baseDuration / 4)
                    .circuitMeta(3)
                    .fluidInputs(Water.getFluid(256000))
                    .fluidInputs(CarbonDioxide.getFluid(32000))
                    .inputs(new ItemStack(input, 8))
                    .input(dust, OrganicFertilizer, 64)
                    .outputs(new ItemStack(output, 128 / outputDivisor))
                    .outputs(new ItemStack(input, 64))
                    .buildAndRegister();
        }
        else {
            GREENHOUSE_RECIPES.recipeBuilder().EUt(baseEUt).duration(baseDuration)
                    .circuitMeta(1)
                    .fluidInputs(Water.getFluid(64000))
                    .inputs(new ItemStack(input, 8))
                    .outputs(new ItemStack(output, 32 / outputDivisor))
                    .buildAndRegister();

            GREENHOUSE_RECIPES.recipeBuilder().EUt(baseEUt * 4).duration(baseDuration / 2)
                    .circuitMeta(2)
                    .fluidInputs(Water.getFluid(128000))
                    .fluidInputs(CarbonDioxide.getFluid(16000))
                    .inputs(new ItemStack(input, 8))
                    .outputs(new ItemStack(output, 64 / outputDivisor))
                    .buildAndRegister();

            GREENHOUSE_RECIPES.recipeBuilder().EUt(baseEUt * 16).duration(baseDuration / 4)
                    .circuitMeta(3)
                    .fluidInputs(Water.getFluid(256000))
                    .fluidInputs(CarbonDioxide.getFluid(32000))
                    .inputs(new ItemStack(input, 8))
                    .inputs(FERTILIZER.getStackForm(64))
                    .outputs(new ItemStack(output, 128 / outputDivisor))
                    .buildAndRegister();

            GREENHOUSE_RECIPES.recipeBuilder().EUt(baseEUt * 16).duration(baseDuration / 4)
                    .circuitMeta(3)
                    .fluidInputs(Water.getFluid(256000))
                    .fluidInputs(CarbonDioxide.getFluid(32000))
                    .inputs(new ItemStack(input, 8))
                    .input(dust, OrganicFertilizer, 64)
                    .outputs(new ItemStack(output, 128 / outputDivisor))
                    .buildAndRegister();
        }
    }
}
