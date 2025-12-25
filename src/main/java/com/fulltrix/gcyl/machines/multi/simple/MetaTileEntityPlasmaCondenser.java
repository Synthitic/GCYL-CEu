package com.fulltrix.gcyl.machines.multi.simple;

import com.fulltrix.gcyl.api.multi.GCYLMultiblockRecipeLogic;
import com.fulltrix.gcyl.api.multi.GCYLRecipeMapMultiblockController;
import com.fulltrix.gcyl.api.pattern.TraceabilityPredicates;
import com.fulltrix.gcyl.blocks.metal.MetalCasing1;
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
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockTurbineCasing;
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

import static com.fulltrix.gcyl.client.ClientHandler.HASTELLOY_N_CASING;
import static com.fulltrix.gcyl.blocks.GCYLMetaBlocks.METAL_CASING_1;
import static gregtech.api.util.RelativeDirection.*;

public class MetaTileEntityPlasmaCondenser extends GCYLRecipeMapMultiblockController {

    private long maxVoltage;

    public MetaTileEntityPlasmaCondenser(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GCYLRecipeMaps.PLASMA_CONDENSER_RECIPES, false);
        this.recipeMapWorkable = new GCYLMultiblockRecipeLogic(this, false) {
            @Override
            public long getMaxVoltage() {
                return GCYMConfigHolder.globalMultiblocks.enableTieredCasings ? maxVoltage : super.getMaxVoltage();
            }
        };
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityPlasmaCondenser(metaTileEntityId);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gcyl.multiblock.plasma_condenser.description"));
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("#####", "#XXX#", "#XXX#", "#XXX#", "#####")
                .aisle("#XXX#", "XGAGX", "XAPAX", "XGpGX", "#XXX#")
                .aisle("#XXX#", "XAPAX", "XPPPX", "XpPpX", "#XXX#")
                .aisle("#XXX#", "XGAGX", "XAPAX", "XGpGX", "#XXX#")
                .aisle("#####", "#XXX#", "#XSX#", "#XXX#", "#####")
                .where('S', selfPredicate())
                .where('X', states(getCasingState()).setMinGlobalLimited(25).or(autoAbilities(true,true,true,true,true,true,false)))
                .where('G', states(MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.STEEL_GEARBOX)))
                .where('P', states(MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE)))
                .where('A', air())
                .where('#', any())
                .where('p', TraceabilityPredicates.tieredHatchPredicate())
                .build();
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder(RIGHT, DOWN, FRONT)
                .aisle("#####", "#XMX#", "#XEX#", "#XXX#", "#####")
                .aisle("#XXX#", "XGAGX", "XAPAX", "XGpGX", "#XXX#")
                .aisle("#XXX#", "XAPAX", "XPPPX", "XpPpX", "#XXX#")
                .aisle("#XXX#", "XGAGX", "XAPAX", "XGpGX", "#XXX#")
                .aisle("#####", "#XXX#", "#ISO#", "#iXo#", "#####")
                .where('S', this, EnumFacing.SOUTH)
                .where('X', this.getCasingState())
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[3], EnumFacing.SOUTH)
                .where('O', MetaTileEntities.ITEM_EXPORT_BUS[3], EnumFacing.SOUTH)
                .where('i', MetaTileEntities.FLUID_IMPORT_HATCH[3], EnumFacing.SOUTH)
                .where('o', MetaTileEntities.FLUID_EXPORT_HATCH[3], EnumFacing.SOUTH)
                .where('M', MetaTileEntities.MAINTENANCE_HATCH, EnumFacing.NORTH)
                .where('G', MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.STEEL_GEARBOX))
                .where('P', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.TUNGSTENSTEEL_PIPE));
        return Arrays.stream(GCYMMetaTileEntities.TIERED_HATCH)
                .map(hatch -> builder.where('p', hatch, EnumFacing.DOWN)
                        .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[hatch.getTier()], EnumFacing.NORTH).build())
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
        return METAL_CASING_1.getState(MetalCasing1.CasingType.HASTELLOY_N);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return HASTELLOY_N_CASING;
    }

    @SideOnly(Side.CLIENT)
    @Nonnull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.ARC_FURNACE_OVERLAY;
    }

}
