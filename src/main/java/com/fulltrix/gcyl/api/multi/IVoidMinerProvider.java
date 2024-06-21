package com.fulltrix.gcyl.api.multi;

import gregtech.api.capability.IMultipleTankHandler;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.IItemHandlerModifiable;

public interface IVoidMinerProvider {
    boolean drainEnergy(boolean simulate);

    IMultipleTankHandler getImportFluidHandler();

    IMultipleTankHandler getExportFluidHandler();

    IItemHandlerModifiable getOutputInventory();
}
