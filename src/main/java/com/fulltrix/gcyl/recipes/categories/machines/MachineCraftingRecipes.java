package com.fulltrix.gcyl.recipes.categories.machines;

import com.fulltrix.gcyl.machines.GCYLTileEntities;
import gregtech.api.GTValues;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.blocks.BlockMachineCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static com.fulltrix.gcyl.item.GCYLCoreItems.TOOL_DATA_MODULE_CLUSTER;
import static com.fulltrix.gcyl.item.GCYLCoreItems.UVA_HALIDE_LAMP;
import static com.fulltrix.gcyl.machines.GCYLTileEntities.STERILE_CLEANING_MAINTENANCE_HATCH;
import static gregicality.multiblocks.api.unification.GCYMMaterials.*;
import static gregtech.api.GTValues.L;
import static gregtech.api.GTValues.VA;
import static gregtech.api.recipes.GTRecipeHandler.removeRecipesByInputs;
import static gregtech.api.recipes.ModHandler.removeTieredRecipeByName;
import static gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES;
import static gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES;
import static gregtech.api.recipes.ingredients.IntCircuitIngredient.getIntegratedCircuit;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;
import static gregtech.common.metatileentities.MetaTileEntities.CLEANING_MAINTENANCE_HATCH;
import static gregtech.common.metatileentities.MetaTileEntities.HULL;

public class MachineCraftingRecipes {

    public static void init() {
        hullOverride();
        MultiblockCraftingRecipes.init();
        SingleblockCraftingRecipes.init();
        misc();
    }

    private static void hullOverride() { //TODO add OpV machine hull recipes

        removeTieredRecipeByName("gregtech:gregtech.machine.hull.", GTValues.ZPM, GTValues.MAX);
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ZPM), OreDictUnifier.get(cableGtSingle, VanadiumGallium, 2)}, new FluidStack[]{Polybenzimidazole.getFluid(L * 2)});
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(plate, NaquadahAlloy, 8), getIntegratedCircuit(8));
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UV), OreDictUnifier.get(cableGtSingle, YttriumBariumCuprate, 2)}, new FluidStack[]{Polybenzimidazole.getFluid(L * 2)});
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(plate, Darmstadtium, 8), getIntegratedCircuit(8));
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UHV), OreDictUnifier.get(cableGtSingle, Europium, 2)}, new FluidStack[]{Polybenzimidazole.getFluid(L * 2)});
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(plate, Neutronium, 8), getIntegratedCircuit(8));
        //ZPM - UV HULLS
        //ModHandler.addShapedRecipe("gcyl_hull_zpm", HULL[7].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ZPM), 'C', new UnificationEntry(cableGtSingle, Naquadah), 'H', new UnificationEntry(plate, Duranium), 'P', new UnificationEntry(plate, Polybenzimidazole));
        //ModHandler.addShapedRecipe("gcyl_hull_zpm", HULL[8].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UV), 'C', new UnificationEntry(cableGtSingle, NaquadahAlloy), 'H', new UnificationEntry(plate, Tritanium), 'P', new UnificationEntry(plate, Polybenzimidazole));

        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ZPM)).input(cableGtSingle, Naquadah, 2).fluidInputs(Polybenzimidazole.getFluid(L * 2)).outputs(HULL[7].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UV)).input(cableGtSingle, NaquadahAlloy, 2).fluidInputs(Polybenzimidazole.getFluid(L * 2)).outputs(HULL[8].getStackForm()).buildAndRegister();

        //ZPM - UV MACHINE CASING
       // ModHandler.addShapedRecipe("gcyl_casing_uhv", MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ZPM), "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, Duranium));
        //ModHandler.addShapedRecipe("gcyl_casing_uhv", MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UV), "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, Tritanium));

        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).circuitMeta(8).input(plate, Duranium, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ZPM)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).circuitMeta(8).input(plate, Tritanium, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UV)).buildAndRegister();

        // UHV+ Hulls

       // ModHandler.addShapedRecipe("gcyl_hull_uhv", HULL[9].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UHV), 'C', new UnificationEntry(cableGtSingle, TungstenTitaniumCarbide), 'H', new UnificationEntry(plate, Seaborgium), 'P', new UnificationEntry(plate, Polyetheretherketone));
       // ModHandler.addShapedRecipe("gcyl_hull_uev", HULL[10].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UEV), 'C', new UnificationEntry(cableGtQuadruple, Pikyonium), 'H', new UnificationEntry(plate, Bohrium), 'P', new UnificationEntry(plate, Polyetheretherketone));
      //  ModHandler.addShapedRecipe("gcyl_hull_uiv", HULL[11].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UIV), 'C', new UnificationEntry(cableGtQuadruple, Cinobite), 'H', new UnificationEntry(plate, Quantum), 'P', new UnificationEntry(plate, Zylon));
       // ModHandler.addShapedRecipe("gcyl_hull_uxv", HULL[12].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UXV), 'C', new UnificationEntry(cableGtQuadruple, NaquadriaticTaranium), 'H', new UnificationEntry(plate, BlackTitanium), 'P', new UnificationEntry(plate, FullerenePolymerMatrix));

        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UHV)).input(cableGtSingle, TungstenTitaniumCarbide, 2).fluidInputs(Polyetheretherketone.getFluid(L * 2)).outputs(HULL[9].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UEV)).input(cableGtQuadruple, Pikyonium, 2).fluidInputs(Polyetheretherketone.getFluid(L * 2)).outputs(HULL[10].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UIV)).input(cableGtQuadruple, Cinobite, 2).fluidInputs(Zylon.getFluid(L * 2)).outputs(HULL[11].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UXV)).input(cableGtQuadruple, NaquadriaticTaranium, 2).fluidInputs(Zylon.getFluid(L * 2)).outputs(HULL[12].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UXV)).input(cableGtQuadruple, Neutronium, 2).fluidInputs(FullerenePolymerMatrix.getFluid(L * 2)).outputs(HULL[13].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UXV)).input(cableGtQuadruple, CosmicNeutronium, 2).fluidInputs(FullerenePolymerMatrix.getFluid(L * 2)).outputs(HULL[14].getStackForm()).buildAndRegister();

        removeTieredRecipeByName("gregtech:casing_", GTValues.ZPM, GTValues.MAX);
        // UHV+ Casings
     //   ModHandler.addShapedRecipe("gcyl_casing_uhv", MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UHV), "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, Seaborgium));
      //  ModHandler.addShapedRecipe("gcyl_casing_uev", MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UEV), "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, Bohrium));
      //  ModHandler.addShapedRecipe("gcyl_casing_uiv", MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UIV), "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, Quantum));
     //   ModHandler.addShapedRecipe("gcyl_casing_uxv", MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UXV), "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, BlackTitanium));
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).circuitMeta(8).input(plate, Seaborgium, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UHV)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).circuitMeta(8).input(plate, Bohrium, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UEV)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).circuitMeta(8).input(plate, Quantum, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UIV)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).circuitMeta(8).input(plate, BlackTitanium, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UXV)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).circuitMeta(8).input(plate, HeavyQuarkDegenerateMatter, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.OpV)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).circuitMeta(8).input(plate, Neutronium, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MAX)).buildAndRegister();

    }

    /*
    public static void init() {
        MultiblockCraftingRecipes.init();

        hullOverride();
        misc();
    }

    private static void hullOverride() {


        // Hull Overrides
        removeTieredRecipeByName("gregtech:hull_", ULV, GTValues.MAX);


            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ULV), OreDictUnifier.get(cableGtSingle, Lead, 2)}, new FluidStack[]{Polyethylene.getFluid(L * 2)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.LV), OreDictUnifier.get(cableGtSingle, Tin, 2)}, new FluidStack[]{Polyethylene.getFluid(L * 2)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MV), OreDictUnifier.get(cableGtSingle, Copper, 2)}, new FluidStack[]{Polyethylene.getFluid(L * 2)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MV), OreDictUnifier.get(cableGtSingle, AnnealedCopper, 2)}, new FluidStack[]{Polyethylene.getFluid(L * 2)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.HV), OreDictUnifier.get(cableGtSingle, Gold, 2)}, new FluidStack[]{Polyethylene.getFluid(L * 2)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.EV), OreDictUnifier.get(cableGtSingle, Aluminium, 2)}, new FluidStack[]{Polyethylene.getFluid(L * 2)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.IV), OreDictUnifier.get(cableGtSingle, Tungsten, 2)}, new FluidStack[]{Polyethylene.getFluid(L * 2)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.LuV), OreDictUnifier.get(cableGtSingle, VanadiumGallium, 2)}, new FluidStack[]{Polyethylene.getFluid(L * 2)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ZPM), OreDictUnifier.get(cableGtSingle, Naquadah, 2)}, new FluidStack[]{Polytetrafluoroethylene.getFluid(L * 2)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UV), OreDictUnifier.get(cableGtQuadruple, NaquadahAlloy, 2)}, new FluidStack[]{Polytetrafluoroethylene.getFluid(L * 2)});
            removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MAX), OreDictUnifier.get(cableGtSingle, Superconductor, 2)}, new FluidStack[]{Polytetrafluoroethylene.getFluid(L * 2)});


        ModHandler.addShapedRecipe("ga_hull_ulv", MetaTileEntities.HULL[ULV].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ULV), 'C', new UnificationEntry(cableGtSingle, Lead), 'H', new UnificationEntry(plate, WroughtIron), 'P', new UnificationEntry(plate, Wood));
        ModHandler.addShapedRecipe("ga_hull_lv", MetaTileEntities.HULL[LV].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.LV), 'C', new UnificationEntry(cableGtSingle, Tin), 'H', new UnificationEntry(plate, Steel), 'P', new UnificationEntry(plate, WroughtIron));
        ModHandler.addShapedRecipe("ga_hull_mv", MetaTileEntities.HULL[MV].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MV), 'C', new UnificationEntry(cableGtSingle, Copper), 'H', new UnificationEntry(plate, Aluminium), 'P', new UnificationEntry(plate, WroughtIron));
        ModHandler.addShapedRecipe("ga_hull_hv", MetaTileEntities.HULL[HV].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.HV), 'C', new UnificationEntry(cableGtSingle, Gold), 'H', new UnificationEntry(plate, StainlessSteel), 'P', new UnificationEntry(plate, Plastic));
        ModHandler.addShapedRecipe("ga_hull_ev", MetaTileEntities.HULL[EV].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.EV), 'C', new UnificationEntry(cableGtSingle, Aluminium), 'H', new UnificationEntry(plate, Titanium), 'P', new UnificationEntry(plate, Plastic));
        ModHandler.addShapedRecipe("ga_hull_iv", MetaTileEntities.HULL[IV].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.IV), 'C', new UnificationEntry(cableGtSingle, Tungsten), 'H', new UnificationEntry(plate, TungstenSteel), 'P', new UnificationEntry(plate, Polytetrafluoroethylene));
        ModHandler.addShapedRecipe("ga_hull_luv", MetaTileEntities.HULL[LuV].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.LuV), 'C', new UnificationEntry(cableGtSingle, VanadiumGallium), 'H', new UnificationEntry(plate, RhodiumPlatedPalladium), 'P', new UnificationEntry(plate, Polytetrafluoroethylene));
        ModHandler.addShapedRecipe("ga_hull_zpm", MetaTileEntities.HULL[GTValues.ZPM].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ZPM), 'C', new UnificationEntry(cableGtSingle, Naquadah), 'H', new UnificationEntry(plate, Osmiridium), 'P', new UnificationEntry(plate, Polybenzimidazole));
        ModHandler.addShapedRecipe("ga_hull_uv", MetaTileEntities.HULL[UV].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UV), 'C', new UnificationEntry(cableGtSingle, NaquadahAlloy), 'H', new UnificationEntry(plate, Tritanium), 'P', new UnificationEntry(plate, Polybenzimidazole));

        ASSEMBLER_RECIPES.recipeBuilder().duration(25).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ULV)).input(cableGtSingle, Lead, 2).fluidInputs(Plastic.getFluid(L * 2)).outputs(MetaTileEntities.HULL[0].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.LV)).input(cableGtSingle, Tin, 2).fluidInputs(Plastic.getFluid(L * 2)).outputs(MetaTileEntities.HULL[1].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MV)).input(cableGtSingle, Copper, 2).fluidInputs(Plastic.getFluid(L * 2)).outputs(MetaTileEntities.HULL[2].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MV)).input(cableGtSingle, AnnealedCopper, 2).fluidInputs(Plastic.getFluid(L * 2)).outputs(MetaTileEntities.HULL[2].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.HV)).input(cableGtSingle, Gold, 2).fluidInputs(Plastic.getFluid(L * 2)).outputs(MetaTileEntities.HULL[3].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.EV)).input(cableGtSingle, Aluminium, 2).fluidInputs(Plastic.getFluid(L * 2)).outputs(MetaTileEntities.HULL[4].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.IV)).input(cableGtSingle, Tungsten, 2).fluidInputs(Polytetrafluoroethylene.getFluid(L * 2)).outputs(MetaTileEntities.HULL[5].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.LuV)).input(cableGtSingle, VanadiumGallium, 2).fluidInputs(Polytetrafluoroethylene.getFluid(L * 2)).outputs(MetaTileEntities.HULL[6].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ZPM)).input(cableGtSingle, Naquadah, 2).fluidInputs(Polybenzimidazole.getFluid(L * 2)).outputs(MetaTileEntities.HULL[7].getStackForm()).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UV)).input(cableGtSingle, NaquadahAlloy, 2).fluidInputs(Polybenzimidazole.getFluid(L * 2)).outputs(MetaTileEntities.HULL[8].getStackForm()).buildAndRegister();




        // Casing Overrides
        // Metals changed from base GTCE
        removeTieredRecipeByName("gregtech:casing_", LuV, GTValues.MAX);
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(plate, Chrome, 8), getIntegratedCircuit(8));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(plate, Iridium, 8), getIntegratedCircuit(8));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(plate, Osmium, 8), getIntegratedCircuit(8));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(plate, Darmstadtium, 8), getIntegratedCircuit(8)); // MAX doesn't have a recipe yet

        ModHandler.addShapedRecipe("ga_casing_luv", MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.LuV), "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, RhodiumPlatedPalladium));
        ModHandler.addShapedRecipe("ga_casing_zpm", MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ZPM), "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, Osmiridium));
        ModHandler.addShapedRecipe("ga_casing_uv", MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UV), "PPP", "PwP", "PPP", 'P', new UnificationEntry(plate, Tritanium));
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).circuitMeta(8).input(plate, RhodiumPlatedPalladium, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.LuV)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).circuitMeta(8).input(plate, Osmiridium, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ZPM)).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(50).circuitMeta(8).input(plate, Tritanium, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UV)).buildAndRegister();



    }

*/
    private static void misc() {
        ModHandler.addShapedRecipe("gcyl_volcanus", GCYLTileEntities.VOLCANUS.getStackForm(), "GCG", "IHI", "PCP", 'H', MetaTileEntities.ELECTRIC_BLAST_FURNACE.getStackForm(), 'C', new UnificationEntry(circuit, MarkerMaterials.Tier.LuV), 'P', new UnificationEntry(plateDense, HastelloyN), 'G', new UnificationEntry(gear, HastelloyN), 'I', MetaItems.ROBOT_ARM_IV);
        ModHandler.addShapedRecipe("gcyl_cryogenic_freezer", GCYLTileEntities.CRYOGENIC_FREEZER.getStackForm(), "GCG", "IHI", "PCP", 'H', MetaTileEntities.VACUUM_FREEZER.getStackForm(), 'C', new UnificationEntry(circuit, MarkerMaterials.Tier.LuV), 'P', new UnificationEntry(plateDense, HG1223), 'G', new UnificationEntry(gear, IncoloyMA956), 'I', MetaItems.ELECTRIC_PISTON_IV);

/*
        // Fluid Export Hatch
        ModHandler.addShapedRecipe("ga_filtered_fluid_export_hatch_ulv", GATileEntities.OUTPUT_HATCH_FILTERED.get(GAValues.ULV).getStackForm(), "F", "M", "G", 'M', MetaTileEntities.HULL[ULV].getStackForm(), 'G', Blocks.GLASS, 'F', MetaItems.FLUID_FILTER);
        ModHandler.addShapedRecipe("ga_filtered_fluid_export_hatch_lv", GATileEntities.OUTPUT_HATCH_FILTERED.get(GAValues.LV).getStackForm(), "F", "M", "G", 'M', MetaTileEntities.HULL[LV].getStackForm(), 'G', Blocks.GLASS, 'F', MetaItems.FLUID_FILTER);
        ModHandler.addShapedRecipe("ga_filtered_fluid_export_hatch_mv", GATileEntities.OUTPUT_HATCH_FILTERED.get(GAValues.MV).getStackForm(), "F", "M", "G", 'M', MetaTileEntities.HULL[GAValues.MV].getStackForm(), 'G', Blocks.GLASS, 'F', MetaItems.FLUID_FILTER);
        ModHandler.addShapedRecipe("ga_filtered_fluid_export_hatch_hv", GATileEntities.OUTPUT_HATCH_FILTERED.get(GAValues.HV).getStackForm(), "F", "M", "G", 'M', MetaTileEntities.HULL[GAValues.HV].getStackForm(), 'G', Blocks.GLASS, 'F', MetaItems.FLUID_FILTER);
        ModHandler.addShapedRecipe("ga_filtered_fluid_export_hatch_ev", GATileEntities.OUTPUT_HATCH_FILTERED.get(GAValues.EV).getStackForm(), "F", "M", "G", 'M', MetaTileEntities.HULL[GAValues.EV].getStackForm(), 'G', Blocks.GLASS, 'F', MetaItems.FLUID_FILTER);
        ModHandler.addShapedRecipe("ga_filtered_fluid_export_hatch_iv", GATileEntities.OUTPUT_HATCH_FILTERED.get(GAValues.IV).getStackForm(), "F", "M", "G", 'M', MetaTileEntities.HULL[GAValues.IV].getStackForm(), 'G', Blocks.GLASS, 'F', MetaItems.FLUID_FILTER);
        ModHandler.addShapedRecipe("ga_filtered_fluid_export_hatch_luv", GATileEntities.OUTPUT_HATCH_FILTERED.get(GAValues.LuV).getStackForm(), "F", "M", "G", 'M', MetaTileEntities.HULL[GAValues.LuV].getStackForm(), 'G', Blocks.GLASS, 'F', MetaItems.FLUID_FILTER);
        ModHandler.addShapedRecipe("ga_filtered_fluid_export_hatch_zpm", GATileEntities.OUTPUT_HATCH_FILTERED.get(GAValues.ZPM).getStackForm(), "F", "M", "G", 'M', MetaTileEntities.HULL[GAValues.ZPM].getStackForm(), 'G', Blocks.GLASS, 'F', MetaItems.FLUID_FILTER);
        ModHandler.addShapedRecipe("ga_filtered_fluid_export_hatch_uv", GATileEntities.OUTPUT_HATCH_FILTERED.get(GAValues.UV).getStackForm(), "F", "M", "G", 'M', MetaTileEntities.HULL[GAValues.UV].getStackForm(), 'G', Blocks.GLASS, 'F', MetaItems.FLUID_FILTER);
        ModHandler.addShapedRecipe("ga_filtered_fluid_export_hatch_max", GATileEntities.OUTPUT_HATCH_FILTERED.get(9).getStackForm(), "F", "M", "G", 'M', MetaTileEntities.HULL[GTValues.MAX].getStackForm(), 'G', Blocks.GLASS, 'F', MetaItems.FLUID_FILTER);

        OrePrefix plateOrCurved = GAConfig.GT6.addCurvedPlates ? plateCurved : plate;
        // Drums
        ModHandler.addShapedRecipe("wooden_barrel", GATileEntities.WOODEN_DRUM.getStackForm(), "rSs", "PRP", "PRP", 'S', Items.SLIME_BALL, 'P', "plankWood", 'R', new UnificationEntry(stickLong, Iron));
        ASSEMBLER_RECIPES.recipeBuilder().EUt(30).duration(200).inputs(new ItemStack(Blocks.PLANKS, 4, GTValues.W)).inputs(new ItemStack(Items.SLIME_BALL)).input(stickLong, Iron, 2).outputs(GATileEntities.WOODEN_DRUM.getStackForm()).circuitMeta(1).buildAndRegister();

        ModHandler.addShapedRecipe("bronze_drum", GATileEntities.BRONZE_DRUM.getStackForm(), " h ", "PRP", "PRP", 'P', new UnificationEntry(plateOrCurved, Bronze), 'R', new UnificationEntry(stickLong, Bronze));
        ModHandler.addShapedRecipe("steel_drum", GATileEntities.STEEL_DRUM.getStackForm(), " h ", "PRP", "PRP", 'P', new UnificationEntry(plateOrCurved, Steel), 'R', new UnificationEntry(stickLong, Steel));
        ModHandler.addShapedRecipe("stainless_steel_drum", GATileEntities.STAINLESS_STEEL_DRUM.getStackForm(), " h ", "PRP", "PRP", 'P', new UnificationEntry(plateOrCurved, StainlessSteel), 'R', new UnificationEntry(stickLong, StainlessSteel));
        ModHandler.addShapedRecipe("titanium_drum", GATileEntities.TITANIUM_DRUM.getStackForm(), " h ", "PRP", "PRP", 'P', new UnificationEntry(plateOrCurved, Titanium), 'R', new UnificationEntry(stickLong, Titanium));
        ModHandler.addShapedRecipe("tungstensteel_drum", GATileEntities.TUNGSTENSTEEL_DRUM.getStackForm(), " h ", "PRP", "PRP", 'P', new UnificationEntry(plateOrCurved, TungstenSteel), 'R', new UnificationEntry(stickLong, TungstenSteel));
        ASSEMBLER_RECIPES.recipeBuilder().EUt(30).duration(200).input(plateOrCurved, Bronze, 4).input(stickLong, Bronze, 2).outputs(GATileEntities.BRONZE_DRUM.getStackForm()).circuitMeta(1).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(30).duration(200).input(plateOrCurved, Steel, 4).input(stickLong, Steel, 2).outputs(GATileEntities.STEEL_DRUM.getStackForm()).circuitMeta(1).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(30).duration(200).input(plateOrCurved, StainlessSteel, 4).input(stickLong, StainlessSteel, 2).outputs(GATileEntities.STAINLESS_STEEL_DRUM.getStackForm()).circuitMeta(1).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(30).duration(200).input(plateOrCurved, Titanium, 4).input(stickLong, Titanium, 2).outputs(GATileEntities.TITANIUM_DRUM.getStackForm()).circuitMeta(1).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(30).duration(200).input(plateOrCurved, TungstenSteel, 4).input(stickLong, TungstenSteel, 2).outputs(GATileEntities.TUNGSTENSTEEL_DRUM.getStackForm()).circuitMeta(1).buildAndRegister();

        // Crates
        ModHandler.addShapedRecipe("wooden_crate", GATileEntities.WOODEN_CRATE.getStackForm(), "RPR", "PsP", "RPR", 'P', "plankWood", 'R', new UnificationEntry(screw, Iron));
        ASSEMBLER_RECIPES.recipeBuilder().EUt(30).duration(200).inputs(new ItemStack(Blocks.PLANKS, 4, GTValues.W)).input(screw, Iron, 4).outputs(GATileEntities.WOODEN_CRATE.getStackForm()).circuitMeta(2).buildAndRegister();

        ModHandler.addShapedRecipe("bronze_crate", GATileEntities.BRONZE_CRATE.getStackForm(), "RPR", "PhP", "RPR", 'P', new UnificationEntry(plateOrCurved, Bronze), 'R', new UnificationEntry(stickLong, Bronze));
        ModHandler.addShapedRecipe("steel_crate", GATileEntities.STEEL_CRATE.getStackForm(), "RPR", "PhP", "RPR", 'P', new UnificationEntry(plateOrCurved, Steel), 'R', new UnificationEntry(stickLong, Steel));
        ModHandler.addShapedRecipe("stainless_steel_crate", GATileEntities.STAINLESS_STEEL_CRATE.getStackForm(), "RPR", "PhP", "RPR", 'P', new UnificationEntry(plateOrCurved, StainlessSteel), 'R', new UnificationEntry(stickLong, StainlessSteel));
        ModHandler.addShapedRecipe("titanium_crate", GATileEntities.TITANIUM_CRATE.getStackForm(), "RPR", "PhP", "RPR", 'P', new UnificationEntry(plateOrCurved, Titanium), 'R', new UnificationEntry(stickLong, Titanium));
        ModHandler.addShapedRecipe("tungstensteel_crate", GATileEntities.TUNGSTENSTEEL_CRATE.getStackForm(), "RPR", "PhP", "RPR", 'P', new UnificationEntry(plateOrCurved, TungstenSteel), 'R', new UnificationEntry(stickLong, TungstenSteel));
        ASSEMBLER_RECIPES.recipeBuilder().EUt(30).duration(200).input(plateOrCurved, Bronze, 4).input(stickLong, Bronze, 4).outputs(GATileEntities.BRONZE_CRATE.getStackForm()).circuitMeta(2).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(30).duration(200).input(plateOrCurved, Steel, 4).input(stickLong, Steel, 4).outputs(GATileEntities.STEEL_CRATE.getStackForm()).circuitMeta(2).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(30).duration(200).input(plateOrCurved, StainlessSteel, 4).input(stickLong, StainlessSteel, 4).outputs(GATileEntities.STAINLESS_STEEL_CRATE.getStackForm()).circuitMeta(2).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(30).duration(200).input(plateOrCurved, Titanium, 4).input(stickLong, Titanium, 4).outputs(GATileEntities.TITANIUM_CRATE.getStackForm()).circuitMeta(2).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(30).duration(200).input(plateOrCurved, TungstenSteel, 4).input(stickLong, TungstenSteel, 4).outputs(GATileEntities.TUNGSTENSTEEL_CRATE.getStackForm()).circuitMeta(2).buildAndRegister();

        // Hot Coolant Rotor Holders
        ModHandler.addShapedRecipe("ga_rotor_holder_hv", GATileEntities.ROTOR_HOLDER[0].getStackForm(), "WHW", "WRW", "WWW", 'H', MetaTileEntities.HULL[HV].getStackForm(), 'W', new UnificationEntry(wireGtHex, Gold), 'R', new UnificationEntry(gear, BlackSteel));
        ModHandler.addShapedRecipe("ga_rotor_holder_luv", GATileEntities.ROTOR_HOLDER[1].getStackForm(), "WHW", "WRW", "WWW", 'H', MetaTileEntities.HULL[LuV].getStackForm(), 'W', new UnificationEntry(wireGtHex, YttriumBariumCuprate), 'R', new UnificationEntry(gear, RhodiumPlatedPalladium));
        ModHandler.addShapedRecipe("ga_rotor_holder_uhv", GATileEntities.ROTOR_HOLDER[2].getStackForm(), "WHW", "WRW", "WWW", 'H', GATileEntities.GA_HULLS[0].getStackForm(), 'W', new UnificationEntry(wireGtHex, Duranium), 'R', new UnificationEntry(gear, Seaborgium));


       */

        //STERILE FILTRATION MAINTENANCE HATCH
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .outputs(STERILE_CLEANING_MAINTENANCE_HATCH.getStackForm())
                .inputs(CLEANING_MAINTENANCE_HATCH.getStackForm())
                .inputs(ROBOT_ARM_UIV.getStackForm(4))
                .input(circuit, MarkerMaterials.Tier.UIV, 6)
                .inputs(HULL[GTValues.UIV].getStackForm())
                .inputs(EMITTER_UIV.getStackForm(2))
                .inputs(UVA_HALIDE_LAMP.getStackForm(4))
                .fluidInputs(Indalloy140.getFluid(L * 8))
                .fluidInputs(Lubricant.getFluid(L * 4))
                .stationResearch(b -> b
                        .researchStack(CLEANING_MAINTENANCE_HATCH.getStackForm())
                        .CWUt(512)
                        .dataStack(TOOL_DATA_MODULE_CLUSTER.getStackForm())
                        .EUt(VA[GTValues.UIV]))
                .duration(300).EUt(GTValues.VA[GTValues.UIV])
                .buildAndRegister();

    }


}
