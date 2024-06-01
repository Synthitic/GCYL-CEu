package com.fulltrix.gcyl.machines.multi.miner;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import com.fulltrix.gcyl.GCYLMaterials;
import com.fulltrix.gcyl.item.metal.MetalCasing1;
import com.fulltrix.gcyl.item.metal.MetalCasing2;
import com.google.common.collect.Lists;
import gregtech.api.GTValues;
import gregtech.api.capability.*;
import gregtech.api.capability.impl.EnergyContainerList;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.capability.impl.ItemHandlerList;
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
import gregtech.client.renderer.texture.Textures;
import gregtech.core.sound.GTSoundEvents;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

import static com.fulltrix.gcyl.client.ClientHandler.*;
import static com.fulltrix.gcyl.item.GCYLMetaBlocks.METAL_CASING_1;
import static com.fulltrix.gcyl.item.GCYLMetaBlocks.METAL_CASING_2;
import static com.fulltrix.gcyl.recipes.categories.handlers.VoidMinerHandler.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.util.GTTransferUtils.addItemsToItemHandler;

public class MetaTileEntityVoidMiner extends MultiblockWithDisplayBase implements IControllable { //TODO: OpenComputers implementation
    private static final int CONSUME_START = 100;

    public boolean isWorkingEnabled = false;
    private final int maxTemperature;
    private final int tier;
    private final long energyDrain;
    protected IItemHandlerModifiable outputInventory;
    private IEnergyContainer energyContainer;
    private IMultipleTankHandler importFluidHandler;
    private IMultipleTankHandler exportFluidHandler;
    private boolean isActive = false;
    private boolean overheat = false;
    private boolean usingPyrotheum = true;
    private int temperature = 0;
    private double currentDrillingFluid = CONSUME_START;


    public MetaTileEntityVoidMiner(ResourceLocation metaTileEntityId, int tier, int temp) {
        super(metaTileEntityId);
        this.tier = tier;
        this.energyDrain = GTValues.V[tier];
        this.maxTemperature = temp;
        this.reinitializeStructurePattern();
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        resetTileAbilities();
        if (isActive)
            setActive(false);
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        initializeAbilities();
    }

    private void initializeAbilities() {
        this.importFluidHandler = new FluidTankList(true, getAbilities(MultiblockAbility.IMPORT_FLUIDS));
        this.exportFluidHandler = new FluidTankList(true, getAbilities(MultiblockAbility.EXPORT_FLUIDS));
        this.outputInventory = new ItemHandlerList(getAbilities(MultiblockAbility.EXPORT_ITEMS));
        this.energyContainer = new EnergyContainerList(getAbilities(MultiblockAbility.INPUT_ENERGY));
    }

    /* TODO: fix wtf this is
    @Override
    public int getActualComparatorValue() {
        float f = temperature / (maxTemperature * 1.0f);
        return MathHelper.floor(f * 14.0f) + (temperature > 0 ? 1 : 0);
    }

     */

    private void resetTileAbilities() {
        this.importFluidHandler = new FluidTankList(true);
        this.exportFluidHandler = new FluidTankList(true);
        this.outputInventory = new ItemStackHandler(0);
        this.energyContainer = new EnergyContainerList(Lists.newArrayList());
    }

    public boolean drainEnergy(boolean simulate) {
        long energyToDrain = GTValues.VA[this.tier];
        long resultEnergy = energyContainer.getEnergyStored() - energyToDrain;
        if (resultEnergy >= 0L && resultEnergy <= energyContainer.getEnergyCapacity()) {
            if (!simulate)
                energyContainer.changeEnergy(-energyToDrain);
            return true;
        }
        return false;
    }

    @Override
    protected void updateFormedValid() {
        if (!getWorld().isRemote && this.getNumMaintenanceProblems() < 6 && isWorkingEnabled()) {
            if (overheat || !drainEnergy(false)) {
                if (temperature > 0) {
                    temperature--;
                }
                if (temperature == 0) {
                    overheat = false;
                }
                if (currentDrillingFluid > CONSUME_START) {
                    currentDrillingFluid--;
                }
                if (currentDrillingFluid < CONSUME_START) {
                    currentDrillingFluid = CONSUME_START;
                }

                if (isActive)
                    setActive(false);
                return;
            }

            if (getOffsetTimer() % 20 == 0) {
                FluidStack pyrotheumFluid = GCYLMaterials.Pyrotheum.getFluid((int) currentDrillingFluid);
                FluidStack cryotheumFluid = GCYLMaterials.Cryotheum.getFluid((int) currentDrillingFluid);
                FluidStack drillingMudFluid = GCYLMaterials.DrillingMud.getFluid((int) currentDrillingFluid);
                FluidStack usedDrillingMudFluid = GCYLMaterials.UsedDrillingMud.getFluid((int) currentDrillingFluid);
                FluidStack canDrainPyrotheum = importFluidHandler.drain(pyrotheumFluid, false);
                FluidStack canDrainCryotheum = importFluidHandler.drain(cryotheumFluid, false);
                FluidStack canDrainDrillingMud = importFluidHandler.drain(drillingMudFluid, false);
                int canFillUsedDrillingMud = exportFluidHandler.fill(usedDrillingMudFluid, false);
                boolean hasConsume = false;
                //consume fluid
                if (canDrainDrillingMud != null && canDrainDrillingMud.amount == (int) currentDrillingFluid &&
                        canFillUsedDrillingMud != 0 && canFillUsedDrillingMud == (int) currentDrillingFluid) {
                    importFluidHandler.drain(drillingMudFluid, true);
                    exportFluidHandler.fill(usedDrillingMudFluid, true);
                } else {
                    setActive(false);
                    return;
                }

                if (!isActive)
                    setActive(true);

                calculateMaintenance();

                if (usingPyrotheum && canDrainPyrotheum != null && canDrainPyrotheum.amount == (int) currentDrillingFluid) {
                    importFluidHandler.drain(pyrotheumFluid, true);
                    temperature += (int) (currentDrillingFluid / 100.0);
                    currentDrillingFluid = currentDrillingFluid * 1.02;
                    hasConsume = true;
                } else if (temperature > 0 && canDrainCryotheum != null && canDrainCryotheum.amount == (int) currentDrillingFluid) {
                    importFluidHandler.drain(cryotheumFluid, true);
                    currentDrillingFluid = currentDrillingFluid / 1.02;
                    temperature -= (int) (currentDrillingFluid / 100.0);
                }
                if (temperature < 0) {
                    temperature = 0;
                }
                if (currentDrillingFluid < CONSUME_START) {
                    currentDrillingFluid = CONSUME_START;
                }
                if (temperature > maxTemperature) {
                    overheat = true;
                    currentDrillingFluid = CONSUME_START;
                    return;
                }
                usingPyrotheum = !usingPyrotheum;

                currentDrillingFluid += this.getNumMaintenanceProblems();
                //mine

                int nbOres = temperature / 1000;

                if (nbOres == 0 || !hasConsume) {
                    return;
                }

                List<ItemStack> ores = getOres();
                Collections.shuffle(ores);
                ores.stream().limit(10).peek(itemStack -> itemStack.setCount(getWorld().rand.nextInt(nbOres * nbOres) + 1)).forEach(itemStack -> addItemsToItemHandler(outputInventory, false, Collections.singletonList(itemStack)));


            }
        }
    }

    List<ItemStack> getOres() {
        return switch (tier) {
            case 8 -> ORES;
            case 9 -> ORES_2;
            default -> ORES_3;
        };
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("CCCCCCCCC", "CCCCCCCCC", "C#######C", "C#######C", "C#######C", "CCCCCCCCC", "CFFFFFFFC", "CFFFFFFFC", "C#######C", "C#######C")
                .aisle("C#######C", "C#######C", "#########", "#########", "#########", "C###D###C", "F##DDD##F", "F##DDD##F", "###DDD###", "#########")
                .aisle("C#######C", "C#######C", "#########", "####D####", "###DDD###", "C##DDD##C", "F#DD#DD#F", "F#D###D#F", "##D###D##", "#########")
                .aisle("C###D###C", "C###D###C", "###DDD###", "###D#D###", "##DD#DD##", "C#D###D#C", "FDD###DDF", "FD#####DF", "#D#####D#", "#########")
                .aisle("C##D#D##C", "C##D#D##C", "###D#D###", "##D###D##", "##D###D##", "CDD###DDC", "FD#####DF", "FD#####DF", "#D#####D#", "#########")
                .aisle("C###D###C", "C###D###C", "###DDD###", "###D#D###", "##DD#DD##", "C#D###D#C", "FDD###DDF", "FD#####DF", "#D#####D#", "#########")
                .aisle("C#######C", "C#######C", "#########", "####D####", "###DDD###", "C##DDD##C", "F#DD#DD#F", "F#D###D#F", "##D###D##", "#########")
                .aisle("C#######C", "C#######C", "#########", "#########", "#########", "C###D###C", "F##DDD##F", "F##DDD##F", "###DDD###", "#########")
                .aisle("CCCCCCCCC", "CCCCSCCCC", "C#######C", "C#######C", "C#######C", "CCCCCCCCC", "CFFFFFFFC", "CFFFFFFFC", "C#######C", "C#######C")
                .where('S', selfPredicate())
                .where('C', states(getCasingState()).setMinGlobalLimited(100).or(autoAbilities(true, true, false, true, true, true, false)))
                .where('D', states(getSecondaryCasingState()))
                .where('F', getFramePredicate())
                .where('#', any())
                .build();
    }

    public TraceabilityPredicate autoAbilities(boolean checkEnergyIn,
                                               boolean checkMaintenance,
                                               boolean checkItemIn,
                                               boolean checkItemOut,
                                               boolean checkFluidIn,
                                               boolean checkFluidOut,
                                               boolean checkMuffler) {
        TraceabilityPredicate predicate = super.autoAbilities(checkMaintenance, checkMuffler);

        if (checkEnergyIn) {
            predicate = predicate.or(abilities(MultiblockAbility.INPUT_ENERGY).setMinGlobalLimited(1)
                    .setMaxGlobalLimited(2)
                    .setPreviewCount(1));
        }

        if (checkItemIn) {
            predicate = predicate.or(abilities(MultiblockAbility.IMPORT_ITEMS).setPreviewCount(1));
        }
        if (checkItemOut) {
            predicate = predicate.or(abilities(MultiblockAbility.EXPORT_ITEMS).setPreviewCount(1));
        }
        if (checkFluidIn) {
            predicate = predicate.or(abilities(MultiblockAbility.IMPORT_FLUIDS).setPreviewCount(1));
        }
        if (checkFluidOut) {
            predicate = predicate.or(abilities(MultiblockAbility.EXPORT_FLUIDS).setPreviewCount(1));
        }
        return predicate;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("gcyl.multiblock.void_miner.description.1"));
        tooltip.add(I18n.format("gcyl.multiblock.void_miner.description.2"));
        tooltip.add(I18n.format("gcyl.multiblock.void_miner.description.3"));
        tooltip.add(I18n.format("gcyl.multiblock.void_miner.description.4"));
        tooltip.add(I18n.format("gcyl.multiblock.void_miner.description.5"));
        tooltip.add(I18n.format("gcyl.multiblock.void_miner.description.6"));
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        if (this.isStructureFormed() && !this.hasMaintenanceProblems()) {
            if (energyContainer != null && energyContainer.getEnergyCapacity() > 0) {
                long maxVoltage = energyContainer.getInputVoltage();
                String voltageName = GTValues.VN[GTUtility.getTierByVoltage(maxVoltage)];
                textList.add(new TextComponentTranslation("gregtech.multiblock.max_energy_per_tick", maxVoltage, voltageName));
            }
            textList.add(new TextComponentTranslation("gregtech.multiblock.universal.energy_used", energyDrain));
            textList.add(new TextComponentTranslation("gregtech.multiblock.universal.vom.temperature", temperature, maxTemperature));
            textList.add(new TextComponentTranslation("gregtech.multiblock.universal.drilling_fluid_amount", (int) currentDrillingFluid));
            if (overheat) {
                textList.add(new TextComponentTranslation("gregtech.multiblock.universal.overheat").setStyle(new Style().setColor(TextFormatting.RED)));
            }
        }

        super.addDisplayText(textList);
    }

    public IBlockState getCasingState() {
        return switch (tier) {
            case 8 -> METAL_CASING_1.getState(MetalCasing1.CasingType.HASTELLOY_N);
            case 9 -> METAL_CASING_2.getState(MetalCasing2.CasingType.TRITANIUM);
            default -> METAL_CASING_2.getState(MetalCasing2.CasingType.QUANTUM);
        };
    }

    public IBlockState getSecondaryCasingState() {
        return switch (tier) {
            case 8 -> METAL_CASING_2.getState(MetalCasing2.CasingType.STABALLOY);
            case 9 -> METAL_CASING_1.getState(MetalCasing1.CasingType.INCOLOY_813);
            default -> METAL_CASING_1.getState(MetalCasing1.CasingType.HASTELLOY_X78);
        };
    }

    @NotNull
    public TraceabilityPredicate getFramePredicate() {
        return switch (tier) {
            case 8 -> frames(TungstenSteel);
            case 9 -> frames(Seaborgium);
            default -> frames(Bohrium);
        };
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return switch (tier) {
            case 9 -> TRITANIUM_CASING;
            case 10 -> QUANTUM_CASING;
            default -> HASTELLOY_N_CASING;
        };
    }

    @SideOnly(Side.CLIENT)
    @Override
    protected @NotNull ICubeRenderer getFrontOverlay() {
        return Textures.LARGE_MINER_OVERLAY_ADVANCED_2;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityVoidMiner(metaTileEntityId, this.tier, this.maxTemperature);
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
        data.setTag("temperature", new NBTTagInt(temperature));
        data.setTag("currentDrillingFluid", new NBTTagDouble(currentDrillingFluid));
        data.setTag("overheat", new NBTTagInt(overheat ? 1 : 0));
        data.setBoolean("isActive", this.isActive);
        data.setBoolean("isWorkingEnabled", this.isWorkingEnabled);
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        temperature = data.getInteger("temperature");
        currentDrillingFluid = data.getDouble("currentDrillingFluid");
        overheat = data.getInteger("overheat") != 0;
        this.isActive = data.getBoolean("isActive");
        this.isWorkingEnabled = data.getBoolean("isWorkingEnabled");
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        getFrontOverlay().renderOrientedState(renderState, translation, pipeline, getFrontFacing(), this.isActive(), this.isWorkingEnabled);
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
    public SoundEvent getSound() {
        return GTSoundEvents.MINER;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing side) {
        if (capability == GregtechTileCapabilities.CAPABILITY_CONTROLLABLE) {
            return GregtechTileCapabilities.CAPABILITY_CONTROLLABLE.cast(this);
        }
        return super.getCapability(capability, side);
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

    public int getMaxTemperature() {
        return maxTemperature;
    }

    public boolean isOverheat() {
        return overheat;
    }

    public int getTemperature() {
        return temperature;
    }

    public double getCurrentDrillingFluid() {
        return currentDrillingFluid;
    }
}
