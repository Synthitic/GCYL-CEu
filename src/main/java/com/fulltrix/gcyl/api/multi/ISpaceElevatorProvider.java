package com.fulltrix.gcyl.api.multi;

import gregtech.api.capability.IEnergyContainer;

public interface ISpaceElevatorProvider {
    int getMotorTier();

    IEnergyContainer getEnergyContainerForModules();
}
