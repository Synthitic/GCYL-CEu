package com.fulltrix.gcyl.api.recipes;

import gregtech.api.GTValues;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.BlastProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import static gregtech.api.unification.ore.OrePrefix.dust;

//TODO Fix recipe durations
public class MixerRecipeProducer {

    public static final MixerRecipeProducer DEFAULT_PRODUCER = new MixerRecipeProducer();


    public void produce(@NotNull Material material, @NotNull BlastProperty blastProperty) {

        final int componentAmount = material.getMaterialComponents().size();

        if (componentAmount < 2)
            return;

        //Item output = OreDictUnifier.get(dust, material, outputAmount);

        RecipeBuilder<SimpleRecipeBuilder> builder;

        if(componentAmount > 5) {
            builder = createBuilder(blastProperty, material, true);
        }
        else {
            builder = createBuilder(blastProperty, material, false);
        }

        int outputAmount = addInputs(material, builder);
        if (outputAmount <= 0) return;

        buildRecipes(OreDictUnifier.get(dust, material, outputAmount), outputAmount, componentAmount, builder);

    }

    @SuppressWarnings("MethodMayBeStatic")
    protected @NotNull SimpleRecipeBuilder createBuilder(@NotNull BlastProperty property, @NotNull Material material, boolean large) {
        SimpleRecipeBuilder builder;
        if(large) {
            builder = GCYLRecipeMaps.ADVANCED_MIXER_RECIPES.recipeBuilder();
        }
        else {
            builder = RecipeMaps.MIXER_RECIPES.recipeBuilder();
        }

        int duration = property.getDurationOverride();
        if (duration < 0) duration = Math.max(1, (int) (material.getMass() * property.getBlastTemperature() / 100L));
        builder.duration(duration);

        int EUt = property.getEUtOverride();
        if (EUt < 0) EUt = GTValues.VA[GTValues.MV];
        builder.EUt(EUt);

        return builder;

    }

    protected int addInputs(@NotNull Material material, @NotNull RecipeBuilder<SimpleRecipeBuilder> builder) {
        int outputAmount = 0;
        int fluidAmount = 0;
        for (MaterialStack materialStack : material.getMaterialComponents()) {
            final Material msMat = materialStack.material;
            final int msAmount = (int) materialStack.amount;

            if (msMat.hasProperty(PropertyKey.DUST)) {
                builder.input(OrePrefix.dust, msMat, msAmount);
            } else if (msMat.hasProperty(PropertyKey.FLUID)) {
                if (fluidAmount >= 2) return -1; // more than 2 fluids won't fit in the machine
                fluidAmount++;
                // assume all fluids have 1000mB/mol, since other quantities should be as an item input
                builder.fluidInputs(msMat.getFluid(1000 * msAmount));
            } else return -1; // no fluid or item prop means no valid recipe
            outputAmount += msAmount;
        }
        return outputAmount;
    }

    protected void buildRecipes(@NotNull ItemStack itemStack, int outputAmount,
                                int componentAmount,
                                @NotNull RecipeBuilder<SimpleRecipeBuilder> builder) {
        // add the fluid output with the correct amount
        builder.outputs(itemStack);

        // apply alloy blast duration reduction: 3/4
        int duration = (int) (builder.getDuration() * outputAmount * (0.02));

        builder.notConsumable(new IntCircuitIngredient(getCircuitNum(componentAmount)))
                .duration(duration)
                .buildAndRegister();
    }

    protected int getCircuitNum(int componentAmount) {
        return componentAmount;
    }

}
