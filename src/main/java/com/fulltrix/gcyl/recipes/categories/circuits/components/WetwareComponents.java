package com.fulltrix.gcyl.recipes.categories.circuits.components;

import com.fulltrix.gcyl.GCYLConfig;
import gregtech.api.metatileentity.multiblock.CleanroomType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static com.fulltrix.gcyl.item.GCYLCoreItems.*;
import static com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps.BIO_REACTOR_RECIPES;
import static com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES;
import static gregtech.api.GTValues.L;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

public class WetwareComponents {

    public static void init() {
        components();
        bacteriaCultures();
    }

    private static void components() {

        // wetware crystal chip
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(600).EUt(80000)
                        .inputs(CRYSTAL_SYSTEM_ON_CHIP.getStackForm())
                                .notConsumable(lens, MagnetoResonatic)
                                        .outputs(ADVANCED_CRYSTAL_SOC.getStackForm())
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        // wetware soc
        CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(160000)
                .inputs(HIGHLY_ADVANCED_SOC.getStackForm(8))
                .inputs(ADVANCED_CRYSTAL_SOC.getStackForm())
                        .outputs(LIVING_SOC.getStackForm())
                                        .fluidInputs(SterileBioGrowthMedium.getFluid(50))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                                                .buildAndRegister();


        // SMD Capacitor
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(30720)
                .input(wireFine, NaquadahAlloy, 8)
                .fluidInputs(Polytetrafluoroethylene.getFluid(L))
                .input(foil, NaquadahEnriched, 4)
                .input(foil, Polybenzimidazole, 4)
                .outputs(SMD_CAPACITOR_WETWARE.getStackForm(32))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        // SMD Resistor
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(30720)
                .input(wireFine, NaquadahAlloy, 8)
                .fluidInputs(Polytetrafluoroethylene.getFluid(L))
                .input(plate, Naquadria)
                .input(plate, RutheniumDioxide)
                .outputs(SMD_RESISTOR_WETWARE.getStackForm(32))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        // SMD Transistor
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(30720)
                .input(wireFine, NaquadahAlloy, 8)
                .fluidInputs(Polytetrafluoroethylene.getFluid(L))
                .input(plate, NaquadahEnriched)
                .outputs(SMD_TRANSISTOR_WETWARE.getStackForm(32))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        // SMD Diode
        ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(30720)
                .input(wireFine, NaquadahAlloy, 8)
                .fluidInputs(Polytetrafluoroethylene.getFluid(L))
                .input(dust, Naquadria)
                .input(dust, LanthanumCalciumManganate)
                .outputs(SMD_DIODE_WETWARE.getStackForm(32))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        // Neuro Support Unit
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(wireFine, NaquadahAlloy, 16)
                .input(plate, Tritanium, 2)
                .inputs(ELECTRIC_PUMP_LuV.getStackForm())
                .input(pipeSmallFluid, Polybenzimidazole, 2)
                .input(dust, Shewanella, 2)
                .fluidInputs(Polybenzimidazole.getFluid(L * 8))
                .outputs(NEURO_SUPPORT_UNIT.getStackForm())
                .EUt(30720)
                .duration(250)
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        // Cyber Processing Unit

        /*
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(ELECTRICALLY_WIRED_PETRI_DISH.getStackForm())
                .input(foil, SiliconeRubber, 8)
                .input(wireFine, Gold, 64)
                .inputs(SMD_TRANSISTOR_WETWARE.getStackForm(4))
                .inputs(SMD_DIODE_WETWARE.getStackForm(4))
                .inputs(SMD_RESISTOR_WETWARE.getStackForm(4))
                .inputs(SMD_CAPACITOR_WETWARE.getStackForm(4))
                .inputs(WETWARE_BOARD.getStackForm())
                .inputs(STEM_CELLS.getStackForm())
                .inputs(NEURO_SUPPORT_UNIT.getStackForm())
                .fluidInputs(Polybenzimidazole.getFluid(L * 8))
                .fluidInputs(SterileGrowthMedium.getFluid(1000))
                .fluidInputs(Titanium.getFluid(L * 9))
                .outputs(CYBER_PROCESSING_UNIT.getStackForm(8))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .EUt(30720 * 4)
                .duration(250)
                .buildAndRegister();
         */

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(ELECTRICALLY_WIRED_PETRI_DISH.getStackForm())
                .input(foil, SiliconeRubber, 64)
                .input(wireFine, Gold, 64)
                .inputs(SMD_TRANSISTOR_WETWARE.getStackForm(4))
                .inputs(SMD_DIODE_WETWARE.getStackForm(4))
                .inputs(SMD_RESISTOR_WETWARE.getStackForm(4))
                .inputs(SMD_CAPACITOR_WETWARE.getStackForm(4))
                .inputs(WETWARE_CIRCUIT_BOARD.getStackForm())
                .inputs(STEM_CELLS.getStackForm(16))
                .inputs(NEURO_SUPPORT_UNIT.getStackForm())
                .fluidInputs(Polybenzimidazole.getFluid(L * 8))
                .fluidInputs(SterileGrowthMedium.getFluid(1000))
                .fluidInputs(Titanium.getFluid(L * 9))
                .fluidInputs(Cryotheum.getFluid(1000))
                .outputs(CYBER_PROCESSING_UNIT.getStackForm(GCYLConfig.recipes.circuitCoresPerCraft))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .EUt(30720 * 4)
                .duration(250)
                .buildAndRegister();
    }

    private static void bacteriaCultures() {

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(160).EUt(480)
                .fluidInputs(Polystyrene.getFluid(L / 4))
                .notConsumable(SHAPE_MOLD_CYLINDER.getStackForm())
                .outputs(PETRI_DISH.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(7680).duration(100)
                .input(wireFine, Titanium)
                .fluidInputs(Polyethylene.getFluid(1008))
                .inputs(STERILIZED_PETRI_DISH.getStackForm())
                .outputs(ELECTRICALLY_WIRED_PETRI_DISH.getStackForm())
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(120).duration(200)
                .fluidInputs(Biomass.getFluid(1000))
                .fluidInputs(SaltWater.getFluid(1000))
                .output(dust, GreenAlgae)
                .output(dust, RedAlgae)
                .output(dust, BrownAlgae)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().EUt(30).duration(150)
                .input(dust, RedAlgae)
                .output(dust, DryRedAlgae)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder().EUt(30).duration(75)
                .input(dust, DryRedAlgae)
                .output(dust, RedAlgaePowder)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().EUt(30).duration(150)
                .input(dust, RedAlgaePowder)
                .input(dustSmall, SodaAsh)
                .output(dust, PreFreezeAgar)
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder().EUt(120).duration(100)
                .input(dust, PreFreezeAgar)
                .output(dust, FrozenAgarCrystals)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().EUt(30).duration(200)
                .input(dust, FrozenAgarCrystals)
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidOutputs(WaterAgarMix.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().EUt(30).duration(50)
                .fluidInputs(WaterAgarMix.getFluid(1000))
                .output(dust, Agar)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(480).duration(100)
                .inputs(STERILIZED_PETRI_DISH.getStackForm())
                .input(dust, Agar)
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .outputs(CLEAN_CULTURE.getStackForm())
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(30720).duration(500)
                .inputs(PIEZOELECTRIC_CRYSTAL.getStackForm())
                .input(stickLong, RhodiumPlatedPalladium)
                .outputs(ULTRASONIC_HOMOGENIZER.getStackForm())
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(30720).duration(2400)
                .input(dust, GreenAlgae)
                .inputs(CLEAN_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .outputs(SHEWANELLA_CULTURE.getStackForm())
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(30720).duration(2400)
                .inputs(new ItemStack(Blocks.DIRT))
                .inputs(CLEAN_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .outputs(BREVIBACTERIUM_CULTURE.getStackForm())
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(30720).duration(2400)
                .inputs(new ItemStack(Blocks.DIRT, 1, 1))
                .inputs(CLEAN_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .outputs(CUPRIVADUS_CULTURE.getStackForm())
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(30720).duration(2400)
                .inputs(new ItemStack(Items.BEEF))
                .inputs(CLEAN_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .outputs(ESCHERICHIA_CULTURE.getStackForm())
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(30720).duration(2400)
                .fluidInputs(Milk.getFluid(1000))
                .inputs(CLEAN_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .outputs(BIFIDOBACTERIUM_CULTURE.getStackForm())
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(30720).duration(2400)
                .inputs(new ItemStack(Items.ROTTEN_FLESH))
                .inputs(CLEAN_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .outputs(STREPTOCOCCUS_CULTURE.getStackForm())
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(30720).duration(200)
                .inputs(SHEWANELLA_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .output(dust, Shewanella)
                .fluidOutputs(DepletedGrowthMedium.getFluid(1000))
                .outputs(CONTAMINATED_PETRI_DISH.getStackForm())
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();


        BIO_REACTOR_RECIPES.recipeBuilder().EUt(30720).duration(200)
                .inputs(STREPTOCOCCUS_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .output(dust, StreptococcusPyogenes)
                .fluidOutputs(DepletedGrowthMedium.getFluid(1000))
                .outputs(CONTAMINATED_PETRI_DISH.getStackForm())
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(30720).duration(200)
                .inputs(BIFIDOBACTERIUM_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .output(dust, BifidobacteriumBreve)
                .fluidOutputs(DepletedGrowthMedium.getFluid(1000))
                .outputs(CONTAMINATED_PETRI_DISH.getStackForm())
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(30720).duration(200)
                .inputs(ESCHERICHIA_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .output(dust, EschericiaColi)
                .fluidOutputs(DepletedGrowthMedium.getFluid(1000))
                .outputs(CONTAMINATED_PETRI_DISH.getStackForm())
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(30720).duration(200)
                .inputs(BREVIBACTERIUM_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .output(dust, BrevibacteriumFlavium)
                .fluidOutputs(DepletedGrowthMedium.getFluid(1000))
                .outputs(CONTAMINATED_PETRI_DISH.getStackForm())
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(30720).duration(200)
                .inputs(CUPRIVADUS_CULTURE.getStackForm())
                .fluidInputs(BacterialGrowthMedium.getFluid(1000))
                .output(dust, CupriavidusNecator)
                .fluidOutputs(DepletedGrowthMedium.getFluid(1000))
                .outputs(CONTAMINATED_PETRI_DISH.getStackForm())
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(120).duration(100)
                .input(dust, Shewanella)
                .output(dust, Shewanella)
                .fluidInputs(BacterialGrowthMedium.getFluid(250))
                .fluidOutputs(DepletedGrowthMedium.getFluid(250))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(120).duration(100)
                .input(dust, BrevibacteriumFlavium)
                .output(dust, BrevibacteriumFlavium)
                .fluidInputs(BacterialGrowthMedium.getFluid(250))
                .fluidOutputs(DepletedGrowthMedium.getFluid(250))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(120).duration(100)
                .input(dust, EschericiaColi)
                .output(dust, EschericiaColi)
                .fluidInputs(BacterialGrowthMedium.getFluid(250))
                .fluidOutputs(DepletedGrowthMedium.getFluid(250))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(120).duration(100)
                .input(dust, StreptococcusPyogenes)
                .output(dust, StreptococcusPyogenes)
                .fluidInputs(BacterialGrowthMedium.getFluid(250))
                .fluidOutputs(DepletedGrowthMedium.getFluid(250))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(120).duration(100)
                .input(dust, BifidobacteriumBreve)
                .output(dust, BifidobacteriumBreve)
                .fluidInputs(BacterialGrowthMedium.getFluid(250))
                .fluidOutputs(DepletedGrowthMedium.getFluid(250))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(120).duration(100)
                .input(dust, CupriavidusNecator)
                .output(dust, CupriavidusNecator)
                .fluidInputs(BacterialGrowthMedium.getFluid(250))
                .fluidOutputs(DepletedGrowthMedium.getFluid(250))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();


    }
}
