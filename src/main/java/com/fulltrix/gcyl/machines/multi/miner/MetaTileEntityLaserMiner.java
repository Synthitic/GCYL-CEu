package com.fulltrix.gcyl.machines.multi.miner;

import com.fulltrix.gcyl.api.multi.GCYLComputationRecipeLogic;
import com.fulltrix.gcyl.api.multi.GCYLRecipeMapMultiblockController;
import com.fulltrix.gcyl.client.ClientHandler;
import com.fulltrix.gcyl.blocks.GCYLMetaBlocks;
import com.fulltrix.gcyl.blocks.fusion.GCYLFusionCoils;
import com.fulltrix.gcyl.blocks.metal.MetalCasing1;
import com.fulltrix.gcyl.api.recipes.properties.AdvFusionCoilProperty;
import gregtech.api.capability.IOpticalComputationHatch;
import gregtech.api.capability.IOpticalComputationProvider;
import gregtech.api.capability.IOpticalComputationReceiver;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.*;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.client.renderer.ICubeRenderer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.fulltrix.gcyl.api.pattern.TraceabilityPredicates.advFusionCoils;
import static com.fulltrix.gcyl.materials.GCYLMaterials.MetastableOganesson;

public class MetaTileEntityLaserMiner extends GCYLRecipeMapMultiblockController implements IOpticalComputationReceiver {

    private IOpticalComputationProvider computationProvider;
    private int coilTier;

    public MetaTileEntityLaserMiner(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, boolean isParallel) {
        super(metaTileEntityId, recipeMap, isParallel);
        this.recipeMapWorkable = new LaserMinerRecipeLogic(this);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("####CCC#########CCC","####CCC#########CCC","####CCC#########CCC","####CCC#########CCC","####CCC#########CCC","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################")
                .aisle("####CCC#########CCC","####CXC#########CXC","####CXC#########CXC","####CXC#########CXC","####CCC#########CCC","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################")
                .aisle("####CCC#########CCC","####CCCC#######CCCC","####CCCC#######CCCC","####CCCC#######CCCC","####CCC#########CCC","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################")
                .aisle("###################","######CC#######CC##","######CCC#####CCC##","######CC#######CC##","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################")
                .aisle("###################","###################","#######CCC###CCC###","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################")
                .aisle("###################","###################","########CCCCCCC####","###################","###################","###################","###################","###################","################B##","###############B###","##############B####","#############B#####","############B######","###########B#######","##########B########","#########B#########","########B##########","#######B###########","######B############","###################","###################","###################","###################","###################","###################")
                .aisle("###################","###################","########CCCCCCC####","##########F#F######","##########F#F######","##########F#F######","##########F#F######","##########F#F###B##","##########F#F##B#B#","##########F#F#B#B##","##########F#FB#B###","##########F#B#B####","##########FB#B#####","##########B#B######","#########B#B#######","########B#B########","#######B#B#########","######B#B##########","#####B#B###########","#####BB############","####B##############","###B###############","##B################","###################","###################")
                .aisle("###################","###################","########CCCCCCC####","###################","###################","###################","###################","###############BBB#","##############B###B","#############B###B#","############B###B##","###########B###B###","##########B###B####","#########B###B#####","########B###B######","#######B###B#######","######B###B########","######B##B#########","#####B##B##########","####BB#B###########","###BBBB############","##BBB##############","#BBB###############","BBB################","AB#################")
                .aisle("###################","###################","########CCCCCCC####","##########F#F######","##########F#F######","##########F#F######","##########F#F######","##########F#F###B##","##########F#F##B#B#","##########F#F#B#B##","##########F#FB#B###","##########F#B#B####","##########FB#B#####","##########B#B######","#########B#B#######","########B#B########","#######B#B#########","######B#B##########","#####B#B###########","#####BB############","####B##############","###B###############","##B################","###################","###################")
                .aisle("###################","###################","########CCCCCCC####","###################","###################","###################","###################","###################","################B##","###############B###","##############B####","#############B#####","############B######","###########B#######","##########B########","#########B#########","########B##########","#######B###########","######B############","###################","###################","###################","###################","###################","###################")
                .aisle("###################","###################","#######CCC###CCC###","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################")
                .aisle("###################","######CC#######CC##","######CCC#####CCC##","######CC#######CC##","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################")
                .aisle("####CCC#########CCC","####CCCC#######CCCC","####CCCC#######CCCC","####CCCC#######CCCC","####CCC#########CCC","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################")
                .aisle("####CCC#########CCC","####CXC#########CXC","####CXC#########CXC","####CXC#########CXC","####CCC#########CCC","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################")
                .aisle("####CCC#########CCC","####CCC#########CCC","####CCC#########CCC","####CSC#########CCC","####CCC#########CCC","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################","###################")
                .where('#', any())
                .where('C', states(getCasingState()).setMinGlobalLimited(240).or(autoAbilities()).or(abilities(MultiblockAbility.COMPUTATION_DATA_RECEPTION).setExactLimit(1)))
                .where('F', frames(MetastableOganesson))
                .where('X', tieredCasing())
                .where('B', advFusionCoils())
                .where('A', abilities(MultiblockAbility.OUTPUT_LASER))
                .where('S', selfPredicate())
                .build();
    }


    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        Object type = context.get("AdvFusionCoilTier");
        if (type instanceof GCYLFusionCoils.CasingType) {
            GCYLFusionCoils.CasingType casingType = (GCYLFusionCoils.CasingType) type;

            if (casingType.equals(GCYLFusionCoils.CasingType.ADV_FUSION_COIL_1)) {
                this.coilTier = 1;
            } else if (casingType.equals(GCYLFusionCoils.CasingType.ADV_FUSION_COIL_2)) {
                this.coilTier = 2;
            } else if (casingType.equals(GCYLFusionCoils.CasingType.ADV_FUSION_COIL_3)) {
                this.coilTier = 3;
            } else if (casingType.equals(GCYLFusionCoils.CasingType.ADV_FUSION_COIL_4)) {
                this.coilTier = 4;
            } else if (casingType.equals(GCYLFusionCoils.CasingType.ADV_FUSION_COIL_5)) {
                this.coilTier = 5;
            }
        }
        List<IOpticalComputationHatch> providers = getAbilities(MultiblockAbility.COMPUTATION_DATA_RECEPTION);
        if (providers != null && providers.size() >= 1) {
            computationProvider = providers.get(0);
        }

        if (computationProvider == null) {
            invalidateStructure();
        }
    }

    private IBlockState getCasingState() {
        return GCYLMetaBlocks.METAL_CASING_1.getState(MetalCasing1.CasingType.HASTELLOY_K243);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return ClientHandler.HASTELLOY_K243_CASING;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityLaserMiner(metaTileEntityId, this.recipeMap, this.isParallel());
    }

    @Override
    public IOpticalComputationProvider getComputationProvider() {
        return computationProvider;
    }

    private class LaserMinerRecipeLogic extends GCYLComputationRecipeLogic {

        public LaserMinerRecipeLogic(RecipeMapMultiblockController metaTileEntity) {
            super(metaTileEntity, ComputationType.STEADY);
        }

        @NotNull
        @Override
        public MetaTileEntityLaserMiner getMetaTileEntity() {
            return (MetaTileEntityLaserMiner) super.getMetaTileEntity();
        }

        @Override
        public boolean checkRecipe(@NotNull Recipe recipe) {
            if (!super.checkRecipe(recipe))
                return false;

            //do not run recipe if coil tier is too low
            return recipe.getProperty(AdvFusionCoilProperty.getInstance(), 0) <= coilTier;
        }

    }
}
