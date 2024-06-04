package com.fulltrix.gcyl.item.fusion;

import gregtech.api.block.VariantBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class GCYLFusionCasing extends VariantBlock<GCYLFusionCasing.CasingType> {
    public GCYLFusionCasing() {
        super(Material.IRON);
        setTranslationKey("gcyl_fusion_casing");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
        setDefaultState(getState(CasingType.ADV_FUSION_CASING));
    }

    @Override
    public boolean canCreatureSpawn(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull EntityLiving.SpawnPlacementType type) {
        return false;
    }

    public enum CasingType implements IStringSerializable {

        ADV_FUSION_CASING("adv_fusion_casing"),
        //FUSION_COIL_2("fusion_coil_2"),
        //FUSION_COIL_3("fusion_coil_3"),
        ADV_FUSION_COIL_1("adv_fusion_coil_1"),
        ADV_FUSION_COIL_2("adv_fusion_coil_2"),
        ADV_FUSION_COIL_3("adv_fusion_coil_3"),
        ADV_FUSION_COIL_4("adv_fusion_coil_4"),
        ADV_FUSION_COIL_5("adv_fusion_coil_5"),
        BLANKET_BASE("blanket_base"),
        FUSION_BLANKET("fusion_blanket"),
        BREEDING_BLANKET("breeding_blanket");


        private final String name;

        CasingType(String name) {
            this.name = name;
        }

        @Override
        public @NotNull String getName() {
            return this.name;
        }

    }
}
