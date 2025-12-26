package com.fulltrix.gcyl.machines.multi.generator;

import com.cleanroommc.modularui.api.drawable.IKey;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;
import com.cleanroommc.modularui.value.sync.StringSyncValue;
import com.fulltrix.gcyl.blocks.metal.MetalCasing1;
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
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.blocks.BlockMultiblockCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.UnaryOperator;

import static com.fulltrix.gcyl.client.ClientHandler.NITINOL_60_CASING;
import static com.fulltrix.gcyl.blocks.GCYLMetaBlocks.METAL_CASING_1;
import static gregtech.api.GTValues.UV;
import static gregtech.api.util.RelativeDirection.*;


public class MetaTileEntityLargeRocketEngine extends FuelMultiblockController implements ProgressBarMultiblock {

    public MetaTileEntityLargeRocketEngine(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GCYLRecipeMaps.ROCKET_FUEL_RECIPES, UV);
        this.recipeMapWorkable.setMaximumOverclockVoltage(655360);
        this.recipeMapWorkable = new MetaTileEntityLargeRocketEngine.LREWorkableHandler(this);
    }

    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityLargeRocketEngine(metaTileEntityId);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World world, @NotNull List<String> tooltip, boolean advanced) {
        super.addInformation(stack, world, tooltip, advanced);
        tooltip.add(I18n.format("gcyl.multiblock.large_rocket_engine.tooltip.1"));
        tooltip.add(I18n.format("gcyl.multiblock.large_rocket_engine.tooltip.2"));
        tooltip.add(I18n.format("gcyl.multiblock.large_rocket_engine.tooltip.3"));
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
    @SideOnly(Side.CLIENT)
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return NITINOL_60_CASING;
    }

    @Override
    public boolean shouldShowVoidingModeButton() {
        return false;
    }

    @Override
    public @NotNull List<ITextComponent> getDataInfo() {
        List<ITextComponent> list = super.getDataInfo();
        if (((LREWorkableHandler) this.recipeMapWorkable).isOxygenBoosted())
            list.add(new TextComponentTranslation("gregtech.multiblock.universal.generator.boosted", LREWorkableHandler.OXYGEN_STACK.getLocalizedName()));
        return list;
    }

    @Override
    protected void configureDisplayText(MultiblockUIBuilder builder) {
        super.configureDisplayText(builder);
        LREWorkableHandler recipeLogic = (LREWorkableHandler)this.recipeMapWorkable;
        builder.addProgressLine(recipeLogic.getProgress(), recipeLogic.getMaxProgress());
        builder.addCustom((key, syncer) -> {
           long EUt = syncer.syncLong(recipeLogic.getInfoProviderEUt());
           int tier = GTUtility.getFloorTierByVoltage(EUt);
           if (EUt > 0)
               key.add(IKey.lang("gregtech.multiblock.universal.energy.production", EUt, GTValues.VOCNF[tier]));
           if (syncer.syncBoolean(recipeLogic.isOxygenBoosted()))
               key.add(IKey.lang("gregtech.multiblock.universal.generator.boosted", LREWorkableHandler.OXYGEN_STACK.getLocalizedName()));
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
        syncManager.syncValue("fuel_amount", fuelValue);
        FixedIntArraySyncValue airValue = new FixedIntArraySyncValue(this::getAirAmount, null);
        syncManager.syncValue("air_amount", airValue);
        FixedIntArraySyncValue liquidOxygenValue = new FixedIntArraySyncValue(this::getLiquidOxygenAmount, null);
        syncManager.syncValue("liquid_oxygen_amount", liquidOxygenValue);

        bars.add(bar -> bar.progress(() -> fuelValue.getValue(1) == 0 ? 0 : 1.0 * fuelValue.getValue(0) / fuelValue.getValue(1))
                .texture(GTGuiTextures.PROGRESS_BAR_LCE_FUEL)
                .tooltipBuilder(tooltip -> this.createFuelTooltip(tooltip, fuelValue, fuelNameValue)));
        bars.add(bar -> bar.progress(() -> airValue.getValue(1) == 0 ? 0 : 1.0 * airValue.getValue(0) / airValue.getValue(1))
                .texture(GTGuiTextures.PROGRESS_BAR_LCE_LUBRICANT)
                .tooltipBuilder(tooltip -> tooltip.addLine(!this.isStructureFormed() ? IKey.lang("gregtech.multiblock.invalid_structure")
                        : airValue.getValue(0) == 0 ? IKey.lang("gcyl.multiblock.large_rocket_engine.air_none")
                        : IKey.lang("gcyl.multiblock.large_rocket_engine.air_amount", airValue.getValue(0), airValue.getValue(1)))));
        bars.add(bar -> bar.progress(() -> liquidOxygenValue.getValue(1) == 0 ? 0 : 1.0 * liquidOxygenValue.getValue(0) / liquidOxygenValue.getValue(1))
                .texture(GTGuiTextures.PROGRESS_BAR_LCE_OXYGEN)
                .tooltipBuilder(tooltip -> tooltip.addLine(!this.isStructureFormed() ? IKey.lang("gregtech.multiblock.invalid_structure")
                        : liquidOxygenValue.getValue(0) == 0 ? IKey.lang("gregtech.multiblock.large_combustion_engine.oxygen_none")
                        : IKey.lang("gregtech.multiblock.large_combustion_engine.liquid_oxygen_amount", liquidOxygenValue.getValue(0), liquidOxygenValue.getValue(1)))));
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
    private int[] getAirAmount() {
        return this.getInputFluidInventory() != null ? this.getTotalFluidAmount(Materials.Air.getFluid(Integer.MAX_VALUE), this.getInputFluidInventory()) : new int[2];
    }

    /**
     * @return an array of [fuel stored, fuel capacity]
     */
    private int[] getLiquidOxygenAmount() {
        return this.getInputFluidInventory() != null ? this.getTotalFluidAmount(Materials.Oxygen.getFluid(FluidStorageKeys.LIQUID, Integer.MAX_VALUE), this.getInputFluidInventory()) : new int[2];
    }

    public static class LREWorkableHandler extends MultiblockFuelRecipeLogic {

        private boolean isOxygenBoosted = false;

        private final MetaTileEntityLargeRocketEngine rocketEngine;

        public static final FluidStack OXYGEN_STACK = Materials.Oxygen.getFluid(FluidStorageKeys.LIQUID,1000);
        public static final FluidStack AIR_STACK = Materials.Air.getFluid(37500);

        public LREWorkableHandler(RecipeMapMultiblockController tileEntity) {
            super(tileEntity);
            this.rocketEngine = (MetaTileEntityLargeRocketEngine) tileEntity;
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
            IMultipleTankHandler tanks = this.rocketEngine.getInputFluidInventory();
            if (!AIR_STACK.isFluidStackIdentical(tanks.drain(AIR_STACK, false)))
                return false;
            tanks.drain(AIR_STACK, true);
            if (this.isOxygenBoosted = OXYGEN_STACK.isFluidStackIdentical(tanks.drain(OXYGEN_STACK, false)))
                tanks.drain(OXYGEN_STACK, true);
            return true;
        }

        @Override
        public long getMaxVoltage() {
            // this multiplies consumption through parallel
            return this.isOxygenBoosted ? 655360 * 3 : 655360;
        }

        @Override
        protected long boostProduction(long production) {
            // this multiplies production without increasing consumption
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
            return compound;
        }

        @Override
        public void deserializeNBT(@NotNull NBTTagCompound compound) {
            super.deserializeNBT(compound);
            this.isOxygenBoosted = compound.getBoolean("isBoosted");
        }

        public boolean isOxygenBoosted() {
            return this.isOxygenBoosted;
        }
    }
}
