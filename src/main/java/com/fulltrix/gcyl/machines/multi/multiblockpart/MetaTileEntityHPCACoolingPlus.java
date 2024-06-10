package com.fulltrix.gcyl.machines.multi.multiblockpart;

import gregtech.api.GTValues;
import gregtech.api.capability.IHPCACoolantProvider;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.resources.TextureArea;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.renderer.texture.cube.SimpleOverlayRenderer;
import gregtech.common.metatileentities.multi.multiblockpart.hpca.MetaTileEntityHPCAComponent;
import net.minecraft.util.ResourceLocation;

public class MetaTileEntityHPCACoolingPlus extends MetaTileEntityHPCAComponent implements IHPCACoolantProvider {

    private final int tier;
    private final boolean advanced = true;

    public MetaTileEntityHPCACoolingPlus(ResourceLocation metaTileEntityId, int tier) {
        super(metaTileEntityId);
        this.tier = tier;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityHPCACoolingPlus(metaTileEntityId, tier);
    }

    @Override
    public boolean isAdvanced() {
        return true;
    }

    public int getTier() {
        return tier;
    }

    @Override
    public SimpleOverlayRenderer getFrontOverlay() {
        return advanced ? Textures.HPCA_ACTIVE_COOLER_OVERLAY : Textures.HPCA_HEAT_SINK_OVERLAY;
    }

    @Override
    public SimpleOverlayRenderer getFrontActiveOverlay() {
        return advanced ? Textures.HPCA_ACTIVE_COOLER_ACTIVE_OVERLAY : getFrontOverlay();
    }

    @Override
    public int getUpkeepEUt() {
        return GTValues.VA[tier - 2];
    }

    @Override
    public boolean canBeDamaged() {
        return false;
    }

    @Override
    public TextureArea getComponentIcon() {
        return GuiTextures.HPCA_ICON_ACTIVE_COOLER_COMPONENT;
    }

    @Override
    public int getCoolingAmount() {
        return (int) Math.pow(2, tier - 6);
    }

    @Override
    public boolean isActiveCooler() {
        return advanced;
    }

    @Override
    public int getMaxCoolantPerTick() {
        return (int) Math.pow(2, tier - 4);
    }
}
