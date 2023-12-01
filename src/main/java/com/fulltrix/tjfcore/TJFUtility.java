package com.fulltrix.tjfcore;

import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class TJFUtility {
    public static @NotNull ResourceLocation tjfId(@NotNull String path) {
        return new ResourceLocation("tjfcore", path);
    }
}
