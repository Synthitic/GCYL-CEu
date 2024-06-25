package com.fulltrix.gcyl.api.recipes;

//import com.fulltrix.tjfcore.recipes.impl.NuclearReactorBuilder;

import com.fulltrix.gcyl.api.recipes.builders.AdvFusionRecipeBuilder;
import com.fulltrix.gcyl.api.recipes.builders.ComponentALRecipeBuilder;
import com.fulltrix.gcyl.api.recipes.builders.DeepMinerBuilder;
import com.fulltrix.gcyl.api.recipes.builders.LaserMinerBuilder;
import com.fulltrix.gcyl.api.recipes.ui.ComponentALRecipeUI;
import gregtech.api.GTValues;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.gui.widgets.ProgressWidget.MoveType;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMapBuilder;
import gregtech.api.recipes.builders.ComputationRecipeBuilder;
import gregtech.api.recipes.builders.FuelRecipeBuilder;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.core.sound.GTSoundEvents;
import net.minecraft.init.SoundEvents;

import static gregtech.api.recipes.RecipeMaps.*;

public final class GCYLRecipeMaps {

    private GCYLRecipeMaps() {
    }

    public static final RecipeMap<SimpleRecipeBuilder> BIO_REACTOR_RECIPES = new RecipeMapBuilder<>("bio_reactor",
            new SimpleRecipeBuilder())
            .itemInputs(3)
            .itemOutputs(3)
            .fluidInputs(5)
            .fluidOutputs(2)
            .progressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE)
            .sound(GTSoundEvents.ELECTROLYZER)
            .build();

    public static final RecipeMap<SimpleRecipeBuilder> CHEMICAL_DEHYDRATOR_RECIPES = new RecipeMapBuilder<>("chemical_dehydrator",
            new SimpleRecipeBuilder())
            .itemInputs(2)
            .itemOutputs(9)
            .fluidInputs(2)
            .fluidOutputs(2)
            .progressBar(GuiTextures.PROGRESS_BAR_SIFT, MoveType.VERTICAL_INVERTED)
            .sound(SoundEvents.BLOCK_SAND_PLACE)
            .build();

    public static final RecipeMap<SimpleRecipeBuilder> DECAY_CHAMBERS_RECIPES = new RecipeMapBuilder<>("decay_chamber",
            new SimpleRecipeBuilder())
            .itemInputs(2)
            .itemOutputs(4)
            .fluidInputs(1)
            .fluidOutputs(1)
            .progressBar(GuiTextures.PROGRESS_BAR_HAMMER, ProgressWidget.MoveType.VERTICAL)
            .sound(GTSoundEvents.ARC)
            .build();

    public static final RecipeMap<AdvFusionRecipeBuilder> ADV_FUSION_RECIPES = new RecipeMapBuilder<>("adv_fusion",
            new AdvFusionRecipeBuilder())
            .fluidInputs(3)
            .fluidOutputs(3)
            .progressBar(GuiTextures.PROGRESS_BAR_FUSION)
            .sound(GTSoundEvents.ARC)
            .build();

    public static final RecipeMap<SimpleRecipeBuilder> STELLAR_FORGE_RECIPES = new RecipeMapBuilder<>("stellar_forge",
            new SimpleRecipeBuilder())
            .itemInputs(3)
            .itemOutputs(2)
            .fluidInputs(3)
            .fluidOutputs(2)
            .progressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE)
            .sound(SoundEvents.ENTITY_GENERIC_EXPLODE)
            .build();

    public static final RecipeMap<SimpleRecipeBuilder> PLASMA_CONDENSER_RECIPES = new RecipeMapBuilder<>("plasma_condenser",
            new SimpleRecipeBuilder())
            .itemInputs(2)
            .itemOutputs(2)
            .fluidInputs(2)
            .fluidOutputs(2)
            .progressBar(GuiTextures.PROGRESS_BAR_BATH, ProgressWidget.MoveType.HORIZONTAL)
            .sound(GTSoundEvents.COOLING)
            .build();

    public static final RecipeMap<SimpleRecipeBuilder> CHEMICAL_PLANT_RECIPES = new RecipeMapBuilder<>("chemical_plant",
            new SimpleRecipeBuilder())
            .itemInputs(6)
            .itemOutputs(4)
            .fluidInputs(5)
            .fluidOutputs(4)
            .progressBar(GuiTextures.PROGRESS_BAR_BATH, ProgressWidget.MoveType.HORIZONTAL)
            .sound(GTSoundEvents.CHEMICAL_REACTOR)
            .build();

    public static final RecipeMap<SimpleRecipeBuilder> ELECTRIC_IMPLOSION_RECIPES = new RecipeMapBuilder<>("electric_implosion",
            new SimpleRecipeBuilder().duration(1).EUt(GTValues.VA[GTValues.UEV]))
            .itemInputs(2)
            .itemOutputs(2)
            .sound(SoundEvents.ENTITY_GENERIC_EXPLODE)
            .build();

    //TODO: implement recipe overlap with mixer map
    public static final RecipeMap<SimpleRecipeBuilder> ADVANCED_MIXER_RECIPES = new RecipeMapBuilder<>("large_mixer",
            new SimpleRecipeBuilder())
            .itemInputs(9)
            .itemOutputs(1)
            .fluidInputs(6)
            .fluidOutputs(1)
            .progressBar(GuiTextures.PROGRESS_BAR_MIXER, MoveType.CIRCULAR)
            .sound(GTSoundEvents.MIXER)
            .build()
            .setSmallRecipeMap(MIXER_RECIPES);

    //TODO: implement recipe overlap with centrifuge map
    public static final RecipeMap<SimpleRecipeBuilder> ADVANCED_CENTRIFUGE_RECIPES = new RecipeMapBuilder<>("large_centrifuge",
            new SimpleRecipeBuilder())
            .itemInputs(2)
            .itemOutputs(6)
            .fluidInputs(2)
            .fluidOutputs(6)
            .itemSlotOverlay(GuiTextures.EXTRACTOR_OVERLAY, false, false)
            .itemSlotOverlay(GuiTextures.CANISTER_OVERLAY, false, true)
            .fluidSlotOverlay(GuiTextures.CENTRIFUGE_OVERLAY, false, true)
            .progressBar(GuiTextures.PROGRESS_BAR_EXTRACT, MoveType.HORIZONTAL)
            .sound(GTSoundEvents.CENTRIFUGE)
            .build()
            .setSmallRecipeMap(CENTRIFUGE_RECIPES);

    //TODO: ?
    /*
    public static final RecipeMap<SimpleRecipeBuilder> ADVANCED_ENGRAVER_RECIPES = new RecipeMap<>("advanced_engraver",
            2, 2, 1, 1, new SimpleRecipeBuilder(), false)
            .setSlotOverlay(false, false, true, GuiTextures.LENS_OVERLAY)
            .setSound(GTSoundEvents.ELECTROLYZER);

     */

    public static final RecipeMap<FuelRecipeBuilder> HYPER_REACTOR_FUELS = new RecipeMapBuilder<>("hyper_reactor",
            new FuelRecipeBuilder())
            .fluidInputs(1)
            .allowEmptyOutputs()
            .sound(GTSoundEvents.COOLING)
            .build();

    public static final RecipeMap<FuelRecipeBuilder> NAQUADAH_REACTOR_FUELS = new RecipeMapBuilder<>("naquadah_reactor",
            new FuelRecipeBuilder())
            .fluidInputs(1)
            .allowEmptyOutputs()
            .sound(GTSoundEvents.COOLING)
            .build();

    public static final RecipeMap<FuelRecipeBuilder> ROCKET_FUEL_RECIPES = new RecipeMapBuilder<>("rocket_fuel",
            new FuelRecipeBuilder())
            .fluidInputs(1)
            .allowEmptyOutputs()
            .sound(GTSoundEvents.COMBUSTION)
            .build();

    public static final RecipeMap<DeepMinerBuilder> DEEP_MINER_RECIPES = new RecipeMapBuilder<>("deep_miner",
            new DeepMinerBuilder())
            .itemInputs(6)
            .itemOutputs(12)
            .fluidInputs(3)
            .fluidOutputs(3)
            .sound(GTSoundEvents.MINER)
            .build();

    public static final RecipeMap<SimpleRecipeBuilder> GREENHOUSE_RECIPES = new RecipeMapBuilder<>("greenhouse",
            new SimpleRecipeBuilder())
            .itemInputs(3)
            .itemOutputs(3)
            .fluidInputs(2)
            .build();

    public static final RecipeMap<LaserMinerBuilder> LASER_MINER_RECIPES = new RecipeMapBuilder<>("laser_miner",
            new LaserMinerBuilder())
            .itemInputs(4)
            .itemOutputs(16)
            .build();

    public static final RecipeMap<ComputationRecipeBuilder> COSMIC_RAY_DETECTOR_RECIPES = new RecipeMapBuilder<>("cosmic_ray_detector",
            new ComputationRecipeBuilder())
            .itemInputs(1)
            .itemOutputs(9)
            .fluidOutputs(1)
            .sound(GTSoundEvents.ARC)
            .build();

    public static final RecipeMap<SimpleRecipeBuilder> FISHER_RECIPES = new RecipeMapBuilder<>("fisher",
            new SimpleRecipeBuilder())
            .itemInputs(2)
            .itemOutputs(6)
            .sound(SoundEvents.ENTITY_BOAT_PADDLE_WATER)
            .build();

    public static final RecipeMap<ComponentALRecipeBuilder> COMPONENT_AL_RECIPES = new RecipeMapComponentAL<>("component_al_recipes",
            new ComponentALRecipeBuilder(), ComponentALRecipeUI::new);



    public static void modifyMaps() {
        LASER_ENGRAVER_RECIPES.setMaxFluidInputs(1);
        LASER_ENGRAVER_RECIPES.setMaxFluidOutputs(1);

        BLAST_RECIPES.setMaxInputs(4);

        EXTRACTOR_RECIPES.setMaxInputs(2);

        GAS_CENTRIFUGE_RECIPES.setMaxFluidOutputs(3);

        CIRCUIT_ASSEMBLER_RECIPES.setMaxInputs(7);
    }

}
