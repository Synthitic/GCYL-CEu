package com.fulltrix.gcyl.recipes.categories;

import gregtech.api.unification.OreDictUnifier;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import static gregtech.api.recipes.GTRecipeHandler.removeRecipesByInputs;
import static gregtech.api.recipes.RecipeMaps.ARC_FURNACE_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.Materials.Oxygen;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class RecipeOverrideLate {
    public static void init() {
        //cobalt in arc furnace
        removeRecipesByInputs(ARC_FURNACE_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, Cobalt)}, new FluidStack[]{Oxygen.getFluid(59)});
        //antimony in arc furnace
        removeRecipesByInputs(ARC_FURNACE_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, Antimony)}, new FluidStack[]{Oxygen.getFluid(121)});
    }
}
