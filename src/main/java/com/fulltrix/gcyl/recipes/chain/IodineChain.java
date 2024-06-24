package com.fulltrix.gcyl.recipes.chain;

import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class IodineChain {
    public static void init() {

        // NaNO3·KNO3·KCl·NaIO3 + H2O = [NaNO3·KNO3·KCl·NaIO3 + H2O]
        MIXER_RECIPES.recipeBuilder().duration(30).EUt(7680)
                .input(dust, Caliche)
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(CalicheIodateBrine.getFluid(1000))
                .buildAndRegister();

        // [NaNO3·KNO3·KCl·NaIO3 + H2O] + 3H2O + 3SO2 = [NaNO3·KNO3·KCl·NaI + H2O] + 3H2SO4
        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(120)
                .fluidInputs(CalicheIodateBrine.getFluid(1000))
                .fluidInputs(SulfurDioxide.getFluid(3000))
                .fluidInputs(Water.getFluid(3000))
                .fluidOutputs(IodideSolution.getFluid(1000))
                .fluidOutputs(SulfuricAcid.getFluid(3000))
                .buildAndRegister();

        // 5[NaNO3·KNO3·KCl·NaI] + [NaNO3·KNO3·KCl·NaIO3 + H2O] -> 6[NaNO3·KNO3·KCl·NaOH + H2O + I]
        MIXER_RECIPES.recipeBuilder().duration(140).EUt(120)
                .fluidInputs(CalicheIodateBrine.getFluid(1000))
                .fluidInputs(IodideSolution.getFluid(5000))
                .fluidOutputs(CalicheIodineBrine.getFluid(6000))
                .buildAndRegister();

        // [NaNO3·KNO3·KCl·NaOH + H2O + I] +  Kerosene ->  [NaNO3·KNO3·KCl·NaOH + H2O] + [Kerosene + I]
        CHEMICAL_RECIPES.recipeBuilder().duration(90).EUt(480)
                .fluidInputs(CalicheIodineBrine.getFluid(1000))
                .fluidInputs(Kerosene.getFluid(1000))
                .fluidOutputs(CalicheNitrateSolution.getFluid(1000))
                .fluidOutputs(KeroseneIodineSolution.getFluid(1000))
                .buildAndRegister();

        // [Kerosene + I] = Kerosene + I
        CENTRIFUGE_RECIPES.recipeBuilder().duration(140).EUt(120)
                .fluidInputs(KeroseneIodineSolution.getFluid(1000))
                .fluidOutputs(Kerosene.getFluid(1000))
                .output(dust, Iodine)
                .buildAndRegister();

        // [NaNO3·KNO3·KCl·NaOH + H2O] = NaNO3 + KNO3 + KCl + NaOH + H2O (Water voided - Dehydrator)
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(120).EUt(30)
                .fluidInputs(CalicheNitrateSolution.getFluid(1000))
                .output(dust, SodiumNitrate, 5)
                .output(dust, Niter, 5)
                .output(dust, RockSalt, 2)
                .output(dust, SodiumHydroxide, 3)
                .buildAndRegister();

        // 2[I + Oil] = Oil + I?
        CENTRIFUGE_RECIPES.recipeBuilder().duration(160).EUt(30)
                .fluidInputs(IodizedOil.getFluid(2000))
                .fluidOutputs(Oil.getFluid(1000))
                .fluidOutputs(IodizedBrine.getFluid(1000))
                .buildAndRegister();

        // I? + 0.3Cl = 1.3I??
        MIXER_RECIPES.recipeBuilder().duration(240).EUt(7680)
                .fluidInputs(IodizedBrine.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(300))
                .fluidOutputs(IodineBrineMix.getFluid(1300))
                .buildAndRegister();

        // 1.3I?? = Br? + 0.3I?
        CENTRIFUGE_RECIPES.recipeBuilder().duration(150).EUt(7680)
                .fluidInputs(IodineBrineMix.getFluid(1300))
                .fluidOutputs(BrominatedBrine.getFluid(1000))
                .fluidOutputs(IodineSlurry.getFluid(300))
                .buildAndRegister();

        // I? = I
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(200).EUt(7680)
                .fluidInputs(IodineSlurry.getFluid(1000))
                .output(dust, Iodine)
                .buildAndRegister();
    }
}
