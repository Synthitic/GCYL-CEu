//TODO none of the mixins i have tried have worked. this is one that i would hope i could get working

/*
package com.fulltrix.gcyl.mixin;

import com.fulltrix.gcyl.item.GCYLCoreItems;
import gregtech.api.util.AssemblyLineManager;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = AssemblyLineManager.class, remap = false)
public final class MixinAssemblyLineManager {

    @Inject(method = "getDefaultResearchStationItem(I)Lnet/minecraft/item/ItemStack;", at = @At("HEAD"), cancellable = true)
    private static void getDefaultResearchStationItem(int cwut, CallbackInfoReturnable<ItemStack> cir) {
        if(cwut > 128) {
            cir.setReturnValue(GCYLCoreItems.TOOL_DATA_MODULE_CLUSTER.getStackForm());
        }
    }
}

 */
