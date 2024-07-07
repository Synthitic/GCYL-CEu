package com.fulltrix.gcyl.blocks.metal;

import com.fulltrix.gcyl.api.multi.GCYLCleanroomType;
import gregtech.api.GTValues;
import gregtech.api.block.ICleanroomFilter;
import gregtech.api.block.IStateHarvestLevel;
import gregtech.api.block.VariantBlock;
import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.client.utils.TooltipHelper;
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
            tooltip.add(TooltipHelper.BLINKING_RED + I18n.format("tile.cleanroom_casing.warning"));
        }

        if (stack.isItemEqual(this.getItemVariant(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO2))) {
            tooltip.add(I18n.format("tile.cleanroom_casing.filter_iso_2.tooltip", new Object[0]));
            tooltip.add(TooltipHelper.BLINKING_RED + I18n.format("tile.cleanroom_casing.warning"));
        }

        if (stack.isItemEqual(this.getItemVariant(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO1))) {
            tooltip.add(I18n.format("tile.cleanroom_casing.filter_iso_1.tooltip", new Object[0]));
            tooltip.add(TooltipHelper.BLINKING_RED + I18n.format("tile.cleanroom_casing.warning"));
        }

        if (stack.isItemEqual(this.getItemVariant(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO0))) {
            tooltip.add(I18n.format("tile.cleanroom_casing.filter_iso_0.tooltip", new Object[0]));
            tooltip.add(TooltipHelper.BLINKING_RED + I18n.format("tile.cleanroom_casing.warning"));
        }

    }

    public static enum CasingType implements IStringSerializable, ICleanroomFilter {
        FILTER_CASING_ISO3("filter_casing_iso_3", GCYLCleanroomType.ISO3, GTValues.UV),
        FILTER_CASING_ISO2("filter_casing_iso_2", GCYLCleanroomType.ISO2, GTValues.UHV),
        FILTER_CASING_ISO1("filter_casing_iso_1", GCYLCleanroomType.ISO1, GTValues.UEV),
        FILTER_CASING_ISO0("filter_casing_iso_0", GCYLCleanroomType.ISO0, GTValues.UIV);


        private final String name;
        private final CleanroomType cleanroomType;
        private final int minTier;

        CasingType(String name, CleanroomType cleanroomType, int minTier) {
            this.name = name;
            this.cleanroomType = cleanroomType;
            this.minTier = minTier;
        }

        public @NotNull String getName() {
            return this.name;
        }

        public @NotNull String toString() {
            return this.getName();
        }

        @Override
        public @Nullable CleanroomType getCleanroomType() {
            return this.cleanroomType;
        }

        @Override
        public int getTier() { return this.ordinal() + 2;}

        @Override
        public int getMinTier() {
            return this.minTier;
        }
    }
}
