package com.fulltrix.gcyl.api.recipes.ui;

import com.fulltrix.gcyl.client.ClientHandler;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.gui.widgets.SlotWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.ui.RecipeMapUI;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.NotNull;

public class ComponentALRecipeUI<R extends RecipeMap<?>> extends RecipeMapUI<R> {
    public ComponentALRecipeUI(@NotNull R recipeMap) {
        super(recipeMap, false, false, false, false);
        setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL);
    }

    @Override
    @NotNull
    public ModularUI.Builder createJeiUITemplate(IItemHandlerModifiable importItems, IItemHandlerModifiable exportItems,
                                                 FluidTankList importFluids, FluidTankList exportFluids, int yOffset) {
        ModularUI.Builder builder = ModularUI.builder(GuiTextures.BACKGROUND, 176, 176)
                .widget(new ProgressWidget(100, 62, 0, 72, 20, ClientHandler.PROGRESS_BAR_COMPONENT_AL,
                        ProgressWidget.MoveType.HORIZONTAL));
        this.addInventorySlotGroup(builder, importItems, importFluids, false, yOffset);
        this.addInventorySlotGroup(builder, exportItems, exportFluids, true, yOffset);
        return builder;
    }

    @Override
    protected void addInventorySlotGroup(@NotNull ModularUI.Builder builder,
                                         @NotNull IItemHandlerModifiable itemHandler,
                                         @NotNull FluidTankList fluidHandler, boolean isOutputs, int yOffset) {
        int startInputsX = 80 - 4 * 18;
        int startInputsY = 37 - 2 * 18;

        if (!isOutputs) {
            // item input slots
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {
                    int slotIndex = i * 3 + j;
                    addSlot(builder, startInputsX + 18 * j, startInputsY + 18 * i, slotIndex, itemHandler, fluidHandler,
                            false, false);
                }
            }

            int startFluidX = startInputsX + 18 * 4;
            int startFluidY = startInputsY + 18;
            for (int i = 0; i < 3; i++) {
                for(int j = 0; j < 4; j++) {
                    int slotIndex = i * 4 + j;
                    addSlot(builder, startFluidX + 18 * j, startFluidY + 18 * i, slotIndex, itemHandler, fluidHandler, true, false);
                }
            }

        } else {
            // output slot
            addSlot(builder, startInputsX + 18 * 7, 1, 0, itemHandler, fluidHandler, false, true);
        }
    }
}
