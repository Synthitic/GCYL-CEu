package com.fulltrix.gcyl.recipes.categories;

import com.fulltrix.gcyl.item.GCYLExplosive;
import com.fulltrix.gcyl.item.GCYLMetaBlocks;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static com.fulltrix.gcyl.GCYLMaterials.*;
import static com.fulltrix.gcyl.item.GCYLCoreItems.*;
import static gregtech.api.GTValues.L;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.AUTOCLAVE_RECIPES;
import static gregtech.api.unification.material.MarkerMaterials.Color.Magenta;
import static gregtech.api.unification.material.MarkerMaterials.Tier.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.api.unification.ore.OrePrefix.wireGtDouble;
import static gregtech.common.items.MetaItems.GELLED_TOLUENE;
import static gregtech.common.items.MetaItems.GRAVI_STAR;

public class MetaItemRecipes {
    public static void init() {
        // Cooling Containers
        ASSEMBLER_RECIPES.recipeBuilder().duration(380).EUt(1150000)
                .input(plate, Steel, 32)
                .inputs(LASER_COOLING_UNIT.getStackForm())
                .inputs(MAGNETIC_TRAP.getStackForm())
                .fluidInputs(SolderingAlloy.getFluid(L))
                .outputs(EMPTY_LASER_COOLING_CONTAINER.getStackForm())
                .buildAndRegister();

        CANNER_RECIPES.recipeBuilder().duration(280).EUt(90000)
                .inputs(EMPTY_LASER_COOLING_CONTAINER.getStackForm())
                .fluidInputs(Rubidium.getFluid(L * 2))
                .outputs(BOSE_EINSTEIN_COOLING_CONTAINER.getStackForm())
                .buildAndRegister();

        // Laser Diode
        ASSEMBLER_RECIPES.recipeBuilder().duration(260).EUt(980000)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .inputs(SMD_DIODE_BIOWARE.getStackForm())
                .input(craftingLens, Magenta)
                .input(wireFine, Gold, 3)
                .outputs(LASER_DIODE.getStackForm())
                .buildAndRegister();

        // Laser Cooling Unit
        ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(1200000)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .input(wireFine, Gold, 4)
                .inputs(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.ALUMINIUM_FROSTPROOF))
                .inputs(LASER_DIODE.getStackForm())
                .input(circuit, HV)
                .outputs(LASER_COOLING_UNIT.getStackForm())
                .buildAndRegister();

        // Magnetic Trap
        ASSEMBLER_RECIPES.recipeBuilder().duration(480).EUt(1000000)
                .fluidInputs(SolderingAlloy.getFluid(L * 3))
                .input(wireGtDouble, UVSuperconductor, 2)
                .inputs(MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.ALUMINIUM_FROSTPROOF))
                .outputs(MAGNETIC_TRAP.getStackForm())
                .buildAndRegister();

        // Gravi Star
        AUTOCLAVE_RECIPES.recipeBuilder().duration(480).EUt(7680)
                .inputs(new ItemStack(Items.NETHER_STAR))
                .fluidInputs(Dubnium.getFluid(L * 2))
                .outputs(GRAVI_STAR.getStackForm())
                .buildAndRegister();

        // Unstable Star
        AUTOCLAVE_RECIPES.recipeBuilder().duration(480).EUt(122880)
                .inputs(GRAVI_STAR.getStackForm())
                .fluidInputs(Adamantium.getFluid(L * 2))
                .outputs(UNSTABLE_STAR.getStackForm())
                .buildAndRegister();

        // Mince Meat
        MACERATOR_RECIPES.recipeBuilder().duration(60).EUt(16)
                .inputs(new ItemStack(Items.PORKCHOP))
                .output(dustSmall, Meat, 6)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder().duration(60).EUt(16)
                .inputs(new ItemStack(Items.BEEF))
                .output(dustSmall, Meat, 6)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder().duration(60).EUt(16)
                .inputs(new ItemStack(Items.RABBIT))
                .output(dustSmall, Meat, 6)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder().duration(40).EUt(16)
                .inputs(new ItemStack(Items.CHICKEN))
                .output(dust, Meat)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder().duration(40).EUt(16)
                .inputs(new ItemStack(Items.MUTTON))
                .output(dust, Meat)
                .buildAndRegister();

        // Explosives
        CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(480)
                .inputs(GELLED_TOLUENE.getStackForm(4))
                .fluidInputs(NitrationMixture.getFluid(200))
                .outputs(GCYLMetaBlocks.EXPLOSIVE.getItemVariant(GCYLExplosive.ExplosiveType.ITNT))
                .fluidOutputs(DilutedSulfuricAcid.getFluid(150))
                .buildAndRegister();
    }
}
