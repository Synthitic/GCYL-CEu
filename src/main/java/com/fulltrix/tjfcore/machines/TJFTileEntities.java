package com.fulltrix.tjfcore.machines;

import com.fulltrix.tjfcore.machines.multi.*;
import com.fulltrix.tjfcore.recipes.TJFRecipeMaps;
import gregtech.api.recipes.RecipeMap;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.util.ResourceLocation;

public class TJFTileEntities {
    public static MetaTileEntityBioReactor BIO_REACTOR;

    public static int id = 32000;

    public static void init() {
        BIO_REACTOR = MetaTileEntities.registerMetaTileEntity(id, new MetaTileEntityBioReactor(new ResourceLocation("tjfcore", "bio_reactor")));
    }
}