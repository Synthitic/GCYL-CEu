package com.fulltrix.gcyl.jei.category;

import com.fulltrix.gcyl.Tags;
import com.fulltrix.gcyl.jei.SpacePumpInfo;
import gregtech.api.gui.GuiTextures;
import gregtech.api.util.GTStringUtils;
import gregtech.api.util.TextFormattingUtil;
import gregtech.integration.jei.basic.BasicRecipeCategory;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class SpacePumpCategory extends BasicRecipeCategory<SpacePumpInfo, SpacePumpInfo> {
    private static final int SLOT_CENTER = 79;
    private static final int TEXT_START_X = 5;
    private static final int START_POS_Y = 40;

    private String planet;
    private FluidStack fluid;
    private int planetID;
    private int fluidID;
    private int fluidAmount;

    protected final IDrawable slot;

    private int planetLength;
    private int planetIDLength;
    private int fluidIDLength;
    private int fluidAmountLength;


    public SpacePumpCategory(IGuiHelper guiHelper) {
        super("space_pump_fluids",
                "space_pump.name",
                guiHelper.createBlankDrawable(176, 166),
                guiHelper);

        this.slot = guiHelper.drawableBuilder(GuiTextures.SLOT.imageLocation, 0, 0, 18, 18).setTextureSize(18, 18)
                .build();
    }


    @Override
    public void setRecipe(@NotNull IRecipeLayout recipeLayout, SpacePumpInfo spacePumpInfo, @NotNull IIngredients ingredients) {
        IGuiFluidStackGroup fluidStackGroup = recipeLayout.getFluidStacks();

        fluidStackGroup.init(0, true, SLOT_CENTER, 19, 16, 16, 1, false, null);

        fluidStackGroup.addTooltipCallback(spacePumpInfo::addTooltip);
        fluidStackGroup.set(ingredients);

        this.planet = spacePumpInfo.getPlanet();
        this.planetID = spacePumpInfo.getPlanetID();
        this.fluidID = spacePumpInfo.getFluidID();
        this.fluid = spacePumpInfo.getFluid();
        this.fluidAmount = spacePumpInfo.getFluid().amount;
    }

    @NotNull
    @Override
    public IRecipeWrapper getRecipeWrapper(@NotNull SpacePumpInfo spacePumpInfo) {
        return spacePumpInfo;
    }

    @Override
    public void drawExtras(@NotNull Minecraft minecraft) {
        GTStringUtils.drawCenteredStringWithCutoff(fluid.getLocalizedName(), minecraft.fontRenderer, 176);

        this.slot.draw(minecraft, SLOT_CENTER - 1, 18);

        String planetName = I18n.format("gcyl.jei.space_pump_planet_name", planet);
        planetLength = minecraft.fontRenderer.getStringWidth(planetName);
        minecraft.fontRenderer.drawString(planetName, TEXT_START_X, START_POS_Y, 0x111111);

        String planetIDName = I18n.format("gcyl.jei.space_pump_planet_number", planetID);
        planetIDLength = minecraft.fontRenderer.getStringWidth(planetIDName);
        minecraft.fontRenderer.drawString(planetIDName, TEXT_START_X, START_POS_Y + FONT_HEIGHT + 1, 0x111111);

        String fluidIDName = I18n.format("gcyl.jei.space_pump_fluid_number", fluidID);
        fluidIDLength = minecraft.fontRenderer.getStringWidth(fluidIDName);
        minecraft.fontRenderer.drawString(fluidIDName, TEXT_START_X, START_POS_Y + 2 * FONT_HEIGHT + 1, 0x111111);

        String fluidAmountName = I18n.format("gcyl.jei.space_pump_fluid_amount", TextFormattingUtil.formatNumbers(fluidAmount));
        fluidAmountLength = minecraft.fontRenderer.getStringWidth(fluidAmountName);
        minecraft.fontRenderer.drawString(fluidAmountName, TEXT_START_X, START_POS_Y + 3 * FONT_HEIGHT + 1, 0x111111);
    }

    @NotNull
    @Override
    public List<String> getTooltipStrings(int mouseX, int mouseY) {
        return Collections.emptyList();
    }

    @Override
    public @NotNull String getModName() {
        return Tags.MODID;
    }
}

