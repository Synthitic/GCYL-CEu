package com.fulltrix.gcyl.machines.multi.multiblockpart;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import com.fulltrix.gcyl.api.util.VirtualEnergyRegistry;
import com.fulltrix.gcyl.client.ClientHandler;
import com.fulltrix.gcyl.machines.GCYLTileEntities;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.util.TextFormattingUtil;
import gregtech.client.renderer.texture.cube.SimpleOverlayRenderer;
import gregtech.client.utils.PipelineUtil;
import gregtech.client.utils.TooltipHelper;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityEnergyHatch;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

//TODO: get it to render its active texture in jei, and have it have its inactive texture when placed, then have its active texture when activated
public class MetaTileEntityWirelessEnergyHatch extends MetaTileEntityEnergyHatch {

    private UUID playerUUID = null;

    private VirtualEnergyRegistry.VirtualEnergyContainer energyContainerWireless;

    private boolean initialize = true;

    public MetaTileEntityWirelessEnergyHatch(ResourceLocation metaTileEntityId, int tier, int amperage, boolean isExportHatch) {
        super(metaTileEntityId, tier, amperage, isExportHatch);
        this.initialize = true;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityWirelessEnergyHatch(metaTileEntityId, getTier(), amperage, isExportHatch);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, boolean advanced) {
        tooltip.add(TextFormatting.LIGHT_PURPLE + I18n.format("gcyl.wireless_hatch.tooltip1"));
        tooltip.add(TooltipHelper.BLINKING_RED + I18n.format("gcyl.wireless_hatch.tooltip2"));
        super.addInformation(stack, world, tooltip, advanced);
    }

    @Override
    public void update() {
        super.update();

        if(!this.initialize && !this.getWorld().isRemote) {
            if (this.isExportHatch) {
                long energy = this.energyContainer.getEnergyStored();
                this.energyContainer.removeEnergy(energy);
                this.energyContainerWireless.addEnergy(energy);
            } else {
                if(this.energyContainer.getEnergyStored() != this.energyContainer.getEnergyCapacity()) {
                    //long energy = this.energyContainer.getInputVoltage() * this.energyContainer.getInputAmperage() > this.energyContainerWireless.getEnergyStored() ? this.energyContainerWireless.getEnergyStored() : this.energyContainer.getInputVoltage() * this.energyContainer.getInputAmperage();
                    long energy = this.energyContainer.getEnergyCapacity() - this.energyContainer.getEnergyStored() > this.energyContainerWireless.getEnergyStored() ? this.energyContainerWireless.getEnergyStored() : this.energyContainer.getEnergyCapacity() - this.energyContainer.getEnergyStored();
                    this.energyContainerWireless.remove(energy);
                    this.energyContainer.addEnergy(energy);
                }
            }
        }
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
    public void getSubItems(CreativeTabs creativeTab, NonNullList<ItemStack> subItems) {

        for (MetaTileEntityWirelessEnergyHatch hatch : GCYLTileEntities.WIRELESS_ENERGY_HATCH_INPUT) {
            if (hatch != null) subItems.add(hatch.getStackForm());
        }
        for (MetaTileEntityWirelessEnergyHatch hatch : GCYLTileEntities.WIRELESS_ENERGY_HATCH_INPUT_4A) {
            if (hatch != null) subItems.add(hatch.getStackForm());
        }
        for (MetaTileEntityWirelessEnergyHatch hatch : GCYLTileEntities.WIRELESS_ENERGY_HATCH_INPUT_16A) {
            if (hatch != null) subItems.add(hatch.getStackForm());
        }
        for (MetaTileEntityWirelessEnergyHatch hatch : GCYLTileEntities.WIRELESS_ENERGY_HATCH_INPUT_64A) {
            if (hatch != null) subItems.add(hatch.getStackForm());
        }
        for (MetaTileEntityWirelessEnergyHatch hatch : GCYLTileEntities.WIRELESS_ENERGY_HATCH_OUTPUT) {
            if (hatch != null) subItems.add(hatch.getStackForm());
        }

    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        if (shouldRenderOverlay()) {
            getOverlay().renderSided(getFrontFacing(), renderState, translation,
                    PipelineUtil.color(pipeline, GTValues.VC[getTier()]));
        }
    }

    @NotNull
    private SimpleOverlayRenderer getOverlay() {
        return switch (this.amperage) {
            case 4 -> ClientHandler.WIRELESS_ENERGY_HATCH_4A;
            case 16 -> ClientHandler.WIRELESS_ENERGY_HATCH_16A;
            default -> ClientHandler.WIRELESS_ENERGY_HATCH;
        };
    }

    @Override
    public void writeInitialSyncData(PacketBuffer packetBuffer) {
        super.writeInitialSyncData(packetBuffer);
        packetBuffer.writeBoolean(this.initialize);
        packetBuffer.writeString(this.playerUUID == null ? "null" : this.playerUUID.toString());
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer packetBuffer) {
        super.receiveInitialSyncData(packetBuffer);
        this.initialize = packetBuffer.readBoolean();
        String uuidStr = packetBuffer.readString(36);
        this.playerUUID = uuidStr.equals("null") ? null : UUID.fromString(uuidStr);
        updateEnergyLink();
    }


    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setBoolean("Initialized", initialize);
        data.setString("PlacedUUID", playerUUID == null ? "null" : playerUUID.toString());
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        String uuidStr = data.getString("PlacedUUID");
        this.playerUUID = uuidStr.equals("null") ? null : UUID.fromString(uuidStr);
        this.initialize = data.getBoolean("Initialized");
        updateEnergyLink();
    }

    protected void updateEnergyLink() {
        this.energyContainerWireless = VirtualEnergyRegistry.getContainerCreate(makeEnergyContainerName(), this.playerUUID);
        this.markDirty();
    }

    private String makeEnergyContainerName() {return "EContainer#";}

}
