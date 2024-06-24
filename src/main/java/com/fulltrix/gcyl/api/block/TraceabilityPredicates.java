package com.fulltrix.gcyl.api.block;

import com.fulltrix.gcyl.item.GCYLMetaBlocks;
import com.fulltrix.gcyl.item.fusion.GCYLFusionCoils;
import com.fulltrix.gcyl.item.metal.GCYLCleanroomCasing;
import crafttweaker.util.ArrayUtil;
import gregtech.api.pattern.PatternStringError;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.util.BlockInfo;
import gregtech.common.blocks.BlockCleanroomCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.function.Supplier;

public class TraceabilityPredicates {
    private static final Supplier<TraceabilityPredicate> ADV_FUSION_COIL_PRED =
            () -> new TraceabilityPredicate(blockWorldState -> {
            IBlockState blockState = blockWorldState.getBlockState();
            Block block = blockState.getBlock();
            if (block instanceof GCYLFusionCoils) {
                GCYLFusionCoils.CasingType casingType = ((GCYLFusionCoils) blockState.getBlock()).getState(blockState);
                Object coil = blockWorldState.getMatchContext().getOrPut("AdvFusionCoilTier", casingType);
                if (!coil.toString().equals(casingType.getName())) {
                    blockWorldState.setError(new PatternStringError("gregtech.multiblock.pattern.error.filters"));
                    return false;
                }
                blockWorldState.getMatchContext().getOrPut("VABlock", new LinkedList<>()).add(blockWorldState.getPos());
                return true;
            }
            return false;
        }, () -> ArrayUtils.addAll(
                Arrays.stream(GCYLFusionCoils.CasingType.values())
                        .map(type -> new BlockInfo(GCYLMetaBlocks.FUSION_COILS.getState(type), null))
                        .toArray(BlockInfo[]::new)))
                .addTooltips("");

    private static final Supplier<TraceabilityPredicate> FILTER_CASING_PRED =
            () -> new TraceabilityPredicate(blockWorldState -> {
                IBlockState blockState = blockWorldState.getBlockState();
                Block block = blockState.getBlock();
                if (block instanceof BlockCleanroomCasing) {
                    BlockCleanroomCasing.CasingType casingType = ((BlockCleanroomCasing) blockState.getBlock())
                            .getState(blockState);
                    if (casingType.equals(BlockCleanroomCasing.CasingType.PLASCRETE)) return false;

                    Object currentFilter = blockWorldState.getMatchContext().getOrPut("FilterType", casingType);
                    if (!currentFilter.toString().equals(casingType.getName())) {
                        blockWorldState.setError(new PatternStringError("gregtech.multiblock.pattern.error.filters"));
                        return false;
                    }
                    blockWorldState.getMatchContext().getOrPut("VABlock", new LinkedList<>()).add(blockWorldState.getPos());
                    return true;
                }

                if (block instanceof GCYLCleanroomCasing) {
                    GCYLCleanroomCasing.CasingType casingType = (GCYLCleanroomCasing.CasingType)((GCYLCleanroomCasing)blockState.getBlock()).getState(blockState);
                    Object currentFilter = blockWorldState.getMatchContext().getOrPut("FilterType", casingType);
                    if (!currentFilter.toString().equals(casingType.getName())) {
                        blockWorldState.setError(new PatternStringError("gregtech.multiblock.pattern.error.filters"));
                        return false;
                    } else {
                        ((LinkedList)blockWorldState.getMatchContext().getOrPut("VABlock", new LinkedList())).add(blockWorldState.getPos());
                        return true;
                    }
                }
                return false;
            }, () -> ArrayUtils.addAll(
                    Arrays.stream(BlockCleanroomCasing.CasingType.values())
                            .filter(type -> !type.equals(BlockCleanroomCasing.CasingType.PLASCRETE))
                            .map(type -> new BlockInfo(MetaBlocks.CLEANROOM_CASING.getState(type), null))
                            .toArray(BlockInfo[]::new),
                    Arrays.stream(GCYLCleanroomCasing.CasingType.values())
                            .map(type -> new BlockInfo(GCYLMetaBlocks.GCYL_CLEANROOM_CASING.getState(type), null))
                            .toArray(BlockInfo[]::new)))
                    .addTooltips("gregtech.multiblock.pattern.error.filters");


    public static TraceabilityPredicate advFusionCoils() {
        return ADV_FUSION_COIL_PRED.get();
    }

    public static TraceabilityPredicate filterCasings() { return FILTER_CASING_PRED.get(); }
}
