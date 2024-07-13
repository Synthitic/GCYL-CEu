package com.fulltrix.gcyl.recipes.chain.updated;

import gregtech.api.fluids.store.FluidStorageKeys;

import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static com.fulltrix.gcyl.materials.chains.NewPlatinumGroupMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.crushedPurified;
import static gregtech.api.unification.ore.OrePrefix.dust;

//TODO add additional ores for processing
//TODO cleanroom requirements?
public class NewPlatinumGroupMetals {

    public static void init() {
        initial();
        osmium();
        platinum();
        iridium();
        palladium();
        ruthenium();
        rhodium();
        inerts();
    }

    private static void initial() {

        CHEMICAL_RECIPES.recipeBuilder().duration(50).EUt(VA[EV])
                .input(crushedPurified, PlatinumMetallicPowder)
                .fluidInputs(NitricAcid.getFluid(400))
                .output(dust, PlatinumGroupSludge, 8)
                .fluidOutputs(SulfuricNickelSolution.getFluid(2000))
                .fluidOutputs(PGMSolution1Plat.getFluid(100))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(50).EUt(VA[EV])
                .input(crushedPurified, PalladiumMetallicPowder)
                .fluidInputs(NitricAcid.getFluid(400))
                .output(dust, PlatinumGroupSludge, 8)
                .fluidOutputs(SulfuricNickelSolution.getFluid(2000))
                .fluidOutputs(PGMSolution1Pall.getFluid(100))
                .buildAndRegister();


        // should be technically 2.9 dust(every input was divided by 10), but w/e
        MIXER_RECIPES.recipeBuilder()
                .input(dust, PlatinumGroupSludge, 15)
                .fluidInputs(AquaRegia.getFluid(20550))
                .fluidInputs(NitricAcid.getFluid(1850))
                .fluidOutputs(PlatinumGroupSludge.getFluid(2900))
                .duration(160)
                .EUt(120)
                .buildAndRegister();

        // [H2OsCl6 (aq) + 7 H2PtCl6 (aq) + 3 H2IrCl6 (aq) + 7 H2PdCl4 (aq) + 4 RhCl3 (aq) + 3 RuCl3 (aq) + 2 AuCl3 (aq)
        // + 2 HAgCl2 (aq)] ->
        // H2OsCl6 (aq) + [7 H2PtCl6 (aq) + 3 H2IrCl6 (aq) + 7 H2PdCl4 (aq) + 4 RhCl3 (aq) + 3 RuCl3 (aq) + 2 AuCl3 (aq)
        // + 2 HAgCl2 (aq)]
        DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(PlatinumGroupSludge.getFluid(2900))
                .fluidOutputs(OsmiumChlorideSolution.getFluid(100))
                .fluidOutputs(PGMSolution1.getFluid(2800))
                .fluidOutputs(NitrogenDioxide.getFluid(8700))
                .fluidOutputs(Water.getFluid(8700))
                .duration(160)
                .EUt(120)
                .buildAndRegister();

        // Cl + NH3 -> NH4Cl
        CHEMICAL_RECIPES.recipeBuilder()
                .circuitMeta(2)
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .output(dust, AmmoniumChloride, 2)
                .duration(500)
                .EUt(VA[MV])
                .buildAndRegister();
    }

    private static void osmium() {
        // H2OsCl6 (aq) + 2K2S2O8 (s) + 4H2O (l) -> [4KHSO4 (aq) + OsO4 (aq) + 6HCl (aq)]
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(OsmiumChlorideSolution.getFluid(1000))
                .input(dust, PotassiumPersulfate, 24)
                .fluidOutputs(AcidicOsmiumTetroxideSolution.getFluid(1000))
                .duration(160)
                .EUt(120)
                .buildAndRegister();

        // [4KHSO4 (aq) + OsO4 (aq) + 4HCl (aq)] -> 4KHSO4 (s) + OsO4 (g) + 4HCl (g)
        DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(AcidicOsmiumTetroxideSolution.getFluid(1000))
                .output(dust, PotassiumBisulfate, 28)
                .fluidOutputs(OsmiumTetroxide.getFluid(FluidStorageKeys.GAS, 1000))
                .fluidOutputs(HydrochloricAcid.getFluid(4000))
                .duration(160)
                .EUt(120)
                .buildAndRegister();

        // 2OsO4 (g) + 5KOH (s) + CH3CH2OH (l) -> 2K2OsO2(OH)4 (s) + KCH3CO2 (s)
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(OsmiumTetroxide.getFluid(FluidStorageKeys.GAS, 2000))
                .fluidInputs(Ethanol.getFluid(1000))
                .input(dust, PotassiumHydroxide, 15)
                .output(dust, PotassiumOsmate, 26)
                .output(dust, PotassiumAcetate, 8)
                .duration(160)
                .EUt(120)
                .buildAndRegister();

        // first route, needs funny catalyst
        // 2K2OsO2(OH)4 (s) + 6NaBH4 (aq) --> 2Os (s) + 3B2H6 (g) + 4KOH (aq) + 6NaOH (aq) + 2H2O (l)
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, PotassiumOsmate, 26)
                .fluidInputs(SodiumBorohydride.getFluid(6000))
                .output(dust, Osmium, 2)
                .fluidOutputs(Diborane.getFluid(3000))
                .fluidOutputs(PotassiumHydroxide.getFluid(4000))
                .output(dust, SodiumHydroxide, 18)
                .fluidOutputs(Water.getFluid(2000))
                .duration(3600)
                .EUt(VH[LuV])
                .buildAndRegister();

        // FASTER ROUTE, unlocked later?
        // K2OsO2(OH)4 + 3H2 (g) -> Os (s) + 2KOH (s) + 4H2O (g)
        BLAST_RECIPES.recipeBuilder()
                .input(dust, PotassiumOsmate, 13)
                .fluidInputs(Hydrogen.getFluid(6000))
                .output(dust, Osmium, 1)
                .output(dust, PotassiumHydroxide, 6)
                .fluidOutputs(Steam.getFluid(4000))
                .blastFurnaceTemp(9200)
                .duration(1200)
                .EUt(VH[UV])
                .buildAndRegister();

        // recycling/catalysts
        // Na + H -> NaH
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Sodium, 1)
                .fluidInputs(Hydrogen.getFluid(1000))
                .notConsumable(MineralOil.getFluid(), 150)
                .output(dust, SodiumHydride, 2)
                .duration(160)
                .EUt(120)
                .buildAndRegister();

        // H3BO3 (s) + 3CH3OH (l) -> (CH3)3BO3 (l) + 3H2O (l)
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, BoricAcid, 7)
                .fluidInputs(Methanol.getFluid(3000))
                .fluidOutputs(TrimethylBorate.getFluid(1000))
                .fluidOutputs(Water.getFluid(3000))
                .duration(160)
                .EUt(120)
                .buildAndRegister();

        // (CH3)3BO3 (l) + 4NaH (s) -> NaBH4 (s) + 3NaCH3O (s)
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(TrimethylBorate.getFluid(3000))
                .input(dust, SodiumHydride, 8)
                .output(dust, SodiumBorohydride, 6)
                .output(dust, SodiumMethoxide, 18)
                .duration(160)
                .EUt(120)
                .buildAndRegister();

        // NaCH3O (s) + H2O (l) -> CH3OH (l) + NaOH (s)
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Water.getFluid(3000))
                .input(dust, SodiumMethoxide, 6)
                .fluidOutputs(Methanol.getFluid(1000))
                .output(dust, SodiumHydroxide, 3)
                .duration(160)
                .EUt(120)
                .buildAndRegister();

        // KCH3CO2 (s) + HCl (aq) -> KCl (s) + CH3COOH (aq)
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, PotassiumAcetate, 8)
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .output(dust, RockSalt, 1)
                .fluidOutputs(AceticAcid.getFluid(1000))
                .duration(160)
                .EUt(120)
                .buildAndRegister();

        // KHSO4 (s) -> KHSO4 (l)
        EXTRACTOR_RECIPES.recipeBuilder()
                .input(dust, PotassiumBisulfate, 7)
                .fluidOutputs(PotassiumBisulfate.getFluid(1000))
                .duration(800)
                .EUt(VA[LV])
                .buildAndRegister();

        // 2KHSO4 -> K2SO4 + SO3 + H2O
        ELECTROLYZER_RECIPES.recipeBuilder()
                .circuitMeta(3)
                .fluidInputs(PotassiumBisulfate.getFluid(2000))
                .output(dust, PotassiumSulfate, 7)
                .fluidOutputs(SulfurTrioxide.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .duration(1000)
                .EUt(VH[MV])
                .buildAndRegister();
    }

    private static void platinum() {
        {
            // [7 H2PtCl6 (aq) + 3 H2IrCl6 (aq) + 7 H2PdCl4 (aq) + 4 RhCl3 (aq) + 3 RuCl3 (aq) + 2 AuCl3 (aq) + 2 HAgCl2
            // (aq)] + FeCl2 (s) ->
            // [7 H2PtCl6 (aq) + 3 HIrCl4 (aq) + 7 H2PdCl4 (aq) + 4 RhCl3 (aq) + 3 RuCl3 (aq) + 2 AuCl3 (aq) + 2 HAgCl2
            // (aq) + HCl (aq)] + FeCl3 (s)
            CHEMICAL_RECIPES.recipeBuilder()
                    .fluidInputs(PGMSolution1.getFluid(28000))
                    .fluidInputs(FerrousChloride.getFluid(1000))
                    .fluidOutputs(PGMSolution1IR.getFluid(28000))
                    .fluidOutputs(Iron3Chloride.getFluid(1000))
                    .duration(160)
                    .EUt(120)
                    .buildAndRegister();
        }

        // the amount of NH4Cl it needs is 2x platinum chloride sol
        {
            // [7 H2PtCl6 (aq) + 3 HIrCl4 (aq) + 7 H2PdCl4 (aq) + 4 RhCl3 (aq) + 3 RuCl3 (aq) + 2 AuCl3 (aq) + 2 HAgCl2
            // (aq) + HCl (aq)] + 14NH4Cl (s) ->
            // [7 (NH4)2PtCl6 (aq) + 3 HIrCl4 (aq) + 7 H2PdCl4 (aq) + 4 RhCl3 (aq) + 3 RuCl3 (aq) + 2 AuCl3 (aq) + 2
            // HAgCl2 (aq) + 15HCl (aq)]
            MIXER_RECIPES.recipeBuilder()
                    .input(dust, AmmoniumChloride, 28)
                    .fluidInputs(PGMSolution1IR.getFluid(28000))
                    .fluidOutputs(PGMSolution1Plat.getFluid(28000))
                    .duration(160)
                    .EUt(120)
                    .buildAndRegister();
        }

        // the amount of HCl it gives is 1 + 2n(n is amount of platinate)
        {
            // [7 (NH4)2PtCl6 (aq) + 3 HIrCl4 (aq) + 7 H2PdCl4 (aq) + 4 RhCl3 (aq) + 3 RuCl3 (aq) + 2 AuCl3 (aq) + 2
            // HAgCl2 (aq) + 15HCl (aq)] ->
            // 7 (NH4)2PtCl6 (s) + [3 HIrCl4 (aq) + 7 H2PdCl4 (aq) + 4 RhCl3 (aq) + 3 RuCl3 (aq) + 2 AuCl3 (aq) + 2
            // HAgCl2 (aq)] + 15HCl (aq)
            DISTILLATION_RECIPES.recipeBuilder()
                    .fluidInputs(PGMSolution1Plat.getFluid(28000))
                    .output(dust, AmmoniumHexachloroplatinate, 7)
                    .fluidOutputs(PGMSolution1Iri.getFluid(21000))
                    .fluidOutputs(HydrochloricAcid.getFluid(15000))
                    .duration(160)
                    .EUt(120)
                    .buildAndRegister();
        }

        // slower route?
        // (NH4)2PtCl6 (s) + 2H2 (g) -> Pt (s) + 2NH4Cl (s) + 4HCl (g)
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, AmmoniumHexachloroplatinate, 1)
                .fluidInputs(Hydrogen.getFluid(4000))
                .output(dust, Platinum, 1)
                .output(dust, AmmoniumChloride, 4)
                .fluidOutputs(HydrochloricAcid.getFluid(4000))
                .duration(160)
                .EUt(120)
                .buildAndRegister();

        // (NH4)2PtCl6 (s) + N2H4 (g) -> Pt (s) + 2NH4Cl (s) + 4HCl (g) + N2 (g)
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, AmmoniumHexachloroplatinate, 1)
                .fluidInputs(Hydrazine.getFluid(1000))
                .output(dust, Platinum, 1)
                .output(dust, AmmoniumChloride, 4)
                .fluidOutputs(HydrochloricAcid.getFluid(4000))
                .fluidOutputs(Nitrogen.getFluid(2000))
                .duration(160)
                .EUt(120)
                .buildAndRegister();
    }

    private static void iridium() {
        // HCl and Cl2 need to be the same as the iridate
        {
            // 2[3 HIrCl4 (aq) + 7 H2PdCl4 (aq) + 4 RhCl3 (aq) + 3 RuCl3 (aq) + 2 AuCl3 (aq) + 2 HAgCl2 (aq)] + 6 HCl
            // (aq) + 3 Cl2 (g) ->
            // 2[3 H2IrCl6 (aq) + 7 H2PdCl4 (aq) + 4 RhCl3 (aq) + 3 RuCl3 (aq) + 2 AuCl3 (aq) + 2 HAgCl2 (aq)]
            CHEMICAL_RECIPES.recipeBuilder()
                    .fluidInputs(PGMSolution1Iri.getFluid(21000 * 2))
                    .fluidInputs(HydrochloricAcid.getFluid(6000))
                    .fluidInputs(Chlorine.getFluid(6000))
                    .fluidOutputs(PGMSolution1IriOxide.getFluid(21000 * 2))
                    .duration(160)
                    .EUt(120)
                    .buildAndRegister();
        }

        // NH4Cl is double what iridate your making, gives same amount of HCl
        {
            // [3 H2IrCl6 (aq) + 7 H2PdCl4 (aq) + 4 RhCl3 (aq) + 3 RuCl3 (aq) + 2 AuCl3 (aq) + 2 HAgCl2 (aq)] + 6 NH4Cl
            // (s) ->
            // 3(NH4)2IrCl6 (s) + 6 HCl (aq) + [7 H2PdCl4 (aq) + 4 RhCl3 (aq) + 3 RuCl3 (aq) + 2 AuCl3 (aq) + 2 HAgCl2
            // (aq)]
            CHEMICAL_RECIPES.recipeBuilder()
                    .input(dust, AmmoniumChloride, 12)
                    .fluidInputs(PGMSolution1IriOxide.getFluid(21000))
                    .output(dust, AmmoniumHexachloroiridate, 6)
                    .fluidOutputs(HydrochloricAcid.getFluid(6000))
                    .fluidOutputs(PGMSolution1Pall.getFluid(18000))
                    .duration(160)
                    .EUt(120)
                    .buildAndRegister();
        }

        // first path, slower
        // (NH4)2IrCl6 (s) + 2H2 (g) -> Ir (s) + 2NH4Cl (s) + 4HCl (g)
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, AmmoniumHexachloroiridate, 2)
                .fluidInputs(Hydrogen.getFluid(4000))
                .output(dust, Iridium, 1)
                .output(dust, AmmoniumChloride, 4)
                .fluidOutputs(HydrochloricAcid.getFluid(4000))
                .duration(160)
                .EUt(120)
                .buildAndRegister();

        // second path
        // (NH4)2IrCl6 (s) + N2H4 (l) + 6NaOH (aq) -> Ir (s) + N2 (g) + 2NH3 (g) + 6NaCl (aq) + 6H2O (l)
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, AmmoniumHexachloroiridate, 2)
                .input(dust, SodiumHydroxide, 6)
                .fluidInputs(Hydrazine.getFluid(1000))
                .output(dust, Iridium, 1)
                .fluidOutputs(Nitrogen.getFluid(2000))
                .fluidOutputs(Ammonia.getFluid(2000))
                .fluidOutputs(SaltWater.getFluid(6000))
                .duration(160)
                .EUt(120)
                .buildAndRegister();
    }

    private static void palladium() {
        // route 1
        // need double the C4H8N2O2 as palladium, makes 4 times the HCl
        {
            // [7 H2PdCl4 (aq) + 4 RhCl3 (aq) + 3 RuCl3 (aq) + 2 AuCl3 (aq) + 2 HAgCl2 (aq)] + 14 C4H8N2O2 (s) ->
            // 7 (C4H7N2O2)2Pd (s) + 28 HCl (aq) + [4 RhCl3 (aq) + 3 RuCl3 (aq) + 2 AuCl3 (aq) + 2 HAgCl2 (aq)]
            CHEMICAL_RECIPES.recipeBuilder()
                    .fluidInputs(PGMSolution1Pall.getFluid(18000))
                    .input(dust, Dimethylglyoxime, 14)
                    .output(dust, PalladiumDimethylglyoxime, 21)
                    .fluidOutputs(HydrochloricAcid.getFluid(28000))
                    .fluidOutputs(PGMSolution1Rho.getFluid(11000))
                    .duration(160)
                    .EUt(120)
                    .buildAndRegister();
        }

        // (C4H7N2O2)2Pd (s) + 2HCl (aq) -> PdCl2 (s) + 2C4H8N2O2 (s)
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, PalladiumDimethylglyoxime, 3)
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .output(dust, PalladiumChloride, 3)
                .output(dust, Dimethylglyoxime, 2)
                .duration(160)
                .EUt(120)
                .buildAndRegister();

        // PdCl2 (s) + H2 (g) -> Pd (s) + 2HCl (g)
        BLAST_RECIPES.recipeBuilder()
                .input(dust, PalladiumChloride, 3)
                .fluidInputs(Hydrogen.getFluid(2000))
                .output(dust, Palladium, 1)
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .duration(160)
                .EUt(120)
                .buildAndRegister();

        // route 2
        {
            // [7 H2PdCl4 (aq) + 4 RhCl3 (aq) + 3 RuCl3 (aq) + 2 AuCl3 (aq) + 2 HAgCl2 (aq)] + 7 Cl2 (g) + 14 NH4Cl (s)
            // ->
            // 7 (NH4)2PdCl6 (s) + 14HCl (aq) + [4 RhCl3 (aq) + 3 RuCl3 (aq) + 2 AuCl3 (aq) + 2 HAgCl2 (aq)]
            CHEMICAL_RECIPES.recipeBuilder()
                    .input(dust, AmmoniumChloride, 28)
                    .fluidInputs(PGMSolution1Pall.getFluid(18000))
                    .fluidInputs(Chlorine.getFluid(14000))
                    .output(dust, AmmoniumHexachloropalladate, 63)
                    .fluidOutputs(HydrochloricAcid.getFluid(14000))
                    .fluidOutputs(PGMSolution1Rho.getFluid(11000))
                    .duration(160)
                    .EUt(120)
                    .buildAndRegister();
        }

        // 3(NH4)2PdCl6 (s) + 20NH3 (g) -> 3Pd(NH3)4Cl2 (s) + 12NH4Cl (s) + N2 (g)
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, AmmoniumHexachloropalladate, 27)
                .fluidInputs(Ammonia.getFluid(20000))
                .output(dust, TetraaminePalladiumChloride, 21)
                .output(dust, AmmoniumChloride, 24)
                .fluidOutputs(Nitrogen.getFluid(2000))
                .duration(160)
                .EUt(120)
                .buildAndRegister();

        // Pd(NH3)4Cl2 (s) + 2HCl (aq) -> Pd(NH3)2Cl2 (s) + 2NH4Cl (s)
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, TetraaminePalladiumChloride, 7)
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .output(dust, DiamineDichloropalladium, 5)
                .output(dust, AmmoniumChloride, 4)
                .duration(160)
                .EUt(120)
                .buildAndRegister();

        // path 1
        // 3 Pd(NH3)2Cl2 (s) —> 3Pd (s) + 4NH4Cl (s) + 2HCl (g) + N2 (g)
        ELECTROLYZER_RECIPES.recipeBuilder()
                .input(dust, DiamineDichloropalladium, 15)
                .output(dust, Palladium, 3)
                .output(dust, AmmoniumChloride, 8)
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .fluidOutputs(Nitrogen.getFluid(2000))
                .duration(160)
                .EUt(120)
                .buildAndRegister();

        // path 2, faster
        // 2 Pd(NH3)2Cl2 (s) + N2H4 (l) —> 2Pd (s) + 4NH4Cl (s) + N2 (g)
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, DiamineDichloropalladium, 10)
                .fluidInputs(Hydrazine.getFluid(1000))
                .output(dust, Palladium, 2)
                .output(dust, AmmoniumChloride, 8)
                .fluidOutputs(Nitrogen.getFluid(2000))
                .duration(160)
                .EUt(120)
                .buildAndRegister();
    }

    private static void rhodium() {
        // need 3x the nitric acid as rhodium and makes 3x the HCl
        {
            // [4 RhCl3 (aq) + 3 RuCl3 (aq) + 2 AuCl3 (aq) + 2 HAgCl2 (aq)] + 12 HNO3 (aq) ->
            // 4 Rh(NO3)3 (aq) + 12 HCl (aq) + [3 RuCl3 (aq) + 2 AuCl3 (aq) + 2 HAgCl2 (aq)]
            LARGE_CHEMICAL_RECIPES.recipeBuilder()
                    .fluidInputs(PGMSolution1Rho.getFluid(11000))
                    .fluidInputs(NitricAcid.getFluid(12000))
                    .fluidOutputs(RhodiumNitrate.getFluid(4000))
                    .fluidOutputs(HydrochloricAcid.getFluid(12000))
                    .fluidOutputs(PGMSolution1Ruth.getFluid(7000))
                    .duration(160)
                    .EUt(120)
                    .buildAndRegister();
        }

        // KNO3 (l) + Pb (l) -> KNO2 (s) + PbO (s)
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(PotassiumNitrate.getFluid(1000))
                .fluidInputs(Lead.getFluid(1000))
                .output(dust, PotassiumNitrite, 4)
                .output(dust, LeadOxide, 2)
                .duration(160)
                .EUt(120)
                .buildAndRegister();

        // Rh(NO3)3 (aq) + 6KNO2 (s) -> K3Rh(NO2)6 (s) + 3KNO3 (aq)
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(RhodiumNitrate.getFluid(1000))
                .input(dust, PotassiumNitrite, 24)
                .output(dust, PotassiumHexanitrorhodate, 10)
                .fluidOutputs(PotassiumNitrate.getFluid(3000))
                .duration(4000)
                .EUt(VH[EV])
                .buildAndRegister();

        // K3Rh(NO2)6 (s) + 6HCl (aq) -> K3RhCl6 (aq) + 3H2O (l) + 3NO2 (g) + 3NO (g)
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, PotassiumHexanitrorhodate, 10)
                .fluidInputs(HydrochloricAcid.getFluid(6000))
                .fluidOutputs(PotassiumHexachlororhodateSolution.getFluid(1000))
                .fluidOutputs(NitrogenDioxide.getFluid(3000))
                .fluidOutputs(NitricOxide.getFluid(3000))
                .duration(3000)
                .EUt(VH[IV])
                .buildAndRegister();

        // K3RhCl6 (aq) + 3H2O (l) + 3NH4Cl (s) -> (NH4)3RhCl6 (s) + 3KCl (aq) + 3H2O (l)
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, AmmoniumChloride, 6)
                .fluidInputs(PotassiumHexachlororhodateSolution.getFluid(1000))
                .output(dust, AmmoniumHexachlororhodate, 10)
                .fluidOutputs(PotassiumChlorideSolution.getFluid(3000))
                .duration(3600)
                .EUt(VH[EV])
                .buildAndRegister();

        // route 1
        // 2(NH4)3RhCl6 (s) + 3H2 (g) -> 2Rh (s) + 6NH4Cl (s) + 6HCl (g)
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, AmmoniumHexachlororhodate, 20)
                .fluidInputs(Hydrogen.getFluid(6000))
                .output(dust, Rhodium, 2)
                .fluidOutputs(HydrochloricAcid.getFluid(6000))
                .output(dust, AmmoniumChloride, 12)
                .duration(14400)
                .EUt(VA[EV])
                .buildAndRegister();

        // route 2
        // 4(NH4)3RhCl6 (s) + 3N2H4 (l) + 24NaOH (aq) -> 4Rh (s) + 3N2 (g) + 12NH3 (g) + 24NaCl (aq) + 24H2O (l)
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, AmmoniumHexachlororhodate, 40)
                .input(dust, SodiumHydroxide, 72)
                .fluidInputs(Hydrazine.getFluid(3000))
                .output(dust, Rhodium, 4)
                .fluidOutputs(Nitrogen.getFluid(6000))
                .fluidOutputs(Ammonia.getFluid(12000))
                .fluidOutputs(SaltWater.getFluid(24000))
                .duration(3600)
                .EUt(VH[IV])
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder()
                .fluidInputs(PotassiumChlorideSolution.getFluid(1000))
                .output(dust, Potassium)
                .fluidOutputs(Chlorine.getFluid(1000))
                .duration(300)
                .EUt(VH[MV])
                .buildAndRegister();
    }

    private static void ruthenium() {
        // needs 3x the KCl and water
        // [3 RuCl3 (aq) + 2 AuCl3 (aq) + 2 HAgCl2 (aq)] + 9 KCl (s) + 3H2O -> 3[K3RuCl6 (aq) + H2O] + [2 AuCl3 (aq) + 2
        // HAgCl2 (aq)]
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(PGMSolution1Ruth.getFluid(7000))
                .input(dust, RockSalt, 18)
                .fluidInputs(Water.getFluid(3000))
                .fluidOutputs(PGMSolution1InertMetals.getFluid(4000))
                .fluidOutputs(PotassiumHexachlororuthenate.getFluid(3000))
                .duration(1800)
                .EUt(VA[HV])
                .buildAndRegister();

        // [6K3RuCl6 (aq) + 6H2O (l)] + 8NaClO3 (s) -> [6RuO4 (aq) + 6H2O (l) + 18KCl (aq) + 9Cl2 (aq)] + 8NaCl (s)
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, SodiumChlorate, 20)
                .fluidInputs(PotassiumHexachlororuthenate.getFluid(3000))
                .output(dust, Salt, 8)
                .fluidOutputs(ChlorinatedRutheniumTetroxideSolution.getFluid(3000))
                .duration(1500)
                .EUt(VH[HV])
                .buildAndRegister();

        // [2RuO4 (aq) + 3Cl2 (aq) + 6KCl (aq) + 2H2O (l)] -> 6KCl (s) + 2H2O (l) + 2RuO4 (g) + 3Cl2 (g)
        DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(ChlorinatedRutheniumTetroxideSolution.getFluid(1000))
                .output(dust, RockSalt, 12)
                .fluidOutputs(Water.getFluid(2000))
                .fluidOutputs(RutheniumTetroxide.getFluid(FluidStorageKeys.GAS, 2000))
                .fluidOutputs(Chlorine.getFluid(6000))
                .duration(800)
                .EUt(VA[HV])
                .buildAndRegister();

        // RuO4 (g) + [NaOH (aq) + H2O (l)] —> [NaHRuO5 (aq) + H2O (aq)]
        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(RutheniumTetroxide.getFluid(FluidStorageKeys.GAS, 1000))
                .fluidInputs(SodiumHydroxideSolution.getFluid(1000))
                .fluidOutputs(BasicRutheniumTetroxideSolution.getFluid(1000))
                .duration(200)
                .EUt(VA[MV])
                .buildAndRegister();

        // [NaHRuO5 (aq) + H2O (aq)] + CH3CH2OH (l) —> RuO2 (s) + NaCH3CO2 (aq) + 3H2O (l)
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(BasicRutheniumTetroxideSolution.getFluid(1000))
                .fluidInputs(Ethanol.getFluid(1000))
                .output(dust, RutheniumDioxide, 3)
                .fluidOutputs(SodiumAcetate.getFluid(1000))
                .fluidOutputs(Water.getFluid(3000))
                .duration(300)
                .EUt(VA[EV])
                .buildAndRegister();

        // RuO2 (s) + 2H2 (g) -> Ru (s) + 2H2O (g)
        BLAST_RECIPES.recipeBuilder()
                .input(dust, RutheniumDioxide, 3)
                .fluidInputs(Hydrogen.getFluid(4000))
                .output(dust, Ruthenium, 1)
                .fluidOutputs(Steam.getFluid(2000))
                .blastFurnaceTemp(3300)
                .duration(1600)
                .EUt(VA[EV])
                .buildAndRegister();

        // 2 NaOH (s) + 2 Cl (aq) -> H2O (l) + NaCl (s) + NaClO (s)
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .circuitMeta(6)
                .fluidInputs(Chlorine.getFluid(2000))
                .input(dust, SodiumHydroxide, 6)
                .fluidOutputs(Water.getFluid(1000))
                .output(dust, Salt, 2)
                .output(dust, SodiumHypochlorite, 3)
                .duration(240)
                .EUt(120)
                .buildAndRegister();

        // 3 NaClO (s) -> 2 NaCl (s) + NaClO3 (s)
        ELECTROLYZER_RECIPES.recipeBuilder()
                .input(dust, SodiumHypochlorite, 9)
                .output(dust, Salt, 4)
                .output(dust, SodiumChlorate, 5)
                .duration(450)
                .EUt(120)
                .buildAndRegister();

        // NaCH3CO2 (s) + HCl (aq) -> NaCl (s) + CH3COOH (aq)
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(SodiumAcetate.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .output(dust, Salt, 2)
                .fluidOutputs(AceticAcid.getFluid(1000))
                .duration(300)
                .EUt(120)
                .buildAndRegister();
    }

    private static void inerts() {
        // [2 AuCl3 (aq) + 2 HAgCl2 (aq)] -> 2 AgCl (s) + 2 HCl (l) + 2 AuCl3 (aq)
        DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(PGMSolution1InertMetals.getFluid(4000))
                .output(dust, SilverChloride, 4)
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .fluidOutputs(GoldChloride.getFluid(2000))
                .duration(600)
                .EUt(480)
                .buildAndRegister();

        // 2 AuCl3 (aq) + 3SO2 (g) + 6H2O (l) -> 2 Au (s) + 3SO3 (aq) + 6HCl (aq) + 3 H2O (aq)
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(GoldChloride.getFluid(2000))
                .fluidInputs(SulfurDioxide.getFluid(3000))
                .fluidInputs(Water.getFluid(6000))
                .output(dust, Gold, 2)
                .fluidOutputs(SulfurTrioxide.getFluid(3000))
                .fluidOutputs(HydrochloricAcid.getFluid(6000))
                .fluidOutputs(Water.getFluid(3000))
                .duration(400)
                .EUt(480)
                .buildAndRegister();
    }
}
