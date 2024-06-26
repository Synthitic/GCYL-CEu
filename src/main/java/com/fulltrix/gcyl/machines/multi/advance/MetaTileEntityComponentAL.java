package com.fulltrix.gcyl.machines.multi.advance;

import com.fulltrix.gcyl.api.block.IComponentALTier;
import com.fulltrix.gcyl.api.recipes.ITier;
import com.fulltrix.gcyl.api.recipes.properties.ComponentALProperty;
import com.fulltrix.gcyl.blocks.GCYLMetaBlocks;
import com.fulltrix.gcyl.blocks.component_al.GCYLComponentALCasing;
import com.fulltrix.gcyl.blocks.metal.GCYLCleanroomCasing;
import com.fulltrix.gcyl.blocks.metal.MetalCasing2;
import com.fulltrix.gcyl.client.ClientHandler;
import gregtech.api.GTValues;
import gregtech.api.capability.IOpticalComputationHatch;
import gregtech.api.capability.IOpticalComputationProvider;
import gregtech.api.capability.IOpticalComputationReceiver;
import gregtech.api.capability.impl.ComputationRecipeLogic;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockDisplayText;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.MultiblockShapeInfo;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.recipes.Recipe;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.blocks.*;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.fulltrix.gcyl.api.pattern.TraceabilityPredicates.*;
import static com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps.COMPONENT_AL_RECIPES;
import static com.fulltrix.gcyl.machines.GCYLTileEntities.COMPONENT_ASSEMBLY_LINE;
import static gregtech.api.unification.material.Materials.TungstenSteel;
import static gregtech.api.util.RelativeDirection.*;

//TODO implement filter bonus, laser hatches
public class MetaTileEntityComponentAL extends RecipeMapMultiblockController implements ITier, IOpticalComputationReceiver {

    private IOpticalComputationProvider computationProvider;
    private int tier;

    public MetaTileEntityComponentAL(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, COMPONENT_AL_RECIPES);
        this.recipeMapWorkable = new ComponentALRecipeLogic(this);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityComponentAL(metaTileEntityId);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RIGHT, UP, FRONT)
                .aisle("CCCCCCCCC","C##F#F##C","C##CCC##C","C##CCC##C","C#######C","C#######C","CC#CCC#CC","#CCCSCCC#","###CCC###","#########")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#######G","G#######G","G#######G","C#######C","T#######T","#TCCCCCT#","#########")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#A###A#G","GB#####BG","GB#####BG","CB#####BC","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#######G","G#######G","G#######G","C#######C","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","GP#####PG","GP#CCC#PG","GP#####PG","GP#####PG","GP##B##PG","CPP#A#PPC","T#PPAPP#T","#TC###CT#","###III###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#######G","G#######G","G#######G","C#######C","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#A###A#G","GB#####BG","GB#####BG","CB#####BC","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#######G","G#######G","G#######G","C#######C","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","CP#####PC","CP#CCC#PC","CP#####PC","CP#####PC","CP##B##PC","CPP#A#PPC","T#PPAPP#T","#TC###CT#","###III###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#######G","G#######G","G#######G","C#######C","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#A###A#G","GB#####BG","GB#####BG","CB#####BC","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#######G","G#######G","G#######G","C#######C","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","GP#####PG","GP#CCC#PG","GP#####PG","GP#####PG","GP##B##PG","CPP#A#PPC","T#PPAPP#T","#TC###CT#","###III###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#######G","G#######G","G#######G","C#######C","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#A###A#G","GB#####BG","GB#####BG","CB#####BC","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#######G","G#######G","G#######G","C#######C","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","CP#####PC","CP#CCC#PC","CP#####PC","CP#####PC","CP##B##PC","CPP#A#PPC","T#PPAPP#T","#TC###CT#","###III###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#######G","G#######G","G#######G","C#######C","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#A###A#G","GB#####BG","GB#####BG","CB#####BC","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#######G","G#######G","G#######G","C#######C","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","GP#####PG","GP#CCC#PG","GP#####PG","GP#####PG","GP##B##PG","CPP#A#PPC","T#PPAPP#T","#TC###CT#","###III###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#######G","G#######G","G#######G","C#######C","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#A###A#G","GB#####BG","GB#####BG","CB#####BC","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#######G","G#######G","G#######G","C#######C","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","CP#####PC","CP#CCC#PC","CP#####PC","CP#####PC","CP##B##PC","CPP#A#PPC","T#PPAPP#T","#TC###CT#","###III###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#######G","G#######G","G#######G","C#######C","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#A###A#G","GB#####BG","GB#####BG","CB#####BC","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#######G","G#######G","G#######G","C#######C","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","GP#####PG","GP#CCC#PG","GP#####PG","GP#####PG","GP##B##PG","CPP#A#PPC","T#PPAPP#T","#TC###CT#","###III###")
                .aisle("CCCCCCCCC","G##F#F##G","G##CCC##G","G#######G","G#######G","G#######G","C#######C","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#A###A#G","GB#####BG","GB#####BG","CB#####BC","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#######G","G#######G","G#######G","C#######C","T#######T","#TCCCCCT#","#########")
                .aisle("CCCCCCCCC","C##CCC##C","C#######C","C#######C","C#######C","C#######C","CC#####CC","#CCCCCCC#","#########","#########")
                .where('S', selfPredicate())
				.where('F', frames(TungstenSteel))
				.where('G', states(MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.LAMINATED_GLASS)))
				.where('T', filterCasings())
				.where('I', componentALCasings())
				.where('A', states(MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.ASSEMBLY_CONTROL)))
				.where('B', states(MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.ASSEMBLY_LINE_CASING)))
				.where('P', states(MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.POLYTETRAFLUOROETHYLENE_PIPE)))
				.where('C', states(GCYLMetaBlocks.METAL_CASING_2.getState(MetalCasing2.CasingType.IRIDIUM))
                .setMinGlobalLimited(630)
                .or(autoAbilities(true,true,true,true,true,false,false))
                        .or(abilities(MultiblockAbility.COMPUTATION_DATA_RECEPTION).setExactLimit(1)))
                .where('#', air())
                .build();
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder(RIGHT, DOWN, FRONT)
                .aisle("CCCCCCCCC","C##F#F##C","C##CZC##C","C##CCC##C","C#######C","C#######C","CC#CCC#CC","#CCCSCCC#","###CWC###","#########")
                .aisle("YCCCCCCCC","G#######G","G##CCC##G","G#######G","G#######G","G#######G","C#######C","T#######T","#TMCCCET#","#########")
                .aisle("YCCCCCCCC","G#######G","G##CCC##G","G#A###A#G","GB#####BG","GB#####BG","CB#####BC","T#######T","#TC###CT#","###CIC###")
                .aisle("YCCCCCCCC","G#######G","G##CCC##G","G#######G","G#######G","G#######G","C#######C","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","GP#####PG","GP#CCC#PG","GP#####PG","GP#####PG","GP##B##PG","CPP#A#PPC","T#PPAPP#T","#TC###CT#","###III###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#######G","G#######G","G#######G","C#######C","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#A###A#G","GB#####BG","GB#####BG","CB#####BC","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#######G","G#######G","G#######G","C#######C","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","CP#####PC","CP#CCC#PC","CP#####PC","CP#####PC","CP##B##PC","CPP#A#PPC","T#PPAPP#T","#TC###CT#","###III###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#######G","G#######G","G#######G","C#######C","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#A###A#G","GB#####BG","GB#####BG","CB#####BC","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#######G","G#######G","G#######G","C#######C","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","GP#####PG","GP#CCC#PG","GP#####PG","GP#####PG","GP##B##PG","CPP#A#PPC","T#PPAPP#T","#TC###CT#","###III###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#######G","G#######G","G#######G","C#######C","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#A###A#G","GB#####BG","GB#####BG","CB#####BC","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#######G","G#######G","G#######G","C#######C","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","CP#####PC","CP#CCC#PC","CP#####PC","CP#####PC","CP##B##PC","CPP#A#PPC","T#PPAPP#T","#TC###CT#","###III###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#######G","G#######G","G#######G","C#######C","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#A###A#G","GB#####BG","GB#####BG","CB#####BC","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#######G","G#######G","G#######G","C#######C","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","GP#####PG","GP#CCC#PG","GP#####PG","GP#####PG","GP##B##PG","CPP#A#PPC","T#PPAPP#T","#TC###CT#","###III###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#######G","G#######G","G#######G","C#######C","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#A###A#G","GB#####BG","GB#####BG","CB#####BC","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#######G","G#######G","G#######G","C#######C","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","CP#####PC","CP#CCC#PC","CP#####PC","CP#####PC","CP##B##PC","CPP#A#PPC","T#PPAPP#T","#TC###CT#","###III###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#######G","G#######G","G#######G","C#######C","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#A###A#G","GB#####BG","GB#####BG","CB#####BC","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#######G","G#######G","G#######G","C#######C","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","GP#####PG","GP#CCC#PG","GP#####PG","GP#####PG","GP##B##PG","CPP#A#PPC","T#PPAPP#T","#TC###CT#","###III###")
                .aisle("CCCCCCCCC","G##F#F##G","G##CCC##G","G#######G","G#######G","G#######G","C#######C","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#A###A#G","GB#####BG","GB#####BG","CB#####BC","T#######T","#TC###CT#","###CIC###")
                .aisle("CCCCCCCCC","G#######G","G##CCC##G","G#######G","G#######G","G#######G","C#######C","T#######T","#TCCCCCT#","#########")
                .aisle("CCCCCCCCC","C##CXC##C","C#######C","C#######C","C#######C","C#######C","CC#####CC","#CCCCCCC#","#########","#########")
                .where('S', COMPONENT_ASSEMBLY_LINE, EnumFacing.NORTH)
                .where('F', MetaBlocks.FRAMES.get(TungstenSteel).getBlock(TungstenSteel))
                .where('G', MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.LAMINATED_GLASS))
                .where('A', MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.ASSEMBLY_CONTROL))
                .where('B', MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.ASSEMBLY_LINE_CASING))
                .where('P', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.POLYTETRAFLUOROETHYLENE_PIPE))
                .where('C', GCYLMetaBlocks.METAL_CASING_2.getState(MetalCasing2.CasingType.IRIDIUM))
                .where('#', Blocks.AIR.getDefaultState())
                .where('M', MetaTileEntities.MAINTENANCE_HATCH, EnumFacing.NORTH)
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[9], EnumFacing.NORTH)
                .where('Z', MetaTileEntities.ITEM_IMPORT_BUS[5], EnumFacing.NORTH)
                .where('X', MetaTileEntities.ITEM_EXPORT_BUS[5], EnumFacing.SOUTH)
                .where('Y', MetaTileEntities.QUADRUPLE_IMPORT_HATCH[3], EnumFacing.EAST)
                .where('T', MetaBlocks.CLEANROOM_CASING.getState(BlockCleanroomCasing.CasingType.FILTER_CASING))
                .where('W', MetaTileEntities.COMPUTATION_HATCH_RECEIVER, EnumFacing.NORTH);

        Arrays.stream(GCYLComponentALCasing.CasingType.values())
                .forEach(casingType -> shapeInfo
                        .add(builder.where('I', GCYLMetaBlocks.GCYL_COMPONENT_AL_CASING.getState(casingType)).build()));

        Arrays.stream(BlockCleanroomCasing.CasingType.values())
                .filter(casingType -> !casingType.equals(BlockCleanroomCasing.CasingType.PLASCRETE) && !casingType.equals(BlockCleanroomCasing.CasingType.FILTER_CASING))
                .forEach(casingType -> shapeInfo
                        .add(builder.where('T', MetaBlocks.CLEANROOM_CASING.getState(casingType)).build()));

        Arrays.stream(GCYLCleanroomCasing.CasingType.values())
                .forEach(casingType -> shapeInfo
                        .add(builder.where('T', GCYLMetaBlocks.GCYL_CLEANROOM_CASING.getState(casingType)).build()));

        return shapeInfo;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return ClientHandler.IRIDIUM_CASING;
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        Object type = context.get("ComponentALTier");
        if(type instanceof IComponentALTier) {
            this.tier = ((IComponentALTier) type).getTier() + 1;
        }
        else
            this.tier = 0;

        List<IOpticalComputationHatch> providers = getAbilities(MultiblockAbility.COMPUTATION_DATA_RECEPTION);
        if (providers != null && providers.size() >= 1) {
            computationProvider = providers.get(0);
        }

        if (computationProvider == null) {
            invalidateStructure();
        }
    }

    @Override
    public boolean canBeDistinct() {
        return true;
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.tier = 0;
    }

    @Override
    public int getTier() {
        return this.tier;
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
        textList.add(new TextComponentTranslation("gcyl.multiblock.coal.max_recipe_tier", GTValues.VN[this.tier]));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("gcyl.multiblock.coal.description.1"));
        tooltip.add(I18n.format("gcyl.multiblock.coal.description.2"));
        //tooltip.add(I18n.format("gcyl.multiblock.coal.description.3"));
    }

    @Override
    public IOpticalComputationProvider getComputationProvider() {
        return computationProvider;
    }


    private class ComponentALRecipeLogic extends ComputationRecipeLogic {

        public ComponentALRecipeLogic(RecipeMapMultiblockController metaTileEntity) {
            super(metaTileEntity, ComputationType.STEADY);
        }

        @Override
        public boolean checkRecipe(@NotNull Recipe recipe) {
            if (!super.checkRecipe(recipe))
                return false;

            return recipe.getProperty(ComponentALProperty.getInstance(), 0) <= tier;
        }
    }
}
