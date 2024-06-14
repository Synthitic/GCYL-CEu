package com.fulltrix.gcyl.api;

import com.fulltrix.gcyl.api.multi.GCYLCleanroomType;
import gregtech.api.GTValues;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.AssemblyLineRecipeBuilder;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import stanhebben.zenscript.annotations.Optional;

import java.util.ArrayList;
import java.util.List;

import static com.fulltrix.gcyl.item.GCYLCoreItems.*;
import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.common.items.MetaItems.*;

public class GCYLUtility {

    public static @NotNull ResourceLocation gcylId(@NotNull String path) {
        return new ResourceLocation("gcyl", path);
    }

    public static int setBetweenInclusive(int value, int start, int end) {
        if (value < start)
            return start;
        return Math.min(value, end);
    }

    public static ItemStack getPowerIC(int tier) {
        if (tier < 3)
            return ULTRA_LOW_POWER_INTEGRATED_CIRCUIT.getStackForm(64);
        if (tier < 4)
            return LOW_POWER_INTEGRATED_CIRCUIT.getStackForm(64);
        if (tier < 5)
            return POWER_INTEGRATED_CIRCUIT.getStackForm(64);
        if (tier < 7)
            return HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64);
        else
            return ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64);
    }

    public static int getCWUt(int tier) {
        return (int) Math.max(32, Math.pow(2, tier - 2));
    }

    public static ItemStack getDataStack(int CWUt) {
        if(CWUt < 128)
            return TOOL_DATA_ORB.getStackForm();
        else if(CWUt < 256)
            return TOOL_DATA_MODULE.getStackForm();
        else if(CWUt < 1024)
            return TOOL_DATA_MODULE_CLUSTER.getStackForm();
        else if(CWUt < 4096)
            return TOOL_DATA_ULTIMATE.getStackForm();
        else
            return TOOL_DATA_SUPRACAUSAL.getStackForm();

    }

    public static Material getSuperconductorByTier(int tier) {
        if(tier < 1)
            return LVSuperconductor;

        return switch (tier) {
            case(1) -> LVSuperconductor;
            case(2) -> MVSuperconductor;
            case(3) -> HVSuperconductor;
            case(4) -> EVSuperconductor;
            case(5) -> IVSuperconductor;
            case(6) -> LuVSuperconductor;
            case(7) -> ZPMSuperconductor;
            case(8) -> UVSuperconductor;
            case(9) -> UHVSuperconductor;
            case(10) -> UEVSuperconductor;
            case(11) -> UIVSuperconductor;
            case(12) -> UXVSuperconductor;
            case(13) -> OpVSuperconductor;
            default -> MAXSuperconductor;
        };
    }

    public static MetaItem<?>.MetaValueItem getFieldGeneratorByTier(int tier) {
        if(tier < 1)
            return FIELD_GENERATOR_LV;

        return switch (tier) {
            case(1) -> FIELD_GENERATOR_LV;
            case(2) -> FIELD_GENERATOR_MV;
            case(3) -> FIELD_GENERATOR_HV;
            case(4) -> FIELD_GENERATOR_EV;
            case(5) -> FIELD_GENERATOR_IV;
            case(6) -> FIELD_GENERATOR_LuV;
            case(7) -> FIELD_GENERATOR_ZPM;
            case(8) -> FIELD_GENERATOR_UV;
            case(9) -> FIELD_GENERATOR_UHV;
            case(10) -> FIELD_GENERATOR_UEV;
            case(11) -> FIELD_GENERATOR_UIV;
            case(12) -> FIELD_GENERATOR_UXV;
            case(13) -> FIELD_GENERATOR_OpV;
            default -> FIELD_GENERATOR_MAX;
        };
    }

    public static MetaItem<?>.MetaValueItem getSensorByTier(int tier) {
        if(tier < 1)
            return SENSOR_LV;

        return switch (tier) {
            case(1) -> SENSOR_LV;
            case(2) -> SENSOR_MV;
            case(3) -> SENSOR_HV;
            case(4) -> SENSOR_EV;
            case(5) -> SENSOR_IV;
            case(6) -> SENSOR_LuV;
            case(7) -> SENSOR_ZPM;
            case(8) -> SENSOR_UV;
            case(9) -> SENSOR_UHV;
            case(10) -> SENSOR_UEV;
            case(11) -> SENSOR_UIV;
            case(12) -> SENSOR_UXV;
            case(13) -> SENSOR_OpV;
            default -> SENSOR_MAX;
        };
    }

    public static Material getMarkerMaterialByTier(int tier) {
        if(tier < 0)
            return MarkerMaterials.Tier.ULV;

        return switch (tier) {
            case(0) -> MarkerMaterials.Tier.ULV;
            case(1) -> MarkerMaterials.Tier.LV;
            case(2) -> MarkerMaterials.Tier.MV;
            case(3) -> MarkerMaterials.Tier.HV;
            case(4) -> MarkerMaterials.Tier.EV;
            case(5) -> MarkerMaterials.Tier.IV;
            case(6) -> MarkerMaterials.Tier.LuV;
            case(7) -> MarkerMaterials.Tier.ZPM;
            case(8) -> MarkerMaterials.Tier.UV;
            case(9) -> MarkerMaterials.Tier.UHV;
            case(10) -> MarkerMaterials.Tier.UIV;
            case(11) -> MarkerMaterials.Tier.UEV;
            case(12) -> MarkerMaterials.Tier.UXV;
            case(13) -> MarkerMaterials.Tier.OpV;
            default -> MarkerMaterials.Tier.MAX;
        };
    }

    public static CleanroomType getCleanroomTypeByTierNotV(int tier) {
        return switch (tier) {
            case(2)-> CleanroomType.STERILE_CLEANROOM;
            case(3)-> GCYLCleanroomType.ISO3;
            case(4)-> GCYLCleanroomType.ISO2;
            case(5)-> GCYLCleanroomType.ISO1;
            case(6)-> GCYLCleanroomType.ISO0;
            default -> CleanroomType.CLEANROOM;
        };
    }

    public static List<RecipeBuilder<?>> buildHigherYieldCleanroomRecipes(RecipeMap<?> recipeMap, MetaItem<?>.MetaValueItem output, int baseOutputAmount, int startCleanRoomTier, int startEUt) {
        List<RecipeBuilder<?>> recipeBuilderList = new ArrayList<>();

        for (int i = startCleanRoomTier; i < 7; i++) {
            recipeBuilderList.add(recipeMap.recipeBuilder()
                    .circuitMeta(i)
                    .cleanroom(getCleanroomTypeByTierNotV(i))
                    .EUt(i == startCleanRoomTier ? startEUt : (int) (startEUt * Math.pow(4, i - startCleanRoomTier)))
                    .outputs(output.getStackForm((int) (baseOutputAmount * Math.pow(2, i - startCleanRoomTier)))));
        }
        return recipeBuilderList;
    }

    public static RecipeBuilder<AssemblyLineRecipeBuilder> getAssLineResearchBuilder(int tier, int duration, ItemStack researchStack, boolean upTierEUt, boolean upTierCWUt) {
            return ASSEMBLY_LINE_RECIPES.recipeBuilder().EUt(GTValues.VA[upTierEUt ? tier + 1 : tier]).duration(duration)
                    .stationResearch(b -> b
                            .dataStack(getDataStack(getCWUt(upTierCWUt ? tier + 1 : tier)))
                            .CWUt(getCWUt(upTierCWUt ? tier + 1 : tier))
                            .researchStack(researchStack)
                            .EUt(GTValues.VA[upTierEUt ? tier + 1 : tier]));
    }

    public static RecipeBuilder<AssemblyLineRecipeBuilder> getAssLineResearchBuilder(int tier, int duration, ItemStack researchStack, boolean upTierEUt, boolean upTierCWUt, double researchCWUTotalMulti) {
        return ASSEMBLY_LINE_RECIPES.recipeBuilder().EUt(GTValues.VA[upTierEUt ? tier + 1 : tier]).duration(duration)
                    .stationResearch(b -> b
                            .CWUt(getCWUt(upTierCWUt ? tier + 1 : tier), (int) (4000 * (GTValues.VA[upTierEUt ? tier + 1 : tier] * researchCWUTotalMulti)))
                            .dataStack(getDataStack(getCWUt(upTierCWUt ? tier + 1 : tier)))
                            .researchStack(researchStack)
                            .EUt(GTValues.VA[upTierEUt ? tier + 1 : tier]));
    }

    public static RecipeBuilder<AssemblyLineRecipeBuilder> getAssLineResearchBuilder(int tier, int duration, ItemStack researchStack, boolean upTierEUt, boolean upTierCWUt, CleanroomType cleanroomType) {
        return ASSEMBLY_LINE_RECIPES.recipeBuilder().EUt(GTValues.VA[upTierEUt ? tier + 1 : tier]).duration(duration).cleanroom(cleanroomType)
                .stationResearch(b -> b
                        .dataStack(getDataStack(getCWUt(upTierCWUt ? tier + 1 : tier)))
                        .CWUt(getCWUt(upTierCWUt ? tier + 1 : tier))
                        .researchStack(researchStack)
                        .EUt(GTValues.VA[upTierEUt ? tier + 1 : tier]));
    }

    public static RecipeBuilder<AssemblyLineRecipeBuilder> getAssLineResearchBuilder(int tier, int duration, ItemStack researchStack, boolean upTierEUt, boolean upTierCWUt, double researchCWUTotalMulti, CleanroomType cleanroomType) {
        return ASSEMBLY_LINE_RECIPES.recipeBuilder().EUt(GTValues.VA[upTierEUt ? tier + 1 : tier]).duration(duration).cleanroom(cleanroomType)
                .stationResearch(b -> b
                        .CWUt(getCWUt(upTierCWUt ? tier + 1 : tier), (int) (4000 * (GTValues.VA[upTierEUt ? tier + 1 : tier] * researchCWUTotalMulti)))
                        .dataStack(getDataStack(getCWUt(upTierCWUt ? tier + 1 : tier)))
                        .researchStack(researchStack)
                        .EUt(GTValues.VA[upTierEUt ? tier + 1 : tier]));
    }

    public static RecipeBuilder<AssemblyLineRecipeBuilder> getAssLineResearchBuilder(int tier, int duration, ItemStack researchStack, boolean upTierEUt, boolean upTierCWUt, CleanroomType cleanroomType, int recipeEUt) {
        return ASSEMBLY_LINE_RECIPES.recipeBuilder().EUt(recipeEUt).duration(duration).cleanroom(cleanroomType)
                .stationResearch(b -> b
                        .dataStack(getDataStack(getCWUt(upTierCWUt ? tier + 1 : tier)))
                        .CWUt(getCWUt(upTierCWUt ? tier + 1 : tier))
                        .researchStack(researchStack)
                        .EUt(GTValues.VA[upTierEUt ? tier + 1 : tier]));
    }
}
