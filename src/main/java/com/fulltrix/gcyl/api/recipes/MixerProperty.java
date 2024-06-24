package com.fulltrix.gcyl.api.recipes;

import gregicality.multiblocks.api.recipes.alloyblast.AlloyBlastRecipeProducer;
import gregtech.api.unification.material.properties.IMaterialProperty;
import gregtech.api.unification.material.properties.MaterialProperties;
import gregtech.api.unification.material.properties.PropertyKey;
import org.jetbrains.annotations.NotNull;

public class MixerProperty implements IMaterialProperty {
    private MixerRecipeProducer recipeProducer = MixerRecipeProducer.DEFAULT_PRODUCER;

    public MixerProperty() {}

    public @NotNull MixerRecipeProducer getRecipeProducer() {
        return this.recipeProducer;
    }

    public void setRecipeProducer(@NotNull MixerRecipeProducer recipeProducer) {
        this.recipeProducer = recipeProducer;
    }

    @Override
    public void verifyProperty(MaterialProperties materialProperties) {
        materialProperties.ensureSet(PropertyKey.BLAST);
    }
}
