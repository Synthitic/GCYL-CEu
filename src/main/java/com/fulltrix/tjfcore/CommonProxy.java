package com.fulltrix.tjfcore;

import com.fulltrix.tjfcore.item.TJFCoreItems;

import com.fulltrix.tjfcore.recipes.RecipeHandler;
import gregtech.api.block.VariantItemBlock;
import gregtech.api.unification.material.event.MaterialEvent;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.function.Function;

import static com.fulltrix.tjfcore.item.TJFMetaBlocks.*;

@Mod.EventBusSubscriber(modid = TJFCore.MODID)
public class CommonProxy {
    public void preInit() {
        TJFCoreItems.init();
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void registerMaterials(MaterialEvent event) {
        //TJFMaterials.registerNuclearMaterials();
        TJFMaterials.register();
        TJFMaterials.materialChanges();
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();
        registry.register(HEATING_COIL);
        registry.register(MULTIBLOCK_CASING2);
        registry.register(TRANSPARENT_CASING);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        registry.register(createItemBlock(HEATING_COIL, VariantItemBlock::new));
        registry.register(createItemBlock(MULTIBLOCK_CASING2, VariantItemBlock::new));
        registry.register(createItemBlock(TRANSPARENT_CASING, VariantItemBlock::new));
    }


    @SubscribeEvent(priority = EventPriority.LOW)
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {

        RecipeHandler.initRecipes();
        RecipeHandler.initChains();

        //RecipeHandler.registerLargeMachineRecipes();
    }

    @SubscribeEvent
    public static void registerOrePrefix(RegistryEvent.Register<IRecipe> event) {
        //NuclearHandler.register();
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
