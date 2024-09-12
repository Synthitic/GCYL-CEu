package com.fulltrix.gcyl.recipes.chain;

import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES;
import static com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps.ADVANCED_CENTRIFUGE_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class REEChain {
    public static void init() {

        /*
         * When I use [REE], it could be any of the below, in any combination,
         * [La, Pr, Nd, Ce, Sc, Eu, Gd, Sm, Y, Tb, Dy, Ho, Er, Tm, Yt, Lu],
         * being all Rare Earth Elements, excluding Promethium (Pm).
         *
         * Unknown compounds key:
         * Acidic Salt Water: 3 == H2SO4(NaCl)3(H2O)3
         * SulfuricBromineSolution: H2SO4Br(H2O)
         * DebrominatedWater: H2O, basically
         * HotVapourMixture: H2SO4Br(H2O)2
         * DampBromine: Br(H2O)
         * RareEarth: [REE]
         * RareEarthHydroxidesSolution: Na[REE]-OH(H2O)
         * RareEarthChloridesSolution: HNaOH[REE]-Cl(H2O)
         * ThUSludge: ThU
         * LaNdOxidesSolution: LaPrNdCeOx
         * SmGdOxidesSolution: ScEuGdSmOx
         * TbHoOxidesSolution: YTbDyHoOx
         * ErLuOxidesSolution: ErTmYtLuOx
         */

        // 3NaCl(H2O) + 2Cl + H2SO4 -> H2SO4(NaCl)3(H2O)3Cl2
        // Formula above multiplied up for simplicity
        // 2C4H8O + 4H -> C8H18O + H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Butyraldehyde.getFluid(2000))
                .fluidInputs(Hydrogen.getFluid(4000))
                .fluidOutputs(Ethylhexanol.getFluid(3000))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(480)
                .duration(200)
                .buildAndRegister();

        // 5C8H18O + 0.5P4O10 -> 2C16H35O4P + 2C4H10O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Ethylhexanol.getFluid(5000))
                .input(dust, PhosphorusPentoxide, 7)
                .fluidOutputs(DiethylhexylPhosphoricAcid.getFluid(2000))
                .fluidOutputs(Butanol.getFluid(2000))
                .EUt(480)
                .duration(200)
                .buildAndRegister();

        // [REE] + 3 [NaOH + H2O] + 3 H2O -> [REE(OH)3 + 3 NaOH + 3 H2O] + 3 H
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, RareEarth)
                .fluidInputs(SodiumHydroxideSolution.getFluid(3000))
                .fluidInputs(Water.getFluid(3000))
                .fluidOutputs(RareEarthHydroxidesSolution.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(3000))
                .EUt(480)
                .duration(200)
                .buildAndRegister();

        // 3 HCl + [REE(OH)3 + 3 NaOH] -> ThU + [REECl3 + 3 H2O] + 3 [NaOH + H2O]
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(HydrochloricAcid.getFluid(3000))
                .fluidInputs(RareEarthHydroxidesSolution.getFluid(1000))
                .fluidOutputs(RareEarthChloridesSolution.getFluid(3000))
                .fluidOutputs(SodiumHydroxideSolution.getFluid(3000))
                .output(dust, ThUSludge, 2)
                .EUt(480)
                .duration(200)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(100).EUt(30)
                .fluidInputs(SodiumHydroxideSolution.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .output(dust, SodiumHydroxide, 3)
                .buildAndRegister();


        // 2 [REECl3 + 3 H2O] + C16H35O4P(cat.) -> [REE2O3](sep.) + 6 HCl + 3 H2O
        ADVANCED_CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(RareEarthChloridesSolution.getFluid(6000))
                .notConsumable(DiethylhexylPhosphoricAcid.getFluid(1))
                .fluidOutputs(LaNdOxidesSolution.getFluid(250))
                .fluidOutputs(SmGdOxidesSolution.getFluid(250))
                .fluidOutputs(TbHoOxidesSolution.getFluid(250))
                .fluidOutputs(ErLuOxidesSolution.getFluid(250))
                .fluidOutputs(HydrochloricAcid.getFluid(6000))
                .EUt(480)
                .duration(600)
                .buildAndRegister();

        // 4LaPrNdCeOx -> La2O3 + Pr2O3 + Nd2O3 + Ce2O3 (each 43% +2.75%)
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(LaNdOxidesSolution.getFluid(4000))
                .chancedOutput(dust, LanthanumOxide, 5, 4300, 275)
                .chancedOutput(dust, PraseodymiumOxide, 5, 4300, 275)
                .chancedOutput(dust, NeodymiumOxide, 5, 4300, 275)
                .chancedOutput(dust, CeriumOxide, 5, 4300, 275)
                .EUt(480)
                .duration(220)
                .buildAndRegister();

        // 4ScEuGdSmOx -> Sc2O3 + Eu2O3 + Gd2O3 + Sm2O3 (each 43% +2.75%)
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(SmGdOxidesSolution.getFluid(4000))
                .chancedOutput(dust, ScandiumOxide, 5, 4300, 275)
                .chancedOutput(dust, EuropiumOxide, 5, 4300, 275)
                .chancedOutput(dust, GadoliniumOxide, 5, 4300, 275)
                .chancedOutput(dust, SamariumOxide, 5, 4300, 275)
                .EUt(480)
                .duration(220)
                .buildAndRegister();

        // 4YTbDyHoOx -> Y2O3 + Tb2O3 + Dy2O3 + Ho2O3 (each 43% +2.75%)
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(TbHoOxidesSolution.getFluid(4000))
                .chancedOutput(dust, YttriumOxide, 5, 4300, 275)
                .chancedOutput(dust, TerbiumOxide, 5, 4300, 275)
                .chancedOutput(dust, DysprosiumOxide, 5, 4300, 275)
                .chancedOutput(dust, HolmiumOxide, 5, 4300, 275)
                .EUt(480)
                .duration(220)
                .buildAndRegister();

        // 4ErTmYtLuOx -> Er2O3 + Tm2O3 + Yt2O3 + Lu2O3 (each 43% +2.75%)
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(ErLuOxidesSolution.getFluid(4000))
                .chancedOutput(dust, ErbiumOxide, 5, 4300, 275)
                .chancedOutput(dust, ThuliumOxide, 5, 4300, 275)
                .chancedOutput(dust, YtterbiumOxide, 5, 4300, 275)
                .chancedOutput(dust, LutetiumOxide, 5, 4300, 275)
                .EUt(480)
                .duration(220)
                .buildAndRegister();

        // 2ThU + O -> 0.5ThO + Th(20%) + U(20%)
        //TODO: nuclear stuff
        /*
        CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust,ThUSludge,4))
                .fluidInputs(Oxygen.getFluid(500))
                .chancedOutput(dust,OreDictUnifier.get(dustTiny, Thorium), 2000, 150)
                .chancedOutput(dust,OreDictUnifier.get(dustTiny, UraniumRadioactive.getMaterial()), 2000, 150)
                .output(oxide, Thorium)
                .EUt(480)
                .duration(250)
                .buildAndRegister();

         */
    }
}
