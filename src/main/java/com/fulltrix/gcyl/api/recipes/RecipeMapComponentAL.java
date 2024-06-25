package com.fulltrix.gcyl.api.recipes;

import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.ui.RecipeMapUIFunction;
import gregtech.core.sound.GTSoundEvents;
import org.jetbrains.annotations.NotNull;

public class RecipeMapComponentAL<R extends RecipeBuilder<R>> extends RecipeMap<R> {
    public RecipeMapComponentAL(@NotNull String unlocalizedName, @NotNull R defaultRecipeBuilder,
                                 @NotNull RecipeMapUIFunction recipeMapUI) {
        super(unlocalizedName, defaultRecipeBuilder, recipeMapUI, 12, 1, 12, 0);
        setSound(GTSoundEvents.ASSEMBLER);
    }
}
