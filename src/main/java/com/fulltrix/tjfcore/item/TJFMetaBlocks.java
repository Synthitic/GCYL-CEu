package com.fulltrix.tjfcore.item;

import com.fulltrix.tjfcore.item.fusion.TJFCryostatCasing;
import com.fulltrix.tjfcore.item.fusion.TJFDivertorCasing;
import com.fulltrix.tjfcore.item.fusion.TJFFusionCasing;
import com.fulltrix.tjfcore.item.fusion.TJFVacuumCasing;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static gregtech.common.blocks.MetaBlocks.statePropertiesToString;

public class TJFMetaBlocks {
    public static TJFMultiblockCasing2 MULTIBLOCK_CASING2;
    public static TJFTransparentCasing TRANSPARENT_CASING;
    public static TJFHeatingCoil HEATING_COIL;

    public static TJFFusionCasing FUSION_CASING;
    public static TJFVacuumCasing VACUUM_CASING;
    public static TJFDivertorCasing DIVERTOR_CASING;
    public static TJFCryostatCasing CRYOSTAT_CASING;

    public static void init() {
        HEATING_COIL = new TJFHeatingCoil();
        HEATING_COIL.setRegistryName("tjf_heating_coil");

        TRANSPARENT_CASING = new TJFTransparentCasing();
        TRANSPARENT_CASING.setRegistryName("tjf_transparent_casing");

        MULTIBLOCK_CASING2 = new TJFMultiblockCasing2();
        MULTIBLOCK_CASING2.setRegistryName("tjf_multiblock_casing2");

        FUSION_CASING = new TJFFusionCasing();
        FUSION_CASING.setRegistryName("tjf_fusion_casing");

        VACUUM_CASING = new TJFVacuumCasing();
        VACUUM_CASING.setRegistryName("tjf_vacuum_casing");

        DIVERTOR_CASING = new TJFDivertorCasing();
        DIVERTOR_CASING.setRegistryName("tjf_divertor_casing");

        CRYOSTAT_CASING = new TJFCryostatCasing();
        CRYOSTAT_CASING.setRegistryName("tjf_cryostat_casing");
    }

    @SideOnly(Side.CLIENT)
    public static void registerItemModels() {

        registerItemModel(MULTIBLOCK_CASING2);
        registerItemModel(TRANSPARENT_CASING);
        registerItemModel(FUSION_CASING);
        registerItemModel(VACUUM_CASING);
        registerItemModel(DIVERTOR_CASING);
        registerItemModel(CRYOSTAT_CASING);

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
