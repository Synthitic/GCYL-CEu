package com.fulltrix.gcyl.machines.multi.multiblockpart;

import gregtech.api.GTValues;
import gregtech.api.capability.IHPCAComputationProvider;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.resources.TextureArea;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.renderer.texture.cube.SimpleOverlayRenderer;
import gregtech.common.metatileentities.multi.multiblockpart.hpca.MetaTileEntityHPCAComponent;
import net.minecraft.util.ResourceLocation;

//TODO: Make better textures
public class MetaTileEntityHPCAComputationPlus extends MetaTileEntityHPCAComponent implements IHPCAComputationProvider {

    private final int tier;
    private final boolean advanced = true;

    public MetaTileEntityHPCAComputationPlus(ResourceLocation metaTileEntityId, int tier) {
        super(metaTileEntityId);
        this.tier = tier;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityHPCAComputationPlus(metaTileEntityId, tier);
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
        if (isDamaged()) return advanced ? Textures.HPCA_ADVANCED_DAMAGED_OVERLAY : Textures.HPCA_DAMAGED_OVERLAY;
        return advanced ? Textures.HPCA_ADVANCED_COMPUTATION_OVERLAY : Textures.HPCA_COMPUTATION_OVERLAY;
    }

    @Override
    public SimpleOverlayRenderer getFrontActiveOverlay() {
        if (isDamaged()) return advanced ? Textures.HPCA_ADVANCED_DAMAGED_ACTIVE_OVERLAY : Textures.HPCA_DAMAGED_ACTIVE_OVERLAY;
        return advanced ? Textures.HPCA_ADVANCED_COMPUTATION_ACTIVE_OVERLAY : Textures.HPCA_COMPUTATION_ACTIVE_OVERLAY;
    }

    @Override
    public int getUpkeepEUt() {
        return GTValues.VA[tier - 2];
    }

    @Override
    public int getMaxEUt() {
        return GTValues.VA[tier];
    }

    @Override
    public int getCWUPerTick() {
        if (isDamaged()) return 0;
        return (int) Math.pow(2, tier - 3);
    }

    @Override
    public int getCoolingPerTick() {
        return (int) Math.pow(2, tier - 5);
    }

    @Override
    public boolean canBeDamaged() {
        return true;
    }

    @Override
    public TextureArea getComponentIcon() {
        if (isDamaged()) {
            return advanced ? GuiTextures.HPCA_ICON_DAMAGED_ADVANCED_COMPUTATION_COMPONENT :
                    GuiTextures.HPCA_ICON_DAMAGED_COMPUTATION_COMPONENT;
        }
        return advanced ? GuiTextures.HPCA_ICON_ADVANCED_COMPUTATION_COMPONENT :
                GuiTextures.HPCA_ICON_COMPUTATION_COMPONENT;
    }
}