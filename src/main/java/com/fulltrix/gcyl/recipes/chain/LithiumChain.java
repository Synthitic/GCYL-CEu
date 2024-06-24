package com.fulltrix.gcyl.recipes.chain;

import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class LithiumChain {
    public static void init() {

        // LiAlSi2O6 = LiAlSi2O6
        BLAST_RECIPES.recipeBuilder().duration(80).EUt(120).blastFurnaceTemp(1400)
                .input(dust, Spodumene, 4)
                .output(dust, RoastedSpodumene, 1)
                .buildAndRegister();

        // KLi3Al4F2O10 + CaO = CaF2 + (KLi3Al4O10)O
        BLAST_RECIPES.recipeBuilder().duration(160).EUt(120).blastFurnaceTemp(1400)
                .input(dust, Lepidolite, 8)
                .input(dust, Quicklime, 2)
                .output(dust, RoastedLepidolite, 1)
                .output(dust, Fluorite, 3)
                .buildAndRegister();

        // LiAlSi2O6 + H2SO4 = [LiAlO2 + H2SO4] + 2SiO2
        CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(120)
                .input(dust, RoastedSpodumene, 1)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(DissolvedLithiumOre.getFluid(1000))
                .output(dust, SiliconDioxide, 6)
                .buildAndRegister();

        // (KLi3Al4O10)O + Al + 3H2SO4 = 3[LiAlO2 + H2SO4] + Al2O3 + K2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(140).EUt(120)
                .input(dust, RoastedLepidolite, 1)
                .input(dust, Aluminium)
                .fluidInputs(SulfuricAcid.getFluid(3000))
                .fluidOutputs(DissolvedLithiumOre.getFluid(3000))
                .output(dust, Potash, 3)
                .output(dust, Alumina, 5)
                .buildAndRegister();

        // 2[LiAlO2 + H2SO4] + H2SO4 + CO2 = Al2(SO4)3 + [Li2CO3 + H2O] + 2H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(140).EUt(120)
                .fluidInputs(DissolvedLithiumOre.getFluid(2000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidInputs(CarbonDioxide.getFluid(1000))
                .output(dust, AluminiumSulfate, 17)
                .fluidOutputs(LithiumCarbonateSolution.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();

        // K2SO4 -> 2K + S + 4O
        ELECTROLYZER_RECIPES.recipeBuilder().duration(180).EUt(120)
                .input(dust, PotassiumSulfate, 7)
                .output(dust, Potassium, 2)
                .output(dust, Sulfur)
                .fluidOutputs(Oxygen.getFluid(4000))
                .buildAndRegister();

        // Al2(SO4)3 = 2Al + 3S + 12O
        ELECTROLYZER_RECIPES.recipeBuilder().duration(180).EUt(120)
                .input(dust, AluminiumSulfate, 17)
                .output(dust, Aluminium, 2)
                .output(dust, Sulfur, 3)
                .fluidOutputs(Oxygen.getFluid(12000))
                .buildAndRegister();

        // [Li2CO3 + H2O] + 2HCl + 2Na = 2[LiCl + H2O] + Na2CO3
        // off by 1 oxygen, which is fine since water is lost in dehydrator step
        CHEMICAL_RECIPES.recipeBuilder().duration(130).EUt(120)
                .input(dust, Sodium, 2)
                .fluidInputs(LithiumCarbonateSolution.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .output(dust, SodaAsh, 6)
                .fluidOutputs(LithiumChlorideSolution.getFluid(2000))
                .buildAndRegister();

        // [LiCl + H2O] = LiCl + H2O (H2O Voided - Dehydrator)
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(180).EUt(120)
                .fluidInputs(LithiumChlorideSolution.getFluid(1000))
                .fluidOutputs(Chlorine.getFluid(1000))
                .output(dust, Lithium)
                .buildAndRegister();

        // Combined Step - Lepidolite
        CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(1920).duration(200)
                .input(dust, Lepidolite, 8)
                .fluidInputs(SulfuricAcid.getFluid(3000))
                .output(dust, LithiumFluoride, 4)
                .output(dust, Potash, 3)
                .output(dust, AluminiumSulfate, 17)
                .buildAndRegister();

        // Combined Step - Spodumene
        CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(1920).duration(200)
                .input(dust, Spodumene, 8)
                .fluidInputs(SulfuricAcid.getFluid(3000))
                .output(dust, SiliconDioxide, 12)
                .output(dust, AluminiumSulfate, 17)
                .output(dust, Lithium, 2)
                .buildAndRegister();
    }
}
