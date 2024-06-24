package com.fulltrix.gcyl.recipes.categories.handlers;

import com.fulltrix.gcyl.GCYLConfig;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.OreProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VoidMinerHandler {
    public static List<ItemStack> ORES = new ArrayList<>();
    public static List<ItemStack> ORES_2 = new ArrayList<>();
    public static List<ItemStack> ORES_3 = new ArrayList<>();

    public static void register() {
        OrePrefix.ore.addProcessingHandler(PropertyKey.ORE, VoidMinerHandler::processVoidOre);
        addWhitelist();
    }

    private static void processVoidOre(OrePrefix dustPrefix, Material material, OreProperty property) {

        OrePrefix currentOre = OrePrefix.ore;

        //TODO: add support for other products in ore processing chain? or add ore processing factory from GTNH

        if (GCYLConfig.multis.voidMiner.oreVariants) {
            if (!Arrays.asList(GCYLConfig.multis.voidMiner.oreBlacklist).contains(material.toString()) && !Arrays.asList(GCYLConfig.multis.voidMiner.oreBlackListDeepMinerConflict).contains(material.toString()) && !Arrays.asList(GCYLConfig.multis.voidMiner.oreBlackListUniversal).contains(material.toString())) {
                ORES.addAll(OreDictUnifier.getAll(new UnificationEntry(currentOre, material)));
            }
            if (!Arrays.asList(GCYLConfig.multis.voidMiner.oreBlacklistUHV).contains(material.toString()) && !Arrays.asList(GCYLConfig.multis.voidMiner.oreBlackListDeepMinerConflict).contains(material.toString()) && !Arrays.asList(GCYLConfig.multis.voidMiner.oreBlackListUniversal).contains(material.toString())) {
                ORES_2.addAll(OreDictUnifier.getAll(new UnificationEntry(currentOre, material)));
            }
            if (!Arrays.asList(GCYLConfig.multis.voidMiner.oreBlacklistUEV).contains(material.toString()) && !Arrays.asList(GCYLConfig.multis.voidMiner.oreBlackListDeepMinerConflict).contains(material.toString()) && !Arrays.asList(GCYLConfig.multis.voidMiner.oreBlackListUniversal).contains(material.toString())) {
                ORES_3.addAll(OreDictUnifier.getAll(new UnificationEntry(currentOre, material)));
            }
        } else {
            if (!Arrays.asList(GCYLConfig.multis.voidMiner.oreBlacklist).contains(material.toString()) && !Arrays.asList(GCYLConfig.multis.voidMiner.oreBlackListDeepMinerConflict).contains(material.toString()) && !Arrays.asList(GCYLConfig.multis.voidMiner.oreBlackListUniversal).contains(material.toString())) {
                ORES.add(OreDictUnifier.get(new UnificationEntry(currentOre, material)));
            }
            if (!Arrays.asList(GCYLConfig.multis.voidMiner.oreBlacklistUHV).contains(material.toString()) && !Arrays.asList(GCYLConfig.multis.voidMiner.oreBlackListDeepMinerConflict).contains(material.toString()) && !Arrays.asList(GCYLConfig.multis.voidMiner.oreBlackListUniversal).contains(material.toString())) {
                ORES_2.add(OreDictUnifier.get(new UnificationEntry(currentOre, material)));
            }
            if (!Arrays.asList(GCYLConfig.multis.voidMiner.oreBlacklistUEV).contains(material.toString()) && !Arrays.asList(GCYLConfig.multis.voidMiner.oreBlackListDeepMinerConflict).contains(material.toString()) && !Arrays.asList(GCYLConfig.multis.voidMiner.oreBlackListUniversal).contains(material.toString())) {
                ORES_3.add(OreDictUnifier.get(new UnificationEntry(currentOre, material)));
            }
        }
    }

    public static void addWhitelist() {
        if(GCYLConfig.multis.voidMiner.oreWhitelist.length > 0)
            addItemsToList(GCYLConfig.multis.voidMiner.oreWhitelist, ORES);
        if(GCYLConfig.multis.voidMiner.oreWhitelistUHV.length > 0)
            addItemsToList(GCYLConfig.multis.voidMiner.oreWhitelistUHV, ORES_2);
        if(GCYLConfig.multis.voidMiner.oreWhitelistUEV.length > 0)
            addItemsToList(GCYLConfig.multis.voidMiner.oreWhitelistUEV, ORES_3);

    }

    private static void addItemsToList(String[] itemStrings, List<ItemStack> list) {
        for (String item : itemStrings) {
            if (!item.isEmpty()) {
                long count = item.chars().filter(c -> c == ':').count();
                int meta = 0;
                //Invalid string
                if (count > 2 || count < 1) {
                    continue;
                }
                String itemStr = item;
                //Has metadata
                if (count == 2) {
                    int index = item.lastIndexOf(':');
                    meta = Integer.parseInt(item.substring(index + 1));
                    itemStr = item.substring(0, index);
                }
                Item tempItem = Item.getByNameOrId(itemStr);
                if (tempItem == null) {
                    continue;
                }
                ItemStack itemStack = new ItemStack(tempItem, 1, meta);
                list.add(itemStack);
            }
        }
    }
}
