package com.fulltrix.tjfcore.recipes.categories.circuits.components;

import com.fulltrix.tjfcore.TJFConfig;
import com.fulltrix.tjfcore.TJFUtility;
import gregtech.api.fluids.store.FluidStorageKey;
import gregtech.api.fluids.store.FluidStorageKeys;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

import static com.fulltrix.tjfcore.TJFMaterials.*;
import static com.fulltrix.tjfcore.item.TJFCoreItems.*;
import static gregtech.api.GTValues.L;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.MIXER_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.Materials.Graphene;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.api.unification.ore.OrePrefix.plate;

public class ExoticComponents {
    private static final List<FluidStack> SOLDER_FLUIDS = new ArrayList<>();

    static {
        for (String fluid : TJFConfig.Misc.solderingFluidList) {
            String[] fluidSplit = fluid.split(":");
            int amount = TJFUtility.setBetweenInclusive(Integer.parseInt(fluidSplit[1]), 1, 64000);

            FluidStack fluidStack = FluidRegistry.getFluidStack(fluidSplit[0], amount);
            if (fluidStack != null) SOLDER_FLUIDS.add(fluidStack);
        }
    }

    public static void init(){

        //TODO: research & inductors

        for(FluidStack fluidStack : SOLDER_FLUIDS){

            ASSEMBLER_RECIPES.recipeBuilder().duration(160).EUt(8000)
                    .fluidInputs(fluidStack)
                    .input(stick, Californium)
                    .input(wireFine, Pikyonium, 4)
                    .inputs(ALUMINO_SILICATE_GLASS_TUBE.getStackForm())
                    .outputs(MICROFOCUS_X_RAY_TUBE.getStackForm())
                    .buildAndRegister();

        }

        ASSEMBLER_RECIPES.recipeBuilder().duration(160).EUt((int)1E+6).fluidInputs(Polyetheretherketone.getFluid(144 * 4))
                .input(foil, Cinobite)
                .input(foil, Quantum)
                .outputs(SMD_CAPACITOR_EXOTIC.getStackForm(32))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(160).EUt((int)1E+6).fluidInputs(Polyetheretherketone.getFluid(144 * 4))
                .input(wireFine, Cinobite)
                .input(foil, TriniumTitanium)
                .outputs(SMD_RESISTOR_EXOTIC.getStackForm(32))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(160).EUt((int)1E+6).fluidInputs(Polyetheretherketone.getFluid(144 * 4))
                .input(plate, Vibranium)
                .input(plate, Quantum)
                .outputs(SMD_DIODE_EXOTIC.getStackForm(32))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(160).EUt((int)1E+6).fluidInputs(Polyetheretherketone.getFluid(144 * 4))
                .inputs(DEGENERATE_RHENIUM_PLATE.getStackForm())
                .input(foil, TriniumTitanium)
                .outputs(SMD_TRANSISTOR_EXOTIC.getStackForm(32))
                .buildAndRegister();

        ARC_FURNACE_RECIPES.recipeBuilder().duration(160).EUt(800000)
                .inputs(WAFER_DUBNIUM.getStackForm())
                .fluidInputs(FreeAlphaGas.getFluid(47))
                .outputs(UNTREATED_EXOTIC_WAFER.getStackForm())
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(160).EUt(2000)
                .inputs(UNTREATED_EXOTIC_WAFER.getStackForm())
                .fluidInputs(MicrocrystallizingHydrogen.getFluid(100))
                .outputs(EXOTIC_WAFER.getStackForm())
                .buildAndRegister();

        CUTTER_RECIPES.recipeBuilder().duration(160).EUt(200000)
                .inputs(EXOTIC_WAFER.getStackForm())
                .outputs(EXOTIC_CHIP.getStackForm(16))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(160).EUt(2000)
                .inputs(HIGH_FREQUENCY_LASER.getStackForm())
                .input(plate, MetastableHassium)
                .inputs(HIGHLY_REFLECTIVE_MIRROR.getStackForm())
                .fluidInputs(Zylon.getFluid(72))
                .outputs(EXCITATION_MAINTAINER.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(160).EUt(8000)
                .input(plate, Graphene)
                .inputs(AEROGRAPHENE.getStackForm(5))
                .input(wireFine, TriniumTitanium)
                .fluidInputs(Zylon.getFluid(72))
                .outputs(CRYOGENIC_INTERFACE.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(160).EUt(4000000)
                .inputs(CRYOGENIC_INTERFACE.getStackForm())
                .inputs(EXCITATION_MAINTAINER.getStackForm())
                .inputs(EXOTIC_CHIP.getStackForm())
                .inputs(X_RAY_WAVEGUIDE.getStackForm())
                .inputs(ELECTRON_SOURCE.getStackForm())
                .fluidInputs(QuantumDots.getFluid(100))
                .fluidInputs(Zylon.getFluid(144))
                .fluidInputs(Hydrogen.getFluid(FluidStorageKeys.LIQUID,250))
                .fluidInputs(Xenon.getFluid(1000))
                .outputs(RYDBERG_SPINORIAL_ASSEMBLY.getStackForm())
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(160).EUt(4000000)
                .inputs(NON_LINEAR_OPTICAL_LENS.getStackForm(4))
                .inputs(DEGENERATE_RHENIUM_PLATE.getStackForm(3))
                .input(foil, TriniumTitanium, 8)
                .inputs(RYDBERG_SPINORIAL_ASSEMBLY.getStackForm())
                .inputs(X_RAY_LASER.getStackForm())
                .inputs(SMD_CAPACITOR_EXOTIC.getStackForm(32))
                .inputs(SMD_DIODE_EXOTIC.getStackForm(32))
                .inputs(SMD_TRANSISTOR_EXOTIC.getStackForm(32))
                .inputs(SMD_RESISTOR_EXOTIC.getStackForm(32))
                .input(wireGtSingle, Pikyonium, 8)
                .inputs(CLADDED_OPTICAL_FIBER_CORE.getStackForm(4))
                .fluidInputs(QuantumDots.getFluid(100))
                .fluidInputs(SolderingAlloy.getFluid(L * 9))
                .fluidInputs(Polybenzimidazole.getFluid(L * 6))
                .outputs(EXOTIC_PROCESSING_CORE.getStackForm(6))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(160).EUt(5000)
                .inputs(X_RAY_WAVEGUIDE.getStackForm())
                .inputs(MICROFOCUS_X_RAY_TUBE.getStackForm())
                .inputs(X_RAY_MIRROR_PLATE.getStackForm())
                .fluidInputs(CaliforniumCyclopentadienide.getFluid(10))
                .outputs(X_RAY_LASER.getStackForm())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(1800).EUt(120) //2H + 2IrO2 -> Ir2O3 + H2O
                .fluidInputs(Hydrogen.getFluid(2000))
                .input(dust, IridiumDioxide, 6)
                .output(dust,IridiumTrioxide,5)
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(240).EUt(2000)
                .fluidInputs(HydrochloricAcid.getFluid(6000))
                .input(dust,IridiumTrioxide,5)
                .fluidOutputs(IridiumTrichlorideSolution.getFluid(6000))
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(80).EUt(100)
                .fluidInputs(FullerenePolymerMatrix.getFluid(18))
                .notConsumable(wireFine, Polyetheretherketone)
                .outputs(FULLERENE_POLYMER_MATRIX_SOFT_TUBING.getStackForm())
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder().duration(240).EUt(500)
                .inputs(FULLERENE_POLYMER_MATRIX_SOFT_TUBING.getStackForm())
                .outputs(FULLERENE_POLYMER_MATRIX_FINE_TUBING.getStackForm())
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(240).EUt(800000)
                .inputs(FULLERENE_POLYMER_MATRIX_FINE_TUBING.getStackForm())
                .fluidInputs(IridiumTrichlorideSolution.getFluid(100))
                .outputs(X_RAY_WAVEGUIDE.getStackForm())
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(240).EUt(200000)
                .fluidInputs(IridiumTrichlorideSolution.getFluid(100))
                .input(plate, Graphene)
                .outputs(X_RAY_MIRROR_PLATE.getStackForm())
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder().duration(240).EUt(500)
                .fluidInputs(Hydrogen.getFluid(FluidStorageKeys.LIQUID,1000))
                .fluidOutputs(SemisolidHydrogen.getFluid(1000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(80).EUt(500)
                .fluidInputs(Hydrogen.getFluid(FluidStorageKeys.LIQUID,1000))
                .fluidInputs(SemisolidHydrogen.getFluid(1000))
                .fluidOutputs(MicrocrystallizingHydrogen.getFluid(1000))
                .buildAndRegister();
    }
}
