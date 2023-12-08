package com.fulltrix.tjfcore;

import net.minecraftforge.common.config.Config;

@Config(modid = TJFCore.MODID)
public class TJFConfig {

    public static Multis multis = new Multis();

    public static class Multis {
        public VoidMiner voidMiner = new VoidMiner();
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
        public String[] oreBlacklist = new String[]{"trinium, triniite"};

        @Config.Comment("The name of the ores to blacklist for the MK2 Void Miner")
        @Config.RequiresMcRestart
        @Config.Name("MK2 Void Miner Blacklist")
        public String[] oreBlacklistUHV = new String[]{""};

        @Config.Comment("The name of the ores to blacklist for the MK3 Void Miner")
        @Config.RequiresMcRestart
        @Config.Name("MK3 Void Miner Blacklist")
        public String[] oreBlacklistUEV = new String[]{""};

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
}
