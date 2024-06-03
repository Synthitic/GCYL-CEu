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

import static gregtech.api.unification.material.Materials.*;
import static gregtech.common.blocks.BlockWireCoil.CoilType.*;


@JEIPlugin
public class JEIGCYLPlugin implements IModPlugin {
    private IIngredientBlacklist itemBlacklist;
    private IIngredientRegistry iItemRegistry;
    public static IJeiRuntime jeiRuntime;

    @Override
    public void register(IModRegistry registry) {
        itemBlacklist = registry.getJeiHelpers().getIngredientBlacklist();
        iItemRegistry = registry.getIngredientRegistry();

        //The list has to be backwards for some godforsaken reason TODO: make it work on manganese phosphide all the way. hide fluids
        List<Material> oldSuperConductors = Arrays.asList(RutheniumTriniumAmericiumNeutronate, EnrichedNaquadahTriniumEuropiumDuranide, UraniumRhodiumDinaquadide,
                IndiumTinBariumTitaniumCuprate, SamariumIronArsenicOxide, SamariumIronArsenicOxide, MercuryBariumCalciumCuprate, MagnesiumDiboride, ManganesePhosphide);

        for(Material mat : oldSuperConductors) {
            superConductorRemoval(mat);
        }

        //TODO hide base infinite water cover from gregtech

    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
        this.jeiRuntime = jeiRuntime;

        itemBlacklist.addIngredientToBlacklist(GCYMMetaTileEntities.LARGE_MIXER.getStackForm());
        itemBlacklist.addIngredientToBlacklist(GCYMMetaTileEntities.LARGE_CENTRIFUGE.getStackForm());

        itemBlacklist.addIngredientToBlacklist(MetaBlocks.WIRE_COIL.getItemVariant(TRINIUM));
        itemBlacklist.addIngredientToBlacklist(MetaBlocks.WIRE_COIL.getItemVariant(TRITANIUM));


        //hide old superconductors



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
