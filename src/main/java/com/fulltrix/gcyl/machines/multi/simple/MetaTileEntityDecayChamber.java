package com.fulltrix.gcyl.machines.multi.simple;

import com.fulltrix.gcyl.api.multi.GCYLMultiblockRecipeLogic;
import com.fulltrix.gcyl.api.multi.GCYLRecipeMapMultiblockController;
import com.fulltrix.gcyl.api.pattern.TraceabilityPredicates;
import com.fulltrix.gcyl.client.ClientHandler;
import com.fulltrix.gcyl.blocks.GCYLMetaBlocks;
import com.fulltrix.gcyl.blocks.GCYLReactorCasing;
import gregicality.multiblocks.common.GCYMConfigHolder;
import gregicality.multiblocks.common.metatileentities.multiblockpart.MetaTileEntityTieredHatch;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps.DECAY_CHAMBERS_RECIPES;


//TODO: add neutron mechanics
//TODO: add tooltips and information
public class MetaTileEntityDecayChamber extends GCYLRecipeMapMultiblockController {

    private long maxVoltage;

    public MetaTileEntityDecayChamber(ResourceLocation metaTileEntityId, boolean isParallel) {
        super(metaTileEntityId, DECAY_CHAMBERS_RECIPES, isParallel);
        this.recipeMapWorkable = new GCYLMultiblockRecipeLogic(this, false) {
            @Override
            public long getMaxVoltage() {
                return GCYMConfigHolder.globalMultiblocks.enableTieredCasings ? maxVoltage : super.getMaxVoltage();
            }
        };
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("CCC","CGC","CGC","CGC","CCC")
                .aisle("CCC","GTG","G#G","GTG","CCC")
                .aisle("CSC","CGC","CGC","CGC","CCC")
                .where('C', states(getCasingState()).setMinGlobalLimited(20).or(autoAbilities()))
                .where('S', selfPredicate())
                .where('G', states(getGlassState()))
                .where('#', air())
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

    public IBlockState getCasingState() {
        return GCYLMetaBlocks.REACTOR_CASING.getState(GCYLReactorCasing.CasingType.CLADDED_REACTOR_CASING);
    }

    private IBlockState getGlassState() {
        return MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.LAMINATED_GLASS);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return ClientHandler.CLADDED_REACTOR_CASING;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityDecayChamber(metaTileEntityId, this.isParallel());
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.REPLICATOR_OVERLAY;
    }
}
