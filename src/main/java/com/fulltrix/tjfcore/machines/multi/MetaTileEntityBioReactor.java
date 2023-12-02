package com.fulltrix.tjfcore.machines.multi;

import com.fulltrix.tjfcore.client.ClientHandler;
import com.fulltrix.tjfcore.item.TJFMetaBlocks;
import com.fulltrix.tjfcore.item.TJFMultiblockCasing2;
import com.fulltrix.tjfcore.item.TJFTransparentCasing;
import com.fulltrix.tjfcore.recipes.TJFRecipeMaps;
import gregicality.multiblocks.api.capability.impl.GCYMMultiblockRecipeLogic;
import gregicality.multiblocks.api.metatileentity.GCYMRecipeMapMultiblockController;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.BlockWorldState;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.client.renderer.texture.cube.SimpleCubeRenderer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.function.Predicate;

public class MetaTileEntityBioReactor extends RecipeMapMultiblockController {

    public MetaTileEntityBioReactor(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, TJFRecipeMaps.BIO_REACTOR_RECIPES);
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
                .where('X', states(getCasingState()).or(autoAbilities(true, true, true, true, true, true, false)))
                .where('L', states(getCasingState()).setMinGlobalLimited(34))
                .where('#', air())
                .where('G', states(TJFMetaBlocks.TRANSPARENT_CASING.getState(TJFTransparentCasing.CasingType.OSMIRIDIUM_GLASS)))
                .build();
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.maxVoltage = 0;
    }

    private IBlockState getCasingState() {
        return TJFMetaBlocks.MULTIBLOCK_CASING2.getState(TJFMultiblockCasing2.CasingType.BIO_REACTOR);
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