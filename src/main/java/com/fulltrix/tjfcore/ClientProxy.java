package com.fulltrix.tjfcore;

import com.fulltrix.tjfcore.client.ClientHandler;
import com.fulltrix.tjfcore.item.TJFCoreItems;
import com.fulltrix.tjfcore.item.TJFMetaBlocks;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit() {
        super.preInit();
        ClientHandler.preInit();
    }

    @Override
    public void onLoad() throws IOException {
        super.onLoad();
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        TJFMetaBlocks.registerItemModels();
    }
}
