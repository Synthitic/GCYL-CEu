package com.fulltrix.gcyl.item.metal;


import gregtech.api.block.VariantBlock;
import gregtech.api.unification.material.Material;
import gregtech.client.renderer.ICubeRenderer;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static com.fulltrix.gcyl.client.ClientHandler.*;

public class MetalCasing1 extends VariantBlock<MetalCasing1.CasingType> {

    public MetalCasing1() {
        super(net.minecraft.block.material.Material.IRON);
        setTranslationKey("gcyl_metal_casing_1");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
        setDefaultState(getState(MetalCasing1.CasingType.HASTELLOY_X78));
    }

    @Override
    public boolean canCreatureSpawn(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull EntityLiving.SpawnPlacementType type) {
        return false;
    }

    public enum CasingType implements IStringSerializable {

        HASTELLOY_X78("casing_hastelloy_x78", HastelloyX78),
        HASTELLOY_N("casing_hastelloy_n", HastelloyN),
        NITINOL_60("casing_nitinol_60", Nitinol60),
        INCOLOY_813("casing_incoloy_813", Incoloy813),
        HASTELLOY_K243("casing_hastelloy_k243", HastelloyK243),
        MARAGING_STEEL_250("casing_maraging_steel_250", MaragingSteel250),
        INCONEL_625("casing_inconel_625", Inconel625);



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
                case "casing_hastelloy_x78" -> {
                    return HASTELLOY_X78_CASING;
                }
                case "casing_hastelloy_n" -> {
                    return HASTELLOY_N_CASING;
                }
                case "casing_incoloy_813" -> {
                    return INCOLOY_813_CASING;
                }
                case "casing_nitinol_60" -> {
                    return NITINOL_60_CASING;
                }
                case "casing_hastelloy_k243" -> {
                    return HASTELLOY_K243_CASING;
                }
                default -> {
                    return HASTELLOY_X78_CASING;
                }
            }
        }
    }
}
