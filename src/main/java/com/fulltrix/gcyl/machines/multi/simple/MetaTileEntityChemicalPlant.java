package com.fulltrix.gcyl.machines.multi.simple;

import com.cleanroommc.modularui.api.drawable.IKey;
import com.fulltrix.gcyl.api.multi.GCYLMultiblockRecipeLogic;
import com.fulltrix.gcyl.api.multi.GCYLRecipeMapMultiblockController;
import com.fulltrix.gcyl.api.pattern.TraceabilityPredicates;
import com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps;
import gregicality.multiblocks.common.GCYMConfigHolder;
import gregicality.multiblocks.common.metatileentities.GCYMMetaTileEntities;
import gregicality.multiblocks.common.metatileentities.multiblockpart.MetaTileEntityTieredHatch;
import gregtech.api.block.IHeatingCoilBlockStats;
import gregtech.api.capability.IHeatingCoil;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIBuilder;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.MultiblockShapeInfo;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.logic.OCResult;
import gregtech.api.recipes.properties.RecipePropertyStorage;
import gregtech.api.util.KeyUtil;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static gregtech.api.GregTechAPI.HEATING_COILS;
import static gregtech.api.util.RelativeDirection.*;

//TODO make a separate mega chemical reactor so this one isnt so broken
public class MetaTileEntityChemicalPlant extends GCYLRecipeMapMultiblockController implements IHeatingCoil {

    private long maxVoltage;
    private int temperature;
    private int tier;

    public MetaTileEntityChemicalPlant(ResourceLocation metaTileEntityId, boolean isParallel) {
        super(metaTileEntityId, new RecipeMap[] { GCYLRecipeMaps.CHEMICAL_PLANT_RECIPES, RecipeMaps.LARGE_CHEMICAL_RECIPES }, isParallel);
        this.recipeMapWorkable = new GCYLMultiblockRecipeLogic(this, true) {
            @Override
            public long getMaxVoltage() {
                return GCYMConfigHolder.globalMultiblocks.enableTieredCasings ? maxVoltage : super.getMaxVoltage();
            }

            @Override
            protected void modifyOverclockPost(@NotNull OCResult ocResult, @NotNull RecipePropertyStorage storage) {
                super.modifyOverclockPost(ocResult, storage);
                int coilTier = ((MetaTileEntityChemicalPlant) metaTileEntity).getCoilTier();
                if (coilTier <= 0)
                    return;

                // each coil above cupronickel (coilTier = 0) uses 5% less energy
                ocResult.setEut(Math.max(1, (long) (ocResult.eut() * (1.0 - coilTier * 0.05))));
            }
        };
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityChemicalPlant(metaTileEntityId, this.isParallel());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gcyl.multiblock.chemical_plant.description"));
        tooltip.add(I18n.format("gcyl.multiblock.chemical_plant.tooltip.1"));
        tooltip.add(I18n.format("gcyl.multiblock.chemical_plant.tooltip.2"));
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("X###X", "XXXXX", "X###X", "XXXXX", "X###X")
                .aisle("XXXXX", "XCCCX", "XPPPX", "XCCCX", "XXXXX")
                .aisle("X###X", "XPPPX", "XMMMX", "XPPPX", "X###X")
                .aisle("XXXXX", "XCCCX", "XPPPX", "XCCCX", "XXXXX")
                .aisle("X###X", "SXXXX", "X###X", "XXXXX", "X###X")
                .where('S', selfPredicate())
                .where('X', states(getCasingState()).setMinGlobalLimited(60).or(autoAbilities(true,true,true,true,true,true,false)))
                .where('C', heatingCoils())
                .where('P', states(MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.POLYTETRAFLUOROETHYLENE_PIPE)))
                .where('#', air())
                .where('M', TraceabilityPredicates.tieredHatchPredicate())
                .build();
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder(RIGHT, DOWN, FRONT)
                .aisle("E###m", "XXXXX", "X###X", "XXXXX", "X###X")
                .aisle("XXXXX", "XCCCX", "XPPPX", "XCCCX", "XXXXX")
                .aisle("X###X", "XPPPX", "XMMMX", "XPPPX", "X###X")
                .aisle("XXXXX", "XCCCX", "XPPPX", "XCCCX", "XXXXX")
                .aisle("X###X", "oIOiS", "X###X", "XXXXX", "X###X")
                .where('S', this, EnumFacing.SOUTH)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[3], EnumFacing.SOUTH)
                .where('O', MetaTileEntities.ITEM_EXPORT_BUS[3], EnumFacing.SOUTH)
                .where('i', MetaTileEntities.FLUID_IMPORT_HATCH[3], EnumFacing.SOUTH)
                .where('o', MetaTileEntities.FLUID_EXPORT_HATCH[3], EnumFacing.SOUTH)
                .where('m', MetaTileEntities.MAINTENANCE_HATCH, EnumFacing.NORTH)
                .where('X', this.getCasingState())
                .where('P', MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.POLYTETRAFLUOROETHYLENE_PIPE));
        return HEATING_COILS.entrySet().stream()
                .sorted(Comparator.comparingInt(entry -> entry.getValue().getTier()))
                .map(entry -> builder.where('C', entry.getKey())
                        .where('M', GCYMMetaTileEntities.TIERED_HATCH[entry.getValue().getTier() + 1], EnumFacing.DOWN)
                        .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[entry.getValue().getTier() + 1], EnumFacing.NORTH).build())
                .collect(Collectors.toList());
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        IHeatingCoilBlockStats coilType = context.getOrDefault("CoilType", BlockWireCoil.CoilType.CUPRONICKEL);
        int tier = context.getOrDefault("tiered_hatches", new ArrayList<MetaTileEntityTieredHatch>()).get(0).getTier() - 1;
        this.maxVoltage = 32L << tier * 2;
        this.temperature = coilType.getCoilTemperature();
        this.tier = coilType.getTier();
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.maxVoltage = 0;
        this.temperature = 0;
        this.tier = 0;
    }

    @Override
    protected void configureDisplayText(MultiblockUIBuilder builder) {
        super.configureDisplayText(builder);
        builder.addCustom((textList, syncer) -> {
            if (!isStructureFormed()) return;

            // Coil energy discount line
            IKey energyDiscount = KeyUtil.number(TextFormatting.AQUA,
                    syncer.syncLong(100 - 5L * getCoilTier()), "%");

            IKey base = KeyUtil.lang(TextFormatting.GRAY,
                    "gregtech.multiblock.cracking_unit.energy",
                    energyDiscount);

            IKey hover = KeyUtil.lang(TextFormatting.GRAY,
                    "gregtech.multiblock.cracking_unit.energy_hover");

            textList.add(KeyUtil.setHover(base, hover));
        });
    }

    public int getCurrentTemperature() {return this.temperature;}

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return Textures.INERT_PTFE_CASING;
    }

    public IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.PTFE_INERT_CASING);
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.LARGE_CHEMICAL_REACTOR_OVERLAY;
    }

    public int getCoilTier() {
        return this.tier;
    }

    @Override
    public int getMaxParallel() {
        return this.getCurrentRecipeMap() == GCYLRecipeMaps.CHEMICAL_PLANT_RECIPES ? 1 : super.getMaxParallel();
    }
}
