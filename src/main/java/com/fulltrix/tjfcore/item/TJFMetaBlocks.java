package com.fulltrix.tjfcore.item;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static gregtech.client.model.modelfactories.MaterialBlockModelLoader.registerItemModel;

public class TJFMetaBlocks {

    public static TJFHeatingCoil HEATING_COIL;
    public static void init() {
        HEATING_COIL = new TJFHeatingCoil();
        HEATING_COIL.setRegistryName("tjf_heating_coil");
    }
    @SideOnly(Side.CLIENT)
    public static void registerItemModels() {
        HEATING_COIL.onModelRegister();
    }
}
