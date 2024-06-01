package com.fulltrix.gcyl.machines.multi.simple;

import com.fulltrix.gcyl.item.metal.MetalCasing1;
import com.fulltrix.gcyl.recipes.GCYLRecipeMaps;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockTurbineCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

import static com.fulltrix.gcyl.client.ClientHandler.HASTELLOY_N_CASING;
import static com.fulltrix.gcyl.item.GCYLMetaBlocks.METAL_CASING_1;

public class MetaTileEntityPlasmaCondenser extends GCYLRecipeMapMultiblockController {

    public MetaTileEntityPlasmaCondenser(ResourceLocation metaTileENtityId, boolean isParallel) {
        super(metaTileENtityId, GCYLRecipeMaps.PLASMA_CONDENSER_RECIPES, isParallel);
        this.recipeMapWorkable = new MultiblockRecipeLogic(this);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityPlasmaCondenser(metaTileEntityId, this.isParallel());
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("#####", "#XXX#", "#XXX#", "#XXX#", "#####")
                .aisle("#XXX#", "XGAGX", "XAPAX", "XGpGX", "#XXX#")
                .aisle("#XXX#", "XAPAX", "XPPPX", "XpPpX", "#XXX#")
                .aisle("#XXX#", "XGAGX", "XAPAX", "XGpGX", "#XXX#")
                .aisle("#####", "#XXX#", "#XSX#", "#XXX#", "#####")
                .where('S', selfPredicate())
                .where('X', states(getCasingState()).setMinGlobalLimited(25).or(autoAbilities(true,true,true,true,true,true,false)))
                .where('G', states(MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.STEEL_GEARBOX)))
                .where('P', states(MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE)))
                .where('A', air())
                .where('#', any())
                .where('p', tieredCasing())
                .build();
    }

    private IBlockState getCasingState() {
        return METAL_CASING_1.getState(MetalCasing1.CasingType.HASTELLOY_N);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return HASTELLOY_N_CASING;
    }

    @SideOnly(Side.CLIENT)
    @Nonnull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.ARC_FURNACE_OVERLAY;
    }

}
