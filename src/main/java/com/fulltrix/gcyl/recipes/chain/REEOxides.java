package com.fulltrix.gcyl.recipes.chain;

import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static com.fulltrix.gcyl.materials.GCYLMaterials.LutetiumOxide;
import static gregtech.api.recipes.RecipeMaps.BLAST_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.Materials.CarbonDioxide;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class REEOxides {

    public static void init() {

        // 3C + 2La2O3 -> 4La + 3CO2
        BLAST_RECIPES.recipeBuilder()
                .blastFurnaceTemp(2500)
                .input(dust, Carbon, 3)
                .input(dust, LanthanumOxide, 10)
                .output(dust, Lanthanum, 4)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        // 3C + 2Pr2O3 -> 4Pr + 3CO2
        BLAST_RECIPES.recipeBuilder()
                .blastFurnaceTemp(2500)
                .input(dust, Carbon, 3)
                .input(dust, PraseodymiumOxide, 10)
                .output(dust, Praseodymium, 4)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        // 3C + 2Nd2O3 -> 4Nd + 3CO2
        BLAST_RECIPES.recipeBuilder()
                .blastFurnaceTemp(2500)
                .input(dust, Carbon, 3)
                .input(dust, NeodymiumOxide, 10)
                .output(dust, Neodymium, 4)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        // 3C + 2Ce2O3 -> 4Ce + 3CO2
        BLAST_RECIPES.recipeBuilder()
                .blastFurnaceTemp(2500)
                .input(dust, Carbon, 3)
                .input(dust, CeriumOxide, 10)
                .output(dust, Cerium, 4)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        // 3C + 2Sc2O3 -> 4Sc + 3CO2
        BLAST_RECIPES.recipeBuilder()
                .blastFurnaceTemp(2500)
                .input(dust, Carbon, 3)
                .input(dust, ScandiumOxide, 10)
                .output(dust, Scandium, 4)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        // 3C + 2Eu2O3 -> 4Eu + 3CO2
        BLAST_RECIPES.recipeBuilder()
                .blastFurnaceTemp(2500)
                .input(dust, Carbon, 3)
                .input(dust, EuropiumOxide, 10)
                .output(dust, Europium, 4)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        // 3C + 2Gd2O3 -> 4Gd + 3CO2
        BLAST_RECIPES.recipeBuilder()
                .blastFurnaceTemp(2500)
                .input(dust, Carbon, 3)
                .input(dust, GadoliniumOxide, 10)
                .output(dust, Gadolinium, 4)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        // 3C + 2Sm2O3 -> 4Sm + 3CO2
        BLAST_RECIPES.recipeBuilder()
                .blastFurnaceTemp(2500)
                .input(dust, Carbon, 3)
                .input(dust, SamariumOxide, 10)
                .output(dust, Samarium, 4)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        // 3C + 2Y2O3 -> 4Y + 3CO2
        BLAST_RECIPES.recipeBuilder()
                .blastFurnaceTemp(2500)
                .input(dust, Carbon, 3)
                .input(dust, YttriumOxide, 10)
                .output(dust, Yttrium, 4)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        // 3C + 2Tb2O3 -> 4Tb + 3CO2
        BLAST_RECIPES.recipeBuilder()
                .blastFurnaceTemp(2500)
                .input(dust, Carbon, 3)
                .input(dust, TerbiumOxide, 10)
                .output(dust, Terbium, 4)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        // 3C + 2Dy2O3 -> 4Dy + 3CO2
        BLAST_RECIPES.recipeBuilder()
                .blastFurnaceTemp(2500)
                .input(dust, Carbon, 3)
                .input(dust, DysprosiumOxide, 10)
                .output(dust, Dysprosium, 4)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        // 3C + 2Ho2O3 -> 4Ho + 3CO2
        BLAST_RECIPES.recipeBuilder()
                .blastFurnaceTemp(2500)
                .input(dust, Carbon, 3)
                .input(dust, HolmiumOxide, 10)
                .output(dust, Holmium, 4)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        // 3C + 2Er2O3 -> 4Er + 3CO2
        BLAST_RECIPES.recipeBuilder()
                .blastFurnaceTemp(2500)
                .input(dust, Carbon, 3)
                .input(dust, ErbiumOxide, 10)
                .output(dust, Erbium, 4)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        // 3C + 2Tm2O3 -> 4Tm + 3CO2
        BLAST_RECIPES.recipeBuilder()
                .blastFurnaceTemp(2500)
                .input(dust, Carbon, 3)
                .input(dust, ThuliumOxide, 10)
                .output(dust, Thulium, 4)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        // 3C + Yt2O3 -> 4Yt + 3CO2
        BLAST_RECIPES.recipeBuilder()
                .blastFurnaceTemp(2500)
                .input(dust, Carbon, 3)
                .input(dust, YtterbiumOxide, 10)
                .output(dust, Ytterbium, 4)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

        // 3C + 2Lu2O3 -> 4Lu + 3CO2
        BLAST_RECIPES.recipeBuilder()
                .blastFurnaceTemp(2500)
                .input(dust, Carbon, 3)
                .input(dust, LutetiumOxide, 10)
                .output(dust, Lutetium, 4)
                .fluidOutputs(CarbonDioxide.getFluid(3000))
                .EUt(480)
                .duration(100)
                .buildAndRegister();

    }

}
