package com.fulltrix.gcyl.api.recipes;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class GCYLMaterialRecipeHandler {

    private GCYLMaterialRecipeHandler() {}

    public static void register() {
        OrePrefix.dust.addProcessingHandler(GCYLPropertyKey.GENERATE_MIXER, GCYLMaterialRecipeHandler::generateMixerRecipes);
    }

    public static void generateMixerRecipes(@Nullable OrePrefix unused, @NotNull Material material,
                                                 @NotNull MixerProperty property) {
        if (material.hasProperty(PropertyKey.BLAST)) {
            property.getRecipeProducer().produce(material, material.getProperty(PropertyKey.BLAST));
        }
    }
}

