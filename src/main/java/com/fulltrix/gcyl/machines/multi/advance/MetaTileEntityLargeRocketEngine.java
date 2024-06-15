package com.fulltrix.gcyl.machines.multi.advance;

import com.fulltrix.gcyl.item.metal.MetalCasing1;
import com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps;
import gregtech.api.GTValues;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.MultiblockFuelRecipeLogic;
import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.*;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.unification.material.Materials;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.blocks.BlockMultiblockCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import static com.fulltrix.gcyl.client.ClientHandler.NITINOL_60_CASING;
import static com.fulltrix.gcyl.item.GCYLMetaBlocks.METAL_CASING_1;
import static gregtech.api.GTValues.LuV;
import static gregtech.api.util.RelativeDirection.*;

//TODO: implement ui, tooltip

public class MetaTileEntityLargeRocketEngine extends FuelMultiblockController implements IProgressBarMultiblock {

    public MetaTileEntityLargeRocketEngine(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GCYLRecipeMaps.ROCKET_FUEL_RECIPES, LuV);
        this.recipeMapWorkable.setMaximumOverclockVoltage(GTValues.V[LuV]);
        this.recipeMapWorkable = new MetaTileEntityLargeRocketEngine.LREWorkableHandler(this);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(LEFT, DOWN, BACK)
                .aisle("CCC", "CEC", "CCC")
                .aisle("CAC", "F#F", "CCC").setRepeatable(8)
                .aisle("KKK", "KSK", "KKK")
                .where('S', selfPredicate())
                .where('C', states(getCasingState()))
                .where('K', states(getCasingState()).or(abilities(MultiblockAbility.MAINTENANCE_HATCH).setMaxGlobalLimited(1)))
                .where('E', states(getCasingState()).or(abilities(MultiblockAbility.OUTPUT_ENERGY)))
                .where('F', states(getCasingState()).or(abilities(MultiblockAbility.IMPORT_FLUIDS)))
                .where('A', states(MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.ENGINE_INTAKE_CASING)))
                .where('#', air())
                .build();
    }

    protected IBlockState getCasingState() {
        return METAL_CASING_1.getState(MetalCasing1.CasingType.NITINOL_60);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return NITINOL_60_CASING;
    }

    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityLargeRocketEngine(metaTileEntityId);
    }

    @Override
    public double getFillPercentage(int i) {
        return 0;
    }


    public static class LREWorkableHandler extends MultiblockFuelRecipeLogic {

        private boolean isOxygenBoosted = false;

        private final MetaTileEntityLargeRocketEngine rocketEngine;

        private final int tier = LuV;

        private static final FluidStack OXYGEN_STACK = Materials.Oxygen.getFluid(FluidStorageKeys.LIQUID,50);
        private static final FluidStack AIR_STACK = Materials.Air.getFluid(37500);

        public LREWorkableHandler(RecipeMapMultiblockController tileEntity) {
            super(tileEntity);
            this.rocketEngine = (MetaTileEntityLargeRocketEngine) tileEntity;
        }

        @Override
        protected void updateRecipeProgress() {
            if(canRecipeProgress && drawEnergy(recipeEUt, true)) {
                drainOxygen();
                drawEnergy(recipeEUt, false);

                if(++progressTime > maxProgressTime) {
                    completeRecipe();
                    drainAir();
                }
            }
        }

        protected void checkOxygen() {
            IMultipleTankHandler inputTank = rocketEngine.getInputFluidInventory();
            FluidStack boosterStack = OXYGEN_STACK;
            isOxygenBoosted = boosterStack.isFluidStackIdentical(inputTank.drain(boosterStack, false));
        }

        protected void drainOxygen() {
            if (isOxygenBoosted && totalContinuousRunningTime % 20 == 0) {
                rocketEngine.getInputFluidInventory().drain(OXYGEN_STACK, true);
            }
        }

        protected boolean checkAir() {
            // check lubricant and invalidate if it fails
            IMultipleTankHandler inputTank = rocketEngine.getInputFluidInventory();
            if (AIR_STACK.isFluidStackIdentical(inputTank.drain(AIR_STACK, false))) {
                return true;
            } else {
                invalidate();
                return false;
            }
        }

        protected void drainAir() {
                IMultipleTankHandler inputTank = rocketEngine.getInputFluidInventory();
                inputTank.drain(AIR_STACK, true);
        }

        @Override
        protected boolean shouldSearchForRecipes() {
            checkOxygen();
            return super.shouldSearchForRecipes() && checkAir();
        }

        @Override
        protected boolean canProgressRecipe() {
            return super.canProgressRecipe() && checkAir();
        }

        @Override
        public long getMaxVoltage() {
            // this multiplies consumption through parallel
            if (isOxygenBoosted)
                return GTValues.V[tier] * 3;
            else
                return GTValues.V[tier];
        }

        @Override
        protected long boostProduction(long production) {
            // this multiplies production without increasing consumption
            if (isOxygenBoosted)
                return production;

            return production;
        }

        @Override
        public void invalidate() {
            isOxygenBoosted = false;
            super.invalidate();
        }
    }
}
