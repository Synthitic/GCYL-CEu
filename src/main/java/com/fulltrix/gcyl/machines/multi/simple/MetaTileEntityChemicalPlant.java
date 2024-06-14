package com.fulltrix.gcyl.machines.multi.simple;

import com.fulltrix.gcyl.api.multi.GCYLMultiblockRecipeLogic;
import com.fulltrix.gcyl.api.multi.GCYLRecipeMapMultiblockController;
import com.fulltrix.gcyl.recipes.GCYLRecipeMaps;
import gregicality.multiblocks.api.capability.impl.GCYMMultiblockRecipeLogic;
import gregtech.api.capability.IHeatingCoil;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

//TODO implement coil bonus
//TODO make a separate mega chemical reactor so this one isnt so broken

public class MetaTileEntityChemicalPlant extends GCYLRecipeMapMultiblockController implements IHeatingCoil {

    private int temperature;

    public MetaTileEntityChemicalPlant(ResourceLocation metaTileEntityId, boolean isParallel) {
        super(metaTileEntityId, new RecipeMap[] { GCYLRecipeMaps.CHEMICAL_PLANT_RECIPES, RecipeMaps.LARGE_CHEMICAL_RECIPES }, isParallel);
        this.recipeMapWorkable = new GCYLMultiblockRecipeLogic(this, true);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityChemicalPlant(metaTileEntityId, this.isParallel());
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("X###X", "XXXXX", "X###X", "XXXXX", "X###X")
                .aisle("XXXXX", "XCCCX", "XPPPX", "XCCCX", "XXXXX")
                .aisle("X###X", "XPPPX", "XMMMX", "XPPPX", "X###X")
                .aisle("XXXXX", "XCCCX", "XPPPX", "XCCCX", "XXXXX")
                .aisle("X###X", "SXXXX", "X###X", "XXXXX", "X###X")
                .where('S', selfPredicate())
                .where('X', states(getCasingState()).setMinGlobalLimited(60).or(autoAbilities(true,true,true,true,true,true,false)))
                .where('C', heatingCoils())
                .where('P', states(MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.POLYTETRAFLUOROETHYLENE_PIPE)))
                .where('#', air())
                .where('M', tieredCasing())
                .build();
    }

    public int getCurrentTemperature() {return this.temperature;}

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return Textures.INERT_PTFE_CASING;
    }

    public IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.PTFE_INERT_CASING);
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.LARGE_CHEMICAL_REACTOR_OVERLAY;
    }
}
