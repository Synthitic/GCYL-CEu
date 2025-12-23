package com.fulltrix.gcyl.machines.multi.generator;

import com.cleanroommc.modularui.api.drawable.IKey;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;
import com.cleanroommc.modularui.value.sync.StringSyncValue;
import com.fulltrix.gcyl.GCYLConfig;
import com.fulltrix.gcyl.blocks.GCYLMetaBlocks;
import com.fulltrix.gcyl.blocks.GCYLReactorCasing;
import com.fulltrix.gcyl.blocks.metal.MetalCasing2;
import com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps;
import gregtech.api.GTValues;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.MultiblockFuelRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.*;
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIBuilder;
import gregtech.api.metatileentity.multiblock.ui.TemplateBarBuilder;
import gregtech.api.mui.GTGuiTextures;
import gregtech.api.mui.sync.FixedIntArraySyncValue;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.recipes.Recipe;
import gregtech.api.util.GTUtility;
import gregtech.api.util.KeyUtil;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.core.unification.material.internal.MaterialRegistryManager;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.UnaryOperator;

import static com.fulltrix.gcyl.api.GCYLUtility.getFluidStorageKeyByName;
import static com.fulltrix.gcyl.client.ClientHandler.*;
import static com.fulltrix.gcyl.blocks.GCYLMetaBlocks.METAL_CASING_2;
import static gregtech.api.unification.material.Materials.*;

public class MetaTileEntityHyperReactor extends FuelMultiblockController implements ProgressBarMultiblock {

    private final int tier;

    private static final int a = GTUtility.getTierByVoltage(GCYLConfig.multis.hyperReactors.euGeneration[1]);
    private static final int b = GTUtility.getTierByVoltage(GCYLConfig.multis.hyperReactors.euGeneration[2]);
    private static final int c = GTUtility.getTierByVoltage(GCYLConfig.multis.hyperReactors.euGeneration[0]);

    private static final FluidStack boosterA = MaterialRegistryManager.getInstance().getMaterial(GCYLConfig.multis.hyperReactors.boosterFluid[1]).getFluid(getFluidStorageKeyByName(GCYLConfig.multis.hyperReactors.boosterFluidStates[1]), GCYLConfig.multis.hyperReactors.boosterFluidAmounts[1]);
    private static final FluidStack boosterB = MaterialRegistryManager.getInstance().getMaterial(GCYLConfig.multis.hyperReactors.boosterFluid[2]).getFluid(getFluidStorageKeyByName(GCYLConfig.multis.hyperReactors.boosterFluidStates[2]), GCYLConfig.multis.hyperReactors.boosterFluidAmounts[2]);
    private static final FluidStack boosterC = MaterialRegistryManager.getInstance().getMaterial(GCYLConfig.multis.hyperReactors.boosterFluid[0]).getFluid(getFluidStorageKeyByName(GCYLConfig.multis.hyperReactors.boosterFluidStates[0]), GCYLConfig.multis.hyperReactors.boosterFluidAmounts[0]);

    private static final FluidStack[] boosters = {boosterC, boosterA, boosterB};
    private static final FluidStack[] gases = {Krypton.getFluid(10000), Xenon.getFluid(10000), Radon.getFluid(10000)};

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
    public boolean shouldShowVoidingModeButton() {
        return false;
    }

    @NotNull
    private FluidStack getBooster(int tier) {
        return boosters[this.getIndex(tier)];
    }

    public FluidStack getGas(int tier) {
        return gases[this.getIndex(tier)];
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

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gcyl.multiblock.hyper_reactor.tooltip.3", this.getGas(this.tier).getLocalizedName()));
        tooltip.add(I18n.format("gcyl.multiblock.hyper_reactor.tooltip.1", this.getBooster(this.tier).getLocalizedName()));
        tooltip.add(I18n.format("gcyl.multiblock.hyper_reactor.tooltip.2", GTValues.VN[this.tier]));
    }

    @Override
    public @NotNull List<ITextComponent> getDataInfo() {
        List<ITextComponent> list = super.getDataInfo();
        if (((HyperReactorWorkableHandler) this.recipeMapWorkable).isBoosted)
            list.add(new TextComponentTranslation("gregtech.multiblock.universal.generator.boosted", this.getBooster(this.tier).getLocalizedName()));
        return list;
    }

    @Override
    protected void configureDisplayText(MultiblockUIBuilder builder) {
        super.configureDisplayText(builder);
        HyperReactorWorkableHandler recipeLogic = (HyperReactorWorkableHandler) recipeMapWorkable;
        builder.addProgressLine(recipeLogic.getProgress(), recipeLogic.getMaxProgress());
        builder.addCustom((keyManager, uiSyncer) -> {
            long EUt = uiSyncer.syncLong(recipeLogic.getInfoProviderEUt());
            int tier = GTUtility.getFloorTierByVoltage(EUt);
            if (EUt > 0)
                keyManager.add(IKey.lang("gregtech.multiblock.universal.energy.production", EUt, GTValues.VOCNF[tier]));
            if (uiSyncer.syncBoolean(recipeLogic.isBoosted))
                keyManager.add(KeyUtil.lang(TextFormatting.AQUA, "gregtech.multiblock.universal.generator.boosted", this.getBooster(this.tier).getLocalizedName()));
            if (this.isStructureFormed())
                keyManager.add(IKey.lang("gcyl.multiblock.large_naquadah_reactor.cycles", uiSyncer.syncInt(20 - recipeLogic.getCycles())));
        });
    }

    @Override
    public int getProgressBarCount() {
        return 3;
    }

    @Override
    public void registerBars(List<UnaryOperator<TemplateBarBuilder>> bars, PanelSyncManager syncManager) {
        StringSyncValue fuelNameValue = new StringSyncValue(() -> {
            FluidStack stack = ((MultiblockFuelRecipeLogic) recipeMapWorkable).getInputFluidStack();
            return stack == null || stack.getFluid() == null ? null : stack.getFluid().getName();
        });
        syncManager.syncValue("fuel_name", fuelNameValue);
        FixedIntArraySyncValue fuelValue = new FixedIntArraySyncValue(this::getFuelAmount, null);
        syncManager.syncValue("fuel_value", fuelValue);
        FixedIntArraySyncValue gasValue = new FixedIntArraySyncValue(this::getGasAmount, null);
        syncManager.syncValue("gas_value", gasValue);
        FixedIntArraySyncValue boosterValue = new FixedIntArraySyncValue(this::getBoosterAmount, null);
        syncManager.syncValue("booster_value", boosterValue);

        bars.add(bar -> bar.progress(() -> fuelValue.getValue(1) == 0 ? 0 : 1.0 * fuelValue.getValue(0) / fuelValue.getValue(1))
                .texture(GTGuiTextures.PROGRESS_BAR_LCE_FUEL)
                .tooltipBuilder(tooltip -> this.createFuelTooltip(tooltip, fuelValue, fuelNameValue)));
        bars.add(bar -> bar.progress(() -> gasValue.getValue(1) == 0 ? 0 : 1.0 * gasValue.getValue(0) / gasValue.getValue(1))
                .texture(GTGuiTextures.PROGRESS_BAR_LCE_LUBRICANT)
                .tooltipBuilder(tooltip -> tooltip.addLine(!this.isStructureFormed() ? IKey.lang("gregtech.multiblock.invalid_structure")
                        : gasValue.getValue(0) == 0 ? IKey.lang("gregtech.multiblock.large_combustion_engine.oxygen_none")
                        : IKey.lang("gregtech.multiblock.universal.fluid_amount", this.getGas(this.tier).getLocalizedName(), gasValue.getValue(0), gasValue.getValue(1)))));
        bars.add(bar -> bar.progress(() -> boosterValue.getValue(1) == 0 ? 0 : 1.0 * boosterValue.getValue(0) / boosterValue.getValue(1))
                .texture(GTGuiTextures.PROGRESS_BAR_LCE_OXYGEN)
                .tooltipBuilder(tooltip -> tooltip.addLine(!this.isStructureFormed() ? IKey.lang("gregtech.multiblock.invalid_structure")
                        : boosterValue.getValue(0) == 0 ? IKey.lang("gregtech.multiblock.large_combustion_engine.oxygen_none")
                        : IKey.lang("gregtech.multiblock.universal.fluid_amount", this.getBooster(this.tier).getLocalizedName(), boosterValue.getValue(0), boosterValue.getValue(1)))));
    }

    /**
     * @return an array of [fuel stored, fuel capacity]
     */
    private int[] getFuelAmount() {
        if (this.getInputFluidInventory() != null) {
            MultiblockFuelRecipeLogic recipeLogic = (MultiblockFuelRecipeLogic) recipeMapWorkable;
            if (recipeLogic.getInputFluidStack() != null) {
                FluidStack testStack = recipeLogic.getInputFluidStack().copy();
                testStack.amount = Integer.MAX_VALUE;
                return this.getTotalFluidAmount(testStack, this.getInputFluidInventory());
            }
        }
        return new int[2];
    }

    /**
     * @return an array of [fuel stored, fuel capacity]
     */
    private int[] getGasAmount() {
        FluidStack gas = this.getGas(this.tier).copy();
        gas.amount = Integer.MAX_VALUE;
        return this.getInputFluidInventory() != null ? this.getTotalFluidAmount(gas, this.getInputFluidInventory()) : new int[2];
    }

    /**
     * @return an array of [fuel stored, fuel capacity]
     */
    private int[] getBoosterAmount() {
        FluidStack booster = this.getBooster(this.tier).copy();
        booster.amount = Integer.MAX_VALUE;
        return this.getInputFluidInventory() != null ? this.getTotalFluidAmount(booster, this.getInputFluidInventory()) : new int[2];
    }

    private static class HyperReactorWorkableHandler extends MultiblockFuelRecipeLogic {

        private final int tier;
        private boolean isBoosted = false;
        private final MetaTileEntityHyperReactor hyperReactor;
        private int cycles = 20;

        public HyperReactorWorkableHandler(RecipeMapMultiblockController tileEntity, int tier) {
            super(tileEntity);
            this.hyperReactor = (MetaTileEntityHyperReactor) tileEntity;
            this.tier = tier;
        }

        @Override
        protected void updateRecipeProgress() {
            if (canRecipeProgress && drawEnergy(recipeEUt, true)) {
                drawEnergy(recipeEUt, false);
                // as recipe starts with progress on 1 this has to be > only not => to compensate for it
                if (++progressTime > maxProgressTime) {
                    completeRecipe();
                }
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

        @Override
        public boolean checkRecipe(@NotNull Recipe recipe) {
            if (!super.checkRecipe(recipe))
                return false;
            IMultipleTankHandler tanks = this.hyperReactor.getInputFluidInventory();
            if (this.cycles >= 19) {
                FluidStack gas = this.hyperReactor.getGas(this.tier);
                if (gas.isFluidStackIdentical(tanks.drain(gas, false))) {
                    tanks.drain(gas, true);
                    this.cycles = 0;
                } else {
                    if (this.cycles < 20)
                        this.cycles++;
                    return false;
                }
            } else this.cycles++;
            FluidStack booster = this.getBoosterFromTier(this.tier);
            if (this.isBoosted = booster.isFluidStackIdentical(tanks.drain(booster, false)))
                tanks.drain(booster, true);
            return true;
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

        @Override
        public @NotNull NBTTagCompound serializeNBT() {
            NBTTagCompound compound = super.serializeNBT();
            compound.setBoolean("isBoosted", this.isBoosted);
            compound.setInteger("cycles", this.cycles);
            return compound;
        }

        @Override
        public void deserializeNBT(@NotNull NBTTagCompound compound) {
            super.deserializeNBT(compound);
            this.isBoosted = compound.getBoolean("isBoosted");
            this.cycles = compound.getInteger("cycles");
        }

        public int getCycles() {
            return this.cycles;
        }
    }

}
