package com.fulltrix.gcyl.machines.multi;

import com.fulltrix.gcyl.api.multi.GCYLMultiblockRecipeLogic;
import com.fulltrix.gcyl.api.multi.GCYLRecipeMapMultiblockController;
import com.fulltrix.gcyl.api.pattern.TraceabilityPredicates;
import com.fulltrix.gcyl.client.ClientHandler;
import com.fulltrix.gcyl.blocks.GCYLMultiblockCasing2;
import com.fulltrix.gcyl.blocks.metal.MetalCasing2;
import com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps;
import gregicality.multiblocks.common.GCYMConfigHolder;
import gregicality.multiblocks.common.metatileentities.multiblockpart.MetaTileEntityTieredHatch;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

import static com.fulltrix.gcyl.api.pattern.TraceabilityPredicates.advFusionCoils;
import static com.fulltrix.gcyl.client.ClientHandler.ENRICHED_NAQUADAH_ALLOY_CASING;
import static com.fulltrix.gcyl.blocks.GCYLMetaBlocks.METAL_CASING_2;
import static com.fulltrix.gcyl.blocks.GCYLMetaBlocks.MULTIBLOCK_CASING2;

public class MetaTileEntityStellarForge extends GCYLRecipeMapMultiblockController {

    private long maxVoltage;

    public MetaTileEntityStellarForge(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GCYLRecipeMaps.STELLAR_FORGE_RECIPES, false);
        this.recipeMapWorkable = new GCYLMultiblockRecipeLogic(this, false) {
            @Override
            public long getMaxVoltage() {
                return GCYMConfigHolder.globalMultiblocks.enableTieredCasings ? maxVoltage : super.getMaxVoltage();
            }
        };
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gcyl.multiblock.stellar_forge.description"));
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
                .where('M', TraceabilityPredicates.tieredHatchPredicate())
                .where('C', states(getCasingState()).setMinGlobalLimited(130).or(autoAbilities(true, true, true, true, true, true, false)))
                .where('X', states(MULTIBLOCK_CASING2.getState(GCYLMultiblockCasing2.CasingType.STELLAR_CONTAINMENT)))
                .where('F', advFusionCoils())
                .where('S', selfPredicate())
                .where('#', any())
                .build();
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        int tier = context.getOrDefault("tiered_hatches", new ArrayList<MetaTileEntityTieredHatch>()).get(0).getTier() - 1;
        this.maxVoltage = 32L << tier * 2;
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.maxVoltage = 0;
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
