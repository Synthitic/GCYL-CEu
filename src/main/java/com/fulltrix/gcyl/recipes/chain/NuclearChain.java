package com.fulltrix.gcyl.recipes.chain;

import gregtech.api.GTValues;
import gregtech.api.unification.material.Material;

import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static com.fulltrix.gcyl.materials.GCYLNuclearMaterials.*;
import static com.fulltrix.gcyl.recipes.GCYLRecipeMaps.DECAY_CHAMBERS_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.ingot;

public class NuclearChain {
    public static void init() {
        //TODO: nuclear
        gasCentrifugeInit();
        decayChamberInit();

        //NUCLEAR PROCESSING

        /*

        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder().duration(300).EUt(30)
                .inputs(PROTACTINIUM_WASTE.getStackForm())
                .chancedOutput(NUCLEAR_WASTE.getStackForm(), 560, 0)
                .chancedOutput(OreDictUnifier.get(dust, Protactinium233.getMaterial(), 1), 3000, 0)
                .outputs(OreDictUnifier.get(dust, Thorium))
                .buildAndRegister();

        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder().duration(300).EUt(30)
                .inputs(THORIUM_WASTE.getStackForm())
                .chancedOutput(NUCLEAR_WASTE.getStackForm(), 560, 0)
                .chancedOutput(OreDictUnifier.get(dust, Protactinium233.getMaterial(), 1), 3000, 0)
                .outputs(OreDictUnifier.get(dust, Uranium))
                .buildAndRegister();

        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder().duration(300).EUt(60)
                .inputs(URANIUM_WASTE.getStackForm())
                .chancedOutput(NUCLEAR_WASTE.getStackForm(), 760, 0)
                .chancedOutput(OreDictUnifier.get(dust, Uranium, 1), 3000, 0)
                .outputs(OreDictUnifier.get(dust, Neptunium.getMaterial()))
                .buildAndRegister();

        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder().duration(300).EUt(120)
                .inputs(NEPTUNIUM_WASTE.getStackForm())
                .chancedOutput(NUCLEAR_WASTE.getStackForm(), 1000, 0)
                .chancedOutput(OreDictUnifier.get(dust, Neptunium.getMaterial(), 1), 3000, 0)
                .outputs(OreDictUnifier.get(dust, PlutoniumRadioactive.getMaterial()))
                .buildAndRegister();

        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder().duration(300).EUt(240)
                .inputs(PLUTONIUM_WASTE.getStackForm())
                .chancedOutput(NUCLEAR_WASTE.getStackForm(), 1330, 0)
                .chancedOutput(OreDictUnifier.get(dust, PlutoniumRadioactive.getMaterial(), 1), 3000, 0)
                .outputs(OreDictUnifier.get(dust, AmericiumRadioactive.getMaterial()))
                .buildAndRegister();

        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder().duration(300).EUt(480)
                .inputs(AMERICIUM_WASTE.getStackForm())
                .chancedOutput(NUCLEAR_WASTE.getStackForm(), 1780, 0)
                .chancedOutput(OreDictUnifier.get(dust, AmericiumRadioactive.getMaterial(), 1), 3000, 0)
                .outputs(OreDictUnifier.get(dust, Curium.getMaterial()))
                .buildAndRegister();

        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder().duration(300).EUt(960)
                .inputs(CURIUM_WASTE.getStackForm())
                .chancedOutput(NUCLEAR_WASTE.getStackForm(), 2370, 0)
                .chancedOutput(OreDictUnifier.get(dust, Curium.getMaterial(), 1), 3000, 0)
                .outputs(OreDictUnifier.get(dust, Berkelium.getMaterial()))
                .buildAndRegister();

        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder().duration(300).EUt(1920)
                .inputs(BERKELIUM_WASTE.getStackForm())
                .chancedOutput(NUCLEAR_WASTE.getStackForm(), 3160, 0)
                .chancedOutput(OreDictUnifier.get(dust, Berkelium.getMaterial(), 1), 3000, 0)
                .outputs(OreDictUnifier.get(dust, Californium.getMaterial()))
                .buildAndRegister();

        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder().duration(300).EUt(3840)
                .inputs(CALIFORNIUM_WASTE.getStackForm())
                .chancedOutput(NUCLEAR_WASTE.getStackForm(), 4220, 0)
                .chancedOutput(OreDictUnifier.get(dust, Californium.getMaterial(), 1), 3000, 0)
                .outputs(OreDictUnifier.get(dust, Einsteinium.getMaterial()))
                .buildAndRegister();

        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder().duration(300).EUt(7680)
                .inputs(EINSTEINIUM_WASTE.getStackForm())
                .chancedOutput(NUCLEAR_WASTE.getStackForm(), 5630, 0)
                .chancedOutput(OreDictUnifier.get(dust, Einsteinium.getMaterial(), 1), 3000, 0)
                .outputs(OreDictUnifier.get(dust, Fermium.getMaterial()))
                .buildAndRegister();

        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder().duration(300).EUt(15360)
                .inputs(FERMIUM_WASTE.getStackForm())
                .chancedOutput(NUCLEAR_WASTE.getStackForm(), 7500, 0)
                .chancedOutput(OreDictUnifier.get(dust, Fermium.getMaterial(), 1), 3000, 0)
                .outputs(OreDictUnifier.get(dust, Mendelevium.getMaterial()))
                .buildAndRegister();

        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder().duration(300).EUt(30720)
                .inputs(MENDELEVIUM_WASTE.getStackForm())
                .chancedOutput(NUCLEAR_WASTE.getStackForm(), 10000, 0)
                .outputs(OreDictUnifier.get(dust, Mendelevium.getMaterial()))
                .buildAndRegister();

         */

        // 3K + 7Na -> Na7K3
        MIXER_RECIPES.recipeBuilder().duration(300).EUt(120)
                .input(dust, Potassium, 3)
                .input(dust, Sodium, 7)
                .circuitMeta(10)
                .output(dust, SodiumPotassiumAlloy, 10)
                .buildAndRegister();

        // LiOH(H2O) + HF -> LiF + 2H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(200)
                .fluidInputs(LithiumHydroxideSolution.getFluid(1000))
                .fluidInputs(HydrofluoricAcid.getFluid(1000))
                .output(dust, LithiumFluoride, 2)
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();

        // Na + F -> NaF
        CHEMICAL_RECIPES.recipeBuilder().duration(200)
                .input(dust, Sodium)
                .fluidInputs(Fluorine.getFluid(1000))
                .output(dust, SodiumFluoride, 2)
                .buildAndRegister();

        // K + F -> KF
        CHEMICAL_RECIPES.recipeBuilder().duration(200)
                .input(dust, Potassium)
                .fluidInputs(Fluorine.getFluid(1000))
                .output(dust, PotassiumFluoride, 2)
                .buildAndRegister();

        // LiF + NaF + KF -> LiNaKF3
        MIXER_RECIPES.recipeBuilder().EUt(64).duration(480)
                .input(dust, LithiumFluoride, 2)
                .input(dust, SodiumFluoride, 2)
                .input(dust, PotassiumFluoride, 2)
                .output(dust, FLiNaK, 6)
                .buildAndRegister();

        // Be + 2F -> BeF2
        CHEMICAL_RECIPES.recipeBuilder().duration(30)
                .input(dust, Beryllium)
                .fluidInputs(Fluorine.getFluid(2000))
                .output(dust, BerylliumFluoride, 3)
                .buildAndRegister();

        // LiF + BeF2 -> F3LiBe
        MIXER_RECIPES.recipeBuilder().duration(600).EUt(120)
                .input(dust, LithiumFluoride, 2)
                .input(dust, BerylliumFluoride, 3)
                .output(dust, FLiBe, 5)
                .buildAndRegister();

        // 3Pb + 7Bi -> Pb3Bi7
        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(100).EUt(16)
                .input(dust, Lead, 3)
                .input(dust, Bismuth, 7)
                .output(dust, LeadBismuthEutectic, 10)
                .buildAndRegister();

        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(100).EUt(16)
                .input(ingot, Lead, 3)
                .input(dust, Bismuth, 7)
                .output(dust, LeadBismuthEutectic, 10)
                .buildAndRegister();

        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(100).EUt(16)
                .input(dust, Lead, 3)
                .input(ingot, Bismuth, 7)
                .output(dust, LeadBismuthEutectic, 10)
                .buildAndRegister();

        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(100).EUt(16)
                .input(ingot, Lead, 3)
                .input(ingot, Bismuth, 7)
                .output(dust, LeadBismuthEutectic, 10)
                .buildAndRegister();


        /*
        ModHandler.addSmeltingRecipe(OreDictUnifier.get(ingot, Protactinium233.getMaterial()), OreDictUnifier.get(ingot, Protactinium.getMaterial()));
        ModHandler.addSmeltingRecipe(OreDictUnifier.get(ingot, Protactinium.getMaterial()), OreDictUnifier.get(ingot, Protactinium233.getMaterial()));


        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().EUt(32).duration(300)
                .inputs(NUCLEAR_WASTE.getStackForm())
                .chancedOutput(NUCLEAR_WASTE_LANTHANIDE_A.getStackForm(), 1111, 111)
                .chancedOutput(NUCLEAR_WASTE_LANTHANIDE_B.getStackForm(), 1111, 111)
                .chancedOutput(NUCLEAR_WASTE_ALKALINE.getStackForm(), 1111, 111)
                .chancedOutput(NUCLEAR_WASTE_HEAVY_METAL.getStackForm(), 1111, 111)
                .chancedOutput(NUCLEAR_WASTE_METAL_A.getStackForm(), 1111, 111)
                .chancedOutput(NUCLEAR_WASTE_METAL_B.getStackForm(), 1111, 111)
                .chancedOutput(NUCLEAR_WASTE_METAL_C.getStackForm(), 1111, 111)
                .chancedOutput(NUCLEAR_WASTE_REACTIVE_NONMETAL.getStackForm(), 1111, 111)
                .chancedOutput(NUCLEAR_WASTE_METALOID.getStackForm(), 1111, 111).buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().EUt(32).duration(300)
                .inputs(NUCLEAR_WASTE_HEAVY_METAL.getStackForm(9))
                .fluidOutputs(Mercury.getFluid(250 * 9) )
                .chancedOutput(OreDictUnifier.get(dust, Zinc, 2), 5555, 200)
                .chancedOutput(OreDictUnifier.get(dust, Gallium, 2), 5555, 300)
                .chancedOutput(OreDictUnifier.get(dust, Cadmium, 2), 5555, 400)
                .chancedOutput(OreDictUnifier.get(dust, Indium, 2), 5555, 500)
                .chancedOutput(OreDictUnifier.get(dust, Tin, 2), 5555, 600)
                .chancedOutput(OreDictUnifier.get(dust, Thallium, 2), 5555, 700)
                .chancedOutput(OreDictUnifier.get(dust, Lead, 2), 5555, 800)
                .chancedOutput(OreDictUnifier.get(dust, Bismuth, 2), 5555, 900)
                .chancedOutput(OreDictUnifier.get(dust, Polonium, 2), 5555, 1000)
                .buildAndRegister();


        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().EUt(32).duration(300)
                .inputs(NUCLEAR_WASTE_LANTHANIDE_A.getStackForm(9))
                .chancedOutput(OreDictUnifier.get(dust, Dysprosium, 2), 8333, 500)
                .chancedOutput(OreDictUnifier.get(dust, Holmium, 2), 8333, 600)
                .chancedOutput(OreDictUnifier.get(dust, Erbium, 2), 8333, 700)
                .chancedOutput(OreDictUnifier.get(dust, Thulium, 2), 8333, 800)
                .chancedOutput(OreDictUnifier.get(dust, Ytterbium, 2), 8333, 900)
                .chancedOutput(OreDictUnifier.get(dust, Lutetium, 2), 8333, 1000)
                .buildAndRegister();
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().EUt(32).duration(300)
                .inputs(NUCLEAR_WASTE_LANTHANIDE_B.getStackForm(9))
                .chancedOutput(OreDictUnifier.get(dust, Lanthanum, 2), 5555, 200)
                .chancedOutput(OreDictUnifier.get(dust, Cerium, 2), 5555, 300)
                .chancedOutput(OreDictUnifier.get(dust, Praseodymium, 2), 5555, 400)
                .chancedOutput(OreDictUnifier.get(dust, Neodymium, 2), 5555, 500)
                .chancedOutput(OreDictUnifier.get(dust, Promethium, 2), 5555, 600)
                .chancedOutput(OreDictUnifier.get(dust, Samarium, 2), 5555, 700)
                .chancedOutput(OreDictUnifier.get(dust, Europium, 2), 5555, 800)
                .chancedOutput(OreDictUnifier.get(dust, Gadolinium, 2), 5555, 900)
                .chancedOutput(OreDictUnifier.get(dust, Terbium, 2), 5555, 1000)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().EUt(32).duration(300)
                .inputs(NUCLEAR_WASTE_METAL_A.getStackForm(9))
                .chancedOutput(OreDictUnifier.get(dust, Hafnium, 2), 6250, 300)
                .chancedOutput(OreDictUnifier.get(dust, Tantalum, 2), 6250, 400)
                .chancedOutput(OreDictUnifier.get(dust, Tungsten, 2), 6250, 500)
                .chancedOutput(OreDictUnifier.get(dust, Osmium, 2), 6250, 700)
                .chancedOutput(OreDictUnifier.get(dust, Iridium, 2), 6250, 800)
                .chancedOutput(OreDictUnifier.get(dust, Platinum, 2), 6250, 900)
                .chancedOutput(OreDictUnifier.get(dust, Gold, 2), 6250, 1000)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().EUt(32).duration(300)
                .inputs(NUCLEAR_WASTE_METAL_B.getStackForm(9))
                .chancedOutput(OreDictUnifier.get(dust, Yttrium, 2), 5555, 200)
                .chancedOutput(OreDictUnifier.get(dust, Zirconium, 2), 5555, 300)
                .chancedOutput(OreDictUnifier.get(dust, Niobium, 2), 5555, 400)
                .chancedOutput(OreDictUnifier.get(dust, Molybdenum, 2), 5555, 500)
                .chancedOutput(OreDictUnifier.get(dust, Technetium, 2), 5555, 600)
                .chancedOutput(OreDictUnifier.get(dust, Ruthenium, 2), 5555, 700)
                .chancedOutput(OreDictUnifier.get(dust, Rhodium, 2), 5555, 800)
                .chancedOutput(OreDictUnifier.get(dust, Palladium, 2), 5555, 900)
                .chancedOutput(OreDictUnifier.get(dust, Silver, 2), 5555, 1000)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().EUt(32).duration(300)
                .inputs(NUCLEAR_WASTE_METAL_C.getStackForm(9))
                .output(dust, Iron, 2)
                .output(dust, Cobalt, 2)
                .output(dust, Nickel, 2)
                .output(dust, Copper, 2)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().EUt(32).duration(300)
                .inputs(NUCLEAR_WASTE_METALOID.getStackForm(9))
                .chancedOutput(OreDictUnifier.get(dust, Germanium, 2), 6250, 500)
                .chancedOutput(OreDictUnifier.get(dust, Arsenic, 2), 6250, 600)
                .chancedOutput(OreDictUnifier.get(dust, Antimony, 2), 6250, 700)
                .chancedOutput(OreDictUnifier.get(dust, Tellurium, 2), 6250, 800)
                .chancedOutput(OreDictUnifier.get(dust, Astatine, 2), 6250, 900)
                .chancedOutput(OreDictUnifier.get(dust, Actinium, 2), 6250, 1000)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().EUt(32).duration(300)
                .inputs(NUCLEAR_WASTE_ALKALINE.getStackForm(9))
                .chancedOutput(OreDictUnifier.get(dust, Rubidium, 2), 6250, 500)
                .chancedOutput(OreDictUnifier.get(dust, Strontium, 2), 6250, 600)
                .chancedOutput(OreDictUnifier.get(dust, Caesium, 2), 6250, 700)
                .chancedOutput(OreDictUnifier.get(dust, Barium, 2), 6250, 800)
                .chancedOutput(OreDictUnifier.get(dust, Francium, 2), 6250, 900)
                .chancedOutput(OreDictUnifier.get(dust, Radium, 2), 6250, 1000)
                .buildAndRegister();


        CENTRIFUGE_RECIPES.recipeBuilder().EUt(32).duration(300)
                .inputs(NUCLEAR_WASTE_REACTIVE_NONMETAL.getStackForm(9))
                .fluidOutputs(Krypton.getFluid(250 * 9))
                .fluidOutputs(Xenon.getFluid(500 * 9))
                .fluidOutputs(Radon.getFluid(9000))
                .chancedOutput(OreDictUnifier.get(dust, Selenium, 2), 6250, 800)
                .chancedOutput(OreDictUnifier.get(dust, Bromine, 2), 6250, 900)
                .chancedOutput(OreDictUnifier.get(dust, Iodine, 2), 6250, 1000)
                .buildAndRegister();

         */
    }

    private static void gasCentrifugeInit() {

        /* TODO: ???
        for (Material mats : radioactiveMats) {
            CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(30)
                    .input(dust, mats)
                    .fluidInputs(NitricAcid.getFluid(1000))
                    .fluidOutputs(Hydrogen.getFluid(2000))
                    .output(dust, GenericRadioactives.get(mats.getName() + "_" + "nitrite"), 3)
                    .buildAndRegister();

            BLAST_RECIPES.recipeBuilder().duration(200).EUt(120)
                    .input(dust, GenericRadioactives.get(mats.getName() + "_" + "nitrite"), 3)
                    .output(dust, GenericRadioactives.get(mats.getName() + "_" + "dioxide"))
                    .fluidOutputs(NitricOxide.getFluid(2000))
                    .blastFurnaceTemp(600)
                    .buildAndRegister();

            CHEMICAL_RECIPES.recipeBuilder().duration(1200).EUt(30)
                    .input(dust, GenericRadioactives.get(mats.getName() + "_" + "dioxide"))
                    .fluidInputs(Chlorine.getFluid(6000))
                    .fluidOutputs(GenericRadioactives.get(mats.getName() + "_" + "hexachloride").getFluid(1000))
                    .fluidOutputs(Oxygen.getFluid(2000))
                    .buildAndRegister();

            CHEMICAL_RECIPES.recipeBuilder().duration(1200).EUt(30)
                    .fluidInputs(GenericRadioactives.get(mats.getName() + "_" + "hexachloride").getFluid(1000))
                    .fluidInputs(HydrofluoricAcid.getFluid(6000))
                    .fluidOutputs(GenericRadioactives.get(mats.getName() + "_" + "hexafluoride").getFluid(1000))
                    .fluidOutputs(HydrochloricAcid.getFluid(6000))
                    .buildAndRegister();
        }

         */
    }

    private static void decayChamberInit() {

        //alpha decay



    }
}
