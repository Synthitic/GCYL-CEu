package com.fulltrix.gcyl.item;

import com.fulltrix.gcyl.item.fusion.GCYLCryostatCasing;
import com.fulltrix.gcyl.item.fusion.GCYLDivertorCasing;
import com.fulltrix.gcyl.item.fusion.GCYLFusionCasing;
import com.fulltrix.gcyl.item.fusion.GCYLVacuumCasing;
import com.fulltrix.gcyl.item.metal.MetalCasing1;
import com.fulltrix.gcyl.item.metal.MetalCasing2;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static gregtech.common.blocks.MetaBlocks.statePropertiesToString;

public class GCYLMetaBlocks {
    public static GCYLMultiblockCasing2 MULTIBLOCK_CASING2;
    //public static GCYLTransparentCasing TRANSPARENT_CASING;
    public static GCYLHeatingCoil HEATING_COIL;
    public static GCYLFusionCasing FUSION_CASING;
    public static GCYLVacuumCasing VACUUM_CASING;
    public static GCYLDivertorCasing DIVERTOR_CASING;
    public static GCYLCryostatCasing CRYOSTAT_CASING;
    public static GCYLExplosive EXPLOSIVE;
    public static GCYLSimpleBlock SIMPLE_BLOCK;
    public static GCYLReactorCasing REACTOR_CASING;
    public static MetalCasing1 METAL_CASING_1;
    public static MetalCasing2 METAL_CASING_2;


    public static void init() {
        HEATING_COIL = new GCYLHeatingCoil();
        HEATING_COIL.setRegistryName("wire_coil");

        //TRANSPARENT_CASING = new GCYLTransparentCasing();
        //TRANSPARENT_CASING.setRegistryName("gcyl_transparent_casing");

        MULTIBLOCK_CASING2 = new GCYLMultiblockCasing2();
        MULTIBLOCK_CASING2.setRegistryName("gcyl_multiblock_casing2");

        FUSION_CASING = new GCYLFusionCasing();
        FUSION_CASING.setRegistryName("gcyl_fusion_casing");

        VACUUM_CASING = new GCYLVacuumCasing();
        VACUUM_CASING.setRegistryName("gcyl_vacuum_casing");

        DIVERTOR_CASING = new GCYLDivertorCasing();
        DIVERTOR_CASING.setRegistryName("gcyl_divertor_casing");

        CRYOSTAT_CASING = new GCYLCryostatCasing();
        CRYOSTAT_CASING.setRegistryName("gcyl_cryostat_casing");

        EXPLOSIVE = new GCYLExplosive();
        EXPLOSIVE.setRegistryName("gcyl_explosive");

        SIMPLE_BLOCK = new GCYLSimpleBlock();
        SIMPLE_BLOCK.setRegistryName("gcyl_simple_block");

        METAL_CASING_1 = new MetalCasing1();
        METAL_CASING_1.setRegistryName("gcyl_metal_casing_1");

        METAL_CASING_2 = new MetalCasing2();
        METAL_CASING_2.setRegistryName("gcyl_metal_casing_2");

        REACTOR_CASING = new GCYLReactorCasing();
        REACTOR_CASING.setRegistryName("gcyl_reactor_casing");

    }

    @SideOnly(Side.CLIENT)
    public static void registerItemModels() {

        registerItemModel(MULTIBLOCK_CASING2);
        //registerItemModel(TRANSPARENT_CASING);
        registerItemModel(FUSION_CASING);
        registerItemModel(VACUUM_CASING);
        registerItemModel(DIVERTOR_CASING);
        registerItemModel(CRYOSTAT_CASING);
        registerItemModel(EXPLOSIVE);
        registerItemModel(SIMPLE_BLOCK);
        registerItemModel(METAL_CASING_1);
        registerItemModel(METAL_CASING_2);
        registerItemModel(REACTOR_CASING);

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
