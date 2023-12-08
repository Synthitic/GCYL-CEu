package com.fulltrix.tjfcore.item.fusion;

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

public class TJFVacuumCasing extends VariantBlock<TJFVacuumCasing.CasingType> {
    public TJFVacuumCasing() {
        super(Material.IRON);
        setTranslationKey("tjf_vacuum_casing");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
        setDefaultState(getState(CasingType.VACUUM_1));
    }

    @Override
    public boolean canCreatureSpawn(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull EntityLiving.SpawnPlacementType type) {
        return false;
    }

    public enum CasingType implements IStringSerializable {

        VACUUM_1("vacuum_1", 1),
        VACUUM_2("vacuum_2", 2),
        VACUUM_3("vacuum_3", 3),
        VACUUM_4("vacuum_4", 4),
        VACUUM_5("vacuum_5", 5);


        private final String name;
        private final int tier;

        CasingType(String name, int tier) {
            this.name = name;
            this.tier = tier;
        }

        @Override
        public @NotNull String getName() {
            return this.name;
        }

        public int getTier() {
            return tier;
        }
    }
}
