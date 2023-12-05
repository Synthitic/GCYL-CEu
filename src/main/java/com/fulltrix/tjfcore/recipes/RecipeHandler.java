package com.fulltrix.tjfcore.recipes;

import com.fulltrix.tjfcore.recipes.categories.circuits.CircuitRecipes;
import com.fulltrix.tjfcore.recipes.chain.GoldChain;
import com.fulltrix.tjfcore.recipes.chain.OpticalChain;
import com.fulltrix.tjfcore.recipes.chain.PEEKChain;
import com.fulltrix.tjfcore.recipes.chain.PolymerChain;

public class RecipeHandler {
    public static void initRecipes() {

        /*
        RecipeOverride.init();

        MachineCraftingRecipes.init();
        ComponentRecipes.init();
        MetaItemRecipes.init();
        CasingRecipes.init();
        SuperconductorRecipes.init();
        MiscRecipes.init();
        StagedRemovalRecipes.init();
        MetalCasingRecipes.init();
        PlasmaCondenserPlasmaRecipes.init();
         */

        CircuitRecipes.init();
    }

    public static void initChains() {
        /*

        NaquadahChain.init();
        NuclearChain.init();

        PlatinumSludgeGroupChain.init();
        TungstenChain.init();
        REEChain.init();
        Batteries.init();
        RheniumChain.init();
        UHVMaterials.init();

         */
        PolymerChain.init();
        GoldChain.init();
        PEEKChain.init();
        OpticalChain.init();
        /*
        ZylonChain.init();
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
        FusionElementsChain.init();
        NanotubeChain.init();
        VariousChains.init();
        SuperconductorsSMDChain.init();
        FusionComponents.init();
        NiobiumTantalumChain.init();
        Dyes.init();
        SensorEmitter.init();
        SeleniumChain.init();
        WormholeGeneratorChain.init();
        CosmicChain.init();
        UltimateMaterials.init();
        DigitalInterfaces.init();
        InsulationWireAssemblyChain.init();
        ArcFurnaceOxidation.init();
        WetwareChain.init();

        CombinedChains.init();
        OrganometallicChains.init();
        QuantumDotsChain.init();
        HNIWChain.init();
        TriniumChain.init();
        TaraniumChain.init();

         */
    }
}