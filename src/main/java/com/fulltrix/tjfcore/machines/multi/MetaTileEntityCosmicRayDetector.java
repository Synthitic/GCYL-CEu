package com.fulltrix.tjfcore.machines.multi;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import com.fulltrix.tjfcore.TJFMaterials;
import com.fulltrix.tjfcore.client.ClientHandler;
import com.fulltrix.tjfcore.item.metal.MetalCasing2;
import com.google.common.collect.Lists;
import gregtech.api.GTValues;
import gregtech.api.capability.*;
import gregtech.api.capability.impl.EnergyContainerList;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.blocks.BlockFusionCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.core.sound.GTSoundEvents;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

import static com.fulltrix.tjfcore.TJFMaterials.BlackTitanium;
import static com.fulltrix.tjfcore.client.ClientHandler.QUANTUM_CASING;
import static com.fulltrix.tjfcore.item.TJFMetaBlocks.METAL_CASING_2;

public class MetaTileEntityCosmicRayDetector extends MultiblockWithDisplayBase implements IControllable {
    public boolean isWorkingEnabled = false;
    public boolean isActive = false;
    protected IMultipleTankHandler exportFluidHandler;
    private long maxVoltage = 0;
    private boolean canSeeSky = false;
    private boolean hasEnoughEnergy = false;
    private IEnergyContainer energyContainer;
    private int amount = 0;
    public MetaTileEntityCosmicRayDetector(ResourceLocation metaTileEntityId) { //todo: tiering and gui
        super(metaTileEntityId);
        reinitializeStructurePattern();
    }

    @Override
    protected void updateFormedValid() {
        if (!getWorld().isRemote && this.getNumMaintenanceProblems() < 1 && isWorkingEnabled()) {

            if (getOffsetTimer() % 20 == 4) {
                canSeeSky = canSeeSky();
            }
            if (canSeeSky && !hasEnoughEnergy) {
                if (getOffsetTimer() % 20 == 8) {
                    hasEnoughEnergy = drainEnergy(true);
                }
            }

            if (canSeeSky && hasEnoughEnergy) {
                setActive(true);
                drainEnergy(false);
            } else
                setActive(false);
        } else
            return;
    }

    private boolean canSeeSky() {
        BlockPos result = this.getPos().up(5);
        Vec3i dirVec = this.getFrontFacing().getOpposite().getDirectionVec();
        Vec3i resVec = new Vec3i(dirVec.getX() * 3, 0, dirVec.getZ() * 3);
        result = result.add(resVec);
        return this.getWorld().canSeeSky(result);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "######xxx######", "###############")
                .aisle("###############", "###############", "###############", "###############", "###############", "###############", "###############", "######xxx######", "####xx###xx####", "###############")
                .aisle("###############", "###############", "###############", "###############", "###############", "###############", "#######x#######", "####xxx#xxx####", "###x#######x###", "###############")
                .aisle("######XXX######", "######XXX######", "######XXX######", "###############", "###############", "#######X#######", "#####xxxxx#####", "###xx#####xx###", "##x#########x##", "###############")
                .aisle("#####XXXXX#####", "#####X###X#####", "#####X###X#####", "######XXX######", "######XXX######", "#####XXXXX#####", "####xxxxxxx####", "##xx#######xx##", "#x###########x#", "###############")
                .aisle("####XXXXXXX####", "####X#####X####", "####X#####X####", "#####X###X#####", "#####X###X#####", "####XXxxxXX####", "###xxx###xxx###", "##x#########x##", "#x###########x#", "###############")
                .aisle("###XXXXXXXXX###", "###X###E###X###", "###X#######X###", "####X#####X####", "####X##F##X####", "####XxxxxxX####", "###xx#####xx###", "#xx#########xx#", "x#############x", "###############")
                .aisle("###XXXXXXXXX###", "###X##EcE##X###", "###X###c###X###", "####X##c##X####", "####X#FcF#X####", "###XXxxExxXX###", "##xxx##C##xxx##", "#x#####C#####x#", "x######C######x", "#######s#######")
                .aisle("###XXXXXXXXX###", "###X###E###X###", "###X#######X###", "####X#####X####", "####X##F##X####", "####XxxxxxX####", "###xx#####xx###", "#xx#########xx#", "x#############x", "###############")
                .aisle("####XXXXXXX####", "####X#####X####", "####X#####X####", "#####X###X#####", "#####X###X#####", "####XXxxxXX####", "###xxx###xxx###", "##x#########x##", "#x###########x#", "###############")
                .aisle("#####XXXXX#####", "#####X###X#####", "#####X###X#####", "######XXX######", "######XXX######", "#####XXXXX#####", "####xxxxxxx####", "##xx#######xx##", "#x###########x#", "###############")
                .aisle("######XXX######", "######XSX######", "######XXX######", "###############", "###############", "#######X#######", "#####xxxxx#####", "###xx#####xx###", "##x#########x##", "###############")
                .aisle("###############", "###############", "###############", "###############", "###############", "###############", "#######x#######", "####xxx#xxx####", "###x#######x###", "###############")
                .aisle("###############", "###############", "###############", "###############", "###############", "###############", "###############", "######xxx######", "####xx###xx####", "###############")
                .aisle("###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "######xxx######", "###############")
                .where('S', selfPredicate())
                .where('X', states(getCasingState()).setMinGlobalLimited(145).or(autoAbilities(true, true, true, false)))
                .where('x', states(getSecondaryCasingState()))
                .where('C', frames(BlackTitanium))
                .where('c', states(MetaBlocks.FUSION_CASING.getState(BlockFusionCasing.CasingType.SUPERCONDUCTOR_COIL)))
                .where('F', air())
                .where('E', air())
                .where('s', air())
                .where('#', any())
                .build();
    }

    public TraceabilityPredicate autoAbilities(boolean checkEnergyIn,
                                               boolean checkMaintenance,
                                               boolean checkFluidOut,
                                               boolean checkMuffler) {
        TraceabilityPredicate predicate = super.autoAbilities(checkMaintenance, checkMuffler);

        if (checkEnergyIn) {
            predicate = predicate.or(abilities(MultiblockAbility.INPUT_ENERGY).setMinGlobalLimited(1)
                    .setMaxGlobalLimited(2)
                    .setPreviewCount(1));
        }
        if (checkFluidOut) {
            predicate = predicate.or(abilities(MultiblockAbility.EXPORT_FLUIDS).setPreviewCount(1));
        }
        return predicate;
    }

    /*
    public static Predicate<BlockWorldState> fieldGenPredicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof FieldGenCasing)) {
                return false;
            } else {
                FieldGenCasing motorCasing = (FieldGenCasing) blockState.getBlock();
                FieldGenCasing.CasingType tieredCasingType = motorCasing.getState(blockState);
                FieldGenCasing.CasingType currentCasing = blockWorldState.getMatchContext().getOrPut("FieldGen", tieredCasingType);
                return currentCasing.getName().equals(tieredCasingType.getName());
            }
        };
    }

    public static Predicate<BlockWorldState> emitterPredicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof EmitterCasing)) {
                return false;
            } else {
                EmitterCasing motorCasing = (EmitterCasing) blockState.getBlock();
                EmitterCasing.CasingType tieredCasingType = motorCasing.getState(blockState);
                EmitterCasing.CasingType currentCasing = blockWorldState.getMatchContext().getOrPut("Emitter", tieredCasingType);
                return currentCasing.getName().equals(tieredCasingType.getName());
            }
        };
    }

    public static Predicate<BlockWorldState> sensorPredicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof SensorCasing)) {
                return false;
            } else {
                SensorCasing motorCasing = (SensorCasing) blockState.getBlock();
                SensorCasing.CasingType tieredCasingType = motorCasing.getState(blockState);
                SensorCasing.CasingType currentCasing = blockWorldState.getMatchContext().getOrPut("Sensor", tieredCasingType);
                return currentCasing.getName().equals(tieredCasingType.getName());
            }
        };
    }

     */

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        if (this.isStructureFormed()) {
            if (energyContainer != null && energyContainer.getEnergyCapacity() > 0) {
                maxVoltage = energyContainer.getInputVoltage();
                if (maxVoltage >= getVoltage()) {
                    String voltageName = GTValues.VN[GTUtility.getTierByVoltage(maxVoltage)];
                    textList.add(new TextComponentTranslation("gregtech.multiblock.max_energy_per_tick", maxVoltage, voltageName));
                } else if (!hasEnoughEnergy)
                    textList.add(new TextComponentTranslation("gregtech.multiblock.not_enough_energy").setStyle(new Style().setColor(TextFormatting.RED)));
            }
            if (!canSeeSky)
                textList.add(new TextComponentTranslation("tjfcore.multiblock.cosmic_ray_detector.tooltip.1")
                        .setStyle(new Style().setColor(TextFormatting.RED)));
            if (exportFluidHandler.fill(TJFMaterials.HeavyLeptonMix.getFluid(1), false) < 1)
                textList.add(new TextComponentTranslation("tjfcore.multiblock.cosmic_ray_detector.tooltip.8").setStyle(new Style().setColor(TextFormatting.RED)));
            if (hasEnoughEnergy && canSeeSky) {
                textList.add(new TextComponentTranslation("tjfcore.multiblock.cosmic_ray_detector.tooltip.5", this.amount));
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("tjfcore.multiblock.cosmic_ray_detector.tooltip.2"));
        tooltip.add(I18n.format("tjfcore.multiblock.cosmic_ray_detector.tooltip.3"));
        tooltip.add(I18n.format("tjfcore.multiblock.cosmic_ray_detector.tooltip.4"));
        tooltip.add(I18n.format("tjfcore.multiblock.cosmic_ray_detector.tooltip.6"));
        tooltip.add(I18n.format("tjfcore.multiblock.cosmic_ray_detector.tooltip.7"));
    }

    /*
    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        //EmitterCasing.CasingType emitter = context.getOrDefault("Emitter", EmitterCasing.CasingType.EMITTER_LV);
        //SensorCasing.CasingType sensor = context.getOrDefault("Sensor", SensorCasing.CasingType.SENSOR_LV);
        //FieldGenCasing.CasingType fieldGen = context.getOrDefault("FieldGen", FieldGenCasing.CasingType.FIELD_GENERATOR_LV);
        //int min = Collections.min(Arrays.asList(emitter.getTier(), sensor.getTier(), fieldGen.getTier()));
        //maxVoltage = (long) (Math.pow(4, min) * 8);
        maxVoltage = energyContainer.getInputVoltage();
        this.initializeAbilities();
        amount = getAmount();
    }
     */

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        initializeAbilities();
        amount = getAmount();
    }

    private int getAmount() {
        double amount = Math.min(((double) this.getPos().getY()) / (256 - 5), 1);
        amount = Math.max(amount, 0);
        amount *= 35;
        amount *= getOverclock();
        amount += 15;
        return (int) amount;
    }

    private void initializeAbilities() {
        this.exportFluidHandler = new FluidTankList(true, getAbilities(MultiblockAbility.EXPORT_FLUIDS));
        this.energyContainer = new EnergyContainerList(getAbilities(MultiblockAbility.INPUT_ENERGY));
    }

    private void resetTileAbilities() {
        this.exportFluidHandler = new FluidTankList(true);
        this.energyContainer = new EnergyContainerList(Lists.newArrayList());
    }

    private boolean drainEnergy(boolean simulate) {
        if (maxVoltage >= getVoltage() && energyContainer.getEnergyStored() >= getVoltage() &&
                exportFluidHandler.fill(TJFMaterials.HeavyLeptonMix.getFluid(1), false) > 0) {
            if (!simulate) {
                energyContainer.removeEnergy(getVoltage() * getOverclock());
                exportFluidHandler.fill(TJFMaterials.HeavyLeptonMix.getFluid(this.amount), true);
            }
            return true;
        }
        return false;
    }

    public long getOverclock() {
        int tierDifference = GTUtility.getTierByVoltage(energyContainer.getInputVoltage()) - GTValues.UHV;
        return (long) Math.floor(Math.pow(4, tierDifference));
    }

    private long getVoltage() {
        return GTValues.V[GTValues.UHV];
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.maxVoltage = 0;
        this.resetTileAbilities();
    }

    private IBlockState getCasingState() {
        return METAL_CASING_2.getState(MetalCasing2.CasingType.QUANTUM);
    }

    private IBlockState getSecondaryCasingState() {
        return METAL_CASING_2.getState(MetalCasing2.CasingType.TRITANIUM);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return QUANTUM_CASING;
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        getFrontOverlay().renderOrientedState(renderState, translation, pipeline, getFrontFacing(), this.isActive(), this.isWorkingEnabled);
    }

    @SideOnly(Side.CLIENT)
    @Override
    protected @NotNull ICubeRenderer getFrontOverlay() {
        return ClientHandler.FUSION_REACTOR_OVERLAY;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityCosmicRayDetector(metaTileEntityId);
    }

    @Override
    public boolean isWorkingEnabled() {
        return isWorkingEnabled;
    }

    @Override
    public void setWorkingEnabled(boolean isWorkingAllowed) {
        this.isWorkingEnabled = isWorkingAllowed;
        markDirty();
        World world = getWorld();
        if (world != null && !world.isRemote) {
            writeCustomData(GregtechDataCodes.WORKING_ENABLED, buf -> buf.writeBoolean(isWorkingEnabled));
        }
    }

    @Override
    public boolean isActive() {
        return super.isActive() && this.isWorkingEnabled;
    }

    protected void setActive(boolean active) {
        if (this.isActive != active) {
            this.isActive = active;
            markDirty();
            World world = getWorld();
            if (world != null && !world.isRemote) {
                writeCustomData(GregtechDataCodes.WORKABLE_ACTIVE, buf -> buf.writeBoolean(active));
            }
        }
    }

    @Override
    public SoundEvent getSound() {
        return GTSoundEvents.ARC;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing side) {
        if (capability == GregtechTileCapabilities.CAPABILITY_CONTROLLABLE) {
            return GregtechTileCapabilities.CAPABILITY_CONTROLLABLE.cast(this);
        }
        return super.getCapability(capability, side);
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        if (dataId == GregtechDataCodes.WORKABLE_ACTIVE) {
            this.isActive = buf.readBoolean();
            scheduleRenderUpdate();
        } else if (dataId == GregtechDataCodes.WORKING_ENABLED) {
            this.isWorkingEnabled = buf.readBoolean();
            scheduleRenderUpdate();
        }
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeBoolean(isActive);
        buf.writeBoolean(this.isWorkingEnabled);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.isActive = buf.readBoolean();
        this.isWorkingEnabled = buf.readBoolean();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setBoolean("isActive", this.isActive);
        data.setBoolean("isWorkingEnabled", this.isWorkingEnabled);
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.isActive = data.getBoolean("isActive");
        this.isWorkingEnabled = data.getBoolean("isWorkingEnabled");
    }
}
