package com.fulltrix.gcyl.api.recipes.builders;

import com.fulltrix.gcyl.api.recipes.properties.GCYLTemperatureProperty;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.EnumValidationResult;
import gregtech.api.util.GTLog;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jetbrains.annotations.NotNull;

public class DeepMinerBuilder extends RecipeBuilder<DeepMinerBuilder> {
    public DeepMinerBuilder() {
    }

    public DeepMinerBuilder(Recipe recipe, RecipeMap<DeepMinerBuilder> recipeMap) {
        super(recipe, recipeMap);
    }

    public DeepMinerBuilder(DeepMinerBuilder recipeBuilder) {
        super(recipeBuilder);
    }

    public DeepMinerBuilder copy() {
        return new DeepMinerBuilder(this);
    }

    public boolean applyProperty(@NotNull String key, Object value) {
        if (key.equals("temperature")) {
            this.temperature(((Number)value).intValue());
            return true;
        } else {
            return super.applyProperty(key, value);
        }
    }

    public DeepMinerBuilder temperature(int temperature) {
        if (temperature <= 0) {
            GTLog.logger.error("Temperature cannot be less than or equal to 0", new IllegalArgumentException());
            this.recipeStatus = EnumValidationResult.INVALID;
        }

        this.applyProperty(GCYLTemperatureProperty.getInstance(), temperature);
        return this;
    }

    public int getTemperature() {
        return this.recipePropertyStorage == null ? 0 : (Integer)this.recipePropertyStorage.getRecipePropertyValue(GCYLTemperatureProperty.getInstance(), 0);
    }

    public String toString() {
        return (new ToStringBuilder(this)).appendSuper(super.toString()).append(GCYLTemperatureProperty.getInstance().getKey(), this.getTemperature()).toString();
    }
}
