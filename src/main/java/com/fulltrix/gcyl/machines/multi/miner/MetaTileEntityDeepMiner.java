package com.fulltrix.gcyl.machines.multi.miner;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import com.fulltrix.gcyl.client.ClientHandler;
import com.fulltrix.gcyl.materials.GCYLMaterials;
import com.fulltrix.gcyl.api.multi.GCYLRecipeMapMultiblockController;
import com.fulltrix.gcyl.api.recipes.properties.GCYLTemperatureProperty;
import gregicality.multiblocks.api.capability.impl.GCYMMultiblockRecipeLogic;
import gregtech.api.block.IHeatingCoilBlockStats;
import gregtech.api.capability.IHeatingCoil;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.recipeproperties.IRecipePropertyStorage;
import gregtech.api.unification.material.Materials;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

//TODO add fram and casing requirements to use helium and neutron plasma (traceability predicate stuff)
//TODO implement breaking a blocks to bedrock
public class MetaTileEntityDeepMiner extends GCYLRecipeMapMultiblockController implements IHeatingCoil {

    protected final MetaTileEntity metaTileEntity;

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
                .where('T', tieredCasing())
                .build();
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
        tooltip.add(I18n.format("gcyl.multiblock.deep_miner.tooltip.7"));
        tooltip.add(I18n.format("gcyl.multiblock.deep_miner.tooltip.8"));
        //tooltip.add(I18n.format("gcyl.multiblock.deep_miner.tooltip.9"));
        //tooltip.add(I18n.format("gcyl.multiblock.deep_miner.tooltip.10"));
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
        if (this.isStructureFormed() && !this.hasMaintenanceProblems()) {
            textList.add(new TextComponentTranslation("gregtech.multiblock.universal.vom.temperature", getCurrentTemperature()));
            textList.add(new TextComponentTranslation("gregtech.multiblock.deep_miner.max.temperature", getMaxTemperature()));
        }
        if(this.getPos().getY() > 8 && !this.getWorld().isRemote)
            textList.add(new TextComponentTranslation("gregtech.multiblock.deep_miner_error"));
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
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

        if(this.recipeMapWorkable.isWorkingEnabled() && checkHeatingFluid() && getCurrentTemperature() <= getMaxTemperature()) {
            if(getOffsetTimer() % 20 == 0) {
                if(getCurrentTemperature() != getMaxTemperature()) {
                    drainHeatingFluid();
                    increaseTemperature();
                }
                else {
                    if(!this.isActive())
                        decreaseTemperature();
                }
            }
        }
        else {
            if (getOffsetTimer() % 20 == 0 && getCurrentTemperature() != 0 && !this.recipeMapWorkable.isActive()) {
                decreaseTemperature();
            }
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

    protected boolean checkHeatingFluid() {
        IMultipleTankHandler inputTank = this.getInputFluidInventory();
        return getHeatingFluid(getFluidType()).isFluidStackIdentical(inputTank.drain(getHeatingFluid(getFluidType()), false));
    }

    protected void drainHeatingFluid() {
        this.getInputFluidInventory().drain(getHeatingFluid(getFluidType()), true);
    }

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
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        currentTemperature = data.getInteger("temperature");
        fluidType = data.getInteger("fluidtype");
    }


    private class DeepMinerRecipeLogic extends GCYMMultiblockRecipeLogic {

        private final MetaTileEntityDeepMiner deepMiner;

        public DeepMinerRecipeLogic(GCYLRecipeMapMultiblockController metaTileEntity) {
            super(metaTileEntity);
            this.deepMiner = (MetaTileEntityDeepMiner) metaTileEntity;
        }

        @Override
        public boolean checkRecipe(Recipe recipe) {
            if (!super.checkRecipe(recipe))
                return false;

            if(deepMiner.getPos().getY() > 8)
                return false;

            return recipe.getProperty(GCYLTemperatureProperty.getInstance(), 0) <= deepMiner.getCurrentTemperature();
        }

        @Override
        protected void modifyOverclockPre(int @NotNull [] values, @NotNull IRecipePropertyStorage storage) {
            super.modifyOverclockPre(values, storage);

            int recipeTemperature = storage.getRecipePropertyValue(GCYLTemperatureProperty.getInstance(),0);
            int temperatureDiff = deepMiner.getCurrentTemperature() - recipeTemperature;
            double durationModifier = temperatureDiff / 1000 < 1 ? 1 : 1.25 * (temperatureDiff / 1000);
            int recipeDuration = (int) (values[1] / durationModifier);

            values[1] = recipeDuration;
        }
    }
}
