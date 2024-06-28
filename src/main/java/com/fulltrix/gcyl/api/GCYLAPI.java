package com.fulltrix.gcyl.api;

import com.fulltrix.gcyl.api.block.IComponentALTier;
import com.fulltrix.gcyl.api.block.IElevatorMotorTier;
import com.fulltrix.gcyl.api.block.IFusionCoilBlockStats;
import gregtech.api.recipes.Recipe;
import gregtech.api.unification.ore.OrePrefix;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class GCYLAPI {
    public static final Object2ObjectMap<IBlockState, IFusionCoilBlockStats> FUSION_COILS = new Object2ObjectOpenHashMap<>();

    public static final Object2ObjectMap<IBlockState, IComponentALTier> COMPONENT_AL_CASINGS = new Object2ObjectOpenHashMap<>();

    public static final Object2ObjectMap<IBlockState, IElevatorMotorTier> ELEVATOR_MOTORS = new Object2ObjectOpenHashMap<>();

}
