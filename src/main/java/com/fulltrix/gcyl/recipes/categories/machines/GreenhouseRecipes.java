package com.fulltrix.gcyl.recipes.categories.machines;

import com.cleanroommc.groovyscript.api.IIngredient;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static com.fulltrix.gcyl.recipes.GCYLRecipeMaps.GREENHOUSE_RECIPES;
import static gregtech.api.unification.material.Materials.CarbonDioxide;
import static gregtech.common.items.MetaItems.STICKY_RESIN;

public class GreenhouseRecipes {
    public static void init() {

        GREENHOUSE_RECIPES.recipeBuilder().EUt(90).duration(600)
                .circuitMeta(1)
                .notConsumable(new ItemStack(Blocks.SAPLING))
                .output(new ItemStack(Blocks.LOG).getItem(), 64)
                .buildAndRegister();

        GREENHOUSE_RECIPES.recipeBuilder().EUt(120).duration(300)
                .circuitMeta(2)
                .notConsumable(new ItemStack(Blocks.SAPLING))
                .fluidInputs(CarbonDioxide.getFluid(16000))
                .output(new ItemStack(Blocks.LOG).getItem(), 128)
                .buildAndRegister();

        GREENHOUSE_RECIPES.recipeBuilder().EUt(90).duration(600)
                .circuitMeta(1)
                .notConsumable(new ItemStack(MetaBlocks.RUBBER_SAPLING))
                .output(new ItemStack(MetaBlocks.RUBBER_LOG).getItem(), 64)
                .chancedOutput(STICKY_RESIN, 64, 2500,0)
                .buildAndRegister();

        GREENHOUSE_RECIPES.recipeBuilder().EUt(120).duration(300)
                .circuitMeta(2)
                .notConsumable(new ItemStack(MetaBlocks.RUBBER_SAPLING))
                .fluidInputs(CarbonDioxide.getFluid(16000))
                .output(new ItemStack(MetaBlocks.RUBBER_LOG).getItem(), 128)
                .chancedOutput(STICKY_RESIN, 128, 5000, 500)
                .buildAndRegister();
    }
}
