package com.fulltrix.gcyl.mixin;

import com.fulltrix.gcyl.mixinhelpers.MixinProspectorMode;
import com.fulltrix.gcyl.mixinhelpers.MixinWidgetOreList;
import com.fulltrix.gcyl.mixinhelpers.MixinWidgetProspectingMap;
import gregtech.api.GTValues;
import gregtech.api.capability.GregtechCapabilities;
import gregtech.api.capability.IElectricItem;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.LabelWidget;
import gregtech.api.items.gui.PlayerInventoryHolder;
import gregtech.api.items.metaitem.stats.IItemBehaviour;
import gregtech.api.util.GTUtility;
import gregtech.common.items.behaviors.ProspectorScannerBehavior;
import gregtech.common.terminal.app.prospector.ProspectorMode;
import gregtech.common.terminal.app.prospector.widget.WidgetOreList;
import gregtech.common.terminal.app.prospector.widget.WidgetProspectingMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import scala.util.parsing.combinator.PackratParsers;

import java.util.List;
import java.util.Locale;

@Mixin(value = ProspectorScannerBehavior.class, remap = false)
public class MixinProspectorScannerBehavior implements IItemBehaviour {

    @Unique
    private MixinWidgetOreList widgetOreList;

    @Unique
    private WidgetOreList widgetOreListOG;

    @Final
    @Shadow
    private int radius;

    @Final
    @Shadow
    private int tier;

    @Shadow
    @Final
    private static int FLUID_PROSPECTION_THRESHOLD;

    @Shadow
    @Final
    private static long VOLTAGE_FACTOR;

    /**
     * @author
     * @reason
     */
    @Overwrite
    public ActionResult<ItemStack> onItemRightClick(@NotNull World world, @NotNull EntityPlayer player, EnumHand hand) {
        ItemStack heldItem = player.getHeldItem(hand);
        if (!world.isRemote) {
            if (player.isSneaking()) {
                ItemStack stack = player.getHeldItem(hand);
                MixinProspectorMode mode = getMode(stack);
                MixinProspectorMode nextMode = mode.next();
                if (nextMode == MixinProspectorMode.FLUID) {
                    if (tier >= FLUID_PROSPECTION_THRESHOLD) {
                        setMode(stack, nextMode);
                        player.sendStatusMessage(new TextComponentTranslation("metaitem.prospector.mode.fluid"), true);
                    }
                } else if (nextMode == MixinProspectorMode.VIRTUAL_ORE) {
                    setMode(stack, nextMode);
                    player.sendStatusMessage(new TextComponentTranslation("metaitem.prospector.mode.virtual_ores"), true);
                } else {
                    setMode(stack, nextMode);
                    player.sendStatusMessage(new TextComponentTranslation("metaitem.prospector.mode.ores"), true);
                }
            } else if (checkCanUseScanner(heldItem, player, true)) {
                new PlayerInventoryHolder(player, hand).openUI();
            } else {
                player.sendMessage(new TextComponentTranslation("behavior.prospector.not_enough_energy"));
            }
        }
        return ActionResult.newResult(EnumActionResult.SUCCESS, heldItem);
    }

    @Unique
    private boolean checkCanUseScanner(ItemStack stack, @NotNull EntityPlayer player, boolean simulate) {
        return player.isCreative() || drainEnergy(stack, GTValues.V[tier] / VOLTAGE_FACTOR, simulate);
    }

    @Unique
    private static boolean drainEnergy(@NotNull ItemStack stack, long amount, boolean simulate) {
        IElectricItem electricItem = stack.getCapability(GregtechCapabilities.CAPABILITY_ELECTRIC_ITEM, null);
        if (electricItem == null) return false;

        return electricItem.discharge(amount, Integer.MAX_VALUE, true, false, simulate) >= amount;
    }

    @Unique
    private static void setMode(ItemStack stack, @NotNull MixinProspectorMode mode) {
        NBTTagCompound tagCompound = GTUtility.getOrCreateNbtCompound(stack);
        tagCompound.setInteger("Mode", mode.ordinal());
    }


    /**
     * @author
     * @reason
     */
    @Overwrite
    public ModularUI createUI(PlayerInventoryHolder holder, @NotNull EntityPlayer entityPlayer) {
        MixinProspectorMode mode = getMode(entityPlayer.getHeldItem(EnumHand.MAIN_HAND));
        ModularUI.Builder builder = ModularUI.builder(GuiTextures.BACKGROUND, 332, 200);

        if(mode == MixinProspectorMode.VIRTUAL_ORE) {
            this.widgetOreList = new MixinWidgetOreList(32 * radius - 6, 18, 332 - 32 * radius, 176);
            builder.widget(this.widgetOreList);
            builder.widget(new MixinWidgetProspectingMap(6, 18, radius, this.widgetOreList, mode, 1));
        } else {
            ProspectorMode modeOG;

            if(mode == MixinProspectorMode.ORE)
                modeOG = ProspectorMode.ORE;
            else
                modeOG = ProspectorMode.FLUID;

            this.widgetOreListOG = new WidgetOreList(32 * radius - 6, 18, 332 - 32 * radius, 176);
            builder.widget(this.widgetOreListOG);
            builder.widget(new WidgetProspectingMap(6, 18, radius, this.widgetOreListOG, modeOG, 1));
        }
        // Cardinal directions
        builder.widget(new LabelWidget(3 + (16 * (radius * 2 - 1)) / 2, 14, "N", 0xAAAAAA).setShadow(true));
        builder.widget(new LabelWidget(3 + (16 * (radius * 2 - 1)) / 2, 14 + 16 * (radius * 2 - 1), "S", 0xAAAAAA)
                .setShadow(true));
        builder.widget(new LabelWidget(3, 15 + (16 * (radius * 2 - 1)) / 2, "W", 0xAAAAAA).setShadow(true));
        builder.widget(new LabelWidget(3 + 16 * (radius * 2 - 1), 15 + (16 * (radius * 2 - 1)) / 2, "E", 0xAAAAAA)
                .setShadow(true));
        return builder.label(6, 6, getTranslationKey()).build(holder, entityPlayer);
    }

    @Unique
    private static MixinProspectorMode getMode(ItemStack stack) {
        if (stack == ItemStack.EMPTY) {
            return MixinProspectorMode.ORE;
        }
        NBTTagCompound tag = stack.getTagCompound();
        if (tag == null) {
            return MixinProspectorMode.ORE;
        }
        if (tag.hasKey("Mode", Constants.NBT.TAG_INT)) {
            return MixinProspectorMode.VALUES[tag.getInteger("Mode")];
        }
        return MixinProspectorMode.ORE;
    }

    @Inject(method = {"addInformation"}, at = {@At("HEAD")}, cancellable = true)
    public void addInformation(ItemStack itemStack, List<String> lines, CallbackInfo ci) {
        IItemBehaviour.super.addInformation(itemStack, lines);
        if (tier >= FLUID_PROSPECTION_THRESHOLD) {
            lines.add(new TextComponentTranslation("metaitem.prospector.tooltip.fluids", radius).getFormattedText());
            lines.add(new TextComponentTranslation((getMode(itemStack).unlocalizedName)).getFormattedText());
        } else {
            lines.add(new TextComponentTranslation("metaitem.prospector.tooltip.ores", radius).getFormattedText());
        }

        ci.cancel();
    }

    @Unique
    private String getTranslationKey() {
        return String.format("metaitem.prospector.%s.name", GTValues.VN[tier].toLowerCase(Locale.ROOT));
    }
}
