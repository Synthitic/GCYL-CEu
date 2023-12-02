package com.fulltrix.tjfcore.item;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.*;

import static gregtech.client.model.modelfactories.MaterialBlockModelLoader.registerItemModel;
import static gregtech.common.blocks.MetaBlocks.statePropertiesToString;

public class TJFMetaBlocks {
    public static TJFMultiblockCasing2 MULTIBLOCK_CASING2;
    public static TJFTransparentCasing TRANSPARENT_CASING;
    public static TJFHeatingCoil HEATING_COIL;

    public static void init() {
        HEATING_COIL = new TJFHeatingCoil();
        HEATING_COIL.setRegistryName("tjf_heating_coil");

        TRANSPARENT_CASING = new TJFTransparentCasing();
        TRANSPARENT_CASING.setRegistryName("tjf_transparent_casing");

        MULTIBLOCK_CASING2 = new TJFMultiblockCasing2();
        MULTIBLOCK_CASING2.setRegistryName("tjf_multiblock_casing2");
    }

    @SideOnly(Side.CLIENT)
    public static void registerItemModels() {

        registerItemModel(MULTIBLOCK_CASING2);
        registerItemModel(TRANSPARENT_CASING);

        HEATING_COIL.onModelRegister();

    }

    @SideOnly(Side.CLIENT)
    private static void registerItemModel(Block block) {
        for (IBlockState state : block.getBlockState().getValidStates()) {
            //noinspection ConstantConditions
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block),
                    block.getMetaFromState(state),
                    new ModelResourceLocation(block.getRegistryName(),
                            statePropertiesToString(state.getProperties())));
        }
    }
}
