package com.fulltrix.gcyl.recipes.chain;

import gregtech.api.recipes.ingredients.IntCircuitIngredient;

import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static com.fulltrix.gcyl.materials.GCYLNuclearMaterials.*;
import static com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps.*;
import static gregicality.multiblocks.api.unification.GCYMMaterials.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.FUSION_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.Materials.Plutonium;
import static gregtech.api.unification.material.Materials.Vanadium;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.SHAPE_MOLD_INGOT;

public class FusionElementsChain {
    public static void init() {

        advFusionRecipes();
        fusionRecipes();

        // Li + 2H2O -> H + LiOH(H2O)
        CHEMICAL_RECIPES.recipeBuilder().duration(140).EUt(120)
                .input(dust, Lithium)
                .fluidInputs(Water.getFluid(2000))
                .notConsumable(new IntCircuitIngredient(1))
                .fluidOutputs(Hydrogen.getFluid(1000))
                .fluidOutputs(LithiumHydroxideSolution.getFluid(1000))
                .buildAndRegister();

        // 2LiOH(H2O) + H2O2 -> Li2O2(H2O) + 3H2O (H2O lost to Dehydrator)
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(180).EUt(120)
                .fluidInputs(LithiumHydroxideSolution.getFluid(2000))
                .fluidInputs(HydrogenPeroxide.getFluid(1000))
                .fluidOutputs(LithiumPeroxideSolution.getFluid(1000))
                .buildAndRegister();

        // CO + Li2O2(H2O) -> Li2CO3(H2O)
        MIXER_RECIPES.recipeBuilder().duration(240).EUt(30)
                .fluidInputs(CarbonMonoxide.getFluid(1000))
                .fluidInputs(LithiumPeroxideSolution.getFluid(1000))
                .fluidOutputs(LithiumCarbonateSolution.getFluid(1000))
                .buildAndRegister();

        // 6O -> 2O3
        ELECTROLYZER_RECIPES.recipeBuilder().duration(120).EUt(480)
                .fluidInputs(Oxygen.getFluid(6000))
                .fluidOutputs(Ozone.getFluid(2000))
                .buildAndRegister();

        // 6NO2 + O3 -> 3N2O5
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(480)
                .fluidInputs(NitrogenDioxide.getFluid(6000))
                .fluidInputs(Ozone.getFluid(1000))
                .fluidOutputs(NitrogenPentoxide.getFluid(3000))
                .buildAndRegister();

        // 2N2O5 + TiCl4 + 2O -> 4Cl + Ti(NO3)4
        CHEMICAL_RECIPES.recipeBuilder().duration(230).EUt(480)
                .fluidInputs(NitrogenPentoxide.getFluid(2000))
                .fluidInputs(TitaniumTetrachloride.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(2000))
                .fluidOutputs(Chlorine.getFluid(4000))
                .output(dust,TitaniumNitrate,17)
                .buildAndRegister();

        // Ti(NO3)4 + 2NaOH + Li2CO3(H2O) -> 4HNO3 + Li2TiO3 + Na2CO3
        BLAST_RECIPES.recipeBuilder().duration(320).EUt(120).blastFurnaceTemp(3100)
                .input(dust,TitaniumNitrate,17)
                .input(dust, SodiumHydroxide, 6)
                .fluidInputs(LithiumCarbonateSolution.getFluid(1000))
                .fluidOutputs(NitricAcid.getFluid(4000))
                .output(ingot, LithiumTitanate, 6)
                .output(dust, SodaAsh, 6)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(340).EUt(1920)
                .fluidInputs(Carbon.getFluid(10000))
                .fluidOutputs(Carbon12.getFluid(9893))
                .fluidOutputs(Carbon13.getFluid(107))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(340).EUt(1920)
                .fluidInputs(Nitrogen.getFluid(100000))
                .fluidOutputs(Nitrogen14.getFluid(99636))
                .fluidOutputs(NItrogen15.getFluid(364))
                .buildAndRegister();

        ADVANCED_MIXER_RECIPES.recipeBuilder().duration(250).EUt(7680)
                .fluidInputs(Carbon12.getFluid(1000))
                .fluidInputs(Carbon13.getFluid(1000))
                .fluidInputs(Nitrogen14.getFluid(1000))
                .fluidInputs(NItrogen15.getFluid(1000))
                .fluidOutputs(CNOcatalyst.getFluid(4000))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(150).EUt(7680)
                .fluidInputs(HeliumCNO.getFluid(800))
                .fluidOutputs(Helium.getFluid(640))
                .fluidOutputs(CNOcatalyst.getFluid(160))
                .buildAndRegister();

        DECAY_CHAMBERS_RECIPES.recipeBuilder().duration(180).EUt(480)
                .input(dust, Radium)
                .fluidOutputs(Radon.getFluid(1000))
                .buildAndRegister();

        DECAY_CHAMBERS_RECIPES.recipeBuilder().duration(180).EUt(7680)
                .fluidInputs(Titanium44.getFluid(144))
                .fluidOutputs(Calcium44.getFluid(144))
                .buildAndRegister();

        DECAY_CHAMBERS_RECIPES.recipeBuilder().duration(180).EUt(7680)
                .fluidInputs(Chromium48.getFluid(144))
                .fluidOutputs(Titanium.getFluid(144))
                .buildAndRegister();

        DECAY_CHAMBERS_RECIPES.recipeBuilder().duration(180).EUt(7680)
                .fluidInputs(Iron52.getFluid(144))
                .fluidOutputs(Chrome.getFluid(144))
                .buildAndRegister();

        DECAY_CHAMBERS_RECIPES.recipeBuilder().duration(180).EUt(7680)
                .fluidInputs(Nickel56.getFluid(144))
                .fluidOutputs(Iron.getFluid(144))
                .buildAndRegister();

        DECAY_CHAMBERS_RECIPES.recipeBuilder().duration(160).EUt(122880)
                .fluidInputs(QuassifissioningPlasma.getPlasma(1000))
                .fluidOutputs(FlYbPlasma.getPlasma(1000))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(290).EUt(1920)
                .fluidInputs(FlYbPlasma.getPlasma(1000))
                .fluidOutputs(MetastableFlerovium.getFluid(288))
                .fluidOutputs(Ytterbium178.getFluid(288))
                .buildAndRegister();

        DECAY_CHAMBERS_RECIPES.recipeBuilder().duration(120).EUt(30720)
                .fluidInputs(Ytterbium178.getFluid(144))
                .fluidOutputs(Hafnium.getFluid(144))
                .buildAndRegister();

        // Ti + 4HF -> TiF4 + 4H
        CHEMICAL_RECIPES.recipeBuilder().duration(300).EUt(120)
                .input(dust, Titanium)
                .fluidInputs(HydrofluoricAcid.getFluid(4000))
                .fluidOutputs(TitaniumTetrafluoride.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(4000))
                .buildAndRegister();

        GAS_CENTRIFUGE_RECIPES.recipeBuilder().duration(210).EUt(30720)
                .fluidInputs(TitaniumTetrafluoride.getFluid(10000))
                .fluidOutputs(Titanium50Tetrafluoride.getFluid(518))
                .fluidOutputs(TitaniumTetrafluoride.getFluid(9482))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(340).EUt(120)
                .blastFurnaceTemp(Titanium.getBlastTemperature())
                .fluidInputs(Titanium50Tetrafluoride.getFluid(1000))
                .input(dust, Sodium, 4)
                .output(ingotHot, Titanium50)
                .output(dust, SodiumFluoride, 8)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(120).EUt(7680)
                .fluidInputs(Titanium50.getFluid(288))
                .fluidInputs(Californium252.getFluid(288))
                .fluidOutputs(OgannesonBreedingBase.getFluid(2000))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(100).EUt(600000)
                .fluidInputs(Curium250.getFluid(36 * 4))
                .fluidInputs(OgannesonBreedingBase.getFluid(125 * 4))
                .fluidOutputs(HotMetastableOganesson.getFluid(125 * 4))
                .AdvCoilTier(2).EUToStart(2500000000L).EUReturn(50)
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder().duration(3800).EUt(120)
                .notConsumable(SHAPE_MOLD_INGOT)
                .fluidInputs(HotMetastableOganesson.getFluid(125))
                .output(ingotHot, MetastableOganesson)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(350).EUt(120).blastFurnaceTemp(3000)
                .input(dust, Hafnium)
                .input(dust, Graphite)
                .output(dust,HafniumCarbide,2)
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(260).EUt(120).blastFurnaceTemp(2500)
                .input(dust, Tantalum)
                .input(dust, Graphite)
                .output(dust,TantalumCarbide,2)
                .buildAndRegister();

        // NaSgO3 + Cl + 4C -> SgC + NaCl + 3CO
        BLAST_RECIPES.recipeBuilder().duration(280).EUt(120).blastFurnaceTemp(3000)
                .input(dust,SodiumSeaborgate,5)
                .fluidInputs(Chlorine.getFluid(1000))
                .input(dust, Carbon, 4)
                .output(dust,SeaborgiumCarbide,2)
                .output(dust, Salt, 2)
                .fluidOutputs(CarbonMonoxide.getFluid(3000))
                .buildAndRegister();

        // 12TaC + 3HfC + SgC -> Ta12Hf3SgC16
        BLAST_RECIPES.recipeBuilder().duration(340).EUt(120).blastFurnaceTemp(6200)
                .input(dust,TantalumCarbide,24)
                .input(dust,HafniumCarbide,6)
                .input(dust,SeaborgiumCarbide,2)
                .output(ingotHot, TantalumHafniumSeaborgiumCarbide, 32)
                .buildAndRegister();
    }

    private static void advFusionRecipes() {

        //TODO: Diversify these recipes as the only incentive for higher fusion tiers is overclocking

        // Alpha Process
        ADV_FUSION_RECIPES.recipeBuilder().duration(384).EUt(7000).EUToStart(1200000000).AdvCoilTier(1).EUReturn(100)
                .fluidInputs(Helium.getFluid(375 * 2))
                .fluidInputs(Helium.getFluid(375 * 2))
                .fluidOutputs(Carbon.getPlasma(250 * 2))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(128).EUt(7000).EUToStart(1280000000).AdvCoilTier(1).EUReturn(100)
                .fluidInputs(Carbon.getFluid(125 * 2))
                .fluidInputs(Helium.getFluid(125 * 2))
                .fluidOutputs(Oxygen.getPlasma(125 * 2))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(128).EUt(7000).EUToStart(1280000000).AdvCoilTier(1).EUReturn(100)
                .fluidInputs(Oxygen.getFluid(125 * 2))
                .fluidInputs(Helium.getFluid(125 * 2))
                .fluidOutputs(Neon.getPlasma(125 * 2))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(128).EUt(7000).EUToStart(1280000000).AdvCoilTier(1).EUReturn(100)
                .fluidInputs(Neon.getFluid(125 * 2))
                .fluidInputs(Helium.getFluid(125 * 2))
                .fluidOutputs(Magnesium.getPlasma(125 * 2))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(128).EUt(7000).EUToStart(1280000000).AdvCoilTier(1).EUReturn(95)
                .fluidInputs(Magnesium.getFluid(125 * 2))
                .fluidInputs(Helium.getFluid(125 * 2))
                .fluidOutputs(Silicon.getPlasma(125 * 2))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(128).EUt(7000).EUToStart(1280000000).AdvCoilTier(1).EUReturn(95)
                .fluidInputs(Silicon.getFluid(125 * 2))
                .fluidInputs(Helium.getFluid(125 * 2))
                .fluidOutputs(Sulfur.getPlasma(125 * 2))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(128).EUt(7000).EUToStart(160000000L).AdvCoilTier(2).EUReturn(95)
                .fluidInputs(Sulfur.getFluid(125 * 2))
                .fluidInputs(Helium.getFluid(125 * 2))
                .fluidOutputs(Argon.getPlasma(125 * 2))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(128).EUt(7000).EUToStart(180000000L).AdvCoilTier(2).EUReturn(90)
                .fluidInputs(Argon.getFluid(125 * 2))
                .fluidInputs(Helium.getFluid(125 * 2))
                .fluidOutputs(Calcium.getPlasma(125 * 2))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(128).EUt(7000).EUToStart(200000000L).AdvCoilTier(2).EUReturn(90)
                .fluidInputs(Calcium.getFluid(125 * 2))
                .fluidInputs(Helium.getFluid(125 * 2))
                .fluidOutputs(Titanium44.getPlasma(125 * 2))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(128).EUt(7000).EUToStart(220000000L).AdvCoilTier(2).EUReturn(90)
                .fluidInputs(Titanium44.getFluid(125 * 2))
                .fluidInputs(Helium.getFluid(125 * 2))
                .fluidOutputs(Chromium48.getPlasma(125 * 2))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(128).EUt(7000).EUToStart(230000000L).AdvCoilTier(2).EUReturn(85)
                .fluidInputs(Calcium44.getFluid(125 * 2))
                .fluidInputs(Helium.getFluid(125 * 2))
                .fluidOutputs(Titanium.getPlasma(125 * 2))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(128).EUt(7000).EUToStart(240000000L).AdvCoilTier(2).EUReturn(85)
                .fluidInputs(Chromium48.getFluid(125 * 2))
                .fluidInputs(Helium.getFluid(125 * 2))
                .fluidOutputs(Iron52.getPlasma(125 * 2))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(128).EUt(7000).EUToStart(250000000L).AdvCoilTier(2).EUReturn(80)
                .fluidInputs(Iron52.getFluid(125 * 2))
                .fluidInputs(Helium.getFluid(125 * 2))
                .fluidOutputs(Nickel56.getPlasma(125 * 2))
                .buildAndRegister();

        // CNO Cycle
        ADV_FUSION_RECIPES.recipeBuilder().duration(128).EUt(8192).EUToStart(100000000).AdvCoilTier(1)
                .fluidInputs(CNOcatalyst.getFluid(160 * 2))
                .fluidInputs(Hydrogen.getFluid(640 * 2))
                .fluidOutputs(HeliumCNO.getPlasma(800 * 2))
                .buildAndRegister();

        // Flerovium Production
        ADV_FUSION_RECIPES.recipeBuilder().duration(75).EUt(600000).AdvCoilTier(2).EUToStart(2500000000L).EUReturn(50)
                .fluidInputs(Uranium.getFluid(125 * 4))
                .fluidInputs(Uranium.getFluid(125 * 4))
                .fluidOutputs(QuassifissioningPlasma.getPlasma(125 * 4))
                .buildAndRegister();

        // Carbon-Burning Process
        ADV_FUSION_RECIPES.recipeBuilder().duration(100).EUt(600000).AdvCoilTier(2).EUToStart(2500000000L).EUReturn(46)
                .fluidInputs(Carbon12.getFluid(125 * 2))
                .fluidInputs(Carbon12.getFluid(125 * 2))
                .fluidOutputs(Neon.getPlasma(125 * 2))
                .fluidOutputs(Helium4.getFluid(125 * 2))
                .buildAndRegister();

        // Other Advanced Fusion Materials
        ADV_FUSION_RECIPES.recipeBuilder().duration(50).EUt(1000000).EUToStart(1000000000).AdvCoilTier(1).EUReturn(40)
                .fluidInputs(Curium.getFluid(144 * 2))
                .fluidInputs(Sodium.getFluid(144 * 2))
                .fluidOutputs(Bohrium.getFluid(288 * 2))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(100).EUt(2000000).AdvCoilTier(1).EUToStart(1000000000).EUReturn(40)
                .fluidInputs(Trinium.getFluid(144 * 2))
                .fluidInputs(Tritanium.getFluid(144 * 2))
                .fluidOutputs(Adamantium.getFluid(288 * 2))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(100).EUt(8000000).AdvCoilTier(2).EUToStart(2500000000L).EUReturn(40)
                .fluidInputs(Adamantium.getFluid(144 * 2))
                .fluidInputs(Seaborgium.getFluid(144 * 2))
                .fluidOutputs(Vibranium.getFluid(288 * 2))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(100).EUt(900000).AdvCoilTier(2).EUToStart(2500000000L).EUReturn(50)
                .fluidInputs(ScandiumTitanium50Mix.getFluid(36 * 4))
                .fluidInputs(RadonRadiumMix.getFluid(144 * 4))
                .fluidOutputs(MetastableHassium.getFluid(144 * 4))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(64).EUt(32768).AdvCoilTier(1).EUToStart(640000000L).EUReturn(100)
                .fluidInputs(Deuterium.getFluid(1000))
                .fluidInputs(Tritium.getFluid(1000))
                .fluidOutputs(Helium.getPlasma(1000))
                .buildAndRegister();

        ADV_FUSION_RECIPES.recipeBuilder().duration(10).EUt(16000000).AdvCoilTier(5).EUToStart(20000000000L).EUReturn(75)
                .fluidInputs(SuperheavyMix.getFluid(1000))
                .fluidInputs(Taranium.getFluid(144))
                .fluidOutputs(NeutronPlasma.getPlasma(8000))
                .buildAndRegister();
    }

    public static void fusionRecipes() {
        // Fusion Recipes
        FUSION_RECIPES.recipeBuilder().duration(16).EUt(4096).EUToStart(40000000)
                .fluidInputs(Deuterium.getFluid(125))
                .fluidInputs(Tritium.getFluid(125))
                .fluidOutputs(Helium.getPlasma(125))
                .buildAndRegister();

        FUSION_RECIPES.recipeBuilder().duration(16).EUt(2048).EUToStart(60000000)
                .fluidInputs(Deuterium.getFluid(125))
                .fluidInputs(Helium3.getFluid(125))
                .fluidOutputs(Helium.getPlasma(125))
                .buildAndRegister();

        FUSION_RECIPES.recipeBuilder().duration(16).EUt(2048).EUToStart(60000000)
                .fluidInputs(Fluorine.getFluid(125))
                .fluidInputs(Helium3.getFluid(125))
                .fluidOutputs(Potassium.getPlasma(125))
                .buildAndRegister();

        FUSION_RECIPES.recipeBuilder().duration(32).EUt(4096).EUToStart(80000000)
                .fluidInputs(Carbon.getFluid(125))
                .fluidInputs(Helium3.getFluid(125))
                .fluidOutputs(Oxygen.getPlasma(125))
                .buildAndRegister();

        FUSION_RECIPES.recipeBuilder().duration(16).EUt(16384).EUToStart(180000000)
                .fluidInputs(Beryllium.getFluid(16))
                .fluidInputs(Deuterium.getFluid(375))
                .fluidOutputs(Nitrogen.getPlasma(175))
                .buildAndRegister();

        FUSION_RECIPES.recipeBuilder().duration(32).EUt(8192).EUToStart(360000000)
                .fluidInputs(Silicon.getFluid(16))
                .fluidInputs(Magnesium.getFluid(16))
                .fluidOutputs(Iron.getPlasma(125))
                .buildAndRegister();

        FUSION_RECIPES.recipeBuilder().duration(16).EUt(32768).EUToStart(480000000)
                .fluidInputs(Potassium.getFluid(16))
                .fluidInputs(Fluorine.getFluid(125))
                .fluidOutputs(Nickel.getPlasma(125))
                .buildAndRegister();

        FUSION_RECIPES.recipeBuilder().duration(32).EUt(32768).EUToStart(150000000)
                .fluidInputs(Beryllium.getFluid(16))
                .fluidInputs(Tungsten.getFluid(16))
                .fluidOutputs(Platinum.getFluid(16))
                .buildAndRegister();

        FUSION_RECIPES.recipeBuilder().duration(64).EUt(24576).EUToStart(150000000)
                .fluidInputs(Neodymium.getFluid(16))
                .fluidInputs(Hydrogen.getFluid(48))
                .fluidOutputs(Europium.getFluid(16))
                .buildAndRegister();

        FUSION_RECIPES.recipeBuilder().duration(96).EUt(49152).EUToStart(200000000)
                .fluidInputs(Lutetium.getFluid(16))
                .fluidInputs(Chrome.getFluid(16))
                .fluidOutputs(Americium.getFluid(16))
                .buildAndRegister();

        FUSION_RECIPES.recipeBuilder().duration(64).EUt(32768).EUToStart(300000000)
                .fluidInputs(Plutonium.getFluid(16))
                .fluidInputs(Thorium.getFluid(16))
                .fluidOutputs(Naquadah.getFluid(16))
                .buildAndRegister();

        FUSION_RECIPES.recipeBuilder().duration(64).EUt(24578).EUToStart(150000000)
                .fluidInputs(Tungsten.getFluid(16))
                .fluidInputs(Helium.getFluid(16))
                .fluidOutputs(Osmium.getFluid(16))
                .buildAndRegister();

        FUSION_RECIPES.recipeBuilder().duration(64).EUt(8192).EUToStart(120000000)
                .fluidInputs(Manganese.getFluid(16))
                .fluidInputs(Hydrogen.getFluid(16))
                .fluidOutputs(Iron.getFluid(16))
                .buildAndRegister();

        FUSION_RECIPES.recipeBuilder().duration(64).EUt(49152).EUToStart(240000000)
                .fluidInputs(Mercury.getFluid(16))
                .fluidInputs(Magnesium.getFluid(16))
                .fluidOutputs(Uranium.getFluid(16))
                .buildAndRegister();

        FUSION_RECIPES.recipeBuilder().duration(64).EUt(49152).EUToStart(240000000)
                .fluidInputs(Gold.getFluid(16))
                .fluidInputs(Aluminium.getFluid(16))
                .fluidOutputs(Uranium.getFluid(16))
                .buildAndRegister();

        FUSION_RECIPES.recipeBuilder().duration(128).EUt(8192).EUToStart(120000000)
                .fluidInputs(Uranium.getFluid(16))
                .fluidInputs(Helium.getFluid(16))
                .fluidOutputs(Plutonium.getFluid(16))
                .buildAndRegister();

        FUSION_RECIPES.recipeBuilder().duration(128).EUt(49152).EUToStart(480000000)
                .fluidInputs(Nickel.getFluid(288 * 2))
                .fluidInputs(Polonium.getFluid(288 * 2))
                .fluidOutputs(Copernicium.getFluid(288 * 2))
                .buildAndRegister();

        FUSION_RECIPES.recipeBuilder().duration(64).EUt(24576).EUToStart(140000000)
                .fluidInputs(Vanadium.getFluid(16))
                .fluidInputs(Hydrogen.getFluid(125))
                .fluidOutputs(Chrome.getFluid(16))
                .buildAndRegister();

        FUSION_RECIPES.recipeBuilder().duration(64).EUt(16384).EUToStart(180000000)
                .fluidInputs(Gallium.getFluid(16))
                .fluidInputs(Radon.getFluid(125))
                .fluidOutputs(Duranium.getFluid(16))
                .buildAndRegister();

        FUSION_RECIPES.recipeBuilder().duration(64).EUt(32768).EUToStart(200000000)
                .fluidInputs(Titanium.getFluid(48))
                .fluidInputs(Duranium.getFluid(32))
                .fluidOutputs(Tritanium.getFluid(16))
                .buildAndRegister();

        FUSION_RECIPES.recipeBuilder().duration(64).EUt(32768).EUToStart(200000000)
                .fluidInputs(Gold.getFluid(16))
                .fluidInputs(Mercury.getFluid(16))
                .fluidOutputs(Radon.getPlasma(125))
                .buildAndRegister();

        FUSION_RECIPES.recipeBuilder().duration(16).EUt(24576).EUToStart(200000000)
                .fluidInputs(Tantalum.getFluid(16))
                .fluidInputs(Tritium.getFluid(16))
                .fluidOutputs(Tungsten.getFluid(16))
                .buildAndRegister();

        FUSION_RECIPES.recipeBuilder().duration(32).EUt(24576).EUToStart(380000000)
                .fluidInputs(Silver.getFluid(16))
                .fluidInputs(Lithium.getFluid(16))
                .fluidOutputs(Indium.getFluid(16))
                .buildAndRegister();

        FUSION_RECIPES.recipeBuilder().duration(64).EUt(49152).EUToStart(400000000)
                .fluidInputs(NaquadahEnriched.getFluid(15))
                .fluidInputs(Radon.getFluid(125))
                .fluidOutputs(Naquadria.getFluid(3))
                .buildAndRegister();

        FUSION_RECIPES.recipeBuilder().duration(16).EUt(8192).EUToStart(80000000)
                .fluidInputs(Lanthanum.getFluid(144 * 2))
                .fluidInputs(Silicon.getFluid(144 * 2))
                .fluidOutputs(Lutetium.getFluid(144 * 2))
                .buildAndRegister();

        // Should technically be Pu242
        FUSION_RECIPES.recipeBuilder().duration(64).EUt(24576).EUToStart(150000000)
                .fluidInputs(Plutonium244.getFluid(16))
                .fluidInputs(Neon.getFluid(16))
                .fluidOutputs(Rutherfordium.getFluid(16))
                .buildAndRegister();

        FUSION_RECIPES.recipeBuilder().duration(96).EUt(49152).EUToStart(200000000)
                .fluidInputs(Americium.getFluid(16))
                .fluidInputs(Neon.getFluid(16))
                .fluidOutputs(Dubnium.getFluid(16))
                .buildAndRegister();

        FUSION_RECIPES.recipeBuilder().duration(96).EUt(49152).EUToStart(200000000)
                .fluidInputs(Americium243.getFluid(16))
                .fluidInputs(Neon.getFluid(16))
                .fluidOutputs(Dubnium.getFluid(16))
                .buildAndRegister();

        FUSION_RECIPES.recipeBuilder().duration(100).EUt(75000).EUToStart(400000000)
                .fluidInputs(Plutonium.getFluid(144))
                .fluidInputs(Calcium.getFluid(144))
                .fluidOutputs(Seaborgium.getFluid(288))
                .buildAndRegister();

        // Carbon-Burning Process (lossy)
        FUSION_RECIPES.recipeBuilder().duration(100).EUt(7680).EUToStart(120000000)
                .fluidInputs(Carbon12.getFluid(125))
                .fluidInputs(Carbon12.getFluid(125))
                .fluidOutputs(Neon.getPlasma(125))
                .buildAndRegister();

        FUSION_RECIPES.recipeBuilder().duration(100).EUt(75000).EUToStart(400_000_000)
                .fluidInputs(Polonium.getFluid(144 * 2))
                .fluidInputs(Chrome.getFluid(144 * 2))
                .fluidOutputs(Meitnerium.getFluid(288 * 2))
                .buildAndRegister();

        FUSION_RECIPES.recipeBuilder().duration(100).EUt(75000).EUToStart(420_000_000)
                .fluidInputs(Americium.getFluid(144 * 2))
                .fluidInputs(Titanium.getFluid(144 * 2))
                .fluidOutputs(Tennessine.getFluid(288 * 2))
                .buildAndRegister();

        FUSION_RECIPES.recipeBuilder().duration(100).EUt(75000).EUToStart(360_000_000)
                .fluidInputs(Plutonium.getFluid(144 * 2))
                .fluidInputs(Titanium.getFluid(144 * 2))
                .fluidOutputs(Livermorium.getFluid(288 * 2))
                .buildAndRegister();

        FUSION_RECIPES.recipeBuilder().duration(100).EUt(75000).EUToStart(380_000_000)
                .fluidInputs(Neptunium.getFluid(144 * 2))
                .fluidInputs(Titanium.getFluid(144 * 2))
                .fluidOutputs(Moscovium.getFluid(288 * 2))
                .buildAndRegister();

        FUSION_RECIPES.recipeBuilder().duration(100).EUt(75000).EUToStart(410_000_000)
                .fluidInputs(Astatine.getFluid(144 * 2))
                .fluidInputs(Nickel.getFluid(144 * 2))
                .fluidOutputs(Nihonium.getFluid(288 * 2))
                .buildAndRegister();

        FUSION_RECIPES.recipeBuilder().duration(100).EUt(75000).EUToStart(460_000_000)
                .fluidInputs(Radium.getFluid(144 * 2))
                .fluidInputs(Vanadium.getFluid(144 * 2))
                .fluidOutputs(Roentgenium.getFluid(288 * 2))
                .buildAndRegister();
    }
}
