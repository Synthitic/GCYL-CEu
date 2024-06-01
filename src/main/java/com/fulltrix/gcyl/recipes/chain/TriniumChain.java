package com.fulltrix.gcyl.recipes.chain;

import gregtech.api.GTValues;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;

import static com.fulltrix.gcyl.GCYLMaterials.*;
import static com.fulltrix.gcyl.item.GCYLCoreItems.*;
import static com.fulltrix.gcyl.recipes.GCYLRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES;
import static com.fulltrix.gcyl.recipes.GCYLRecipeMaps.ADVANCED_CENTRIFUGE_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.ELECTROLYZER_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class TriniumChain {
    public static void init(){
        //Carborane

        //B(OH)3 + 3 C2H5OH + 4 NaH -> 3 C2H5ONa + NaBH4 + 3 H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .duration(120)
                .EUt(480)
                .input(dust,SodiumHydride,8)
                .fluidInputs(BoricAcid.getFluid(1000))
                .fluidInputs(Ethanol.getFluid(3000))
                .notConsumable(SulfuricAcid.getFluid(250))
                .output(dust,SodiumBorohydride,6)
                .output(dust,SodiumEthoxide,27)
                .fluidOutputs(Water.getFluid(3000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(150).EUt(125)
                .fluidInputs(Diethylether.getFluid(1000))
                .fluidInputs(BoronFluoride.getFluid(1000))
                .fluidOutputs(BoronTrifluorideEtherate.getFluid(1000))
                .buildAndRegister();

        //17 NaBH4 + 20 BF3·(CH2CH3)2O + 4 H2O2 + 2 HF -> 2 NaF + 2 H2O + 2 B10H14 + 2 B(OH)3 + 15 NaBF4 + 20 H2 + 20 (CH2CH3)2O
        //divided by two, and optimal choice as a gate
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(380).EUt(2000)
                .input(dust,SodiumBorohydride,51)
                .fluidInputs(HydrofluoricAcid.getFluid(1000))
                .fluidInputs(HydrogenPeroxide.getFluid(2000))
                .fluidInputs(BoronTrifluorideEtherate.getFluid(10000))
                .output(dust,Decaborane,24)
                .output(dust, SodiumFluoride, 2)
                .output(dust,SodiumTetrafluoroborate,45)
                .fluidOutputs(Water.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(20000))
                .fluidOutputs(Diethylether.getFluid(10000))
                .buildAndRegister();

        //NaBF4 -> NaF + BF3
        CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(125)
                .input(dust,SodiumTetrafluoroborate,6)
                .notConsumable(new IntCircuitIngredient(0))
                .output(dust, SodiumFluoride, 2)
                .fluidOutputs(BoronFluoride.getFluid(1000))
                .buildAndRegister();

        //B10H14 + NaCN + CsOH + 2 HCl + 3 CH3OH -> CsB10H12CN(CH3)3Cl + 4 H2O + NaCl
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(500)
                .input(dust,Decaborane,24)
                .input(dust,CaesiumHydroxide,3)
                .fluidInputs(SodiumCyanide.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .fluidInputs(Methanol.getFluid(3000))
                .notConsumable(SulfuricAcid.getFluid(250))
                .output(dust,CesiumCarboranePrecusor,38)
                .output(dust, Salt, 2)
                .fluidOutputs(Water.getFluid(4000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(165).EUt(125)
                .fluidInputs(Dimethylsulfide.getFluid(2000))
                .fluidInputs(Diborane.getFluid(1000))
                .fluidOutputs(BoraneDimethylsulfide.getFluid(2000))
                .buildAndRegister();

        //CsB10H12CN(CH3)3Cl + NaH + BH3.(CH3)2S -> CsCB11H12 + N(CH3)3 + NaCl + H2S + 2 CH4
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(500)
                .input(dust,CesiumCarboranePrecusor,38)
                .input(dust,SodiumHydride,2)
                .fluidInputs(BoraneDimethylsulfide.getFluid(1000))
                .notConsumable(Tetrahydrofuran.getFluid(250))
                .output(dust,CesiumCarborane,25)
                .output(dust, Salt, 2)
                .fluidOutputs(Trimethylamine.getFluid(1000))
                .fluidOutputs(HydrogenSulfide.getFluid(1000))
                .fluidOutputs(Methane.getFluid(2000))
                .buildAndRegister();

        //2 CsCB11H12 + 2 AgNO3 + 44F + 2I + HCl + (CH3)3SiH -> 2 H(CHB11F11) + 2CsNO3 + 2AgI + 22HF + (CH3)3SiCl
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(2000)
                .input(dust,CesiumCarborane,50)
                .input(dust,SilverNitrate,10)
                .input(dust, Iodine, 2)
                .fluidInputs(Fluorine.getFluid(44000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidInputs(Trimethylsilane.getFluid(1000))
                .output(dust,Fluorocarborane,50)
                .output(dust,CaesiumNitrate,10)
                .output(dust,SilverIodide,4)
                .fluidOutputs(HydrofluoricAcid.getFluid(22000))
                .fluidOutputs(Trimethylchlorosilane.getFluid(1000))
                .buildAndRegister();

        //Ag + 2 HNO3 -> AgNO3 + NO2 + H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(125)
                .input(dust, Silver)
                .fluidInputs(NitricAcid.getFluid(2000))
                .output(dust,SilverNitrate,5)
                .fluidOutputs(NitrogenDioxide.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        //Ag2O + 2 HNO3 -> 2 AgNO3 + H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(125)
                .input(dust, SilverOxide, 3)
                .fluidInputs(NitricAcid.getFluid(2000))
                .output(dust,SilverNitrate,10)
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(175).EUt(30)
                .input(dust,CaesiumNitrate,5)
                .output(dust, Caesium)
                .fluidOutputs(Nitrogen.getFluid(1000))
                .fluidOutputs(Oxygen.getFluid(3000))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(210).EUt(125).blastFurnaceTemp(1100)
                .input(dust,SilverIodide,4)
                .fluidInputs(Oxygen.getFluid(1000))
                .output(dust, SilverOxide, 3)
                .output(dust, Iodine, 2)
                .buildAndRegister();

        //Trinium Chain Proper

        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(125)
                .input(dust, SodiumNitrate, 5)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(FumingNitricAcid.getFluid(1000))
                .output(dust, SodiumBisulfate, 7)
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder().duration(180).EUt(125)
                .fluidInputs(FumingNitricAcid.getFluid(1000))
                .output(dust,PureCrystallineNitricAcid,5)
                .buildAndRegister();

        //2 Ke3Ac2Se4At4 + 8 HNO3 + 8 SO2 + NaClO4 ->  8 SeO2 + 8At + [NaCl + 4 H2O + 2 Ke3Ac2S4(NO3)4 + ?]
        BLAST_RECIPES.recipeBuilder().duration(265).EUt(130500).blastFurnaceTemp(9400)
                .input(dust, Triniite, 16)
                .input(dust,PureCrystallineNitricAcid,40)
                .input(dust,SodiumPerchlorate,6)
                .fluidInputs(SulfurDioxide.getFluid(8000))
                .output(dust, Astatine, 8)
                .output(dust,SeleniumOxide,24)
                .fluidOutputs(NitratedTriniiteSolution.getFluid(4000))
                .buildAndRegister();

        //1/2[NaCl + 4 H2O + 2 Ke3Ac2S4(NO3)4 + ?] + 12 NaOH -> 4 Na2S + Ke3Ac2(OH)12? + [0.5 NaCl + 4 NaNO3 + 2 H2O + ?]
        CHEMICAL_RECIPES.recipeBuilder().duration(190).EUt(500)
                .input(dust, SodiumHydroxide, 36)
                .fluidInputs(NitratedTriniiteSolution.getFluid(2000))
                .output(dust,ActiniumTriniumHydroxides,29)
                .output(dust, SodiumSulfide, 12)
                .fluidOutputs(ResidualTriniiteSolution.getFluid(2000))
                .buildAndRegister();

        //0.5 NaCl + 4 NaNO3 + 2 H2O + ? -> 0.5 NaCl + 4 NaNO3 + 0.75 Nq + 0.5 Nq + 0.444 Nq*
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(190).EUt(8100)
                .fluidInputs(ResidualTriniiteSolution.getFluid(2000))
                .output(dust, Salt)
                .output(dust, SodiumNitrate, 20)
                .output(dustSmall, NaquadriaticCompound, 3)
                .output(dustSmall, EnrichedNaquadricCompound, 2)
                .output(dustTiny, NaquadriaticCompound, 4)
                .buildAndRegister();

        // 6 KF + C6H6 + 6 Cl -> C6F6 + 6 KCl + 6 H
        CHEMICAL_RECIPES.recipeBuilder().duration(185).EUt(125)
                .input(dust, PotassiumFluoride, 12)
                .fluidInputs(Chlorine.getFluid(6000))
                .fluidInputs(Benzene.getFluid(1000))
                .notConsumable(dust, Rhenium)
                .fluidOutputs(Perfluorobenzene.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(6000))
                .output(dust, RockSalt, 12)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(350).EUt(32750)
                .input(wireFine, CarbonNanotubes,6)
                .input(dust,Fullerene,1)
                .input(dust,Fluorocarborane,50)
                .fluidInputs(Perfluorobenzene.getFluid(2000))
                .outputs(PROTONATED_FULLERENE_SIEVING_MATRIX.getStackForm())
                .buildAndRegister();

        //C65H70B55F55 + 6 Ra + 2 Ke3Ac2(OH)12? -> 2 Ra3Ac2(OH)12? + Ke6C65H70B55F55
        MIXER_RECIPES.recipeBuilder().duration(210).EUt(262000)
                .input(dust, Radium, 6)
                .input(dust,ActiniumTriniumHydroxides,58)
                .inputs(PROTONATED_FULLERENE_SIEVING_MATRIX.getStackForm())
                .fluidInputs(Water.getFluid(2000))
                .outputs(SATURATED_FULLERENE_SIEVING_MATRIX.getStackForm())
                .fluidOutputs(ActiniumRadiumHydroxideSolution.getFluid(2000))
                .buildAndRegister();

        //Ke6C65H70B55F55 + 13 H2SbF7 + 59 KrF2 -> 59 Kr + 13 SbF3 + 6 KeF4 + 32 C2H2 + CF4 + 55 BF3 + 32 HF
        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(131000)
                .inputs(SATURATED_FULLERENE_SIEVING_MATRIX.getStackForm())
                .fluidInputs(FluoroantimonicAcid.getFluid(8000))
                .fluidInputs(KryptonDifluoride.getFluid(16000))
                .output(dust,AntimonyTrifluoride,32)
                .output(dust, Fluorocarborane,50)
                .fluidOutputs(Krypton.getFluid(16000))
                .fluidOutputs(HeavilyFluorinatedTriniumSolution.getFluid(8000))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(350).EUt(32760)
                .fluidInputs(HeavilyFluorinatedTriniumSolution.getFluid(8000))
                .output(dust,TriniumTetrafluoride,60)
                .fluidOutputs(Fluorine.getFluid(16000))
                .fluidOutputs(Perfluorobenzene.getFluid(2000))
                .buildAndRegister();


        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30)
                .input(dust, Calcium)
                .fluidInputs(Fluorine.getFluid(2000))
                .output(dust, Fluorite, 3)
                .buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder().duration(40).EUt(30)
                .input(dust, Fluorite)
                .fluidOutputs(Fluorite.getFluid(GTValues.L))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(160).EUt(30)
                .input(dust, Calcium)
                .fluidInputs(Fluorite.getFluid(432))
                .fluidOutputs(MoltenCalciumSalts.getFluid(1000))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(200).EUt(2000)
                .input(dust,TriniumTetrafluoride,5)
                .fluidInputs(MoltenCalciumSalts.getFluid(1000))
                .output(dust, Trinium)
                .output(dust, Calcium, 2)
                .fluidOutputs(Fluorine.getFluid(6000))
                .buildAndRegister();

        //QoL to hook up the acetylene output into the fullerene chain

        //3 C2H2 -> C6H6
        FLUID_HEATER_RECIPES.recipeBuilder().duration(190).EUt(500)
                .fluidInputs(Acetylene.getFluid(3000))
                .notConsumable(ALUMINO_SILICATE_GLASS_TUBE.getStackForm())
                .fluidOutputs(Benzene.getFluid(1000))
                .buildAndRegister();

        //C6H6 + 2 C2H2 -> C10H8 + 2 H
        CHEMICAL_RECIPES.recipeBuilder().duration(230).EUt(500)
                .fluidInputs(Benzene.getFluid(1000))
                .fluidInputs(Acetylene.getFluid(2000))
                .notConsumable(UVA_HALIDE_LAMP.getStackForm())
                .fluidOutputs(Naphthalene.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(2000))
                .buildAndRegister();

        //Actinium Side

        //C2H2O + HCl -> C2H3OCl
        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(125)
                .fluidInputs(Ethenone.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(AcetylChloride.getFluid(1000))
                .buildAndRegister();

        //C2H5OH + C2H2O + 3 HF -> C4H5F3O2 + 6 H
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(230).EUt(500)
                .fluidInputs(Ethanol.getFluid(1000))
                .fluidInputs(AcetylChloride.getFluid(1000))
                .fluidInputs(HydrofluoricAcid.getFluid(3000))
                .fluidOutputs(EthylTrifluoroacetate.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(6000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(190).EUt(500).blastFurnaceTemp(900)
                .input(dust, Phosphorus, 4)
                .input(dust, Sulfur, 10)
                .output(dust,PhosphorousPentasulfide,14)
                .buildAndRegister();

        //P4S10 + 10 C4H6O2 + 10 C2H3OCl -> P4O10 + 10 C6H6SO + 10 [HCl + H2O]
        CHEMICAL_RECIPES.recipeBuilder().duration(210).EUt(500)
                .input(dust,PhosphorousPentasulfide,7)
                .fluidInputs(Succinaldehyde.getFluid(5000))
                .fluidInputs(AcetylChloride.getFluid(5000))
                .fluidOutputs(Acetothienone.getFluid(5000))
                .fluidOutputs(DilutedHydrochloricAcid.getFluid(10000))
                .output(dust, PhosphorusPentoxide, 7)
                .buildAndRegister();

        //C2H5ONa + C6H6SO + C4H5F3O2 + HCl -> NaCl + 2 C2H5OH + C8H5F3O2S
        CHEMICAL_RECIPES.recipeBuilder().duration(220).EUt(1000)
                .input(dust,SodiumEthoxide,9)
                .fluidInputs(EthylTrifluoroacetate.getFluid(1000))
                .fluidInputs(Acetothienone.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(TheonylTrifluoroacetate.getFluid(1000))
                .fluidOutputs(Ethanol.getFluid(2000))
                .output(dust, Salt, 2)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(290).EUt(4100)
                .fluidInputs(ActiniumRadiumHydroxideSolution.getFluid(1000))
                .fluidInputs(NitricAcid.getFluid(12000))
                .fluidOutputs(ActiniumRadiumNitrateSolution.getFluid(13000))
                .buildAndRegister();

        ADVANCED_CENTRIFUGE_RECIPES.recipeBuilder().duration(240).EUt(32700)
                .fluidInputs(ActiniumRadiumNitrateSolution.getFluid(13000))
                .notConsumable(TheonylTrifluoroacetate.getFluid(500))
                .output(dust,ActiniumNitrate,26)
                .output(dust,RadiumNitrate,27)
                .output(dustSmall, Thorium)
                .output(dustSmall, Protactinium)
                .output(dustSmall, Francium)
                .output(dustSmall, Radium)
                .fluidOutputs(Water.getFluid(13000))
                .buildAndRegister();

        //Ac(NO3)3 -> Ac + 3 N + 9 O
        ELECTROLYZER_RECIPES.recipeBuilder().duration(210).EUt(500)
                .input(dust,ActiniumNitrate,13)
                .output(dust, Actinium)
                .fluidOutputs(Nitrogen.getFluid(3000))
                .fluidOutputs(Oxygen.getFluid(9000))
                .buildAndRegister();

        //Ra(NO3)2 -> Ra + 2 N + 6 O
        ELECTROLYZER_RECIPES.recipeBuilder().duration(160).EUt(500)
                .input(dust,RadiumNitrate,9)
                .output(dust, Radium)
                .fluidOutputs(Nitrogen.getFluid(2000))
                .fluidOutputs(Oxygen.getFluid(6000))
                .buildAndRegister();
    }
}
