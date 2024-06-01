package com.fulltrix.gcyl;

import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class GCYLUtility {

    public static @NotNull ResourceLocation gcylId(@NotNull String path) {
        return new ResourceLocation("gcyl", path);
    }

    public static int setBetweenInclusive(int value, int start, int end) {
        if (value < start)
            return start;
        return Math.min(value, end);
    }
}
