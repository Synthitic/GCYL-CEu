package com.fulltrix.gcyl.recipes.categories;

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
