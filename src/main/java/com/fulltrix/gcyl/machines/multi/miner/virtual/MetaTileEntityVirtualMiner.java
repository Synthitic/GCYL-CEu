package com.fulltrix.gcyl.machines.multi.miner.virtual;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import com.fulltrix.gcyl.api.multi.VirtualMinerLogic;
import com.fulltrix.gcyl.api.multi.abilities.GCYLAbilities;
import com.fulltrix.gcyl.api.worldgen.VirtualOreVeinHandler;
import com.google.common.collect.Lists;
import gregtech.api.GTValues;
import gregtech.api.capability.GregtechTileCapabilities;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.IWorkable;
import gregtech.api.capability.impl.EnergyContainerList;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.capability.impl.ItemHandlerList;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.resources.TextureArea;
import gregtech.api.metatileentity.ITieredMetaTileEntity;
import gregtech.api.metatileentity.multiblock.IProgressBarMultiblock;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.util.GTUtility;
import gregtech.api.util.TextComponentUtil;
import gregtech.api.worldgen.bedrockFluids.BedrockFluidVeinHandler;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static gregtech.api.items.toolitem.ToolHelper.DURABILITY_KEY;
import static gregtech.api.items.toolitem.ToolHelper.MAX_DURABILITY_KEY;


//TODO finish implementing
public abstract class MetaTileEntityVirtualMiner extends MultiblockWithDisplayBase implements ITieredMetaTileEntity, IWorkable, IProgressBarMultiblock {

    protected final int tier;
    protected ObjectList<Integer> layers = new ObjectArrayList<>();
    protected final VirtualMinerLogic minerLogic;
    protected IMultipleTankHandler inputFluidInventory;
    protected IItemHandlerModifiable inputInventory;
    protected IItemHandlerModifiable outputInventory;
    protected IItemHandlerModifiable drillHolder;

    protected IEnergyContainer energyContainer;
    protected int fuelBurnTimeLeft;

    public MetaTileEntityVirtualMiner(ResourceLocation metaTileEntityId, int tier) {
        super(metaTileEntityId);
        this.tier = tier;
        this.minerLogic = new VirtualMinerLogic(this);
    }

    protected void initializeAbilities() {
        this.inputInventory = new ItemHandlerList(getAbilities(MultiblockAbility.IMPORT_ITEMS));
        this.outputInventory = new ItemHandlerList(getAbilities(MultiblockAbility.EXPORT_ITEMS));
        this.drillHolder = new ItemHandlerList(getAbilities(GCYLAbilities.DRILL_HOLDER));

        if(this.tier > 0) {
            this.inputFluidInventory = new FluidTankList(true, getAbilities(MultiblockAbility.IMPORT_FLUIDS));
            this.energyContainer = new EnergyContainerList(getAbilities(MultiblockAbility.INPUT_ENERGY));
        }
    }

    @Override
    protected void updateFormedValid() {
        this.minerLogic.performDrilling();
        if (!getWorld().isRemote && this.minerLogic.wasActiveAndNeedsUpdate()) {
            this.minerLogic.setWasActiveAndNeedsUpdate(false);
            this.minerLogic.setActive(false);
        }
    }

    @Override
    public boolean hasMaintenanceMechanics() {
        return false;
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        initializeAbilities();
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        resetTileAbilities();
        if (this.minerLogic.isActive())
            this.minerLogic.setActive(false);
    }

    private void resetTileAbilities() {

        this.inputInventory = new ItemStackHandler(0);
        this.outputInventory = new ItemStackHandler(0);
        this.drillHolder = new ItemStackHandler(0);

        if(this.tier > 0) {
            this.inputFluidInventory = new FluidTankList(true);
            this.energyContainer = new EnergyContainerList(Lists.newArrayList());
        }
    }

    @Override
    public IItemHandlerModifiable getExportItems() {
        return this.outputInventory;
    }

    public ObjectList<Integer> getLayers() {
        return this.layers;
    }

    public boolean useDrill(boolean simulate) {
        ItemStack drill = drillHolder.getStackInSlot(0);

        if(drill.isEmpty())
            return false;

        if(OreDictUnifier.getPrefix(drill) != OrePrefix.toolHeadDrill) {
            return false;
        }
        else {
            if(simulate) return true;

            Material material = OreDictUnifier.getMaterial(drill).material;

            if(drill.getTagCompound() == null){

                NBTTagCompound tagCompound = new NBTTagCompound();

                int durability = material.getProperty(PropertyKey.TOOL).getToolDurability();

                tagCompound.setInteger(MAX_DURABILITY_KEY, durability);
                tagCompound.setInteger(DURABILITY_KEY, durability);

                drill.setTagCompound(tagCompound);

                drill.setStackDisplayName(drill.getDisplayName() + " (" + durability + "/" + durability + ")");

                return true;
            } else {
                NBTTagCompound tagCompound = drill.getTagCompound();

                if(tagCompound.getInteger(DURABILITY_KEY) == 0) {
                    drill.shrink(1);
                    return false;
                }
                else {
                    tagCompound.setInteger(DURABILITY_KEY, tagCompound.getInteger(DURABILITY_KEY) - 1);

                    drill.setStackDisplayName(drill.getDisplayName() + " (" + tagCompound.getInteger(DURABILITY_KEY) + "/" + tagCompound.getInteger(MAX_DURABILITY_KEY) + ")");

                    drill.setTagCompound(tagCompound);

                    return true;
                }
            }
        }
    }

    public boolean drainEnergy(boolean simulate) {
        long energyToDrain = GTValues.VA[getEnergyTier()];
        long resultEnergy = energyContainer.getEnergyStored() - energyToDrain;
        if (resultEnergy >= 0L && resultEnergy <= energyContainer.getEnergyCapacity()) {
            if (!simulate)
                energyContainer.changeEnergy(-energyToDrain);
            return true;
        }
        return false;
    }

    public boolean consumeFuel(boolean simulate) {
        if(inputInventory.getSlots() == 0) return false;

        ItemStack fuelInSlot = inputInventory.extractItem(0, 1, true);
        if (fuelInSlot.isEmpty()) return false;
        // Prevent consuming buckets with burn time
        if (FluidUtil.getFluidHandler(fuelInSlot) != null) {
            return false;
        }
        int burnTime = TileEntityFurnace.getItemBurnTime(fuelInSlot);
        if (burnTime <= 0) return false;

        if(!simulate) {
            inputInventory.extractItem(0, 1, false);
            setFuelMaxBurnTime(burnTime);
        }

        return true;
    }

    public void setFuelMaxBurnTime(int fuelMaxBurnTime) {
        this.fuelBurnTimeLeft = fuelMaxBurnTime;
        if (!getWorld().isRemote) {
            markDirty();
        }
    }

    public int getFuelBurnTimeLeft() {
        return this.fuelBurnTimeLeft;
    }

    public void removeBurnTime() {
        this.fuelBurnTimeLeft--;
    }

    public long getEnergyInputPerSecond() {
        return energyContainer.getInputPerSec();
    }

    public int getDepletionChance() {
        return 1;
    }

    public int getRigMultiplier() {
        return 1;
    }

    @Override
    public int getTier() {
        return this.tier;
    }

    public int getEnergyTier() {
        if (energyContainer == null) return this.tier;
        return Math.min(this.tier + 1,
                Math.max(this.tier, GTUtility.getFloorTierByVoltage(energyContainer.getInputVoltage())));
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        this.getFrontOverlay().renderOrientedState(renderState, translation, pipeline, getFrontFacing(),
                this.minerLogic.isActive(), this.minerLogic.isWorkingEnabled());
    }

    @Override
    public boolean isActive() {
        return minerLogic.isActive() && isWorkingEnabled();
    }

    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.LARGE_MINER_OVERLAY_BASIC;
    }

    @Override
    public TextureArea getProgressBarTexture(int index) {
        return GuiTextures.PROGRESS_BAR_FLUID_RIG_DEPLETION;
    }

    @Override
    public void addBarHoverText(List<ITextComponent> hoverList, int index) {
        int numOperationsLeft = BedrockFluidVeinHandler.getOperationsRemaining(getWorld(), minerLogic.getChunkX(),
                minerLogic.getChunkZ());
        int maxOperations = BedrockFluidVeinHandler.MAXIMUM_VEIN_OPERATIONS;
        int percentage = (int) Math.round(1.0 * numOperationsLeft / maxOperations * 100);
        TextFormatting color = percentage > 40 ? TextFormatting.GREEN :
                percentage > 10 ? TextFormatting.YELLOW : TextFormatting.RED;

        if (numOperationsLeft == 0) {
            hoverList.add(TextComponentUtil.translationWithColor(TextFormatting.RED,
                    "gregtech.multiblock.fluid_rig.vein_depleted"));
        } else {
            ITextComponent veinInfo = TextComponentUtil.stringWithColor(color, percentage + "%");
            hoverList.add(TextComponentUtil.translationWithColor(
                    TextFormatting.GRAY,
                    "gregtech.multiblock.fluid_rig.vein_depletion",
                    veinInfo));
        }
    }

    @Override
    public boolean isWorkingEnabled() {
        return this.minerLogic.isWorkingEnabled();
    }

    @Override
    public void setWorkingEnabled(boolean isActivationAllowed) {
        this.minerLogic.setWorkingEnabled(isActivationAllowed);
    }

    @Override
    public boolean allowsExtendedFacing() {
        return false;
    }

    @Override
    protected boolean shouldShowVoidingModeButton() {
        return false;
    }

    @Override
    public double getFillPercentage(int index) {
        int numOperationsLeft = VirtualOreVeinHandler.getOperationsRemaining(getWorld(), minerLogic.getChunkX(),
                minerLogic.getChunkZ()).get(index);
        int maxOperations = VirtualOreVeinHandler.getYield(getWorld(), minerLogic.getChunkX(),
                minerLogic.getChunkZ()).get(index);
        return 1.0 * numOperationsLeft / maxOperations;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing side) {
        if (capability == GregtechTileCapabilities.CAPABILITY_WORKABLE)
            return GregtechTileCapabilities.CAPABILITY_WORKABLE.cast(this);
        if (capability == GregtechTileCapabilities.CAPABILITY_CONTROLLABLE)
            return GregtechTileCapabilities.CAPABILITY_CONTROLLABLE.cast(this);
        return super.getCapability(capability, side);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        return this.minerLogic.writeToNBT(data);
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.minerLogic.readFromNBT(data);
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        this.minerLogic.writeInitialSyncData(buf);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.minerLogic.receiveInitialSyncData(buf);
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        this.minerLogic.receiveCustomData(dataId, buf);
    }

    @Override
    public int getProgress() {
        return minerLogic.getProgressTime();
    }

    @Override
    public int getMaxProgress() {
        return VirtualMinerLogic.MAX_PROGRESS;
    }
}
