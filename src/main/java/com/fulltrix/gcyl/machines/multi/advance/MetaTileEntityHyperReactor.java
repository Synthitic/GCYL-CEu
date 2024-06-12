package com.fulltrix.gcyl.machines.multi.advance;

import com.fulltrix.gcyl.GCYLConfig;
import com.fulltrix.gcyl.item.GCYLMetaBlocks;
import com.fulltrix.gcyl.item.GCYLReactorCasing;
import com.fulltrix.gcyl.item.GCYLTransparentCasing;
import com.fulltrix.gcyl.item.metal.MetalCasing2;
import com.fulltrix.gcyl.recipes.GCYLRecipeMaps;
import gregtech.api.GTValues;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.MultiblockFuelRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.*;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.fulltrix.gcyl.client.ClientHandler.*;
import static com.fulltrix.gcyl.item.GCYLMetaBlocks.METAL_CASING_2;
import static gregtech.api.unification.material.Materials.Naquadria;

public class MetaTileEntityHyperReactor extends FuelMultiblockController implements IProgressBarMultiblock {

    //TODO finish implementing UI and cleanup

    private final int tier;

    private static final int a = GTUtility.getTierByVoltage(GCYLConfig.multis.hyperReactors.euGeneration[1]);
    private static final int b = GTUtility.getTierByVoltage(GCYLConfig.multis.hyperReactors.euGeneration[2]);
    private static final int c = GTUtility.getTierByVoltage(GCYLConfig.multis.hyperReactors.euGeneration[0]);

    private static final FluidStack boosterA = new FluidStack(FluidRegistry.getFluid(GCYLConfig.multis.hyperReactors.boosterFluid[1]), GCYLConfig.multis.hyperReactors.boosterFluidAmounts[1]);
    private static final FluidStack boosterB = new FluidStack(FluidRegistry.getFluid("plasma."+ GCYLConfig.multis.hyperReactors.boosterFluid[2]), GCYLConfig.multis.hyperReactors.boosterFluidAmounts[2]);
    private static final FluidStack boosterC = new FluidStack(FluidRegistry.getFluid(GCYLConfig.multis.hyperReactors.boosterFluid[0]), GCYLConfig.multis.hyperReactors.boosterFluidAmounts[0]);



    public MetaTileEntityHyperReactor(ResourceLocation metaTileEntityId, int tier) {
        super(metaTileEntityId, GCYLRecipeMaps.HYPER_REACTOR_FUELS, tier);
        this.tier = tier;
        this.recipeMapWorkable.setMaximumOverclockVoltage(GTValues.V[tier]);
        this.recipeMapWorkable = new HyperReactorWorkableHandler(this, tier);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityHyperReactor(metaTileEntityId, tier);
    }

    private int getIndex (int tier) {
        if (tier == a)
            return 1;
        else if(tier == b)
            return 2;
        else
            return 0;
    }

    @Override
    protected boolean shouldShowVoidingModeButton() {
        return false;
    }

    @NotNull
    private FluidStack getBooster(int tier) {
        Fluid temp;
        temp = FluidRegistry.getFluid(GCYLConfig.multis.hyperReactors.boosterFluid[getIndex(tier)]);

        return new FluidStack(Objects.requireNonNull(temp), GCYLConfig.multis.hyperReactors.boosterFluidAmounts[getIndex(tier)]);
    }

    @Override
    public double getFillPercentage(int index) {
        if(index == 0) {
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
        } else {
            int[] boosterAmount = new int[2];
            if (getInputFluidInventory() != null) {
                boosterAmount = getTotalFluidAmount(getBooster(tier),
                        getInputFluidInventory());
            }
            return boosterAmount[1] != 0 ? 1.0 * boosterAmount[0] / boosterAmount[1] : 0;
        }
    }


    @Override
    protected BlockPattern createStructurePattern() {
            if (tier == a) {
                return FactoryBlockPattern.start()
                        .aisle("#######C#######", "#####CCCCC#####", "#######C#######")
                        .aisle("####CCCCCCC####", "###CC#####CC###", "####CCCCCCC####")
                        .aisle("###CCCCCCCCC###", "##C##CCCCC##C##", "###CCCCCCCCC###")
                        .aisle("##CCC#####CCC##", "#C##C#####C##C#", "##CCC#####CCC##")
                        .aisle("#CCC#######CCC#", "#C#C#######C#C#", "#CCC#######CCC#")
                        .aisle("#CC#########CC#", "C#C#########C#C", "#CC#########CC#")
                        .aisle("#CC####F####CC#", "C#C####H####C#C", "#CC#########CC#")
                        .aisle("CCC###FHF###CCC", "C#C###HHH###C#C", "CCC####H####CCC")
                        .aisle("#CC####F####CC#", "C#C####H####C#C", "#CC#########CC#")
                        .aisle("#CC#########CC#", "C#C#########C#C", "#CC#########CC#")
                        .aisle("#CCC#######CCC#", "#C#C#######C#C#", "#CCC#######CCC#")
                        .aisle("##CCC#####CCC##", "#C##C#####C##C#", "##CCC#####CCC##")
                        .aisle("###CCCCCCCCC###", "##C##CCCCC##C##", "###CCCCCCCCC###")
                        .aisle("####CCCCCCC####", "###CC#####CC###", "####CCCCCCC####")
                        .aisle("#######C#######", "#####CCSCC#####", "#######C#######")
                        .where('C', states(getCasingState(a)).setMinGlobalLimited(220).or(autoAbilities(false,true,true,true,true,true,false)
                                .or(abilities(MultiblockAbility.OUTPUT_ENERGY).setExactLimit(1))))
                        .where('#', any())
                        .where('S', selfPredicate())
                        .where('F', frames(Naquadria))
                        .where('H', states(GCYLMetaBlocks.REACTOR_CASING.getState(GCYLReactorCasing.CasingType.HYPER_CORE_2)))
                        .where('c', states(getCasingState(a)).setMinGlobalLimited(250))
                        .build();
            } else if (tier == b) {
                return FactoryBlockPattern.start()
                        .aisle("###########","###########","###########","###########","###########", "###########", "###########", "###########", "####CCC####", "###CCCCC###", "###CCCCC###", "###CCCCC###", "####CCC####", "###########", "###########", "###########")
                        .aisle("###########","###########","###########","###########","###########", "###########", "###########", "###CCCCC###", "##CC###CC##", "##C#####C##", "##C#####C##", "##C#####C##", "##CC###CC##", "###CCCCC###", "###########", "###########")
                        .aisle("##F#####F##","##F#####F##","##F#####F##","##F#####F##","##F#####F##", "##F#####F##", "##FCCCCCF##", "##C#####C##", "#C#######C#", "#C#######C#", "#C#######C#", "#C#######C#", "#C#######C#", "##C#####C##", "###CCCCC###", "###########")
                        .aisle("###F###F###","###F###F###","###F###F###","###F###F###","###F###F###", "###FCCCF###", "##CC###CC##", "#C#######C#", "#C#######C#", "C#########C", "C####H####C", "C#########C", "#C#######C#", "#C#######C#", "##CC###CC##", "####CCC####")
                        .aisle("###########","###########","###########","###########","###########", "###CCCCC###", "##C#####C##", "#C#######C#", "C#########C", "G####H####G", "G###HHH###G", "G####H####G", "C#########C", "#C#######C#", "##C#####C##", "###CCCCC###")
                        .aisle("###########","###########","###########","###########","###########", "###CCCCC###", "##C#####C##", "#C#######C#", "C####H####C", "G###HHH###G", "G##HHHHH##G", "G###HHH###G", "C####H####C", "#C#######C#", "##C#####C##", "###CCCCC###")
                        .aisle("###########","###########","###########","###########","###########", "###CCCCC###", "##C#####C##", "#C#######C#", "C#########C", "G####H####G", "G###HHH###G", "G####H####G", "C#########C", "#C#######C#", "##C#####C##", "###CCCCC###")
                        .aisle("###F###F###","###F###F###","###F###F###","###F###F###","###F###F###", "###FCCCF###", "##CC###CC##", "#C#######C#", "#C#######C#", "C#########C", "C####H####C", "C#########C", "#C#######C#", "#C#######C#", "##CC###CC##", "####CCC####")
                        .aisle("##F#####F##","##F#####F##","##F#####F##","##F#####F##","##F#####F##", "##F#####F##", "##FCCCCCF##", "##C#####C##", "#C#######C#", "#C#######C#", "#C#######C#", "#C#######C#", "#C#######C#", "##C#####C##", "###CCCCC###", "###########")
                        .aisle("###########","###########","###########","###########","###########", "###########", "###########", "###CCCCC###", "##CC###CC##", "##C#####C##", "##C#####C##", "##C#####C##", "##CC###CC##", "###CCCCC###", "###########", "###########")
                        .aisle("###########","###########","###########","###########","###########", "###########", "###########", "###########", "####CCC####", "###CCCCC###", "###CCSCC###", "###CCCCC###", "####CCC####", "###########", "###########", "###########")
                        .where('C', states(getCasingState(b)).setMinGlobalLimited(250).or(autoAbilities(false,true,true,true,true,true,false)
                                .or(abilities(MultiblockAbility.OUTPUT_ENERGY).setExactLimit(1))))
                        .where('#', any())
                        .where('S', selfPredicate())
                        .where('F', frames(Naquadria))
                        .where('H', states(GCYLMetaBlocks.REACTOR_CASING.getState(GCYLReactorCasing.CasingType.HYPER_CORE_3)))
                        .where('G', states(MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.LAMINATED_GLASS)))
                        .where('c', states(getCasingState(b)).setMinGlobalLimited(250))
                        .build();
            }
            else {
                return FactoryBlockPattern.start()
                        .aisle("CCCCC", "CGGGC", "CGGGC", "CGGGC", "CCCCC")
                        .aisle("CCCCC", "G###G", "G#H#G", "G###G", "CCCCC")
                        .aisle("CCCCC", "G#H#G", "GHHHG", "G#H#G", "CCCCC")
                        .aisle("CCCCC", "G###G", "G#H#G", "G###G", "CCCCC")
                        .aisle("CCSCC", "CGGGC", "CGGGC", "CGGGC", "CCCCC")
                        .where('S', selfPredicate())
                        .where('C', states(getCasingState(c)).setMinGlobalLimited(55).or(autoAbilities(false,true,true,true,true,true,false)
                                .or(abilities(MultiblockAbility.OUTPUT_ENERGY).setExactLimit(1))))
                        .where('G', states(MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.LAMINATED_GLASS)))
                        .where('H', states(GCYLMetaBlocks.REACTOR_CASING.getState(GCYLReactorCasing.CasingType.HYPER_CORE)))
                        .where('#', air())
                        .build();
            }
    }


    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        if (tier == a)
            return HYPER_CASING;
        else if(tier == b)
            return HYPER_CASING_2;
        else
            return NAQUADRIA_CASING;
    }

    /*
    public static @NotNull TraceabilityPredicate tieredCasing() {
        return new TraceabilityPredicate(abilities(GCYMMultiblockAbility.TIERED_HATCH)
                .setMinGlobalLimited(GCYMConfigHolder.globalMultiblocks.enableTieredCasings ? 1 : 0));
    }

     */

    protected IBlockState getCasingState(int tier) {
        if (tier == a)
            return GCYLMetaBlocks.REACTOR_CASING.getState(GCYLReactorCasing.CasingType.HYPER_CASING);
        else if(tier == b)
            return GCYLMetaBlocks.REACTOR_CASING.getState(GCYLReactorCasing.CasingType.HYPER_CASING_2);
        else
            return METAL_CASING_2.getState(MetalCasing2.CasingType.NAQUADRIA);
    }

    private static class HyperReactorWorkableHandler extends MultiblockFuelRecipeLogic {

        private final int tier;
        private boolean isBoosted = false;
        private final MetaTileEntityHyperReactor hyperReactor;

        public HyperReactorWorkableHandler(RecipeMapMultiblockController tileEntity, int tier) {
            super(tileEntity);
            this.hyperReactor = (MetaTileEntityHyperReactor) tileEntity;
            this.tier = tier;
        }

        @Override
        protected void updateRecipeProgress() {
            if (canRecipeProgress && drawEnergy(recipeEUt, true)) {
                drainBooster();
                drawEnergy(recipeEUt, false);

                // as recipe starts with progress on 1 this has to be > only not => to compensate for it
                if (++progressTime > maxProgressTime) {
                    completeRecipe();
                }
            }
        }

        protected void drainBooster() {
            if (isBoosted && totalContinuousRunningTime % 20 == 0) {
                FluidStack boosterStack = getBoosterFromTier(tier);
                hyperReactor.getInputFluidInventory().drain(boosterStack, true);
            }
        }

        private FluidStack getBoosterFromTier(int tier) {
            if(tier == a) {
                return boosterA;
            }
            else if (tier == b)
                return boosterB;
            else
                return boosterC;
        }

        protected void checkBooster() {
                IMultipleTankHandler inputTank = hyperReactor.getInputFluidInventory();
                FluidStack boosterStack = getBoosterFromTier(tier);
                isBoosted = boosterStack.isFluidStackIdentical(inputTank.drain(boosterStack, false));
        }

        @Override
        protected boolean shouldSearchForRecipes() {
            checkBooster();
            return super.shouldSearchForRecipes();
        }

        @Override
        public long getMaxVoltage() {
            // this multiplies consumption through parallel
            if (isBoosted)
                return GTValues.V[tier] * GCYLConfig.multis.hyperReactors.boostedFuelAmount[hyperReactor.getIndex(tier)];
            else
                return GTValues.V[tier];
        }

        @Override
        protected long boostProduction(long production) {
            // this multiplies production without increasing consumption
            if (isBoosted)
                return production * GCYLConfig.multis.hyperReactors.boostedEuAmount[hyperReactor.getIndex(tier)];

            return production;
        }

        @Override
        public void invalidate() {
            isBoosted = false;
            super.invalidate();
        }

    }

}
