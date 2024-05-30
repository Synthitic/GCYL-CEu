package com.fulltrix.tjfcore.recipes.chain;

import static com.fulltrix.tjfcore.TJFMaterials.*;
import static com.fulltrix.tjfcore.recipes.TJFRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class SeleniumChain {
    public static void init() {

        /*
         * Unknown compounds key:
         * ElectricallyImpureCopper: Cu
         * CopperRefiningSolution: CuH2SO4
         * AnodicSlime: TeSe
         * SelenateTellurateMix: TeSe(H2Na2CO3)2
         * SelenateSolution: SeH2CO3
         */

        // CuFeS2 + SiO2 + 5O -> Cu(EIC) + FeSiO3 + 2SO2
        BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1500)
                .input(crushedCentrifuged, Chalcopyrite)
                .input(dust, SiliconDioxide, 3)
                .fluidInputs(Oxygen.getFluid(5000))
                .output(dust, ElectricallyImpureCopper)
                .output(dust, Ferrosilite, 5)
                .fluidOutputs(SulfurDioxide.getFluid(2000))
                .buildAndRegister();

        // Cu + H2SO4 -> CuH2SO4
        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(120)
                .input(dust, ElectricallyImpureCopper)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(CopperRefiningSolution.getFluid(1000))
                .buildAndRegister();

        // 2Cu + CuH2SO4 -> H2SO4 + 3Cu + TeSe(75%)
        ELECTROLYZER_RECIPES.recipeBuilder().duration(450).EUt(120)
                .input(plate, ElectricallyImpureCopper, 2)
                .fluidInputs(CopperRefiningSolution.getFluid(1000))
                .fluidOutputs(SulfuricAcid.getFluid(1000))
                .output(ingot, Copper, 3)
                .chancedOutput(dust, AnodicSlime, 1, 7500, 0)
                .buildAndRegister();

        // TeSe + 2Na2CO3 + 4O -> TeO2SeO2(Na2CO3)2
        BLAST_RECIPES.recipeBuilder().duration(320).EUt(120).blastFurnaceTemp(2100)
                .input(dust, AnodicSlime, 1)
                .input(dust, SodaAsh, 12)
                .fluidInputs(Oxygen.getFluid(4000))
                .output(dustTiny, PreciousMetal, 5)
                .fluidOutputs(SeleniteTelluriteMix.getFluid(1000))
                .buildAndRegister();

        // TeO2SeO2(Na2CO3)2 + H2SO4 -> TeO2 + Na2SO4 + Na2SeO3 + CO2 + H2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(270).EUt(1920)
                .fluidInputs(SeleniteTelluriteMix.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .output(dust, TelluriumOxide, 3)
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(SulfurTrioxide.getFluid(1000))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .fluidOutputs(SeleniteSolution.getFluid(1000))
                .buildAndRegister();

        // Na2SeO3 + 2HCl -> 2NaCl + SeO2 + H2O
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(240).EUt(480)
                .fluidInputs(SeleniteSolution.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .output(dust, Salt, 4)
                .output(dust, SeleniumOxide, 3)
                .buildAndRegister();

        // SeO2 + 2SO2 -> Se + 2SO3
        CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(120)
                .input(dust, SeleniumOxide, 3)
                .fluidInputs(SulfurDioxide.getFluid(2000))
                .output(dust, Selenium)
                .fluidOutputs(SulfurTrioxide.getFluid(2000))
                .buildAndRegister();

        // TeO2 + 2SO2 -> Te + 2SO3
        CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(120)
                .input(dust, TelluriumOxide, 3)
                .fluidInputs(SulfurDioxide.getFluid(2000))
                .output(dust, Tellurium)
                .fluidOutputs(SulfurTrioxide.getFluid(2000))
                .buildAndRegister();
    }
}
