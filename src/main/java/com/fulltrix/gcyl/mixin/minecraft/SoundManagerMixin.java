package com.fulltrix.gcyl.mixin.minecraft;

import com.google.common.collect.Multimap;
import gregtech.api.util.GTLog;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.ITickableSound;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//TODO remove (THREADED TESTING MARK123)
@Mixin(SoundManager.class)
public abstract class SoundManagerMixin {

    @Shadow
    @Final
    private static Marker LOG_MARKER;

    @Shadow
    @Final
    private static Logger LOGGER;

    @Shadow
    private SoundManager.SoundSystemStarterThread sndSystem;

    @Shadow
    private int playTime;

    @Shadow
    @Final
    private Map<String, ISound> playingSounds;

    @Shadow
    @Final
    private Map<ISound, String> invPlayingSounds;

    @Shadow
    @Final
    private Multimap<SoundCategory, String> categorySounds;

    @Shadow
    @Final
    private List<ITickableSound> tickableSounds;

    @Shadow
    @Final
    private Map<ISound, Integer> delayedSounds;

    @Shadow
    @Final
    private Map<String, Integer> playingSoundsStopTime;

    @Shadow
    public abstract void stopSound(ISound sound);

    @Shadow
    public abstract void playSound(ISound p_sound);

    @Shadow
    abstract float getClampedVolume(ISound soundIn);

    @Shadow
    abstract float getClampedPitch(ISound soundIn);

    /**
     * @author
     * @reason
     */
    @Overwrite
    public void updateAllSounds()
    {
        ++this.playTime;

        for (ITickableSound itickablesound : this.tickableSounds)
        {
            itickablesound.update();

            if (itickablesound.isDonePlaying())
            {
                this.stopSound(itickablesound);
            }
            else
            {
                String s = this.invPlayingSounds.get(itickablesound);
                this.sndSystem.setVolume(s, this.getClampedVolume(itickablesound));
                this.sndSystem.setPitch(s, this.getClampedPitch(itickablesound));
                this.sndSystem.setPosition(s, itickablesound.getXPosF(), itickablesound.getYPosF(), itickablesound.getZPosF());
            }
        }

        Iterator<Map.Entry<String, ISound>> iterator = this.playingSounds.entrySet().iterator();

        try {
            while (iterator.hasNext()) {
                Map.Entry<String, ISound> entry = (Map.Entry) iterator.next();
                String s1 = entry.getKey();
                ISound isound = entry.getValue();

                if (!this.sndSystem.playing(s1)) {
                    int i = ((Integer) this.playingSoundsStopTime.get(s1)).intValue();

                    if (i <= this.playTime) {
                        int j = isound.getRepeatDelay();

                        if (isound.canRepeat() && j > 0) {
                            this.delayedSounds.put(isound, Integer.valueOf(this.playTime + j));
                        }

                        iterator.remove();
                        LOGGER.debug(LOG_MARKER, "Removed channel {} because it's not playing anymore", (Object) s1);
                        this.sndSystem.removeSource(s1);
                        this.playingSoundsStopTime.remove(s1);

                        try {
                            this.categorySounds.remove(isound.getCategory(), s1);
                        } catch (RuntimeException var8) {
                            ;
                        }

                        if (isound instanceof ITickableSound) {
                            this.tickableSounds.remove(isound);
                        }
                    }
                }
            }
        } catch (ConcurrentModificationException e){
            GTLog.logger.info("Sound CME Caught!");
        }

        Iterator<Map.Entry<ISound, Integer>> iterator1 = this.delayedSounds.entrySet().iterator();

        try {
            while (iterator1.hasNext()) {
                Map.Entry<ISound, Integer> entry1 = (Map.Entry) iterator1.next();

                if (this.playTime >= ((Integer) entry1.getValue()).intValue()) {
                    ISound isound1 = entry1.getKey();

                    if (isound1 instanceof ITickableSound) {
                        ((ITickableSound) isound1).update();
                    }

                    this.playSound(isound1);
                    iterator1.remove();
                }
            }
        } catch (ConcurrentModificationException ignored) {
            GTLog.logger.info("Sound CME Caught!");
        }
    }


}
