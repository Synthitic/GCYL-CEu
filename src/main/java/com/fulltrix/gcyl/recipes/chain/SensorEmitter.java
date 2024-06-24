package com.fulltrix.gcyl.recipes.chain;

import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static com.fulltrix.gcyl.item.GCYLCoreItems.*;
import static com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps.CHEMICAL_PLANT_RECIPES;
import static com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps.ADVANCED_MIXER_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class SensorEmitter {
    public static void init() {


        // Sr + BaO + 2CH3COOH -> [C4H6BaSrO4 + H2O]
        MIXER_RECIPES.recipeBuilder().duration(420).EUt(120)
                .input(dust, Strontium)
                .input(dust, BariumOxide, 2)
                .fluidInputs(AceticAcid.getFluid(2000))
                .fluidOutputs(BariumStrontiumAcetateSolution.getFluid(1000))
                .buildAndRegister();

        // Ti + 2O -> TiO2
        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(1920)
                .input(dust, Titanium)
                .fluidInputs(Oxygen.getFluid(2000))
                .output(dust, Rutile, 3)
                .buildAndRegister();

        // TiO2 + 4C3H8O -> C12H28O4Ti + 2H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(480)
                .input(dust, Rutile, 3)
                .fluidInputs(IsopropylAlcohol.getFluid(4000))
                .fluidOutputs(TitaniumIsopropoxide.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();

        // [C4H6BaSrO4 + H2O] + C12H28O4Ti + 2C -> [C4H6BaSrO4 + C12H28O4Ti + H2O + 2C]
        MIXER_RECIPES.recipeBuilder().duration(280).EUt(120)
                .fluidInputs(BariumStrontiumAcetateSolution.getFluid(1000))
                .fluidInputs(TitaniumIsopropoxide.getFluid(1000))
                .input(dust, Carbon, 2)
                .fluidOutputs(BariumStrontiumTitanatePreparation.getFluid(2000))
                .buildAndRegister();

        // [C4H6BaSrO4 + C12H28O4Ti + H2O + 2C] -> BaO4SrTi + C5H10O2 + 4C3H6 + H2O + CO2
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(250).EUt(480)
                .fluidInputs(BariumStrontiumTitanatePreparation.getFluid(2000))
                .output(dust, BariumStrontiumTitanate, 7)
                .fluidOutputs(IsopropylAcetate.getFluid(1000))
                .fluidOutputs(Propene.getFluid(4000))
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .buildAndRegister();

        // C5H10O2 + H2O -> C3H8O + CH3COOH
        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(480)
                .notConsumable(dust, PhosphorusPentoxide)
                .circuitMeta(1)
                .fluidInputs(IsopropylAcetate.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(IsopropylAlcohol.getFluid(1000))
                .fluidOutputs(AceticAcid.getFluid(1000))
                .buildAndRegister();

        // 9H2O + 2F7K2Ta -> Ta2O5 + 14HF + 4KOH
        CHEMICAL_RECIPES.recipeBuilder().duration(130).EUt(120)
                .input(dust, PotasssiumFluoroTantalate, 20)
                .fluidInputs(Water.getFluid(9000))
                .output(dust, TantalumOxide, 7)
                .fluidOutputs(HydrofluoricAcid.getFluid(14000))
                .fluidOutputs(PotassiumHydroxide.getFluid(4000))
                .buildAndRegister();

        // 2Ta + 10Cl + 5H2O -> Ta2O5 + 10HCl
        CHEMICAL_RECIPES.recipeBuilder().duration(480).EUt(120)
                .input(dust, Tantalum, 2)
                .fluidInputs(Chlorine.getFluid(10000))
                .fluidInputs(Water.getFluid(5000))
                .output(dust, TantalumOxide, 7)
                .fluidOutputs(HydrochloricAcid.getFluid(10000))
                .buildAndRegister();

        // 2Sc + 6Cl + 3H2O -> Sc2O3 + 6HCl
        CHEMICAL_RECIPES.recipeBuilder().duration(480).EUt(120)
                .input(dust, Scandium, 2)
                .fluidInputs(Chlorine.getFluid(6000))
                .fluidInputs(Water.getFluid(3000))
                .output(dust, ScandiumOxide, 5)
                .fluidOutputs(HydrochloricAcid.getFluid(6000))
                .buildAndRegister();

        // 2Lu + 6Cl + 3H2O -> Lu2O3 + 6HCl
        CHEMICAL_RECIPES.recipeBuilder().duration(480).EUt(120)
                .input(dust, Lutetium, 2)
                .fluidInputs(Chlorine.getFluid(6000))
                .fluidInputs(Water.getFluid(3000))
                .output(dust, LutetiumOxide, 5)
                .fluidOutputs(HydrochloricAcid.getFluid(6000))
                .buildAndRegister();

        // 2Tm + 6Cl + 3H2O -> Tm2O3 + 6HCl
        CHEMICAL_RECIPES.recipeBuilder().duration(480).EUt(120)
                .input(dust, Thulium, 2)
                .fluidInputs(Chlorine.getFluid(6000))
                .fluidInputs(Water.getFluid(3000))
                .output(dust, ThuliumOxide, 5)
                .fluidOutputs(HydrochloricAcid.getFluid(6000))
                .buildAndRegister();

        // 2Eu + 6Cl + 3H2O -> Eu2O3 + 6HCl
        CHEMICAL_RECIPES.recipeBuilder().duration(480).EUt(120)
                .input(dust, Europium, 2)
                .fluidInputs(Chlorine.getFluid(6000))
                .fluidInputs(Water.getFluid(3000))
                .output(dust, EuropiumOxide, 5)
                .fluidOutputs(HydrochloricAcid.getFluid(6000))
                .buildAndRegister();

        // Pb(NO3)2 + 9Ca -> [Pb(NO3)2 + 9Ca]
        MIXER_RECIPES.recipeBuilder().duration(200).EUt(30)
                .input(dust, LeadNitrate, 9)
                .input(dust, Calcium, 9)
                .output(dust, LeadNitrateCalciumMixture, 18)
                .buildAndRegister();

        // [2Pb(NO3)2 + 9Ca] + Ta2O5 + Sc2O3 + 3O -> 2Pb(ScTa)O3 + 9CaO + 4NO2
        BLAST_RECIPES.recipeBuilder().duration(350).EUt(120).blastFurnaceTemp(1350)
                .input(dust, LeadNitrateCalciumMixture, 18)
                .input(dust, TantalumOxide, 7)
                .input(dust, ScandiumOxide, 5)
                .fluidInputs(Oxygen.getFluid(3000))
                .output(dust, LeadScandiumTantalate, 12)
                .output(dust, Quicklime, 18)
                .fluidOutputs(NitrogenDioxide.getFluid(4000))
                .buildAndRegister();

        // 4Tb + 7Ds + 10Fe + 5Co + 2B + Si + C -> [4Tb + 7Ds + 10Fe + 5Co + 2B + Si + C]
        ADVANCED_MIXER_RECIPES.recipeBuilder().duration(940).EUt(30)
                .input(dust, Terbium, 4)
                .input(dust, Dysprosium, 7)
                .input(dust, Iron, 10)
                .input(dust, Cobalt, 5)
                .input(dust, Boron, 2)
                .input(dust, Silicon)
                .input(dust, Carbon)
                .output(dust, MagnetorestrictiveAlloy, 30)
                .buildAndRegister();

        // Pb + Se -> PbSe
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(120)
                .input(dust, Lead)
                .input(dust, Selenium)
                .output(dust, LeadSenenide, 2)
                .buildAndRegister();

        // Zn + Se -> ZnSe
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(120)
                .input(dust, Zinc)
                .input(dust, Selenium)
                .output(dust, ZincSelenide, 2)
                .buildAndRegister();

        // Fr + Cs + 2Cd + 6Br -> FrCsCd2Br6
        BLAST_RECIPES.recipeBuilder().duration(350).EUt(120).blastFurnaceTemp(2200)
                .input(dust, Francium)
                .input(dust, Caesium)
                .input(dust, Cadmium, 2)
                .fluidInputs(Bromine.getFluid(6000))
                .output(dust, FranciumCaesiumCadmiumBromide, 10)
                .buildAndRegister();

        // C6H5NH2 + NaNO2 + 2HCl + NaI -> 2NaCl + 2N + 2H2O + C6H5I
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(220).EUt(480)
                .fluidInputs(Aniline.getFluid(1000))
                .input(dust, SodiumNitrite, 4)
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .input(dust, SodiumIodide, 2)
                .output(dust, Salt, 4)
                .fluidOutputs(Nitrogen.getFluid(2000))
                .fluidOutputs(Water.getFluid(2000))
                .fluidOutputs(Iodobenzene.getFluid(1000))
                .buildAndRegister();

        // C6H5I + C8H8 + Cl -> HCl + I + C14H12
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(350).EUt(18000)
                .notConsumable(dust, PalladiumAcetate, 1)
                .fluidInputs(Iodobenzene.getFluid(1000))
                .fluidInputs(Styrene.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(1000))
                .notConsumable(Tributylamine.getFluid(1))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .output(dust, Iodine)
                .output(dust, Stilbene, 26)
                .buildAndRegister();

        // C6H6O2 + NH3 -> C6H7NO + H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(220).EUt(480)
                .fluidInputs(Resorcinol.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidOutputs(Amino3phenol.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        // C2H4 + NH3 -> C2H5NH2
        CHEMICAL_RECIPES.recipeBuilder().duration(130).EUt(480)
                .fluidInputs(Ethylene.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidOutputs(Ethylamine.getFluid(2000))
                .notConsumable(dust, SodiumAzanide, 1)
                .buildAndRegister();

        // 3C2H5NH2 + C6H7NO + 2C8H4O3 + HCl -> 2NH3 + C28H31ClN2O3 + 4O
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(490000)
                .fluidInputs(Ethylamine.getFluid(3000))
                .fluidInputs(Amino3phenol.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .input(dust, PhthalicAnhydride, 30)
                .notConsumable(dust, TetraethylammoniumNonahydridides, 1)
                .fluidOutputs(Ammonia.getFluid(2000))
                .fluidOutputs(Oxygen.getFluid(4000))
                .output(dust, RhodamineB, 65)
                .buildAndRegister();

        // Re + 7F + 4H2O + NH3 -> 7HF + NH4ReO4
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(300).EUt(7680)
                .input(dust, Rhenium)
                .fluidInputs(Fluorine.getFluid(7000))
                .fluidInputs(Water.getFluid(4000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidOutputs(HydrofluoricAcid.getFluid(7000))
                .fluidOutputs(AmmoniumPerrhenate.getFluid(1000))
                .buildAndRegister();

        // Tc + NaOH + 2HNO3 -> NO2 + H3NO + NaTcO4
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(300).EUt(480)
                .input(dust, Technetium)
                .input(dust, SodiumHydroxide, 3)
                .fluidInputs(NitricAcid.getFluid(2000))
                .fluidOutputs(NitrogenDioxide.getFluid(1000))
                .fluidOutputs(Hydroxylamine.getFluid(1000))
                .output(dust, SodiumPertechnetate, 6)
                .buildAndRegister();

        // KOH + NH4ReO4 -> KReO4 + NH3 + H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(480)
                .fluidInputs(PotassiumHydroxide.getFluid(1000))
                .fluidInputs(AmmoniumPerrhenate.getFluid(1000))
                .output(dust, PotassiumPerrhenate, 6)
                .fluidOutputs(Ammonia.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        // K + NaTcO4 -> KTcO4 + Na
        BLAST_RECIPES.recipeBuilder().duration(280).EUt(120).blastFurnaceTemp(6500)
                .input(dust, Potassium)
                .input(dust, SodiumPertechnetate, 6)
                .output(dust, PotassiumPertechnate, 6)
                .output(dust, Sodium)
                .buildAndRegister();

        // KReO4 + 18K + 13C2H5OH + 4O -> 4KOH + H9K2ReO4 + 13C2H5KO
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(1920)
                .input(dust, PotassiumPerrhenate, 6)
                .input(dust, Potassium, 18)
                .fluidInputs(Ethanol.getFluid(13000))
                .fluidInputs(Oxygen.getFluid(4000))
                .fluidOutputs(PotassiumHydroxide.getFluid(4000))
                .output(dust, PotassiumNonahydridorhenate, 16)
                .fluidOutputs(PotassiumEthoxide.getFluid(13000))
                .buildAndRegister();

        // KTcO4 + 18K + 13C2H5OH + 4O -> 4KOH + H9K2TcO4 + 13C2H5KO
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(1920)
                .input(dust, PotassiumPertechnate, 6)
                .input(dust, Potassium, 18)
                .fluidInputs(Ethanol.getFluid(13000))
                .fluidInputs(Oxygen.getFluid(4000))
                .fluidOutputs(PotassiumHydroxide.getFluid(4000))
                .output(dust, PotassiumNonahydridotechnetate, 16)
                .fluidOutputs(PotassiumEthoxide.getFluid(13000))
                .buildAndRegister();

        // C2H5KO + HCl -> C2H5OH + KCl
        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(480)
                .fluidInputs(PotassiumEthoxide.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(Ethanol.getFluid(1000))
                .output(dust, RockSalt, 2)
                .buildAndRegister();

        // H9K2ReO4 + H9K2TcO4 + 2C8H20NBr + 2Br -> 4KBr + [C8H20NH9ReO4 + C8H20NH9TcO4]
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(360).EUt(15310)
                .input(dust, PotassiumNonahydridorhenate, 16)
                .input(dust, PotassiumNonahydridotechnetate, 16)
                .fluidInputs(TetraethylammoniumBromide.getFluid(2000))
                .fluidInputs(Bromine.getFluid(2000))
                .output(dust, PotassiumBromide, 8)
                .output(dust, TetraethylammoniumNonahydridides, 86)
                .buildAndRegister();

        // KI + C6H7NO + C4H9Li -> C10H15N + KOH + LiI
        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(480)
                .input(dust, PotassiumIodide, 2)
                .fluidInputs(Aminophenol.getFluid(1000))
                .fluidInputs(ButylLithium.getFluid(1000))
                .fluidOutputs(Butylaniline.getFluid(1000))
                .fluidOutputs(PotassiumHydroxide.getFluid(1000))
                .output(dust, LithiumIodide, 2)
                .buildAndRegister();

        // NaH + C3H9SiCl -> C3H10Si + NaCl
        CHEMICAL_RECIPES.recipeBuilder().duration(170).EUt(1920)
                .input(dust, SodiumHydride, 2)
                .fluidInputs(Trimethylchlorosilane.getFluid(1000))
                .fluidOutputs(Trimethylsilane.getFluid(1000))
                .output(dust, Salt, 2)
                .buildAndRegister();

        // K + Br -> KBr
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(120)
                .input(dust, Potassium)
                .fluidInputs(Bromine.getFluid(1000))
                .output(dust, PotassiumBromide, 2)
                .buildAndRegister();

        // KBr + O3 -> KBrO3
        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(480)
                .input(dust, PotassiumBromide, 2)
                .fluidInputs(Ozone.getFluid(1000))
                .output(dust, PotassiumBromate, 5)
                .buildAndRegister();

        // C8H4O3 + NaI + NaNO2 + [NaOH + H2O] + NH3 + HCl + 2Cl -> C7H5IO2 + N2 + CO2 + 3[H2O + NaCl]
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(350).EUt(122880)
                .input(dust, PhthalicAnhydride, 15)
                .input(dust, SodiumIodide, 2)
                .input(dust, SodiumNitrite, 4)
                .fluidInputs(SodiumHydroxideSolution.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(2000))
                .fluidOutputs(IodobenzoicAcid.getFluid(1000))
                .fluidOutputs(Nitrogen.getFluid(1000))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .fluidOutputs(SaltWater.getFluid(3000))
                .buildAndRegister();

        // 3C7H5IO2 + H2SO4 + 2O -> H2S + 3C7H5IO4
        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(1920)
                .notConsumable(dust, PotassiumBromate, 1)
                .fluidInputs(IodobenzoicAcid.getFluid(3000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(2000))
                .fluidOutputs(HydrogenSulfide.getFluid(1000))
                .output(dust, IBX, 51)
                .buildAndRegister();

        // LiI -> Li + I
        ELECTROLYZER_RECIPES.recipeBuilder().duration(224).EUt(30)
                .input(dust, LithiumIodide, 2)
                .output(dust, Lithium)
                .output(dust, Iodine)
                .buildAndRegister();

        // KBr -> K + Br
        ELECTROLYZER_RECIPES.recipeBuilder().duration(224).EUt(30)
                .input(dust, PotassiumBromide, 2)
                .output(dust, Potassium)
                .fluidOutputs(Bromine.getFluid(1000))
                .buildAndRegister();

        // C3H10Si + C6H5Cl + 2CH2O + C7H5IO4 -> 2H + (CH3)3SiCl + C8H8O2 + C7H5IO4
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(250).EUt(7680)
                .fluidInputs(Trimethylsilane.getFluid(1000))
                .fluidInputs(Chlorobenzene.getFluid(1000))
                .fluidInputs(Formaldehyde.getFluid(2000))
                .input(dust, IBX, 17)
                .notConsumable(dust, CobaltOxide)
                .fluidOutputs(Trimethylchlorosilane.getFluid(1000))
                .fluidOutputs(Methoxybenzaldehyde.getFluid(1000))
                .fluidOutputs(IodobenzoicAcid.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(2000))
                .buildAndRegister();

        // C8H8O2 + C10H15N -> C18H21NO + H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(190).EUt(122880)
                .fluidInputs(Methoxybenzaldehyde.getFluid(1000))
                .fluidInputs(Butylaniline.getFluid(1000))
                .notConsumable(dust, TetraethylammoniumNonahydridides, 1)
                .fluidOutputs(MBBA.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(320).EUt(480)
                .input(dust, CarbonNanotubes)
                .fluidInputs(MBBA.getFluid(1000))
                .fluidOutputs(LiquidCrystalDetector.getFluid(1000))
                .buildAndRegister();

        // PdCl2 + 2CH3COOH -> C4H6O4Pd + 2HCl
        CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(480)
                .input(dust, PalladiumChloride, 3)
                .fluidInputs(AceticAcid.getFluid(2000))
                .output(dust, PalladiumAcetate, 15)
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .buildAndRegister();

        // I + Cl -> ICl
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(120)
                .input(dust, Iodine)
                .fluidInputs(Chlorine.getFluid(1000))
                .fluidOutputs(IodineMonochloride.getFluid(1000))
                .buildAndRegister();

        // Re + Rh + Nq -> ReRhNq
        MIXER_RECIPES.recipeBuilder().duration(260).EUt(84500)
                .input(dust, Rhenium)
                .input(dust, Rhodium)
                .input(dust, Naquadah)
                .output(dust, RhReNqCatalyst, 3)
                .buildAndRegister();

        // Mg + 2Cl -> MgCl2
        CHEMICAL_RECIPES.recipeBuilder().duration(50).EUt(120)
                .input(dust, Magnesium)
                .fluidInputs(Chlorine.getFluid(2000))
                .output(dust, MagnesiumChloride, 3)
                .buildAndRegister();

        // 2MgCl2 + 3C2H2 + (CH3)3SiCl + 2Br -> 3HCl + C9H12Si(MgBr)2 + 2Cl
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(350).EUt(480)
                .input(dust, MagnesiumChloride, 6)
                .fluidInputs(Acetylene.getFluid(3000))
                .fluidInputs(Trimethylchlorosilane.getFluid(1000))
                .fluidInputs(Bromine.getFluid(2000))
                .fluidOutputs(HydrochloricAcid.getFluid(3000))
                .fluidOutputs(AcetylatingReagent.getFluid(1000))
                .fluidOutputs(Chlorine.getFluid(2000))
                .buildAndRegister();

        // C10H8 + 2CH4O -> 2H2O + C12H12
        CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(120)
                .fluidInputs(Naphthalene.getFluid(1000))
                .fluidInputs(Methanol.getFluid(2000))
                .fluidOutputs(Water.getFluid(2000))
                .fluidOutputs(Dimethylnaphthalene.getFluid(1000))
                .buildAndRegister();

        // C12H12 + C9H12Si(MgBr)2 + ICl + 2 Cl + C4H4BrNO2 -> 2MgClBr + H2C18H11I + (CH3)3SiCl  + HBr + C4H5NO2
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(290).EUt(122880)
                .fluidInputs(Dimethylnaphthalene.getFluid(1000))
                .fluidInputs(AcetylatingReagent.getFluid(1000))
                .fluidInputs(IodineMonochloride.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(2000))
                .input(dust, Bromosuccinimide, 12)
                .notConsumable(dust, RhReNqCatalyst, 1)
                .output(dust, MgClBromide, 6)
                .output(dust, Succinimide, 12)
                .fluidOutputs(Dihydroiodotetracene.getFluid(1000))
                .fluidOutputs(Trimethylchlorosilane.getFluid(1000))
                .fluidOutputs(HydrobromicAcid.getFluid(1000))
                .buildAndRegister();

        // C6H5OH + 10Cl + 2HCN + O -> 8HCl + C8Cl2N2O2
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(250).EUt(30720)
                .fluidInputs(Phenol.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(10000))
                .fluidInputs(HydrogenCyanide.getFluid(2000))
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(8000))
                .fluidOutputs(Dichlorodicyanobenzoquinone.getFluid(1000))
                .buildAndRegister();

        // C8N2Cl2(OH)2 + H2O2 -> C8Cl2N2O2 + 2H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(250).EUt(480)
                .fluidInputs(Dichlorodicyanohydroquinone.getFluid(1000))
                .fluidInputs(HydrogenPeroxide.getFluid(1000))
                .fluidOutputs(Dichlorodicyanobenzoquinone.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .notConsumable(dust, VanadiumOxide, 1)
                .buildAndRegister();

        // C4H6O4Pd + Li2TiO3 + 2HCl + H2O -> C4H6O4 + 2[H2O + LiCl] + PdTiO2
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(350).EUt(491520)
                .input(dust, PalladiumAcetate, 15)
                .input(wireFine, LithiumTitanate, 24)
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .fluidInputs(Water.getFluid(1000))
                .notConsumable(UVA_HALIDE_LAMP.getStackForm())
                .fluidOutputs(LithiumChlorideSolution.getFluid(2000))
                .output(dust, SuccinicAcid, 14)
                .output(dust, PalladiumLoadedRutileNanoparticles, 5)
                .buildAndRegister();

        // 2H2C18H11I + 2C8Cl2N2O2 + C3H8O -> C3H6O + 2C8N2Cl2(OH)2 + 2I + 2C18H12
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(491520)
                .fluidInputs(Dihydroiodotetracene.getFluid(2000))
                .fluidInputs(Dichlorodicyanobenzoquinone.getFluid(2000))
                .fluidInputs(IsopropylAlcohol.getFluid(1000))
                .fluidOutputs(Acetone.getFluid(1000))
                .fluidOutputs(Dichlorodicyanohydroquinone.getFluid(2000))
                .output(dust, Iodine, 2)
                .output(dust, Tetracene, 60)
                .notConsumable(UVA_HALIDE_LAMP.getStackForm())
                .notConsumable(dust, PalladiumLoadedRutileNanoparticles, 1)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(491520)
                .inputs(LEPTON_TRAP_CRYSTAL.getStackForm())
                .input(dustSmall, Vibranium, 2)
                .fluidInputs(HeavyLeptonMix.getFluid(500))
                .fluidInputs(FreeElectronGas.getFluid(500))
                .outputs(CHARGED_LEPTON_TRAP_CRYSTAL.getStackForm())
                .buildAndRegister();


    }
}
