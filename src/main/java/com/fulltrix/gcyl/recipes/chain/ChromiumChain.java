package com.fulltrix.gcyl.recipes.chain;

import gregtech.api.unification.OreDictUnifier;

import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustTiny;

public class ChromiumChain {
    public static void init() {

        // Na2CO3 + H2O -> Na2CO3(H2O)
        MIXER_RECIPES.recipeBuilder().duration(60).EUt(30)
                .input(dust, SodaAsh, 6)
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(SodiumCarbonateSolution.getFluid(1000))
                .buildAndRegister();

        // 2NaOH + CO2 -> Na2CO3 + H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(480)
                .input(dust, SodiumHydroxide, 6)
                .fluidInputs(CarbonDioxide.getFluid(1000))
                .output(dust, SodaAsh, 6)
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        // CaCO3 + 2NaCl -> Na2CO3 + CaCl2
        BLAST_RECIPES.recipeBuilder().duration(120).EUt(120).blastFurnaceTemp(500)
                .input(dust, Calcite, 5)
                .input(dust, Salt, 4)
                .output(dust, SodaAsh, 6)
                .output(dust, CalciumChloride, 3)
                .buildAndRegister();

        // 2Cr2FeO4 + 4Na2CO3(H2O) + 7O -> Fe2O3 + 4CO2 + 4Na2CrO4(H2O)
        CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(120)
                .input(dust, Chromite, 7)
                .fluidInputs(SodiumCarbonateSolution.getFluid(4000))
                .fluidInputs(Oxygen.getFluid(7000))
                .output(dust, BandedIron, 5)
                .fluidOutputs(CarbonDioxide.getFluid(4000))
                .fluidOutputs(SodiumChromateSolution.getFluid(4000))
                .buildAndRegister();

        // 2Na2CrO4(H2O) + H2SO4 -> Na2Cr2O7(H2O) + Na2SO4 + 2H2O (H2O lost to dehydrator)
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(180).EUt(120)
                .fluidInputs(SodiumChromateSolution.getFluid(2000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(SodiumDichromateSolution.getFluid(1000))
                .output(dust, SodiumSulfate, 7)
                .buildAndRegister();

        // 2C + Na2Cr2O7(H2O) -> Na2CO3 + Cr2O3 + CO + H2O (H2O lost to dehydrator)
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(200).EUt(120)
                .input(dust, Carbon, 2)
                .fluidInputs(SodiumDichromateSolution.getFluid(1000))
                .output(dust, SodaAsh, 6)
                .output(dust, ChromiumIIIOxide, 5)
                .fluidOutputs(CarbonMonoxide.getFluid(1000))
                .buildAndRegister();

        // Cr2O3 + 2Al -> 2 Cr + Al2O3
        BLAST_RECIPES.recipeBuilder().duration(200).EUt(120).blastFurnaceTemp(1200)
                .input(dust, ChromiumIIIOxide, 5)
                .input(dust, Aluminium, 2)
                .output(dust, Chrome, 2)
                .output(dust, Alumina, 5)
                .buildAndRegister();

        // Combined Step - Chromite
        CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(30720).duration(200)
                .input(dust, Chromite, 7)
                .fluidInputs(SulfuricAcid.getFluid(3000))
                .output(dust, BandedIron, 5)
                .output(dust, Sulfur, 3)
                .output(dust, Chrome, 4)
                .buildAndRegister();

        // Na2SO4(H2O) -> Na2SO4
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(120).EUt(120)
                .fluidInputs(SodiumSulfateSolution.getFluid(1000))
                .output(dust, SodiumSulfate, 7)
                .buildAndRegister();

        // Al2O3Cr + (HNO3 + HCl) -> Al2O3Cr(HNO3HCl)
        MIXER_RECIPES.recipeBuilder().duration(280).EUt(1920)
                .input(dust, Ruby, 6)
                .fluidInputs(AquaRegia.getFluid(2000))
                .fluidOutputs(RubySlurry.getFluid(2000))
                .buildAndRegister();

        // Al2O3Cr(HNO3HCl) -> 2Al + Cr + 3O + Ti + Fe + V + HNO3 + HCl
        CENTRIFUGE_RECIPES.recipeBuilder().duration(320).EUt(480)
                .fluidInputs(RubySlurry.getFluid(2000))
                .output(dust, Aluminium, 2)
                .output(dust, Chrome)
                .chancedOutput(OreDictUnifier.get(dustTiny, Titanium), 2000, 0)
                .chancedOutput(OreDictUnifier.get(dustTiny, Iron), 2000, 0)
                .chancedOutput(OreDictUnifier.get(dustTiny, Vanadium), 2000, 0)
                .fluidOutputs(Oxygen.getFluid(3000))
                .fluidOutputs(NitricAcid.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .buildAndRegister();

        // Al2O3 + (HNO3 + HCl) -> Al2O3(HNO3HCl)
        MIXER_RECIPES.recipeBuilder().duration(280).EUt(1920)
                .input(dust, Sapphire, 5)
                .fluidInputs(AquaRegia.getFluid(2000))
                .fluidOutputs(SapphireSlurry.getFluid(2000))
                .buildAndRegister();

        // Al2O3(HNO3HCl) -> 2Al + 3O + Ti + Fe + V + HNO3 + HCl
        CENTRIFUGE_RECIPES.recipeBuilder().duration(320).EUt(480)
                .fluidInputs(SapphireSlurry.getFluid(2000))
                .output(dust, Aluminium, 2)
                .chancedOutput(OreDictUnifier.get(dustTiny, Titanium), 2000, 0)
                .chancedOutput(OreDictUnifier.get(dustTiny, Iron), 2000, 0)
                .chancedOutput(OreDictUnifier.get(dustTiny, Vanadium), 2000, 0)
                .fluidOutputs(Oxygen.getFluid(3000))
                .fluidOutputs(NitricAcid.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .buildAndRegister();

        // Al2O3 + (HNO3 + HCl) -> Al2O3(HNO3HCl)
        MIXER_RECIPES.recipeBuilder().duration(280).EUt(1920)
                .input(dust, GreenSapphire, 5)
                .fluidInputs(AquaRegia.getFluid(2000))
                .fluidOutputs(GreenSapphireSlurry.getFluid(2000))
                .buildAndRegister();

        // Al2O3(HNO3HCl) -> 2Al + 3O + Be + Ti + Fe + V + HNO3 + HCl
        CENTRIFUGE_RECIPES.recipeBuilder().duration(320).EUt(480)
                .fluidInputs(GreenSapphireSlurry.getFluid(2000))
                .output(dust, Aluminium, 2)
                .chancedOutput(OreDictUnifier.get(dustTiny, Beryllium), 2000, 0)
                .chancedOutput(OreDictUnifier.get(dustTiny, Titanium), 2000, 0)
                .chancedOutput(OreDictUnifier.get(dustTiny, Iron), 2000, 0)
                .chancedOutput(OreDictUnifier.get(dustTiny, Vanadium), 2000, 0)
                .fluidOutputs(Oxygen.getFluid(3000))
                .fluidOutputs(NitricAcid.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .buildAndRegister();
    }
}
