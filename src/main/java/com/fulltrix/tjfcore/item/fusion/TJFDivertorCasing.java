package com.fulltrix.tjfcore.item.fusion;

import gregtech.api.block.VariantBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nonnull;

public class TJFDivertorCasing extends VariantBlock<TJFDivertorCasing.CasingType> {
    public TJFDivertorCasing() {
        super(Material.IRON);
        setTranslationKey("ga_divertor_casing");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 2);
        setDefaultState(getState(CasingType.DIVERTOR_1));
    }

    @Override
    public boolean canCreatureSpawn(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull EntityLiving.SpawnPlacementType type) {
        return false;
    }

    public enum CasingType implements IStringSerializable {

        DIVERTOR_1("divertor_1", 1),
        DIVERTOR_2("divertor_2", 2),
        DIVERTOR_3("divertor_3", 3),
        DIVERTOR_4("divertor_4", 4),
        DIVERTOR_5("divertor_5", 5);


        private final String name;
        private final int tier;

        CasingType(String name, int tier) {
            this.name = name;
            this.tier = tier;
        }

        @Override
        public String getName() {
            return this.name;
        }

        public int getTier() {
            return tier;
        }
    }
}
