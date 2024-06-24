package com.fulltrix.gcyl.recipes.impl;

import com.fulltrix.gcyl.recipes.recipeproperties.AdvFusionCoilProperty;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.ComputationRecipeBuilder;
import gregtech.api.recipes.recipeproperties.ComputationProperty;
import gregtech.api.recipes.recipeproperties.TotalComputationProperty;
import gregtech.api.util.EnumValidationResult;
import gregtech.api.util.GTLog;
import org.jetbrains.annotations.NotNull;

public class LaserMinerBuilder extends RecipeBuilder<LaserMinerBuilder> {

    public LaserMinerBuilder() {
    }

    public LaserMinerBuilder(Recipe recipe, RecipeMap<LaserMinerBuilder> recipeMap) {
        super(recipe, recipeMap);
    }

    public LaserMinerBuilder(RecipeBuilder<LaserMinerBuilder> recipeBuilder) { super(recipeBuilder);}

    @Override
    public LaserMinerBuilder copy() {return new LaserMinerBuilder(this);}


    @Override
    public boolean applyProperty(@NotNull String key, Object value) {
        if (key.equals(ComputationProperty.KEY)) {
            this.CWUt(((Number) value).intValue());
            return true;
        }
        if (key.equals(TotalComputationProperty.KEY)) {
            this.totalCWU(((Number) value).intValue());
            return true;
        }

        if (key.equals(AdvFusionCoilProperty.KEY)) {
            this.AdvCoilTier(((Number) value).intValue());
        }
        return super.applyProperty(key, value);
    }

    public int getAdvCoilTier() {
        return this.recipePropertyStorage == null ? 0 :
                this.recipePropertyStorage.getRecipePropertyValue(AdvFusionCoilProperty.getInstance(), 0);
    }

    public LaserMinerBuilder AdvCoilTier(int tier) {
        if (tier < 0) {
            GTLog.logger.error("Adv coil tier cannot be less than 0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(AdvFusionCoilProperty.getInstance(), tier);
        return this;
    }

    public LaserMinerBuilder CWUt(int cwut) {
        if (cwut < 0) {
            GTLog.logger.error("CWU/t cannot be less than 0", new Throwable());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(ComputationProperty.getInstance(), cwut);
        return this;
    }

    /**
     * The total computation for this recipe. If desired, this should be used instead of a call to duration().
     */
    public LaserMinerBuilder totalCWU(int totalCWU) {
        if (totalCWU < 0) {
            GTLog.logger.error("Total CWU cannot be less than 0", new Throwable());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(TotalComputationProperty.getInstance(), totalCWU);
        return duration(totalCWU);
    }
}
