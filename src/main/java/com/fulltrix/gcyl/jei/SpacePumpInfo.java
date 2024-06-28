package com.fulltrix.gcyl.jei;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpacePumpInfo implements IRecipeWrapper {

    private final String planet;
    private final int planetID;
    private final int fluidID;
    private final FluidStack fluid;
    private final List<List<FluidStack>> fluidList = new ArrayList<>();
    private final List<List<ItemStack>> bucketList = new ArrayList<>();

    public SpacePumpInfo(String planet, int planetID, int fluidID, FluidStack fluid) {
        this.planet = planet;
        this.planetID = planetID;
        this.fluidID = fluidID;
        this.fluid = fluid;

        List<FluidStack> fluidList2 = new ArrayList<>();
        fluidList2.add(fluid);
        fluidList.add(fluidList2);
    }


    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInputLists(VanillaTypes.FLUID, fluidList);
        ingredients.setOutputLists(VanillaTypes.FLUID, fluidList);

        ItemStack bucket = FluidUtil.getFilledBucket(fluid);
        if (!bucket.isEmpty()) {
            bucketList.add(Collections.singletonList(bucket));
            ingredients.setInputLists(VanillaTypes.ITEM, bucketList);
            ingredients.setOutputLists(VanillaTypes.ITEM, bucketList);
        }
    }

    public void addTooltip(int slotIndex, boolean input, Object ingredient, List<String> tooltip) {

    }

    public String getPlanet() {
        return planet;
    }

    public int getPlanetID() {
        return planetID;
    }

    public int getFluidID() {
        return fluidID;
    }

    public FluidStack getFluid() {
        return fluid;
    }
}
