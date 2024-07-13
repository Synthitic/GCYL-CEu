package com.fulltrix.gcyl.recipes.chain.updated;

import gregtech.api.recipes.chance.output.ChancedOutputLogic;
import gregtech.api.unification.material.Material;

import static com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES;
import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static com.fulltrix.gcyl.materials.chains.NewREEMaterials.*;
import static gregtech.api.GTValues.IV;
import static gregtech.api.GTValues.VA;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class NewREEChain {

    public static void init() {
        Bastnasite();
        Monazite();
        Xenotime();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(400).EUt(480)
                .input(dust, RareEarth, 3)
                .chancedOutput(dust, Monazite, 2500, 1000)
                .chancedOutput(dust, Xenotime, 2500, 1000)
                .chancedOutput(dust, Bastnasite, 2500, 1000)
                .buildAndRegister();

    }

    private static void Bastnasite() {
        // 2CeCO3F (s) + 3H2SO4 (aq) -> Ce2(SO4)3 (aq) + 2CO2 (g) + 2HF (aq) + 2H2O (l)
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Bastnasite, 12)
                .fluidInputs(SulfuricAcid.getFluid(3000))
                .fluidOutputs(CeriumRichLREESulfateSolution.getFluid(1000))
                .fluidOutputs(CarbonDioxide.getFluid(2000))
                .fluidOutputs(HydrofluoricAcid.getFluid(2000))
                .fluidOutputs(Water.getFluid(2000))
                .EUt(1920)
                .duration(600)
                .buildAndRegister();

        // Ce2(SO4)3 (aq) + 6NaOH (s) -> 2Ce(OH)3 (s) + 3Na2SO4 (s)
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, SodiumHydroxide, 18)
                .fluidInputs(CeriumRichLREESulfateSolution.getFluid(1000))
                .fluidOutputs(CeriumRichLREEHydroxides.getFluid(2000))
                .output(dust, SodiumSulfate, 21)
                .EUt(960)
                .duration(450)
                .buildAndRegister();

        // 4Ce(OH)3 (s) + O (g) -> 2CeO2 (s) + REE2O3 (s) + 3H2O (l)
        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(CeriumRichLREEHydroxides.getFluid(2000))
                .fluidInputs(Oxygen.getFluid(1000))
                .output(dust, CeriumOxide, 6)
                .output(dust, LREEOxides, 5)
                .fluidOutputs(Water.getFluid(3000))
                .EUt(960)
                .duration(450)
                .buildAndRegister();

        SeparationProcess(LREENitrateSolution, LREEOxalates, LREEOxides, LREEPentachlorides, LREEChlorides);

        ELECTROLYZER_RECIPES.recipeBuilder()
                .input(dust, LREEChlorides, 4)
                .chancedOutputLogic(ChancedOutputLogic.XOR)
                .chancedOutput(dust, LanthanumOxide, 5, 5000, 50)
                .chancedOutput(dust, PraseodymiumOxide, 5, 1400, 50)
                .chancedOutput(dust, NeodymiumOxide, 5, 3600, 50)
                .fluidOutputs(Chlorine.getFluid(3000))
                .duration(400)
                .EUt(480)
                .buildAndRegister();
    }

    private static void Monazite() {
        // LnPO4Th3(PO4)4 (s) + 15NaOH (s) + H2O (l) -> Ln(OH)3 (aq) + 3Th(OH)4 (aq) + H2O (l) + 5Na3PO4 (s)
        AUTOCLAVE_RECIPES.recipeBuilder()
                .input(dust, Monazite, 30)
                .input(dust, SodiumHydroxide, 45)
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(MonaziteHydroxideSolution.getFluid(1000))
                .output(dust, TrisodiumPhosphate, 40)
                .duration(450)
                .EUt(480)
                .buildAndRegister();

        // Ln(OH)3 (aq) + 3Th(OH)4 (aq) + 15HNO3 (aq) -> Ln(NO3)3 (aq) + 3Th(NO3)4*5H2O (s)
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(MonaziteHydroxideSolution.getFluid(1000))
                .fluidInputs(NitricAcid.getFluid(15000))
                .fluidOutputs(MREENitrateSolution.getFluid(500))
                .fluidOutputs(LREENitrateSolution.getFluid(500))
                .output(dust, ThoriumNitratePentahydrate, 30)
                .duration(450)
                .EUt(VA[IV])
                .buildAndRegister();

        // Th(NO3)4*5H2O (s) -> ThO2 (s) + 4NO2 (aq) + 5H2O (g) + O2 (g)
        CENTRIFUGE_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .input(dust, ThoriumNitratePentahydrate, 30)
                .fluidInputs(Air.getFluid(30000))
                .output(dust, ThoriumDioxide, 3)
                .fluidOutputs(NitrogenDioxide.getFluid(4000))
                .fluidOutputs(Steam.getFluid(5000))
                .fluidOutputs(IrradiatedAir.getFluid(30000))
                .duration(400)
                .EUt(1920)
                .buildAndRegister();

        SeparationProcess(MREENitrateSolution, MREEOxalates, MREEOxides, MREEPentachlorides, MREEChlorides);

        ELECTROLYZER_RECIPES.recipeBuilder()
                .input(dust, MREEChlorides, 8)
                .chancedOutputLogic(ChancedOutputLogic.XOR)
                .chancedOutput(dust, SamariumOxide, 5, 5000, 50)
                .chancedOutput(dust, EuropiumOxide, 5, 500, 50)
                .chancedOutput(dust, GadoliniumOxide, 5, 3000, 50)
                .chancedOutput(dust, TerbiumOxide, 5, 1500, 50)
                .fluidOutputs(Chlorine.getFluid(6000))
                .duration(400)
                .EUt(480)
                .buildAndRegister();
    }

    private static void Xenotime() {
        // 2REEPO4 (s) + 3H2SO4 (aq) -> REE2(SO4)3 (aq) + 2H3PO4 (aq)
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Xenotime, 8)
                .fluidInputs(SulfuricAcid.getFluid(3000))
                .fluidOutputs(XenotimeSulfateSolution.getFluid(1000))
                .fluidOutputs(PhosphoricAcid.getFluid(2000))
                .duration(400)
                .EUt(1920)
                .buildAndRegister();

        // REE2(SO4)3 (aq) + 10NaOH (s) -> [2REE(OH)3 (aq) + Th(OH)4 (aq) + Fe (aq)] + 3Na2SO4 (s)
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(XenotimeSulfateSolution.getFluid(1000))
                .input(dust, SodiumHydroxide, 30)
                .fluidOutputs(XenotimeHydroxideSolution.getFluid(1000))
                .output(dust, SodiumSulfate, 21)
                .duration(400)
                .EUt(1920)
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(XenotimeHydroxideSolution.getFluid(1000))
                .fluidInputs(NitricAcid.getFluid(10000))
                .notConsumable(DEHPA.getFluid(3000))
                .notConsumable(Kerosene.getFluid(7000))
                .fluidOutputs(HREENitrateSolution.getFluid(900))
                .fluidOutputs(MREENitrateSolution.getFluid(100))
                .output(dust, ThoriumNitratePentahydrate, 10)
                .output(dust, Iron, 1)
                .fluidOutputs(Water.getFluid(5000))
                .duration(900)
                .EUt(1920)
                .buildAndRegister();

        SeparationProcess(HREENitrateSolution, HREEOxalates, HREEOxides, HREEPentachlorides, HREEChlorides);

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .input(dust, HREEChlorides, 8)
                .notConsumable(AmmoniumThiocyanate.getFluid(10000))
                .chancedOutputLogic(ChancedOutputLogic.XOR)
                .chancedOutput(dust, DysprosiumOxide, 5, 1000, 50)
                .chancedOutput(dust, HolmiumOxide, 5, 200, 50)
                .chancedOutput(dust, ErbiumOxide, 5, 600, 50)
                .chancedOutput(dust, ThuliumOxide, 5, 100, 50)
                .chancedOutput(dust, YtterbiumOxide, 5, 700, 50)
                .chancedOutput(dust, LutetiumOxide, 5, 40, 50)
                .chancedOutput(dust, YttriumOxide, 5, 7200, 50)
                .fluidOutputs(Chlorine.getFluid(6000))
                .duration(400)
                .EUt(480)
                .buildAndRegister();
    }

    private static void SeparationProcess(Material nitrate, Material oxalate, Material oxide, Material pentachloride,
                                          Material chloride) {
        // 2REE(NO3)3 (aq) + 3C2H2O4 (s) -> REE2(C2O4)3 (s) + 6HNO3 (aq)
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(nitrate.getFluid(2000))
                .input(dust, OxalicAcid, 24)
                .output(dust, oxalate, 1)
                .fluidOutputs(NitricAcid.getFluid(6000))
                .duration(2000)
                .EUt(1920)
                .buildAndRegister();

        // 2REE2(C2O4)3 (s) + 3O2 (g) -> 2REE2O3 + 12CO2
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, oxalate, 2)
                .fluidInputs(Oxygen.getFluid(6000))
                .output(dust, oxide, 10)
                .fluidOutputs(CarbonDioxide.getFluid(12000))
                .duration(2000)
                .EUt(1920)
                .buildAndRegister();

        // REE2O3 (s) + 10NH4Cl (s) -> 2(NH4)2[REECl5] (s) + 6NH3 (g) + 3H2O (l)
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, oxide, 5)
                .input(dust, AmmoniumChloride, 20)
                .output(dust, pentachloride, 6)
                .fluidOutputs(Ammonia.getFluid(6000))
                .fluidOutputs(Water.getFluid(3000))
                .duration(2000)
                .EUt(1920)
                .buildAndRegister();

        // (NH4)2[REECl5] (s) -> REECl3 (s) + 2NH4Cl (s)
        CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, pentachloride, 3)
                .output(dust, chloride, 4)
                .output(dust, AmmoniumChloride, 4)
                .duration(300)
                .EUt(480)
                .buildAndRegister();
    }
}
