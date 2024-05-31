package com.fulltrix.tjfcore.machines.multi.advance;

import com.fulltrix.tjfcore.TJFMaterials;
import com.fulltrix.tjfcore.item.metal.MetalCasing1;
import gregicality.multiblocks.api.capability.impl.GCYMMultiblockRecipeLogic;
import gregicality.multiblocks.api.metatileentity.GCYMRecipeMapMultiblockController;
import gregicality.multiblocks.api.render.GCYMTextures;
import gregtech.api.GTValues;
import gregtech.api.block.IHeatingCoilBlockStats;
import gregtech.api.capability.IHeatingCoil;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.recipeproperties.IRecipePropertyStorage;
import gregtech.api.recipes.recipeproperties.TemperatureProperty;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.common.blocks.BlockWireCoil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.fulltrix.tjfcore.client.ClientHandler.HASTELLOY_N_CASING;
import static com.fulltrix.tjfcore.item.TJFMetaBlocks.METAL_CASING_1;
import static gregtech.api.recipes.logic.OverclockingLogic.heatingCoilOverclockingLogic;

//TODO: confirm limiting to ZPM & and rewrite because its written terribly

public class MetaTileEntityVolcanus extends GCYMRecipeMapMultiblockController implements IHeatingCoil {

    protected final MetaTileEntity metaTileEntity;
    private int blastFurnaceTemperature;
    private FluidTankList heatHandler;
    private boolean wasGoodAndRunning = false;

    public MetaTileEntityVolcanus(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, RecipeMaps.BLAST_RECIPES);
        this.recipeMapWorkable = new MetaTileEntityVolcanus.VolcanusRecipeLogic(this);
        this.metaTileEntity = this;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity metaTileEntityHolder) {
        return new MetaTileEntityVolcanus(this.metaTileEntityId);
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        if (isStructureFormed()) {
            textList.add(new TextComponentTranslation("gregtech.multiblock.blast_furnace.max_temperature",
                    blastFurnaceTemperature)
                    .setStyle(new Style().setColor(TextFormatting.RED)));
        }
        super.addDisplayText(textList);
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        Object type = context.get("CoilType");
        if (type instanceof IHeatingCoilBlockStats) {
            this.blastFurnaceTemperature = ((IHeatingCoilBlockStats) type).getCoilTemperature();
        } else {
            this.blastFurnaceTemperature = BlockWireCoil.CoilType.CUPRONICKEL.getCoilTemperature();
        }

        this.blastFurnaceTemperature += 100 *
                Math.max(0, GTUtility.getTierByVoltage(getEnergyContainer().getInputVoltage()) - GTValues.MV);

        this.heatHandler = new FluidTankList(false, getAbilities(MultiblockAbility.IMPORT_FLUIDS));

    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.blastFurnaceTemperature = 0;
    }


    @Override
    protected void updateFormedValid() {
        super.updateFormedValid();
        if (!getWorld().isRemote) {
            if (!wasGoodAndRunning || getOffsetTimer() % 20 == 4) {
                if (drainFluid(true)) {
                    if (this.isActive()) {
                        drainFluid(false);
                        wasGoodAndRunning = true;
                    }
                } else {
                    energyContainer.removeEnergy(energyContainer.getEnergyCapacity());
                    wasGoodAndRunning = false;
                }
            }

        }
    }

    @Override
    public boolean isTiered() {return false;}

    public boolean drainFluid(boolean simulate) {

        FluidStack heatingFluid = TJFMaterials.Pyrotheum.getFluid((int) Math.pow(2, GTUtility.getTierByVoltage(energyContainer.getInputVoltage())) * 5);
        FluidStack fluidStack = this.heatHandler.getTankAt(0).getFluid();

        if (fluidStack != null && fluidStack.isFluidEqual(TJFMaterials.Pyrotheum.getFluid(1)) &&
                fluidStack.amount >= heatingFluid.amount) {
            if (!simulate)
                this.heatHandler.drain(heatingFluid, true);
            return true;
        }
        return false;
    }

    @Override
    public boolean checkRecipe(@NotNull Recipe recipe, boolean consumeIfSuccess) {
        return this.blastFurnaceTemperature >= recipe.getProperty(TemperatureProperty.getInstance(), 0);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gregtech.multiblock.volcanus.description"));
        tooltip.add(I18n.format("gregtech.machine.electric_blast_furnace.tooltip.1"));
        tooltip.add(I18n.format("gregtech.machine.electric_blast_furnace.tooltip.2"));
        tooltip.add(I18n.format("gregtech.machine.electric_blast_furnace.tooltip.3"));
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return HASTELLOY_N_CASING;
    }

    @Override
    protected @NotNull OrientedOverlayRenderer getFrontOverlay() {
        return GCYMTextures.MEGA_BLAST_FURNACE_OVERLAY;
    }

    @Override
    public boolean hasMufflerMechanics() {
        return true;
    }

    @Override
    public boolean canBeDistinct() {
        return true;
    }

    @Override
    public int getCurrentTemperature() {
        return this.blastFurnaceTemperature;
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

    protected IBlockState getCasingState() {
        return METAL_CASING_1.getState(MetalCasing1.CasingType.HASTELLOY_N);
    }

    @SuppressWarnings("InnerClassMayBeStatic")
    private class VolcanusRecipeLogic extends GCYMMultiblockRecipeLogic {

        public VolcanusRecipeLogic(RecipeMapMultiblockController metaTileEntity) {
            super(metaTileEntity);
            setAllowOverclocking(false);
        }

        @Override
        public long getMaxVoltage() {
            if (energyContainer.getInputVoltage() >= GTValues.V[7]) {
                return Math.min(energyContainer.getInputVoltage() / energyContainer.getInputAmperage(), GTValues.V[7]);
            } else
                return GTValues.V[7] / energyContainer.getInputAmperage();
        }

        @Override
        public long getMaximumOverclockVoltage() {
            return getMaxVoltage();
        }

        @Override
        protected int @NotNull [] runOverclockingLogic(@NotNull IRecipePropertyStorage propertyStorage, int recipeEUt,
                                                       long maxVoltage, int duration, int maxOverclocks) {
            return heatingCoilOverclockingLogic(Math.abs(recipeEUt),
                    maxVoltage,
                    duration,
                    maxOverclocks,
                    ((IHeatingCoil) metaTileEntity).getCurrentTemperature(),
                    propertyStorage.getRecipePropertyValue(TemperatureProperty.getInstance(), 0));
        }
    }
}
