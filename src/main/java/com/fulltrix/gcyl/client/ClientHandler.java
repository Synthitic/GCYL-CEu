package com.fulltrix.gcyl.client;

import com.fulltrix.gcyl.GCYLCore;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.client.renderer.texture.cube.SimpleOverlayRenderer;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(modid = GCYLCore.MODID, value = Side.CLIENT)
public class ClientHandler {

    public static OrientedOverlayRenderer QUBIT_COMPUTER_OVERLAY;
    public static OrientedOverlayRenderer ORGANIC_REPLICATOR_OVERLAY;
    public static OrientedOverlayRenderer FUSION_REACTOR_OVERLAY;
    public static OrientedOverlayRenderer FREEZER_OVERLAY;
    public static SimpleOverlayRenderer FUSION_TEXTURE;
    public static SimpleOverlayRenderer BIO_REACTOR;
    public static SimpleOverlayRenderer STABALLOY_CASING;
    public static SimpleOverlayRenderer QUANTUM_CASING;
    public static SimpleOverlayRenderer TRITANIUM_CASING;
    public static SimpleOverlayRenderer HASTELLOY_X78_CASING;
    public static SimpleOverlayRenderer HASTELLOY_N_CASING;
    public static SimpleOverlayRenderer INCOLOY_813_CASING;
    public static SimpleOverlayRenderer NAQUADRIA_CASING;
    public static SimpleOverlayRenderer IRIDIUM_CASING;
    public static SimpleOverlayRenderer HYPER_CASING;
    public static SimpleOverlayRenderer HYPER_CASING_2;
    public static SimpleOverlayRenderer ENRICHED_NAQUADAH_ALLOY_CASING;
    public static SimpleOverlayRenderer ADVANCED_FUSION;
    public static SimpleOverlayRenderer ADVANCED_FUSION_ACTIVE;
    public static SimpleOverlayRenderer NITINOL_60_CASING;
    public static SimpleOverlayRenderer HASTELLOY_K243_CASING;
    public static SimpleOverlayRenderer MAINTENANCE_OVERLAY_STERILE_CLEANING;
    public static SimpleOverlayRenderer MAINTENANCE_OVERLAY_ISO3_CLEANING;
    public static SimpleOverlayRenderer MAINTENANCE_OVERLAY_ISO2_CLEANING;
    public static SimpleOverlayRenderer MAINTENANCE_OVERLAY_ISO1_CLEANING;
    public static SimpleOverlayRenderer CLADDED_REACTOR_CASING;
    public static SimpleOverlayRenderer ENDER_ITEM_LINK;
    public static SimpleOverlayRenderer WIRELESS_ENERGY_HATCH;
    public static SimpleOverlayRenderer WIRELESS_ENERGY_HATCH_4A;
    public static SimpleOverlayRenderer WIRELESS_ENERGY_HATCH_16A;
    public static SimpleOverlayRenderer WIRELESS_ENERGY_HATCH_OFF;
    public static SimpleOverlayRenderer WIRELESS_DATA_HATCH;
    public static SimpleOverlayRenderer SEABORGIUM_SUBSTATION_CASING;
    public static SimpleOverlayRenderer MARAGING_STEEL_250_CASING;
    public static SimpleOverlayRenderer INCONEL_625_CASING;

    public static OrientedOverlayRenderer NAQADAH_OVERLAY;
    public static OrientedOverlayRenderer ROCKET_OVERLAY;


    private ClientHandler() {
    }

    public static void preInit() {
        ADVANCED_FUSION = new SimpleOverlayRenderer("casings/fusion/machine_casing_adv_fusion_hatch");
        ADVANCED_FUSION_ACTIVE = new SimpleOverlayRenderer("casings/fusion/machine_casing_adv_fusion_hatch_active");

        NAQADAH_OVERLAY = new OrientedOverlayRenderer("machines/naquadah_reactor");
        ROCKET_OVERLAY = new OrientedOverlayRenderer("machines/rocket_generator");

        BIO_REACTOR = new SimpleOverlayRenderer("casings/solid/bio_reactor_casing");
        FUSION_TEXTURE = new SimpleOverlayRenderer("casings/fusion/machine_casing_fusion_glass");
        SEABORGIUM_SUBSTATION_CASING = new SimpleOverlayRenderer("casings/solid/seaborgium_substation_casing");

        ORGANIC_REPLICATOR_OVERLAY = new OrientedOverlayRenderer("machines/organic_replicator");
        FUSION_REACTOR_OVERLAY = new OrientedOverlayRenderer("machines/fusion_reactor");
        QUBIT_COMPUTER_OVERLAY = new OrientedOverlayRenderer("machines/qubit_computer");

        FREEZER_OVERLAY = new OrientedOverlayRenderer("machines/freezer");

        MAINTENANCE_OVERLAY_STERILE_CLEANING = new SimpleOverlayRenderer("overlay/machine/overlay_maintenance_sterile_cleaning");
        MAINTENANCE_OVERLAY_ISO3_CLEANING = new SimpleOverlayRenderer("overlay/machine/overlay_maintenance_iso_3_cleaning");
        MAINTENANCE_OVERLAY_ISO2_CLEANING = new SimpleOverlayRenderer("overlay/machine/overlay_maintenance_iso_2_cleaning");
        MAINTENANCE_OVERLAY_ISO1_CLEANING = new SimpleOverlayRenderer("overlay/machine/overlay_maintenance_iso_1_cleaning");


        ENDER_ITEM_LINK = new SimpleOverlayRenderer("cover/overlay_ender_item_link");

        WIRELESS_ENERGY_HATCH = new SimpleOverlayRenderer("overlay/machine/overlay_wireless_energy");
        WIRELESS_ENERGY_HATCH_4A = new SimpleOverlayRenderer("overlay/machine/overlay_wireless_energy_4a");
        WIRELESS_ENERGY_HATCH_16A = new SimpleOverlayRenderer("overlay/machine/overlay_wireless_energy_16a");
        WIRELESS_ENERGY_HATCH_OFF = new SimpleOverlayRenderer("overlay/machine/overlay_wireless_energy_off");
        WIRELESS_DATA_HATCH = new SimpleOverlayRenderer("overlay/machine/overlay_wireless_data");

        //MetaCasing1
        STABALLOY_CASING = new SimpleOverlayRenderer("casings/metal_casings/staballoy");
        QUANTUM_CASING = new SimpleOverlayRenderer("casings/metal_casings/quantum");
        TRITANIUM_CASING = new SimpleOverlayRenderer("casings/metal_casings/tritanium");

        //MetalCasing2
        HASTELLOY_X78_CASING = new SimpleOverlayRenderer("casings/metal_casings/hastelloy_x78");
        HASTELLOY_N_CASING = new SimpleOverlayRenderer("casings/metal_casings/hastelloy_n");
        INCOLOY_813_CASING = new SimpleOverlayRenderer("casings/metal_casings/incoloy_813");
        NITINOL_60_CASING = new SimpleOverlayRenderer("casings/metal_casings/nitinol_60");
        HASTELLOY_K243_CASING = new SimpleOverlayRenderer("casings/metal_casings/hastelloy_k243");
        MARAGING_STEEL_250_CASING = new SimpleOverlayRenderer("casings/metal_casings/maraging_steel_250");
        INCONEL_625_CASING = new SimpleOverlayRenderer("casings/metal_casings/inconel_625");

        ENRICHED_NAQUADAH_ALLOY_CASING = new SimpleOverlayRenderer("casings/metal_casings/enriched_naquadah_alloy");
        NAQUADRIA_CASING = new SimpleOverlayRenderer("casings/metal_casings/naquadria");
        IRIDIUM_CASING = new SimpleOverlayRenderer("casings/metal_casings/iridium");
        HYPER_CASING = new SimpleOverlayRenderer("casings/solid/hyper_casing");
        HYPER_CASING_2 = new SimpleOverlayRenderer("casings/solid/hyper_casing_2");
        CLADDED_REACTOR_CASING = new SimpleOverlayRenderer("casings/solid/cladded_reactor_casing");
    }
}
