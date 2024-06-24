package com.fulltrix.gcyl.recipes.chain;

import gregtech.api.unification.OreDictUnifier;

import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class VanadiumChain {
    public static void init() {

        // Fe3O4V + C = 3Fe + (VO)C(TiO2) + CO
        BLAST_RECIPES.recipeBuilder().duration(220).EUt(120).blastFurnaceTemp(1500)
                .input(dust, VanadiumMagnetite, 4)
                .input(dust, Carbon)
                .output(ingot, Iron, 3)
                .output(dust, VanadiumSlag, 5)
                .fluidOutputs(CarbonMonoxide.getFluid(1000))
                .buildAndRegister();

        // (VO)C(TiO2) = Dark Ash + TiO2 + VO
        MACERATOR_RECIPES.recipeBuilder().duration(80).EUt(24)
                .input(dust, VanadiumSlag, 5)
                .output(dust, DarkAsh)
                .output(dustSmall, Rutile)
                .output(dust, VanadiumSlagDust, 2)
                .buildAndRegister();

        // 2VO + 3Na2CO3 = 2Na3VO4 + 3CO
        BLAST_RECIPES.recipeBuilder().duration(150).EUt(120).blastFurnaceTemp(700)
                .input(dust, VanadiumSlagDust, 4)
                .input(dust, SodaAsh, 18)
                .output(dust, SodiumVanadate, 16)
                .fluidOutputs(CarbonMonoxide.getFluid(3000))
                .buildAndRegister();

        // H2SO4 + NH4Cl + Na3VO4 = NH4VO3 + [Cl + 3Na + O + H2SO4]
        CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(120)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .input(dust, SodiumVanadate, 8)
                .fluidInputs(AmmoniumChloride.getFluid(1000))
                .output(dust, AmmoniumVanadate, 9)
                .fluidOutputs(VanadiumWasteSolution.getFluid(1000))
                .buildAndRegister();

        // [Cl + 3Na + O + H2SO4] = SiO2 + Al(OH)3 + NaCl + Na2SO4 + H2O (H2O lost to dehydrator)
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(120).EUt(30)
                .fluidInputs(VanadiumWasteSolution.getFluid(4000))
                .output(dust, Salt, 2)
                .output(dust, SodiumSulfate, 7)
                .chancedOutput(OreDictUnifier.get(dust, SiliconDioxide, 3), 5000, 1200)
                .chancedOutput(dust, AluminiumHydroxide, 7, 5000, 1200)
                .buildAndRegister();

        // Na2SO4 + H2 -> H2SO4 + 2Na
        CHEMICAL_RECIPES.recipeBuilder().duration(90).EUt(30)
                .input(dust, SodiumSulfate, 7)
                .fluidInputs(Hydrogen.getFluid(2000))
                .output(dust, Sodium, 2)
                .fluidOutputs(SulfuricAcid.getFluid(1000))
                .buildAndRegister();

        // 2NH4VO3 = V2O5 + 2NH3 + H2O (H2O lost to dehydrator)
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(240).EUt(120)
                .input(dust, AmmoniumVanadate, 18)
                .output(dust, VanadiumOxide, 7)
                .fluidOutputs(Ammonia.getFluid(2000))
                .buildAndRegister();

        // V2O5 + 2Al + C = Al2O3 + 2V + CO2
        BLAST_RECIPES.recipeBuilder().duration(200).EUt(120).blastFurnaceTemp(1200)
                .input(dust, VanadiumOxide, 7)
                .input(dust, Aluminium, 2)
                .input(dust, Carbon)
                .output(dust, Alumina, 5)
                .output(dust, Vanadium, 2)
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .buildAndRegister();

        // Combined Step - Vanadium Magnetite
        CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(1920).duration(200)
                .input(dust, VanadiumMagnetite, 4)
                .fluidInputs(SulfuricAcid.getFluid(3000))
                .output(dust, Vanadium, 2)
                .output(dust, Magnetite, 7)
                .output(dust, Sulfur, 3)
                .buildAndRegister();
    }
}
