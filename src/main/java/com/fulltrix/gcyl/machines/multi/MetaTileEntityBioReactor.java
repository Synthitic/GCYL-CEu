package com.fulltrix.gcyl.machines.multi;

import com.fulltrix.gcyl.client.ClientHandler;
import com.fulltrix.gcyl.item.GCYLMetaBlocks;
import com.fulltrix.gcyl.item.GCYLMultiblockCasing2;
import com.fulltrix.gcyl.item.GCYLTransparentCasing;
import com.fulltrix.gcyl.recipes.GCYLRecipeMaps;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class MetaTileEntityBioReactor extends RecipeMapMultiblockController {

    public MetaTileEntityBioReactor(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GCYLRecipeMaps.BIO_REACTOR_RECIPES);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityBioReactor(metaTileEntityId);
    }

    public long maxVoltage = 0;

    @Override
    protected BlockPattern createStructurePattern() { //TODO: overhaul bioreactor mechanics in an overhaul pr
        return FactoryBlockPattern.start()
                .aisle("XXXXX", "XGGGX", "XGGGX", "XGGGX", "XXXXX")
                .aisle("XXXXX", "G###G", "G###G", "G###G", "XXXXX")
                .aisle("XXXXX", "G###G", "G###G", "G###G", "XXXXX")
                .aisle("XXXXX", "G###G", "G###G", "G###G", "XXXXX")
                .aisle("XXSXX", "XGGGX", "XGGGX", "XGGGX", "XXXXX")
                .where('S', selfPredicate())
                .where('X', states(getCasingState()).setMinGlobalLimited(50).or(autoAbilities(true, true, true, true, true, true, false)))
                .where('L', states(getCasingState()))
                .where('#', air())
                .where('G', states(MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.LAMINATED_GLASS)))
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