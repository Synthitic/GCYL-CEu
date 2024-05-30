package com.fulltrix.tjfcore.recipes;

import com.fulltrix.tjfcore.recipes.categories.RecipeOverride;
import com.fulltrix.tjfcore.recipes.categories.circuits.CircuitRecipes;
import com.fulltrix.tjfcore.recipes.categories.machines.MachineCraftingRecipes;
import com.fulltrix.tjfcore.recipes.chain.*;

public class RecipeHandler {
    public static void initRecipes() {


        RecipeOverride.init();

        /*
        ComponentRecipes.init();
        MetaItemRecipes.init();
        CasingRecipes.init();
        SuperconductorRecipes.init();
        MiscRecipes.init();
        StagedRemovalRecipes.init();
        MetalCasingRecipes.init();
         */

        MachineCraftingRecipes.init();
        CircuitRecipes.init();
        PlasmaCondenserPlasmaRecipes.init();
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

        /*
        UraniumChain.init();
        Dyes.init();
        DigitalInterfaces.init();
        ArcFurnaceOxidation.init();
        TriniumChain.init();
         */
    }

}