package com.fulltrix.gcyl.machines.multi.advance;

import com.fulltrix.gcyl.client.ClientHandler;
import com.fulltrix.gcyl.item.GCYLMetaBlocks;
import com.fulltrix.gcyl.item.metal.GCYLCleanroomCasing;
import com.fulltrix.gcyl.item.metal.MetalCasing1;
import com.fulltrix.gcyl.machines.GCYLTileEntities;
import gregicality.multiblocks.api.capability.impl.GCYMMultiblockRecipeLogic;
import gregicality.multiblocks.api.metatileentity.GCYMRecipeMapMultiblockController;
import gregicality.multiblocks.common.metatileentities.GCYMMetaTileEntities;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.MultiblockShapeInfo;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.recipes.recipeproperties.IRecipePropertyStorage;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockCleanroomCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.fulltrix.gcyl.api.block.TraceabilityPredicates.filterCasings;
import static gregtech.api.recipes.RecipeMaps.GAS_COLLECTOR_RECIPES;

public class MetaTileEntityLargeAirCollector extends GCYMRecipeMapMultiblockController {

    private int filterTier = 0;

    public MetaTileEntityLargeAirCollector(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GAS_COLLECTOR_RECIPES);
        this.recipeMapWorkable = new LargeAirCollectorRecipeLogic(this);
    }
    @Override
    public boolean isTiered() {
        return false;
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.filterTier = 0;
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        Object type = context.get("FilterType");
        if (type instanceof BlockCleanroomCasing.CasingType) {
            BlockCleanroomCasing.CasingType casingType = (BlockCleanroomCasing.CasingType) type;

            if (casingType.equals(BlockCleanroomCasing.CasingType.FILTER_CASING)) {
                this.filterTier = 0;
            } else if (casingType.equals(BlockCleanroomCasing.CasingType.FILTER_CASING_STERILE)) {
                this.filterTier = 1;
            }
        }

        if (type instanceof GCYLCleanroomCasing.CasingType) {
            GCYLCleanroomCasing.CasingType casingType = (GCYLCleanroomCasing.CasingType) type;

            if (casingType.equals(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO3)) {
                this.filterTier = 2;
            } else if (casingType.equals(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO2)) {
                this.filterTier = 3;
            } else if (casingType.equals(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO1)) {
                this.filterTier = 4;
            } else if (casingType.equals(GCYLCleanroomCasing.CasingType.FILTER_CASING_ISO0)) {
                this.filterTier = 5;
            }

        }
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXX", "XXX", "FFF")
                .aisle("XXX", "X#X", "FFF")
                .aisle("XXX", "XSX", "FFF")
                .where('S', selfPredicate())
                .where('X', states(getCasingState()).setMinGlobalLimited(10).or(autoAbilities()))
                .where('F', filterCasings())
                .where('#', air())
                .build();
    }

    protected IBlockState getCasingState() {
        return GCYLMetaBlocks.METAL_CASING_1.getState(MetalCasing1.CasingType.MARAGING_STEEL_250);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return ClientHandler.MARAGING_STEEL_250_CASING;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityLargeAirCollector(metaTileEntityId);
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.GAS_COLLECTOR_OVERLAY;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        ArrayList<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder()
                .aisle("XXX", "XXX", "FFF")
                .aisle("XXX", "X#X", "FFF")
                .aisle("EPM", "ISO", "FFF")
                .where('X', getCasingState())
                .where('S', GCYLTileEntities.LARGE_AIR_COLLECTOR, EnumFacing.NORTH)
                .where('#', Blocks.AIR.getDefaultState())
                .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.IV], EnumFacing.NORTH)
                .where('M',
                        () -> ConfigHolder.machines.enableMaintenance ? MetaTileEntities.MAINTENANCE_HATCH :
                                getCasingState(),
                        EnumFacing.NORTH)
                .where('P', GCYMMetaTileEntities.PARALLEL_HATCH[0], EnumFacing.NORTH)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[0], EnumFacing.NORTH)
                .where('O', MetaTileEntities.FLUID_EXPORT_HATCH[5], EnumFacing.NORTH);


        Arrays.stream(BlockCleanroomCasing.CasingType.values())
                .filter(casingType -> !casingType.equals(BlockCleanroomCasing.CasingType.PLASCRETE))
                .forEach(casingType -> shapeInfo
                        .add(builder.where('F', MetaBlocks.CLEANROOM_CASING.getState(casingType)).build()));

        Arrays.stream(GCYLCleanroomCasing.CasingType.values())
                .forEach(casingType -> shapeInfo
                        .add(builder.where('F', GCYLMetaBlocks.GCYL_CLEANROOM_CASING.getState(casingType)).build()));

        return shapeInfo;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gcyl.machine.large_air_collector.tooltip.1"));
    }

    private class LargeAirCollectorRecipeLogic extends GCYMMultiblockRecipeLogic {
        private final MetaTileEntityLargeAirCollector largeAirCollector;

        public LargeAirCollectorRecipeLogic(GCYMRecipeMapMultiblockController metaTilEntity) {
            super(metaTilEntity);
            this.largeAirCollector = (MetaTileEntityLargeAirCollector) metaTilEntity;
        }


        @Override
        protected void modifyOverclockPre(int @NotNull [] values, @NotNull IRecipePropertyStorage storage) {
            super.modifyOverclockPre(values, storage);

            int duration = (int) Math.max(1, values[1] / (largeAirCollector.filterTier + 1.0));
            int EUt = (int) (values[0] / (largeAirCollector.filterTier + 1.0));

            values[0] = EUt;
            values[1] = duration;
        }
    }
}
