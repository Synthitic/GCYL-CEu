package com.fulltrix.gcyl.jei.category;

import com.fulltrix.gcyl.jei.VirtualOresInfo;
import com.fulltrix.gcyl.jei.VoidMinerInfo;
import gregtech.api.GTValues;
import gregtech.api.gui.GuiTextures;
import gregtech.api.unification.material.Material;
import gregtech.api.util.GTStringUtils;
import gregtech.api.util.GTUtility;
import gregtech.api.worldgen.config.WorldGenRegistry;
import gregtech.integration.jei.basic.BasicRecipeCategory;
import gregtech.integration.jei.utils.JEIResourceDepositCategoryUtils;
import it.unimi.dsi.fastutil.objects.ObjectList;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class VirtualOresCategory extends BasicRecipeCategory<VirtualOresInfo, VirtualOresInfo> {
    private static final int TEXT_START_X = 18;
    private static final int START_POS_Y = 50;

    private static final int FONT_HEIGHT = 18;

    protected final IDrawable slot;
    private static final int SLOT_CENTER = 79;
    private String veinName;
    private int weight;
    private int[] yields;
    private ObjectList<Float> multipliers;
    private List<Material> materials;
    private int[] dimensions;
    private int weightLength;
    private int minYieldLength;
    private int maxYieldLength;

    public VirtualOresCategory(IGuiHelper guiHelper) {
        super("virtual_ores",
                "virtual_ores.name",
                guiHelper.createBlankDrawable(176,166),
                guiHelper);

        this.slot = guiHelper.drawableBuilder(GuiTextures.SLOT.imageLocation, 0, 0, 18, 18).setTextureSize(18, 18)
                .build();
    }


    @Override
    public void setRecipe(IRecipeLayout recipeLayout, VirtualOresInfo recipeWrapper, @NotNull IIngredients ingredients) {
        IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();
        int baseYPos = START_POS_Y - 4;
        int baseXPos = TEXT_START_X - 21;

        itemStackGroup.init(0, false, baseXPos, baseYPos);
        itemStackGroup.init(1, false, baseXPos, baseYPos + 18);
        itemStackGroup.init(2, false, baseXPos, baseYPos + 18 * 2);
        itemStackGroup.init(3, false, baseXPos, baseYPos + 18 * 3);

        itemStackGroup.addTooltipCallback(recipeWrapper::addTooltip);
        itemStackGroup.set(ingredients);

        this.veinName = recipeWrapper.getName();
        this.weight = recipeWrapper.getWeight();
        this.yields = recipeWrapper.getYields();
        this.multipliers = recipeWrapper.getMultipliers();
        this.materials = recipeWrapper.getMaterials();

        this.dimensions = JEIResourceDepositCategoryUtils.getAllRegisteredDimensions(
                recipeWrapper.getDefinition().getDimensionFilter());
    }

    @NotNull
    @Override
    public IRecipeWrapper getRecipeWrapper(@NotNull VirtualOresInfo recipe) {
        return recipe;
    }

    @Override
    public void drawExtras(@NotNull Minecraft minecraft) {
        GTStringUtils.drawCenteredStringWithCutoff(veinName, minecraft.fontRenderer, 176);

        for(int i = 0; i < materials.size(); i++) {
            String layerName = I18n.format("gcyl.jei.virtual_layer", i+1, materials.get(i).getLocalizedName(), yields[0], (int) (yields[1] * multipliers.get(i)));
            minecraft.fontRenderer.drawString(layerName, TEXT_START_X, START_POS_Y + i * FONT_HEIGHT + 1, 0x111111);
        }

        String weightString = I18n.format("gcyl.jei.space_miner.weight", weight);
        int weightLength = minecraft.fontRenderer.getStringWidth(weightString);
        minecraft.fontRenderer.drawString(weightString, TEXT_START_X, START_POS_Y + materials.size() * FONT_HEIGHT + 1, 0x111111);


        String veinDimension = I18n.format("gregtech.jei.fluid.dimension") + " ";
        int dimensionLength = minecraft.fontRenderer.getStringWidth(veinDimension);
        minecraft.fontRenderer.drawString(veinDimension, TEXT_START_X, START_POS_Y + (materials.size()+1) * FONT_HEIGHT + 1, 0x111111);

        JEIResourceDepositCategoryUtils.drawMultiLineCommaSeparatedDimensionList(WorldGenRegistry.getNamedDimensions(),
                dimensions,
                minecraft.fontRenderer,
                TEXT_START_X,
                START_POS_Y + (this.materials.size()+1) * FONT_HEIGHT + 1,
                TEXT_START_X + dimensionLength);

    }


    @Override
    public @NotNull String getModName() {
        return "gcyl";
    }
}
