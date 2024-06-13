package com.fulltrix.gcyl.machines.multi.advance;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import com.fulltrix.gcyl.api.util.VirtualEnergyRegistry;
import com.fulltrix.gcyl.client.ClientHandler;
import com.fulltrix.gcyl.item.GCYLMetaBlocks;
import com.fulltrix.gcyl.item.GCYLMultiblockCasing2;
import com.fulltrix.gcyl.machines.GCYLTileEntities;
import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.capability.*;
import gregtech.api.capability.impl.EnergyContainerList;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.resources.TextureArea;
import gregtech.api.gui.widgets.*;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.*;
import gregtech.api.pattern.*;
import gregtech.api.util.BlockInfo;
import gregtech.api.util.TextComponentUtil;
import gregtech.api.util.TextFormattingUtil;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.utils.TooltipHelper;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.VisibleForTesting;

import java.math.BigInteger;
import java.time.Duration;
import java.util.*;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import static gregtech.api.util.RelativeDirection.*;

public class MetaTileEntityWirelessPowerSubstation extends MultiblockWithDisplayBase
        implements IControllable, IProgressBarMultiblock {

    private UUID playerUUID = null;

    private VirtualEnergyRegistry.VirtualEnergyContainer energyContainerWireless;

    private boolean initialize;

    // Structure Constants
    public static final int MAX_BATTERY_LAYERS = 18;
    private static final int MIN_CASINGS = 35;

    // Passive Drain Constants
    // 1% capacity per 24 hours
    public static final long PASSIVE_DRAIN_DIVISOR = 20 * 60 * 60 * 24 * 100;
    // no more than 100kEU/t per storage block
    public static final long PASSIVE_DRAIN_MAX_PER_STORAGE = 100_000L;

    // NBT Keys
    private static final String NBT_ENERGY_BANK = "EnergyBank";

    // Match Context Headers
    private static final String PMC_BATTERY_HEADER = "PSSBattery_";

    private static final BigInteger BIG_INTEGER_MAX_LONG = BigInteger.valueOf(Long.MAX_VALUE);

    private MetaTileEntityWirelessPowerSubstation.PowerStationEnergyBank energyBank;
    private EnergyContainerList inputHatches;
    private EnergyContainerList outputHatches;
    private long passiveDrain;
    private boolean isActive, isWorkingEnabled = true;

    // Stats tracked for UI display
    private long netInLastSec;
    private long averageInLastSec;
    private long netOutLastSec;
    private long averageOutLastSec;

    public MetaTileEntityWirelessPowerSubstation(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
        this.initialize = true;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityWirelessPowerSubstation(metaTileEntityId);
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        List<IEnergyContainer> inputs = new ArrayList<>();
        inputs.addAll(getAbilities(MultiblockAbility.INPUT_ENERGY));
        inputs.addAll(getAbilities(MultiblockAbility.SUBSTATION_INPUT_ENERGY));
        inputs.addAll(getAbilities(MultiblockAbility.INPUT_LASER));
        this.inputHatches = new EnergyContainerList(inputs);

        List<IEnergyContainer> outputs = new ArrayList<>();
        outputs.addAll(getAbilities(MultiblockAbility.OUTPUT_ENERGY));
        outputs.addAll(getAbilities(MultiblockAbility.SUBSTATION_OUTPUT_ENERGY));
        outputs.addAll(getAbilities(MultiblockAbility.OUTPUT_LASER));
        this.outputHatches = new EnergyContainerList(outputs);

        List<IBatteryData> parts = new ArrayList<>();
        for (Map.Entry<String, Object> battery : context.entrySet()) {
            if (battery.getKey().startsWith(PMC_BATTERY_HEADER) &&
                    battery.getValue() instanceof MetaTileEntityWirelessPowerSubstation.BatteryMatchWrapper wrapper) {
                for (int i = 0; i < wrapper.amount; i++) {
                    parts.add(wrapper.partType);
                }
            }
        }
        if (parts.isEmpty()) {
            // only empty batteries found in the structure
            invalidateStructure();
            return;
        }
        if (this.energyBank == null) {
            this.energyBank = new MetaTileEntityWirelessPowerSubstation.PowerStationEnergyBank(parts);
        } else {
            this.energyBank = energyBank.rebuild(parts);
        }
        this.passiveDrain = this.energyBank.getPassiveDrainPerTick();
    }

    @Override
    public void invalidateStructure() {
        // don't null out energyBank since it holds the stored energy, which
        // we need to hold on to across rebuilds to not void all energy if a
        // multiblock part or block other than the controller is broken.
        inputHatches = null;
        outputHatches = null;
        passiveDrain = 0;
        netInLastSec = 0;
        averageInLastSec = 0;
        netOutLastSec = 0;
        averageOutLastSec = 0;
        super.invalidateStructure();
    }

    @Override
    protected void updateFormedValid() {
        if (!getWorld().isRemote) {
            if (getOffsetTimer() % 20 == 0) {
                // active here is just used for rendering
                setActive(energyBank.hasEnergy());
                averageInLastSec = netInLastSec / 20;
                averageOutLastSec = netOutLastSec / 20;
                netInLastSec = 0;
                netOutLastSec = 0;
            }

            if (isWorkingEnabled()) {
                // Bank from Energy Input Hatches
                long energyBanked = energyBank.fill(inputHatches.getEnergyStored());
                inputHatches.changeEnergy(-energyBanked);
                netInLastSec += energyBanked;

                // Passive drain
                long energyPassiveDrained = energyBank.drain(getPassiveDrain());
                netOutLastSec += energyPassiveDrained;

                // Debank to Dynamo Hatches
                long energyDebanked = energyBank
                        .drain(outputHatches.getEnergyCapacity() - outputHatches.getEnergyStored());
                outputHatches.changeEnergy(energyDebanked);
                netOutLastSec += energyDebanked;

                // Send to wireless network
                if (!initialize) {
                    if(this.getOffsetTimer() % 1200 == 0) {
                        long tempEnergyBank;

                        try {
                            tempEnergyBank = energyBank.getStored().longValueExact();
                        }
                        catch (ArithmeticException e) {
                            tempEnergyBank = Long.MAX_VALUE;
                        }

                        if (this.energyContainerWireless.getEnergyStored() < tempEnergyBank) {
                            this.energyContainerWireless.addEnergy(energyBank.drain((tempEnergyBank - this.energyContainerWireless.getEnergyStored()) / 2));
                        }
                        else {
                            this.energyContainerWireless.remove((this.energyContainerWireless.getEnergyStored() - tempEnergyBank) / 2);
                            energyBank.fill((this.energyContainerWireless.getEnergyStored() - tempEnergyBank) / 2);
                        }
                    }
                }
            }
        }
    }

    public long getPassiveDrain() {
        if (ConfigHolder.machines.enableMaintenance) {
            int multiplier = 1 + getNumMaintenanceProblems();
            double modifier = getMaintenanceDurationMultiplier();
            return (long) (passiveDrain * multiplier * modifier);
        }
        return passiveDrain;
    }

    @Override
    public boolean isActive() {
        return super.isActive() && this.isActive;
    }

    public void setActive(boolean active) {
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
    public boolean isWorkingEnabled() {
        return this.isWorkingEnabled;
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
    protected boolean shouldShowVoidingModeButton() {
        return false;
    }

    @NotNull
    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RIGHT, FRONT, UP)
                .aisle("XXSXX", "XXXXX", "XXXXX", "XXXXX", "XXXXX")
                .aisle("XXXXX", "XCCCX", "XCCCX", "XCCCX", "XXXXX")
                .aisle("GGGGG", "GBBBG", "GBBBG", "GBBBG", "GGGGG").setRepeatable(1, MAX_BATTERY_LAYERS)
                .aisle("GGGGG", "GGGGG", "GGGGG", "GGGGG", "GGGGG")
                .where('S', selfPredicate())
                .where('C', states(getCasingState()))
                .where('X', states(getCasingState()).setMinGlobalLimited(MIN_CASINGS)
                        .or(maintenancePredicate())
                        .or(abilities(MultiblockAbility.INPUT_ENERGY, MultiblockAbility.SUBSTATION_INPUT_ENERGY,
                                MultiblockAbility.INPUT_LASER).setMinGlobalLimited(1))
                        .or(abilities(MultiblockAbility.OUTPUT_ENERGY, MultiblockAbility.SUBSTATION_OUTPUT_ENERGY,
                                MultiblockAbility.OUTPUT_LASER).setMinGlobalLimited(1)))
                .where('G', states(getGlassState()))
                .where('B', BATTERY_PREDICATE.get())
                .build();
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        List<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
        MultiblockShapeInfo.Builder builder = MultiblockShapeInfo.builder()
                .aisle("CCCCC", "CCCCC", "GGGGG", "GGGGG", "GGGGG")
                .aisle("CCCCC", "CCCCC", "GBBBG", "GBBBG", "GGGGG")
                .aisle("CCCCC", "CCCCC", "GBBBG", "GBBBG", "GGGGG")
                .aisle("CCCCC", "CCCCC", "GBBBG", "GBBBG", "GGGGG")
                .aisle("ICSCO", "NCMCT", "GGGGG", "GGGGG", "GGGGG")
                .where('S', GCYLTileEntities.WIRELESS_PSS, EnumFacing.SOUTH)
                .where('C', getCasingState())
                .where('G', getGlassState())
                .where('I', MetaTileEntities.ENERGY_INPUT_HATCH[GTValues.HV], EnumFacing.SOUTH)
                .where('N', MetaTileEntities.SUBSTATION_ENERGY_INPUT_HATCH[0], EnumFacing.SOUTH)
                .where('O', MetaTileEntities.ENERGY_OUTPUT_HATCH[GTValues.HV], EnumFacing.SOUTH)
                .where('T', MetaTileEntities.SUBSTATION_ENERGY_OUTPUT_HATCH[0], EnumFacing.SOUTH)
                .where('M',
                        () -> ConfigHolder.machines.enableMaintenance ? MetaTileEntities.MAINTENANCE_HATCH :
                                MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.PALLADIUM_SUBSTATION),
                        EnumFacing.SOUTH);

        GregTechAPI.PSS_BATTERIES.entrySet().stream()
                // filter out empty batteries in example structures, though they are still
                // allowed in the predicate (so you can see them on right-click)
                .filter(entry -> entry.getValue().getCapacity() > 0)
                .sorted(Comparator.comparingInt(entry -> entry.getValue().getTier()))
                .forEach(entry -> shapeInfo.add(builder.where('B', entry.getKey()).build()));

        return shapeInfo;
    }

    protected IBlockState getCasingState() {
        return GCYLMetaBlocks.MULTIBLOCK_CASING2.getState(GCYLMultiblockCasing2.CasingType.SEABORGIUM_SUBSTATION);
    }

    protected IBlockState getGlassState() {
        return MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.LAMINATED_GLASS);
    }

    protected static final Supplier<TraceabilityPredicate> BATTERY_PREDICATE = () -> new TraceabilityPredicate(
            blockWorldState -> {
                IBlockState state = blockWorldState.getBlockState();
                if (GregTechAPI.PSS_BATTERIES.containsKey(state)) {
                    IBatteryData battery = GregTechAPI.PSS_BATTERIES.get(state);
                    // Allow unfilled batteries in the structure, but do not add them to match context.
                    // This lets you use empty batteries as "filler slots" for convenience if desired.
                    if (battery.getTier() != -1 && battery.getCapacity() > 0) {
                        String key = PMC_BATTERY_HEADER + battery.getBatteryName();
                        MetaTileEntityWirelessPowerSubstation.BatteryMatchWrapper wrapper = blockWorldState.getMatchContext().get(key);
                        if (wrapper == null) wrapper = new MetaTileEntityWirelessPowerSubstation.BatteryMatchWrapper(battery);
                        blockWorldState.getMatchContext().set(key, wrapper.increment());
                    }
                    return true;
                }
                return false;
            }, () -> GregTechAPI.PSS_BATTERIES.entrySet().stream()
            .sorted(Comparator.comparingInt(entry -> entry.getValue().getTier()))
            .map(entry -> new BlockInfo(entry.getKey(), null))
            .toArray(BlockInfo[]::new))
            .addTooltips("gregtech.multiblock.pattern.error.batteries");

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return ClientHandler.SEABORGIUM_SUBSTATION_CASING;
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.POWER_SUBSTATION_OVERLAY;
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        getFrontOverlay().renderOrientedState(renderState, translation, pipeline, getFrontFacing(), this.isActive(),
                this.isWorkingEnabled());
    }
    @Override
    protected ModularUI.Builder createUITemplate(EntityPlayer entityPlayer) {
        ModularUI.Builder builder;
        label38: {
            builder = ModularUI.builder(GuiTextures.BACKGROUND, 198, 250);
            if (this instanceof IProgressBarMultiblock) {
                IProgressBarMultiblock progressMulti = (IProgressBarMultiblock)this;
                if (progressMulti.showProgressBar()) {
                    builder.image(4, 4, 190, 151, GuiTextures.DISPLAY);
                    ProgressWidget progressBar;
                    if (progressMulti.getNumProgressBars() == 3) {
                        progressBar = (new ProgressWidget(() -> {
                            return progressMulti.getFillPercentage(0);
                        }, 4, 157, 62, 7, progressMulti.getProgressBarTexture(0), ProgressWidget.MoveType.HORIZONTAL)).setHoverTextConsumer((list) -> {
                            progressMulti.addBarHoverText(list, 0);
                        });
                        builder.widget(progressBar);
                        progressBar = (new ProgressWidget(() -> {
                            return progressMulti.getFillPercentage(1);
                        }, 68, 157, 62, 7, progressMulti.getProgressBarTexture(1), ProgressWidget.MoveType.HORIZONTAL)).setHoverTextConsumer((list) -> {
                            progressMulti.addBarHoverText(list, 1);
                        });
                        builder.widget(progressBar);
                        progressBar = (new ProgressWidget(() -> {
                            return progressMulti.getFillPercentage(2);
                        }, 132, 157, 62, 7, progressMulti.getProgressBarTexture(2), ProgressWidget.MoveType.HORIZONTAL)).setHoverTextConsumer((list) -> {
                            progressMulti.addBarHoverText(list, 2);
                        });
                        builder.widget(progressBar);
                    } else if (progressMulti.getNumProgressBars() == 2) {
                        progressBar = (new ProgressWidget(() -> {
                            return progressMulti.getFillPercentage(0);
                        }, 4, 157, 94, 7, progressMulti.getProgressBarTexture(0), ProgressWidget.MoveType.HORIZONTAL)).setHoverTextConsumer((list) -> {
                            progressMulti.addBarHoverText(list, 0);
                        });
                        builder.widget(progressBar);
                        progressBar = (new ProgressWidget(() -> {
                            return progressMulti.getFillPercentage(1);
                        }, 100, 157, 94, 7, progressMulti.getProgressBarTexture(1), ProgressWidget.MoveType.HORIZONTAL)).setHoverTextConsumer((list) -> {
                            progressMulti.addBarHoverText(list, 1);
                        });
                        builder.widget(progressBar);
                    } else {
                        progressBar = (new ProgressWidget(() -> {
                            return progressMulti.getFillPercentage(0);
                        }, 4, 157, 190, 7, progressMulti.getProgressBarTexture(0), ProgressWidget.MoveType.HORIZONTAL)).setHoverTextConsumer((list) -> {
                            progressMulti.addBarHoverText(list, 0);
                        });
                        builder.widget(progressBar);
                    }

                    builder.widget((new IndicatorImageWidget(174, 135, 17, 17, this.getLogo())).setWarningStatus(this.getWarningLogo(), this::addWarningText).setErrorStatus(this.getErrorLogo(), this::addErrorText));
                    break label38;
                }
            }

            builder.image(4, 4, 190, 159, GuiTextures.DISPLAY);
            builder.widget((new IndicatorImageWidget(174, 101, 17, 17, this.getLogo())).setWarningStatus(this.getWarningLogo(), this::addWarningText).setErrorStatus(this.getErrorLogo(), this::addErrorText));
        }

        builder.label(9, 9, this.getMetaFullName(), 16777215);
        builder.widget((new AdvancedTextWidget(9, 20, this::addDisplayText, 16777215)).setMaxWidthLimit(181).setClickHandler(this::handleDisplayClick));
        IControllable controllable = (IControllable)this.getCapability(GregtechTileCapabilities.CAPABILITY_CONTROLLABLE, (EnumFacing)null);
        TextureArea var10007;
        BooleanSupplier var10008;
        if (controllable != null) {
            var10007 = GuiTextures.BUTTON_POWER;
            Objects.requireNonNull(controllable);
            var10008 = controllable::isWorkingEnabled;
            Objects.requireNonNull(controllable);
            builder.widget(new ImageCycleButtonWidget(173, 225, 18, 18, var10007, var10008, controllable::setWorkingEnabled));
            builder.widget(new ImageWidget(173, 243, 18, 6, GuiTextures.BUTTON_POWER_DETAIL));
        }

        if (this.shouldShowVoidingModeButton()) {
            builder.widget((new ImageCycleButtonWidget(173, 203, 18, 18, GuiTextures.BUTTON_VOID_MULTIBLOCK, 4, this::getVoidingMode, this::setVoidingMode)).setTooltipHoverString(MultiblockWithDisplayBase::getVoidingModeTooltip));
        } else {
            builder.widget((new ImageWidget(173, 203, 18, 18, GuiTextures.BUTTON_VOID_NONE)).setTooltip("gregtech.gui.multiblock_voiding_not_supported"));
        }

        label30: {
            if (this instanceof IDistinctBusController) {
                IDistinctBusController distinct = (IDistinctBusController)this;
                if (distinct.canBeDistinct()) {
                    var10007 = GuiTextures.BUTTON_DISTINCT_BUSES;
                    Objects.requireNonNull(distinct);
                    var10008 = distinct::isDistinct;
                    Objects.requireNonNull(distinct);
                    builder.widget((new ImageCycleButtonWidget(173, 185, 18, 18, var10007, var10008, distinct::setDistinct)).setTooltipHoverString((i) -> {
                        return "gregtech.multiblock.universal.distinct_" + (i == 0 ? "disabled" : "enabled");
                    }));
                    break label30;
                }
            }

            builder.widget((new ImageWidget(173, 185, 18, 18, GuiTextures.BUTTON_NO_DISTINCT_BUSES)).setTooltip("gregtech.multiblock.universal.distinct_not_supported"));
        }

        builder.widget(this.getFlexButton(173, 167, 18, 18));
        builder.bindPlayerInventory(entityPlayer.inventory, 167);
        return builder;
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        MultiblockDisplayText.builder(textList, isStructureFormed())
                .setWorkingStatus(true, isActive() && isWorkingEnabled()) // transform into two-state system for display
                .setWorkingStatusKeys(
                        "gregtech.multiblock.idling",
                        "gregtech.multiblock.idling",
                        "gregtech.machine.active_transformer.routing")
                .addCustom(tl -> {
                    if (isStructureFormed() && energyBank != null) {
                        BigInteger energyStored = energyBank.getStored();
                        BigInteger energyCapacity = energyBank.getCapacity();

                        // Stored EU line
                        ITextComponent storedFormatted = TextComponentUtil.stringWithColor(
                                TextFormatting.GOLD,
                                TextFormattingUtil.formatNumbers(energyStored) + " EU");
                        tl.add(TextComponentUtil.translationWithColor(
                                TextFormatting.GRAY,
                                "gregtech.multiblock.power_substation.stored",
                                storedFormatted));

                        // EU Capacity line
                        ITextComponent capacityFormatted = TextComponentUtil.stringWithColor(
                                TextFormatting.GOLD,
                                TextFormattingUtil.formatNumbers(energyCapacity) + " EU");
                        tl.add(TextComponentUtil.translationWithColor(
                                TextFormatting.GRAY,
                                "gregtech.multiblock.power_substation.capacity",
                                capacityFormatted));

                        // Passive Drain line
                        ITextComponent passiveDrain = TextComponentUtil.stringWithColor(
                                TextFormatting.DARK_RED,
                                TextFormattingUtil.formatNumbers(getPassiveDrain()) + " EU/t");
                        tl.add(TextComponentUtil.translationWithColor(
                                TextFormatting.GRAY,
                                "gregtech.multiblock.power_substation.passive_drain",
                                passiveDrain));

                        // Average EU IN line
                        ITextComponent avgValue = TextComponentUtil.stringWithColor(
                                TextFormatting.GREEN,
                                TextFormattingUtil.formatNumbers(averageInLastSec) + " EU/t");
                        ITextComponent base = TextComponentUtil.translationWithColor(
                                TextFormatting.GRAY,
                                "gregtech.multiblock.power_substation.average_in",
                                avgValue);
                        ITextComponent hover = TextComponentUtil.translationWithColor(
                                TextFormatting.GRAY,
                                "gregtech.multiblock.power_substation.average_in_hover");
                        tl.add(TextComponentUtil.setHover(base, hover));

                        // Average EU OUT line
                        avgValue = TextComponentUtil.stringWithColor(
                                TextFormatting.RED,
                                TextFormattingUtil.formatNumbers(averageOutLastSec) + " EU/t");
                        base = TextComponentUtil.translationWithColor(
                                TextFormatting.GRAY,
                                "gregtech.multiblock.power_substation.average_out",
                                avgValue);
                        hover = TextComponentUtil.translationWithColor(
                                TextFormatting.GRAY,
                                "gregtech.multiblock.power_substation.average_out_hover");
                        tl.add(TextComponentUtil.setHover(base, hover));

                        // Time to fill/drain line
                        if (averageInLastSec > averageOutLastSec) {
                            ITextComponent timeToFill = getTimeToFillDrainText(energyCapacity.subtract(energyStored)
                                    .divide(BigInteger.valueOf((averageInLastSec - averageOutLastSec) * 20)));
                            TextComponentUtil.setColor(timeToFill, TextFormatting.GREEN);
                            tl.add(TextComponentUtil.translationWithColor(
                                    TextFormatting.GRAY,
                                    "gregtech.multiblock.power_substation.time_to_fill",
                                    timeToFill));
                        } else if (averageInLastSec < averageOutLastSec) {
                            ITextComponent timeToDrain = getTimeToFillDrainText(
                                    energyStored.divide(BigInteger.valueOf(
                                            (averageOutLastSec - averageInLastSec) * 20)));
                            TextComponentUtil.setColor(timeToDrain, TextFormatting.RED);
                            tl.add(TextComponentUtil.translationWithColor(
                                    TextFormatting.GRAY,
                                    "gregtech.multiblock.power_substation.time_to_drain",
                                    timeToDrain));
                        }
                    }

                    if(!initialize && !this.getWorld().isRemote && isStructureFormed()) {
                        ITextComponent wirelessFormatted = TextComponentUtil.stringWithColor(TextFormatting.LIGHT_PURPLE, TextFormattingUtil.formatNumbers(this.energyContainerWireless.getEnergyStored()) + " EU");
                        try {
                            tl.add(TextComponentUtil.translationWithColor(TextFormatting.LIGHT_PURPLE, "gcyl.multiblock.wireless_pss.private", Objects.requireNonNull(this.getWorld().getPlayerEntityByUUID(this.playerUUID)).getName()));
                            tl.add(TextComponentUtil.translationWithColor(TextFormatting.LIGHT_PURPLE, "gcyl.multiblock.wireless_pss.wireless_eu", wirelessFormatted));
                        }
                        catch (NullPointerException e) {
                            tl.add(TextComponentUtil.translationWithColor(TextFormatting.LIGHT_PURPLE, "gcyl.multiblock.wireless_pss.public"));
                            tl.add(TextComponentUtil.translationWithColor(TextFormatting.LIGHT_PURPLE, "gcyl.multiblock.wireless_pss.wireless_eu", wirelessFormatted));
                        }
                    }
                    else
                        tl.add(new TextComponentTranslation("gcyl.multiblock.wireless_pss.not_initialized"));
                })
                .addWorkingStatusLine();



    }

    @Override
    protected void addWarningText(List<ITextComponent> textList) {
        super.addWarningText(textList);
        if (isStructureFormed()) {
            if (averageInLastSec < averageOutLastSec) { // decreasing
                BigInteger timeToDrainSeconds = energyBank.getStored()
                        .divide(BigInteger.valueOf((averageOutLastSec - averageInLastSec) * 20));
                if (timeToDrainSeconds.compareTo(BigInteger.valueOf(60 * 60)) < 0) { // less than 1 hour left
                    textList.add(TextComponentUtil.translationWithColor(
                            TextFormatting.YELLOW,
                            "gregtech.multiblock.power_substation.under_one_hour_left"));
                }
            }
        }
    }

    private static ITextComponent getTimeToFillDrainText(BigInteger timeToFillSeconds) {
        if (timeToFillSeconds.compareTo(BIG_INTEGER_MAX_LONG) > 0) {
            // too large to represent in a java Duration
            timeToFillSeconds = BIG_INTEGER_MAX_LONG;
        }

        Duration duration = Duration.ofSeconds(timeToFillSeconds.longValue());
        String key;
        long fillTime;
        if (duration.getSeconds() <= 180) {
            fillTime = duration.getSeconds();
            key = "gregtech.multiblock.power_substation.time_seconds";
        } else if (duration.toMinutes() <= 180) {
            fillTime = duration.toMinutes();
            key = "gregtech.multiblock.power_substation.time_minutes";
        } else if (duration.toHours() <= 72) {
            fillTime = duration.toHours();
            key = "gregtech.multiblock.power_substation.time_hours";
        } else if (duration.toDays() <= 730) { // 2 years
            fillTime = duration.toDays();
            key = "gregtech.multiblock.power_substation.time_days";
        } else if (duration.toDays() / 365 < 1_000_000) {
            fillTime = duration.toDays() / 365;
            key = "gregtech.multiblock.power_substation.time_years";
        } else {
            return new TextComponentTranslation("gregtech.multiblock.power_substation.time_forever");
        }

        return new TextComponentTranslation(key, TextFormattingUtil.formatNumbers(fillTime));
    }

    @Override
    public boolean onScrewdriverClick(EntityPlayer playerIn, EnumHand hand, EnumFacing facing,
                                      CuboidRayTraceResult hitResult) {
        if(!getWorld().isRemote) {
            if (this.initialize) {
                if(playerIn.isSneaking()) {
                    this.playerUUID = playerIn.getUniqueID();
                    this.energyContainerWireless = VirtualEnergyRegistry.getContainerCreate(makeEnergyContainerName(), this.playerUUID);
                    this.initialize = false;
                    playerIn.sendStatusMessage(new TextComponentTranslation("gcyl.wireless_initialized_private"), false);
                }
                else {
                    this.playerUUID = new UUID(0,0);
                    this.energyContainerWireless = VirtualEnergyRegistry.getContainerCreate(makeEnergyContainerName(), this.playerUUID);
                    this.initialize = false;
                    playerIn.sendStatusMessage(new TextComponentTranslation("gcyl.wireless_initialized_public"), false);
                }
            }
        }
        return true;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setBoolean("isActive", isActive);
        data.setBoolean("isWorkingEnabled", isWorkingEnabled);
        if (energyBank != null) {
            data.setTag(NBT_ENERGY_BANK, energyBank.writeToNBT(new NBTTagCompound()));
        }
        data.setBoolean("Initialized", initialize);
        data.setString("PlacedUUID", playerUUID == null ? "null" : playerUUID.toString());
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        isActive = data.getBoolean("isActive");
        isWorkingEnabled = data.getBoolean("isWorkingEnabled");
        if (data.hasKey(NBT_ENERGY_BANK)) {
            energyBank = new MetaTileEntityWirelessPowerSubstation.PowerStationEnergyBank(data.getCompoundTag(NBT_ENERGY_BANK));
        }
        String uuidStr = data.getString("PlacedUUID");
        this.playerUUID = uuidStr.equals("null") ? null : UUID.fromString(uuidStr);
        this.initialize = data.getBoolean("Initialized");
        updateEnergyLink();
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeBoolean(isActive);
        buf.writeBoolean(isWorkingEnabled);
        buf.writeBoolean(this.initialize);
        buf.writeString(this.playerUUID == null ? "null" : this.playerUUID.toString());
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        isActive = buf.readBoolean();
        isWorkingEnabled = buf.readBoolean();
        this.initialize = buf.readBoolean();
        String uuidStr = buf.readString(36);
        this.playerUUID = uuidStr.equals("null") ? null : UUID.fromString(uuidStr);
        updateEnergyLink();
    }

    @Override
    public void receiveCustomData(int dataId, @NotNull PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        if (dataId == GregtechDataCodes.WORKABLE_ACTIVE) {
            isActive = buf.readBoolean();
            scheduleRenderUpdate();
        } else if (dataId == GregtechDataCodes.WORKING_ENABLED) {
            isWorkingEnabled = buf.readBoolean();
            scheduleRenderUpdate();
        }
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing side) {
        if (capability == GregtechTileCapabilities.CAPABILITY_CONTROLLABLE) {
            return GregtechTileCapabilities.CAPABILITY_CONTROLLABLE.cast(this);
        }
        return super.getCapability(capability, side);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, @NotNull List<String> tooltip,
                               boolean advanced) {
        tooltip.add(TextFormatting.LIGHT_PURPLE + I18n.format("gcyl.machine.wireless_power_substation.tooltip1"));
        tooltip.add(TextFormatting.LIGHT_PURPLE + I18n.format("gcyl.machine.wireless_power_substation.tooltip2"));
        tooltip.add(TooltipHelper.BLINKING_RED + I18n.format("gcyl.machine.wireless_power_substation.tooltip4"));
        tooltip.add(TextFormatting.LIGHT_PURPLE + I18n.format("gcyl.machine.wireless_power_substation.tooltip3"));
        tooltip.add(I18n.format("gregtech.machine.power_substation.tooltip1"));
        tooltip.add(I18n.format("gregtech.machine.power_substation.tooltip2"));
        tooltip.add(I18n.format("gregtech.machine.power_substation.tooltip3", MAX_BATTERY_LAYERS));
        tooltip.add(I18n.format("gregtech.machine.power_substation.tooltip4"));
        tooltip.add(I18n.format("gregtech.machine.power_substation.tooltip5", PASSIVE_DRAIN_MAX_PER_STORAGE));
        tooltip.add(I18n.format("gregtech.machine.power_substation.tooltip6") + TooltipHelper.RAINBOW_SLOW +
                I18n.format("gregtech.machine.power_substation.tooltip6.5"));
    }

    protected void updateEnergyLink() {
        this.energyContainerWireless = VirtualEnergyRegistry.getContainerCreate(makeEnergyContainerName(), this.playerUUID);
        this.markDirty();
    }

    private String makeEnergyContainerName() {return "EContainer#";}

    public String getStored() {
        if (energyBank == null) {
            return "0";
        }
        return TextFormattingUtil.formatNumbers(energyBank.getStored());
    }

    public long getStoredLong() {
        if (energyBank == null) {
            return 0;
        }
        return energyBank.getStored().longValue() & ~(1L << 63);
    }

    public long getCapacityLong() {
        if (energyBank == null) {
            return 0;
        }
        return energyBank.getCapacity().longValue() & ~(1L << 63);
    }

    public String getCapacity() {
        if (energyBank == null) {
            return "0";
        }
        return TextFormattingUtil.formatNumbers(energyBank.getCapacity());
    }

    public long getAverageInLastSec() {
        return averageInLastSec;
    }

    public long getAverageOutLastSec() {
        return averageOutLastSec;
    }

    @Override
    public double getFillPercentage(int index) {
        if (energyBank == null) return 0;
        return energyBank.getStored().doubleValue() / energyBank.getCapacity().doubleValue();
    }

    @Override
    public void addBarHoverText(List<ITextComponent> hoverList, int index) {
        String stored = energyBank != null ? TextFormattingUtil.formatNumbers(energyBank.getStored()) : "0";
        String capacity = energyBank != null ? TextFormattingUtil.formatNumbers(energyBank.getCapacity()) : "0";

        ITextComponent energyInfo = TextComponentUtil.stringWithColor(
                TextFormatting.YELLOW,
                stored + " / " + capacity + " EU");
        hoverList.add(TextComponentUtil.translationWithColor(
                TextFormatting.GRAY,
                "gregtech.multiblock.energy_stored",
                energyInfo));
    }

    public static class PowerStationEnergyBank {

        private static final String NBT_SIZE = "Size";
        private static final String NBT_STORED = "Stored";
        private static final String NBT_MAX = "Max";

        private final long[] storage;
        private final long[] maximums;
        private final BigInteger capacity;
        private int index;

        public PowerStationEnergyBank(List<IBatteryData> batteries) {
            storage = new long[batteries.size()];
            maximums = new long[batteries.size()];
            for (int i = 0; i < batteries.size(); i++) {
                maximums[i] = batteries.get(i).getCapacity();
            }
            capacity = summarize(maximums);
        }

        public PowerStationEnergyBank(NBTTagCompound storageTag) {
            int size = storageTag.getInteger(NBT_SIZE);
            storage = new long[size];
            maximums = new long[size];
            for (int i = 0; i < size; i++) {
                NBTTagCompound subtag = storageTag.getCompoundTag(String.valueOf(i));
                if (subtag.hasKey(NBT_STORED)) {
                    storage[i] = subtag.getLong(NBT_STORED);
                }
                maximums[i] = subtag.getLong(NBT_MAX);
            }
            capacity = summarize(maximums);
        }

        private NBTTagCompound writeToNBT(NBTTagCompound compound) {
            compound.setInteger(NBT_SIZE, storage.length);
            for (int i = 0; i < storage.length; i++) {
                NBTTagCompound subtag = new NBTTagCompound();
                if (storage[i] > 0) {
                    subtag.setLong(NBT_STORED, storage[i]);
                }
                subtag.setLong(NBT_MAX, maximums[i]);
                compound.setTag(String.valueOf(i), subtag);
            }
            return compound;
        }

        /**
         * Rebuild the power storage with a new list of batteries.
         * Will use existing stored power and try to map it onto new batteries.
         * If there was more power before the rebuild operation, it will be lost.
         */
        public PowerStationEnergyBank rebuild(@NotNull List<IBatteryData> batteries) {
            if (batteries.isEmpty()) {
                throw new IllegalArgumentException("Cannot rebuild Power Substation power bank with no batteries!");
            }
            PowerStationEnergyBank newStorage = new MetaTileEntityWirelessPowerSubstation.PowerStationEnergyBank(batteries);
            for (long stored : storage) {
                newStorage.fill(stored);
            }
            return newStorage;
        }

        /** @return Amount filled into storage */
        public long fill(long amount) {
            if (amount < 0) throw new IllegalArgumentException("Amount cannot be negative!");

            // ensure index
            if (index != storage.length - 1 && storage[index] == maximums[index]) {
                index++;
            }

            long maxFill = Math.min(maximums[index] - storage[index], amount);

            // storage is completely full
            if (maxFill == 0 && index == storage.length - 1) {
                return 0;
            }

            // fill this "battery" as much as possible
            storage[index] += maxFill;
            amount -= maxFill;

            // try to fill other "batteries" if necessary
            if (amount > 0 && index != storage.length - 1) {
                return maxFill + fill(amount);
            }

            // other fill not necessary, either because the storage is now completely full,
            // or we were able to consume all the energy in this "battery"
            return maxFill;
        }

        /** @return Amount drained from storage */
        public long drain(long amount) {
            if (amount < 0) throw new IllegalArgumentException("Amount cannot be negative!");

            // ensure index
            if (index != 0 && storage[index] == 0) {
                index--;
            }

            long maxDrain = Math.min(storage[index], amount);

            // storage is completely empty
            if (maxDrain == 0 && index == 0) {
                return 0;
            }

            // drain this "battery" as much as possible
            storage[index] -= maxDrain;
            amount -= maxDrain;

            // try to drain other "batteries" if necessary
            if (amount > 0 && index != 0) {
                index--;
                return maxDrain + drain(amount);
            }

            // other drain not necessary, either because the storage is now completely empty,
            // or we were able to drain all the energy from this "battery"
            return maxDrain;
        }

        public BigInteger getCapacity() {
            return capacity;
        }

        public BigInteger getStored() {
            return summarize(storage);
        }

        public boolean hasEnergy() {
            for (long l : storage) {
                if (l > 0) return true;
            }
            return false;
        }

        private static BigInteger summarize(long[] values) {
            BigInteger retVal = BigInteger.ZERO;
            long currentSum = 0;
            for (long value : values) {
                if (currentSum != 0 && value > Long.MAX_VALUE - currentSum) {
                    // will overflow if added
                    retVal = retVal.add(BigInteger.valueOf(currentSum));
                    currentSum = 0;
                }
                currentSum += value;
            }
            if (currentSum != 0) {
                retVal = retVal.add(BigInteger.valueOf(currentSum));
            }
            return retVal;
        }

        @VisibleForTesting
        public long getPassiveDrainPerTick() {
            long[] maximumsExcl = new long[maximums.length];
            int index = 0;
            int numExcl = 0;
            for (long maximum : maximums) {
                if (maximum / PASSIVE_DRAIN_DIVISOR >= PASSIVE_DRAIN_MAX_PER_STORAGE) {
                    numExcl++;
                } else {
                    maximumsExcl[index++] = maximum;
                }
            }
            maximumsExcl = Arrays.copyOf(maximumsExcl, index);
            BigInteger capacityExcl = summarize(maximumsExcl);

            return capacityExcl.divide(BigInteger.valueOf(PASSIVE_DRAIN_DIVISOR))
                    .add(BigInteger.valueOf(PASSIVE_DRAIN_MAX_PER_STORAGE * numExcl))
                    .longValue();
        }
    }

    private static class BatteryMatchWrapper {

        private final IBatteryData partType;
        private int amount;

        public BatteryMatchWrapper(IBatteryData partType) {
            this.partType = partType;
        }

        public MetaTileEntityWirelessPowerSubstation.BatteryMatchWrapper increment() {
            amount++;
            return this;
        }
    }
}