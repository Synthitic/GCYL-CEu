package com.fulltrix.gcyl.api.multi;

import gregicality.multiblocks.api.capability.IParallelMultiblock;
import gregicality.multiblocks.api.capability.impl.GCYMMultiblockRecipeLogic;
import gregicality.multiblocks.api.metatileentity.GCYMMultiblockAbility;
import gregicality.multiblocks.api.metatileentity.GCYMRecipeMapMultiblockController;
import gregicality.multiblocks.common.GCYMConfigHolder;
import gregtech.api.GTValues;
import gregtech.api.capability.impl.ComputationRecipeLogic;
import gregtech.api.metatileentity.ITieredMetaTileEntity;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GCYLComputationRecipeLogic extends ComputationRecipeLogic {


    public GCYLComputationRecipeLogic(RecipeMapMultiblockController metaTileEntity, ComputationType type) {
        super(metaTileEntity, type);
    }

    public @NotNull RecipeMapMultiblockController getMetaTileEntity() {
        return (RecipeMapMultiblockController)super.getMetaTileEntity();
    }

    @Override
    public int getParallelLimit() {
        return this.metaTileEntity instanceof IParallelMultiblock && ((IParallelMultiblock)this.metaTileEntity).isParallel() ? ((IParallelMultiblock)this.metaTileEntity).getMaxParallel() : 1;
    }

    @Override
    public long getMaxVoltage() {
        if (!GCYMConfigHolder.globalMultiblocks.enableTieredCasings) {
            return super.getMaxVoltage();
        } else {
            RecipeMapMultiblockController var2 = this.getMetaTileEntity();
            if (var2 instanceof GCYMRecipeMapMultiblockController) {
                GCYMRecipeMapMultiblockController controller = (GCYMRecipeMapMultiblockController)var2;
                if (!controller.isTiered()) {
                    return super.getMaxVoltage();
                }
            }

            List<ITieredMetaTileEntity> list = this.getMetaTileEntity().getAbilities(GCYMMultiblockAbility.TIERED_HATCH);
            return list.isEmpty() ? super.getMaxVoltage() : Math.min(GTValues.V[((ITieredMetaTileEntity)list.get(0)).getTier()], super.getMaxVoltage());
        }
    }
}
