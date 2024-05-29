package com.fulltrix.tjfcore.recipes;

import com.fulltrix.tjfcore.recipes.categories.circuits.CircuitRecipes;
import com.fulltrix.tjfcore.recipes.categories.machines.MachineCraftingRecipes;
import com.fulltrix.tjfcore.recipes.chain.*;

public class RecipeHandler {
    public static void initRecipes() {

        /*
        RecipeOverride.init();


        ComponentRecipes.init();
        MetaItemRecipes.init();
        CasingRecipes.init();
        SuperconductorRecipes.init();
        MiscRecipes.init();
        StagedRemovalRecipes.init();
        MetalCasingRecipes.init();
        PlasmaCondenserPlasmaRecipes.init();
         */

        MachineCraftingRecipes.init();
        CircuitRecipes.init();
    }

    public static void initChains() {
        /*

        NaquadahChain.init();
        NuclearChain.init();

        PlatinumSludgeGroupChain.init();
        TungstenChain.init();

        Batteries.init();



         */
        UHVMaterials.init();
        PolymerChain.init();
        GoldChain.init();
        PEEKChain.init();
        OpticalChain.init();
        FusionElementsChain.init();
        FusionComponents.init();
        WormholeGeneratorChain.init();
        HNIWChain.init();
        QuantumDotsChain.init();
        ZylonChain.init();
        UltimateMaterials.init();
        TaraniumChain.init();
        SensorEmitter.init();
        RheniumChain.init();
        REEChain.init();
        OrganometallicChains.init();
        VariousChains.init();
        /*

        FullereneChain.init();
        BariumChain.init();
        UraniumChain.init();
        VanadiumChain.init();
        IodineChain.init();
        ZirconChain.init();
        ZincChain.init();
        AluminiumChain.init();
        AmmoniaChain.init();
        ChromiumChain.init();
        LithiumChain.init();
        BrineChain.init();

        NanotubeChain.init();

        SuperconductorsSMDChain.init();

        NiobiumTantalumChain.init();
        Dyes.init();

        SeleniumChain.init();

        CosmicChain.init();

        DigitalInterfaces.init();
        InsulationWireAssemblyChain.init();
        ArcFurnaceOxidation.init();
        WetwareChain.init();

        CombinedChains.init();



        TriniumChain.init();


         */
    }
}