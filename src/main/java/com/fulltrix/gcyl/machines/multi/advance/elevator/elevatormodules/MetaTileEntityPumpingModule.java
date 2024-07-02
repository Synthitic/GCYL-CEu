package com.fulltrix.gcyl.machines.multi.advance.elevator.elevatormodules;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import com.fulltrix.gcyl.client.ClientHandler;
import com.fulltrix.gcyl.machines.multi.advance.elevator.MetaTileEntityModuleBase;
import gregtech.api.capability.GregtechTileCapabilities;
import gregtech.api.capability.IControllable;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.resources.TextureArea;
import gregtech.api.gui.widgets.*;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.common.ConfigHolder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
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

    private String getPlanetValue(int index) {
        return String.valueOf(this.planet[index]);
    }

    private void setPlanetValue(String val, int index) {
        try {
            this.planet[index] = Integer.parseInt(val);
        }
        catch (NumberFormatException e) {
            this.planet[index] = 0;
        }

    }

    private String getFluidValue(int index) {
        return String.valueOf(this.fluidNumber[index]);
    }

    private void setFluidValue(String val, int index) {
        try {
            this.fluidNumber[index] = Integer.parseInt(val);
        }
        catch (NumberFormatException e) {
            this.fluidNumber[index] = 0;
        }
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