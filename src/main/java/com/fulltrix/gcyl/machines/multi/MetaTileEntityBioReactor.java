package com.fulltrix.gcyl.machines.multi;

import com.fulltrix.gcyl.api.pattern.TraceabilityPredicates;
import com.fulltrix.gcyl.client.ClientHandler;
import com.fulltrix.gcyl.blocks.GCYLMetaBlocks;
import com.fulltrix.gcyl.blocks.GCYLMultiblockCasing2;
import com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps;
import gregicality.multiblocks.common.GCYMConfigHolder;
import gregicality.multiblocks.common.metatileentities.multiblockpart.MetaTileEntityTieredHatch;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public class MetaTileEntityBioReactor extends RecipeMapMultiblockController {

    private long maxVoltage;

    public MetaTileEntityBioReactor(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GCYLRecipeMaps.BIO_REACTOR_RECIPES);
        this.recipeMapWorkable = new MultiblockRecipeLogic(this) {
            @Override
            public long getMaxVoltage() {
                return GCYMConfigHolder.globalMultiblocks.enableTieredCasings ? maxVoltage : super.getMaxVoltage();
            }
        };
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityBioReactor(metaTileEntityId);
    }

    @Override
    protected BlockPattern createStructurePattern() {
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
                .where('T', TraceabilityPredicates.tieredHatchPredicate())
                .build();
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        int tier = context.getOrDefault("tiered_hatches", new ArrayList<MetaTileEntityTieredHatch>()).get(0).getTier() - 1;
        this.maxVoltage = 32L << tier * 2;
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