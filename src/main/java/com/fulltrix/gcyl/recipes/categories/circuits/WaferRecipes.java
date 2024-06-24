package com.fulltrix.gcyl.recipes.categories.circuits;

import com.fulltrix.gcyl.api.multi.GCYLCleanroomType;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;

import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static com.fulltrix.gcyl.item.GCYLCoreItems.*;
import static com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES;
import static com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps.CHEMICAL_PLANT_RECIPES;
import static gregtech.api.GTValues.L;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.MarkerMaterials.Color.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

public class WaferRecipes {
    public static void init() { //TODO remove neutronium, verify recipes work

        // BOULES ======================================================================================================

        // Rutherfordium Boule
        BLAST_RECIPES.recipeBuilder().EUt(7680).duration(1500)
                .input(block, Silicon, 16)
                .input(ingot, Rutherfordium)
                .notConsumable(new IntCircuitIngredient(1))
                .blastFurnaceTemp(7200)
                .outputs(BOULE_RUTHERFORDIUM.getStackForm())
                .fluidInputs(Krypton.getFluid(8000))
                .buildAndRegister();

        CUTTER_RECIPES.recipeBuilder().EUt(6144).duration(1600)
                .inputs(BOULE_RUTHERFORDIUM.getStackForm())
                .outputs(WAFER_RUTHERFORDIUM.getStackForm(64))
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

        // Dubnium Boule
        BLAST_RECIPES.recipeBuilder().EUt(30720).duration(1500)
                .input(block, Silicon, 32)
                .input(ingot, Dubnium)
                .notConsumable(new IntCircuitIngredient(1))
                .blastFurnaceTemp(8600)
                .outputs(BOULE_DUBNIUM.getStackForm())
                .fluidInputs(Xenon.getFluid(8000))
                .buildAndRegister();

        CUTTER_RECIPES.recipeBuilder().EUt(24576).duration(1600)
                .inputs(BOULE_DUBNIUM.getStackForm())
                .outputs(WAFER_DUBNIUM.getStackForm(64))
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

        // Neutronium Boule
        BLAST_RECIPES.recipeBuilder().EUt(122880).duration(1500)
                .input(block, Silicon, 64)
                .input(ingot, Neutronium)
                .notConsumable(new IntCircuitIngredient(1))
                .blastFurnaceTemp(9100)
                .outputs(NEUTRONIUM_BOULE.getStackForm())
                .fluidInputs(Radon.getFluid(8000))
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

        CUTTER_RECIPES.recipeBuilder().EUt(98304).duration(1600)
                .inputs(NEUTRONIUM_BOULE.getStackForm())
                .outputs(NEUTRONIUM_WAFER.getStackForm(64))
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

        // Hassium Boule
        AUTOCLAVE_RECIPES.recipeBuilder().duration(120).EUt(3400000)
                .input(dustTiny, MetastableHassium)
                .fluidInputs(Nitrogen.getFluid(1000))
                .outputs(HASSIUM_SEED_CRYSTAL.getStackForm())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(240).EUt(17000000).blastFurnaceTemp(11200)
                .input(dust, MetastableHassium, 2)
                .inputs(HASSIUM_SEED_CRYSTAL.getStackForm())
                .circuitMeta(3)
                .fluidInputs(Xenon.getFluid(1000))
                .outputs(HASSIUM_BOULE.getStackForm())
                .buildAndRegister();

        CUTTER_RECIPES.recipeBuilder().duration(290).EUt(175000)
                .inputs(HASSIUM_BOULE.getStackForm())
                .outputs(HASSIUM_SEED_CRYSTAL.getStackForm())
                .outputs(HASSIUM_WAFER.getStackForm(8))
                .cleanroom(GCYLCleanroomType.ISO1)
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(240).EUt(345000)
                .inputs(HASSIUM_WAFER.getStackForm())
                .fluidInputs(Trichloroferane.getFluid(250))
                .outputs(COATED_HASSIUM_WAFER.getStackForm())
                .cleanroom(GCYLCleanroomType.ISO1)
                .buildAndRegister();

        // WAFER ENGRAVING =============================================================================================

        // ILC Wafer
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(7680).inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(craftingLens, Red).outputs(INTEGRATED_LOGIC_CIRCUIT_WAFER.getStackForm(12)).cleanroom(CleanroomType.CLEANROOM).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(50).EUt(30720).inputs(WAFER_DUBNIUM.getStackForm()).notConsumable(craftingLens, Red).outputs(INTEGRATED_LOGIC_CIRCUIT_WAFER.getStackForm(16)).cleanroom(CleanroomType.CLEANROOM).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(25).EUt(122880).inputs(NEUTRONIUM_WAFER.getStackForm()).notConsumable(craftingLens, Red).outputs(INTEGRATED_LOGIC_CIRCUIT_WAFER.getStackForm(20)).cleanroom(CleanroomType.CLEANROOM).buildAndRegister();

        // ARAM Wafer
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(122_880).inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(lens, MagnetoResonatic).outputs(ARAM_WAFER.getStackForm(1)).cleanroom(CleanroomType.CLEANROOM).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(50).EUt(491_520).inputs(WAFER_DUBNIUM.getStackForm()).notConsumable(lens, MagnetoResonatic).outputs(ARAM_WAFER.getStackForm(4)).cleanroom(CleanroomType.CLEANROOM).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(25).EUt(1_966_080).inputs(NEUTRONIUM_WAFER.getStackForm()).notConsumable(lens, MagnetoResonatic).outputs(ARAM_WAFER.getStackForm(8)).cleanroom(CleanroomType.CLEANROOM).buildAndRegister();

        // NAND Memory Wafer
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(7680).inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(craftingLens, LightBlue).outputs(NAND_MEMORY_CHIP_WAFER.getStackForm(8)).cleanroom(CleanroomType.CLEANROOM).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(50).EUt(30720).inputs(WAFER_DUBNIUM.getStackForm()).notConsumable(craftingLens, LightBlue).outputs(NAND_MEMORY_CHIP_WAFER.getStackForm(12)).cleanroom(CleanroomType.CLEANROOM).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(25).EUt(122880).inputs(NEUTRONIUM_WAFER.getStackForm()).notConsumable(craftingLens, LightBlue).outputs(NAND_MEMORY_CHIP_WAFER.getStackForm(16)).cleanroom(CleanroomType.CLEANROOM).buildAndRegister();

        // NOR Memory Wafer
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(7680).inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(craftingLens, Lime).outputs(NOR_MEMORY_CHIP_WAFER.getStackForm(8)).cleanroom(CleanroomType.CLEANROOM).buildAndRegister();

        // CPU Wafer
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(7680).inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(lens, NetherStar).outputs(CENTRAL_PROCESSING_UNIT_WAFER.getStackForm(12)).cleanroom(CleanroomType.CLEANROOM).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(50).EUt(30720).inputs(WAFER_DUBNIUM.getStackForm()).notConsumable(lens, NetherStar).outputs(CENTRAL_PROCESSING_UNIT_WAFER.getStackForm(16)).cleanroom(CleanroomType.CLEANROOM).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(25).EUt(122880).inputs(NEUTRONIUM_WAFER.getStackForm()).notConsumable(lens, NetherStar).outputs(CENTRAL_PROCESSING_UNIT_WAFER.getStackForm(20)).cleanroom(CleanroomType.CLEANROOM).buildAndRegister();

        // RAM Wafer
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(7680).inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(craftingLens, LightGray).outputs(RANDOM_ACCESS_MEMORY_WAFER.getStackForm(12)).cleanroom(CleanroomType.CLEANROOM).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(50).EUt(30720).inputs(WAFER_DUBNIUM.getStackForm()).notConsumable(craftingLens, LightGray).outputs(RANDOM_ACCESS_MEMORY_WAFER.getStackForm(16)).cleanroom(CleanroomType.CLEANROOM).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(25).EUt(122880).inputs(NEUTRONIUM_WAFER.getStackForm()).notConsumable(craftingLens, LightGray).outputs(RANDOM_ACCESS_MEMORY_WAFER.getStackForm(20)).cleanroom(CleanroomType.CLEANROOM).buildAndRegister();

        // SoC Wafer
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(7680).inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(craftingLens, Yellow).outputs(SYSTEM_ON_CHIP_WAFER.getStackForm(8)).cleanroom(CleanroomType.CLEANROOM).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(50).EUt(30720).inputs(WAFER_DUBNIUM.getStackForm()).notConsumable(craftingLens, Yellow).outputs(SYSTEM_ON_CHIP_WAFER.getStackForm(16)).cleanroom(CleanroomType.CLEANROOM).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(25).EUt(122880).inputs(NEUTRONIUM_WAFER.getStackForm()).notConsumable(craftingLens, Yellow).outputs(SYSTEM_ON_CHIP_WAFER.getStackForm(24)).cleanroom(CleanroomType.CLEANROOM).buildAndRegister();

        // ASoC Wafer
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(7680).inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(craftingLens, Orange).outputs(ADVANCED_SYSTEM_ON_CHIP_WAFER.getStackForm(4)).cleanroom(CleanroomType.CLEANROOM).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(50).EUt(30720).inputs(WAFER_DUBNIUM.getStackForm()).notConsumable(craftingLens, Orange).outputs(ADVANCED_SYSTEM_ON_CHIP_WAFER.getStackForm(8)).cleanroom(CleanroomType.CLEANROOM).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(25).EUt(122880).inputs(NEUTRONIUM_WAFER.getStackForm()).notConsumable(craftingLens, Orange).outputs(ADVANCED_SYSTEM_ON_CHIP_WAFER.getStackForm(12)).cleanroom(CleanroomType.CLEANROOM).buildAndRegister();

        // PIC Wafer
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(7680).inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(craftingLens, Blue).outputs(POWER_INTEGRATED_CIRCUIT_WAFER.getStackForm(8)).cleanroom(CleanroomType.CLEANROOM).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(50).EUt(30720).inputs(WAFER_DUBNIUM.getStackForm()).notConsumable(craftingLens, Blue).outputs(POWER_INTEGRATED_CIRCUIT_WAFER.getStackForm(12)).cleanroom(CleanroomType.CLEANROOM).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(25).EUt(122880).inputs(NEUTRONIUM_WAFER.getStackForm()).notConsumable(craftingLens, Blue).outputs(POWER_INTEGRATED_CIRCUIT_WAFER.getStackForm(16)).cleanroom(CleanroomType.CLEANROOM).buildAndRegister();

        // UHPIC Wafer
        //LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(491_520).inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(lens, Amethyst).outputs(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT_WAFER.getStackForm(1)).cleanroom(CleanroomType.CLEANROOM).buildAndRegister();
       // LASER_ENGRAVER_RECIPES.recipeBuilder().duration(25).EUt(1_966_080).inputs(WAFER_DUBNIUM.getStackForm()).notConsumable(lens, Amethyst).outputs(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT_WAFER.getStackForm(2)).cleanroom(CleanroomType.CLEANROOM).buildAndRegister();
       // LASER_ENGRAVER_RECIPES.recipeBuilder().duration(12).EUt(7_864_320).inputs(NEUTRONIUM_WAFER.getStackForm()).notConsumable(lens, Amethyst).outputs(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT_WAFER.getStackForm(4)).cleanroom(CleanroomType.CLEANROOM).buildAndRegister();

        //NPIC Wafer
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(600).EUt(GTValues.VA[GTValues.UHV]).inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(lens, Amethyst).outputs(NPIC_WAFER.getStackForm(1)).cleanroom(GCYLCleanroomType.ISO3).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(300).EUt(GTValues.VA[GTValues.UEV]).inputs(WAFER_DUBNIUM.getStackForm()).notConsumable(lens, Amethyst).outputs(NPIC_WAFER.getStackForm(2)).cleanroom(GCYLCleanroomType.ISO3).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(150).EUt(GTValues.VA[GTValues.UIV]).inputs(NEUTRONIUM_WAFER.getStackForm()).notConsumable(lens, Amethyst).outputs(NPIC_WAFER.getStackForm(4)).cleanroom(GCYLCleanroomType.ISO3).buildAndRegister();

        //PPIC WAFER
        CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(GTValues.VA[GTValues.UIV]).inputs(NPIC_WAFER.getStackForm(1)).input(dust, IndiumGalliumPhosphide, 64).fluidInputs(Xenon.getFluid(1000)).outputs(PPIC_WAFER.getStackForm(1)).cleanroom(GCYLCleanroomType.ISO2).buildAndRegister();

        //QPIC WAFER
        CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(GTValues.VA[GTValues.OpV]).inputs(PPIC_WAFER.getStackForm(1)).input(dust, Neutronium, 16).fluidInputs(MetastableOganesson.getFluid(1000)).outputs(QPIC_WAFER.getStackForm(1)).cleanroom(GCYLCleanroomType.ISO1).buildAndRegister();

        // HASoC Wafer
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(600).EUt(491_520).inputs(WAFER_RUTHERFORDIUM.getStackForm()).notConsumable(lens, CubicZirconia).outputs(HIGHLY_ADVANCED_SOC_WAFER.getStackForm(1)).cleanroom(CleanroomType.CLEANROOM).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(300).EUt(1_966_080).inputs(WAFER_DUBNIUM.getStackForm()).notConsumable(lens, CubicZirconia).outputs(HIGHLY_ADVANCED_SOC_WAFER.getStackForm(4)).cleanroom(CleanroomType.CLEANROOM).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(150).EUt(7_864_320).inputs(NEUTRONIUM_WAFER.getStackForm()).notConsumable(lens, CubicZirconia).outputs(HIGHLY_ADVANCED_SOC_WAFER.getStackForm(8)).cleanroom(CleanroomType.CLEANROOM).buildAndRegister();

        // UHASoC Wafer
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(600).EUt(1_966_080).inputs(WAFER_DUBNIUM.getStackForm()).notConsumable(lens, Prasiolite).outputs(UHASOC_WAFER.getStackForm(1)).cleanroom(CleanroomType.CLEANROOM).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(300).EUt(7_864_320).inputs(NEUTRONIUM_WAFER.getStackForm()).notConsumable(lens, Prasiolite).outputs(UHASOC_WAFER.getStackForm(4)).cleanroom(CleanroomType.CLEANROOM).buildAndRegister();

        // Misc Cutting Recipes
        //CUTTER_RECIPES.recipeBuilder().inputs(UHPIC_WAFER.getStackForm()) .outputs(UHPIC.getStackForm(2)) .EUt(491_520).duration(600).buildAndRegister();
        CUTTER_RECIPES.recipeBuilder().inputs(ARAM_WAFER.getStackForm()).outputs(ARAM.getStackForm(16)).EUt(122_880).duration(600).cleanroom(CleanroomType.CLEANROOM).buildAndRegister();
        //CUTTER_RECIPES.recipeBuilder().inputs(HASOC_WAFER.getStackForm()) .outputs(HASOC.getStackForm(6)) .EUt(491_520).duration(600).buildAndRegister();
        CUTTER_RECIPES.recipeBuilder().inputs(UHASOC_WAFER.getStackForm()).outputs(UHASOC.getStackForm(6)).EUt(1_966_080).duration(600).cleanroom(CleanroomType.CLEANROOM).buildAndRegister();

        //NPIC
        CUTTER_RECIPES.recipeBuilder().inputs(NPIC_WAFER.getStackForm()).outputs(NPIC.getStackForm(2)).EUt(GTValues.VA[GTValues.UHV]).duration(600).cleanroom(GCYLCleanroomType.ISO3).buildAndRegister();
        //PPIC
        CUTTER_RECIPES.recipeBuilder().inputs(PPIC_WAFER.getStackForm()).outputs(PPIC.getStackForm(2)).EUt(GTValues.VA[GTValues.UIV]).duration(600).cleanroom(GCYLCleanroomType.ISO2).buildAndRegister();
        //QPIC
        CUTTER_RECIPES.recipeBuilder().inputs(QPIC_WAFER.getStackForm()).outputs(QPIC.getStackForm(2)).EUt(GTValues.VA[GTValues.OpV]).duration(600).cleanroom(GCYLCleanroomType.ISO1).buildAndRegister();
        // Optical SoC

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(390).EUt(983040)
                .inputs(UHASOC_WAFER.getStackForm())
                .fluidInputs(ZBLANDust.getFluid(L))
                .fluidInputs(CarbonNanotubes.getFluid(L))
                .fluidInputs(SeaborgiumDopedNanotubes.getFluid(L))
                .input(dust, IndiumPhosphide)
                .fluidInputs(DielectricMirrorFormationMix.getFluid(250))
                .outputs(OPTICAL_SOC_WAFER.getStackForm())
                .buildAndRegister();

        CUTTER_RECIPES.recipeBuilder().duration(280).EUt(850000)
                .inputs(OPTICAL_SOC_WAFER.getStackForm())
                .outputs(OPTICAL_SOC.getStackForm(4))
                .buildAndRegister();

        // Optical Boules
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(210).EUt(30720)
                .notConsumable(stick, Sapphire)
                .input(dust, PrHoYLFNanoparticles, 1)
                .fluidInputs(PrHoYLF.getFluid(L / 9))
                .outputs(PRHOYLF_BOULE.getStackForm())
                .buildAndRegister();

        LATHE_RECIPES.recipeBuilder().duration(340).EUt(26000)
                .inputs(PRHOYLF_BOULE.getStackForm())
                .outputs(PRHOYLF_ROD.getStackForm(2))
                .output(dustTiny, PrHoYLF)
                .buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder().duration(260).EUt(30)
                .input(dust, PrHoYLFNanoparticles, 1)
                .fluidOutputs(PrHoYLF.getFluid(L))
                .buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder().duration(20).EUt(30)
                .input(dustTiny, PrHoYLF)
                .fluidOutputs(PrHoYLF.getFluid(L / 9))
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(210).EUt(30000)
                .notConsumable(stick, Sapphire)
                .input(dust, LuTmYVONanoparticles, 1)
                .fluidInputs(LuTmYVO.getFluid(L / 9))
                .outputs(LUTMYVO_BOULE.getStackForm())
                .buildAndRegister();

        LATHE_RECIPES.recipeBuilder().duration(340).EUt(26000)
                .inputs(LUTMYVO_BOULE.getStackForm())
                .outputs(LUTMYVO_ROD.getStackForm(2))
                .output(dustTiny, LuTmYVO)
                .buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder().duration(260).EUt(30)
                .input(dust, LuTmYVONanoparticles, 1)
                .fluidOutputs(LuTmYVO.getFluid(L))
                .buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder().duration(20).EUt(30)
                .input(dustTiny, LuTmYVO)
                .fluidOutputs(LuTmYVO.getFluid(L / 9))
                .buildAndRegister();

        // NdYAG Boules
        // 9Y2O3 + Nd2O3 -> 10 Neodymium Doped Yttrium
        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(220).EUt(7680)
                .input(dust, YttriumOxide, 45)
                .input(dust, NeodymiumOxide, 5)
                .output(dust, NeodymiumDopedYttrium, 10)
                .buildAndRegister();

        // [Al2O3  + CH2Cl2 + 2C12H27N] + Neodymium Doped Yttrium + (NH2)CO(NH2) -> 2Unprocessed Nd:YAG + 2C12H27N
        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(7680)
                .fluidInputs(AluminaSolution.getFluid(1000))
                .input(dust, NeodymiumDopedYttrium, 1)
                .input(dust, Urea, 8)
                .fluidOutputs(UnprocessedNdYAGSolution.getFluid(2000))
                .fluidOutputs(Tributylamine.getFluid(2000))
                .buildAndRegister();

        // Unprocessed Nd:YAG -> Unprocessed Nd:YAG Dust + CH2Cl2
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(220).EUt(7680)
                .fluidInputs(UnprocessedNdYAGSolution.getFluid(1000))
                .fluidOutputs(Dichloromethane.getFluid(500))
                .output(dust, UnprocessedNdYAGDust, 1)
                .buildAndRegister();

        // Unprocessed Nd:YAG Dust -> Nd:YAG Nanoparticles
        BLAST_RECIPES.recipeBuilder().duration(3800).EUt(120).blastFurnaceTemp(300)
                .input(dust, UnprocessedNdYAGDust, 1)
                .output(dust, NdYAGNanoparticles, 1)
                .buildAndRegister();

        // Nd:YAG Nanoparticles + NdYAG -> Nd:YAG Boule
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(210).EUt(30720)
                .notConsumable(stick, Sapphire)
                .input(dust, NdYAGNanoparticles, 1)
                .fluidInputs(NdYAG.getFluid(L / 9))
                .outputs(NDYAG_BOULE.getStackForm())
                .buildAndRegister();

        LATHE_RECIPES.recipeBuilder().duration(340).EUt(26000)
                .inputs(NDYAG_BOULE.getStackForm())
                .outputs(NDYAG_ROD.getStackForm(2))
                .output(dustTiny, NdYAG)
                .buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder().duration(260).EUt(30)
                .input(dust, NdYAGNanoparticles, 1)
                .fluidOutputs(NdYAG.getFluid(L))
                .buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder().duration(20).EUt(30)
                .input(dustTiny, NdYAG)
                .fluidOutputs(NdYAG.getFluid(L / 9))
                .buildAndRegister();
    }
}
