package com.fulltrix.gcyl.recipes.chain;

import gregtech.api.GTValues;
import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;

import static com.fulltrix.gcyl.GCYLMaterials.*;
import static com.fulltrix.gcyl.item.GCYLCoreItems.*;
import static com.fulltrix.gcyl.recipes.GCYLRecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustSmall;

public class TaraniumChain {
    public static void init() {
        MIXER_RECIPES.recipeBuilder()
                .input(dust, Stone, 24)
                .fluidInputs(HydrofluoricAcid.getFluid(6000))
                .fluidOutputs(DirtyHexafluorosilicicAcid.getFluid(3000))
                .duration(40)
                .EUt(100)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(DirtyHexafluorosilicicAcid.getFluid(3000))
                .fluidOutputs(DiluteHexafluorosilicicAcid.getFluid(3000))
                .output(dust, StoneResidueDust, 12)
                .duration(40)
                .EUt(100)
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(DiluteHexafluorosilicicAcid.getFluid(3000))
                .fluidOutputs(Water.getFluid(2000))
                .fluidOutputs(FluorosilicicAcid.getFluid(1000))
                .duration(160)
                .EUt(200)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, StoneResidueDust, 24)
                .fluidInputs(SodiumHydroxideSolution.getFluid(1000))
                .output(dust, UncommonResidues, 1)
                .output(dustSmall, Magnetite)
                .fluidOutputs(SodiumHydroxideSolution.getFluid(925))
                .fluidOutputs(RedMud.getFluid(75))
                .duration(40)
                .EUt(100)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Oxygen.getFluid(FluidStorageKeys.LIQUID, 2000))
                .fluidInputs(Fluorine.getFluid(FluidStorageKeys.LIQUID, 2000))
                .notConsumable(MICROFOCUS_X_RAY_TUBE.getStackForm())
                .fluidOutputs(Dioxygendifluoride.getFluid(1000))
                .duration(80)
                .EUt(200)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, UncommonResidues, 1)
                .fluidInputs(Dioxygendifluoride.getFluid(1000))
                .output(dust, PartiallyOxidizedResidues, 1)
                .duration(80)
                .EUt(100)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(DistilledWater.getFluid(10000))
                .input(dust, PartiallyOxidizedResidues, 10)
                .fluidOutputs(OxidizedResidualSolution.getFluid(10000))
                .output(dust, InertResidues, 1)
                .duration(200)
                .EUt(100)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .fluidInputs(OxidizedResidualSolution.getFluid(2000))
                .output(dust, OxidizedResidues, 1)
                .output(dust, HeavyOxidizedResidues, 1)
                .duration(80)
                .EUt(3000)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder()
                .input(dust, OxidizedResidues, 10)
                .fluidInputs(Hydrogen.getFluid(60000))
                .output(dust, MetallicResidues, 1)
                .fluidOutputs(DiluteHydrofluoricAcid.getFluid(40000))
                .duration(1600)
                .EUt(2000)
                .blastFurnaceTemp(3500)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder()
                .input(dust, HeavyOxidizedResidues, 10)
                .fluidInputs(Hydrogen.getFluid(60000))
                .output(dust, HeavyMetallicResidues, 1)
                .fluidOutputs(DiluteHydrofluoricAcid.getFluid(40000))
                .duration(1600)
                .EUt(2000)
                .blastFurnaceTemp(3500)
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(DiluteHydrofluoricAcid.getFluid(2000))
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(HydrofluoricAcid.getFluid(1000))
                .duration(80)
                .EUt(200)
                .buildAndRegister();

        ADVANCED_CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, MetallicResidues, 10)
                .notConsumable(SEPARATION_ELECTROMAGNET)
                .output(dust, DiamagneticResidues, 3)
                .output(dust, ParamagneticResidues, 3)
                .output(dust, FerromagneticResidues, 3)
                .output(dust, UncommonResidues, 1)
                .duration(80)
                .EUt(8000)
                .buildAndRegister();

        ADVANCED_CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, HeavyMetallicResidues, 10)
                .notConsumable(SEPARATION_ELECTROMAGNET)
                .output(dust, HeavyDiamagneticResidues, 3)
                .output(dust, HeavyParamagneticResidues, 3)
                .output(dust, HeavyFerromagneticResidues, 3)
                .output(dust, ExoticHeavyResidues, 1)
                .duration(80)
                .EUt(8000)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, FerromagneticResidues, 6)
                .output(dustSmall, Iron)
                .output(dustSmall, Nickel)
                .output(dustSmall, Cobalt)
                .duration(100)
                .EUt(3000)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, DiamagneticResidues, 6)
                .output(dustSmall, Calcium)
                .output(dustSmall, Zinc)
                .output(dustSmall, Copper)
                .output(dustSmall, Gallium)
                .output(dustSmall, Beryllium)
                .output(dustSmall, Tin)
                .duration(100)
                .EUt(3000)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, ParamagneticResidues, 6)
                .output(dustSmall, Sodium)
                .output(dustSmall, Potassium)
                .output(dustSmall, Magnesium)
                .output(dustSmall, Titanium)
                .output(dustSmall, Vanadium)
                .output(dustSmall, Manganese)
                .duration(100)
                .EUt(3000)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, HeavyParamagneticResidues, 6)
                .output(dustSmall, Thorium)
                .output(dustSmall, Uranium)
                .output(dustSmall, Tungsten)
                .output(dustSmall, Hafnium)
                .output(dustSmall, Tantalum)
                .output(dustSmall, Thallium)
                .duration(120)
                .EUt(3000)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, HeavyDiamagneticResidues, 6)
                .output(dustSmall, Lead)
                .output(dustSmall, Cadmium)
                .output(dustSmall, Indium)
                .output(dustSmall, Gold)
                .output(dustSmall, Bismuth)
                .fluidOutputs(Mercury.getFluid(36))
                .duration(120)
                .EUt(3000)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, HeavyFerromagneticResidues, 6)
                .output(dustSmall, Dysprosium)
                .duration(120)
                .EUt(3000)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(DistilledWater.getFluid(2000))
                .input(dust, ExoticHeavyResidues, 16)
                .input(dust, SodiumHydroxide, 3)
                .inputs(PROTONATED_FULLERENE_SIEVING_MATRIX.getStackForm())
                .fluidOutputs(SodiumHydroxideSolution.getFluid(1000))
                .outputs(SATURATED_FULLERENE_SIEVING_MATRIX.getStackForm())
                .duration(40)
                .EUt(2000000)
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, InertResidues, 10)
                .notConsumable(FluoroantimonicAcid.getFluid(0))
                .output(dust, CleanInertResidues, 10)
                .output(dust, NaquadricCompound)
                .duration(320)
                .EUt(200)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Tritium.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(1000))
                .fluidOutputs(TritiumHydride.getFluid(1000))
                .duration(160)
                .EUt(2000)
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder()
                .fluidInputs(TritiumHydride.getFluid(10000))
                .fluidOutputs(Helium3Hydride.getFluid(100))
                .fluidOutputs(TritiumHydride.getFluid(9900))
                .duration(800)
                .EUt(200)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .input(dust, CleanInertResidues, 1)
                .fluidInputs(Helium3Hydride.getFluid(1000))
                .fluidOutputs(UltraacidicResidueSolution.getFluid(1000))
                .duration(160)
                .EUt(2000)
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(UltraacidicResidueSolution.getFluid(2000))
                .fluidInputs(Oxygen.getFluid(FluidStorageKeys.LIQUID, 4000))
                .fluidInputs(Xenon.getFluid(FluidStorageKeys.LIQUID, 1000))
                .fluidOutputs(XenicAcid.getFluid(1000))
                .fluidOutputs(DustyLiquidHelium3.getFluid(2000))
                .duration(120)
                .EUt(2000)
                .buildAndRegister();

        // 2 H2XeO4 -> 2 Xe + H2O + H2O2 + O3 + 2 O
        ELECTROLYZER_RECIPES.recipeBuilder()
                .fluidInputs(XenicAcid.getFluid(2000))
                .fluidOutputs(Xenon.getFluid(2000))
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(Ozone.getFluid(1000))
                .fluidOutputs(HydrogenPeroxide.getFluid(1000))
                .fluidOutputs(Oxygen.getFluid(2000))
                .duration(120)
                .EUt(500)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(DustyLiquidHelium3.getFluid(1000))
                .fluidOutputs(TaraniumEnrichedLHelium3.getFluid(100))
                .fluidOutputs(TaraniumSemidepletedLHelium3.getFluid(300))
                .fluidOutputs(TaraniumDepletedLHelium3.getFluid(600))
                .duration(400)
                .EUt(3000)
                .buildAndRegister();

        FUSION_RECIPES.recipeBuilder()
                .fluidInputs(TaraniumEnrichedLHelium3.getFluid(1000))
                .fluidInputs(Helium3.getFluid(1000))
                .fluidOutputs(TaraniumRichDustyHeliumPlasma.getPlasma(3000))
                .duration(160)
                .EUt(7680)
                .EUToStart(480000000)
                .buildAndRegister();

        ADVANCED_CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(TaraniumRichDustyHeliumPlasma.getPlasma(3000))
                .notConsumable(SEPARATION_ELECTROMAGNET.getStackForm())
                .fluidOutputs(TaraniumRichHelium4.getPlasma(500))
                .fluidOutputs(Hydrogen.getPlasma(2000))
                .fluidOutputs(TaraniumDepletedHeliumPlasma.getPlasma(500))
                .duration(80)
                .EUt(2000)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(Helium3.getPlasma(1000))
                .fluidInputs(TaraniumDepletedLHelium3.getFluid(1000))
                .fluidOutputs(TaraniumDepletedHeliumPlasma.getPlasma(2000))
                .duration(160)
                .EUt(2000)
                .buildAndRegister();

        ADVANCED_CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(TaraniumDepletedHeliumPlasma.getPlasma(10000))
                .notConsumable(SEPARATION_ELECTROMAGNET.getStackForm())
                .fluidOutputs(Helium3.getPlasma(5000))
                .output(dust, CleanInertResidues, 2)
                .duration(160)
                .EUt(2000)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(TaraniumSemidepletedLHelium3.getFluid(1000))
                .fluidOutputs(TaraniumEnrichedLHelium3.getFluid(100))
                .fluidOutputs(TaraniumDepletedLHelium3.getFluid(900))
                .duration(400)
                .EUt(3000)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(TaraniumRichHelium4.getFluid(400))
                .output(dust, Taranium, 4)
                .fluidOutputs(TaraniumPoorLiquidHelium.getFluid(400))
                .duration(20)
                .EUt(8000)
                .buildAndRegister();

        ADVANCED_CENTRIFUGE_RECIPES.recipeBuilder()
                .input(dust, Stone, 3)
                .chancedOutput(OreDictUnifier.get(dust, Taranium), 1000, 0)
                .chancedOutput(OreDictUnifier.get(dust, Taranium), 1000, 0)
                .chancedOutput(OreDictUnifier.get(dust, Taranium), 1000, 0)
                .duration(50)
                .EUt(GTValues.VA[GTValues.OpV])
                .buildAndRegister();


        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(TaraniumPoorLiquidHelium.getFluid(1000))
                .fluidInputs(Helium3.getFluid(FluidStorageKeys.LIQUID, 200))
                .fluidOutputs(TaraniumPoorLiquidHeliumMix.getFluid(1200))
                .duration(80)
                .EUt(8000)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(TaraniumPoorLiquidHeliumMix.getFluid(1200))
                .fluidOutputs(Helium.getFluid(FluidStorageKeys.LIQUID, 1000))
                .fluidOutputs(DustyLiquidHelium3.getFluid(200))
                .duration(80)
                .EUt(8000)
                .buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .fluidInputs(Helium3.getFluid(FluidStorageKeys.LIQUID, 100))
                .fluidInputs(TaraniumRichHelium4.getPlasma(1000))
                .fluidOutputs(TaraniumRichHelium4.getFluid(1000))
                .fluidOutputs(Helium.getFluid(100))
                .notConsumable(new IntCircuitIngredient(1))
                .duration(80)
                .EUt(8000)
                .buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder()
                .fluidInputs(Helium3.getFluid(FluidStorageKeys.LIQUID, 100))
                .fluidInputs(Hydrogen.getPlasma(1000))
                .fluidOutputs(Hydrogen.getFluid(1000))
                .fluidOutputs(Helium.getFluid(100))
                .notConsumable(new IntCircuitIngredient(1))
                .duration(80)
                .EUt(8000)
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder().duration(30).EUt(480)
                .fluidInputs(Fluorine.getFluid(1000))
                .fluidOutputs(Fluorine.getFluid(FluidStorageKeys.LIQUID, 1000))
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder().duration(30).EUt(480)
                .fluidInputs(Xenon.getFluid(1000))
                .fluidOutputs(Xenon.getFluid(FluidStorageKeys.LIQUID, 1000))
                .buildAndRegister();

        FLUID_HEATER_RECIPES.recipeBuilder()
                .fluidInputs(Helium3.getFluid(1000))
                .fluidOutputs(Helium3.getPlasma(1000))
                .circuitMeta(0)
                .duration(60)
                .EUt(8000)
                .buildAndRegister();

    }
}
