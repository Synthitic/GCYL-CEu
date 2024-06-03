package com.fulltrix.gcyl.machines.multi.advance;

import com.fulltrix.gcyl.GCYLMaterials;
import com.fulltrix.gcyl.client.ClientHandler;
import com.fulltrix.gcyl.item.metal.MetalCasing1;
import gregicality.multiblocks.api.capability.impl.GCYMMultiblockRecipeLogic;
import gregicality.multiblocks.api.metatileentity.GCYMRecipeMapMultiblockController;
import gregicality.multiblocks.api.render.GCYMTextures;
import gregicality.multiblocks.common.block.GCYMMetaBlocks;
import gregicality.multiblocks.common.block.blocks.BlockLargeMultiblockCasing;
import gregtech.api.GTValues;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.util.List;

import static com.fulltrix.gcyl.item.GCYLMetaBlocks.METAL_CASING_1;
import static gregtech.api.GTValues.ZPM;

//TODO: limit reflect in ui. update tooltip. improve performance

public class MetaTileEntityCryogenicFreezer extends GCYMRecipeMapMultiblockController {

    protected final MetaTileEntity metaTileEntity;

    public MetaTileEntityCryogenicFreezer(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, RecipeMaps.VACUUM_RECIPES);
        this.recipeMapWorkable = new MetaTileEntityCryogenicFreezer.CryogenicRecipeLogic(this);
        this.metaTileEntity = this;
        this.recipeMapWorkable.setMaximumOverclockVoltage(Math.min(this.energyContainer.getInputVoltage() , GTValues.V[ZPM]));
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity metaTileEntityHolder) {
        return new MetaTileEntityCryogenicFreezer(this.metaTileEntityId);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXX", "XXX", "XXX")
                .aisle("XXX", "X#X", "XXX")
                .aisle("XXX", "XSX", "XXX")
                .where('S', selfPredicate())
                .where('X', states(getCasingState()).setMinGlobalLimited(14).or(autoAbilities(true, true, true, true, true, true, false)))
                .where('#', air())
                .build();
    }

    private IBlockState getCasingState() {
        return GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING
                .getState(BlockLargeMultiblockCasing.CasingType.VIBRATION_SAFE_CASING);
    }

    @Override
    public boolean isTiered() {
        return false;
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GCYMTextures.VIBRATION_SAFE_CASING;
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return ClientHandler.FREEZER_OVERLAY;
    }



    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gregtech.multiblock.cryogenic_freezer.description"));
        tooltip.add(I18n.format("gtadditions.multiblock.universal.tooltip.1", this.recipeMap.getLocalizedName()));
        tooltip.add(I18n.format("gtadditions.multiblock.universal.tooltip.2", 20 / 100.0));
        tooltip.add(I18n.format("gtadditions.multiblock.universal.tooltip.3", 60 / 100.0));
        tooltip.add(I18n.format("gregtech.multiblock.vol_cryo.description"));
    }


    private class CryogenicRecipeLogic extends GCYMMultiblockRecipeLogic {

        private final MetaTileEntityCryogenicFreezer freezer;

        public CryogenicRecipeLogic(RecipeMapMultiblockController metaTileEntity) {
            super(metaTileEntity);
            this.freezer = (MetaTileEntityCryogenicFreezer) metaTileEntity;
        }

        protected FluidStack getCryotheum() {
            return GCYLMaterials.Cryotheum.getFluid((int) Math.pow(2, GTUtility.getTierByVoltage(this.freezer.energyContainer.getInputVoltage())));
        }

        protected boolean checkCryotheum() {
            IMultipleTankHandler inputTank = freezer.getInputFluidInventory();
            if(getCryotheum().isFluidStackIdentical(inputTank.drain(getCryotheum(), false))) {
                return true;
            }
            else {
                invalidate();
                return false;
            }
        }

        protected void drainCryotheum() {
            freezer.getInputFluidInventory().drain(getCryotheum(), true);
        }

        @Override
        protected boolean shouldSearchForRecipes() {
            return super.shouldSearchForRecipes() && checkCryotheum();
        }

        @Override
        protected boolean canProgressRecipe() {
            if(checkCryotheum() && freezer.isActive()) {
                drainCryotheum();
            }

            return super.canProgressRecipe() && checkCryotheum();
        }

        @Override
        public long getMaximumOverclockVoltage() {
            return Math.min(freezer.getEnergyContainer().getInputVoltage(), GTValues.V[ZPM]);
        }
    }
}
