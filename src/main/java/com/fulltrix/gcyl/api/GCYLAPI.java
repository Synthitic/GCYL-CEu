package com.fulltrix.gcyl.api;

import com.fulltrix.gcyl.api.block.IFusionCoilBlockStats;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.block.state.IBlockState;

public class GCYLAPI {
    public static final Object2ObjectMap<IBlockState, IFusionCoilBlockStats> FUSION_COILS = new Object2ObjectOpenHashMap<>();
}
