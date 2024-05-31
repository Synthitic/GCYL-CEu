package com.fulltrix.tjfcore.machines.multi;

import com.fulltrix.tjfcore.item.TJFMetaBlocks;
import com.fulltrix.tjfcore.item.metal.MetalCasing1;
import com.fulltrix.tjfcore.machines.multi.simple.GCYLRecipeMapMultiblockController;
import com.fulltrix.tjfcore.recipes.TJFRecipeMaps;
import gregicality.multiblocks.api.render.GCYMTextures;
import gregicality.multiblocks.common.block.GCYMMetaBlocks;
import gregicality.multiblocks.common.block.blocks.BlockLargeMultiblockCasing;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.common.blocks.BlockMultiblockCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

import static gregtech.api.metatileentity.multiblock.MultiblockAbility.MUFFLER_HATCH;

public class MetaTileEntityElectricImplosion extends GCYLRecipeMapMultiblockController {

    public MetaTileEntityElectricImplosion(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, TJFRecipeMaps.ELECTRIC_IMPLOSION_RECIPES, false);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity metaTileEntityHolder) {
        return new MetaTileEntityElectricImplosion(metaTileEntityId);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXX", "GXG", "GXG", "GXG", "GXG", "GXG", "XXX")
                .aisle("XXX", "X#X", "X#X", "X#X", "X#X", "X#X", "XMX")
                .aisle("XSX", "GXG", "GXG", "GXG", "GXG", "GXG", "XXX")
                .where('S', selfPredicate())
                .where('#', air())
                .where('X', states(getCasingState()).or(autoAbilities(true,true,true,true,false,false,false)))
                .where('G', states(MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING)))
                .where('M', abilities(MUFFLER_HATCH))
                .build();
    }

    protected IBlockState getCasingState() {
        return GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.VIBRATION_SAFE_CASING);
        //.getState(MetalCasing1.CasingType.INCOLOY_MA956);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GCYMTextures.VIBRATION_SAFE_CASING;
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return GCYMTextures.ELECTRIC_IMPLOSION_OVERLAY;
    }

    @Override
    public boolean hasMufflerMechanics() {
        return true;
    }
}
