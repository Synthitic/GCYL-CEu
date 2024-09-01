package com.fulltrix.gcyl;

import gregtech.api.GTValues;
import net.minecraftforge.common.config.Config;

@Config(modid = GCYLCore.MODID)
public class GCYLConfig {

    public static Multis multis = new Multis();

    public static class Multis {
        public VoidMiner voidMiner = new VoidMiner();
        public HyperReactors hyperReactors = new HyperReactors();
        public LargeFisher largeFisher = new LargeFisher();
    }

    public static class LargeFisher {
        @Config.Comment("The whitelisted biomes for the large fisher to work in.")
        @Config.RequiresMcRestart
        public String[] whitelistedBiomes = {"Ocean", "Deep Ocean", "River"};
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

        @Config.Comment("The material fluid that boosts the Reactor.")
        @Config.RequiresMcRestart
        @Config.Name("Hyper Reactor boosters")
        public String[] boosterFluid = {"helium", "radon", "gcyl:degenerate_rhenium_plasma"};

        @Config.Comment("The fluid state of the boosters for the Reactor.")
        @Config.RequiresMcRestart
        @Config.Name("Hyper Reactor boosters fluid states")
        public String[] boosterFluidStates = {"plasma", "plasma", "plasma"};

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

        @Config.Comment("The name of the ores to blacklist for all Void Miners because there are existing recipes in the Deep Miner")
        @Config.RequiresMcRestart
        @Config.Name("Void Miner because Deep Miner Blacklist")
        public String[] oreBlackListDeepMinerConflict = new String[]{"diamond", "coal", "pyrochlore", "columbite", "bauxite", "aluminium", "rutile", "gallite", "platinum_metallic_powder", "palladium_metallic_powder", "bastnasite", "monazite", "neodymium", "nether_star", "salt", "fluorite", "lepidolite", "spodumene", "naquadric_compound", "enriched_naquadric_compound", "naquadriatic_compound", "uranium_238", "plutonium_generic", "rarest_metal_mixture", "rhodium_salt", "sodium_ruthenate", "tantalum", "tantalite"};

        @Config.Comment("The name of the ores to blacklist for all Void Miners")
        @Config.RequiresMcRestart
        @Config.Name("Universal Void Miner Blacklist")
        public String[] oreBlackListUniversal = new String[]{"naquadah", "cooperite", "platinum", "palladium", "iridium", "osmium", "barium", "arsenic", "titanium", "tungsten"};

        @Config.Comment("The name of the ores to blacklist for the MK1 Void Miner")
        @Config.RequiresMcRestart
        @Config.Name("MK1 Void Miner Blacklist")
        public String[] oreBlacklist = new String[]{"trinium", "triniite", "duranium", "tritanium", "rutherfordium", "californium", "curium", "seaborgium", "berkelium", "fermium", "einsteinium", "dubnium", "bohrium"};

        @Config.Comment("The name of the ores to blacklist for the MK2 Void Miner")
        @Config.RequiresMcRestart
        @Config.Name("MK2 Void Miner Blacklist")
        public String[] oreBlacklistUHV = new String[]{"trinium", "fermium", "bohrium", "seaborgium", "einsteinium"};

        @Config.Comment("The name of the ores to blacklist for the MK3 Void Miner")
        @Config.RequiresMcRestart
        @Config.Name("MK3 Void Miner Blacklist")
        public String[] oreBlacklistUEV = new String[]{"bohrium", "fermium"};

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
        public String[] solderingFluidList = new String[]{"soldering_alloy:72", "tin:144", "lead:288", "indalloy_140:36"};

        @Config.Comment({"Whether to enable Magneto Resonatic Circuits. When false, they will have no recipes and will be hidden from JEI.", "default = true"})
        @Config.Name("Enable Magneto Circuits")
        @Config.RequiresMcRestart
        public boolean enableMagnetoCircuits = true;

        @Config.Comment({"Whether to enable wireless dynamo hatches or not. When false, they will be hidden in JEI. They do not have recipes.", "default=false"})
        @Config.Name("Enable Wireless Dynamos")
        @Config.RequiresMcRestart
        public boolean enableWirelessDynamos = false;

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


    @Config.Comment("Config options for recipes")
    public static Recipes recipes = new Recipes();

    public static class Recipes {

        @Config.Comment({"Base amount of circuits outputted per craft of the first circuit of a tier.", "default = 1"})
        @Config.RequiresMcRestart
        public int circuitsPerCraft = 1;


        @Config.Comment({"Base amount of high tier circuit cores outputted per craft.", "default = 1"})
        @Config.RequiresMcRestart
        public int circuitCoresPerCraft = 1;

        @Config.Comment({"How many Multiblock Casings to make per craft. Overrides the default GT Option", "Default: 1"})
        @Config.RequiresMcRestart
        public int gcylCasingsPerCraftOverride = 1;

        @Config.Comment({"Whether to use the new Platinum Group Line instead of Legacy.", "Default: true"})
        @Config.Name("New Platinum Chain")
        @Config.RequiresMcRestart
        public boolean useNewPlatinumChain = true;

        @Config.Comment({"Whether to use the new Rare Earth Processing instead of Legacy.", "Default: true"})
        @Config.Name("New REE Chain")
        @Config.RequiresMcRestart
        public boolean useNewREEChain = true;
    }

    @Config.Comment("Config options for space")
    public static Space space = new Space();

    public static class Space{

        @Config.Comment({"List of Planet Names. These are mapped to an ID. Starting Index is 1."})
        @Config.RequiresMcRestart
        public String[] planetNames = {"Jupiter", "Saturn", "Uranus", "Neptune"};

    }

    //TODO remove (THREADED TESTING MARK123)
    public static Experimental experimental = new Experimental();

    public static class Experimental {

        @Config.Comment({"Whether threading MTE updates is enabled.", "Default: false"})
        @Config.RequiresMcRestart
        public boolean threadedMTEUpdates = false;

        @Config.Comment({
                "Override to number of threads available to the Global Thread Pool Executor. -1 for no override, can't be higher than CPUs - 2.",
                "Default: -1" })
        @Config.RangeInt(min = 2)
        public int globalThreadPoolExecutorThreadCount = -1;
    }
}
