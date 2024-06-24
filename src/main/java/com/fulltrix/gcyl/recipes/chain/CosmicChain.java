package com.fulltrix.gcyl.recipes.chain;

import com.fulltrix.gcyl.item.GCYLExplosive;
import com.fulltrix.gcyl.item.GCYLMetaBlocks;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;

import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static com.fulltrix.gcyl.item.GCYLCoreItems.*;
import static com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class CosmicChain {
    public static void init() {

        STELLAR_FORGE_RECIPES.recipeBuilder().duration(60).EUt(14000000)
                .inputs(GCYLMetaBlocks.EXPLOSIVE.getItemVariant(GCYLExplosive.ExplosiveType.LEPTONIC_CHARGE))
                .inputs(DEGENERATE_RHENIUM_DUST.getStackForm())
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(QuarkGluonPlasma.getFluid(4000))
                .buildAndRegister();

        STELLAR_FORGE_RECIPES.recipeBuilder().duration(60).EUt(14000000)
                .inputs(GCYLMetaBlocks.EXPLOSIVE.getItemVariant(GCYLExplosive.ExplosiveType.LEPTONIC_CHARGE))
                .inputs(DEGENERATE_RHENIUM_PLATE.getStackForm())
                .notConsumable(new IntCircuitIngredient(2))
                .fluidOutputs(QuarkGluonPlasma.getFluid(2000))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(122880)
                .input(wireFine, YttriumBariumCuprate, 64)
                .input(wireFine, NiobiumTitanium, 64)
                .input(plate, NiobiumNitride, 8)
                .input(foil, Polybenzimidazole, 16)
                .input(stickLong, VanadiumGallium)
                .outputs(SEPARATION_ELECTROMAGNET.getStackForm())
                .buildAndRegister();

        ADVANCED_CENTRIFUGE_RECIPES.recipeBuilder().duration(200).EUt(1200000)
                .fluidInputs(QuarkGluonPlasma.getFluid(1000 * 5))
                .notConsumable(SEPARATION_ELECTROMAGNET.getStackForm())
                .fluidOutputs(HeavyQuarks.getFluid(750 * 5))
                .fluidOutputs(Gluons.getFluid(500 * 5))
                .fluidOutputs(LightQuarks.getFluid(250 * 5))
                .buildAndRegister();

        ADVANCED_MIXER_RECIPES.recipeBuilder().duration(100).EUt(2400000)
                .fluidInputs(HeavyLeptonMix.getFluid(1000))
                .fluidInputs(HeavyQuarks.getFluid(1000))
                .fluidInputs(Gluons.getFluid(1000))
                .fluidOutputs(CosmicComputingMix.getFluid(3000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(100).EUt(3250000)
                .fluidInputs(HeavyQuarks.getFluid(750))
                .fluidInputs(LightQuarks.getFluid(250))
                .fluidOutputs(HeavyQuarkEnrichedMix.getFluid(1000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(130).EUt(450000)
                .fluidInputs(Titanium50.getFluid(144))
                .fluidInputs(Scandium.getFluid(144))
                .fluidOutputs(ScandiumTitanium50Mix.getFluid(288))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(130).EUt(450000)
                .fluidInputs(Radon.getFluid(1000))
                .fluidInputs(Radium.getFluid(144))
                .fluidOutputs(RadonRadiumMix.getFluid(288))
                .buildAndRegister();

        ADVANCED_MIXER_RECIPES.recipeBuilder().duration(140).EUt(4500000)
                .fluidInputs(Deuterium.getFluid(2000))
                .fluidInputs(MetastableHassium.getFluid(144))
                .fluidInputs(MetastableFlerovium.getFluid(144))
                .fluidInputs(MetastableOganesson.getFluid(144))
                .fluidOutputs(DeuteriumSuperheavyMix.getFluid(2592))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(100).EUt(1100000).AdvCoilTier(3).EUToStart(5000000000L).EUReturn(50)
                .fluidInputs(DeuteriumSuperheavyMix.getFluid(720))
                .fluidInputs(HeavyQuarkEnrichedMix.getFluid(720))
                .fluidOutputs(HeavyQuarkDegenerateMatter.getFluid(720))
                .buildAndRegister();

        // 3HCl + Fl -> FlCl3 + 3H
        CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(7680)
                .fluidInputs(HydrochloricAcid.getFluid(3000))
                .input(dust, MetastableFlerovium)
                .fluidOutputs(Trichloroferane.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(3000))
                .buildAndRegister();

        // C6H5F + 2Na -> C6H5Na + NaF
        CHEMICAL_RECIPES.recipeBuilder().duration(210).EUt(480)
                .fluidInputs(FluoroBenzene.getFluid(1000))
                .input(dust, Sodium, 2)
                .fluidOutputs(Phenylsodium.getFluid(1000))
                .output(dust, SodiumFluoride, 2)
                .buildAndRegister();

        // LiAlH4 + C4H6O4 -> C4H6O2 + Li + Al + 2H2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(15360)
                .input(dust, LithiumAluminiumHydride, 4)
                .input(dust, SuccinicAcid, 14)
                .fluidOutputs(Succinaldehyde.getFluid(1000))
                .output(dust, Lithium)
                .output(dust, Aluminium)
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();

        // 2KF + H + C6H4Cl2 + N -> 2KCl + C6H5F2N
        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(480)
                .input(dust, PotassiumFluoride, 4)
                .fluidInputs(Hydrogen.getFluid(1000))
                .fluidInputs(Dichlorobenzene.getFluid(1000))
                .fluidInputs(Nitrogen.getFluid(1000))
                .notConsumable(dust, AluminiumChloride, 1)
                .output(dust, RockSalt, 4)
                .fluidOutputs(Difluoroaniline.getFluid(1000))
                .buildAndRegister();

        // C6H5F2N + C4H6O2 -> C10H7F2N + 2H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(480)
                .fluidInputs(Difluoroaniline.getFluid(1000))
                .fluidInputs(Succinaldehyde.getFluid(1000))
                .notConsumable(dust, PhosphorusPentoxide)
                .fluidOutputs(NDifluorophenylpyrrole.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();

        // 6 H2O + 2 HCl + 2 AgBF4 + 8 C6H5Na + 2C8H20NBr + 2 AgClO4 + 3 (C5H5)2Cl2Ti + 6 C10H7F2N -> 4AgCl + 2NaBr + 6H2ONaCl + [3 (C5H5)2(C10H6F2N)2Ti + 2 (C6H5)4NBF4 + 8 C2H5OH]
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(350).EUt(30720)
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .fluidInputs(Silvertetrafluoroborate.getFluid(2000))
                .fluidInputs(Phenylsodium.getFluid(8000))
                .fluidInputs(TetraethylammoniumBromide.getFluid(2000))
                .input(dust, SilverPerchlorate, 12)
                .input(dust, TitaniumCyclopentadienyl, 69)
                .input(dust, Ice, 42)
                .fluidInputs(NDifluorophenylpyrrole.getFluid(6000))
                .output(dust, SilverChloride, 8)
                .output(dust, SodiumBromide, 4)
                .fluidOutputs(SaltWater.getFluid(6000))
                .fluidOutputs(PhotopolymerSolution.getFluid(8000))
                .buildAndRegister();

        // 3NaClO -> 2NaCl + NaClO3
        ELECTROLYZER_RECIPES.recipeBuilder().duration(210).EUt(120)
                .input(dust, SodiumHypochlorite, 9)
                .output(dust, Salt, 4)
                .output(dust, SodiumChlorate, 5)
                .buildAndRegister();

        // NaClO3 + H2O2 -> NaClO4 + H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(120)
                .input(dust, SodiumChlorate, 5)
                .fluidInputs(HydrogenPeroxide.getFluid(1000))
                .output(dust, SodiumPerchlorate, 6)
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        // Ag2O + 2NaClO4 + HCl -> 2AgClO4 + Na2O + dil.HCl
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(350).EUt(480)
                .input(dust, SilverOxide, 3)
                .input(dust, SodiumPerchlorate, 12)
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .output(dust, SilverPerchlorate, 12)
                .output(dust, SodiumOxide, 3)
                .fluidOutputs(DilutedHydrochloricAcid.getFluid(1000))
                .buildAndRegister();

        // 2AgCl + H2O -> Ag2O + 2HCl
        CHEMICAL_RECIPES.recipeBuilder().duration(100)
                .input(dust, SilverChloride, 4)
                .notConsumable(dust, SodiumHydroxide)
                .fluidInputs(Water.getFluid(1000))
                .output(dust, SilverOxide, 3)
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .buildAndRegister();

        // Ag2O + C -> 2Ag + CO
        BLAST_RECIPES.recipeBuilder().duration(80).EUt(120).blastFurnaceTemp(1200)
                .input(dust, SilverOxide, 3)
                .input(dust, Carbon)
                .output(ingot, Silver, 2)
                .fluidOutputs(CarbonMonoxide.getFluid(1000))
                .buildAndRegister();

        // Ge + 2S -> GeS2
        CHEMICAL_RECIPES.recipeBuilder().duration(340).EUt(120)
                .input(dust, Germanium)
                .input(dust, Sulfur, 2)
                .output(dust, GermaniumSulfide, 3)
                .buildAndRegister();

        // GeS2 + 6O -> GeO2 + 2SO2
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(2500)
                .input(dust, GermaniumSulfide, 3)
                .fluidInputs(Oxygen.getFluid(6000))
                .output(dust, GermaniumOxide, 3)
                .fluidOutputs(SulfurDioxide.getFluid(2000))
                .buildAndRegister();

        // WC + 4O -> WO3 + CO
        CHEMICAL_RECIPES.recipeBuilder().duration(400).EUt(480)
                .input(dust, TungstenCarbide, 2)
                .fluidInputs(Oxygen.getFluid(4000))
                .output(dust, TungstenTrioxide, 4)
                .fluidOutputs(CarbonMonoxide.getFluid(1000))
                .buildAndRegister();

        // CdS + WO3 + 3O -> CdWO4 + SO2
        BLAST_RECIPES.recipeBuilder().duration(320).EUt(120).blastFurnaceTemp(2800)
                .input(dust, CadmiumSulfide, 2)
                .input(dust, TungstenTrioxide, 4)
                .fluidInputs(Oxygen.getFluid(3000))
                .output(dust, CadmiumTungstate, 6)
                .fluidOutputs(SulfurDioxide.getFluid(1000))
                .buildAndRegister();

        // CsI + Tm + Tl -> CsITmTl
        BLAST_RECIPES.recipeBuilder().duration(260).EUt(120).blastFurnaceTemp(2853)
                .input(dust, CesiumIodide, 1)
                .input(dustSmall, Thulium, 2)
                .input(dustSmall, Thallium, 2)
                .output(dust, TlTmCesiumIodide, 2)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(240).EUt(7680)
                .fluidInputs(Anthracene.getFluid(1000))
                .input(dust, Tetracene, 2)
                .output(dust, PolycyclicAromaticMix, 3)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(340).EUt(120)
                .input(dust, Caesium)
                .input(dust, Iodine)
                .output(dust, CesiumIodide, 2)
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(500000)
                .fluidInputs(BismuthNitrateSoluton.getFluid(4000))
                .input(dust, GermaniumOxide, 3)
                .notConsumable(LOW_FREQUENCY_LASER.getStackForm())
                .output(dust, BismuthGermanate, 33)
                .output(dust, Potash, 18)
                .fluidOutputs(NitrogenDioxide.getFluid(12000))
                .fluidOutputs(Water.getFluid(4000))
                .buildAndRegister();
    }
}
