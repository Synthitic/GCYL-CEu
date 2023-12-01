package com.fulltrix.tjfcore;

import com.fulltrix.tjfcore.item.TJFCoreItems;
import com.fulltrix.tjfcore.item.TJFHeatingCoil;

import com.fulltrix.tjfcore.item.TJFMetaBlocks;
import com.fulltrix.tjfcore.recipes.RecipeHandler;
import gregtech.api.block.VariantItemBlock;
import gregtech.api.unification.material.event.MaterialEvent;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.util.FluidTooltipUtil;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static com.fulltrix.tjfcore.item.TJFMetaBlocks.*;

@Mod.EventBusSubscriber(modid = TJFCore.MODID)
public class CommonProxy {
    public void preInit() {
        TJFCoreItems.init();
        TJFMetaBlocks.init();
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void registerMaterials(MaterialEvent event) {
        TJFMaterials.register();
        TJFMaterials.materialChanges();
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();
        registry.register(HEATING_COIL);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        registry.register(createItemBlock(HEATING_COIL, VariantItemBlock::new));
    }


    @SubscribeEvent(priority = EventPriority.LOW)
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {



        RecipeHandler.initRecipes();

        //RecipeHandler.registerLargeMachineRecipes();
    }

    private static <T extends Block> ItemBlock createItemBlock(T block, Function<T, ItemBlock> producer) {
        ItemBlock itemBlock = producer.apply(block);
        ResourceLocation registryName = block.getRegistryName();
        if (registryName == null) {
            throw new IllegalArgumentException("Block " + block.getTranslationKey() + " has no registry name.");
        }
        itemBlock.setRegistryName(registryName);
        return itemBlock;
    }

    //public void onLoad() throws IOException {

   //}
}
