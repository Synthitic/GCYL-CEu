package com.fulltrix.gcyl.materials;

import gregtech.api.GTValues;
import gregtech.api.fluids.FluidBuilder;
import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialFlag;
import gregtech.api.unification.material.properties.BlastProperty;
import gregtech.api.unification.material.properties.OreProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.material.properties.WireProperties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static com.fulltrix.gcyl.materials.GCYLNuclearMaterials.*;
import static gregicality.multiblocks.api.unification.GCYMMaterialFlags.NO_ALLOY_BLAST_RECIPES;
import static gregicality.multiblocks.api.unification.GCYMMaterials.IncoloyMA956;
import static gregicality.multiblocks.api.unification.GCYMMaterials.Zeron100;
import static gregtech.api.GTValues.OpV;
import static gregtech.api.GTValues.UV;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.Materials.Duranium;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtech.api.unification.material.info.MaterialIconSet.ROUGH;
import static gregtech.api.unification.ore.OrePrefix.plate;
import static gregtech.integration.crafttweaker.material.MaterialPropertyExpansion.*;
import static gregtech.integration.crafttweaker.material.MaterialPropertyExpansion.addFluidPipes;
import static gregtech.integration.groovy.MaterialPropertyExpansion.addLiquid;

public class GCYLMaterialOverride {
    public static void materialChanges() {

        addDust(Germanium, 1, 0);

        //NUCLEAR STUFF
        List<Material> nuclearMats = new ArrayList<>();
        Collections.addAll(nuclearMats, Einsteinium, Fermium, Mendelevium);

        for (Material mat : nuclearMats) {
            addDust(mat, 1, 0);
            addLiquid(mat);
            mat.addFlags(GENERATE_PLATE);
        }

        Germanium.addFlags(GENERATE_PLATE);

        //EXT METAL ADDITIONS
        List<Material> e1mmats = new ArrayList<>();
        Collections.addAll(e1mmats, Tanzanite);
        for (Material mat : e1mmats) {
            for (MaterialFlag flag : EXT_METAL) {
                mat.addFlags(flag);
            }
        }

        //EXT2 METAL ADDITIONS
        List<Material> e2mmats = new ArrayList<>();
        Collections.addAll(e2mmats, MaragingSteel250, AbyssalAlloy, HG1223, BabbittAlloy, Inconel625);
        for (Material mat : e2mmats) {
            for (MaterialFlag flag : EXT2_METAL) {
                mat.addFlags(flag);
            }
        }

        //CORE METAL ADDITIONS
        List<Material> cmmats = new ArrayList<>();
        Collections.addAll(cmmats, Bohrium, Dubnium, Duranium, Seaborgium, Rhenium, Rutherfordium, NaquadahEnriched, IncoloyMA956, CosmicNeutronium);

        for (Material mat : cmmats) {
            for (MaterialFlag flag : CORE_METAL) {
                addIngot(mat);
                //addLiquid(mat);
                mat.addFlags(flag);
            }
        }

        //STD GEM ADDITIONS
        List<Material> stdgem = new ArrayList<>();
        Collections.addAll(stdgem, Vinteum);
        for (Material mat: stdgem) {
            for (MaterialFlag flag : STD_GEM) {
                mat.addFlags(flag);
            }
        }

        //PLATE ADDITIONS

        List<Material> platemats = new ArrayList<>();
        Collections.addAll(platemats, Vanadium, Tennessine, Rutile);
        for (Material mat : platemats) {
            mat.addFlags(GENERATE_PLATE);
        }



        //FOIL ADDITIONS
        List<Material> foilmats = new ArrayList<>();
        Collections.addAll(foilmats, Barium, Calcium, Thallium);
        for (Material mat : foilmats) {
            mat.addFlags(GENERATE_FOIL);
        }

        //FINE WIRE ADDITIONS
        List<Material> wirefinemats = new ArrayList<>();
        Collections.addAll(wirefinemats, AbyssalAlloy, Titanium, Iron);
        for (Material mat : wirefinemats) {
            mat.addFlags(GENERATE_FINE_WIRE);
        }

        //LONG STICK ADDITIONS
        List<Material> longstickmats = new ArrayList<>();
        Collections.addAll(longstickmats, NeodymiumMagnetic);
        for (Material mat : longstickmats) {
            mat.addFlags(GENERATE_LONG_ROD);
        }

        //STICK ADDITIONS
        List<Material> stickmats = new ArrayList<>();
        Collections.addAll(stickmats, ReinforcedEpoxyResin, Californium);
        for (Material mat : stickmats) {
            mat.addFlags(GENERATE_ROD);
        }

        //DUST ADDITIONS
        List<Material> dmats = new ArrayList<>();
        Collections.addAll(dmats, Dysprosium, Iodine, Gadolinium, Strontium, Holmium, Californium, Zirconium, Thallium, Rubidium, Technetium,
                Terbium, Promethium, Radium, Tellurium, Francium, Berkelium, Curium, Actinium, Protactinium, Scandium, Thulium, Hafnium, Astatine,
                Selenium, Praseodymium, Copernicium, Erbium, Polonium);
        for (Material mat : dmats) {
            addDust(mat, 1, 0);
        }

        //FLUID Additions
        List<Material> fmats = new ArrayList<>();
        Collections.addAll(fmats, Sodium, Bromine, AmmoniumChloride, Rubidium, Caesium, Francium, Polonium, Praseodymium, Ytterbium, Neptunium,
                ProtoAdamantium, Scandium, Radium, MetastableHassium, MetastableFlerovium, MetastableOganesson, HeavyQuarkDegenerateMatter, Dubnium,
                Sulfur, Calcium, Curium, Bohrium, Seaborgium, Copernicium, Rutherfordium,Meitnerium, Tennessine, Livermorium, Moscovium, Nihonium,
                Roentgenium, Astatine, Hafnium, RutheniumTetroxide);

        for (Material mat : fmats) {
            addLiquid(mat);
        }

        //SPECIFIC FLUID ADDITIONS

        //LIQUIDS
        //TODO: get proper temperatures
        List<Material> lmats = new ArrayList<>();
        Collections.addAll(lmats, Fluorine, Xenon, Helium3, Hydrogen, Nitrogen);
        for (Material mat : lmats) {
            addLiquid(mat, new FluidBuilder()
                    .temperature(40)
                    .color(mat.getMaterialRGB())
                    .name("liquid_" + mat.getName())
                    .translation("gregtech.fluid.liquid_generic"));
            mat.getProperty(PropertyKey.FLUID).setPrimaryKey(FluidStorageKeys.GAS);
        }

        //PLASMAS
        List<Material> pmats = new ArrayList<>();
        Collections.addAll(pmats, Carbon, Hydrogen, Helium3, Radon, Krypton, Neon, Magnesium, Silicon, Sulfur, Argon,Calcium, Titanium, Potassium);
        for (Material mat : pmats) {

            /*addPlasma(mat, new FluidBuilder()
                    .temperature(15000)
                    .color(mat.getMaterialRGB())
                    .name("plasma." + mat.getName())
                    .translation("gregtech.fluid.plasma"));
            mat.getProperty(PropertyKey.FLUID).setPrimaryKey(FluidStorageKeys.GAS);

             */
            addFluid(mat, "plasma", false);
        }

        //ORE ADDITIONS
        List<Material> omats = new ArrayList<>();
        Collections.addAll(omats, Lignite, Witherite, Barytocalcite, PlatinumMetallicPowder, IridiumMetalResidue, PreciousMetal,
                RarestMetalMixture, PalladiumMetallicPowder, Celestine, Caliche, Fluorite, Rhodocrosite, Columbite, Niter, Zircon, Bowieite,
                Zinc, Enargite, Andradite, Dolomite, Wollastonite, Kaolinite, Tenorite, Tennantite, Cuprite, Tellurium, Zirkelite,
                Arsenopyrite, Draconium, Iridium, Osmium, Rutile, Gallite, NetherStar, Plutonium, Uranium238, FluoroApatite);
        for (Material mat : omats) {
            addOre(mat, 1, 1,false);
        }

        //EMISSIVE ORES
        List<Material> eomats = new ArrayList<>();
        Collections.addAll(eomats,Triniite, NaquadricCompound, EnrichedNaquadricCompound, NaquadriaticCompound, Uranium);
        for (Material mat : eomats) {
            addOre(mat, 1,1,true);
        }

        //ORE BYPRODUCTS & Multipliers & OTHER STUFF TODO: Finish this
        OreProperty oreProp;

        oreProp = NaquadricCompound.getProperty(PropertyKey.ORE);
        oreProp.addOreByProducts(EnrichedNaquadricCompound);

        oreProp = EnrichedNaquadricCompound.getProperty(PropertyKey.ORE);
        oreProp.addOreByProducts(NaquadricCompound, NaquadriaticCompound);

        oreProp = Triniite.getProperty(PropertyKey.ORE);
        oreProp.addOreByProducts(NaquadricCompound, Bismuth, Thallium);

        oreProp = Zirkelite.getProperty(PropertyKey.ORE);
        oreProp.addOreByProducts(Thorium, Zirconium, Cerium);

        oreProp = Arsenopyrite.getProperty(PropertyKey.ORE);
        oreProp.addOreByProducts(Iron, Cobaltite, Cobalt);

        oreProp = PlatinumMetallicPowder.getProperty(PropertyKey.ORE);
        oreProp.setOreMultiplier(2);
        oreProp.setWashedIn(SodiumPersulfate);
        oreProp.addOreByProducts(Nickel,IridiumMetalResidue,RarestMetalMixture,PlatinumMetallicPowder);

        oreProp = PalladiumMetallicPowder.getProperty(PropertyKey.ORE);
        oreProp.setOreMultiplier(2);
        oreProp.setWashedIn(SodiumPersulfate);

        oreProp = Bowieite.getProperty(PropertyKey.ORE);
        oreProp.addOreByProducts(Nickel,PlatinumMetallicPowder,RarestMetalMixture,CrudeRhodiumMetal);

        oreProp = Tennantite.getProperty(PropertyKey.ORE);
        oreProp.setOreMultiplier(2);
        oreProp.addOreByProducts(Iron, Antimony, Zinc);

        oreProp = Tenorite.getProperty(PropertyKey.ORE);
        oreProp.addOreByProducts(Iron, Manganese, Malachite);
        oreProp.setDirectSmeltResult(Copper);

        oreProp = Cuprite.getProperty(PropertyKey.ORE);
        oreProp.setDirectSmeltResult(Copper);
        oreProp.addOreByProducts(Iron, Antimony, Malachite);

        oreProp = IridiumMetalResidue.getProperty(PropertyKey.ORE);
        oreProp.setWashedIn(SodiumPersulfate);

        oreProp = RarestMetalMixture.getProperty(PropertyKey.ORE);
        oreProp.setWashedIn(SodiumPersulfate);

        oreProp = Witherite.getProperty(PropertyKey.ORE);
        oreProp.setWashedIn(SodiumPersulfate);

        oreProp = PreciousMetal.getProperty(PropertyKey.ORE);
        oreProp.setOreMultiplier(2);

        oreProp = Columbite.getProperty(PropertyKey.ORE);
        oreProp.addOreByProducts(Iron, Manganese, Niobium);
            //BYPRODUCT OVERRIDES
        oreProp = Nickel.getProperty(PropertyKey.ORE);
        oreProp.setOreByProducts(Cobalt, PlatinumMetallicPowder, Iron);

        oreProp = Bornite.getProperty(PropertyKey.ORE);
        oreProp.setOreByProducts(Pyrite, Cobalt, Cadmium, PreciousMetal);

        oreProp = Chalcopyrite.getProperty(PropertyKey.ORE);
        oreProp.setOreByProducts(Pyrite, Cobalt, Cadmium, PreciousMetal);

        oreProp = Copper.getProperty(PropertyKey.ORE);
        oreProp.setOreByProducts(Cobalt, PreciousMetal, Nickel);

        oreProp = Magnetite.getProperty(PropertyKey.ORE);
        oreProp.setOreByProducts(Iron, PreciousMetal);

        oreProp = Pitchblende.getProperty(PropertyKey.ORE);
        oreProp.addOreByProducts(Thorium, Uranium, Lead);

        oreProp = Iridium.getProperty(PropertyKey.ORE);
        oreProp.setOreByProducts(PlatinumMetallicPowder, RarestMetalMixture);

        oreProp = Osmium.getProperty(PropertyKey.ORE);
        oreProp.setOreByProducts(IridiumMetalResidue);

        oreProp = Gallite.getProperty(PropertyKey.ORE);
        oreProp.setOreByProducts(Sulfur,Copper,Copper,Gallium);
        //SPECIFIC CASES

        NaquadahAlloy.addFlags(GENERATE_FINE_WIRE);

        Copper.addFlags(GENERATE_DENSE);
        StainlessSteel.addFlags(GENERATE_DENSE);
        Titanium.addFlags(GENERATE_DENSE);

        BlastProperty blastProperty;

        blastProperty = Hafnium.getProperty(PropertyKey.BLAST);
        blastProperty.setGasTier(BlastProperty.GasTier.HIGH);

        //ADD FRAMES
        List<Material> framemats = new ArrayList<>();
        Collections.addAll(framemats, Bohrium, Naquadria,Osmiridium);
        for(Material mat : framemats) {
            mat.addFlags(GENERATE_FRAME);
        }


        //FLUID PIPES
        addFluidPipes(Zeron100, 15000, 1750, true);
        addFluidPipes(Enderium, 1500,650,true);

        //ORE PREFIX IGNORE FIXES
        plate.removeIgnored(BorosilicateGlass);

        //TODO: invalid recipe issues
        /*
        List<Material> cablemats = new ArrayList<>();
        Collections.addAll(cablemats, MVSuperconductorBase,HVSuperconductorBase,EVSuperconductorBase,IVSuperconductorBase,LuVSuperconductorBase,
                ZPMSuperconductorBase,UVSuperconductorBase,UHVSuperconductorBase,UEVSuperconductorBase,UIVSuperconductorBase,UXVSuperconductorBase,
                OpVSuperconductorBase);
        for (Material mat : cablemats) {
            cableGtSingle.setIgnored(mat);
            cableGtDouble.setIgnored(mat);
            cableGtQuadruple.setIgnored(mat);
            cableGtOctal.setIgnored(mat);
            cableGtHex.setIgnored(mat);
        }
         */


        //DISABLE ALLOY BLAST for old superconductors
        List<Material> wireMats = new ArrayList<>();
        Collections.addAll(wireMats, ManganesePhosphide, MagnesiumDiboride, MercuryBariumCalciumCuprate, UraniumTriplatinum, SamariumIronArsenicOxide,
                IndiumTinBariumTitaniumCuprate, UraniumRhodiumDinaquadide, EnrichedNaquadahTriniumEuropiumDuranide, RutheniumTriniumAmericiumNeutronate);

        for(Material mat : wireMats) {
            mat.addFlags(NO_ALLOY_BLAST_RECIPES);
        }

        //LENSES
        List<Material> lensmats = new ArrayList<>();
        Collections.addAll(lensmats, Amethyst);
        for (Material mat : lensmats) {
            mat.addFlags(GENERATE_LENS);
        }

        //WIRE PROPERTIES
        Neutronium.setProperty(PropertyKey.WIRE, new WireProperties((int) GTValues.V[OpV], 4, 64));



        //REPLACE MATERIALS
        IridiumMetalResidue.setFormula("Ir2O4(SiO2)2Au3", true);
        IridiumMetalResidue.setMaterialRGB(0x846649);
        IridiumMetalResidue.setMaterialIconSet(ROUGH);

        RarestMetalMixture.setFormula("Ir2O2(SiO2)2Au3?",true);
        RarestMetalMixture.setMaterialRGB(0x644629);
        RarestMetalMixture.setMaterialIconSet(ROUGH);

        NaquadahAlloy.setFormula("Nq(Ir3Os)",true);
    }

    public static void tempMaterialModifications() {

        /*
        List<Material> #mats = new ArrayList<>();
        Collections.addAll(#mats, );
        for (Material mat : #mats) {
            mat.addFlags();
        }
         */

        //Add rounds
        List<Material> rmats = new ArrayList<>();
        Collections.addAll(rmats, HDCS, HastelloyX78, HSSG, HSSE, Bohrium, Neutronium, CosmicNeutronium);
        for (Material mat : rmats) {
            mat.addFlags(GENERATE_ROUND);
        }

        //add fine wire
        List<Material> wirefinemats = new ArrayList<>();
        Collections.addAll(wirefinemats, Trinium, TinAlloy, ReinforcedEpoxyResin, HSSS, Ruthenium, Plutonium, Cerium,
                HVSuperconductor, IVSuperconductor, LuVSuperconductor, ZPMSuperconductor, UVSuperconductor, UHVSuperconductor, UEVSuperconductor, UIVSuperconductor, UXVSuperconductor, OpVSuperconductor, MAXSuperconductor);
        for (Material mat : wirefinemats) {
            mat.addFlags(GENERATE_FINE_WIRE);
        }

        //add gears
        List<Material> gearmats = new ArrayList<>();
        Collections.addAll(gearmats, RhodiumPlatedPalladium, EglinSteel, Magnalium, Inconel625, QCDMatter, AbyssalAlloy, BabbittAlloy, Nitinol60, HG1223);
        for (Material mat : gearmats) {
            mat.addFlags(GENERATE_GEAR);
        }

        //Small Specific cases
        HSSG.addFlags(GENERATE_RING);
        Osmium.addFlags(GENERATE_RING);
        Neutronium.addFlags(GENERATE_RING);
        HSSE.addFlags(GENERATE_SMALL_GEAR);
        Zeron100.addFlags(GENERATE_BOLT_SCREW);
        Trinium.addFlags(GENERATE_FRAME);
        Naquadria.addFlags(GENERATE_DENSE);
        Lead.addFlags(GENERATE_DENSE);
        NaquadahAlloy.addFlags(DISABLE_DECOMPOSITION);
        NaquadahAlloy.addFlags(NO_ALLOY_BLAST_RECIPES);
        YttriumBariumCuprate.addFlags(NO_ALLOY_BLAST_RECIPES);
        Glowstone.addFlags(DISABLE_DECOMPOSITION);
        Pyrochlore.addFlags(DISABLE_DECOMPOSITION);
        Plutonium239.addFlags(GENERATE_ROD);



        //ADD ROTORS
        List<Material> rotmats = new ArrayList<>();
        Collections.addAll(rotmats, HSSG, Tritanium, HSSE);
        for (Material mat : rotmats) {
            mat.addFlags(GENERATE_ROTOR);
        }

        //Cable
        Duranium.setProperty(PropertyKey.WIRE, new WireProperties((int) GTValues.V[UV], 1, 16));
        Titanium.setProperty(PropertyKey.WIRE, new WireProperties((int) GTValues.V[GTValues.EV], 4,2));

        WireProperties wireProp;

        wireProp = YttriumBariumCuprate.getProperty(PropertyKey.WIRE);
        wireProp.setVoltage((int) GTValues.V[GTValues.LuV]);
        wireProp.setAmperage(4);
        wireProp.setLossPerBlock(4);



    }
}
