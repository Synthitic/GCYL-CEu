package com.fulltrix.gcyl.machines.multi.multiblockpart;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.ColourMultiplier;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import com.fulltrix.gcyl.api.multi.GCYLCleanroomType;
import com.fulltrix.gcyl.client.ClientHandler;
import com.fulltrix.gcyl.machines.GCYLTileEntities;
import com.google.common.collect.ImmutableSet;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.*;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.ConfigHolder;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityAutoMaintenanceHatch;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;

public class MetaTileEntityISO3CleaningMaintenanceHatch extends MetaTileEntityAutoMaintenanceHatch {

    protected static final Set<CleanroomType> CLEANED_TYPES = new ObjectOpenHashSet<>();

    static {
        CLEANED_TYPES.add(CleanroomType.CLEANROOM);
        CLEANED_TYPES.add(CleanroomType.STERILE_CLEANROOM);
        CLEANED_TYPES.add(GCYLCleanroomType.ISO3);
    }

    // must come after the static block
    private static final ICleanroomProvider DUMMY_CLEANROOM = DummyCleanroom.createForTypes(CLEANED_TYPES);

    public MetaTileEntityISO3CleaningMaintenanceHatch(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntitySterileCleaningMaintenanceHatch(metaTileEntityId);
    }

    @Override
    public void addToMultiBlock(MultiblockControllerBase controllerBase) {
        super.addToMultiBlock(controllerBase);
        if (controllerBase instanceof ICleanroomReceiver &&
                ((ICleanroomReceiver) controllerBase).getCleanroom() == null) {
            ((ICleanroomReceiver) controllerBase).setCleanroom(DUMMY_CLEANROOM);
        }
    }

    @Override
    public int getTier() {
        return GTValues.UEV;
    }

    @Override
    public ICubeRenderer getBaseTexture() {
        MultiblockControllerBase controller = getController();
        if (controller != null) {
            return this.hatchTexture = controller.getBaseTexture(this);
        } else if (this.hatchTexture != null) {
            if (hatchTexture != Textures.getInactiveTexture(hatchTexture)) {
                return this.hatchTexture = Textures.getInactiveTexture(hatchTexture);
            }
            return this.hatchTexture;
        } else {
            return Textures.VOLTAGE_CASINGS[getTier()];
        }
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        getBaseTexture().render(renderState, translation, ArrayUtils.add(pipeline,
                new ColourMultiplier(GTUtility.convertRGBtoOpaqueRGBA_CL(getPaintingColorForRendering()))));
        if (shouldRenderOverlay())
            ClientHandler.MAINTENANCE_OVERLAY_ISO3_CLEANING.renderSided(getFrontFacing(), renderState, translation, pipeline);
    }

    @Override
    public void getSubItems(CreativeTabs creativeTab, NonNullList<ItemStack> subItems) {
        if (ConfigHolder.machines.enableMaintenance) {
            super.getSubItems(creativeTab, subItems);
            // keeps things in order despite IDs being out of order, due to the Cleaning Hatch being added later
            subItems.add(GCYLTileEntities.ISO3_CLEANING_MAINTENANCE_HATCH.getStackForm());
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gregtech.machine.maintenance_hatch_cleanroom_auto.tooltip.1"));
        tooltip.add(I18n.format("gregtech.machine.maintenance_hatch.cleanroom_auto.tooltip.2"));
        for (CleanroomType type : CLEANED_TYPES) {
            tooltip.add(String.format("  %s%s", TextFormatting.LIGHT_PURPLE, I18n.format(type.getTranslationKey())));
        }
    }

    /**
     * Add an {@link CleanroomType} that is provided to multiblocks with this hatch
     *
     * @param type the type to add
     */
    @SuppressWarnings("unused")
    public static void addCleanroomType(@NotNull CleanroomType type) {
        CLEANED_TYPES.add(type);
    }

    /**
     * @return the {@link CleanroomType}s this hatch provides to multiblocks
     */
    @SuppressWarnings("unused")
    public static ImmutableSet<CleanroomType> getCleanroomTypes() {
        return ImmutableSet.copyOf(CLEANED_TYPES);
    }

}
