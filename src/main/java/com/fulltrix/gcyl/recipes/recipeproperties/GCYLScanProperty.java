package com.fulltrix.gcyl.recipes.recipeproperties;

import gregtech.api.recipes.recipeproperties.RecipeProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import org.jetbrains.annotations.NotNull;

public class GCYLScanProperty extends RecipeProperty<Boolean> {
    public static final String KEY = "gcyl_scan";

    private static GCYLScanProperty INSTANCE;

    private GCYLScanProperty() {super(KEY, Boolean.class);}

    @NotNull
    public static GCYLScanProperty getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GCYLScanProperty();
        }
        return INSTANCE;
    }

    @Override
    public void drawInfo(Minecraft minecraft, int x, int y, int color, Object value) {
        minecraft.fontRenderer.drawString(I18n.format("gcyl.recipe.scan_for_deep_miner"), x, y, color);
    }
}
