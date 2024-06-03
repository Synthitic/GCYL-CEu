package com.fulltrix.gcyl.covers;

import com.fulltrix.gcyl.item.GCYLCoreItem;
import com.fulltrix.gcyl.item.GCYLCoreItems;
import gregtech.api.GTValues;
import gregtech.api.cover.CoverBase;
import gregtech.common.covers.CoverBehaviors;

import static com.fulltrix.gcyl.GCYLUtility.gcylId;
import static gregtech.api.GTValues.*;
import static gregtech.common.covers.CoverBehaviors.registerBehavior;

public final class GCYLCoverBehaviors {
    private GCYLCoverBehaviors() {}

    public static void init() {

        registerBehavior(gcylId("infinite_water_mv"), GCYLCoreItems.MV_INFINITE_WATER_SOURCE, (def,view, side) -> new GCYLCoverInfiniteWater(def, view, side, MV));
        registerBehavior(gcylId("infinite_water_hv"), GCYLCoreItems.HV_INFINITE_WATER_SOURCE, (def,view, side) -> new GCYLCoverInfiniteWater(def, view, side, HV));
        registerBehavior(gcylId("infinite_water_ev"), GCYLCoreItems.EV_INFINITE_WATER_SOURCE, (def,view, side) -> new GCYLCoverInfiniteWater(def, view, side, EV));
        registerBehavior(gcylId("infinite_water_iv"), GCYLCoreItems.IV_INFINITE_WATER_SOURCE, (def,view, side) -> new GCYLCoverInfiniteWater(def, view, side, IV));
        registerBehavior(gcylId("infinite_water_luv"), GCYLCoreItems.LuV_INFINITE_WATER_SOURCE, (def,view, side) -> new GCYLCoverInfiniteWater(def, view, side, LuV));
        registerBehavior(gcylId("infinite_water_zpm"), GCYLCoreItems.ZPM_INFINITE_WATER_SOURCE, (def,view, side) -> new GCYLCoverInfiniteWater(def, view, side, ZPM));
        registerBehavior(gcylId("infinite_water_uv"), GCYLCoreItems.UV_INFINITE_WATER_SOURCE, (def,view, side) -> new GCYLCoverInfiniteWater(def, view, side, UV));

    }
}
