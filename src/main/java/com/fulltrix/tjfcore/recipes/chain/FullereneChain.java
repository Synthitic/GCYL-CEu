package com.fulltrix.tjfcore.recipes.chain;

import gregtech.api.recipes.ingredients.IntCircuitIngredient;

import static com.fulltrix.tjfcore.TJFMaterials.*;
import static com.fulltrix.tjfcore.item.TJFCoreItems.UVA_HALIDE_LAMP;
import static com.fulltrix.tjfcore.recipes.TJFRecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.MarkerMaterials.Color.Magenta;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.craftingLens;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class FullereneChain {
    public static void init() {

        // NaCN + 2 Br + C10H8 -> NaBr + C10H7CN + HBr
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Naphthalene.getFluid(1000))
                .fluidInputs(SodiumCyanide.getFluid(1000))
                .fluidInputs(Bromine.getFluid(2000))
                .output(dust, SodiumBromide, 2)
                .notConsumable(UVA_HALIDE_LAMP)
                .fluidOutputs(HydrobromicAcid.getFluid(1000))
                .output(dust, Cyanonaphthalene, 19)
                .EUt(1920)
                .duration(80)
                .buildAndRegister();

        //HBr + Cl -> HCl + Br
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(HydrobromicAcid.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(1000))
                .fluidOutputs(Bromine.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .duration(160)
                .EUt(125)
                .buildAndRegister();

        //2 NaBr + H3PO4 -> 2 HBr + Na2HPO4
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, SodiumBromide, 4)
                .fluidInputs(PhosphoricAcid.getFluid(1000))
                .fluidOutputs(HydrobromicAcid.getFluid(2000))
                .output(dust, DisodiumPhosphate, 8)
                .duration(190)
                .EUt(125)
                .buildAndRegister();

        // C10H7CN + H2O + 3HCl -> C11H8O + NH4Cl + 2Cl
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Cyanonaphthalene, 19)
                .notConsumable(dust, TinChloride, 1)
                .fluidInputs(Water.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(3000))
                .fluidOutputs(Naphthaldehyde.getFluid(1000))
                .fluidOutputs(AmmoniumChloride.getFluid(1000))
                .fluidOutputs(Chlorine.getFluid(2000))
                .EUt(1920)
                .duration(80)
                .buildAndRegister();

        // NH4Cl -> HCl + NH3
        ELECTROLYZER_RECIPES.recipeBuilder()
                .fluidInputs(AmmoniumChloride.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(Ammonia.getFluid(1000))
                .duration(200)
                .EUt(120)
                .buildAndRegister();

        // C11H8O + C8H10 + O -> C19H14 + 2H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Naphthaldehyde.getFluid(1000))
                .fluidInputs(Ethylbenzene.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(1000))
                .notConsumable(dust, Iodine)
                .notConsumable(dust, Triphenylphosphine, 1)
                .fluidOutputs(Water.getFluid(2000))
                .output(dust, Methylbenzophenanthrene, 33)
                .EUt(1920)
                .duration(100)
                .buildAndRegister();

        // C19H14 + KCN + C4H4BrNO2 -> C20H13N + KBr + C4H5NO2
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Methylbenzophenanthrene, 33)
                .input(dust, PotassiumCyanide, 3)
                .input(dust, Bromosuccinimide, 12)
                .output(dust, Benzophenanthrenylacetonitrile, 34)
                .output(dust, PotassiumBromide, 2)
                .output(dust, Succinimide, 12)
                .EUt(1920)
                .duration(100)
                .buildAndRegister();

        // 3C20H13N -> C60H30 + 3NH3
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Benzophenanthrenylacetonitrile, 102)
                .notConsumable(dust, TiAlChloride, 1)
                .output(dust, UnfoldedFullerene, 1)
                .fluidOutputs(Ammonia.getFluid(3000))
                .EUt(7680)
                .duration(250)
                .buildAndRegister();

        //TODO: advanced laser engraver?

        // C60H30 + 10N -> C60 + 10NH3
        ADVANCED_ENGRAVER_RECIPES.recipeBuilder()
                .notConsumable(craftingLens, Magenta)
                .input(dust, UnfoldedFullerene, 1)
                .fluidInputs(Nitrogen.getFluid(10000))
                .output(dust, Fullerene, 1)
                .fluidOutputs(Ammonia.getFluid(10000))
                .EUt(2000000)
                .duration(200)
                .buildAndRegister();

        // 6Na + PCl3 + 3C6H5Cl -> 6NaCl + C18H15P
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Sodium, 6)
                .fluidInputs(PhosphorusTrichloride.getFluid(1000))
                .fluidInputs(Chlorobenzene.getFluid(3000))
                .output(dust, Salt, 12)
                .output(dust, Triphenylphosphine, 34)
                .EUt(7680)
                .duration(250)
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder()
                .input(dust, EschericiaColi, 1)
                .input(dust, Sugar)
                .EUt(480)
                .output(dust, SuccinicAcid, 14)
                .duration(200)
                .buildAndRegister();

        // C4H6O4 + NH3 -> C4H5NO2 + 2H2O
        BLAST_RECIPES.recipeBuilder()
                .input(dust, SuccinicAcid, 14)
                .fluidInputs(Ammonia.getFluid(1000))
                .output(dust, Succinimide, 12)
                .fluidOutputs(Water.getFluid(2000))
                .EUt(120)
                .duration(500)
                .blastFurnaceTemp(2100)
                .buildAndRegister();

        // C4H5NO2 + 2Br -> C4H4BrNO2 + HBr
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Succinimide, 12)
                .fluidInputs(Bromine.getFluid(2000))
                .output(dust, Bromosuccinimide, 12)
                .fluidOutputs(HydrobromicAcid.getFluid(1000))
                .EUt(480)
                .duration(200)
                .buildAndRegister();

        // C4H4BrNO2 -> HBr + NO2 + 4C + 3H
        ELECTROLYZER_RECIPES.recipeBuilder()
                .input(dust, Bromosuccinimide, 12)
                .output(dust, Carbon, 4)
                .fluidOutputs(Hydrogen.getFluid(3000))
                .fluidOutputs(HydrobromicAcid.getFluid(1000))
                .fluidOutputs(NitrogenDioxide.getFluid(1000))
                .duration(158)
                .EUt(120)
                .buildAndRegister();


        // SO3 + S + 2Cl -> SO2 + SOCl2
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(SulfurTrioxide.getFluid(1000))
                .input(dust, Sulfur)
                .fluidInputs(Chlorine.getFluid(2000))
                .fluidOutputs(SulfurDioxide.getFluid(1000))
                .fluidOutputs(ThionylChloride.getFluid(1000))
                .EUt(480)
                .duration(400)
                .buildAndRegister();

        // TiCl4 + AlCl3 -> TiAlCl7
        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(TitaniumTetrachloride.getFluid(1000))
                .input(dust, AluminiumChloride, 4)
                .EUt(7680)
                .duration(50)
                .output(dust, TiAlChloride, 9)
                .buildAndRegister();

        // HCN + KOH -> KCN + H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(HydrogenCyanide.getFluid(1000))
                .fluidInputs(PotassiumHydroxide.getFluid(1000))
                .notConsumable(new IntCircuitIngredient(0))
                .output(dust, PotassiumCyanide, 3)
                .fluidOutputs(Water.getFluid(1000))
                .EUt(1920)
                .duration(200)
                .buildAndRegister();

        // KCN -> K + C + N
        ELECTROLYZER_RECIPES.recipeBuilder()
                .input(dust, PotassiumCyanide, 3)
                .output(dust, Potassium)
                .output(dust, Carbon)
                .fluidOutputs(Nitrogen.getFluid(1000))
                .duration(150)
                .EUt(120)
                .buildAndRegister();

        // KCl + H2O -> KOH + HCl
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, RockSalt, 2)
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(PotassiumHydroxide.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .EUt(480)
                .duration(270)
                .buildAndRegister();

        // K + Cl -> KCl
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Potassium)
                .fluidInputs(Chlorine.getFluid(1000))
                .output(dust, RockSalt, 2)
                .EUt(30)
                .duration(220)
                .buildAndRegister();

        // H + FeCl3 -> FeCl2 + HCl
        CHEMICAL_RECIPES.recipeBuilder().duration(40).EUt(30)
                .fluidInputs(Hydrogen.getFluid(1000))
                .fluidInputs(Iron3Chloride.getFluid(1000))
                .fluidOutputs(Iron2Chloride.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .buildAndRegister();

        // 2C5H6 + FeCl2 -> C10H10Fe + 2HCl
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, ZeoliteSievingPellets, 1)
                .notConsumable(dust, PdIrReOCeOS, 1)
                .fluidInputs(Iron2Chloride.getFluid(1000))
                .fluidInputs(Cyclopentadiene.getFluid(2000))
                .fluidOutputs(Ferrocene.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .output(dust, WetZeoliteSievingPellets, 1)
                .EUt(30720)
                .duration(100)
                .buildAndRegister();

        LARGE_MIXER_RECIPES.recipeBuilder()
                .input(dust, Palladium)
                .input(dust, Iridium)
                .input(dust, Rhenium)
                .input(dust, Cerium)
                .input(dust, Osmium)
                .input(dust, Silicon)
                .fluidInputs(Oxygen.getFluid(4000))
                .output(dust, PdIrReOCeOS, 10)
                .EUt(7680)
                .duration(50)
                .buildAndRegister();

        //TODO: mixin lcr recipe map instead of using chemical plant

        // C10H10Fe + C60 + C3H7NO2 + CHCl3 + 3 NaOC2H5 ->
        // 3 NaCl + 3 C2H5OH + [C73H15NFe] + CO2
        CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .input(dust, Fullerene, 1)
                .input(dust, Sarcosine, 13)
                .input(dust, SodiumEthoxide, 27)
                .fluidInputs(Chloroform.getFluid(1000))
                .fluidInputs(Ferrocene.getFluid(1000))
                .notConsumable(dust, TitaniumTetrachloride)
                .notConsumable(dust, Toluene)
                .output(dust, Salt, 6)
                .fluidOutputs(Ferrocenylfulleropyrrolidine.getFluid(1000))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .fluidOutputs(Ethanol.getFluid(3000))
                .EUt(500000)
                .duration(750)
                .buildAndRegister();

        // NaOH + C2H5OH -> H2O + C2H5ONa
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, SodiumHydroxide, 3)
                .fluidInputs(Ethanol.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .output(dust, SodiumEthoxide, 9)
                .EUt(7680)
                .duration(50)
                .buildAndRegister();

        // CH3NH2 + 2Cl + CH3COOH -> C3H7NO2 + 2HCl
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Methylamine.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(2000))
                .fluidInputs(AceticAcid.getFluid(1000))
                .notConsumable(AceticAnhydride.getFluid(0))
                .output(dust, Sarcosine, 13)
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        // NH3 + CH4O -> CH3NH2 + H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidInputs(Methanol.getFluid(1000))
                .notConsumable(dust, AluminoSilicateWool, 1)
                .notConsumable(new IntCircuitIngredient(3))
                .fluidOutputs(Methylamine.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(7680)
                .duration(100)
                .buildAndRegister();

        // C73H15NFe + Pd -> PdC73H15NFe
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Ferrocenylfulleropyrrolidine.getFluid(1000))
                .input(dust, Palladium)
                .notConsumable(AceticAcid.getFluid())
                .notConsumable(NitricAcid.getFluid())
                .output(dust, PdFullereneMatrix, 1)
                .EUt(2000000)
                .duration(200)
                .buildAndRegister();

        // H2S + 2CH4O -> C2H6S + 2H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(HydrogenSulfide.getFluid(1000))
                .fluidInputs(Methanol.getFluid(2000))
                .fluidOutputs(Dimethylsulfide.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .EUt(1920)
                .duration(100)
                .buildAndRegister();

        // 2C7H8 + SOCl2 + 4KMnO4 -> H2O + 4MnO2 + 4KOH + SO2 + 2C7H5ClO
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, PotassiumPermanganate, 24)
                .fluidInputs(ThionylChloride.getFluid(1000))
                .fluidInputs(Toluene.getFluid(2000))
                .output(dust, Pyrolusite, 12)
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(PotassiumHydroxide.getFluid(4000))
                .fluidOutputs(SulfurDioxide.getFluid(1000))
                .fluidOutputs(BenzoylChloride.getFluid(2000))
                .EUt(480)
                .duration(200)
                .buildAndRegister();

        // H2O2 + 2C7H5ClO -> C14H10O4 + 2HCl
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(HydrogenPeroxide.getFluid(1000))
                .fluidInputs(BenzoylChloride.getFluid(2000))
                .fluidOutputs(BenzoylPeroxide.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .EUt(1920)
                .duration(160)
                .buildAndRegister();

        //4 I + N2H4 -> 2 N + 4 HI
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Iodine, 4)
                .fluidInputs(Hydrazine.getFluid(1000))
                .fluidOutputs(Nitrogen.getFluid(2000))
                .fluidOutputs(HydroiodicAcid.getFluid(4000))
                .duration(210)
                .EUt(500)
                .buildAndRegister();

        //C3H3N + HI + 2 H2O + C8H8 + LiAlH4 -> C11H14O2 + LiI + AlH3 + NH3 (SnMe3Cl cat.)
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(AcryloNitrile.getFluid(1000))
                .fluidInputs(HydroiodicAcid.getFluid(1000))
                .fluidInputs(Water.getFluid(2000))
                .fluidInputs(Styrene.getFluid(1000))
                .input(dust, LithiumAluminiumHydride, 6)
                .notConsumable(TrimethyltinChloride.getFluid(100))
                .fluidOutputs(Phenylpentanoicacid.getFluid(1000))
                .fluidOutputs(Ammonia.getFluid(1000))
                .output(dust, AluminiumHydride, 4)
                .output(dust, LithiumIodide, 2)
                .EUt(3840)
                .duration(110)
                .buildAndRegister();

        // 2 C60 + 2 C11H14O2 + C2H6S + 2 C6H5Cl -> 2 C71H12O2 + 2 C7H8 + H2S + 2 HCl
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Fullerene, 2)
                .fluidInputs(Phenylpentanoicacid.getFluid(2000))
                .fluidInputs(Dimethylsulfide.getFluid(1000))
                .fluidInputs(Chlorobenzene.getFluid(2000))
                .notConsumable(BenzoylPeroxide.getFluid(0))
                .fluidOutputs(Toluene.getFluid(2000))
                .fluidOutputs(HydrogenSulfide.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .fluidOutputs(PCBA.getFluid(2000))
                .EUt(500000)
                .duration(450)
                .buildAndRegister();

        // Ag + Cl -> AgCl
        CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(120)
                .input(dust, Silver)
                .fluidInputs(Chlorine.getFluid(1000))
                .output(dust, SilverChloride, 2)
                .buildAndRegister();

        // 3Ag2O + 8BF3 -> 6AgBF4 + B2O3
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(BoronFluoride.getFluid(8000))
                .input(dust, SilverOxide, 9)
                .notConsumable(Benzene.getFluid())
                .fluidOutputs(Silvertetrafluoroborate.getFluid(6000))
                .output(dust, BoronOxide, 5)
                .EUt(7680)
                .duration(100)
                .buildAndRegister();

        // 2NH3 + COCl2 + 2C3H8O -> C7H14N2 + 3H2O + 2HCl
        // loses 1B water, but keeps it out of an LCR. Good tradeoff I think
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Ammonia.getFluid(2000))
                .fluidInputs(Phosgene.getFluid(1000))
                .fluidInputs(IsopropylAlcohol.getFluid(2000))
                .notConsumable(dust, Triphenylphosphine, 1)
                .fluidOutputs(DilutedHydrochloricAcid.getFluid(4000))
                .fluidOutputs(Diisopropylcarbodiimide.getFluid(1000))
                .EUt(7680)
                .duration(250)
                .buildAndRegister();

        // CO + 2Cl -> COCl2
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(CarbonMonoxide.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(2000))
                .notConsumable(dust, Carbon)
                .fluidOutputs(Phosgene.getFluid(1000))
                .EUt(1920)
                .duration(400)
                .buildAndRegister();

        // (CH3)2NH + C5H5N -> H2 + C7H10N2
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Dimethylamine.getFluid(1000))
                .fluidInputs(Pyridine.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(2000))
                .output(dust, Dimethylaminopyridine, 19)
                .EUt(7680)
                .duration(200)
                .buildAndRegister();

        // C71H12O2 + C8H8 + CH2Cl2 -> C80H20O2 + 2 HCl
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(dust, Dimethylaminopyridine, 1)
                .notConsumable(Diisopropylcarbodiimide.getFluid(10))
                .fluidInputs(PCBA.getFluid(1000))
                .fluidInputs(Styrene.getFluid(1000))
                .fluidInputs(Dichloromethane.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .fluidOutputs(PCBS.getFluid(1000))
                .EUt(500000)
                .duration(400)
                .buildAndRegister();

        // PdC73H15NFe + C80H21O2 -> [PdC73H15NFe + C80H21O2]
        CHEMICAL_BATH_RECIPES.recipeBuilder()
                .fluidInputs(PCBS.getFluid(1000))
                .input(dust, PdFullereneMatrix, 1)
                .output(dust, FullerenePolymerMatrix, 2)
                .EUt(8000000)
                .duration(40)
                .buildAndRegister();
    }
}
