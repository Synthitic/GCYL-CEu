package com.fulltrix.gcyl.jei;


import gregicality.multiblocks.common.metatileentities.GCYMMetaTileEntities;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;
import mezz.jei.api.*;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.ingredients.IIngredientRegistry;

import java.util.Arrays;
import java.util.List;

import static com.fulltrix.gcyl.machines.GCYLTileEntities.WIRELESS_ENERGY_HATCH_OUTPUT;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.common.blocks.BlockWireCoil.CoilType.*;
import static gregtech.common.items.MetaItems.*;
import static gregtech.common.metatileentities.MetaTileEntities.CIRCUIT_ASSEMBLER;


@JEIPlugin
public class JEIGCYLPlugin implements IModPlugin {
    private IIngredientBlacklist itemBlacklist;
    private IIngredientRegistry iItemRegistry;
    public static IJeiRuntime jeiRuntime;

    @Override
    public void register(IModRegistry registry) {
        itemBlacklist = registry.getJeiHelpers().getIngredientBlacklist();
        iItemRegistry = registry.getIngredientRegistry();



    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
        this.jeiRuntime = jeiRuntime;

        itemBlacklist.addIngredientToBlacklist(GCYMMetaTileEntities.LARGE_MIXER.getStackForm());
        itemBlacklist.addIngredientToBlacklist(GCYMMetaTileEntities.LARGE_CENTRIFUGE.getStackForm());
        itemBlacklist.addIngredientToBlacklist(GCYMMetaTileEntities.ELECTRIC_IMPLOSION_COMPRESSOR.getStackForm());

        itemBlacklist.addIngredientToBlacklist(MetaBlocks.WIRE_COIL.getItemVariant(TRINIUM));
        itemBlacklist.addIngredientToBlacklist(MetaBlocks.WIRE_COIL.getItemVariant(TRITANIUM));

        itemBlacklist.addIngredientToBlacklist(COVER_INFINITE_WATER.getStackForm());

        itemBlacklist.addIngredientToBlacklist(MICROPROCESSOR_LV.getStackForm());
        itemBlacklist.addIngredientToBlacklist(WORKSTATION_EV.getStackForm());
        itemBlacklist.addIngredientToBlacklist(PROCESSOR_MV.getStackForm());
        itemBlacklist.addIngredientToBlacklist(ADVANCED_SMD_CAPACITOR.getStackForm());
        itemBlacklist.addIngredientToBlacklist(ADVANCED_SMD_INDUCTOR.getStackForm());
        itemBlacklist.addIngredientToBlacklist(ADVANCED_SMD_DIODE.getStackForm());
        itemBlacklist.addIngredientToBlacklist(ADVANCED_SMD_RESISTOR.getStackForm());
        itemBlacklist.addIngredientToBlacklist(ADVANCED_SMD_TRANSISTOR.getStackForm());
        itemBlacklist.addIngredientToBlacklist(SMD_INDUCTOR.getStackForm());

        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.dust, NaquadriaSulfate));
        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.dustSmall, NaquadriaSulfate));
        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.dustTiny, NaquadriaSulfate));

        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.dust, EnrichedNaquadahSulfate));
        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.dustSmall, EnrichedNaquadahSulfate));
        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.dustTiny, EnrichedNaquadahSulfate));

        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.dust, TriniumSulfide));
        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.dustSmall, TriniumSulfide));
        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.dustTiny, TriniumSulfide));

        for (int i = 5; i < 14; i++) {
            itemBlacklist.addIngredientToBlacklist(CIRCUIT_ASSEMBLER[i].getStackForm());
        }

        for (int i = 0; i < WIRELESS_ENERGY_HATCH_OUTPUT.length - 1; i++) {
            itemBlacklist.addIngredientToBlacklist(WIRELESS_ENERGY_HATCH_OUTPUT[i].getStackForm());
        }




        //The list has to be in this order for some godforsaken reason TODO: make it work on manganese phosphide all the way. hide fluids
        //hide old superconductors
        List<Material> oldSuperConductors = Arrays.asList(UraniumTriplatinum, RutheniumTriniumAmericiumNeutronate, EnrichedNaquadahTriniumEuropiumDuranide, UraniumRhodiumDinaquadide,
                IndiumTinBariumTitaniumCuprate, SamariumIronArsenicOxide, SamariumIronArsenicOxide, MercuryBariumCalciumCuprate, MagnesiumDiboride, ManganesePhosphide);

        for(Material mat : oldSuperConductors) {
            superConductorRemoval(mat);
        }


    }

    private void superConductorRemoval(Material mat) {
        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.wireGtSingle, mat));
        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.wireGtDouble, mat));
        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.wireGtQuadruple, mat));
        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.wireGtOctal, mat));
        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.wireGtHex, mat));
        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.ingot, mat));
        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.ingotHot, mat));
        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.block, mat));
        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.dustSmall, mat));
        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.dustTiny, mat));
        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.dust, mat));
        itemBlacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.nugget, mat));
    }
}
