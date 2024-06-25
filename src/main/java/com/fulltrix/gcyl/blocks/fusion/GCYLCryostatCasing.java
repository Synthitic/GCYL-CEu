package com.fulltrix.gcyl.blocks.fusion;

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

public class GCYLCryostatCasing extends VariantBlock<GCYLCryostatCasing.CasingType> {
    public GCYLCryostatCasing() {
        super(Material.IRON);
        setTranslationKey("gcyl_cryostat_casing");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
        setDefaultState(getState(CasingType.CRYOSTAT_1));
    }

    @Override
    public boolean canCreatureSpawn(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull EntityLiving.SpawnPlacementType type) {
        return false;
    }

    public enum CasingType implements IStringSerializable {

        CRYOSTAT_1("cryostat_1", 1),
        CRYOSTAT_2("cryostat_2", 2),
        CRYOSTAT_3("cryostat_3", 3),
        CRYOSTAT_4("cryostat_4", 4),
        CRYOSTAT_5("cryostat_5", 5);


        private final String name;
        private final int tier;

        CasingType(String name, int tier) {
            this.name = name;
            this.tier = tier;
        }

        public int getTier() {
            return tier;
        }

        @Override
        public @NotNull String getName() {
            return this.name;
        }

    }
}
