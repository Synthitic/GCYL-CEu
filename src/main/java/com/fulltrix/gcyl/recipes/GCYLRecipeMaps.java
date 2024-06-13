package com.fulltrix.gcyl.recipes;

//import com.fulltrix.tjfcore.recipes.impl.NuclearReactorBuilder;

import com.fulltrix.gcyl.recipes.impl.AdvFusionRecipeBuilder;
import com.fulltrix.gcyl.recipes.impl.DeepMinerBuilder;
import gregtech.api.GTValues;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.gui.widgets.ProgressWidget.MoveType;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.FuelRecipeBuilder;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.core.sound.GTSoundEvents;
import net.minecraft.init.SoundEvents;

import static gregtech.api.recipes.RecipeMaps.*;

public final class GCYLRecipeMaps {

    private GCYLRecipeMaps() {
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

    public static final RecipeMap<SimpleRecipeBuilder> DECAY_CHAMBERS_RECIPES = new RecipeMap<>("decay_chamber", 2, 4, 1, 1, new SimpleRecipeBuilder(), false)
            .setProgressBar(GuiTextures.PROGRESS_BAR_HAMMER, ProgressWidget.MoveType.VERTICAL)
            .setSound(GTSoundEvents.ARC); //NUCLEAR_MARK

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

    public static final RecipeMap<SimpleRecipeBuilder> CHEMICAL_PLANT_RECIPES = new RecipeMap<>("chemical_plant",
            6, 4, 5, 4, (new SimpleRecipeBuilder()), false)
            .setProgressBar(GuiTextures.PROGRESS_BAR_BATH, ProgressWidget.MoveType.HORIZONTAL)
            .setSound(GTSoundEvents.CHEMICAL_REACTOR);

    public static final RecipeMap<SimpleRecipeBuilder> ELECTRIC_IMPLOSION_RECIPES = new RecipeMap<>("electric_implosion",
            2, 2, 0, 0, new SimpleRecipeBuilder().duration(1).EUt(GTValues.VA[10]), false)
            .setSound(SoundEvents.ENTITY_GENERIC_EXPLODE); // UEV-tier, 1tick processing time

    //TODO: implement recipe overlap with mixer map
    public static final RecipeMap<SimpleRecipeBuilder> ADVANCED_MIXER_RECIPES = new RecipeMap<>("large_mixer",
            9, 1, 6, 1, new SimpleRecipeBuilder(), false)
            .setProgressBar(GuiTextures.PROGRESS_BAR_MIXER, MoveType.CIRCULAR)
            .setSound(GTSoundEvents.MIXER);

    //TODO: implement recipe overlap with centrifuge map
    public static final RecipeMap<SimpleRecipeBuilder> ADVANCED_CENTRIFUGE_RECIPES = new RecipeMap<>("large_centrifuge",
            2, 6, 2, 6, new SimpleRecipeBuilder(), false)
            .setSlotOverlay(false, false, false, GuiTextures.EXTRACTOR_OVERLAY)
            .setSlotOverlay(false, false, true, GuiTextures.CANISTER_OVERLAY)
            .setSlotOverlay(false, true, true, GuiTextures.CENTRIFUGE_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_EXTRACT, MoveType.HORIZONTAL)
            .setSound(GTSoundEvents.CENTRIFUGE);


    //TODO: ?
    /*
    public static final RecipeMap<SimpleRecipeBuilder> ADVANCED_ENGRAVER_RECIPES = new RecipeMap<>("advanced_engraver",
            2, 2, 1, 1, new SimpleRecipeBuilder(), false)
            .setSlotOverlay(false, false, true, GuiTextures.LENS_OVERLAY)
            .setSound(GTSoundEvents.ELECTROLYZER);

     */

    public static final RecipeMap<FuelRecipeBuilder> HYPER_REACTOR_FUELS = new RecipeMap<>("hyper_reactor",
            0,0,1,0, new FuelRecipeBuilder(),false)
            .allowEmptyOutput()
            .setSound(GTSoundEvents.COOLING);

    public static final RecipeMap<FuelRecipeBuilder> NAQUADAH_REACTOR_FUELS = new RecipeMap<>("naquadah_reactor",
            0,0,1,0, new FuelRecipeBuilder(),false)
            .allowEmptyOutput()
            .setSound(GTSoundEvents.COOLING);

    public static final RecipeMap<FuelRecipeBuilder> ROCKET_FUEL_RECIPES = new RecipeMap<>("rocket_fuel",
            0,0,1,0, new FuelRecipeBuilder(),false)
            .allowEmptyOutput()
            .setSound(GTSoundEvents.COMBUSTION);


    public static final RecipeMap<DeepMinerBuilder> DEEP_MINER_RECIPES = new RecipeMap<>("deep_miner",
            6,12,3,3,new DeepMinerBuilder(),false)
            .setSound(GTSoundEvents.MINER);

    public static final RecipeMap<SimpleRecipeBuilder> GREENHOUSE_RECIPES = new RecipeMap<>("greenhouse",
            2,2,1,0,new SimpleRecipeBuilder(), false);




    public static void modifyMaps() {
        LASER_ENGRAVER_RECIPES.setMaxFluidInputs(1);
        LASER_ENGRAVER_RECIPES.setMaxFluidOutputs(1);

        BLAST_RECIPES.setMaxInputs(4);

        EXTRACTOR_RECIPES.setMaxInputs(2);

        GAS_CENTRIFUGE_RECIPES.setMaxFluidOutputs(3);
    }

}
