package com.fulltrix.gcyl.jei;

import com.fulltrix.gcyl.api.worldgen.VirtualOreDepositDefinition;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.util.FileUtility;
import gregtech.api.worldgen.config.BedrockFluidDepositDefinition;
import gregtech.integration.jei.utils.JEIResourceDepositCategoryUtils;
import it.unimi.dsi.fastutil.objects.ObjectList;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class VirtualOresInfo implements IRecipeWrapper {

    private final VirtualOreDepositDefinition definition;
    private String name;
    private final String description;
    private final int weight;
    private final int[] yields; // the [minimum, maximum) yields
    private final ObjectList<Float> multipliers;
    private final Function<Biome, Integer> biomeFunction;
    private final List<Material> materials;
    private final List<List<ItemStack>> groupedOutputsAsItemStacks = new ArrayList<>();

    public VirtualOresInfo(VirtualOreDepositDefinition definition) {
        this.definition = definition;

        this.name = definition.getAssignedName();
        if (this.name == null) {
            this.name = FileUtility.trimFileName(definition.getDepositName());
        }

        this.description = definition.getDescription();

        this.weight = definition.getWeight();

        this.yields = definition.getYields();

        this.materials = definition.getStoredMaterials();

        this.multipliers = definition.getMultipliers();

        this.biomeFunction = definition.getBiomeWeightModifier();


        for(Material material : this.materials) {
            this.groupedOutputsAsItemStacks.add(Collections.singletonList(OreDictUnifier.get(OrePrefix.ore, material)));
        }


    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setOutputLists(VanillaTypes.ITEM, groupedOutputsAsItemStacks);
    }

    public void addTooltip(int slotIndex, boolean input, Object ingredient, List<String> tooltip) {
        if (description != null) {
            tooltip.add(description);
        }
        tooltip.addAll(JEIResourceDepositCategoryUtils.createSpawnPageBiomeTooltip(biomeFunction, weight));
    }

    public VirtualOreDepositDefinition getDefinition() {
        return definition;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getWeight() {
        return weight;
    }

    public int[] getYields() {
        return yields;
    }

    public ObjectList<Float> getMultipliers() {return multipliers;}

    public List<Material> getMaterials() {return materials;}


}
