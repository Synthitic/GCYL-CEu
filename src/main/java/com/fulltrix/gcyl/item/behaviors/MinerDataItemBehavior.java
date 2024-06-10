package com.fulltrix.gcyl.item.behaviors;

import com.fulltrix.gcyl.recipes.categories.DeepMinerRecipes;
import gregtech.api.items.metaitem.stats.IDataItem;
import gregtech.api.items.metaitem.stats.IItemBehaviour;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MinerDataItemBehavior implements IItemBehaviour, IDataItem {

    private final boolean requireDataBank;

    public MinerDataItemBehavior() {
        this.requireDataBank = false;
    }

    public MinerDataItemBehavior(boolean requireDataBank) {this.requireDataBank = requireDataBank;}

    @Override
    public boolean requireDataBank() { return requireDataBank; }

    @Override
    public void addInformation(@NotNull ItemStack itemStack, List<String> lines) {
        String researchId = DeepMinerRecipes.readResearchId(itemStack);
        if (researchId == null) return;
        lines.add(I18n.format("gcyl.research."+researchId+".tooltip"));
    }

}
