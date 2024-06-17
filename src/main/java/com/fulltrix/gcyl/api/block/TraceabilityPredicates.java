package com.fulltrix.gcyl.api.block;

import com.fulltrix.gcyl.item.GCYLMetaBlocks;
import com.fulltrix.gcyl.item.fusion.GCYLFusionCoils;
import crafttweaker.util.ArrayUtil;
import gregtech.api.pattern.PatternStringError;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.util.BlockInfo;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.function.Supplier;

public class TraceabilityPredicates {
    private static Supplier<TraceabilityPredicate> ADV_FUSION_COIL_PRED =
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

    public static TraceabilityPredicate advFusionCoils() {
        return ADV_FUSION_COIL_PRED.get();
    }
}
