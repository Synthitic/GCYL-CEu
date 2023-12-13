package com.fulltrix.tjfcore.machines.multi;

import com.fulltrix.tjfcore.client.ClientHandler;
import com.fulltrix.tjfcore.item.TJFMetaBlocks;
import com.fulltrix.tjfcore.item.TJFMultiblockCasing2;
import com.fulltrix.tjfcore.item.fusion.TJFFusionCasing;
import com.fulltrix.tjfcore.item.metal.MetalCasing2;
import com.fulltrix.tjfcore.recipes.TJFRecipeMaps;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

import javax.annotation.Nonnull;
import java.util.List;

import static com.fulltrix.tjfcore.client.ClientHandler.ENRICHED_NAQUADAH_ALLOY_CASING;
import static com.fulltrix.tjfcore.item.TJFMetaBlocks.METAL_CASING_2;
import static com.fulltrix.tjfcore.item.TJFMetaBlocks.MULTIBLOCK_CASING2;

public class MetaTileEntityStellarForge extends RecipeMapMultiblockController { //TODO implement tiering
    private long maxVoltage;

    public MetaTileEntityStellarForge(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, TJFRecipeMaps.STELLAR_FORGE_RECIPES);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("###############", "######CCC######", "######C#C######", "######C#C######", "######C#C######", "######C#C######", "######C#C######", "######CCC######", "###############")
                .aisle("######C#C######", "#####FFFFF#####", "###############", "###############", "###############", "###############", "###############", "#####FFFFF#####", "######C#C######")
                .aisle("######C#C######", "###FF#####FF###", "###############", "###############", "###############", "###############", "###############", "###FF#####FF###", "######C#C######")
                .aisle("######C#C######", "##F#########F##", "#####FFFFF#####", "###############", "###############", "###############", "#####FFFFF#####", "##F#########F##", "######C#C######")
                .aisle("######C#C######", "##F#########F##", "####F#XXX#F####", "######XXX######", "######XXX######", "######XXX######", "####F#XXX#F####", "##F#########F##", "######C#C######")
                .aisle("######C#C######", "#F###########F#", "###F#X###X#F###", "#####X###X#####", "#####X###X#####", "#####X###X#####", "###F#X###X#F###", "#F###########F#", "######C#C######")
                .aisle("#CCCCCCMCCCCCC#", "CF####XXX####FC", "C##FX#####XF##C", "C###X#####X###C", "C###X#####X###C", "C###X#####X###C", "C##FX#####XF##C", "CF####XXX####FC", "#CCCCCCMCCCCCC#")
                .aisle("######MMM######", "CF####XXX####FC", "###FX#####XF###", "####X#####X####", "####X#####X####", "####X#####X####", "###FX#####XF###", "CF####XXX####FC", "######MMM######")
                .aisle("#CCCCCCMCCCCCC#", "CF####XXX####FC", "C##FX#####XF##C", "C###X#####X###C", "C###X#####X###C", "C###X#####X###C", "C##FX#####XF##C", "CF####XXX####FC", "#CCCCCCMCCCCCC#")
                .aisle("######C#C######", "#F###########F#", "###F#X###X#F###", "#####X###X#####", "#####X###X#####", "#####X###X#####", "###F#X###X#F###", "#F###########F#", "######C#C######")
                .aisle("######C#C######", "##F#########F##", "####F#XXX#F####", "######XXX######", "######XXX######", "######XXX######", "####F#XXX#F####", "##F#########F##", "######C#C######")
                .aisle("######C#C######", "##F#########F##", "#####FFFFF#####", "###############", "###############", "###############", "#####FFFFF#####", "##F#########F##", "######C#C######")
                .aisle("######C#C######", "###FF#####FF###", "###############", "###############", "###############", "###############", "###############", "###FF#####FF###", "######C#C######")
                .aisle("######C#C######", "#####FFFFF#####", "###############", "###############", "###############", "###############", "###############", "#####FFFFF#####", "######C#C######")
                .aisle("###############", "######CSC######", "######C#C######", "######C#C######", "######C#C######", "######C#C######", "######C#C######", "######CCC######", "###############")
                .where('M', air())
                .where('C', states(getCasingState()).setMinGlobalLimited(130).or(autoAbilities(true, true, true, true, true, true, false)))
                .where('X', states(MULTIBLOCK_CASING2.getState(TJFMultiblockCasing2.CasingType.STELLAR_CONTAINMENT)))
                .where('F', states(TJFMetaBlocks.FUSION_CASING.getState(TJFFusionCasing.CasingType.FUSION_COIL_2)))
                .where('S', selfPredicate())
                .where('#', any())
                .build();
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.maxVoltage = 0;
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
        textList.add(new TextComponentTranslation("gregtech.multiblock.universal.framework", this.maxVoltage));
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return ENRICHED_NAQUADAH_ALLOY_CASING;
    }

    protected IBlockState getCasingState() {
        return METAL_CASING_2.getState(MetalCasing2.CasingType.ENRICHED_NAQUADAH_ALLOY);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityStellarForge(metaTileEntityId);
    }

    @Nonnull
    @Override
    protected OrientedOverlayRenderer getFrontOverlay() {
        return ClientHandler.QUBIT_COMPUTER_OVERLAY;
    }
}
