package com.fulltrix.gcyl.recipes;

import com.fulltrix.gcyl.recipes.categories.*;
import com.fulltrix.gcyl.recipes.categories.circuits.CircuitRecipes;
import com.fulltrix.gcyl.recipes.categories.machines.GreenhouseRecipes;
import com.fulltrix.gcyl.recipes.categories.machines.MachineCraftingRecipes;
import com.fulltrix.gcyl.recipes.chain.*;

public class RecipeHandler {
    public static void initRecipes() {


        RecipeOverride.init();

        /*
        StagedRemovalRecipes.init();
         */

        MetaItemRecipes.init();
        ComponentRecipes.init();
        MachineCraftingRecipes.init();
        CircuitRecipes.init();
        PlasmaCondenserPlasmaRecipes.init();
        MiscRecipes.init();
        MetalCasingRecipes.init();
        SuperconductorRecipes.init();
        CasingRecipes.init();
        DeepMinerRecipes.init();
        GreenhouseRecipes.init();
    }

    public static void initChains() {

        NuclearChain.init();
        Batteries.init();
        PlatinumSludgeGroupChain.init();
        TungstenChain.init();
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
        AluminiumChain.init();
        AmmoniaChain.init();
        BariumChain.init();
        BrineChain.init();
        FullereneChain.init();
        InsulationWireAssemblyChain.init();
        IodineChain.init();
        LithiumChain.init();
        NanotubeChain.init();
        ChromiumChain.init();
        CosmicChain.init();
        NiobiumTantalumChain.init();
        SeleniumChain.init();
        SuperconductorsSMDChain.init();
        VanadiumChain.init();
        WetwareChain.init();
        ZirconChain.init();
        ZincChain.init();
        CombinedChains.init();
        NaquadahChain.init();
        TriniumChain.init();
        Dyes.init();
        ArcFurnaceOxidation.init();

        /*
        UraniumChain.init();
        DigitalInterfaces.init();
         */
    }

}