package com.fulltrix.gcyl;

import gregtech.api.GTValues;
import net.minecraftforge.common.config.Config;

@Config(modid = GCYLCore.MODID)
public class GCYLConfig {

    public static Multis multis = new Multis();

    public static class Multis {
        public VoidMiner voidMiner = new VoidMiner();
        public HyperReactors hyperReactors = new HyperReactors();
    }

    public static class HyperReactors {
        @Config.Comment("The base EU/t production of the Hyper Reactor.")
        @Config.RequiresMcRestart
        @Config.Name("Hyper Reactor EU/t generation")
        @Config.RangeInt(min = 1)
        public long[] euGeneration = {GTValues.V[GTValues.UIV], GTValues.V[GTValues.UXV], GTValues.V[GTValues.OpV]};

        @Config.Comment("The fuel multiplier when the Reactor is boosted.")
        @Config.RequiresMcRestart
        @Config.Name("Hyper Reactor boosted fuel amount multiplier")
        @Config.RangeInt(min = 1)
        public int[] boostedFuelAmount = {2, 2, 2};

        @Config.Comment("The EU/t multiplier when the Reactor is boosted.")
        @Config.RequiresMcRestart
        @Config.Name("Hyper Reactor boosted EU/t multiplier")
        @Config.RangeInt(min = 1)
        public int[] boostedEuAmount = {2, 2, 2};

        @Config.Comment("The liquid that boosts the Reactor.")
        @Config.RequiresMcRestart
        @Config.Name("Hyper Reactor boosters")
        public String[] boosterFluid = {"plasma.helium", "plasma.radon", "degenerate_rhenium_plasma"};

        @Config.Comment("The amount of liquid that boosts the Reactor.")
        @Config.RequiresMcRestart
        @Config.Name("Hyper Reactor booster amount")
        public int[] boosterFluidAmounts = {50, 20, 10};
    }

    public static class VoidMiner {
        @Config.Comment("The maximum temperature the void miner can reach before overheating. Every second the void miner will generate 10 different ores with amount between 1 and (temperature/1000)^2 ores. default: [9000]")
        @Config.RangeInt(min = 1000)
        @Config.RequiresMcRestart
        @Config.Name("Void Miner I max temperature")
        public int maxTemp = 9000;

        @Config.Comment("The maximum temperature the void miner can reach before overheating. Every second the void miner will generate 10 different ores with amount between 1 and (temperature/1000)^2 ores. default: [9000]")
        @Config.RangeInt(min = 1000)
        @Config.RequiresMcRestart
        @Config.Name("Void Miner II max temperature")
        public int maxTempUHV = 16000;

        @Config.Comment("The maximum temperature the void miner can reach before overheating. Every second the void miner will generate 10 different ores with amount between 1 and (temperature/1000)^2 ores. default: [9000]")
        @Config.RangeInt(min = 1000)
        @Config.RequiresMcRestart
        @Config.Name("Void Miner III max temperature")
        public int maxTempUEV = 40000;

        @Config.Comment("Whether or not to add all ore variants to the Void Miner's ore table. If false only the first ore in the material's ore dictionary will be added.")
        @Config.RequiresMcRestart
        @Config.Name("Void miner ore variants")
        public boolean oreVariants = true;

        @Config.Comment("The name of the ores to blacklist for the MK1 Void Miner")
        @Config.RequiresMcRestart
        @Config.Name("MK1 Void Miner Blacklist")
        public String[] oreBlacklist = new String[]{"trinium, triniite, duranium, tritanium, rutherfordium, californium, curium, seaborgium, berkelium, fermium, einsteinium, dubnium, bohrium"};

        @Config.Comment("The name of the ores to blacklist for the MK2 Void Miner")
        @Config.RequiresMcRestart
        @Config.Name("MK2 Void Miner Blacklist")
        public String[] oreBlacklistUHV = new String[]{"trinium, fermium, bohrium, seaborgium, einsteinium"};

        @Config.Comment("The name of the ores to blacklist for the MK3 Void Miner")
        @Config.RequiresMcRestart
        @Config.Name("MK3 Void Miner Blacklist")
        public String[] oreBlacklistUEV = new String[]{"bohrium, fermium"};

        @Config.Comment("The name of items you wish to add to the MK1 Void Miner. Example: \"minecraft:wool:2\"")
        @Config.RequiresMcRestart
        @Config.Name("MK1 Void Miner Whitelist")
        public String[] oreWhitelist = new String[]{""};

        @Config.Comment("The name of items you wish to add to the MK2 Void Miner")
        @Config.RequiresMcRestart
        @Config.Name("MK2 Void Miner Whitelist")
        public String[] oreWhitelistUHV = new String[]{""};

        @Config.Comment("The name of items you wish to add to the MK3 Void Miner")
        @Config.RequiresMcRestart
        @Config.Name("MK3 Void Miner Whitelist")
        public String[] oreWhitelistUEV = new String[]{""};
    }
    @Config.Comment("Config options of miscellaneous features")
    public static Misc Misc = new Misc();

    public static class Misc {

        @Config.Comment("List of Soldering fluid [<fluid>:<amount>] amount=[1 ~ 64000]")
        @Config.RequiresMcRestart
        public String[] solderingFluidList = new String[]{"soldering_alloy:72", "tin:144", "lead:288"};

        @Config.Comment({"Whether to enable Magneto Resonatic Circuits. When false, they will have no recipes and will be hidden from JEI.", "default = true"})
        @Config.Name("Enable Magneto Circuits")
        @Config.RequiresMcRestart
        public boolean enableMagnetoCircuits = true;

        @Config.Comment("Whether or not to add diminishing returns for Gregicality Naquadah Reactors. Does not affect the Large Nauqadah Reactor")
        @Config.Name("Naquadah Reactor efficiency loss")
        @Config.RequiresMcRestart
        public boolean naqEfficieny = true;

        @Config.Comment("Whether or not to add diminishing returns for Gregicality Rocket Engines. Does not affect the Large Rocket Engine")
        @Config.Name("Rocket Engine efficiency loss")
        @Config.RequiresMcRestart
        public boolean rocketEfficiency = true;
        @Config.Comment("Efficiency level for the EV rocket engine")
        public int EVRocketEfficiency= 65;
        @Config.Comment("Efficiency level for the IV rocket engine")
        public int IVRocketEfficiency= 35;
        @Config.Comment("Efficiency level for the LuV rocket engine")
        public int LuVRocketEfficiency= 25;

    }
}
