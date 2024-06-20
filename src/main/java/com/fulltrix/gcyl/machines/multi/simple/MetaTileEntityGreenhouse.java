package com.fulltrix.gcyl.machines.multi.simple;

import gregicality.multiblocks.api.metatileentity.GCYMRecipeMapMultiblockController;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.BlockMachineCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import static com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps.GREENHOUSE_RECIPES;

//TODO: GET A MATCHING TEXTURE FOR THE CASINGS AND BASE TEXTURE
//TODO: add tooltips and information
public class MetaTileEntityGreenhouse extends GCYMRecipeMapMultiblockController {

    private final int tier;
    public MetaTileEntityGreenhouse(ResourceLocation metaTileEntityId, int tier) {
        super(metaTileEntityId, GREENHOUSE_RECIPES);
        this.tier = tier;
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle(" CCC ", " CCC ", " CCC ", " CCC ")
                .aisle("CCCCC", "CDDDC", "C###C", "CGGGC")
                .aisle("CCCCC", "CDDDC", "C###C", "CGGGC")
                .aisle("CCCCC", "CDDDC", "C###C", "CGGGC")
                .aisle(" CCC ", " CSC ", " CCC ", " CCC ")
                .where('S', selfPredicate())
                .where('G', states(getGlassState()))
                .where('D', states(Blocks.DIRT.getDefaultState(), Blocks.GRASS.getDefaultState()))
                .where('C', states(getCasingState())
                        .setMinGlobalLimited(42)
                        .or(autoAbilities(true, true, true, true, true, true ,false)))
                .where('#', air())
                .build();
    }
    @Override
    public boolean isParallel() {
        return tier > 2;
    }
    @Override
    public boolean isTiered() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return tier > 2 ? Textures.VOLTAGE_CASINGS[8] : Textures.VOLTAGE_CASINGS[0];
    }

    protected IBlockState getGlassState() {
        return tier > 2 ? MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.LAMINATED_GLASS) : MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.TEMPERED_GLASS);
    }

    protected IBlockState getCasingState() {
        return tier > 2 ? MetaBlocks.MACHINE_CASING.getState(BlockMachineCasing.MachineCasingType.UV): MetaBlocks.MACHINE_CASING.getState(BlockMachineCasing.MachineCasingType.ULV);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityGreenhouse(metaTileEntityId, this.tier);
    }
}
