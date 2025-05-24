package com.fulltrix.gcyl.recipes.categories;

import gregtech.api.unification.OreDictUnifier;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import static com.fulltrix.gcyl.api.GCYLUtility.removeExtractorRecipe;
import static com.fulltrix.gcyl.materials.GCYLMaterials.QCDMatter;
import static com.fulltrix.gcyl.materials.chains.NewPlatinumGroupMaterials.PotassiumBisulfate;
import static gregtech.api.recipes.GTRecipeHandler.removeRecipesByInputs;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.recipes.ingredients.IntCircuitIngredient.getIntegratedCircuit;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.Materials.Oxygen;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.common.items.MetaItems.*;

public class RecipeOverrideLate {
    public static void init() {
        //cobalt in arc furnace
        removeRecipesByInputs(ARC_FURNACE_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, Cobalt)}, new FluidStack[]{Oxygen.getFluid(59)});
        //antimony in arc furnace
        removeRecipesByInputs(ARC_FURNACE_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, Antimony)}, new FluidStack[]{Oxygen.getFluid(121)});

        //potassium bisulfate extractor
        removeExtractorRecipe(PotassiumBisulfate, dust);

        //monazite autoclave
        removeRecipesByInputs(AUTOCLAVE_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, Monazite)}, new FluidStack[]{Water.getFluid(250)});

        //yttrium barium cuprate dust again for autogen
        removeRecipesByInputs(MIXER_RECIPES, new ItemStack[]{getIntegratedCircuit(4), OreDictUnifier.get(dust, Yttrium), OreDictUnifier.get(dust, Barium, 2), OreDictUnifier.get(dust, Copper,3)}, new FluidStack[]{Oxygen.getFluid(7000)});



    }
}
