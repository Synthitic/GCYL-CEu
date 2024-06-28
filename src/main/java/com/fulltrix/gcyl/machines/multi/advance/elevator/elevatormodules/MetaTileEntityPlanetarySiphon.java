package com.fulltrix.gcyl.machines.multi.advance.elevator.elevatormodules;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import com.fulltrix.gcyl.client.ClientHandler;
import com.fulltrix.gcyl.machines.multi.advance.elevator.MetaTileEntityModuleBase;
import com.fulltrix.gcyl.machines.multi.advance.elevator.MetaTileEntityModuleRecipeBase;
import gregtech.api.capability.GregtechTileCapabilities;
import gregtech.api.capability.IControllable;
import gregtech.api.capability.IDistinctBusController;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.resources.TextureArea;
import gregtech.api.gui.widgets.*;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMaintenance;
import gregtech.api.metatileentity.multiblock.IProgressBarMultiblock;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.terminal.gui.widgets.TextEditorWidget;
import gregtech.api.unification.material.Material;
import gregtech.common.ConfigHolder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BooleanSupplier;

import static com.fulltrix.gcyl.recipes.categories.ElevatorRecipes.GAS_SIPHON_RECIPES;


public class MetaTileEntityPlanetarySiphon extends MetaTileEntityModuleBase {

    private IMultipleTankHandler outputFluidInventory;
    @SuppressWarnings("FieldMayBeFinal")
    private int[] planet = {0, 0, 0, 0};

    @SuppressWarnings("FieldMayBeFinal")
    private int[] fluidNumber = {0, 0, 0, 0};

    public MetaTileEntityPlanetarySiphon(ResourceLocation metaTileEntityId, int tier, int moduleTier, int minMotorTier) {
        super(metaTileEntityId, tier, moduleTier, minMotorTier);
    }
    @Override
    protected void initializeAbilities() {
        this.outputFluidInventory  = new FluidTankList(false, getAbilities(MultiblockAbility.EXPORT_FLUIDS));
    }



    @Override
    protected TraceabilityPredicate abilities() {
        return abilities(MultiblockAbility.EXPORT_FLUIDS);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
            return new MetaTileEntityPlanetarySiphon(metaTileEntityId, this.tier, this.moduleTier, this.minMotorTier);
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
                ClientHandler.PUMP_MODULE_OVERLAY.renderSided(renderSide, renderState, translation, pipeline);
        }
    }

    @Override
    protected ModularUI.Builder createUITemplate(EntityPlayer entityPlayer) {
        ModularUI.Builder builder;
        label38: {
            builder = ModularUI.builder(GuiTextures.BACKGROUND, 198, 208);
            if (this instanceof IProgressBarMultiblock) {
                IProgressBarMultiblock progressMulti = (IProgressBarMultiblock)this;
                if (progressMulti.showProgressBar()) {
                    builder.image(4, 4, 190, 109, GuiTextures.DISPLAY);
                    ProgressWidget progressBar;
                    if (progressMulti.getNumProgressBars() == 3) {
                        progressBar = (new ProgressWidget(() -> {
                            return progressMulti.getFillPercentage(0);
                        }, 4, 115, 62, 7, progressMulti.getProgressBarTexture(0), ProgressWidget.MoveType.HORIZONTAL)).setHoverTextConsumer((list) -> {
                            progressMulti.addBarHoverText(list, 0);
                        });
                        builder.widget(progressBar);
                        progressBar = (new ProgressWidget(() -> {
                            return progressMulti.getFillPercentage(1);
                        }, 68, 115, 62, 7, progressMulti.getProgressBarTexture(1), ProgressWidget.MoveType.HORIZONTAL)).setHoverTextConsumer((list) -> {
                            progressMulti.addBarHoverText(list, 1);
                        });
                        builder.widget(progressBar);
                        progressBar = (new ProgressWidget(() -> {
                            return progressMulti.getFillPercentage(2);
                        }, 132, 115, 62, 7, progressMulti.getProgressBarTexture(2), ProgressWidget.MoveType.HORIZONTAL)).setHoverTextConsumer((list) -> {
                            progressMulti.addBarHoverText(list, 2);
                        });
                        builder.widget(progressBar);
                    } else if (progressMulti.getNumProgressBars() == 2) {
                        progressBar = (new ProgressWidget(() -> {
                            return progressMulti.getFillPercentage(0);
                        }, 4, 115, 94, 7, progressMulti.getProgressBarTexture(0), ProgressWidget.MoveType.HORIZONTAL)).setHoverTextConsumer((list) -> {
                            progressMulti.addBarHoverText(list, 0);
                        });
                        builder.widget(progressBar);
                        progressBar = (new ProgressWidget(() -> {
                            return progressMulti.getFillPercentage(1);
                        }, 100, 115, 94, 7, progressMulti.getProgressBarTexture(1), ProgressWidget.MoveType.HORIZONTAL)).setHoverTextConsumer((list) -> {
                            progressMulti.addBarHoverText(list, 1);
                        });
                        builder.widget(progressBar);
                    } else {
                        progressBar = (new ProgressWidget(() -> {
                            return progressMulti.getFillPercentage(0);
                        }, 4, 115, 190, 7, progressMulti.getProgressBarTexture(0), ProgressWidget.MoveType.HORIZONTAL)).setHoverTextConsumer((list) -> {
                            progressMulti.addBarHoverText(list, 0);
                        });
                        builder.widget(progressBar);
                    }

                    builder.widget((new IndicatorImageWidget(174, 93, 17, 17, this.getLogo())).setWarningStatus(this.getWarningLogo(), this::addWarningText).setErrorStatus(this.getErrorLogo(), this::addErrorText));
                    break label38;
                }
            }

            builder.image(4, 4, 190, 117, GuiTextures.DISPLAY);
            builder.widget((new IndicatorImageWidget(174, 101, 17, 17, this.getLogo())).setWarningStatus(this.getWarningLogo(), this::addWarningText).setErrorStatus(this.getErrorLogo(), this::addErrorText));
        }

        builder.label(9, 9, this.getMetaFullName(), 16777215);


        //builder.widget(new ImageWidget(15, 50, 30, 30, GuiTextures.FLUID_SLOT));
        int size = 18;
        int padding = 3;

        builder.widget(new LabelWidget(10, 5 + (size + padding), "gcyl.multiblock.pump_module.planet", 0x55FF55));
        builder.widget(new TextFieldWidget2(50, 5 + (size + padding), size * size, size, () -> this.getPlanetValue(0), s -> this.setPlanetValue(s, 0)).setMaxLength(3).setAllowedChars(TextFieldWidget2.WHOLE_NUMS));
        builder.widget(new LabelWidget(10, 5 + 2 *(size + padding), "gcyl.multiblock.pump_module.fluid", 0xFF55FF));
        builder.widget(new TextFieldWidget2(50, 5 + 2 * (size + padding), size * size, size, () -> this.getFluidValue(0), s -> this.setFluidValue(s, 0)).setMaxLength(3).setAllowedChars(TextFieldWidget2.WHOLE_NUMS));

        if(this.moduleTier > 1) {
            builder.widget(new LabelWidget(100, 5 + (size + padding), "gcyl.multiblock.pump_module.planet", 0x55FF55));
            builder.widget(new TextFieldWidget2(140, 5 + (size + padding), size * size, size, () -> this.getPlanetValue(1), s -> this.setPlanetValue(s, 1)).setMaxLength(3).setAllowedChars(TextFieldWidget2.WHOLE_NUMS));
            builder.widget(new LabelWidget(100, 5 + 2 *(size + padding), "gcyl.multiblock.pump_module.fluid", 0xFF55FF));
            builder.widget(new TextFieldWidget2(140, 5 + 2 * (size + padding), size * size, size, () -> this.getFluidValue(1), s -> this.setFluidValue(s, 1)).setMaxLength(3).setAllowedChars(TextFieldWidget2.WHOLE_NUMS));

            builder.widget(new LabelWidget(10, 5 + 3 *(size + padding), "gcyl.multiblock.pump_module.planet", 0x55FF55));
            builder.widget(new TextFieldWidget2(50, 5 + 3 * (size + padding), size * size, size, () -> this.getPlanetValue(2), s -> this.setPlanetValue(s, 2)).setMaxLength(3).setAllowedChars(TextFieldWidget2.WHOLE_NUMS));
            builder.widget(new LabelWidget(10, 5 + 4 *(size + padding), "gcyl.multiblock.pump_module.fluid", 0xFF55FF));
            builder.widget(new TextFieldWidget2(50, 5 + 4 * (size + padding), size * size, size, () -> this.getFluidValue(2), s -> this.setFluidValue(s, 2)).setMaxLength(3).setAllowedChars(TextFieldWidget2.WHOLE_NUMS));

            builder.widget(new LabelWidget(100, 5 + 3 * (size + padding), "gcyl.multiblock.pump_module.planet", 0x55FF55));
            builder.widget(new TextFieldWidget2(140, 5 + 3 * (size + padding), size * size, size, () -> this.getPlanetValue(3), s -> this.setPlanetValue(s, 3)).setMaxLength(3).setAllowedChars(TextFieldWidget2.WHOLE_NUMS));
            builder.widget(new LabelWidget(100, 5 + 4 *(size + padding), "gcyl.multiblock.pump_module.fluid", 0xFF55FF));
            builder.widget(new TextFieldWidget2(140, 5 + 4 * (size + padding), size * size, size, () -> this.getFluidValue(3), s -> this.setFluidValue(s, 3)).setMaxLength(3).setAllowedChars(TextFieldWidget2.WHOLE_NUMS));
        }


        builder.widget((new AdvancedTextWidget(9, 20, this::addDisplayText, 16777215)).setMaxWidthLimit(181).setClickHandler(this::handleDisplayClick));
        IControllable controllable = (IControllable)this.getCapability(GregtechTileCapabilities.CAPABILITY_CONTROLLABLE, (EnumFacing)null);
        TextureArea var10007;
        BooleanSupplier var10008;
        if (controllable != null) {
            var10007 = GuiTextures.BUTTON_POWER;
            Objects.requireNonNull(controllable);
            var10008 = controllable::isWorkingEnabled;
            Objects.requireNonNull(controllable);
            builder.widget(new ImageCycleButtonWidget(173, 183, 18, 18, var10007, var10008, controllable::setWorkingEnabled));
            builder.widget(new ImageWidget(173, 201, 18, 6, GuiTextures.BUTTON_POWER_DETAIL));
        }

        if (this.shouldShowVoidingModeButton()) {
            builder.widget((new ImageCycleButtonWidget(173, 161, 18, 18, GuiTextures.BUTTON_VOID_MULTIBLOCK, 4, this::getVoidingMode, this::setVoidingMode)).setTooltipHoverString(MultiblockWithDisplayBase::getVoidingModeTooltip));
        } else {
            builder.widget((new ImageWidget(173, 161, 18, 18, GuiTextures.BUTTON_VOID_NONE)).setTooltip("gregtech.gui.multiblock_voiding_not_supported"));
        }

        label30: {
            if (this instanceof IDistinctBusController) {
                IDistinctBusController distinct = (IDistinctBusController)this;
                if (distinct.canBeDistinct()) {
                    var10007 = GuiTextures.BUTTON_DISTINCT_BUSES;
                    Objects.requireNonNull(distinct);
                    var10008 = distinct::isDistinct;
                    Objects.requireNonNull(distinct);
                    builder.widget((new ImageCycleButtonWidget(173, 143, 18, 18, var10007, var10008, distinct::setDistinct)).setTooltipHoverString((i) -> {
                        return "gregtech.multiblock.universal.distinct_" + (i == 0 ? "disabled" : "enabled");
                    }));
                    break label30;
                }
            }

            builder.widget((new ImageWidget(173, 143, 18, 18, GuiTextures.BUTTON_NO_DISTINCT_BUSES)).setTooltip("gregtech.multiblock.universal.distinct_not_supported"));
        }

        builder.widget(this.getFlexButton(173, 125, 18, 18));
        builder.bindPlayerInventory(entityPlayer.inventory, 125);
        return builder;
    }

    private String getPlanetValue(int index) {
        return String.valueOf(this.planet[index]);
    }

    private void setPlanetValue(String val, int index) {
        this.planet[index] = Integer.parseInt(val);
    }

    private String getFluidValue(int index) {
        return String.valueOf(this.fluidNumber[index]);
    }

    private void setFluidValue(String val, int index) {
        this.fluidNumber[index] = Integer.parseInt(val);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        NBTTagList nbtTagPlanetList = new NBTTagList();
        NBTTagList nbtTagFLuidList = new NBTTagList();
        for(int i = 0; i < 4; i++) {
            NBTTagCompound planetTag = new NBTTagCompound();
            NBTTagCompound fluidTag = new NBTTagCompound();

            planetTag.setInteger("planet", this.planet[i]);
            nbtTagPlanetList.appendTag(planetTag);

            fluidTag.setInteger("fluid", this.fluidNumber[i]);
            nbtTagFLuidList.appendTag(fluidTag);
        }
        data.setTag("planets", nbtTagPlanetList);
        data.setTag("fluids", nbtTagFLuidList);
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        NBTTagList nbtTagPlanetList = data.getTagList("planets", Constants.NBT.TAG_COMPOUND);
        NBTTagList nbtTagFLuidList = data.getTagList("fluids", Constants.NBT.TAG_COMPOUND);
        for(int i = 0; i < 4; i++) {
            NBTTagCompound planetTag = nbtTagPlanetList.getCompoundTagAt(i);
            NBTTagCompound fluidTag = nbtTagFLuidList.getCompoundTagAt(i);

            this.planet[i] = planetTag.getInteger("planet");
            this.fluidNumber[i] = fluidTag.getInteger("fluid");
        }
    }

    @Override
    public void updateFormedValid() {
        super.updateFormedValid();
        if(!this.isWorkingEnabled()) {
            return;
        }


        if(!drainEnergy(true)) {
            if (this.progressTime >= 2) {
                if (ConfigHolder.machines.recipeProgressLowEnergy) this.progressTime = 1;
                else this.progressTime = Math.max(1, this.progressTime - 2);
            }
            return;
        }

        if(!checkRecipes()) {
            setActive(false);
            return;
        }
        else {
            drainEnergy(false);
            setActive(true);

            List<FluidStack> fluidStacks = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                if(GAS_SIPHON_RECIPES.get(this.planet[i] + "," + this.fluidNumber[i]) != null) {
                    fluidStacks.add(GAS_SIPHON_RECIPES.get(this.planet[i] + "," + this.fluidNumber[i]));
                }
            }

            for(FluidStack fluidStack : fluidStacks) {
                this.outputFluidInventory.fill(fluidStack, true);
            }
        }
    }

    protected boolean checkRecipes() {

        if(this.moduleTier > 1) {
            for (int i = 0; i < 4; i++) {
                FluidStack stack = GAS_SIPHON_RECIPES.get(this.planet[i] + "," + this.fluidNumber[i]);
                if(stack != null) {
                    return true;
                }
            }
        }
        else {
            FluidStack stack = GAS_SIPHON_RECIPES.get(this.planet[0]+ "," + this.fluidNumber[0]);
            return stack != null;
        }
        return false;
    }
}