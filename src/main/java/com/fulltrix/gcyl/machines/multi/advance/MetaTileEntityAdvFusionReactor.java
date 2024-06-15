package com.fulltrix.gcyl.machines.multi.advance;

import com.fulltrix.gcyl.api.multi.GCYLRecipeMapMultiblockController;
import com.fulltrix.gcyl.client.ClientHandler;
import com.fulltrix.gcyl.item.GCYLMetaBlocks;
import com.fulltrix.gcyl.item.fusion.GCYLCryostatCasing;
import com.fulltrix.gcyl.item.fusion.GCYLDivertorCasing;
import com.fulltrix.gcyl.item.fusion.GCYLFusionCasing;
import com.fulltrix.gcyl.item.fusion.GCYLVacuumCasing;
import com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps;
import com.fulltrix.gcyl.recipes.recipeproperties.AdvFusionCoilProperty;
import com.fulltrix.gcyl.recipes.recipeproperties.AdvFusionEUReturnProperty;
import com.google.common.util.concurrent.AtomicDouble;
import gregtech.api.GTValues;
import gregtech.api.capability.GregtechDataCodes;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.impl.*;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.resources.TextureArea;
import gregtech.api.gui.widgets.ImageCycleButtonWidget;
import gregtech.api.gui.widgets.ImageWidget;
import gregtech.api.gui.widgets.IndicatorImageWidget;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.metatileentity.IFastRenderMetaTileEntity;
import gregtech.api.metatileentity.ITieredMetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.*;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.recipeproperties.FusionEUToStartProperty;
import gregtech.api.recipes.recipeproperties.IRecipePropertyStorage;
import gregtech.api.util.RelativeDirection;
import gregtech.api.util.TextComponentUtil;
import gregtech.api.util.TextFormattingUtil;
import gregtech.api.util.interpolate.Eases;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.IRenderSetup;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.shader.postprocessing.BloomEffect;
import gregtech.client.shader.postprocessing.BloomType;
import gregtech.client.utils.*;
import gregtech.common.ConfigHolder;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

import java.util.Arrays;
import java.util.List;
import java.util.function.DoubleSupplier;

import static com.fulltrix.gcyl.materials.GCYLMaterials.*;

public class MetaTileEntityAdvFusionReactor extends GCYLRecipeMapMultiblockController implements ITieredMetaTileEntity, IFastRenderMetaTileEntity, IBloomEffect {

    //TODO make this better. make coils independent of tier. fix bloom. make it be able to run regular fusion recipes. make recipe cancel if energy runs out

    private static final List<Fluid> HOT = Arrays.asList(SupercriticalSteam.getFluid(),
            SupercriticalSodiumPotassiumAlloy.getFluid(),
            SupercriticalFLiNaK.getFluid(), SupercriticalFLiBe.getFluid(), SupercriticalLeadBismuthEutectic.getFluid());
    private final FusionProgressSupplier progressBarSupplier;

    protected static final int NO_COLOR = 0;
    private final int tier;
    private int coilTier;
    private int vacuumTier;
    private int divertorTier;
    private boolean canWork;
    private int fusionRingColor = NO_COLOR;
    @SideOnly(Side.CLIENT)
    private boolean registeredBloomRenderTicket;
    private EnergyContainerList inputEnergyContainers;
    private long heat = 0;


    public MetaTileEntityAdvFusionReactor(ResourceLocation metaTileEntityId, int tier) {
        super(metaTileEntityId, new RecipeMap[] {GCYLRecipeMaps.ADV_FUSION_RECIPES, RecipeMaps.FUSION_RECIPES}, false);
        this.recipeMapWorkable = new AdvFusionRecipeLogic(this);
        this.tier = tier;
        this.energyContainer = new EnergyContainerHandler(this, 0, 0, 0, 0, 0) {

            @NotNull
            @Override
            public String getName() {
                return GregtechDataCodes.FUSION_REACTOR_ENERGY_CONTAINER_TRAIT;
            }
        };
        this.progressBarSupplier = new MetaTileEntityAdvFusionReactor.FusionProgressSupplier();
    }
    @Override
    public boolean isTiered() {
        return false;
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("###############", "###############", "#####ccCcc#####", "#####ccCcc#####", "###############", "###############")
                .aisle("###############", "#######C#######", "###ccvvvvvcc###", "###ccvvvvvcc###", "#######C#######", "###############")
                .aisle("#######C#######", "##C##ddddd##C##", "##Cvv#####vvC##", "##Cvv#####vvC##", "##C##bbbbb##C##", "#######C#######")
                .aisle("###C###C###C###", "###ddddddddd###", "#cv#########vc#", "#cv#########vc#", "###bbbbbbbbb###", "###C###C###C###")
                .aisle("####C#####C####", "###ddd#C#ddd###", "#cv###vvv###vc#", "#cv###vvv###vc#", "###bbb#C#bbb###", "####C#####C####")
                .aisle("###############", "##dddC###Cddd##", "cv###v#C#v###vc", "cv###v#C#v###vc", "##bbbC###Cbbb##", "###############")
                .aisle("######XXX######", "##dd##CCC##dd##", "cv##v#CCC#v##vc", "cv##v#CCC#v##vc", "##bb##CCC##bb##", "######XXX######")
                .aisle("##CC##XXX##CC##", "#CddC#CCC#CddC#", "Cv##vCCCCCv##vC", "Cv##vCCCCCv##vC", "#CbbC#CCC#CbbC#", "##CC##XXX##CC##")
                .aisle("######XXX######", "##dd##CCC##dd##", "cv##v#CCC#v##vc", "cv##v#CCC#v##vc", "##bb##CCC##bb##", "######XXX######")
                .aisle("###############", "##dddC###Cddd##", "cv###v#C#v###vc", "cv###v#C#v###vc", "##bbbC###Cbbb##", "###############")
                .aisle("####C#####C####", "###ddd#C#ddd###", "#cv###vvv###vc#", "#cv###vvv###vc#", "###bbb#C#bbb###", "####C#####C####")
                .aisle("###C###C###C###", "###ddddddddd###", "#cv#########vc#", "#cv#########vc#", "###bbbbbbbbb###", "###C###C###C###")
                .aisle("#######C#######", "##C##ddddd##C##", "##Cvv#####vvC##", "##Cvv#####vvC##", "##C##bbbbb##C##", "#######C#######")
                .aisle("###############", "#######S#######", "###ccvvvvvcc###", "###ccvvvvvcc###", "#######C#######", "###############")
                .aisle("###############", "###############", "#####ccCcc#####", "#####ccCcc#####", "###############", "###############")
                .where('S', selfPredicate())
                .where('#', any())
                .where('C', states(getCoilState()))
                .where('X', states(getCasingState()))
                .where('d', states(getDivertorState()).or(metaTileEntities(Arrays
                        .stream(MetaTileEntities.ENERGY_INPUT_HATCH)
                        .filter(mte -> mte != null && tier <= mte.getTier() && mte.getTier() <= GTValues.OpV)
                        .toArray(MetaTileEntity[]::new))
                        .setMinGlobalLimited(1).setPreviewCount(16)))
                .where('v', states(getVacuumState()))
                .where('c', states(getCryostatState()))
                .where('b', states(GCYLMetaBlocks.FUSION_CASING.getState(GCYLFusionCasing.CasingType.FUSION_BLANKET))
                        .or(abilities(MultiblockAbility.IMPORT_FLUIDS).setMinGlobalLimited(3).setMaxGlobalLimited(3).setPreviewCount(3))
                        .or(abilities(MultiblockAbility.EXPORT_FLUIDS).setMaxGlobalLimited(3).setPreviewCount(3)))
                .where('E', metaTileEntities(Arrays
                        .stream(MetaTileEntities.ENERGY_INPUT_HATCH)
                        .filter(mte -> mte != null && tier <= mte.getTier() && mte.getTier() <= GTValues.OpV)
                        .toArray(MetaTileEntity[]::new))
                        .setMinGlobalLimited(1).setPreviewCount(16))
                .where('I', states(getCasingState()).or(abilities(MultiblockAbility.IMPORT_FLUIDS).setMinGlobalLimited(3)))
                .where('i', states(getCasingState()).or(abilities(MultiblockAbility.EXPORT_FLUIDS)))
                .build();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        if (this.recipeMapWorkable.isActive()) {
            return ClientHandler.ADVANCED_FUSION_ACTIVE;
        } else {
            return ClientHandler.ADVANCED_FUSION;
        }
    }

    @Override
    public boolean hasMaintenanceMechanics() {
        return false;
    }

    private IBlockState getCasingState() {
        return GCYLMetaBlocks.FUSION_CASING.getState(GCYLFusionCasing.CasingType.ADV_FUSION_CASING);
    }

    @Override
    protected void updateFormedValid() {
        if (this.inputEnergyContainers.getEnergyStored() > 0) {
            long energyAdded = this.energyContainer.addEnergy(this.inputEnergyContainers.getEnergyStored());
            if (energyAdded > 0) this.inputEnergyContainers.removeEnergy(energyAdded);
        }
        super.updateFormedValid();
        if (recipeMapWorkable.isWorking() && fusionRingColor == NO_COLOR) {
            if (recipeMapWorkable.getPreviousRecipe() != null &&
                    !recipeMapWorkable.getPreviousRecipe().getFluidOutputs().isEmpty()) {
                setFusionRingColor(0xFF000000 |
                        recipeMapWorkable.getPreviousRecipe().getFluidOutputs().get(0).getFluid().getColor());
            }
        } else if (!recipeMapWorkable.isWorking() && isStructureFormed()) {
            setFusionRingColor(NO_COLOR);
        }
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeVarInt(this.fusionRingColor);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.fusionRingColor = buf.readVarInt();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, @NotNull List<String> tooltip,
                               boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(
                I18n.format("gregtech.machine.fusion_reactor.capacity", calculateEnergyStorageFactor(16) / 1000000L));
        tooltip.add(I18n.format("gcyl.machine.adv_fusion_reactor.tooltip.1"));
        tooltip.add(I18n.format("gcyl.machine.adv_fusion_reactor.tooltip.2"));
        tooltip.add(I18n.format("gcyl.machine.adv_fusion_reactor.tooltip.3"));
        tooltip.add(I18n.format("gcyl.machine.adv_fusion_reactor.tooltip.4"));
    }

    private IBlockState getCoilState() {
        if (tier == GTValues.UHV)
            return GCYLMetaBlocks.FUSION_CASING.getState(GCYLFusionCasing.CasingType.ADV_FUSION_COIL_1);
        if (tier == GTValues.UEV)
            return GCYLMetaBlocks.FUSION_CASING.getState(GCYLFusionCasing.CasingType.ADV_FUSION_COIL_2);
        if (tier == GTValues.UIV)
            return GCYLMetaBlocks.FUSION_CASING.getState(GCYLFusionCasing.CasingType.ADV_FUSION_COIL_3);
        if (tier == GTValues.UXV)
            return GCYLMetaBlocks.FUSION_CASING.getState(GCYLFusionCasing.CasingType.ADV_FUSION_COIL_4);

        return GCYLMetaBlocks.FUSION_CASING.getState(GCYLFusionCasing.CasingType.ADV_FUSION_COIL_5);
    }

    private IBlockState getCryostatState() {
        if (tier == GTValues.UHV)
            return GCYLMetaBlocks.CRYOSTAT_CASING.getState(GCYLCryostatCasing.CasingType.CRYOSTAT_1);
        if (tier == GTValues.UEV)
            return GCYLMetaBlocks.CRYOSTAT_CASING.getState(GCYLCryostatCasing.CasingType.CRYOSTAT_2);
        if (tier == GTValues.UIV)
            return GCYLMetaBlocks.CRYOSTAT_CASING.getState(GCYLCryostatCasing.CasingType.CRYOSTAT_3);
        if (tier == GTValues.UXV)
            return GCYLMetaBlocks.CRYOSTAT_CASING.getState(GCYLCryostatCasing.CasingType.CRYOSTAT_4);

        return GCYLMetaBlocks.CRYOSTAT_CASING.getState(GCYLCryostatCasing.CasingType.CRYOSTAT_5);
    }

    private IBlockState getDivertorState() {
        if (tier == GTValues.UHV)
            return GCYLMetaBlocks.DIVERTOR_CASING.getState(GCYLDivertorCasing.CasingType.DIVERTOR_1);
        if (tier == GTValues.UEV)
            return GCYLMetaBlocks.DIVERTOR_CASING.getState(GCYLDivertorCasing.CasingType.DIVERTOR_2);
        if (tier == GTValues.UIV)
            return GCYLMetaBlocks.DIVERTOR_CASING.getState(GCYLDivertorCasing.CasingType.DIVERTOR_3);
        if (tier == GTValues.UXV)
            return GCYLMetaBlocks.DIVERTOR_CASING.getState(GCYLDivertorCasing.CasingType.DIVERTOR_4);

        return GCYLMetaBlocks.DIVERTOR_CASING.getState(GCYLDivertorCasing.CasingType.DIVERTOR_5);
    }




    /*
    public static TraceabilityPredicate<BlockWorldState> cryostatPredicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof TJFCryostatCasing)) {
                return false;
            } else {
                TJFCryostatCasing cryostatCasing = (TJFCryostatCasing) blockState.getBlock();
                TJFCryostatCasing.CasingType tieredCasingType = cryostatCasing.getState(blockState);
                TJFCryostatCasing.CasingType currentCasing = blockWorldState.getMatchContext().getOrPut("Cryostat", tieredCasingType);
                return currentCasing.getName().equals(tieredCasingType.getName());
            }
        };
    }

    public static Predicate<BlockWorldState> divertorPredicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof TJFDivertorCasing)) {
                return false;
            } else {
                TJFDivertorCasing divertorCasing = (TJFDivertorCasing) blockState.getBlock();
                TJFDivertorCasing.CasingType tieredCasingType = divertorCasing.getState(blockState);
                TJFDivertorCasing.CasingType currentCasing = blockWorldState.getMatchContext().getOrPut("Divertor", tieredCasingType);
                return currentCasing.getName().equals(tieredCasingType.getName());
            }
        };
    }

    public static Predicate<BlockWorldState> vacuumPredicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof TJFVacuumCasing)) {
                return false;
            } else {
                TJFVacuumCasing vacuumCasing = (TJFVacuumCasing) blockState.getBlock();
                TJFVacuumCasing.CasingType tieredCasingType = vacuumCasing.getState(blockState);
                TJFVacuumCasing.CasingType currentCasing = blockWorldState.getMatchContext().getOrPut("Vacuum", tieredCasingType);
                return currentCasing.getName().equals(tieredCasingType.getName());
            }
        };
    }


    public static Predicate<BlockWorldState> coilPredicate() {
        return (blockWorldState) -> {
            IBlockState blockState = blockWorldState.getBlockState();
            if (!(blockState.getBlock() instanceof TJFFusionCasing)) {
                return false;
            } else {
                TJFFusionCasing coil = (TJFFusionCasing) blockState.getBlock();
                TJFFusionCasing.CasingType tieredCasingType = coil.getState(blockState);
                if (tieredCasingType.getName().startsWith("adv_fusion_coil")) {
                    TJFFusionCasing.CasingType currentCasing = blockWorldState.getMatchContext().getOrPut("Coil", tieredCasingType);
                    return currentCasing.getName().equals(tieredCasingType.getName());
                } else {
                    return false;
                }
            }
        };
    }

     */

    private IBlockState getVacuumState() {
        if (tier == GTValues.UHV)
            return GCYLMetaBlocks.VACUUM_CASING.getState(GCYLVacuumCasing.CasingType.VACUUM_1);
        if (tier == GTValues.UEV)
            return GCYLMetaBlocks.VACUUM_CASING.getState(GCYLVacuumCasing.CasingType.VACUUM_2);
        if (tier == GTValues.UIV)
            return GCYLMetaBlocks.VACUUM_CASING.getState(GCYLVacuumCasing.CasingType.VACUUM_3);
        if (tier == GTValues.UXV)
            return GCYLMetaBlocks.VACUUM_CASING.getState(GCYLVacuumCasing.CasingType.VACUUM_4);

        return GCYLMetaBlocks.VACUUM_CASING.getState(GCYLVacuumCasing.CasingType.VACUUM_5);
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        /*
        GCYLFusionCasing.CasingType coil = context.getOrDefault("Coil", GCYLFusionCasing.CasingType.ADV_FUSION_COIL_1);
        coilTier = Integer.parseInt(coil.getName().substring(coil.getName().length() - 1));
        vacuumTier = context.getOrDefault("Vacuum", GCYLVacuumCasing.CasingType.VACUUM_1).getTier();
        divertorTier = context.getOrDefault("Divertor", GCYLDivertorCasing.CasingType.DIVERTOR_1).getTier();
        int cryostatTier = context.getOrDefault("Cryostat", GCYLCryostatCasing.CasingType.CRYOSTAT_1).getTier();
        canWork = Math.min(Math.min(vacuumTier, divertorTier), cryostatTier) >= coilTier;
         */
        this.coilTier = this.tier - 8;
        long energyStored = this.energyContainer.getEnergyStored();
        this.initializeAbilities();
        ((EnergyContainerHandler) this.energyContainer).setEnergyStored(energyStored);
    }

    @Override
    protected void initializeAbilities() {
        this.inputInventory = new ItemHandlerList(getAbilities(MultiblockAbility.IMPORT_ITEMS));
        this.inputFluidInventory = new FluidTankList(true, getAbilities(MultiblockAbility.IMPORT_FLUIDS));
        this.outputInventory = new ItemHandlerList(getAbilities(MultiblockAbility.EXPORT_ITEMS));
        this.outputFluidInventory = new FluidTankList(true, getAbilities(MultiblockAbility.EXPORT_FLUIDS));
        List<IEnergyContainer> energyInputs = getAbilities(MultiblockAbility.INPUT_ENERGY);
        this.inputEnergyContainers = new EnergyContainerList(energyInputs);
        long euCapacity = calculateEnergyStorageFactor(energyInputs.size());
        this.energyContainer = new EnergyContainerHandler(this, euCapacity, GTValues.V[tier], 0, 0, 0) {

            @NotNull
            @Override
            public String getName() {
                return GregtechDataCodes.FUSION_REACTOR_ENERGY_CONTAINER_TRAIT;
            }
        };
    }

    private long calculateEnergyStorageFactor(int energyInputAmount) {
        return energyInputAmount * (long) Math.pow(2, tier - 6) * 10000000L;
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.coilTier = 0;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityAdvFusionReactor(metaTileEntityId, tier);
    }

    @Override
    protected @NotNull ICubeRenderer getFrontOverlay() {
        return Textures.FUSION_REACTOR_OVERLAY;
    }

    @Override
    public int getTier() {
        return this.tier;
    }

    @Override
    protected ModularUI.Builder createUITemplate(EntityPlayer entityPlayer) {
        // Background
        ModularUI.Builder builder = ModularUI.builder(GuiTextures.BACKGROUND, 198, 236);

        // Display
        builder.image(4, 4, 190, 138, GuiTextures.DISPLAY);

        // Energy Bar
        builder.widget(new ProgressWidget(
                () -> energyContainer.getEnergyCapacity() > 0 ?
                        1.0 * energyContainer.getEnergyStored() / energyContainer.getEnergyCapacity() : 0,
                4, 144, 94, 7,
                GuiTextures.PROGRESS_BAR_FUSION_ENERGY, ProgressWidget.MoveType.HORIZONTAL)
                .setHoverTextConsumer(this::addEnergyBarHoverText));

        // Heat Bar
        builder.widget(new ProgressWidget(
                () -> energyContainer.getEnergyCapacity() > 0 ? 1.0 * heat / energyContainer.getEnergyCapacity() : 0,
                100, 144, 94, 7,
                GuiTextures.PROGRESS_BAR_FUSION_HEAT, ProgressWidget.MoveType.HORIZONTAL)
                .setHoverTextConsumer(this::addHeatBarHoverText));

        // Indicator Widget
        builder.widget(new IndicatorImageWidget(174, 122, 17, 17, getLogo())
                .setWarningStatus(getWarningLogo(), this::addWarningText)
                .setErrorStatus(getErrorLogo(), this::addErrorText));

        // Title
        /*
        if (tier == GTValues.UHV) {
            // MK1
            builder.widget(new ImageWidget(66, 9, 67, 12, GuiTextures.FUSION_REACTOR_MK1_TITLE).setIgnoreColor(true));
        } else if (tier == GTValues.UEV) {
            // MK2
            builder.widget(new ImageWidget(65, 9, 69, 12, GuiTextures.FUSION_REACTOR_MK2_TITLE).setIgnoreColor(true));
        } else {
            // MK3
            builder.widget(new ImageWidget(64, 9, 71, 12, GuiTextures.FUSION_REACTOR_MK3_TITLE).setIgnoreColor(true));
        }

         */

        // Fusion Diagram + Progress Bar
        builder.widget(new ImageWidget(55, 24, 89, 101, GuiTextures.FUSION_REACTOR_DIAGRAM).setIgnoreColor(true));
        builder.widget(MetaTileEntityAdvFusionReactor.FusionProgressSupplier.Type.BOTTOM_LEFT.getWidget(this));
        builder.widget(MetaTileEntityAdvFusionReactor.FusionProgressSupplier.Type.TOP_LEFT.getWidget(this));
        builder.widget(MetaTileEntityAdvFusionReactor.FusionProgressSupplier.Type.TOP_RIGHT.getWidget(this));
        builder.widget(MetaTileEntityAdvFusionReactor.FusionProgressSupplier.Type.BOTTOM_RIGHT.getWidget(this));

        // Fusion Legend
        builder.widget(new ImageWidget(7, 98, 108, 41, GuiTextures.FUSION_REACTOR_LEGEND).setIgnoreColor(true));

        // Power Button + Detail
        builder.widget(new ImageCycleButtonWidget(173, 211, 18, 18, GuiTextures.BUTTON_POWER,
                recipeMapWorkable::isWorkingEnabled, recipeMapWorkable::setWorkingEnabled));
        builder.widget(new ImageWidget(173, 229, 18, 6, GuiTextures.BUTTON_POWER_DETAIL));

        // Voiding Mode Button
        builder.widget(new ImageCycleButtonWidget(173, 189, 18, 18, GuiTextures.BUTTON_VOID_MULTIBLOCK,
                4, this::getVoidingMode, this::setVoidingMode)
                .setTooltipHoverString(MultiblockWithDisplayBase::getVoidingModeTooltip));

        // Distinct Buses Unavailable Image
        builder.widget(new ImageWidget(173, 171, 18, 18, GuiTextures.BUTTON_NO_DISTINCT_BUSES)
                .setTooltip("gregtech.multiblock.universal.distinct_not_supported"));

        // Flex Unavailable Image
        builder.widget(getFlexButton(173, 153, 18, 18));

        // Player Inventory
        builder.bindPlayerInventory(entityPlayer.inventory, 153);
        return builder;
    }

    private void addEnergyBarHoverText(List<ITextComponent> hoverList) {
        ITextComponent energyInfo = TextComponentUtil.stringWithColor(
                TextFormatting.AQUA,
                TextFormattingUtil.formatNumbers(energyContainer.getEnergyStored()) + " / " +
                        TextFormattingUtil.formatNumbers(energyContainer.getEnergyCapacity()) + " EU");
        hoverList.add(TextComponentUtil.translationWithColor(
                TextFormatting.GRAY,
                "gregtech.multiblock.energy_stored",
                energyInfo));
    }

    private void addHeatBarHoverText(List<ITextComponent> hoverList) {
        ITextComponent heatInfo = TextComponentUtil.stringWithColor(
                TextFormatting.RED,
                TextFormattingUtil.formatNumbers(heat) + " / " +
                        TextFormattingUtil.formatNumbers(energyContainer.getEnergyCapacity()));
        hoverList.add(TextComponentUtil.translationWithColor(
                TextFormatting.GRAY,
                "gregtech.multiblock.fusion_reactor.heat",
                heatInfo));
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        if (dataId == GregtechDataCodes.UPDATE_COLOR) {
            this.fusionRingColor = buf.readVarInt();
        } else {
            super.receiveCustomData(dataId, buf);
        }
    }

    protected int getFusionRingColor() {
        return this.fusionRingColor;
    }

    protected boolean hasFusionRingColor() {
        return this.fusionRingColor != NO_COLOR;
    }

    protected void setFusionRingColor(int fusionRingColor) {
        if (this.fusionRingColor != fusionRingColor) {
            this.fusionRingColor = fusionRingColor;
            writeCustomData(GregtechDataCodes.UPDATE_COLOR, buf -> buf.writeVarInt(fusionRingColor));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderMetaTileEntity(double x, double y, double z, float partialTicks) {
        if (this.hasFusionRingColor() && !this.registeredBloomRenderTicket) {
            this.registeredBloomRenderTicket = true;
            BloomEffectUtil.registerBloomRender(FusionBloomSetup.INSTANCE, getBloomType(), this, this);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderBloomEffect(@NotNull BufferBuilder buffer, @NotNull EffectRenderContext context) {
        if (!this.hasFusionRingColor()) return;
        int color = RenderUtil.interpolateColor(this.getFusionRingColor(), -1, Eases.QUAD_IN.getInterpolation(
                Math.abs((Math.abs(getOffsetTimer() % 50) + context.partialTicks()) - 25) / 25));
        float a = (float) (color >> 24 & 255) / 255.0F;
        float r = (float) (color >> 16 & 255) / 255.0F;
        float g = (float) (color >> 8 & 255) / 255.0F;
        float b = (float) (color & 255) / 255.0F;
        EnumFacing relativeBack = RelativeDirection.BACK.getRelativeFacing(getFrontFacing(), getUpwardsFacing(),
                isFlipped());
        EnumFacing.Axis axis = RelativeDirection.UP.getRelativeFacing(getFrontFacing(), getUpwardsFacing(), isFlipped())
                .getAxis();

        buffer.begin(GL11.GL_QUAD_STRIP, DefaultVertexFormats.POSITION_COLOR);
        RenderBufferHelper.renderRing(buffer,
                getPos().getX() - context.cameraX() + relativeBack.getXOffset() * 7 + 0.5,
                getPos().getY() - context.cameraY() + relativeBack.getYOffset() * 7 + 2.0,
                getPos().getZ() - context.cameraZ() + relativeBack.getZOffset() * 7 + 1.5,
                4.5, 0.6, 10, 20,
                r, g, b, a, axis);
        Tessellator.getInstance().draw();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldRenderBloomEffect(@NotNull EffectRenderContext context) {
        return this.hasFusionRingColor();
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        EnumFacing relativeRight = RelativeDirection.RIGHT.getRelativeFacing(getFrontFacing(), getUpwardsFacing(),
                isFlipped());
        EnumFacing relativeBack = RelativeDirection.BACK.getRelativeFacing(getFrontFacing(), getUpwardsFacing(),
                isFlipped());

        return new AxisAlignedBB(
                this.getPos().offset(relativeBack).offset(relativeRight, 6),
                this.getPos().offset(relativeBack, 13).offset(relativeRight.getOpposite(), 6));
    }

    @Override
    public boolean shouldRenderInPass(int pass) {
        return pass == 0;
    }

    @Override
    public boolean isGlobalRenderer() {
        return true;
    }

    private static BloomType getBloomType() {
        ConfigHolder.FusionBloom fusionBloom = ConfigHolder.client.shader.fusionBloom;
        return BloomType.fromValue(fusionBloom.useShader ? fusionBloom.bloomStyle : -1);
    }

    @SideOnly(Side.CLIENT)
    private static final class FusionBloomSetup implements IRenderSetup {

        private static final MetaTileEntityAdvFusionReactor.FusionBloomSetup INSTANCE = new MetaTileEntityAdvFusionReactor.FusionBloomSetup();

        float lastBrightnessX;
        float lastBrightnessY;

        @Override
        public void preDraw(@NotNull BufferBuilder buffer) {
            BloomEffect.strength = (float) ConfigHolder.client.shader.fusionBloom.strength;
            BloomEffect.baseBrightness = (float) ConfigHolder.client.shader.fusionBloom.baseBrightness;
            BloomEffect.highBrightnessThreshold = (float) ConfigHolder.client.shader.fusionBloom.highBrightnessThreshold;
            BloomEffect.lowBrightnessThreshold = (float) ConfigHolder.client.shader.fusionBloom.lowBrightnessThreshold;
            BloomEffect.step = 1;

            lastBrightnessX = OpenGlHelper.lastBrightnessX;
            lastBrightnessY = OpenGlHelper.lastBrightnessY;
            GlStateManager.color(1, 1, 1, 1);
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
            GlStateManager.disableTexture2D();
        }

        @Override
        public void postDraw(@NotNull BufferBuilder buffer) {
            GlStateManager.enableTexture2D();
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lastBrightnessX, lastBrightnessY);
        }
    }

    private static class FusionProgressSupplier {

        private final AtomicDouble tracker = new AtomicDouble(0.0);
        private final ProgressWidget.TimedProgressSupplier bottomLeft;
        private final DoubleSupplier topLeft;
        private final DoubleSupplier topRight;
        private final DoubleSupplier bottomRight;

        public FusionProgressSupplier() {
            // Bottom Left, fill on [0, 0.25)
            bottomLeft = new ProgressWidget.TimedProgressSupplier(200, 164, false) {

                @Override
                public double getAsDouble() {
                    double val = super.getAsDouble();
                    tracker.set(val);
                    if (val >= 0.25) {
                        return 1;
                    }
                    return 4 * val;
                }

                @Override
                public void resetCountdown() {
                    super.resetCountdown();
                    tracker.set(0);
                }
            };

            // Top Left, fill on [0.25, 0.5)
            topLeft = () -> {
                double val = tracker.get();
                if (val < 0.25) {
                    return 0;
                } else if (val >= 0.5) {
                    return 1;
                }
                return 4 * (val - 0.25);
            };

            // Top Right, fill on [0.5, 0.75)
            topRight = () -> {
                double val = tracker.get();
                if (val < 0.5) {
                    return 0;
                } else if (val >= 0.75) {
                    return 1;
                }
                return 4 * (val - 0.5);
            };

            // Bottom Right, fill on [0.75, 1.0]
            bottomRight = () -> {
                double val = tracker.get();
                if (val < 0.75) {
                    return 0;
                } else if (val >= 1) {
                    return 1;
                }
                return 4 * (val - 0.75);
            };
        }

        public void resetCountdown() {
            bottomLeft.resetCountdown();
        }

        public DoubleSupplier getSupplier(Type type) {
            return switch (type) {
                case BOTTOM_LEFT -> bottomLeft;
                case TOP_LEFT -> topLeft;
                case TOP_RIGHT -> topRight;
                case BOTTOM_RIGHT -> bottomRight;
            };
        }

        private enum Type {

            BOTTOM_LEFT(
                    61, 66, 35, 41,
                    GuiTextures.PROGRESS_BAR_FUSION_REACTOR_DIAGRAM_BL, ProgressWidget.MoveType.VERTICAL),
            TOP_LEFT(
                    61, 30, 41, 35,
                    GuiTextures.PROGRESS_BAR_FUSION_REACTOR_DIAGRAM_TL, ProgressWidget.MoveType.HORIZONTAL),
            TOP_RIGHT(
                    103, 30, 35, 41,
                    GuiTextures.PROGRESS_BAR_FUSION_REACTOR_DIAGRAM_TR, ProgressWidget.MoveType.VERTICAL_DOWNWARDS),
            BOTTOM_RIGHT(
                    97, 72, 41, 35,
                    GuiTextures.PROGRESS_BAR_FUSION_REACTOR_DIAGRAM_BR, ProgressWidget.MoveType.HORIZONTAL_BACKWARDS);

            private final int x;
            private final int y;
            private final int width;
            private final int height;
            private final TextureArea texture;
            private final ProgressWidget.MoveType moveType;

            Type(int x, int y, int width, int height, TextureArea texture, ProgressWidget.MoveType moveType) {
                this.x = x;
                this.y = y;
                this.width = width;
                this.height = height;
                this.texture = texture;
                this.moveType = moveType;
            }

            public ProgressWidget getWidget(MetaTileEntityAdvFusionReactor instance) {
                return new ProgressWidget(
                        () -> instance.recipeMapWorkable.isActive() ?
                                instance.progressBarSupplier.getSupplier(this).getAsDouble() : 0,
                        x, y, width, height, texture, moveType)
                        .setIgnoreColor(true)
                        .setHoverTextConsumer(
                                tl -> MultiblockDisplayText.builder(tl, instance.isStructureFormed())
                                        .setWorkingStatus(instance.recipeMapWorkable.isWorkingEnabled(),
                                                instance.recipeMapWorkable.isActive())
                                        .addWorkingStatusLine());
            }
        }
    }

    public class AdvFusionRecipeLogic extends MultiblockRecipeLogic {


        public AdvFusionRecipeLogic(MetaTileEntityAdvFusionReactor tileEntity) {
            super(tileEntity);
        }

        @Override
        public void updateWorkable() {
            super.updateWorkable();
            if (heat > 0) {
                if (!isActive || !workingEnabled || (hasNotEnoughEnergy && progressTime == 0)) {
                    heat = heat <= 10000 ? 0 : (heat - 10000);
                }
            }
        }

        @Override
        protected double getOverclockingDurationDivisor() {return 2.0;}

        @Override
        protected double getOverclockingVoltageMultiplier() {
            return 2.0D;
        }

        @Override
        public long getMaxVoltage() {
            return Math.min(GTValues.V[tier], super.getMaxVoltage());
        }

        @Override
        public boolean checkRecipe(@NotNull Recipe recipe) {
            if (!super.checkRecipe(recipe))
                return false;

            // if the reactor is not able to hold enough energy for it, do not run the recipe
            if (recipe.getProperty(FusionEUToStartProperty.getInstance(), 0L) > energyContainer.getEnergyCapacity())
                return false;

            //do not run recipe if coil tier is too low
            if (recipe.getProperty(AdvFusionCoilProperty.getInstance(), 0) > coilTier)
                return false;

            long heatDiff = recipe.getProperty(FusionEUToStartProperty.getInstance(), 0L) - heat;
            // if the stored heat is >= required energy, recipe is okay to run
            if (heatDiff <= 0)
                return true;

            // if the remaining energy needed is more than stored, do not run
            if (energyContainer.getEnergyStored() < heatDiff)
                return false;

            // remove the energy needed
            energyContainer.removeEnergy(heatDiff);

            // increase the stored heat
            heat += heatDiff;
            return true;
        }

        @Override
        protected void modifyOverclockPre(int @NotNull [] values, @NotNull IRecipePropertyStorage storage) {
            super.modifyOverclockPre(values, storage);

            // Limit the number of OCs to the difference in fusion reactor MK.
            // I.e., a MK2 reactor can overclock a MK1 recipe once, and a
            // MK3 reactor can overclock a MK2 recipe once, or a MK1 recipe twice.
            long euToStart = storage.getRecipePropertyValue(FusionEUToStartProperty.getInstance(), 0L);
            int fusionTier = FusionEUToStartProperty.getFusionTier(euToStart);
            int coilTier = storage.getRecipePropertyValue(AdvFusionCoilProperty.getInstance(), 0);
            double euReturn = storage.getRecipePropertyValue(AdvFusionEUReturnProperty.getInstance(), 0);

            if(coilTier != 0) {
                if (fusionTier != 0) fusionTier = MetaTileEntityAdvFusionReactor.this.tier - fusionTier;
                coilTier = MetaTileEntityAdvFusionReactor.this.coilTier - coilTier;

                values[0] = (int) (values[0] - (values[0] * (euReturn / 100.0)));
                values[2] = Math.min(fusionTier + coilTier, values[2]);
            }
            else {
                if (fusionTier != 0) fusionTier = MetaTileEntityAdvFusionReactor.this.tier - fusionTier;
                values[0] = (int) (values[0] - (values[0] * (euReturn / 100.0)));
                values[2] = Math.min(fusionTier * 2, values[2]);
            }

        }

        /*
        @Override
        protected Recipe findRecipe(long maxVoltage, IItemHandlerModifiable inputs, IMultipleTankHandler fluidInputs) {
            Recipe recipe = super.findRecipe(maxVoltage, inputs, fluidInputs);
            RecipeBuilder<?> newRecipe;
            if (recipe == null || recipe.getProperty(FusionEUToStartProperty.getInstance(), 0L) > energyContainer.getEnergyCapacity()) {
                return null;
            } else {
                int recipeTier = recipe.getProperty(AdvFusionCoilProperty.getInstance(), 0);
                int coilTierDifference = coilTier - recipeTier;
                int vacuumTierDifference = vacuumTier - recipeTier;
                int divertorTierDifference = divertorTier - recipeTier;
                newRecipe = recipeMap.recipeBuilder().duration((int) Math.max(1.0, recipe.getDuration() * (1 - GAConfig.multis.advFusion.coilDurationDiscount * coilTierDifference)));
                newRecipe.EUt((int) Math.max(1, recipe.getEUt() * (1 - vacuumTierDifference * GAConfig.multis.advFusion.vacuumEnergyDecrease)));

                newRecipe.fluidInputs(recipe.getFluidInputs().get(0), recipe.getFluidInputs().get(1));
                FluidStack newOutput = recipe.getFluidOutputs().get(0);
                newOutput.amount = (int) (newOutput.amount * (1 + divertorTierDifference * GAConfig.multis.advFusion.divertorOutputIncrease));
                newRecipe.fluidOutputs(newOutput);

                if (recipe.getFluidInputs().size() == 3) {

                    FluidStack newFluid = recipe.getFluidInputs().get(2).copy();
                    newFluid.amount = (int) (newFluid.amount * (1 + vacuumTierDifference * GAConfig.multis.advFusion.vacuumCoolantIncrease));
                    newRecipe.fluidInputs(newFluid);

                    newOutput = recipe.getFluidOutputs().get(1).copy();
                    newOutput.amount = (int) (newOutput.amount * (1 + divertorTierDifference * GAConfig.multis.advFusion.vacuumCoolantIncrease));
                    newRecipe.fluidOutputs(newOutput);
                }
                ((AdvFusionRecipeBuilder) newRecipe).EUToStart(recipe.getProperty(FusionEUToStartProperty.getInstance(), 0L));
                ((AdvFusionRecipeBuilder) newRecipe).EUReturn(recipe.getProperty(AdvFusionEUReturnProperty.getInstance(), 0));
                ((AdvFusionRecipeBuilder) newRecipe).AdvCoilTier(recipe.getProperty(AdvFusionCoilProperty.getInstance(), 0));
            }
            return newRecipe.build().getResult();
        }

         */

        @NotNull
        @Override
        public NBTTagCompound serializeNBT() {
            NBTTagCompound tag = super.serializeNBT();
            tag.setLong("Heat", heat);
            return tag;
        }

        @Override
        public void deserializeNBT(@NotNull NBTTagCompound compound) {
            super.deserializeNBT(compound);
            heat = compound.getLong("Heat");
        }

        @Override
        protected void setActive(boolean active) {
            if (active != isActive) {
                MetaTileEntityAdvFusionReactor.this.progressBarSupplier.resetCountdown();
            }
            super.setActive(active);
        }
    }
}
