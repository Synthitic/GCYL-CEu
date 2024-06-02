package com.fulltrix.gcyl.jei;


import gregicality.multiblocks.common.metatileentities.GCYMMetaTileEntities;
import gregtech.api.GTValues;
import gregtech.api.recipes.RecipeMap;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import mezz.jei.api.*;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.recipe.IFocus;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;

import static gregtech.common.blocks.BlockWireCoil.CoilType.*;


@JEIPlugin
public class JEIGCYLPlugin implements IModPlugin {
    private IIngredientBlacklist itemBlacklist;
    private IIngredientRegistry iItemRegistry;
    public static IJeiRuntime jeiRuntime;

    @Override
    public void register(IModRegistry registry) {
        itemBlacklist = registry.getJeiHelpers().getIngredientBlacklist();
        iItemRegistry = registry.getIngredientRegistry();
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
        this.jeiRuntime = jeiRuntime;

        itemBlacklist.addIngredientToBlacklist(GCYMMetaTileEntities.LARGE_MIXER.getStackForm());
        itemBlacklist.addIngredientToBlacklist(GCYMMetaTileEntities.LARGE_CENTRIFUGE.getStackForm());

        itemBlacklist.addIngredientToBlacklist(MetaBlocks.WIRE_COIL.getItemVariant(TRINIUM));
        itemBlacklist.addIngredientToBlacklist(MetaBlocks.WIRE_COIL.getItemVariant(TRITANIUM));

    }
}
