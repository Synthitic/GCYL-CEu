package com.fulltrix.gcyl.mixin;

import com.fulltrix.gcyl.GCYLConfig;
import gregtech.api.GregTechAPI;
import gregtech.api.block.machines.BlockMachine;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.TickableTileEntityBase;
import gregtech.api.util.GTLog;
import gregtech.client.particle.GTNameTagParticle;
import gregtech.core.network.packets.PacketRecoverMTE;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.*;

import java.text.DecimalFormat;
import java.util.concurrent.CompletableFuture;

import static com.fulltrix.gcyl.api.GCYLUtility.getGlobalThreadPoolExecutor;

//TODO remove (THREADED TESTING MARK123)
@Mixin(value = MetaTileEntityHolder.class, remap = false)
public abstract class MetaTileEntityHolderMixin extends TickableTileEntityBase {

    @Shadow
    MetaTileEntity metaTileEntity;

    @Shadow
    private boolean needToUpdateLightning;

    @Shadow
    @Final
    private int[] timeStatistics;

    @Shadow
    private int timeStatisticsIndex;

    @Shadow
    private int lagWarningCount;

    @Shadow
    public abstract MetaTileEntity getMetaTileEntity();

    @Unique
    private CompletableFuture<Void> updateFuture;

    /**
     * @author
     * @reason
     */
    @Overwrite
    public void update() {
        long tickTime = System.nanoTime();
        if (this.metaTileEntity != null) {
            if(GCYLConfig.experimental.threadedMTEUpdates) {
                if (updateFuture == null || updateFuture.isDone()) {
                    updateFuture = CompletableFuture.runAsync(() -> metaTileEntity.update(), getGlobalThreadPoolExecutor());
                }
                while(getGlobalThreadPoolExecutor().getActiveCount() == getGlobalThreadPoolExecutor().getMaximumPoolSize()) {

                }
            } else {
                metaTileEntity.update();
            }
        } else if (this.world.isRemote) {
            GregTechAPI.networkHandler.sendToServer(new PacketRecoverMTE(this.world.provider.getDimension(), this.getPos()));
        } else if (this.world.getBlockState(this.pos).getBlock() instanceof BlockMachine) {
            this.world.setBlockToAir(this.pos);
        }

        if (this.needToUpdateLightning) {
            this.getWorld().checkLight(this.getPos());
            this.needToUpdateLightning = false;
        }

        if (!this.world.isRemote && this.metaTileEntity != null && this.getMetaTileEntity().isValid()) {
            tickTime = System.nanoTime() - tickTime;
            if (this.timeStatistics.length > 0) {
                this.timeStatistics[this.timeStatisticsIndex] = (int)tickTime;
                this.timeStatisticsIndex = (this.timeStatisticsIndex + 1) % this.timeStatistics.length;
            }

            if (tickTime > 100000000L && this.getMetaTileEntity().doTickProfileMessage() && this.lagWarningCount++ < 10) {
                GTLog.logger.warn("WARNING: Possible Lag Source at [" + this.getPos().getX() + ", " + this.getPos().getY() + ", " + this.getPos().getZ() + "] in Dimension " + this.world.provider.getDimension() + " with " + tickTime + "ns caused by an instance of " + this.getMetaTileEntity().getClass());
            }
        }

        super.update();
    }
}
