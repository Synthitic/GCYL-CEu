package com.fulltrix.gcyl.recipes.categories.handlers;

import com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.GTRecipeInput;
import gregtech.api.util.ItemStackHashStrategy;
import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.objects.ObjectOpenCustomHashSet;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ElectricImplosionHandler {
    /**
     * Hashing strategy used to compare ItemStacks in the recipe.
     * Note that it does NOT compare count, since I needed this
     * to properly remove the explosive without knowing its count.
     */
    private static final Hash.Strategy<ItemStack> strategy = new ItemStackHashStrategy.ItemStackHashStrategyBuilder()
            .compareItem(true)
            .compareDamage(true)
            .build();

    /**
     * List containing the Sets of inputs. Used to make sure that
     * recipes are not duplicated due to both TNT and Dynamite
     * being used as explosive options in the Implosion Compressor.
     */
    private static final List<Set<ItemStack>> usedRecipes = new ArrayList<>();

    /**
     * The name of the Property used for explosives in
     * the Implosion Compressor.
     */
    private static final String PROPERTY = "explosives";

    /**
     * Builds Electric Implosion Compressor recipes.
     * <p>
     * NOTE that this does NOT have any checking for integrated circuits
     * or other not-consumed items. If we add these to the default
     * Implosion Compressor, this code will have to be adjusted.
     */
    public static void buildElectricImplosionRecipes() {

        RecipeMaps.IMPLOSION_RECIPES.getRecipeList().forEach(recipe -> {

            // Get the explosive type used in this recipe
            ItemStack explosive = (ItemStack) recipe.getRecipePropertyStorage().getRawRecipePropertyValue(PROPERTY);

            // Get the input list, converting from CountableIngredient to ItemStack
            AtomicInteger stackCount = new AtomicInteger();
            Set<ItemStack> inputs = new ObjectOpenCustomHashSet<>(strategy);
            inputs.addAll(recipe.getInputs().stream()
                    .peek(ing -> stackCount.set(ing.getAmount()))
                    .map(GTRecipeInput::getInputStacks)
                    .filter(stack -> stack.length != 0)
                    .map(array -> array[0])
                    .peek(is -> is.copy().setCount(stackCount.get()))
                    .collect(Collectors.toSet()));


            /*
            List<ItemStack[]> inputs = new ArrayList<>();
            inputs.addAll(recipe.getInputs().stream()
                    .peek(ing -> stackCount.set(ing.getAmount()))
                    .map(GTRecipeInput::getInputStacks)
                    .toList());

             */

            // Make sure inputs were properly acquired, and remove explosive
            if (inputs.size() > 0 && inputs.remove(explosive))
                registerRecipe(recipe, inputs);
        });
    }

    /**
     * Adds a recipe to the Electric Implosion Compressor.
     * <p>
     * This will skip any potential duplicate recipes caused by
     * TNT and Dynamite explosives options.
     *
     * @param recipe The Implosion Compressor recipe to copy.
     * @param inputs The Set of inputs without an explosive present.
     */
    private static void registerRecipe(Recipe recipe, Set<ItemStack> inputs) {

        // Check if this recipe has already been set
        for (Set<ItemStack> inputSet : usedRecipes)
            if (inputSet.containsAll(inputs))
                return;

        GCYLRecipeMaps.ELECTRIC_IMPLOSION_RECIPES.recipeBuilder()
                .inputStacks(inputs)
                .outputs(recipe.getOutputs())
                .buildAndRegister();

        usedRecipes.add(inputs);
    }
}
