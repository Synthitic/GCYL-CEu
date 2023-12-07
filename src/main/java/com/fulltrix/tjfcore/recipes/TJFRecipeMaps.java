package com.fulltrix.tjfcore.recipes;

//import com.fulltrix.tjfcore.recipes.impl.NuclearReactorBuilder;

import com.fulltrix.tjfcore.recipes.impl.AdvFusionRecipeBuilder;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.gui.widgets.ProgressWidget.MoveType;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.core.sound.GTSoundEvents;
import net.minecraft.init.SoundEvents;

public class TJFRecipeMaps {

    private TJFRecipeMaps() {
    }

    public static final RecipeMap<SimpleRecipeBuilder> BIO_REACTOR_RECIPES = new RecipeMap<>("bio_reactor",
            3, 3, 5, 2, new SimpleRecipeBuilder(), false)
            .setSound(GTSoundEvents.ELECTROLYZER);

    public static final RecipeMap<SimpleRecipeBuilder> CHEMICAL_DEHYDRATOR_RECIPES = new RecipeMap<>("chemical_dehydrator", 2, 9, 2, 2, new SimpleRecipeBuilder(), false)
            .setProgressBar(GuiTextures.PROGRESS_BAR_SIFT, MoveType.VERTICAL_INVERTED)
            .setSound(SoundEvents.BLOCK_SAND_PLACE);


    //public static final RecipeMap<SimpleRecipeBuilder> GAS_CENTRIFUGE_RECIPES = new RecipeMap<>("gas_centrifuge", 1, 0, 1, 3, new SimpleRecipeBuilder(), false)
    //       .setSlotOverlay(false, false, GuiTextures.CIRCUIT_OVERLAY)
    //       .setProgressBar(GuiTextures.PROGRESS_BAR_BATH, ProgressWidget.MoveType.VERTICAL_INVERTED); //NUCLEAR_MARK

    //public static final RecipeMap<NuclearReactorBuilder> NUCLEAR_REACTOR_RECIPES = new RecipeMap<>("nuclear_reactor", 4, 4, 0, 0, new NuclearReactorBuilder().EUt(1920),false); //NUCLEAR_MARK

    //public static final RecipeMap<NuclearReactorBuilder> NUCLEAR_BREEDER_RECIPES = new RecipeMap<>("nuclear_breeder", 4, 4, 0, 0, new NuclearReactorBuilder().EUt(1920),false); //NUCLEAR_MARK

    public static final RecipeMap<SimpleRecipeBuilder> DECAY_CHAMBERS_RECIPES = new RecipeMap<>("decay_chamber", 1, 1, 0, 0, new SimpleRecipeBuilder().EUt(30), false)
            .setProgressBar(GuiTextures.PROGRESS_BAR_HAMMER, ProgressWidget.MoveType.VERTICAL); //NUCLEAR_MARK

    public static final RecipeMap<AdvFusionRecipeBuilder> ADV_FUSION_RECIPES = new RecipeMap<>("adv_fusion", 0, 0, 3, 3, new AdvFusionRecipeBuilder(), false)
            .setSound(GTSoundEvents.ARC);

    public static final RecipeMap<SimpleRecipeBuilder> STELLAR_FORGE_RECIPES = new RecipeMap<>("stellar_forge",
            3, 2, 3, 2, (new SimpleRecipeBuilder()), false)
            .setProgressBar(GuiTextures.PROGRESS_BAR_BATH, ProgressWidget.MoveType.HORIZONTAL)
            .setSound(GTSoundEvents.COMBUSTION);


    public static final RecipeMap<SimpleRecipeBuilder> PLASMA_CONDENSER_RECIPES = new RecipeMap<>("plasma_condenser",
            2, 2, 2, 2, (new SimpleRecipeBuilder()), false)
            .setProgressBar(GuiTextures.PROGRESS_BAR_BATH, ProgressWidget.MoveType.HORIZONTAL)
            .setSound(GTSoundEvents.COOLING);
}
