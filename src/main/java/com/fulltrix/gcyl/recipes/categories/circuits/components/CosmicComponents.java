package com.fulltrix.gcyl.recipes.categories.circuits.components;

import com.fulltrix.gcyl.GCYLConfig;
import com.fulltrix.gcyl.api.multi.GCYLCleanroomType;
import gregtech.api.metatileentity.multiblock.CleanroomType;

import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static com.fulltrix.gcyl.item.GCYLCoreItems.*;
import static gregtech.api.GTValues.L;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.MarkerMaterials.Color.White;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.api.unification.ore.OrePrefix.gemFlawless;

public class CosmicComponents {
    public static void init() {

        //TODO: research & inductors

        // SMD Diode
        ASSEMBLER_RECIPES.recipeBuilder().duration(250).EUt(6000000)
                .input(wireFine, HastelloyX78, 8)
                .input(plate, SuperheavyHAlloy, 4)
                .input(plate, AbyssalAlloy, 4)
                .fluidInputs(FullerenePolymerMatrix.getFluid(L))
                .outputs(SMD_DIODE_COSMIC.getStackForm(32))
                .cleanroom(GCYLCleanroomType.ISO1)
                .buildAndRegister();

        // SMD Transistor
        ASSEMBLER_RECIPES.recipeBuilder().duration(250).EUt(6000000)
                .input(wireFine, HastelloyX78, 8)
                .input(plate, MetastableHassium)
                .inputs(DEGENERATE_RHENIUM_PLATE.getStackForm(4))
                .fluidInputs(Zylon.getFluid(L))
                .outputs(SMD_TRANSISTOR_COSMIC.getStackForm(32))
                .cleanroom(GCYLCleanroomType.ISO1)
                .buildAndRegister();

        // SMD Capacitor
        ASSEMBLER_RECIPES.recipeBuilder().duration(250).EUt(6000000)
                .input(wireFine, HastelloyX78, 8)
                .input(foil, Taranium)
                .inputs(AEROGRAPHENE.getStackForm())
                .fluidInputs(Zylon.getFluid(L))
                .outputs(SMD_CAPACITOR_COSMIC.getStackForm(32))
                .cleanroom(GCYLCleanroomType.ISO1)
                .buildAndRegister();

        // SMD Resistor
        ASSEMBLER_RECIPES.recipeBuilder().duration(250).EUt(6000000)
                .input(wireFine, HastelloyX78, 8)
                .input(plate, SuperheavyLAlloy, 4)
                .input(plate, TriniumTitanium, 4)
                .fluidInputs(FullerenePolymerMatrix.getFluid(L))
                .outputs(SMD_RESISTOR_COSMIC.getStackForm(32))
                .cleanroom(GCYLCleanroomType.ISO1)
                .buildAndRegister();

        // Cosmic Processing Core
        /*
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(320).EUt(11796480)
                .inputs(COSMIC_COMPUTE_UNIT.getStackForm())
                .inputs(SMD_DIODE_COSMIC.getStackForm(48))
                .inputs(SMD_RESISTOR_COSMIC.getStackForm(48))
                .inputs(SMD_TRANSISTOR_COSMIC.getStackForm(48))
                .inputs(SMD_CAPACITOR_COSMIC.getStackForm(48))
                .input(foil, FullerenePolymerMatrix, 2)
                .inputs(ULTRASHORT_PULSE_LASER.getStackForm())
                .input(wireGtSingle, Cinobite, 8)
                .inputs(CLADDED_OPTICAL_FIBER_CORE.getStackForm(8))
                .inputs(BOSE_EINSTEIN_COOLING_CONTAINER.getStackForm(4))
                .input(plate,BlackTitanium,4)
                .fluidInputs(Zylon.getFluid(L * 6))
                .fluidInputs(Quantum.getFluid(L * 3))
                .fluidInputs(SolderingAlloy.getFluid(L * 9))
                .fluidInputs(ProtoAdamantium.getFluid(L * 3))
                .outputs(COSMIC_PROCESSING_CORE.getStackForm(8))
                .cleanroom(GCYLCleanroomType.ISO1)
                .buildAndRegister();
         */

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(320).EUt(11796480)
                .inputs(COSMIC_COMPUTE_UNIT.getStackForm())
                .inputs(SMD_DIODE_COSMIC.getStackForm(48))
                .inputs(SMD_RESISTOR_COSMIC.getStackForm(48))
                .inputs(SMD_TRANSISTOR_COSMIC.getStackForm(48))
                .inputs(SMD_CAPACITOR_COSMIC.getStackForm(48))
                .input(foil, FullerenePolymerMatrix, 2)
                .inputs(ULTRASHORT_PULSE_LASER.getStackForm())
                .input(wireGtSingle, Cinobite, 8)
                .inputs(CLADDED_OPTICAL_FIBER_CORE.getStackForm(8))
                .inputs(BOSE_EINSTEIN_COOLING_CONTAINER.getStackForm(4))
                .input(plate,BlackTitanium,4)
                .fluidInputs(Zylon.getFluid(L * 6))
                .fluidInputs(Quantum.getFluid(L * 3))
                .fluidInputs(SolderingAlloy.getFluid(L * 9))
                .fluidInputs(ProtoAdamantium.getFluid(L * 3))
                .outputs(COSMIC_PROCESSING_CORE.getStackForm(GCYLConfig.recipes.circuitCoresPerCraft))
                .cleanroom(GCYLCleanroomType.ISO1)
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(240).EUt(695000)
                .inputs(COATED_HASSIUM_WAFER.getStackForm())
                .fluidInputs(PhotopolymerSolution.getFluid(500))
                .outputs(PHOTOCOATED_HASSIUM_WAFER.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(320).EUt(840000)
                .input(craftingLens, White)
                .inputs(CLADDED_OPTICAL_FIBER_CORE.getStackForm(8))
                .outputs(GRATING_LITHOGRAPHY_MASK.getStackForm())
                .buildAndRegister();

        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(320).EUt(3200000)
                .inputs(PHOTOCOATED_HASSIUM_WAFER.getStackForm())
                .notConsumable(GRATING_LITHOGRAPHY_MASK.getStackForm())
                .outputs(DIFFRACTOR_GRATING_MIRROR.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(270).EUt(4500000)
                .inputs(HIGH_FREQUENCY_LASER.getStackForm())
                .inputs(DIFFRACTOR_GRATING_MIRROR.getStackForm(4))
                .inputs(NDYAG_ROD.getStackForm())
                .inputs(LUTMYVO_ROD.getStackForm())
                .inputs(ALUMINO_SILICATE_GLASS_TUBE.getStackForm())
                .inputs(CLADDED_OPTICAL_FIBER_CORE.getStackForm(2))
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .outputs(ULTRASHORT_PULSE_LASER.getStackForm())
                .buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().duration(280).EUt(1500000)
                .input(plate, Vibranium, 2)
                .input(dust,TlTmCesiumIodide)
                .input(dust,PolycyclicAromaticMix)
                .input(dust,CadmiumTungstate)
                .input(dust,BismuthGermanate)
                .outputs(SCINTILLATOR_CRYSTAL.getStackForm())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(340).EUt(3450000).blastFurnaceTemp(10900)
                .input(dust, MetastableHassium)
                .input(dust, Molybdenum)
                .input(dust, Rhenium)
                .fluidInputs(NaquadahAlloy.getFluid(L))
                .outputs(LEPTON_TRAP_CRYSTAL.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(380).EUt(3500000)
                .inputs(SCINTILLATOR_CRYSTAL.getStackForm())
                .input(screw, HDCS, 12)
                .input(plate, Cinobite, 4)
                .input(plate, Quantum, 4)
                .inputs(SEPARATION_ELECTROMAGNET.getStackForm())
                .input(foil, Zylon, 6)
                .input(wireFine, Cinobite, 12)
                .fluidInputs(TriniumTitanium.getFluid(L * 3))
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .outputs(SCINTILLATOR.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(350).EUt(7500000)
                .inputs(DEGENERATE_RHENIUM_PLATE.getStackForm(1))
                .input(wireFine, HeavyQuarkDegenerateMatter, 6)
                .inputs(EXOTIC_CHIP.getStackForm(4))
                .inputs(EMPTY_LASER_COOLING_CONTAINER.getStackForm())
                .input(gemFlawless, Vinteum, 2)
                .input(gemFlawless, Tanzanite, 2)
                .fluidInputs(CosmicComputingMix.getFluid(1000))
                .outputs(COSMIC_COMPUTE_UNIT.getStackForm())
                .cleanroom(GCYLCleanroomType.ISO1)
                .buildAndRegister();
    }
}
