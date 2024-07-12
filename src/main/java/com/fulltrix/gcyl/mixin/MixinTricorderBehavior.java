package com.fulltrix.gcyl.mixin;

import com.fulltrix.gcyl.api.worldgen.VirtualOreVeinHandler;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.common.items.behaviors.TricorderBehavior;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(value = TricorderBehavior.class, remap = false)
public class MixinTricorderBehavior {

    @Inject(method = {"getScannerInfo"}, at = {@At("TAIL")}, cancellable = true)
    public void getScannerInfo(EntityPlayer player, World world, BlockPos pos, CallbackInfoReturnable<List<ITextComponent>> cir) {

        List<ITextComponent> list = cir.getReturnValue();

        list.add(new TextComponentTranslation("behavior.tricorder.divider"));

        List<Material> materials = VirtualOreVeinHandler.getMaterialsInChunk(world, pos.getX() / 16, pos.getZ() / 16);

        if(materials != null) {
            for (int i = 0; i < materials.size(); i++) {
                Material material = materials.get(i);
                if (material != null) {
                    int operationsRemaining = VirtualOreVeinHandler.getOperationsRemaining(world, pos.getX() / 16, pos.getZ() / 16).get(i);
                    int yield = VirtualOreVeinHandler.getYield(world, pos.getX() / 16, pos.getZ() / 16).get(i);
                    double materialPercent = operationsRemaining * 100.0 / yield;

                    if (player.isCreative()) {
                        list.add(new TextComponentTranslation("behavior.tricorder.virtual_ore.amount", i + 1,
                                new TextComponentTranslation(material.getLocalizedName())
                                        .setStyle(new Style().setColor(TextFormatting.GOLD)),
                                new TextComponentTranslation(String.valueOf(operationsRemaining))
                                        .setStyle(new Style().setColor(TextFormatting.GOLD)),
                                new TextComponentTranslation(String.valueOf(materialPercent))
                                        .setStyle(new Style().setColor(TextFormatting.YELLOW))));
                    } else {
                        list.add(new TextComponentTranslation("behavior.tricorder.virtual_ore.amount_unknown", i + 1,
                                new TextComponentTranslation(String.valueOf(materialPercent))
                                        .setStyle(new Style().setColor(TextFormatting.YELLOW))));
                    }
                } else {
                    list.add(new TextComponentTranslation("behavior.tricorder.virtual_ore.nothing"));
                }
            }
            cir.setReturnValue(list);
        }
    }
}
