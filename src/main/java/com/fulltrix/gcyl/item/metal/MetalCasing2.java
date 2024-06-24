package com.fulltrix.gcyl.item.metal;

import gregtech.api.block.VariantBlock;
import gregtech.api.unification.material.Material;
import gregtech.client.renderer.ICubeRenderer;
import net.minecraft.block.SoundType;
import net.minecraft.util.IStringSerializable;
import org.jetbrains.annotations.NotNull;

import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static com.fulltrix.gcyl.client.ClientHandler.*;
import static gregtech.api.unification.material.Materials.*;

public class MetalCasing2 extends VariantBlock<MetalCasing2.CasingType> {
    public MetalCasing2() {
        super(net.minecraft.block.material.Material.IRON);
        setTranslationKey("gcyl_metal_casing_2");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
        setDefaultState(getState(CasingType.STABALLOY));
    }

    public enum CasingType implements IStringSerializable {

        STABALLOY("casing_staballoy", Staballoy),
        QUANTUM("casing_quantum", Quantum),
        TRITANIUM("casing_tritanium", Tritanium),
        ENRICHED_NAQUADAH_ALLOY("casing_enriched_naquadah_alloy", EnrichedNaquadahAlloy),
        NAQUADRIA("casing_naquadria", Naquadria),
        IRIDIUM("casing_iridium", Iridium);

        private final String name;
        private final Material material;

        CasingType(String name, Material material) {
            this.name = name;
            this.material = material;
        }

        @Override
        public @NotNull String getName() {
            return this.name;
        }

        public Material getMaterial() {
            return this.material;
        }

        public ICubeRenderer getTexture() {
            switch (name) {
                case "casing_quantum" -> {
                    return QUANTUM_CASING;
                }
                case "casing_tritanium" -> {
                    return TRITANIUM_CASING;
                }
                case "casing_enriched_naquadah_alloy" -> {
                    return ENRICHED_NAQUADAH_ALLOY_CASING;
                }
                case "casing_naquadria" -> {
                    return NAQUADRIA_CASING;
                }
                case "casing_iridium" -> {
                    return IRIDIUM_CASING;
                }
                default -> {
                    return STABALLOY_CASING;
                }
            }
        }

    }
}
