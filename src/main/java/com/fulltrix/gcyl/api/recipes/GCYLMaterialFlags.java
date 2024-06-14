package com.fulltrix.gcyl.api.recipes;

import gregtech.api.unification.material.info.MaterialFlag;
import gregtech.api.unification.material.properties.PropertyKey;

public class GCYLMaterialFlags {

    public static final MaterialFlag NO_MIXER_RECIPE;

    private GCYLMaterialFlags() {
    }

    static {
        NO_MIXER_RECIPE = (new MaterialFlag.Builder("no_mixer_recipes")).requireProps(new PropertyKey[]{PropertyKey.BLAST}).build();
    }
}
