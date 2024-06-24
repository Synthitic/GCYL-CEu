package com.fulltrix.gcyl.api.recipes;

import gregicality.multiblocks.api.unification.GCYMMaterialFlags;
import gregicality.multiblocks.api.unification.properties.AlloyBlastProperty;
import gregicality.multiblocks.api.unification.properties.AlloyBlastPropertyAddition;
import gregicality.multiblocks.api.unification.properties.GCYMPropertyKey;
import gregtech.api.GregTechAPI;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.BlastProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.stack.MaterialStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MixerPropertyAddition {

    private MixerPropertyAddition() {}

    public static void init() {
        for (Material material : GregTechAPI.materialManager.getRegisteredMaterials()) {
            if (!material.hasFlag(GCYLMaterialFlags.NO_MIXER_RECIPE)) {
                addMixerRecipeProperty(material);
            }
        }
    }

    private static void addMixerRecipeProperty(@NotNull Material material) {
        final List<MaterialStack> components = material.getMaterialComponents();
        // ignore materials which are not alloys
        if (components.size() < 2) return;

        BlastProperty blastProperty = material.getProperty(PropertyKey.BLAST);
        if (blastProperty == null) return;

        material.setProperty(GCYLPropertyKey.GENERATE_MIXER, new MixerProperty());
    }

    private static boolean isMaterialStackFluidOnly(@NotNull MaterialStack ms) {
        return !ms.material.hasProperty(PropertyKey.DUST) && ms.material.hasProperty(PropertyKey.FLUID);
    }
}
