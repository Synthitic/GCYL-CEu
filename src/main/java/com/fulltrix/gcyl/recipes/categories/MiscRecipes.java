package com.fulltrix.gcyl.recipes.categories;

import gregtech.api.GTValues;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import static com.fulltrix.gcyl.item.GCYLCoreItems.COVER_ENDER_ITEM_LINK;
import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static com.fulltrix.gcyl.materials.GCYLNuclearMaterials.Americium241;
import static com.fulltrix.gcyl.recipes.GCYLRecipeMaps.ADVANCED_MIXER_RECIPES;
import static gregicality.multiblocks.api.unification.GCYMMaterials.Stellite100;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.Materials.Bismuth;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;
import static gregtech.common.items.MetaItems.COVER_ENDER_FLUID_LINK;

public class MiscRecipes {
    public static void init() {
        // Quantum Dust
        ADVANCED_MIXER_RECIPES.recipeBuilder().duration(10500).EUt(30)
                .input(dust, Stellite100, 15)
                .input(dust, Jasper, 5)
                .input(dust, Gallium, 5)
                .input(dust, Americium241, 5)
                .input(dust, Palladium, 5)
                .input(dust, Bismuth, 5)
                .input(dust, Germanium, 5)
                .input(dust,SiliconCarbide,5)
                .output(dust, Quantum, 50)
                .buildAndRegister();

        //Bright steel
        MIXER_RECIPES.recipeBuilder().duration(400).EUt(1920)
                .input(dust, Steel, 4)
                .input(dust, Bismuth, 2)
                .input(dust, Caesium, 2)
                .input(dust, Europium,1)
                .output(dust, BrightSteel, 9)
                .circuitMeta(9)
                .buildAndRegister();

        //Inconel 625 dust
        MIXER_RECIPES.recipeBuilder().duration(860).EUt(480)
                .input(dust, Nickel, 3)
                .input(dust, Chrome, 7)
                .input(dust, Molybdenum, 10)
                .input(dust, Invar, 10)
                .input(dust, Nichrome, 13)
                .circuitMeta(5)
                .output(dust, Inconel625, 43)
                .buildAndRegister();

        //eglin steel dust and base dust
        MIXER_RECIPES.recipeBuilder().duration(20 * 10).EUt(120)
                .input(dust, Iron, 4)
                .input(dust, Kanthal)
                .input(dust, Invar, 5)
                .circuitMeta(3)
                .output(dust, EglinSteelBase, 10)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(13 * 20).EUt(120)
                .input(dust, EglinSteelBase, 10)
                .input(dust, Sulfur)
                .input(dust, Silicon)
                .input(dust, Carbon)
                .output(dust, EglinSteel, 13)
                .circuitMeta(5)
                .buildAndRegister();

        //Babbitt alloy
        MIXER_RECIPES.recipeBuilder().duration(50 * 20).EUt(GTValues.VA[ZPM])
                .input(dust, Tin, 5)
                .input(dust, Lead, 36)
                .input(dust, Antimony)
                .input(dust, Arsenic)
                .circuitMeta(4)
                .output(dust, BabbittAlloy, 50)
                .buildAndRegister();

        //Ender Item Link
        ASSEMBLER_RECIPES.recipeBuilder()
                .input(plate, EnderPearl, 9)
                .input(plateDouble, StainlessSteel)
                .input(SENSOR_HV)
                .input(EMITTER_HV)
                .input(ELECTRIC_PISTON_HV)
                .fluidInputs(Polyethylene.getFluid(L * 2))
                .output(COVER_ENDER_ITEM_LINK)
                .EUt(VA[HV]).duration(320)
                .buildAndRegister();

        //Rock breaker recipes for netherrack and endstone
        ROCK_BREAKER_RECIPES.recipeBuilder()
                .notConsumable(new ItemStack(Blocks.NETHERRACK))
                .duration(16)
                .EUt(240)
                .dimension(-1)
                .outputs(new ItemStack(Blocks.NETHERRACK))
                .buildAndRegister();

        ROCK_BREAKER_RECIPES.recipeBuilder()
                .notConsumable(new ItemStack(Blocks.END_STONE))
                .duration(16)
                .EUt(960)
                .dimension(1)
                .outputs(new ItemStack(Blocks.END_STONE))
                .buildAndRegister();
    }

}
