package com.fulltrix.gcyl.jei;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VoidMinerInfo implements IRecipeWrapper {
    private final List<List<ItemStack>> groupedInputsAsItemStacks = new ArrayList<>();
    private final List<List<ItemStack>> groupedOutputsAsItemStacks = new ArrayList<>();
    private final int tier;

    public VoidMinerInfo(List<ItemStack> ores, int tier) {
        this.groupedInputsAsItemStacks.add(ores);
        this.groupedOutputsAsItemStacks.add(ores);
        this.tier = tier;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInputLists(VanillaTypes.ITEM, groupedInputsAsItemStacks);
        ingredients.setOutputLists(VanillaTypes.ITEM, groupedOutputsAsItemStacks);
    }

    public int getOutputCount() {
        return groupedOutputsAsItemStacks.size();
    }

    public int getTier() {
        return this.tier;
    }
}
