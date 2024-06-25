package com.fulltrix.gcyl.machines.multi;

import com.fulltrix.gcyl.api.multi.GCYLRecipeMapMultiblockController;
import com.fulltrix.gcyl.client.ClientHandler;
import com.fulltrix.gcyl.blocks.GCYLMetaBlocks;
import com.fulltrix.gcyl.blocks.GCYLMultiblockCasing2;
import com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class MetaTileEntityBioReactor extends GCYLRecipeMapMultiblockController {

    public MetaTileEntityBioReactor(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GCYLRecipeMaps.BIO_REACTOR_RECIPES, false);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityBioReactor(metaTileEntityId);
    }

    public long maxVoltage = 0;

    @Override
    protected BlockPattern createStructurePattern() { //TODO: add tiered casings
        return FactoryBlockPattern.start()
                .aisle("XXXXX", "XGGGX", "XGGGX", "XGGGX", "XXXXX")
                .aisle("XXXXX", "G###G", "G#T#G", "G###G", "XXXXX")
                .aisle("XXXXX", "G#T#G", "GTTTG", "G#T#G", "XXXXX")
                .aisle("XXXXX", "G###G", "G#T#G", "G###G", "XXXXX")
                .aisle("XXSXX", "XGGGX", "XGGGX", "XGGGX", "XXXXX")
                .where('S', selfPredicate())
                .where('X', states(getCasingState()).setMinGlobalLimited(50).or(autoAbilities(true, true, true, true, true, true, false)))
                .where('L', states(getCasingState()))
                .where('#', air())
                .where('G', states(MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.LAMINATED_GLASS)))
                .where('T', tieredCasing())
                .build();
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.maxVoltage = 0;
    }

    private IBlockState getCasingState() {
        return GCYLMetaBlocks.MULTIBLOCK_CASING2.getState(GCYLMultiblockCasing2.CasingType.BIO_REACTOR);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return ClientHandler.BIO_REACTOR;
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return ClientHandler.ORGANIC_REPLICATOR_OVERLAY;
    }
}