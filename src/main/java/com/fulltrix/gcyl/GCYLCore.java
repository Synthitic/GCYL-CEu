package com.fulltrix.gcyl;

import com.fulltrix.gcyl.item.GCYLHeatingCoil;
import com.fulltrix.gcyl.item.GCYLMetaBlocks;
import com.fulltrix.gcyl.machines.GCYLTileEntities;
import gregtech.GTInternalTags;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import java.io.IOException;

import static com.fulltrix.gcyl.item.GCYLMetaBlocks.HEATING_COIL;
import static gregtech.api.GregTechAPI.HEATING_COILS;

@Mod(modid = GCYLCore.MODID, name = GCYLCore.NAME, version = GCYLCore.VERSION,
        dependencies = GTInternalTags.DEP_VERSION_STRING)
public class GCYLCore {
    public static final String MODID = Tags.MODID;
    public static final String NAME = Tags.MODNAME;
    public static final String VERSION = Tags.VERSION;
    @SidedProxy(modId = MODID, clientSide = "com.fulltrix.gcyl.ClientProxy", serverSide = "com.fulltrix.gcyl.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        proxy.preInit();
        GCYLMetaBlocks.init();
        GCYLTileEntities.init();

        for (GCYLHeatingCoil.CoilType type : GCYLHeatingCoil.CoilType.values()) {
            HEATING_COILS.put(HEATING_COIL.getState(type), type);
        }
    }

    @EventHandler
    // load "Do your mod setup. Build whatever data structures you care about." (Remove if not needed)
    public void init(FMLInitializationEvent event) throws IOException {
        proxy.onLoad();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        //CommonProxy.postInit();
    }

    @EventHandler
    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {
    }

    static {
        FluidRegistry.enableUniversalBucket();
    }
}
