package com.fulltrix.gcyl.api.multi;

import gregtech.api.capability.IMultipleTankHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

public interface IOreFactoryProvider {

    boolean drainEnergy(boolean simulate);

    IMultipleTankHandler getImportFluidHandler();

    IItemHandlerModifiable getOutputInventory();

    IItemHandlerModifiable getInputInventory();

    int getVoltageTier();
}
