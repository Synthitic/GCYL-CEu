package com.fulltrix.tjfcore.recipes.recipeproperties;

import gregtech.api.recipes.recipeproperties.RecipeProperty;
import gregtech.api.util.TextFormattingUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import org.apache.commons.lang3.Validate;

import java.util.Map;
import java.util.TreeMap;

public class AdvFusionCoilProperty extends RecipeProperty<Integer> {
    public static final String KEY = "adv_fusion_coil_tier";

    private static final TreeMap<Integer, String> registeredAdvFusionTiers = new TreeMap<>();

    private static AdvFusionCoilProperty INSTANCE;

    protected AdvFusionCoilProperty() {
        super(KEY, Integer.class);
    }

    public static AdvFusionCoilProperty getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AdvFusionCoilProperty();
        }

        return INSTANCE;
    }

    private static String getCoilTier(Integer tier) {
        Map.Entry<Integer, String> mapEntry = registeredAdvFusionTiers.ceilingEntry(tier);

        if (mapEntry == null) {
            throw new IllegalArgumentException("oopz");
        }

        return String.format(" %s", mapEntry.getValue());
    }

    public static void registerAdvFusionTier(int tier, String shortName) {
        Validate.notNull(shortName);
        registeredAdvFusionTiers.put(tier, shortName);
    }

    @Override
    public void drawInfo(Minecraft minecraft, int x, int y, int color, Object value) {
        minecraft.fontRenderer.drawString(I18n.format("gregtech.recipe.adv_fusion_coil_tier",
                        TextFormattingUtil.formatLongToCompactString(castValue(value))) + getCoilTier(castValue(value)), x, y,
                color);
    }
}
