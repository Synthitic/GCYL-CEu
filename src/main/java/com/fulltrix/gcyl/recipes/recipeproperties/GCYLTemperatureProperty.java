package com.fulltrix.gcyl.recipes.recipeproperties;

import gregtech.api.recipes.recipeproperties.RecipeProperty;
import gregtech.api.recipes.recipeproperties.TemperatureProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

public class GCYLTemperatureProperty extends RecipeProperty<Integer> {
    public static final String KEY = "temperature";

    private static GCYLTemperatureProperty INSTANCE;

    private GCYLTemperatureProperty() {
        super("temperature", Integer.class);
    }

    public static GCYLTemperatureProperty getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GCYLTemperatureProperty();
        }

        return INSTANCE;
    }

    @Override
    public void drawInfo(Minecraft minecraft, int x, int y, int color, Object value) {
        minecraft.fontRenderer.drawString(I18n.format("gcyl.recipe.temperature", value), x, y, color);
    }

}
