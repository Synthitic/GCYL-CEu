package com.fulltrix.gcyl.item.metal;

import gregtech.api.block.IStateHarvestLevel;
import gregtech.api.block.VariantBlock;
import gregtech.common.blocks.BlockCleanroomCasing;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GCYLCleanroomCasing extends VariantBlock<GCYLCleanroomCasing.CasingType> implements IStateHarvestLevel {

    public GCYLCleanroomCasing() {
        super(Material.IRON);
        this.setTranslationKey("cleanroom_casing");
        this.setHardness(2.0F);
        this.setResistance(8.0F);
        this.setSoundType(SoundType.METAL);
        this.setDefaultState(this.getState(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO3));
    }

    public int getHarvestLevel(@NotNull IBlockState state) {
        return state == this.getState(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO3) ? 2 : 1;
    }

    public @Nullable String getHarvestTool(@NotNull IBlockState state) {
        return state == this.getState(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO3) ? "pickaxe" : "wrench";
    }

    public void addInformation(@NotNull ItemStack stack, @Nullable World player, @NotNull List<String> tooltip, @NotNull ITooltipFlag advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        if (stack.isItemEqual(this.getItemVariant(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO3))) {
            tooltip.add(I18n.format("tile.cleanroom_casing.filter_iso_3.tooltip", new Object[0]));
        }

        if (stack.isItemEqual(this.getItemVariant(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO2))) {
            tooltip.add(I18n.format("tile.cleanroom_casing.filter_iso_2.tooltip", new Object[0]));
        }

        if (stack.isItemEqual(this.getItemVariant(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO1))) {
            tooltip.add(I18n.format("tile.cleanroom_casing.filter_iso_1.tooltip", new Object[0]));
        }

    }

    public static enum CasingType implements IStringSerializable {
        FILTER_CASING_ISO3("filter_casing_iso_3"),
        FILTER_CASING_ISO2("filter_casing_iso_2"),
        FILTER_CASING_ISO1("filter_casing_iso_1");


        private final String name;

        private CasingType(String name) {
            this.name = name;
        }

        public @NotNull String getName() {
            return this.name;
        }

        public @NotNull String toString() {
            return this.getName();
        }
    }
}
