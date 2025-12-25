package com.fulltrix.gcyl.machines.multi.miner;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;
import com.cleanroommc.modularui.value.sync.StringSyncValue;
import com.fulltrix.gcyl.api.pattern.TraceabilityPredicates;
import com.fulltrix.gcyl.client.ClientHandler;
import com.fulltrix.gcyl.materials.GCYLMaterials;
import com.fulltrix.gcyl.api.multi.GCYLRecipeMapMultiblockController;
import com.fulltrix.gcyl.api.recipes.properties.GCYLTemperatureProperty;
import gregicality.multiblocks.api.capability.impl.GCYMMultiblockRecipeLogic;
import gregicality.multiblocks.common.GCYMConfigHolder;
import gregicality.multiblocks.common.metatileentities.GCYMMetaTileEntities;
import gregicality.multiblocks.common.metatileentities.multiblockpart.MetaTileEntityTieredHatch;
import gregtech.api.block.IHeatingCoilBlockStats;
import gregtech.api.capability.IHeatingCoil;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.ProgressBarMultiblock;
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIBuilder;
import gregtech.api.metatileentity.multiblock.ui.TemplateBarBuilder;
import gregtech.api.mui.GTGuiTextures;
import gregtech.api.mui.sync.FixedIntArraySyncValue;
import gregtech.api.pattern.*;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.logic.OCParams;
import gregtech.api.recipes.properties.RecipePropertyStorage;
import gregtech.api.unification.material.Materials;
import gregtech.api.util.GTTransferUtils;
import gregtech.api.util.GTUtility;
import gregtech.api.util.KeyUtil;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import static gregtech.api.GregTechAPI.HEATING_COILS;
import static gregtech.api.util.RelativeDirection.*;

//TODO add fram and casing requirements to use helium and neutron plasma (traceability predicate stuff)
public class MetaTileEntityDeepMiner extends GCYLRecipeMapMultiblockController implements IHeatingCoil, ProgressBarMultiblock {

    protected final MetaTileEntity metaTileEntity;
    private final float TEMPERATURE_DURATION_MULTIPLIER = 1.11F;
    private final float TEMPERATURE_DURATION_MULTIPLIER_INVERSE = 0.90F;
    private final BlockPos.MutableBlockPos minerPos = new BlockPos.MutableBlockPos();
    private BlockPos offsetPos;

    private boolean runRecipe;
    private long maxVoltage;
    private int currentTemperature;
    private int maxTemperature;
    private int fluidType = 0;

    public MetaTileEntityDeepMiner(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, boolean isParallel) {
        super(metaTileEntityId, recipeMap, isParallel);
        this.recipeMapWorkable = new MetaTileEntityDeepMiner.DeepMinerRecipeLogic(this);
        this.metaTileEntity = this;
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("##C###C##","#########","#########","#########","#########","#########","#########","#########","#########","#########","#########","#########","#########")
                .aisle("#CC###CC#","##C###C##","#########","#########","#########","#########","#########","#########","#########","#########","#########","#########","#########")
                .aisle("CCCFFFCCC","#CCFFFCC#","##CCMCC##","##F###F##","##F###F##","##F###F##","##CFFFC##","#########","#########","#########","#########","#########","#########")
                .aisle("##FTTTF##","##FHHHF##","##CHHHC##","###HHH###","###HHH###","###HHH###","##FCCCF##","####F####","####F####","####F####","#########","#########","#########")
                .aisle("##FTATF##","##FHAHF##","##CHAHC##","###HAH###","###HAH###","###HAH###","##FCACF##","###FCF###","###FCF###","###FCF###","####F####","####F####","####F####")
                .aisle("##FTTTF##","##FHHHF##","##CHHHC##","###HHH###","###HHH###","###HHH###","##FCCCF##","####F####","####F####","####F####","#########","#########","#########")
                .aisle("CCCFFFCCC","#CCFFFCC#","##CCSCC##","##F###F##","##F###F##","##F###F##","##CFFFC##","#########","#########","#########","#########","#########","#########")
                .aisle("#CC###CC#","##C###C##","#########","#########","#########","#########","#########","#########","#########","#########","#########","#########","#########")
                .aisle("##C###C##","#########","#########","#########","#########","#########","#########","#########","#########","#########","#########","#########","#########")
                .where('#', any())
                .where('F', getFramePredicate())
                .where('H', heatingCoils())
                .where('A', air())
                .where('C', states(getCasingState()).setMinGlobalLimited(50).or(autoAbilities(true,true,true,true,true,true,false)))
                .where('S', selfPredicate())
                .where('M', abilities(MultiblockAbility.MUFFLER_HATCH))
                .where('T', TraceabilityPredicates.tieredHatchPredicate())
                .build();
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder(RIGHT, DOWN, FRONT)
                .aisle("##C###C##","#########","#########","#########","#########","#########","#########","#########","#########","#########","#########","#########","#########")
                .aisle("#CC###CC#","##C###C##","#########","#########","#########","#########","#########","#########","#########","#########","#########","#########","#########")
                .aisle("CCCFFFCCC","#CCFFFCC#","##CmMEC##","##F###F##","##F###F##","##F###F##","##CFFFC##","#########","#########","#########","#########","#########","#########")
                .aisle("##FTTTF##","##FHHHF##","##CHHHC##","###HHH###","###HHH###","###HHH###","##FCCCF##","####F####","####F####","####F####","#########","#########","#########")
                .aisle("##FTATF##","##FHAHF##","##CHAHC##","###HAH###","###HAH###","###HAH###","##FCACF##","###FCF###","###FCF###","###FCF###","####F####","####F####","####F####")
                .aisle("##FTTTF##","##FHHHF##","##CHHHC##","###HHH###","###HHH###","###HHH###","##FCCCF##","####F####","####F####","####F####","#########","#########","#########")
                .aisle("CCCFFFCCC","#CCFFFCC#","##iISOo##","##F###F##","##F###F##","##F###F##","##CFFFC##","#########","#########","#########","#########","#########","#########")
                .aisle("#CC###CC#","##C###C##","#########","#########","#########","#########","#########","#########","#########","#########","#########","#########","#########")
                .aisle("##C###C##","#########","#########","#########","#########","#########","#########","#########","#########","#########","#########","#########","#########")
                .where('S', this, EnumFacing.SOUTH)
                .where('M', MetaTileEntities.MUFFLER_HATCH[1], EnumFacing.NORTH)
                .where('m', MetaTileEntities.MAINTENANCE_HATCH, EnumFacing.NORTH)
                .where('I', MetaTileEntities.ITEM_IMPORT_BUS[3], EnumFacing.SOUTH)
                .where('O', MetaTileEntities.ITEM_EXPORT_BUS[3], EnumFacing.SOUTH)
                .where('i', MetaTileEntities.FLUID_IMPORT_HATCH[3], EnumFacing.SOUTH)
                .where('o', MetaTileEntities.FLUID_EXPORT_HATCH[3], EnumFacing.SOUTH)
                .where('C', this.getCasingState())
                .where('F', MetaBlocks.FRAMES.get(Materials.Steel).getStateFromMeta(4));
        return HEATING_COILS.entrySet().stream()
                .sorted(Comparator.comparingInt(entry -> entry.getValue().getTier()))
                .map(entry -> builder.where('H', entry.getKey())
                        .where('T', GCYMMetaTileEntities.TIERED_HATCH[entry.getValue().getTier() + 1], EnumFacing.DOWN)
                        .where('E', MetaTileEntities.ENERGY_INPUT_HATCH[entry.getValue().getTier() + 1], EnumFacing.NORTH).build())
                .collect(Collectors.toList());
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack,player,tooltip,advanced);
        tooltip.add(I18n.format("gcyl.multiblock.deep_miner.tooltip.1"));
        tooltip.add(I18n.format("gcyl.multiblock.deep_miner.tooltip.2"));
        tooltip.add(I18n.format("gcyl.multiblock.deep_miner.tooltip.3"));
        tooltip.add(I18n.format("gcyl.multiblock.deep_miner.tooltip.4"));
        tooltip.add(I18n.format("gcyl.multiblock.deep_miner.tooltip.5"));
        tooltip.add(I18n.format("gcyl.multiblock.deep_miner.tooltip.6"));
        tooltip.add(I18n.format("gcyl.multiblock.deep_miner.tooltip.11"));
        tooltip.add(I18n.format("gcyl.multiblock.deep_miner.tooltip.7"));
        tooltip.add(I18n.format("gcyl.multiblock.deep_miner.tooltip.8", TEMPERATURE_DURATION_MULTIPLIER_INVERSE));
        //tooltip.add(I18n.format("gcyl.multiblock.deep_miner.tooltip.9"));
        //tooltip.add(I18n.format("gcyl.multiblock.deep_miner.tooltip.10"));
    }

    @Override
    protected void configureDisplayText(MultiblockUIBuilder builder) {
        super.configureDisplayText(builder);
            builder.addCustom((keyManager, uiSyncer) -> {
                int currentTemp = uiSyncer.syncInt(getCurrentTemperature());
                int maxTemp = uiSyncer.syncInt(getMaxTemperature());
                int x = uiSyncer.syncInt(this.minerPos.getX());
                int y = uiSyncer.syncInt(this.minerPos.getY());
                int z = uiSyncer.syncInt(this.minerPos.getZ());
                boolean runRecipe = uiSyncer.syncBoolean(this.runRecipe);
                keyManager.add(KeyUtil.lang(TextFormatting.GRAY, "gregtech.multiblock.universal.vom.temperature", currentTemp));
                keyManager.add(KeyUtil.lang(TextFormatting.GRAY, "gregtech.multiblock.deep_miner.max.temperature", maxTemp));
                keyManager.add(KeyUtil.lang(TextFormatting.GRAY, "gregtech.multiblock.deep_miner.max.fluid consumption", uiSyncer.syncBoolean(this.recipeMapWorkable.isActive()) && currentTemp == maxTemp ? getHeatingFluidActiveMax(getFluidType()).amount : getHeatingFluid(getFluidType()).amount));
                if (uiSyncer.syncBoolean(this.recipeMapWorkable.isActive()))
                    keyManager.add(runRecipe ? KeyUtil.lang(TextFormatting.GREEN, "gcyl.multiblock.deep_miner.clear")
                            : KeyUtil.lang("gregtech.multiblock.deep_miner_error", x, y, z));
            });
    }


    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        int tier = context.getOrDefault("tiered_hatches", new ArrayList<MetaTileEntityTieredHatch>()).get(0).getTier() - 1;
        this.maxVoltage = 32L << tier * 2;
        this.offsetPos = this.getPos().offset(this.getFrontFacing().getOpposite(), 2);
        Object type = context.get("CoilType");
        if (type instanceof IHeatingCoilBlockStats) {
            this.maxTemperature = (int) (((IHeatingCoilBlockStats) type).getCoilTemperature() * getTemperatureModifierFromFluidType(getFluidType()));
        } else {
            this.maxTemperature = BlockWireCoil.CoilType.CUPRONICKEL.getCoilTemperature();
        }
    }

    @Override
    protected void updateFormedValid() {
        super.updateFormedValid();

        if (this.recipeMapWorkable.isWorkingEnabled() && checkHeatingFluid() && getCurrentTemperature() <= getMaxTemperature()) {
            if (getOffsetTimer() % 20 == 0) {
                if (getCurrentTemperature() < getMaxTemperature()) {
                    drainHeatingFluid();
                    increaseTemperature();
                } else if (getCurrentTemperature() == getMaxTemperature() && this.recipeMapWorkable.isActive()) {
                    drainHeatingFluidActiveMax();
                } else if (!this.isActive()) {
                    decreaseTemperature();
                }
            }
        } else if (getOffsetTimer() % 20 == 0 && getCurrentTemperature() != 0 && !this.recipeMapWorkable.isActive()) {
            decreaseTemperature();
        }
    }

    protected FluidStack getHeatingFluid(int fluidType) {
        return switch (fluidType) {
            case 0 ->
                    Materials.Lava.getFluid(4 * (int) (Math.pow(2, 1.5 * GTUtility.getTierByVoltage(this.energyContainer.getInputVoltage()))));
            case 1 ->
                    GCYLMaterials.Pyrotheum.getFluid((int) (Math.pow(2, 1.5 * GTUtility.getTierByVoltage(this.energyContainer.getInputVoltage()))));
            case 2 ->
                    Materials.Helium.getFluid(FluidStorageKeys.PLASMA, (int) (4 * Math.pow(2, GTUtility.getTierByVoltage(this.energyContainer.getInputVoltage()))));
            default -> GCYLMaterials.NeutronPlasma.getFluid(FluidStorageKeys.PLASMA,(int) (2 * Math.pow(2, GTUtility.getTierByVoltage(this.energyContainer.getInputVoltage()))));
        };
    }

    protected FluidStack getHeatingFluidActiveMax(int fluidType) {
        return switch (fluidType) {
            case 0 ->
                    Materials.Lava.getFluid(4 * (int) (Math.pow(2, 1.5 * GTUtility.getTierByVoltage(this.energyContainer.getInputVoltage())) / 10.0));
            case 1 ->
                    GCYLMaterials.Pyrotheum.getFluid((int) (Math.pow(2, 1.5 * GTUtility.getTierByVoltage(this.energyContainer.getInputVoltage())) / 10.0));
            case 2 ->
                    Materials.Helium.getFluid(FluidStorageKeys.PLASMA, (int) (4 * Math.pow(2, GTUtility.getTierByVoltage(this.energyContainer.getInputVoltage())) / 10.0));
            default -> GCYLMaterials.NeutronPlasma.getFluid(FluidStorageKeys.PLASMA,(int) (2 * Math.pow(2, GTUtility.getTierByVoltage(this.energyContainer.getInputVoltage())) / 10.0));
        };
    }

    protected boolean checkHeatingFluid() {
        IMultipleTankHandler inputTank = this.getInputFluidInventory();
        return getHeatingFluid(getFluidType()).isFluidStackIdentical(inputTank.drain(getHeatingFluid(getFluidType()), false));
    }

    protected void drainHeatingFluid() {
        this.getInputFluidInventory().drain(getHeatingFluid(getFluidType()), true);
    }

    protected void drainHeatingFluidActiveMax() {this.getInputFluidInventory().drain(getHeatingFluidActiveMax(getFluidType()), true);}

    protected void increaseTemperature() {
        if (getCurrentTemperature() < getMaxTemperature()) {
            int tTemperature = this.currentTemperature;
            tTemperature += (int) (Math.max(((getMaxTemperature() - getCurrentTemperature()) / 20.0), 5) * getModifierFromFluidType(getFluidType()));
            this.currentTemperature = Math.min(tTemperature, getMaxTemperature());
        }
    }

    protected void decreaseTemperature() {
        if(getCurrentTemperature() > 0) {
            int tTemperature = this.currentTemperature;
            tTemperature -= Math.max((int) (getCurrentTemperature() / 20.0), 5);
            this.currentTemperature = Math.max(tTemperature, 0);
        }
    }

    protected double getModifierFromFluidType(int type) {
        return switch (type) {
            case 0 -> 0.5;
            case 1 -> 1.0;
            case 2 -> 4.0;
            default -> 16.0;
        };
    }

    protected double getTemperatureModifierFromFluidType(int type) {
        return switch (type) {
            case 0 -> 0.75;
            case 1 -> 1.0;
            case 2 -> 4.0;
            default -> 16.0;
        };
    }

    @Override
    public boolean onScrewdriverClick(EntityPlayer playerIn, EnumHand hand, EnumFacing facing,
                                      CuboidRayTraceResult hitResult) {
        if(!getWorld().isRemote && !this.isActive()) {
            if(fluidType == 0) {
                fluidType = 1;
                playerIn.sendStatusMessage(
                        new TextComponentTranslation("gcyl.machine.deep_miner.config.1"), false);
                invalidateStructure();
            }
            else if(fluidType == 1) {
                fluidType = 2;
                playerIn.sendStatusMessage(
                        new TextComponentTranslation("gcyl.machine.deep_miner.config.2"), false);
                invalidateStructure();
                this.createStructurePattern();
            }
            else if(fluidType == 2) {
                fluidType = 3;
                playerIn.sendStatusMessage(
                        new TextComponentTranslation("gcyl.machine.deep_miner.config.3"), false);
                this.createStructurePattern();
                invalidateStructure();
            }
            else {
                fluidType = 0;
                playerIn.sendStatusMessage(
                        new TextComponentTranslation("gcyl.machine.deep_miner.config.0"), false);
                this.createStructurePattern();
                invalidateStructure();
            }
        }
        return true;
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.currentTemperature = 0;
        this.maxVoltage = 0;
        this.offsetPos = null;
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return switch (fluidType) {
            case 2-> ClientHandler.INCOLOY_813_CASING;
            case 3-> ClientHandler.HASTELLOY_K243_CASING;
            default-> Textures.SOLID_STEEL_CASING;
        };
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityDeepMiner(metaTileEntityId, this.recipeMap, this.isParallel());
    }

    @Override
    public boolean hasMufflerMechanics() {return true;}

    private IBlockState getCasingState() {


        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID);
    }

    /*

    frames(GCYLMaterials.Incoloy813);
    frames(GCYLMaterials.HastelloyK243);
    frames(Materials.Steel);

     GCYLMetaBlocks.METAL_CASING_1.getState(MetalCasing1.CasingType.INCOLOY_813);
    GCYLMetaBlocks.METAL_CASING_1.getState(MetalCasing1.CasingType.HASTELLOY_K243);
   MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID);


     */

    private TraceabilityPredicate getFramePredicate() {


        return frames(Materials.Steel);
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.LARGE_MINER_OVERLAY_BASIC;
    }

    @Override
    public int getCurrentTemperature() {
        return this.currentTemperature;
    }

    public int getMaxTemperature() {
        return this.maxTemperature;
    }

    public int getFluidType() {
        return this.fluidType;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setTag("temperature", new NBTTagInt(getCurrentTemperature()));
        data.setTag("fluidtype", new NBTTagInt(getFluidType()));
        data.setBoolean("runRecipe", this.runRecipe);
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.currentTemperature = data.getInteger("temperature");
        this.fluidType = data.getInteger("fluidtype");
        this.runRecipe = data.getBoolean("runRecipe");
    }

    @Override
    public int getProgressBarCount() {
        return 1;
    }

    @Override
    public void registerBars(List<UnaryOperator<TemplateBarBuilder>> bars, PanelSyncManager syncManager) {
        StringSyncValue boosterNameValue = new StringSyncValue(() -> this.getHeatingFluid(this.getFluidType()).getFluid().getName());
        syncManager.syncValue("booster_name", boosterNameValue);
        FixedIntArraySyncValue boosterValue = new FixedIntArraySyncValue(this::getBoosterAmount, null);
        syncManager.syncValue("booster_value", boosterValue);

        bars.add(bar -> bar.progress(() -> boosterValue.getValue(1) == 0 ? 0 : 1.0 * boosterValue.getValue(0) / boosterValue.getValue(1))
                .texture(GTGuiTextures.PROGRESS_BAR_LCE_FUEL)
                .tooltipBuilder(tooltip -> this.createFuelTooltip(tooltip, boosterValue, boosterNameValue)));
    }

    private int[] getBoosterAmount() {
        FluidStack booster = this.getHeatingFluid(this.getFluidType()).copy();
        booster.amount = Integer.MAX_VALUE;
        return this.getInputFluidInventory() != null ? this.getTotalFluidAmount(booster, this.getInputFluidInventory()) : new int[2];
    }


    private class DeepMinerRecipeLogic extends GCYMMultiblockRecipeLogic {

        private final MetaTileEntityDeepMiner deepMiner;

        public DeepMinerRecipeLogic(GCYLRecipeMapMultiblockController metaTileEntity) {
            super(metaTileEntity);
            this.deepMiner = (MetaTileEntityDeepMiner) metaTileEntity;
        }

        @Override
        protected void updateRecipeProgress() {
            if (offsetPos != null && this.metaTileEntity.getOffsetTimer() % 20 == 0)
                this.mineBlocks();
            super.updateRecipeProgress();
        }

        @Override
        protected boolean canProgressRecipe() {
            return runRecipe && super.canProgressRecipe();
        }

        private void mineBlocks() {
            runRecipe = false;
            for (int y = offsetPos.getY(); y > -1; y--) {
                minerPos.setPos(offsetPos.getX(), y, offsetPos.getZ());
                World world = this.metaTileEntity.getWorld();
                IBlockState state = world.getBlockState(minerPos);
                Block block = state.getBlock();
                if (block == Blocks.BEDROCK || y < 1) {
                    runRecipe = true;
                    break;
                } else if (state.getMaterial().isLiquid() || block instanceof IFluidBlock) {
                    this.replaceWithCobble(world, minerPos, state);
                    break;
                } else if (block != Blocks.AIR) {
                    this.breakBlock(world, minerPos, state);
                    break;
                }
            }
        }

        /**
         * Replace 3x3 area with cobblestone if a fluid is hit
         */
        private void replaceWithCobble(World world, BlockPos pos, IBlockState state) {
            BlockPos.MutableBlockPos newPos = new BlockPos.MutableBlockPos(pos);
            for (int x = -1; x < 2; x++) {
                for (int z = -1; z < 2; z++) {
                    newPos.setPos(pos.getX() + x, pos.getY(), pos.getZ() + z);
                    this.breakBlock(world, newPos, state);
                    world.setBlockState(newPos, Blocks.COBBLESTONE.getDefaultState());
                }
            }
        }

        /**
         * Break block and send its drops to item output. Doesn't check if the drops fit
         */
        private void breakBlock(World world, BlockPos pos, IBlockState state) {
            NonNullList<ItemStack> itemDrops = NonNullList.create();
            state.getBlock().getDrops(itemDrops, world, pos, state, 0);
            world.destroyBlock(pos, false);
            GTTransferUtils.addItemsToItemHandler(this.getOutputInventory(), false, itemDrops);
        }

        @Override
        public boolean checkRecipe(Recipe recipe) {
            if (!super.checkRecipe(recipe))
                return false;
            return recipe.getProperty(GCYLTemperatureProperty.getInstance(), 0) <= deepMiner.getCurrentTemperature();
        }

        @Override
        protected void modifyOverclockPre(@NotNull OCParams ocParams, @NotNull RecipePropertyStorage storage) {
            super.modifyOverclockPre(ocParams, storage);

            int recipeTemperature = storage.get(GCYLTemperatureProperty.getInstance(),0);
            int temperatureDiff = deepMiner.getCurrentTemperature() - recipeTemperature;
            double durationModifier = temperatureDiff / 1000 < 1 ? 1 : TEMPERATURE_DURATION_MULTIPLIER * (temperatureDiff / 1000);
            ocParams.setDuration((int) (ocParams.duration() / durationModifier));
        }

        @Override
        public long getMaxVoltage() {
            return GCYMConfigHolder.globalMultiblocks.enableTieredCasings ? maxVoltage : super.getMaxVoltage();
        }
    }
}
