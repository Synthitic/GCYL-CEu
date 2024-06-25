package com.fulltrix.gcyl.blocks.component_al;

import com.fulltrix.gcyl.api.block.IComponentALTier;
import gregtech.api.block.IStateHarvestLevel;
import gregtech.api.block.VariantBlock;
import gregtech.api.items.toolitem.ToolClasses;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class GCYLComponentALCasing extends VariantBlock<GCYLComponentALCasing.CasingType> {
    public GCYLComponentALCasing() {
        super(Material.IRON);
        setTranslationKey("gcyl_component_al_casing");
        setHardness(5.0f);
        setResistance(10.0f);
        setSoundType(SoundType.METAL);
        setDefaultState(getState(CasingType.CASING_LV));
    }

    @Override
    public boolean canCreatureSpawn(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull EntityLiving.SpawnPlacementType type) {
        return false;
    }

    public enum CasingType implements IStringSerializable, IStateHarvestLevel, IComponentALTier {

        CASING_LV("lv", 3),
        CASING_MV("mv", 3),
        CASING_HV("hv", 3),
        CASING_EV("ev", 3),
        CASING_IV("iv", 3),
        CASING_LuV("luv", 3),
        CASING_ZPM("zpm", 3),
        CASING_UV("uv", 3),
        CASING_UHV("uhv", 3),
        CASING_UEV("uev", 3),
        CASING_UIV("uiv", 3),
        CASING_UXV("uxv", 3),
        CASING_OpV("opv", 3),
        CASING_MAX("max", 3);

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

        @Override
        public int getTier() {return this.ordinal(); }

        public CasingType getCasingByTier(int tier) {
            return switch (tier) {
                case(2)-> CASING_MV;
                case(3)-> CASING_HV;
                case(4)-> CASING_EV;
                case(5)-> CASING_IV;
                case(6)-> CASING_LuV;
                case(7)-> CASING_ZPM;
                case(8)-> CASING_UV;
                case(9)-> CASING_UHV;
                case(10)-> CASING_UEV;
                case(11)-> CASING_UIV;
                case(12)-> CASING_UXV;
                case(13)-> CASING_OpV;
                case(14)-> CASING_MAX;
                default -> CASING_LV;
            };
        }
    }
}