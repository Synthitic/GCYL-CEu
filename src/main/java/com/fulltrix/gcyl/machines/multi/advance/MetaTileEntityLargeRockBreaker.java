package com.fulltrix.gcyl.machines.multi.advance;

import com.fulltrix.gcyl.client.ClientHandler;
import com.fulltrix.gcyl.blocks.GCYLMetaBlocks;
import com.fulltrix.gcyl.blocks.metal.MetalCasing1;
import gregicality.multiblocks.api.capability.impl.GCYMMultiblockRecipeLogic;
import gregicality.multiblocks.api.metatileentity.GCYMMultiblockAbility;
import gregicality.multiblocks.api.metatileentity.GCYMRecipeMapMultiblockController;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.unification.material.Materials;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.fulltrix.gcyl.materials.GCYLMaterials.Inconel625;
import static gregtech.api.recipes.RecipeMaps.ROCK_BREAKER_RECIPES;
import static net.minecraft.init.Blocks.LAVA;
import static net.minecraft.init.Blocks.WATER;

public class MetaTileEntityLargeRockBreaker extends GCYMRecipeMapMultiblockController {

    public MetaTileEntityLargeRockBreaker(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, ROCK_BREAKER_RECIPES);
        this.recipeMapWorkable = new RockBreakerRecipeLogic(this);
    }

    @Override
    public boolean isTiered() {
        return false;
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXXXX", "F###F", "F###F", "F###F", "XXXXX")
                .aisle("XXXXX", "#GGG#", "#GGG#", "#GGG#", "XXXXX")
                .aisle("XXXXX", "#GWG#", "#GXG#", "#GLG#", "XXMXX")
                .aisle("XXXXX", "#GGG#", "#GGG#", "#GGG#", "XXXXX")
                .aisle("XXSXX", "F###F", "F###F", "F###F", "XXXXX")
                .where('S', selfPredicate())
                .where('X', states(getCasingState()).setMinGlobalLimited(18)
                        .or(abilities(MultiblockAbility.IMPORT_FLUIDS).setMinGlobalLimited(1).setMaxGlobalLimited(2))
                        .or(abilities(MultiblockAbility.IMPORT_ITEMS).setExactLimit(1))
                        .or(abilities(MultiblockAbility.EXPORT_ITEMS).setExactLimit(1))
                        .or(abilities(GCYMMultiblockAbility.PARALLEL_HATCH).setMaxGlobalLimited(1))
                        .or(abilities(MultiblockAbility.INPUT_ENERGY).setMinGlobalLimited(1).setMaxGlobalLimited(2)))
                .where('M', abilities(MultiblockAbility.MUFFLER_HATCH))
                .where('F', frames(Inconel625))
                .where('G', states(MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.FUSION_GLASS)))
                .where('W', states(WATER.getDefaultState()))
                .where('L', states(LAVA.getDefaultState()))
                .where('#', any())
                .build();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gcyl.machine.large_rock_breaker.tooltip.1"));
    }

    protected IBlockState getCasingState() {
        return GCYLMetaBlocks.METAL_CASING_1.getState(MetalCasing1.CasingType.INCONEL_625);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return ClientHandler.INCONEL_625_CASING;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityLargeRockBreaker(metaTileEntityId);
    }

    private class RockBreakerRecipeLogic extends GCYMMultiblockRecipeLogic {

        MetaTileEntityLargeRockBreaker largeRockBreaker;

        public RockBreakerRecipeLogic(RecipeMapMultiblockController tileEntity) {
            super(tileEntity);
            this.largeRockBreaker = (MetaTileEntityLargeRockBreaker) tileEntity;
        }

        protected FluidStack getLava() {
            return Materials.Lava.getFluid((int) Math.pow(3, GTUtility.getTierByVoltage(this.largeRockBreaker.energyContainer.getInputVoltage())));
        }

        protected boolean checkLava() {
            IMultipleTankHandler inputTank = largeRockBreaker.getInputFluidInventory();
            if(getLava().isFluidStackIdentical(inputTank.drain(getLava(), false))) {
                return true;
            }
            else {
                invalidate();
                return false;
            }
        }

        protected void drainLava() {
            largeRockBreaker.getInputFluidInventory().drain(getLava(), true);
        }

        protected FluidStack getWater() {
            return Materials.Water.getFluid((int) Math.pow(3,GTUtility.getTierByVoltage(this.largeRockBreaker.energyContainer.getInputVoltage())));
        }

        protected boolean checkWater() {
            IMultipleTankHandler inputTank = largeRockBreaker.getInputFluidInventory();
            if(getWater().isFluidStackIdentical(inputTank.drain(getWater(), false))) {
                return true;
            }
            else {
                invalidate();
                return false;
            }
        }

        protected void drainWater() {
            largeRockBreaker.getInputFluidInventory().drain(getWater(), true);
        }

        @Override
        protected boolean shouldSearchForRecipes() {
            return super.shouldSearchForRecipes() && checkLava() && checkWater();
        }

        @Override
        protected boolean canProgressRecipe() {
            if(checkLava() && checkWater() && largeRockBreaker.isActive()) {
                drainLava();
                drainWater();
            }

            return super.canProgressRecipe() && checkLava() && checkWater();
        }
    }
}
