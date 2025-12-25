package com.fulltrix.gcyl.machines.multi.advance.elevator.elevatormodules;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import com.cleanroommc.modularui.api.widget.IWidget;
import com.cleanroommc.modularui.value.sync.IntSyncValue;
import com.cleanroommc.modularui.widgets.TextWidget;
import com.cleanroommc.modularui.widgets.textfield.TextFieldWidget;
import com.fulltrix.gcyl.client.ClientHandler;
import com.fulltrix.gcyl.machines.multi.advance.elevator.MetaTileEntityModuleBase;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.gui.widgets.*;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.ui.MultiblockUIFactory;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.util.GTTransferUtils;
import gregtech.api.util.KeyUtil;
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

import static com.fulltrix.gcyl.recipes.categories.elevator.SpacePumpRecipes.GAS_SIPHON_RECIPES;


public class MetaTileEntityPumpingModule extends MetaTileEntityModuleBase {

    private IMultipleTankHandler outputFluidInventory;
    @SuppressWarnings("FieldMayBeFinal")
    private int[] planet = {0, 0, 0, 0};

    @SuppressWarnings("FieldMayBeFinal")
    private int[] fluidNumber = {0, 0, 0, 0};

    public MetaTileEntityPumpingModule(ResourceLocation metaTileEntityId, int tier, int moduleTier, int minMotorTier) {
        super(metaTileEntityId, tier, moduleTier, minMotorTier);
        setMaxProgress(moduleTier == 3 ? 20 : 80);
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
            return new MetaTileEntityPumpingModule(this.metaTileEntityId, this.tier, this.moduleTier, this.minMotorTier);
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
    protected MultiblockUIFactory createUIFactory() {
        return super.createUIFactory().addScreenChildren(((parentWidget, panelSyncManager) -> {
            IntSyncValue firstPlanetValueSync = new IntSyncValue(this::getFirstPlanetValue, this::setFirstPlanetValue);
            IntSyncValue secondPlanetValueSync = new IntSyncValue(this::getSecondPlanetValue, this::setSecondPlanetValue);
            IntSyncValue thirdPlanetValueSync = new IntSyncValue(this::getThirdPlanetValue, this::setThirdPlanetValue);
            IntSyncValue fourthPlanetValueSync = new IntSyncValue(this::getFourthPlanetValue, this::setFourthPlanetValue);

            IntSyncValue firstFluidValueSync = new IntSyncValue(this::getFirstFluidValue, this::setFirstFluidValue);
            IntSyncValue secondFluidValueSync = new IntSyncValue(this::getSecondFluidValue, this::setSecondFluidValue);
            IntSyncValue thirdFluidValueSync = new IntSyncValue(this::getThirdFluidValue, this::setThirdFluidValue);
            IntSyncValue fourthFluidValueSync = new IntSyncValue(this::getFourthFluidValue, this::setFourthFluidValue);

            int padding = 18;
            parentWidget.child(KeyUtil.lang(TextFormatting.LIGHT_PURPLE, "gcyl.multiblock.pump_module.planet").asWidget()
                    .pos(10, 9 + padding))
                    .child(new TextFieldWidget()
                            .pos(43, 7 + padding)
                            .size(25, 10)
                            .value(firstPlanetValueSync))
                    .child(KeyUtil.lang(TextFormatting.LIGHT_PURPLE, "gcyl.multiblock.pump_module.fluid").asWidget()
                            .pos(10, 9 + 2 * padding))
                    .child(new TextFieldWidget()
                            .pos(43, 7 + 2 * padding)
                            .size(25, 10)
                            .value(firstFluidValueSync));
            if (moduleTier > 1) {
                parentWidget.child(KeyUtil.lang(TextFormatting.LIGHT_PURPLE, "gcyl.multiblock.pump_module.planet").asWidget()
                                .pos(100, 9 + padding))
                        .child(new TextFieldWidget()
                                .pos(133, 7 + padding)
                                .size(25, 10)
                                .value(secondPlanetValueSync))
                        .child(KeyUtil.lang(TextFormatting.LIGHT_PURPLE, "gcyl.multiblock.pump_module.fluid").asWidget()
                                .pos(100, 9 + 2 * padding))
                        .child(new TextFieldWidget()
                                .pos(133, 7 + 2 * padding)
                                .size(25, 10)
                                .value(secondFluidValueSync))

                        .child(KeyUtil.lang(TextFormatting.LIGHT_PURPLE, "gcyl.multiblock.pump_module.planet").asWidget()
                                .pos(10, 9 + 3 * padding))
                        .child(new TextFieldWidget()
                                .pos(43, 7 + 3 * padding)
                                .size(25, 10)
                                .value(thirdPlanetValueSync))
                        .child(KeyUtil.lang(TextFormatting.LIGHT_PURPLE, "gcyl.multiblock.pump_module.fluid").asWidget()
                                .pos(10, 9 + 4 * padding))
                        .child(new TextFieldWidget()
                                .pos(43, 7 + 4 * padding)
                                .size(25, 10)
                                .value(thirdFluidValueSync))

                        .child(KeyUtil.lang(TextFormatting.LIGHT_PURPLE, "gcyl.multiblock.pump_module.planet").asWidget()
                                .pos(100, 9 + 3 * padding))
                        .child(new TextFieldWidget()
                                .pos(133, 7 + 3 * padding)
                                .size(25, 10)
                                .value(fourthPlanetValueSync))
                        .child(KeyUtil.lang(TextFormatting.LIGHT_PURPLE, "gcyl.multiblock.pump_module.fluid").asWidget()
                                .pos(100, 9 + 4 * padding))
                        .child(new TextFieldWidget()
                                .pos(133, 7 + 4 * padding)
                                .size(25, 10)
                                .value(fourthFluidValueSync));
            }
        }));
    }



    /*
    @Override
    protected ModularUI.Builder createUITemplate(EntityPlayer entityPlayer) {
        ModularUI.Builder builder = ModularUI.builder(GuiTextures.BACKGROUND, 198, 208);;
        builder.image(4, 4, 190, 117, GuiTextures.DISPLAY);
        builder.widget((new IndicatorImageWidget(174, 101, 17, 17, this.getLogo())).setWarningStatus(this.getWarningLogo(), this::addWarningText).setErrorStatus(this.getErrorLogo(), this::addErrorText));

        builder.label(9, 9, this.getMetaFullName(), 16777215);

        int size = 18;
        int padding = 3;



            builder.widget(new LabelWidget(10, 9 + (size + padding), "gcyl.multiblock.pump_module.planet", 0x55FF55));
            builder.widget(new TextFieldWidget2(50, 9 + (size + padding), size * size, size, () -> this.getPlanetValue(0), s -> this.setPlanetValue(s, 0)).setMaxLength(3).setAllowedChars(TextFieldWidget2.WHOLE_NUMS));
            builder.widget(new LabelWidget(10, 9 + 2 * (size + padding), "gcyl.multiblock.pump_module.fluid", 0xFF55FF));
            builder.widget(new TextFieldWidget2(50, 9 + 2 * (size + padding), size * size, size, () -> this.getFluidValue(0), s -> this.setFluidValue(s, 0)).setMaxLength(3).setAllowedChars(TextFieldWidget2.WHOLE_NUMS));

            if (this.moduleTier > 1) {
                builder.widget(new LabelWidget(100, 9 + (size + padding), "gcyl.multiblock.pump_module.planet", 0x55FF55));
                builder.widget(new TextFieldWidget2(140, 9 + (size + padding), size * size, size, () -> this.getPlanetValue(1), s -> this.setPlanetValue(s, 1)).setMaxLength(3).setAllowedChars(TextFieldWidget2.WHOLE_NUMS));
                builder.widget(new LabelWidget(100, 9 + 2 * (size + padding), "gcyl.multiblock.pump_module.fluid", 0xFF55FF));
                builder.widget(new TextFieldWidget2(140, 9 + 2 * (size + padding), size * size, size, () -> this.getFluidValue(1), s -> this.setFluidValue(s, 1)).setMaxLength(3).setAllowedChars(TextFieldWidget2.WHOLE_NUMS));

                builder.widget(new LabelWidget(10, 9 + 3 * (size + padding), "gcyl.multiblock.pump_module.planet", 0x55FF55));
                builder.widget(new TextFieldWidget2(50, 9 + 3 * (size + padding), size * size, size, () -> this.getPlanetValue(2), s -> this.setPlanetValue(s, 2)).setMaxLength(3).setAllowedChars(TextFieldWidget2.WHOLE_NUMS));
                builder.widget(new LabelWidget(10, 9 + 4 * (size + padding), "gcyl.multiblock.pump_module.fluid", 0xFF55FF));
                builder.widget(new TextFieldWidget2(50, 9 + 4 * (size + padding), size * size, size, () -> this.getFluidValue(2), s -> this.setFluidValue(s, 2)).setMaxLength(3).setAllowedChars(TextFieldWidget2.WHOLE_NUMS));

                builder.widget(new LabelWidget(100, 9 + 3 * (size + padding), "gcyl.multiblock.pump_module.planet", 0x55FF55));
                builder.widget(new TextFieldWidget2(140, 9 + 3 * (size + padding), size * size, size, () -> this.getPlanetValue(3), s -> this.setPlanetValue(s, 3)).setMaxLength(3).setAllowedChars(TextFieldWidget2.WHOLE_NUMS));
                builder.widget(new LabelWidget(100, 9 + 4 * (size + padding), "gcyl.multiblock.pump_module.fluid", 0xFF55FF));
                builder.widget(new TextFieldWidget2(140, 9 + 4 * (size + padding), size * size, size, () -> this.getFluidValue(3), s -> this.setFluidValue(s, 3)).setMaxLength(3).setAllowedChars(TextFieldWidget2.WHOLE_NUMS));
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


        builder.widget((new ImageWidget(173, 161, 18, 18, GuiTextures.BUTTON_VOID_NONE)).setTooltip("gregtech.gui.multiblock_voiding_not_supported"));


        builder.widget(this.getFlexButton(173, 125, 18, 18));
        builder.bindPlayerInventory(entityPlayer.inventory, 125);
        return builder;
    }
     */

    private int getFirstPlanetValue() {
        return this.planet[0];
    }

    private void setFirstPlanetValue(int val) {
        this.planet[0] = val;
    }

    private int getSecondPlanetValue() {
        return this.planet[1];
    }

    private void setSecondPlanetValue(int val) {
        this.planet[1] = val;
    }

    private int getThirdPlanetValue() {
        return this.planet[2];
    }

    private void setThirdPlanetValue(int val) {
        this.planet[2] = val;
    }

    private int getFourthPlanetValue() {
        return this.planet[3];
    }

    private void setFourthPlanetValue(int val) {
        this.planet[3] = val;
    }

    private int getFirstFluidValue() {
        return this.fluidNumber[0];
    }

    private void setFirstFluidValue(int val) {
            this.fluidNumber[0] = val;
    }

    private int getSecondFluidValue() {
        return this.fluidNumber[1];
    }

    private void setSecondFluidValue(int val) {
        this.fluidNumber[1] = val;
    }

    private int getThirdFluidValue() {
        return this.fluidNumber[2];
    }

    private void setThirdFluidValue(int val) {
        this.fluidNumber[2] = val;
    }

    private int getFourthFluidValue() {
        return this.fluidNumber[3];
    }

    private void setFourthFluidValue(int val) {
        this.fluidNumber[3] = val;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        NBTTagList nbtTagPlanetList = new NBTTagList();
        NBTTagList nbtTagFluidList = new NBTTagList();
        for(int i = 0; i < 4; i++) {
            NBTTagCompound planetTag = new NBTTagCompound();
            NBTTagCompound fluidTag = new NBTTagCompound();

            planetTag.setInteger("planet", this.planet[i]);
            nbtTagPlanetList.appendTag(planetTag);

            fluidTag.setInteger("fluid", this.fluidNumber[i]);
            nbtTagFluidList.appendTag(fluidTag);
        }
        data.setTag("planets", nbtTagPlanetList);
        data.setTag("fluids", nbtTagFluidList);
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        NBTTagList nbtTagPlanetList = data.getTagList("planets", Constants.NBT.TAG_COMPOUND);
        NBTTagList nbtTagFluidList = data.getTagList("fluids", Constants.NBT.TAG_COMPOUND);
        for(int i = 0; i < 4; i++) {
            NBTTagCompound planetTag = nbtTagPlanetList.getCompoundTagAt(i);
            NBTTagCompound fluidTag = nbtTagFluidList.getCompoundTagAt(i);

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

        List<FluidStack> fluidStacks = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if(GAS_SIPHON_RECIPES.get(this.planet[i] + "," + this.fluidNumber[i]) != null) {
                fluidStacks.add(GAS_SIPHON_RECIPES.get(this.planet[i] + "," + this.fluidNumber[i]));
            }
        }

        if (this.canVoidRecipeFluidOutputs() && (this.outputFluidInventory == null || !GTTransferUtils.addFluidsToFluidHandler(this.outputFluidInventory, true, fluidStacks)))
            return;


        if(!drainEnergy(true)) {
            if (this.progressTime >= 2) {
                if (ConfigHolder.machines.recipeProgressLowEnergy) this.progressTime = 1;
                else this.progressTime = Math.max(1, this.progressTime - 2);
            }
            return;
        }

        if(progressTime == 0 && !checkRecipes()) {
            setActive(false);
        }
        else {
            drainEnergy(false);
            setActive(true);

            progressTime++;
            if (progressTime % getMaxProgress() != 0) return;
            progressTime = 0;

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