package com.fulltrix.gcyl.mixin.minecraft;

import com.google.common.base.Predicate;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import gregtech.api.util.GTLog;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.management.PlayerChunkMap;
import net.minecraft.server.management.PlayerChunkMapEntry;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.*;

@Mixin(PlayerChunkMap.class)
public class PlayerChunkMapMixin {

    @Shadow
    @Final
    private WorldServer world;

    @Shadow
    @Final
    private List<EntityPlayerMP> players;

    @Shadow
    @Final
    private Set<PlayerChunkMapEntry> dirtyEntries;

    @Shadow
    @Final
    private List<PlayerChunkMapEntry> pendingSendToPlayers;

    @Shadow
    @Final
    private List<PlayerChunkMapEntry> entriesWithoutChunks;

    @Shadow
    @Final
    private List<PlayerChunkMapEntry> entries;

    @Shadow
    private long previousTotalWorldTime;

    @Shadow
    private boolean sortMissingChunks = true;

    @Shadow
    private boolean sortSendToPlayers = true;

    @Shadow
    @Final
    private static Predicate<EntityPlayerMP> CAN_GENERATE_CHUNKS;


    /**
     * @author
     * @reason
     */
    @Overwrite
    public void tick()
    {
        long i = this.world.getTotalWorldTime();

        if (i - this.previousTotalWorldTime > 8000L)
        {
            this.previousTotalWorldTime = i;

            try {
                for (int j = 0; j < this.entries.size(); ++j) {
                    PlayerChunkMapEntry playerchunkmapentry = this.entries.get(j);
                    playerchunkmapentry.update();
                    playerchunkmapentry.updateChunkInhabitedTime();
                }
            } catch (Exception e) {
                GTLog.logger.info("PlayerChunkMap CME Caught!* (first for loop)");
            }
        }

        if (!this.dirtyEntries.isEmpty())
        {
            try {
                Object[] array = this.dirtyEntries.toArray();
                for (Object o : array) {
                    PlayerChunkMapEntry playerChunkMapEntry2 = (PlayerChunkMapEntry) o;
                    playerChunkMapEntry2.update();
                }
            } catch (ConcurrentModificationException e) {
                GTLog.logger.info("PlayerChunkMap CME Caught! (second for loop)");
            }

            this.dirtyEntries.clear();
        }

        if (this.sortMissingChunks && i % 4L == 0L)
        {
            this.sortMissingChunks = false;
            Collections.sort(this.entriesWithoutChunks, new Comparator<PlayerChunkMapEntry>()
            {
                public int compare(PlayerChunkMapEntry p_compare_1_, PlayerChunkMapEntry p_compare_2_)
                {
                    return ComparisonChain.start().compare(p_compare_1_.getClosestPlayerDistance(), p_compare_2_.getClosestPlayerDistance()).result();
                }
            });
        }

        if (this.sortSendToPlayers && i % 4L == 2L)
        {
            this.sortSendToPlayers = false;
            Collections.sort(this.pendingSendToPlayers, new Comparator<PlayerChunkMapEntry>()
            {
                public int compare(PlayerChunkMapEntry p_compare_1_, PlayerChunkMapEntry p_compare_2_)
                {
                    return ComparisonChain.start().compare(p_compare_1_.getClosestPlayerDistance(), p_compare_2_.getClosestPlayerDistance()).result();
                }
            });
        }

        if (!this.entriesWithoutChunks.isEmpty())
        {
            long l = System.nanoTime() + 50000000L;
            int k = 49;
            Iterator<PlayerChunkMapEntry> iterator = this.entriesWithoutChunks.iterator();

            try {
                while (iterator.hasNext()) {

                    PlayerChunkMapEntry playerchunkmapentry1 = iterator.next();


                    if (playerchunkmapentry1.getChunk() == null) {
                        boolean flag = playerchunkmapentry1.hasPlayerMatching(CAN_GENERATE_CHUNKS);

                        if (playerchunkmapentry1.providePlayerChunk(flag)) {
                            iterator.remove();

                            if (playerchunkmapentry1.sendToPlayers()) {
                                this.pendingSendToPlayers.remove(playerchunkmapentry1);
                            }

                            --k;

                            if (k < 0 || System.nanoTime() > l) {
                                break;
                            }
                        }
                    }
                }
            } catch (ConcurrentModificationException e) {
                GTLog.logger.info("PlayerChunkMap CME Caught! (iterator.hasNext)");
            }
        }

        if (!this.pendingSendToPlayers.isEmpty())
        {
            int i1 = 81;
            Iterator<PlayerChunkMapEntry> iterator1 = this.pendingSendToPlayers.iterator();

            try {
                while (iterator1.hasNext()) {

                    PlayerChunkMapEntry playerchunkmapentry3 = iterator1.next();
                    if (playerchunkmapentry3.sendToPlayers()) {
                        iterator1.remove();
                        --i1;

                        if (i1 < 0) {
                            break;
                        }
                    }
                }
            } catch (ConcurrentModificationException e) {
                GTLog.logger.info("PlayerChunkMap CME Caught! (iterator1.hasNext)");
            }
        }

        if (this.players.isEmpty())
        {
            WorldProvider worldprovider = this.world.provider;

            if (!worldprovider.canRespawnHere())
            {
                this.world.getChunkProvider().queueUnloadAll();
            }
        }
    }
}
