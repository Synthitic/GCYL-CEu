package com.fulltrix.gcyl.machines.multi.multiblockpart;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import com.fulltrix.gcyl.api.util.VirtualEnergyRegistry;
import com.fulltrix.gcyl.api.util.VirtualResearchRegistry;
import com.fulltrix.gcyl.client.ClientHandler;
import gregtech.api.capability.IDataAccessHatch;
import gregtech.api.gui.ModularUI;
import gregtech.api.metatileentity.IDataInfoProvider;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.machines.IResearchRecipeMap;
import gregtech.api.util.AssemblyLineManager;
import gregtech.client.utils.TooltipHelper;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityDataBank;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityDataAccessHatch;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import org.apache.http.util.TextUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class MetaTileEntityWirelessDataHatch extends MetaTileEntityDataAccessHatch implements IMultiblockAbilityPart<IDataAccessHatch>, IDataAccessHatch,
        IDataInfoProvider {

    private UUID playerUUID = null;

    private final Set<Recipe> recipes;

    private VirtualResearchRegistry.VirtualResearchContainer researchContainerWireless;

    private boolean initialize;


    public MetaTileEntityWirelessDataHatch(ResourceLocation metaTileEntityId, int tier) {
        super(metaTileEntityId, tier, false);
        this.recipes = new ObjectOpenHashSet<>();
        this.initialize = true;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityWirelessDataHatch(metaTileEntityId, getTier());
    }

    @Override
    public boolean isRecipeAvailable(@NotNull Recipe recipe, @NotNull Collection<IDataAccessHatch> seen) {
        seen.add(this);
        return recipes.contains(recipe);
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        return null;
    }

    @Override
    public void update() {
        super.update();

        if(!this.initialize && !this.getWorld().isRemote && this.getOffsetTimer() % 1200 == 0) {
            rebuildData();
        }
    }


    private void rebuildData() {
        if (getWorld() == null || getWorld().isRemote || this.initialize) return;
        recipes.clear();
        for (String researchId : this.researchContainerWireless.getResearchIds()) {
            if (researchId != null) {
                Collection<Recipe> collection = ((IResearchRecipeMap) RecipeMaps.ASSEMBLY_LINE_RECIPES)
                        .getDataStickEntry(researchId);
                if (collection != null) {
                    recipes.addAll(collection);
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
                    this.researchContainerWireless = VirtualResearchRegistry.getContainerCreate(makeResearchContainerName(), this.playerUUID);
                    this.initialize = false;
                    playerIn.sendStatusMessage(new TextComponentTranslation("gcyl.wireless_initialized_private"), false);
                }
                else {
                    this.playerUUID = new UUID(0,0);
                    this.researchContainerWireless = VirtualResearchRegistry.getContainerCreate(makeResearchContainerName(), this.playerUUID);
                    this.initialize = false;
                    playerIn.sendStatusMessage(new TextComponentTranslation("gcyl.wireless_initialized_public"), false);
                }
            }
        }
        return true;
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
        updateResearchLink();
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
        updateResearchLink();
    }

    protected void updateResearchLink() {
        this.researchContainerWireless = VirtualResearchRegistry.getContainerCreate(makeResearchContainerName(), this.playerUUID);
        this.markDirty();
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        ClientHandler.WIRELESS_DATA_HATCH.renderSided(getFrontFacing(), renderState, translation, pipeline);
    }


    private String makeResearchContainerName() {return "RContainer#";}

    @Override
    public void registerAbilities(List<IDataAccessHatch> abilityList) {
        abilityList.add(this);
    }

    @Override
    public void addToMultiBlock(MultiblockControllerBase controllerBase) {
        super.addToMultiBlock(controllerBase);
    }


    @Override
    public void addInformation(ItemStack stack, @Nullable World world, @NotNull List<String> tooltip,
                               boolean advanced) {
        tooltip.add(TextFormatting.LIGHT_PURPLE + I18n.format("gcyl.machine.wireless_data_hatch.tooltip.1"));
        tooltip.add(TextFormatting.LIGHT_PURPLE + I18n.format("gcyl.machine.wireless_data_hatch.tooltip.2"));
        tooltip.add(TooltipHelper.BLINKING_RED + I18n.format("gcyl.machine.wireless_data_hatch.tooltip.3"));
        tooltip.add(TextFormatting.LIGHT_PURPLE + I18n.format("gcyl.machine.wireless_data_hatch.tooltip.4"));
        if (canPartShare()) {
            tooltip.add(I18n.format("gregtech.universal.enabled"));
        } else {
            tooltip.add(I18n.format("gregtech.universal.disabled"));
        }
    }
}
