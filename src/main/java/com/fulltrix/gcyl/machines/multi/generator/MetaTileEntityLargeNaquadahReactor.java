package com.fulltrix.gcyl.machines.multi.generator;

import com.cleanroommc.modularui.api.drawable.IKey;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;
import com.cleanroommc.modularui.value.sync.StringSyncValue;
import com.fulltrix.gcyl.blocks.metal.MetalCasing2;
import com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps;
import gregtech.api.GTValues;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.MultiblockFuelRecipeLogic;
import gregtech.api.fluids.store.FluidStorageKeys;
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
import gregtech.api.unification.material.Materials;
import gregtech.api.util.GTUtility;
import gregtech.api.util.KeyUtil;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockMultiblockCasing;
import gregtech.common.blocks.BlockTurbineCasing;
import gregtech.common.blocks.MetaBlocks;
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

import static com.fulltrix.gcyl.client.ClientHandler.NAQUADRIA_CASING;
import static com.fulltrix.gcyl.blocks.GCYLMetaBlocks.METAL_CASING_2;
import static gregtech.api.unification.material.Materials.Naquadria;


public class MetaTileEntityLargeNaquadahReactor extends FuelMultiblockController implements ProgressBarMultiblock {

    private static final int tier = GTValues.UEV;

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

    @Override
    public @NotNull List<ITextComponent> getDataInfo() {
        List<ITextComponent> list = super.getDataInfo();
        if (((NaquadahReactorWorkableHandler) this.recipeMapWorkable).isOxygenBoosted)
            list.add(new TextComponentTranslation("gregtech.multiblock.universal.generator.boosted", NaquadahReactorWorkableHandler.OXYGEN_STACK.getLocalizedName()));
        return list;
    }

    @Override
    protected void configureDisplayText(MultiblockUIBuilder builder) {
        super.configureDisplayText(builder);
        NaquadahReactorWorkableHandler recipeLogic = (NaquadahReactorWorkableHandler) recipeMapWorkable;
        builder.addProgressLine(recipeLogic.getProgress(), recipeLogic.getMaxProgress());
        builder.addCustom((keyManager, uiSyncer) -> {
            long EUt = uiSyncer.syncLong(recipeLogic.getInfoProviderEUt());
            int tier = GTUtility.getFloorTierByVoltage(EUt);
            if (EUt > 0)
                keyManager.add(IKey.lang("gregtech.multiblock.universal.energy.production", EUt, GTValues.VOCNF[tier]));
            if (uiSyncer.syncBoolean(recipeLogic.isOxygenBoosted))
                keyManager.add(KeyUtil.lang(TextFormatting.AQUA, "gregtech.multiblock.universal.generator.boosted", NaquadahReactorWorkableHandler.OXYGEN_STACK.getLocalizedName()));
            if (this.isStructureFormed())
                keyManager.add(IKey.lang("gcyl.multiblock.large_naquadah_reactor.cycles", uiSyncer.syncInt(20 - recipeLogic.getCycles())));
        });
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gcyl.multiblock.large_naquadah_reactor.tooltip.1"));
        tooltip.add(I18n.format("gcyl.multiblock.large_naquadah_reactor.tooltip.2"));
        tooltip.add(I18n.format("gcyl.multiblock.large_naquadah_reactor.tooltip.3"));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return NAQUADRIA_CASING;
    }

    @Override
    public boolean shouldShowVoidingModeButton() {
        return false;
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
        syncManager.syncValue("fuel_amount", fuelValue);
        FixedIntArraySyncValue tritiumValue = new FixedIntArraySyncValue(this::getTritiumAmount, null);
        syncManager.syncValue("tritium_amount", tritiumValue);
        FixedIntArraySyncValue oxygenPlasmaValue = new FixedIntArraySyncValue(this::getOxygenPlasmaAmount, null);
        syncManager.syncValue("oxygen_plasma_amount", oxygenPlasmaValue);

        bars.add(bar -> bar.progress(() -> fuelValue.getValue(1) == 0 ? 0 : 1.0 * fuelValue.getValue(0) / fuelValue.getValue(1))
                .texture(GTGuiTextures.PROGRESS_BAR_LCE_FUEL)
                .tooltipBuilder(tooltip -> this.createFuelTooltip(tooltip, fuelValue, fuelNameValue)));
        bars.add(bar -> bar.progress(() -> tritiumValue.getValue(1) == 0 ? 0 : 1.0 * tritiumValue.getValue(0) / tritiumValue.getValue(1))
                .texture(GTGuiTextures.PROGRESS_BAR_LCE_LUBRICANT)
                .tooltipBuilder(tooltip -> tooltip.addLine(!this.isStructureFormed() ? IKey.lang("gregtech.multiblock.invalid_structure")
                        : tritiumValue.getValue(0) == 0 ? IKey.lang("gcyl.multiblock.large_naquadah_reactor.tritium_none")
                        : IKey.lang("gcyl.multiblock.large_naquadah_reactor.tritium_amount", tritiumValue.getValue(0), tritiumValue.getValue(1)))));
        bars.add(bar -> bar.progress(() -> oxygenPlasmaValue.getValue(1) == 0 ? 0 : 1.0 * oxygenPlasmaValue.getValue(0) / oxygenPlasmaValue.getValue(1))
                .texture(GTGuiTextures.PROGRESS_BAR_LCE_OXYGEN)
                .tooltipBuilder(tooltip -> tooltip.addLine(!this.isStructureFormed() ? IKey.lang("gregtech.multiblock.invalid_structure")
                        : oxygenPlasmaValue.getValue(0) == 0 ? IKey.lang("gregtech.multiblock.large_combustion_engine.oxygen_none")
                        : IKey.lang("gregtech.multiblock.universal.fluid_amount", NaquadahReactorWorkableHandler.OXYGEN_STACK.getLocalizedName(), oxygenPlasmaValue.getValue(0), oxygenPlasmaValue.getValue(1)))));
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
    private int[] getTritiumAmount() {
        return this.getInputFluidInventory() != null ? this.getTotalFluidAmount(Materials.Tritium.getFluid(Integer.MAX_VALUE), this.getInputFluidInventory()) : new int[2];
    }

    /**
     * @return an array of [fuel stored, fuel capacity]
     */
    private int[] getOxygenPlasmaAmount() {
        return this.getInputFluidInventory() != null ? this.getTotalFluidAmount(Materials.Oxygen.getFluid(FluidStorageKeys.PLASMA, Integer.MAX_VALUE), this.getInputFluidInventory()) : new int[2];
    }

    private static class NaquadahReactorWorkableHandler extends MultiblockFuelRecipeLogic {

        private boolean isOxygenBoosted = false;

        private int cycles = 20;

        private final MetaTileEntityLargeNaquadahReactor naquadahReactor;

        public static final FluidStack OXYGEN_STACK = Materials.Oxygen.getPlasma(50);
        public static final FluidStack TRITIUM_STACK = Materials.Tritium.getFluid(1000);

        public NaquadahReactorWorkableHandler(RecipeMapMultiblockController tileEntity) {
            super(tileEntity);
            this.naquadahReactor = (MetaTileEntityLargeNaquadahReactor) tileEntity;
        }

        @Override
        protected void updateRecipeProgress() {
            if (canRecipeProgress && drawEnergy(recipeEUt, true)) {
                drawEnergy(recipeEUt, false);
                if (++progressTime > maxProgressTime) {
                    completeRecipe();
                }
            }
        }

        @Override
        public boolean checkRecipe(@NotNull Recipe recipe) {
            if (!super.checkRecipe(recipe))
                return false;
            IMultipleTankHandler tanks = this.naquadahReactor.getInputFluidInventory();
            if (this.cycles >= 19) {
                if (TRITIUM_STACK.isFluidStackIdentical(tanks.drain(TRITIUM_STACK, false))) {
                    tanks.drain(TRITIUM_STACK, true);
                    this.cycles = 0;
                } else {
                    if (this.cycles < 20)
                        this.cycles++;
                    return false;
                }
            } else this.cycles++;
            if (this.isOxygenBoosted = OXYGEN_STACK.isFluidStackIdentical(tanks.drain(OXYGEN_STACK, false)))
                tanks.drain(OXYGEN_STACK, true);
            return true;
        }

        @Override
        public long getMaxVoltage() {
            // this multiplies consumption through parallel
            return this.isOxygenBoosted ? GTValues.V[tier] * 2 : GTValues.V[tier];
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

        @Override
        public @NotNull NBTTagCompound serializeNBT() {
            NBTTagCompound compound = super.serializeNBT();
            compound.setBoolean("isBoosted", this.isOxygenBoosted);
            compound.setInteger("cycles", this.cycles);
            return compound;
        }

        @Override
        public void deserializeNBT(@NotNull NBTTagCompound compound) {
            super.deserializeNBT(compound);
            this.isOxygenBoosted = compound.getBoolean("isBoosted");
            this.cycles = compound.getInteger("cycles");
        }

        public int getCycles() {
            return this.cycles;
        }
    }
}
