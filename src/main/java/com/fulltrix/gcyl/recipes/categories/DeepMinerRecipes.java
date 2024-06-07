package com.fulltrix.gcyl.recipes.categories;

import com.fulltrix.gcyl.recipes.recipeproperties.GCYLScanProperty;
import crafttweaker.api.item.IItemStack;
import gregtech.api.GTValues;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.nbtmatch.NBTCondition;
import gregtech.api.recipes.ingredients.nbtmatch.NBTMatcher;
import gregtech.api.recipes.recipeproperties.ScanProperty;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.util.GTUtility;
import gregtech.common.ConfigHolder;
import gregtech.common.items.MetaItems;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static com.fulltrix.gcyl.GCYLMaterials.*;
import static com.fulltrix.gcyl.recipes.GCYLRecipeMaps.DEEP_MINER_RECIPES;
import static gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES;
import static gregtech.api.recipes.RecipeMaps.SCANNER_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.api.util.AssemblyLineManager.createDefaultResearchRecipe;
import static gregtech.api.util.AssemblyLineManager.writeResearchToNBT;
import static gregtech.common.items.MetaItems.*;

public class DeepMinerRecipes {



    private static Map<String, ItemStack> minerScanMap = new HashMap<String,ItemStack>();

    public static void init() {

        //EXAMPLE

        //createResearchRecipe("deep_platinum", OreDictUnifier.get(block, Platinum), TOOL_DATA_STICK.getStackForm(), true, 300, 512, 0);
        /*
        DEEP_MINER_RECIPES.recipeBuilder()
                .notConsumable(minerScanMap.get("deep_platinum"))
                .input(SENSOR_EV)
                .fluidInputs(DrillingFluid.getFluid(64000))
                .output(ore, PlatinumMetallicPowder, 64)
                .chancedOutput(ore, PlatinumMetallicPowder, 64,100,500)
                .output(ore, PalladiumMetallicPowder, 64)
                .chancedOutput(ore, PalladiumMetallicPowder, 64,100,500)
                .duration(6000)
                .EUt(1920)
                .temperature(3500)
                .buildAndRegister();

         */

    }


    public static void createResearchRecipe(@NotNull String researchId, @NotNull ItemStack researchItem,
                                                   @NotNull ItemStack dataItem, boolean ignoreNBT, int duration,
                                                   int EUt, int CWUt) {

        NBTTagCompound compound = GTUtility.getOrCreateNbtCompound(dataItem);
        writeResearchToNBT(compound, researchId);

        dataItem.setTagCompound(compound);

        dataItem.setStackDisplayName(I18n.format("gcyl.research."+researchId));

        if (CWUt > 0) {
            RecipeBuilder<?> researchBuilder = RecipeMaps.RESEARCH_STATION_RECIPES.recipeBuilder()
                    .inputNBT(dataItem.getItem(), 1, dataItem.getMetadata(), NBTMatcher.ANY, NBTCondition.ANY)
                    .outputs(dataItem)
                    .EUt(EUt)
                    .CWUt(CWUt)
                    .totalCWU(duration);

            if (ignoreNBT) {
                researchBuilder.inputNBT(researchItem.getItem(), 1, researchItem.getMetadata(), NBTMatcher.ANY,
                        NBTCondition.ANY);
            } else {
                researchBuilder.inputs(researchItem);
            }

            researchBuilder.buildAndRegister();
        } else {
            RecipeBuilder<?> builder = RecipeMaps.SCANNER_RECIPES.recipeBuilder()
                    .inputNBT(dataItem.getItem(), 1, dataItem.getMetadata(), NBTMatcher.ANY, NBTCondition.ANY)
                    .outputs(dataItem)
                    .duration(duration)
                    .EUt(EUt);

            if (ignoreNBT) {
                builder.inputNBT(researchItem.getItem(), 1, researchItem.getMetadata(), NBTMatcher.ANY,
                        NBTCondition.ANY);
            } else {
                builder.inputs(researchItem);
            }

            builder.applyProperty(GCYLScanProperty.getInstance(), true);
            builder.buildAndRegister();
        }

        minerScanMap.put(researchId, dataItem);

    }


}
