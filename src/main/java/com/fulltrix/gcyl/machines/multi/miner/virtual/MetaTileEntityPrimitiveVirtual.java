package com.fulltrix.gcyl.machines.multi.miner.virtual;

import com.fulltrix.gcyl.api.multi.abilities.GCYLAbilities;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.unification.material.Materials;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.core.sound.GTSoundEvents;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

//TODO finish implementing
public class MetaTileEntityPrimitiveVirtual extends MetaTileEntityVirtualMiner {

    private final ObjectList<Integer> layers = new ObjectArrayList<>(Collections.singletonList(0));

    public MetaTileEntityPrimitiveVirtual(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, 0);
    }

    @Override
    protected @NotNull BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("##F##","##F##","##F##","#####","#####")
                .aisle("#####","##F##","#####","#####","#####")
                .aisle("F#D#F","FFCFF","F#S#F","##X##","##X##")
                .aisle("#####","##F##","#####","#####","#####")
                .aisle("##F##","##F##","##F##","#####","#####")
                .where('S', selfPredicate())
                .where('X', getFramePredicate()
                        .or(abilities(MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.IMPORT_ITEMS).setMaxGlobalLimited(2)))
                .where('D', abilities(GCYLAbilities.DRILL_HOLDER))
                .where('C', states(getCasingState()))
                .where('F', getFramePredicate())
                .where('#', any())
                .build();
    }

    private IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID);
    }

    @NotNull
    private TraceabilityPredicate getFramePredicate() {
        return frames(Materials.Steel);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return Textures.SOLID_STEEL_CASING;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityPrimitiveVirtual(metaTileEntityId);
    }

    @Override
    public ObjectList<Integer> getLayers() {
        return this.layers;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public SoundEvent getSound() {
        return GTSoundEvents.DRILL_TOOL;
    }
}
