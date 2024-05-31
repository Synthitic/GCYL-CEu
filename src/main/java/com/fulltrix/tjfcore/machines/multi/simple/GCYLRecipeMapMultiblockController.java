package com.fulltrix.tjfcore.machines.multi.simple;

import gregicality.multiblocks.api.capability.impl.GCYMMultiblockRecipeLogic;
import gregicality.multiblocks.api.metatileentity.GCYMMultiblockAbility;
import gregicality.multiblocks.api.metatileentity.GCYMRecipeMapMultiblockController;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public abstract class GCYLRecipeMapMultiblockController extends GCYMRecipeMapMultiblockController {

    private boolean isParallel;

    public GCYLRecipeMapMultiblockController(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, boolean isParallel) {
        this(metaTileEntityId, new RecipeMap<?>[] {recipeMap}, isParallel);
        this.isParallel = isParallel;
    }

    public GCYLRecipeMapMultiblockController(ResourceLocation metaTileEntityId, RecipeMap<?>[] recipeMaps, boolean isParallel) {
        super(metaTileEntityId, recipeMaps);
        this.recipeMapWorkable = new GCYMMultiblockRecipeLogic(this);
        this.isParallel = isParallel;
    }

    @Override
    public boolean isParallel() { return  this.isParallel; }

    @Override
    public boolean isTiered() { return  true; }

    public static @NotNull TraceabilityPredicate tieredCasing() {
        return new TraceabilityPredicate(abilities(GCYMMultiblockAbility.TIERED_HATCH));
    }
}
