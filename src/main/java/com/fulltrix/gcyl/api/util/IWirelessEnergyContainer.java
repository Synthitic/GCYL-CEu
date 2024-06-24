package com.fulltrix.gcyl.api.util;

import java.math.BigInteger;

public interface IWirelessEnergyContainer {

    long changeEnergy(long differenceAmount);

    default long addEnergy(long energyToAdd) { return changeEnergy(energyToAdd); }

    default long remove(long energyToRemove) { return changeEnergy(-energyToRemove); }

    long getEnergyStored();


}
