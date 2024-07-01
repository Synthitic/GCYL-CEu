package com.fulltrix.gcyl.jei;

import com.fulltrix.gcyl.recipes.categories.elevator.SpaceMiningRecipes;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.ore.OrePrefix;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import org.eclipse.xtext.xbase.lib.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.fulltrix.gcyl.recipes.categories.elevator.SpaceMiningRecipes.SPACE_MINING_RECIPES;
import static gregtech.api.unification.material.Materials.*;

//TODO add bucket stuff
public class SpaceMiningInfo implements IRecipeWrapper {

    private final List<List<ItemStack>> groupedInputsAsItemStacks = new ArrayList<>();
    private final List<List<ItemStack>> groupedOutputsAsItemStacks = new ArrayList<>();
    private final List<List<FluidStack>> fluidList = new ArrayList<>();
    private final List<List<ItemStack>> bucketList = new ArrayList<>();
    private SpaceMiningRecipes.SpaceMiningRecipePartTwo recipe;
    private ItemStack drone;
    private int tier;


    public SpaceMiningInfo(List<ItemStack> inputs, SpaceMiningRecipes.SpaceMiningRecipePartTwo recipe) {
        this.recipe = recipe;
        this.tier = recipe.getMinModuleTier();

        for(ItemStack input : inputs) {
            this.groupedInputsAsItemStacks.add(Arrays.asList(input));
        }

        for(Pair<Material, Integer> pair : recipe.getOutputs()) {
            this.groupedOutputsAsItemStacks.add(Collections.singletonList(OreDictUnifier.get(OrePrefix.ore, pair.getKey(), pair.getValue())));
        }

        this.fluidList.addAll(Arrays.asList(Arrays.asList(Helium.getPlasma(1000), Bismuth.getPlasma(500), Radon.getPlasma(300))));
    }


    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInputLists(VanillaTypes.ITEM, groupedInputsAsItemStacks);
        ingredients.setOutputLists(VanillaTypes.ITEM, groupedOutputsAsItemStacks);

        ingredients.setInputLists(VanillaTypes.FLUID, fluidList);


        /*
        ItemStack bucket = FluidUtil.getFilledBucket(fluid);
        if (!bucket.isEmpty()) {
            bucketList.add(Collections.singletonList(bucket));
            ingredients.setInputLists(VanillaTypes.ITEM, bucketList);
            ingredients.setOutputLists(VanillaTypes.ITEM, bucketList);
        }

         */
    }

    public int getTier() {
        return this.tier;
    }

    public int getOutputCount() {
        return groupedOutputsAsItemStacks.size();
    }

    public int getInputCount() {
        return groupedInputsAsItemStacks.size();
    }

    public long getTotalEU() {
        return this.recipe.getDuration() * this.recipe.getEUt();
    }

    public long getEUt() {
        return this.recipe.getEUt();
    }

    public int getDuration() {
        return this.recipe.getDuration();
    }

    public int getMinDistance() {
        return this.recipe.getMinDistance();
    }

    public int getMaxDistance() {
        return this.recipe.getMaxDistance();
    }

    public int getMinSize() {
        return this.recipe.getMinSize();
    }

    public int getMaxSize() {
        return this.recipe.getMaxSize();
    }

    public int getMinCWUt() {
        return this.recipe.getComputation();
    }

    public int getWeight() {
        return this.recipe.getWeight();
    }
}
