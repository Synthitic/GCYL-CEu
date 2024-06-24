package com.fulltrix.gcyl.api.items;

import com.fulltrix.gcyl.api.util.VirtualEnergyRegistry;
import gregtech.api.GTValues;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IElectricItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.Constants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;

public class WirelessElectricItem implements IElectricItem, ICapabilityProvider {

    protected final ItemStack itemStack;

    protected final long maxCharge;
    protected final int tier;

    protected final boolean chargeable;
    protected final boolean canProvideEnergyExternally;

    protected final List<BiConsumer<ItemStack, Long>> listeners = new ArrayList<>();

    public WirelessElectricItem(ItemStack itemStack, long maxCharge, int tier, boolean chargeable,
                        boolean canProvideEnergyExternally) {
        this.itemStack = itemStack;
        this.maxCharge = maxCharge;
        this.tier = tier;
        this.chargeable = chargeable;
        this.canProvideEnergyExternally = canProvideEnergyExternally;
    }

    @Override
    public void addChargeListener(BiConsumer<ItemStack, Long> chargeListener) {
        listeners.add(chargeListener);
    }

    public void setCharge(long change) {
        if (!itemStack.hasTagCompound()) {
            itemStack.setTagCompound(new NBTTagCompound());
        }
        // noinspection ConstantConditions
        itemStack.getTagCompound().setLong("Charge", change);
        listeners.forEach(l -> l.accept(itemStack, change));
    }

    public void setMaxChargeOverride(long maxCharge) {
        if (!itemStack.hasTagCompound()) {
            itemStack.setTagCompound(new NBTTagCompound());
        }
        // noinspection ConstantConditions
        itemStack.getTagCompound().setLong("MaxCharge", maxCharge);
    }

    @Override
    public long getTransferLimit() {
        return GTValues.V[getTier()];
    }

    @Override
    public long getMaxCharge() {
        NBTTagCompound tagCompound = itemStack.getTagCompound();
        if (tagCompound == null)
            return maxCharge;
        if (tagCompound.hasKey("MaxCharge", Constants.NBT.TAG_LONG))
            return tagCompound.getLong("MaxCharge");
        return maxCharge;
    }


    public UUID getPlayerUUID() {
        NBTTagCompound tagCompound = itemStack.getTagCompound();
        if (tagCompound == null)
            return null;
        return tagCompound.getUniqueId("playerUUID");
    }

    public boolean getInitialized() {
        NBTTagCompound tagCompound = itemStack.getTagCompound();
        if (tagCompound == null)
            return false;
        return tagCompound.getBoolean("initialized");
    }

    public String getPlayerName() {
        NBTTagCompound tagCompound = itemStack.getTagCompound();
        if (tagCompound == null)
            return null;
        return tagCompound.getString("playerName");
    }


    public void setPlayerUUID(UUID uuid) {
        if (!itemStack.hasTagCompound()) {
            itemStack.setTagCompound(new NBTTagCompound());
        }

        itemStack.getTagCompound().setUniqueId("playerUUID", uuid);
    }

    public void setInitialized(boolean initialized) {
        if (!itemStack.hasTagCompound()) {
            itemStack.setTagCompound(new NBTTagCompound());
        }

        itemStack.getTagCompound().setBoolean("initialized", initialized);
    }

    public void setPlayerName(String name) {
        if (!itemStack.hasTagCompound()) {
            itemStack.setTagCompound(new NBTTagCompound());
        }

        itemStack.getTagCompound().setString("playerName", name);
    }

    public long getCharge() {
        NBTTagCompound tagCompound = itemStack.getTagCompound();
        if (tagCompound == null)
            return 0;
        if (tagCompound.getBoolean("Infinite"))
            return getMaxCharge();
        return Math.min(tagCompound.getLong("Charge"), getMaxCharge());
    }

    public void setInfiniteCharge(boolean infiniteCharge) {
        if (!itemStack.hasTagCompound()) {
            itemStack.setTagCompound(new NBTTagCompound());
        }
        // noinspection ConstantConditions
        itemStack.getTagCompound().setBoolean("Infinite", infiniteCharge);
        listeners.forEach(l -> l.accept(itemStack, getMaxCharge()));
    }

    @Override
    public boolean canProvideChargeExternally() {
        return this.canProvideEnergyExternally;
    }

    @Override
    public boolean chargeable() {
        return chargeable;
    }

    @Override
    public long charge(long amount, int chargerTier, boolean ignoreTransferLimit, boolean simulate) {
        if (itemStack.getCount() != 1) {
            return 0L;
        }
        if ((chargeable || amount == Long.MAX_VALUE) && (chargerTier >= tier) && amount > 0L) {
            long canReceive = getMaxCharge() - getCharge();
            if (!ignoreTransferLimit) {
                amount = Math.min(amount, getTransferLimit());
            }
            long charged = Math.min(amount, canReceive);
            if (!simulate) {
                setCharge(getCharge() + charged);
            }
            return charged;
        }
        return 0;
    }

    @Override
    public long discharge(long amount, int chargerTier, boolean ignoreTransferLimit, boolean externally,
                          boolean simulate) {
        if (itemStack.getCount() != 1) {
            return 0L;
        }
        if ((canProvideEnergyExternally || !externally || amount == Long.MAX_VALUE) && (chargerTier >= tier) &&
                amount > 0L) {
            if (!ignoreTransferLimit) {
                amount = Math.min(amount, getTransferLimit());
            }
            long charge = getCharge();
            long discharged = Math.min(amount, charge);
            if (!simulate) {
                setCharge(charge - discharged);
            }
            return discharged;
        }
        return 0;
    }

    @Override
    public int getTier() {
        return tier;
    }

    @Override
    public boolean hasCapability(@NotNull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM;
    }

    @Nullable
    @Override
    public <T> T getCapability(@NotNull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM ? (T) this : null;
    }
}