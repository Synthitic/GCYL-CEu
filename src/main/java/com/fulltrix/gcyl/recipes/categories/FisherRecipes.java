package com.fulltrix.gcyl.recipes.categories;

import gregtech.api.recipes.chance.output.ChancedOutputLogic;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps.FISHER_RECIPES;
import static gregtech.api.unification.material.Materials.Meat;

public class FisherRecipes {
    public static void init() {
        FISHER_RECIPES.recipeBuilder().duration(1200).EUt(7680)
                .inputs(new ItemStack(Items.STRING ,64))
                .input(OrePrefix.dust, Meat, 32)
                .chancedOutputLogic(ChancedOutputLogic.XOR)
                .chancedOutput(new ItemStack(Items.FISH, 64), 3333, 333)
                .chancedOutput(new ItemStack(Items.FISH, 64, 2), 3333, 333)
                .chancedOutput(new ItemStack(Items.FISH, 64, 3), 3333, 333)
                .buildAndRegister();
    }
}
