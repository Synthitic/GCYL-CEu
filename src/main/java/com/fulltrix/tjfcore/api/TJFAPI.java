package com.fulltrix.tjfcore.api;

import com.fulltrix.tjfcore.api.block.IFusionCoilBlockStats;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.block.state.IBlockState;

public class TJFAPI {
    public static final Object2ObjectMap<IBlockState, IFusionCoilBlockStats> FUSION_COILS = new Object2ObjectOpenHashMap<>();
}
