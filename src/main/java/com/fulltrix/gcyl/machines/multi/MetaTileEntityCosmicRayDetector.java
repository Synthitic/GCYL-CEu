package com.fulltrix.gcyl.machines.multi;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import com.fulltrix.gcyl.api.multi.GCYLComputationRecipeLogic;
import com.fulltrix.gcyl.api.multi.GCYLRecipeMapMultiblockController;
import com.fulltrix.gcyl.item.GCYLMetaBlocks;
import com.fulltrix.gcyl.item.fusion.GCYLFusionCoils;
import com.fulltrix.gcyl.machines.multi.miner.MetaTileEntityLaserMiner;
import com.fulltrix.gcyl.materials.GCYLMaterials;
import com.fulltrix.gcyl.client.ClientHandler;
import com.fulltrix.gcyl.item.metal.MetalCasing2;
import com.google.common.collect.Lists;
import gregtech.api.GTValues;
import gregtech.api.capability.*;
import gregtech.api.capability.impl.ComputationRecipeLogic;
import gregtech.api.capability.impl.EnergyContainerList;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.recipeproperties.IRecipePropertyStorage;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.blocks.BlockFusionCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.core.sound.GTSoundEvents;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

import static com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps.COSMIC_RAY_DETECTOR_RECIPES;
import static com.fulltrix.gcyl.item.GCYLMetaBlocks.FUSION_COILS;
import static com.fulltrix.gcyl.materials.GCYLMaterials.BlackTitanium;
import static com.fulltrix.gcyl.client.ClientHandler.QUANTUM_CASING;
import static com.fulltrix.gcyl.item.GCYLMetaBlocks.METAL_CASING_2;

public class MetaTileEntityCosmicRayDetector extends GCYLRecipeMapMultiblockController implements IOpticalComputationReceiver {
    private IOpticalComputationProvider computationProvider;
    public MetaTileEntityCosmicRayDetector(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, COSMIC_RAY_DETECTOR_RECIPES, false);
        this.recipeMapWorkable = new CosmicRayRecipeLogic(this);
    }

    private boolean canSeeSky() {
        BlockPos result = this.getPos().up(5);
        Vec3i dirVec = this.getFrontFacing().getOpposite().getDirectionVec();
        Vec3i resVec = new Vec3i(dirVec.getX() * 3, 0, dirVec.getZ() * 3);
        result = result.add(resVec);
        return this.getWorld().canSeeSky(result);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "######xxx######", "###############")
                .aisle("###############", "###############", "###############", "###############", "###############", "###############", "###############", "######xxx######", "####xx###xx####", "###############")
                .aisle("###############", "###############", "###############", "###############", "###############", "###############", "#######x#######", "####xxx#xxx####", "###x#######x###", "###############")
                .aisle("######XXX######", "######XXX######", "######XXX######", "###############", "###############", "#######X#######", "#####xxxxx#####", "###xx#####xx###", "##x#########x##", "###############")
                .aisle("#####XXXXX#####", "#####X###X#####", "#####X###X#####", "######XXX######", "######XXX######", "#####XXXXX#####", "####xxxxxxx####", "##xx#######xx##", "#x###########x#", "###############")
                .aisle("####XXXXXXX####", "####X#####X####", "####X#####X####", "#####X###X#####", "#####X###X#####", "####XXxxxXX####", "###xxx###xxx###", "##x#########x##", "#x###########x#", "###############")
                .aisle("###XXXXXXXXX###", "###X###E###X###", "###X#######X###", "####X#####X####", "####X##F##X####", "####XxxxxxX####", "###xx#####xx###", "#xx#########xx#", "x#############x", "###############")
                .aisle("###XXXXXXXXX###", "###X##EcE##X###", "###X###c###X###", "####X##c##X####", "####X#FcF#X####", "###XXxxExxXX###", "##xxx##C##xxx##", "#x#####C#####x#", "x######C######x", "#######s#######")
                .aisle("###XXXXXXXXX###", "###X###E###X###", "###X#######X###", "####X#####X####", "####X##F##X####", "####XxxxxxX####", "###xx#####xx###", "#xx#########xx#", "x#############x", "###############")
                .aisle("####XXXXXXX####", "####X#####X####", "####X#####X####", "#####X###X#####", "#####X###X#####", "####XXxxxXX####", "###xxx###xxx###", "##x#########x##", "#x###########x#", "###############")
                .aisle("#####XXXXX#####", "#####X###X#####", "#####X###X#####", "######XXX######", "######XXX######", "#####XXXXX#####", "####xxxxxxx####", "##xx#######xx##", "#x###########x#", "###############")
                .aisle("######XXX######", "######XSX######", "######XXX######", "###############", "###############", "#######X#######", "#####xxxxx#####", "###xx#####xx###", "##x#########x##", "###############")
                .aisle("###############", "###############", "###############", "###############", "###############", "###############", "#######x#######", "####xxx#xxx####", "###x#######x###", "###############")
                .aisle("###############", "###############", "###############", "###############", "###############", "###############", "###############", "######xxx######", "####xx###xx####", "###############")
                .aisle("###############", "###############", "###############", "###############", "###############", "###############", "###############", "###############", "######xxx######", "###############")
                .where('S', selfPredicate())
                .where('X', states(getCasingState()).setMinGlobalLimited(145).or(autoAbilities(true, true, true, true, false, true, false)).or(abilities(MultiblockAbility.COMPUTATION_DATA_RECEPTION).setExactLimit(1)))
                .where('x', states(getSecondaryCasingState()))
                .where('C', frames(BlackTitanium))
                .where('c', states(GCYLMetaBlocks.FUSION_COILS.getState(GCYLFusionCoils.CasingType.ADV_FUSION_COIL_3)))
                .where('F', tieredCasing())
                .where('E', tieredCasing())
                .where('s', states(GCYLMetaBlocks.FUSION_COILS.getState(GCYLFusionCoils.CasingType.ADV_FUSION_COIL_3)))
                .where('#', any())
                .build();
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        List<IOpticalComputationHatch> providers = getAbilities(MultiblockAbility.COMPUTATION_DATA_RECEPTION);
        if (providers != null && providers.size() >= 1) {
            computationProvider = providers.get(0);
        }

        if (computationProvider == null) {
            invalidateStructure();
        }
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
        if (this.isStructureFormed()) {
            if (!canSeeSky())
                textList.add(new TextComponentTranslation("gcyl.multiblock.cosmic_ray_detector.tooltip.1")
                        .setStyle(new Style().setColor(TextFormatting.RED)));
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gcyl.multiblock.cosmic_ray_detector.tooltip.2"));
        tooltip.add(I18n.format("gcyl.multiblock.cosmic_ray_detector.tooltip.3"));
        tooltip.add(I18n.format("gcyl.multiblock.cosmic_ray_detector.tooltip.4"));
        tooltip.add(I18n.format("gcyl.multiblock.cosmic_ray_detector.tooltip.5"));
    }


    private IBlockState getCasingState() {
        return METAL_CASING_2.getState(MetalCasing2.CasingType.QUANTUM);
    }

    private IBlockState getSecondaryCasingState() {
        return METAL_CASING_2.getState(MetalCasing2.CasingType.TRITANIUM);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return QUANTUM_CASING;
    }


    @SideOnly(Side.CLIENT)
    @Override
    protected @NotNull ICubeRenderer getFrontOverlay() {
        return ClientHandler.FUSION_REACTOR_OVERLAY;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityCosmicRayDetector(metaTileEntityId);
    }

    @Override
    public IOpticalComputationProvider getComputationProvider() {
        return computationProvider;
    }

    private class CosmicRayRecipeLogic extends GCYLComputationRecipeLogic {
        public CosmicRayRecipeLogic(RecipeMapMultiblockController metaTileEntity) {
            super(metaTileEntity, ComputationType.STEADY);
        }

        @NotNull
        @Override
        public MetaTileEntityCosmicRayDetector getMetaTileEntity() {
            return (MetaTileEntityCosmicRayDetector) super.getMetaTileEntity();
        }

        @Override
        public boolean checkRecipe(@NotNull Recipe recipe) {
            if (!super.checkRecipe(recipe))
                return false;

            return getMetaTileEntity().canSeeSky();
        }

        @Override
        protected void modifyOverclockPre(int @NotNull [] values, @NotNull IRecipePropertyStorage storage) {
            super.modifyOverclockPre(values, storage);

            int duration = Math.max(1,values[1] - getMetaTileEntity().getPos().getY());

            values[1] = duration;
        }
    }
}
