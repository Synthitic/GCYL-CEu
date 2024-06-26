package com.fulltrix.gcyl.api.recipes.builders;


import com.fulltrix.gcyl.api.recipes.properties.ComponentALProperty;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.recipeproperties.ComputationProperty;
import gregtech.api.recipes.recipeproperties.TotalComputationProperty;
import gregtech.api.util.EnumValidationResult;
import gregtech.api.util.GTLog;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jetbrains.annotations.NotNull;

public class ComponentALRecipeBuilder extends RecipeBuilder<ComponentALRecipeBuilder> {
    public ComponentALRecipeBuilder(){}

    public ComponentALRecipeBuilder(Recipe recipe, RecipeMap<ComponentALRecipeBuilder> recipeMap) {
        super(recipe, recipeMap);
    }

    public ComponentALRecipeBuilder(RecipeBuilder<ComponentALRecipeBuilder> recipeBuilder) {
        super(recipeBuilder);
    }

    @Override
    public ComponentALRecipeBuilder copy() {
        return new ComponentALRecipeBuilder(this);
    }

    public boolean applyProperty(@NotNull String key, Object value) {
        if (key.equals(ComponentALProperty.KEY)) {
            this.CasingTier(((Number) value).intValue());
            return true;
        }
        if (key.equals(ComputationProperty.KEY)) {
            this.CWUt(((Number) value).intValue());
            return true;
        }
        if (key.equals(TotalComputationProperty.KEY)) {
            this.totalCWU(((Number) value).intValue());
            return true;
        }

        return super.applyProperty(key, value);
    }

    public ComponentALRecipeBuilder CasingTier(int tier) {
        if (tier < 0) {
            GTLog.logger.error("Casing tier cannot be less than 0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(ComponentALProperty.getInstance(), tier);
        return this;
    }

    public int getCasingTier() {
        return this.recipePropertyStorage == null ? 0 :
                this.recipePropertyStorage.getRecipePropertyValue(ComponentALProperty.getInstance(), 0);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append(ComponentALProperty.getInstance().getKey(), getCasingTier())
                .toString();
    }

    public ComponentALRecipeBuilder CWUt(int cwut) {
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
    public ComponentALRecipeBuilder totalCWU(int totalCWU) {
        if (totalCWU < 0) {
            GTLog.logger.error("Total CWU cannot be less than 0", new Throwable());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(TotalComputationProperty.getInstance(), totalCWU);
        return duration(totalCWU);
    }
}
