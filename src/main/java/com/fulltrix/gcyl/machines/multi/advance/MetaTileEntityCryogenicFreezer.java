package com.fulltrix.gcyl.machines.multi.advance;

import com.cleanroommc.modularui.api.drawable.IKey;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;
import com.fulltrix.gcyl.GCYLConfig;
import com.fulltrix.gcyl.api.multi.GCYLRecipeMapMultiblockController;
import com.fulltrix.gcyl.materials.GCYLMaterials;
import com.fulltrix.gcyl.client.ClientHandler;
import gregicality.multiblocks.api.capability.impl.GCYMMultiblockRecipeLogic;
import gregicality.multiblocks.api.render.GCYMTextures;
import gregicality.multiblocks.common.block.GCYMMetaBlocks;
import gregicality.multiblocks.common.block.blocks.BlockLargeMultiblockCasing;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.ProgressBarMultiblock;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIBuilder;
import gregtech.api.metatileentity.multiblock.ui.TemplateBarBuilder;
import gregtech.api.mui.GTGuiTextures;
import gregtech.api.mui.sync.FixedIntArraySyncValue;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.logic.OCResult;
import gregtech.api.recipes.properties.RecipePropertyStorage;
import gregtech.api.util.GTUtility;
import gregtech.api.util.KeyUtil;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.util.List;
import java.util.function.UnaryOperator;

//TODO: limit reflect in ui. update tooltip. improve performance

public class MetaTileEntityCryogenicFreezer extends GCYLRecipeMapMultiblockController implements ProgressBarMultiblock {

    private static final FluidStack CRYOTHEUM = GCYLMaterials.Cryotheum.getFluid(Integer.MAX_VALUE);

    private FluidStack cryotheum = CRYOTHEUM;

    public MetaTileEntityCryogenicFreezer(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, RecipeMaps.VACUUM_RECIPES, false);
        this.recipeMapWorkable = new MetaTileEntityCryogenicFreezer.CryogenicRecipeLogic(this);
        if (GCYLConfig.Misc.cryogenicFreezerMaxVoltage > 0)
            this.recipeMapWorkable.setMaximumOverclockVoltage(Math.min(this.energyContainer.getInputVoltage(), GTValues.VOC[GCYLConfig.Misc.cryogenicFreezerMaxVoltage]));
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity metaTileEntityHolder) {
        return new MetaTileEntityCryogenicFreezer(this.metaTileEntityId);
    }

    @Override
    protected void configureDisplayText(MultiblockUIBuilder builder) {
        super.configureDisplayText(builder);
        builder.addCustom((key, syncer) -> key.add(KeyUtil.lang(TextFormatting.GRAY, "gcyl.machine.fluid.tick.consuming", this.cryotheum.getLocalizedName(), syncer.syncInt(this.cryotheum.amount))));
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        this.cryotheum = GCYLMaterials.Cryotheum.getFluid((int) Math.pow(2, GTUtility.getTierByVoltage(this.energyContainer.getInputVoltage())));
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.cryotheum = CRYOTHEUM;
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
        tooltip.add(I18n.format("gregtech.multiblock.vol_cryo.description"));
        if (GCYLConfig.Misc.cryogenicFreezerMaxVoltage > 0)
            tooltip.add(I18n.format("gregtech.multiblock.volcanus.description.4", GTValues.VOCNF[GCYLConfig.Misc.cryogenicFreezerMaxVoltage]));
        tooltip.add(I18n.format("gregtech.multiblock.volcanus.description.3"));
    }

    @Override
    public int getProgressBarCount() {
        return 1;
    }

    @Override
    public void registerBars(List<UnaryOperator<TemplateBarBuilder>> bars, PanelSyncManager syncManager) {
        FixedIntArraySyncValue cryotheumAmount = new FixedIntArraySyncValue(this::getCryotheumAmount, null);
        syncManager.syncValue("cryotheum_amount", cryotheumAmount);

        bars.add(bar -> bar.progress(() -> cryotheumAmount.getValue(1) == 0 ? 0 : 1.0 * cryotheumAmount.getValue(0) / cryotheumAmount.getValue(1))
                .texture(GTGuiTextures.PROGRESS_BAR_LCE_OXYGEN)
                .tooltipBuilder(tooltip -> tooltip.addLine(!this.isStructureFormed() ? IKey.lang("gregtech.multiblock.invalid_structure")
                        : cryotheumAmount.getValue(0) == 0 ? IKey.lang("gregtech.multiblock.large_combustion_engine.fuel_none")
                        : IKey.lang("gcyl.multiblock.void_miner.cryotheum", cryotheumAmount.getValue(0), cryotheumAmount.getValue(1)))));
    }

    private int[] getCryotheumAmount() {
        return this.getInputFluidInventory() != null ? this.getTotalFluidAmount(CRYOTHEUM, this.getInputFluidInventory()) : new int[2];
    }

    private class CryogenicRecipeLogic extends GCYMMultiblockRecipeLogic {

        public CryogenicRecipeLogic(RecipeMapMultiblockController metaTileEntity) {
            super(metaTileEntity);
        }

        @Override
        protected void updateRecipeProgress() {
            if (cryotheum.isFluidStackIdentical(this.getInputTank().drain(cryotheum, false))) {
                this.getInputTank().drain(cryotheum, true);
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
        protected void modifyOverclockPost(@NotNull OCResult ocResult, @NotNull RecipePropertyStorage storage) {
            super.modifyOverclockPost(ocResult, storage);
            ocResult.setEut((long) (ocResult.duration() * 0.9));
            ocResult.setDuration((int) (ocResult.duration() / 1.2));
        }
    }
}
