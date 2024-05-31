package com.fulltrix.tjfcore.recipes.categories;

import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.stack.UnificationEntry;
import net.minecraft.item.ItemStack;

import static com.fulltrix.tjfcore.TJFMaterials.*;
import static com.fulltrix.tjfcore.item.TJFCoreItems.*;
import static com.fulltrix.tjfcore.recipes.TJFRecipeMaps.*;
import static gregicality.multiblocks.api.unification.GCYMMaterials.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.Materials.Amethyst;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.common.items.MetaItems.*;

public class SuperconductorRecipes {
    private static final Material[] SUPERCONDUCTORS = {
            null, null, MVSuperconductor,
            HVSuperconductor, EVSuperconductor, IVSuperconductor,
            LuVSuperconductor, ZPMSuperconductor, UVSuperconductor,
            UHVSuperconductor, UEVSuperconductor, UIVSuperconductor,
            UXVSuperconductor, OpVSuperconductor, MAXSuperconductor};

    private static final Material[] SUPERCONDUCTOR_BASES = {
            null, null, MVSuperconductorBase,
            HVSuperconductorBase, EVSuperconductorBase, IVSuperconductorBase,
            LuVSuperconductorBase, ZPMSuperconductorBase, UVSuperconductorBase,
            UHVSuperconductorBase, UEVSuperconductorBase, UIVSuperconductorBase,
            UXVSuperconductorBase, OpVSuperconductorBase};



    public static void init() {

            ASSEMBLER_RECIPES.recipeBuilder().duration(300).EUt(120)
                    .input(wireGtSingle, MVSuperconductorBase, 3)
                    .input(pipeTinyFluid, StainlessSteel, 2)
                    .inputs(ELECTRIC_PUMP_MV.getStackForm(2))
                    .notConsumable(IntCircuitIngredient.getIntegratedCircuit(1))
                    .fluidInputs(Nitrogen.getFluid(2000))
                    .output(wireGtSingle, MVSuperconductor, 3)
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(256)
                    .input(wireGtSingle, HVSuperconductorBase, 3)
                    .input(pipeTinyFluid, Titanium, 2)
                    .inputs(ELECTRIC_PUMP_HV.getStackForm())
                    .notConsumable(IntCircuitIngredient.getIntegratedCircuit(1))
                    .fluidInputs(Nitrogen.getFluid(2000))
                    .output(wireGtSingle, HVSuperconductor, 3)
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(480)
                    .input(wireGtSingle, EVSuperconductorBase, 9)
                    .input(pipeTinyFluid, TungstenSteel, 6)
                    .inputs(ELECTRIC_PUMP_EV.getStackForm(2))
                    .notConsumable(IntCircuitIngredient.getIntegratedCircuit(1))
                    .fluidInputs(Nitrogen.getFluid(FluidStorageKeys.LIQUID,6000))
                    .output(wireGtSingle, EVSuperconductor, 9)
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(300).EUt(1920)
                    .input(wireGtSingle, IVSuperconductorBase, 6)
                    .input(pipeTinyFluid, NiobiumTitanium, 4)
                    .inputs(ELECTRIC_PUMP_IV.getStackForm())
                    .notConsumable(IntCircuitIngredient.getIntegratedCircuit(1))
                    .fluidInputs(Nitrogen.getFluid(FluidStorageKeys.LIQUID,6000))
                    .output(wireGtSingle, IVSuperconductor, 6)
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(350).EUt(7680)
                    .input(wireGtSingle, LuVSuperconductorBase, 8)
                    .input(pipeTinyFluid, Enderium, 5)
                    .inputs(ELECTRIC_PUMP_LuV.getStackForm())
                    .notConsumable(IntCircuitIngredient.getIntegratedCircuit(1))
                    .fluidInputs(Nitrogen.getFluid(FluidStorageKeys.LIQUID,6000))
                    .output(wireGtSingle, LuVSuperconductor, 8)
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(30720)
                    .input(wireGtSingle, ZPMSuperconductorBase, 16)
                    .input(pipeTinyFluid, Naquadah, 6)
                    .inputs(ELECTRIC_PUMP_ZPM.getStackForm())
                    .notConsumable(IntCircuitIngredient.getIntegratedCircuit(1))
                    .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID,8000))
                    .output(wireGtSingle, ZPMSuperconductor, 16)
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(122880)
                    .input(wireGtSingle, UVSuperconductorBase, 32)
                    .input(pipeTinyFluid, Ultimet, 7)
                    .inputs(ELECTRIC_PUMP_UV.getStackForm())
                    .notConsumable(IntCircuitIngredient.getIntegratedCircuit(1))
                    .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID,10000))
                    .output(wireGtSingle, UVSuperconductor, 32)
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(491520)
                    .input(wireGtSingle, UHVSuperconductorBase, 32)
                    .input(pipeTinyFluid, Zeron100, 8)
                    .inputs(ELECTRIC_PUMP_UHV.getStackForm())
                    .notConsumable(IntCircuitIngredient.getIntegratedCircuit(1))
                    .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID,12000))
                    .output(wireGtSingle, UHVSuperconductor, 32)
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(1966080)
                    .input(wireGtSingle, UEVSuperconductorBase, 32)
                    .input(pipeTinyFluid, Lafium, 9)
                    .inputs(ELECTRIC_PUMP_UEV.getStackForm())
                    .notConsumable(IntCircuitIngredient.getIntegratedCircuit(1))
                    .fluidInputs(MicrocrystallizingHydrogen.getFluid(14000))
                    .output(wireGtSingle, UEVSuperconductor, 32)
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(7864320)
                    .input(wireGtSingle, UIVSuperconductorBase, 32)
                    .input(pipeTinyFluid, TantalumHafniumSeaborgiumCarbide, 10)
                    .inputs(ELECTRIC_PUMP_UIV.getStackForm())
                    .notConsumable(IntCircuitIngredient.getIntegratedCircuit(1))
                    .fluidInputs(MicrocrystallizingHydrogen.getFluid(16000))
                    .output(wireGtSingle, UIVSuperconductor, 32)
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(31457280)
                    .input(wireGtSingle, UXVSuperconductorBase, 32)
                    .input(pipeTinyFluid, Neutronium, 11)
                    .inputs(ELECTRIC_PUMP_UXV.getStackForm())
                    .notConsumable(IntCircuitIngredient.getIntegratedCircuit(1))
                    .fluidInputs(MicrocrystallizingHydrogen.getFluid(18000))
                    .output(wireGtSingle, UXVSuperconductor, 32)
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(125829120)
                    .input(wireGtSingle, OpVSuperconductorBase, 32)
                    .input(pipeTinyFluid, Neutronium, 12)
                    .inputs(ELECTRIC_PUMP_OpV.getStackForm())
                    .notConsumable(IntCircuitIngredient.getIntegratedCircuit(1))
                    .fluidInputs(FreeElectronGas.getFluid(20000))
                    .output(wireGtSingle, OpVSuperconductor, 32)
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(500).EUt(503316480)
                    .input(wireGtDouble, OpVSuperconductorBase, 64)
                    .input(pipeTinyFluid, Neutronium, 13)
                    .inputs(ELECTRIC_PUMP_MAX.getStackForm())
                    .notConsumable(IntCircuitIngredient.getIntegratedCircuit(2))
                    .fluidInputs(FreeElectronGas.getFluid(22000))
                    .output(wireGtSingle, MAXSuperconductor, 64)
                    .buildAndRegister();


        // UHV Superconductor Base Dust
        MIXER_RECIPES.recipeBuilder().duration(2781).EUt(30)
                .input(dust,TBCCODust,4)
                .input(dust,StrontiumSuperconductorDust,4)
                .input(dust, Amethyst)
                .output(dust, UHVSuperconductorBase, 9)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(3081).EUt(30)
                .input(dust, UHVSuperconductorBase, 9)
                .output(dust,TBCCODust,4)
                .output(dust,StrontiumSuperconductorDust,4)
                .output(dust, Amethyst)
                .buildAndRegister();

        // UEV Superconductor Base Dust
        LARGE_MIXER_RECIPES.recipeBuilder().duration(11292).EUt(30)
                .input(dust,ActiniumSuperhydride,1)
                .input(dust,BETSPerrhenate,1)
                .input(dust, Vibranium, 2)
                .input(dust, Quantum)
                .input(dust, TriniumTitanium)
                .output(dust, UEVSuperconductorBase, 6)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(11892).EUt(30)
                .input(dust, UEVSuperconductorBase, 6)
                .output(dust,ActiniumSuperhydride,1)
                .output(dust, Vibranium, 2)
                .output(dust, Quantum)
                .output(dust, TriniumTitanium)
                .buildAndRegister();

        // UIV Superconductor Base Dust
        MIXER_RECIPES.recipeBuilder().duration(3395).EUt(30)
                .input(dust,BorocarbideDust,2)
                .input(dust,FullereneSuperconductiveDust,1)
                .input(dust, MetastableOganesson, 2)
                .input(dust, ProtoAdamantium, 2)
                .output(dust, UIVSuperconductorBase, 7)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(3995).EUt(30)
                .input(dust, UIVSuperconductorBase, 7)
                .output(dust,BorocarbideDust,2)
                .output(dust,FullereneSuperconductiveDust,1)
                .output(dust, MetastableOganesson, 2)
                .output(dust, ProtoAdamantium, 2)
                .buildAndRegister();

        // UMV Superconductor Base Dust
        MIXER_RECIPES.recipeBuilder().duration(720).EUt(8500000)
                .input(dust, BlackTitanium, 3)
                .input(dust, SuperheavyHAlloy, 2)
                .input(dust,ChargedCesiumCeriumCobaltIndium,3)
                .input(dust,RheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate,6)
                .output(dust, UXVSuperconductorBase, 14)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(1020).EUt(8500000)
                .input(dust, UXVSuperconductorBase, 14)
                .output(dust, BlackTitanium, 3)
                .output(dust, SuperheavyHAlloy, 2)
                .output(dust,ChargedCesiumCeriumCobaltIndium,3)
                .output(dust,RheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate,6)
                .buildAndRegister();

        // UXV Superconductor Base Dust
        LARGE_MIXER_RECIPES.recipeBuilder().duration(720).EUt(33500000)
                .input(dust,Legendarium,5)
                .input(dust, Neutronium, 4)
                .input(dust,ActiniumSuperhydride,5)
                .input(dust,LanthanumFullereneNanotubes,4)
                .input(dust,RheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate,12)
                .output(dust, OpVSuperconductorBase, 30)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().duration(1020).EUt(33500000)
                .input(dust, OpVSuperconductorBase, 30)
                .output(dust,Legendarium,5)
                .output(dust, Neutronium, 4)
                .output(dust,ActiniumSuperhydride,5)
                .output(dust,LanthanumFullereneNanotubes,4)
                .output(dust,RheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate,12)
                .buildAndRegister();
    }
}
