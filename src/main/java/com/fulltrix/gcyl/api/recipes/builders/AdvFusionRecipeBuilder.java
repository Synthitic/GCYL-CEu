package com.fulltrix.gcyl.api.recipes.builders;

import com.fulltrix.gcyl.api.recipes.properties.AdvFusionCoilProperty;
import com.fulltrix.gcyl.api.recipes.properties.AdvFusionEUReturnProperty;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.recipeproperties.FusionEUToStartProperty;
import gregtech.api.util.EnumValidationResult;
import gregtech.api.util.GTLog;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static gregtech.api.unification.material.Materials.Steam;

public class AdvFusionRecipeBuilder extends RecipeBuilder<AdvFusionRecipeBuilder> {

    public static Map<FluidStack, Fluid> COOLANTS = new HashMap<>();

    static {
        COOLANTS.put(Steam.getFluid(570), SupercriticalSteam.getFluid());
        //COOLANTS.put(Deuterium.getFluid(240), SupercriticalDeuterium.fluid);
        COOLANTS.put(SodiumPotassiumAlloy.getFluid(120), SupercriticalSodiumPotassiumAlloy.getFluid());
        //COOLANTS.put(Sodium.getFluid(100), SupercriticalSodium.fluid);
        COOLANTS.put(FLiNaK.getFluid(50), SupercriticalFLiNaK.getFluid());
        COOLANTS.put(FLiBe.getFluid(55), SupercriticalFLiBe.getFluid());
        COOLANTS.put(LeadBismuthEutectic.getFluid(60), SupercriticalLeadBismuthEutectic.getFluid());
    }

    public AdvFusionRecipeBuilder() {
    }

    public AdvFusionRecipeBuilder(Recipe recipe, RecipeMap<AdvFusionRecipeBuilder> recipeMap) {
        super(recipe, recipeMap);
    }

    public AdvFusionRecipeBuilder(RecipeBuilder<AdvFusionRecipeBuilder> recipeBuilder) {
        super(recipeBuilder);
    }

    @Override
    public AdvFusionRecipeBuilder copy() {
        return new AdvFusionRecipeBuilder(this);
    }

    public boolean applyProperty(@NotNull String key, Object value) {
        if (key.equals(FusionEUToStartProperty.KEY)) {
            this.EUToStart(((Number) value).longValue());
            return true;
        }

        if (key.equals(AdvFusionCoilProperty.KEY)) {
            this.AdvCoilTier(((Number) value).intValue());
        }
        return super.applyProperty(key, value);
    }

    public AdvFusionRecipeBuilder EUToStart(long EUToStart) {
        if (EUToStart <= 0) {
            GTLog.logger.error("EU to start cannot be less than or equal to 0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(FusionEUToStartProperty.getInstance(), EUToStart);
        return this;
    }

    public AdvFusionRecipeBuilder AdvCoilTier(int tier) {
        if (tier < 0) {
            GTLog.logger.error("Adv coil tier cannot be less than 0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(AdvFusionCoilProperty.getInstance(), tier);
        return this;
    }

    public AdvFusionRecipeBuilder EUReturn(int percentage) {
        if (percentage < 0) {
            GTLog.logger.error("Advanced Fusion EU return cannot be less than 0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.applyProperty(AdvFusionEUReturnProperty.getInstance(), percentage);
        AdvFusionEUReturnProperty.setEUReturn(percentage);
        return this;
    }

    public long getEUToStart() {
        return this.recipePropertyStorage == null ? 0L :
                this.recipePropertyStorage.getRecipePropertyValue(FusionEUToStartProperty.getInstance(), 0L);
    }

    public int getAdvCoilTier() {
        return this.recipePropertyStorage == null ? 0 :
                this.recipePropertyStorage.getRecipePropertyValue(AdvFusionCoilProperty.getInstance(), 0);
    }

    public int getEUReturn() {
        return this.recipePropertyStorage == null ? 0 :
                this.recipePropertyStorage.getRecipePropertyValue(AdvFusionEUReturnProperty.getInstance(), 0);
    }

    @Override
    public void buildAndRegister() {
        if (fluidInputs.size() == 2) {
            if (this.getEUReturn() > 0) {
                long eu = (this.getEUToStart() + ((long) EUt) * duration) * this.getEUReturn() / 100;
                for (FluidStack fluidStack : COOLANTS.keySet()) {
                    FluidStack fluidInput = fluidStack.copy();
                    int amount = Math.max((int) ((eu / (2048 * 10000)) * fluidInput.amount), 1);
                    fluidInput.amount = amount;
                    FluidStack fluidOutput = new FluidStack(COOLANTS.get(fluidStack), amount);
                    recipeMap.addRecipe(this.copy()
                            .fluidInputs(fluidInput)
                            .fluidOutputs(fluidOutput)
                            .build());
                }
            } else {
                recipeMap.addRecipe(this.build());
            }
        } else {
            recipeMap.addRecipe(this.build());
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append(FusionEUToStartProperty.getInstance().getKey(), getEUToStart())
                .append(AdvFusionCoilProperty.getInstance().getKey(), getAdvCoilTier())
                .append(AdvFusionEUReturnProperty.getInstance().getKey(), getEUReturn())
                .toString();
    }
}
