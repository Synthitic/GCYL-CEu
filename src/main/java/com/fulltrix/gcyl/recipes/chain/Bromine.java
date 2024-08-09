package com.fulltrix.gcyl.recipes.chain;

import static com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES;
import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static com.fulltrix.gcyl.materials.GCYLMaterials.DampBromine;
import static gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES;
import static gregtech.api.recipes.RecipeMaps.CHEMICAL_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.Materials.Bromine;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class Bromine {

    public static void init() {

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(SaltWater.getFluid(1500))
                .fluidInputs(Chlorine.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(500))
                .fluidOutputs(AcidicSaltWater.getFluid(3000))
                .EUt(480)
                .duration(180)
                .buildAndRegister();

        // H2SO4(NaCl)3(H2O)3Cl2 -> 3NaCl + H2SO4Br(H2O)Cl2 + 2H2O
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(AcidicSaltWater.getFluid(6000))
                .output(dust, Salt, 6)
                .fluidOutputs(SulfuricBromineSolution.getFluid(2000))
                .fluidOutputs(DebrominatedWater.getFluid(2000))
                .EUt(480)
                .duration(180)
                .buildAndRegister();

        // H2SO4Br(H2O)Cl2 + H2O -> H2SO4Br(H2O)2Cl2
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(SulfuricBromineSolution.getFluid(2000))
                .fluidInputs(Steam.getFluid(1000))
                .fluidOutputs(HotVapourMixture.getFluid(3000))
                .EUt(480)
                .duration(150)
                .buildAndRegister();

        // H2SO4Br(H2O)2Cl2 -> H2SO4 + H2O + 2Cl + Br(H2O)
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(HotVapourMixture.getFluid(3000))
                .fluidOutputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(DebrominatedWater.getFluid(1000))
                .fluidOutputs(Chlorine.getFluid(2000))
                .fluidOutputs(DampBromine.getFluid(1000))
                .EUt(480)
                .duration(180)
                .buildAndRegister();

        // Br(H2O) -> Br
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(DampBromine.getFluid(1000))
                .fluidOutputs(Bromine.getFluid(1000))
                .EUt(480)
                .duration(400)
                .buildAndRegister();

    }
}
