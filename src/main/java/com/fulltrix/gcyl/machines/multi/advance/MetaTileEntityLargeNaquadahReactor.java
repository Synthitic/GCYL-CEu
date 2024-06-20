package com.fulltrix.gcyl.machines.multi.advance;

import com.fulltrix.gcyl.item.metal.MetalCasing2;
import com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps;
import gregtech.api.GTValues;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.MultiblockFuelRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.*;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.unification.material.Materials;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockMultiblockCasing;
import gregtech.common.blocks.BlockTurbineCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.fulltrix.gcyl.client.ClientHandler.NAQUADRIA_CASING;
import static com.fulltrix.gcyl.item.GCYLMetaBlocks.METAL_CASING_2;
import static gregtech.api.unification.material.Materials.Naquadria;

//TODO finish implementing UI
//TODO add tooltips and information
public class MetaTileEntityLargeNaquadahReactor extends FuelMultiblockController implements IProgressBarMultiblock {

    private static final int tier = GTValues.UHV;

    public MetaTileEntityLargeNaquadahReactor(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GCYLRecipeMaps.NAQUADAH_REACTOR_FUELS, tier);
        this.recipeMapWorkable.setMaximumOverclockVoltage(GTValues.V[tier]);
        this.recipeMapWorkable = new NaquadahReactorWorkableHandler(this);
    }

    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityLargeNaquadahReactor(metaTileEntityId);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("#CCC#", "#CGC#", "#CCC#", "##C##", "##C##", "#CCC#", "#CGC#", "#CCC#")
                .aisle("CCCCC", "CPAPC", "CgAgC", "#PAP#", "#PAP#", "CgAgC", "CPAPC", "#CCC#")
                .aisle("CCCCC", "GAFAG", "CAFAC", "CAFAC", "CAFAC", "CAFAC", "GAFAG", "#CmC#")
                .aisle("CCCCC", "CPAPC", "CgAgC", "#PAP#", "#PAP#", "CgAgC", "CPAPC", "#CCC#")
                .aisle("#CCC#", "#CSC#", "#CCC#", "##C##", "##C##", "#CCC#", "#CGC#", "#CCC#")
                .where('S', selfPredicate())
                .where('C', states(getCasingState()).setMinGlobalLimited(78).or(autoAbilities(false,false,false,false,true,false,false).setMaxGlobalLimited(3))
                                .or(abilities(MultiblockAbility.OUTPUT_ENERGY).setExactLimit(1)))
                .where('G', states(MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING)))
                .where('g', states(MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.TUNGSTENSTEEL_GEARBOX)))
                .where('F', frames(Naquadria))
                .where('P', states(MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE)))
                .where('m', abilities(MultiblockAbility.MAINTENANCE_HATCH).setExactLimit(1))
                .where('A', air())
                .where('#', any())
                .build();
    }

    protected IBlockState getCasingState() {
        return METAL_CASING_2.getState(MetalCasing2.CasingType.NAQUADRIA);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return NAQUADRIA_CASING;
    }

    @Override
    protected boolean shouldShowVoidingModeButton() {
        return false;
    }

    @Override
    public int getNumProgressBars() {
        return 3;
    }

    @Override
    public double getFillPercentage(int index) {
        if (index == 0) {
            int[] fuelAmount = new int[2];
            if (getInputFluidInventory() != null) {
                MultiblockFuelRecipeLogic recipeLogic = (MultiblockFuelRecipeLogic) recipeMapWorkable;
                if (recipeLogic.getInputFluidStack() != null) {
                    FluidStack testStack = recipeLogic.getInputFluidStack().copy();
                    testStack.amount = Integer.MAX_VALUE;
                    fuelAmount = getTotalFluidAmount(testStack, getInputFluidInventory());
                }
            }
            return fuelAmount[1] != 0 ? 1.0 * fuelAmount[0] / fuelAmount[1] : 0;
        } else if (index == 1) {
            int[] tritiumAmount = new int[2];
            if (getInputFluidInventory() != null) {
                tritiumAmount = getTotalFluidAmount(Materials.Tritium.getFluid(Integer.MAX_VALUE),
                        getInputFluidInventory());
            }
            return tritiumAmount[1] != 0 ? 1.0 * tritiumAmount[0] / tritiumAmount[1] : 0;
        } else {
            int[] oxygenAmount = new int[2];
            if (getInputFluidInventory() != null) {
                    FluidStack oxygenStack = Materials.Oxygen.getPlasma(Integer.MAX_VALUE);
                    oxygenAmount = getTotalFluidAmount(oxygenStack, getInputFluidInventory());

            }
            return oxygenAmount[1] != 0 ? 1.0 * oxygenAmount[0] / oxygenAmount[1] : 0;
        }
    }

    private static class NaquadahReactorWorkableHandler extends MultiblockFuelRecipeLogic {

        private boolean isOxygenBoosted = false;

        private int cycles = 0;

        private final MetaTileEntityLargeNaquadahReactor naquadahReactor;

        private static final FluidStack OXYGEN_STACK = Materials.Oxygen.getPlasma(50);
        private static final FluidStack TRITIUM_STACK = Materials.Tritium.getFluid(1000);

        public NaquadahReactorWorkableHandler(RecipeMapMultiblockController tileEntity) {
            super(tileEntity);
            this.naquadahReactor = (MetaTileEntityLargeNaquadahReactor) tileEntity;
        }

        @Override
        protected void updateRecipeProgress() {
            if(canRecipeProgress && drawEnergy(recipeEUt, true)) {
                drainTritium();
                drainOxygen();
                drawEnergy(recipeEUt, false);

                if(++progressTime > maxProgressTime) {
                    completeRecipe();
                    if(cycles < 20)
                        cycles++;
                }
            }
        }

        protected void checkOxygen() {
            IMultipleTankHandler inputTank = naquadahReactor.getInputFluidInventory();
            FluidStack boosterStack = OXYGEN_STACK;
            isOxygenBoosted = boosterStack.isFluidStackIdentical(inputTank.drain(boosterStack, false));
        }

        protected void drainOxygen() {
            if (isOxygenBoosted && ++progressTime > maxProgressTime) {
                naquadahReactor.getInputFluidInventory().drain(OXYGEN_STACK, true);
            }
        }

        protected boolean checkTritium() {
            IMultipleTankHandler inputTank = naquadahReactor.getInputFluidInventory();
            if (TRITIUM_STACK.isFluidStackIdentical(inputTank.drain(TRITIUM_STACK, false))) {
                return true;
            } else {
                invalidate();
                return false;
            }
        }

        protected void drainTritium() {
            if (cycles == 20 || totalContinuousRunningTime == 0) {
                IMultipleTankHandler inputTank = naquadahReactor.getInputFluidInventory();
                inputTank.drain(TRITIUM_STACK, true);
                cycles = 0;
            }
        }

        @Override
        protected boolean shouldSearchForRecipes() {
            checkOxygen();
            return super.shouldSearchForRecipes() && checkTritium();
        }

        @Override
        public long getMaxVoltage() {
            // this multiplies consumption through parallel
            if (isOxygenBoosted)
                return GTValues.V[tier] * 2;
            else
                return GTValues.V[tier];
        }

        @Override
        protected long boostProduction(long production) {
            // this multiplies production without increasing consumption
            if (isOxygenBoosted)
                return production * 3/2;
            return production;
        }

        @Override
        public void invalidate() {
            isOxygenBoosted = false;
            super.invalidate();
        }

    }
}
