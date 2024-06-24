package com.fulltrix.gcyl.recipes.categories.circuits.components;

import com.fulltrix.gcyl.GCYLConfig;
import com.fulltrix.gcyl.api.multi.GCYLCleanroomType;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.unification.material.MarkerMaterials;

import static com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps.ADVANCED_MIXER_RECIPES;
import static com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps.BIO_REACTOR_RECIPES;
import static com.fulltrix.gcyl.item.GCYLCoreItems.*;
import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static gregtech.api.GTValues.L;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.Materials.UUMatter;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.api.unification.ore.OrePrefix.wireFine;
import static gregtech.common.items.MetaItems.*;

public class BioWareComponents {
    public static void init() {

        //BIO CELLS
        BIO_REACTOR_RECIPES.recipeBuilder().duration(600).EUt(GTValues.VA[GTValues.UV])
                .inputs(STEM_CELLS.getStackForm(32))
                .input(dust, Tennessine, 16)
                .outputs(BIO_CELLS.getStackForm(32))
                .fluidInputs(SterileBioGrowthMedium.getFluid(2000))
                .fluidOutputs(DepletedGrowthMedium.getFluid(1000)) //TODO mutagen
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        //RAW BIO GROWTH MEDIUM
        BIO_REACTOR_RECIPES.recipeBuilder().duration(1200).EUt(GTValues.VA[GTValues.ZPM])
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .inputs(STEM_CELLS.getStackForm(16))
                .input(dust, Tritanium, 4)
                .fluidInputs(RawGrowthMedium.getFluid(16000))
                .fluidOutputs(RawBioGrowthMedium.getFluid(8000))
                .buildAndRegister();

        //BIO GROWTH MEDIUM
        MIXER_RECIPES.recipeBuilder().duration(200).EUt(GTValues.VA[GTValues.LuV])
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .fluidInputs(Sulfanilamide.getFluid(1000))
                .fluidInputs(RawBioGrowthMedium.getFluid(1000))
                .fluidOutputs(SterileBioGrowthMedium.getFluid(2000))
                .buildAndRegister();

        //BIO WAFER
        AUTOCLAVE_RECIPES.recipeBuilder().EUt(1966080).duration(600)
                .cleanroom(GCYLCleanroomType.ISO3)
                .input(OPTICAL_SOC_WAFER)
                .input(dust, HeavyQuarkDegenerateMatter, 16)
                .output(BIO_WAFER)
                .fluidInputs(SterileBioGrowthMedium.getFluid(8000))
                .buildAndRegister();



        //BIOWARE SOC
        CUTTER_RECIPES.recipeBuilder().EUt(7864320).duration(150)
                .fluidInputs(UUMatter.getFluid(8000))
                .inputs(BIO_WAFER.getStackForm())
                .outputs(LIVING_BIO_SOC.getStackForm(16))
                .outputs(BIO_CELLS.getStackForm(16))
                .cleanroom(GCYLCleanroomType.ISO3)
                .buildAndRegister();

        //SMDS
        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(30720 * 4)
                .input(wireFine, Dubnium, 8)
                .input(plate, GermaniumTungstenNitride, 4)
                .fluidInputs(Polyimide.getFluid(L * 2))
                .outputs(SMD_TRANSISTOR_BIOWARE.getStackForm(32))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(30720 * 4)
                .input(wireFine, PEDOT, 8)
                .input(foil, Polytetrafluoroethylene, 4)
                .input(foil, BariumTitanate, 4)
                .fluidInputs(Polyimide.getFluid(L * 2))
                .outputs(SMD_CAPACITOR_BIOWARE.getStackForm(32))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(30720 * 4)
                .input(wireFine, Osmiridium, 8)
                .input(dust, AluminiumComplex)
                .input(dust, CopperGalliumIndiumSelenide)
                .fluidInputs(Polyimide.getFluid(L * 2))
                .outputs(SMD_DIODE_BIOWARE.getStackForm(32))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(30720 * 4)
                .input(wireFine, NaquadahAlloy, 6)
                .input(plate, BismuthRuthenate)
                .input(plate, BismuthIridiate)
                .fluidInputs(Polyimide.getFluid(L * 2))
                .outputs(SMD_RESISTOR_BIOWARE.getStackForm(24))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        //NEURO PROCESSOR
        /*
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(foil, SiliconeRubber, 32)
                .input(wireFine, NaquadahAlloy, 16)
                .inputs(SMD_TRANSISTOR_BIOWARE.getStackForm(16))
                .inputs(SMD_RESISTOR_BIOWARE.getStackForm(16))
                .inputs(SMD_DIODE_BIOWARE.getStackForm(16))
                .inputs(SMD_CAPACITOR_BIOWARE.getStackForm(16))
                .inputs(CYBER_PROCESSING_UNIT.getStackForm())
                .inputs(STEM_CELLS.getStackForm(4))
                .fluidInputs(Tritanium.getFluid(144))
                .outputs(NEURO_PROCESSOR.getStackForm(4))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .EUt(30720 * 16)
                .duration(150)
                .buildAndRegister();
         */

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(BIO_CIRCUIT_BOARD.getStackForm())
                .input(foil, SiliconeRubber, 64)
                .input(wireFine, NaquadahAlloy, 16)
                .inputs(SMD_TRANSISTOR_BIOWARE.getStackForm(16))
                .inputs(SMD_RESISTOR_BIOWARE.getStackForm(16))
                .inputs(SMD_DIODE_BIOWARE.getStackForm(16))
                .inputs(SMD_CAPACITOR_BIOWARE.getStackForm(16))
                .inputs(CYBER_PROCESSING_UNIT.getStackForm())
                .inputs(BIO_CELLS.getStackForm(16))
                .fluidInputs(Tritanium.getFluid(144))
                .fluidInputs(UUMatter.getFluid(500))
                .fluidInputs(SterileBioGrowthMedium.getFluid(500))
                .fluidInputs(SupercooledCryotheum.getFluid(2000))
                .outputs(NEURO_PROCESSOR.getStackForm(GCYLConfig.recipes.circuitCoresPerCraft))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .EUt(30720 * 16)
                .duration(150)
                .buildAndRegister();
    }
}
