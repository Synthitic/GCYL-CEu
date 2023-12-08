package com.fulltrix.tjfcore.item.metal;

import gregtech.api.block.VariantBlock;
import gregtech.api.unification.material.Material;
import gregtech.client.renderer.ICubeRenderer;
import net.minecraft.block.SoundType;
import net.minecraft.util.IStringSerializable;

import static com.fulltrix.tjfcore.TJFMaterials.Quantum;
import static com.fulltrix.tjfcore.TJFMaterials.Staballoy;
import static com.fulltrix.tjfcore.client.ClientHandler.*;
import static gregtech.api.unification.material.Materials.Tritanium;

public class MetalCasing2 extends VariantBlock<MetalCasing2.CasingType> {
    public MetalCasing2() {
        super(net.minecraft.block.material.Material.IRON);
        setTranslationKey("tjf_metal_casing_2");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
        setDefaultState(getState(CasingType.STABALLOY));
    }

    public enum CasingType implements IStringSerializable {

        STABALLOY("casing_staballoy", Staballoy),
        QUANTUM("casing_quantum", Quantum),
        TRITANIUM("casing_tritanium", Tritanium);

        private final String name;
        private final Material material;

        CasingType(String name, Material material) {
            this.name = name;
            this.material = material;
        }

        @Override
        public String getName() {
            return this.name;
        }

        public Material getMaterial() {
            return this.material;
        }

        public ICubeRenderer getTexture() {
            switch (name) {
                case "casing_staballoy" -> {
                    return STABALLOY_CASING;
                }
                case "casing_quantum" -> {
                    return QUANTUM_CASING;
                }
                case "casing_tritanium" -> {
                    return TRITANIUM_CASING;
                }
                default -> {
                    return STABALLOY_CASING;
                }
            }
        }

    }
}
