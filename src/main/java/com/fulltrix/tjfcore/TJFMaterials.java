package com.fulltrix.tjfcore;

//import com.fulltrix.tjfcore.materials.IsotopeMaterial;
//import com.fulltrix.tjfcore.materials.RadioactiveMaterial;

import gregtech.api.GTValues;
import gregtech.api.fluids.FluidBuilder;
import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.unification.Elements;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialFlag;
import gregtech.api.unification.material.properties.OreProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.material.properties.ToolProperty;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.fulltrix.tjfcore.TJFElements.*;
import static com.fulltrix.tjfcore.TJFUtility.tjfId;
import static gregicality.multiblocks.api.unification.GCYMMaterialFlags.NO_ALLOY_BLAST_RECIPES;
import static gregicality.multiblocks.api.unification.GCYMMaterials.*;
import static gregtech.api.fluids.attribute.FluidAttributes.ACID;
import static gregtech.api.unification.Elements.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtech.api.unification.material.info.MaterialIconSet.*;
import static gregtech.api.unification.ore.OrePrefix.plate;
import static gregtech.integration.crafttweaker.material.MaterialPropertyExpansion.*;
import static gregtech.integration.groovy.MaterialPropertyExpansion.addLiquid;
import static kono.ceu.materialreplication.api.unification.materials.flags.MRMaterialFlags.DISABLE_DECONSTRUCTION;
import static kono.ceu.materialreplication.api.unification.materials.flags.MRMaterialFlags.DISABLE_REPLICATION;
import static net.minecraft.util.text.TextFormatting.*;

public class TJFMaterials {

    public static final MaterialFlag GENERATE_NUCLEAR_COMPOUND = new MaterialFlag.Builder("generate_nuclear_compound").build();

    public static final MaterialFlag GENERATE_ISOTOPES_COMPOUND = new MaterialFlag.Builder("generate_isotopes_compound").build();

    //public static final MaterialFlag DISABLE_REPLICATION = new MaterialFlag.Builder("disable_replication").build();
    public static final List<MaterialFlag> CORE_METAL = new ArrayList<>();
    private static final TextFormatting[] fanciness = new TextFormatting[]{RED, GOLD, YELLOW, GREEN, AQUA, BLUE, LIGHT_PURPLE};

    public static int id = 24000;
    //NUCLEAR_MARK
    public static Material Uranium;
    public static Material Americium241;
    public static Material Plutonium;
    public static Material Fermium258;
    public static Material Californium252;
    public static Material Mendelevium261;

    /////////////////////////////////////////
    public static Material Pikyonium;
    public static Material Inconel792;
    public static Material HDCS;
    public static Material EglinSteel;
    public static Material EglinSteelBase;
    public static Material LithiumNiobate;
    public static Material Polyetheretherketone;
    public static Material LeadZirconateTitanate;
    public static Material LithiumTitanate;
    public static Material CarbonNanotubes;
    public static Material SodiumSeaborgate;
    public static Material TBCCODust;
    public static Material EnrichedNaquadahAlloy;
    public static Material FullereneDopedNanotubes;
    public static Material LithiumNiobateNanoparticles;
    public static Material ZirconiumTetrafluoride;
    public static Material BariumDifluoride;
    public static Material LanthanumTrifluoride;
    public static Material AluminiumTrifluoride;
    public static Material SodiumFluoride;
    public static Material ZBLANDust;
    public static Material ErbiumTrifluoride;
    public static Material ErbiumDopedZBLANDust;
    public static Material TungstenTitaniumCarbide;
    public static Material Prasiolite;
    public static Material BismuthTellurite;
    public static Material CubicZirconia;
    public static Material MagnetoResonatic;
    public static Material Jasper;
    public static Material SiliconCarbide;
    public static Material Quantum;
    public static Material Adamantium;
    public static Material Cinobite;
    public static Material Polyimide;
    public static Material GermaniumTungstenNitride;
    public static Material BariumTitanate;
    public static Material PEDOT;
    public static Material AluminiumComplex;
    public static Material CopperGalliumIndiumMix;
    public static Material CopperGalliumIndiumSelenide;
    public static Material BismuthRuthenate;
    public static Material BismuthIridiate;
    public static Material RutheniumDioxide;
    public static Material LanthanumCalciumManganate;
    public static Material Polystyrene;
    public static Material Shewanella;
    public static Material GreenAlgae;
    public static Material RedAlgae;
    public static Material BrownAlgae;
    public static Material DryRedAlgae;
    public static Material RedAlgaePowder;
    public static Material PreFreezeAgar;
    public static Material FrozenAgarCrystals;
    public static Material WaterAgarMix;
    public static Material BacterialGrowthMedium;
    public static Material DepletedGrowthMedium;
    public static Material BoricAcid;
    public static Material FluoroBoricAcid;
    public static Material Acetylene;
    public static Material CoAcABCatalyst;
    public static Material SodiumNitrate;
    public static Material SodiumNitrateSolution;
    public static Material SodiumNitrite;
    public static Material Aniline;
    public static Material BenzenediazoniumTetrafluoroborate;
    public static Material BoronFluoride;
    public static Material FluoroBenzene;
    public static Material Fluorotoluene;
    public static Material ZnFeAlClCatalyst;
    public static Material Difluorobenzophenone;
    public static Material Hydroquinone;
    public static Material Resorcinol;
    public static Material MgClBromide;
    public static Material SiliconFluoride;
    public static Material FluorosilicicAcid;
    public static Material AmmoniumFluoride;
    public static Material AmmoniumBifluoride;
    public static Material AmmoniumBifluorideSolution;
    public static Material AmmoniumVanadate;
    public static Material SodiumVanadate;
    public static Material PureSodiumVanadate;
    public static Material SodiumOxide;
    public static Material YttriumOxide;
    public static Material LutetiumOxide;
    public static Material ThuliumOxide;
    public static Material LuTmYChlorideSolution;
    public static Material SodiumMetavanadate;
    public static Material Urea;
    public static Material LuTmYVOPrecipitate;
    public static Material Ethanol100;
    public static Material LuTmYVONanoparticles;
    public static Material AmmoniumCarbonate;
    public static Material SodiumHydride;
    public static Material CalciumHydroxide;
    public static Material MagnesiumFluoride;
    public static Material TantalumOxide;
    public static Material DielectricMirrorFormationMix;
    public static Material NiobiumChloride;
    public static Material LithiumAluminiumHydride;
    public static Material LithiumHydride;
    public static Material AluminiumHydride;
    public static Material HydrogenPeroxide;
    public static Material NiobiumHydroxide;
    public static Material OxalicAcid;
    public static Material AmmoniumNiobiumOxalateSolution;
    public static Material IndiumPhospide;
    public static Material AmmoniumNitrate;
    public static Material AmmoniumSulfate;
    public static Material Ethanolamine;
    public static Material Ethylenediamine;
    public static Material Formaldehyde;
    public static Material SodiumCyanide;
    public static Material EDTASolution;
    public static Material EDTA;
    public static Material Glycine;
    public static Material CaesiumHydroxide;
    public static Material CaesiumBromideSolution;
    public static Material Sarcosine;
    public static Material PraseodymiumOxide;
    public static Material HolmiumOxide;
    public static Material NeodymiumOxide;
    public static Material PrYHoNitrateSolution;
    public static Material CetaneTrimethylAmmoniumBromide;
    public static Material PrHoYLFNanoparticles;
    public static Material BerylliumFluoride;
    public static Material ChlorinatedSolvents;
    public static Material Dichloromethane;
    public static Material CarbonTetrachloride;
    public static Material Butanol;
    public static Material ButanolGas;
    public static Material Tributylamine;
    public static Material Alumina;
    public static Material AluminiumNitrate;
    public static Material CrudeAluminaSolution;
    public static Material HydrogenCyanide;
    public static Material PotassiumPermanganate;
    public static Material ManganeseSulfate;
    public static Material PotassiumSulfate;
    public static Material AmmoniumCyanate;
    public static Material PotassiumManganate;
    public static Material PotassiumHydroxide;
    public static Material LithiumFluoride;
    public static Material AluminaSolution;
    public static Material PreciousMetal;
    public static Material GoldAlloy;
    public static Material GoldLeach;
    public static Material CopperLeach;
    public static Material ChloroauricAcid;
    public static Material PotassiumMetabisulfite;
    public static Material OrthoXylene;
    public static Material Durene;
    public static Material PyromelliticDianhydride;
    public static Material Oxydianiline;
    public static Material PolyamicAcid;
    public static Material Hexafluoropropylene;
    public static Material FluorinatedEthylenePropylene;
    public static Material Taranium;
    public static Material NaquadriaticTaranium;
    public static Material BlackTitanium;
    public static Material FullerenePolymerMatrix;
    public static Material Zylon;
    public static Material SupercriticalSteam;
    public static Material SodiumPotassiumAlloy;
    public static Material SupercriticalSodiumPotassiumAlloy;
    public static Material FLiNaK;
    public static Material SupercriticalFLiNaK;
    public static Material FLiBe;
    public static Material SupercriticalFLiBe;
    public static Material LeadBismuthEutectic;
    public static Material SupercriticalLeadBismuthEutectic;
    public static Material HastelloyN;
    public static Material TantalumHafniumSeaborgiumCarbide;
    public static Material Vibranium;
    public static Material HeavyQuarkDegenerateMatter;
    public static Material MetastableFlerovium;
    public static Material MetastableHassium;
    public static Material QCDMatter;
    public static Material SuperheavyLAlloy;
    public static Material Lafium;
    public static Material TriniumTitanium;
    public static Material Nitinol60;
    public static Material ActiniumSuperhydride;
    public static Material BETSPerrhenate;
    public static Material BorocarbideDust;
    public static Material FullereneSuperconductiveDust;
    public static Material MetastableOganesson;
    public static Material ProtoAdamantium;
    public static Material SuperheavyHAlloy;
    public static Material ChargedCaesiumCeriumCobaltIndium;
    public static Material RheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate;
    public static Material Legendarium;
    public static Material LanthanumFullereneMix;
    public static Material LanthanumFullereneNanotubes;
    public static Material HexanitroHexaazaisowurtzitane;
    public static Material Glyceryl;
    public static Material ElectronDegenerateRheniumPlasma;
    public static Material SuperheavyMix;
    public static Material NeutronPlasma;
    public static Material FreeAlphaGas;
    public static Material FreeElectronGas;
    public static Material PotassiumCarbonate;
    public static Material PotassiumBisulfite;
    public static Material PotassiumNitrite;
    public static Material NitrousAcid;
    public static Material SodiumAcetate;
    public static Material PotassiumHydroxylaminedisulfonate;
    public static Material HydroxylammoniumSulfate;
    public static Material BariumChloride;
    public static Material BariumSulfateSolution;
    public static Material HydroxylamineHydrochloride;
    public static Material SuccinicAcid;
    public static Material AceticAnhydride;
    public static Material SuccinicAnhydride;
    public static Material Tetrahydrofuran;
    public static Material NHydroxysuccinimide;
    public static Material Triethylamine;
    public static Material SuccinimidylAcetate;
    public static Material SeleniumOxide;
    public static Material SelenousAcid;
    public static Material AmmoniumAcetate;
    public static Material Acetamide;
    public static Material Acetonitrile;
    public static Material BenzylChloride;
    public static Material Hexamethylenetetramine;
    public static Material Benzylamine;
    public static Material Glyoxal;
    public static Material Hexabenzylhexaazaisowurtzitane;
    public static Material PalladiumChloride;
    public static Material PdCCatalyst;
    public static Material HydrobromicAcid;
    public static Material Dimethylformamide;
    public static Material DibenzylTetraacetylhexaazaisowurtzitane;
    public static Material NitroniumTetrafluoroborate;
    public static Material NitrosoniumTetrafluoroborate;
    public static Material Tetraacetyldinitrosohexaazaisowurtzitane;
    public static Material TetrafluoroboricAcid;
    public static Material Benzaldehyde;
    public static Material CrudeHexanitroHexaazaisowurtzitane;
    public static Material SilicaGel;
    public static Material BrevibacteriumFlavium;
    public static Material Succinimide;
    public static Material MaleicAnhydride;
    public static Material Silvertetrafluoroborate;
    public static Material SilverOxide;
    public static Material Acetaldehyde;
    public static Material Trichloroferane;
    public static Material SeaborgiumDopedNanotubes;
    public static Material PrHoYLF;
    public static Material LuTmYVO;
    public static Material NeodymiumDopedYttrium;
    public static Material UnprocessedNdYAGSolution;
    public static Material UnprocessedNdYAGDust;
    public static Material NdYAGNanoparticles;
    public static Material NdYAG;
    public static Material Pyrotheum;
    public static Material Cryotheum;
    public static Material DrillingMud;
    public static Material UsedDrillingMud;
    public static Material HastelloyX78;
    public static Material Incoloy813;
    public static Material Staballoy;
    public static Material Blizz;
    public static Material OrthoXyleneZeoliteMixture;
    public static Material ParaXylene;
    public static Material Dibromomethylbenzene;
    public static Material Terephthalaldehyde;
    public static Material AuPdCCatalyst;
    public static Material Isochloropropane;
    public static Material Dinitrodipropanyloxybenzene;
    public static Material PreZylon;
    public static Material Soap;
    public static Material DeglyceratedSoap;
    public static Material StearicAcid;
    public static Material Trioctylphosphine;
    public static Material QuantumDots;
    public static Material HastelloyK243;
    public static Material Enderium;
    public static Material PreciousMetals;
    public static Material RefractoryMetals;
    public static Material LightTranstionMetals;
    public static Material Alkalis;
    public static Material PostTransitionMetals;
    public static Material Lanthanoids;
    public static Material Actinoids;
    public static Material NonMetals;
    public static Material NobleGases;
    public static Material Periodicium;
    public static Material HeavyLeptonMix;
    public static Material Gluons;
    public static Material DenseNeutronPlasma;
    public static Material SuperfluidHelium;
    public static Material Cycloparaphenylene;
    public static Material NeutroniumDopedNanotubes;
    public static Material CosmicMeshPlasma;
    public static Material AcidicSaltWater;
    public static Material SulfuricBromineSolution;
    public static Material DebrominatedWater;
    public static Material HotVapourMixture;
    public static Material DampBromine;
    public static Material Ethylhexanol;
    public static Material DiethylhexylPhosphoricAcid;
    public static Material SodiumHydroxideSolution;
    public static Material RareEarthHydroxidesSolution;
    public static Material RareEarthChloridesSolution;
    public static Material ThUSludge;
    public static Material LaNdOxidesSolution;
    public static Material SmGdOxidesSolution;
    public static Material TbHoOxidesSolution;
    public static Material ErLuOxidesSolution;
    public static Material LanthanumOxide;
    public static Material CeriumOxide;
    public static Material ScandiumOxide;
    public static Material EuropiumOxide;
    public static Material GadoliniumOxide;
    public static Material SamariumOxide;
    public static Material TerbiumOxide;
    public static Material DysprosiumOxide;
    public static Material ErbiumOxide;
    public static Material YtterbiumOxide;
    public static Material Dimethylether;
    public static Material EthyleneOxide;
    public static Material Dimethoxyethane;
    public static Material Cyclooctadiene;
    public static Material Cyclopentadiene;
    public static Material ButylLithium;
    public static Material LithiumCyclopentadienide;
    public static Material GoldCyanide;
    public static Material GoldDepleteMolybdenite;
    public static Material MolybdenumConcentrate;
    public static Material ChlorideLeachedSolution;
    public static Material CopperChloride;
    public static Material LeadChloride;
    public static Material BismuthChloride;
    public static Material Iron2Chloride;
    public static Material MolybdenumFlue;
    public static Material MolybdenumTrioxide;
    public static Material RheniumSulfuricSolution;
    public static Material AmmoniumPerrhenate;
    public static Material MercuryAcetate;
    public static Material Methylamine;
    public static Material Methylethanolamine;
    public static Material CalciumCarbide;
    public static Material CalciumCyanamide;
    public static Material Methylguanidine;
    public static Material Methylnitronitrosoguanidine;
    public static Material SelectivelyMutatedCupriavidiusNecator;
    public static Material IsoamylAlcohol;
    public static Material Oct1ene;
    public static Material Octanol;
    public static Material PalladiumLoadedRutileNanoparticles;
    public static Material CupriavidusNecator;
    public static Material Trioctylamine;
    public static Material Kerosene;
    public static Material MethylIsobutylKetone;
    public static Material PlatinumSalt;
    public static Material RheniumSeparationMixture;
    public static Material RheniumScrubbedSolution;
    public static Material LeachedColumbite;
    public static Material PurifiedColumbite;
    public static Material LeachedPyrochlore;
    public static Material PurifiedPyrochlore;
    public static Material MesitylOxide;
    public static Material Glucose;
    public static Material FluoroniobicAcid;
    public static Material PlatinumSaltRefined;
    public static Material DirtyHexafluorosilicicAcid;
    public static Material StoneResidueDust;
    public static Material DiluteHexafluorosilicicAcid;
    public static Material UncommonResidues;
    public static Material RedMud;
    public static Material Dioxygendifluoride;
    public static Material PartiallyOxidizedResidues;
    public static Material OxidizedResidualSolution;
    public static Material InertResidues;
    public static Material CleanInertResidues;
    public static Material NaquadricCompound;
    public static Material OxidizedResidues;
    public static Material HeavyOxidizedResidues;
    public static Material MetallicResidues;
    public static Material DiluteHydrofluoricAcid;
    public static Material HeavyMetallicResidues;
    public static Material DiamagneticResidues;
    public static Material ParamagneticResidues;
    public static Material FerromagneticResidues;
    public static Material HeavyDiamagneticResidues;
    public static Material HeavyParamagneticResidues;
    public static Material HeavyFerromagneticResidues;
    public static Material ExoticHeavyResidues;
    public static Material TritiumHydride;
    public static Material Helium3Hydride;
    public static Material UltraacidicResidueSolution;
    public static Material XenicAcid;
    public static Material DustyLiquidHelium3;
    public static Material Ozone;
    public static Material TaraniumEnrichedLHelium3;
    public static Material TaraniumSemidepletedLHelium3;
    public static Material TaraniumDepletedLHelium3;
    public static Material TaraniumRichDustyHeliumPlasma;
    public static Material TaraniumRichHelium4;
    public static Material TaraniumDepletedHeliumPlasma;
    public static Material TaraniumPoorLiquidHelium;
    public static Material TaraniumPoorLiquidHeliumMix;
    public static Material Triniite;
    public static Material EnrichedNaquadricCompound;
    public static Material NaquadriaticCompound;
    public static Material NitratedTriniiteSolution;
    public static Material BariumOxide;
    public static Material BariumStrontiumAcetateSolution;
    public static Material IsopropylAlcohol;
    public static Material TitaniumIsopropoxide;
    public static Material BariumStrontiumTitanatePreparation;
    public static Material BariumStrontiumTitanate;
    public static Material IsopropylAcetate;
    public static Material PotasssiumFluoroTantalate;
    public static Material LeadNitrate;
    public static Material LeadNitrateCalciumMixture;
    public static Material LeadScandiumTantalate;
    public static Material MagnetorestrictiveAlloy;
    public static Material LeadSenenide;
    public static Material ZincSelenide;
    public static Material FranciumCaesiumCadmiumBromide;
    public static Material SodiumIodide;
    public static Material Iodobenzene;
    public static Material PalladiumAcetate;
    public static Material Stilbene;
    public static Material Amino3phenol;
    public static Material Ethylamine;
    public static Material SodiumAzanide;
    public static Material PhthalicAnhydride;
    public static Material TetraethylammoniumNonahydridides;
    public static Material RhodamineB;
    public static Material Hydroxylamine;
    public static Material SodiumPertechnetate;
    public static Material PotassiumPerrhenate;
    public static Material PotassiumPertechnate;
    public static Material PotassiumNonahydridorhenate;
    public static Material PotassiumEthoxide;
    public static Material PotassiumNonahydridotechnetate;
    public static Material TetraethylammoniumBromide;
    public static Material PotassiumIodide;
    public static Material Aminophenol;
    public static Material Butylaniline;
    public static Material LithiumIodide;
    public static Material Trimethylchlorosilane;
    public static Material Trimethylsilane;
    public static Material PotassiumBromide;
    public static Material PotassiumBromate;
    public static Material IodobenzoicAcid;
    public static Material IBX;
    public static Material Methoxybenzaldehyde;
    public static Material MBBA;
    public static Material LiquidCrystalDetector;
    public static Material IodineMonochloride;
    public static Material RhReNqCatalyst;
    public static Material AcetylatingReagent;
    public static Material Dimethylnaphthalene;
    public static Material Bromosuccinimide;
    public static Material Dihydroiodotetracene;
    public static Material Dichlorodicyanobenzoquinone;
    public static Material VanadiumOxide;
    public static Material LithiumChlorideSolution;
    public static Material Dichlorodicyanohydroquinone;
    public static Material Tetracene;
    public static Material StreptococcusPyogenes;
    public static Material BifidobacteriumBreve;
    public static Material EschericiaColi;
    public static Material SodiumHypochlorite;
    public static Material HotNitrogen;
    public static Material Lignite;
    public static Material DehydratedLignite;
    public static Material BCEPellet;
    public static Material GlucoseIronSolution;
    public static Material GrapheneOxidationSolution;
    public static Material GraphiteOxide;
    public static Material GrapheneOxidationResidue;
    public static Material GrapheneOxide;
    public static Material Hydrazine;
    public static Material Snow;
    public static Material SupercooledCryotheum;
    public static Material RedOil;
    public static Material RP1;
    public static Material TributylPhosphate;
    public static Material PhosphorusTrichloride;
    public static Material PhosphorylChloride;
    public static Material SodiumFormate;
    public static Material FormicAcid;
    public static Material OrganicFertilizer;
    public static Material ZirconiumTetrachloride;
    public static Material CarbonFluoride;
    public static Material CalciumCarbonateSolution;
    public static Material BentoniteClaySlurry;
    public static Material AluminiumChloride;
    public static Material EthylAnthraHydroQuinone;
    public static Material Anthracene;
    public static Material EthylAnthraQuinone;
    public static Material Chloroethanol;
    public static Material Trimethylamine;
    public static Material Choline;
    public static Material ATL;
    public static Material EthyleneGlycol;
    public static Material CaCBaSMixture;
    public static Material LubricantClaySlurry;
    public static Material ATLEthylene;
    public static Material DrillingMudMixture;
    public static Material WetEthyleneOxide;
    public static Material Phosgene;
    public static Material TolueneDiisocyanate;
    public static Material Polyurethane;
    public static Material ViscoelasticPolyurethane;
    public static Material ViscoelasticPolyurethaneFoam;
    public static Material RocketFuelH8N4C2O4;
    public static Material MonoMethylHydrazine;
    public static Material RocketFuelCN3H7O3;
    public static Material DenseHydrazineFuelMixture;
    public static Material RP1RocketFuel;
    public static Material CoalTarOil;
    public static Material SulfuricCoalTarOil;
    public static Material AluminiumHydroxide;
    public static Material SodiumHexafluoroaluminate;
    public static Material SodiumHydroxideBauxite;
    public static Material ImpureAluminiumHydroxideSolution;
    public static Material PureAluminiumHydroxideSolution;
    public static Material NeutralisedRedMud;
    public static Material RedSlurry;
    public static Material FerricREEChloride;
    public static Material TitanylSulfate;
    public static Material NiAlOCatalyst;
    public static Material RichNitrogenMix;
    public static Material FeCrOCatalyst;
    public static Material OxidisedNitrogenMix;
    public static Material PurifiedNitrogenMix;
    public static Material CarbonatedEthanolamine;
    public static Material AmmoniaRichMix;
    public static Material BariumCarbonate;
    public static Material BariumAluminate;
    public static Material Barytocalcite;
    public static Material Witherite;
    public static Material AcryloNitrile;
    public static Material SodiumThiocyanate;
    public static Material PolyacrylonitrileSolution;
    public static Material AcrylicFibers;
    public static Material MethylFormate;
    public static Material WetFormamide;
    public static Material Formamide;
    public static Material HydroxylamineDisulfate;
    public static Material Amidoxime;
    public static Material SeaWater;
    public static Material PureUranylNitrateSolution;
    public static Material UranylNitrate;
    public static Material DiluteNitricAcid;
    public static Material Brine;
    public static Material ConcentratedBrine;
    public static Material BrominatedBrine;
    public static Material AcidicBrominatedBrine;
    public static Material CalciumFreeBrine;
    public static Material CalciumSalts;
    public static Material SodiumFreeBrine;
    public static Material SodiumSalts;
    public static Material PotassiumFreeBrine;
    public static Material PotassiumMagnesiumSalts;
    public static Material BoronFreeSolution;
    public static Material CalciumMagnesiumSalts;
    public static Material SodiumLithiumSolution;
    public static Material SodiumAluminiumHydride;
    public static Material Fructose;
    public static Material Cellulose;
    public static Material SodiumAzide;
    public static Material LithiumHydroxideSolution;
    public static Material Glucosamine;
    public static Material Chitosan;
    public static Material SodiumSulfateSolution;
    public static Material BoronOxide;
    public static Material Diborane;
    public static Material LithiumAluminiumFluoride;
    public static Material CarbonSulfide;
    public static Material DimethylthiocarbamoilChloride;
    public static Material Mercaptophenol;
    public static Material AmineMixture;
    public static Material SodiumMolybdate;
    public static Material SodiumPhosphomolybdate;
    public static Material SodiumTungstate;
    public static Material SodiumPhosphotungstate;
    public static Material IridiumCyclooctadienylChlorideDimer;
    public static Material ChlorodiisopropylPhosphine;
    public static Material DehydrogenationCatalyst;
    public static Material AmmoniumPersulfate;
    public static Material PolystyreneNanoParticles;
    public static Material Celestine;
    public static Material MagnesiumSulfate;
    public static Material StrontiumCarbonate;
    public static Material StrontiumOxide;
    public static Material ChilledBrine;
    public static Material MagnesiumContainingBrine;
    public static Material SodiumSulfate;
    public static Material Dichlorocycloctadieneplatinium;
    public static Material PotassiumFluoride;
    public static Material SodiumCarbonateSolution;
    public static Material SodiumChromateSolution;
    public static Material SodiumDichromateSolution;
    public static Material ChromiumIIIOxide;
    public static Material UnfoldedFullerene;
    public static Material PotassiumPeroxymonosulfate;
    public static Material AuricChloride;
    public static Material XenonTrioxide;
    public static Material RadonTrioxide;
    public static Material Fullerene;
    public static Material Ferrocene;
    public static Material SodiumEthoxide;
    public static Material PdFullereneMatrix;
    public static Material Dimethylaminopyridine;
    public static Material Phenylpentanoicacid;
    public static Material Dimethylsulfide;
    public static Material PCBS;
    public static Material QuarkGluonPlasma;
    public static Material HeavyQuarks;
    public static Material LightQuarks;
    public static Material CosmicComputingMix;
    public static Material HeavyQuarkEnrichedMix;
    public static Material Titanium50;
    public static Material ScandiumTitanium50Mix;
    public static Material RadonRadiumMix;
    public static Material DeuteriumSuperheavyMix;
    public static Material Phenylsodium;
    public static Material Succinaldehyde;
    public static Material Difluoroaniline;
    public static Material NDifluorophenylpyrrole;
    public static Material SilverPerchlorate;
    public static Material TitaniumCyclopentadienyl;
    public static Material SilverChloride;
    public static Material SodiumBromide;
    public static Material PhotopolymerSolution;
    public static Material SodiumChlorate;
    public static Material SodiumPerchlorate;
    public static Material GermaniumSulfide;
    public static Material GermaniumOxide;
    public static Material TungstenTrioxide;
    public static Material CadmiumSulfide;
    public static Material CadmiumTungstate;
    public static Material CesiumIodide;
    public static Material TlTmCesiumIodide;
    public static Material PolycyclicAromaticMix;
    public static Material BismuthNitrateSoluton;
    public static Material BismuthGermanate;
    public static Material Naphthaldehyde;
    public static Material Benzophenanthrenylacetonitrile;
    public static Material Pyridine;
    public static Material PCBA;
    public static Material CalciumTungstate;
    public static Material DisodiumPhosphate;
    public static Material Cyanonaphthalene;
    public static Material TinChloride;
    public static Material Triphenylphosphine;
    public static Material Methylbenzophenanthrene;
    public static Material PotassiumCyanide;
    public static Material TiAlChloride;
    public static Material ThionylChloride;
    public static Material ZeoliteSievingPellets;
    public static Material PdIrReOCeOS;
    public static Material WetZeoliteSievingPellets;
    public static Material Ferrocenylfulleropyrrolidine;
    public static Material BenzoylChloride;
    public static Material BenzoylPeroxide;
    public static Material HydroiodicAcid;
    public static Material TrimethyltinChloride;
    public static Material Diisopropylcarbodiimide;
    public static Material Hexanediol;
    public static Material Hexamethylenediamine;
    public static Material SaccharicAcid;
    public static Material AdipicAcid;
    public static Material Tertbutanol;
    public static Material DitertbutylDicarbonate;
    public static Material Triaminoethaneamine;
    public static Material TertButylAzidoformate;
    public static Material AminatedFullerene;
    public static Material Azafullerene;
    public static Material GrapheneGelSuspension;
    public static Material DryGrapheneGel;
    public static Material SupercriticalCO2;
    public static Material Caliche;
    public static Material CalicheIodateBrine;
    public static Material IodideSolution;
    public static Material CalicheIodineBrine;
    public static Material CalicheNitrateSolution;
    public static Material KeroseneIodineSolution;
    public static Material IodizedOil;
    public static Material IodizedBrine;
    public static Material IodineBrineMix;
    public static Material IodineSlurry;
    public static Material RoastedSpodumene;
    public static Material RoastedLepidolite;
    public static Material Fluorite;
    public static Material DissolvedLithiumOre;
    public static Material AluminiumSulfate;
    public static Material LithiumCarbonateSolution;
    public static Material Diiodobiphenyl;
    public static Material ThalliumChloride;
    public static Material NiAlCatalyst;
    public static Material Bipyridine;
    public static Material Dibenzylideneacetone;
    public static Material PalladiumBisDibenzylidieneacetone;
    public static Material ChloroPlatinicAcid;
    public static Material PotassiumTetrachloroplatinate;
    public static Material NickelChloride;
    public static Material NickelTriphenylPhosphite;
    public static Material GrapheneNanotubeMix;
    public static Material GrapheneAlignedCNT;
    public static Material Rhodocrosite;
    public static Material CassiteriteCokePellets;
    public static Material TinSlag;
    public static Material NbTaContainingDust;
    public static Material NiobiumTantalumOxide;
    public static Material NbTaFluorideMix;
    public static Material Columbite;
    public static Material FusedColumbite;
    public static Material BariumPeroxide;
    public static Material ColumbiteMinorOxideResidue;
    public static Material IronSulfateDust;
    public static Material FusedTantalite;
    public static Material TantaliteMinorOxideResidue;
    public static Material LeachedTantalite;
    public static Material FluorotantalicAcid;
    public static Material NbTaSeparationMixture;
    public static Material AcidicLeachedPyrochlore;
    public static Material REEThUSulfateSolution;
    public static Material AlkalineEarthSulfateSolution;
    public static Material UranylThoriumNitrate;
    public static Material RareEarthNitrateSolution;
    public static Material OxypentafluoroNiobate;
    public static Material HeptafluoroTantalate;
    public static Material PotasssiumFluoroNiobate;
    public static Material AluminoSilicateWool;
    public static Material ScandiumTriflate;
    public static Material Toluenesulfonate;
    public static Material Niter;
    public static Material ElectricallyImpureCopper;
    public static Material CopperRefiningSolution;
    public static Material AnodicSlime;
    public static Material SeleniteTelluriteMix;
    public static Material TelluriumOxide;
    public static Material SeleniteSolution;
    public static Material HydroselenicAcid;
    public static Material IronPlatinumCatalyst;
    public static Material Hydroxyquinoline;
    public static Material SodiumRuthenate;
    public static Material IridiumDioxide;
    public static Material BariumChlorideSolution;
    public static Material BariumTitanatePreparation;
    public static Material Perbromothiophene;
    public static Material Diethoxythiophene;
    public static Material EDOT;
    public static Material ZirconylChloride;
    public static Material WoodsGlass;
    public static Material IronIodide;
    public static Material ThalliumIodide;
    public static Material RubidiumIodide;
    public static Material IndiumIodide;
    public static Material GalliumIodide;
    public static Material ScandiumIodide;
    public static Material IronCarbonyl;
    public static Material PurifiedIronCarbonyl;
    public static Material CarbonylPurifiedIron;
    public static Material YttriumEuropiumVanadate;
    public static Material StrontiumChloride;
    public static Material StrontiumEuropiumAluminate;
    public static Material GreenHalideMix;
    public static Material RedHalideMix;
    public static Material BlueHalideMix;
    public static Material WhiteHalideMix;
    public static Material UVAHalideMix;
    public static Material ThoriumDopedTungsten;
    public static Material MaragingSteel250;
    public static Material Biperfluoromethanedisulfide;
    public static Material BariumTriflateSolution;
    public static Material BariumTriflate;
    public static Material BariumNitrate;
    public static Material CopperNitrate;
    public static Material YttriumNitrate;
    public static Material CitricAcid;
    public static Material WellMixedYBCOxides;
    public static Material PiledTBCC;
    public static Material ActiniumOxalate;
    public static Material ActiniumHydride;
    public static Material ActiniumSuperhydridePlasma;
    public static Material LanthanumEmbeddedFullerene;
    public static Material Dibromoacrolein;
    public static Material SodiumThiosulfate;
    public static Material Chloroethane;
    public static Material Bromohydrothiine;
    public static Material Bromobutane;
    public static Material Lithiumthiinediselenide;
    public static Material Propadiene;
    public static Material BETS;
    public static Material FranciumCarbide;
    public static Material BoronCarbide;
    public static Material BoronFranciumCarbide;
    public static Material AstatideSolution;
    public static Material MixedAstatideSalts;
    public static Material SodiumIodate;
    public static Material CopperSulfateSolution;
    public static Material SodiumPeriodate;
    public static Material StrontiumSuperconductorDust;
    public static Material KryptonDifluoride;
    public static Material ManganeseFluoride;
    public static Material PhenylenedioxydiaceticAcid;
    public static Material Diethylthiourea;
    public static Material Isophthaloylbisdiethylthiourea;
    public static Material HassiumChloride;
    public static Material RheniumChloride;
    public static Material AntimonyPentafluoride;
    public static Material AntimonyTrichloride;
    public static Material FluorophosphoricAcid;
    public static Material ChargedCesiumCeriumCobaltIndium;
    public static Material VanadiumSlag;
    public static Material VanadiumSlagDust;
    public static Material VanadiumWasteSolution;
    public static Material PropargylAlcohol;
    public static Material PropargylChloride;
    public static Material Resin;
    public static Material Turpentine;
    public static Material BetaPinene;
    public static Material Citral;
    public static Material BetaIonone;
    public static Material VitaminA;
    public static Material Yeast;
    public static Material LinoleicAcid;
    public static Material Biotin;
    public static Material Catalase;
    public static Material B27Supplement;
    public static Material CleanAmmoniaSolution;
    public static Material Glutamine;
    public static Material Blood;
    public static Material BloodCells;
    public static Material BloodPlasma;
    public static Material BFGF;
    public static Material EGF;
    public static Material SilicaGelBase;
    public static Material SilicaAluminaGel;
    public static Material PiranhaSolution;
    public static Material ChlorosulfonicAcid;
    public static Material AcetylsulfanilylChloride;
    public static Material Sulfanilamide;
    public static Material AnimalCells;
    public static Material RapidlyReplicatingAnimalCells;
    public static Material Oct4Gene;
    public static Material SOX2Gene;
    public static Material KFL4Gene;
    public static Material GeneTherapyFluid;
    public static Material Cas9;
    public static Material MycGene;
    public static Material GenePlasmids;
    public static Material Chitin;
    public static Material ZincCokePellets;
    public static Material ZincResidualSlag;
    public static Material ZincExhaustMixture;
    public static Material ZincSulfate;
    public static Material ZincLeachingResidue;
    public static Material ZincFlueDust;
    public static Material FineZincSlagDust;
    public static Material ZincSlagSlurry;
    public static Material MetalRichSlagSlurry;
    public static Material AcidicMetalSlurry;
    public static Material SeparatedMetalSlurry;
    public static Material MetalHydroxideMix;
    public static Material ZincPoorMix;
    public static Material IronPoorMix;
    public static Material IndiumHydroxideConcentrate;
    public static Material IndiumHydroxide;
    public static Material CadmiumZincDust;
    public static Material CadmiumThalliumLiquor;
    public static Material ZincAmalgam;
    public static Material ThalliumResidue;
    public static Material CadmiumSulfateSolution;
    public static Material ThalliumSulfateSolution;
    public static Material ZincChloride;
    public static Material SodiumSulfite;
    public static Material PolyphenolMix;
    public static Material AcidifiedPolyphenolMix;
    public static Material Diethylether;
    public static Material TannicAcid;
    public static Material GermanicAcidSolution;
    public static Material GermaniumChloride;
    public static Material BariumHydroxide;
    public static Material ThiocyanicAcid;
    public static Material ZrHfSeparationMix;
    public static Material Zircon;
    public static Material ZrHfChloride;
    public static Material ZirconChlorinatingResidue;
    public static Material SiliconChloride;
    public static Material ZrHfOxyChloride;
    public static Material HafniumOxide;
    public static Material HafniumChloride;
    public static Material HeliumCNO;
    public static Material Iron52;
    public static Material Chromium48;
    public static Material Titanium44;
    public static Material Nickel56;
    public static Material LiquidEnrichedHelium;
    public static Material AbyssalAlloy;

    //COILS
    public static Material TitanSteel;

    public static Material CosmicNeutronium;

    public static Material Infinity;

    public static Material Eternity;

    //superconductors
    public static Material LVSuperconductor = ManganesePhosphide;
    public static Material MVSuperconductor = MagnesiumDiboride;
    public static Material HVSuperconductor = MercuryBariumCalciumCuprate;
    public static Material EVSuperconductor = UraniumTriplatinum;
    public static Material IVSuperconductor = SamariumIronArsenicOxide;
    public static Material LuVSuperconductor = IndiumTinBariumTitaniumCuprate;
    public static Material ZPMSuperconductor = UraniumRhodiumDinaquadide;
    public static Material UVSuperconductor = EnrichedNaquadahTriniumEuropiumDuranide;
    public static Material UHVSuperconductor = RutheniumTriniumAmericiumNeutronate;

    public static Material UEVSuperconductor;

    public static Material UIVSuperconductor;

    public static Material UXVSuperconductor;

    public static Material OpVSuperconductor;


    ///////////////////////////////////////////

    static {
        CORE_METAL.addAll(EXT2_METAL);
        CORE_METAL.addAll(Arrays.asList(GENERATE_RING, GENERATE_FRAME, GENERATE_ROTOR, GENERATE_SMALL_GEAR, GENERATE_DENSE, GENERATE_FINE_WIRE, GENERATE_GEAR));
    }

    public static void register() {

        //NUCLEAR_MARK
        Uranium = new Material.Builder(id++, tjfId("uranium_generic"))
                .ingot(3).liquid()
                .color(0xF03232)
                .iconSet(METALLIC)
                .flags(EXT_METAL)
                .element(Elements.U)
                .build();

        Plutonium = new Material.Builder(id++, tjfId("plutonium_generic"))
                .ingot(3).liquid()
                .color(0xF03232)
                .iconSet(METALLIC)
                .flags(EXT_METAL)
                .element(Elements.Pu)
                .build();


        Americium241 = new Material.Builder(id++, tjfId("americium_241"))
                .ingot()
                .color(13158600)
                .iconSet(METALLIC)
                .flags(STD_METAL)
                .build()
                .setFormula("Am_241", true);

        Fermium258 = new Material.Builder(id++, tjfId("fermium_258"))
                .ingot()
                .color(0x984ACF)
                .iconSet(METALLIC)
                .flags(STD_METAL)
                .build()
                .setFormula("Fm_241", true);

        Californium252 = new Material.Builder(id++, tjfId("californium_252"))
                .ingot()
                .color(0xA85A12)
                .iconSet(METALLIC)
                .flags(STD_METAL)
                .build()
                .setFormula("Cf_252", true);

        Mendelevium261 = new Material.Builder(id++, tjfId("californium_261"))
                .ingot()
                .color(Mendelevium.getMaterialRGB())
                .iconSet(METALLIC)
                .flags(STD_METAL)
                .build()
                .setFormula("Md_252", true);

        //////////////////////

        EnrichedNaquadahAlloy = new Material.Builder(id++, tjfId("enriched_naquadah_alloy"))
                .ingot(2)
                .liquid()
                .color(0x403f3d)
                .iconSet(SHINY)
                .flags(EXT2_METAL, DISABLE_DECOMPOSITION, GENERATE_FRAME, GENERATE_SMALL_GEAR, GENERATE_ROUND, GENERATE_RING, GENERATE_ROTOR)
                .components(NaquadahEnriched, 4, Rhodium, 2, Ruthenium, 2, Dubnium, 1, Rubidium, 2, Einsteinium, 1)
                .blast(10000)
                .build();

        Inconel792 = new Material.Builder(id++, tjfId("inconel_b"))
                .ingot(5)
                .color(0x6CF076)
                .iconSet(SHINY)
                .flags(CORE_METAL)
                .components(Nickel, 2, Niobium, 1, Aluminium, 2, Nichrome, 1)
                .blast(6200)
                .build();

        EglinSteelBase = new Material.Builder(id++, tjfId("eglin_steel_base"))
                .dust(6)
                .color(0x8B4513)
                .iconSet(SAND)
                .components(Iron, 4, Kanthal, 1, Invar, 5)
                .build();

        EglinSteel = new Material.Builder(id++, tjfId("eglin_steel"))
                .ingot(6)
                .color(0x8B4513)
                .iconSet(METALLIC)
                .flags(EXT2_METAL, GENERATE_FRAME)
                .components(EglinSteelBase, 10, Sulfur, 1, Silicon, 1, Carbon, 1)
                .blast(1048)
                .build();

        Pikyonium = new Material.Builder(id++, tjfId("pikyonium"))
                .ingot(7)
                .color(0x3467BA)
                .flags(CORE_METAL)
                .iconSet(SHINY)
                .cableProperties(GTValues.V[GTValues.UEV], 4, 32)
                .components(Inconel792, 8, EglinSteel, 5, NaquadahEnriched, 4, Cerium, 3, Antimony, 2, Platinum, 2, Ytterbium, 1, TungstenSteel, 4)
                .blast(11500)
                .build();

        LithiumNiobate = new Material.Builder(id++, tjfId("lithium_niobate"))
                .ingot()
                .color(0xcfcf3a)
                .iconSet(SHINY)
                .flags(GENERATE_PLATE)
                .components(Lithium, 1, Niobium, 1, Oxygen, 4)
                .blast(6700)
                .build();

        Polyetheretherketone = new Material.Builder(id++, tjfId("polyetheretherketone"))
                .polymer(2).liquid()
                .color(0x403e37)
                .iconSet(DULL)
                .flags(NO_SMASHING, GENERATE_FOIL, GENERATE_FINE_WIRE, NO_ALLOY_BLAST_RECIPES)
                .components(Carbon, 20, Hydrogen, 12, Oxygen, 3)
                .blast(6700)
                .build();

        LeadZirconateTitanate = new Material.Builder(id++, tjfId("lead_zirconate_titanate"))
                .gem(3)
                .color(0x359ade)
                .iconSet(OPAL)
                .flags(GENERATE_PLATE)
                .components(Lead, 1, Zirconium, 1, Titanium, 1, Oxygen, 3)
                .build();

        LithiumTitanate = new Material.Builder(id++, tjfId("lithium_titanate"))
                .ingot(5)
                .color(0xfe71a9)
                .iconSet(SHINY)
                .flags(CORE_METAL, GENERATE_PLATE)
                .components(Lithium, 2, Titanium, 1, Oxygen, 3)
                .build();

        CarbonNanotubes = new Material.Builder(id++, tjfId("carbon_nanotubes"))
                .ingot().liquid()
                .color(0x2c2c2c)
                .iconSet(SHINY)
                .flags(EXCLUDE_BLOCK_CRAFTING_RECIPES, GENERATE_FOIL, DISABLE_DECOMPOSITION, GENERATE_FINE_WIRE)
                .components(Carbon, 1)
                .build();

        SodiumSeaborgate = new Material.Builder(id++, tjfId("sodium_seaborgate"))
                .ingot()
                .color(0x55bbd4)
                .iconSet(SHINY)
                .components(Sodium, 2, Seaborgium, 1, Oxygen, 4)
                .build();

        TBCCODust = new Material.Builder(id++, tjfId("TBCCODust"))
                .dust()
                .color(0x669900)
                .iconSet(SHINY)
                .components(Thallium, 1, Barium, 2, Calcium, 2, Copper, 3, Oxygen, 10)
                .build();

        FullereneDopedNanotubes = new Material.Builder(id++, tjfId("fullerene_doped_nanotubes"))
                .liquid()
                .color(0x6c2c6c)
                .components()
                .build()
                .setFormula("C60CNT",true);

        LithiumNiobateNanoparticles = new Material.Builder(id++, tjfId("lithium_niobate_nanoparticles"))
                .dust()
                .color(LithiumNiobate.getMaterialRGB()-10)
                .iconSet(SHINY)
                .build()
                .setFormula("LiNbO4",true);

        ZirconiumTetrafluoride = new Material.Builder(id++, tjfId("zirconium_tetrafluoride"))
                .dust()
                .color((Zirconium.getMaterialRGB()+Fluorine.getMaterialRGB())/2)
                .iconSet(SHINY)
                .build()
                .setFormula("ZrF4", true);

        BariumDifluoride = new Material.Builder(id++, tjfId("barium_difluoride"))
                .dust()
                .color((Barium.getMaterialRGB()+Fluorine.getMaterialRGB())/2)
                .iconSet(METALLIC)
                .build()
                .setFormula("BaF2", true);

        LanthanumTrifluoride = new Material.Builder(id++, tjfId("lanthanum_trifluoride"))
                .dust()
                .color((Lanthanum.getMaterialRGB()+Fluorine.getMaterialRGB())/2)
                .iconSet(METALLIC)
                .build()
                .setFormula("LaF3", true);

        AluminiumTrifluoride = new Material.Builder(id++, tjfId("aluminium_trifluoride"))
                .dust()
                .color((Aluminium.getMaterialRGB()+Fluorine.getMaterialRGB())/2)
                .iconSet(ROUGH)
                .build()
                .setFormula("AlF3", true);

        ErbiumTrifluoride = new Material.Builder(id++, tjfId("erbium_trifluoride"))
                .dust()
                .color((Erbium.getMaterialRGB()+Fluorine.getMaterialRGB())/2)
                .iconSet(ROUGH)
                .build()
                .setFormula("ErF3", true);

        ZBLANDust = new Material.Builder(id++, tjfId("zblan_dust"))
                .dust().liquid()
                .color((ZirconiumTetrafluoride.getMaterialRGB()+BariumDifluoride.getMaterialRGB()+LanthanumTrifluoride.getMaterialRGB()+AluminiumTrifluoride.getMaterialRGB())/4)
                .iconSet(SHINY)
                .build()
                .setFormula("(ZrF4)18(BaF2)7(LaF3)2(AlF3)(NaF)7", true);

        ErbiumDopedZBLANDust = new Material.Builder(id++, tjfId("erbium_doped_zblan_dust"))
                .dust()
                .color((ZBLANDust.getMaterialRGB()+ErbiumTrifluoride.getMaterialRGB())/2)
                .iconSet(SHINY)
                .build()
                .setFormula("(ErF3)(ZrF4)18(BaF2)7(LaF3)2(AlF3)(NaF)7", true);

        SodiumFluoride = new Material.Builder(id++, tjfId("sodium_fluoride"))
                .dust(2)
                .color((Sodium.getMaterialRGB() + Fluorine.getMaterialRGB()) / 2)
                .iconSet(DULL)
                .components(Sodium, 1, Fluorine, 1)
                .build();

        TungstenTitaniumCarbide = new Material.Builder(id++, tjfId("tungsten_titanium_carbide"))
                .ingot(7)
                .color(0x800d0d)
                .iconSet(SHINY)
                .flags(CORE_METAL, DISABLE_DECOMPOSITION)
                .components(TungstenCarbide, 7, Titanium, 3)
                .cableProperties(GTValues.V[GTValues.UHV], 4, 16)
                .blast(4422)
                .build();



        Prasiolite = new Material.Builder(id++, tjfId("prasiolite"))
                .gem(2).ore()
                .color(0x9EB749)
                .iconSet(QUARTZ)
                .flags(GENERATE_LENS)
                .components(Silicon, 5, Oxygen, 10, Iron, 1)
                .build();

        BismuthTellurite = new Material.Builder(id++, tjfId("bismuth_tellurite"))
                .dust(2)
                .color(0x006B38)
                .iconSet(SAND)
                .components(Bismuth, 2, Tellurium, 3)
                .build();

        CubicZirconia = new Material.Builder(id++, tjfId("cubic_zirconia"))
                .gem(6)
                .color(0xFFDFE2)
                .iconSet(DIAMOND)
                .flags(NO_SMELTING, GENERATE_LENS)
                .components(Zirconium, 1, Oxygen, 2)
                .build();

        MagnetoResonatic = new Material.Builder(id++, tjfId("magneto_resonatic"))
                .gem(2)
                .color(0xFF97FF)
                .iconSet(MAGNETIC)
                .flags(DISABLE_DECOMPOSITION, FLAMMABLE, HIGH_SIFTER_OUTPUT, NO_SMELTING, GENERATE_LENS)
                .components(Prasiolite, 3, BismuthTellurite, 6, CubicZirconia, 1, SteelMagnetic, 1)
                .build();

        HDCS = new Material.Builder(id++, tjfId("hdcs"))
                .ingot(5)
                .color(0x334433)
                .iconSet(SHINY)
                .flags(CORE_METAL, DISABLE_DECOMPOSITION)
                .components(TungstenSteel, 12, HSSS, 9, HSSG, 6, Ruridit, 3, MagnetoResonatic, 2, Plutonium, 1)
                .blast(6100)
                .build();


        Jasper = new Material.Builder(id++, tjfId("jasper"))
                .gem(2)
                .color(13127760)
                .iconSet(EMERALD)
                .flags(NO_SMELTING, HIGH_SIFTER_OUTPUT)
                .build();



        SiliconCarbide = new Material.Builder(id++, tjfId("silicon_carbide"))
                .dust()
                .color((Silicon.getMaterialRGB() + Carbon.getMaterialRGB()) / 2)
                .iconSet(SHINY)
                .build()
                .setFormula("SiC", true);

        Quantum = new Material.Builder(id++, tjfId("quantum"))
                .ingot(7).liquid()
                .color(0x0f0f0f)
                .iconSet(SHINY)
                .flags(CORE_METAL, DISABLE_DECOMPOSITION)
                .components(Stellite100, 15, Jasper, 5, Gallium, 5, Americium241, 5, Palladium, 5, Bismuth, 5, Germanium, 5, SiliconCarbide, 5)
                .blast(11400)
                .build();

        Adamantium = new Material.Builder(id++, tjfId("adamantium"))
                .ingot(7).liquid()
                .color(0x2d365c)
                .iconSet(SHINY)
                .flags(CORE_METAL)
                .element(Ad)
                .blast(10850)
                .build();

        Cinobite = new Material.Builder(id++, tjfId("cinobite"))
                .ingot(5).liquid()
                .color(0x010101)
                .iconSet(SHINY)
                .flags(CORE_METAL, DISABLE_DECOMPOSITION)
                .components(Zeron100, 8, Naquadria, 4, Gadolinium, 3, Aluminium, 2, Mercury, 1, Tin, 1, Titanium, 6, Osmiridium, 1)
                .cableProperties(GTValues.V[GTValues.UIV], 4, 64)
                .blast(11465)
                .build();


        Polyimide = new Material.Builder(id++, tjfId("polyimide"))
                .ingot(1).liquid()
                .color(0xFF7F50)
                .iconSet(DULL)
                .flags(DISABLE_DECOMPOSITION, GENERATE_PLATE, FLAMMABLE, NO_SMASHING)
                .components(Carbon, 22, Hydrogen, 12, Nitrogen, 2, Oxygen, 6)
                .build();

        GermaniumTungstenNitride = new Material.Builder(id++, tjfId("germanium_tungsten_nitride"))
                .ingot(2)
                .color(0x8f8fcf)
                .iconSet(DULL)
                .flags(GENERATE_PLATE)
                .components(Germanium, 3, Tungsten, 3, Nitrogen, 10)
                .blast(5400)
                .build();

        BariumTitanate = new Material.Builder(id++, tjfId("barium_titanate"))
                .ingot(2)
                .color(0x99FF99)
                .iconSet(SHINY)
                .flags(GENERATE_FOIL, DISABLE_DECOMPOSITION)
                .components(Barium, 1, Titanium, 1, Oxygen, 3)
                .build();

        PEDOT = new Material.Builder(id++, tjfId("pedot"))
                .ingot(22)
                .color(0x5cef20)
                .iconSet(DULL)
                .flags(GENERATE_FINE_WIRE, DISABLE_DECOMPOSITION)
                .components(Carbon, 6, Hydrogen, 6, Oxygen, 2, Sulfur, 1)
                .build();

        AluminiumComplex = new Material.Builder(id++, tjfId("aluminium_complex"))
                .dust()
                .color(0x3f5a9f)
                .iconSet(SHINY)
                .build()
                .setFormula("AlC9H7NO", true);

        CopperGalliumIndiumMix = new Material.Builder(id++, tjfId("copper_gallium_indium_mix"))
                .dust()
                .color((Indium.getMaterialRGB() + Copper.getMaterialRGB() + Gallium.getMaterialRGB()) / 3)
                .iconSet(ROUGH)
                .build()
                .setFormula("CuGaIn", true);

        CopperGalliumIndiumSelenide = new Material.Builder(id++, tjfId("copper_gallium_indium_selenide"))
                .dust()
                .color((CopperGalliumIndiumMix.getMaterialRGB() + Selenium.getMaterialRGB()) / 2)
                .iconSet(SHINY)
                .build()
                .setFormula("CuGaInSe2", true);

        BismuthRuthenate = new Material.Builder(id++, tjfId("bismuth_ruthenate"))
                .ingot(2)
                .color(0x94cf5c)
                .iconSet(DULL)
                .flags(GENERATE_PLATE, DISABLE_DECOMPOSITION)
                .components(Bismuth, 2, Ruthenium, 2, Oxygen, 7)
                .build();

        BismuthIridiate = new Material.Builder(id++, tjfId("bismuth_iridiate"))
                .ingot(22)
                .color(0x478a6b)
                .iconSet(DULL)
                .flags(GENERATE_PLATE, DISABLE_DECOMPOSITION)
                .components(Bismuth, 2, Iridium, 2, Oxygen, 7)
                .build();

        RutheniumDioxide = new Material.Builder(id++, tjfId("ruthenium_dioxide"))
                .ingot(2)
                .color(RutheniumTetroxide.getMaterialRGB())
                .iconSet(DULL)
                .flags(GENERATE_PLATE, DISABLE_DECOMPOSITION)
                .components(Ruthenium, 1, Oxygen, 2)
                .build();

        LanthanumCalciumManganate = new Material.Builder(id++, tjfId("lanthanum_gallium_manganate"))
                .dust()
                .color(0x8aa07b)
                .iconSet(SHINY)
                .build()
                .setFormula("LaCaMnO3", true);

        Polystyrene = new Material.Builder(id++, tjfId("polystyrene"))
                .polymer(1).liquid()
                .color(8945785)
                .iconSet(DULL)
                .flags(GENERATE_FOIL, DISABLE_DECOMPOSITION)
                .components(Carbon, 8, Hydrogen, 8)
                .build();

        Shewanella = new Material.Builder(id++, tjfId("shewanella"))
                .dust()
                .color(0x8752ab)
                .iconSet(METALLIC)
                .build()
                .setFormula("Bacteria", true);

        GreenAlgae = new Material.Builder(id++, tjfId("green_algae"))
                .dust()
                .color(0x228b22)
                .iconSet(METALLIC)
                .build()
                .setFormula("An Algae", true);

        BrownAlgae = new Material.Builder(id++, tjfId("brown_algae"))
                .dust()
                .color(0xa52a2a)
                .iconSet(METALLIC)
                .build()
                .setFormula("An Algae", true);

        RedAlgae = new Material.Builder(id++, tjfId("red_algae"))
                .dust()
                .color(0xf08080)
                .iconSet(METALLIC)
                .build()
                .setFormula("An Algae", true);

        DryRedAlgae = new Material.Builder(id++, tjfId("dry_red_algae"))
                .dust()
                .color(0xff7f50)
                .iconSet(ROUGH)
                .build()
                .setFormula("A Dry Algae", true);

        RedAlgaePowder = new Material.Builder(id++, tjfId("red_algae_powder"))
                .dust()
                .color(0xcc2f2f)
                .iconSet(ROUGH)
                .build()
                .setFormula("A Powdered Algae", true);

        PreFreezeAgar = new Material.Builder(id++, tjfId("pre_freeze_agar"))
                .dust()
                .color(0x132b0d)
                .iconSet(ROUGH)
                .build()
                .setFormula("Warm Agar", true);

        FrozenAgarCrystals = new Material.Builder(id++, tjfId("frozen_agar_crystals"))
                .dust()
                .color(0x68db4b)
                .iconSet(SHINY)
                .build()
                .setFormula("Cold Agar", true);

        WaterAgarMix = new Material.Builder(id++, tjfId("water_agar_mix"))
                .liquid()
                .color(0x48dbbe)
                .build()
                .setFormula("H2O?", true);

        BacterialGrowthMedium = new Material.Builder(id++, tjfId("bacterial_growth_medium"))
                .liquid()
                .color(0x0b2e12)
                .build()
                .setFormula("For Bacteria", true);

        DepletedGrowthMedium = new Material.Builder(id++, tjfId("depleted_growth_medium"))
                .liquid()
                .color(0x071209)
                .build()
                .setFormula("Depleted", true);


        BoricAcid = new Material.Builder(id++, tjfId("boric_acid"))
                .liquid()
                .color(0xD5D2D7)
                .iconSet(FLUID)
                .build()
                .setFormula("H3BO3", true);


        FluoroBoricAcid = new Material.Builder(id++, tjfId("fluoroboric_acid"))
                .liquid()
                .color(0xD5D2D7)
                .iconSet(FLUID)
                .build()
                .setFormula("HBF4", true);

        Acetylene = new Material.Builder(id++, tjfId("acetylene"))
                .liquid()
                .color(0x959c60)
                .iconSet(FLUID)
                .build()
                .setFormula("C2H2", true);

        CoAcABCatalyst = new Material.Builder(id++, tjfId("coacab_catalyst"))
                .dust()
                .color(0x755f30)
                .iconSet(FINE)
                .build()
                .setFormula("Co/AC-AB", true);

        SodiumNitrate = new Material.Builder(id++, tjfId("sodium_nitrate"))
                .dust()
                .color(0x846684)
                .iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Sodium, 1, Nitrogen, 1, Oxygen, 3)
                .build();

        SodiumNitrateSolution = new Material.Builder(id++, tjfId("sodium_nitrate_solution"))
                .liquid()
                .color(0xA09ED7)
                .iconSet(FLUID)
                .build()
                .setFormula("H20NaNO3", true);

        SodiumNitrite = new Material.Builder(id++, tjfId("sodium_nitrite"))
                .dust()
                .color((Sodium.getMaterialRGB()+Nitrogen.getMaterialRGB())/2)
                .iconSet(ROUGH)
                .build()
                .setFormula("NaNO2",true);

        Aniline = new Material.Builder(id++, tjfId("aniline"))
                .liquid()
                .color(0x4c911d)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H5NH2", true);

        BenzenediazoniumTetrafluoroborate = new Material.Builder(id++, tjfId("benzenediazonium_tetrafluoroborate"))
                .liquid()
                .color(0xD5D2D7)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H5BF4N2",true);

        BoronFluoride = new Material.Builder(id++, tjfId("boron_fluoride"))
                .liquid()
                .color(0xD5D2D7)
                .iconSet(FLUID)
                .build()
                .setFormula("BF3",true);

        FluoroBenzene = new Material.Builder(id++, tjfId("fluoro_benzene"))
                .liquid()
                .color(0xD5D2D7)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H5F", true);

        Fluorotoluene = new Material.Builder(id++, tjfId("fluorotoluene"))
                .liquid()
                        .color(0xE0DA99)
                        .iconSet(FLUID)
                        .build()
                .setFormula("C7H7F", true);

        ZnFeAlClCatalyst = new Material.Builder(id++, tjfId("znfealcl_catalyst"))
                .dust()
                .color((Zinc.getMaterialRGB()+Iron.getMaterialRGB()+Aluminium.getMaterialRGB()+Chlorine.getMaterialRGB())/4)
                .iconSet(METALLIC)
                .build()
                .setFormula("ZnFeAlCl", true);

        Difluorobenzophenone= new Material.Builder(id++, tjfId("difluorobenzophenone"))
                        .dust()
                        .color((FluoroBenzene.getMaterialRGB()+Fluorotoluene.getMaterialRGB())/2)
                        .iconSet(SHINY)
                        .build()
                        .setFormula("(FC6H4)2CO", true);

        Hydroquinone = new Material.Builder(id++, tjfId("hydroquinone"))
                .liquid()
                .color((Oxygen.getMaterialRGB()+Propene.getMaterialRGB())/2)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H4(OH)2", true);

        Resorcinol = new Material.Builder(id++, tjfId("resorcinol"))
                .liquid()
                .color(0xD5DDBE)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H6O2", true);

        MgClBromide = new Material.Builder(id++, tjfId("mgcl_bromide"))
                .dust()
                .color((MagnesiumChloride.getMaterialRGB()+Bromine.getMaterialRGB())/2)
                .iconSet(ROUGH)
                .build()
                .setFormula("MgClBr", true);

        SiliconFluoride = new Material.Builder(id++, tjfId("silicon_fluoride"))
                .liquid()
                .color(0xB2B4B4)
                .iconSet(FLUID)
                .build()
                .setFormula("SiF4",true);

        FluorosilicicAcid = new Material.Builder(id++, tjfId("fluorosilicic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0x2ccf2a)
                .iconSet(FLUID)
                .build()
                .setFormula("H2SiF6",true);

        AmmoniumFluoride = new Material.Builder(id++, tjfId("ammonium_fluoride"))
                .liquid()
                .color(AmmoniumChloride.getMaterialRGB())
                .iconSet(FLUID)
                .build()
                .setFormula("NH4F",true);

        AmmoniumBifluoride = new Material.Builder(id++, tjfId("ammonium_bifluoride"))
                .dust()
                .color(0x20cfcf)
                .iconSet(ROUGH)
                .build()
                .setFormula("NH4HF2", true);

        AmmoniumBifluorideSolution = new Material.Builder(id++, tjfId("ammonium_bifluoride_solution"))
                .liquid()
                .color((Ammonia.getMaterialRGB()+Fluorine.getMaterialRGB())/2)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)NH4FHF",true);

        AmmoniumVanadate = new Material.Builder(id++, tjfId("ammonium_vanadate"))
                .dust()
                .color(0xf2ff1c)
                .iconSet(FINE)
                .build()
                .setFormula("NH4VO3", true);

        SodiumOxide = new Material.Builder(id++, tjfId("sodium_oxide"))
                .dust()
                .color(0x0373fc)
                .iconSet(SHINY)
                .build()
                .setFormula("Na2O",true);

        SodiumVanadate = new Material.Builder(id++, tjfId("sodium_vanadate"))
                .dust()
                .color(0xf2df1d)
                .iconSet(ROUGH)
                .build()
                .setFormula("Na3VO4",true);

        PureSodiumVanadate = new Material.Builder(id++, tjfId("pure_sodium_vanadate"))
                .dust()
                .color(SodiumVanadate.getMaterialRGB())
                .iconSet(SHINY)
                .build()
                .setFormula("Na3VO4",true);

        YttriumOxide = new Material.Builder(id++, tjfId("yttrium_oxide"))
                .dust()
                .color(0xC6EBB3)
                .iconSet(SAND)
                .components(Yttrium, 2, Oxygen, 3)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        LutetiumOxide = new Material.Builder(id++, tjfId("lutetium_oxide"))
                .dust()
                .color(Lutetium.getMaterialRGB())
                .iconSet(Lutetium.getMaterialIconSet())
                .build()
                .setFormula("Lu2O3",true);

        ThuliumOxide = new Material.Builder(id++, tjfId("thulium_oxide"))
                .dust()
                .color(Thulium.getMaterialRGB())
                .iconSet(Thulium.getMaterialIconSet())
                .build()
                .setFormula("Tm2O3",true);

        LuTmYChlorideSolution = new Material.Builder(id++, tjfId("lutmy_chloride_solution"))
                .liquid()
                .color(0x00f2b2)
                .iconSet(FLUID)
                .build()
                .setFormula("(YCl3)6(LuCl3)2(TmCl3)2(H2O)15",true);

        SodiumMetavanadate = new Material.Builder(id++, tjfId("sodium_metavanadate"))
                .dust()
                .color(SodaAsh.getMaterialRGB())
                .iconSet(FINE)
                .build()
                .setFormula("NaVO3",true);

        Urea = new Material.Builder(id++, tjfId("urea"))
                .dust()
                .color(0x30cf20)
                .iconSet(ROUGH)
                .build()
                .setFormula("CH4N2O",true);

        LuTmYVOPrecipitate = new Material.Builder(id++, tjfId("lutm_yvo_precipitate"))
                .dust()
                .color(0xcf8acf)
                .iconSet(DULL)
                .build()
                .setFormula("Lu/Tm:YVO?",true);

        Ethanol100 = new Material.Builder(id++, tjfId("ethanol_100"))
                .liquid()
                .color(Ethanol.getMaterialRGB())
                .iconSet(FLUID)
                .build()
                .setFormula("C2H5OH",true);

        LuTmYVONanoparticles = new Material.Builder(id++, tjfId("lutm_yvo_nanoparticles"))
                .dust()
                .color(0x206faf)
                .iconSet(SHINY)
                .build()
                .setFormula("Lu/Tm:YVO",true);

        AmmoniumSulfate = new Material.Builder(id++, tjfId("ammonium_sulfate"))
                .liquid()
                .color(0x6464f5)
                .iconSet(FLUID)
                .build()
                .setFormula("(NH4)2SO4",true);

        AmmoniumCarbonate = new Material.Builder(id++, tjfId("ammonium_carbonate"))
                .dust()
                .color(AmmoniumSulfate.getMaterialRGB())
                .iconSet(DULL)
                .build()
                .setFormula("(NH4)2CO3",true);

                SodiumHydride= new Material.Builder(id++, tjfId("sodium_hydride"))
                        .dust()
                        .color(0xcacac8)
                        .iconSet(ROUGH)
                        .build()
                        .setFormula("NaH",true);

        CalciumHydroxide= new Material.Builder(id++, tjfId("calcium_hydroxide"))
                .dust()
                .color(0x5f8764)
                .iconSet(DULL)
                .build()
                .setFormula("Ca(OH)2",true);

                MagnesiumFluoride= new Material.Builder(id++, tjfId("magnesium_fluoride"))
                        .dust()
                        .color(0xcfcfcf)
                        .iconSet(SHINY)
                        .build()
                        .setFormula("MgF2",true);

        TantalumOxide= new Material.Builder(id++, tjfId("tantalum_oxide"))
                .dust()
                .color((Tantalum.getMaterialRGB()+Oxygen.getMaterialRGB())/2)
                .iconSet(SHINY)
                .build()
                .setFormula("Ta2O5",true);

        DielectricMirrorFormationMix = new Material.Builder(id++, tjfId("dielectric_mirror_formation_mix"))
                .liquid()
                        .color(0xff992c)
                        .iconSet(FLUID)
                        .build()
                        .setFormula("MgF2ZnSTa2Ti(C2H6O8)",true);

        NiobiumChloride= new Material.Builder(id++, tjfId("niobium_chloride"))
                .dust()
                .color(Niobium.getMaterialRGB())
                .iconSet(SHINY)
                .build()
                .setFormula("NbCl5",true);

                LithiumAluminiumHydride= new Material.Builder(id++, tjfId("lithium_aluminium_hydride"))
                        .dust()
                        .color(0xc0defc)
                        .iconSet(ROUGH)
                        .build()
                        .setFormula("LiAlH4",true);

        LithiumHydride= new Material.Builder(id++, tjfId("lithium_hydride"))
                .dust()
                .color((Lithium.getMaterialRGB()+Hydrogen.getMaterialRGB())/2)
                .iconSet(ROUGH)
                .build()
                .setFormula("LiH",true);

                AluminiumHydride= new Material.Builder(id++, tjfId("aluminium_hydride"))
                        .dust()
                        .color(0x0b585c)
                        .iconSet(ROUGH)
                        .build()
                        .setFormula("AlH3",true);

        HydrogenPeroxide= new Material.Builder(id++, tjfId("hydrogen_peroxide"))
                .liquid()
                .color(0xD1FFFF)
                .iconSet(FLUID)
                .components(Hydrogen, 2, Oxygen, 2)
                .flags(DISABLE_DECOMPOSITION)
                .build();

                NiobiumHydroxide= new Material.Builder(id++, tjfId("niobium_hydroxide"))
                        .dust()
                        .color(0x7c7c7c)
                        .iconSet(ROUGH)
                        .build()
                        .setFormula("Nb(OH)5",true);

        OxalicAcid= new Material.Builder(id++, tjfId("oxalic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0x4aaae2)
                .iconSet(FLUID)
                .build()
                .setFormula("HOOCCOOH",true);

                AmmoniumNiobiumOxalateSolution= new Material.Builder(id++, tjfId("ammonium_niobium_oxalate_solution"))
                        .liquid()
                        .color(0x6c6cac)
                        .iconSet(FLUID)
                        .build()
                        .setFormula("(NH4)C10Nb2O20",true);

        IndiumPhospide= new Material.Builder(id++, tjfId("indium_phosphide"))
                .dust()
                .color(0x5c9c9c)
                .iconSet(SHINY)
                .flags(DISABLE_DECOMPOSITION)
                .components(Indium, 1, Phosphorus, 1)
                .build();

                AmmoniumNitrate= new Material.Builder(id++, tjfId("ammonium_nitrate"))
                        .liquid()
                        .color(Ammonia.getMaterialRGB())
                        .iconSet(FLUID)
                        .build()
                        .setFormula("NH4NO3",true);

        Ethanolamine= new Material.Builder(id++, tjfId("ethanolamine"))
                .liquid()
                .color(0x6f7d87)
                .iconSet(FLUID)
                .build()
                .setFormula("HOCH2CH2NH2",true);

        Ethylenediamine= new Material.Builder(id++, tjfId("ethylenediamine"))
                .liquid()
                        .color(Ethanolamine.getMaterialRGB())
                        .iconSet(FLUID)
                        .build()
                        .setFormula("C2H4(NH2)2",true);

        Formaldehyde= new Material.Builder(id++, tjfId("formaldehyde"))
                .liquid()
                .color(0x95a13a)
                .iconSet(FLUID)
                .build()
                .setFormula("CH2O",true);

        SodiumCyanide = new Material.Builder(id++, tjfId("sodium_cyanide"))
                .liquid()
                        .color(0x5f7c8c)
                        .iconSet(FLUID)
                        .build()
                        .setFormula("NaCN",true);

        EDTASolution= new Material.Builder(id++, tjfId("edta_solution"))
                .liquid()
                .color(0x0026d9)
                .iconSet(FLUID)
                .build()
                .setFormula("(C10H16N2O8)3(C2H8N2)O2",true);

        EDTA = new Material.Builder(id++, tjfId("edta"))
                .liquid()
                        .color(0x0026d9)
                        .iconSet(FLUID)
                        .build()
                        .setFormula("C10H16N2O8",true);

        Glycine = new Material.Builder(id++, tjfId("glycine"))
                .liquid()
                .color((Ethylenediamine.getMaterialRGB()+Formaldehyde.getMaterialRGB())/2)
                .iconSet(FLUID)
                .build()
                .setFormula("NH2CH2COOH",true);

        CaesiumHydroxide = new Material.Builder(id++, tjfId("caesium_hydroxide"))
                        .dust()
                        .color(Caesium.getMaterialRGB()-10)
                        .iconSet(ROUGH)
                        .build()
                        .setFormula("CsOH",true);

        CaesiumBromideSolution= new Material.Builder(id++, tjfId("caesium_bromide_solution"))
                .liquid()
                .color((Caesium.getMaterialRGB()-10+SaltWater.getMaterialRGB())/2)
                .iconSet(FLUID)
                .build()
                .setFormula("CsBr(H2O)",true);

        Sarcosine = new Material.Builder(id++, tjfId("sarcosine"))
                .dust()
                .color((Glycine.getMaterialRGB() + Oxygen.getMaterialRGB()) / 2)
                .iconSet(SHINY)
                .build()
                .setFormula("C3H7NO2", true);

        PraseodymiumOxide = new Material.Builder(id++, tjfId("praseodymium_oxide"))
                .dust()
                .color(Praseodymium.getMaterialRGB())
                .iconSet(Praseodymium.getMaterialIconSet())
                .build()
                .setFormula("Pr2O3",true);

                HolmiumOxide = new Material.Builder(id++, tjfId("holmium_oxide"))
                        .dust()
                        .color(Holmium.getMaterialRGB())
                        .iconSet(Holmium.getMaterialIconSet())
                        .build()
                        .setFormula("Ho2O3",true);

        NeodymiumOxide = new Material.Builder(id++, tjfId("neodymium_oxide"))
                        .dust()
                        .color(Neodymium.getMaterialRGB())
                        .iconSet(Neodymium.getMaterialIconSet())
                        .build()
                        .setFormula("Nd2O3",true);

                PrYHoNitrateSolution = new Material.Builder(id++, tjfId("pryho_nitrate_solution"))
                        .liquid()
                        .color(0x00f2b2)
                        .iconSet(FLUID)
                        .build()
                        .setFormula("(Y(NO3)3)6(Pr(NO3)3)2(Nd(NO3)3)2(H2O)15",true);

        CetaneTrimethylAmmoniumBromide = new Material.Builder(id++, tjfId("cetane_trimethyl_ammonium_bromide"))
                .liquid()
                .color(0xb9c1c9)
                .iconSet(FLUID)
                .build()
                .setFormula("C19H42BrN",true);



                PrHoYLFNanoparticles= new Material.Builder(id++, tjfId("prho_ylf_nanoparticles"))
                        .dust()
                        .color(0xcf8acf)
                        .iconSet(SHINY)
                        .build()
                        .setFormula("Pr/Ho:YLF",true);

        BerylliumFluoride= new Material.Builder(id++, tjfId("beryllium_fluoride"))
                .ingot(2)
                .color(0x757575)
                .iconSet(SHINY)
                .components(Beryllium, 1, Fluorine, 2)
                .build();

                ChlorinatedSolvents = new Material.Builder(id++, tjfId("chlorinated_solvents"))
                        .liquid()
                        .color(0x40804c)
                        .iconSet(FLUID)
                        .build()
                        .setFormula("(CH4)2Cl5",true);

        Dichloromethane= new Material.Builder(id++, tjfId("dichloromethane"))
                .liquid()
                .color(Chloromethane.getMaterialRGB()-10)
                .iconSet(FLUID)
                .build()
                .setFormula("CH2Cl2",true);

                CarbonTetrachloride = new Material.Builder(id++, tjfId("carbon_tetrachloride"))
                        .liquid()
                        .color(0x2d8020)
                        .iconSet(FLUID)
                        .build()
                        .setFormula("CCl4",true);

        Butanol= new Material.Builder(id++, tjfId("butanol"))
                .liquid()
                .color((FermentedBiomass.getMaterialRGB()+20))
                .iconSet(FLUID)
                .build()
                .setFormula("C4H9OH",true);

                ButanolGas = new Material.Builder(id++, tjfId("butanol_gas"))
                        .gas()
                        .color(Butanol.getMaterialRGB()+20)
                        .iconSet(FLUID)
                        .build()
                        .setFormula("C4H9OH",true);

        Tributylamine = new Material.Builder(id++, tjfId("tributylamine"))
                .liquid()
                .color(0x801a80)
                .iconSet(FLUID)
                .build()
                .setFormula("(C4H9)3N",true);

                Alumina= new Material.Builder(id++, tjfId("alumina"))
                        .dust()
                        .color(0x0b585c)
                        .iconSet(ROUGH)
                        .build()
                        .setFormula("Al2O3",true);

        AluminiumNitrate= new Material.Builder(id++, tjfId("aluminium_nitrate"))
                .dust()
                .color(Alumina.getMaterialRGB())
                .iconSet(SHINY)
                .build()
                .setFormula("Al(NO3)3",true);

                CrudeAluminaSolution= new Material.Builder(id++, tjfId("crude_alumina_solution"))
                        .liquid()
                        .color(Aluminium.getMaterialRGB()-30)
                        .iconSet(FLUID)
                        .build()
                        .setFormula("Al(NO3)3)2(CH2Cl2)(C12H27N)",true);

        HydrogenCyanide= new Material.Builder(id++, tjfId("hydrogen_cyanide"))
                .liquid()
                .color(0xb6d1ae)
                .iconSet(FLUID)
                .build()
                .setFormula("HCN",true);

        PotassiumManganate= new Material.Builder(id++, tjfId("potassium_manganate"))
                .dust()
                .color(0xaf20af)
                .iconSet(DULL)
                .build()
                .setFormula("K2MnO4",true);

                PotassiumPermanganate= new Material.Builder(id++, tjfId("potassium_permanganate"))
                        .dust()
                        .color(PotassiumManganate.getMaterialRGB()-15)
                        .iconSet(ROUGH)
                        .build()
                        .setFormula("KMnO4",true);

        ManganeseSulfate= new Material.Builder(id++, tjfId("manganese_sulfate"))
                .dust()
                .color((Manganese.getMaterialRGB()+Sulfur.getMaterialRGB())/2)
                .iconSet(ROUGH)
                .build()
                .setFormula("MnSO4",true);

                PotassiumSulfate= new Material.Builder(id++, tjfId("potassium_sulfate"))
                        .dust()
                        .color((Potassium.getMaterialRGB()+Sulfur.getMaterialRGB())/2)
                        .iconSet(FINE)
                        .build()
                        .setFormula("K2SO4",true);

        AmmoniumCyanate= new Material.Builder(id++, tjfId("ammonium_cyanate"))
                .liquid()
                .color(0x3a5dcf)
                .iconSet(FLUID)
                .build()
                .setFormula("NH4CNO",true);

        PotassiumHydroxide= new Material.Builder(id++, tjfId("potassium_hydroxide"))
                .liquid()
                .color((Potassium.getMaterialRGB()+Hydrogen.getMaterialRGB()+Oxygen.getMaterialRGB())/3)
                .iconSet(FLUID)
                .build()
                .setFormula("KOH",true);

        LithiumFluoride = new Material.Builder(id++, tjfId("lithium_fluoride"))
                .ingot()
                .color(0x757575)
                .iconSet(SHINY)
                .components(Lithium, 1, Fluorine, 1)
                .build();

        AluminaSolution = new Material.Builder(id++, tjfId("alumina_solution"))
                .liquid()
                .color((Aluminium.getMaterialRGB()-15))
                .iconSet(FLUID)
                .build()
                .setFormula("(Al2O3)(CH2Cl2)(C12H27N)2",true);

        PreciousMetal= new Material.Builder(id++, tjfId("precious_metal"))
                .ingot(2).ore()
                .color(0xB99023)
                .iconSet(SHINY)
                .build()
                .setFormula("Au?", true);

                GoldAlloy= new Material.Builder(id++, tjfId("gold_alloy"))
                        .ingot(2)
                        .color(0xBBA52B)
                        .iconSet(SHINY)
                        .build()
                        .setFormula("Cu3Au?",true);

        GoldLeach= new Material.Builder(id++, tjfId("gold_leach"))
                .dust()
                .color(0xBBA52B)
                .iconSet(ROUGH)
                .build()
                .setFormula("Cu3Au?",true);

                CopperLeach= new Material.Builder(id++, tjfId("copper_leach"))
                        .dust()
                        .color(0x765A30)
                        .iconSet(SHINY)
                        .build()
                        .setFormula("Cu3?",true);

        ChloroauricAcid = new Material.Builder(id++, tjfId("chloroauric_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0xDFD11F)
                .iconSet(FLUID)
                .build()
                .setFormula("HAuCl?", true);

        PotassiumMetabisulfite = new Material.Builder(id++, tjfId("potassium_metabisulfite"))
                .dust()
                .color(0xFFFFFF)
                .iconSet(DULL)
                .components(Potassium, 2, Sulfur, 2, Oxygen, 5)
                .build();

        OrthoXylene = new Material.Builder(id++, tjfId("ortho_xylene"))
                .liquid()
                .color(0xB9575E)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H4(CH3)2", true);

        Durene = new Material.Builder(id++, tjfId("durene"))
                .dust()
                .color(0xA39C95)
                .iconSet(ROUGH)
                .build()
                .setFormula("C6H2(CH3)4", true);

        PyromelliticDianhydride = new Material.Builder(id++, tjfId("pyromellitic_dianhydride"))
                .dust()
                .color(0xF0EAD6)
                .iconSet(SHINY)
                .build()
                .setFormula("C6H2(C2O3)2", true);

        Oxydianiline = new Material.Builder(id++, tjfId("oxydianiline"))
                .liquid()
                .color(0xF0E130)
                .iconSet(FLUID)
                .build()
                .setFormula("C12H12N2O", true);
        PolyamicAcid = new Material.Builder(id++, tjfId("polyamic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0xFFAE42)
                .iconSet(FLUID)
                .build()
                .setFormula("C22H14N2O7", true);

        Hexafluoropropylene = new Material.Builder(id++, tjfId("hexafluoropropylene"))
                .liquid()
                .color(0x111111)
                .iconSet(FLUID)
                .build()
                .setFormula("C3F6", true);

        FluorinatedEthylenePropylene = new Material.Builder(id++, tjfId("fluorinated_ethylene_propylene"))
                .ingot().liquid()
                .color(0xC8C8C8)
                .iconSet(DULL)
                .flags(GENERATE_PLATE, FLAMMABLE, NO_SMASHING, DISABLE_DECOMPOSITION, NO_ALLOY_BLAST_RECIPES)
                .components(Carbon, 5, Fluorine, 10)
                .build();

        Taranium = new Material.Builder(id++, tjfId("taranium"))
                .ingot(7).liquid()
                .color(0x0c0c0d)
                .iconSet(SHINY)
                .flags(CORE_METAL)
                .element(Tn)
                .blast(10000)
                .build();

        NaquadriaticTaranium = new Material.Builder(id++, tjfId("naquadriatic_taranium"))
                .ingot()
                .color((Naquadria.getMaterialRGB() + Taranium.getMaterialRGB()) / 2)
                .iconSet(SHINY)
                .flags(STD_METAL, GENERATE_LONG_ROD)
                .components(Naquadria, 1, Taranium, 1)
                .cableProperties(GTValues.V[GTValues.UXV], 2, 32)
                .blast(11200)
                .build();

        BlackTitanium = new Material.Builder(id++, tjfId("black_titanium"))
                .ingot()
                .color(0x6C003B)
                .iconSet(SHINY)
                .flags(CORE_METAL, DISABLE_DECOMPOSITION)
                .components(Titanium, 26, Lanthanum, 6, Tungsten, 4, Cobalt, 3, Manganese, 2, Phosphorus, 2, Palladium, 2, Niobium, 1, Argon, 5)
                .cableProperties(GTValues.V[GTValues.UIV], 2, 32)
                .blast(11500)
                .build();

        FullerenePolymerMatrix = new Material.Builder(id++, tjfId("fullerene_polymer_matrix"))
                .polymer(2).liquid()
                .color(0x403e37)
                .iconSet(DULL)
                .flags(EXCLUDE_BLOCK_CRAFTING_RECIPES, GENERATE_FOIL, NO_SMASHING, DISABLE_DECOMPOSITION)
                .components(Palladium, 1, Iron, 1, Carbon, 153, Hydrogen, 36, Nitrogen, 1, Oxygen, 2)
                .build();

        Zylon = new Material.Builder(id++, tjfId("zylon"))
                .polymer(2).liquid()
                .color(0xFFE000)
                .iconSet(SHINY)
                .flags(EXCLUDE_BLOCK_CRAFTING_RECIPES, GENERATE_FOIL, NO_SMASHING, DISABLE_DECOMPOSITION)
                .components(Carbon, 14, Hydrogen, 6, Nitrogen, 2, Oxygen, 2)
                .build();

        SupercriticalSteam = new Material.Builder(id++, tjfId("supercritical_steam"))
                .liquid()
                .color(Steam.getMaterialRGB())
                .iconSet(FLUID)
                .build()
                .setFormula("H2O", true);

        SodiumPotassiumAlloy = new Material.Builder(id++, tjfId("sodium_potassium_alloy"))
                .dust(2).liquid()
                .color(0x252525)
                .iconSet(SHINY)
                .components(Sodium, 7, Potassium, 3)
                .build();

        SupercriticalSodiumPotassiumAlloy = new Material.Builder(id++, tjfId("supercritical_sodium_potassium_alloy"))
                .liquid()
                .color(SodiumPotassiumAlloy.getMaterialRGB())
                .iconSet(FLUID)
                .components(SodiumPotassiumAlloy, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Na7K3", true);

        FLiNaK = new Material.Builder(id++, tjfId("flinak"))
                .dust(2).liquid()
                .color(0x252525)
                .iconSet(DULL)
                .components(Fluorine, 3, Lithium, 1, Sodium, 1, Potassium, 1)
                .build();

        SupercriticalFLiNaK = new Material.Builder(id++, tjfId("supercritical_flinak"))
                .liquid()
                .color(FLiNaK.getMaterialRGB())
                .iconSet(FLUID)
                .components(FLiNaK, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        FLiBe = new Material.Builder(id++, tjfId("flibe"))
                .dust(2).liquid()
                .color(0x252525)
                .iconSet(DULL)
                .components(Fluorine, 3, Lithium, 1, Beryllium, 1)
                .build();

        SupercriticalFLiBe = new Material.Builder(id++, tjfId("supercritical_flibe"))
                .liquid()
                .color(FLiBe.getMaterialRGB())
                .components(FLiBe, 1)
                .flags(DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .build();

        LeadBismuthEutectic = new Material.Builder(id++, tjfId("lead_bismuth_eutatic"))
                .ingot().liquid()
                .color(0x757575)
                .iconSet(SHINY)
                .components(Lead, 3, Bismuth, 7)
                .build();

        SupercriticalLeadBismuthEutectic = new Material.Builder(id++, tjfId("supercritical_lead_bismuth_eutatic"))
                .liquid()
                .color(LeadBismuthEutectic.getMaterialRGB())
                .iconSet(FLUID)
                .flags(DISABLE_DECOMPOSITION)
                .components(LeadBismuthEutectic, 1)
                .build();

        HastelloyN = new Material.Builder(id++, tjfId("hastelloy_n"))
                .ingot(6).liquid()
                .color(0xDDDDDD)
                .iconSet(METALLIC)
                .flags(EXT2_METAL, GENERATE_FRAME, GENERATE_DENSE, GENERATE_GEAR)
                .components(Yttrium, 2, Molybdenum, 4, Chrome, 2, Titanium, 2, Nickel, 15)
                .blast(4350)
                .build();

        Lafium = new Material.Builder(id++, tjfId("lafium"))
                .ingot(7)
                .color(0x0d0d60)
                .iconSet(SHINY)
                .flags(CORE_METAL, DISABLE_DECOMPOSITION)
                .fluidPipeProperties(23000, 2000, true, true, true, true)
                .components(HastelloyN, 8, Naquadah, 4, Samarium, 2, Tungsten, 4, Argon, 2, Aluminium, 6, Nickel, 8, Carbon, 2)
                .blast(9865)
                .build();

        HeavyQuarkDegenerateMatter = new Material.Builder(id++, tjfId("heavy_quark_degenerate_matter"))
                .ingot(6)
                .color(0x5dbd3a)
                .iconSet(SHINY)
                .flags(CORE_METAL)
                .blast(13000)
                .build()
                .setFormula("?");

        MetastableFlerovium = new Material.Builder(id++, tjfId("metastable_flerovium"))
                .ingot(6)
                .color(0x521973)
                .iconSet(SHINY)
                .flags(CORE_METAL)
                .element(Fl)
                .blast(11240)
                .build();

        QCDMatter = new Material.Builder(id++, tjfId("qcd_confined_matter"))
                .ingot(7)
                .color(0xeb9e3f)
                .iconSet(SHINY)
                .flags(GENERATE_PLATE, DISABLE_REPLICATION, NO_WORKING, NO_SMELTING, NO_SMASHING, GENERATE_FRAME, GENERATE_ROD)
                .components()
                .blast(13100)
                .build()
                .setFormula("?");

        TitanSteel = new Material.Builder(id++, tjfId("titan_steel"))
                .ingot(7)
                .color(0xAA0d0d)
                .iconSet(SHINY)
                .flags(CORE_METAL, DISABLE_DECOMPOSITION)
                .components(TungstenTitaniumCarbide, 3, Jasper, 3)
                .cableProperties(GTValues.V[GTValues.UEV], 2, 16)
                .blast(9200)
                .build();

        MetastableHassium = new Material.Builder(id++, tjfId("metastable_hassium"))
                .ingot(6)
                .color(0x2d3a9d)
                .iconSet(SHINY)
                .flags(CORE_METAL)
                .element(Hs)
                .blast(11240)
                .build();

        Vibranium = new Material.Builder(id++, tjfId("vibranium"))
                .ingot(7).liquid()
                .color(0x828aad)
                .iconSet(SHINY)
                .flags(CORE_METAL)
                .element(Vb)
                .blast(11220)
                .build();

        TriniumTitanium = new Material.Builder(id++, tjfId("trinium_titanium"))
                .ingot(7).liquid()
                .color(0x9986a3)
                .iconSet(SHINY)
                .flags(CORE_METAL)
                .components(Trinium, 2, Titanium, 1)
                .blast(11000)
                .build();

        TantalumHafniumSeaborgiumCarbide = new Material.Builder(id++, tjfId("tantalum_hafnium_seaborgium_carbide"))
                .ingot(6)
                .color(0x2c2c2c)
                .iconSet(SHINY)
                .flags(GENERATE_PLATE, EXCLUDE_BLOCK_CRAFTING_RECIPES, DISABLE_DECOMPOSITION)
                .components(Tantalum, 12, Hafnium, 3, Seaborgium, 1, Carbon, 16)
                .blast(5200)
                .build();

        Nitinol60 = new Material.Builder(id++, tjfId("nitinol_a"))
                .ingot(4)
                .color(0xCCB0EC)
                .iconSet(METALLIC)
                .flags(EXT2_METAL, GENERATE_FRAME)
                .components(Nickel, 2, Titanium, 3)
                .blast(Titanium.getBlastTemperature())
                .build();

        ActiniumSuperhydride = new Material.Builder(id++, tjfId("actinium_superhydride"))
                .dust()
                .color(Actinium.getMaterialRGB() * 9 / 8)
                .iconSet(SHINY)
                .build()
                .setFormula("AcH12", true);

        BETSPerrhenate = new Material.Builder(id++, tjfId("bets_perrhenate"))
                .dust()
                .color(0x7ada00)
                .iconSet(SHINY)
                .build()
                .setFormula("ReC10H8S4Se4O4", true);

        BorocarbideDust = new Material.Builder(id++, tjfId("borocarbide_dust"))
                .dust()
                .color(0x9a9a2a)
                .iconSet(SHINY)
                .build()
                .setFormula("B4C7Fr4At6Ho2Th2Fl2Cn2", true);

        FullereneSuperconductiveDust = new Material.Builder(id++, tjfId("fullerene_superconductor_dust"))
                .dust()
                .color(0x99cc00)
                .iconSet(SHINY)
                .build()
                .setFormula("LaCsRb(C60)2", true);

        MetastableOganesson = new Material.Builder(id++, tjfId("metastable_oganesson"))
                .ingot(7)
                .color(0xE61C24)
                .iconSet(SHINY)
                .flags(CORE_METAL)
                .element(Og)
                .blast(10380)
                .build();

        ProtoAdamantium = new Material.Builder(id++, tjfId("proto_adamantium"))
                .ingot(7)
                .color(0x4662d4)
                .iconSet(SHINY)
                .flags(CORE_METAL)
                .components(Adamantium, 3, Promethium, 2)
                .blast(11244)
                .build();

        SuperheavyHAlloy = new Material.Builder(id++, tjfId("superheavy_h_alloy"))
                .ingot(6)
                .color(0xE84B36)
                .iconSet(SHINY)
                .flags(GENERATE_PLATE, DISABLE_DECOMPOSITION)
                .components(Copernicium, 1, Nihonium, 1, MetastableFlerovium, 1, Moscovium, 1, Livermorium, 1, Tennessine, 1, MetastableOganesson, 1)
                .blast(10600)
                .build();

        ChargedCaesiumCeriumCobaltIndium = new Material.Builder(id++, tjfId("charged_caesium_cerium_cobalt_indium"))
                .dust()
                .color(0x52ad25)
                .iconSet(SHINY)
                .build()
                .setFormula("CsCeCo2In10", true);

        RheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate = new Material.Builder(id++, tjfId("rhenium_hassium_thallium_isophtaloylbisdiethylthiourea"))
                .dust()
                .color(0xa26a8b)
                .iconSet(SHINY)
                .build()
                .setFormula("ReHsTlC60PN12H84S6O12F6", true);

        Legendarium = new Material.Builder(id++, tjfId("legendarium"))
                .dust()
                .color(0xffffff)
                .iconSet(SHINY)
                .build()
                .setFormula("NqNq+*Nq*DrTrKeTnAdVb", true);

        LanthanumFullereneMix = new Material.Builder(id++, tjfId("lanthanum_fullerene_mix"))
                .dust()
                .color(0xdfcafa)
                .iconSet(SHINY)
                .build()
                .setFormula("(C60)2La2?", true);

        LanthanumFullereneNanotubes = new Material.Builder(id++, tjfId("lanthanum_fullerene_nanotubes"))
                .dust()
                .color(LanthanumFullereneMix.getMaterialRGB() * 3 / 5)
                .iconSet(SHINY)
                .build()
                .setFormula("La2(C60)2CNT", true);

        UEVSuperconductor = new Material.Builder(id++, tjfId("uev_superconductor"))
                .ingot(1)
                .color(0x954fe0)
                .iconSet(SHINY)
                .flags(STD_METAL, DISABLE_DECOMPOSITION)
                .components(ActiniumSuperhydride, 1, BETSPerrhenate, 1, TriniumTitanium, 2, Quantum, 1, Vibranium, 2)
                .cableProperties(GTValues.V[GTValues.UEV], 4, 0, true)
                .blast(11600)
                .build();

        UIVSuperconductor = new Material.Builder(id++, tjfId("uiv_superconductor"))
                .ingot(1)
                .color(0x8bf743)
                .iconSet(SHINY)
                .flags(STD_METAL, DISABLE_DECOMPOSITION)
                .components(BorocarbideDust, 2, FullereneSuperconductiveDust, 1, MetastableOganesson, 2, ProtoAdamantium, 2)
                .cableProperties(GTValues.V[GTValues.UIV], 4, 0, true)
                .blast(11600)
                .build();

        UXVSuperconductor = new Material.Builder(id++, tjfId("uxv_superconductor"))
                .ingot(1)
                .color(0x883afc)
                .iconSet(SHINY)
                .flags(STD_METAL, DISABLE_DECOMPOSITION)
                .components(BlackTitanium, 3, SuperheavyHAlloy, 2, ChargedCaesiumCeriumCobaltIndium, 3, RheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate, 6)
                .cableProperties(GTValues.V[GTValues.UXV], 4, 0, true)
                .blast(12000)
                .build();

        OpVSuperconductor = new Material.Builder(id++, tjfId("opv_superconductor"))
                .ingot(1)
                .color(0xe34b5a)
                .iconSet(SHINY)
                .flags(STD_METAL, DISABLE_DECOMPOSITION)
                .components(Neutronium, 4, Legendarium, 5, ActiniumSuperhydride, 5, LanthanumFullereneNanotubes, 4, RheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate, 12)
                .cableProperties(GTValues.V[GTValues.OpV], 4, 0, true)
                .blast(14000)
                .build();

        SuperheavyLAlloy = new Material.Builder(id++, tjfId("superheavy_l_alloy"))
                .ingot(6)
                .color(0x2B45DF)
                .iconSet(SHINY)
                .flags(GENERATE_PLATE, DISABLE_DECOMPOSITION)
                .components(Rutherfordium, 1, Dubnium, 1, Seaborgium, 1, Bohrium, 1, MetastableHassium, 1, Meitnerium, 1, Roentgenium, 1)
                .blast(10600)
                .build();

        HexanitroHexaazaisowurtzitane = new Material.Builder(id++, tjfId("hexanitrohexaazaisowurtzitane"))
                .dust()
                .color(0x414a4f)
                .iconSet(SHINY)
                .build()
                .setFormula("C6H6N12O12", true);

        Glyceryl = new Material.Builder(id++, tjfId("glyceryl"))
                .liquid()
                .color(38550)
                .iconSet(FLUID)
                .flags(FLAMMABLE, EXPLOSIVE, NO_SMASHING)
                .components(Carbon, 3, Hydrogen, 5, Nitrogen, 3, Oxygen, 9)
                .build();

        ElectronDegenerateRheniumPlasma = new Material.Builder(id++, tjfId("degenerate_rhenium_plasma"))
                .plasma(new FluidBuilder().temperature(100000000))//TODO fix temperature
                .flags(DISABLE_REPLICATION, DISABLE_DECONSTRUCTION)
                .color(0x6666FF)
                .iconSet(FLUID)
                .build()
                .setFormula("Rh", true);

        SuperheavyMix = new Material.Builder(id++, tjfId("superheavy_mix"))
                .liquid()
                .color(0x403737)
                .iconSet(FLUID)
                .build()
                .setFormula("SgBhRfDb", true);

        NeutronPlasma = new Material.Builder(id++, tjfId("neutron_plasma"))
                .plasma(new FluidBuilder().temperature(1000000000))//TODO fix temperature
                .flags(DISABLE_REPLICATION, DISABLE_DECONSTRUCTION)
                .color(0xf0e9e9)
                .iconSet(FLUID)
                .build()
                .setFormula("n", true);

        FreeAlphaGas = new Material.Builder(id++, tjfId("free_alpha_gas"))
                .liquid()
                .color(0xe0d407)
                .iconSet(FLUID)
                .build()
                .setFormula("a", true);

        FreeElectronGas = new Material.Builder(id++, tjfId("free_electron_gas"))
                .liquid()
                .color(0x044c4c)
                .iconSet(FLUID)
                .build()
                .setFormula("e-", true);


        PotassiumCarbonate = new Material.Builder(id++, tjfId("potassium_carbonate"))
                .dust()
                .color((Potassium.getMaterialRGB() + Carbon.getMaterialRGB() + Oxygen.getMaterialRGB()) / 3)
                .iconSet(FINE)
                .build()
                .setFormula("K2CO3", true);

        PotassiumBisulfite = new Material.Builder(id++, tjfId("potassium_bisulfite"))
                .dust()
                .color(0xF0EAD6)
                .iconSet(DULL)
                .build()
                .setFormula("KHSO3", true);

        PotassiumNitrite = new Material.Builder(id++, tjfId("potassium_nitrite"))
                .dust()
                .color(0xF0EAD6)
                .iconSet(DULL)
                .build()
                .setFormula("KNO2", true);

        NitrousAcid = new Material.Builder(id++, tjfId("nitrous_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0x1e73b0)
                .iconSet(FLUID)
                .build()
                .setFormula("HNO2", true);


        AceticAnhydride = new Material.Builder(id++, tjfId("acetic_anhydride"))
                .liquid()
                .color(0xD5DDDF)
                .iconSet(FLUID)
                .build()
                .setFormula("(CH3CO)2O", true);

        SodiumAcetate = new Material.Builder(id++, tjfId("sodium_acetate"))
                .liquid()
                .color((Sodium.getMaterialRGB() + AceticAnhydride.getMaterialRGB()) / 2)
                .iconSet(FLUID)
                .build()
                .setFormula("C2H3NaO2", true);

        PotassiumHydroxylaminedisulfonate = new Material.Builder(id++, tjfId("potassium_hydroxylaminedisulfonate"))
                .dust()
                .color((0xF0EAD6 + NitrousAcid.getMaterialRGB()) / 2)
                .iconSet(DULL)
                .build()
                .setFormula("KHSO3", true);

        HydroxylammoniumSulfate = new Material.Builder(id++, tjfId("hydroxylammonium_sulfate"))
                .dust()
                .color(0xF0EAD6)
                .iconSet(DULL)
                .build()
                .setFormula("(NH3OH)2SO4", true);

        BariumChloride = new Material.Builder(id++, tjfId("barium_chloride"))
                .dust()
                .color((Barium.getMaterialRGB() + Chlorine.getMaterialRGB()) / 2)
                .iconSet(SHINY)
                .build()
                .setFormula("BaCl2", true);


        BariumSulfateSolution = new Material.Builder(id++, tjfId("barium_sulfate_solution"))
                .liquid()
                .color(Barite.getMaterialRGB())
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)BaSO4", true);

        HydroxylamineHydrochloride = new Material.Builder(id++, tjfId("hydroxylamine_hydrochloride"))
                .liquid()
                .color(((Barium.getMaterialRGB() + Chlorine.getMaterialRGB()) / 2 + 0xF0EAD6) / 2)
                .iconSet(FLUID)
                .build()
                .setFormula("HONH2HCl", true);

        MaleicAnhydride = new Material.Builder(id++, tjfId("maleic_anhydride"))
                .liquid()
                .color(0x3c20ad)
                .iconSet(FLUID)
                .build()
                .setFormula("C4H2O3", true);

        SuccinicAcid = new Material.Builder(id++, tjfId("succinic_acid"))
                .dust()
                .color((MaleicAnhydride.getMaterialRGB() + Water.getMaterialRGB() + Hydrogen.getMaterialRGB()) / 3)
                .iconSet(ROUGH)
                .build()
                .setFormula("C4H6O4", true);

        SuccinicAnhydride = new Material.Builder(id++, tjfId("succinic_anhydride"))
                .dust()
                .color((SuccinicAcid.getMaterialRGB() + AceticAnhydride.getMaterialRGB()) / 2)
                .iconSet(DULL)
                .build()
                .setFormula("(CH2CO)2O", true);

        Tetrahydrofuran = new Material.Builder(id++, tjfId("tetrahydrofuran"))
                .liquid()
                .color(0xb7ebcd)
                .iconSet(FLUID)
                .build()
                .setFormula("(CH2)4O", true);

        NHydroxysuccinimide = new Material.Builder(id++, tjfId("n-hydroxysuccinimide"))
                .dust()
                .color(0xdbcae3)
                .iconSet(DULL)
                .build()
                .setFormula("(CH2CO)2NOH", true);

        Triethylamine = new Material.Builder(id++, tjfId("triethylamine"))
                .liquid()
                .color(Ethylenediamine.getMaterialRGB())
                .iconSet(FLUID)
                .build()
                .setFormula("N(CH2CH3)3", true);

        SuccinimidylAcetate = new Material.Builder(id++, tjfId("succinimidyl_acetate"))
                .dust()
                .color(0xbd93a6)
                .iconSet(DULL)
                .build()
                .setFormula("C6H7NO4", true);

        SeleniumOxide = new Material.Builder(id++, tjfId("selenium_oxide"))
                .dust()
                .color(0xFFFF66)
                .iconSet(SHINY)
                .build()
                .setFormula("SeO2", true);

        SelenousAcid = new Material.Builder(id++, tjfId("selenous_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color((0xFFFF66 + Water.getMaterialRGB()) / 2)
                .iconSet(FLUID)
                .build()
                .setFormula("H2SeO3", true);

        AmmoniumAcetate = new Material.Builder(id++, tjfId("ammonium_acetate"))
                .dust()
                .color(0xb6dee0)
                .iconSet(DULL)
                .build()
                .setFormula("NH4CH3CO2", true);

        Acetamide = new Material.Builder(id++, tjfId("acetamide"))
                .dust()
                .color(0xa6bebf)
                .iconSet(DULL)
                .build()
                .setFormula("CH3CONH2", true);

        Acetonitrile = new Material.Builder(id++, tjfId("acetonitrile"))
                .dust()
                .color(0xa2afb0)
                .iconSet(DULL)
                .build()
                .setFormula("CH3CN", true);

        BenzylChloride = new Material.Builder(id++, tjfId("benzyl_chloride"))
                .liquid()
                .color(0xaef7fc)
                .iconSet(FLUID)
                .build()
                .setFormula("C7H7Cl", true);

        Hexamethylenetetramine = new Material.Builder(id++, tjfId("hexamethylenetetramine"))
                .dust()
                .color(0x7e8d94)
                .iconSet(DULL)
                .build()
                .setFormula("(CH2)6N4", true);

        Benzylamine = new Material.Builder(id++, tjfId("benzylamine"))
                .liquid()
                .color(0x5c8082)
                .iconSet(FLUID)
                .build()
                .setFormula("C7H9N", true);

        Glyoxal = new Material.Builder(id++, tjfId("glyoxal"))
                .liquid()
                .color(0xf2f068)
                .iconSet(FLUID)
                .build()
                .setFormula("C2H2O2", true);

        Hexabenzylhexaazaisowurtzitane = new Material.Builder(id++, tjfId("hexabenzylhexaazaisowurtzitane"))
                .dust()
                .color(0x624573)
                .iconSet(DULL)
                .build()
                .setFormula("C48N6H48", true);

        PalladiumChloride = new Material.Builder(id++, tjfId("palladium_chloride"))
                .dust()
                .color(0xb9c0c7)
                .iconSet(SHINY)
                .build()
                .setFormula("PdCl2", true);

        PdCCatalyst = new Material.Builder(id++, tjfId("pdc_catalyst"))
                .dust()
                .color((Palladium.getMaterialRGB() + Carbon.getMaterialRGB()) / 2)
                .iconSet(DULL)
                .build()
                .setFormula("PdC", true);


        HydrobromicAcid = new Material.Builder(id++, tjfId("hydrobromic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0xBC6C53)
                .iconSet(FLUID)
                .build()
                .setFormula("HBr", true);

        Dimethylformamide = new Material.Builder(id++, tjfId("dimethylformamide"))
                .liquid()
                .color(0x42bdff)
                .iconSet(FLUID)
                .build()
                .setFormula("(CH3)2NCH", true);

        DibenzylTetraacetylhexaazaisowurtzitane = new Material.Builder(id++, tjfId("dibenzyltetraacetylhexaazaisowurtzitane"))
                .dust()
                .color(0xb3c98b)
                .iconSet(DULL)
                .build()
                .setFormula("C28N6H32O4", true);

        NitroniumTetrafluoroborate = new Material.Builder(id++, tjfId("nitronium_tetrafluoroborate"))
                .dust()
                .color(0x686c6e)
                .iconSet(DULL)
                .build()
                .setFormula("NO2BF4", true);

        NitrosoniumTetrafluoroborate = new Material.Builder(id++, tjfId("nitrosonium_tetrafluoroborate"))
                .dust()
                .color(0x7e8d94)
                .iconSet(DULL)
                .build()
                .setFormula("NOBF4", true);

        Tetraacetyldinitrosohexaazaisowurtzitane = new Material.Builder(id++, tjfId("tetraacetyldinitrosohexaazaisowurtzitane"))
                .dust()
                .color((DibenzylTetraacetylhexaazaisowurtzitane.getMaterialRGB() + Hexabenzylhexaazaisowurtzitane.getMaterialRGB()) / 2)
                .iconSet(DULL)
                .build()
                .setFormula("C14N8H18O6", true);

        SilverOxide = new Material.Builder(id++, tjfId("silver_oxide"))
                .dust()
                .color(0x4D4D4D)
                .iconSet(DULL)
                .components(Silver, 2, Oxygen, 1)
                .build();

        Silvertetrafluoroborate = new Material.Builder(id++, tjfId("silvertetrafluoroborate"))
                .liquid()
                .color((SilverOxide.getMaterialRGB() + BoronFluoride.getMaterialRGB()) / 2)
                .iconSet(FLUID)
                .build()
                .setFormula("AgBF4", true);

        TetrafluoroboricAcid = new Material.Builder(id++, tjfId("tetrafluoroboric_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(Silvertetrafluoroborate.getMaterialRGB())
                .iconSet(FLUID)
                .build()
                .setFormula("HBF4", true);

        Benzaldehyde = new Material.Builder(id++, tjfId("benzaldehyde"))
                .liquid()
                .color(0xb26f22)
                .iconSet(FLUID)
                .build()
                .setFormula("C7H6O", true);

        CrudeHexanitroHexaazaisowurtzitane = new Material.Builder(id++, tjfId("crude_hexanitrohexaazaisowurtzitane"))
                .dust()
                .color(HexanitroHexaazaisowurtzitane.getMaterialRGB() * 5 / 7)
                .iconSet(DULL)
                .build()
                .setFormula("C6H6N12O12", true);

        SilicaGel = new Material.Builder(id++, tjfId("silica_gel"))
                .dust()
                .color(0x61daff)
                .iconSet(SHINY)
                .build()
                .setFormula("SiO2", true);

        BrevibacteriumFlavium = new Material.Builder(id++, tjfId("brevibacterium_flavium"))
                .dust()
                .color(0x2c4d24)
                .iconSet(ROUGH)
                .build()
                .setFormula("Bacteria", true);

        Succinimide = new Material.Builder(id++, tjfId("succinimide"))
                .dust()
                .color((SuccinicAcid.getMaterialRGB() + Ammonia.getMaterialRGB()) / 2)
                .iconSet(METALLIC)
                .build()
                .setFormula("C4H4BrNO2", true);

        Acetaldehyde = new Material.Builder(id++, tjfId("acetaldehyde"))
                .liquid()
                .color(0xFF9933)
                .iconSet(FLUID)
                .build()
                .setFormula("C2H4O", true);

        Trichloroferane = new Material.Builder(id++, tjfId("trichloroferane"))
                .liquid()
                .color(0x521973)
                .iconSet(FLUID)
                .build()
                .setFormula("FlCl3", true);

        SeaborgiumDopedNanotubes = new Material.Builder(id++, tjfId("seaborgium_doped_nanotubes"))
                .liquid()
                .color(0x2c2c8c)
                .iconSet(FLUID)
                .build()
                .setFormula("SgCNT", true);

        PrHoYLF = new Material.Builder(id++, tjfId("prho_ylf"))
                .dust(6).liquid()
                .color(0x6f20af)
                .iconSet(SHINY)
                .build()
                .setFormula("PrHoYLF", true);

        LuTmYVO = new Material.Builder(id++, tjfId("lutm_yvo"))
                .dust(6).liquid()
                .color(0x206faf)
                .iconSet(SHINY)
                .build()
                .setFormula("LuTmYVO", true);

        NeodymiumDopedYttrium = new Material.Builder(id++, tjfId("neodymium_doped_yttrium"))
                .dust()
                .color(YttriumOxide.getMaterialRGB())
                .iconSet(DULL)
                .build()
                .setFormula("Nd:Y?", true);

        UnprocessedNdYAGSolution = new Material.Builder(id++, tjfId("unprocessed_ndyag_solution"))
                .liquid()
                .color(0x6f20af)
                .iconSet(FLUID)
                .build()
                .setFormula("Nd:YAG", true);

        UnprocessedNdYAGDust = new Material.Builder(id++, tjfId("unprocessed_ndyag_dust"))
                .dust()
                .color(0x6f20af)
                .iconSet(DULL)
                .build()
                .setFormula("Nd:YAG?", true);

        NdYAGNanoparticles = new Material.Builder(id++, tjfId("nd_yag_nanoparticles"))
                .dust()
                .color(0x6f20af)
                .iconSet(SHINY)
                .build()
                .setFormula("Nd:YAG", true);

        NdYAG = new Material.Builder(id++, tjfId("nd_yag"))
                .dust(6).liquid()
                .color(0xcf8acf)
                .iconSet(SHINY)
                .build()
                .setFormula("Nd:YAG", true);

        Pyrotheum = new Material.Builder(id++, tjfId("pyrotheum"))
                .dust().liquid()
                .color(0xFF9A3C)
                .iconSet(SAND)
                .flags(DISABLE_DECOMPOSITION, EXCLUDE_BLOCK_CRAFTING_RECIPES)
                .components(Redstone, 1, Blaze, 2, Sulfur, 1)
                .build();

        Blizz = new Material.Builder(id++, tjfId("blizz"))
                .dust()
                .color(0x01F3F6)
                .iconSet(SAND)
                .flags(NO_SMELTING, MORTAR_GRINDABLE)
                .components(Redstone, 1, Water, 1)
                .build();

        Cryotheum = new Material.Builder(id++, tjfId("cryotheum"))
                .dust().liquid()
                .color(0x01F3F6)
                .iconSet(DULL)
                .flags(DISABLE_DECOMPOSITION, EXCLUDE_BLOCK_CRAFTING_RECIPES)
                .components(Redstone, 1, Blizz, 2, Water, 1)
                .build();

        DrillingMud = new Material.Builder(id++, tjfId("drilling_mud"))
                .liquid()
                .color(0x996600)
                .iconSet(FLUID)
                .build()
                .setFormula("For the Void Miner", true);

        UsedDrillingMud = new Material.Builder(id++, tjfId("used_drilling_mud"))
                .liquid()
                .color(0x998833)
                .iconSet(FLUID)
                .build()
                .setFormula("Used Mud", true);

        HastelloyX78 = new Material.Builder(id++, tjfId("hastelloyx_78"))
                .ingot()
                .color(0x6ba3e3)
                .iconSet(SHINY)
                .flags(CORE_METAL)
                .components(NaquadahAlloy, 10, Rhenium, 5, Naquadria, 4, Gadolinium, 3, Strontium, 2, Polonium, 3, Rutherfordium, 2, Fermium258, 1)
                .blast(12000)
                .build();

        Incoloy813 = new Material.Builder(id++, tjfId("incoloy_813"))
                .ingot()
                .color(0x37bf7e)
                .iconSet(SHINY)
                .flags(CORE_METAL)
                .components(VanadiumSteel, 4, Osmiridium, 2, Technetium, 3, Germanium, 4, Iridium, 7, Duranium, 5, Californium252, 1)
                .blast(10000)
                .build();

        Staballoy = new Material.Builder(id++, tjfId("staballoy"))
                .ingot()
                .color(0x444B42)
                .iconSet(METALLIC)
                .flags(EXT2_METAL, GENERATE_FRAME, GENERATE_GEAR, DISABLE_DECOMPOSITION)
                .components(Uranium238, 9, Titanium, 1)
                .blast(3450)
                .build();

        OrthoXyleneZeoliteMixture = new Material.Builder(id++, tjfId("ortho_xylene_zeolite"))
                .liquid()
                .color(0xB9785E)
                .iconSet(FLUID)
                .build()
                .setFormula("(NaC4Si27Al9(H2O)28O72)C6H4(CH3)2", true);

        ParaXylene = new Material.Builder(id++, tjfId("para_xylene"))
                .liquid()
                .color(0xB9575E)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H4(CH3)2", true);

        Dibromomethylbenzene = new Material.Builder(id++, tjfId("dibromomethylbenzene"))
                .liquid()
                .color(0x0A1D2C)
                .iconSet(FLUID)
                .build()
                .setFormula("C7H6Br2", true);

        Terephthalaldehyde = new Material.Builder(id++, tjfId("terephthalaldehyde"))
                .dust()
                .color((Dibromomethylbenzene.getMaterialRGB() + SulfuricAcid.getMaterialRGB()) / 2)
                .iconSet(FINE)
                .build()
                .setFormula("C8H6O2", true);

        AuPdCCatalyst = new Material.Builder(id++, tjfId("aupdc_catalyst"))
                .dust()
                .color((Gold.getMaterialRGB() + Palladium.getMaterialRGB() + Carbon.getMaterialRGB()) / 3)
                .iconSet(SHINY)
                .build()
                .setFormula("AuPdC", true);

        Isochloropropane = new Material.Builder(id++, tjfId("isochloropropane"))
                .liquid()
                .color(0xD5DD95)
                .iconSet(FLUID)
                .build()
                .setFormula("CH3CClCH3", true);

        Dinitrodipropanyloxybenzene = new Material.Builder(id++, tjfId("dinitrodipropanyloxybenzene"))
                .liquid()
                .color(0x83945F)
                .iconSet(FLUID)
                .build()
                .setFormula("C12H16O2(NO2)2", true);

        PreZylon = new Material.Builder(id++, tjfId("pre_zylon"))
                .dust()
                .color((Terephthalaldehyde.getMaterialRGB() + Dinitrodipropanyloxybenzene.getMaterialRGB()) / 2)
                .iconSet(FINE)
                .build()
                .setFormula("C20H22N2O2", true);

        Soap = new Material.Builder(id++, tjfId("soap"))
                .liquid()
                .color(0xFFAE42)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        DeglyceratedSoap = new Material.Builder(id++, tjfId("deglyceratedsoap"))
                .liquid()
                .color(0xFFAE41)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        StearicAcid = new Material.Builder(id++, tjfId("stearicacid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0x2bbbb4)
                .iconSet(FLUID)
                .build()
                .setFormula("C18H36O2", true);

        Trioctylphosphine = new Material.Builder(id++, tjfId("trioctylphosphine"))
                .liquid()
                .color(0xF1E130)
                .iconSet(FLUID)
                .build()
                .setFormula("C24H51P", true);

        QuantumDots = new Material.Builder(id++, tjfId("quantumdots"))
                .liquid()
                .color(0xff0000)
                .iconSet(FLUID)
                .build()
                .setFormula(makeFancy("qd"), true);

        HastelloyK243 = new Material.Builder(id++, tjfId("hastelloyk_243"))
                .ingot(2)
                .color(0xa5f564)
                .iconSet(SHINY)
                .flags(EXT2_METAL, GENERATE_FRAME, DISABLE_DECOMPOSITION, GENERATE_SMALL_GEAR, GENERATE_ROUND, GENERATE_RING, GENERATE_ROTOR)
                .components(HastelloyX78, 5, NiobiumNitride, 2, Tritanium, 4, TungstenCarbide, 4, Promethium, 4, Mendelevium261, 1)
                .blast(12100)
                .build();

        Enderium = new Material.Builder(id++, tjfId("enderium"))
                .ingot(3)
                .toolStats(ToolProperty.Builder.of(8.0F, 3.0F, 1280, 3).build())
                .color(0x23524a)
                .iconSet(METALLIC)
                .flags(EXT2_METAL, DISABLE_DECOMPOSITION)
                .components(Lead, 3, Platinum, 1, EnderPearl, 1)
                .blast(4500)
                .build();

        PreciousMetals = new Material.Builder(id++, tjfId("precious_metals"))
                .dust()
                .color((Ruthenium.getMaterialRGB() + Rhodium.getMaterialRGB() + Palladium.getMaterialRGB() + Silver.getMaterialRGB() + Rhenium.getMaterialRGB() + Osmium.getMaterialRGB() + Iridium.getMaterialRGB() + Platinum.getMaterialRGB() + Gold.getMaterialRGB()) / 9)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("RuRhPdAgReOsIrPtAu", true);

        RefractoryMetals = new Material.Builder(id++, tjfId("refractory_metals"))
                .dust()
                .color((Zirconium.getMaterialRGB() + Niobium.getMaterialRGB() + Molybdenum.getMaterialRGB() + Technetium.getMaterialRGB() + Hafnium.getMaterialRGB() + Tantalum.getMaterialRGB() + Tungsten.getMaterialRGB()) / 7)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("ZrNbMoTcHfTaW", true);

        LightTranstionMetals = new Material.Builder(id++, tjfId("light_transition_metals"))
                .dust()
                .color((Titanium.getMaterialRGB() + Vanadium.getMaterialRGB() + Chrome.getMaterialRGB() + Manganese.getMaterialRGB() + Iron.getMaterialRGB() + Cobalt.getMaterialRGB() + Nickel.getMaterialRGB() + Copper.getMaterialRGB()) / 8)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("TiVCrMnFeCoNiCu", true);

        Alkalis = new Material.Builder(id++, tjfId("alkalis"))
                .dust()
                .color((Lithium.getMaterialRGB() + Beryllium.getMaterialRGB() + Sodium.getMaterialRGB() + Magnesium.getMaterialRGB() + Potassium.getMaterialRGB() + Calcium.getMaterialRGB() + Scandium.getMaterialRGB() + Rubidium.getMaterialRGB() + Strontium.getMaterialRGB() + Yttrium.getMaterialRGB() + Caesium.getMaterialRGB() + Barium.getMaterialRGB() + Francium.getMaterialRGB() + Radium.getMaterialRGB()) / 12)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("LiBeNaMgKCaScRbSrYCeBaFrRa", true);

        PostTransitionMetals = new Material.Builder(id++, tjfId("post_transition_metals"))
                .dust()
                .color((Aluminium.getMaterialRGB() + Silicon.getMaterialRGB() + Zinc.getMaterialRGB() + Gallium.getMaterialRGB() + Germanium.getMaterialRGB() + Cadmium.getMaterialRGB() + Indium.getMaterialRGB() + Tin.getMaterialRGB() + Antimony.getMaterialRGB() + Mercury.getMaterialRGB() + Thallium.getMaterialRGB() + Lead.getMaterialRGB() + Bismuth.getMaterialRGB() + Polonium.getMaterialRGB()) / 14)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("AlSiZnGaGeCdInSnSbHgTlPbBiPo", true);

        Lanthanoids = new Material.Builder(id++, tjfId("lanthanoids"))
                .dust()
                .color((Lanthanum.getMaterialRGB() + Cerium.getMaterialRGB() + Praseodymium.getMaterialRGB() + Neodymium.getMaterialRGB() + Promethium.getMaterialRGB() + Samarium.getMaterialRGB() + Europium.getMaterialRGB() + Gadolinium.getMaterialRGB() + Terbium.getMaterialRGB() + Dysprosium.getMaterialRGB() + Holmium.getMaterialRGB() + Erbium.getMaterialRGB() + Thulium.getMaterialRGB() + Ytterbium.getMaterialRGB() + Lutetium.getMaterialRGB()) / 15)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("LaPrNdPmSmEuGdTbDyHoErTmYbLu", true);
        Actinoids = new Material.Builder(id++, tjfId("actinoids"))
                .dust()
                .color((Actinium.getMaterialRGB() + Thorium.getMaterialRGB() + Protactinium.getMaterialRGB() + Uranium.getMaterialRGB() + Neptunium.getMaterialRGB() + Plutonium.getMaterialRGB() + Americium.getMaterialRGB() + Curium.getMaterialRGB() + Berkelium.getMaterialRGB() + Californium.getMaterialRGB() + Einsteinium.getMaterialRGB() + Fermium.getMaterialRGB() + Mendelevium.getMaterialRGB()) / 13)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("AcThPaNpPuAmCmBkCfEsFmMd", true);

        NonMetals = new Material.Builder(id++, tjfId("non_metals"))
                .liquid()
                .color((Hydrogen.getMaterialRGB() + Boron.getMaterialRGB() + Carbon.getMaterialRGB() + Nitrogen.getMaterialRGB() + Oxygen.getMaterialRGB() + Fluorine.getMaterialRGB() + Phosphorus.getMaterialRGB() + Sulfur.getMaterialRGB() + Chlorine.getMaterialRGB() + Arsenic.getMaterialRGB() + Selenium.getMaterialRGB() + Bromine.getMaterialRGB() + Tellurium.getMaterialRGB() + Iodine.getMaterialRGB() + Astatine.getMaterialRGB()))
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("BCPSAsSeTeIAtONHFClBr", true);

        NobleGases = new Material.Builder(id++, tjfId("noble_gases_mixture"))
                .gas()
                .color((Helium.getMaterialRGB() + Neon.getMaterialRGB() + Argon.getMaterialRGB() + Krypton.getMaterialRGB() + Xenon.getMaterialRGB() + Radon.getMaterialRGB()))
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("HeNeArKrXeRn", true);

        Periodicium = new Material.Builder(id++, tjfId("periodicium"))
                .ingot(6).liquid()
                .color(0x3d4bf6)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .components(Hydrogen, 1,
                        Helium, 1,
                        Lithium, 1,
                        Beryllium, 1,
                        Boron, 1,
                        Carbon, 1,
                        Nitrogen, 1,
                        Oxygen, 1,
                        Fluorine, 1,
                        Neon, 1,
                        Sodium, 1,
                        Magnesium, 1,
                        Aluminium, 1,
                        Silicon, 1,
                        Phosphorus, 1,
                        Sulfur, 1,
                        Chlorine, 1,
                        Argon, 1,
                        Potassium, 1,
                        Calcium, 1,
                        Scandium, 1,
                        Titanium, 1,
                        Vanadium, 1,
                        Chrome, 1,
                        Manganese, 1,
                        Iron, 1,
                        Cobalt, 1,
                        Nickel, 1,
                        Copper, 1,
                        Zinc, 1,
                        Gallium, 1,
                        Germanium, 1,
                        Arsenic, 1,
                        Selenium, 1,
                        Bromine, 1,
                        Krypton, 1,
                        Rubidium, 1,
                        Strontium, 1,
                        Yttrium, 1,
                        Zirconium, 1,
                        Niobium, 1,
                        Molybdenum, 1,
                        Technetium, 1,
                        Ruthenium, 1,
                        Rhodium, 1,
                        Palladium, 1,
                        Silver, 1,
                        Cadmium, 1,
                        Indium, 1,
                        Tin, 1,
                        Antimony, 1,
                        Tellurium, 1,
                        Iodine, 1,
                        Xenon, 1,
                        Caesium, 1,
                        Barium, 1,
                        Lanthanum, 1,
                        Cerium, 1,
                        Praseodymium, 1,
                        Neodymium, 1,
                        Promethium, 1,
                        Samarium, 1,
                        Europium, 1,
                        Gadolinium, 1,
                        Terbium, 1,
                        Dysprosium, 1,
                        Holmium, 1,
                        Erbium, 1,
                        Thulium, 1,
                        Ytterbium, 1,
                        Lutetium, 1,
                        Hafnium, 1,
                        Tantalum, 1,
                        Tungsten, 1,
                        Rhenium, 1,
                        Osmium, 1,
                        Iridium, 1,
                        Platinum, 1,
                        Gold, 1,
                        Mercury, 1,
                        Thallium, 1,
                        Lead, 1,
                        Bismuth, 1,
                        Polonium, 1,
                        Astatine, 1,
                        Radon, 1,
                        Francium, 1,
                        Radium, 1,
                        Actinium, 1,
                        Thorium, 1,
                        Protactinium, 1,
                        Uranium, 1,
                        Neptunium, 1,
                        Plutonium, 1,
                        Americium, 1,
                        Curium, 1,
                        Berkelium, 1,
                        Californium, 1,
                        Einsteinium, 1,
                        Fermium, 1,
                        Mendelevium, 1,
                        Nobelium, 1,
                        Lawrencium, 1,
                        Rutherfordium, 1,
                        Dubnium, 1,
                        Seaborgium, 1,
                        Bohrium, 1,
                        Hassium, 1,
                        Meitnerium, 1,
                        Darmstadtium, 1,
                        Roentgenium, 1,
                        Copernicium, 1,
                        Nihonium, 1,
                        Flerovium, 1,
                        Moscovium, 1,
                        Livermorium, 1,
                        Tennessine, 1,
                        Oganesson, 1
                )
                .blast(13500)
                .build();

        HeavyLeptonMix = new Material.Builder(id++, tjfId("heavy_lepton_mix"))
                .liquid()
                .color(0x5adf52)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula(TextFormatting.OBFUSCATED + "a" + TextFormatting.RESET + "(t2)u" + TextFormatting.OBFUSCATED + "a", true);

        Gluons = new Material.Builder(id++, tjfId("gluons"))
                .liquid()
                .color(0xfcfcfa)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula(TextFormatting.OBFUSCATED + "a" + TextFormatting.RESET + "g" + TextFormatting.OBFUSCATED + "a", true);

        DenseNeutronPlasma = new Material.Builder(id++, tjfId("dense_neutron_plasma"))
                .plasma(new FluidBuilder().temperature(1000000))
                .color(0xacecac)
                .flags(DISABLE_REPLICATION, DISABLE_DECONSTRUCTION)
                .build()
                .setFormula(TextFormatting.OBFUSCATED + "a" + TextFormatting.RESET + TextFormatting.GRAY + "n" + TextFormatting.OBFUSCATED + "a", true);

        SuperfluidHelium = new Material.Builder(id++, tjfId("superfluid_helium"))
                .liquid()
                .color(Helium.getMaterialRGB())
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("He", true);

        Cycloparaphenylene = new Material.Builder(id++, tjfId("cycloparaphenylene"))
                .liquid()
                .color(0x333333)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("CPP", true);

        NeutroniumDopedNanotubes = new Material.Builder(id++, tjfId("neutronium_doped_nanotubes"))
                .liquid()
                .color((Neutronium.getMaterialRGB() + CarbonNanotubes.getMaterialRGB()) / 2)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("Nt?", true);

        CosmicMeshPlasma = new Material.Builder(id++, tjfId("cosmic_mesh_plasma"))
                .plasma(new FluidBuilder().temperature(1000000))
                .color(0x1c1c8c)
                .flags(DISABLE_REPLICATION, DISABLE_DECONSTRUCTION)
                .build()
                .setFormula(TextFormatting.OBFUSCATED + "nn", true);

        AcidicSaltWater = new Material.Builder(id++, tjfId("acidic_salt_water"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0x006960)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2SO4(NaCl)3(H2O)3Cl2", true);

        SulfuricBromineSolution = new Material.Builder(id++, tjfId("sulfuric_bromine_solution"))
                .liquid()
                .color(0xff5100)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2SO4Br(H2O)Cl2", true);

        DebrominatedWater = new Material.Builder(id++, tjfId("debrominated_brine"))
                .liquid()
                .color(0x0000ff)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2O", true);

        HotVapourMixture = new Material.Builder(id++, tjfId("hot_vapour_mixture"))
                .gas()
                .color(0xff5100)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2SO4Br(H2O)2Cl2", true);

        Ethylhexanol = new Material.Builder(id++, tjfId("ethylhexanol"))
                .liquid()
                .color(0xfeea9a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C8H18O", true);

        DampBromine = new Material.Builder(id++, tjfId("damp_bromine"))
                .liquid()
                .color(0xe17594)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Br(H2O)", true);

        DiethylhexylPhosphoricAcid = new Material.Builder(id++, tjfId("di_ethylhexyl_phosphoric_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0xffff99)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C16H35O4P", true);

        SodiumHydroxideSolution = new Material.Builder(id++, tjfId("sodium_hydroxide_solution"))
                .liquid()
                .color(SodiumHydroxide.getMaterialRGB() + 50)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)NaOH", true);

        RareEarthHydroxidesSolution = new Material.Builder(id++, tjfId("rare_earth_hydroxides_solution"))
                .liquid()
                .color(0xcfb37d)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("NaOH(H2O)?(OH)3", true);

        RareEarthChloridesSolution = new Material.Builder(id++, tjfId("rare_earth_chlorides_solution"))
                .liquid()
                .color(0x164b45)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(?Cl3)H2O", true);

        ThUSludge = new Material.Builder(id++, tjfId("thorium_uranium_sludge"))
                .dust()
                .color(0x002908)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("ThU", true);

        LaNdOxidesSolution = new Material.Builder(id++, tjfId("la_nd_oxides_solution"))
                .liquid()
                .color(0x9ce3db)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(La2O3)(Pr2O3)(Nd2O3)(Ce2O3)", true);

        SmGdOxidesSolution = new Material.Builder(id++, tjfId("sm_gd_oxides_solution"))
                .liquid()
                .color(0xffff99)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(Sc2O3)(Eu2O3)(Gd2O3)(Sm2O3)", true);

        TbHoOxidesSolution = new Material.Builder(id++, tjfId("tb_ho_oxides_solution"))
                .liquid()
                .color(0x99ff99)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(Y2O3)(Tb2O3)(Dy2O3)(Ho2O3)", true);

        ErLuOxidesSolution = new Material.Builder(id++, tjfId("er_lu_oxides_solution"))
                .liquid()
                .color(0xffb3ff)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(Er2O3)(Tm2O3)(Yb2O3)(Lu2O3)", true);

        LanthanumOxide = new Material.Builder(id++, tjfId("lanthanum_oxide"))
                .dust()
                .color(Lanthanum.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(Lanthanum.getMaterialIconSet())
                .build()
                .setFormula("La2O3", true);

        CeriumOxide = new Material.Builder(id++, tjfId("cerium_oxide"))
                .dust()
                .color(Cerium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(Cerium.getMaterialIconSet())
                .build()
                .setFormula("Ce2O3", true);

        ScandiumOxide = new Material.Builder(id++, tjfId("scandium_oxide"))
                .dust()
                .color(Scandium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(Scandium.getMaterialIconSet())
                .build()
                .setFormula("Sc2O3", true);

        EuropiumOxide = new Material.Builder(id++, tjfId("europium_oxide"))
                .dust()
                .color(Europium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(Europium.getMaterialIconSet())
                .build()
                .setFormula("Eu2O3", true);

        GadoliniumOxide = new Material.Builder(id++, tjfId("gadolinium_oxide"))
                .dust()
                .color(Gadolinium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(Gadolinium.getMaterialIconSet())
                .build()
                .setFormula("Gd2O3", true);

        SamariumOxide = new Material.Builder(id++, tjfId("samarium_oxide"))
                .dust()
                .color(Samarium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(Samarium.getMaterialIconSet())
                .build()
                .setFormula("Sm2O3", true);

        TerbiumOxide = new Material.Builder(id++, tjfId("terbium_oxide"))
                .dust()
                .color(Terbium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(Terbium.getMaterialIconSet())
                .build()
                .setFormula("Tb2O3", true);

        DysprosiumOxide = new Material.Builder(id++, tjfId("dysprosium_oxide"))
                .dust()
                .color(Dysprosium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(Dysprosium.getMaterialIconSet())
                .build()
                .setFormula("Dy2O3", true);

        ErbiumOxide = new Material.Builder(id++, tjfId("erbium_oxide"))
                .dust()
                .color(Erbium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(Erbium.getMaterialIconSet())
                .build()
                .setFormula("Er2O3", true);

        YtterbiumOxide = new Material.Builder(id++, tjfId("ytterbium_oxide"))
                .dust()
                .color(Ytterbium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(Ytterbium.getMaterialIconSet())
                .build()
                .setFormula("Yb2O3", true);

        Dimethylether = new Material.Builder(id++, tjfId("dimethylether"))
                .liquid()
                .color(0xe6cd11)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C2H6O", true);

        EthyleneOxide = new Material.Builder(id++, tjfId("ethylene_oxide"))
                .liquid()
                .color(0xa0c3de)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C2H4O", true);

        Dimethoxyethane = new Material.Builder(id++, tjfId("dimethoxyethane"))
                .liquid()
                .color(0x2acbb4)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C4H10O2", true);

        Cyclooctadiene = new Material.Builder(id++, tjfId("cyclooctadiene"))
                .liquid()
                .color(0x33CC33)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C8H12", true);

        Cyclopentadiene = new Material.Builder(id++, tjfId("cyclopentadiene"))
                .liquid()
                .color(Cyclooctadiene.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C5H6", true);

        ButylLithium = new Material.Builder(id++, tjfId("butyl_lithium"))
                .liquid()
                .color(Butane.getMaterialRGB() + Lithium.getMaterialRGB() / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C4H9Li", true);

        LithiumCyclopentadienide = new Material.Builder(id++, tjfId("lithiumcyclopentadienide"))
                .liquid()
                .color(0x95556a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("LiC5H5", true);

        GoldCyanide = new Material.Builder(id++, tjfId("gold_cyanide"))
                .liquid()
                .color(0x8c8761)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("AuCN", true);

        GoldDepleteMolybdenite = new Material.Builder(id++, tjfId("gold_deplete_molybdenite"))
                .dust()
                .color(0x7c7c8f)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("MoS2?", true);

        MolybdenumConcentrate = new Material.Builder(id++, tjfId("molybdenum_concentrate"))
                .dust()
                .color(0x565666)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("MoS2Re", true);

        ChlorideLeachedSolution = new Material.Builder(id++, tjfId("chloride_leached_solution"))
                .liquid()
                .color(0x41472e)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("CaCl2(CuCl2)(PbCl2)(BiCl3)(FeCl2)", true);

        CopperChloride = new Material.Builder(id++, tjfId("copper_chloride"))
                .dust()
                .color(0xf5b35d)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("CuCl2", true);

        LeadChloride = new Material.Builder(id++, tjfId("lead_chloride"))
                .dust()
                .color(Lead.getMaterialRGB() + Chlorine.getMaterialRGB() / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("PbCl2", true);

        BismuthChloride = new Material.Builder(id++, tjfId("bismuth_chloride"))
                .dust()
                .color(0x95f5d7)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("BiCl3", true);

        Iron2Chloride = new Material.Builder(id++, tjfId("iron_ii_chloride"))
                .liquid()
                .color(Iron3Chloride.getMaterialRGB() - 10)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("FeCl2", true);

        MolybdenumFlue = new Material.Builder(id++, tjfId("molybdenum_flue_gas"))
                .gas()
                .color(0x333338)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2OReS?", true);

        MolybdenumTrioxide = new Material.Builder(id++, tjfId("molybdenum_trioxide"))
                .dust()
                .color(0x666685)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("MoO3", true);

        RheniumSulfuricSolution = new Material.Builder(id++, tjfId("rhenium_sulfuric_solution"))
                .liquid()
                .color(0xbabaff)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("ReS?", true);

        AmmoniumPerrhenate = new Material.Builder(id++, tjfId("ammonium_perrhenate"))
                .liquid()
                .color(0x1c1c45)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("NH4ReO4", true);

        MercuryAcetate = new Material.Builder(id++, tjfId("mercury_acetate"))
                .dust()
                .color(0xcc8562)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("Hg(CH3COO)2", true);

        Methylamine = new Material.Builder(id++, tjfId("methylamine"))
                .liquid()
                .color((Methanol.getMaterialRGB() + Ammonia.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("CH3NH2", true);

        Methylethanolamine = new Material.Builder(id++, tjfId("methylethanolamine"))
                .liquid()
                .color(0x6a3baa)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C3H9NO", true);

        CalciumCarbide = new Material.Builder(id++, tjfId("calcium_carbide"))
                .dust()
                .color(0x807b70)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("CaC2", true);

        CalciumCyanamide = new Material.Builder(id++, tjfId("calcium_cyanamide"))
                .dust()
                .color(CalciumCarbide.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("CaCN2", true);

        Methylguanidine = new Material.Builder(id++, tjfId("methylguanidine"))
                .liquid()
                .color(0x5a9a3c)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C2H7N3", true);

        Methylnitronitrosoguanidine = new Material.Builder(id++, tjfId("methylnitronitrosoguanidine"))
                .liquid()
                .color(0x68b15d)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C2H5N5O3", true);

        CupriavidusNecator = new Material.Builder(id++, tjfId("cupriavidus_necator"))
                .dust()
                .color(0x22704f)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("Bacteria", true);

        SelectivelyMutatedCupriavidiusNecator = new Material.Builder(id++, tjfId("selectively_mutated_cupriavidius_necator"))
                .dust()
                .color(CupriavidusNecator.getMaterialRGB() * 5 / 4)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Bacteria", true);

        IsoamylAlcohol = new Material.Builder(id++, tjfId("isoamyl_alcohol"))
                .liquid()
                .color(0xcaba77)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C5H12O", true);

        Oct1ene = new Material.Builder(id++, tjfId("1_octene"))
                .liquid()
                .color(0x7e8778)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C8H16", true);

        Octanol = new Material.Builder(id++, tjfId("octanol"))
                .liquid()
                .color(0xa2b8c2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C8H18O", true);

        PalladiumLoadedRutileNanoparticles = new Material.Builder(id++, tjfId("palladium_loaded_rutile_nanoparticles"))
                .dust()
                .color((Palladium.getMaterialRGB() + Rutile.getMaterialRGB()))
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("PdTiO2", true);

        Trioctylamine = new Material.Builder(id++, tjfId("trioctylamine"))
                .liquid()
                .color(0x87a2bc)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C24H51N", true);

        Kerosene = new Material.Builder(id++, tjfId("kerosene"))
                .liquid()
                .color(0xD570D5)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        MesitylOxide = new Material.Builder(id++, tjfId("mesityl_oxide"))
                .liquid()
                .color(Acetone.getMaterialRGB() - 10)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H10O", true);

        MethylIsobutylKetone = new Material.Builder(id++, tjfId("methyl_isobutyl_ketone"))
                .liquid()
                .color((MesitylOxide.getMaterialRGB() + WaterAgarMix.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H12O", true);

        PlatinumSalt = new Material.Builder(id++, tjfId("platinum_salt"))
                .dust()
                .color(Platinum.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("Pt?", true);

        RheniumSeparationMixture = new Material.Builder(id++, tjfId("rhenium_separation_mixture"))
                .liquid()
                .color(0xed2c3a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C11H24", true);

        RheniumScrubbedSolution = new Material.Builder(id++, tjfId("rhenium_scrubbed_solution"))
                .liquid()
                .color(0xedccca)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Re?", true);

        LeachedColumbite = new Material.Builder(id++, tjfId("leached_columbite"))
                .dust()
                .color(0xCCCC00)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("(Nb2O5)9Ta2O5?", true);

        PurifiedColumbite = new Material.Builder(id++, tjfId("purified_columbite"))
                .dust()
                .color(LeachedColumbite.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Ta2O5Nb18O45", true);

        LeachedPyrochlore = new Material.Builder(id++, tjfId("leached_pyrochlore"))
                .dust()
                .color(0x996633)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("(Nb2O5)9Ta2O5?", true);

        PurifiedPyrochlore = new Material.Builder(id++, tjfId("purified_pyrochlore"))
                .dust()
                .color(LeachedPyrochlore.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Ta2O5Nb18O45", true);

        Glucose = new Material.Builder(id++, tjfId("glucose"))
                .dust()
                .color((Sugar.getMaterialRGB() + 5))
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("C6H12O6", true);

        FluoroniobicAcid = new Material.Builder(id++, tjfId("fluroniobic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0x73ff00)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("NbHF7", true);

        PlatinumSaltRefined = new Material.Builder(id++, tjfId("refined_platinum_salt"))
                .dust()
                .color(Platinum.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(METALLIC)
                .build()
                .setFormula("PtCl?", true);

        DirtyHexafluorosilicicAcid = new Material.Builder(id++, tjfId("dirty_hexafluorosilicic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color((Stone.getMaterialRGB() + FluorosilicicAcid.getMaterialRGB() / 2))
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2SiF6?", true);

        StoneResidueDust = new Material.Builder(id++, tjfId("stone_residue_dust"))
                .dust()
                .color(Stone.getMaterialRGB() / 5 * 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build();

        DiluteHexafluorosilicicAcid = new Material.Builder(id++, tjfId("dilute_hexafluorosilicic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color((Water.getMaterialRGB() * 2 + FluorosilicicAcid.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)2(H2SiF6)", true);

        Triniite = new Material.Builder(id++, tjfId("triniite"))
                .dust(7)
                .color(0x5F5A76)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(SHINY)
                .components(Trinium, 3, Actinium, 3, Selenium, 4, Astatine, 4)
                .build();

        UncommonResidues = new Material.Builder(id++, tjfId("uncommon_residues"))
                .dust()
                .color((Triniite.getMaterialRGB() + NaquadriaticTaranium.getMaterialRGB() + PreciousMetals.getMaterialRGB()) / 5)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("?", true);

        RedMud = new Material.Builder(id++, tjfId("red_mud"))
                .liquid()
                .color(0xcc3300)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("HCl?", true);

        Dioxygendifluoride = new Material.Builder(id++, tjfId("dioxygen_difluoride"))
                .liquid()
                .color(0x32bdaf)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("FOOF", true);

        PartiallyOxidizedResidues = new Material.Builder(id++, tjfId("partially_oxidized_residues"))
                .dust()
                .color(StoneResidueDust.getMaterialRGB() + Dioxygendifluoride.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build();

        OxidizedResidualSolution = new Material.Builder(id++, tjfId("oxidized_residual_solution"))
                .liquid()
                .color(0x23ad7f)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        InertResidues = new Material.Builder(id++, tjfId("inert_residues"))
                .dust()
                .color(0x61587a)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build();

        CleanInertResidues = new Material.Builder(id++, tjfId("clean_inert_residues"))
                .dust()
                .color((Taranium.getMaterialRGB() + Xenon.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build();

        NaquadricCompound = new Material.Builder(id++, tjfId("naquadric_compound"))
                .dust()
                .color(Naquadah.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(Naquadah.getMaterialIconSet())
                .components(Naquadah.getMaterialComponents())
                .build();

        EnrichedNaquadricCompound = new Material.Builder(id++, tjfId("enriched_naquadric_compound"))
                .dust()
                .color(NaquadahEnriched.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(NaquadahEnriched.getMaterialIconSet())
                .components(NaquadahEnriched.getMaterialComponents())
                .build();

        NaquadriaticCompound = new Material.Builder(id++, tjfId("naquadriatic_compound"))
                .dust()
                .color(Naquadria.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(Naquadria.getMaterialIconSet())
                .components(Naquadria.getMaterialComponents())
                .build();

        DiamagneticResidues = new Material.Builder(id++, tjfId("diamagnetic_residues"))
                .dust()
                .color((Calcium.getMaterialRGB() + Zinc.getMaterialRGB() + Copper.getMaterialRGB() + Gallium.getMaterialRGB() + Beryllium.getMaterialRGB() + Tin.getMaterialRGB()) / 15)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build();

        ParamagneticResidues = new Material.Builder(id++, tjfId("paramagnetic_residues"))
                .dust()
                .color((Sodium.getMaterialRGB() + Potassium.getMaterialRGB() + Magnesium.getMaterialRGB() + Titanium.getMaterialRGB() + Vanadium.getMaterialRGB() + Manganese.getMaterialRGB()) / 15)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build();

        FerromagneticResidues = new Material.Builder(id++, tjfId("ferromagnetic_residues"))
                .dust()
                .color((Iron.getMaterialRGB() + Nickel.getMaterialRGB() + Cobalt.getMaterialRGB()) / 7)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build();

        OxidizedResidues = new Material.Builder(id++, tjfId("oxidized_residues"))
                .dust()
                .color((DiamagneticResidues.getMaterialRGB() + ParamagneticResidues.getMaterialRGB() + FerromagneticResidues.getMaterialRGB() + 0x9f0000) / 4)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build();

        HeavyOxidizedResidues = new Material.Builder(id++, tjfId("heavy_oxidized_residues"))
                .dust()
                .color(OxidizedResidues.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build();

        MetallicResidues = new Material.Builder(id++, tjfId("metallic_residues"))
                .dust()
                .color((DiamagneticResidues.getMaterialRGB() + ParamagneticResidues.getMaterialRGB() + FerromagneticResidues.getMaterialRGB() + UncommonResidues.getMaterialRGB() / 3) / 4)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build();

        HeavyDiamagneticResidues = new Material.Builder(id++, tjfId("heavy_diamagnetic_residues"))
                .dust()
                .color((Lead.getMaterialRGB() + Mercury.getMaterialRGB() + Cadmium.getMaterialRGB() + Indium.getMaterialRGB() + Gold.getMaterialRGB() + Bismuth.getMaterialRGB()))
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build();

        HeavyParamagneticResidues = new Material.Builder(id++, tjfId("heavy_paramagnetic_residues"))
                .dust()
                .color((Thorium.getMaterialRGB() + Thallium.getMaterialRGB() + Uranium.getMaterialRGB() + Tungsten.getMaterialRGB() + Hafnium.getMaterialRGB() + Tantalum.getMaterialRGB()) / 15)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build();

        HeavyFerromagneticResidues = new Material.Builder(id++, tjfId("heavy_ferromagnetic_residues"))
                .dust()
                .color(DysprosiumOxide.getMaterialRGB() * 3 / 11)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build();

        HeavyMetallicResidues = new Material.Builder(id++, tjfId("heavy_metallic_residues"))
                .dust()
                .color((HeavyDiamagneticResidues.getMaterialRGB() + HeavyParamagneticResidues.getMaterialRGB() + HeavyFerromagneticResidues.getMaterialRGB() + UncommonResidues.getMaterialRGB() / 3) / 4)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build();

        NitratedTriniiteSolution = new Material.Builder(id++, tjfId("nitrated_triniite_solution"))
                .liquid()
                .color(0x428c9f)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        ExoticHeavyResidues = new Material.Builder(id++, tjfId("exotic_heavy_residues"))
                .dust()
                .color(NitratedTriniiteSolution.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build();

        DiluteHydrofluoricAcid = new Material.Builder(id++, tjfId("dilute_hydrofluoric_acid"))
                .liquid()
                .color((Water.getMaterialRGB() + HydrofluoricAcid.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)(HF)", true);

        TritiumHydride = new Material.Builder(id++, tjfId("tritium_hydride"))
                .liquid()
                .color(Tritium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("TH", true);

        Helium3Hydride = new Material.Builder(id++, tjfId("helium_iii_hydride"))
                .liquid()
                .color(Helium3.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("He_3H", true);

        UltraacidicResidueSolution = new Material.Builder(id++, tjfId("ultraacidic_residue_solution"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color((FluoroantimonicAcid.getMaterialRGB() + Helium3Hydride.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        XenicAcid = new Material.Builder(id++, tjfId("xenic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0x5a4c9c)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2XeO4", true);

        DustyLiquidHelium3 = new Material.Builder(id++, tjfId("dusty_liquid_helium_3"))
                .liquid()
                .color(2 * Helium3.getMaterialRGB() / 3 + Taranium.getMaterialRGB() / 5)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("He_3", true);

        Ozone = new Material.Builder(id++, tjfId("ozone"))
                .gas()
                .color(0x0099FF)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("O3", true);

        TaraniumEnrichedLHelium3 = new Material.Builder(id++, tjfId("taranium_enriched_liquid_helium_3"))
                .liquid()
                .color(Helium3.getMaterialRGB() / 2 + Taranium.getMaterialRGB() / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        TaraniumSemidepletedLHelium3 = new Material.Builder(id++, tjfId("taranium_semidepleted_liquid_helium_3"))
                .liquid()
                .color(2 * Helium3.getMaterialRGB() / 3 + Taranium.getMaterialRGB() / 5)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        TaraniumSemidepletedLHelium3 = new Material.Builder(id++, tjfId("taranium_semidepleted_liquid_helium_3"))
                .liquid()
                .color(2 * Helium3.getMaterialRGB() / 3 + Taranium.getMaterialRGB() / 5)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        TaraniumDepletedLHelium3 = new Material.Builder(id++, tjfId("taranium_depleted_liquid_helium_3"))
                .liquid()
                .color(Helium3.getMaterialRGB() * 5 / 6 + Taranium.getMaterialRGB() / 8)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        TaraniumRichDustyHeliumPlasma = new Material.Builder(id++, tjfId("taranium_rich_dusty_helium_plasma"))
                .plasma(new FluidBuilder().temperature(10000))
                .color(Helium.getMaterialRGB() / 2 + Taranium.getMaterialRGB() / 2)
                .flags(DISABLE_REPLICATION, DISABLE_DECONSTRUCTION)
                .iconSet(FLUID)
                .build();

        TaraniumRichHelium4 = new Material.Builder(id++, tjfId("taranium_rich_helium_4"))
                .liquid()
                .plasma()
                .color(Helium.getMaterialRGB() / 2 + Taranium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        TaraniumDepletedHeliumPlasma = new Material.Builder(id++, tjfId("taranium_depleted_helium_plasma"))
                .plasma(new FluidBuilder().temperature(10000))
                .color(Helium.getMaterialRGB() / 2 + Taranium.getMaterialRGB() / 2)
                .flags(DISABLE_REPLICATION, DISABLE_DECONSTRUCTION)
                .iconSet(FLUID)
                .build();

        TaraniumPoorLiquidHelium = new Material.Builder(id++, tjfId("taranium_poor_liquid_helium"))
                .liquid()
                .color(Helium3.getMaterialRGB() * 6 / 7 + Taranium.getMaterialRGB() / 14)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        TaraniumPoorLiquidHeliumMix = new Material.Builder(id++, tjfId("taranium_poor_liquid_helium_mix"))
                .liquid()
                .color(TaraniumPoorLiquidHelium.getMaterialRGB() * 10 / 11 + Helium.getMaterialRGB() / 11)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        BariumOxide = new Material.Builder(id++, tjfId("barium_oxide"))
                .dust()
                .color((Barium.getMaterialRGB() + Oxygen.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("BaO", true);

        BariumStrontiumAcetateSolution = new Material.Builder(id++, tjfId("basr_acetate_solution"))
                .liquid()
                .color(0x9a9b98)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C2H3BaO2Sr", true);

        IsopropylAlcohol = new Material.Builder(id++, tjfId("isopropyl_alcohol"))
                .liquid()
                .color((Water.getMaterialRGB() + Propene.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C3H8O", true);

        TitaniumIsopropoxide = new Material.Builder(id++, tjfId("titanium_isopropoxide"))
                .liquid()
                .color(0xFF0066)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Ti(OCH(CH3)2)4", true);

        BariumStrontiumTitanatePreparation = new Material.Builder(id++, tjfId("basr_titanate_preparation"))
                .liquid()
                .color(0xFF0066)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(BaTiO3)C2H3BaO2Sr", true);

        BariumStrontiumTitanate = new Material.Builder(id++, tjfId("barium_strontium_titanate"))
                .dust()
                .color(0xFF0066)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("BaO4SrTi", true);

        IsopropylAcetate = new Material.Builder(id++, tjfId("isopropyl_acetate"))
                .liquid()
                .color((Strontium.getMaterialRGB() + IsopropylAlcohol.getMaterialRGB() + Water.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(CH3)2CHCOOCH3", true);

        PotasssiumFluoroTantalate = new Material.Builder(id++, tjfId("potassium_fluorotantalate"))
                .dust()
                .color(0x73ff00)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("K2TaF7", true);

        LeadNitrate = new Material.Builder(id++, tjfId("lead_nitrate"))
                .dust()
                .color(0xFEFEFE)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .components(Lead, 1, Nitrogen, 2, Oxygen, 6)
                .build();

        LeadNitrateCalciumMixture = new Material.Builder(id++, tjfId("lead_nitrate_calcium_mixture"))
                .dust()
                .color((LeadNitrate.getMaterialRGB() + Calcium.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("(Pb(NO3)2)Ca9", true);

        LeadScandiumTantalate = new Material.Builder(id++, tjfId("lead_scandium_tantalate"))
                .dust()
                .color((Lead.getMaterialRGB() + Scandium.getMaterialRGB() + Tantalum.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Pb(ScTa)O3", true);

        MagnetorestrictiveAlloy = new Material.Builder(id++, tjfId("magnetorestrictive_alloy"))
                .dust()
                .color(0xafefef)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("Tb4Dy7Fe10Co5B2SiC", true);

        LeadSenenide = new Material.Builder(id++, tjfId("lead_selenide"))
                .dust()
                .color((Lead.getMaterialRGB() + Selenium.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("PbSe", true);

        ZincSelenide = new Material.Builder(id++, tjfId("zinc_selenide"))
                .dust()
                .color(0xfcfc00)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("ZnSe", true);

        FranciumCaesiumCadmiumBromide = new Material.Builder(id++, tjfId("francium_caesium_cadmium_bromide"))
                .dust()
                .color((Francium.getMaterialRGB() + Caesium.getMaterialRGB() + Cadmium.getMaterialRGB() + Bromine.getMaterialRGB()) / 4)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("FrCsCf2Br6", true);

        SodiumIodide = new Material.Builder(id++, tjfId("sodium_iodide"))
                .dust()
                .color(0x555588)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("NaI", true);

        Iodobenzene = new Material.Builder(id++, tjfId("iodobenzene"))
                .liquid()
                .color(0x2c2c6c0)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H5I", true);

        PalladiumAcetate = new Material.Builder(id++, tjfId("palladium_acetate"))
                .dust()
                .color(0xcc3300)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("C4H6O4Pd", true);

        Stilbene = new Material.Builder(id++, tjfId("stilbene"))
                .dust()
                .color(0x3c9c3c)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("C14H12", true);

        Aminophenol = new Material.Builder(id++, tjfId("aminophenol"))
                .liquid()
                .color(0xafca3a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H4(OH)(NH2)", true);

        Amino3phenol = new Material.Builder(id++, tjfId("3_aminophenol"))
                .liquid()
                .color(Aminophenol.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H7NO", true);

        Ethylamine = new Material.Builder(id++, tjfId("ethylamine"))
                .liquid()
                .color(Ethylenediamine.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C2H5NH2", true);

        SodiumAzanide = new Material.Builder(id++, tjfId("sodium_azanide"))
                .dust()
                .color((Sodium.getMaterialRGB() + Hydrogen.getMaterialRGB() + Nitrogen.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("NaNH2", true);

        PhthalicAnhydride = new Material.Builder(id++, tjfId("phthalicanhydride"))
                .dust()
                .color(0xD1D1D1)
                .flags(DISABLE_REPLICATION)
                .iconSet(SAND)
                .components(Carbon, 8, Hydrogen, 4, Oxygen, 3)
                .build();

        TetraethylammoniumNonahydridides = new Material.Builder(id++, tjfId("tetraethylammonium_nonahydrides"))
                .dust()
                .color(0xbee8b9)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("(C8H20N)(ReH9)(TcH9)", true);

        RhodamineB = new Material.Builder(id++, tjfId("rhodamine_b"))
                .dust()
                .color(0xfc2020)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("C28H31ClN2O3", true);

        Hydroxylamine = new Material.Builder(id++, tjfId("hydroxylamine"))
                .liquid()
                .color(0x99cc99)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H3NO", true);

        SodiumPertechnetate = new Material.Builder(id++, tjfId("sodium_pertechnetate"))
                .dust()
                .color(0x6162c4)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("NaTcO4", true);

        PotassiumPerrhenate = new Material.Builder(id++, tjfId("potassium_perrhenate"))
                .dust()
                .color(0xdec451)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("KReO4", true);

        PotassiumPertechnate = new Material.Builder(id++, tjfId("potassium_pertechnate"))
                .dust()
                .color(0xdec451)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("KTcO4", true);

        PotassiumNonahydridorhenate = new Material.Builder(id++, tjfId("potassium_nonahydridorhenate"))
                .dust()
                .color(0xeae2a8)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("H9K2ReO4", true);

        PotassiumEthoxide = new Material.Builder(id++, tjfId("potassium_ethoxide"))
                .liquid()
                .color(Ethanol.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C2H5KO", true);

        PotassiumNonahydridotechnetate = new Material.Builder(id++, tjfId("potassium_nonahydridotechnetate"))
                .dust()
                .color(0xede2a4)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("H9K2TcO4", true);

        TetraethylammoniumBromide = new Material.Builder(id++, tjfId("tetraethylammonium_bromide"))
                .liquid()
                .color(0xcc33ff)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C8H20NBr", true);

        PotassiumIodide = new Material.Builder(id++, tjfId("potassium_iodide"))
                .dust()
                .color((Potassium.getMaterialRGB() + Iodine.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("KI", true);

        Butylaniline = new Material.Builder(id++, tjfId("butylaniline"))
                .liquid()
                .color(Aniline.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C10H15N", true);

        LithiumIodide = new Material.Builder(id++, tjfId("lithium_iodide"))
                .dust()
                .color((Lithium.getMaterialRGB() + Iodine.getMaterialRGB()))
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("LiI", true);

        Trimethylchlorosilane = new Material.Builder(id++, tjfId("trimethylchlorosilane"))
                .liquid()
                .color(Dimethyldichlorosilane.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(CH3)3SiCl", true);

        Trimethylsilane = new Material.Builder(id++, tjfId("trimethylsilane"))
                .liquid()
                .color(Trimethylchlorosilane.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C3H10Si", true);

        PotassiumBromide = new Material.Builder(id++, tjfId("potassium_bromide"))
                .dust()
                .color(0xe066a3)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("KBr", true);

        PotassiumBromate = new Material.Builder(id++, tjfId("potassium_bromate"))
                .dust()
                .color(0x8a4cd1)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("KBrO3", true);

        IodobenzoicAcid = new Material.Builder(id++, tjfId("iodobenzoic_acid"))
                .liquid()
                .color(0x2cac6c0)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C7H5IO2", true);

        IBX = new Material.Builder(id++, tjfId("ibx"))
                .dust()
                .color(0x20208c)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("C7H5IO4", true);

        Methoxybenzaldehyde = new Material.Builder(id++, tjfId("methoxybenzaldehyde"))
                .liquid()
                .color(0x3c3a7a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C8H8O2", true);

        MBBA = new Material.Builder(id++, tjfId("mbba"))
                .liquid()
                .color(0xfa30fa)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C18H21NO", true);

        LiquidCrystalDetector = new Material.Builder(id++, tjfId("liquid_crystal_detector"))
                .liquid()
                .color(0xda20da)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        IodineMonochloride = new Material.Builder(id++, tjfId("iodine_monochloride"))
                .liquid()
                .color(0x004c4c)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("ICl", true);

        RhReNqCatalyst = new Material.Builder(id++, tjfId("rhrenq_catalyst"))
                .dust()
                .color((Rhenium.getMaterialRGB() + Rhodium.getMaterialRGB() + Naquadah.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("ReRhNq", true);

        AcetylatingReagent = new Material.Builder(id++, tjfId("acetylating_reagent"))
                .liquid()
                .color(0x8d5e63)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C9H12Si(MgBr)2", true);

        Dimethylnaphthalene = new Material.Builder(id++, tjfId("dimethylnaphthalene"))
                .liquid()
                .color(0xe34fb0)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C12H12", true);

        Bromosuccinimide = new Material.Builder(id++, tjfId("bromo_succinimide"))
                .dust()
                .color((Succinimide.getMaterialRGB() + Bromine.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(METALLIC)
                .build()
                .setFormula("C4H4BrNO2", true);

        Dihydroiodotetracene = new Material.Builder(id++, tjfId("dihydroiodotetracene"))
                .liquid()
                .color(0x5c4d38)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2C18H11I", true);

        Dichlorodicyanobenzoquinone = new Material.Builder(id++, tjfId("dichlorodicyanobenzoquinone"))
                .liquid()
                .color(0x3a2aba)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C8Cl2N2O2", true);

        VanadiumOxide = new Material.Builder(id++, tjfId("vanadium_oxide"))
                .dust()
                .color(0xf2ef1b)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("V2O5", true);

        LithiumChlorideSolution = new Material.Builder(id++, tjfId("lithium_chloride_solution"))
                .liquid()
                .color((Lithium.getMaterialRGB() + Chlorine.getMaterialRGB()))
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("LiCl(H2O)", true);

        Dichlorodicyanohydroquinone = new Material.Builder(id++, tjfId("dichlorodicyanohidroquinone"))
                .liquid()
                .color(0x3a2aba)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C8Cl2N2(OH)2", true);

        Tetracene = new Material.Builder(id++, tjfId("tetracene"))
                .dust()
                .color(0x99801a)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("C18H12", true);

        StreptococcusPyogenes = new Material.Builder(id++, tjfId("streptococcus_pyogenes"))
                .dust()
                .color(0x1c3b15)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("Bacteria", true);

        BifidobacteriumBreve = new Material.Builder(id++, tjfId("bifidobacterium_breve"))
                .dust()
                .color(0x377528)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("Bacteria", true);

        EschericiaColi = new Material.Builder(id++, tjfId("eschericia_coli"))
                .dust()
                .color(0x2d4228)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("Bacteria", true);

        SodiumHypochlorite = new Material.Builder(id++, tjfId("sodium_hypochlorite"))
                .dust()
                .color(0x6cff50)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("NaClO", true);

        HotNitrogen = new Material.Builder(id++, tjfId("hot_nitrogen"))
                .gas()
                .color(Nitrogen.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("N", true);

        Lignite = new Material.Builder(id++, tjfId("lignite"))
                .gem(0, 1600)
                .color(6571590)
                .flags(DISABLE_REPLICATION, FLAMMABLE, NO_SMELTING, NO_SMASHING, MORTAR_GRINDABLE)
                .iconSet(LIGNITE)
                .components(Carbon, 2, Water, 4, DarkAsh, 1)
                .build()
                .setFormula("C2(H2O)4C", true);

        DehydratedLignite = new Material.Builder(id++, tjfId("dehydrated_lignite"))
                .dust()
                .color(0x5c4020)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("C2(H2O)4C?", true);

        BCEPellet = new Material.Builder(id++, tjfId("bce_pellet"))
                .dust(1, 1600)
                .color(0x3c3020)
                .flags(DISABLE_REPLICATION)
                .iconSet(LIGNITE)
                .build()
                .setFormula("C2(H2O)4C", true);

        GlucoseIronSolution = new Material.Builder(id++, tjfId("glucose_iron_solution"))
                .liquid()
                .color((Sugar.getMaterialRGB() + Iron.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(C6H12O6)FeCl3", true);

        GrapheneOxidationSolution = new Material.Builder(id++, tjfId("graphene_oxidation_solution"))
                .liquid()
                .color(0x96821a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(KMnO4)(NaNO3)(H2SO4)", true);

        GraphiteOxide = new Material.Builder(id++, tjfId("graphite_oxide"))
                .dust()
                .color(Graphite.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("C(O2)", true);

        GrapheneOxidationResidue = new Material.Builder(id++, tjfId("graphene_oxidation_residue"))
                .dust()
                .color(0x96821a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("(KMnO4)(NaNO3)(H2SO4)", true);

        GrapheneOxide = new Material.Builder(id++, tjfId("graphene_oxide"))
                .dust()
                .color(Graphene.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("C(O2)", true);

        Hydrazine = new Material.Builder(id++, tjfId("hydrazine"))
                .liquid()
                .color(0xFFFFFF)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .components(Nitrogen, 2, Hydrogen, 4)
                .build();

        Snow = new Material.Builder(id++, tjfId("snow"))
                .dust()
                .color(0xFFFFFF)
                .flags(DISABLE_REPLICATION, NO_SMELTING)
                .iconSet(OPAL)
                .components(Hydrogen, 2, Oxygen, 1)
                .build();

        SupercooledCryotheum = new Material.Builder(id++, tjfId("supercooled_cryotheum"))
                .liquid()
                .color(Cryotheum.getMaterialRGB() - 10)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .components(Cryotheum, 1)
                .build();

        RedOil = new Material.Builder(id++, tjfId("red_oil"))
                .liquid()
                .color(0x7C1500)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2N4(RP-1)NiZnFe4", true);

        RP1 = new Material.Builder(id++, tjfId("rp"))
                .liquid()
                .color(0xFF6E5D)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        TributylPhosphate = new Material.Builder(id++, tjfId("tributyl_phosphate"))
                .liquid()
                .color(0x7C5B2C)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(C4H9)3PO4", true);

        PhosphorusTrichloride = new Material.Builder(id++, tjfId("phosphorus_trichloride"))
                .liquid()
                .color((Phosphorus.getMaterialRGB() + Chlorine.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("PCl3", true);

        PhosphorylChloride = new Material.Builder(id++, tjfId("phosphoryl_chloride"))
                .liquid()
                .color(0xE6E6E6)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("POCl3", true);

        SodiumFormate = new Material.Builder(id++, tjfId("sodium_formate"))
                .liquid()
                .color(0xFFAAAA)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .components(Hydrogen, 1, Carbon, 1, Oxygen, 1, Oxygen, 1, Sodium, 1)
                .build();

        FormicAcid = new Material.Builder(id++, tjfId("formic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0xFFAA77)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .components(Carbon, 1, Hydrogen, 2, Oxygen, 2)
                .build();

        OrganicFertilizer = new Material.Builder(id++, tjfId("organic_fertilizer"))
                .dust()
                .color(0xDDDDDD)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(SHINY)
                .components(Calcium, 5, Phosphate, 3, Hydrogen, 1, Oxygen, 1)
                .build();

        ZirconiumTetrachloride = new Material.Builder(id++, tjfId("zirconium_tetrachloride"))
                .dust()
                .color(0xF0F0F0)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("ZrCl4", true);

        CarbonFluoride = new Material.Builder(id++, tjfId("carbone_fluoride"))
                .liquid()
                .color(0xE6E6E6)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("CF4", true);

        CalciumCarbonateSolution = new Material.Builder(id++, tjfId("calcium_carbonate_solution"))
                .liquid()
                .color(Calcite.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)CaCO3", true);

        BentoniteClaySlurry = new Material.Builder(id++, tjfId("bentonite_clay_solution"))
                .liquid()
                .color(0xdbc9c5)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2O?", true);

        AluminiumChloride = new Material.Builder(id++, tjfId("aluminium_chloride"))
                .dust()
                .color((Aluminium.getMaterialRGB() + Chlorine.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("AlCl3", true);

        EthylAnthraQuinone = new Material.Builder(id++, tjfId("ethylanthraquinone"))
                .liquid()
                .color(0xFFFF00)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .components(Carbon, 16, Hydrogen, 12, Oxygen, 2)
                .build();

        EthylAnthraHydroQuinone = new Material.Builder(id++, tjfId("ethylanthrahydroquinone"))
                .liquid()
                .color(0xFFFF47)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .components(EthylAnthraQuinone, 1, Hydrogen, 2)
                .build();

        Anthracene = new Material.Builder(id++, tjfId("anthracene"))
                .liquid()
                .color(0xA2ACA2)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .components(Carbon, 14, Hydrogen, 10)
                .build();

        Chloroethanol = new Material.Builder(id++, tjfId("chloroethanol"))
                .liquid()
                .color(0xcfb050)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C2H5ClO", true);

        Trimethylamine = new Material.Builder(id++, tjfId("trimetylamine"))
                .liquid()
                .color((Dimethylamine.getMaterialRGB() + 20))
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(CH3)3N", true);

        Choline = new Material.Builder(id++, tjfId("choline"))
                .liquid()
                .color(0x63e45f)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C5H14NO", true);

        ATL = new Material.Builder(id++, tjfId("atl"))
                .liquid()
                .color(0x709c4a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("ATL", true);

        EthyleneGlycol = new Material.Builder(id++, tjfId("ethylene_glycol"))
                .liquid()
                .color(0x8080fa)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C2H6O2", true);

        CaCBaSMixture = new Material.Builder(id++, tjfId("cacbas_mixture"))
                .liquid()
                .color((CalciumCarbonateSolution.getMaterialRGB() + BariumSulfateSolution.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        LubricantClaySlurry = new Material.Builder(id++, tjfId("lubricant_clay_slurry"))
                .liquid()
                .color((Lubricant.getMaterialRGB() + BentoniteClaySlurry.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        ATLEthylene = new Material.Builder(id++, tjfId("atl_ethylene_mixture"))
                .liquid()
                .color((ATL.getMaterialRGB() + EthyleneGlycol.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        DrillingMudMixture = new Material.Builder(id++, tjfId("drilling_mud_mixture"))
                .liquid()
                .color((CaCBaSMixture.getMaterialRGB() + LubricantClaySlurry.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        WetEthyleneOxide = new Material.Builder(id++, tjfId("wet_etylene_oxide"))
                .liquid()
                .color(0x90b3ff)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)C2H4O", true);

        Phosgene = new Material.Builder(id++, tjfId("phosgene"))
                .liquid()
                .color((Chlorine.getMaterialRGB() + CarbonMonoxide.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("COCl2", true);

        TolueneDiisocyanate = new Material.Builder(id++, tjfId("toluene_diisocyanate"))
                .liquid()
                .color(0xbaf6ca)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C9H6N2O2", true);

        Polyurethane = new Material.Builder(id++, tjfId("polyurethane"))
                .ingot(2).liquid()
                .color(0xeffcef)
                .iconSet(DULL)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION, GENERATE_ROD, NO_SMASHING)
                .components(Carbon, 17, Hydrogen, 16, Nitrogen, 2, Oxygen, 4)
                .build();

        ViscoelasticPolyurethane = new Material.Builder(id++, tjfId("viscoelastic_polyurethane"))
                .liquid()
                .color(0xeffcef)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C17H16N2O4?", true);

        ViscoelasticPolyurethaneFoam = new Material.Builder(id++, tjfId("viscoelastic_polyurethane_foam"))
                .liquid()
                .color(0xeffcef)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C17H16N2O4?", true);

        RocketFuelH8N4C2O4 = new Material.Builder(id++, tjfId("rocket_fuel_a"))
                .liquid()
                .color(0x5ECB22)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .components(Hydrogen, 8, Nitrogen, 4, Carbon, 2, Oxygen, 4)
                .build();

        MonoMethylHydrazine = new Material.Builder(id++, tjfId("monomethylhydrazine"))
                .liquid()
                .color(0xFFFFFF)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .components(Carbon, 1, Hydrogen, 6, Nitrogen, 2)
                .build();

        RocketFuelCN3H7O3 = new Material.Builder(id++, tjfId("rocket_fuel_b"))
                .liquid()
                .color(0xBE46C5)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .components(Carbon, 1, Nitrogen, 3, Hydrogen, 7, Oxygen, 3)
                .build();

        DenseHydrazineFuelMixture = new Material.Builder(id++, tjfId("dense_hydrazine_fuel_mixture"))
                .liquid()
                .color(0x5E2B4A)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .components(Hydrazine, 1, Methanol, 1)
                .build();

        RP1RocketFuel = new Material.Builder(id++, tjfId("rocket_fuel_c"))
                .liquid()
                .color(0xFF503C)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .build()
                .setFormula("?O", true);

        CoalTarOil = new Material.Builder(id++, tjfId("coal_tar_oil"))
                .liquid()
                .color(0xB5B553)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .components(CoalTar, 1)
                .build();

        SulfuricCoalTarOil = new Material.Builder(id++, tjfId("sulfuric_coal_tar_oil"))
                .liquid()
                .color(0xFFFFAD)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .components(CoalTarOil, 1, SulfuricAcid, 1)
                .build();

        AluminiumHydroxide = new Material.Builder(id++, tjfId("aluminium_hydroxide"))
                .dust()
                .color(Aluminium.getMaterialRGB() - 25)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("Al(OH)3", true);

        SodiumHexafluoroaluminate = new Material.Builder(id++, tjfId("sodium_hexafluoroaluminate"))
                .liquid()
                .color((Sodium.getMaterialRGB() + Aluminium.getMaterialRGB() + Fluorine.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Na3AlF6", true);

        SodiumHydroxideBauxite = new Material.Builder(id++, tjfId("sodium_hydroxide_bauxite"))
                .liquid()
                .color(0xbf731a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Al2H2O4", true);

        ImpureAluminiumHydroxideSolution = new Material.Builder(id++, tjfId("impure_aloh_3_soution"))
                .liquid()
                .color(0xd8653e)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)Al(OH)3?", true);

        PureAluminiumHydroxideSolution = new Material.Builder(id++, tjfId("pure_aloh_3_soution"))
                .liquid()
                .color((Aluminium.getMaterialRGB() + Oxygen.getMaterialRGB() + Hydrogen.getMaterialRGB() + 40) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)Al2(OH)6", true);

        NeutralisedRedMud = new Material.Builder(id++, tjfId("neutralised_red_mud"))
                .liquid()
                .color(0xcc3300)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Fe??", true);

        RedSlurry = new Material.Builder(id++, tjfId("red_slurry"))
                .liquid()
                .color(0xcc3300)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("TiO2?", true);

        FerricREEChloride = new Material.Builder(id++, tjfId("ferric_ree_chloride"))
                .liquid()
                .color(0x30301a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Fe?", true);

        TitanylSulfate = new Material.Builder(id++, tjfId("titanyl_sulfate"))
                .liquid()
                .color(0xdc3d7c)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("TiO(SO4)", true);

        NiAlOCatalyst = new Material.Builder(id++, tjfId("nialo_catalyst"))
                .dust()
                .color(0x0af0af)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("NiAl2O4", true);

        RichNitrogenMix = new Material.Builder(id++, tjfId("rich_nitrogen_mix"))
                .liquid()
                .color(0x6891d8)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2O(CH4)?", true);

        FeCrOCatalyst = new Material.Builder(id++, tjfId("fecro_catalyst"))
                .dust()
                .color(0x8C4517)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("FeCrO3", true);

        OxidisedNitrogenMix = new Material.Builder(id++, tjfId("oxidised_nitrogen_mix"))
                .liquid()
                .color(0x708ACD)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)2(CH4)??", true);

        PurifiedNitrogenMix = new Material.Builder(id++, tjfId("purified_nitrogen_mix"))
                .liquid()
                .color(0x6891d8)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)2(CH4)?", true);

        CarbonatedEthanolamine = new Material.Builder(id++, tjfId("carbonated_ethanolamine"))
                .liquid()
                .color(0x6f7d87)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2NCH2CH2OHC", true);

        AmmoniaRichMix = new Material.Builder(id++, tjfId("ammonia_rich_mix"))
                .liquid()
                .color(0x2f5d99)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("NH3((H2O)2(CH4)?)", true);

        BariumCarbonate = new Material.Builder(id++, tjfId("barium_carbonate"))
                .dust()
                .color(Salt.getMaterialRGB() + 10)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("BaCO3", true);

        BariumAluminate = new Material.Builder(id++, tjfId("barium_aluminate"))
                .dust()
                .color(Saltpeter.getMaterialRGB() + 10)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("BaAl2O4", true);

        Barytocalcite = new Material.Builder(id++, tjfId("barytocalcite"))
                .dust(2)
                .color(0xbf9c7c)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .components(Barium, 1, Calcium, 1, Carbon, 2, Oxygen, 6)
                .build();

        Witherite = new Material.Builder(id++, tjfId("witherite"))
                .dust(2)
                .color(0xc6c29d)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .components(Barium, 1, Carbon, 1, Oxygen, 3)
                .build();

        AcryloNitrile = new Material.Builder(id++, tjfId("acrylonitrile"))
                .liquid()
                .color(0x9999ff)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("CH2CHCN", true);

        SodiumThiocyanate = new Material.Builder(id++, tjfId("sodium_thiocyanate"))
                .liquid()
                .color((Sodium.getMaterialRGB() + Sulfur.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("NaSCN", true);

        PolyacrylonitrileSolution = new Material.Builder(id++, tjfId("polyacrylonitrile_solution"))
                .liquid()
                .color(0x9999ff)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(C3H3N)n(NaSCN)", true);

        AcrylicFibers = new Material.Builder(id++, tjfId("acrylic_fibers"))
                .dust()
                .color(0xfdfdfb)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("(C5O2H8)n", true);

        MethylFormate = new Material.Builder(id++, tjfId("methyl_formate"))
                .liquid()
                .color(0Xff9999)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("HCOOCH3", true);

        WetFormamide = new Material.Builder(id++, tjfId("wet_formamide"))
                .liquid()
                .color(0x33CCFF)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)CH3NO", true);

        Formamide = new Material.Builder(id++, tjfId("formamide"))
                .liquid()
                .color(0x33CCFF)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("CH3NO", true);

        HydroxylamineDisulfate = new Material.Builder(id++, tjfId("hydroxylamine_disulfate"))
                .liquid()
                .color(0x99add6)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(NH3OH)2(NH4)2(SO4)2", true);

        Amidoxime = new Material.Builder(id++, tjfId("amidoxime"))
                .liquid()
                .color(0x66ff33)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H3N2O(CH)", true);

        SeaWater = new Material.Builder(id++, tjfId("sea_water"))
                .liquid()
                .color(0x0000FF)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2O?", true);

        PureUranylNitrateSolution = new Material.Builder(id++, tjfId("pure_uranyl_nitrate"))
                .liquid()
                .color(0x33bd45)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)UO2(NO3)2", true);

        UranylNitrate = new Material.Builder(id++, tjfId("uranyl_nitrate"))
                .dust()
                .color(0x33bd45)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("UO2(NO3)2", true);

        DiluteNitricAcid = new Material.Builder(id++, tjfId("dilute_nitric_acid"))
                .liquid()
                .color((NitricAcid.getMaterialRGB() + Water.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)HNO3", true);

        Brine = new Material.Builder(id++, tjfId("brine"))
                .liquid()
                .color(0xfcfc8a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        ConcentratedBrine = new Material.Builder(id++, tjfId("concentrated_brine"))
                .liquid()
                .color(0xfcfc95)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        BrominatedBrine = new Material.Builder(id++, tjfId("brominated_brine"))
                .liquid()
                .color(0xfdd48d)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Br?", true);

        AcidicBrominatedBrine = new Material.Builder(id++, tjfId("acidic_brominated_brine"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0xfdd48d)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2SO4)Cl?", true);

        CalciumFreeBrine = new Material.Builder(id++, tjfId("calcium_free_brine"))
                .liquid()
                .color(0xfcfca6)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        CalciumSalts = new Material.Builder(id++, tjfId("calcium_salts"))
                .dust()
                .color(Calcium.getMaterialRGB() - 10)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .components(Calcite, 1, Gypsum, 1)
                .build();

        SodiumFreeBrine = new Material.Builder(id++, tjfId("sodium_free_brine"))
                .liquid()
                .color(0xfcfcb1)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        SodiumSalts = new Material.Builder(id++, tjfId("sodium_salts"))
                .dust()
                .color(Sodium.getMaterialRGB() - 5)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("NaCl?", true);

        PotassiumFreeBrine = new Material.Builder(id++, tjfId("potassium_free_brine"))
                .liquid()
                .color(0xfcfcbc)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        PotassiumMagnesiumSalts = new Material.Builder(id++, tjfId("kmg_salts"))
                .dust()
                .color(0xcacac8)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("KClMg(SO4)K2(SO4)KF", true);

        BoronFreeSolution = new Material.Builder(id++, tjfId("boron_free_solution"))
                .liquid()
                .color(0xfcfccd)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        CalciumMagnesiumSalts = new Material.Builder(id++, tjfId("camg_salts"))
                .dust()
                .color(0xcacac8)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("Ca(CO3)(Sr(CO3))(CO2)(MgO)", true);

        SodiumLithiumSolution = new Material.Builder(id++, tjfId("sodium_lithium_solution"))
                .liquid()
                .color(0xfcfccd)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("NaLi?", true);

        SodiumAluminiumHydride = new Material.Builder(id++, tjfId("sodium_aluminium_hydride"))
                .dust()
                .color(0x98cafc)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("NaAlH4", true);

        Cellulose = new Material.Builder(id++, tjfId("cellulose"))
                .dust()
                .color(0xfefefc)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("C6H10O5", true);

        Fructose = new Material.Builder(id++, tjfId("fructose"))
                .dust()
                .color((Cellulose.getMaterialRGB() + Sugar.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("C6H12O6", true);

        SodiumAzide = new Material.Builder(id++, tjfId("sodium_azide"))
                .dust()
                .color((Sodium.getMaterialRGB() + Nitrogen.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("NaN3", true);

        LithiumHydroxideSolution = new Material.Builder(id++, tjfId("lithium_hydroxide_solution"))
                .liquid()
                .color((Lithium.getMaterialRGB() + Oxygen.getMaterialRGB() + Hydrogen.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2O)LiOH", true);

        Glucosamine = new Material.Builder(id++, tjfId("glucosamine"))
                .dust()
                .color((Cellulose.getMaterialRGB() + Water.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("C6H13NO5", true);

        Chitosan = new Material.Builder(id++, tjfId("chitosan"))
                .liquid()
                .color(0xb1bd42)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        SodiumSulfate = new Material.Builder(id++, tjfId("sodium_sulfate"))
                .dust()
                .color(0xFFFFFF)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .components(Sodium, 2, Sulfur, 1, Oxygen, 4)
                .build();

        SodiumSulfateSolution = new Material.Builder(id++, tjfId("sodium_sulfate_solution"))
                .liquid()
                .color((SodiumSulfate.getMaterialRGB() + 30))
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Na2SO4(H2O)", true);

        BoronOxide = new Material.Builder(id++, tjfId("boron_oxide"))
                .dust()
                .color((Boron.getMaterialRGB() + Oxygen.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("B2O3", true);

        Diborane = new Material.Builder(id++, tjfId("diborane"))
                .liquid()
                .color(Boron.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(BH3)2", true);

        LithiumAluminiumFluoride = new Material.Builder(id++, tjfId("lithium_aluminium_fluoride"))
                .dust()
                .color((Lithium.getMaterialRGB() + Aluminium.getMaterialRGB() + Fluorine.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("AlF4Li", true);

        CarbonSulfide = new Material.Builder(id++, tjfId("carbon_sulfide"))
                .liquid()
                .color(0x40ffbf)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("CS2", true);

        DimethylthiocarbamoilChloride = new Material.Builder(id++, tjfId("dimethylthiocarbamoil_chloride"))
                .liquid()
                .color(0xd9ff26)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(CH3)2NC(S)Cl", true);

        Mercaptophenol = new Material.Builder(id++, tjfId("mercaptophenol"))
                .liquid()
                .color(0xbaaf18)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H6OS", true);

        AmineMixture = new Material.Builder(id++, tjfId("amine_mixture"))
                .liquid()
                .color((Methanol.getMaterialRGB() - 20 + Ammonia.getMaterialRGB() - 10) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(NH3)CH4", true);

        SodiumMolybdate = new Material.Builder(id++, tjfId("sodium_molybdate"))
                .dust()
                .color(0xfcfc00)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("Na2MoO4", true);

        SodiumPhosphomolybdate = new Material.Builder(id++, tjfId("sodium_phosphomolybdate"))
                .dust()
                .color(0xfcfc00)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("(MoO3)12Na3PO4", true);

        SodiumTungstate = new Material.Builder(id++, tjfId("sodium_tungstate"))
                .liquid()
                .color(0x7a7777)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .components(Sodium, 2, Tungsten, 1, Oxygen, 4)
                .build();

        SodiumPhosphotungstate = new Material.Builder(id++, tjfId("sodium_phosphotungstate"))
                .dust()
                .color(0x7a7777)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("(WO3)12Na3PO4", true);

        Dichlorocycloctadieneplatinium = new Material.Builder(id++, tjfId("dichlorocyclooctadieneplatinium"))
                .dust()
                .color(0xe0f78a)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("C8H12Cl2Pt", true);

        IridiumCyclooctadienylChlorideDimer = new Material.Builder(id++, tjfId("iridium_cyclooctadienyl_chloride_dimer"))
                .dust()
                .color((Dichlorocycloctadieneplatinium.getMaterialRGB() + Iridium.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Ir2(C8H12)2Cl2", true);

        ChlorodiisopropylPhosphine = new Material.Builder(id++, tjfId("chlorodiisopropyl_phosphine"))
                .liquid()
                .color(0xa2c122)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        DehydrogenationCatalyst = new Material.Builder(id++, tjfId("dehydrogenation_catalyst"))
                .dust()
                .color(0x6464f5)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("?", true);

        AmmoniumPersulfate = new Material.Builder(id++, tjfId("ammonium_persulfate"))
                .liquid()
                .color(0x6464f5)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(NH4)2S2O8", true);

        PolystyreneNanoParticles = new Material.Builder(id++, tjfId("polystryrene_nanoparticles"))
                .dust()
                .color(0x888079)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("(C8H8)n", true);

        Celestine = new Material.Builder(id++, tjfId("celestine"))
                .dust()
                .color(0x9db1b8)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .components(Strontium, 1, Sulfur, 1, Oxygen, 4)
                .build();

        MagnesiumSulfate = new Material.Builder(id++, tjfId("magnesium_sulfate"))
                .dust()
                .color(0xcacac8)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("MgSO4", true);

        StrontiumCarbonate = new Material.Builder(id++, tjfId("strontium_carbonate"))
                .dust()
                .color(0xcacac8)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("SrCO3", true);

        StrontiumOxide = new Material.Builder(id++, tjfId("strontium_oxide"))
                .dust()
                .color(0xcacac8)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("SrO", true);

        ChilledBrine = new Material.Builder(id++, tjfId("chilled_brine"))
                .liquid()
                .color(0xfcfc95)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        MagnesiumContainingBrine = new Material.Builder(id++, tjfId("magnesium_containing_brine"))
                .liquid()
                .color(0xfcfcbc)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Mg?", true);

        PotassiumFluoride = new Material.Builder(id++, tjfId("potassium_fluoride"))
                .dust()
                .color(0xFDFDFD)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .components(Potassium, 1, Fluorine, 1)
                .build();

        SodiumCarbonateSolution = new Material.Builder(id++, tjfId("sodium_carbonate_solution"))
                .liquid()
                .color((SodaAsh.getMaterialRGB() + 30))
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Na2CO3(H2O)", true);

        SodiumChromateSolution = new Material.Builder(id++, tjfId("sodium_chromate_solution"))
                .liquid()
                .color(0xf2e70f)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Na2CrO4(H2O)", true);

        SodiumDichromateSolution = new Material.Builder(id++, tjfId("sodium_dichromate_solution"))
                .liquid()
                .color(0xf2750f)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Na2Cr2O7", true);

        ChromiumIIIOxide = new Material.Builder(id++, tjfId("chromium_iii_oxide"))
                .dust()
                .color(0x4bf25f)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("Cr2O3", true);

        Naphthaldehyde = new Material.Builder(id++, tjfId("napthaldehyde"))
                .liquid()
                .color(0xBCA853)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C10H7CHO", true);

        Benzophenanthrenylacetonitrile = new Material.Builder(id++, tjfId("benzophenanthrenylacetonitrile"))
                .dust()
                .color((Naphthaldehyde.getMaterialRGB() + Ethylene.getMaterialRGB() - 20) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("C20H13N", true);

        UnfoldedFullerene = new Material.Builder(id++, tjfId("unfolded_fullerene"))
                .dust()
                .color((Benzophenanthrenylacetonitrile.getMaterialRGB() + Oxygen.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("C60H30", true);

        PotassiumPeroxymonosulfate = new Material.Builder(id++, tjfId("potassium_peroxymonosulfate"))
                .dust()
                .color((PotassiumMetabisulfite.getMaterialRGB() + 20))
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("KHSO5", true);

        AuricChloride = new Material.Builder(id++, tjfId("auric_chloride"))
                .liquid()
                .color(0xdffb50)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Au2Cl6", true);

        XenonTrioxide = new Material.Builder(id++, tjfId("xenon_trioxide"))
                .liquid()
                .color(0x432791)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("XeO3", true);

        RadonTrioxide = new Material.Builder(id++, tjfId("radon_trioxide"))
                .liquid()
                .color(0x9966ff)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("RnO3", true);

        Fullerene = new Material.Builder(id++, tjfId("fullerene"))
                .dust()
                .color((UnfoldedFullerene.getMaterialRGB() - 20))
                .flags(DISABLE_REPLICATION)
                .iconSet(METALLIC)
                .build()
                .setFormula("C60", true);

        Ferrocene = new Material.Builder(id++, tjfId("ferrocene"))
                .liquid()
                .color((Water.getMaterialRGB() + Chlorine.getMaterialRGB() + Iron.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C10H10Fe", true);

        SodiumEthoxide = new Material.Builder(id++, tjfId("sodium_ethoxide"))
                .dust()
                .color((Ethanol.getMaterialRGB() + SodiumHydroxide.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(METALLIC)
                .build()
                .setFormula("C2H5ONa", true);

        PdFullereneMatrix = new Material.Builder(id++, tjfId("pd_fullerene_matrix"))
                .dust()
                .color((Palladium.getMaterialRGB() + Fullerene.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("PdC73H15NFe", true);

        Pyridine = new Material.Builder(id++, tjfId("pyridine"))
                .liquid()
                .color((Ammonia.getMaterialRGB() + Formaldehyde.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C5H5N", true);

        Dimethylaminopyridine = new Material.Builder(id++, tjfId("dimethylaminopyridine"))
                .dust()
                .color((Dimethylamine.getMaterialRGB() + Pyridine.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("(CH3)2NC5H4N", true);

        Phenylpentanoicacid = new Material.Builder(id++, tjfId("phenylpentanoicacid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color((Butene.getMaterialRGB() + CarbonMonoxide.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C11H14O2", true);

        Dimethylsulfide = new Material.Builder(id++, tjfId("dimethylsulfide"))
                .liquid()
                .color((Methanol.getMaterialRGB() + HydrogenSulfide.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(CH3)2S", true);

        PCBA = new Material.Builder(id++, tjfId("pcba"))
                .liquid()
                .color((Chlorobenzene.getMaterialRGB() + Dimethylsulfide.getMaterialRGB() + Phenylpentanoicacid.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C72H14O2", true);

        PCBS = new Material.Builder(id++, tjfId("pcbs"))
                .liquid()
                .color((Styrene.getMaterialRGB() + PCBA.getMaterialRGB() - 40) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C80H21O2", true);

        QuarkGluonPlasma = new Material.Builder(id++, tjfId("quark_gluon_plasma"))
                .liquid()
                .color(0x8f00ff)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula(makeFancy(TextFormatting.OBFUSCATED + "a" + TextFormatting.RESET + "(u2)d(c2)s(t2)bg" + TextFormatting.OBFUSCATED + "a"), true);

        HeavyQuarks = new Material.Builder(id++, tjfId("heavy_quarks"))
                .liquid()
                .color(0x008800)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula(makeFancy(TextFormatting.OBFUSCATED + "a" + TextFormatting.RESET + "(u2)ds" + TextFormatting.OBFUSCATED + "a"), true);

        LightQuarks = new Material.Builder(id++, tjfId("light_quarks"))
                .liquid()
                .color(0x0000ff)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula(makeFancy(TextFormatting.OBFUSCATED + "a" + TextFormatting.RESET + "(c2)(t2)b" + TextFormatting.OBFUSCATED + "a"), true);

        CosmicComputingMix = new Material.Builder(id++, tjfId("cosmic_computing_mix"))
                .liquid()
                .color(0xafad2f)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula(makeFancy(TextFormatting.OBFUSCATED + "aaaaa"), true);

        HeavyQuarkEnrichedMix = new Material.Builder(id++, tjfId("heavy_quark_enriched_mix"))
                .liquid()
                .color(0xefefef)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula(makeFancy(TextFormatting.OBFUSCATED + "a" + TextFormatting.RESET + "(u2)d(c2)s(t2)b" + TextFormatting.OBFUSCATED + "a"), true);

        Titanium50 = new Material.Builder(id++, tjfId("titanium_50"))
                .ingot(5).liquid()
                .color(Titanium.getMaterialRGB())
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .blast(2000)
                .build()
                .setFormula("Ti_50");

        ScandiumTitanium50Mix = new Material.Builder(id++, tjfId("scandium_titanium_50_mix"))
                .liquid()
                .color((Scandium.getMaterialRGB() + Titanium.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("ScTi_50", true);

        RadonRadiumMix = new Material.Builder(id++, tjfId("radon_radium_mix"))
                .liquid()
                .color((Radium.getMaterialRGB() + Radon.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("RnRa", true);

        DeuteriumSuperheavyMix = new Material.Builder(id++, tjfId("deuterium_superheavy_mix"))
                .liquid()
                .color(0xa2d2a4)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H_2)FlHsOg", true);

        Phenylsodium = new Material.Builder(id++, tjfId("phenylsodium"))
                .liquid()
                .color(0x2c2cc8)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H5Na", true);

        Succinaldehyde = new Material.Builder(id++, tjfId("succinaldehyde"))
                .liquid()
                .color(0x7c6d9a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C4H6O2", true);

        Difluoroaniline = new Material.Builder(id++, tjfId("difluoroaniline"))
                .liquid()
                .color(0x3fac4a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H5F2N", true);

        NDifluorophenylpyrrole = new Material.Builder(id++, tjfId("n_difluorophenylpyrrole"))
                .liquid()
                .color(0x3a9aa9)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C10H7F2N", true);

        SilverChloride = new Material.Builder(id++, tjfId("silver_chloride"))
                .dust()
                .color(0xFEFEFE)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(DULL)
                .components(Silver, 1, Chlorine, 1)
                .build();

        SilverPerchlorate = new Material.Builder(id++, tjfId("silver_perchlorate"))
                .dust()
                .color(SilverChloride.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("AgClO4", true);

        TitaniumCyclopentadienyl = new Material.Builder(id++, tjfId("titanium_cyclopentadienyl"))
                .dust()
                .color(0xbc30bc)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("(C5H5)2Cl2Ti", true);

        SodiumBromide = new Material.Builder(id++, tjfId("sodium_bromide"))
                .dust()
                .color(0xfeaffc)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("NaBr", true);

        PhotopolymerSolution = new Material.Builder(id++, tjfId("photopolymer_solution"))
                .liquid()
                .color(0x8a526d)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C149H97N10O2(TiBF20)", true);

        SodiumChlorate = new Material.Builder(id++, tjfId("sodium_chlorate"))
                .dust()
                .color(Salt.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("NaClO3", true);

        SodiumPerchlorate = new Material.Builder(id++, tjfId("sodium_perchlorate"))
                .dust()
                .color(Salt.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("NaClO4", true);

        GermaniumOxide = new Material.Builder(id++, tjfId("germanium_oxide"))
                .dust()
                .color((Germanium.getMaterialRGB() + 10))
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("GeO2", true);

        GermaniumSulfide = new Material.Builder(id++, tjfId("germanium_sulfide"))
                .dust()
                .color(GermaniumOxide.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("GeS2", true);

        TungstenTrioxide = new Material.Builder(id++, tjfId("tungsten_trioxide"))
                .dust()
                .color(0x99FF97)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(Tungsten.getMaterialIconSet())
                .components(Tungsten, 1, Oxygen, 3)
                .build();

        CadmiumSulfide = new Material.Builder(id++, tjfId("cadmium_sulfide"))
                .dust()
                .color(0xffff00)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("CdS", true);

        CalciumTungstate = new Material.Builder(id++, tjfId("calcium_tungstate"))
                .dust()
                .color(0x6e6867)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .components(Tungsten, 1, Calcium, 1)
                .build();

        CadmiumTungstate = new Material.Builder(id++, tjfId("cadmium_tungstate"))
                .dust()
                .color(CalciumTungstate.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("CdWO4", true);

        CesiumIodide = new Material.Builder(id++, tjfId("cesium_iodide"))
                .dust()
                .color(CaesiumHydroxide.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("CsI", true);

        TlTmCesiumIodide = new Material.Builder(id++, tjfId("tl_tm_cesium_iodide"))
                .dust()
                .color(CaesiumHydroxide.getMaterialRGB() * 9 / 10 + Thallium.getMaterialRGB() / 10)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("CsITlTm", true);

        PolycyclicAromaticMix = new Material.Builder(id++, tjfId("polycyclic_aromatic_mix"))
                .dust()
                .color(Tetracene.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("C18H12", true);

        BismuthNitrateSoluton = new Material.Builder(id++, tjfId("bismuth_nitrate_solution"))
                .liquid()
                .color((Bismuth.getMaterialRGB() + Chlorine.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)Bi(NO3)3", true);

        BismuthGermanate = new Material.Builder(id++, tjfId("bismuth_germanate"))
                .dust()
                .color(0x94cf5c)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("Bi12GeO20", true);

        DisodiumPhosphate = new Material.Builder(id++, tjfId("sodium_diphosphate"))
                .dust()
                .color((Sodium.getMaterialRGB() + Phosphorus.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("Na2HPO4", true);

        Cyanonaphthalene = new Material.Builder(id++, tjfId("cyanonaphthalene"))
                .dust()
                .color((SodiumCyanide.getMaterialRGB() + Naphthalene.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("C11H7N", true);

        TinChloride = new Material.Builder(id++, tjfId("tin_chloride"))
                .dust()
                .color(0x8c8075)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("(CH3)3SnCl", true);

        Triphenylphosphine = new Material.Builder(id++, tjfId("triphenylphosphine"))
                .dust()
                .color((Chlorobenzene.getMaterialRGB() + PhosphorusTrichloride.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("(C6H5)3P", true);

        Methylbenzophenanthrene = new Material.Builder(id++, tjfId("methylbenzophenanthrene"))
                .dust()
                .color((Naphthaldehyde.getMaterialRGB() + Ethylbenzene.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("C19H14", true);

        PotassiumCyanide = new Material.Builder(id++, tjfId("potassium_cyanide"))
                .dust()
                .color((Potassium.getMaterialRGB() + Nitrogen.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("KCN", true);

        TiAlChloride = new Material.Builder(id++, tjfId("tial_chloride"))
                .dust()
                .color((Titanium.getMaterialRGB() + Aluminium.getMaterialRGB() + Chlorine.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("TiAlCl7", true);

        ThionylChloride = new Material.Builder(id++, tjfId("thionyl_chloride"))
                .liquid()
                .color(0xF9F7E5)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("SOCl2", true);

        ZeoliteSievingPellets = new Material.Builder(id++, tjfId("zeolite_sieving_pellets"))
                .dust()
                .color(0xa17bd1)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("Al2O3SiO2", true);

        WetZeoliteSievingPellets = new Material.Builder(id++, tjfId("wet_zeolite_sieving_pellets"))
                .dust()
                .color(0x392f45)
                .flags(DISABLE_REPLICATION)
                .iconSet(METALLIC)
                .build()
                .setFormula("Al2O3SiO2?", true);

        PdIrReOCeOS = new Material.Builder(id++, tjfId("pdirreoceos"))
                .dust()
                .color((Palladium.getMaterialRGB() + Iridium.getMaterialRGB() + Rhenium.getMaterialRGB() + Cerium.getMaterialRGB() + Osmium.getMaterialRGB() + Silicon.getMaterialRGB() + Oxygen.getMaterialRGB()) / 7)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("PdIrReCeOsSiO4", true);

        Ferrocenylfulleropyrrolidine = new Material.Builder(id++, tjfId("ferrocenylfulleropyrddolidine"))
                .liquid()
                .color((Ferrocene.getMaterialRGB() + Ethylene.getMaterialRGB() + CarbonMonoxide.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C74H19FeN", true);

        BenzoylChloride = new Material.Builder(id++, tjfId("benzoyl_chloride"))
                .liquid()
                .color((Toluene.getMaterialRGB() + ThionylChloride.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C7H5ClO", true);

        BenzoylPeroxide = new Material.Builder(id++, tjfId("benzoyl_peroxide"))
                .liquid()
                .color((Barium.getMaterialRGB() + BenzoylChloride.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C14H10O4", true);

        HydroiodicAcid = new Material.Builder(id++, tjfId("hydroiodic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(Hydrogen.getMaterialRGB() / 2 + Iodine.getMaterialRGB() / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("HI :)", true);

        TrimethyltinChloride = new Material.Builder(id++, tjfId("trimethyltin_chloride"))
                .liquid()
                .color(0x8c8075)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(CH3)3SnCl", true);

        Diisopropylcarbodiimide = new Material.Builder(id++, tjfId("diisopropylcarbodiimide"))
                .liquid()
                .color(0xA0CFFE)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C7H14N2", true);

        Hexanediol = new Material.Builder(id++, tjfId("hexanediol"))
                .liquid()
                .color(EthyleneGlycol.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H14O2", true);

        Hexamethylenediamine = new Material.Builder(id++, tjfId("hexamethylenediamine"))
                .liquid()
                .color(Ethylenediamine.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H16N2", true);

        SaccharicAcid = new Material.Builder(id++, tjfId("saccharic_acid"))
                .dust()
                .color(Glucose.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(METALLIC)
                .build()
                .setFormula("C6H10O8", true);

        AdipicAcid = new Material.Builder(id++, tjfId("adipic_acid"))
                .dust()
                .color(0xda9288)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("C6H10O4", true);

        Tertbutanol = new Material.Builder(id++, tjfId("tertbutanol"))
                .liquid()
                .color(0xcccc2c)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C4H10O", true);

        DitertbutylDicarbonate = new Material.Builder(id++, tjfId("ditertbutyl_dicarbonate"))
                .dust()
                .color(0xccccf6)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("C10H18O5", true);

        Triaminoethaneamine = new Material.Builder(id++, tjfId("triaminoethaneamine"))
                .liquid()
                .color(0x6f7d87)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(NH2CH2CH2)3N", true);

        TertButylAzidoformate = new Material.Builder(id++, tjfId("tertbuthylcarbonylazide"))
                .liquid()
                .color(0x888818)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C5H9N3O2", true);

        AminatedFullerene = new Material.Builder(id++, tjfId("aminated_fullerene"))
                .liquid()
                .color(0x2c2caa)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C60N12H12", true);

        Azafullerene = new Material.Builder(id++, tjfId("azafullerene"))
                .liquid()
                .color(0x8a7a1a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C60N12H12", true);

        GrapheneGelSuspension = new Material.Builder(id++, tjfId("graphene_gel_suspension"))
                .dust()
                .color(0xadadad)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("C", true);

        DryGrapheneGel = new Material.Builder(id++, tjfId("dry_graphene_gel"))
                .dust()
                .color(0x3a3ada)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("C", true);

        SupercriticalCO2 = new Material.Builder(id++, tjfId("supercritcal_co_2"))
                .liquid()
                .color(CarbonDioxide.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("CO2", true);

        Caliche = new Material.Builder(id++, tjfId("caliche"))
                .dust(3)
                .color(0xeb9e3f)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(DULL)
                .components(SodiumNitrate, 1, Potassium, 1, Nitrogen, 1, Oxygen, 3, RockSalt, 1, Sodium, 1, Iodine, 1, Oxygen, 3)
                .build();

        CalicheIodateBrine = new Material.Builder(id++, tjfId("caliche_iodate_brine"))
                .liquid()
                .color(0xffe6660)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)NaNO3KNO3KClNaIO3", true);

        IodideSolution = new Material.Builder(id++, tjfId("iodide_solution"))
                .liquid()
                .color(0x08081c)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)NaNO3KNO3KClNaI", true);

        CalicheIodineBrine = new Material.Builder(id++, tjfId("caliche_iodine_brine"))
                .liquid()
                .color(0xffe6660)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)NaNO3KNO3KClNaOHI", true);

        CalicheNitrateSolution = new Material.Builder(id++, tjfId("caliche_nitrate_solution"))
                .liquid()
                .color(0xffe6660)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)NaNO3KNO3KClNaOH", true);

        KeroseneIodineSolution = new Material.Builder(id++, tjfId("kerosene_iodine_solution"))
                .liquid()
                .color(0x08081c)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C12H26I", true);

        IodizedOil = new Material.Builder(id++, tjfId("iodized_oil"))
                .liquid()
                .color(0x666666)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        IodizedBrine = new Material.Builder(id++, tjfId("iodized_brine"))
                .liquid()
                .color(0x525242)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("I?", true);

        IodineBrineMix = new Material.Builder(id++, tjfId("iodine_brine_mix"))
                .liquid()
                .color(0x525242)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("I??", true);

        IodineSlurry = new Material.Builder(id++, tjfId("iodine_slurry"))
                .liquid()
                .color(0x08081c)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("I?", true);

        RoastedSpodumene = new Material.Builder(id++, tjfId("roasted_spodumene"))
                .dust()
                .color(0x3d3d29)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("LiAlSi2O6", true);

        RoastedLepidolite = new Material.Builder(id++, tjfId("roasted_lepidolite"))
                .dust()
                .color(0x470024)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("KLi3Al4O11", true);

        Fluorite = new Material.Builder(id++, tjfId("fluorite"))
                .dust(2)
                .color(0x009933)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .components(Calcium, 1, Fluorine, 2)
                .build();

        DissolvedLithiumOre = new Material.Builder(id++, tjfId("dissolved_lithium_ores"))
                .liquid()
                .color(0x664850)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("LiAlO2(H2SO4)", true);

        AluminiumSulfate = new Material.Builder(id++, tjfId("aluminium_sulfate"))
                .dust()
                .color((Aluminium.getMaterialRGB() + Sulfur.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Al2(SO4)3", true);

        LithiumCarbonateSolution = new Material.Builder(id++, tjfId("lithium_carbonate_solution"))
                .liquid()
                .color((Lithium.getMaterialRGB() + Carbon.getMaterialRGB() + Oxygen.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Li2CO3(H2O)", true);

        Diiodobiphenyl = new Material.Builder(id++, tjfId("diiodobiphenyl"))
                .dust()
                .color(0x000f66)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("C12H8I2", true);

        ThalliumChloride = new Material.Builder(id++, tjfId("thallium_chloride"))
                .dust()
                .color((Thallium.getMaterialRGB() + Chlorine.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("TlCl", true);

        NiAlCatalyst = new Material.Builder(id++, tjfId("nial_catalyst"))
                .dust()
                .color(0x6ea2ff)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("NiAl", true);

        Bipyridine = new Material.Builder(id++, tjfId("bipyridine"))
                .dust()
                .color(0X978662)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("C10H8N2", true);

        Dibenzylideneacetone = new Material.Builder(id++, tjfId("dibenzylideneacetone"))
                .liquid()
                .color(0Xcc6699)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C17H14O", true);

        PalladiumBisDibenzylidieneacetone = new Material.Builder(id++, tjfId("palladium_bisdibenzylidieneacetone"))
                .dust()
                .color(0Xbe81a0)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("C51H42O3Pd2", true);

        ChloroPlatinicAcid = new Material.Builder(id++, tjfId("chloroplatinic_acid"))
                .liquid()
                .color(0xffba54)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2PtCl6", true);

        PotassiumTetrachloroplatinate = new Material.Builder(id++, tjfId("potassium_tetrachloroplatinate"))
                .dust()
                .color(0xffba54)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("K2PtCl4", true);

        NickelChloride = new Material.Builder(id++, tjfId("nickel_chloride"))
                .dust()
                .color(Nickel.getMaterialRGB() + 10)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("NiCl2", true);

        NickelTriphenylPhosphite = new Material.Builder(id++, tjfId("nickel_triphenyl_phosphite"))
                .dust()
                .color(0xd9d973)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("C36H30Cl2NiP2", true);

        GrapheneNanotubeMix = new Material.Builder(id++, tjfId("graphene_nanotube_mix"))
                .dust()
                .color(0x2c2c2c)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("(C)C?", true);

        GrapheneAlignedCNT = new Material.Builder(id++, tjfId("graphene_aligned_cnt"))
                .dust()
                .color(0x2c2c2c)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("(C)C30H20", true);

        Rhodocrosite = new Material.Builder(id++, tjfId("rhodocrosite"))
                .dust(2)
                .color(0xff6699)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .components(Manganese, 1, Carbon, 1, Oxygen, 3)
                .build();

        CassiteriteCokePellets = new Material.Builder(id++, tjfId("cassiterite_coke_pellets"))
                .dust()
                .color(0x8f8f8f)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("SnO2C?", true);

        TinSlag = new Material.Builder(id++, tjfId("tin_slag"))
                .dust()
                .color(0xc8b9a9)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("NbTa?", true);

        NbTaContainingDust = new Material.Builder(id++, tjfId("nbta_containing_dust"))
                .dust()
                .color(0xc8b9a9)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("NbTa", true);

        NiobiumTantalumOxide = new Material.Builder(id++, tjfId("niobium_tantalum_oxide"))
                .dust()
                .color((Niobium.getMaterialRGB() + Tantalum.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("(Nb2O5)(Ta2O5)", true);

        NbTaFluorideMix = new Material.Builder(id++, tjfId("nbta_fluoride_mix"))
                .liquid()
                .color(0xbcac93)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2NbOF5)(H2TaF7)", true);

        Columbite = new Material.Builder(id++, tjfId("columbite"))
                .dust(2)
                .color(0xCCCC00)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .components(Iron, 1, Niobium, 2, Oxygen, 6)
                .build();

        FusedColumbite = new Material.Builder(id++, tjfId("fused_columbite"))
                .dust()
                .color(0xCCCC00)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("(Fe2O3)(NaO)Nb2O5", true);

        BariumPeroxide = new Material.Builder(id++, tjfId("barium_peroxide"))
                .dust()
                .color((Barium.getMaterialRGB() + Oxygen.getMaterialRGB() - 30) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("BaO2", true);

        ColumbiteMinorOxideResidue = new Material.Builder(id++, tjfId("columbite_minor_oxide_residue"))
                .dust()
                .color(0x915028)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("(BaO)(SnO2)(WO3)(Al2O3)", true);

        IronSulfateDust = new Material.Builder(id++, tjfId("iron_sulfate_dust"))
                .dust()
                .color((Iron.getMaterialRGB() + Sulfur.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("FeSO4", true);

        FusedTantalite = new Material.Builder(id++, tjfId("fused_tantalite"))
                .dust()
                .color(0x915028)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("(Fe2O3)(NaO)Ta2O5", true);

        TantaliteMinorOxideResidue = new Material.Builder(id++, tjfId("tantalite_minor_oxide_residue"))
                .dust()
                .color(0xCCCC00)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("(BaO)(ZrO2)(TiO2)(SiO2)", true);

        LeachedTantalite = new Material.Builder(id++, tjfId("leached_tantalite"))
                .dust()
                .color(0x915028)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("(Ta2O5)9Nb2O5?", true);

        FluorotantalicAcid = new Material.Builder(id++, tjfId("flurotantalic_acid"))
                .liquid()
                .color(0x73ff00)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("TaHF7", true);

        NbTaSeparationMixture = new Material.Builder(id++, tjfId("nbta_separation_mixture"))
                .liquid()
                .color(0xbcac93)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C18H39O5P", true);

        AcidicLeachedPyrochlore = new Material.Builder(id++, tjfId("acidic_leached_pyrochlore"))
                .dust()
                .color(0x996633)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("(H2SO4)Ca12Sr6Ba6?ThUNb26O78F26", true);

        REEThUSulfateSolution = new Material.Builder(id++, tjfId("reethu_sulfate_solution"))
                .liquid()
                .color(0x89be5c)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?SO4", true);

        AlkalineEarthSulfateSolution = new Material.Builder(id++, tjfId("alkalineearth_sulfate"))
                .liquid()
                .color(0xe6ebff)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?SO4", true);

        UranylThoriumNitrate = new Material.Builder(id++, tjfId("uranium_thorium_nitrate"))
                .dust()
                .color(0xe7e848)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("UO2(NO3)2Th(NO3)4", true);

        RareEarthNitrateSolution = new Material.Builder(id++, tjfId("rare_earth_nitrate_solution"))
                .liquid()
                .color(0xcfb37d)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?NO3", true);

        OxypentafluoroNiobate = new Material.Builder(id++, tjfId("oxypentafluoroniobate"))
                .liquid()
                .color(0x73ff00)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2NbOF5", true);

        HeptafluoroTantalate = new Material.Builder(id++, tjfId("heptafluorotantalate"))
                .liquid()
                .color(0x73ff00)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2TaF7", true);

        PotasssiumFluoroNiobate = new Material.Builder(id++, tjfId("potassium_fluoroniobate"))
                .dust()
                .color(0x73ff00)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("K2NbF7", true);

        AluminoSilicateWool = new Material.Builder(id++, tjfId("alumino_silicate_wool"))
                .dust(1)
                .color(0xbbbbbb)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(SAND)
                .components(Aluminium, 2, Silicon, 1, Oxygen, 5)
                .build();

        ScandiumTriflate = new Material.Builder(id++, tjfId("scandium_triflate"))
                .dust()
                .color(0xdfcfcf)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Sc(OSO2CF3)3", true);

        Toluenesulfonate = new Material.Builder(id++, tjfId("toluenesulfonate"))
                .liquid()
                .color(0x8f8f00)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C7H7SO3Na", true);

        Niter = new Material.Builder(id++, tjfId("niter"))
                .dust(1)
                .color(16763080)
                .flags(DISABLE_REPLICATION, NO_SMASHING, NO_SMELTING)
                .iconSet(FLINT)
                .components(Saltpeter, 1)
                .build();

        ElectricallyImpureCopper = new Material.Builder(id++, tjfId("electrically_impure_copper"))
                .ingot(2).liquid()
                .color(0x765A30)
                .iconSet(DULL)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION, GENERATE_PLATE)
                .components(Copper, 1, RareEarth, 1)
                .build();

        CopperRefiningSolution = new Material.Builder(id++, tjfId("copper_refining_solution"))
                .liquid()
                .color(0x765A30)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("CuH2SO4", true);

        AnodicSlime = new Material.Builder(id++, tjfId("anodic_slime"))
                .dust()
                .color(0x765A30)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("SeTe??", true);

        SeleniteTelluriteMix = new Material.Builder(id++, tjfId("selenite_tellurite_mixture"))
                .liquid()
                .color(0x765A30)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("TeO2SeO2(Na2CO3)2", true);

        TelluriumOxide = new Material.Builder(id++, tjfId("tellurium_oxide"))
                .dust()
                .color(0xFFFF66)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("TeO2", true);

        SeleniteSolution = new Material.Builder(id++, tjfId("selenite_solution"))
                .liquid()
                .color(0xc1c46a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Na2SeO3", true);

        HydroselenicAcid = new Material.Builder(id++, tjfId("hydroselenic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(Selenium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2Se", true);

        IronPlatinumCatalyst = new Material.Builder(id++, tjfId("iron_platinum_catalyst"))
                .dust()
                .color(Iron.getMaterialRGB() / 2 + Platinum.getMaterialRGB() / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("FePt", true);

        Hydroxyquinoline = new Material.Builder(id++, tjfId("hydroxyquinoline"))
                .liquid()
                .color(0x3a9a71)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C9H7NO", true);

        SodiumRuthenate = new Material.Builder(id++, tjfId("sodium_ruthenate"))
                .dust(2)
                .color(0x3A40CB)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(SHINY)
                .components(Sodium, 2, Oxygen, 4, Ruthenium, 1)
                .build();

        IridiumDioxide = new Material.Builder(id++, tjfId("iridium_dioxide"))
                .dust()
                .color((Iridium.getMaterialRGB() + Oxygen.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION, EXCLUDE_BLOCK_CRAFTING_RECIPES, DISABLE_DECOMPOSITION)
                .iconSet(DULL)
                .components(Iridium, 1, Oxygen, 2)
                .build();

        BariumChlorideSolution = new Material.Builder(id++, tjfId("barium_chloride_solution"))
                .liquid()
                .color((Barium.getMaterialRGB() + Chlorine.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)BaCl3", true);

        BariumTitanatePreparation = new Material.Builder(id++, tjfId("barium_titanate_preparation"))
                .liquid()
                .color(0x99FF99)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("BaTiO3", true);

        Perbromothiophene = new Material.Builder(id++, tjfId("perbromothiophene"))
                .liquid()
                .color(0x87cc17)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C4Br4S", true);

        Diethoxythiophene = new Material.Builder(id++, tjfId("dietoxythiophene"))
                .liquid()
                .color(0x90ff43)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C4H2(OC2H5)2S", true);

        EDOT = new Material.Builder(id++, tjfId("ethylenedioxythiophene"))
                .liquid()
                .color(0x7a9996)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C2H4O2C4H2S", true);

        ZirconylChloride = new Material.Builder(id++, tjfId("zirconyl_chloride"))
                .dust()
                .color(ZirconiumTetrachloride.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("ZrOCl2", true);

        WoodsGlass = new Material.Builder(id++, tjfId("woods_glass"))
                .ingot(2).liquid()
                .color(0x730099)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION, GENERATE_PLATE, DISABLE_DECOMPOSITION)
                .components(SiliconDioxide, 1, Barium, 1, Oxygen, 1, Garnierite, 1, SodaAsh, 1)
                .build();

        IronIodide = new Material.Builder(id++, tjfId("iron_iodide"))
                .dust()
                .color((Iron.getMaterialRGB() + Iodine.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("FeI2", true);

        ThalliumIodide = new Material.Builder(id++, tjfId("thallium_iodide"))
                .dust()
                .color((Thallium.getMaterialRGB() + Iodine.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("TlI", true);

        RubidiumIodide = new Material.Builder(id++, tjfId("rubidium_iodide"))
                .dust()
                .color((Rubidium.getMaterialRGB() + Iodine.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("RbI", true);

        IndiumIodide = new Material.Builder(id++, tjfId("indium_iodide"))
                .dust()
                .color((Indium.getMaterialRGB() + Iodine.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("InI3", true);

        GalliumIodide = new Material.Builder(id++, tjfId("gallium_iodide"))
                .dust()
                .color((Gallium.getMaterialRGB() + Iodine.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("GaI3", true);

        ScandiumIodide = new Material.Builder(id++, tjfId("scandium_iodide"))
                .dust()
                .color((Scandium.getMaterialRGB() + Iodine.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("ScI3", true);

        IronCarbonyl = new Material.Builder(id++, tjfId("iron_carbonyl"))
                .liquid()
                .color(0xff8000)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Fe?", true);

        PurifiedIronCarbonyl = new Material.Builder(id++, tjfId("purified_iron_carbonyl"))
                .liquid()
                .color(0xff8000)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Fe", true);

        CarbonylPurifiedIron = new Material.Builder(id++, tjfId("carbonyl_purified_iron"))
                .dust()
                .color(Iron.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Fe", true);

        YttriumEuropiumVanadate = new Material.Builder(id++, tjfId("yttrium_europium_vanadate"))
                .dust()
                .color((Yttrium.getMaterialRGB() + Europium.getMaterialRGB() + Vanadium.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("YEuVO4", true);

        StrontiumChloride = new Material.Builder(id++, tjfId("strontium_chloride"))
                .dust()
                .color(0x3a9aba)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("SrCl2", true);

        StrontiumEuropiumAluminate = new Material.Builder(id++, tjfId("strontium_europium_aluminate"))
                .dust()
                .color((Strontium.getMaterialRGB() + Europium.getMaterialRGB() + Aluminium.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("SrEuAl2O4", true);

        GreenHalideMix = new Material.Builder(id++, tjfId("green_halide_mix"))
                .dust()
                .color((ThalliumIodide.getMaterialRGB() + PotassiumIodide.getMaterialRGB() + Mercury.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Hg(TlI)KI", true);

        RedHalideMix = new Material.Builder(id++, tjfId("red_halide_mix"))
                .dust()
                .color((RubidiumIodide.getMaterialRGB() + PotassiumIodide.getMaterialRGB() + Mercury.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Hg(RbI)KI", true);

        BlueHalideMix = new Material.Builder(id++, tjfId("blue_halide_mix"))
                .dust()
                .color((IndiumIodide.getMaterialRGB() + PotassiumIodide.getMaterialRGB() + Mercury.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Hg(InI3)KI", true);

        WhiteHalideMix = new Material.Builder(id++, tjfId("white_halide_mix"))
                .dust()
                .color((ScandiumIodide.getMaterialRGB() + PotassiumIodide.getMaterialRGB() + Mercury.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Hg(ScI3)KI", true);

        UVAHalideMix = new Material.Builder(id++, tjfId("uva_halide_mix"))
                .dust()
                .color((GalliumIodide.getMaterialRGB() + PotassiumIodide.getMaterialRGB() + Mercury.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Hg(GaI3)KI", true);

        ThoriumDopedTungsten = new Material.Builder(id++, tjfId("thoria_doped_tungsten"))
                .ingot(2).liquid()
                .color(Tungsten.getMaterialRGB())
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION, GENERATE_ROD, GENERATE_FINE_WIRE, DISABLE_DECOMPOSITION)
                .components(Thorium, 1, Tungsten, 9)
                .build();

        MaragingSteel250 = new Material.Builder(id++, tjfId("maraging_steel_a"))
                .ingot(6).liquid()
                .color(0x92918D)
                .iconSet(METALLIC)
                .flags(DISABLE_REPLICATION, GENERATE_FRAME)
                .components(Steel, 16, Molybdenum, 1, Titanium, 1, Nickel, 4, Cobalt, 2)
                .blast(2413)
                .build();

        Biperfluoromethanedisulfide = new Material.Builder(id++, tjfId("biperfluoromethanedisulfide"))
                .liquid()
                .color(0x3ada40)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C2F6S2", true);
    }

    public static void register2() {

        BariumTriflateSolution = new Material.Builder(id++, tjfId("barium_triflate_solution"))
                .liquid()
                .color((Barium.getMaterialRGB() + Fluorine.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)3(Hg)C2BaF6O6S2", true);

        BariumTriflate = new Material.Builder(id++, tjfId("barium_triflate"))
                .dust()
                .color((Barium.getMaterialRGB() + Fluorine.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Ba(OSO2CF3)2", true);

        BariumNitrate = new Material.Builder(id++, tjfId("barium_nitrate"))
                .dust()
                .color((Barium.getMaterialRGB() + NitricAcid.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Ba(NO3)2", true);

        CopperNitrate = new Material.Builder(id++, tjfId("copper_nitrate"))
                .dust()
                .color(0xcaecec)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Cu(NO3)2", true);

        YttriumNitrate = new Material.Builder(id++, tjfId("yttrium_nitrate"))
                .dust()
                .color(0xdadafc)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Y(NO3)3", true);

        CitricAcid = new Material.Builder(id++, tjfId("citric_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0xffcc00)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H8O7", true);

        WellMixedYBCOxides = new Material.Builder(id++, tjfId("well_mixed_ybc_oxides"))
                .dust()
                .color(0x2c3429)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("YBa2Cu3O6", true);

        PiledTBCC = new Material.Builder(id++, tjfId("piled_tbcc"))
                .dust()
                .color(0x669900)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Tl2Ba2Cu3Ca2", true);

        ActiniumOxalate = new Material.Builder(id++, tjfId("actinium_oxalate"))
                .dust()
                .color(Actinium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Ac(CO2)4", true);

        ActiniumHydride = new Material.Builder(id++, tjfId("actinium_hydride"))
                .dust()
                .color(Actinium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("AcH3", true);

        ActiniumSuperhydridePlasma = new Material.Builder(id++, tjfId("actinium_superhydride_plasma"))
                .plasma()
                .color(Actinium.getMaterialRGB() * 9 / 8)
                .flags(DISABLE_REPLICATION, DISABLE_DECONSTRUCTION)
                .iconSet(FLUID)
                .build()
                .setFormula("AcH12", true);

        LanthanumEmbeddedFullerene = new Material.Builder(id++, tjfId("lanthanum_embedded_fullerene"))
                .dust()
                .color(0x99cc00)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("(C60)2La2", true);

        Dibromoacrolein = new Material.Builder(id++, tjfId("dibromoacrolein"))
                .liquid()
                .color(0x4a4a4a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C2H2Br2O2", true);

        SodiumThiosulfate = new Material.Builder(id++, tjfId("sodium_thiosulfate"))
                .dust()
                .color(0x2090fc)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("Na2S2O3", true);

        Chloroethane = new Material.Builder(id++, tjfId("chloroethane"))
                .liquid()
                .color(0x33aa33)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("CH3CH2Cl", true);

        Bromohydrothiine = new Material.Builder(id++, tjfId("bromodihydrothiine"))
                .liquid()
                .color(0x40ff3a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C4H4S2Br2", true);

        Bromobutane = new Material.Builder(id++, tjfId("bromobutane"))
                .liquid()
                .color(0xff3333)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("CH3(CH2)3Br", true);

        Lithiumthiinediselenide = new Material.Builder(id++, tjfId("lithiumthiinediselenide"))
                .dust()
                .color(0x7ada00)
                .flags(DISABLE_REPLICATION)
                .iconSet(METALLIC)
                .build()
                .setFormula("C4H4S2Li2Se2", true);

        Propadiene = new Material.Builder(id++, tjfId("propadiene"))
                .liquid()
                .color((Butanol.getMaterialRGB() - 20))
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C3H4", true);

        BETS = new Material.Builder(id++, tjfId("bets"))
                .dust()
                .color(0x7ada00)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("C10H8S4Se4", true);

        FranciumCarbide = new Material.Builder(id++, tjfId("francium_carbide"))
                .dust()
                .color(Francium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Fr2C2", true);

        BoronCarbide = new Material.Builder(id++, tjfId("boron_carbide"))
                .dust()
                .color(0x303030)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("B4C3", true);

        BoronFranciumCarbide = new Material.Builder(id++, tjfId("boron_francium_carbide"))
                .dust()
                .color(0x808080)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Fr4B4C7", true);

        AstatideSolution = new Material.Builder(id++, tjfId("astatide_solution"))
                .liquid()
                .color(0x6df63f)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("At(H2O)(SO3)", true);

        MixedAstatideSalts = new Material.Builder(id++, tjfId("mixed_astatide_salts"))
                .dust()
                .color(0x6df63f)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("(At3)(Ho)(Th)(Cn)(Fl)", true);

        SodiumIodate = new Material.Builder(id++, tjfId("sodium_iodate"))
                .dust()
                .color(0x11116d)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("NaIO3", true);

        CopperSulfateSolution = new Material.Builder(id++, tjfId("blue_vitriol_water_solution"))
                .liquid()
                .color(4761024)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .components(Copper, 1, Sulfur, 1, Oxygen, 4, Water, 5)
                .build();

        SodiumPeriodate = new Material.Builder(id++, tjfId("sodium_periodate"))
                .dust()
                .color(0x11116d)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("NaIO4", true);

        StrontiumSuperconductorDust = new Material.Builder(id++, tjfId("strontium_superconductor_dust"))
                .dust()
                .color(0x45abf4)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Sr2RuSgO8", true);

        KryptonDifluoride = new Material.Builder(id++, tjfId("krypton_difluoride"))
                .liquid()
                .color(Krypton.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("KrF2", true);

        ManganeseFluoride = new Material.Builder(id++, tjfId("manganese_fluoride"))
                .dust()
                .color(Pyrolusite.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("MnF2", true);

        PhenylenedioxydiaceticAcid = new Material.Builder(id++, tjfId("phenylenedioxydiacetic_acid"))
                .liquid()
                .color(0x99546a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C10H10O6", true);

        Diethylthiourea = new Material.Builder(id++, tjfId("diethylthiourea"))
                .liquid()
                .color(0x2acaa4)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(C2H5NH)2CS", true);

        Isophthaloylbisdiethylthiourea = new Material.Builder(id++, tjfId("isophthaloylbisdiethylthiourea"))
                .liquid()
                .color(0x8a7b9c)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C18H26N4O2S2", true);

        HassiumChloride = new Material.Builder(id++, tjfId("hassium_chloride"))
                .dust()
                .color(0x5d2abc)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("HsCl4", true);

        RheniumChloride = new Material.Builder(id++, tjfId("rhenium_chloride"))
                .dust()
                .color(0x3c2a5c)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("ReCl5", true);

        AntimonyPentafluoride = new Material.Builder(id++, tjfId("antimony_pentafluoride"))
                .liquid()
                .color(Antimony.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("SbF5", true);

        AntimonyTrichloride = new Material.Builder(id++, tjfId("antimony_trichloride"))
                .dust()
                .color(AntimonyTrifluoride.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("SbCl3", true);

        FluorophosphoricAcid = new Material.Builder(id++, tjfId("fluorophosphoric_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(PhosphorusTrichloride.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("HPF6", true);

        ChargedCesiumCeriumCobaltIndium = new Material.Builder(id++, tjfId("charged_cesium_cerium_cobalt_indium"))
                .dust()
                .color(0x52ad25)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("CsCeCo2In10", true);

        VanadiumSlag = new Material.Builder(id++, tjfId("vanadium_slag"))
                .dust()
                .color((Vanadium.getMaterialRGB() + Titanium.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("(VO)C(TiO2)", true);

        VanadiumSlagDust = new Material.Builder(id++, tjfId("vanadium_slag_dust"))
                .dust()
                .color(0xf2ef1b)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("VO", true);

        VanadiumWasteSolution = new Material.Builder(id++, tjfId("vanadium_waste_solution"))
                .liquid()
                .color(0xbf95f5)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("NaCl(Na2SO4)(SiO2)(Al(OH)3)", true);

        PropargylAlcohol = new Material.Builder(id++, tjfId("propargyl_alcohol"))
                .liquid()
                .color(0xbfb32a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("CHCCH2OH", true);

        PropargylChloride = new Material.Builder(id++, tjfId("propargyl_chloride"))
                .liquid()
                .color(0x918924)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("HC2CH2Cl", true);

        Resin = new Material.Builder(id++, tjfId("resin"))
                .liquid()
                .color(0x3d2f11)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        Turpentine = new Material.Builder(id++, tjfId("turpentine"))
                .liquid()
                .color(0x93bd46)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C10H16", true);

        BetaPinene = new Material.Builder(id++, tjfId("beta_pinene"))
                .dust()
                .color(0x61ad6b)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("C10H16", true);

        Citral = new Material.Builder(id++, tjfId("citral"))
                .liquid()
                .color(0xf2e541)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C10H16O", true);

        BetaIonone = new Material.Builder(id++, tjfId("beta_ionone"))
                .liquid()
                .color(0xdc5ce6)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C13H20O", true);

        VitaminA = new Material.Builder(id++, tjfId("vitamin_a"))
                .liquid()
                .color(0x8d5c91)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C20H30O", true);

        Yeast = new Material.Builder(id++, tjfId("yeast"))
                .dust()
                .color(0xf0e660)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("???", true);

        LinoleicAcid = new Material.Builder(id++, tjfId("linoleic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0xD5D257)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C18H32O2", true);

        Biotin = new Material.Builder(id++, tjfId("biotin"))
                .liquid()
                .color(0x68cc6a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C10H16N2O3S", true);

        Catalase = new Material.Builder(id++, tjfId("catalase"))
                .liquid()
                .color(0xdb6596)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        B27Supplement = new Material.Builder(id++, tjfId("b_27_supplement"))
                .liquid()
                .color(0x386939)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C142H230N36O44S", true);

        CleanAmmoniaSolution = new Material.Builder(id++, tjfId("clear_ammonia_solution"))
                .liquid()
                .color(0x53c9a0)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("NH3(H2O)", true);

        Glutamine = new Material.Builder(id++, tjfId("glutamine"))
                .dust()
                .color(0xede9b4)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("C5H10N2O3", true);

        Blood = new Material.Builder(id++, tjfId("blood"))
                .liquid()
                .color(0x5c0606)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Blood", true);

        BloodCells = new Material.Builder(id++, tjfId("blood_cells"))
                .liquid()
                .color(0xad2424)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("???", true);

        BloodPlasma = new Material.Builder(id++, tjfId("blood_plasma"))
                .liquid()
                .color(0xe37171)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("???", true);

        BFGF = new Material.Builder(id++, tjfId("bfgf"))
                .liquid()
                .color(0xb365e0)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("bFGF", true);

        EGF = new Material.Builder(id++, tjfId("egf"))
                .liquid()
                .color(0x815799)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C257H381N73O83S7", true);

        SilicaGelBase = new Material.Builder(id++, tjfId("silica_gel_base"))
                .liquid()
                .color(0x27a176)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("SiO2(HCl)(NaOH)(H2O)", true);

        SilicaAluminaGel = new Material.Builder(id++, tjfId("silica_alumina_gel"))
                .dust()
                .color(0x558d9e)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("Al2O3SiO2", true);

        PiranhaSolution = new Material.Builder(id++, tjfId("piranha_solution"))
                .liquid()
                .color(0x4820ab)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2SO4)H2O2", true);

        ChlorosulfonicAcid = new Material.Builder(id++, tjfId("chlorosulfonic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0x916c1d)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("HSO3Cl", true);

        AcetylsulfanilylChloride = new Material.Builder(id++, tjfId("acetylsulfanilyl_chloride"))
                .liquid()
                .color((Aniline.getMaterialRGB() + AceticAnhydride.getMaterialRGB() + ChlorosulfonicAcid.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C8H8ClNO3S", true);

        Sulfanilamide = new Material.Builder(id++, tjfId("sulfanilamide"))
                .liquid()
                .color(0x523b0a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H8N2O2S", true);

        AnimalCells = new Material.Builder(id++, tjfId("animal_cells"))
                .liquid()
                .color(0xc94996)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("???", true);

        RapidlyReplicatingAnimalCells = new Material.Builder(id++, tjfId("rapidly_replicating_animal_cells"))
                .liquid()
                .color(0x7a335e)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula(TextFormatting.OBFUSCATED + "????", true);

        Oct4Gene = new Material.Builder(id++, tjfId("oct_4_gene"))
                .liquid()
                .color(0x374f0d)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        SOX2Gene = new Material.Builder(id++, tjfId("sox_2_gene"))
                .liquid()
                .color(0x5d8714)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        KFL4Gene = new Material.Builder(id++, tjfId("kfl_4_gene"))
                .liquid()
                .color(0x759143)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        GeneTherapyFluid = new Material.Builder(id++, tjfId("pluripotency_induction_gene_therapy_fluid"))
                .liquid()
                .color(0x6b2f66)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        Cas9 = new Material.Builder(id++, tjfId("cas_9"))
                .liquid()
                .color(0x5f6e46)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        MycGene = new Material.Builder(id++, tjfId("myc_gene"))
                .liquid()
                .color(0x445724)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        GenePlasmids = new Material.Builder(id++, tjfId("pluripotency_induction_gene_plasmids"))
                .liquid()
                .color(0xabe053)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        Chitin = new Material.Builder(id++, tjfId("chitin"))
                .liquid()
                .color(0xcbd479)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        ZincCokePellets = new Material.Builder(id++, tjfId("zinc_coke_pellets"))
                .dust()
                .color((Zinc.getMaterialRGB() + Coke.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("(H2O)(ZnS)C", true);

        ZincResidualSlag = new Material.Builder(id++, tjfId("zinc_residual_slag"))
                .dust()
                .color((Zinc.getMaterialRGB() - 20))
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("?", true);

        ZincExhaustMixture = new Material.Builder(id++, tjfId("zinc_exhaust_mixture"))
                .liquid()
                .color((CarbonDioxide.getMaterialRGB() + SulfurDioxide.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(SO2)(CO2)?", true);

        ZincSulfate = new Material.Builder(id++, tjfId("zinc_sulfate"))
                .dust()
                .color((Zinc.getMaterialRGB() + Sulfur.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(QUARTZ)
                .components(Zinc, 1, Sulfur, 1, Oxygen, 4)
                .build();

        ZincLeachingResidue = new Material.Builder(id++, tjfId("zinc_leaching_residue"))
                .dust()
                .color((Germanium.getMaterialRGB() + Oxygen.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("Ge?", true);

        ZincFlueDust = new Material.Builder(id++, tjfId("zinc_flue_dust"))
                .dust()
                .color(0xfcfca)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("?", true);

        FineZincSlagDust = new Material.Builder(id++, tjfId("fine_zinc_slag_dust"))
                .dust()
                .color((Zinc.getMaterialRGB() - 10))
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("?", true);

        ZincSlagSlurry = new Material.Builder(id++, tjfId("zinc_slag_slurry"))
                .liquid()
                .color((Zinc.getMaterialRGB() - 20))
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2O?", true);

        MetalRichSlagSlurry = new Material.Builder(id++, tjfId("metal_slag_slurry"))
                .liquid()
                .color((Zinc.getMaterialRGB() - 20))
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2O?", true);

        AcidicMetalSlurry = new Material.Builder(id++, tjfId("acidic_metal_slurry"))
                .liquid()
                .color((Zinc.getMaterialRGB() - 10 + PhosphoricAcid.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H3PO4?", true);

        SeparatedMetalSlurry = new Material.Builder(id++, tjfId("separated_metal_slurry"))
                .liquid()
                .color((Zinc.getMaterialRGB() - 20))
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H3PO4?", true);

        MetalHydroxideMix = new Material.Builder(id++, tjfId("metal_hydroxide_mix"))
                .liquid()
                .color((Zinc.getMaterialRGB() - 30))
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?ZnOH", true);
        ZincPoorMix = new Material.Builder(id++, tjfId("zinc_poor_mix"))
                .liquid()
                .color((Iron.getMaterialRGB() - 10))
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?Fe", true);
        IronPoorMix = new Material.Builder(id++, tjfId("iron_poor_mix"))
                .liquid()
                .color((Copper.getMaterialRGB() + 10))
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?In", true);
        IndiumHydroxideConcentrate = new Material.Builder(id++, tjfId("indium_hydroxide_concentrate"))
                .liquid()
                .color((Indium.getMaterialRGB() + Hydrogen.getMaterialRGB() + 10) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("In(OH)3", true);

        IndiumHydroxide = new Material.Builder(id++, tjfId("indium_hydroxide"))
                .dust()
                .color((Indium.getMaterialRGB() + SodiumHydroxide.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("In(OH)3", true);

        CadmiumZincDust = new Material.Builder(id++, tjfId("cadmium_zinc_dust"))
                .dust()
                .color((Cadmium.getMaterialRGB() + Zinc.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("(H2SO4)CdZn?", true);

        CadmiumThalliumLiquor = new Material.Builder(id++, tjfId("cdtl_liquor"))
                .liquid()
                .color((Cadmium.getMaterialRGB() + Thallium.getMaterialRGB() + RareEarth.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2SO4)CdTl", true);

        ZincAmalgam = new Material.Builder(id++, tjfId("zinc_amalgam"))
                .liquid()
                .color((Zinc.getMaterialRGB() - 20))
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("ZnHg", true);

        ThalliumResidue = new Material.Builder(id++, tjfId("thallium_residue"))
                .dust()
                .color((Thallium.getMaterialRGB() - 10))
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Tl?", true);

        CadmiumSulfateSolution = new Material.Builder(id++, tjfId("cadmium_sulfate"))
                .liquid()
                .color((Cadmium.getMaterialRGB() + SulfuricAcid.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("CdSO4?", true);

        ThalliumSulfateSolution = new Material.Builder(id++, tjfId("thallium_sulfate"))
                .liquid()
                .color((Thallium.getMaterialRGB() + SulfuricAcid.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Tl2SO4?", true);

        ZincChloride = new Material.Builder(id++, tjfId("zinc_chloride"))
                .dust()
                .color((Zinc.getMaterialRGB() + Chlorine.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("ZnCl2", true);

        SodiumSulfite = new Material.Builder(id++, tjfId("sodium_sulfite"))
                .dust()
                .color((SodiumHydroxide.getMaterialRGB() + Sulfur.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("Na2SO3", true);

        PolyphenolMix = new Material.Builder(id++, tjfId("polyphenol_mix"))
                .liquid()
                .color((Phenol.getMaterialRGB() + 10))
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        AcidifiedPolyphenolMix = new Material.Builder(id++, tjfId("acidified_polyphenol_mix"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color((PolyphenolMix.getMaterialRGB() + SulfuricAcid.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        Diethylether = new Material.Builder(id++, tjfId("diethylether"))
                .liquid()
                .color(AcidifiedPolyphenolMix.getMaterialRGB() - 20)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(C2H5)2O", true);

        TannicAcid = new Material.Builder(id++, tjfId("tannic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color((Diethylether.getMaterialRGB() + AcidifiedPolyphenolMix.getMaterialRGB()) / 4)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C76H52O46", true);

        GermanicAcidSolution = new Material.Builder(id++, tjfId("germanic_acid_solution"))
                .liquid()
                .color((Germanium.getMaterialRGB() - 10))
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H4GeO4", true);

        GermaniumChloride = new Material.Builder(id++, tjfId("germanium_chloride"))
                .liquid()
                .color((Germanium.getMaterialRGB() + Chlorine.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("GeCl4", true);

        BariumHydroxide = new Material.Builder(id++, tjfId("barium_hydroxide"))
                .dust()
                .color((Barium.getMaterialRGB() + Hydrogen.getMaterialRGB() + Oxygen.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("Ba(OH)2", true);

        ThiocyanicAcid = new Material.Builder(id++, tjfId("thiocyanic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0xfcfc30)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("HSCN", true);

        ZrHfSeparationMix = new Material.Builder(id++, tjfId("zrhf_separation_mix"))
                .liquid()
                .color(0xfcfc95)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        Zircon = new Material.Builder(id++, tjfId("zircon"))
                .gem(3)
                .color(0xeb9e3f)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(GEM_VERTICAL)
                .components(Zirconium, 1, Silicon, 1, Oxygen, 4)
                .build();

        ZrHfChloride = new Material.Builder(id++, tjfId("zrhf_chloride"))
                .liquid()
                .color(0x51d351)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("ZrHfCl4", true);

        ZirconChlorinatingResidue = new Material.Builder(id++, tjfId("zircon_chlorinating_residue"))
                .liquid()
                .color(0x51d351)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(SiCl4)Co?", true);

        SiliconChloride = new Material.Builder(id++, tjfId("silicon_chloride"))
                .dust()
                .color(Silicon.getMaterialRGB() - 15)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("SiCl4", true);

        ZrHfOxyChloride = new Material.Builder(id++, tjfId("zrhf_oxychloride"))
                .liquid()
                .color(0x51d351)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Cl2HfOZr", true);

        HafniumOxide = new Material.Builder(id++, tjfId("hafnium_oxide"))
                .dust()
                .color(0x404040)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("HfO2", true);

        HafniumChloride = new Material.Builder(id++, tjfId("hafnium_chloride"))
                .dust()
                .color(Hafnium.getMaterialRGB() + 20)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("HfCl4", true);

        HeliumCNO = new Material.Builder(id++, tjfId("helium_rich_cno"))
                .liquid().plasma()
                .color(0x59ffa6)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("He?", true);

        Iron52 = new Material.Builder(id++, tjfId("iron_52"))
                .liquid().plasma()
                .color(Iron.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Fe_52", true);

        Chromium48 = new Material.Builder(id++, tjfId("chromium_48"))
                .liquid().plasma()
                .color(Chrome.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Cr_48", true);

        Titanium44 = new Material.Builder(id++, tjfId("titanium_44"))
                .liquid().plasma()
                .color(Titanium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Ti_44", true);

        Nickel56 = new Material.Builder(id++, tjfId("nickel_56"))
                .liquid().plasma()
                .color(Nickel.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Ni_56", true);

        LiquidEnrichedHelium = new Material.Builder(id++, tjfId("liquid_enriched_helium"))
                .liquid()
                .color(Helium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("HeHe_3", true);

        AbyssalAlloy = new Material.Builder(id++, tjfId("abyssal_alloy"))
                .ingot(6).liquid()
                .color(0x9E706A)
                .iconSet(METALLIC)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .cableProperties(GTValues.V[GTValues.UHV], 2, 8)
                .components(StainlessSteel, 5, TungstenCarbide, 5, Nichrome, 5, Bronze, 5, IncoloyMA956, 5, Iodine, 1, Germanium, 1, Radon, 1)
                .blast(9625)
                .build();

        CosmicNeutronium = new Material.Builder(id++, tjfId("cosmic_neutronium"))
                .ingot(7).liquid()
                .color(0x323232)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .components(Neutronium, 1)
                .blast(14100)
                .build();

                                /*
        = new Material.Builder(id++, tjfId("material"))
                .ingot().liquid()
                .color()
                .iconSet()
                .flags(DISABLE_REPLICATION)
                .components()
                .blast()
                .build();

        = new Material.Builder(id++, tjfId("material"))
                .liquid()
                .color()
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("",true);

        = new Material.Builder(id++, tjfId("material"))
                .dust()
                .color()
                .flags(DISABLE_REPLICATION)
                .iconSet()
                .build()
                .setFormula("",true);


         */

    }

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

        //EXT2 METAL ADDITIONS
        List<Material> e2mmats = new ArrayList<>();
        Collections.addAll(e2mmats, MaragingSteel250, AbyssalAlloy);
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

        //PLATE ADDITIONS
        /*
        List<Material> platemats = new ArrayList<>();
        Collections.addAll(platemats, BorosilicateGlass);
        for (Material mat : platemats) {
            mat.addFlags(GENERATE_PLATE);
        }

         */

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
        Collections.addAll(stickmats, ReinforcedEpoxyResin);
        for (Material mat : stickmats) {
            mat.addFlags(GENERATE_ROD);
        }

        //DUST ADDITIONS
        List<Material> dmats = new ArrayList<>();
        Collections.addAll(dmats, Dysprosium, Iodine, Gadolinium, Strontium, Holmium, Californium, Zirconium, Thallium, Rubidium, Technetium,
                Terbium, Promethium, Radium, Tellurium, Francium, Berkelium, Curium, Actinium, Protactinium, Scandium, Thulium, Hafnium, Astatine,
                Selenium, Praseodymium, Copernicium, Erbium);
        for (Material mat : dmats) {
            addDust(mat, 1, 0);
        }

        //FLUID Additions
        List<Material> fmats = new ArrayList<>();
        Collections.addAll(fmats, Sodium, Bromine, AmmoniumChloride, Rubidium, Caesium, Francium, Polonium, Praseodymium, Ytterbium, Neptunium,
                ProtoAdamantium, Scandium, Radium, MetastableHassium, MetastableFlerovium, MetastableOganesson, HeavyQuarkDegenerateMatter);

        for (Material mat : fmats) {
            addLiquid(mat);
        }

        //SPECIFIC FLUID ADDITIONS

        //LIQUIDS
        List<Material> lmats = new ArrayList<>();
        Collections.addAll(lmats, Fluorine, Xenon, Helium3, Hydrogen);
        for (Material mat : lmats) {
            addLiquid(mat);
            mat.getProperty(PropertyKey.FLUID).setPrimaryKey(FluidStorageKeys.GAS);
        }

        //PLASMAS
        List<Material> pmats = new ArrayList<>();
        Collections.addAll(pmats, Carbon, Hydrogen, Helium3, Radon, Krypton, Neon);
        for (Material mat : pmats) {
            addFluid(mat, "plasma", false);
        }

        //ORE ADDITIONS
        List<Material> omats = new ArrayList<>();
        Collections.addAll(omats, Triniite, NaquadricCompound, EnrichedNaquadricCompound, NaquadriaticCompound, Lignite, Witherite, Barytocalcite,
                Celestine, Caliche, Fluorite, Rhodocrosite, Columbite, Niter, Zircon);
        for (Material mat : omats) {
            addOre(mat, 1, 1, true);
        }

        //ORE BYPRODUCTS
        OreProperty oreProp;

        oreProp = NaquadricCompound.getProperty(PropertyKey.ORE);
        oreProp.addOreByProducts(EnrichedNaquadricCompound);

        oreProp = EnrichedNaquadricCompound.getProperty(PropertyKey.ORE);
        oreProp.addOreByProducts(NaquadricCompound, NaquadriaticCompound);

        oreProp = Triniite.getProperty(PropertyKey.ORE);
        oreProp.addOreByProducts(NaquadricCompound, Bismuth, Thallium);

        //SPECIFIC CASES

        Bohrium.addFlags(GENERATE_FRAME, GENERATE_ROUND);
        NaquadahAlloy.addFlags(GENERATE_FINE_WIRE);

        Copper.addFlags(GENERATE_DENSE);
        StainlessSteel.addFlags(GENERATE_DENSE);

        Naquadria.addFlags(GENERATE_FRAME);
        Osmiridium.addFlags(GENERATE_FRAME);

        //FLUID PIPES
        addFluidPipes(Zeron100, 15000, 1750, true);

        //ORE PREFIX IGNORE FIXES
        plate.removeIgnored(BorosilicateGlass);

        //LENSES
        List<Material> lensmats = new ArrayList<>();
        Collections.addAll(lensmats, Amethyst);
        for (Material mat : lensmats) {
            mat.addFlags(GENERATE_LENS);
        }

    }

    private static String makeFancy(String input) {
        return fancyTest(input, fanciness, 80.0, 1);
    }

    private static String fancyTest(String input, TextFormatting[] colors, double delay, int posstep) {
        StringBuilder sb = new StringBuilder();

        int offset = (int) Math.floor(MinecraftServer.getCurrentTimeMillis() / delay) % colors.length;
        String format = null;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (!(c == '&' || c == '\u00a7')) {
                int col = ((i * posstep) + colors.length - offset) % colors.length;
                sb.append(colors[col].toString());
                if (format != null)
                    sb.append(format);
                sb.append(c);
            } else
                format = input.charAt(++i) == 'r' ? null : format == null ? "" + c + input.charAt(i) : format + c + input.charAt(i);
        }
        return sb.toString();
    }
}