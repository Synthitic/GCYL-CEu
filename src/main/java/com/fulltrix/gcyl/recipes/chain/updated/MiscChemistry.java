package com.fulltrix.gcyl.recipes.chain.updated;


import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static com.fulltrix.gcyl.materials.chains.MiscMaterials.*;
import static com.fulltrix.gcyl.materials.chains.NewPlatinumGroupMaterials.*;
import static com.fulltrix.gcyl.materials.chains.NewREEMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class MiscChemistry {

    public static void init() {


        // Hydryoxlyammonium Sulfate and Dimethylglyoxime
        // C4H8 (g) + H2O (l) -> C4H10O (l)
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Butene.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(Butanol.getFluid(1000))
                .duration(160)
                .EUt(120)
                .buildAndRegister();

        // C4H1OO (l) -> C4H8O (l) + H2 (g)
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Butanol.getFluid(1000))
                .notConsumable(plate, ZincBronze, 1)
                .fluidOutputs(Butanone.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(2000))
                .duration(160)
                .EUt(120)
                .buildAndRegister();

        // NaOH (s) + HNO3 (aq) -> NaNO3 (s) + H2O (l)
        CHEMICAL_RECIPES.recipeBuilder()
                .circuitMeta(10)
                .input(dust, SodiumHydroxide, 3)
                .fluidInputs(NitricAcid.getFluid(1000))
                .output(dust, SodiumNitrate, 5)
                .fluidOutputs(Water.getFluid(1000))
                .duration(160)
                .EUt(120)
                .buildAndRegister();

        // 3NaNO3 (l) + 2Fe (s) -> 3NaNO2 (s) + Fe2O3 (s)
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(SodiumNitrate.getFluid(3000))
                .input(dust, Iron, 2)
                .output(dust, SodiumNitrite, 12)
                .output(dust, BandedIron, 5)
                .duration(160)
                .EUt(120)
                .buildAndRegister();

        // C2H5OH (l) + NaNO2 (s) -> C2H5NO2 (g) + NaOH (s)
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Ethanol.getFluid(1000))
                .input(dust, SodiumNitrite, 4)
                .notConsumable(SulfuricAcid.getFluid(500))
                .output(dust, SodiumHydroxide, 3)
                .fluidOutputs(EthylNitrite.getFluid(1000))
                .duration(160)
                .EUt(120)
                .buildAndRegister();

        // C4H8O (l) + C2H5NO2 (g) -> C4H7NO2 (s) + C2H5OH (l)
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Butanone.getFluid(1000))
                .fluidInputs(EthylNitrite.getFluid(1000))
                .notConsumable(HydrochloricAcid.getFluid(500))
                .output(dust, DiacetylMonoxime, 14)
                .fluidOutputs(Ethanol.getFluid(1000))
                .duration(160)
                .EUt(120)
                .buildAndRegister();

        // NH4OH (aq) + NO2 (g) + NO (g) -> NH4NO2 (s) + HNO2 (aq)
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(AmmoniumHydroxide.getFluid(1000))
                .fluidInputs(NitrogenDioxide.getFluid(1000))
                .fluidInputs(NitricOxide.getFluid(1000))
                .fluidOutputs(AmmoniumNitrite.getFluid(1000))
                .fluidOutputs(NitrousAcid.getFluid(1000))
                .duration(160)
                .EUt(120)
                .buildAndRegister();

        // 2(NH4)NO2 (s) + 4SO2 (g) + 6H2O (l) -> (NH3OH)2SO4 (s) + 3H2SO4 (aq) + 2NH3 (g)
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(AmmoniumNitrite.getFluid(2000))
                .fluidInputs(SulfurDioxide.getFluid(4000))
                .fluidInputs(Water.getFluid(6000))
                .fluidOutputs(HydroxylammoniumSulfate.getFluid(1000))
                .fluidOutputs(SulfuricAcid.getFluid(3000))
                .fluidOutputs(Ammonia.getFluid(2000))
                .duration(160)
                .EUt(120)
                .buildAndRegister();

        // 2C4H7NO2 (s) + (NH3OH)2SO4 (s) -> 2C4H8N2O2 (s) + H2SO4 (aq) + 2H2O (l)
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, DiacetylMonoxime, 28)
                .fluidInputs(HydroxylammoniumSulfate.getFluid(1000))
                .output(dust, Dimethylglyoxime, 2)
                .fluidOutputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .duration(160)
                .EUt(120)
                .buildAndRegister();

        // potassium stuffs
        // KCl + H2SO4 = HCl + KHSO4
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, RockSalt, 2)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .output(dust, PotassiumBisulfate, 7)
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .duration(800)
                .EUt(VH[MV])
                .buildAndRegister();

        // 2 KHSO4 -> K2S2O8 + 2H
        ELECTROLYZER_RECIPES.recipeBuilder()
                .input(dust, PotassiumBisulfate, 14)
                .notConsumable(SulfuricAcid.getFluid(50))
                .output(dust, PotassiumPersulfate, 12)
                .fluidOutputs(Hydrogen.getFluid(2000))
                .duration(600)
                .EUt(VA[MV])
                .buildAndRegister();

        // 2 KCl + 2 H2O -> 2KOH + 2Cl + 2H
        //TODO CHANGE BACK TO ELECTROLYZER?
        CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, RockSalt, 4)
                .fluidInputs(Water.getFluid(2000))
                .output(dust, PotassiumHydroxide, 6)
                .fluidOutputs(Chlorine.getFluid(2000))
                .fluidOutputs(Hydrogen.getFluid(2000))
                .duration(600)
                .EUt(VA[MV])
                .buildAndRegister();

        // ferrous chloride
        // 2 FeCl3 + C6H5Cl -> 2 FeCl2 + C6H4Cl2 + HCl
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Iron3Chloride.getFluid(2000))
                .fluidInputs(Chlorobenzene.getFluid(1000))
                .fluidOutputs(FerrousChloride.getFluid(2000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(Dichlorobenzene.getFluid(1000))
                .duration(800)
                .EUt(VA[HV])
                .buildAndRegister();

        // potassium nitrate
        // NaNO3 + KCl -> NaCl + KNO3
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(SodiumNitrate.getFluid(1000))
                .input(dust, RockSalt, 2)
                .output(dust, Salt, 2)
                .fluidOutputs(PotassiumNitrate.getFluid(1000))
                .duration(100)
                .EUt(VH[MV])
                .buildAndRegister();

        // Palm oil -> octanoic acid
        DISTILLERY_RECIPES.recipeBuilder()
                .fluidInputs(PalmOil.getFluid(1000))
                .fluidOutputs(OctanicAcid.getFluid(70))
                .EUt(120)
                .duration(80)
                .buildAndRegister();

        // octanoic acid -> octanol
        CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(dust, Cobalt, 1)
                .fluidInputs(Hydrogen.getFluid(3000))
                .fluidInputs(OctanicAcid.getFluid(1000))
                .fluidOutputs(Octanol.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(480)
                .duration(320)
                .buildAndRegister();

        // octanol -> trioctylamine
        CHEMICAL_RECIPES.recipeBuilder()
                .notConsumable(dust, Nickel, 1)
                .fluidInputs(Octanol.getFluid(3000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidOutputs(Trioctylamine.getFluid(1000))
                .fluidOutputs(Water.getFluid(3000))
                .EUt(480)
                .duration(320)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Butyraldehyde.getFluid(2000))
                .fluidInputs(Hydrogen.getFluid(2000))
                .fluidOutputs(Ethylhexanol.getFluid(1000))
                .duration(320)
                .EUt(480)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Ethylhexanol.getFluid(4000))
                .input(dust, PhosphorusPentoxide, 14)
                .input(dust, SodiumHydroxide, 9)
                .output(dust, TrisodiumPhosphate, 8)
                .fluidOutputs(DEHPA.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .duration(320)
                .EUt(480)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder()
                .input(dust, Carbon, 1)
                .input(dust, Sulfur, 2)
                .output(dust, CarbonDisulfide, 3)
                .blastFurnaceTemp(600)
                .duration(400)
                .EUt(120)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, CarbonDisulfide, 3)
                .fluidInputs(Ammonia.getFluid(2000))
                .fluidOutputs(AmmoniumThiocyanate.getFluid(1000))
                .fluidOutputs(HydrogenSulfide.getFluid(1000))
                .duration(1000)
                .EUt(120)
                .buildAndRegister();
    }
}
