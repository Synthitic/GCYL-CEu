package com.fulltrix.gcyl.recipes.chain;

import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static com.fulltrix.gcyl.item.GCYLCoreItems.*;
import static com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustSmall;
import static gregtech.common.items.MetaItems.*;
import static net.minecraft.init.Items.APPLE;

public class WetwareChain {
    public static void init() {

        //TODO: add sterile cleanroom to almost all of these

        // GROWTH MEDIUM ===============================================================================================

        // 2CaO + 5C -> CO2 + 2CaC2
        BLAST_RECIPES.recipeBuilder()
                .input(dust, Quicklime, 4)
                .input(dust, Carbon, 5)
                .fluidOutputs(CarbonMonoxide.getFluid(1000))
                .output(dust, CalciumCarbide, 6)
                .blastFurnaceTemp(2500)
                .EUt(120)
                .duration(300)
                .buildAndRegister();

        // CaC2 + 2H2O -> Ca(OH)2 + C2H2
        BLAST_RECIPES.recipeBuilder()
                .input(dust, CalciumCarbide, 3)
                .fluidInputs(Steam.getFluid(2000))
                .output(dust, CalciumHydroxide, 5)
                .fluidOutputs(Acetylene.getFluid(1000))
                .blastFurnaceTemp(2300)
                .EUt(120)
                .duration(200)
                .buildAndRegister();

        // Ca(OH)2 + 2HCl -> 2H2O + CaCl2
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, CalciumHydroxide, 5)
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .fluidOutputs(Water.getFluid(2000))
                .output(dust, CalciumChloride, 3)
                .EUt(120)
                .duration(60)
                .buildAndRegister();

        // O + CH3OH -> CH2O + H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(dust, Silver)
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidInputs(Methanol.getFluid(1000))
                .fluidOutputs(Formaldehyde.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(30720)
                .duration(200)
                .buildAndRegister();

        // CH2O + C2H2 -> C3H4O
        CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(dust, Copper)
                .fluidInputs(Formaldehyde.getFluid(1000))
                .fluidInputs(Acetylene.getFluid(1000))
                .fluidOutputs(PropargylAlcohol.getFluid(1000))
                .EUt(120)
                .duration(120)
                .buildAndRegister();

        // C3H4O + HCl -> C3H3Cl + H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(PropargylAlcohol.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(PropargylChloride.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(120)
                .duration(100)
                .buildAndRegister();


        DISTILLATION_RECIPES.recipeBuilder()
                .output(dustSmall, RawRubber)
                .fluidInputs(Resin.getFluid(1000))
                .fluidOutputs(Turpentine.getFluid(200))
                .fluidOutputs(Isoprene.getFluid(20))
                .EUt(480)
                .duration(500)
                .buildAndRegister();

        // C10H16 + H2SO4 -> C10H16 + H2SO4
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Turpentine.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .output(dust, BetaPinene, 26)
                .fluidOutputs(SulfuricAcid.getFluid(1000))
                .EUt(480)
                .duration(110)
                .buildAndRegister();

        // C10H16 + 2C5H8 + 2O -> 2C10H16O
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, BetaPinene, 26)
                .fluidInputs(Isoprene.getFluid(2000))
                .fluidInputs(Oxygen.getFluid(2000))
                .fluidOutputs(Citral.getFluid(2000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        // C10H16O + C3H6O -> C13H20O + H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Citral.getFluid(1000))
                .fluidInputs(Acetone.getFluid(1000))
                .fluidOutputs(BetaIonone.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(120)
                .duration(250)
                .buildAndRegister();

        // 25C13H20O + 5C3H3Cl -> 17C20H30O + 8O + 5HCl
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(BetaIonone.getFluid(25000))
                .fluidInputs(PropargylChloride.getFluid(5000))
                .fluidOutputs(VitaminA.getFluid(17000))
                .fluidOutputs(Oxygen.getFluid(8000))
                .fluidOutputs(HydrochloricAcid.getFluid(5000))
                .EUt(480)
                .duration(150)
                .buildAndRegister();

        // This needs to be better, Zalgo was working on something
        MACERATOR_RECIPES.recipeBuilder()
                .inputs(new ItemStack(APPLE))
                .chancedOutput(dust, Yeast, 1, 500, 500)
                .EUt(30)
                .duration(50)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(Biomass.getFluid(1000))
                .input(dust, Yeast, 1)
                .notConsumable(ULTRASONIC_HOMOGENIZER.getStackForm())
                .fluidOutputs(LinoleicAcid.getFluid(1000))
                .EUt(1920)
                .duration(200)
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        // C2H4 + O -> C2H4O
        CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(new IntCircuitIngredient(10))
                .fluidInputs(Ethylene.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(EthyleneOxide.getFluid(1000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        // NH3 + C2H4O -> C2H7NO
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(EthyleneOxide.getFluid(1000))
                .fluidOutputs(Ethanolamine.getFluid(1000))
                .EUt(7680)
                .duration(60)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .input(dust, CupriavidusNecator, 1)
                .input(dust, Sugar)
                .fluidInputs(Nitrogen.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(1000))
                .fluidOutputs(Biotin.getFluid(2000))
                .EUt(7680)
                .duration(40)
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Biotin.getFluid(1000))
                .fluidInputs(LinoleicAcid.getFluid(1000))
                .fluidInputs(Catalase.getFluid(1000))
                .fluidInputs(VitaminA.getFluid(1000))
                .fluidInputs(Ethanolamine.getFluid(1000))
                .fluidOutputs(B27Supplement.getFluid(5000))
                .EUt(7680)
                .duration(150)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidOutputs(CleanAmmoniaSolution.getFluid(2000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(CleanAmmoniaSolution.getFluid(1000))
                .input(dust, BrevibacteriumFlavium, 1)
                .input(dust, Sugar)
                .output(dust, Glutamine, 40)
                .EUt(30720)
                .duration(500)
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, Meat)
                .fluidOutputs(Blood.getFluid(1000))
                .EUt(30)
                .duration(50)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(Blood.getFluid(1000))
                .fluidOutputs(BloodCells.getFluid(500))
                .fluidOutputs(BloodPlasma.getFluid(500))
                .EUt(480)
                .duration(200)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(BloodPlasma.getFluid(1000))
                .fluidOutputs(Catalase.getFluid(200))
                .fluidOutputs(BFGF.getFluid(200))
                .fluidOutputs(EGF.getFluid(200))
                .EUt(480)
                .duration(50)
                .buildAndRegister();

        ADVANCED_MIXER_RECIPES.recipeBuilder()
                .fluidInputs(B27Supplement.getFluid(1000))
                .fluidInputs(AmmoniumNitrate.getFluid(1000))
                .input(dust, Glutamine, 20)
                .fluidInputs(BFGF.getFluid(1000))
                .fluidInputs(EGF.getFluid(1000))
                .fluidOutputs(RawGrowthMedium.getFluid(4000))
                .EUt(480)
                .duration(500)
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(BloodCells.getFluid(1000))
                .notConsumable(ULTRASONIC_HOMOGENIZER.getStackForm())
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidOutputs(BacterialGrowthMedium.getFluid(2000))
                .EUt(120)
                .duration(100).cleanroom(CleanroomType.STERILE_CLEANROOM)

                .buildAndRegister();

        // STERILIZED GROWTH MEDIUM ====================================================================================

        MIXER_RECIPES.recipeBuilder()
                .input(dust, SiliconDioxide, 3)
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .input(dust, SodiumHydroxide, 3)
                .fluidInputs(Steam.getFluid(1000))
                .fluidOutputs(SilicaGelBase.getFluid(1000))
                .EUt(120)
                .duration(120)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(SilicaGelBase.getFluid(1000))
                .output(dust, SilicaGel, 3)
                .output(dust, Salt, 2)
                .EUt(480)
                .duration(125)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .input(dust, SilicaGel, 3)
                .input(dust, Alumina, 5)
                .output(dust, SilicaAluminaGel, 8)
                .EUt(30)
                .duration(60)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder()
                .input(dust, SilicaAluminaGel, 1)
                .output(dust, ZeoliteSievingPellets, 1)
                .blastFurnaceTemp(4500)
                .EUt(120)
                .duration(400)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .input(dust, WetZeoliteSievingPellets, 1)
                .output(dust, ZeoliteSievingPellets, 1)
                .EUt(120)
                .duration(50)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .input(dust, ZeoliteSievingPellets, 1)
                .fluidInputs(Ethanol.getFluid(1000))
                .fluidOutputs(Ethanol100.getFluid(1000))
                .output(dust, WetZeoliteSievingPellets, 1)
                .EUt(120)
                .duration(100)
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        AUTOCLAVE_RECIPES.recipeBuilder()
                .inputs(PETRI_DISH.getStackForm())
                .fluidInputs(Ethanol100.getFluid(100))
                .outputs(STERILIZED_PETRI_DISH.getStackForm())
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .EUt(7680)
                .duration(25)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(HydrogenPeroxide.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(PiranhaSolution.getFluid(2000))
                .EUt(30)
                .duration(50)
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder()
                .fluidInputs(PiranhaSolution.getFluid(100))
                .inputs(CONTAMINATED_PETRI_DISH.getStackForm())
                .outputs(PETRI_DISH.getStackForm())
                .EUt(30)
                .duration(25)
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        // 6H + C6H5NO2 -> C6H5NH2 + 2H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Hydrogen.getFluid(6000))
                .fluidInputs(Nitrobenzene.getFluid(1000))
                .notConsumable(dust, Zinc)
                .fluidOutputs(Aniline.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .EUt(30)
                .duration(100)
                .buildAndRegister();

        // HCl + 2H2SO4 + O -> HSO3Cl + 2H2O + SO3
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(2000))
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(ChlorosulfonicAcid.getFluid(2000))
                .fluidOutputs(Water.getFluid(2000))
                .fluidOutputs(SulfurTrioxide.getFluid(1000))
                .EUt(480)
                .duration(200)
                .buildAndRegister();

        // C6H5NH2 + (CH3CO)2O + HSO3Cl -> C8H8ClNO3S + H2O + CH3COOH
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Aniline.getFluid(1000))
                .fluidInputs(AceticAnhydride.getFluid(1000))
                .fluidInputs(ChlorosulfonicAcid.getFluid(1000))
                .fluidOutputs(AcetylsulfanilylChloride.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(AceticAcid.getFluid(1000))
                .EUt(1920)
                .duration(100)
                .buildAndRegister();

        // H2O + Na2CO3 -> NaHCO3 + NaOH
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(140).EUt(30)
                .fluidInputs(Water.getFluid(1000))
                .circuitMeta(2)
                .input(dust, SodaAsh, 1)
                .output(dust, SodiumBicarbonate, 6)
                .output(dust, SodiumHydroxide, 3)
                .buildAndRegister();

        // NaHCO3 -> NaOH + CO2
        ELECTROLYZER_RECIPES.recipeBuilder().duration(145).EUt(120)
                .input(dust, SodiumBicarbonate, 6)
                .output(dust, SodiumHydroxide, 3)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .buildAndRegister();

        // NaHCO3 + C8H8ClNO3S + NH3 -> NaCl + C6H8N2O2S + CO2 + CH3COOH
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, SodiumBicarbonate, 6)
                .fluidInputs(AcetylsulfanilylChloride.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .output(dust, Salt, 2)
                .fluidOutputs(Sulfanilamide.getFluid(1000))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .fluidOutputs(AceticAcid.getFluid(1000))
                .EUt(30720)
                .duration(50)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(RawGrowthMedium.getFluid(1000))
                .fluidInputs(Sulfanilamide.getFluid(250))
                .fluidOutputs(SterileGrowthMedium.getFluid(1250))
                .EUt(7680)
                .duration(100)
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        // STEM CELLS ==================================================================================================

        MIXER_RECIPES.recipeBuilder()
                .input(dust, Meat)
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidOutputs(AnimalCells.getFluid(1000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .notConsumable(dust, Naquadria)
                .fluidInputs(AnimalCells.getFluid(1000))
                .fluidOutputs(RapidlyReplicatingAnimalCells.getFluid(1000))
                .EUt(7680)
                .duration(500)
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(RapidlyReplicatingAnimalCells.getFluid(1000))
                .notConsumable(new IntCircuitIngredient(1))
                .EUt(480)
                .duration(100)
                .fluidOutputs(MycGene.getFluid(1000))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(RapidlyReplicatingAnimalCells.getFluid(1000))
                .notConsumable(new IntCircuitIngredient(2))
                .EUt(480)
                .duration(100)
                .fluidOutputs(Oct4Gene.getFluid(1000))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(RapidlyReplicatingAnimalCells.getFluid(1000))
                .notConsumable(new IntCircuitIngredient(3))
                .EUt(480)
                .duration(100)
                .fluidOutputs(SOX2Gene.getFluid(1000))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(RapidlyReplicatingAnimalCells.getFluid(1000))
                .notConsumable(new IntCircuitIngredient(4))
                .EUt(480)
                .duration(100)
                .fluidOutputs(KFL4Gene.getFluid(1000))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(SterileGrowthMedium.getFluid(1000))
                .fluidInputs(AnimalCells.getFluid(1000))
                .fluidInputs(GeneTherapyFluid.getFluid(1000))
                .EUt(30720)
                .duration(1000)
                .outputs(STEM_CELLS.getStackForm())
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .inputs(STEM_CELLS.getStackForm())
                .fluidInputs(SterileGrowthMedium.getFluid(1000))
                .outputs(STEM_CELLS.getStackForm(2))
                .fluidOutputs(DepletedGrowthMedium.getFluid(500))
                .EUt(480)
                .duration(100)
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        FERMENTING_RECIPES.recipeBuilder()
                .fluidInputs(DepletedGrowthMedium.getFluid(1000))
                .fluidOutputs(FermentedBiomass.getFluid(1000))
                .EUt(30)
                .duration(100)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .notConsumable(ULTRASONIC_HOMOGENIZER.getStackForm())
                .input(dust, StreptococcusPyogenes, 1)
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidOutputs(Cas9.getFluid(1000))
                .EUt(480)
                .duration(100)
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(Cas9.getFluid(1000))
                .fluidInputs(MycGene.getFluid(1000))
                .fluidInputs(Oct4Gene.getFluid(1000))
                .fluidInputs(SOX2Gene.getFluid(1000))
                .fluidInputs(KFL4Gene.getFluid(1000))
                .input(dust, EschericiaColi, 1)
                .fluidOutputs(GenePlasmids.getFluid(5000))
                .EUt(1920)
                .duration(50)
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .notConsumable(ULTRASONIC_HOMOGENIZER.getStackForm())
                .inputs(new ItemStack(Blocks.BROWN_MUSHROOM))
                .fluidOutputs(Chitin.getFluid(100))
                .EUt(30)
                .duration(100)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .notConsumable(ULTRASONIC_HOMOGENIZER.getStackForm())
                .inputs(new ItemStack(Blocks.RED_MUSHROOM))
                .fluidOutputs(Chitin.getFluid(100))
                .EUt(30)
                .duration(100)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .notConsumable(ULTRASONIC_HOMOGENIZER.getStackForm())
                .inputs(new ItemStack(Blocks.BROWN_MUSHROOM, 1))
                .fluidOutputs(Chitin.getFluid(100))
                .EUt(30)
                .duration(100)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .notConsumable(ULTRASONIC_HOMOGENIZER.getStackForm())
                .inputs(new ItemStack(Blocks.RED_MUSHROOM, 1))
                .fluidOutputs(Chitin.getFluid(100))
                .EUt(30)
                .duration(100)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(Chitin.getFluid(1000))
                .input(dust, BifidobacteriumBreve, 1)
                .fluidOutputs(Chitosan.getFluid(1000))
                .EUt(120)
                .duration(100)
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(GenePlasmids.getFluid(1000))
                .fluidInputs(Chitosan.getFluid(1000))
                .fluidOutputs(GeneTherapyFluid.getFluid(2000))
                .EUt(7680)
                .duration(25)
                .buildAndRegister();
    }
}
