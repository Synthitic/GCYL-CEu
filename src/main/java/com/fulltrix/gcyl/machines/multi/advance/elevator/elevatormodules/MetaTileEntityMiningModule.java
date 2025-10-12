package com.fulltrix.gcyl.machines.multi.advance.elevator.elevatormodules;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import com.cleanroommc.modularui.factory.PosGuiData;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.value.sync.BooleanSyncValue;
import com.cleanroommc.modularui.value.sync.IntSyncValue;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;
import com.cleanroommc.modularui.value.sync.StringSyncValue;
import com.cleanroommc.modularui.widget.Widget;
import com.cleanroommc.modularui.widgets.ButtonWidget;
import com.cleanroommc.modularui.widgets.TextWidget;
import com.cleanroommc.modularui.widgets.ToggleButton;
import com.cleanroommc.modularui.widgets.layout.Flow;
import com.cleanroommc.modularui.widgets.textfield.TextFieldWidget;
import com.fulltrix.gcyl.client.ClientHandler;
import com.fulltrix.gcyl.machines.multi.advance.elevator.MetaTileEntityModuleBase;
import com.fulltrix.gcyl.recipes.categories.elevator.SpaceMiningRecipes;
import gregtech.api.capability.*;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.capability.impl.ItemHandlerList;
import gregtech.api.gui.widgets.LabelWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIBuilder;
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIFactory;
import gregtech.api.mui.GTGuiTextures;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.util.GTTransferUtils;
import gregtech.api.util.KeyUtil;
import gregtech.api.util.TextComponentUtil;
import gregtech.common.ConfigHolder;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.eclipse.xtext.xbase.lib.Pair;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.input.Keyboard;

import java.util.*;

import static com.fulltrix.gcyl.item.GCYLCoreItems.*;
import static com.fulltrix.gcyl.recipes.categories.elevator.SpaceMiningRecipes.*;
import static gregtech.api.unification.material.Materials.*;

public class MetaTileEntityMiningModule extends MetaTileEntityModuleBase implements IOpticalComputationReceiver {

    private IItemHandlerModifiable inputInventory;
    private IItemHandlerModifiable outputInventory;
    private IMultipleTankHandler inputFluidInventory;
    private IOpticalComputationProvider computationProvider;
    private int minDistance = 0;
    private int maxDistance = 0;
    private int distance = 0;
    private int range = 0;
    private final int MAX_PARALLEL;
    private final int MAX_DISTANCE = 300;
    private final int MAX_RANGE = 150;
    private boolean cycleMode = false;
    private int step = 0;
    private int parallel;

    private SpaceMiningRecipes.SpaceMiningRecipePartTwo randomRecipe;
    private List<ItemStack> randomOutput = new ArrayList<>();

    private List<String> whitelist = new ArrayList<>();
    private boolean isWhitelist = true;

    private long totalEUt = 0;
    private int totalComputation = 0;

    public MetaTileEntityMiningModule(ResourceLocation metaTileEntityId, int tier, int moduleTier, int minMotorTier, int maxParallel) {
        super(metaTileEntityId, tier, moduleTier, minMotorTier);
        this.MAX_PARALLEL = maxParallel;
        this.parallel = maxParallel;
    }

    @Override
    public void initializeAbilities() {
        this.inputInventory = new ItemHandlerList(getAbilities(MultiblockAbility.IMPORT_ITEMS));
        this.outputInventory = new ItemHandlerList(getAbilities(MultiblockAbility.EXPORT_ITEMS));
        this.inputFluidInventory = new FluidTankList(false, getAbilities(MultiblockAbility.IMPORT_FLUIDS));

        try {
            this.computationProvider = getSpaceElevator().getComputationProvider();
        } catch (NullPointerException ignored) {
        }
    }

    @Override
    protected TraceabilityPredicate abilities() {
        return abilities(MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS, MultiblockAbility.IMPORT_FLUIDS);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityMiningModule(this.metaTileEntityId, this.tier, this.moduleTier, this.minMotorTier, this.MAX_PARALLEL);
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

    @Override
    public void updateFormedValid() {
        super.updateFormedValid();
        if (!this.isWorkingEnabled()) {
            return;
        }

        if (!drainEnergy(true, this.totalEUt) || this.computationProvider.requestCWUt(this.totalComputation, true) != this.totalComputation) {
            if (this.progressTime >= 2) {
                if (ConfigHolder.machines.recipeProgressLowEnergy) this.progressTime = 1;
                else this.progressTime = Math.max(1, this.progressTime - 2);
            }
            return;
        }

        if (progressTime == 0 && checkFluidInventory(true) == null) {
            setActive(false);
            return;
        }

        if (progressTime == 0 && checkRecipes(true) == null) {
            setActive(false);
            if (this.cycleMode) {
                setDistance(this.distance + this.step);
            }
        } else {
            Random rand = this.getWorld().rand;

            setActive(true);

            if (progressTime == 0) {

                List<SpaceMiningRecipes.SpaceMiningRecipePartTwo> recipes = checkRecipes(false);
                Fluid fluid = checkFluidInventory(false);

                int averageProgress = 0;

                for (int x = 0; x < this.parallel; x++) {
                    int totalWeight = 0;
                    for (SpaceMiningRecipes.SpaceMiningRecipePartTwo i : recipes) {
                        totalWeight += i.getWeight();
                    }

                    int idx = 0;
                    for (int r = rand.nextInt(totalWeight); idx < recipes.size() - 1; ++idx) {
                        r -= recipes.get(idx).getWeight();
                        if (r <= 0) break;
                    }

                    this.randomRecipe = recipes.get(idx);

                    averageProgress += this.randomRecipe.getDuration();
                    this.totalEUt += this.randomRecipe.getEUt();
                    this.totalComputation += this.randomRecipe.getComputation();


                    int randomStackSize = rand.nextInt(this.randomRecipe.getMaxSize() - this.randomRecipe.getMinSize()) + this.randomRecipe.getMinSize();

                    int totalWeight2 = 0;
                    for (Pair<Material, Integer> i : this.randomRecipe.getOutputs()) {
                        totalWeight2 += i.getValue();
                    }

                    int idx2 = 0;
                    for (int r = rand.nextInt(totalWeight2); idx2 < this.randomRecipe.getOutputs().size() - 1; ++idx2) {
                        r -= this.randomRecipe.getOutputs().get(idx2).getValue();
                        if (r <= 0) break;
                    }

                    if (!this.whitelist.isEmpty()) {
                        for (int i = 0; i < this.whitelist.size(); i++) {
                            if (this.whitelist.get(i).equalsIgnoreCase(this.randomRecipe.getOutputs().get(idx2).getKey().getLocalizedName())) {
                                if (this.isWhitelist) {
                                    this.randomOutput.add(OreDictUnifier.get(OrePrefix.ore, this.randomRecipe.getOutputs().get(idx2).getKey(), randomStackSize));
                                    break;
                                } else continue;
                            }

                            if (i == this.whitelist.size() - 1 && !this.isWhitelist) {
                                this.randomOutput.add(OreDictUnifier.get(OrePrefix.ore, this.randomRecipe.getOutputs().get(idx2).getKey(), randomStackSize));
                            }
                        }
                    } else {
                        this.randomOutput.add(OreDictUnifier.get(OrePrefix.ore, this.randomRecipe.getOutputs().get(idx2).getKey(), randomStackSize));
                    }

                    if (x == this.parallel - 1) {
                        if (fluid == Helium.getFluid()) {
                            setMaxProgress(averageProgress / this.parallel);
                        } else if (fluid == Bismuth.getFluid()) {
                            setMaxProgress(averageProgress / this.parallel / 2);
                        } else if (fluid == Radon.getFluid()) {
                            setMaxProgress(averageProgress / this.parallel / 4);
                        }
                    }
                }
            }

            if (this.randomRecipe == null || this.randomOutput == null) {
                progressTime = 0;
                return;
            }

            this.computationProvider.requestCWUt(this.totalComputation, false);
            drainEnergy(false, this.totalEUt);

            progressTime++;
            if (progressTime % getMaxProgress() != 0) return;
            progressTime = 0;

            if (this.cycleMode) {
                setDistance(this.distance + this.step);
            }

            GTTransferUtils.addItemsToItemHandler(this.outputInventory, false, this.randomOutput);

            this.randomOutput.clear();
            this.totalEUt = 0;
            this.totalComputation = 0;

        }
    }

    public List<SpaceMiningRecipes.SpaceMiningRecipePartTwo> checkRecipes(boolean simulate) {

        ItemStack item = null;

        Material matStick = null;
        Material matDrill = null;

        int stickCount = 0;
        int drillCount = 0;

        final int neededSticks = STICK_INPUT_STACK_SIZE * this.parallel;
        final int neededDrills = DRILL_HEAD_INPUT_STACK_SIZE * this.parallel;

        for (int i = 0; i < getInputInventory().getSlots(); i++) {
            ItemStack slot = getInputInventory().getStackInSlot(i);
            if (ItemStack.areItemStacksEqual(slot, MINING_DRONE_1.getStackForm())
                    || ItemStack.areItemStacksEqual(slot, MINING_DRONE_2.getStackForm())
                    || ItemStack.areItemStacksEqual(slot, MINING_DRONE_3.getStackForm())
                    || ItemStack.areItemStacksEqual(slot, MINING_DRONE_4.getStackForm())
                    || ItemStack.areItemStacksEqual(slot, MINING_DRONE_5.getStackForm())
                    || ItemStack.areItemStacksEqual(slot, MINING_DRONE_6.getStackForm())
                    || ItemStack.areItemStacksEqual(slot, MINING_DRONE_7.getStackForm())
                    || ItemStack.areItemStacksEqual(slot, MINING_DRONE_8.getStackForm())
                    || ItemStack.areItemStacksEqual(slot, MINING_DRONE_9.getStackForm())
                    || ItemStack.areItemStacksEqual(slot, MINING_DRONE_10.getStackForm())
                    || ItemStack.areItemStacksEqual(slot, MINING_DRONE_11.getStackForm())
                    || ItemStack.areItemStacksEqual(slot, MINING_DRONE_12.getStackForm())
                    || ItemStack.areItemStacksEqual(slot, MINING_DRONE_13.getStackForm())
                    || ItemStack.areItemStacksEqual(slot, MINING_DRONE_14.getStackForm())) {
                item = slot;
                continue;
            }
            if (OreDictUnifier.getPrefix(slot) == OrePrefix.stick) {
                if (matStick != null && matStick != Objects.requireNonNull(OreDictUnifier.getMaterial(slot)).material) {
                    return null;
                }
                matStick = Objects.requireNonNull(OreDictUnifier.getMaterial(slot)).material;
                stickCount += slot.getCount();
                continue;
            }

            if (OreDictUnifier.getPrefix(slot) == OrePrefix.toolHeadDrill) {
                if (matDrill != null && matDrill != Objects.requireNonNull(OreDictUnifier.getMaterial(slot)).material) {
                    return null;
                }
                matDrill = Objects.requireNonNull(OreDictUnifier.getMaterial(slot)).material;
                drillCount += slot.getCount();
            }

            if (item != null && stickCount >= neededSticks && drillCount >= neededDrills)
                break;
        }

        if (stickCount < neededSticks || drillCount < neededDrills) {
            return null;
        } else {
            stickCount = neededSticks;
            drillCount = neededDrills;
        }

        if (matStick != matDrill || matStick == null)
            return null;

        List<SpaceMiningRecipes.SpaceMiningRecipePartTwo> recipesBeforeCheck = SPACE_MINING_RECIPES.get(new SpaceMiningRecipes.SpaceMiningRecipePartOne(item, matStick).hashCode());

        if (recipesBeforeCheck == null) return null;

        List<SpaceMiningRecipes.SpaceMiningRecipePartTwo> recipesAfterCheck = new ArrayList<>();

        for (SpaceMiningRecipes.SpaceMiningRecipePartTwo recipe : recipesBeforeCheck) {
            if (recipe != null) {
                if (this.moduleTier < recipe.getMinModuleTier()) {
                    continue;
                }

                if (!(this.minDistance <= recipe.getMaxDistance() && recipe.getMinDistance() <= this.maxDistance)) {
                    continue;
                }

                if (!drainEnergy(true, recipe.getEUt() * this.parallel)) {
                    continue;
                }

                if (this.computationProvider.requestCWUt(recipe.getComputation() * this.parallel, true) != recipe.getComputation() * this.parallel) {
                    continue;
                }

                recipesAfterCheck.add(recipe);

                if (simulate)
                    return recipesAfterCheck;
            }
        }
        if (recipesAfterCheck.isEmpty())
            return null;
        else {
            int currentStickCount = 0;
            int currentDrillCount = 0;
            for (int i = 0; i < getInputInventory().getSlots(); i++) {
                ItemStack item2 = getInputInventory().getStackInSlot(i);

                try {
                    if (currentStickCount != stickCount && OreDictUnifier.getPrefix(item2) == OrePrefix.stick && OreDictUnifier.getMaterial(item2).material == matStick) {
                        currentStickCount += item2.getCount();
                        getInputInventory().extractItem(i, Math.min(item2.getCount(), stickCount), false);
                    }

                    if (currentDrillCount != drillCount && OreDictUnifier.getPrefix(item2) == OrePrefix.toolHeadDrill && OreDictUnifier.getMaterial(item2).material == matDrill) {
                        currentDrillCount += item2.getCount();
                        getInputInventory().extractItem(i, Math.min(item2.getCount(), drillCount), false);
                    }
                } catch (NullPointerException ignored) {
                }

                if (currentStickCount >= stickCount && currentDrillCount >= drillCount)
                    break;
            }
            return recipesAfterCheck;
        }
    }

    private Fluid checkFluidInventory(boolean simulate) {

        FluidStack canDrainHelium = getInputFluidInventory().drain(Helium.getPlasma(1000 * this.parallel), false);
        FluidStack canDrainBismuth = getInputFluidInventory().drain(Bismuth.getPlasma(500 * this.parallel), false);
        FluidStack canDrainRadon = getInputFluidInventory().drain(Radon.getPlasma(300 * this.parallel), false);

        if (canDrainHelium != null || canDrainBismuth != null || canDrainRadon != null) {

            if (canDrainHelium != null && canDrainHelium.isFluidStackIdentical(Helium.getPlasma(1000 * this.parallel))) {
                if (!simulate) {
                    getInputFluidInventory().drain(Helium.getPlasma(1000 * this.parallel), true);
                }
                return Helium.getFluid();
            }

            if (canDrainBismuth != null && canDrainBismuth.isFluidStackIdentical(Bismuth.getPlasma(500 * this.parallel))) {
                if (!simulate) {
                    getInputFluidInventory().drain(Bismuth.getPlasma(500 * this.parallel), true);
                }

                return Bismuth.getFluid();
            }

            if (canDrainRadon != null && canDrainRadon.isFluidStackIdentical(Radon.getPlasma(300 * this.parallel))) {
                if (!simulate) {
                    getInputFluidInventory().drain(Radon.getPlasma(300 * this.parallel), true);
                }
                return Radon.getFluid();
            }

        }
        return null;
    }

    private IMultipleTankHandler getInputFluidInventory() {
        return this.inputFluidInventory;
    }

    private IItemHandlerModifiable getInputInventory() {
        return this.inputInventory;
    }

    @Override
    public IOpticalComputationProvider getComputationProvider() {
        return this.computationProvider;
    }

    @Override
    protected MultiblockUIFactory createUIFactory() {
        return new MultiblockUIFactory(this) {
            @Override
            protected @NotNull Flow createButtons(@NotNull ModularPanel mainPanel, @NotNull PanelSyncManager panelSyncManager, PosGuiData guiData) {
                BooleanSyncValue cycleModeSync = new BooleanSyncValue(MetaTileEntityMiningModule.this::getCycleMode, MetaTileEntityMiningModule.this::setCycleMode);
                BooleanSyncValue whitelistModeSync = new BooleanSyncValue(MetaTileEntityMiningModule.this::getWhitelistMode, MetaTileEntityMiningModule.this::setWhitelistMode);

                Widget powerButton = this.createPowerButton(mainPanel, panelSyncManager);
                return Flow.column().debugName("button_col").right(4).coverChildren()
                        .child(new ToggleButton()
                                .stateOverlay(ClientHandler.BUTTON_CYCLE)
                                .addTooltipLine(KeyUtil.lang("gcyl.gui.mining_module.cycle"))
                                .value(cycleModeSync)
                                .size(18))
                        .child(new ToggleButton()
                                .stateOverlay(ClientHandler.BUTTON_WHITE_BLACK_LIST)
                                .addTooltipLine(KeyUtil.lang("gcyl.gui.mining_module.change_whitelist_mode"))
                                .value(whitelistModeSync)
                                .size(18))
                        .child(new ButtonWidget<>()
                                .overlay(GTGuiTextures.BUTTON_CLEAR_GRID)
                                .addTooltipLine(KeyUtil.lang("gcyl.gui.mining_module.print_whitelist_or_clear"))
                                .onMousePressed(mousePressed -> MetaTileEntityMiningModule.this.printWhitelistOrClear(panelSyncManager.getPlayer()))
                                .size(18)
                        )
                        .childIf(powerButton != null, powerButton);
            }
        }.addScreenChildren((parentWidget, syncManager) -> {
            IntSyncValue distanceSync = new IntSyncValue(this::getDistance, this::setDistance);
            IntSyncValue rangeSync = new IntSyncValue(this::getRange, this::setRange);
            IntSyncValue stepSync = new IntSyncValue(this::getStep, this::setStep);
            IntSyncValue parallelSync = new IntSyncValue(this::getParallel, this::setParallel);
            StringSyncValue addToWhitelistSync = new StringSyncValue(this::getBlankName, this::addToWhiteList);
            StringSyncValue RemoveFromWhitelistSync = new StringSyncValue(this::getBlankRemoveName, this::removeFromWhitelist);

            int padding = 18;

            parentWidget
                    .child(new TextWidget(KeyUtil.lang(TextFormatting.LIGHT_PURPLE, "gcyl.gui.mining_module.distance"))
                            .pos(120, 5 + padding))
                    .child(new TextFieldWidget()
                            .pos(163, 3 + padding)
                            .size(25,10)
                            .value(distanceSync)
                            .setNumbers(0, this.MAX_DISTANCE))
                    .child(new TextWidget(KeyUtil.lang(TextFormatting.LIGHT_PURPLE, "gcyl.gui.mining_module.range"))
                            .pos(120, 5 + 2 * padding))
                    .child(new TextFieldWidget()
                            .pos(163, 3 + 2 * padding)
                            .size(25,10)
                            .value(rangeSync)
                            .setNumbers(0, this.MAX_RANGE))
                    .child(new TextWidget(KeyUtil.lang(TextFormatting.LIGHT_PURPLE, "gcyl.gui.mining_module.step"))
                            .pos(120, 5 + 3 * padding))
                    .child(new TextFieldWidget()
                            .pos(163, 3 + 3 * padding)
                            .size(25,10)
                            .value(stepSync))
                    .child(new TextWidget(KeyUtil.lang(TextFormatting.LIGHT_PURPLE, "gcyl.gui.mining_module.parallel"))
                            .pos(120, 5 + 4 * padding))
                    .child(new TextFieldWidget()
                            .pos(163, 3 + 4 * padding)
                            .size(25,10)
                            .value(parallelSync)
                            .setNumbers(0, this.MAX_PARALLEL))
                    .child(new TextFieldWidget()
                            .pos(47, 56)
                            .width(70)
                            .height(10)
                            .value(addToWhitelistSync))
                    .child(new TextFieldWidget()
                            .pos(47, 70)
                            .width(70)
                            .height(10)
                            .value(RemoveFromWhitelistSync));
        }).configureWarningText(this::configureWarningText)
                .configureDisplayText(this::configureDisplayText)
                .configureErrorText(this::configureErrorText);
    }

    /*
    @Override
    protected ModularUI.Builder createUITemplate(EntityPlayer entityPlayer) {
        ModularUI.Builder builder = ModularUI.builder(GuiTextures.BACKGROUND, 198, 238);
        ;
        builder.image(4, 4, 190, 147, GuiTextures.DISPLAY);
        builder.widget((new IndicatorImageWidget(174, 131, 17, 17, this.getLogo())).setWarningStatus(this.getWarningLogo(), this::addWarningText).setErrorStatus(this.getErrorLogo(), this::addErrorText));

        builder.label(9, 9, this.getMetaFullName(), 16777215);

        int size = 18;
        int padding = 3;


        builder.widget(new LabelWidget(120, 9 + (size + padding), "gcyl.gui.mining_module.distance", 0xFF55FF));
        builder.widget(new TextFieldWidget2(170, 9 + (size + padding), size * size, size, this::getDistance, this::setDistance).setMaxLength(3).setAllowedChars(TextFieldWidget2.NATURAL_NUMS));

        builder.widget(new LabelWidget(120, 9 + 2 * (size + padding), "gcyl.gui.mining_module.range", 0xFF55FF));
        builder.widget(new TextFieldWidget2(170, 9 + 2 * (size + padding), size * size, size, this::getRange, this::setRange).setMaxLength(3).setAllowedChars(TextFieldWidget2.NATURAL_NUMS));

        builder.widget(new LabelWidget(120, 9 + 3 * (size + padding), "gcyl.gui.mining_module.step", 0xFF55FF));
        builder.widget(new TextFieldWidget2(170, 9 + 3 * (size + padding), size * size, size, this::getStep, this::setStep).setMaxLength(3).setAllowedChars(TextFieldWidget2.NATURAL_NUMS));

        builder.widget(new LabelWidget(120, 9 + 4 * (size + padding), "gcyl.gui.mining_module.parallel", 0xFF55FF));
        builder.widget(new TextFieldWidget2(170, 9 + 4 * (size + padding), size * size, size, this::getParallel, this::setParallel).setMaxLength(1).setAllowedChars(TextFieldWidget2.NATURAL_NUMS));



        builder.widget(new TextFieldWidget2(9, (size + padding) + 11 * 5, size * size, size, this::getBlankName, this::addToWhiteList).setMaxLength(21).setAllowedChars(TextFieldWidget2.LETTERS));

        builder.widget(new TextFieldWidget2(9, (size + padding) + 11 * 7, size * size, size, this::getBlankRemoveName, this::removeFromWhitelist).setMaxLength(21).setAllowedChars(TextFieldWidget2.LETTERS));



        builder.widget((new AdvancedTextWidget(9, (size + padding), this::addDisplayText, 16777215)).setMaxWidthLimit(181).setClickHandler(this::handleDisplayClick));
        IControllable controllable = (IControllable) this.getCapability(GregtechTileCapabilities.CAPABILITY_CONTROLLABLE, (EnumFacing) null);
        TextureArea var10007;
        BooleanSupplier var10008;
        if (controllable != null) {
            var10007 = GuiTextures.BUTTON_POWER;
            Objects.requireNonNull(controllable);
            var10008 = controllable::isWorkingEnabled;
            Objects.requireNonNull(controllable);
            builder.widget(new ImageCycleButtonWidget(173, 213, 18, 18, var10007, var10008, controllable::setWorkingEnabled));
            builder.widget(new ImageWidget(173, 231, 18, 6, GuiTextures.BUTTON_POWER_DETAIL));
        }

        //clear or retrieve list //TODO change the texture of this
        builder.widget(new ClickButtonWidget(173, 155, 18, 18, "", data -> printWhitelistOrClear(data, entityPlayer)).setButtonTexture(GuiTextures.BUTTON_CLEAR_GRID).setTooltipText("gcyl.gui.mining_module.print_whitelist_or_clear"));

        //whitelist or blacklist
        builder.widget(new ImageCycleButtonWidget(173, 173, 18, 18, ClientHandler.BUTTON_WHITE_BLACK_LIST, this::getWhitelistMode, this::setWhitelistMode).setTooltipHoverString("gcyl.gui.mining_module.change_whitelist_mode"));

        //Cycle mode
        builder.widget(new ImageCycleButtonWidget(173, 191, 18, 18, ClientHandler.BUTTON_CYCLE, this::getCycleMode, this::setCycleMode).setTooltipHoverString("gcyl.gui.mining_module.cycle"));


        builder.bindPlayerInventory(entityPlayer.inventory, 155);
        return builder;
    }
     */

    @Override
    protected void configureDisplayText(MultiblockUIBuilder builder) {
        builder.setWorkingStatus(isWorkingEnabled(), isActive())
                .addWorkingStatusLine()
                .addParallelsLine(this.MAX_PARALLEL)
                .addCustom((keyManager, uiSyncer) -> {
                    keyManager.add(KeyUtil.lang(TextFormatting.YELLOW, "gcyl.gui.mining_module.min_distance", uiSyncer.syncInt(this.minDistance)));
                    keyManager.add(KeyUtil.lang(TextFormatting.RED, "gcyl.gui.mining_module.max_distance", uiSyncer.syncInt(this.maxDistance)));
                })
                .addCustom((keyManager, uiSyncer) -> keyManager.add(KeyUtil.lang(uiSyncer.syncBoolean(this.isWhitelist) ? TextFormatting.DARK_GREEN : TextFormatting.DARK_RED, uiSyncer.syncBoolean(this.isWhitelist) ? "gcyl.gui.mining_module.whitelist" : "gcyl.gui.mining_module.blacklist")))
                .addEmptyLine()
                .addCustom((keyManager, uiSyncer) -> keyManager.add(KeyUtil.lang(TextFormatting.RED, "gcyl.gui.mining_module.remove")))
                .addEmptyLine()
                .addProgressLine(getProgress(), getMaxProgress())
                .addEnergyUsageExactLine(this.totalEUt)
                .addComputationUsageExactLine(this.totalComputation);
    }


    private boolean printWhitelistOrClear(EntityPlayer player) {
        if(this.whitelist.isEmpty()) {
            player.sendStatusMessage(TextComponentUtil.translationWithColor(TextFormatting.WHITE, "gcyl.gui.mining_module." + (this.isWhitelist ? "whitelist_empty" : "blacklist_empty")), false);
            return true;
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            this.whitelist.clear();
            player.sendStatusMessage(TextComponentUtil.translationWithColor(TextFormatting.WHITE, "gcyl.gui.mining_module." + (this.isWhitelist ? "whitelist_cleared" : "blacklist_cleared")), false);
        }
        else {

            StringBuilder sb = new StringBuilder(I18n.format("gcyl.gui.mining_module." + (this.isWhitelist ? "whitelist" : "blacklist")) + "\n");

            for(int i = 0; i < this.whitelist.size(); i++) {
                sb.append(this.whitelist.get(i));
                if(i != this.whitelist.size() - 1) {
                    sb.append("\n");
                }
            }

            player.sendStatusMessage(TextComponentUtil.stringWithColor(TextFormatting.WHITE, sb.toString()), false);
        }
        return true;
    }


    private void addToWhiteList(String name) {
        if (!this.whitelist.contains(name) && !Objects.equals(name, I18n.format("gcyl.gui.mining_module.blank_name"))) {
            this.whitelist.add(name);
        }
    }

    private void removeFromWhitelist(String name) {
        this.whitelist.remove(name);
    }

    private String getBlankName() {
        return I18n.format("gcyl.gui.mining_module.blank_name");
    }

    private String getBlankRemoveName() {
        return I18n.format("gcyl.gui.mining_module.blank_name_remove");
    }

    private void setWhitelistMode(boolean bool) {
        this.isWhitelist = bool;
    }

    private boolean getWhitelistMode() {
        return this.isWhitelist;
    }

    private void setCycleMode(boolean bool) {
        this.cycleMode = bool;
    }

    private boolean getCycleMode() {
        return this.cycleMode;
    }

    private void setDistance(int distance) {
        int real = distance;
        if (real > this.MAX_DISTANCE) {
            while (real > this.MAX_DISTANCE) {
                real -= this.MAX_DISTANCE;
            }
        }
        this.distance = real;
        this.minDistance = Math.max(this.distance - this.range / 2, 0);
        this.maxDistance = Math.min(this.distance + this.range / 2, this.MAX_DISTANCE);
    }

    private int getDistance() {
        return this.distance;
    }

    private void setRange(int range) {
        if (range > this.MAX_RANGE) {
            this.range = 0;
            this.minDistance = this.distance;
            this.maxDistance = this.distance;
        } else {
            this.range = range;
            this.minDistance = Math.max(this.distance - this.range / 2, 0);
            this.maxDistance = Math.min(this.distance + this.range / 2, this.MAX_DISTANCE);
        }
    }

    private int getRange() {
        return this.range;
    }

    private void setStep(int step) {
        this.step = step;
    }

    private int getStep() {
        return this.step;
    }

    private void setParallel(int parallel) {
        if (parallel < 1)
                this.parallel = 1;
            else this.parallel = Math.min(parallel, this.MAX_PARALLEL);
    }

    private int getParallel() {
        return this.parallel;
    }


    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setInteger("distance", this.distance);
        data.setInteger("minDistance", this.minDistance);
        data.setInteger("maxDistance", this.maxDistance);
        data.setInteger("range", this.range);
        data.setInteger("step", this.step);
        data.setInteger("parallel", this.parallel);
        data.setBoolean("cycle", this.cycleMode);
        data.setBoolean("isWhitelist", this.isWhitelist);


            NBTTagList nbtTagList = new NBTTagList();

            for(String s : this.whitelist) {
                NBTTagCompound tag = new NBTTagCompound();

                tag.setString("material", s);
                nbtTagList.appendTag(tag);
            }

            data.setInteger("whitelistSize", this.whitelist.size());
            data.setTag("whitelist", nbtTagList);

        return super.writeToNBT(data);
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.distance = data.getInteger("distance");
        this.minDistance = data.getInteger("minDistance");
        this.maxDistance = data.getInteger("maxDistance");
        this.range = data.getInteger("range");
        this.step = data.getInteger("step");
        this.parallel = data.getInteger("parallel");
        this.cycleMode = data.getBoolean("cycle");
        this.isWhitelist = data.getBoolean("isWhitelist");

        if (data.getInteger("whitelistSize") > 0) {
            NBTTagList nbtTagList = data.getTagList("whitelist", Constants.NBT.TAG_COMPOUND);
            for (int i = 0; i < data.getInteger("whitelistSize"); i++) {
                NBTTagCompound tag = nbtTagList.getCompoundTagAt(i);
                this.whitelist.add(tag.getString("material"));
            }
        }
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeInt(this.distance);
        buf.writeInt(this.minDistance);
        buf.writeInt(this.maxDistance);
        buf.writeInt(this.range);
        buf.writeInt(this.step);
        buf.writeInt(this.parallel);
        buf.writeBoolean(this.cycleMode);
        buf.writeBoolean(this.isWhitelist);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.distance = buf.readInt();
        this.minDistance = buf.readInt();
        this.maxDistance = buf.readInt();
        this.range = buf.readInt();
        this.step = buf.readInt();
        this.parallel = buf.readInt();
        this.cycleMode = buf.readBoolean();
        this.isWhitelist = buf.readBoolean();
    }
}
