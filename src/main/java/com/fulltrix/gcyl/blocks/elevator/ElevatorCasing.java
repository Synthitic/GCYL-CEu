package com.fulltrix.gcyl.blocks.elevator;

import com.fulltrix.gcyl.blocks.fusion.GCYLCryostatCasing;
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

public class ElevatorCasing extends VariantBlock<ElevatorCasing.CasingType> {
    public ElevatorCasing() {
        super(Material.IRON);
        setTranslationKey("gcyl_elevator_casing");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("wrench", 3);
        setDefaultState(getState(ElevatorCasing.CasingType.HIGH_STRENGTH_CONCRETE));
    }

    @Override
    public boolean canCreatureSpawn(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull EntityLiving.SpawnPlacementType type) {
        return false;
    }

    public enum CasingType implements IStringSerializable {

        HIGH_STRENGTH_CONCRETE("concrete_strong"),
        ELEVATOR_BASE_CASING("elevator_base"),
        ELEVATOR_INTERNAL_STRUCTURE("elevator_internal"),
        ELEVATOR_SUPPORT_STRUCTURE("elevator_support"),
        ELEVATOR_CABLE("elevator_cable");


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
