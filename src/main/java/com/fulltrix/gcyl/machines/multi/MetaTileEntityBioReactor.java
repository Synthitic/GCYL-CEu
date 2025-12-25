package com.fulltrix.gcyl.machines.multi;

import com.fulltrix.gcyl.api.multi.GCYLMultiblockRecipeLogic;
import com.fulltrix.gcyl.api.multi.GCYLRecipeMapMultiblockController;
import com.fulltrix.gcyl.api.pattern.TraceabilityPredicates;
import com.fulltrix.gcyl.client.ClientHandler;
import com.fulltrix.gcyl.blocks.GCYLMetaBlocks;
import com.fulltrix.gcyl.blocks.GCYLMultiblockCasing2;
import com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps;
import gregicality.multiblocks.common.GCYMConfigHolder;
import gregicality.multiblocks.common.metatileentities.GCYMMetaTileEntities;
import gregicality.multiblocks.common.metatileentities.multiblockpart.MetaTileEntityTieredHatch;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.MultiblockShapeInfo;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static gregtech.api.util.RelativeDirection.*;

public class MetaTileEntityBioReactor extends GCYLRecipeMapMultiblockController {

    private long maxVoltage;

    public MetaTileEntityBioReactor(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GCYLRecipeMaps.BIO_REACTOR_RECIPES, false);
        this.recipeMapWorkable = new GCYLMultiblockRecipeLogic(this, false) {
            @Override
            public long getMaxVoltage() {
                return GCYMConfigHolder.globalMultiblocks.enableTieredCasings ? maxVoltage : super.getMaxVoltage();
            }
        };
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gcyl.multiblock.bio_reactor.description"));
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityBioReactor(metaTileEntityId);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXXXX", "XGGGX", "XGGGX", "XGGGX", "XXXXX")
                .aisle("XXXXX", "G###G", "G#T#G", "G###G", "XXXXX")
                .aisle("XXXXX", "G#T#G", "GTTTG", "G#T#G", "XXXXX")
                .aisle("XXXXX", "G###G", "G#T#G", "G###G", "XXXXX")
                .aisle("XXSXX", "XGGGX", "XGGGX", "XGGGX", "XXXXX")
                .where('S', selfPredicate())
                .where('X', states(getCasingState()).setMinGlobalLimited(50).or(autoAbilities(true, true, true, true, true, true, false)))
                .where('L', states(getCasingState()))
                .where('#', air())
                .where('G', states(MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.LAMINATED_GLASS)))
                .where('T', TraceabilityPredicates.tieredHatchPredicate())
                .build();
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder(RIGHT, DOWN, FRONT)
                .aisle("XXEMX", "XGGGX", "XGGGX", "XGGGX", "XXXXX")
                .aisle("XXXXX", "G###G", "G#T#G", "G###G", "XXXXX")
                .aisle("XXXXX", "G#T#G", "GTTTG", "G#T#G", "XXXXX")
                .aisle("XXXXX", "G###G", "G#T#G", "G###G", "XXXXX")
                .aisle("iISOo", "XGGGX", "XGGGX", "XGGGX", "XXXXX")
                .where('S', this, EnumFacing.SOUTH)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[3], EnumFacing.SOUTH)
                .where('O', MetaTileEntities.ITEM_EXPORT_BUS[3], EnumFacing.SOUTH)
                .where('i', MetaTileEntities.FLUID_IMPORT_HATCH[3], EnumFacing.SOUTH)
                .where('o', MetaTileEntities.FLUID_EXPORT_HATCH[3], EnumFacing.SOUTH)
                .where('M', MetaTileEntities.MAINTENANCE_HATCH, EnumFacing.NORTH)
                .where('X', this.getCasingState())
                .where('L', this.getCasingState())
                .where('G', MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.LAMINATED_GLASS));
        return Arrays.stream(GCYMMetaTileEntities.TIERED_HATCH)
                .map(hatch -> builder.where('T', hatch, EnumFacing.DOWN)
                        .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[hatch.getTier()], EnumFacing.NORTH)
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        int tier = context.getOrDefault("tiered_hatches", new ArrayList<MetaTileEntityTieredHatch>()).get(0).getTier() - 1;
        this.maxVoltage = 32L << tier * 2;
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.maxVoltage = 0;
    }

    private IBlockState getCasingState() {
        return GCYLMetaBlocks.MULTIBLOCK_CASING2.getState(GCYLMultiblockCasing2.CasingType.BIO_REACTOR);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return ClientHandler.BIO_REACTOR;
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return ClientHandler.ORGANIC_REPLICATOR_OVERLAY;
    }
}