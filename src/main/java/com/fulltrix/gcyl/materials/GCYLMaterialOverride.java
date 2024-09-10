package com.fulltrix.gcyl.materials;

import com.fulltrix.gcyl.GCYLConfig;
import gregtech.api.GTValues;
import gregtech.api.fluids.FluidBuilder;
import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialFlag;
import gregtech.api.unification.material.properties.*;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.fulltrix.gcyl.api.recipes.GCYLMaterialFlags.NO_MIXER_RECIPE;
import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static gregicality.multiblocks.api.unification.GCYMMaterialFlags.NO_ALLOY_BLAST_RECIPES;
import static gregicality.multiblocks.api.unification.GCYMMaterials.*;
import static gregtech.api.GTValues.OpV;
import static gregtech.api.GTValues.UV;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtech.api.unification.material.info.MaterialIconSet.ROUGH;
import static gregtech.api.unification.ore.OrePrefix.plate;
import static gregtech.integration.crafttweaker.material.MaterialPropertyExpansion.*;

public class GCYLMaterialOverride {

    public static void init() {
        materialChanges();
        coolants();
        tempMaterialModifications();
    }

    private static void coolants() {

        CarbonDioxide.setProperty(PropertyKey.COOLANT,
                new CoolantProperty(CarbonDioxide, SupercriticalCO2, FluidStorageKeys.LIQUID,13.,28,195,380000,846));

        Helium3.setProperty(PropertyKey.COOLANT,
                new CoolantProperty(Helium3, HotHPHelium3,FluidStorageKeys.LIQUID, 0.,262,3,8600,5193));

        Helium4.setProperty(PropertyKey.COOLANT,
                new CoolantProperty(Helium4, HotHPHelium4,FluidStorageKeys.LIQUID, 0., 252, 4, 20700, 5193));

        FLiBe.setProperty(PropertyKey.COOLANT,
                new CoolantProperty(FLiBe, SupercriticalFLiBe,FluidStorageKeys.LIQUID,0.001,1830,1703,3330000,2386));

        FLiNaK.setProperty(PropertyKey.COOLANT,
                new CoolantProperty(FLiNaK, SupercriticalFLiNaK,FluidStorageKeys.LIQUID,0., 1290,1844,9520000,1854));

        SodiumPotassiumAlloy.setProperty(PropertyKey.COOLANT,
                new CoolantProperty(SodiumPotassiumAlloy, SupercriticalSodiumPotassiumAlloy,FluidStorageKeys.LIQUID,0.001,400,1059, 2500000, 1191));


        Sodium.setProperty(PropertyKey.COOLANT,
                new CoolantProperty(Sodium, HotLiquidSodium,FluidStorageKeys.LIQUID, 0.05, 120100,1156,4250000,1230));

        Mercury.setProperty(PropertyKey.COOLANT,
                new CoolantProperty(Mercury, HotMercury,FluidStorageKeys.LIQUID, 0., 13800,630,295000,140));

        Tin.setProperty(PropertyKey.COOLANT,
                new CoolantProperty(Tin, HotLiquidTin, FluidStorageKeys.LIQUID,0.01, 54000, 2875, 2440000,217));

        Lead.setProperty(PropertyKey.COOLANT,
                new CoolantProperty(Lead, HotLiquidLead,FluidStorageKeys.LIQUID,0.06,55000, 2022,866000,139));

        LeadBismuthEutectic.setProperty(PropertyKey.COOLANT,
                new CoolantProperty(LeadBismuthEutectic, SupercriticalLeadBismuthEutectic,FluidStorageKeys.LIQUID, 0.01,10800,1944,852000,147));

        BoricAcid.setProperty(PropertyKey.COOLANT,
                new CoolantProperty(BoricAcid, HotLiquidBoronTrioxide,FluidStorageKeys.LIQUID,0.,370,573,8100000,1392)
                        .setAccumulatesHydrogen(true));


    }

    private static void materialChanges() {

        addDust(Germanium, 1, 0);

        //NUCLEAR STUFF
        List<Material> nuclearMats = new ArrayList<>();
        Collections.addAll(nuclearMats, Einsteinium, Fermium, Mendelevium);

        for (Material mat : nuclearMats) {
            addDust(mat, 1, 0);
            mat.setProperty(PropertyKey.FLUID, new FluidProperty(FluidStorageKeys.LIQUID, new FluidBuilder()));
            //addLiquid(mat);
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
        Collections.addAll(cmmats, Bohrium, Dubnium, Duranium, Seaborgium, Rhenium, Rutherfordium, NaquadahEnriched, IncoloyMA956, CosmicNeutronium, QCDMatter, Cerium, Barium, Calcium, Californium, Thallium, Tennessine, Plutonium);

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
        Collections.addAll(foilmats, Barium, Calcium, Thallium, EnrichedNaquadahAlloy);
        for (Material mat : foilmats) {
            mat.addFlags(GENERATE_FOIL);
        }

        //FINE WIRE ADDITIONS
        List<Material> wirefinemats = new ArrayList<>();
        Collections.addAll(wirefinemats, AbyssalAlloy, Titanium, Iron, Neutronium);
        for (Material mat : wirefinemats) {
            mat.addFlags(GENERATE_FINE_WIRE);
        }

        //LONG STICK ADDITIONS
        List<Material> longstickmats = new ArrayList<>();
        Collections.addAll(longstickmats, NeodymiumMagnetic, Mendelevium, IronMagnetic, SteelMagnetic, Chrome);
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
                Selenium, Praseodymium, Copernicium, Erbium, Polonium, Nihonium, Moscovium, Livermorium, Tennessine);
        for (Material mat : dmats) {
            addDust(mat, 1, 0);
        }

        //FLUID Additions
        List<Material> fmats = new ArrayList<>();
        Collections.addAll(fmats, Sodium, Bromine, AmmoniumChloride, Rubidium, Caesium, Francium, Polonium, Praseodymium, Ytterbium, Neptunium,
                ProtoAdamantium, Scandium, Radium, MetastableHassium, MetastableFlerovium, MetastableOganesson, HeavyQuarkDegenerateMatter, Dubnium,
                Sulfur, Calcium, Curium, Bohrium, Seaborgium, Copernicium, Rutherfordium,Meitnerium, Tennessine, Livermorium, Moscovium, Nihonium,
                Roentgenium, Astatine, Hafnium, RutheniumTetroxide, Lawrencium, Nobelium, Germanium, NeodymiumMagnetic, SamariumMagnetic, Lafium,
                PlatinumGroupSludge, Plutonium);

        for (Material mat : fmats) {
            mat.setProperty(PropertyKey.FLUID, new FluidProperty(FluidStorageKeys.LIQUID, new FluidBuilder()));
            //addLiquid(mat);
        }

        //GAS Additions
        List<Material> gmats = new ArrayList<>();
        Collections.addAll(gmats, OsmiumTetroxide, RutheniumTetroxide);
        for(Material mat : gmats) {
            try {
                mat.setProperty(PropertyKey.FLUID, new FluidProperty(FluidStorageKeys.GAS, new FluidBuilder()));
            } catch (Exception e) {
                mat.getProperty(PropertyKey.FLUID).enqueueRegistration(FluidStorageKeys.GAS, new FluidBuilder());
            }
        }

        //SPECIFIC FLUID ADDITIONS

        //LIQUIDS
        //TODO: get proper temperatures
        List<Material> lmats = new ArrayList<>();
        Collections.addAll(lmats, Fluorine, Xenon, Helium3, Hydrogen, Nitrogen, DeepNetherGas, DeepOverworldGas, CarbonDioxide, Helium4);
        for (Material mat : lmats) {
            mat.getProperty(PropertyKey.FLUID).enqueueRegistration(FluidStorageKeys.LIQUID, new FluidBuilder()
                    .temperature(1)
                    .name("liquid_" + mat.getName())
                    .translation("gregtech.fluid.liquid_generic"));
            mat.getProperty(PropertyKey.FLUID).setPrimaryKey(FluidStorageKeys.GAS);
        }

        //PLASMAS
        List<Material> pmats = new ArrayList<>();
        Collections.addAll(pmats, Carbon, Hydrogen, Helium3, Radon, Krypton, Neon, Magnesium, Silicon, Sulfur,Calcium, Titanium, Potassium, Bismuth);
        for (Material mat : pmats) {

            /*addPlasma(mat, new FluidBuilder()
                    .temperature(15000)
                    .color(mat.getMaterialRGB())
                    .name("plasma." + mat.getName())
                    .translation("gregtech.fluid.plasma"));
            mat.getProperty(PropertyKey.FLUID).setPrimaryKey(FluidStorageKeys.GAS);

             */
            mat.getProperty(PropertyKey.FLUID).enqueueRegistration(FluidStorageKeys.PLASMA, new FluidBuilder()
                    .name("plasma."+ mat.getName())
                    .translation("gregtech.fluid.plasma"));
            mat.getProperty(PropertyKey.FLUID).setPrimaryKey(FluidStorageKeys.GAS);
            //addFluid(mat, "plasma", false);
        }

        //ORE ADDITIONS
        List<Material> omats = new ArrayList<>();
        Collections.addAll(omats, Lignite, Witherite, Barytocalcite, PlatinumMetallicPowder, IridiumMetalResidue, PreciousMetal,
                RarestMetalMixture, PalladiumMetallicPowder, Celestine, Caliche, Fluorite, Rhodocrosite, Columbite, Niter, Zircon,
                Bowieite, Zinc, Enargite, Andradite, Dolomite, Wollastonite, Kaolinite, Tenorite, Tennantite, Cuprite, Tellurium,
                Zirkelite, Arsenopyrite, Draconium, Iridium, Osmium, Rutile, Gallite, NetherStar, Uranium238, FluoroApatite,
                RhodiumSalt, SodiumRuthenate, Tritanium, Arsenic, Barium, Titanium, Tungsten, Tantalum, Duranium);
        for (Material mat : omats) {

            if(GCYLConfig.recipes.useNewPlatinumChain) {
                if (mat == RhodiumSalt || mat == SodiumRuthenate || mat == IridiumMetalResidue || mat == RarestMetalMixture)
                    continue;
            }

            addOre(mat, 1, 1,false);
        }

        //EMISSIVE ORES
        List<Material> eomats = new ArrayList<>();
        Collections.addAll(eomats,Triniite, NaquadricCompound, EnrichedNaquadricCompound, NaquadriaticCompound, Uranium, Plutonium);
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

        if(GCYLConfig.recipes.useNewPlatinumChain) {

            oreProp = PlatinumMetallicPowder.getProperty(PropertyKey.ORE);
            oreProp.setOreMultiplier(2);

            oreProp = PalladiumMetallicPowder.getProperty(PropertyKey.ORE);
            oreProp.setOreMultiplier(2);


        } else {
            oreProp = PlatinumMetallicPowder.getProperty(PropertyKey.ORE);
            oreProp.setOreMultiplier(2);
            oreProp.setWashedIn(SodiumPersulfate);
            oreProp.addOreByProducts(Nickel, IridiumMetalResidue, RarestMetalMixture, PlatinumMetallicPowder);

            oreProp = PalladiumMetallicPowder.getProperty(PropertyKey.ORE);
            oreProp.setOreMultiplier(2);
            oreProp.setWashedIn(SodiumPersulfate);

            oreProp = IridiumMetalResidue.getProperty(PropertyKey.ORE);
            oreProp.setWashedIn(SodiumPersulfate);

            oreProp = RarestMetalMixture.getProperty(PropertyKey.ORE);
            oreProp.setWashedIn(SodiumPersulfate);

            oreProp = Iridium.getProperty(PropertyKey.ORE);
            oreProp.setOreByProducts(PlatinumMetallicPowder, RarestMetalMixture);

            oreProp = Osmium.getProperty(PropertyKey.ORE);
            oreProp.setOreByProducts(IridiumMetalResidue);
        }



        oreProp = Bowieite.getProperty(PropertyKey.ORE);

        if(GCYLConfig.recipes.useNewPlatinumChain) //TODO: add additional higher tier pgs materials to this
            oreProp.addOreByProducts(Nickel, PlatinumGroupSludge);
        else
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

        oreProp = Witherite.getProperty(PropertyKey.ORE);
        oreProp.setWashedIn(SodiumPersulfate);

        oreProp = PreciousMetal.getProperty(PropertyKey.ORE);
        oreProp.setOreMultiplier(2);

        oreProp = Columbite.getProperty(PropertyKey.ORE);
        oreProp.addOreByProducts(Iron, Manganese, Niobium);
            //BYPRODUCT OVERRIDES
        oreProp = Pollucite.getProperty(PropertyKey.ORE);
        oreProp.setOreByProducts(Caesium, Aluminium, Rubidium);

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

        oreProp = Gallite.getProperty(PropertyKey.ORE);
        oreProp.setOreByProducts(Sulfur,Copper,Copper,Gallium);
        //SPECIFIC CASES

        NaquadahAlloy.addFlags(GENERATE_FINE_WIRE);

        //DENSE PLATE
        List<Material> densemats = new ArrayList<>();
        Collections.addAll(densemats,Copper, StainlessSteel, Titanium, WatertightSteel, Ruridit, HSSS, Iridium, Steel, Aluminium, Tritanium, NaquadriaticTaranium, HastelloyK243, EnrichedNaquadahAlloy, HSSE, Osmiridium, Rubber, SiliconeRubber, StyreneButadieneRubber);
        for(Material mat : densemats) {
            mat.addFlags(GENERATE_DENSE);
        }

        BlastProperty blastProperty;

        blastProperty = Hafnium.getProperty(PropertyKey.BLAST);
        blastProperty.setGasTier(BlastProperty.GasTier.HIGH);

        //ADD FRAMES
        List<Material> framemats = new ArrayList<>();
        Collections.addAll(framemats, Bohrium, Naquadria,Osmiridium, Inconel625, RhodiumPlatedPalladium);
        for(Material mat : framemats) {
            mat.addFlags(GENERATE_FRAME);
        }


        //FLUID PIPES
        addFluidPipes(Zeron100, 15000, 1750, true);
        addFluidPipes(Enderium, 1500,650,true);
        addFluidPipes(CosmicNeutronium, 1000000, 50000, true,true,true,true);

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
            mat.addFlags(NO_MIXER_RECIPE);
            mat.addFlags(DISABLE_DECOMPOSITION);
        }

        //LENSES
        List<Material> lensmats = new ArrayList<>();
        Collections.addAll(lensmats, Amethyst);
        for (Material mat : lensmats) {
            mat.addFlags(GENERATE_LENS);
        }

        //WIRE PROPERTIES
        Neutronium.setProperty(PropertyKey.WIRE, new WireProperties((int) GTValues.V[OpV], 4, 64));

        if(!GCYLConfig.recipes.hardMode) {
            Draconium.setProperty(PropertyKey.WIRE, new WireProperties((int) GTValues.V[GTValues.UHV], 10, 0, true));
        }

        AwakenDraconium.setProperty(PropertyKey.WIRE, new WireProperties((int) GTValues.V[GTValues.UEV], 100 , 0, true));
        Chaos.setProperty(PropertyKey.WIRE, new WireProperties((int) GTValues.V[GTValues.UIV], 1000 , 0, true));
        ChaosAlloy.setProperty(PropertyKey.WIRE, new WireProperties((int) GTValues.V[OpV], 10000 , 0, true));

        //REPLACE MATERIALS
        IridiumMetalResidue.setFormula("Ir2O4(SiO2)2Au3", true);
        IridiumMetalResidue.setMaterialRGB(0x846649);
        IridiumMetalResidue.setMaterialIconSet(ROUGH);

        RarestMetalMixture.setFormula("Ir2O2(SiO2)2Au3?",true);
        RarestMetalMixture.setMaterialRGB(0x644629);
        RarestMetalMixture.setMaterialIconSet(ROUGH);

        NaquadahAlloy.setFormula("Nq(Ir3Os)",true);

        OreDictUnifier.registerOre(new ItemStack(Blocks.CLAY), OrePrefix.ore, Clay);

        //Liquid Temperatures
        Sodium.getProperty(PropertyKey.FLUID).getQueuedBuilder(FluidStorageKeys.LIQUID).temperature(370);
    }

    private static void tempMaterialModifications() {

        /*
        List<Material> #mats = new ArrayList<>();
        Collections.addAll(#mats, );
        for (Material mat : #mats) {
            mat.addFlags();
        }
         */

        //Add rounds
        List<Material> rmats = new ArrayList<>();
        Collections.addAll(rmats, HDCS, HastelloyX78, HSSG, HSSE, Bohrium, Neutronium, CosmicNeutronium, QCDMatter);
        for (Material mat : rmats) {
            mat.addFlags(GENERATE_ROUND);
        }

        //add fine wire
        List<Material> wirefinemats = new ArrayList<>();
        Collections.addAll(wirefinemats, Trinium, TinAlloy, ReinforcedEpoxyResin, HSSS, Ruthenium, Plutonium, Cerium, LanthanumFullereneNanotubes,
                HVSuperconductor, IVSuperconductor, LuVSuperconductor, ZPMSuperconductor, UVSuperconductor, UHVSuperconductor, UEVSuperconductor, UIVSuperconductor, UXVSuperconductor, OpVSuperconductor, MAXSuperconductor);
        for (Material mat : wirefinemats) {
            mat.addFlags(GENERATE_FINE_WIRE);
        }

        //add gears
        List<Material> gearmats = new ArrayList<>();
        Collections.addAll(gearmats, RhodiumPlatedPalladium, EglinSteel, Magnalium, Inconel625, AbyssalAlloy, BabbittAlloy, Nitinol60, HG1223);
        for (Material mat : gearmats) {
            mat.addFlags(GENERATE_GEAR);
        }

        //no mixer recipe
        List<Material> mixermats = new ArrayList<>();
        Collections.addAll(mixermats, HSSS, Osmiridium, WatertightSteel, MaragingSteel300, Stellite100, HastelloyC276, HastelloyX, Trinaquadalloy, Zeron100, TitaniumCarbide, TantalumCarbide, HSLASteel, BlackSteel, Zircaloy, BlackBronze,
                Inconel, SterlingSilver, NaquadahAlloy, LVSuperconductorBase, MVSuperconductorBase, HVSuperconductorBase,EVSuperconductorBase,IVSuperconductorBase,LuVSuperconductorBase,ZPMSuperconductorBase,UVSuperconductorBase,UHVSuperconductorBase,
                UEVSuperconductorBase,UIVSuperconductorBase,UXVSuperconductorBase,OpVSuperconductorBase);
        for(Material mat: mixermats) {
            mat.addFlags(NO_MIXER_RECIPE);
        }

        //add springs
        List<Material> springmats = new ArrayList<>();
        Collections.addAll(springmats, Pikyonium, NaquadriaticTaranium, TungstenTitaniumCarbide, Cinobite, Duranium);
        for(Material mat: springmats) {
            mat.addFlags(GENERATE_SPRING);
        }

        //add screws
        List<Material> screwMats = new ArrayList<>();
        Collections.addAll(screwMats, Zeron100);
        for(Material mat: screwMats) {
            mat.addFlags(GENERATE_BOLT_SCREW);
        }

        //Small Specific cases
        HSSG.addFlags(GENERATE_RING);
        Osmium.addFlags(GENERATE_RING);
        Neutronium.addFlags(GENERATE_RING);
        HSSE.addFlags(GENERATE_SMALL_GEAR);
        Neutronium.addFlags(GENERATE_SMALL_GEAR);


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
        Collections.addAll(rotmats, HSSG, Tritanium, HSSE, Neutronium, CosmicNeutronium, MaragingSteel250);
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
