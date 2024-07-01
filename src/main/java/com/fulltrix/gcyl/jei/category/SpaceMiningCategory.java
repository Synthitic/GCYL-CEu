package com.fulltrix.gcyl.jei.category;

import com.fulltrix.gcyl.client.ClientHandler;
import com.fulltrix.gcyl.jei.SpaceMiningInfo;
import gregtech.api.GTValues;
import gregtech.api.gui.GuiTextures;
import gregtech.api.util.GTUtility;
import gregtech.integration.jei.basic.BasicRecipeCategory;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import org.jetbrains.annotations.NotNull;

public class SpaceMiningCategory extends BasicRecipeCategory<SpaceMiningInfo, SpaceMiningInfo> {

    protected final IDrawable slot;
    protected final IDrawable fluidSlot;
    protected final IDrawable progressBar;
    private static final int SLOT_CENTER = 79;
    private static final int SLOT_WIDTH = 18;
    private static final int SLOT_HEIGHT = 18;
    private static final int TEXT_START_X = 5;
    private static final int START_POS_Y = 80;
    protected int outputCount;
    protected int inputCount;
    protected int tier;
    protected long totalEU;
    protected long EUt;
    protected int duration;
    protected int minDistance;
    protected int maxDistance;
    protected int minSize;
    protected int maxSize;
    protected int CWUt;
    protected int weight;

    public SpaceMiningCategory(IGuiHelper guiHelper) {
        super("space_mining",
                "space_mining.name",
                guiHelper.createBlankDrawable(176,166),
                guiHelper);

        this.slot = guiHelper.drawableBuilder(GuiTextures.SLOT.imageLocation, 0, 0, 18, 18).setTextureSize(18, 18)
                .build();

        this.fluidSlot = guiHelper.drawableBuilder(GuiTextures.FLUID_SLOT.imageLocation, 0, 0, 18, 18).setTextureSize(18,18)
                .build();

        this.progressBar = guiHelper.drawableBuilder(ClientHandler.PROGRESS_BAR_MINING_MODULE.imageLocation, 0, 0, 23,63).setTextureSize(23,63)
                .build();
    }



    @Override
    public IRecipeWrapper getRecipeWrapper(SpaceMiningInfo recipe) {
        return recipe;
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, SpaceMiningInfo recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();
        IGuiFluidStackGroup fluidStackGroup = recipeLayout.getFluidStacks();
        int baseYPos = 19;

        //Drone
        itemStackGroup.init(0, true, 4 * SLOT_WIDTH + SLOT_CENTER - 6, 9);

        //Inputs
        int count = 1;
        for(int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if(count < recipeWrapper.getInputCount()) {
                    itemStackGroup.init(count, true, j * SLOT_WIDTH + 9, i * SLOT_HEIGHT);
                    count++;
                } else
                    break;
            }
        }

        //Fluid Inputs
        fluidStackGroup.init(0, true, 10, 46, 16, 16, 1, false, null);

        //Outputs
        int oldCount = count;
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(count < recipeWrapper.getOutputCount() + oldCount) {
                    itemStackGroup.init(count, false, SLOT_CENTER + j * SLOT_WIDTH - 11, i * SLOT_HEIGHT);
                    count++;
                } else
                    break;
            }
        }

        itemStackGroup.set(ingredients);
        fluidStackGroup.set(ingredients);
        this.inputCount = recipeWrapper.getInputCount();
        this.outputCount = recipeWrapper.getOutputCount();
        this.tier = recipeWrapper.getTier();

        this.totalEU = recipeWrapper.getTotalEU();
        this.EUt = recipeWrapper.getEUt();
        this.duration = recipeWrapper.getDuration();
        this.minDistance = recipeWrapper.getMinDistance();
        this.maxDistance = recipeWrapper.getMaxDistance();
        this.minSize = recipeWrapper.getMinSize();
        this.maxSize = recipeWrapper.getMaxSize();
        this.CWUt = recipeWrapper.getMinCWUt();
        this.weight = recipeWrapper.getWeight();
    }

    @Override
    public void drawExtras(@NotNull Minecraft minecraft) {


        //Inputs
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 2; j++) {
                this.slot.draw(minecraft, j * SLOT_WIDTH + 9, i * SLOT_HEIGHT);
            }
        }

        //Fluid Input
        this.fluidSlot.draw(minecraft, 9, 45);
        this.fluidSlot.draw(minecraft, SLOT_WIDTH + 9, 45);

        //Outputs
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                this.slot.draw(minecraft, SLOT_CENTER + j * SLOT_WIDTH - 11, i * SLOT_HEIGHT);
            }
        }

        //Drone Slot
        this.slot.draw(minecraft, 4 * SLOT_WIDTH + SLOT_CENTER - 6, 9);

        //Progress Bar
        this.progressBar.draw(minecraft, SLOT_WIDTH + 27, 0);


        String totalEUName = I18n.format("gcyl.jei.space_miner.total_eu", totalEU);
        minecraft.fontRenderer.drawString(totalEUName, TEXT_START_X, START_POS_Y, 0x111111);

        String EUtName = I18n.format("gcyl.jei.space_miner.eut", EUt, GTValues.VN[GTUtility.getTierByVoltage(EUt)]);
        minecraft.fontRenderer.drawString(EUtName, TEXT_START_X, START_POS_Y + FONT_HEIGHT + 1, 0x111111);

        String durationName = I18n.format("gcyl.jei.space_miner.duration", duration / 20.0);
        minecraft.fontRenderer.drawString(durationName, TEXT_START_X, START_POS_Y + 2 * FONT_HEIGHT + 1, 0x111111);

        String tierName = I18n.format("gcyl.jei.space_miner.tier", getTierString(tier));
        minecraft.fontRenderer.drawString(tierName, TEXT_START_X, START_POS_Y+ 3 * FONT_HEIGHT + 1, 0x111111);

        String distanceName = I18n.format("gcyl.jei.space_miner.distance", minDistance, maxDistance);
        minecraft.fontRenderer.drawString(distanceName, TEXT_START_X, START_POS_Y+ 4 * FONT_HEIGHT + 1, 0x111111);

        String sizeName = I18n.format("gcyl.jei.space_miner.size", minSize, maxSize);
        minecraft.fontRenderer.drawString(sizeName, TEXT_START_X, START_POS_Y+ 5 * FONT_HEIGHT + 1, 0x111111);

        String CWUtName = I18n.format("gcyl.jei.space_miner.cwut", CWUt);
        minecraft.fontRenderer.drawString(CWUtName, TEXT_START_X, START_POS_Y+ 6 * FONT_HEIGHT + 1, 0x111111);

        String weightName = I18n.format("gcyl.jei.space_miner.weight", weight);
        minecraft.fontRenderer.drawString(weightName, TEXT_START_X, START_POS_Y+ 7 * FONT_HEIGHT + 1, 0x111111);


    }

    private String getTierString(int tier) {
        return switch (tier) {
          case (1) -> "I";
          case (2) -> "II";
          case (3) -> "III";
            default -> null;
        };
    }


    @Override
    public @NotNull String getModName() {
        return "gcyl";
    }
}
