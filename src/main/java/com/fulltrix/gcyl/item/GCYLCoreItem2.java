package com.fulltrix.gcyl.item;

import gregtech.api.GTValues;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IElectricItem;
import gregtech.api.items.metaitem.ElectricStats;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.StandardMetaItem;
import gregtech.api.items.metaitem.stats.IItemBehaviour;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

import static com.fulltrix.gcyl.item.GCYLCoreItems.*;

public class GCYLCoreItem2 extends StandardMetaItem {
    public GCYLCoreItem2(short metaItemOffset) {
        super(metaItemOffset);
    }

    @Override
    public void registerSubItems() {
        BATTERY_NIMH = addItem(1, "nickel.metal.hydride.battery").addComponents(ElectricStats.createRechargeableBattery(7200000, GTValues.EV)).setModelAmount(8);

        BATTERY_SMALL_LITHIUM_ION = addItem(3, "small.lithium.ion.battery").addComponents(ElectricStats.createRechargeableBattery(28800000, GTValues.IV)).setModelAmount(8);
        BATTERY_MEDIUM_LITHIUM_ION = addItem(6, "medium.lithium.ion.battery").addComponents(ElectricStats.createRechargeableBattery(115200000, GTValues.LuV)).setModelAmount(8);
        BATTERY_LARGE_LITHIUM_ION = addItem(4, "large.lithium.ion.battery").addComponents(ElectricStats.createRechargeableBattery(460800000, GTValues.ZPM)).setModelAmount(8);

        BATTERY_SMALL_LIS = addItem(7, "small.lithium.sulfide.battery").addComponents(ElectricStats.createRechargeableBattery(1843200000, GTValues.UV)).setModelAmount(8);
        BATTERY_MEDIUM_LIS = addItem(2, "medium.lithium.sulfide.battery").addComponents(ElectricStats.createRechargeableBattery(7372800000L, GTValues.UHV)).setModelAmount(8);
        BATTERY_LARGE_LIS = addItem(5, "large.lithium.sulfide.battery").addComponents(ElectricStats.createRechargeableBattery(29491200000L, GTValues.UEV)).setModelAmount(8);

        BATTERY_SMALL_FLUORIDE = addItem(8, "small.fluoride.battery").addComponents(ElectricStats.createRechargeableBattery(117964800000L, GTValues.UIV)).setModelAmount(8);
        BATTERY_MEDIUM_FLUORIDE = addItem(9, "medium.fluoride.battery").addComponents(ElectricStats.createRechargeableBattery(471859200000L, GTValues.UXV)).setModelAmount(8);
        BATTERY_LARGE_FLUORIDE = addItem(10, "large.fluoride.battery").addComponents(ElectricStats.createRechargeableBattery(1887436800000L, GTValues.OpV)).setModelAmount(8);

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, @Nullable World worldIn, List<String> lines, ITooltipFlag tooltipFlag) {
        MetaItem<?>.MetaValueItem item = getItem(itemStack);
        if (item == null) return;
        String unlocalizedTooltip = "metaitem." + item.unlocalizedName + ".tooltip";
        if (I18n.hasKey(unlocalizedTooltip)) {
            lines.addAll(Arrays.asList(I18n.format(unlocalizedTooltip).split("/n")));
        }

        IElectricItem electricItem = itemStack.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null);
        if (electricItem != null) {
            lines.add(I18n.format("metaitem.generic.electric_item.tooltip",
                    electricItem.getCharge(),
                    electricItem.getMaxCharge(),
                    GTValues.VN[electricItem.getTier()]));
        }

        IFluidHandlerItem fluidHandler = ItemHandlerHelper.copyStackWithSize(itemStack, 1)
                .getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
        if (fluidHandler != null) {
            IFluidTankProperties fluidTankProperties = fluidHandler.getTankProperties()[0];
            FluidStack fluid = fluidTankProperties.getContents();
            if (fluid != null) {
                lines.add(I18n.format("metaitem.generic.fluid_container.tooltip",
                        fluid.amount,
                        fluidTankProperties.getCapacity(),
                        fluid.getLocalizedName()));
            } else lines.add(I18n.format("metaitem.generic.fluid_container.tooltip_empty"));
        }

        for (IItemBehaviour behaviour : getBehaviours(itemStack)) {
            behaviour.addInformation(itemStack, lines);
        }
    }
}
