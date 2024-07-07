package com.fulltrix.gcyl;

import com.fulltrix.gcyl.api.recipes.CachedRecipes;
import com.fulltrix.gcyl.api.util.VirtualContainerRegistry;
import com.fulltrix.gcyl.api.util.VirtualEnergyRegistry;
import com.fulltrix.gcyl.api.util.VirtualResearchRegistry;
import com.fulltrix.gcyl.blocks.component_al.GCYLComponentALCasing;
import com.fulltrix.gcyl.blocks.elevator.ElevatorCasingTiered;
import com.fulltrix.gcyl.blocks.metal.GCYLCleanroomCasing;
import com.fulltrix.gcyl.covers.GCYLCoverBehaviors;
import com.fulltrix.gcyl.blocks.GCYLHeatingCoil;
import com.fulltrix.gcyl.blocks.GCYLMetaBlocks;
import com.fulltrix.gcyl.machines.GCYLTileEntities;
import gregtech.GTInternalTags;
import gregtech.api.GregTechAPI;
import gregtech.api.cover.CoverDefinition;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.MetaBlocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;

import java.io.IOException;

import static com.fulltrix.gcyl.api.GCYLAPI.*;
import static com.fulltrix.gcyl.blocks.GCYLMetaBlocks.*;
import static gregtech.api.GregTechAPI.COVER_REGISTRY;
import static gregtech.api.GregTechAPI.HEATING_COILS;

@Mod(modid = GCYLCore.MODID, name = GCYLCore.NAME, version = GCYLCore.VERSION,
        dependencies = "required:forge@[14.23.5.2847,);" + "required-after:codechickenlib@[3.2.3,);" +
                "required-after:modularui@[2.3,);" + GTInternalTags.DEP_VERSION_STRING + "required-after:gcym@[1.3.,);" +
                "required-after:materialreplication@[1.3,);" + "required-after:groovyscript@[1.1,);" + "required-after:jei@[4.15.0,);")
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

        for (GCYLComponentALCasing.CasingType type : GCYLComponentALCasing.CasingType.values()) {
            COMPONENT_AL_CASINGS.put(GCYL_COMPONENT_AL_CASING.getState(type), type);
        }

        for (GCYLCleanroomCasing.CasingType type : GCYLCleanroomCasing.CasingType.values()) {
            GCYL_FILTER_CASINGS.put(GCYL_CLEANROOM_CASING.getState(type), type);
        }

        for(ElevatorCasingTiered.CasingType type : ElevatorCasingTiered.CasingType.values()) {
            ELEVATOR_MOTORS.put(ELEVATOR_CASING_TIERED.getState(type), type);
        }


        HEATING_COILS.remove(MetaBlocks.WIRE_COIL.getState(BlockWireCoil.CoilType.TRINIUM),BlockWireCoil.CoilType.TRINIUM);
        HEATING_COILS.remove(MetaBlocks.WIRE_COIL.getState(BlockWireCoil.CoilType.TRITANIUM),BlockWireCoil.CoilType.TRITANIUM);


    }

    @EventHandler
    // load "Do your mod setup. Build whatever data structures you care about." (Remove if not needed)
    public void init(FMLInitializationEvent event) throws IOException {
        proxy.onLoad();

        COVER_REGISTRY.unfreeze();
        GCYLCoverBehaviors.init();
        MinecraftForge.EVENT_BUS.post(new GregTechAPI.RegisterEvent<>(COVER_REGISTRY, CoverDefinition.class));

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit();
    }

    @EventHandler
    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {
    }

    static {
        FluidRegistry.enableUniversalBucket();
    }

    @EventHandler
    public void serverStopped(FMLServerStoppedEvent event) {
        VirtualEnergyRegistry.clearMaps();
        VirtualContainerRegistry.clearMaps();
        VirtualResearchRegistry.clearMaps();
    }
}
