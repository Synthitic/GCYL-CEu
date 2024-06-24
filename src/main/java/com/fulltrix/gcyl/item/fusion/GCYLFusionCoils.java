package com.fulltrix.gcyl.item.fusion;

import gregtech.api.block.IStateHarvestLevel;
import gregtech.api.block.VariantActiveBlock;
import gregtech.api.items.toolitem.ToolClasses;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

//TODO: make coils have bloom while working.
public class GCYLFusionCoils extends VariantActiveBlock<GCYLFusionCoils.CasingType> {
    public GCYLFusionCoils() {
        super(net.minecraft.block.material.Material.IRON);
        setTranslationKey("gcyl_fusion_casing");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setDefaultState(getState(CasingType.ADV_FUSION_COIL_1));
    }

    @Override
    public boolean canCreatureSpawn(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull EntityLiving.SpawnPlacementType type) {
        return false;
    }


    public enum CasingType implements IStringSerializable, IStateHarvestLevel {

        ADV_FUSION_COIL_1("adv_fusion_coil_1", 3),
        ADV_FUSION_COIL_2("adv_fusion_coil_2", 3),
        ADV_FUSION_COIL_3("adv_fusion_coil_3", 4),
        ADV_FUSION_COIL_4("adv_fusion_coil_4", 4),
        ADV_FUSION_COIL_5("adv_fusion_coil_5", 5);

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

        @NotNull
        @Override
        public String toString() {
            return getName();
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
