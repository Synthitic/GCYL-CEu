package com.fulltrix.gcyl.jei;


import com.fulltrix.gcyl.GCYLConfig;
import com.fulltrix.gcyl.Tags;
import com.fulltrix.gcyl.api.worldgen.VirtualOreDepositDefinition;
import com.fulltrix.gcyl.api.worldgen.WorldGenRegister;
import com.fulltrix.gcyl.jei.category.SpaceMiningCategory;
import com.fulltrix.gcyl.jei.category.SpacePumpCategory;
import com.fulltrix.gcyl.jei.category.VirtualOresCategory;
import com.fulltrix.gcyl.jei.category.VoidMinerCategory;
import com.fulltrix.gcyl.machines.GCYLTileEntities;
import com.fulltrix.gcyl.machines.multi.multiblockpart.MetaTileEntityWirelessEnergyHatch;
import com.fulltrix.gcyl.recipes.categories.elevator.SpaceMiningRecipes;
import gregicality.multiblocks.common.metatileentities.GCYMMetaTileEntities;
import gregtech.api.GTValues;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.category.GTRecipeCategory;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;
import mezz.jei.api.*;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.fulltrix.gcyl.machines.GCYLTileEntities.MINING_MODULE;
import static com.fulltrix.gcyl.machines.GCYLTileEntities.WIRELESS_ENERGY_HATCH_OUTPUT;
import static com.fulltrix.gcyl.recipes.categories.elevator.SpaceMiningRecipes.HASH_TO_ITEMS;
import static com.fulltrix.gcyl.recipes.categories.elevator.SpaceMiningRecipes.SPACE_MINING_RECIPES;
import static com.fulltrix.gcyl.recipes.categories.elevator.SpacePumpRecipes.GAS_SIPHON_RECIPES;
import static com.fulltrix.gcyl.recipes.categories.elevator.SpacePumpRecipes.getPlanetNameByID;
import static com.fulltrix.gcyl.recipes.categories.handlers.VoidMinerHandler.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.common.blocks.BlockWireCoil.CoilType.*;
import static gregtech.common.items.MetaItems.*;
import static gregtech.common.metatileentities.MetaTileEntities.CIRCUIT_ASSEMBLER;


@JEIPlugin
public class JEIGCYLPlugin implements IModPlugin {
    private IIngredientBlacklist itemBlacklist;
    private IIngredientRegistry iItemRegistry;
    public static IJeiRuntime jeiRuntime;
    public static IGuiHelper guiHelper;

    @Override
    public void registerCategories(@NotNull IRecipeCategoryRegistration registry) {
        guiHelper = registry.getJeiHelpers().getGuiHelper();

        registry.addRecipeCategories(new SpaceMiningCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new SpacePumpCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new VoidMinerCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new VirtualOresCategory(registry.getJeiHelpers().getGuiHelper()));

        @Nullable GTRecipeCategory category = GTRecipeCategory.getByName(RecipeMaps.BLAST_RECIPES.unlocalizedName);
        if (category != null) {
            category.jeiIcon(MetaTileEntities.ELECTRIC_BLAST_FURNACE.getStackForm());
        }
    }


    @Override
    public void register(IModRegistry registry) {
        itemBlacklist = registry.getJeiHelpers().getIngredientBlacklist();
        iItemRegistry = registry.getIngredientRegistry();

        //VIRTUAL ORES
        List<VirtualOreDepositDefinition> virtualVeins = WorldGenRegister.getVirtualOreDepositDefinitions();
        List<VirtualOresInfo> virtualOresInfos = new ArrayList<>();
        for(VirtualOreDepositDefinition definition : virtualVeins) {
            virtualOresInfos.add(new VirtualOresInfo(definition));
        }

        String virtualVeinSpawnID = Tags.MODID + ":" + "virtual_ores";
        registry.addRecipes(virtualOresInfos, virtualVeinSpawnID);
        registry.addRecipeCatalyst(MetaItems.PROSPECTOR_LV.getStackForm(), virtualVeinSpawnID);
        registry.addRecipeCatalyst(MetaItems.PROSPECTOR_HV.getStackForm(), virtualVeinSpawnID);
        registry.addRecipeCatalyst(MetaItems.PROSPECTOR_LUV.getStackForm(), virtualVeinSpawnID);



        //SPACE MINING
        String spaceMineID = Tags.MODID + ":" + "space_mining";
        List<SpaceMiningInfo> spaceMiningInfo1 = new ArrayList<>();
        List<SpaceMiningInfo> spaceMiningInfo2 = new ArrayList<>();
        List<SpaceMiningInfo> spaceMiningInfo3 = new ArrayList<>();

        for(Map.Entry<Integer, List<SpaceMiningRecipes.SpaceMiningRecipePartTwo>> entry : SPACE_MINING_RECIPES.entrySet()) {
            Integer key = entry.getKey();
            List<SpaceMiningRecipes.SpaceMiningRecipePartTwo> recipe = entry.getValue();

            List<ItemStack> inputs = HASH_TO_ITEMS.get(key);

            for(SpaceMiningRecipes.SpaceMiningRecipePartTwo recipeIterate : recipe) {
                spaceMiningInfo1.add(new SpaceMiningInfo(inputs, recipeIterate));

                /*
                if(recipeIterate.getMinModuleTier() > 2) {
                    spaceMiningInfo3.add(new SpaceMiningInfo(inputs, recipeIterate));
                } else if(recipeIterate.getMinModuleTier() > 1) {
                    spaceMiningInfo3.add(new SpaceMiningInfo(inputs, recipeIterate));
                    spaceMiningInfo2.add(new SpaceMiningInfo(inputs, recipeIterate));
                } else {
                    spaceMiningInfo3.add(new SpaceMiningInfo(inputs, recipeIterate));
                    spaceMiningInfo2.add(new SpaceMiningInfo(inputs, recipeIterate));
                    spaceMiningInfo1.add(new SpaceMiningInfo(inputs, recipeIterate));
                }

                 */
            }
        }

        registry.addRecipes(spaceMiningInfo1, spaceMineID);
        //registry.addRecipes(spaceMiningInfo2, spaceMineID);
        //registry.addRecipes(spaceMiningInfo3, spaceMineID);
        registry.addRecipeCatalyst(MINING_MODULE[0].getStackForm(), spaceMineID);
        registry.addRecipeCatalyst(MINING_MODULE[1].getStackForm(), spaceMineID);
        registry.addRecipeCatalyst(MINING_MODULE[2].getStackForm(), spaceMineID);



        List<SpacePumpInfo> spacePumpInfos = new ArrayList<>();
        for(Map.Entry<String, FluidStack> entry : GAS_SIPHON_RECIPES.entrySet()) {
            String key = entry.getKey();
            FluidStack value = entry.getValue();

            int planetID = 0;
            int fluidID = 0;

            int index = key.indexOf(',');
            char[] arr = key.toCharArray();

            StringBuilder stringBuilderPlanet = new StringBuilder();
            StringBuilder stringBuilderFluid = new StringBuilder();

            for(int i = 0; i < arr.length; i++) {
                if(i < index) {
                    stringBuilderPlanet.append(arr[i]);
                }

                if(i > index) {
                    stringBuilderFluid.append(arr[i]);
                }
            }

            planetID = Integer.parseInt(stringBuilderPlanet.toString());
            fluidID = Integer.parseInt(stringBuilderFluid.toString());

            spacePumpInfos.add(new SpacePumpInfo(getPlanetNameByID(planetID), planetID, fluidID, value));
        }


        String spacePumpID = Tags.MODID + ":" + "space_pump_fluids";
        registry.addRecipes(spacePumpInfos, spacePumpID);
        registry.addRecipeCatalyst(GCYLTileEntities.PUMP_MODULE[0].getStackForm(), spacePumpID);
        registry.addRecipeCatalyst(GCYLTileEntities.PUMP_MODULE[1].getStackForm(), spacePumpID);
        registry.addRecipeCatalyst(GCYLTileEntities.PUMP_MODULE[2].getStackForm(), spacePumpID);


        String voidMinerID = Tags.MODID + ":" + "void_miner_ores";
        List<VoidMinerInfo> voidMinerInfo1 = new ArrayList<>();
        List<VoidMinerInfo> voidMinerInfo2 = new ArrayList<>();
        List<VoidMinerInfo> voidMinerInfo3 = new ArrayList<>();

        voidMinerInfo1.add(new VoidMinerInfo(ORES, 0));
        voidMinerInfo2.add(new VoidMinerInfo(ORES_2, 1));
        voidMinerInfo3.add(new VoidMinerInfo(ORES_3, 2));
        registry.addRecipes(voidMinerInfo1, voidMinerID);
        registry.addRecipes(voidMinerInfo2, voidMinerID);
        registry.addRecipes(voidMinerInfo3, voidMinerID);
        registry.addRecipeCatalyst(GCYLTileEntities.VOID_MINER[0].getStackForm(), voidMinerID);
        registry.addRecipeCatalyst(GCYLTileEntities.VOID_MINER[1].getStackForm(), voidMinerID);
        registry.addRecipeCatalyst(GCYLTileEntities.VOID_MINER[2].getStackForm(), voidMinerID);
    }

    @Override
    public void onRuntimeAvailable(@NotNull IJeiRuntime jeiRuntime) {
        JEIGCYLPlugin.jeiRuntime = jeiRuntime;

        itemBlacklist.addIngredientToBlacklist(GCYMMetaTileEntities.LARGE_MIXER.getStackForm());
        itemBlacklist.addIngredientToBlacklist(GCYMMetaTileEntities.LARGE_CENTRIFUGE.getStackForm());
        itemBlacklist.addIngredientToBlacklist(GCYMMetaTileEntities.ELECTRIC_IMPLOSION_COMPRESSOR.getStackForm());

        itemBlacklist.addIngredientToBlacklist(MetaBlocks.WIRE_COIL.getItemVariant(TRINIUM));
        itemBlacklist.addIngredientToBlacklist(MetaBlocks.WIRE_COIL.getItemVariant(TRITANIUM));

        itemBlacklist.addIngredientToBlacklist(COVER_INFINITE_WATER.getStackForm());

        itemBlacklist.addIngredientToBlacklist(MICROPROCESSOR_LV.getStackForm());
        itemBlacklist.addIngredientToBlacklist(WORKSTATION_EV.getStackForm());
        itemBlacklist.addIngredientToBlacklist(PROCESSOR_MV.getStackForm());
        itemBlacklist.addIngredientToBlacklist(ADVANCED_SMD_CAPACITOR.getStackForm());
        itemBlacklist.addIngredientToBlacklist(ADVANCED_SMD_INDUCTOR.getStackForm());
        itemBlacklist.addIngredientToBlacklist(ADVANCED_SMD_DIODE.getStackForm());
        itemBlacklist.addIngredientToBlacklist(ADVANCED_SMD_RESISTOR.getStackForm());
        itemBlacklist.addIngredientToBlacklist(ADVANCED_SMD_TRANSISTOR.getStackForm());
        itemBlacklist.addIngredientToBlacklist(SMD_INDUCTOR.getStackForm());

        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.dust, NaquadriaSulfate));
        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.dustSmall, NaquadriaSulfate));
        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.dustTiny, NaquadriaSulfate));

        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.dust, EnrichedNaquadahSulfate));
        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.dustSmall, EnrichedNaquadahSulfate));
        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.dustTiny, EnrichedNaquadahSulfate));

        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.dust, TriniumSulfide));
        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.dustSmall, TriniumSulfide));
        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.dustTiny, TriniumSulfide));

        for (int i = 5; i < 14; i++) {
            itemBlacklist.addIngredientToBlacklist(CIRCUIT_ASSEMBLER[i].getStackForm());
        }

        if(!GCYLConfig.Misc.enableWirelessDynamos) {
            for (MetaTileEntityWirelessEnergyHatch metaTileEntityWirelessEnergyHatch : WIRELESS_ENERGY_HATCH_OUTPUT) {
                itemBlacklist.addIngredientToBlacklist(metaTileEntityWirelessEnergyHatch.getStackForm());
            }
        }


        //The list has to be in this order for some godforsaken reason TODO: make it work on manganese phosphide all the way. hide fluids
        //hide old superconductors
        List<Material> oldSuperConductors = Arrays.asList(UraniumTriplatinum, RutheniumTriniumAmericiumNeutronate, EnrichedNaquadahTriniumEuropiumDuranide, UraniumRhodiumDinaquadide,
                IndiumTinBariumTitaniumCuprate, SamariumIronArsenicOxide, MercuryBariumCalciumCuprate, MagnesiumDiboride, ManganesePhosphide);

        for(Material mat : oldSuperConductors) {
            superConductorRemoval(mat);
        }

    }

    private void superConductorRemoval(Material mat) {
        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.wireGtSingle, mat));
        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.wireGtDouble, mat));
        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.wireGtQuadruple, mat));
        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.wireGtOctal, mat));
        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.wireGtHex, mat));
        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.ingot, mat));
        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.ingotHot, mat));
        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.block, mat));
        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.dustSmall, mat));
        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.dustTiny, mat));
        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.dust, mat));
        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.nugget, mat));
    }
}
