package com.fulltrix.gcyl.item.fusion;

import gregtech.api.block.IStateHarvestLevel;
import gregtech.api.block.VariantActiveBlock;
import gregtech.api.block.VariantBlock;
import gregtech.api.items.toolitem.ToolClasses;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class GCYLFusionCasing extends VariantBlock<GCYLFusionCasing.CasingType> {
    public GCYLFusionCasing() {
        super(net.minecraft.block.material.Material.IRON);
        setTranslationKey("gcyl_fusion_casing");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setDefaultState(getState(CasingType.ADV_FUSION_CASING));
    }

    @Override
    public boolean canCreatureSpawn(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull EntityLiving.SpawnPlacementType type) {
        return false;
    }

    public enum CasingType implements IStringSerializable, IStateHarvestLevel {

        ADV_FUSION_CASING("adv_fusion_casing", 3),
        BLANKET_BASE("blanket_base", 3),
        FUSION_BLANKET("fusion_blanket", 3),
        BREEDING_BLANKET("breeding_blanket", 3);

        private final int harvestLevel;
        private final String name;

        CasingType(String name, int harvestLevel) {
            this.name = name;
            this.harvestLevel = harvestLevel;
        }
        @NotNull
        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public int getHarvestLevel(IBlockState state) {
            return harvestLevel;
        }

        @Override
        public String getHarvestTool(IBlockState state) {
            return ToolClasses.WRENCH;
        }

    }
}