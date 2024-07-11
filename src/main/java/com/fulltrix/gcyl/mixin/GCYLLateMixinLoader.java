package com.fulltrix.gcyl.mixin;

import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.ArrayList;
import java.util.List;

public class GCYLLateMixinLoader implements ILateMixinLoader {
    @Override
    public List<String> getMixinConfigs() {

        List<String> configs = new ArrayList<>();

        configs.add("mixins.gcyl.json");

        return configs;
    }
}
