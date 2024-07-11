package com.fulltrix.gcyl.mixinhelpers;


import org.jetbrains.annotations.NotNull;

public enum MixinProspectorMode {

    ORE("ore_prospector", "metaitem.prospector.mode.ores"),
    FLUID("fluid_prospector", "metaitem.prospector.mode.fluid"),
    VIRTUAL_ORE("virtual_ore_prospector", "metaitem.prospector.mode.virtual_ores");

    public static final MixinProspectorMode[] VALUES = values();

    public final String terminalName;
    public final String unlocalizedName;

    MixinProspectorMode(@NotNull String terminalName, @NotNull String unlocalizedName) {
        this.terminalName = terminalName;
        this.unlocalizedName = unlocalizedName;
    }

    @NotNull
    public MixinProspectorMode next() {
        int next = ordinal() + 1;
        if (next >= VALUES.length) {
            return MixinProspectorMode.VALUES[0];
        }
        return VALUES[next];
    }

}
