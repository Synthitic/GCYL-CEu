package com.fulltrix.gcyl.api.worldgen;

import gregtech.api.network.IClientExecutor;
import gregtech.api.network.IPacket;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Map;

public class PacketVirtualOreVeinList implements IPacket, IClientExecutor {

    private Map<VirtualOreVeinHandler.VirtualOreVeinWorldEntry, Integer> map;

    @SuppressWarnings("unused")
    public PacketVirtualOreVeinList() {}

    public PacketVirtualOreVeinList(HashMap<VirtualOreVeinHandler.VirtualOreVeinWorldEntry, Integer> map) {
        this.map = map;
    }

    @Override
    public void encode(PacketBuffer buf) {
        buf.writeVarInt(map.size());
        for (Map.Entry<VirtualOreVeinHandler.VirtualOreVeinWorldEntry, Integer> entry : map.entrySet()) {
            NBTTagCompound tag = entry.getKey().writeToNBT();
            tag.setInteger("weight", entry.getValue());
            ByteBufUtils.writeTag(buf, tag);
        }
    }

    @Override
    public void decode(PacketBuffer buf) {
        this.map = new HashMap<>();
        int size = buf.readVarInt();
        for (int i = 0; i < size; i++) {
            NBTTagCompound tag = ByteBufUtils.readTag(buf);
            if (tag == null || tag.isEmpty()) continue;

            VirtualOreVeinHandler.VirtualOreVeinWorldEntry entry = VirtualOreVeinHandler.VirtualOreVeinWorldEntry
                    .readFromNBT(tag);
            this.map.put(entry, tag.getInteger("weight"));
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void executeClient(NetHandlerPlayClient handler) {
        VirtualOreVeinHandler.virtualOreVeinList.clear();
        for (Map.Entry<VirtualOreVeinHandler.VirtualOreVeinWorldEntry, Integer> entry : map.entrySet()) {
            VirtualOreVeinHandler.virtualOreVeinList.put(entry.getKey().getDefinition(), entry.getValue());
        }
    }

}
