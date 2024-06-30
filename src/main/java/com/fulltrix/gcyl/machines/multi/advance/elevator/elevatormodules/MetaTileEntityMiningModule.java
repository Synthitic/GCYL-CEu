package com.fulltrix.gcyl.machines.multi.advance.elevator.elevatormodules;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import com.fulltrix.gcyl.client.ClientHandler;
import com.fulltrix.gcyl.machines.multi.advance.elevator.MetaTileEntityModuleBase;
import com.fulltrix.gcyl.machines.multi.advance.elevator.MetaTileEntityModuleRecipeBase;
import gregtech.api.capability.impl.ItemHandlerList;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.recipes.RecipeMap;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandlerModifiable;

public class MetaTileEntityMiningModule extends MetaTileEntityModuleBase {
    public MetaTileEntityMiningModule(ResourceLocation metaTileEntityId, int tier, int moduleTier, int minMotorTier) {
        super(metaTileEntityId, tier, moduleTier, minMotorTier);
    }

    private IItemHandlerModifiable inputInventory;
    private IItemHandlerModifiable outputInventory;

    @Override
    public void initializeAbilities() {
        this.inputInventory = new ItemHandlerList(getAbilities(MultiblockAbility.IMPORT_ITEMS));
        this.outputInventory = new ItemHandlerList(getAbilities(MultiblockAbility.EXPORT_ITEMS));
    }

    @Override
    protected TraceabilityPredicate abilities() {
        return abilities(MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityMiningModule(this.metaTileEntityId, this.tier, this.moduleTier, this.minMotorTier);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        for (EnumFacing renderSide : EnumFacing.HORIZONTALS) {
            if (renderSide == getFrontFacing()) {
                getFrontOverlay().renderOrientedState(renderState, translation, pipeline, getFrontFacing(), this.isActive(),
                        true);
            } else
                ClientHandler.MINING_MODULE_OVERLAY.renderSided(renderSide, renderState, translation, pipeline);
        }
    }
}
