package com.fulltrix.gcyl.machines.multi.advance;

import com.fulltrix.gcyl.GCYLConfig;
import gregicality.multiblocks.api.capability.impl.GCYMMultiblockRecipeLogic;
import gregicality.multiblocks.api.metatileentity.GCYMRecipeMapMultiblockController;
import gregicality.multiblocks.api.render.GCYMTextures;
import gregicality.multiblocks.common.block.GCYMMetaBlocks;
import gregicality.multiblocks.common.block.blocks.BlockLargeMultiblockCasing;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.recipes.Recipe;
import gregtech.client.renderer.ICubeRenderer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

import static com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps.FISHER_RECIPES;
import static net.minecraft.init.Blocks.WATER;

//TODO optimize
public class MetaTileEntityLargeFisher extends GCYMRecipeMapMultiblockController {

    private static final String[] VALID_BIOMES = GCYLConfig.multis.largeFisher.whitelistedBiomes;
    public MetaTileEntityLargeFisher(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, FISHER_RECIPES);
        this.recipeMapWorkable = new FisherRecipeLogic(this);
    }
    @Override
    public boolean isTiered() {
        return false;
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("WWWWW","XXXXX").setRepeatable(4)
                .aisle("WWWWW","XXSXX")
                .where('X', states(getCasingState()).setMinGlobalLimited(20).or(autoAbilities()))
                .where('S', selfPredicate())
                .where('#', any())
                .where('W', states(WATER.getDefaultState()))
                .build();

    }

    protected IBlockState getCasingState() {
        return GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING.getState(BlockLargeMultiblockCasing.CasingType.WATERTIGHT_CASING);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GCYMTextures.WATERTIGHT_CASING;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityLargeFisher(metaTileEntityId);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, @NotNull List<String> tooltip,
                               boolean advanced) {
        StringBuilder r = new StringBuilder();
        for(int i = 0 ; i < GCYLConfig.multis.largeFisher.whitelistedBiomes.length; i++ ) {
            r.append(GCYLConfig.multis.largeFisher.whitelistedBiomes[i]);
            if(i != GCYLConfig.multis.largeFisher.whitelistedBiomes.length - 1)
                r.append(", ");
        }

        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gcyl.machine.large_fisher.tooltip.1"));
        tooltip.add(I18n.format("gcyl.machine.large_fisher.tooltip.2", r));
    }

    private class FisherRecipeLogic extends GCYMMultiblockRecipeLogic {

        MetaTileEntityLargeFisher largeFisher;

        public FisherRecipeLogic(RecipeMapMultiblockController tileEntity) {
            super(tileEntity);
            this.largeFisher = (MetaTileEntityLargeFisher) tileEntity;
        }

        @Override
        public boolean checkRecipe(@NotNull Recipe recipe) {
            if (!super.checkRecipe(recipe))
                return false;

            return Arrays.asList(VALID_BIOMES).contains(this.largeFisher.getWorld().getBiome(this.largeFisher.getPos()).biomeName);
        }

    }
}
