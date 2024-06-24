package com.fulltrix.gcyl.recipes.chain;

import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static com.fulltrix.gcyl.item.GCYLCoreItems.UVA_HALIDE_LAMP;
import static com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class RheniumChain {
    public static void init() {
        // Molybdenite MUST NOT be producible from it's components!

        /*
         * Unknown compounds key: (could use some help on this)
         * GoldCyanide: AuCN
         * GoldDepleteMolybdenite: MoS2
         * MolybdenumConcentrate:
         * ChlorideLeachedSolution:
         * MolybdenumFlue:
         * RheniumSulfuricSolution:
         * RheniumSeparationMixture: Does not matter, doesn't impact formulas (despite being consumed)
         * PlatinumSaltCrude:
         * PlatinumSaltRefined:
         * RheniumScrubbedSolution: Re
         * LeachedColumbite:
         * PurifiedColumbite:
         * LeachedPyrochlore:
         * PurifiedPyrochlore:
         */

        // CH4 + NH3 + 3O -> HCN + 3H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Methane.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(3000))
                .fluidOutputs(HydrogenCyanide.getFluid(1000))
                .fluidOutputs(Water.getFluid(3000))
                .EUt(1920)
                .duration(50)
                .buildAndRegister();

        // 2HCN + O + 2Na -> 2NaCN + H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(HydrogenCyanide.getFluid(2000))
                .fluidInputs(Oxygen.getFluid(1000))
                .input(dust, Sodium, 2)
                .fluidOutputs(SodiumCyanide.getFluid(2000))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(1920)
                .duration(12)
                .buildAndRegister();

        // MoS2 + NaCN -> AuCN + MoS2(depl.)
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Molybdenite, 3)
                .fluidInputs(SodiumCyanide.getFluid(1000))
                .fluidOutputs(GoldCyanide.getFluid(1000))
                .output(dust, GoldDepleteMolybdenite, 3)
                .EUt(7680)
                .duration(100)
                .buildAndRegister();

        // AuCN + Na -> NaCN + Au
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(GoldCyanide.getFluid(1000))
                .input(dust, Sodium)
                .fluidOutputs(SodiumCyanide.getFluid(1000))
                .output(dust, Gold)
                .EUt(1920)
                .duration(150)
                .buildAndRegister();

        // MoS2 + FeCl3 ->
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, GoldDepleteMolybdenite, 3)
                .fluidInputs(Iron3Chloride.getFluid(500))
                .output(dust, MolybdenumConcentrate, 4)
                .fluidOutputs(ChlorideLeachedSolution.getFluid(500))
                .EUt(1920)
                .duration(10)
                .buildAndRegister();

        // 8? -> CaCl2 + CuCl2 + PbCl2 + BiCl3 + 8FeCl2
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(ChlorideLeachedSolution.getFluid(8000))
                .output(dust, CalciumChloride, 3)
                .output(dust, CopperChloride, 3)
                .output(dust, LeadChloride, 3)
                .output(dust, BismuthChloride, 4)
                .fluidOutputs(Iron2Chloride.getFluid(8000))
                .EUt(480)
                .duration(200)
                .buildAndRegister();

        // FeCl2 + Cl -> FeCl3
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Iron2Chloride.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(1000))
                .fluidOutputs(Iron3Chloride.getFluid(1000))
                .EUt(30)
                .duration(150)
                .buildAndRegister();

        // CuCl2 -> Cu + Cl2
        ELECTROLYZER_RECIPES.recipeBuilder()
                .input(dust, CopperChloride, 3)
                .output(dust, Copper)
                .fluidOutputs(Chlorine.getFluid(2000))
                .EUt(120)
                .duration(100)
                .buildAndRegister();

        // PbCl2 -> Pb + Cl2
        ELECTROLYZER_RECIPES.recipeBuilder()
                .input(dust, LeadChloride, 3)
                .output(dust, Lead)
                .fluidOutputs(Chlorine.getFluid(2000))
                .EUt(120)
                .duration(100)
                .buildAndRegister();

        // BiCl3 -> Bi + 3Cl
        ELECTROLYZER_RECIPES.recipeBuilder()
                .input(dust, BismuthChloride, 4)
                .output(dust, Bismuth)
                .fluidOutputs(Chlorine.getFluid(3000))
                .EUt(120)
                .duration(100)
                .buildAndRegister();

        // ? + ~7O -> 3? + MoO3
        BLAST_RECIPES.recipeBuilder()
                .input(dust, MolybdenumConcentrate, 4)
                .fluidInputs(Oxygen.getFluid(7250))
                .blastFurnaceTemp(8550)
                .EUt(120)
                .duration(340)
                .fluidOutputs(MolybdenumFlue.getFluid(3000))
                .output(dust, MolybdenumTrioxide, 4)
                .buildAndRegister();

        // 6H + MoO3 -> Mo + 3H2O(steam)
        BLAST_RECIPES.recipeBuilder()
                .fluidInputs(Hydrogen.getFluid(6000))
                .input(dust, MolybdenumTrioxide, 4)
                .output(dust, Molybdenum)
                .fluidOutputs(Steam.getFluid(3000))
                .blastFurnaceTemp(1000)
                .EUt(120)
                .duration(200)
                .buildAndRegister();

        // 3? + 0.5NH3 -> 3SO2 + 0.5NH4ReO4
        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(MolybdenumFlue.getFluid(3000))
                .fluidInputs(Water.getFluid(250))
                .fluidOutputs(RheniumSulfuricSolution.getFluid(3000))
                .EUt(491520)
                .duration(30)
                .buildAndRegister();

        // ? + 0.5NH3 -> 3SO2 + 0.5NH4ReO4
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(RheniumSulfuricSolution.getFluid(3000))
                .fluidInputs(Ammonia.getFluid(500))
                .fluidOutputs(SulfurDioxide.getFluid(3000))
                .fluidOutputs(AmmoniumPerrhenate.getFluid(500))
                .EUt(480)
                .duration(90)
                .buildAndRegister();

        // NH4ReO4 + 7H -> Re + NH3 + 4H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(AmmoniumPerrhenate.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(7000))
                .output(dust, Rhenium)
                .fluidOutputs(Ammonia.getFluid(1000))
                .fluidOutputs(Water.getFluid(4000))
                .EUt(480)
                .duration(110)
                .buildAndRegister();

        // Hg + 2CH3COOH -> C4H6O4Hg + H2
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Mercury.getFluid(1000))
                .fluidInputs(AceticAcid.getFluid(2000))
                .output(dust, MercuryAcetate, 15)
                .fluidOutputs(Hydrogen.getFluid(2000))
                .EUt(480)
                .duration(90)
                .buildAndRegister();

        // C2H4O + CH3NH2 -> C3H9NO
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(EthyleneOxide.getFluid(1000))
                .fluidInputs(Methylamine.getFluid(1000))
                .fluidOutputs(Methylethanolamine.getFluid(1000))
                .EUt(480)
                .duration(90)
                .buildAndRegister();

        // CaC2 + N2 -> CaCN2 + C
        BLAST_RECIPES.recipeBuilder()
                .input(dust, CalciumCarbide, 3)
                .fluidInputs(Nitrogen.getFluid(2000))
                .output(dust, CalciumCyanamide, 4)
                .output(dust, Carbon)
                .blastFurnaceTemp(2000)
                .EUt(120)
                .duration(360)
                .buildAndRegister();

        // 2HCl + C3H9NO + O + CaCN2 -> C2H7N3 + CH3COOH + CaCl2
        CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .fluidInputs(Methylethanolamine.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(1000))
                .input(dust, CalciumCyanamide, 4)
                .fluidOutputs(Methylguanidine.getFluid(1000))
                .fluidOutputs(AceticAcid.getFluid(1000))
                .output(dust, CalciumChloride, 3)
                .notConsumable(dust, Palladium)
                .notConsumable(dust, MercuryAcetate, 1)
                .EUt(480)
                .duration(240)
                .buildAndRegister();

        // C2H7N3 + HNO3 + H2SO4 + HCl + NaNO2 -> 2H2SO4(dil.) + C2H5N5O3 + NaCl
        // Not perfect, but was the best way to try and conserve acids
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Methylguanidine.getFluid(1000))
                .fluidInputs(NitrationMixture.getFluid(2000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .input(dust, SodiumNitrite, 4)
                .fluidOutputs(DilutedSulfuricAcid.getFluid(2000))
                .fluidOutputs(Methylnitronitrosoguanidine.getFluid(1000))
                .output(dust, Salt, 2)
                .EUt(480)
                .duration(220)
                .buildAndRegister();

        // C6H12O6 + bacteria -> C5H12O
        BIO_REACTOR_RECIPES.recipeBuilder()
                .input(dust, Glucose, 24)
                .notConsumable(dust, SelectivelyMutatedCupriavidiusNecator, 1)
                .fluidInputs(Ammonia.getFluid(10))
                .fluidOutputs(IsoamylAlcohol.getFluid(1000))
                .EUt(491520)
                .duration(20)
                .buildAndRegister();

        // C8H16 + H2O -> C8H18O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Oct1ene.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(Octanol.getFluid(1000))
                .notConsumable(PhosphoricAcid.getFluid(1))
                .notConsumable(dust, PalladiumLoadedRutileNanoparticles, 1)
                .notConsumable(UVA_HALIDE_LAMP.getStackForm())
                .EUt(30720)
                .duration(110)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .input(dust, CupriavidusNecator, 2)
                .notConsumable(UVA_HALIDE_LAMP)
                .fluidInputs(Methylnitronitrosoguanidine.getFluid(250))
                .output(dust, SelectivelyMutatedCupriavidiusNecator, 1)
                .EUt(30720)
                .duration(20)
                .buildAndRegister();

        // 3C8H18O + NH3 -> 3H2O + C24H51N
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Octanol.getFluid(3000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidOutputs(Water.getFluid(3000))
                .fluidOutputs(Trioctylamine.getFluid(1000))
                .EUt(480)
                .duration(90)
                .buildAndRegister();

        // 2C12H26 + 0.5C24H51N + 0.25C8H18O + 0.5C6H12O + 0.375CH3COOH + 0.375C5H12O -> ~4C11H24
        ADVANCED_MIXER_RECIPES.recipeBuilder()
                .fluidInputs(Kerosene.getFluid(8000))
                .fluidInputs(Trioctylamine.getFluid(500))
                .fluidInputs(Octanol.getFluid(500))
                .fluidInputs(MethylIsobutylKetone.getFluid(250))
                .fluidInputs(AceticAcid.getFluid(250))
                .fluidInputs(IsoamylAlcohol.getFluid(250))
                .fluidOutputs(RheniumSeparationMixture.getFluid(10000))
                .EUt(7680)
                .duration(90)
                .buildAndRegister();

        // ? + RSM -> ? + ?(20%) + Re
        CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, PlatinumSalt, 2)
                .fluidInputs(RheniumSeparationMixture.getFluid(250))
                .chancedOutput(dust, PlatinumSaltRefined, 2, 9500, 0)
                .fluidOutputs(RheniumScrubbedSolution.getFluid(250))
                .fluidOutputs(RheniumSeparationMixture.getFluid(200))
                .EUt(7680)
                .duration(50)
                .buildAndRegister();

        // ? + 0.2? -> ? + Re
        CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, LeachedColumbite, 17)
                .fluidInputs(RheniumSeparationMixture.getFluid(250))
                .output(dust, PurifiedColumbite, 1)
                .fluidOutputs(RheniumScrubbedSolution.getFluid(250))
                .fluidOutputs(RheniumSeparationMixture.getFluid(200))
                .EUt(7680)
                .duration(50)
                .buildAndRegister();

        //  + 0.2? -> ? + Re
        CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, LeachedPyrochlore, 1)
                .fluidInputs(RheniumSeparationMixture.getFluid(250))
                .output(dust, PurifiedPyrochlore, 1)
                .fluidOutputs(RheniumScrubbedSolution.getFluid(250))
                .fluidOutputs(RheniumSeparationMixture.getFluid(200))
                .EUt(7680)
                .duration(50)
                .buildAndRegister();

        // ? + 5HF -> H2NbOF5
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, PurifiedColumbite, 1)
                .fluidInputs(HydrofluoricAcid.getFluid(5000))
                .fluidOutputs(FluoroniobicAcid.getFluid(1000))
                .EUt(480)
                .duration(180)
                .buildAndRegister();

        // Nb(H2O) + 5HF -> H2NbOF5
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, PurifiedPyrochlore, 1)
                .fluidInputs(HydrofluoricAcid.getFluid(5000))
                .fluidOutputs(FluoroniobicAcid.getFluid(1000))
                .EUt(480)
                .duration(180)
                .buildAndRegister();

        // Re + 3NH3 + H2SO4 -> (NH4)2SO4 + NH4ReO4
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(RheniumScrubbedSolution.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(3000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(AmmoniumSulfate.getFluid(1000))
                .fluidOutputs(AmmoniumPerrhenate.getFluid(1000))
                .EUt(1920)
                .duration(110)
                .buildAndRegister();
    }
}
