package com.fulltrix.gcyl.machines.multi.advance;

import com.cleanroommc.modularui.api.drawable.IKey;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;
import com.fulltrix.gcyl.GCYLConfig;
import com.fulltrix.gcyl.api.multi.GCYLRecipeMapMultiblockController;
import com.fulltrix.gcyl.materials.GCYLMaterials;
import com.fulltrix.gcyl.blocks.metal.MetalCasing1;
import gregicality.multiblocks.api.render.GCYMTextures;
import gregtech.api.GTValues;
import gregtech.api.block.IHeatingCoilBlockStats;
import gregtech.api.capability.IHeatingCoil;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.*;
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIBuilder;
import gregtech.api.metatileentity.multiblock.ui.TemplateBarBuilder;
import gregtech.api.mui.GTGuiTextures;
import gregtech.api.mui.sync.FixedIntArraySyncValue;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.MultiblockShapeInfo;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.logic.OCParams;
import gregtech.api.recipes.logic.OCResult;
import gregtech.api.recipes.logic.OverclockingLogic;
import gregtech.api.recipes.properties.RecipePropertyStorage;
import gregtech.api.recipes.properties.impl.TemperatureProperty;
import gregtech.api.util.GTUtility;
import gregtech.api.util.KeyUtil;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import static com.fulltrix.gcyl.client.ClientHandler.HASTELLOY_N_CASING;
import static com.fulltrix.gcyl.blocks.GCYLMetaBlocks.METAL_CASING_1;

import static gregtech.api.GregTechAPI.HEATING_COILS;
import static gregtech.api.recipes.logic.OverclockingLogic.heatingCoilOC;
import static gregtech.api.util.RelativeDirection.*;

//TODO: update ui & tooltip. improve performance

public class MetaTileEntityVolcanus extends GCYLRecipeMapMultiblockController implements IHeatingCoil, ProgressBarMultiblock {

    private static final FluidStack PYROTHEUM = GCYLMaterials.Pyrotheum.getFluid(Integer.MAX_VALUE);

    private int blastFurnaceTemperature;
    private FluidStack pyrotheum = PYROTHEUM;

    public MetaTileEntityVolcanus(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, RecipeMaps.BLAST_RECIPES, false);
        this.recipeMapWorkable = new MetaTileEntityVolcanus.VolcanusRecipeLogic(this);
        if (GCYLConfig.Misc.volcanusMaxVoltage > 0)
            this.recipeMapWorkable.setMaximumOverclockVoltage(Math.min(this.energyContainer.getInputVoltage(), GTValues.VOC[GCYLConfig.Misc.volcanusMaxVoltage]));
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity metaTileEntityHolder) {
        return new MetaTileEntityVolcanus(this.metaTileEntityId);
    }

    @Override
    protected void configureDisplayText(MultiblockUIBuilder builder) {
        super.configureDisplayText(builder);
        builder.addCustom((keyManager, uiSyncer) -> {
            keyManager.add(KeyUtil.lang(TextFormatting.RED, "gregtech.multiblock.blast_furnace.max_temperature", uiSyncer.syncInt(this.blastFurnaceTemperature)));
            keyManager.add(KeyUtil.lang(TextFormatting.GRAY, "gcyl.machine.fluid.tick.consuming", this.pyrotheum.getLocalizedName(), uiSyncer.syncInt(this.pyrotheum.amount)));
        });
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        IHeatingCoilBlockStats coilType = context.getOrDefault("CoilType", BlockWireCoil.CoilType.CUPRONICKEL);
        this.blastFurnaceTemperature = coilType.getCoilTemperature();
        this.blastFurnaceTemperature += 100 * Math.max(0, GTUtility.getTierByVoltage(getEnergyContainer().getInputVoltage()) - GTValues.MV);
        this.pyrotheum = GCYLMaterials.Pyrotheum.getFluid((int) Math.pow(2,GTUtility.getTierByVoltage(this.energyContainer.getInputVoltage())));
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.blastFurnaceTemperature = 0;
        this.pyrotheum = PYROTHEUM;
    }

    @Override
    public boolean checkRecipe(@NotNull Recipe recipe, boolean consumeIfSuccess) {
        return this.blastFurnaceTemperature >= recipe.getProperty(TemperatureProperty.getInstance(), 0);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gregtech.multiblock.volcanus.description.1"));
        if (GCYLConfig.Misc.volcanusMaxVoltage > 0)
            tooltip.add(I18n.format("gregtech.multiblock.volcanus.description.4", GTValues.VOCNF[GCYLConfig.Misc.volcanusMaxVoltage]));
        tooltip.add(I18n.format("gregtech.multiblock.volcanus.description.2"));
        tooltip.add(I18n.format("gregtech.multiblock.volcanus.description.3"));
        tooltip.add(I18n.format("gregtech.machine.electric_blast_furnace.tooltip.1"));
        tooltip.add(I18n.format("gregtech.machine.electric_blast_furnace.tooltip.2"));
        tooltip.add(I18n.format("gregtech.machine.electric_blast_furnace.tooltip.3"));
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return HASTELLOY_N_CASING;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return GCYMTextures.MEGA_BLAST_FURNACE_OVERLAY;
    }

    @Override
    public boolean hasMufflerMechanics() {
        return true;
    }

    @Override
    public int getCurrentTemperature() {
        return this.blastFurnaceTemperature;
    }

    @Override
    public boolean isTiered() {
        return false;
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXX", "CCC", "CCC", "XXX")
                .aisle("XXX", "C#C", "C#C", "XMX")
                .aisle("XSX", "CCC", "CCC", "XXX")
                .where('S', selfPredicate())
                .where('X', states(getCasingState()).setMinGlobalLimited(8)
                        .or(autoAbilities(true, true, true, true, true, true, false)))
                .where('M', abilities(MultiblockAbility.MUFFLER_HATCH))
                .where('C', heatingCoils())
                .where('#', air())
                .build();
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder(RIGHT, DOWN, FRONT)
                .aisle("mEX", "CCC", "CCC", "XXX")
                .aisle("XXX", "C#C", "C#C", "XMX")
                .aisle("ISO", "CCC", "CCC", "iXo")
                .where('S', this, EnumFacing.SOUTH)
                .where('X', this.getCasingState())
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[3], EnumFacing.SOUTH)
                .where('O', MetaTileEntities.ITEM_EXPORT_BUS[3], EnumFacing.SOUTH)
                .where('i', MetaTileEntities.FLUID_IMPORT_HATCH[3], EnumFacing.SOUTH)
                .where('o', MetaTileEntities.FLUID_EXPORT_HATCH[3], EnumFacing.SOUTH)
                .where('M', MetaTileEntities.MUFFLER_HATCH[3], EnumFacing.UP)
                .where('m', MetaTileEntities.MAINTENANCE_HATCH, EnumFacing.NORTH);
        return HEATING_COILS.entrySet().stream()
                .sorted(Comparator.comparingInt(entry -> entry.getValue().getTier()))
                .map(entry -> builder.where('C', entry.getKey())
                        .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[entry.getValue().getTier() + 1], EnumFacing.NORTH).build())
                .collect(Collectors.toList());
    }

    protected IBlockState getCasingState() {
        return METAL_CASING_1.getState(MetalCasing1.CasingType.HASTELLOY_N);
    }

    @Override
    public int getProgressBarCount() {
        return 1;
    }

    @Override
    public void registerBars(List<UnaryOperator<TemplateBarBuilder>> bars, PanelSyncManager syncManager) {
        FixedIntArraySyncValue pyrotheumAmount = new FixedIntArraySyncValue(this::getPyrotheumAmount, null);
        syncManager.syncValue("pyrotheum_amount", pyrotheumAmount);

        bars.add(bar -> bar.progress(() -> pyrotheumAmount.getValue(1) == 0 ? 0 : 1.0 * pyrotheumAmount.getValue(0) / pyrotheumAmount.getValue(1))
                .texture(GTGuiTextures.PROGRESS_BAR_LCE_LUBRICANT)
                .tooltipBuilder(tooltip -> tooltip.addLine(!this.isStructureFormed() ? IKey.lang("gregtech.multiblock.invalid_structure")
                        : pyrotheumAmount.getValue(0) == 0 ? IKey.lang("gregtech.multiblock.large_combustion_engine.fuel_none")
                        : IKey.lang("gcyl.multiblock.void_miner.pyrotheum", pyrotheumAmount.getValue(0), pyrotheumAmount.getValue(1)))));
    }

    private int[] getPyrotheumAmount() {
        return this.getInputFluidInventory() != null ? this.getTotalFluidAmount(PYROTHEUM, this.getInputFluidInventory()) : new int[2];
    }

    private class VolcanusRecipeLogic extends MultiblockRecipeLogic {

        public VolcanusRecipeLogic(RecipeMapMultiblockController metaTileEntity) {
            super(metaTileEntity);
        }

        @Override
        protected void updateRecipeProgress() {
            if (pyrotheum.isFluidStackIdentical(this.getInputTank().drain(pyrotheum, false))) {
                this.getInputTank().drain(pyrotheum, true);
                super.updateRecipeProgress();
            } else this.decreaseProgress();
        }

        @Override
        public long getMaxParallelVoltage() {
            return Long.MAX_VALUE;
        }

        @Override
        public int getParallelLimit() {
            return 8;
        }

        @Override
        protected void modifyOverclockPre(@NotNull OCParams ocParams, @NotNull RecipePropertyStorage storage) {
            super.modifyOverclockPre(ocParams, storage);
            ocParams.setEut(OverclockingLogic.applyCoilEUtDiscount(ocParams.eut(), getCurrentTemperature(), storage.get(TemperatureProperty.getInstance(), 0)));
        }

        @Override
        protected void runOverclockingLogic(@NotNull OCParams ocParams, @NotNull OCResult ocResult,
                                            @NotNull RecipePropertyStorage propertyStorage, long maxVoltage) {
            heatingCoilOC(ocParams, ocResult, maxVoltage, getCurrentTemperature(), propertyStorage.get(TemperatureProperty.getInstance(), 0));
        }

        @Override
        protected void modifyOverclockPost(@NotNull OCResult ocResult, @NotNull RecipePropertyStorage storage) {
            super.modifyOverclockPost(ocResult, storage);
            ocResult.setEut((long) (ocResult.eut() * 0.9));
            ocResult.setDuration((int) (ocResult.duration() / 1.2));
        }
    }
}
