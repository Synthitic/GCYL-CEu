package com.fulltrix.gcyl.api.worldgen;

import com.fulltrix.gcyl.Tags;
import gregtech.api.worldgen.bedrockFluids.ChunkPosDimension;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class VirtualOreVeinSaveData extends WorldSavedData {

    private static VirtualOreVeinSaveData INSTANCE;

    public static final String dataName = Tags.MODID + ".virtualOreVeinData";

    public VirtualOreVeinSaveData(String s) {
        super(s);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        NBTTagList veinList = nbt.getTagList("veinInfo", 10);
        VirtualOreVeinHandler.veinCache.clear();
        for (int i = 0; i < veinList.tagCount(); i++) {
            NBTTagCompound tag = veinList.getCompoundTagAt(i);
            ChunkPosDimension coords = ChunkPosDimension.readFromNBT(tag);
            if (coords != null) {
                VirtualOreVeinHandler.VirtualOreVeinWorldEntry info = VirtualOreVeinHandler.VirtualOreVeinWorldEntry
                        .readFromNBT(tag.getCompoundTag("info"));
                VirtualOreVeinHandler.veinCache.put(coords, info);
            }
        }

        if (nbt.hasKey("version")) {
            VirtualOreVeinHandler.saveDataVersion = nbt.getInteger("version");
        } else if (veinList.isEmpty()) {
            // there are no veins, so there is no data to be changed or lost by bumping the version
            VirtualOreVeinHandler.saveDataVersion = VirtualOreVeinHandler.MAX_VIRTUAL_ORE_SAVE_DATA_VERSION;
        } else {
            // version number was added to the save data with version 2
            VirtualOreVeinHandler.saveDataVersion = 1;
        }
    }

    @Override
    public @NotNull NBTTagCompound writeToNBT(@NotNull NBTTagCompound nbt) {
        NBTTagList oilList = new NBTTagList();
        for (Map.Entry<ChunkPosDimension, VirtualOreVeinHandler.VirtualOreVeinWorldEntry> e : VirtualOreVeinHandler.veinCache
                .entrySet()) {
            if (e.getKey() != null && e.getValue() != null) {
                NBTTagCompound tag = e.getKey().writeToNBT();
                tag.setTag("info", e.getValue().writeToNBT());
                oilList.appendTag(tag);
            }
        }
        nbt.setTag("veinInfo", oilList);
        nbt.setInteger("version", VirtualOreVeinHandler.saveDataVersion);

        return nbt;
    }

    public static void setDirty() {
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER && INSTANCE != null)
            INSTANCE.markDirty();
    }

    public static void setInstance(VirtualOreVeinSaveData in) {
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER)
            INSTANCE = in;
    }
}
