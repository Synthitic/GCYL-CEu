package com.fulltrix.gcyl.materials;

//import com.fulltrix.tjfcore.materials.IsotopeMaterial;
//import com.fulltrix.tjfcore.materials.RadioactiveMaterial;

import gregtech.api.GTValues;
import gregtech.api.fluids.FluidBuilder;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialFlag;
import gregtech.api.unification.material.properties.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.fulltrix.gcyl.GCYLElements.*;
import static com.fulltrix.gcyl.api.GCYLUtility.gcylId;
import static com.fulltrix.gcyl.materials.GCYLNuclearMaterials.*;
import static gregicality.multiblocks.api.unification.GCYMMaterialFlags.NO_ALLOY_BLAST_RECIPES;
import static gregicality.multiblocks.api.unification.GCYMMaterials.*;
import static gregtech.api.GTValues.*;
import static gregtech.api.fluids.attribute.FluidAttributes.ACID;
import static gregtech.api.unification.Elements.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtech.api.unification.material.info.MaterialIconSet.*;
import static gregtech.integration.groovy.MaterialPropertyExpansion.addLiquid;
import static kono.ceu.materialreplication.api.unification.materials.flags.MRMaterialFlags.DISABLE_DECONSTRUCTION;
import static kono.ceu.materialreplication.api.unification.materials.flags.MRMaterialFlags.DISABLE_REPLICATION;
import static net.minecraft.util.text.TextFormatting.*;

//TODO: add custom material icon sets for special materials
//TODO: add additional blast properties like gas tiers, eu/t, and duration
//TODO: add/mixin gas tiers

public class GCYLMaterials {

    public static final MaterialFlag GENERATE_NUCLEAR_COMPOUND = new MaterialFlag.Builder("generate_nuclear_compound").build();

    public static final MaterialFlag GENERATE_ISOTOPES_COMPOUND = new MaterialFlag.Builder("generate_isotopes_compound").build();

    //public static final MaterialFlag DISABLE_REPLICATION = new MaterialFlag.Builder("disable_replication").build();
    public static final List<MaterialFlag> CORE_METAL = new ArrayList<>();
    public static final List<MaterialFlag> STD_SOLID = new ArrayList<>();
    public static final List<MaterialFlag> STD_GEM = new ArrayList<>();

    private static final TextFormatting[] fanciness = new TextFormatting[]{RED, GOLD, YELLOW, GREEN, AQUA, BLUE, LIGHT_PURPLE};

    public static int id = 0;

    /////////////////////////////////////////
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
    public static Material RheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate;
    public static Material Legendarium;
    public static Material LanthanumFullereneMix;
    public static Material LanthanumFullereneNanotubes;
    public static Material HexanitroHexaazaisowurtzitane;
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
    public static Material ChargedCaesiumCeriumCobaltIndium;
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
    public static Material HighEnergyQGP;
    public static Material CaliforniumCyclopentadienide;
    public static Material IridiumTrioxide;
    public static Material IridiumTrichlorideSolution;
    public static Material SemisolidHydrogen;
    public static Material MicrocrystallizingHydrogen;
    public static Material Vinteum;
    public static Material Tanzanite;
    public static Material Dibismusthydroborat;
    public static Material CircuitCompoundMK3;
    public static Material HyperFuelI;
    public static Material HyperFuelII;
    public static Material HyperFuelIII;
    public static Material HyperFuelIV;
    public static Material NaquadricSolution;
    public static Material EnrichedNaquadricSolution;
    public static Material NaquadriaticSolution;
    public static Material EnrichedFluoronaquadricAcid;
    public static Material FluoronaquadriaticAcid;
    public static Material NaquadahDifluoride;
    public static Material EnrichedNaquadahDifluoride;
    public static Material NaquadriaDifluoride;
    public static Material IndiumTrifluoride;
    public static Material NaquadahConcentrate;
    public static Material EnrichedNaquadahConcentrate;
    public static Material NaquadriaConcentrate;
    public static Material NaquadriaHexafluoride;
    public static Material RadonDifluoride;
    public static Material RadonNaquadriaoctafluoride;
    public static Material CesiumFluoride;
    public static Material CesiumXenontrioxideFluoride;
    public static Material NaquadriaCesiumXenonNonfluoride;
    public static Material NitrylFluoride;
    public static Material NitrosoniumOctafluoroxenate;
    public static Material NaquadriaCesiumfluoride;
    public static Material EnrichedNaquadahhexafluoride;
    public static Material EnrichedXenonHexafluoronaquadate;
    public static Material BromineTrifluoride;
    public static Material AuricFluoride;
    public static Material XenoauricFluoroantimonicAcid;
    public static Material NaquadahSulfate;
    public static Material IndiumTrioxide;
    public static Material NaquadahSolution;
    public static Material ClearNaquadahLiquid;
    public static Material ComplicatedNaquadahGas;
    public static Material ComplicatedLightNaquadah;
    public static Material ComplicatedMediumNaquadah;
    public static Material ComplicatedHeavyNaquadah;
    public static Material NaquadahGas;
    public static Material HeavyNaquadah;
    public static Material LightNaquadah;
    public static Material MediumNaquadah;
    public static Material FCrackedMediumNaquadah;
    public static Material FCrackedLightNaquadah;
    public static Material FCrackedHeavyNaquadah;
    public static Material LightNaquadahFuel;
    public static Material MediumNaquadahFuel;
    public static Material HeavyNaquadahFuel;
    public static Material ENaquadahSolution;
    public static Material ClearENaquadahLiquid;
    public static Material ComplicatedLightENaquadah;
    public static Material ComplicatedMediumENaquadah;
    public static Material ComplicatedHeavyENaquadah;
    public static Material HeavyENaquadah;
    public static Material MediumENaquadah;
    public static Material LightENaquadah;
    public static Material RnCrackedMediumENaquadah;
    public static Material RnCrackedLightNaquadah;
    public static Material RnCrackedHeavyENaquadah;
    public static Material LightENaquadahFuel;
    public static Material MediumENaquadahFuel;
    public static Material HeavyENaquadahFuel;
    public static Material FluoronaquadricAcid;
    public static Material Fluorescein;
    public static Material SodiumBorohydride;
    public static Material BoronTrifluorideEtherate;
    public static Material Decaborane;
    public static Material SodiumTetrafluoroborate;
    public static Material CesiumCarboranePrecusor;
    public static Material BoraneDimethylsulfide;
    public static Material CesiumCarborane;
    public static Material SilverNitrate;
    public static Material Fluorocarborane;
    public static Material CaesiumNitrate;
    public static Material SilverIodide;
    public static Material FumingNitricAcid;
    public static Material PureCrystallineNitricAcid;
    public static Material ActiniumTriniumHydroxides;
    public static Material ResidualTriniiteSolution;
    public static Material Perfluorobenzene;
    public static Material ActiniumRadiumHydroxideSolution;
    public static Material HeavilyFluorinatedTriniumSolution;
    public static Material MoltenCalciumSalts;
    public static Material TriniumTetrafluoride;
    public static Material AcetylChloride;
    public static Material EthylTrifluoroacetate;
    public static Material PhosphorousPentasulfide;
    public static Material Acetothienone;
    public static Material TheonylTrifluoroacetate;
    public static Material ActiniumRadiumNitrateSolution;
    public static Material ActiniumNitrate;
    public static Material RadiumNitrate;
    public static Material LithiumPeroxideSolution;
    public static Material NitrogenPentoxide;
    public static Material TitaniumNitrate;
    public static Material Carbon12;
    public static Material Carbon13;
    public static Material Nitrogen14;
    public static Material NItrogen15;
    public static Material CNOcatalyst;
    public static Material Calcium44;
    public static Material QuassifissioningPlasma;
    public static Material FlYbPlasma;
    public static Material Ytterbium178;
    public static Material TitaniumTetrafluoride;
    public static Material Titanium50Tetrafluoride;
    public static Material OgannesonBreedingBase;
    public static Material HotMetastableOganesson;
    public static Material HafniumCarbide;
    public static Material SeaborgiumCarbide;
    public static Material Helium4;
    public static Material OsmiumSolution;
    public static Material LithiumHydroxide;
    public static Material TungstenHexachloride;
    public static Material Bowieite;
    public static Material PlatinumMetallicPowder;
    public static Material CrudeRhodiumMetal;
    public static Material AcidicIridiumSolution;
    public static Material PGSDResidue2;
    public static Material PlatinumConcentrate;
    public static Material PlatinumResidue;
    public static Material PotassiumDisulfate;
    public static Material LeachResidue;
    public static Material PalladiumMetallicPowder;
    public static Material PalladiumAmmonia;
    public static Material PalladiumSalt;
    public static Material PalladiumRawPowder;
    public static Material RhodiumSulfateSolution;
    public static Material RhodiumSalt;
    public static Material RhodiumSaltSolution;
    public static Material RhodiumNitrate;
    public static Material RhodiumFilterCake;
    public static Material RhodiumFilterCakeSolution;
    public static Material RutheniumTetroxideSolution;
    public static Material HotRutheniumTetroxideSolution;
    public static Material MicaPulp;
    public static Material NickelOxideHydroxide;
    public static Material LithiumCobaltOxide;
    public static Material LithiumTriflate;
    public static Material Xylose;
    public static Material SodiumAlginateSolution;
    public static Material CalciumAlginate;
    public static Material SiliconNanoparticles;
    public static Material Sorbose;
    public static Material AscorbicAcid;
    public static Material DehydroascorbicAcid;
    public static Material GalliumChloride;
    public static Material Halloysite;
    public static Material SulfurCoatedHalloysite;
    public static Material FluorideBatteryElectrolyte;
    public static Material LanthanumNickelOxide;
    public static Material HG1223;
    public static Material Indalloy140;
    public static Material Seabohrgium;
    public static Material Fordnium;
    public static Material Enargite;
    public static Material Dolomite;
    public static Material Wollastonite;
    public static Material Kaolinite;
    public static Material Tenorite;
    public static Material Tennantite;
    public static Material Cuprite;
    public static Material Zirkelite;
    public static Material Arsenopyrite;
    public static Material Draconium;
    public static Material AwakenDraconium;
    public static Material Chaos;
    public static Material ChaosAlloy;
    public static Material BrightSteel;
    public static Material Gallite;
    public static Material BabbittAlloy;
    public static Material Inconel625;
    public static Material ApatiteAcidicLeach;
    public static Material PhosphorousArsenicSolution;
    public static Material ApatiteSolidResidue;
    public static Material FluoroApatite;
    public static Material FluoroapatiteAcidicLeach;
    public static Material FluoroapatiteSolidResidue;
    public static Material SodiumArsenate;
    public static Material FinelyPowderedRutile;
    public static Material RawSienna;
    public static Material BurnedSienna;
    public static Material MercuryNitrate;
    public static Material MercuryChloride;
    public static Material MercuryIodide;
    public static Material BismuthVanadateSolution;
    public static Material BismuthVanadate;
    public static Material CopperArsenite;
    public static Material ScheelesGreen;
    public static Material CobaltZincOxide;
    public static Material CobaltAluminate;
    public static Material PotassiumFerrocyanide;
    public static Material PrussianBlue;
    public static Material TitaniumYellow;
    public static Material ManganeseIIIOxide;
    public static Material AmmoniumManganesePhosphate;
    public static Material HanPurple;
    public static Material ChromeYellow;
    public static Material ChromeOrange;
    public static Material Nitrotoluene;
    public static Material DiaminostilbenedisulfonicAcid;
    public static Material Nigrosin;
    public static Material SodiumSulfanilate;
    public static Material Naphthylamine;
    public static Material DirectBrown;
    public static Material DianilineterephthalicAcid;
    public static Material Quinacridone;
    public static Material Acetoacetanilide;
    public static Material DiarylideYellow;
    public static Material Quinizarin;
    public static Material Toluidine;
    public static Material AlizarineCyanineGreen;
    public static Material Anthraquinone;
    public static Material Aminoanthraquinone;
    public static Material IndanthroneBlue;
    public static Material Mauveine;
    public static Material Isopropylsuccinate;
    public static Material Benzonitrile;
    public static Material Diketopyrrolopyrrole;
    public static Material Indigo;
    public static Material Tetrabromoindigo;
    public static Material CyanIndigoDye;
    public static Material Erythrosine;
    public static Material DeepOverworldGas;
    public static Material DeepNetherGas;

    //COILS
    public static Material Pikyonium;
    public static Material TitanSteel;
    public static Material CosmicNeutronium;
    public static Material Infinity;
    public static Material Eternity;
    //superconductors
    public static Material LVSuperconductor;
    public static Material MVSuperconductor;
    public static Material HVSuperconductor;
    public static Material EVSuperconductor;
    public static Material IVSuperconductor;
    public static Material LuVSuperconductor;
    public static Material ZPMSuperconductor;
    public static Material UVSuperconductor;
    public static Material UHVSuperconductor;

    public static Material UEVSuperconductor;
    public static Material UIVSuperconductor;
    public static Material UXVSuperconductor;
    public static Material OpVSuperconductor;
    public static Material MAXSuperconductor;

    public static Material LVSuperconductorBase;
    public static Material MVSuperconductorBase;
    public static Material HVSuperconductorBase;
    public static Material EVSuperconductorBase;
    public static Material IVSuperconductorBase;
    public static Material LuVSuperconductorBase;
    public static Material ZPMSuperconductorBase;
    public static Material UVSuperconductorBase;

    public static Material UHVSuperconductorBase;
    public static Material UEVSuperconductorBase;
    public static Material UIVSuperconductorBase;
    public static Material UXVSuperconductorBase;
    public static Material OpVSuperconductorBase;


    ///////////////////////////////////////////

    static {
        STD_SOLID.addAll(Arrays.asList(GENERATE_PLATE, GENERATE_ROD, GENERATE_BOLT_SCREW, GENERATE_LONG_ROD));
        STD_GEM.addAll(STD_SOLID);
        STD_GEM.add(GENERATE_LENS);
        CORE_METAL.addAll(EXT2_METAL);
        CORE_METAL.addAll(Arrays.asList(GENERATE_RING, GENERATE_FRAME, GENERATE_ROTOR, GENERATE_SMALL_GEAR, GENERATE_DENSE, GENERATE_FINE_WIRE, GENERATE_GEAR));
    }

    public static void init() {
        registerNuclear();
        initDEMaterials();
        register();
        register2();
        registerSuperconductors();
        register3();
    }

    private static void register() {

        //////////////////////

        EnrichedNaquadahAlloy = new Material.Builder(++id, gcylId("enriched_naquadah_alloy"))
                .ingot(2)
                .liquid()
                .color(0x403f3d)
                .iconSet(SHINY)
                .flags(EXT2_METAL, DISABLE_DECOMPOSITION, GENERATE_FRAME, GENERATE_SMALL_GEAR, GENERATE_ROUND, GENERATE_RING, GENERATE_ROTOR, GENERATE_GEAR)
                .components(NaquadahEnriched, 4, Rhodium, 2, Ruthenium, 2, Dubnium, 1, Rubidium, 2, Einsteinium, 1)
                .fluidPipeProperties(25000,2200,true)
                .blast(10000)
                .flags(DISABLE_REPLICATION).build();

        Inconel792 = new Material.Builder(++id, gcylId("inconel_b"))
                .ingot(5)
                .color(0x6CF076)
                .iconSet(SHINY)
                .flags(CORE_METAL)
                .components(Nickel, 2, Niobium, 1, Aluminium, 2, Nichrome, 1)
                .blast(6200)
                .flags(DISABLE_REPLICATION).build();

        EglinSteelBase = new Material.Builder(++id, gcylId("eglin_steel_base"))
                .dust(6)
                .color(0x8B4513)
                .iconSet(SAND)
                .components(Iron, 4, Kanthal, 1, Invar, 5)
                .flags(DISABLE_REPLICATION).build();

        EglinSteel = new Material.Builder(++id, gcylId("eglin_steel"))
                .ingot(6).liquid()
                .color(0x8B4513)
                .iconSet(METALLIC)
                .flags(EXT2_METAL, GENERATE_FRAME)
                .components(EglinSteelBase, 10, Sulfur, 1, Silicon, 1, Carbon, 1)
                .blast(1048)
                .flags(DISABLE_REPLICATION).build();

        Pikyonium = new Material.Builder(++id, gcylId("pikyonium"))
                .ingot(7).liquid()
                .color(0x3467BA)
                .flags(CORE_METAL)
                .iconSet(SHINY)
                .cableProperties(GTValues.V[GTValues.UEV], 4, 32)
                .components(Inconel792, 8, EglinSteel, 5, NaquadahEnriched, 4, Cerium, 3, Antimony, 2, Platinum, 2, Ytterbium, 1, TungstenSteel, 4)
                .blast(11500)
                .flags(DISABLE_REPLICATION).build();

        LithiumNiobate = new Material.Builder(++id, gcylId("lithium_niobate"))
                .ingot()
                .color(0xcfcf3a)
                .iconSet(SHINY)
                .flags(GENERATE_PLATE)
                .components(Lithium, 1, Niobium, 1, Oxygen, 4)
                .blast(6700)
                .flags(DISABLE_REPLICATION).build();

        Polyetheretherketone = new Material.Builder(++id, gcylId("polyetheretherketone"))
                .polymer(2).liquid()
                .color(0x403e37)
                .iconSet(DULL)
                .flags(NO_SMASHING, GENERATE_FOIL, GENERATE_FINE_WIRE, NO_ALLOY_BLAST_RECIPES)
                .components(Carbon, 20, Hydrogen, 12, Oxygen, 3)
                .blast(6700)
                .flags(DISABLE_REPLICATION).build();

        LeadZirconateTitanate = new Material.Builder(++id, gcylId("lead_zirconate_titanate"))
                .gem(3)
                .color(0x359ade)
                .iconSet(OPAL)
                .flags(GENERATE_PLATE)
                .components(Lead, 1, Zirconium, 1, Titanium, 1, Oxygen, 3)
                .flags(DISABLE_REPLICATION).build();

        LithiumTitanate = new Material.Builder(++id, gcylId("lithium_titanate"))
                .ingot(5)
                .color(0xfe71a9)
                .iconSet(SHINY)
                .flags(CORE_METAL, GENERATE_PLATE)
                .components(Lithium, 2, Titanium, 1, Oxygen, 3)
                .flags(DISABLE_REPLICATION).build();

        CarbonNanotubes = new Material.Builder(++id, gcylId("carbon_nanotubes"))
                .ingot().liquid()
                .color(0x2c2c2c)
                .iconSet(SHINY)
                .flags(EXCLUDE_BLOCK_CRAFTING_RECIPES, GENERATE_FOIL, DISABLE_DECOMPOSITION, GENERATE_FINE_WIRE)
                .components(Carbon, 1)
                .flags(DISABLE_REPLICATION).build();

        SodiumSeaborgate = new Material.Builder(++id, gcylId("sodium_seaborgate"))
                .ingot()
                .color(0x55bbd4)
                .iconSet(SHINY)
                .components(Sodium, 2, Seaborgium, 1, Oxygen, 4)
                .flags(DISABLE_REPLICATION).build();

        TBCCODust = new Material.Builder(++id, gcylId("TBCCODust"))
                .dust()
                .color(0x669900)
                .iconSet(SHINY)
                .components(Thallium, 1, Barium, 2, Calcium, 2, Copper, 3, Oxygen, 10)
                .flags(DISABLE_REPLICATION).build();

        FullereneDopedNanotubes = new Material.Builder(++id, gcylId("fullerene_doped_nanotubes"))
                .liquid()
                .color(0x6c2c6c)
                .components()
                .build()
                .setFormula("C60CNT",true);

        LithiumNiobateNanoparticles = new Material.Builder(++id, gcylId("lithium_niobate_nanoparticles"))
                .dust()
                .color(LithiumNiobate.getMaterialRGB()-10)
                .iconSet(SHINY)
                .build()
                .setFormula("LiNbO4",true);

        ZirconiumTetrafluoride = new Material.Builder(++id, gcylId("zirconium_tetrafluoride"))
                .dust()
                .color((Zirconium.getMaterialRGB()+Fluorine.getMaterialRGB())/2)
                .iconSet(SHINY)
                .build()
                .setFormula("ZrF4", true);

        BariumDifluoride = new Material.Builder(++id, gcylId("barium_difluoride"))
                .dust()
                .color((Barium.getMaterialRGB()+Fluorine.getMaterialRGB())/2)
                .iconSet(METALLIC)
                .build()
                .setFormula("BaF2", true);

        LanthanumTrifluoride = new Material.Builder(++id, gcylId("lanthanum_trifluoride"))
                .dust()
                .color((Lanthanum.getMaterialRGB()+Fluorine.getMaterialRGB())/2)
                .iconSet(METALLIC)
                .build()
                .setFormula("LaF3", true);

        AluminiumTrifluoride = new Material.Builder(++id, gcylId("aluminium_trifluoride"))
                .dust()
                .color((Aluminium.getMaterialRGB()+Fluorine.getMaterialRGB())/2)
                .iconSet(ROUGH)
                .build()
                .setFormula("AlF3", true);

        ErbiumTrifluoride = new Material.Builder(++id, gcylId("erbium_trifluoride"))
                .dust()
                .color((Erbium.getMaterialRGB()+Fluorine.getMaterialRGB())/2)
                .iconSet(ROUGH)
                .build()
                .setFormula("ErF3", true);

        ZBLANDust = new Material.Builder(++id, gcylId("zblan_dust"))
                .dust().liquid()
                .color((ZirconiumTetrafluoride.getMaterialRGB()+BariumDifluoride.getMaterialRGB()+LanthanumTrifluoride.getMaterialRGB()+AluminiumTrifluoride.getMaterialRGB())/4)
                .iconSet(SHINY)
                .build()
                .setFormula("(ZrF4)18(BaF2)7(LaF3)2(AlF3)(NaF)7", true);

        ErbiumDopedZBLANDust = new Material.Builder(++id, gcylId("erbium_doped_zblan_dust"))
                .dust()
                .color((ZBLANDust.getMaterialRGB()+ErbiumTrifluoride.getMaterialRGB())/2)
                .iconSet(SHINY)
                .build()
                .setFormula("(ErF3)(ZrF4)18(BaF2)7(LaF3)2(AlF3)(NaF)7", true);

        SodiumFluoride = new Material.Builder(++id, gcylId("sodium_fluoride"))
                .dust(2)
                .color((Sodium.getMaterialRGB() + Fluorine.getMaterialRGB()) / 2)
                .iconSet(DULL)
                .components(Sodium, 1, Fluorine, 1)
                .flags(DISABLE_REPLICATION).build();

        TungstenTitaniumCarbide = new Material.Builder(++id, gcylId("tungsten_titanium_carbide"))
                .ingot(7).fluid().dust()
                .color(0x800d0d)
                .iconSet(SHINY)
                .flags(CORE_METAL, DISABLE_DECOMPOSITION)
                .components(TungstenCarbide, 7, Titanium, 3)
                .cableProperties(GTValues.V[GTValues.UHV], 4, 16)
                .blast(4422)
                .flags(DISABLE_REPLICATION).build();



        Prasiolite = new Material.Builder(++id, gcylId("prasiolite"))
                .gem(2).ore()
                .color(0x9EB749)
                .iconSet(QUARTZ)
                .flags(GENERATE_LENS)
                .components(Silicon, 5, Oxygen, 10, Iron, 1)
                .flags(DISABLE_REPLICATION).build();

        BismuthTellurite = new Material.Builder(++id, gcylId("bismuth_tellurite"))
                .dust(2)
                .color(0x006B38)
                .iconSet(SAND)
                .components(Bismuth, 2, Tellurium, 3)
                .flags(DISABLE_REPLICATION).build();

        CubicZirconia = new Material.Builder(++id, gcylId("cubic_zirconia"))
                .gem(6)
                .color(0xFFDFE2)
                .iconSet(DIAMOND)
                .flags(NO_SMELTING, GENERATE_LENS)
                .components(Zirconium, 1, Oxygen, 2)
                .flags(DISABLE_REPLICATION).build();

        MagnetoResonatic = new Material.Builder(++id, gcylId("magneto_resonatic"))
                .gem(2)
                .color(0xFF97FF)
                .iconSet(MAGNETIC)
                .flags(DISABLE_DECOMPOSITION, FLAMMABLE, HIGH_SIFTER_OUTPUT, NO_SMELTING, GENERATE_LENS)
                .components(Prasiolite, 3, BismuthTellurite, 6, CubicZirconia, 1, SteelMagnetic, 1)
                .flags(DISABLE_REPLICATION).build();

        HDCS = new Material.Builder(++id, gcylId("hdcs"))
                .ingot(5).fluid()
                .color(0x334433)
                .iconSet(SHINY)
                .flags(CORE_METAL, DISABLE_DECOMPOSITION)
                .components(TungstenSteel, 12, HSSS, 9, HSSG, 6, Ruridit, 3, MagnetoResonatic, 2, Plutonium, 1)
                .blast(6100)
                .flags(DISABLE_REPLICATION).build();


        Jasper = new Material.Builder(++id, gcylId("jasper"))
                .gem(2)
                .color(13127760)
                .iconSet(EMERALD)
                .flags(NO_SMELTING, HIGH_SIFTER_OUTPUT)
                .flags(DISABLE_REPLICATION).build();



        SiliconCarbide = new Material.Builder(++id, gcylId("silicon_carbide"))
                .dust()
                .color((Silicon.getMaterialRGB() + Carbon.getMaterialRGB()) / 2)
                .iconSet(SHINY)
                .build()
                .setFormula("SiC", true);

        Quantum = new Material.Builder(++id, gcylId("quantum"))
                .ingot(7).liquid()
                .color(0x0f0f0f)
                .iconSet(SHINY)
                .flags(CORE_METAL, DISABLE_DECOMPOSITION)
                .components(Stellite100, 15, Jasper, 5, Gallium, 5, Americium241, 5, Palladium, 5, Bismuth, 5, Germanium, 5, SiliconCarbide, 5)
                .blast(b->b.temp(11400, BlastProperty.GasTier.HIGHEST).blastStats(VA[UHV], 1200))
                .flags(DISABLE_REPLICATION).build();

        Adamantium = new Material.Builder(++id, gcylId("adamantium"))
                .ingot(7).liquid()
                .color(0x2d365c)
                .iconSet(SHINY)
                .flags(CORE_METAL)
                .element(Ad)
                .blast(10850)
                .flags(DISABLE_REPLICATION).build();

        Cinobite = new Material.Builder(++id, gcylId("cinobite"))
                .ingot(5).liquid()
                .color(0x010101)
                .iconSet(SHINY)
                .flags(CORE_METAL, DISABLE_DECOMPOSITION)
                .components(Zeron100, 8, Naquadria, 4, Gadolinium, 3, Aluminium, 2, Mercury, 1, Tin, 1, Titanium, 6, Osmiridium, 1)
                .cableProperties(GTValues.V[GTValues.UIV], 4, 64)
                .blast(11465)
                .flags(DISABLE_REPLICATION).build();


        Polyimide = new Material.Builder(++id, gcylId("polyimide"))
                .ingot(1).liquid()
                .color(0xFF7F50)
                .iconSet(DULL)
                .flags(DISABLE_DECOMPOSITION, GENERATE_PLATE, FLAMMABLE, NO_SMASHING)
                .components(Carbon, 22, Hydrogen, 12, Nitrogen, 2, Oxygen, 6)
                .flags(DISABLE_REPLICATION).build();

        GermaniumTungstenNitride = new Material.Builder(++id, gcylId("germanium_tungsten_nitride"))
                .ingot(2)
                .color(0x8f8fcf)
                .iconSet(DULL)
                .flags(GENERATE_PLATE)
                .components(Germanium, 3, Tungsten, 3, Nitrogen, 10)
                .blast(5400)
                .flags(DISABLE_REPLICATION).build();

        BariumTitanate = new Material.Builder(++id, gcylId("barium_titanate"))
                .ingot(2)
                .color(0x99FF99)
                .iconSet(SHINY)
                .flags(GENERATE_FOIL, DISABLE_DECOMPOSITION)
                .components(Barium, 1, Titanium, 1, Oxygen, 3)
                .flags(DISABLE_REPLICATION).build();

        PEDOT = new Material.Builder(++id, gcylId("pedot"))
                .ingot(22)
                .color(0x5cef20)
                .iconSet(DULL)
                .flags(GENERATE_FINE_WIRE, DISABLE_DECOMPOSITION)
                .components(Carbon, 6, Hydrogen, 6, Oxygen, 2, Sulfur, 1)
                .flags(DISABLE_REPLICATION).build();

        AluminiumComplex = new Material.Builder(++id, gcylId("aluminium_complex"))
                .dust()
                .color(0x3f5a9f)
                .iconSet(SHINY)
                .build()
                .setFormula("AlC9H7NO", true);

        CopperGalliumIndiumMix = new Material.Builder(++id, gcylId("copper_gallium_indium_mix"))
                .dust()
                .color((Indium.getMaterialRGB() + Copper.getMaterialRGB() + Gallium.getMaterialRGB()) / 3)
                .iconSet(ROUGH)
                .build()
                .setFormula("CuGaIn", true);

        CopperGalliumIndiumSelenide = new Material.Builder(++id, gcylId("copper_gallium_indium_selenide"))
                .dust()
                .color((CopperGalliumIndiumMix.getMaterialRGB() + Selenium.getMaterialRGB()) / 2)
                .iconSet(SHINY)
                .build()
                .setFormula("CuGaInSe2", true);

        BismuthRuthenate = new Material.Builder(++id, gcylId("bismuth_ruthenate"))
                .ingot(2)
                .color(0x94cf5c)
                .iconSet(DULL)
                .flags(GENERATE_PLATE, DISABLE_DECOMPOSITION)
                .components(Bismuth, 2, Ruthenium, 2, Oxygen, 7)
                .flags(DISABLE_REPLICATION).build();

        BismuthIridiate = new Material.Builder(++id, gcylId("bismuth_iridiate"))
                .ingot(22)
                .color(0x478a6b)
                .iconSet(DULL)
                .flags(GENERATE_PLATE, DISABLE_DECOMPOSITION)
                .components(Bismuth, 2, Iridium, 2, Oxygen, 7)
                .flags(DISABLE_REPLICATION).build();

        RutheniumDioxide = new Material.Builder(++id, gcylId("ruthenium_dioxide"))
                .ingot(2)
                .color(RutheniumTetroxide.getMaterialRGB())
                .iconSet(DULL)
                .flags(GENERATE_PLATE, DISABLE_DECOMPOSITION)
                .components(Ruthenium, 1, Oxygen, 2)
                .flags(DISABLE_REPLICATION).build();

        LanthanumCalciumManganate = new Material.Builder(++id, gcylId("lanthanum_gallium_manganate"))
                .dust()
                .color(0x8aa07b)
                .iconSet(SHINY)
                .build()
                .setFormula("LaCaMnO3", true);

        Polystyrene = new Material.Builder(++id, gcylId("polystyrene"))
                .polymer(1).liquid()
                .color(8945785)
                .iconSet(DULL)
                .flags(GENERATE_FOIL, DISABLE_DECOMPOSITION)
                .components(Carbon, 8, Hydrogen, 8)
                .flags(DISABLE_REPLICATION).build();

        Shewanella = new Material.Builder(++id, gcylId("shewanella"))
                .dust()
                .color(0x8752ab)
                .iconSet(METALLIC)
                .build()
                .setFormula("Bacteria", true);

        GreenAlgae = new Material.Builder(++id, gcylId("green_algae"))
                .dust()
                .color(0x228b22)
                .iconSet(METALLIC)
                .build()
                .setFormula("An Algae", true);

        BrownAlgae = new Material.Builder(++id, gcylId("brown_algae"))
                .dust()
                .color(0xa52a2a)
                .iconSet(METALLIC)
                .build()
                .setFormula("An Algae", true);

        RedAlgae = new Material.Builder(++id, gcylId("red_algae"))
                .dust()
                .color(0xf08080)
                .iconSet(METALLIC)
                .build()
                .setFormula("An Algae", true);

        DryRedAlgae = new Material.Builder(++id, gcylId("dry_red_algae"))
                .dust()
                .color(0xff7f50)
                .iconSet(ROUGH)
                .build()
                .setFormula("A Dry Algae", true);

        RedAlgaePowder = new Material.Builder(++id, gcylId("red_algae_powder"))
                .dust()
                .color(0xcc2f2f)
                .iconSet(ROUGH)
                .build()
                .setFormula("A Powdered Algae", true);

        PreFreezeAgar = new Material.Builder(++id, gcylId("pre_freeze_agar"))
                .dust()
                .color(0x132b0d)
                .iconSet(ROUGH)
                .build()
                .setFormula("Warm Agar", true);

        FrozenAgarCrystals = new Material.Builder(++id, gcylId("frozen_agar_crystals"))
                .dust()
                .color(0x68db4b)
                .iconSet(SHINY)
                .build()
                .setFormula("Cold Agar", true);

        WaterAgarMix = new Material.Builder(++id, gcylId("water_agar_mix"))
                .liquid()
                .color(0x48dbbe)
                .build()
                .setFormula("H2O?", true);

        BacterialGrowthMedium = new Material.Builder(++id, gcylId("bacterial_growth_medium"))
                .liquid()
                .color(0x0b2e12)
                .build()
                .setFormula("For Bacteria", true);

        DepletedGrowthMedium = new Material.Builder(++id, gcylId("depleted_growth_medium"))
                .liquid()
                .color(0x071209)
                .build()
                .setFormula("Depleted", true);


        BoricAcid = new Material.Builder(++id, gcylId("boric_acid"))
                .liquid()
                .color(0xD5D2D7)
                .iconSet(FLUID)
                .build()
                .setFormula("H3BO3", true);


        FluoroBoricAcid = new Material.Builder(++id, gcylId("fluoroboric_acid"))
                .liquid()
                .color(0xD5D2D7)
                .iconSet(FLUID)
                .build()
                .setFormula("HBF4", true);

        Acetylene = new Material.Builder(++id, gcylId("acetylene"))
                .liquid()
                .color(0x959c60)
                .iconSet(FLUID)
                .build()
                .setFormula("C2H2", true);

        CoAcABCatalyst = new Material.Builder(++id, gcylId("coacab_catalyst"))
                .dust()
                .color(0x755f30)
                .iconSet(FINE)
                .build()
                .setFormula("Co/AC-AB", true);

        SodiumNitrate = new Material.Builder(++id, gcylId("sodium_nitrate"))
                .dust()
                .color(0x846684)
                .iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .components(Sodium, 1, Nitrogen, 1, Oxygen, 3)
                .flags(DISABLE_REPLICATION).build();

        SodiumNitrateSolution = new Material.Builder(++id, gcylId("sodium_nitrate_solution"))
                .liquid()
                .color(0xA09ED7)
                .iconSet(FLUID)
                .build()
                .setFormula("H20NaNO3", true);

        SodiumNitrite = new Material.Builder(++id, gcylId("sodium_nitrite"))
                .dust()
                .color((Sodium.getMaterialRGB()+Nitrogen.getMaterialRGB())/2)
                .iconSet(ROUGH)
                .build()
                .setFormula("NaNO2",true);

        Aniline = new Material.Builder(++id, gcylId("aniline"))
                .liquid()
                .color(0x4c911d)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H5NH2", true);

        BenzenediazoniumTetrafluoroborate = new Material.Builder(++id, gcylId("benzenediazonium_tetrafluoroborate"))
                .liquid()
                .color(0xD5D2D7)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H5BF4N2",true);

        BoronFluoride = new Material.Builder(++id, gcylId("boron_fluoride"))
                .liquid()
                .color(0xD5D2D7)
                .iconSet(FLUID)
                .build()
                .setFormula("BF3",true);

        FluoroBenzene = new Material.Builder(++id, gcylId("fluoro_benzene"))
                .liquid()
                .color(0xD5D2D7)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H5F", true);

        Fluorotoluene = new Material.Builder(++id, gcylId("fluorotoluene"))
                .liquid()
                        .color(0xE0DA99)
                        .iconSet(FLUID)
                        .build()
                .setFormula("C7H7F", true);

        ZnFeAlClCatalyst = new Material.Builder(++id, gcylId("znfealcl_catalyst"))
                .dust()
                .color((Zinc.getMaterialRGB()+Iron.getMaterialRGB()+Aluminium.getMaterialRGB()+Chlorine.getMaterialRGB())/4)
                .iconSet(METALLIC)
                .build()
                .setFormula("ZnFeAlCl", true);

        Difluorobenzophenone= new Material.Builder(++id, gcylId("difluorobenzophenone"))
                        .dust()
                        .color((FluoroBenzene.getMaterialRGB()+Fluorotoluene.getMaterialRGB())/2)
                        .iconSet(SHINY)
                        .build()
                        .setFormula("(FC6H4)2CO", true);

        Hydroquinone = new Material.Builder(++id, gcylId("hydroquinone"))
                .liquid()
                .color((Oxygen.getMaterialRGB()+Propene.getMaterialRGB())/2)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H4(OH)2", true);

        Resorcinol = new Material.Builder(++id, gcylId("resorcinol"))
                .liquid()
                .color(0xD5DDBE)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H6O2", true);

        MgClBromide = new Material.Builder(++id, gcylId("mgcl_bromide"))
                .dust()
                .color((MagnesiumChloride.getMaterialRGB()+Bromine.getMaterialRGB())/2)
                .iconSet(ROUGH)
                .build()
                .setFormula("MgClBr", true);

        SiliconFluoride = new Material.Builder(++id, gcylId("silicon_fluoride"))
                .liquid()
                .color(0xB2B4B4)
                .iconSet(FLUID)
                .build()
                .setFormula("SiF4",true);

        FluorosilicicAcid = new Material.Builder(++id, gcylId("fluorosilicic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0x2ccf2a)
                .iconSet(FLUID)
                .build()
                .setFormula("H2SiF6",true);

        AmmoniumFluoride = new Material.Builder(++id, gcylId("ammonium_fluoride"))
                .liquid()
                .color(AmmoniumChloride.getMaterialRGB())
                .iconSet(FLUID)
                .build()
                .setFormula("NH4F",true);

        AmmoniumBifluoride = new Material.Builder(++id, gcylId("ammonium_bifluoride"))
                .dust()
                .color(0x20cfcf)
                .iconSet(ROUGH)
                .build()
                .setFormula("NH4HF2", true);

        AmmoniumBifluorideSolution = new Material.Builder(++id, gcylId("ammonium_bifluoride_solution"))
                .liquid()
                .color((Ammonia.getMaterialRGB()+Fluorine.getMaterialRGB())/2)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)NH4FHF",true);

        AmmoniumVanadate = new Material.Builder(++id, gcylId("ammonium_vanadate"))
                .dust()
                .color(0xf2ff1c)
                .iconSet(FINE)
                .build()
                .setFormula("NH4VO3", true);

        SodiumOxide = new Material.Builder(++id, gcylId("sodium_oxide"))
                .dust()
                .color(0x0373fc)
                .iconSet(SHINY)
                .build()
                .setFormula("Na2O",true);

        SodiumVanadate = new Material.Builder(++id, gcylId("sodium_vanadate"))
                .dust()
                .color(0xf2df1d)
                .iconSet(ROUGH)
                .build()
                .setFormula("Na3VO4",true);

        PureSodiumVanadate = new Material.Builder(++id, gcylId("pure_sodium_vanadate"))
                .dust()
                .color(SodiumVanadate.getMaterialRGB())
                .iconSet(SHINY)
                .build()
                .setFormula("Na3VO4",true);

        YttriumOxide = new Material.Builder(++id, gcylId("yttrium_oxide"))
                .dust()
                .color(0xC6EBB3)
                .iconSet(SAND)
                .components(Yttrium, 2, Oxygen, 3)
                .flags(DISABLE_DECOMPOSITION)
                .flags(DISABLE_REPLICATION).build();

        LutetiumOxide = new Material.Builder(++id, gcylId("lutetium_oxide"))
                .dust()
                .color(Lutetium.getMaterialRGB())
                .iconSet(Lutetium.getMaterialIconSet())
                .build()
                .setFormula("Lu2O3",true);

        ThuliumOxide = new Material.Builder(++id, gcylId("thulium_oxide"))
                .dust()
                .color(Thulium.getMaterialRGB())
                .iconSet(Thulium.getMaterialIconSet())
                .build()
                .setFormula("Tm2O3",true);

        LuTmYChlorideSolution = new Material.Builder(++id, gcylId("lutmy_chloride_solution"))
                .liquid()
                .color(0x00f2b2)
                .iconSet(FLUID)
                .build()
                .setFormula("(YCl3)6(LuCl3)2(TmCl3)2(H2O)15",true);

        SodiumMetavanadate = new Material.Builder(++id, gcylId("sodium_metavanadate"))
                .dust()
                .color(SodaAsh.getMaterialRGB())
                .iconSet(FINE)
                .build()
                .setFormula("NaVO3",true);

        Urea = new Material.Builder(++id, gcylId("urea"))
                .dust()
                .color(0x30cf20)
                .iconSet(ROUGH)
                .build()
                .setFormula("CH4N2O",true);

        LuTmYVOPrecipitate = new Material.Builder(++id, gcylId("lutm_yvo_precipitate"))
                .dust()
                .color(0xcf8acf)
                .iconSet(DULL)
                .build()
                .setFormula("Lu/Tm:YVO?",true);

        Ethanol100 = new Material.Builder(++id, gcylId("ethanol_100"))
                .liquid()
                .color(Ethanol.getMaterialRGB())
                .iconSet(FLUID)
                .build()
                .setFormula("C2H5OH",true);

        LuTmYVONanoparticles = new Material.Builder(++id, gcylId("lutm_yvo_nanoparticles"))
                .dust()
                .color(0x206faf)
                .iconSet(SHINY)
                .build()
                .setFormula("Lu/Tm:YVO",true);

        AmmoniumSulfate = new Material.Builder(++id, gcylId("ammonium_sulfate"))
                .liquid()
                .color(0x6464f5)
                .iconSet(FLUID)
                .build()
                .setFormula("(NH4)2SO4",true);

        AmmoniumCarbonate = new Material.Builder(++id, gcylId("ammonium_carbonate"))
                .dust()
                .color(AmmoniumSulfate.getMaterialRGB())
                .iconSet(DULL)
                .build()
                .setFormula("(NH4)2CO3",true);

                SodiumHydride= new Material.Builder(++id, gcylId("sodium_hydride"))
                        .dust()
                        .color(0xcacac8)
                        .iconSet(ROUGH)
                        .build()
                        .setFormula("NaH",true);

        CalciumHydroxide= new Material.Builder(++id, gcylId("calcium_hydroxide"))
                .dust()
                .color(0x5f8764)
                .iconSet(DULL)
                .build()
                .setFormula("Ca(OH)2",true);

                MagnesiumFluoride= new Material.Builder(++id, gcylId("magnesium_fluoride"))
                        .dust()
                        .color(0xcfcfcf)
                        .iconSet(SHINY)
                        .build()
                        .setFormula("MgF2",true);

        TantalumOxide= new Material.Builder(++id, gcylId("tantalum_oxide"))
                .dust()
                .color((Tantalum.getMaterialRGB()+Oxygen.getMaterialRGB())/2)
                .iconSet(SHINY)
                .build()
                .setFormula("Ta2O5",true);

        DielectricMirrorFormationMix = new Material.Builder(++id, gcylId("dielectric_mirror_formation_mix"))
                .liquid()
                        .color(0xff992c)
                        .iconSet(FLUID)
                        .build()
                        .setFormula("MgF2ZnSTa2Ti(C2H6O8)",true);

        NiobiumChloride= new Material.Builder(++id, gcylId("niobium_chloride"))
                .dust()
                .color(Niobium.getMaterialRGB())
                .iconSet(SHINY)
                .build()
                .setFormula("NbCl5",true);

                LithiumAluminiumHydride= new Material.Builder(++id, gcylId("lithium_aluminium_hydride"))
                        .dust()
                        .color(0xc0defc)
                        .iconSet(ROUGH)
                        .build()
                        .setFormula("LiAlH4",true);

        LithiumHydride= new Material.Builder(++id, gcylId("lithium_hydride"))
                .dust()
                .color((Lithium.getMaterialRGB()+Hydrogen.getMaterialRGB())/2)
                .iconSet(ROUGH)
                .build()
                .setFormula("LiH",true);

                AluminiumHydride= new Material.Builder(++id, gcylId("aluminium_hydride"))
                        .dust()
                        .color(0x0b585c)
                        .iconSet(ROUGH)
                        .build()
                        .setFormula("AlH3",true);

        HydrogenPeroxide= new Material.Builder(++id, gcylId("hydrogen_peroxide"))
                .liquid()
                .color(0xD1FFFF)
                .iconSet(FLUID)
                .components(Hydrogen, 2, Oxygen, 2)
                .flags(DISABLE_DECOMPOSITION)
                .flags(DISABLE_REPLICATION).build();

                NiobiumHydroxide= new Material.Builder(++id, gcylId("niobium_hydroxide"))
                        .dust()
                        .color(0x7c7c7c)
                        .iconSet(ROUGH)
                        .build()
                        .setFormula("Nb(OH)5",true);

        OxalicAcid= new Material.Builder(++id, gcylId("oxalic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0x4aaae2)
                .iconSet(FLUID)
                .build()
                .setFormula("HOOCCOOH",true);

                AmmoniumNiobiumOxalateSolution= new Material.Builder(++id, gcylId("ammonium_niobium_oxalate_solution"))
                        .liquid()
                        .color(0x6c6cac)
                        .iconSet(FLUID)
                        .build()
                        .setFormula("(NH4)C10Nb2O20",true);

                AmmoniumNitrate= new Material.Builder(++id, gcylId("ammonium_nitrate"))
                        .liquid()
                        .color(Ammonia.getMaterialRGB())
                        .iconSet(FLUID)
                        .build()
                        .setFormula("NH4NO3",true);

        Ethanolamine= new Material.Builder(++id, gcylId("ethanolamine"))
                .liquid()
                .color(0x6f7d87)
                .iconSet(FLUID)
                .build()
                .setFormula("HOCH2CH2NH2",true);

        Ethylenediamine= new Material.Builder(++id, gcylId("ethylenediamine"))
                .liquid()
                        .color(Ethanolamine.getMaterialRGB())
                        .iconSet(FLUID)
                        .build()
                        .setFormula("C2H4(NH2)2",true);

        Formaldehyde= new Material.Builder(++id, gcylId("formaldehyde"))
                .liquid()
                .color(0x95a13a)
                .iconSet(FLUID)
                .build()
                .setFormula("CH2O",true);

        SodiumCyanide = new Material.Builder(++id, gcylId("sodium_cyanide"))
                .liquid()
                        .color(0x5f7c8c)
                        .iconSet(FLUID)
                        .build()
                        .setFormula("NaCN",true);

        EDTASolution= new Material.Builder(++id, gcylId("edta_solution"))
                .liquid()
                .color(0x0026d9)
                .iconSet(FLUID)
                .build()
                .setFormula("(C10H16N2O8)3(C2H8N2)O2",true);

        EDTA = new Material.Builder(++id, gcylId("edta"))
                .liquid()
                        .color(0x0026d9)
                        .iconSet(FLUID)
                        .build()
                        .setFormula("C10H16N2O8",true);

        Glycine = new Material.Builder(++id, gcylId("glycine"))
                .liquid()
                .color((Ethylenediamine.getMaterialRGB()+Formaldehyde.getMaterialRGB())/2)
                .iconSet(FLUID)
                .build()
                .setFormula("NH2CH2COOH",true);

        CaesiumHydroxide = new Material.Builder(++id, gcylId("caesium_hydroxide"))
                        .dust()
                        .color(Caesium.getMaterialRGB()-10)
                        .iconSet(ROUGH)
                        .build()
                        .setFormula("CsOH",true);

        CaesiumBromideSolution= new Material.Builder(++id, gcylId("caesium_bromide_solution"))
                .liquid()
                .color((Caesium.getMaterialRGB()-10+SaltWater.getMaterialRGB())/2)
                .iconSet(FLUID)
                .build()
                .setFormula("CsBr(H2O)",true);

        Sarcosine = new Material.Builder(++id, gcylId("sarcosine"))
                .dust()
                .color((Glycine.getMaterialRGB() + Oxygen.getMaterialRGB()) / 2)
                .iconSet(SHINY)
                .build()
                .setFormula("C3H7NO2", true);

        PraseodymiumOxide = new Material.Builder(++id, gcylId("praseodymium_oxide"))
                .dust()
                .color(Praseodymium.getMaterialRGB())
                .iconSet(Praseodymium.getMaterialIconSet())
                .build()
                .setFormula("Pr2O3",true);

                HolmiumOxide = new Material.Builder(++id, gcylId("holmium_oxide"))
                        .dust()
                        .color(Holmium.getMaterialRGB())
                        .iconSet(Holmium.getMaterialIconSet())
                        .build()
                        .setFormula("Ho2O3",true);

        NeodymiumOxide = new Material.Builder(++id, gcylId("neodymium_oxide"))
                        .dust()
                        .color(Neodymium.getMaterialRGB())
                        .iconSet(Neodymium.getMaterialIconSet())
                        .build()
                        .setFormula("Nd2O3",true);

                PrYHoNitrateSolution = new Material.Builder(++id, gcylId("pryho_nitrate_solution"))
                        .liquid()
                        .color(0x00f2b2)
                        .iconSet(FLUID)
                        .build()
                        .setFormula("(Y(NO3)3)6(Pr(NO3)3)2(Nd(NO3)3)2(H2O)15",true);

        CetaneTrimethylAmmoniumBromide = new Material.Builder(++id, gcylId("cetane_trimethyl_ammonium_bromide"))
                .liquid()
                .color(0xb9c1c9)
                .iconSet(FLUID)
                .build()
                .setFormula("C19H42BrN",true);



                PrHoYLFNanoparticles= new Material.Builder(++id, gcylId("prho_ylf_nanoparticles"))
                        .dust()
                        .color(0xcf8acf)
                        .iconSet(SHINY)
                        .build()
                        .setFormula("Pr/Ho:YLF",true);

        BerylliumFluoride= new Material.Builder(++id, gcylId("beryllium_fluoride"))
                .ingot(2)
                .color(0x757575)
                .iconSet(SHINY)
                .components(Beryllium, 1, Fluorine, 2)
                .flags(DISABLE_REPLICATION).build();

                ChlorinatedSolvents = new Material.Builder(++id, gcylId("chlorinated_solvents"))
                        .liquid()
                        .color(0x40804c)
                        .iconSet(FLUID)
                        .build()
                        .setFormula("(CH4)2Cl5",true);

        Dichloromethane= new Material.Builder(++id, gcylId("dichloromethane"))
                .liquid()
                .color(Chloromethane.getMaterialRGB()-10)
                .iconSet(FLUID)
                .build()
                .setFormula("CH2Cl2",true);

                CarbonTetrachloride = new Material.Builder(++id, gcylId("carbon_tetrachloride"))
                        .liquid()
                        .color(0x2d8020)
                        .iconSet(FLUID)
                        .build()
                        .setFormula("CCl4",true);

        Butanol= new Material.Builder(++id, gcylId("butanol"))
                .liquid()
                .color((FermentedBiomass.getMaterialRGB()+20))
                .iconSet(FLUID)
                .build()
                .setFormula("C4H9OH",true);

                ButanolGas = new Material.Builder(++id, gcylId("butanol_gas"))
                        .gas()
                        .color(Butanol.getMaterialRGB()+20)
                        .iconSet(FLUID)
                        .build()
                        .setFormula("C4H9OH",true);

        Tributylamine = new Material.Builder(++id, gcylId("tributylamine"))
                .liquid()
                .color(0x801a80)
                .iconSet(FLUID)
                .build()
                .setFormula("(C4H9)3N",true);

                Alumina= new Material.Builder(++id, gcylId("alumina"))
                        .dust()
                        .color(0x0b585c)
                        .iconSet(ROUGH)
                        .build()
                        .setFormula("Al2O3",true);

        AluminiumNitrate= new Material.Builder(++id, gcylId("aluminium_nitrate"))
                .dust()
                .color(Alumina.getMaterialRGB())
                .iconSet(SHINY)
                .build()
                .setFormula("Al(NO3)3",true);

                CrudeAluminaSolution= new Material.Builder(++id, gcylId("crude_alumina_solution"))
                        .liquid()
                        .color(Aluminium.getMaterialRGB()-30)
                        .iconSet(FLUID)
                        .build()
                        .setFormula("Al(NO3)3)2(CH2Cl2)(C12H27N)",true);

        HydrogenCyanide= new Material.Builder(++id, gcylId("hydrogen_cyanide"))
                .liquid()
                .color(0xb6d1ae)
                .iconSet(FLUID)
                .build()
                .setFormula("HCN",true);

        PotassiumManganate= new Material.Builder(++id, gcylId("potassium_manganate"))
                .dust()
                .color(0xaf20af)
                .iconSet(DULL)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("K2MnO4",true);

                PotassiumPermanganate= new Material.Builder(++id, gcylId("potassium_permanganate"))
                        .dust()
                        .color(PotassiumManganate.getMaterialRGB()-15)
                        .iconSet(ROUGH)
                        .build()
                        .setFormula("KMnO4",true);

        ManganeseSulfate= new Material.Builder(++id, gcylId("manganese_sulfate"))
                .dust()
                .color((Manganese.getMaterialRGB()+Sulfur.getMaterialRGB())/2)
                .iconSet(ROUGH)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("MnSO4",true);

                PotassiumSulfate= new Material.Builder(++id, gcylId("potassium_sulfate"))
                        .dust()
                        .color((Potassium.getMaterialRGB()+Sulfur.getMaterialRGB())/2)
                        .iconSet(FINE)
                        .flags(DISABLE_REPLICATION)
                        .build()
                        .setFormula("K2SO4",true);

        AmmoniumCyanate= new Material.Builder(++id, gcylId("ammonium_cyanate"))
                .liquid()
                .color(0x3a5dcf)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("NH4CNO",true);

        PotassiumHydroxide= new Material.Builder(++id, gcylId("potassium_hydroxide"))
                .liquid()
                .color((Potassium.getMaterialRGB()+Hydrogen.getMaterialRGB()+Oxygen.getMaterialRGB())/3)
                .iconSet(FLUID)
                .build()
                .setFormula("KOH",true);

        LithiumFluoride = new Material.Builder(++id, gcylId("lithium_fluoride"))
                .ingot()
                .color(0x757575)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .components(Lithium, 1, Fluorine, 1)
                .build();

        AluminaSolution = new Material.Builder(++id, gcylId("alumina_solution"))
                .liquid()
                .color((Aluminium.getMaterialRGB()-15))
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("(Al2O3)(CH2Cl2)(C12H27N)2",true);

        PreciousMetal= new Material.Builder(++id, gcylId("precious_metal"))
                .ingot(2).ore()
                .color(0xB99023)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("Au?", true);

                GoldAlloy= new Material.Builder(++id, gcylId("gold_alloy"))
                        .ingot(2)
                        .color(0xBBA52B)
                        .iconSet(SHINY)
                        .flags(DISABLE_REPLICATION)
                        .build()
                        .setFormula("Cu3Au?",true);

        GoldLeach= new Material.Builder(++id, gcylId("gold_leach"))
                .dust()
                .color(0xBBA52B)
                .iconSet(ROUGH)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("Cu3Au?",true);

                CopperLeach= new Material.Builder(++id, gcylId("copper_leach"))
                        .dust()
                        .color(0x765A30)
                        .iconSet(SHINY)
                        .flags(DISABLE_REPLICATION)
                        .build()
                        .setFormula("Cu3?",true);

        ChloroauricAcid = new Material.Builder(++id, gcylId("chloroauric_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0xDFD11F)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("HAuCl?", true);

        PotassiumMetabisulfite = new Material.Builder(++id, gcylId("potassium_metabisulfite"))
                .dust()
                .color(0xFFFFFF)
                .iconSet(DULL)
                .flags(DISABLE_REPLICATION)
                .components(Potassium, 2, Sulfur, 2, Oxygen, 5)
                .build();

        OrthoXylene = new Material.Builder(++id, gcylId("ortho_xylene"))
                .liquid()
                .color(0xB9575E)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("C6H4(CH3)2", true);

        Durene = new Material.Builder(++id, gcylId("durene"))
                .dust()
                .color(0xA39C95)
                .iconSet(ROUGH)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("C6H2(CH3)4", true);

        PyromelliticDianhydride = new Material.Builder(++id, gcylId("pyromellitic_dianhydride"))
                .dust()
                .color(0xF0EAD6)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("C6H2(C2O3)2", true);

        Oxydianiline = new Material.Builder(++id, gcylId("oxydianiline"))
                .liquid()
                .color(0xF0E130)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("C12H12N2O", true);

        PolyamicAcid = new Material.Builder(++id, gcylId("polyamic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0xFFAE42)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("C22H14N2O7", true);

        Hexafluoropropylene = new Material.Builder(++id, gcylId("hexafluoropropylene"))
                .liquid()
                .color(0x111111)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("C3F6", true);

        FluorinatedEthylenePropylene = new Material.Builder(++id, gcylId("fluorinated_ethylene_propylene"))
                .ingot().liquid()
                .color(0xC8C8C8)
                .iconSet(DULL)
                .flags(DISABLE_REPLICATION)
                .flags(GENERATE_PLATE, FLAMMABLE, NO_SMASHING, DISABLE_DECOMPOSITION, NO_ALLOY_BLAST_RECIPES)
                .components(Carbon, 5, Fluorine, 10)
                .build();

        Taranium = new Material.Builder(++id, gcylId("taranium"))
                .ingot(7).liquid()
                .color(0x0c0c0d)
                .iconSet(SHINY)
                .flags(CORE_METAL)
                .element(Tn)
                .blast(10000)
                .build();

        NaquadriaticTaranium = new Material.Builder(++id, gcylId("naquadriatic_taranium"))
                .ingot()
                .color((Naquadria.getMaterialRGB() + Taranium.getMaterialRGB()) / 2)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .flags(STD_METAL, GENERATE_LONG_ROD)
                .components(Naquadria, 1, Taranium, 1)
                .cableProperties(GTValues.V[GTValues.UXV], 2, 32)
                .blast(11200)
                .build();

        BlackTitanium = new Material.Builder(++id, gcylId("black_titanium"))
                .ingot().liquid()
                .color(0x6C003B)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .flags(CORE_METAL, DISABLE_DECOMPOSITION)
                .components(Titanium, 26, Lanthanum, 6, Tungsten, 4, Cobalt, 3, Manganese, 2, Phosphorus, 2, Palladium, 2, Niobium, 1, Argon, 5)
                .cableProperties(GTValues.V[GTValues.UIV], 2, 32)
                .blast(11500)
                .build();

        FullerenePolymerMatrix = new Material.Builder(++id, gcylId("fullerene_polymer_matrix"))
                .polymer(2).liquid()
                .color(0x403e37)
                .iconSet(DULL)
                .flags(DISABLE_REPLICATION)
                .flags(EXCLUDE_BLOCK_CRAFTING_RECIPES, GENERATE_FOIL, NO_SMASHING, DISABLE_DECOMPOSITION)
                .components(Palladium, 1, Iron, 1, Carbon, 153, Hydrogen, 36, Nitrogen, 1, Oxygen, 2)
                .build();

        Zylon = new Material.Builder(++id, gcylId("zylon"))
                .polymer(2).liquid()
                .color(0xFFE000)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .flags(EXCLUDE_BLOCK_CRAFTING_RECIPES, GENERATE_FOIL, NO_SMASHING, DISABLE_DECOMPOSITION)
                .components(Carbon, 14, Hydrogen, 6, Nitrogen, 2, Oxygen, 2)
                .build();

        SupercriticalSteam = new Material.Builder(++id, gcylId("supercritical_steam"))
                .liquid()
                .color(Steam.getMaterialRGB())
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("H2O", true);

        SodiumPotassiumAlloy = new Material.Builder(++id, gcylId("sodium_potassium_alloy"))
                .dust(2).liquid()
                .color(0x252525)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .components(Sodium, 7, Potassium, 3)
                .build();

        SupercriticalSodiumPotassiumAlloy = new Material.Builder(++id, gcylId("supercritical_sodium_potassium_alloy"))
                .liquid()
                .color(SodiumPotassiumAlloy.getMaterialRGB())
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .components(SodiumPotassiumAlloy, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build()
                .setFormula("Na7K3", true);

        FLiNaK = new Material.Builder(++id, gcylId("flinak"))
                .dust(2).liquid()
                .color(0x252525)
                .iconSet(DULL)
                .flags(DISABLE_REPLICATION)
                .components(Fluorine, 3, Lithium, 1, Sodium, 1, Potassium, 1)
                .build();

        SupercriticalFLiNaK = new Material.Builder(++id, gcylId("supercritical_flinak"))
                .liquid()
                .color(FLiNaK.getMaterialRGB())
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .components(FLiNaK, 1)
                .flags(DISABLE_DECOMPOSITION)
                .build();

        FLiBe = new Material.Builder(++id, gcylId("flibe"))
                .dust(2).liquid()
                .color(0x252525)
                .iconSet(DULL)
                .flags(DISABLE_REPLICATION)
                .components(Fluorine, 3, Lithium, 1, Beryllium, 1)
                .build();

        SupercriticalFLiBe = new Material.Builder(++id, gcylId("supercritical_flibe"))
                .liquid()
                .color(FLiBe.getMaterialRGB())
                .components(FLiBe, 1)
                .flags(DISABLE_REPLICATION)
                .flags(DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .build();

        LeadBismuthEutectic = new Material.Builder(++id, gcylId("lead_bismuth_eutatic"))
                .ingot().liquid()
                .color(0x757575)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .components(Lead, 3, Bismuth, 7)
                .build();

        SupercriticalLeadBismuthEutectic = new Material.Builder(++id, gcylId("supercritical_lead_bismuth_eutatic"))
                .liquid()
                .color(LeadBismuthEutectic.getMaterialRGB())
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .flags(DISABLE_DECOMPOSITION)
                .components(LeadBismuthEutectic, 1)
                .build();

        HastelloyN = new Material.Builder(++id, gcylId("hastelloy_n"))
                .ingot(6).liquid()
                .color(0xDDDDDD)
                .iconSet(METALLIC)
                .flags(DISABLE_REPLICATION)
                .flags(EXT2_METAL, GENERATE_FRAME, GENERATE_DENSE, GENERATE_GEAR)
                .components(Yttrium, 2, Molybdenum, 4, Chrome, 2, Titanium, 2, Nickel, 15)
                .blast(4350)
                .build();

        Lafium = new Material.Builder(++id, gcylId("lafium"))
                .ingot(7)
                .color(0x0d0d60)
                .iconSet(SHINY)
                .flags(CORE_METAL, DISABLE_DECOMPOSITION)
                .fluidPipeProperties(23000, 2000, true, true, true, true)
                .components(HastelloyN, 8, Naquadah, 4, Samarium, 2, Tungsten, 4, Argon, 2, Aluminium, 6, Nickel, 8, Carbon, 2)
                .blast(9865)
                .build();

        HeavyQuarkDegenerateMatter = new Material.Builder(++id, gcylId("heavy_quark_degenerate_matter"))
                .ingot(6)
                .color(0x5dbd3a)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .flags(CORE_METAL)
                .blast(13000)
                .build()
                .setFormula("?");

        MetastableFlerovium = new Material.Builder(++id, gcylId("metastable_flerovium"))
                .ingot(6)
                .color(0x521973)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .flags(CORE_METAL)
                .element(Fl)
                .blast(11240)
                .build();

        QCDMatter = new Material.Builder(++id, gcylId("qcd_confined_matter"))
                .ingot(7).liquid()
                .color(0xeb9e3f)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .flags(GENERATE_PLATE, DISABLE_REPLICATION, NO_WORKING, NO_SMELTING, NO_SMASHING, GENERATE_FRAME, GENERATE_ROD)
                .components()
                .blast(13100)
                .build()
                .setFormula("?");

        TitanSteel = new Material.Builder(++id, gcylId("titan_steel"))
                .ingot(7).liquid()
                .color(0xAA0d0d)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .flags(CORE_METAL, DISABLE_DECOMPOSITION)
                .components(TungstenTitaniumCarbide, 3, Jasper, 3)
                .cableProperties(GTValues.V[GTValues.UEV], 2, 16)
                .blast(9200)
                .build();

        MetastableHassium = new Material.Builder(++id, gcylId("metastable_hassium"))
                .ingot(6)
                .color(0x2d3a9d)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .flags(CORE_METAL)
                .element(Hs)
                .blast(11240, BlastProperty.GasTier.HIGHEST)
                .build();

        Vibranium = new Material.Builder(++id, gcylId("vibranium"))
                .ingot(7).liquid()
                .color(0x828aad)
                .iconSet(SHINY)
                .flags(CORE_METAL)
                .element(Vb)
                .blast(11220)
                .build();

        TriniumTitanium = new Material.Builder(++id, gcylId("trinium_titanium"))
                .ingot(7).liquid()
                .color(0x9986a3)
                .iconSet(SHINY)
                .flags(CORE_METAL)
                .flags(DISABLE_REPLICATION)
                .components(Trinium, 2, Titanium, 1)
                .blast(11000)
                .build();

        TantalumHafniumSeaborgiumCarbide = new Material.Builder(++id, gcylId("tantalum_hafnium_seaborgium_carbide"))
                .ingot(6)
                .color(0x2c2c2c)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .flags(GENERATE_PLATE, EXCLUDE_BLOCK_CRAFTING_RECIPES, DISABLE_DECOMPOSITION)
                .components(Tantalum, 12, Hafnium, 3, Seaborgium, 1, Carbon, 16)
                .fluidPipeProperties(500000, 2400, true)
                .blast(5200)
                .build();

        Nitinol60 = new Material.Builder(++id, gcylId("nitinol_a"))
                .ingot(4)
                .color(0xCCB0EC)
                .iconSet(METALLIC)
                .flags(DISABLE_REPLICATION)
                .flags(EXT2_METAL, GENERATE_FRAME)
                .components(Nickel, 2, Titanium, 3)
                .blast(Titanium.getBlastTemperature())
                .build();

        ActiniumSuperhydride = new Material.Builder(++id, gcylId("actinium_superhydride"))
                .dust()
                .color(Actinium.getMaterialRGB() * 9 / 8)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("AcH12", true);

        BETSPerrhenate = new Material.Builder(++id, gcylId("bets_perrhenate"))
                .dust()
                .color(0x7ada00)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("ReC10H8S4Se4O4", true);

        BorocarbideDust = new Material.Builder(++id, gcylId("borocarbide_dust"))
                .dust()
                .color(0x9a9a2a)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("B4C7Fr4At6Ho2Th2Fl2Cn2", true);

        FullereneSuperconductiveDust = new Material.Builder(++id, gcylId("fullerene_superconductor_dust"))
                .dust()
                .color(0x99cc00)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("LaCsRb(C60)2", true);

        MetastableOganesson = new Material.Builder(++id, gcylId("metastable_oganesson"))
                .ingot(7)
                .color(0xE61C24)
                .iconSet(SHINY)
                .flags(CORE_METAL)
                .element(Og)
                .blast(10380)
                .build();

        ProtoAdamantium = new Material.Builder(++id, gcylId("proto_adamantium"))
                .ingot(7)
                .color(0x4662d4)
                .iconSet(SHINY)
                .flags(CORE_METAL)
                .flags(DISABLE_REPLICATION)
                .components(Adamantium, 3, Promethium, 2)
                .blast(11244)
                .build();

        SuperheavyHAlloy = new Material.Builder(++id, gcylId("superheavy_h_alloy"))
                .ingot(6).liquid()
                .color(0xE84B36)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .flags(GENERATE_PLATE, DISABLE_DECOMPOSITION)
                .components(Copernicium, 1, Nihonium, 1, MetastableFlerovium, 1, Moscovium, 1, Livermorium, 1, Tennessine, 1, MetastableOganesson, 1)
                .blast(10600)
                .build();

        RheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate = new Material.Builder(++id, gcylId("rhenium_hassium_thallium_isophtaloylbisdiethylthiourea"))
                .dust()
                .color(0xa26a8b)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("ReHsTlC60PN12H84S6O12F6", true);

        Legendarium = new Material.Builder(++id, gcylId("legendarium"))
                .dust()
                .color(0xffffff)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("NqNq+*Nq*DrTrKeTnAdVb", true);

        LanthanumFullereneMix = new Material.Builder(++id, gcylId("lanthanum_fullerene_mix"))
                .dust()
                .color(0xdfcafa)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("(C60)2La2?", true);

        LanthanumFullereneNanotubes = new Material.Builder(++id, gcylId("lanthanum_fullerene_nanotubes"))
                .dust()
                .color(LanthanumFullereneMix.getMaterialRGB() * 3 / 5)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("La2(C60)2CNT", true);

        SuperheavyLAlloy = new Material.Builder(++id, gcylId("superheavy_l_alloy"))
                .ingot(6).liquid()
                .color(0x2B45DF)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .flags(GENERATE_PLATE, DISABLE_DECOMPOSITION)
                .components(Rutherfordium, 1, Dubnium, 1, Seaborgium, 1, Bohrium, 1, MetastableHassium, 1, Meitnerium, 1, Roentgenium, 1)
                .blast(10600)
                .build();

        HexanitroHexaazaisowurtzitane = new Material.Builder(++id, gcylId("hexanitrohexaazaisowurtzitane"))
                .dust()
                .color(0x414a4f)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("C6H6N12O12", true);

        ElectronDegenerateRheniumPlasma = new Material.Builder(++id, gcylId("degenerate_rhenium_plasma"))
                .plasma(new FluidBuilder().temperature(100000000))//TODO fix temperature
                .flags(DISABLE_REPLICATION, DISABLE_DECONSTRUCTION)
                .color(0x6666FF)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("Rh", true);

        SuperheavyMix = new Material.Builder(++id, gcylId("superheavy_mix"))
                .liquid()
                .color(0x403737)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("SgBhRfDb", true);

        NeutronPlasma = new Material.Builder(++id, gcylId("neutron_plasma"))
                .plasma(new FluidBuilder().temperature(1000000000))//TODO fix temperature
                .flags(DISABLE_REPLICATION, DISABLE_DECONSTRUCTION)
                .color(0xf0e9e9)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("n", true);

        FreeAlphaGas = new Material.Builder(++id, gcylId("free_alpha_gas"))
                .liquid()
                .color(0xe0d407)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("a", true);

        FreeElectronGas = new Material.Builder(++id, gcylId("free_electron_gas"))
                .liquid()
                .color(0x044c4c)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("e-", true);


        PotassiumCarbonate = new Material.Builder(++id, gcylId("potassium_carbonate"))
                .dust()
                .color((Potassium.getMaterialRGB() + Carbon.getMaterialRGB() + Oxygen.getMaterialRGB()) / 3)
                .iconSet(FINE)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("K2CO3", true);

        PotassiumBisulfite = new Material.Builder(++id, gcylId("potassium_bisulfite"))
                .dust()
                .color(0xF0EAD6)
                .iconSet(DULL)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("KHSO3", true);

        PotassiumNitrite = new Material.Builder(++id, gcylId("potassium_nitrite"))
                .dust()
                .color(0xF0EAD6)
                .iconSet(DULL)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("KNO2", true);

        NitrousAcid = new Material.Builder(++id, gcylId("nitrous_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0x1e73b0)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("HNO2", true);


        AceticAnhydride = new Material.Builder(++id, gcylId("acetic_anhydride"))
                .liquid()
                .color(0xD5DDDF)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("(CH3CO)2O", true);

        SodiumAcetate = new Material.Builder(++id, gcylId("sodium_acetate"))
                .liquid()
                .color((Sodium.getMaterialRGB() + AceticAnhydride.getMaterialRGB()) / 2)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("C2H3NaO2", true);

        PotassiumHydroxylaminedisulfonate = new Material.Builder(++id, gcylId("potassium_hydroxylaminedisulfonate"))
                .dust()
                .color((0xF0EAD6 + NitrousAcid.getMaterialRGB()) / 2)
                .iconSet(DULL)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("KHSO3", true);

        HydroxylammoniumSulfate = new Material.Builder(++id, gcylId("hydroxylammonium_sulfate"))
                .dust()
                .color(0xF0EAD6)
                .iconSet(DULL)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("(NH3OH)2SO4", true);

        BariumChloride = new Material.Builder(++id, gcylId("barium_chloride"))
                .dust()
                .color((Barium.getMaterialRGB() + Chlorine.getMaterialRGB()) / 2)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("BaCl2", true);


        BariumSulfateSolution = new Material.Builder(++id, gcylId("barium_sulfate_solution"))
                .liquid()
                .color(Barite.getMaterialRGB())
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("(H2O)BaSO4", true);

        HydroxylamineHydrochloride = new Material.Builder(++id, gcylId("hydroxylamine_hydrochloride"))
                .liquid()
                .color(((Barium.getMaterialRGB() + Chlorine.getMaterialRGB()) / 2 + 0xF0EAD6) / 2)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("HONH2HCl", true);

        MaleicAnhydride = new Material.Builder(++id, gcylId("maleic_anhydride"))
                .liquid()
                .color(0x3c20ad)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("C4H2O3", true);

        SuccinicAcid = new Material.Builder(++id, gcylId("succinic_acid"))
                .dust()
                .color((MaleicAnhydride.getMaterialRGB() + Water.getMaterialRGB() + Hydrogen.getMaterialRGB()) / 3)
                .iconSet(ROUGH)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("C4H6O4", true);

        SuccinicAnhydride = new Material.Builder(++id, gcylId("succinic_anhydride"))
                .dust()
                .color((SuccinicAcid.getMaterialRGB() + AceticAnhydride.getMaterialRGB()) / 2)
                .iconSet(DULL)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("(CH2CO)2O", true);

        Tetrahydrofuran = new Material.Builder(++id, gcylId("tetrahydrofuran"))
                .liquid()
                .color(0xb7ebcd)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("(CH2)4O", true);

        NHydroxysuccinimide = new Material.Builder(++id, gcylId("n-hydroxysuccinimide"))
                .dust()
                .color(0xdbcae3)
                .iconSet(DULL)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("(CH2CO)2NOH", true);

        Triethylamine = new Material.Builder(++id, gcylId("triethylamine"))
                .liquid()
                .color(Ethylenediamine.getMaterialRGB())
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("N(CH2CH3)3", true);

        SuccinimidylAcetate = new Material.Builder(++id, gcylId("succinimidyl_acetate"))
                .dust()
                .color(0xbd93a6)
                .iconSet(DULL)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("C6H7NO4", true);

        SeleniumOxide = new Material.Builder(++id, gcylId("selenium_oxide"))
                .dust()
                .color(0xFFFF66)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("SeO2", true);

        SelenousAcid = new Material.Builder(++id, gcylId("selenous_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color((0xFFFF66 + Water.getMaterialRGB()) / 2)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("H2SeO3", true);

        AmmoniumAcetate = new Material.Builder(++id, gcylId("ammonium_acetate"))
                .dust()
                .color(0xb6dee0)
                .iconSet(DULL)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("NH4CH3CO2", true);

        Acetamide = new Material.Builder(++id, gcylId("acetamide"))
                .dust()
                .color(0xa6bebf)
                .iconSet(DULL)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("CH3CONH2", true);

        Acetonitrile = new Material.Builder(++id, gcylId("acetonitrile"))
                .dust()
                .color(0xa2afb0)
                .iconSet(DULL)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("CH3CN", true);

        BenzylChloride = new Material.Builder(++id, gcylId("benzyl_chloride"))
                .liquid()
                .color(0xaef7fc)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("C7H7Cl", true);

        Hexamethylenetetramine = new Material.Builder(++id, gcylId("hexamethylenetetramine"))
                .dust()
                .color(0x7e8d94)
                .iconSet(DULL)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("(CH2)6N4", true);

        Benzylamine = new Material.Builder(++id, gcylId("benzylamine"))
                .liquid()
                .color(0x5c8082)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("C7H9N", true);

        Glyoxal = new Material.Builder(++id, gcylId("glyoxal"))
                .liquid()
                .color(0xf2f068)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("C2H2O2", true);

        Hexabenzylhexaazaisowurtzitane = new Material.Builder(++id, gcylId("hexabenzylhexaazaisowurtzitane"))
                .dust()
                .color(0x624573)
                .iconSet(DULL)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("C48N6H48", true);

        PalladiumChloride = new Material.Builder(++id, gcylId("palladium_chloride"))
                .dust()
                .color(0xb9c0c7)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("PdCl2", true);

        PdCCatalyst = new Material.Builder(++id, gcylId("pdc_catalyst"))
                .dust()
                .color((Palladium.getMaterialRGB() + Carbon.getMaterialRGB()) / 2)
                .iconSet(DULL)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("PdC", true);


        HydrobromicAcid = new Material.Builder(++id, gcylId("hydrobromic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0xBC6C53)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("HBr", true);

        Dimethylformamide = new Material.Builder(++id, gcylId("dimethylformamide"))
                .liquid()
                .color(0x42bdff)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("(CH3)2NCH", true);

        DibenzylTetraacetylhexaazaisowurtzitane = new Material.Builder(++id, gcylId("dibenzyltetraacetylhexaazaisowurtzitane"))
                .dust()
                .color(0xb3c98b)
                .iconSet(DULL)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("C28N6H32O4", true);

        NitroniumTetrafluoroborate = new Material.Builder(++id, gcylId("nitronium_tetrafluoroborate"))
                .dust()
                .color(0x686c6e)
                .iconSet(DULL)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("NO2BF4", true);

        NitrosoniumTetrafluoroborate = new Material.Builder(++id, gcylId("nitrosonium_tetrafluoroborate"))
                .dust()
                .color(0x7e8d94)
                .iconSet(DULL)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("NOBF4", true);

        Tetraacetyldinitrosohexaazaisowurtzitane = new Material.Builder(++id, gcylId("tetraacetyldinitrosohexaazaisowurtzitane"))
                .dust()
                .color((DibenzylTetraacetylhexaazaisowurtzitane.getMaterialRGB() + Hexabenzylhexaazaisowurtzitane.getMaterialRGB()) / 2)
                .iconSet(DULL)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("C14N8H18O6", true);

        SilverOxide = new Material.Builder(++id, gcylId("silver_oxide"))
                .dust()
                .color(0x4D4D4D)
                .iconSet(DULL)
                .flags(DISABLE_REPLICATION)
                .components(Silver, 2, Oxygen, 1)
                .build();

        Silvertetrafluoroborate = new Material.Builder(++id, gcylId("silvertetrafluoroborate"))
                .liquid()
                .color((SilverOxide.getMaterialRGB() + BoronFluoride.getMaterialRGB()) / 2)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("AgBF4", true);

        TetrafluoroboricAcid = new Material.Builder(++id, gcylId("tetrafluoroboric_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(Silvertetrafluoroborate.getMaterialRGB())
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("HBF4", true);

        Benzaldehyde = new Material.Builder(++id, gcylId("benzaldehyde"))
                .liquid()
                .color(0xb26f22)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("C7H6O", true);

        CrudeHexanitroHexaazaisowurtzitane = new Material.Builder(++id, gcylId("crude_hexanitrohexaazaisowurtzitane"))
                .dust()
                .color(HexanitroHexaazaisowurtzitane.getMaterialRGB() * 5 / 7)
                .iconSet(DULL)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("C6H6N12O12", true);

        SilicaGel = new Material.Builder(++id, gcylId("silica_gel"))
                .dust()
                .color(0x61daff)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("SiO2", true);

        BrevibacteriumFlavium = new Material.Builder(++id, gcylId("brevibacterium_flavium"))
                .dust()
                .color(0x2c4d24)
                .iconSet(ROUGH)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("Bacteria", true);

        Succinimide = new Material.Builder(++id, gcylId("succinimide"))
                .dust()
                .color((SuccinicAcid.getMaterialRGB() + Ammonia.getMaterialRGB()) / 2)
                .iconSet(METALLIC)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("C4H4BrNO2", true);

        Acetaldehyde = new Material.Builder(++id, gcylId("acetaldehyde"))
                .liquid()
                .color(0xFF9933)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("C2H4O", true);

        Trichloroferane = new Material.Builder(++id, gcylId("trichloroferane"))
                .liquid()
                .color(0x521973)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("FlCl3", true);

        SeaborgiumDopedNanotubes = new Material.Builder(++id, gcylId("seaborgium_doped_nanotubes"))
                .liquid()
                .color(0x2c2c8c)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("SgCNT", true);

        PrHoYLF = new Material.Builder(++id, gcylId("prho_ylf"))
                .dust(6).liquid()
                .color(0x6f20af)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("PrHoYLF", true);

        LuTmYVO = new Material.Builder(++id, gcylId("lutm_yvo"))
                .dust(6).liquid()
                .color(0x206faf)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("LuTmYVO", true);

        NeodymiumDopedYttrium = new Material.Builder(++id, gcylId("neodymium_doped_yttrium"))
                .dust()
                .color(YttriumOxide.getMaterialRGB())
                .iconSet(DULL)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("Nd:Y?", true);

        UnprocessedNdYAGSolution = new Material.Builder(++id, gcylId("unprocessed_ndyag_solution"))
                .liquid()
                .color(0x6f20af)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("Nd:YAG", true);

        UnprocessedNdYAGDust = new Material.Builder(++id, gcylId("unprocessed_ndyag_dust"))
                .dust()
                .color(0x6f20af)
                .iconSet(DULL)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("Nd:YAG?", true);

        NdYAGNanoparticles = new Material.Builder(++id, gcylId("nd_yag_nanoparticles"))
                .dust()
                .color(0x6f20af)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("Nd:YAG", true);

        NdYAG = new Material.Builder(++id, gcylId("nd_yag"))
                .dust(6).liquid()
                .color(0xcf8acf)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("Nd:YAG", true);

        Pyrotheum = new Material.Builder(++id, gcylId("pyrotheum"))
                .dust().liquid(new FluidBuilder().temperature(5000))
                .color(0xFF9A3C)
                .iconSet(SAND)
                .flags(DISABLE_DECOMPOSITION, EXCLUDE_BLOCK_CRAFTING_RECIPES,DISABLE_REPLICATION)
                .components(Redstone, 1, Blaze, 2, Sulfur, 1)
                .build();

        Blizz = new Material.Builder(++id, gcylId("blizz"))
                .dust()
                .color(0x01F3F6)
                .iconSet(SAND)
                .flags(NO_SMELTING, MORTAR_GRINDABLE)
                .components(Redstone, 1, Water, 1)
                .build();

        Cryotheum = new Material.Builder(++id, gcylId("cryotheum"))
                .dust().liquid()
                .color(0x01F3F6)
                .iconSet(DULL)
                .flags(DISABLE_DECOMPOSITION, EXCLUDE_BLOCK_CRAFTING_RECIPES,DISABLE_REPLICATION)
                .components(Redstone, 1, Blizz, 2, Water, 1)
                .build();

        DrillingMud = new Material.Builder(++id, gcylId("drilling_mud"))
                .liquid()
                .color(0x996600)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("For the Void Miner", true);

        UsedDrillingMud = new Material.Builder(++id, gcylId("used_drilling_mud"))
                .liquid()
                .color(0x998833)
                .iconSet(FLUID)

                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("Used Mud", true);

        HastelloyX78 = new Material.Builder(++id, gcylId("hastelloyx_78"))
                .ingot().fluid()
                .color(0x6ba3e3)
                .iconSet(SHINY)
                .flags(CORE_METAL,DISABLE_REPLICATION)
                .components(NaquadahAlloy, 10, Rhenium, 5, Naquadria, 4, Gadolinium, 3, Strontium, 2, Polonium, 3, Rutherfordium, 2, Fermium258, 1)
                .blast(12000)
                .build();

        Incoloy813 = new Material.Builder(++id, gcylId("incoloy_813"))
                .ingot().fluid()
                .color(0x37bf7e)
                .iconSet(SHINY)
                .flags(CORE_METAL,DISABLE_REPLICATION)
                .components(VanadiumSteel, 4, Osmiridium, 2, Technetium, 3, Germanium, 4, Iridium, 7, Duranium, 5, Californium252, 1)
                .blast(10000)
                .build();

        Staballoy = new Material.Builder(++id, gcylId("staballoy"))
                .ingot().fluid()
                .color(0x444B42)
                .iconSet(METALLIC)
                .flags(EXT2_METAL, GENERATE_FRAME, GENERATE_GEAR, DISABLE_DECOMPOSITION,DISABLE_REPLICATION)
                .components(Uranium238, 9, Titanium, 1)
                .blast(3450)
                .build();

        OrthoXyleneZeoliteMixture = new Material.Builder(++id, gcylId("ortho_xylene_zeolite"))
                .liquid()
                .color(0xB9785E)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("(NaC4Si27Al9(H2O)28O72)C6H4(CH3)2", true);

        ParaXylene = new Material.Builder(++id, gcylId("para_xylene"))
                .liquid()
                .color(0xB9575E)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("C6H4(CH3)2", true);

        Dibromomethylbenzene = new Material.Builder(++id, gcylId("dibromomethylbenzene"))
                .liquid()
                .color(0x0A1D2C)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("C7H6Br2", true);

        Terephthalaldehyde = new Material.Builder(++id, gcylId("terephthalaldehyde"))
                .dust()
                .color((Dibromomethylbenzene.getMaterialRGB() + SulfuricAcid.getMaterialRGB()) / 2)
                .iconSet(FINE)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("C8H6O2", true);

        AuPdCCatalyst = new Material.Builder(++id, gcylId("aupdc_catalyst"))
                .dust()
                .color((Gold.getMaterialRGB() + Palladium.getMaterialRGB() + Carbon.getMaterialRGB()) / 3)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("AuPdC", true);

        Isochloropropane = new Material.Builder(++id, gcylId("isochloropropane"))
                .liquid()
                .color(0xD5DD95)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("CH3CClCH3", true);

        Dinitrodipropanyloxybenzene = new Material.Builder(++id, gcylId("dinitrodipropanyloxybenzene"))
                .liquid()
                .color(0x83945F)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("C12H16O2(NO2)2", true);

        PreZylon = new Material.Builder(++id, gcylId("pre_zylon"))
                .dust()
                .color((Terephthalaldehyde.getMaterialRGB() + Dinitrodipropanyloxybenzene.getMaterialRGB()) / 2)
                .iconSet(FINE)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("C20H22N2O2", true);

        Soap = new Material.Builder(++id, gcylId("soap"))
                .liquid()
                .color(0xFFAE42)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("?", true);

        DeglyceratedSoap = new Material.Builder(++id, gcylId("deglyceratedsoap"))
                .liquid()
                .color(0xFFAE41)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("?", true);

        StearicAcid = new Material.Builder(++id, gcylId("stearicacid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0x2bbbb4)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("C18H36O2", true);

        Trioctylphosphine = new Material.Builder(++id, gcylId("trioctylphosphine"))
                .liquid()
                .color(0xF1E130)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("C24H51P", true);

        QuantumDots = new Material.Builder(++id, gcylId("quantumdots"))
                .liquid()
                .color(0xff0000)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula(makeFancy("qd"), true);

        HastelloyK243 = new Material.Builder(++id, gcylId("hastelloyk_243"))
                .ingot(2).fluid()
                .color(0xa5f564)
                .iconSet(SHINY)
                .flags(EXT2_METAL, GENERATE_FRAME, DISABLE_DECOMPOSITION, GENERATE_SMALL_GEAR, GENERATE_GEAR, GENERATE_ROUND, GENERATE_RING, GENERATE_ROTOR)
                .components(HastelloyX78, 5, NiobiumNitride, 2, Tritanium, 4, TungstenCarbide, 4, Promethium, 4, Mendelevium261, 1)
                .blast(12100)
                .build();

        Enderium = new Material.Builder(++id, gcylId("enderium"))
                .ingot(3).fluid()
                .toolStats(ToolProperty.Builder.of(8.0F, 3.0F, 1280, 3).build())
                .color(0x23524a)
                .iconSet(METALLIC)
                .flags(EXT2_METAL, DISABLE_DECOMPOSITION, DISABLE_REPLICATION)
                .components(Lead, 3, Platinum, 1, EnderPearl, 1)
                .blast(4500)
                .build();

        PreciousMetals = new Material.Builder(++id, gcylId("precious_metals"))
                .dust()
                .color((Ruthenium.getMaterialRGB() + Rhodium.getMaterialRGB() + Palladium.getMaterialRGB() + Silver.getMaterialRGB() + Rhenium.getMaterialRGB() + Osmium.getMaterialRGB() + Iridium.getMaterialRGB() + Platinum.getMaterialRGB() + Gold.getMaterialRGB()) / 9)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("RuRhPdAgReOsIrPtAu", true);

        RefractoryMetals = new Material.Builder(++id, gcylId("refractory_metals"))
                .dust()
                .color((Zirconium.getMaterialRGB() + Niobium.getMaterialRGB() + Molybdenum.getMaterialRGB() + Technetium.getMaterialRGB() + Hafnium.getMaterialRGB() + Tantalum.getMaterialRGB() + Tungsten.getMaterialRGB()) / 7)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("ZrNbMoTcHfTaW", true);

        LightTranstionMetals = new Material.Builder(++id, gcylId("light_transition_metals"))
                .dust()
                .color((Titanium.getMaterialRGB() + Vanadium.getMaterialRGB() + Chrome.getMaterialRGB() + Manganese.getMaterialRGB() + Iron.getMaterialRGB() + Cobalt.getMaterialRGB() + Nickel.getMaterialRGB() + Copper.getMaterialRGB()) / 8)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("TiVCrMnFeCoNiCu", true);

        Alkalis = new Material.Builder(++id, gcylId("alkalis"))
                .dust()
                .color((Lithium.getMaterialRGB() + Beryllium.getMaterialRGB() + Sodium.getMaterialRGB() + Magnesium.getMaterialRGB() + Potassium.getMaterialRGB() + Calcium.getMaterialRGB() + Scandium.getMaterialRGB() + Rubidium.getMaterialRGB() + Strontium.getMaterialRGB() + Yttrium.getMaterialRGB() + Caesium.getMaterialRGB() + Barium.getMaterialRGB() + Francium.getMaterialRGB() + Radium.getMaterialRGB()) / 12)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("LiBeNaMgKCaScRbSrYCeBaFrRa", true);

        PostTransitionMetals = new Material.Builder(++id, gcylId("post_transition_metals"))
                .dust()
                .color((Aluminium.getMaterialRGB() + Silicon.getMaterialRGB() + Zinc.getMaterialRGB() + Gallium.getMaterialRGB() + Germanium.getMaterialRGB() + Cadmium.getMaterialRGB() + Indium.getMaterialRGB() + Tin.getMaterialRGB() + Antimony.getMaterialRGB() + Mercury.getMaterialRGB() + Thallium.getMaterialRGB() + Lead.getMaterialRGB() + Bismuth.getMaterialRGB() + Polonium.getMaterialRGB()) / 14)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("AlSiZnGaGeCdInSnSbHgTlPbBiPo", true);

        Lanthanoids = new Material.Builder(++id, gcylId("lanthanoids"))
                .dust()
                .color((Lanthanum.getMaterialRGB() + Cerium.getMaterialRGB() + Praseodymium.getMaterialRGB() + Neodymium.getMaterialRGB() + Promethium.getMaterialRGB() + Samarium.getMaterialRGB() + Europium.getMaterialRGB() + Gadolinium.getMaterialRGB() + Terbium.getMaterialRGB() + Dysprosium.getMaterialRGB() + Holmium.getMaterialRGB() + Erbium.getMaterialRGB() + Thulium.getMaterialRGB() + Ytterbium.getMaterialRGB() + Lutetium.getMaterialRGB()) / 15)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("LaPrNdPmSmEuGdTbDyHoErTmYbLu", true);
        Actinoids = new Material.Builder(++id, gcylId("actinoids"))
                .dust()
                .color((Actinium.getMaterialRGB() + Thorium.getMaterialRGB() + Protactinium.getMaterialRGB() + Uranium.getMaterialRGB() + Neptunium.getMaterialRGB() + Plutonium.getMaterialRGB() + Americium.getMaterialRGB() + Curium.getMaterialRGB() + Berkelium.getMaterialRGB() + Californium.getMaterialRGB() + Einsteinium.getMaterialRGB() + Fermium.getMaterialRGB() + Mendelevium.getMaterialRGB()) / 13)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("AcThPaNpPuAmCmBkCfEsFmMd", true);

        NonMetals = new Material.Builder(++id, gcylId("non_metals"))
                .liquid()
                .color((Hydrogen.getMaterialRGB() + Boron.getMaterialRGB() + Carbon.getMaterialRGB() + Nitrogen.getMaterialRGB() + Oxygen.getMaterialRGB() + Fluorine.getMaterialRGB() + Phosphorus.getMaterialRGB() + Sulfur.getMaterialRGB() + Chlorine.getMaterialRGB() + Arsenic.getMaterialRGB() + Selenium.getMaterialRGB() + Bromine.getMaterialRGB() + Tellurium.getMaterialRGB() + Iodine.getMaterialRGB() + Astatine.getMaterialRGB()))
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("BCPSAsSeTeIAtONHFClBr", true);

        NobleGases = new Material.Builder(++id, gcylId("noble_gases_mixture"))
                .gas()
                .color((Helium.getMaterialRGB() + Neon.getMaterialRGB() + Argon.getMaterialRGB() + Krypton.getMaterialRGB() + Xenon.getMaterialRGB() + Radon.getMaterialRGB()))
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("HeNeArKrXeRn", true);

        Periodicium = new Material.Builder(++id, gcylId("periodicium"))
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

        HeavyLeptonMix = new Material.Builder(++id, gcylId("heavy_lepton_mix"))
                .liquid()
                .color(0x5adf52)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula(TextFormatting.OBFUSCATED + "a" + TextFormatting.RESET + "(t2)u" + TextFormatting.OBFUSCATED + "a", true);

        Gluons = new Material.Builder(++id, gcylId("gluons"))
                .liquid()
                .color(0xfcfcfa)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula(TextFormatting.OBFUSCATED + "a" + TextFormatting.RESET + "g" + TextFormatting.OBFUSCATED + "a", true);

        DenseNeutronPlasma = new Material.Builder(++id, gcylId("dense_neutron_plasma"))
                .plasma(new FluidBuilder().temperature(1000000))
                .color(0xacecac)
                .flags(DISABLE_REPLICATION, DISABLE_DECONSTRUCTION)
                .build()
                .setFormula(TextFormatting.OBFUSCATED + "a" + TextFormatting.RESET + TextFormatting.GRAY + "n" + TextFormatting.OBFUSCATED + "a", true);

        SuperfluidHelium = new Material.Builder(++id, gcylId("superfluid_helium"))
                .liquid()
                .color(Helium.getMaterialRGB())
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("He", true);

        Cycloparaphenylene = new Material.Builder(++id, gcylId("cycloparaphenylene"))
                .liquid()
                .color(0x333333)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("CPP", true);

        NeutroniumDopedNanotubes = new Material.Builder(++id, gcylId("neutronium_doped_nanotubes"))
                .liquid()
                .color((Neutronium.getMaterialRGB() + CarbonNanotubes.getMaterialRGB()) / 2)
                .iconSet(FLUID)
                .flags(DISABLE_REPLICATION)
                .build()
                .setFormula("Nt?", true);

        CosmicMeshPlasma = new Material.Builder(++id, gcylId("cosmic_mesh_plasma"))
                .plasma(new FluidBuilder().temperature(1000000))
                .color(0x1c1c8c)
                .flags(DISABLE_REPLICATION, DISABLE_DECONSTRUCTION)
                .build()
                .setFormula(TextFormatting.OBFUSCATED + "nn", true);

        AcidicSaltWater = new Material.Builder(++id, gcylId("acidic_salt_water"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0x006960)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2SO4(NaCl)3(H2O)3Cl2", true);

        SulfuricBromineSolution = new Material.Builder(++id, gcylId("sulfuric_bromine_solution"))
                .liquid()
                .color(0xff5100)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2SO4Br(H2O)Cl2", true);

        DebrominatedWater = new Material.Builder(++id, gcylId("debrominated_brine"))
                .liquid()
                .color(0x0000ff)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2O", true);

        HotVapourMixture = new Material.Builder(++id, gcylId("hot_vapour_mixture"))
                .gas()
                .color(0xff5100)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2SO4Br(H2O)2Cl2", true);

        Ethylhexanol = new Material.Builder(++id, gcylId("ethylhexanol"))
                .liquid()
                .color(0xfeea9a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C8H18O", true);

        DampBromine = new Material.Builder(++id, gcylId("damp_bromine"))
                .liquid()
                .color(0xe17594)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Br(H2O)", true);

        DiethylhexylPhosphoricAcid = new Material.Builder(++id, gcylId("di_ethylhexyl_phosphoric_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0xffff99)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C16H35O4P", true);

        SodiumHydroxideSolution = new Material.Builder(++id, gcylId("sodium_hydroxide_solution"))
                .liquid()
                .color(SodiumHydroxide.getMaterialRGB() + 50)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)NaOH", true);

        RareEarthHydroxidesSolution = new Material.Builder(++id, gcylId("rare_earth_hydroxides_solution"))
                .liquid()
                .color(0xcfb37d)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("NaOH(H2O)?(OH)3", true);

        RareEarthChloridesSolution = new Material.Builder(++id, gcylId("rare_earth_chlorides_solution"))
                .liquid()
                .color(0x164b45)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(?Cl3)H2O", true);

        ThUSludge = new Material.Builder(++id, gcylId("thorium_uranium_sludge"))
                .dust()
                .color(0x002908)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("ThU", true);

        LaNdOxidesSolution = new Material.Builder(++id, gcylId("la_nd_oxides_solution"))
                .liquid()
                .color(0x9ce3db)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(La2O3)(Pr2O3)(Nd2O3)(Ce2O3)", true);

        SmGdOxidesSolution = new Material.Builder(++id, gcylId("sm_gd_oxides_solution"))
                .liquid()
                .color(0xffff99)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(Sc2O3)(Eu2O3)(Gd2O3)(Sm2O3)", true);

        TbHoOxidesSolution = new Material.Builder(++id, gcylId("tb_ho_oxides_solution"))
                .liquid()
                .color(0x99ff99)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(Y2O3)(Tb2O3)(Dy2O3)(Ho2O3)", true);

        ErLuOxidesSolution = new Material.Builder(++id, gcylId("er_lu_oxides_solution"))
                .liquid()
                .color(0xffb3ff)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(Er2O3)(Tm2O3)(Yb2O3)(Lu2O3)", true);

        LanthanumOxide = new Material.Builder(++id, gcylId("lanthanum_oxide"))
                .dust()
                .color(Lanthanum.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(Lanthanum.getMaterialIconSet())
                .build()
                .setFormula("La2O3", true);

        CeriumOxide = new Material.Builder(++id, gcylId("cerium_oxide"))
                .dust()
                .color(Cerium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(Cerium.getMaterialIconSet())
                .build()
                .setFormula("Ce2O3", true);

        ScandiumOxide = new Material.Builder(++id, gcylId("scandium_oxide"))
                .dust()
                .color(Scandium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(Scandium.getMaterialIconSet())
                .build()
                .setFormula("Sc2O3", true);

        EuropiumOxide = new Material.Builder(++id, gcylId("europium_oxide"))
                .dust()
                .color(Europium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(Europium.getMaterialIconSet())
                .build()
                .setFormula("Eu2O3", true);

        GadoliniumOxide = new Material.Builder(++id, gcylId("gadolinium_oxide"))
                .dust()
                .color(Gadolinium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(Gadolinium.getMaterialIconSet())
                .build()
                .setFormula("Gd2O3", true);

        SamariumOxide = new Material.Builder(++id, gcylId("samarium_oxide"))
                .dust()
                .color(Samarium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(Samarium.getMaterialIconSet())
                .build()
                .setFormula("Sm2O3", true);

        TerbiumOxide = new Material.Builder(++id, gcylId("terbium_oxide"))
                .dust()
                .color(Terbium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(Terbium.getMaterialIconSet())
                .build()
                .setFormula("Tb2O3", true);

        DysprosiumOxide = new Material.Builder(++id, gcylId("dysprosium_oxide"))
                .dust()
                .color(Dysprosium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(Dysprosium.getMaterialIconSet())
                .build()
                .setFormula("Dy2O3", true);

        ErbiumOxide = new Material.Builder(++id, gcylId("erbium_oxide"))
                .dust()
                .color(Erbium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(Erbium.getMaterialIconSet())
                .build()
                .setFormula("Er2O3", true);

        YtterbiumOxide = new Material.Builder(++id, gcylId("ytterbium_oxide"))
                .dust()
                .color(Ytterbium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(Ytterbium.getMaterialIconSet())
                .build()
                .setFormula("Yb2O3", true);

        Dimethylether = new Material.Builder(++id, gcylId("dimethylether"))
                .liquid()
                .color(0xe6cd11)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C2H6O", true);

        EthyleneOxide = new Material.Builder(++id, gcylId("ethylene_oxide"))
                .liquid()
                .color(0xa0c3de)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C2H4O", true);

        Dimethoxyethane = new Material.Builder(++id, gcylId("dimethoxyethane"))
                .liquid()
                .color(0x2acbb4)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C4H10O2", true);

        Cyclooctadiene = new Material.Builder(++id, gcylId("cyclooctadiene"))
                .liquid()
                .color(0x33CC33)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C8H12", true);

        Cyclopentadiene = new Material.Builder(++id, gcylId("cyclopentadiene"))
                .liquid()
                .color(Cyclooctadiene.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C5H6", true);

        ButylLithium = new Material.Builder(++id, gcylId("butyl_lithium"))
                .liquid()
                .color(Butane.getMaterialRGB() + Lithium.getMaterialRGB() / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C4H9Li", true);

        LithiumCyclopentadienide = new Material.Builder(++id, gcylId("lithiumcyclopentadienide"))
                .liquid()
                .color(0x95556a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("LiC5H5", true);

        GoldCyanide = new Material.Builder(++id, gcylId("gold_cyanide"))
                .liquid()
                .color(0x8c8761)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("AuCN", true);

        GoldDepleteMolybdenite = new Material.Builder(++id, gcylId("gold_deplete_molybdenite"))
                .dust()
                .color(0x7c7c8f)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("MoS2?", true);

        MolybdenumConcentrate = new Material.Builder(++id, gcylId("molybdenum_concentrate"))
                .dust()
                .color(0x565666)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("MoS2Re", true);

        ChlorideLeachedSolution = new Material.Builder(++id, gcylId("chloride_leached_solution"))
                .liquid()
                .color(0x41472e)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("CaCl2(CuCl2)(PbCl2)(BiCl3)(FeCl2)", true);

        CopperChloride = new Material.Builder(++id, gcylId("copper_chloride"))
                .dust()
                .color(0xf5b35d)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("CuCl2", true);

        LeadChloride = new Material.Builder(++id, gcylId("lead_chloride"))
                .dust()
                .color(Lead.getMaterialRGB() + Chlorine.getMaterialRGB() / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("PbCl2", true);

        BismuthChloride = new Material.Builder(++id, gcylId("bismuth_chloride"))
                .dust()
                .color(0x95f5d7)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("BiCl3", true);

        Iron2Chloride = new Material.Builder(++id, gcylId("iron_ii_chloride"))
                .liquid()
                .color(Iron3Chloride.getMaterialRGB() - 10)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("FeCl2", true);

        MolybdenumFlue = new Material.Builder(++id, gcylId("molybdenum_flue_gas"))
                .gas()
                .color(0x333338)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2OReS?", true);

        MolybdenumTrioxide = new Material.Builder(++id, gcylId("molybdenum_trioxide"))
                .dust()
                .color(0x666685)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("MoO3", true);

        RheniumSulfuricSolution = new Material.Builder(++id, gcylId("rhenium_sulfuric_solution"))
                .liquid()
                .color(0xbabaff)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("ReS?", true);

        AmmoniumPerrhenate = new Material.Builder(++id, gcylId("ammonium_perrhenate"))
                .liquid()
                .color(0x1c1c45)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("NH4ReO4", true);

        MercuryAcetate = new Material.Builder(++id, gcylId("mercury_acetate"))
                .dust()
                .color(0xcc8562)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("Hg(CH3COO)2", true);

        Methylamine = new Material.Builder(++id, gcylId("methylamine"))
                .liquid()
                .color((Methanol.getMaterialRGB() + Ammonia.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("CH3NH2", true);

        Methylethanolamine = new Material.Builder(++id, gcylId("methylethanolamine"))
                .liquid()
                .color(0x6a3baa)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C3H9NO", true);

        CalciumCarbide = new Material.Builder(++id, gcylId("calcium_carbide"))
                .dust()
                .color(0x807b70)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("CaC2", true);

        CalciumCyanamide = new Material.Builder(++id, gcylId("calcium_cyanamide"))
                .dust()
                .color(CalciumCarbide.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("CaCN2", true);

        Methylguanidine = new Material.Builder(++id, gcylId("methylguanidine"))
                .liquid()
                .color(0x5a9a3c)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C2H7N3", true);

        Methylnitronitrosoguanidine = new Material.Builder(++id, gcylId("methylnitronitrosoguanidine"))
                .liquid()
                .color(0x68b15d)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C2H5N5O3", true);

        CupriavidusNecator = new Material.Builder(++id, gcylId("cupriavidus_necator"))
                .dust()
                .color(0x22704f)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("Bacteria", true);

        SelectivelyMutatedCupriavidiusNecator = new Material.Builder(++id, gcylId("selectively_mutated_cupriavidius_necator"))
                .dust()
                .color(CupriavidusNecator.getMaterialRGB() * 5 / 4)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Bacteria", true);

        IsoamylAlcohol = new Material.Builder(++id, gcylId("isoamyl_alcohol"))
                .liquid()
                .color(0xcaba77)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C5H12O", true);

        Oct1ene = new Material.Builder(++id, gcylId("1_octene"))
                .liquid()
                .color(0x7e8778)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C8H16", true);

        Octanol = new Material.Builder(++id, gcylId("octanol"))
                .liquid()
                .color(0xa2b8c2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C8H18O", true);

        PalladiumLoadedRutileNanoparticles = new Material.Builder(++id, gcylId("palladium_loaded_rutile_nanoparticles"))
                .dust()
                .color((Palladium.getMaterialRGB() + Rutile.getMaterialRGB()))
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("PdTiO2", true);

        Trioctylamine = new Material.Builder(++id, gcylId("trioctylamine"))
                .liquid()
                .color(0x87a2bc)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C24H51N", true);

        Kerosene = new Material.Builder(++id, gcylId("kerosene"))
                .liquid()
                .color(0xD570D5)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        MesitylOxide = new Material.Builder(++id, gcylId("mesityl_oxide"))
                .liquid()
                .color(Acetone.getMaterialRGB() - 10)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H10O", true);

        MethylIsobutylKetone = new Material.Builder(++id, gcylId("methyl_isobutyl_ketone"))
                .liquid()
                .color((MesitylOxide.getMaterialRGB() + WaterAgarMix.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H12O", true);

        PlatinumSalt = new Material.Builder(++id, gcylId("platinum_salt"))
                .dust()
                .color(Platinum.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("Pt?", true);

        RheniumSeparationMixture = new Material.Builder(++id, gcylId("rhenium_separation_mixture"))
                .liquid()
                .color(0xed2c3a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C11H24", true);

        RheniumScrubbedSolution = new Material.Builder(++id, gcylId("rhenium_scrubbed_solution"))
                .liquid()
                .color(0xedccca)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Re?", true);

        LeachedColumbite = new Material.Builder(++id, gcylId("leached_columbite"))
                .dust()
                .color(0xCCCC00)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("(Nb2O5)9Ta2O5?", true);

        PurifiedColumbite = new Material.Builder(++id, gcylId("purified_columbite"))
                .dust()
                .color(LeachedColumbite.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Ta2O5Nb18O45", true);

        LeachedPyrochlore = new Material.Builder(++id, gcylId("leached_pyrochlore"))
                .dust()
                .color(0x996633)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("(Nb2O5)9Ta2O5?", true);

        PurifiedPyrochlore = new Material.Builder(++id, gcylId("purified_pyrochlore"))
                .dust()
                .color(LeachedPyrochlore.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Ta2O5Nb18O45", true);

        Glucose = new Material.Builder(++id, gcylId("glucose"))
                .dust()
                .color((Sugar.getMaterialRGB() + 5))
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("C6H12O6", true);

        FluoroniobicAcid = new Material.Builder(++id, gcylId("fluroniobic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0x73ff00)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("NbHF7", true);

        PlatinumSaltRefined = new Material.Builder(++id, gcylId("refined_platinum_salt"))
                .dust()
                .color(Platinum.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(METALLIC)
                .build()
                .setFormula("PtCl?", true);

        DirtyHexafluorosilicicAcid = new Material.Builder(++id, gcylId("dirty_hexafluorosilicic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color((Stone.getMaterialRGB() + FluorosilicicAcid.getMaterialRGB() / 2))
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2SiF6?", true);

        StoneResidueDust = new Material.Builder(++id, gcylId("stone_residue_dust"))
                .dust()
                .color(Stone.getMaterialRGB() / 5 * 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build();

        DiluteHexafluorosilicicAcid = new Material.Builder(++id, gcylId("dilute_hexafluorosilicic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color((Water.getMaterialRGB() * 2 + FluorosilicicAcid.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)2(H2SiF6)", true);

        Triniite = new Material.Builder(++id, gcylId("triniite"))
                .dust(7)
                .color(0x5F5A76)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(SHINY)
                .components(Trinium, 3, Actinium, 3, Selenium, 4, Astatine, 4)
                .build();

        UncommonResidues = new Material.Builder(++id, gcylId("uncommon_residues"))
                .dust()
                .color((Triniite.getMaterialRGB() + NaquadriaticTaranium.getMaterialRGB() + PreciousMetals.getMaterialRGB()) / 5)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("?", true);

        RedMud = new Material.Builder(++id, gcylId("red_mud"))
                .liquid()
                .color(0xcc3300)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("HCl?", true);

        Dioxygendifluoride = new Material.Builder(++id, gcylId("dioxygen_difluoride"))
                .liquid()
                .color(0x32bdaf)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("FOOF", true);

        PartiallyOxidizedResidues = new Material.Builder(++id, gcylId("partially_oxidized_residues"))
                .dust()
                .color(StoneResidueDust.getMaterialRGB() + Dioxygendifluoride.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build();

        OxidizedResidualSolution = new Material.Builder(++id, gcylId("oxidized_residual_solution"))
                .liquid()
                .color(0x23ad7f)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        InertResidues = new Material.Builder(++id, gcylId("inert_residues"))
                .dust()
                .color(0x61587a)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build();

        CleanInertResidues = new Material.Builder(++id, gcylId("clean_inert_residues"))
                .dust()
                .color((Taranium.getMaterialRGB() + Xenon.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build();

        NaquadricCompound = new Material.Builder(++id, gcylId("naquadric_compound"))
                .dust()
                .color(Naquadah.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(Naquadah.getMaterialIconSet())
                .components(Naquadah.getMaterialComponents())
                .build();

        EnrichedNaquadricCompound = new Material.Builder(++id, gcylId("enriched_naquadric_compound"))
                .dust()
                .color(NaquadahEnriched.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(NaquadahEnriched.getMaterialIconSet())
                .components(NaquadahEnriched.getMaterialComponents())
                .build();

        NaquadriaticCompound = new Material.Builder(++id, gcylId("naquadriatic_compound"))
                .dust()
                .color(Naquadria.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(Naquadria.getMaterialIconSet())
                .components(Naquadria.getMaterialComponents())
                .build();

        DiamagneticResidues = new Material.Builder(++id, gcylId("diamagnetic_residues"))
                .dust()
                .color((Calcium.getMaterialRGB() + Zinc.getMaterialRGB() + Copper.getMaterialRGB() + Gallium.getMaterialRGB() + Beryllium.getMaterialRGB() + Tin.getMaterialRGB()) / 15)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build();

        ParamagneticResidues = new Material.Builder(++id, gcylId("paramagnetic_residues"))
                .dust()
                .color((Sodium.getMaterialRGB() + Potassium.getMaterialRGB() + Magnesium.getMaterialRGB() + Titanium.getMaterialRGB() + Vanadium.getMaterialRGB() + Manganese.getMaterialRGB()) / 15)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build();

        FerromagneticResidues = new Material.Builder(++id, gcylId("ferromagnetic_residues"))
                .dust()
                .color((Iron.getMaterialRGB() + Nickel.getMaterialRGB() + Cobalt.getMaterialRGB()) / 7)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build();

        OxidizedResidues = new Material.Builder(++id, gcylId("oxidized_residues"))
                .dust()
                .color((DiamagneticResidues.getMaterialRGB() + ParamagneticResidues.getMaterialRGB() + FerromagneticResidues.getMaterialRGB() + 0x9f0000) / 4)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build();

        HeavyOxidizedResidues = new Material.Builder(++id, gcylId("heavy_oxidized_residues"))
                .dust()
                .color(OxidizedResidues.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build();

        MetallicResidues = new Material.Builder(++id, gcylId("metallic_residues"))
                .dust()
                .color((DiamagneticResidues.getMaterialRGB() + ParamagneticResidues.getMaterialRGB() + FerromagneticResidues.getMaterialRGB() + UncommonResidues.getMaterialRGB() / 3) / 4)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build();

        HeavyDiamagneticResidues = new Material.Builder(++id, gcylId("heavy_diamagnetic_residues"))
                .dust()
                .color((Lead.getMaterialRGB() + Mercury.getMaterialRGB() + Cadmium.getMaterialRGB() + Indium.getMaterialRGB() + Gold.getMaterialRGB() + Bismuth.getMaterialRGB()))
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build();

        HeavyParamagneticResidues = new Material.Builder(++id, gcylId("heavy_paramagnetic_residues"))
                .dust()
                .color((Thorium.getMaterialRGB() + Thallium.getMaterialRGB() + Uranium.getMaterialRGB() + Tungsten.getMaterialRGB() + Hafnium.getMaterialRGB() + Tantalum.getMaterialRGB()) / 15)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build();

        HeavyFerromagneticResidues = new Material.Builder(++id, gcylId("heavy_ferromagnetic_residues"))
                .dust()
                .color(DysprosiumOxide.getMaterialRGB() * 3 / 11)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build();

        HeavyMetallicResidues = new Material.Builder(++id, gcylId("heavy_metallic_residues"))
                .dust()
                .color((HeavyDiamagneticResidues.getMaterialRGB() + HeavyParamagneticResidues.getMaterialRGB() + HeavyFerromagneticResidues.getMaterialRGB() + UncommonResidues.getMaterialRGB() / 3) / 4)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build();

        NitratedTriniiteSolution = new Material.Builder(++id, gcylId("nitrated_triniite_solution"))
                .liquid()
                .color(0x428c9f)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        ExoticHeavyResidues = new Material.Builder(++id, gcylId("exotic_heavy_residues"))
                .dust()
                .color(NitratedTriniiteSolution.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build();

        DiluteHydrofluoricAcid = new Material.Builder(++id, gcylId("dilute_hydrofluoric_acid"))
                .liquid()
                .color((Water.getMaterialRGB() + HydrofluoricAcid.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)(HF)", true);

        TritiumHydride = new Material.Builder(++id, gcylId("tritium_hydride"))
                .liquid()
                .color(Tritium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("TH", true);

        Helium3Hydride = new Material.Builder(++id, gcylId("helium_iii_hydride"))
                .liquid()
                .color(Helium3.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("He_3H", true);

        UltraacidicResidueSolution = new Material.Builder(++id, gcylId("ultraacidic_residue_solution"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color((FluoroantimonicAcid.getMaterialRGB() + Helium3Hydride.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        XenicAcid = new Material.Builder(++id, gcylId("xenic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0x5a4c9c)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2XeO4", true);

        DustyLiquidHelium3 = new Material.Builder(++id, gcylId("dusty_liquid_helium_3"))
                .liquid()
                .color(2 * Helium3.getMaterialRGB() / 3 + Taranium.getMaterialRGB() / 5)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("He_3", true);

        Ozone = new Material.Builder(++id, gcylId("ozone"))
                .gas()
                .color(0x0099FF)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("O3", true);

        TaraniumEnrichedLHelium3 = new Material.Builder(++id, gcylId("taranium_enriched_liquid_helium_3"))
                .liquid()
                .color(Helium3.getMaterialRGB() / 2 + Taranium.getMaterialRGB() / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        TaraniumSemidepletedLHelium3 = new Material.Builder(++id, gcylId("taranium_semidepleted_liquid_helium_3"))
                .liquid()
                .color(2 * Helium3.getMaterialRGB() / 3 + Taranium.getMaterialRGB() / 5)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        TaraniumSemidepletedLHelium3 = new Material.Builder(++id, gcylId("taranium_semidepleted_liquid_helium_3"))
                .liquid()
                .color(2 * Helium3.getMaterialRGB() / 3 + Taranium.getMaterialRGB() / 5)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        TaraniumDepletedLHelium3 = new Material.Builder(++id, gcylId("taranium_depleted_liquid_helium_3"))
                .liquid()
                .color(Helium3.getMaterialRGB() * 5 / 6 + Taranium.getMaterialRGB() / 8)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        TaraniumRichDustyHeliumPlasma = new Material.Builder(++id, gcylId("taranium_rich_dusty_helium_plasma"))
                .plasma(new FluidBuilder().temperature(10000))
                .color(Helium.getMaterialRGB() / 2 + Taranium.getMaterialRGB() / 2)
                .flags(DISABLE_REPLICATION, DISABLE_DECONSTRUCTION)
                .iconSet(FLUID)
                .build();

        TaraniumRichHelium4 = new Material.Builder(++id, gcylId("taranium_rich_helium_4"))
                .liquid()
                .plasma()
                .color(Helium.getMaterialRGB() / 2 + Taranium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        TaraniumDepletedHeliumPlasma = new Material.Builder(++id, gcylId("taranium_depleted_helium_plasma"))
                .plasma(new FluidBuilder().temperature(10000))
                .color(Helium.getMaterialRGB() / 2 + Taranium.getMaterialRGB() / 2)
                .flags(DISABLE_REPLICATION, DISABLE_DECONSTRUCTION)
                .iconSet(FLUID)
                .build();

        TaraniumPoorLiquidHelium = new Material.Builder(++id, gcylId("taranium_poor_liquid_helium"))
                .liquid()
                .color(Helium3.getMaterialRGB() * 6 / 7 + Taranium.getMaterialRGB() / 14)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        TaraniumPoorLiquidHeliumMix = new Material.Builder(++id, gcylId("taranium_poor_liquid_helium_mix"))
                .liquid()
                .color(TaraniumPoorLiquidHelium.getMaterialRGB() * 10 / 11 + Helium.getMaterialRGB() / 11)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        BariumOxide = new Material.Builder(++id, gcylId("barium_oxide"))
                .dust()
                .color((Barium.getMaterialRGB() + Oxygen.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("BaO", true);

        BariumStrontiumAcetateSolution = new Material.Builder(++id, gcylId("basr_acetate_solution"))
                .liquid()
                .color(0x9a9b98)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C2H3BaO2Sr", true);

        IsopropylAlcohol = new Material.Builder(++id, gcylId("isopropyl_alcohol"))
                .liquid()
                .color((Water.getMaterialRGB() + Propene.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C3H8O", true);

        TitaniumIsopropoxide = new Material.Builder(++id, gcylId("titanium_isopropoxide"))
                .liquid()
                .color(0xFF0066)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Ti(OCH(CH3)2)4", true);

        BariumStrontiumTitanatePreparation = new Material.Builder(++id, gcylId("basr_titanate_preparation"))
                .liquid()
                .color(0xFF0066)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(BaTiO3)C2H3BaO2Sr", true);

        BariumStrontiumTitanate = new Material.Builder(++id, gcylId("barium_strontium_titanate"))
                .dust()
                .color(0xFF0066)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("BaO4SrTi", true);

        IsopropylAcetate = new Material.Builder(++id, gcylId("isopropyl_acetate"))
                .liquid()
                .color((Strontium.getMaterialRGB() + IsopropylAlcohol.getMaterialRGB() + Water.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(CH3)2CHCOOCH3", true);

        PotasssiumFluoroTantalate = new Material.Builder(++id, gcylId("potassium_fluorotantalate"))
                .dust()
                .color(0x73ff00)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("K2TaF7", true);

        LeadNitrate = new Material.Builder(++id, gcylId("lead_nitrate"))
                .dust()
                .color(0xFEFEFE)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .components(Lead, 1, Nitrogen, 2, Oxygen, 6)
                .build();

        LeadNitrateCalciumMixture = new Material.Builder(++id, gcylId("lead_nitrate_calcium_mixture"))
                .dust()
                .color((LeadNitrate.getMaterialRGB() + Calcium.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("(Pb(NO3)2)Ca9", true);

        LeadScandiumTantalate = new Material.Builder(++id, gcylId("lead_scandium_tantalate"))
                .dust()
                .color((Lead.getMaterialRGB() + Scandium.getMaterialRGB() + Tantalum.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Pb(ScTa)O3", true);

        MagnetorestrictiveAlloy = new Material.Builder(++id, gcylId("magnetorestrictive_alloy"))
                .dust()
                .color(0xafefef)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("Tb4Dy7Fe10Co5B2SiC", true);

        LeadSenenide = new Material.Builder(++id, gcylId("lead_selenide"))
                .dust()
                .color((Lead.getMaterialRGB() + Selenium.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("PbSe", true);

        ZincSelenide = new Material.Builder(++id, gcylId("zinc_selenide"))
                .dust()
                .color(0xfcfc00)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("ZnSe", true);

        FranciumCaesiumCadmiumBromide = new Material.Builder(++id, gcylId("francium_caesium_cadmium_bromide"))
                .dust()
                .color((Francium.getMaterialRGB() + Caesium.getMaterialRGB() + Cadmium.getMaterialRGB() + Bromine.getMaterialRGB()) / 4)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("FrCsCf2Br6", true);

        SodiumIodide = new Material.Builder(++id, gcylId("sodium_iodide"))
                .dust()
                .color(0x555588)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("NaI", true);

        Iodobenzene = new Material.Builder(++id, gcylId("iodobenzene"))
                .liquid()
                .color(0x2c2c6c0)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H5I", true);

        PalladiumAcetate = new Material.Builder(++id, gcylId("palladium_acetate"))
                .dust()
                .color(0xcc3300)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("C4H6O4Pd", true);

        Stilbene = new Material.Builder(++id, gcylId("stilbene"))
                .dust()
                .color(0x3c9c3c)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("C14H12", true);

        Aminophenol = new Material.Builder(++id, gcylId("aminophenol"))
                .liquid()
                .color(0xafca3a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H4(OH)(NH2)", true);

        Amino3phenol = new Material.Builder(++id, gcylId("3_aminophenol"))
                .liquid()
                .color(Aminophenol.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H7NO", true);

        Ethylamine = new Material.Builder(++id, gcylId("ethylamine"))
                .liquid()
                .color(Ethylenediamine.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C2H5NH2", true);

        SodiumAzanide = new Material.Builder(++id, gcylId("sodium_azanide"))
                .dust()
                .color((Sodium.getMaterialRGB() + Hydrogen.getMaterialRGB() + Nitrogen.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("NaNH2", true);

        PhthalicAnhydride = new Material.Builder(++id, gcylId("phthalicanhydride"))
                .dust()
                .color(0xD1D1D1)
                .flags(DISABLE_REPLICATION)
                .iconSet(SAND)
                .components(Carbon, 8, Hydrogen, 4, Oxygen, 3)
                .build();

        TetraethylammoniumNonahydridides = new Material.Builder(++id, gcylId("tetraethylammonium_nonahydrides"))
                .dust()
                .color(0xbee8b9)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("(C8H20N)(ReH9)(TcH9)", true);

        RhodamineB = new Material.Builder(++id, gcylId("rhodamine_b"))
                .dust()
                .color(0xfc2020)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("C28H31ClN2O3", true);

        Hydroxylamine = new Material.Builder(++id, gcylId("hydroxylamine"))
                .liquid()
                .color(0x99cc99)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H3NO", true);

        SodiumPertechnetate = new Material.Builder(++id, gcylId("sodium_pertechnetate"))
                .dust()
                .color(0x6162c4)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("NaTcO4", true);

        PotassiumPerrhenate = new Material.Builder(++id, gcylId("potassium_perrhenate"))
                .dust()
                .color(0xdec451)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("KReO4", true);

        PotassiumPertechnate = new Material.Builder(++id, gcylId("potassium_pertechnate"))
                .dust()
                .color(0xdec451)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("KTcO4", true);

        PotassiumNonahydridorhenate = new Material.Builder(++id, gcylId("potassium_nonahydridorhenate"))
                .dust()
                .color(0xeae2a8)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("H9K2ReO4", true);

        PotassiumEthoxide = new Material.Builder(++id, gcylId("potassium_ethoxide"))
                .liquid()
                .color(Ethanol.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C2H5KO", true);

        PotassiumNonahydridotechnetate = new Material.Builder(++id, gcylId("potassium_nonahydridotechnetate"))
                .dust()
                .color(0xede2a4)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("H9K2TcO4", true);

        TetraethylammoniumBromide = new Material.Builder(++id, gcylId("tetraethylammonium_bromide"))
                .liquid()
                .color(0xcc33ff)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C8H20NBr", true);

        PotassiumIodide = new Material.Builder(++id, gcylId("potassium_iodide"))
                .dust()
                .color((Potassium.getMaterialRGB() + Iodine.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("KI", true);

        Butylaniline = new Material.Builder(++id, gcylId("butylaniline"))
                .liquid()
                .color(Aniline.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C10H15N", true);

        LithiumIodide = new Material.Builder(++id, gcylId("lithium_iodide"))
                .dust()
                .color((Lithium.getMaterialRGB() + Iodine.getMaterialRGB()))
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("LiI", true);

        Trimethylchlorosilane = new Material.Builder(++id, gcylId("trimethylchlorosilane"))
                .liquid()
                .color(Dimethyldichlorosilane.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(CH3)3SiCl", true);

        Trimethylsilane = new Material.Builder(++id, gcylId("trimethylsilane"))
                .liquid()
                .color(Trimethylchlorosilane.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C3H10Si", true);

        PotassiumBromide = new Material.Builder(++id, gcylId("potassium_bromide"))
                .dust()
                .color(0xe066a3)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("KBr", true);

        PotassiumBromate = new Material.Builder(++id, gcylId("potassium_bromate"))
                .dust()
                .color(0x8a4cd1)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("KBrO3", true);

        IodobenzoicAcid = new Material.Builder(++id, gcylId("iodobenzoic_acid"))
                .liquid()
                .color(0x2cac6c0)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C7H5IO2", true);

        IBX = new Material.Builder(++id, gcylId("ibx"))
                .dust()
                .color(0x20208c)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("C7H5IO4", true);

        Methoxybenzaldehyde = new Material.Builder(++id, gcylId("methoxybenzaldehyde"))
                .liquid()
                .color(0x3c3a7a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C8H8O2", true);

        MBBA = new Material.Builder(++id, gcylId("mbba"))
                .liquid()
                .color(0xfa30fa)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C18H21NO", true);

        LiquidCrystalDetector = new Material.Builder(++id, gcylId("liquid_crystal_detector"))
                .liquid()
                .color(0xda20da)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        IodineMonochloride = new Material.Builder(++id, gcylId("iodine_monochloride"))
                .liquid()
                .color(0x004c4c)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("ICl", true);

        RhReNqCatalyst = new Material.Builder(++id, gcylId("rhrenq_catalyst"))
                .dust()
                .color((Rhenium.getMaterialRGB() + Rhodium.getMaterialRGB() + Naquadah.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("ReRhNq", true);

        AcetylatingReagent = new Material.Builder(++id, gcylId("acetylating_reagent"))
                .liquid()
                .color(0x8d5e63)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C9H12Si(MgBr)2", true);

        Dimethylnaphthalene = new Material.Builder(++id, gcylId("dimethylnaphthalene"))
                .liquid()
                .color(0xe34fb0)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C12H12", true);

        Bromosuccinimide = new Material.Builder(++id, gcylId("bromo_succinimide"))
                .dust()
                .color((Succinimide.getMaterialRGB() + Bromine.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(METALLIC)
                .build()
                .setFormula("C4H4BrNO2", true);

        Dihydroiodotetracene = new Material.Builder(++id, gcylId("dihydroiodotetracene"))
                .liquid()
                .color(0x5c4d38)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2C18H11I", true);

        Dichlorodicyanobenzoquinone = new Material.Builder(++id, gcylId("dichlorodicyanobenzoquinone"))
                .liquid()
                .color(0x3a2aba)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C8Cl2N2O2", true);

        VanadiumOxide = new Material.Builder(++id, gcylId("vanadium_oxide"))
                .dust()
                .color(0xf2ef1b)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("V2O5", true);

        LithiumChlorideSolution = new Material.Builder(++id, gcylId("lithium_chloride_solution"))
                .liquid()
                .color((Lithium.getMaterialRGB() + Chlorine.getMaterialRGB()))
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("LiCl(H2O)", true);

        Dichlorodicyanohydroquinone = new Material.Builder(++id, gcylId("dichlorodicyanohidroquinone"))
                .liquid()
                .color(0x3a2aba)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C8Cl2N2(OH)2", true);

        Tetracene = new Material.Builder(++id, gcylId("tetracene"))
                .dust()
                .color(0x99801a)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("C18H12", true);

        StreptococcusPyogenes = new Material.Builder(++id, gcylId("streptococcus_pyogenes"))
                .dust()
                .color(0x1c3b15)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("Bacteria", true);

        BifidobacteriumBreve = new Material.Builder(++id, gcylId("bifidobacterium_breve"))
                .dust()
                .color(0x377528)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("Bacteria", true);

        EschericiaColi = new Material.Builder(++id, gcylId("eschericia_coli"))
                .dust()
                .color(0x2d4228)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("Bacteria", true);

        SodiumHypochlorite = new Material.Builder(++id, gcylId("sodium_hypochlorite"))
                .dust()
                .color(0x6cff50)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("NaClO", true);

        HotNitrogen = new Material.Builder(++id, gcylId("hot_nitrogen"))
                .gas()
                .color(Nitrogen.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("N", true);

        Lignite = new Material.Builder(++id, gcylId("lignite"))
                .gem(0, 1600)
                .color(6571590)
                .flags(DISABLE_REPLICATION, FLAMMABLE, NO_SMELTING, NO_SMASHING, MORTAR_GRINDABLE)
                .iconSet(LIGNITE)
                .components(Carbon, 2, Water, 4, DarkAsh, 1)
                .build()
                .setFormula("C2(H2O)4C", true);

        DehydratedLignite = new Material.Builder(++id, gcylId("dehydrated_lignite"))
                .dust()
                .color(0x5c4020)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("C2(H2O)4C?", true);

        BCEPellet = new Material.Builder(++id, gcylId("bce_pellet"))
                .dust(1, 1600)
                .color(0x3c3020)
                .flags(DISABLE_REPLICATION)
                .iconSet(LIGNITE)
                .build()
                .setFormula("C2(H2O)4C", true);

        GlucoseIronSolution = new Material.Builder(++id, gcylId("glucose_iron_solution"))
                .liquid()
                .color((Sugar.getMaterialRGB() + Iron.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(C6H12O6)FeCl3", true);

        GrapheneOxidationSolution = new Material.Builder(++id, gcylId("graphene_oxidation_solution"))
                .liquid()
                .color(0x96821a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(KMnO4)(NaNO3)(H2SO4)", true);

        GraphiteOxide = new Material.Builder(++id, gcylId("graphite_oxide"))
                .dust()
                .color(Graphite.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("C(O2)", true);

        GrapheneOxidationResidue = new Material.Builder(++id, gcylId("graphene_oxidation_residue"))
                .dust()
                .color(0x96821a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("(KMnO4)(NaNO3)(H2SO4)", true);

        GrapheneOxide = new Material.Builder(++id, gcylId("graphene_oxide"))
                .dust()
                .color(Graphene.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("C(O2)", true);

        Hydrazine = new Material.Builder(++id, gcylId("hydrazine"))
                .liquid()
                .color(0xFFFFFF)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .components(Nitrogen, 2, Hydrogen, 4)
                .build();

        Snow = new Material.Builder(++id, gcylId("snow"))
                .dust()
                .color(0xFFFFFF)
                .flags(DISABLE_REPLICATION, NO_SMELTING)
                .iconSet(OPAL)
                .components(Hydrogen, 2, Oxygen, 1)
                .build();

        SupercooledCryotheum = new Material.Builder(++id, gcylId("supercooled_cryotheum"))
                .liquid(new FluidBuilder().temperature(10))
                .color(Cryotheum.getMaterialRGB() - 10)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .components(Cryotheum, 1)
                .build();

        RedOil = new Material.Builder(++id, gcylId("red_oil"))
                .liquid()
                .color(0x7C1500)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2N4(RP-1)NiZnFe4", true);

        RP1 = new Material.Builder(++id, gcylId("rp"))
                .liquid()
                .color(0xFF6E5D)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        TributylPhosphate = new Material.Builder(++id, gcylId("tributyl_phosphate"))
                .liquid()
                .color(0x7C5B2C)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(C4H9)3PO4", true);

        PhosphorusTrichloride = new Material.Builder(++id, gcylId("phosphorus_trichloride"))
                .liquid()
                .color((Phosphorus.getMaterialRGB() + Chlorine.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("PCl3", true);

        PhosphorylChloride = new Material.Builder(++id, gcylId("phosphoryl_chloride"))
                .liquid()
                .color(0xE6E6E6)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("POCl3", true);

        SodiumFormate = new Material.Builder(++id, gcylId("sodium_formate"))
                .liquid()
                .color(0xFFAAAA)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .components(Hydrogen, 1, Carbon, 1, Oxygen, 1, Oxygen, 1, Sodium, 1)
                .build();

        FormicAcid = new Material.Builder(++id, gcylId("formic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0xFFAA77)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .components(Carbon, 1, Hydrogen, 2, Oxygen, 2)
                .build();

        OrganicFertilizer = new Material.Builder(++id, gcylId("organic_fertilizer"))
                .dust()
                .color(0xDDDDDD)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(SHINY)
                .components(Calcium, 5, Phosphate, 3, Hydrogen, 1, Oxygen, 1)
                .build();

        CarbonFluoride = new Material.Builder(++id, gcylId("carbone_fluoride"))
                .liquid()
                .color(0xE6E6E6)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("CF4", true);

        CalciumCarbonateSolution = new Material.Builder(++id, gcylId("calcium_carbonate_solution"))
                .liquid()
                .color(Calcite.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)CaCO3", true);

        BentoniteClaySlurry = new Material.Builder(++id, gcylId("bentonite_clay_solution"))
                .liquid()
                .color(0xdbc9c5)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2O?", true);

        AluminiumChloride = new Material.Builder(++id, gcylId("aluminium_chloride"))
                .dust()
                .color((Aluminium.getMaterialRGB() + Chlorine.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("AlCl3", true);

        EthylAnthraQuinone = new Material.Builder(++id, gcylId("ethylanthraquinone"))
                .liquid()
                .color(0xFFFF00)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .components(Carbon, 16, Hydrogen, 12, Oxygen, 2)
                .build();

        EthylAnthraHydroQuinone = new Material.Builder(++id, gcylId("ethylanthrahydroquinone"))
                .liquid()
                .color(0xFFFF47)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .components(EthylAnthraQuinone, 1, Hydrogen, 2)
                .build();

        Anthracene = new Material.Builder(++id, gcylId("anthracene"))
                .liquid()
                .color(0xA2ACA2)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .components(Carbon, 14, Hydrogen, 10)
                .build();

        Chloroethanol = new Material.Builder(++id, gcylId("chloroethanol"))
                .liquid()
                .color(0xcfb050)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C2H5ClO", true);

        Trimethylamine = new Material.Builder(++id, gcylId("trimetylamine"))
                .liquid()
                .color((Dimethylamine.getMaterialRGB() + 20))
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(CH3)3N", true);

        Choline = new Material.Builder(++id, gcylId("choline"))
                .liquid()
                .color(0x63e45f)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C5H14NO", true);

        ATL = new Material.Builder(++id, gcylId("atl"))
                .liquid()
                .color(0x709c4a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("ATL", true);

        EthyleneGlycol = new Material.Builder(++id, gcylId("ethylene_glycol"))
                .liquid()
                .color(0x8080fa)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C2H6O2", true);

        CaCBaSMixture = new Material.Builder(++id, gcylId("cacbas_mixture"))
                .liquid()
                .color((CalciumCarbonateSolution.getMaterialRGB() + BariumSulfateSolution.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        LubricantClaySlurry = new Material.Builder(++id, gcylId("lubricant_clay_slurry"))
                .liquid()
                .color((Lubricant.getMaterialRGB() + BentoniteClaySlurry.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        ATLEthylene = new Material.Builder(++id, gcylId("atl_ethylene_mixture"))
                .liquid()
                .color((ATL.getMaterialRGB() + EthyleneGlycol.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        DrillingMudMixture = new Material.Builder(++id, gcylId("drilling_mud_mixture"))
                .liquid()
                .color((CaCBaSMixture.getMaterialRGB() + LubricantClaySlurry.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        WetEthyleneOxide = new Material.Builder(++id, gcylId("wet_etylene_oxide"))
                .liquid()
                .color(0x90b3ff)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)C2H4O", true);

        Phosgene = new Material.Builder(++id, gcylId("phosgene"))
                .liquid()
                .color((Chlorine.getMaterialRGB() + CarbonMonoxide.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("COCl2", true);

        TolueneDiisocyanate = new Material.Builder(++id, gcylId("toluene_diisocyanate"))
                .liquid()
                .color(0xbaf6ca)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C9H6N2O2", true);

        Polyurethane = new Material.Builder(++id, gcylId("polyurethane"))
                .ingot(2).liquid()
                .color(0xeffcef)
                .iconSet(DULL)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION, GENERATE_ROD, NO_SMASHING)
                .components(Carbon, 17, Hydrogen, 16, Nitrogen, 2, Oxygen, 4)
                .build();

        ViscoelasticPolyurethane = new Material.Builder(++id, gcylId("viscoelastic_polyurethane"))
                .liquid()
                .color(0xeffcef)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C17H16N2O4?", true);

        ViscoelasticPolyurethaneFoam = new Material.Builder(++id, gcylId("viscoelastic_polyurethane_foam"))
                .liquid()
                .color(0xeffcef)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C17H16N2O4?", true);

        RocketFuelH8N4C2O4 = new Material.Builder(++id, gcylId("rocket_fuel_a"))
                .liquid()
                .color(0x5ECB22)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .components(Hydrogen, 8, Nitrogen, 4, Carbon, 2, Oxygen, 4)
                .build();

        MonoMethylHydrazine = new Material.Builder(++id, gcylId("monomethylhydrazine"))
                .liquid()
                .color(0xFFFFFF)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .components(Carbon, 1, Hydrogen, 6, Nitrogen, 2)
                .build();

        RocketFuelCN3H7O3 = new Material.Builder(++id, gcylId("rocket_fuel_b"))
                .liquid()
                .color(0xBE46C5)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .components(Carbon, 1, Nitrogen, 3, Hydrogen, 7, Oxygen, 3)
                .build();

        DenseHydrazineFuelMixture = new Material.Builder(++id, gcylId("dense_hydrazine_fuel_mixture"))
                .liquid()
                .color(0x5E2B4A)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .components(Hydrazine, 1, Methanol, 1)
                .build();

        RP1RocketFuel = new Material.Builder(++id, gcylId("rocket_fuel_c"))
                .liquid()
                .color(0xFF503C)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .build()
                .setFormula("?O", true);

        CoalTarOil = new Material.Builder(++id, gcylId("coal_tar_oil"))
                .liquid()
                .color(0xB5B553)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .components(CoalTar, 1)
                .build();

        SulfuricCoalTarOil = new Material.Builder(++id, gcylId("sulfuric_coal_tar_oil"))
                .liquid()
                .color(0xFFFFAD)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .components(CoalTarOil, 1, SulfuricAcid, 1)
                .build();

        AluminiumHydroxide = new Material.Builder(++id, gcylId("aluminium_hydroxide"))
                .dust()
                .color(Aluminium.getMaterialRGB() - 25)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("Al(OH)3", true);

        SodiumHexafluoroaluminate = new Material.Builder(++id, gcylId("sodium_hexafluoroaluminate"))
                .liquid()
                .color((Sodium.getMaterialRGB() + Aluminium.getMaterialRGB() + Fluorine.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Na3AlF6", true);

        SodiumHydroxideBauxite = new Material.Builder(++id, gcylId("sodium_hydroxide_bauxite"))
                .liquid()
                .color(0xbf731a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Al2H2O4", true);

        ImpureAluminiumHydroxideSolution = new Material.Builder(++id, gcylId("impure_aloh_3_soution"))
                .liquid()
                .color(0xd8653e)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)Al(OH)3?", true);

        PureAluminiumHydroxideSolution = new Material.Builder(++id, gcylId("pure_aloh_3_soution"))
                .liquid()
                .color((Aluminium.getMaterialRGB() + Oxygen.getMaterialRGB() + Hydrogen.getMaterialRGB() + 40) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)Al2(OH)6", true);

        NeutralisedRedMud = new Material.Builder(++id, gcylId("neutralised_red_mud"))
                .liquid()
                .color(0xcc3300)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Fe??", true);

        RedSlurry = new Material.Builder(++id, gcylId("red_slurry"))
                .liquid()
                .color(0xcc3300)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("TiO2?", true);

        FerricREEChloride = new Material.Builder(++id, gcylId("ferric_ree_chloride"))
                .liquid()
                .color(0x30301a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Fe?", true);

        TitanylSulfate = new Material.Builder(++id, gcylId("titanyl_sulfate"))
                .liquid()
                .color(0xdc3d7c)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("TiO(SO4)", true);

        NiAlOCatalyst = new Material.Builder(++id, gcylId("nialo_catalyst"))
                .dust()
                .color(0x0af0af)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("NiAl2O4", true);

        RichNitrogenMix = new Material.Builder(++id, gcylId("rich_nitrogen_mix"))
                .liquid()
                .color(0x6891d8)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2O(CH4)?", true);

        FeCrOCatalyst = new Material.Builder(++id, gcylId("fecro_catalyst"))
                .dust()
                .color(0x8C4517)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("FeCrO3", true);

        OxidisedNitrogenMix = new Material.Builder(++id, gcylId("oxidised_nitrogen_mix"))
                .liquid()
                .color(0x708ACD)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)2(CH4)??", true);

        PurifiedNitrogenMix = new Material.Builder(++id, gcylId("purified_nitrogen_mix"))
                .liquid()
                .color(0x6891d8)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)2(CH4)?", true);

        CarbonatedEthanolamine = new Material.Builder(++id, gcylId("carbonated_ethanolamine"))
                .liquid()
                .color(0x6f7d87)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2NCH2CH2OHC", true);

        AmmoniaRichMix = new Material.Builder(++id, gcylId("ammonia_rich_mix"))
                .liquid()
                .color(0x2f5d99)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("NH3((H2O)2(CH4)?)", true);

        BariumCarbonate = new Material.Builder(++id, gcylId("barium_carbonate"))
                .dust()
                .color(Salt.getMaterialRGB() + 10)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("BaCO3", true);

        BariumAluminate = new Material.Builder(++id, gcylId("barium_aluminate"))
                .dust()
                .color(Saltpeter.getMaterialRGB() + 10)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("BaAl2O4", true);

        Barytocalcite = new Material.Builder(++id, gcylId("barytocalcite"))
                .dust(2)
                .color(0xbf9c7c)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .components(Barium, 1, Calcium, 1, Carbon, 2, Oxygen, 6)
                .build();

        Witherite = new Material.Builder(++id, gcylId("witherite"))
                .dust(2)
                .color(0xc6c29d)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .components(Barium, 1, Carbon, 1, Oxygen, 3)
                .build();

        AcryloNitrile = new Material.Builder(++id, gcylId("acrylonitrile"))
                .liquid()
                .color(0x9999ff)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("CH2CHCN", true);

        SodiumThiocyanate = new Material.Builder(++id, gcylId("sodium_thiocyanate"))
                .liquid()
                .color((Sodium.getMaterialRGB() + Sulfur.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("NaSCN", true);

        PolyacrylonitrileSolution = new Material.Builder(++id, gcylId("polyacrylonitrile_solution"))
                .liquid()
                .color(0x9999ff)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(C3H3N)n(NaSCN)", true);

        AcrylicFibers = new Material.Builder(++id, gcylId("acrylic_fibers"))
                .dust()
                .color(0xfdfdfb)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("(C5O2H8)n", true);

        MethylFormate = new Material.Builder(++id, gcylId("methyl_formate"))
                .liquid()
                .color(0Xff9999)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("HCOOCH3", true);

        WetFormamide = new Material.Builder(++id, gcylId("wet_formamide"))
                .liquid()
                .color(0x33CCFF)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)CH3NO", true);

        Formamide = new Material.Builder(++id, gcylId("formamide"))
                .liquid()
                .color(0x33CCFF)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("CH3NO", true);

        HydroxylamineDisulfate = new Material.Builder(++id, gcylId("hydroxylamine_disulfate"))
                .liquid()
                .color(0x99add6)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(NH3OH)2(NH4)2(SO4)2", true);

        Amidoxime = new Material.Builder(++id, gcylId("amidoxime"))
                .liquid()
                .color(0x66ff33)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H3N2O(CH)", true);

        SeaWater = new Material.Builder(++id, gcylId("sea_water"))
                .liquid()
                .color(0x0000FF)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2O?", true);

        PureUranylNitrateSolution = new Material.Builder(++id, gcylId("pure_uranyl_nitrate"))
                .liquid()
                .color(0x33bd45)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)UO2(NO3)2", true);

        UranylNitrate = new Material.Builder(++id, gcylId("uranyl_nitrate"))
                .dust()
                .color(0x33bd45)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("UO2(NO3)2", true);

        DiluteNitricAcid = new Material.Builder(++id, gcylId("dilute_nitric_acid"))
                .liquid()
                .color((NitricAcid.getMaterialRGB() + Water.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)HNO3", true);

        Brine = new Material.Builder(++id, gcylId("brine"))
                .liquid()
                .color(0xfcfc8a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        ConcentratedBrine = new Material.Builder(++id, gcylId("concentrated_brine"))
                .liquid()
                .color(0xfcfc95)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        BrominatedBrine = new Material.Builder(++id, gcylId("brominated_brine"))
                .liquid()
                .color(0xfdd48d)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Br?", true);

        AcidicBrominatedBrine = new Material.Builder(++id, gcylId("acidic_brominated_brine"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0xfdd48d)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2SO4)Cl?", true);

        CalciumFreeBrine = new Material.Builder(++id, gcylId("calcium_free_brine"))
                .liquid()
                .color(0xfcfca6)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        CalciumSalts = new Material.Builder(++id, gcylId("calcium_salts"))
                .dust()
                .color(Calcium.getMaterialRGB() - 10)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .components(Calcite, 1, Gypsum, 1)
                .build();

        SodiumFreeBrine = new Material.Builder(++id, gcylId("sodium_free_brine"))
                .liquid()
                .color(0xfcfcb1)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        SodiumSalts = new Material.Builder(++id, gcylId("sodium_salts"))
                .dust()
                .color(Sodium.getMaterialRGB() - 5)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("NaCl?", true);

        PotassiumFreeBrine = new Material.Builder(++id, gcylId("potassium_free_brine"))
                .liquid()
                .color(0xfcfcbc)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        PotassiumMagnesiumSalts = new Material.Builder(++id, gcylId("kmg_salts"))
                .dust()
                .color(0xcacac8)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("KClMg(SO4)K2(SO4)KF", true);

        BoronFreeSolution = new Material.Builder(++id, gcylId("boron_free_solution"))
                .liquid()
                .color(0xfcfccd)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        CalciumMagnesiumSalts = new Material.Builder(++id, gcylId("camg_salts"))
                .dust()
                .color(0xcacac8)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("Ca(CO3)(Sr(CO3))(CO2)(MgO)", true);

        SodiumLithiumSolution = new Material.Builder(++id, gcylId("sodium_lithium_solution"))
                .liquid()
                .color(0xfcfccd)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("NaLi?", true);

        SodiumAluminiumHydride = new Material.Builder(++id, gcylId("sodium_aluminium_hydride"))
                .dust()
                .color(0x98cafc)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("NaAlH4", true);

        Cellulose = new Material.Builder(++id, gcylId("cellulose"))
                .dust()
                .color(0xfefefc)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("C6H10O5", true);

        Fructose = new Material.Builder(++id, gcylId("fructose"))
                .dust()
                .color((Cellulose.getMaterialRGB() + Sugar.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("C6H12O6", true);

        SodiumAzide = new Material.Builder(++id, gcylId("sodium_azide"))
                .dust()
                .color((Sodium.getMaterialRGB() + Nitrogen.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("NaN3", true);

        LithiumHydroxideSolution = new Material.Builder(++id, gcylId("lithium_hydroxide_solution"))
                .liquid()
                .color((Lithium.getMaterialRGB() + Oxygen.getMaterialRGB() + Hydrogen.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2O)LiOH", true);

        Glucosamine = new Material.Builder(++id, gcylId("glucosamine"))
                .dust()
                .color((Cellulose.getMaterialRGB() + Water.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("C6H13NO5", true);

        Chitosan = new Material.Builder(++id, gcylId("chitosan"))
                .liquid()
                .color(0xb1bd42)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        SodiumSulfate = new Material.Builder(++id, gcylId("sodium_sulfate"))
                .dust()
                .color(0xFFFFFF)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .components(Sodium, 2, Sulfur, 1, Oxygen, 4)
                .build();

        SodiumSulfateSolution = new Material.Builder(++id, gcylId("sodium_sulfate_solution"))
                .liquid()
                .color((SodiumSulfate.getMaterialRGB() + 30))
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Na2SO4(H2O)", true);

        BoronOxide = new Material.Builder(++id, gcylId("boron_oxide"))
                .dust()
                .color((Boron.getMaterialRGB() + Oxygen.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("B2O3", true);

        Diborane = new Material.Builder(++id, gcylId("diborane"))
                .liquid()
                .color(Boron.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(BH3)2", true);

        LithiumAluminiumFluoride = new Material.Builder(++id, gcylId("lithium_aluminium_fluoride"))
                .dust()
                .color((Lithium.getMaterialRGB() + Aluminium.getMaterialRGB() + Fluorine.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("AlF4Li", true);

        CarbonSulfide = new Material.Builder(++id, gcylId("carbon_sulfide"))
                .liquid()
                .color(0x40ffbf)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("CS2", true);

        DimethylthiocarbamoilChloride = new Material.Builder(++id, gcylId("dimethylthiocarbamoil_chloride"))
                .liquid()
                .color(0xd9ff26)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(CH3)2NC(S)Cl", true);

        Mercaptophenol = new Material.Builder(++id, gcylId("mercaptophenol"))
                .liquid()
                .color(0xbaaf18)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H6OS", true);

        AmineMixture = new Material.Builder(++id, gcylId("amine_mixture"))
                .liquid()
                .color((Methanol.getMaterialRGB() - 20 + Ammonia.getMaterialRGB() - 10) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(NH3)CH4", true);

        SodiumMolybdate = new Material.Builder(++id, gcylId("sodium_molybdate"))
                .dust()
                .color(0xfcfc00)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("Na2MoO4", true);

        SodiumPhosphomolybdate = new Material.Builder(++id, gcylId("sodium_phosphomolybdate"))
                .dust()
                .color(0xfcfc00)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("(MoO3)12Na3PO4", true);

        SodiumTungstate = new Material.Builder(++id, gcylId("sodium_tungstate"))
                .liquid()
                .color(0x7a7777)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .components(Sodium, 2, Tungsten, 1, Oxygen, 4)
                .build();

        SodiumPhosphotungstate = new Material.Builder(++id, gcylId("sodium_phosphotungstate"))
                .dust()
                .color(0x7a7777)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("(WO3)12Na3PO4", true);

        Dichlorocycloctadieneplatinium = new Material.Builder(++id, gcylId("dichlorocyclooctadieneplatinium"))
                .dust()
                .color(0xe0f78a)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("C8H12Cl2Pt", true);

        IridiumCyclooctadienylChlorideDimer = new Material.Builder(++id, gcylId("iridium_cyclooctadienyl_chloride_dimer"))
                .dust()
                .color((Dichlorocycloctadieneplatinium.getMaterialRGB() + Iridium.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Ir2(C8H12)2Cl2", true);

        ChlorodiisopropylPhosphine = new Material.Builder(++id, gcylId("chlorodiisopropyl_phosphine"))
                .liquid()
                .color(0xa2c122)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        DehydrogenationCatalyst = new Material.Builder(++id, gcylId("dehydrogenation_catalyst"))
                .dust()
                .color(0x6464f5)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("?", true);

        AmmoniumPersulfate = new Material.Builder(++id, gcylId("ammonium_persulfate"))
                .liquid()
                .color(0x6464f5)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(NH4)2S2O8", true);

        PolystyreneNanoParticles = new Material.Builder(++id, gcylId("polystryrene_nanoparticles"))
                .dust()
                .color(0x888079)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("(C8H8)n", true);

        Celestine = new Material.Builder(++id, gcylId("celestine"))
                .dust()
                .color(0x9db1b8)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .components(Strontium, 1, Sulfur, 1, Oxygen, 4)
                .build();

        MagnesiumSulfate = new Material.Builder(++id, gcylId("magnesium_sulfate"))
                .dust()
                .color(0xcacac8)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("MgSO4", true);

        StrontiumCarbonate = new Material.Builder(++id, gcylId("strontium_carbonate"))
                .dust()
                .color(0xcacac8)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("SrCO3", true);

        StrontiumOxide = new Material.Builder(++id, gcylId("strontium_oxide"))
                .dust()
                .color(0xcacac8)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("SrO", true);

        ChilledBrine = new Material.Builder(++id, gcylId("chilled_brine"))
                .liquid()
                .color(0xfcfc95)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        MagnesiumContainingBrine = new Material.Builder(++id, gcylId("magnesium_containing_brine"))
                .liquid()
                .color(0xfcfcbc)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Mg?", true);

        PotassiumFluoride = new Material.Builder(++id, gcylId("potassium_fluoride"))
                .dust()
                .color(0xFDFDFD)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .components(Potassium, 1, Fluorine, 1)
                .build();

        SodiumCarbonateSolution = new Material.Builder(++id, gcylId("sodium_carbonate_solution"))
                .liquid()
                .color((SodaAsh.getMaterialRGB() + 30))
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Na2CO3(H2O)", true);

        SodiumChromateSolution = new Material.Builder(++id, gcylId("sodium_chromate_solution"))
                .liquid()
                .color(0xf2e70f)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Na2CrO4(H2O)", true);

        SodiumDichromateSolution = new Material.Builder(++id, gcylId("sodium_dichromate_solution"))
                .liquid()
                .color(0xf2750f)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Na2Cr2O7", true);

        ChromiumIIIOxide = new Material.Builder(++id, gcylId("chromium_iii_oxide"))
                .dust()
                .color(0x4bf25f)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("Cr2O3", true);

        Naphthaldehyde = new Material.Builder(++id, gcylId("napthaldehyde"))
                .liquid()
                .color(0xBCA853)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C10H7CHO", true);

        Benzophenanthrenylacetonitrile = new Material.Builder(++id, gcylId("benzophenanthrenylacetonitrile"))
                .dust()
                .color((Naphthaldehyde.getMaterialRGB() + Ethylene.getMaterialRGB() - 20) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("C20H13N", true);

        UnfoldedFullerene = new Material.Builder(++id, gcylId("unfolded_fullerene"))
                .dust()
                .color((Benzophenanthrenylacetonitrile.getMaterialRGB() + Oxygen.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("C60H30", true);

        PotassiumPeroxymonosulfate = new Material.Builder(++id, gcylId("potassium_peroxymonosulfate"))
                .dust()
                .color((PotassiumMetabisulfite.getMaterialRGB() + 20))
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("KHSO5", true);

        AuricChloride = new Material.Builder(++id, gcylId("auric_chloride"))
                .liquid()
                .color(0xdffb50)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Au2Cl6", true);

        XenonTrioxide = new Material.Builder(++id, gcylId("xenon_trioxide"))
                .liquid()
                .color(0x432791)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("XeO3", true);

        RadonTrioxide = new Material.Builder(++id, gcylId("radon_trioxide"))
                .liquid()
                .color(0x9966ff)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("RnO3", true);

        Fullerene = new Material.Builder(++id, gcylId("fullerene"))
                .dust()
                .color((UnfoldedFullerene.getMaterialRGB() - 20))
                .flags(DISABLE_REPLICATION)
                .iconSet(METALLIC)
                .build()
                .setFormula("C60", true);

        Ferrocene = new Material.Builder(++id, gcylId("ferrocene"))
                .liquid()
                .color((Water.getMaterialRGB() + Chlorine.getMaterialRGB() + Iron.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C10H10Fe", true);

        SodiumEthoxide = new Material.Builder(++id, gcylId("sodium_ethoxide"))
                .dust()
                .color((Ethanol.getMaterialRGB() + SodiumHydroxide.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(METALLIC)
                .build()
                .setFormula("C2H5ONa", true);

        PdFullereneMatrix = new Material.Builder(++id, gcylId("pd_fullerene_matrix"))
                .dust()
                .color((Palladium.getMaterialRGB() + Fullerene.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("PdC73H15NFe", true);

        Pyridine = new Material.Builder(++id, gcylId("pyridine"))
                .liquid()
                .color((Ammonia.getMaterialRGB() + Formaldehyde.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C5H5N", true);

        Dimethylaminopyridine = new Material.Builder(++id, gcylId("dimethylaminopyridine"))
                .dust()
                .color((Dimethylamine.getMaterialRGB() + Pyridine.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("(CH3)2NC5H4N", true);

        Phenylpentanoicacid = new Material.Builder(++id, gcylId("phenylpentanoicacid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color((Butene.getMaterialRGB() + CarbonMonoxide.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C11H14O2", true);

        Dimethylsulfide = new Material.Builder(++id, gcylId("dimethylsulfide"))
                .liquid()
                .color((Methanol.getMaterialRGB() + HydrogenSulfide.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(CH3)2S", true);

        PCBA = new Material.Builder(++id, gcylId("pcba"))
                .liquid()
                .color((Chlorobenzene.getMaterialRGB() + Dimethylsulfide.getMaterialRGB() + Phenylpentanoicacid.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C72H14O2", true);

        PCBS = new Material.Builder(++id, gcylId("pcbs"))
                .liquid()
                .color((Styrene.getMaterialRGB() + PCBA.getMaterialRGB() - 40) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C80H21O2", true);

        QuarkGluonPlasma = new Material.Builder(++id, gcylId("quark_gluon_plasma"))
                .liquid()
                .color(0x8f00ff)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula(makeFancy(TextFormatting.OBFUSCATED + "a" + TextFormatting.RESET + "(u2)d(c2)s(t2)bg" + TextFormatting.OBFUSCATED + "a"), true);

        HeavyQuarks = new Material.Builder(++id, gcylId("heavy_quarks"))
                .liquid()
                .color(0x008800)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula(makeFancy(TextFormatting.OBFUSCATED + "a" + TextFormatting.RESET + "(u2)ds" + TextFormatting.OBFUSCATED + "a"), true);

        LightQuarks = new Material.Builder(++id, gcylId("light_quarks"))
                .liquid()
                .color(0x0000ff)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula(makeFancy(TextFormatting.OBFUSCATED + "a" + TextFormatting.RESET + "(c2)(t2)b" + TextFormatting.OBFUSCATED + "a"), true);

        CosmicComputingMix = new Material.Builder(++id, gcylId("cosmic_computing_mix"))
                .liquid()
                .color(0xafad2f)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula(makeFancy(TextFormatting.OBFUSCATED + "aaaaa"), true);

        HeavyQuarkEnrichedMix = new Material.Builder(++id, gcylId("heavy_quark_enriched_mix"))
                .liquid()
                .color(0xefefef)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula(makeFancy(TextFormatting.OBFUSCATED + "a" + TextFormatting.RESET + "(u2)d(c2)s(t2)b" + TextFormatting.OBFUSCATED + "a"), true);

        Titanium50 = new Material.Builder(++id, gcylId("titanium_50"))
                .ingot(5).liquid()
                .color(Titanium.getMaterialRGB())
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .blast(2000)
                .build()
                .setFormula("Ti_50");

        ScandiumTitanium50Mix = new Material.Builder(++id, gcylId("scandium_titanium_50_mix"))
                .liquid()
                .color((Scandium.getMaterialRGB() + Titanium.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("ScTi_50", true);

        RadonRadiumMix = new Material.Builder(++id, gcylId("radon_radium_mix"))
                .liquid()
                .color((Radium.getMaterialRGB() + Radon.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("RnRa", true);

        DeuteriumSuperheavyMix = new Material.Builder(++id, gcylId("deuterium_superheavy_mix"))
                .liquid()
                .color(0xa2d2a4)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H_2)FlHsOg", true);

        Phenylsodium = new Material.Builder(++id, gcylId("phenylsodium"))
                .liquid()
                .color(0x2c2cc8)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H5Na", true);

        Succinaldehyde = new Material.Builder(++id, gcylId("succinaldehyde"))
                .liquid()
                .color(0x7c6d9a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C4H6O2", true);

        Difluoroaniline = new Material.Builder(++id, gcylId("difluoroaniline"))
                .liquid()
                .color(0x3fac4a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H5F2N", true);

        NDifluorophenylpyrrole = new Material.Builder(++id, gcylId("n_difluorophenylpyrrole"))
                .liquid()
                .color(0x3a9aa9)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C10H7F2N", true);

        SilverChloride = new Material.Builder(++id, gcylId("silver_chloride"))
                .dust()
                .color(0xFEFEFE)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(DULL)
                .components(Silver, 1, Chlorine, 1)
                .build();

        SilverPerchlorate = new Material.Builder(++id, gcylId("silver_perchlorate"))
                .dust()
                .color(SilverChloride.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("AgClO4", true);

        TitaniumCyclopentadienyl = new Material.Builder(++id, gcylId("titanium_cyclopentadienyl"))
                .dust()
                .color(0xbc30bc)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("(C5H5)2Cl2Ti", true);

        SodiumBromide = new Material.Builder(++id, gcylId("sodium_bromide"))
                .dust()
                .color(0xfeaffc)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("NaBr", true);

        PhotopolymerSolution = new Material.Builder(++id, gcylId("photopolymer_solution"))
                .liquid()
                .color(0x8a526d)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C149H97N10O2(TiBF20)", true);

        SodiumChlorate = new Material.Builder(++id, gcylId("sodium_chlorate"))
                .dust()
                .color(Salt.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("NaClO3", true);

        SodiumPerchlorate = new Material.Builder(++id, gcylId("sodium_perchlorate"))
                .dust()
                .color(Salt.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("NaClO4", true);

        GermaniumOxide = new Material.Builder(++id, gcylId("germanium_oxide"))
                .dust()
                .color((Germanium.getMaterialRGB() + 10))
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("GeO2", true);

        GermaniumSulfide = new Material.Builder(++id, gcylId("germanium_sulfide"))
                .dust()
                .color(GermaniumOxide.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("GeS2", true);

        TungstenTrioxide = new Material.Builder(++id, gcylId("tungsten_trioxide"))
                .dust()
                .color(0x99FF97)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(Tungsten.getMaterialIconSet())
                .components(Tungsten, 1, Oxygen, 3)
                .build();

        CadmiumSulfide = new Material.Builder(++id, gcylId("cadmium_sulfide"))
                .dust()
                .color(0xffff00)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("CdS", true);

        CalciumTungstate = new Material.Builder(++id, gcylId("calcium_tungstate"))
                .dust()
                .color(0x6e6867)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .components(Tungsten, 1, Calcium, 1)
                .build();

        CadmiumTungstate = new Material.Builder(++id, gcylId("cadmium_tungstate"))
                .dust()
                .color(CalciumTungstate.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("CdWO4", true);

        CesiumIodide = new Material.Builder(++id, gcylId("cesium_iodide"))
                .dust()
                .color(CaesiumHydroxide.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("CsI", true);

        TlTmCesiumIodide = new Material.Builder(++id, gcylId("tl_tm_cesium_iodide"))
                .dust()
                .color(CaesiumHydroxide.getMaterialRGB() * 9 / 10 + Thallium.getMaterialRGB() / 10)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("CsITlTm", true);

        PolycyclicAromaticMix = new Material.Builder(++id, gcylId("polycyclic_aromatic_mix"))
                .dust()
                .color(Tetracene.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("C18H12", true);

        BismuthNitrateSoluton = new Material.Builder(++id, gcylId("bismuth_nitrate_solution"))
                .liquid()
                .color((Bismuth.getMaterialRGB() + Chlorine.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)Bi(NO3)3", true);

        BismuthGermanate = new Material.Builder(++id, gcylId("bismuth_germanate"))
                .dust()
                .color(0x94cf5c)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("Bi12GeO20", true);

        DisodiumPhosphate = new Material.Builder(++id, gcylId("sodium_diphosphate"))
                .dust()
                .color((Sodium.getMaterialRGB() + Phosphorus.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("Na2HPO4", true);

        Cyanonaphthalene = new Material.Builder(++id, gcylId("cyanonaphthalene"))
                .dust()
                .color((SodiumCyanide.getMaterialRGB() + Naphthalene.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("C11H7N", true);

        TinChloride = new Material.Builder(++id, gcylId("tin_chloride"))
                .dust()
                .color(0x8c8075)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("(CH3)3SnCl", true);

        Triphenylphosphine = new Material.Builder(++id, gcylId("triphenylphosphine"))
                .dust()
                .color((Chlorobenzene.getMaterialRGB() + PhosphorusTrichloride.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("(C6H5)3P", true);

        Methylbenzophenanthrene = new Material.Builder(++id, gcylId("methylbenzophenanthrene"))
                .dust()
                .color((Naphthaldehyde.getMaterialRGB() + Ethylbenzene.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("C19H14", true);

        PotassiumCyanide = new Material.Builder(++id, gcylId("potassium_cyanide"))
                .dust()
                .color((Potassium.getMaterialRGB() + Nitrogen.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("KCN", true);

        TiAlChloride = new Material.Builder(++id, gcylId("tial_chloride"))
                .dust()
                .color((Titanium.getMaterialRGB() + Aluminium.getMaterialRGB() + Chlorine.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("TiAlCl7", true);

        ThionylChloride = new Material.Builder(++id, gcylId("thionyl_chloride"))
                .liquid()
                .color(0xF9F7E5)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("SOCl2", true);

        ZeoliteSievingPellets = new Material.Builder(++id, gcylId("zeolite_sieving_pellets"))
                .dust()
                .color(0xa17bd1)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("Al2O3SiO2", true);

        WetZeoliteSievingPellets = new Material.Builder(++id, gcylId("wet_zeolite_sieving_pellets"))
                .dust()
                .color(0x392f45)
                .flags(DISABLE_REPLICATION)
                .iconSet(METALLIC)
                .build()
                .setFormula("Al2O3SiO2?", true);

        PdIrReOCeOS = new Material.Builder(++id, gcylId("pdirreoceos"))
                .dust()
                .color((Palladium.getMaterialRGB() + Iridium.getMaterialRGB() + Rhenium.getMaterialRGB() + Cerium.getMaterialRGB() + Osmium.getMaterialRGB() + Silicon.getMaterialRGB() + Oxygen.getMaterialRGB()) / 7)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("PdIrReCeOsSiO4", true);

        Ferrocenylfulleropyrrolidine = new Material.Builder(++id, gcylId("ferrocenylfulleropyrddolidine"))
                .liquid()
                .color((Ferrocene.getMaterialRGB() + Ethylene.getMaterialRGB() + CarbonMonoxide.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C74H19FeN", true);

        BenzoylChloride = new Material.Builder(++id, gcylId("benzoyl_chloride"))
                .liquid()
                .color((Toluene.getMaterialRGB() + ThionylChloride.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C7H5ClO", true);

        BenzoylPeroxide = new Material.Builder(++id, gcylId("benzoyl_peroxide"))
                .liquid()
                .color((Barium.getMaterialRGB() + BenzoylChloride.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C14H10O4", true);

        HydroiodicAcid = new Material.Builder(++id, gcylId("hydroiodic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(Hydrogen.getMaterialRGB() / 2 + Iodine.getMaterialRGB() / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("HI :)", true);

        TrimethyltinChloride = new Material.Builder(++id, gcylId("trimethyltin_chloride"))
                .liquid()
                .color(0x8c8075)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(CH3)3SnCl", true);

        Diisopropylcarbodiimide = new Material.Builder(++id, gcylId("diisopropylcarbodiimide"))
                .liquid()
                .color(0xA0CFFE)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C7H14N2", true);

        Hexanediol = new Material.Builder(++id, gcylId("hexanediol"))
                .liquid()
                .color(EthyleneGlycol.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H14O2", true);

        Hexamethylenediamine = new Material.Builder(++id, gcylId("hexamethylenediamine"))
                .liquid()
                .color(Ethylenediamine.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H16N2", true);

        SaccharicAcid = new Material.Builder(++id, gcylId("saccharic_acid"))
                .dust()
                .color(Glucose.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(METALLIC)
                .build()
                .setFormula("C6H10O8", true);

        AdipicAcid = new Material.Builder(++id, gcylId("adipic_acid"))
                .dust()
                .color(0xda9288)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("C6H10O4", true);

        Tertbutanol = new Material.Builder(++id, gcylId("tertbutanol"))
                .liquid()
                .color(0xcccc2c)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C4H10O", true);

        DitertbutylDicarbonate = new Material.Builder(++id, gcylId("ditertbutyl_dicarbonate"))
                .dust()
                .color(0xccccf6)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("C10H18O5", true);

        Triaminoethaneamine = new Material.Builder(++id, gcylId("triaminoethaneamine"))
                .liquid()
                .color(0x6f7d87)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(NH2CH2CH2)3N", true);

        TertButylAzidoformate = new Material.Builder(++id, gcylId("tertbuthylcarbonylazide"))
                .liquid()
                .color(0x888818)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C5H9N3O2", true);

        AminatedFullerene = new Material.Builder(++id, gcylId("aminated_fullerene"))
                .liquid()
                .color(0x2c2caa)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C60N12H12", true);

        Azafullerene = new Material.Builder(++id, gcylId("azafullerene"))
                .liquid()
                .color(0x8a7a1a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C60N12H12", true);

        GrapheneGelSuspension = new Material.Builder(++id, gcylId("graphene_gel_suspension"))
                .dust()
                .color(0xadadad)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("C", true);

        DryGrapheneGel = new Material.Builder(++id, gcylId("dry_graphene_gel"))
                .dust()
                .color(0x3a3ada)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("C", true);

        SupercriticalCO2 = new Material.Builder(++id, gcylId("supercritcal_co_2"))
                .liquid()
                .color(CarbonDioxide.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("CO2", true);

        Caliche = new Material.Builder(++id, gcylId("caliche"))
                .dust(3)
                .color(0xeb9e3f)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(DULL)
                .components(SodiumNitrate, 1, Potassium, 1, Nitrogen, 1, Oxygen, 3, RockSalt, 1, Sodium, 1, Iodine, 1, Oxygen, 3)
                .build();

        CalicheIodateBrine = new Material.Builder(++id, gcylId("caliche_iodate_brine"))
                .liquid()
                .color(0xffe6660)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)NaNO3KNO3KClNaIO3", true);

        IodideSolution = new Material.Builder(++id, gcylId("iodide_solution"))
                .liquid()
                .color(0x08081c)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)NaNO3KNO3KClNaI", true);

        CalicheIodineBrine = new Material.Builder(++id, gcylId("caliche_iodine_brine"))
                .liquid()
                .color(0xffe6660)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)NaNO3KNO3KClNaOHI", true);

        CalicheNitrateSolution = new Material.Builder(++id, gcylId("caliche_nitrate_solution"))
                .liquid()
                .color(0xffe6660)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)NaNO3KNO3KClNaOH", true);

        KeroseneIodineSolution = new Material.Builder(++id, gcylId("kerosene_iodine_solution"))
                .liquid()
                .color(0x08081c)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C12H26I", true);

        IodizedOil = new Material.Builder(++id, gcylId("iodized_oil"))
                .liquid()
                .color(0x666666)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        IodizedBrine = new Material.Builder(++id, gcylId("iodized_brine"))
                .liquid()
                .color(0x525242)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("I?", true);

        IodineBrineMix = new Material.Builder(++id, gcylId("iodine_brine_mix"))
                .liquid()
                .color(0x525242)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("I??", true);

        IodineSlurry = new Material.Builder(++id, gcylId("iodine_slurry"))
                .liquid()
                .color(0x08081c)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("I?", true);

        RoastedSpodumene = new Material.Builder(++id, gcylId("roasted_spodumene"))
                .dust()
                .color(0x3d3d29)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("LiAlSi2O6", true);

        RoastedLepidolite = new Material.Builder(++id, gcylId("roasted_lepidolite"))
                .dust()
                .color(0x470024)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("KLi3Al4O11", true);

        Fluorite = new Material.Builder(++id, gcylId("fluorite"))
                .dust(2).liquid()
                .color(0x009933)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .components(Calcium, 1, Fluorine, 2)
                .build();

        DissolvedLithiumOre = new Material.Builder(++id, gcylId("dissolved_lithium_ores"))
                .liquid()
                .color(0x664850)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("LiAlO2(H2SO4)", true);

        AluminiumSulfate = new Material.Builder(++id, gcylId("aluminium_sulfate"))
                .dust()
                .color((Aluminium.getMaterialRGB() + Sulfur.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Al2(SO4)3", true);

        LithiumCarbonateSolution = new Material.Builder(++id, gcylId("lithium_carbonate_solution"))
                .liquid()
                .color((Lithium.getMaterialRGB() + Carbon.getMaterialRGB() + Oxygen.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Li2CO3(H2O)", true);

        Diiodobiphenyl = new Material.Builder(++id, gcylId("diiodobiphenyl"))
                .dust()
                .color(0x000f66)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("C12H8I2", true);

        ThalliumChloride = new Material.Builder(++id, gcylId("thallium_chloride"))
                .dust()
                .color((Thallium.getMaterialRGB() + Chlorine.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("TlCl", true);

        NiAlCatalyst = new Material.Builder(++id, gcylId("nial_catalyst"))
                .dust()
                .color(0x6ea2ff)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("NiAl", true);

        Bipyridine = new Material.Builder(++id, gcylId("bipyridine"))
                .dust()
                .color(0X978662)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("C10H8N2", true);

        Dibenzylideneacetone = new Material.Builder(++id, gcylId("dibenzylideneacetone"))
                .liquid()
                .color(0Xcc6699)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C17H14O", true);

        PalladiumBisDibenzylidieneacetone = new Material.Builder(++id, gcylId("palladium_bisdibenzylidieneacetone"))
                .dust()
                .color(0Xbe81a0)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("C51H42O3Pd2", true);

        ChloroPlatinicAcid = new Material.Builder(++id, gcylId("chloroplatinic_acid"))
                .liquid()
                .color(0xffba54)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2PtCl6", true);

        PotassiumTetrachloroplatinate = new Material.Builder(++id, gcylId("potassium_tetrachloroplatinate"))
                .dust()
                .color(0xffba54)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("K2PtCl4", true);

        NickelChloride = new Material.Builder(++id, gcylId("nickel_chloride"))
                .dust()
                .color(Nickel.getMaterialRGB() + 10)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("NiCl2", true);

        NickelTriphenylPhosphite = new Material.Builder(++id, gcylId("nickel_triphenyl_phosphite"))
                .dust()
                .color(0xd9d973)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("C36H30Cl2NiP2", true);

        GrapheneNanotubeMix = new Material.Builder(++id, gcylId("graphene_nanotube_mix"))
                .dust()
                .color(0x2c2c2c)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("(C)C?", true);

        GrapheneAlignedCNT = new Material.Builder(++id, gcylId("graphene_aligned_cnt"))
                .dust()
                .color(0x2c2c2c)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("(C)C30H20", true);

        Rhodocrosite = new Material.Builder(++id, gcylId("rhodocrosite"))
                .dust(2)
                .color(0xff6699)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .components(Manganese, 1, Carbon, 1, Oxygen, 3)
                .build();

        CassiteriteCokePellets = new Material.Builder(++id, gcylId("cassiterite_coke_pellets"))
                .dust()
                .color(0x8f8f8f)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("SnO2C?", true);

        TinSlag = new Material.Builder(++id, gcylId("tin_slag"))
                .dust()
                .color(0xc8b9a9)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("NbTa?", true);

        NbTaContainingDust = new Material.Builder(++id, gcylId("nbta_containing_dust"))
                .dust()
                .color(0xc8b9a9)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("NbTa", true);

        NiobiumTantalumOxide = new Material.Builder(++id, gcylId("niobium_tantalum_oxide"))
                .dust()
                .color((Niobium.getMaterialRGB() + Tantalum.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("(Nb2O5)(Ta2O5)", true);

        NbTaFluorideMix = new Material.Builder(++id, gcylId("nbta_fluoride_mix"))
                .liquid()
                .color(0xbcac93)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2NbOF5)(H2TaF7)", true);

        Columbite = new Material.Builder(++id, gcylId("columbite"))
                .dust(2)
                .color(0xCCCC00)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .components(Iron, 1, Niobium, 2, Oxygen, 6)
                .build();

        FusedColumbite = new Material.Builder(++id, gcylId("fused_columbite"))
                .dust()
                .color(0xCCCC00)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("(Fe2O3)(NaO)Nb2O5", true);

        BariumPeroxide = new Material.Builder(++id, gcylId("barium_peroxide"))
                .dust()
                .color((Barium.getMaterialRGB() + Oxygen.getMaterialRGB() - 30) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("BaO2", true);

        ColumbiteMinorOxideResidue = new Material.Builder(++id, gcylId("columbite_minor_oxide_residue"))
                .dust()
                .color(0x915028)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("(BaO)(SnO2)(WO3)(Al2O3)", true);

        IronSulfateDust = new Material.Builder(++id, gcylId("iron_sulfate_dust"))
                .dust()
                .color((Iron.getMaterialRGB() + Sulfur.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("FeSO4", true);

        FusedTantalite = new Material.Builder(++id, gcylId("fused_tantalite"))
                .dust()
                .color(0x915028)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("(Fe2O3)(NaO)Ta2O5", true);

        TantaliteMinorOxideResidue = new Material.Builder(++id, gcylId("tantalite_minor_oxide_residue"))
                .dust()
                .color(0xCCCC00)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("(BaO)(ZrO2)(TiO2)(SiO2)", true);

        LeachedTantalite = new Material.Builder(++id, gcylId("leached_tantalite"))
                .dust()
                .color(0x915028)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("(Ta2O5)9Nb2O5?", true);

        FluorotantalicAcid = new Material.Builder(++id, gcylId("flurotantalic_acid"))
                .liquid()
                .color(0x73ff00)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("TaHF7", true);

        NbTaSeparationMixture = new Material.Builder(++id, gcylId("nbta_separation_mixture"))
                .liquid()
                .color(0xbcac93)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C18H39O5P", true);

        AcidicLeachedPyrochlore = new Material.Builder(++id, gcylId("acidic_leached_pyrochlore"))
                .dust()
                .color(0x996633)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("(H2SO4)Ca12Sr6Ba6?ThUNb26O78F26", true);

        REEThUSulfateSolution = new Material.Builder(++id, gcylId("reethu_sulfate_solution"))
                .liquid()
                .color(0x89be5c)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?SO4", true);

        AlkalineEarthSulfateSolution = new Material.Builder(++id, gcylId("alkalineearth_sulfate"))
                .liquid()
                .color(0xe6ebff)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?SO4", true);

        UranylThoriumNitrate = new Material.Builder(++id, gcylId("uranium_thorium_nitrate"))
                .dust()
                .color(0xe7e848)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("UO2(NO3)2Th(NO3)4", true);

        RareEarthNitrateSolution = new Material.Builder(++id, gcylId("rare_earth_nitrate_solution"))
                .liquid()
                .color(0xcfb37d)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?NO3", true);

        OxypentafluoroNiobate = new Material.Builder(++id, gcylId("oxypentafluoroniobate"))
                .liquid()
                .color(0x73ff00)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2NbOF5", true);

        HeptafluoroTantalate = new Material.Builder(++id, gcylId("heptafluorotantalate"))
                .liquid()
                .color(0x73ff00)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2TaF7", true);

        PotasssiumFluoroNiobate = new Material.Builder(++id, gcylId("potassium_fluoroniobate"))
                .dust()
                .color(0x73ff00)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("K2NbF7", true);

        AluminoSilicateWool = new Material.Builder(++id, gcylId("alumino_silicate_wool"))
                .dust(1)
                .color(0xbbbbbb)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(SAND)
                .components(Aluminium, 2, Silicon, 1, Oxygen, 5)
                .build();

        ScandiumTriflate = new Material.Builder(++id, gcylId("scandium_triflate"))
                .dust()
                .color(0xdfcfcf)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Sc(OSO2CF3)3", true);

        Toluenesulfonate = new Material.Builder(++id, gcylId("toluenesulfonate"))
                .liquid()
                .color(0x8f8f00)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C7H7SO3Na", true);

        Niter = new Material.Builder(++id, gcylId("niter"))
                .dust(1)
                .color(16763080)
                .flags(DISABLE_REPLICATION, NO_SMASHING, NO_SMELTING)
                .iconSet(FLINT)
                .components(Saltpeter, 1)
                .build();

        ElectricallyImpureCopper = new Material.Builder(++id, gcylId("electrically_impure_copper"))
                .ingot(2).liquid()
                .color(0x765A30)
                .iconSet(DULL)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION, GENERATE_PLATE)
                .components(Copper, 1, RareEarth, 1)
                .build();

        CopperRefiningSolution = new Material.Builder(++id, gcylId("copper_refining_solution"))
                .liquid()
                .color(0x765A30)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("CuH2SO4", true);

        AnodicSlime = new Material.Builder(++id, gcylId("anodic_slime"))
                .dust()
                .color(0x765A30)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("SeTe??", true);

        SeleniteTelluriteMix = new Material.Builder(++id, gcylId("selenite_tellurite_mixture"))
                .liquid()
                .color(0x765A30)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("TeO2SeO2(Na2CO3)2", true);

        TelluriumOxide = new Material.Builder(++id, gcylId("tellurium_oxide"))
                .dust()
                .color(0xFFFF66)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("TeO2", true);

        SeleniteSolution = new Material.Builder(++id, gcylId("selenite_solution"))
                .liquid()
                .color(0xc1c46a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Na2SeO3", true);

        HydroselenicAcid = new Material.Builder(++id, gcylId("hydroselenic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(Selenium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2Se", true);

        IronPlatinumCatalyst = new Material.Builder(++id, gcylId("iron_platinum_catalyst"))
                .dust()
                .color(Iron.getMaterialRGB() / 2 + Platinum.getMaterialRGB() / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("FePt", true);

        Hydroxyquinoline = new Material.Builder(++id, gcylId("hydroxyquinoline"))
                .liquid()
                .color(0x3a9a71)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C9H7NO", true);

        SodiumRuthenate = new Material.Builder(++id, gcylId("sodium_ruthenate"))
                .dust(2)
                .color(0x3A40CB)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(SHINY)
                .components(Sodium, 2, Oxygen, 4, Ruthenium, 1)
                .build();

        IridiumDioxide = new Material.Builder(++id, gcylId("iridium_dioxide"))
                .dust()
                .color((Iridium.getMaterialRGB() + Oxygen.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION, EXCLUDE_BLOCK_CRAFTING_RECIPES, DISABLE_DECOMPOSITION)
                .iconSet(DULL)
                .components(Iridium, 1, Oxygen, 2)
                .build();

        BariumChlorideSolution = new Material.Builder(++id, gcylId("barium_chloride_solution"))
                .liquid()
                .color((Barium.getMaterialRGB() + Chlorine.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)BaCl3", true);

        BariumTitanatePreparation = new Material.Builder(++id, gcylId("barium_titanate_preparation"))
                .liquid()
                .color(0x99FF99)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("BaTiO3", true);

        Perbromothiophene = new Material.Builder(++id, gcylId("perbromothiophene"))
                .liquid()
                .color(0x87cc17)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C4Br4S", true);

        Diethoxythiophene = new Material.Builder(++id, gcylId("dietoxythiophene"))
                .liquid()
                .color(0x90ff43)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C4H2(OC2H5)2S", true);

        EDOT = new Material.Builder(++id, gcylId("ethylenedioxythiophene"))
                .liquid()
                .color(0x7a9996)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C2H4O2C4H2S", true);

        ZirconylChloride = new Material.Builder(++id, gcylId("zirconyl_chloride"))
                .dust()
                .color(ZirconiumTetrachloride.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("ZrOCl2", true);

        WoodsGlass = new Material.Builder(++id, gcylId("woods_glass"))
                .ingot(2).liquid()
                .color(0x730099)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION, GENERATE_PLATE, DISABLE_DECOMPOSITION)
                .components(SiliconDioxide, 1, Barium, 1, Oxygen, 1, Garnierite, 1, SodaAsh, 1)
                .build();

        IronIodide = new Material.Builder(++id, gcylId("iron_iodide"))
                .dust()
                .color((Iron.getMaterialRGB() + Iodine.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("FeI2", true);

        ThalliumIodide = new Material.Builder(++id, gcylId("thallium_iodide"))
                .dust()
                .color((Thallium.getMaterialRGB() + Iodine.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("TlI", true);

        RubidiumIodide = new Material.Builder(++id, gcylId("rubidium_iodide"))
                .dust()
                .color((Rubidium.getMaterialRGB() + Iodine.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("RbI", true);

        IndiumIodide = new Material.Builder(++id, gcylId("indium_iodide"))
                .dust()
                .color((Indium.getMaterialRGB() + Iodine.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("InI3", true);

        GalliumIodide = new Material.Builder(++id, gcylId("gallium_iodide"))
                .dust()
                .color((Gallium.getMaterialRGB() + Iodine.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("GaI3", true);

        ScandiumIodide = new Material.Builder(++id, gcylId("scandium_iodide"))
                .dust()
                .color((Scandium.getMaterialRGB() + Iodine.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("ScI3", true);

        IronCarbonyl = new Material.Builder(++id, gcylId("iron_carbonyl"))
                .liquid()
                .color(0xff8000)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Fe?", true);

        PurifiedIronCarbonyl = new Material.Builder(++id, gcylId("purified_iron_carbonyl"))
                .liquid()
                .color(0xff8000)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Fe", true);

        CarbonylPurifiedIron = new Material.Builder(++id, gcylId("carbonyl_purified_iron"))
                .dust()
                .color(Iron.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Fe", true);

        YttriumEuropiumVanadate = new Material.Builder(++id, gcylId("yttrium_europium_vanadate"))
                .dust()
                .color((Yttrium.getMaterialRGB() + Europium.getMaterialRGB() + Vanadium.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("YEuVO4", true);

        StrontiumChloride = new Material.Builder(++id, gcylId("strontium_chloride"))
                .dust()
                .color(0x3a9aba)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("SrCl2", true);

        StrontiumEuropiumAluminate = new Material.Builder(++id, gcylId("strontium_europium_aluminate"))
                .dust()
                .color((Strontium.getMaterialRGB() + Europium.getMaterialRGB() + Aluminium.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("SrEuAl2O4", true);

        GreenHalideMix = new Material.Builder(++id, gcylId("green_halide_mix"))
                .dust()
                .color((ThalliumIodide.getMaterialRGB() + PotassiumIodide.getMaterialRGB() + Mercury.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Hg(TlI)KI", true);

        RedHalideMix = new Material.Builder(++id, gcylId("red_halide_mix"))
                .dust()
                .color((RubidiumIodide.getMaterialRGB() + PotassiumIodide.getMaterialRGB() + Mercury.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Hg(RbI)KI", true);

        BlueHalideMix = new Material.Builder(++id, gcylId("blue_halide_mix"))
                .dust()
                .color((IndiumIodide.getMaterialRGB() + PotassiumIodide.getMaterialRGB() + Mercury.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Hg(InI3)KI", true);

        WhiteHalideMix = new Material.Builder(++id, gcylId("white_halide_mix"))
                .dust()
                .color((ScandiumIodide.getMaterialRGB() + PotassiumIodide.getMaterialRGB() + Mercury.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Hg(ScI3)KI", true);

        UVAHalideMix = new Material.Builder(++id, gcylId("uva_halide_mix"))
                .dust()
                .color((GalliumIodide.getMaterialRGB() + PotassiumIodide.getMaterialRGB() + Mercury.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Hg(GaI3)KI", true);

        ThoriumDopedTungsten = new Material.Builder(++id, gcylId("thoria_doped_tungsten"))
                .ingot(2).liquid()
                .color(Tungsten.getMaterialRGB())
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION, GENERATE_ROD, GENERATE_FINE_WIRE, DISABLE_DECOMPOSITION)
                .components(Thorium, 1, Tungsten, 9)
                .build();

        MaragingSteel250 = new Material.Builder(++id, gcylId("maraging_steel_a"))
                .ingot(6).liquid()
                .color(0x92918D)
                .iconSet(METALLIC)
                .flags(DISABLE_REPLICATION, GENERATE_FRAME)
                .components(Steel, 16, Molybdenum, 1, Titanium, 1, Nickel, 4, Cobalt, 2)
                .blast(2413)
                .build();

        Biperfluoromethanedisulfide = new Material.Builder(++id, gcylId("biperfluoromethanedisulfide"))
                .liquid()
                .color(0x3ada40)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C2F6S2", true);
    }

    private static void register2() {

        BariumTriflateSolution = new Material.Builder(++id, gcylId("barium_triflate_solution"))
                .liquid()
                .color((Barium.getMaterialRGB() + Fluorine.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)3(Hg)C2BaF6O6S2", true);

        BariumTriflate = new Material.Builder(++id, gcylId("barium_triflate"))
                .dust()
                .color((Barium.getMaterialRGB() + Fluorine.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Ba(OSO2CF3)2", true);

        BariumNitrate = new Material.Builder(++id, gcylId("barium_nitrate"))
                .dust()
                .color((Barium.getMaterialRGB() + NitricAcid.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Ba(NO3)2", true);

        CopperNitrate = new Material.Builder(++id, gcylId("copper_nitrate"))
                .dust()
                .color(0xcaecec)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Cu(NO3)2", true);

        YttriumNitrate = new Material.Builder(++id, gcylId("yttrium_nitrate"))
                .dust()
                .color(0xdadafc)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Y(NO3)3", true);

        CitricAcid = new Material.Builder(++id, gcylId("citric_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0xffcc00)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H8O7", true);

        WellMixedYBCOxides = new Material.Builder(++id, gcylId("well_mixed_ybc_oxides"))
                .dust()
                .color(0x2c3429)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("YBa2Cu3O6", true);

        PiledTBCC = new Material.Builder(++id, gcylId("piled_tbcc"))
                .dust()
                .color(0x669900)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Tl2Ba2Cu3Ca2", true);

        ActiniumOxalate = new Material.Builder(++id, gcylId("actinium_oxalate"))
                .dust()
                .color(Actinium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Ac(CO2)4", true);

        ActiniumHydride = new Material.Builder(++id, gcylId("actinium_hydride"))
                .dust()
                .color(Actinium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("AcH3", true);

        ActiniumSuperhydridePlasma = new Material.Builder(++id, gcylId("actinium_superhydride_plasma"))
                .plasma()
                .color(Actinium.getMaterialRGB() * 9 / 8)
                .flags(DISABLE_REPLICATION, DISABLE_DECONSTRUCTION)
                .iconSet(FLUID)
                .build()
                .setFormula("AcH12", true);

        LanthanumEmbeddedFullerene = new Material.Builder(++id, gcylId("lanthanum_embedded_fullerene"))
                .dust()
                .color(0x99cc00)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("(C60)2La2", true);

        Dibromoacrolein = new Material.Builder(++id, gcylId("dibromoacrolein"))
                .liquid()
                .color(0x4a4a4a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C2H2Br2O2", true);

        SodiumThiosulfate = new Material.Builder(++id, gcylId("sodium_thiosulfate"))
                .dust()
                .color(0x2090fc)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("Na2S2O3", true);

        Chloroethane = new Material.Builder(++id, gcylId("chloroethane"))
                .liquid()
                .color(0x33aa33)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("CH3CH2Cl", true);

        Bromohydrothiine = new Material.Builder(++id, gcylId("bromodihydrothiine"))
                .liquid()
                .color(0x40ff3a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C4H4S2Br2", true);

        Bromobutane = new Material.Builder(++id, gcylId("bromobutane"))
                .liquid()
                .color(0xff3333)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("CH3(CH2)3Br", true);

        Lithiumthiinediselenide = new Material.Builder(++id, gcylId("lithiumthiinediselenide"))
                .dust()
                .color(0x7ada00)
                .flags(DISABLE_REPLICATION)
                .iconSet(METALLIC)
                .build()
                .setFormula("C4H4S2Li2Se2", true);

        Propadiene = new Material.Builder(++id, gcylId("propadiene"))
                .liquid()
                .color((Butanol.getMaterialRGB() - 20))
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C3H4", true);

        BETS = new Material.Builder(++id, gcylId("bets"))
                .dust()
                .color(0x7ada00)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("C10H8S4Se4", true);

        FranciumCarbide = new Material.Builder(++id, gcylId("francium_carbide"))
                .dust()
                .color(Francium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Fr2C2", true);

        BoronFranciumCarbide = new Material.Builder(++id, gcylId("boron_francium_carbide"))
                .dust()
                .color(0x808080)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Fr4B4C7", true);

        AstatideSolution = new Material.Builder(++id, gcylId("astatide_solution"))
                .liquid()
                .color(0x6df63f)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("At(H2O)(SO3)", true);

        MixedAstatideSalts = new Material.Builder(++id, gcylId("mixed_astatide_salts"))
                .dust()
                .color(0x6df63f)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("(At3)(Ho)(Th)(Cn)(Fl)", true);

        SodiumIodate = new Material.Builder(++id, gcylId("sodium_iodate"))
                .dust()
                .color(0x11116d)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("NaIO3", true);

        CopperSulfateSolution = new Material.Builder(++id, gcylId("blue_vitriol_water_solution"))
                .liquid()
                .color(4761024)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .components(Copper, 1, Sulfur, 1, Oxygen, 4, Water, 5)
                .build();

        SodiumPeriodate = new Material.Builder(++id, gcylId("sodium_periodate"))
                .dust()
                .color(0x11116d)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("NaIO4", true);

        StrontiumSuperconductorDust = new Material.Builder(++id, gcylId("strontium_superconductor_dust"))
                .dust()
                .color(0x45abf4)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Sr2RuSgO8", true);

        KryptonDifluoride = new Material.Builder(++id, gcylId("krypton_difluoride"))
                .liquid()
                .color(Krypton.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("KrF2", true);

        ManganeseFluoride = new Material.Builder(++id, gcylId("manganese_fluoride"))
                .dust()
                .color(Pyrolusite.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("MnF2", true);

        PhenylenedioxydiaceticAcid = new Material.Builder(++id, gcylId("phenylenedioxydiacetic_acid"))
                .liquid()
                .color(0x99546a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C10H10O6", true);

        Diethylthiourea = new Material.Builder(++id, gcylId("diethylthiourea"))
                .liquid()
                .color(0x2acaa4)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(C2H5NH)2CS", true);

        Isophthaloylbisdiethylthiourea = new Material.Builder(++id, gcylId("isophthaloylbisdiethylthiourea"))
                .liquid()
                .color(0x8a7b9c)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C18H26N4O2S2", true);

        HassiumChloride = new Material.Builder(++id, gcylId("hassium_chloride"))
                .dust()
                .color(0x5d2abc)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("HsCl4", true);

        RheniumChloride = new Material.Builder(++id, gcylId("rhenium_chloride"))
                .dust()
                .color(0x3c2a5c)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("ReCl5", true);

        AntimonyPentafluoride = new Material.Builder(++id, gcylId("antimony_pentafluoride"))
                .liquid()
                .color(Antimony.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("SbF5", true);

        AntimonyTrichloride = new Material.Builder(++id, gcylId("antimony_trichloride"))
                .dust()
                .color(AntimonyTrifluoride.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("SbCl3", true);

        FluorophosphoricAcid = new Material.Builder(++id, gcylId("fluorophosphoric_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(PhosphorusTrichloride.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("HPF6", true);

        ChargedCaesiumCeriumCobaltIndium = new Material.Builder(++id, gcylId("charged_caesium_cerium_cobalt_indium"))
                .dust()
                .color(0x52ad25)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("CsCeCo2In10", true);

        VanadiumSlag = new Material.Builder(++id, gcylId("vanadium_slag"))
                .dust()
                .color((Vanadium.getMaterialRGB() + Titanium.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("(VO)C(TiO2)", true);

        VanadiumSlagDust = new Material.Builder(++id, gcylId("vanadium_slag_dust"))
                .dust()
                .color(0xf2ef1b)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("VO", true);

        VanadiumWasteSolution = new Material.Builder(++id, gcylId("vanadium_waste_solution"))
                .liquid()
                .color(0xbf95f5)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("NaCl(Na2SO4)(SiO2)(Al(OH)3)", true);

        PropargylAlcohol = new Material.Builder(++id, gcylId("propargyl_alcohol"))
                .liquid()
                .color(0xbfb32a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("CHCCH2OH", true);

        PropargylChloride = new Material.Builder(++id, gcylId("propargyl_chloride"))
                .liquid()
                .color(0x918924)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("HC2CH2Cl", true);

        Resin = new Material.Builder(++id, gcylId("resin"))
                .liquid()
                .color(0x3d2f11)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        Turpentine = new Material.Builder(++id, gcylId("turpentine"))
                .liquid()
                .color(0x93bd46)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C10H16", true);

        BetaPinene = new Material.Builder(++id, gcylId("beta_pinene"))
                .dust()
                .color(0x61ad6b)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("C10H16", true);

        Citral = new Material.Builder(++id, gcylId("citral"))
                .liquid()
                .color(0xf2e541)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C10H16O", true);

        BetaIonone = new Material.Builder(++id, gcylId("beta_ionone"))
                .liquid()
                .color(0xdc5ce6)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C13H20O", true);

        VitaminA = new Material.Builder(++id, gcylId("vitamin_a"))
                .liquid()
                .color(0x8d5c91)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C20H30O", true);

        Yeast = new Material.Builder(++id, gcylId("yeast"))
                .dust()
                .color(0xf0e660)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("???", true);

        LinoleicAcid = new Material.Builder(++id, gcylId("linoleic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0xD5D257)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C18H32O2", true);

        Biotin = new Material.Builder(++id, gcylId("biotin"))
                .liquid()
                .color(0x68cc6a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C10H16N2O3S", true);

        Catalase = new Material.Builder(++id, gcylId("catalase"))
                .liquid()
                .color(0xdb6596)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        B27Supplement = new Material.Builder(++id, gcylId("b_27_supplement"))
                .liquid()
                .color(0x386939)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C142H230N36O44S", true);

        CleanAmmoniaSolution = new Material.Builder(++id, gcylId("clear_ammonia_solution"))
                .liquid()
                .color(0x53c9a0)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("NH3(H2O)", true);

        Glutamine = new Material.Builder(++id, gcylId("glutamine"))
                .dust()
                .color(0xede9b4)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("C5H10N2O3", true);

        Blood = new Material.Builder(++id, gcylId("blood"))
                .liquid()
                .color(0x5c0606)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Blood", true);

        BloodCells = new Material.Builder(++id, gcylId("blood_cells"))
                .liquid()
                .color(0xad2424)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("???", true);

        BloodPlasma = new Material.Builder(++id, gcylId("blood_plasma"))
                .liquid()
                .color(0xe37171)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("???", true);

        BFGF = new Material.Builder(++id, gcylId("bfgf"))
                .liquid()
                .color(0xb365e0)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("bFGF", true);

        EGF = new Material.Builder(++id, gcylId("egf"))
                .liquid()
                .color(0x815799)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C257H381N73O83S7", true);

        SilicaGelBase = new Material.Builder(++id, gcylId("silica_gel_base"))
                .liquid()
                .color(0x27a176)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("SiO2(HCl)(NaOH)(H2O)", true);

        SilicaAluminaGel = new Material.Builder(++id, gcylId("silica_alumina_gel"))
                .dust()
                .color(0x558d9e)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("Al2O3SiO2", true);

        PiranhaSolution = new Material.Builder(++id, gcylId("piranha_solution"))
                .liquid()
                .color(0x4820ab)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2SO4)H2O2", true);

        ChlorosulfonicAcid = new Material.Builder(++id, gcylId("chlorosulfonic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0x916c1d)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("HSO3Cl", true);

        AcetylsulfanilylChloride = new Material.Builder(++id, gcylId("acetylsulfanilyl_chloride"))
                .liquid()
                .color((Aniline.getMaterialRGB() + AceticAnhydride.getMaterialRGB() + ChlorosulfonicAcid.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C8H8ClNO3S", true);

        Sulfanilamide = new Material.Builder(++id, gcylId("sulfanilamide"))
                .liquid()
                .color(0x523b0a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H8N2O2S", true);

        AnimalCells = new Material.Builder(++id, gcylId("animal_cells"))
                .liquid()
                .color(0xc94996)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("???", true);

        RapidlyReplicatingAnimalCells = new Material.Builder(++id, gcylId("rapidly_replicating_animal_cells"))
                .liquid()
                .color(0x7a335e)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula(TextFormatting.OBFUSCATED + "????", true);

        Oct4Gene = new Material.Builder(++id, gcylId("oct_4_gene"))
                .liquid()
                .color(0x374f0d)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        SOX2Gene = new Material.Builder(++id, gcylId("sox_2_gene"))
                .liquid()
                .color(0x5d8714)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        KFL4Gene = new Material.Builder(++id, gcylId("kfl_4_gene"))
                .liquid()
                .color(0x759143)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        GeneTherapyFluid = new Material.Builder(++id, gcylId("pluripotency_induction_gene_therapy_fluid"))
                .liquid()
                .color(0x6b2f66)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        Cas9 = new Material.Builder(++id, gcylId("cas_9"))
                .liquid()
                .color(0x5f6e46)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        MycGene = new Material.Builder(++id, gcylId("myc_gene"))
                .liquid()
                .color(0x445724)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        GenePlasmids = new Material.Builder(++id, gcylId("pluripotency_induction_gene_plasmids"))
                .liquid()
                .color(0xabe053)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        Chitin = new Material.Builder(++id, gcylId("chitin"))
                .liquid()
                .color(0xcbd479)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        ZincCokePellets = new Material.Builder(++id, gcylId("zinc_coke_pellets"))
                .dust()
                .color((Zinc.getMaterialRGB() + Coke.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("(H2O)(ZnS)C", true);

        ZincResidualSlag = new Material.Builder(++id, gcylId("zinc_residual_slag"))
                .dust()
                .color((Zinc.getMaterialRGB() - 20))
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("?", true);

        ZincExhaustMixture = new Material.Builder(++id, gcylId("zinc_exhaust_mixture"))
                .liquid()
                .color((CarbonDioxide.getMaterialRGB() + SulfurDioxide.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(SO2)(CO2)?", true);

        ZincSulfate = new Material.Builder(++id, gcylId("zinc_sulfate"))
                .dust()
                .color((Zinc.getMaterialRGB() + Sulfur.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(QUARTZ)
                .components(Zinc, 1, Sulfur, 1, Oxygen, 4)
                .build();

        ZincLeachingResidue = new Material.Builder(++id, gcylId("zinc_leaching_residue"))
                .dust()
                .color((Germanium.getMaterialRGB() + Oxygen.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("Ge?", true);

        ZincFlueDust = new Material.Builder(++id, gcylId("zinc_flue_dust"))
                .dust()
                .color(0xfcfca)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("?", true);

        FineZincSlagDust = new Material.Builder(++id, gcylId("fine_zinc_slag_dust"))
                .dust()
                .color((Zinc.getMaterialRGB() - 10))
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("?", true);

        ZincSlagSlurry = new Material.Builder(++id, gcylId("zinc_slag_slurry"))
                .liquid()
                .color((Zinc.getMaterialRGB() - 20))
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2O?", true);

        MetalRichSlagSlurry = new Material.Builder(++id, gcylId("metal_slag_slurry"))
                .liquid()
                .color((Zinc.getMaterialRGB() - 20))
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2O?", true);

        AcidicMetalSlurry = new Material.Builder(++id, gcylId("acidic_metal_slurry"))
                .liquid()
                .color((Zinc.getMaterialRGB() - 10 + PhosphoricAcid.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H3PO4?", true);

        SeparatedMetalSlurry = new Material.Builder(++id, gcylId("separated_metal_slurry"))
                .liquid()
                .color((Zinc.getMaterialRGB() - 20))
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H3PO4?", true);

        MetalHydroxideMix = new Material.Builder(++id, gcylId("metal_hydroxide_mix"))
                .liquid()
                .color((Zinc.getMaterialRGB() - 30))
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?ZnOH", true);
        ZincPoorMix = new Material.Builder(++id, gcylId("zinc_poor_mix"))
                .liquid()
                .color((Iron.getMaterialRGB() - 10))
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?Fe", true);
        IronPoorMix = new Material.Builder(++id, gcylId("iron_poor_mix"))
                .liquid()
                .color((Copper.getMaterialRGB() + 10))
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?In", true);
        IndiumHydroxideConcentrate = new Material.Builder(++id, gcylId("indium_hydroxide_concentrate"))
                .liquid()
                .color((Indium.getMaterialRGB() + Hydrogen.getMaterialRGB() + 10) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("In(OH)3", true);

        IndiumHydroxide = new Material.Builder(++id, gcylId("indium_hydroxide"))
                .dust()
                .color((Indium.getMaterialRGB() + SodiumHydroxide.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("In(OH)3", true);

        CadmiumZincDust = new Material.Builder(++id, gcylId("cadmium_zinc_dust"))
                .dust()
                .color((Cadmium.getMaterialRGB() + Zinc.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("(H2SO4)CdZn?", true);

        CadmiumThalliumLiquor = new Material.Builder(++id, gcylId("cdtl_liquor"))
                .liquid()
                .color((Cadmium.getMaterialRGB() + Thallium.getMaterialRGB() + RareEarth.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2SO4)CdTl", true);

        ZincAmalgam = new Material.Builder(++id, gcylId("zinc_amalgam"))
                .liquid()
                .color((Zinc.getMaterialRGB() - 20))
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("ZnHg", true);

        ThalliumResidue = new Material.Builder(++id, gcylId("thallium_residue"))
                .dust()
                .color((Thallium.getMaterialRGB() - 10))
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Tl?", true);

        CadmiumSulfateSolution = new Material.Builder(++id, gcylId("cadmium_sulfate"))
                .liquid()
                .color((Cadmium.getMaterialRGB() + SulfuricAcid.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("CdSO4?", true);

        ThalliumSulfateSolution = new Material.Builder(++id, gcylId("thallium_sulfate"))
                .liquid()
                .color((Thallium.getMaterialRGB() + SulfuricAcid.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Tl2SO4?", true);

        ZincChloride = new Material.Builder(++id, gcylId("zinc_chloride"))
                .dust()
                .color((Zinc.getMaterialRGB() + Chlorine.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("ZnCl2", true);

        SodiumSulfite = new Material.Builder(++id, gcylId("sodium_sulfite"))
                .dust()
                .color((SodiumHydroxide.getMaterialRGB() + Sulfur.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("Na2SO3", true);

        PolyphenolMix = new Material.Builder(++id, gcylId("polyphenol_mix"))
                .liquid()
                .color((Phenol.getMaterialRGB() + 10))
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        AcidifiedPolyphenolMix = new Material.Builder(++id, gcylId("acidified_polyphenol_mix"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color((PolyphenolMix.getMaterialRGB() + SulfuricAcid.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        Diethylether = new Material.Builder(++id, gcylId("diethylether"))
                .liquid()
                .color(AcidifiedPolyphenolMix.getMaterialRGB() - 20)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(C2H5)2O", true);

        TannicAcid = new Material.Builder(++id, gcylId("tannic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color((Diethylether.getMaterialRGB() + AcidifiedPolyphenolMix.getMaterialRGB()) / 4)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C76H52O46", true);

        GermanicAcidSolution = new Material.Builder(++id, gcylId("germanic_acid_solution"))
                .liquid()
                .color((Germanium.getMaterialRGB() - 10))
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H4GeO4", true);

        GermaniumChloride = new Material.Builder(++id, gcylId("germanium_chloride"))
                .liquid()
                .color((Germanium.getMaterialRGB() + Chlorine.getMaterialRGB()) / 2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("GeCl4", true);

        BariumHydroxide = new Material.Builder(++id, gcylId("barium_hydroxide"))
                .dust()
                .color((Barium.getMaterialRGB() + Hydrogen.getMaterialRGB() + Oxygen.getMaterialRGB()) / 3)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("Ba(OH)2", true);

        ThiocyanicAcid = new Material.Builder(++id, gcylId("thiocyanic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0xfcfc30)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("HSCN", true);

        ZrHfSeparationMix = new Material.Builder(++id, gcylId("zrhf_separation_mix"))
                .liquid()
                .color(0xfcfc95)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?", true);

        ZrHfChloride = new Material.Builder(++id, gcylId("zrhf_chloride"))
                .liquid()
                .color(0x51d351)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("ZrHfCl4", true);

        ZirconChlorinatingResidue = new Material.Builder(++id, gcylId("zircon_chlorinating_residue"))
                .liquid()
                .color(0x51d351)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(SiCl4)Co?", true);

        SiliconChloride = new Material.Builder(++id, gcylId("silicon_chloride"))
                .dust()
                .color(Silicon.getMaterialRGB() - 15)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("SiCl4", true);

        ZrHfOxyChloride = new Material.Builder(++id, gcylId("zrhf_oxychloride"))
                .liquid()
                .color(0x51d351)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Cl2HfOZr", true);

        HafniumOxide = new Material.Builder(++id, gcylId("hafnium_oxide"))
                .dust()
                .color(0x404040)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("HfO2", true);

        HafniumChloride = new Material.Builder(++id, gcylId("hafnium_chloride"))
                .dust()
                .color(Hafnium.getMaterialRGB() + 20)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("HfCl4", true);

        HeliumCNO = new Material.Builder(++id, gcylId("helium_rich_cno"))
                .liquid().plasma()
                .color(0x59ffa6)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("He?", true);

        Iron52 = new Material.Builder(++id, gcylId("iron_52"))
                .liquid().plasma()
                .color(Iron.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Fe_52", true);

        Chromium48 = new Material.Builder(++id, gcylId("chromium_48"))
                .liquid().plasma()
                .color(Chrome.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Cr_48", true);

        Titanium44 = new Material.Builder(++id, gcylId("titanium_44"))
                .liquid().plasma()
                .color(Titanium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Ti_44", true);

        Nickel56 = new Material.Builder(++id, gcylId("nickel_56"))
                .liquid().plasma()
                .color(Nickel.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Ni_56", true);

        LiquidEnrichedHelium = new Material.Builder(++id, gcylId("liquid_enriched_helium"))
                .liquid()
                .color(Helium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("HeHe_3", true);

        AbyssalAlloy = new Material.Builder(++id, gcylId("abyssal_alloy"))
                .ingot(6).liquid()
                .color(0x9E706A)
                .iconSet(METALLIC)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .cableProperties(GTValues.V[GTValues.UHV], 2, 8)
                .components(StainlessSteel, 5, TungstenCarbide, 5, Nichrome, 5, Bronze, 5, IncoloyMA956, 5, Iodine, 1, Germanium, 1, Radon, 1)
                .blast(9625)
                .build();

        CosmicNeutronium = new Material.Builder(++id, gcylId("cosmic_neutronium"))
                .ingot(7).liquid()
                .color(0x323232)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .components(Neutronium, 1)
                .cableProperties(GTValues.V[MAX],64,64)
                .blast(14100)
                .build();

        HighEnergyQGP = new Material.Builder(++id, gcylId("high_energy_qgp"))
                .liquid()
                .color(0x8f00ff)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula(makeFancy(TextFormatting.OBFUSCATED + "a" + TextFormatting.RESET + "(u2)d(c2)s(t2)bg" + TextFormatting.OBFUSCATED + "a"),true);

        CaliforniumCyclopentadienide = new Material.Builder(++id, gcylId("californiumcyclopentadienide"))
                .liquid()
                .color(0x94445b)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C15H15Cf",true);

        IridiumTrioxide  = new Material.Builder(++id, gcylId("iridiumtrioxide"))
                .dust()
                .color(0x9a9a2b)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("Ir2O3",true);

        IridiumTrichlorideSolution = new Material.Builder(++id, gcylId("iridiumtrichloridesolution"))
                .liquid()
                .color(0x96821a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("IrCl3",true);

        SemisolidHydrogen = new Material.Builder(++id, gcylId("semisolidhydrogen"))
                .liquid()
                .color(0x044c4b)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H",true);

        MicrocrystallizingHydrogen = new Material.Builder(++id, gcylId("microcrystallizinghydrogen"))
                .liquid()
                .color(0x155d5c)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H",true);

        Vinteum = new Material.Builder(++id, gcylId("vinteum"))
                .gem(3)
                .color(6605055)
                .flags(DISABLE_REPLICATION, NO_SMASHING, NO_SMELTING)
                .iconSet(EMERALD)
                .build()
                .setFormula("Vt¤",true);

        Tanzanite = new Material.Builder(++id, gcylId("tanzanite"))
                .gem(2)
                .color(4194504)
                .flags(DISABLE_REPLICATION, NO_SMASHING, NO_SMELTING, HIGH_SIFTER_OUTPUT)
                .iconSet(GEM_VERTICAL)
                .components(Calcium,2,Aluminium,3,Silicon,3,Hydrogen,1,Oxygen,13)
                .build();

        Dibismusthydroborat = new Material.Builder(++id, gcylId("dibismuthhydroborat"))
                .dust()
                .color(0x00B749)
                .flags(DISABLE_REPLICATION)
                .iconSet(SAND)
                .components(Bismuth,2,Hydrogen,1,Boron,1)
                .build();

        CircuitCompoundMK3 = new Material.Builder(++id, gcylId("circuit_compound_mkc"))
                .dust()
                .color(0x003316)
                .flags(DISABLE_REPLICATION)
                .iconSet(SAND)
                .components(IndiumGalliumPhosphide,1,Dibismusthydroborat,3,BismuthTellurite,2)
                .build();

        HyperFuelI = new Material.Builder(++id, gcylId("hyper_fluid_i"))
                .liquid()
                .color(0xfaff5e)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Nq(Nq+)(*Nq*)RfPu",true);

        HyperFuelII = new Material.Builder(++id, gcylId("hyper_fluid_ii"))
                .liquid()
                .color(0xd8db67)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Nq(Nq+)(*Nq*)DbCm",true);

        HyperFuelIII = new Material.Builder(++id, gcylId("hyper_fluid_iii"))
                .liquid()
                .color(0x8f9146)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Nq(Nq+)(*Nq*)AdCf",true);

        HyperFuelIV = new Material.Builder(++id, gcylId("hyper_fluid_iv"))
                .liquid()
                .color(0x4d4e31)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Nq(Nq+)(*Nq*)AdCfNtTn",true);

        NaquadricSolution = new Material.Builder(++id, gcylId("naquadric_solution"))
                .liquid()
                .color(0x232225)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("NqNO2",true);

        EnrichedNaquadricSolution = new Material.Builder(++id, gcylId("enriched_naquadric_solution"))
                .liquid()
                .color(0x312735)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Nq+NO2",true);

        NaquadriaticSolution = new Material.Builder(++id, gcylId("naquadriatic_solution"))
                .liquid()
                .color(0x312735)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("*Nq*NO2",true);

        EnrichedFluoronaquadricAcid = new Material.Builder(++id, gcylId("enriched_fluoronaquadric_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0x485d60)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2Nq+F4",true);

        FluoronaquadriaticAcid = new Material.Builder(++id, gcylId("fluoronaquadriatic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0x485d60)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2*Nq*F4",true);

        NaquadahDifluoride = new Material.Builder(++id, gcylId("naquadah_difluoride"))
                .liquid()
                .color(0x324649)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("NqF2",true);

        EnrichedNaquadahDifluoride = new Material.Builder(++id, gcylId("enriched_naquadah_difluoride"))
                .liquid()
                .color(0x141e1f)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Nq+F2",true);

        NaquadriaDifluoride = new Material.Builder(++id, gcylId("naquadria_difluoride"))
                .liquid()
                .color(0x141e1f)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("*Nq*F2",true);

        IndiumTrifluoride = new Material.Builder(++id, gcylId("indium_trifluoride"))
                .dust()
                .color(0x2b0f48)
                .flags(DISABLE_REPLICATION)
                .iconSet(Indium.getMaterialIconSet())
                .build()
                .setFormula("InF3",true);

        NaquadahConcentrate = new Material.Builder(++id, gcylId("naquadah_concentrate"))
                .dust()
                .color(Naquadah.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(Naquadah.getMaterialIconSet())
                .build()
                .setFormula("Nq?",true);

        EnrichedNaquadahConcentrate = new Material.Builder(++id, gcylId("enriched_naquadah_concentrate"))
                .dust()
                .color(NaquadahEnriched.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(NaquadahEnriched.getMaterialIconSet())
                .build()
                .setFormula("Nq+?",true);

        NaquadriaConcentrate = new Material.Builder(++id, gcylId("naquadria_concentrate"))
                .dust()
                .color(Naquadria.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(Naquadria.getMaterialIconSet())
                .build()
                .setFormula("*Nq*?",true);

        NaquadriaHexafluoride = new Material.Builder(++id, gcylId("naquadria_hexafluoride"))
                .liquid()
                .color(0x111c27)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("*Nq*F6",true);

        RadonDifluoride = new Material.Builder(++id, gcylId("radon_difluoride"))
                .liquid()
                .color(0x9966ff)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("RnF2",true);

        RadonNaquadriaoctafluoride = new Material.Builder(++id, gcylId("radon_naquadriaoctafluoride"))
                .liquid()
                .color(0x111c27)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Rd*Nq*F8",true);

        CesiumFluoride = new Material.Builder(++id, gcylId("cesium_fluoride"))
                .liquid()
                .color(0xabab69)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("CsF",true);

        CesiumXenontrioxideFluoride = new Material.Builder(++id, gcylId("cesium_xenontrioxide_fluoride"))
                .liquid()
                .color(0x3333cc)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("CsXeO3F",true);

        NaquadriaCesiumXenonNonfluoride = new Material.Builder(++id, gcylId("naquadria_cesium_xenon_nonfluoride"))
                .liquid()
                .color(0x1c1c5e)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("*Nq*CsXeF9",true);

        NitrylFluoride = new Material.Builder(++id, gcylId("nitryl_fluoride"))
                .liquid()
                .color(NitricOxide.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("NO2F",true);

        NitrosoniumOctafluoroxenate = new Material.Builder(++id, gcylId("nitrosonium_octafluoroxenate"))
                .liquid()
                .color(0x3f3f83)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(NO2)2XeF8",true);

        NaquadriaCesiumfluoride = new Material.Builder(++id, gcylId("naquadria_cesiumfluoride"))
                .liquid()
                .color(0x636379)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("*Nq*F2CsF",true);

        EnrichedNaquadahhexafluoride = new Material.Builder(++id, gcylId("enriched_naquadahhexafluoride"))
                .liquid()
                .color(0x030330)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Nq+F6",true);

        EnrichedXenonHexafluoronaquadate = new Material.Builder(++id, gcylId("enriched_xenon_hexafluoronaquadate"))
                .liquid()
                .color(0x1e1ec2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("XeNq+F6",true);

        BromineTrifluoride = new Material.Builder(++id, gcylId("bromine_trifluoride"))
                .liquid()
                .color(0xfcde1d)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("BrF3",true);

        AuricFluoride = new Material.Builder(++id, gcylId("auric_fluoride"))
                .dust()
                .color(0xdffb50)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("AuF3",true);

        XenoauricFluoroantimonicAcid = new Material.Builder(++id, gcylId("xenoauric_fluoroantimonic_acid"))
                .liquid()
                .color(0x685b08)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("XeAuSbF6",true);

        NaquadahSulfate = new Material.Builder(++id, gcylId("naquadah_sulfate"))
                .liquid()
                .color(0x38330f)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("NqSO4",true);

        IndiumTrioxide = new Material.Builder(++id, gcylId("indium_trioxide"))
                .dust()
                .color(0x2b0f48)
                .flags(DISABLE_REPLICATION)
                .iconSet(Indium.getMaterialIconSet())
                .build()
                .setFormula("In2O3",true);

        NaquadahSolution = new Material.Builder(++id, gcylId("naquadah_solution"))
                .liquid()
                .color(0x523b3a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("NqNH4NO3",true);

        ClearNaquadahLiquid = new Material.Builder(++id, gcylId("clear_naquadah_liquid"))
                .liquid()
                .color(0xa89f9e)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Nq?",true);

        ComplicatedNaquadahGas = new Material.Builder(++id, gcylId("complicated_naquadah_gas"))
                .gas()
                .color(0x403d3d)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Nq??",true);

        ComplicatedLightNaquadah = new Material.Builder(++id, gcylId("complicated_light_naquadah"))
                .liquid()
                .color(0x403d3d)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Nq??",true);

        ComplicatedMediumNaquadah = new Material.Builder(++id, gcylId("complicated_medium_naquadah"))
                .liquid()
                .color(0x403d3d)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Nq??",true);

        ComplicatedHeavyNaquadah = new Material.Builder(++id, gcylId("complicated_heavy_naquadah"))
                .liquid()
                .color(0x403d3d)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Nq??",true);

        NaquadahGas = new Material.Builder(++id, gcylId("naquadah_gas"))
                .gas()
                .color(0x575757)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Nq",true);

        HeavyNaquadah = new Material.Builder(++id, gcylId("heavy_naquadah"))
                .liquid()
                .color(0x2e2e2e)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Nq",true);

        LightNaquadah = new Material.Builder(++id, gcylId("light_naquadah"))
                .liquid()
                .color(0x2e2e2e)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Nq",true);

        MediumNaquadah = new Material.Builder(++id, gcylId("medium_naquadah"))
                .liquid()
                .color(0x2e2e2e)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Nq",true);

        FCrackedMediumNaquadah = new Material.Builder(++id, gcylId("fl_cracked_medium_naquadah"))
                .liquid()
                .color(0x505e5b)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("FlNq",true);

        FCrackedLightNaquadah = new Material.Builder(++id, gcylId("fl_cracked_light_naquadah"))
                .liquid()
                .color(0x505e5b)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("FlNq",true);

        FCrackedHeavyNaquadah = new Material.Builder(++id, gcylId("fl_cracked_heavy_naquadah"))
                .liquid()
                .color(0x505e5b)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("FlNq",true);

        LightNaquadahFuel = new Material.Builder(++id, gcylId("light_naquadah_fuel"))
                .liquid()
                .color(0x2e2e2e)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Nq",true);

        MediumNaquadahFuel = new Material.Builder(++id, gcylId("medium_naquadah_fuel"))
                .liquid()
                .color(0x2e2e2e)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Nq",true);

        HeavyNaquadahFuel = new Material.Builder(++id, gcylId("heavy_naquadah_fuel"))
                .liquid()
                .color(0x2e2e2e)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Nq",true);

        ENaquadahSolution = new Material.Builder(++id, gcylId("e_naquadah_solution"))
                .liquid()
                .color(0x523b3a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Nq+?",true);

        ClearENaquadahLiquid = new Material.Builder(++id, gcylId("clear_e_naquadah_liquid"))
                .liquid()
                .color(0xa89f9e)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Nq+?",true);

        ComplicatedLightENaquadah = new Material.Builder(++id, gcylId("complicated_light_e_naquadah"))
                .liquid()
                .color(0x403d3d)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Nq+??",true);

        ComplicatedMediumENaquadah = new Material.Builder(++id, gcylId("complicated_medium_e_naquadah"))
                .liquid()
                .color(0x403d3d)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Nq+??",true);
        ComplicatedHeavyENaquadah = new Material.Builder(++id, gcylId("complicated_heavy_e_naquadah"))
                .liquid()
                .color(0x403d3d)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Nq+??",true);

        HeavyENaquadah = new Material.Builder(++id, gcylId("heavy_e_naquadah"))
                .liquid()
                .color(0x2e2e2e)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Nq+",true);

        MediumENaquadah = new Material.Builder(++id, gcylId("medium_e_naquadah"))
                .liquid()
                .color(0x2e2e2e)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Nq+",true);

        LightENaquadah = new Material.Builder(++id, gcylId("light_e_naquadah"))
                .liquid()
                .color(0x2e2e2e)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Nq+",true);

        RnCrackedMediumENaquadah = new Material.Builder(++id, gcylId("rn_cracked_medium_e_naquadah"))
                .liquid()
                .color(0x505e5b)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("RnNq+",true);

        RnCrackedLightNaquadah = new Material.Builder(++id, gcylId("rn_cracked_light_e_naquadah"))
                .liquid()
                .color(0x505e5b)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("RnNq+",true);

        RnCrackedHeavyENaquadah = new Material.Builder(++id, gcylId("rn_cracked_heavy_e_naquadah"))
                .liquid()
                .color(0x505e5b)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("RnNq+",true);

        LightENaquadahFuel = new Material.Builder(++id, gcylId("light_e_naquadah_fuel"))
                .liquid()
                .color(0x2e2e2e)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Nq+",true);

        MediumENaquadahFuel = new Material.Builder(++id, gcylId("medium_e_naquadah_fuel"))
                .liquid()
                .color(0x2e2e2e)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Nq+",true);

        HeavyENaquadahFuel = new Material.Builder(++id, gcylId("heavy_e_naquadah_fuel"))
                .liquid()
                .color(0x2e2e2e)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Nq+",true);

        FluoronaquadricAcid = new Material.Builder(++id, gcylId("fluoronaquadric_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0x485d60)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H2NqF4",true);

        Fluorescein = new Material.Builder(++id, gcylId("fluorescein"))
                .dust()
                .color(0x990000)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("C20H12O5",true);

        SodiumBorohydride = new Material.Builder(++id, gcylId("sodium_borohydride"))
                .dust()
                .color(0xc2c2fa)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("NaBH4",true);

        BoronTrifluorideEtherate = new Material.Builder(++id, gcylId("boron_trifluoride_etherate"))
                .liquid()
                .color((BoronFluoride.getMaterialRGB()+Diethylether.getMaterialRGB())/2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(BF3)(C2H5)2O",true);

        Decaborane = new Material.Builder(++id, gcylId("decaborane"))
                .dust()
                .color(Diborane.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("B10H14",true);

        SodiumTetrafluoroborate = new Material.Builder(++id, gcylId("sodium_tetrafluoroborate"))
                .dust()
                .color((SodiumBorohydride.getMaterialRGB()+Fluorine.getMaterialRGB())/2)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("NaBF4",true);

        CesiumCarboranePrecusor = new Material.Builder(++id, gcylId("cesium_carborane_precursor"))
                .dust()
                .color(0xb56487)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("CsB10H12CN(CH3)3Cl",true);

        BoraneDimethylsulfide = new Material.Builder(++id, gcylId("borane_dimethylsulfide"))
                .liquid()
                .color((Diborane.getMaterialRGB()+Dimethylsulfide.getMaterialRGB())/2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(BH3)(CH3)2S",true);

        CesiumCarborane = new Material.Builder(++id, gcylId("cesium_carborane"))
                .dust()
                .color(CesiumCarboranePrecusor.getMaterialRGB()*6/5)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("CsCB11H12",true);

        SilverNitrate = new Material.Builder(++id, gcylId("silver_nitrate"))
                .dust()
                .color(0xfdfdca)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("AgNO3",true);

        Fluorocarborane = new Material.Builder(++id, gcylId("fluorocarborane"))
                .dust()
                .color(0x20EB7A)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("HCHB11F11",true);

        CaesiumNitrate = new Material.Builder(++id, gcylId("caesium_nitrate"))
                .dust()
                .color(0x7452DC)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("CsNO3",true);

        SilverIodide = new Material.Builder(++id, gcylId("silver_iodide"))
                .dust()
                .color((SilverChloride.getMaterialRGB()*2+Iodine.getMaterialRGB())/3)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("AgI",true);

        FumingNitricAcid = new Material.Builder(++id, gcylId("fuming_nitric_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color( NitricAcid.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("HNO3",true);

        PureCrystallineNitricAcid = new Material.Builder(++id, gcylId("crystalline_nitric_acid"))
                .dust()
                .color(NitricAcid.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("HNO3",true);

        ActiniumTriniumHydroxides = new Material.Builder(++id, gcylId("actinium_trinium_hydroxides"))
                .dust()
                .color((ActiniumOxalate.getMaterialRGB()+Trinium.getMaterialRGB())/2)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("Ke3Ac2(OH)12",true);

        ResidualTriniiteSolution = new Material.Builder(++id, gcylId("residual_triniite_solution"))
                .liquid()
                .color(0x219daf)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        Perfluorobenzene = new Material.Builder(++id, gcylId("perfluorobenzene"))
                .liquid()
                .color(0x226E22)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C6F6",true);

        Fluorocarborane = new Material.Builder(++id, gcylId("fluorocarborane"))
                .dust()
                .color(0x20EB7A)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("HCHB11F11",true);

        ActiniumRadiumHydroxideSolution = new Material.Builder(++id, gcylId("actinium_radium_hydroxide_solution"))
                .liquid()
                .color(0xe2f5ef)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        HeavilyFluorinatedTriniumSolution = new Material.Builder(++id, gcylId("heavily_fluorinated_trinium_solution"))
                .liquid()
                .color(0x348d41)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        MoltenCalciumSalts = new Material.Builder(++id, gcylId("molten_calcium_salts"))
                .liquid()
                .color((Fluorite.getMaterialRGB()+Calcium.getMaterialRGB())/2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        TriniumTetrafluoride = new Material.Builder(++id, gcylId("trinium_tetrafluoride"))
                .dust()
                .color(0x477347)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("KeF4",true);

        AcetylChloride = new Material.Builder(++id, gcylId("acetyl_chloride"))
                .liquid()
                .color(AceticAcid.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C2H3OCl",true);

        EthylTrifluoroacetate = new Material.Builder(++id, gcylId("ethyl_trifluoroacetate"))
                .liquid()
                .color(0x88a12d)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C4H5F3O2",true);

        PhosphorousPentasulfide = new Material.Builder(++id, gcylId("phosphorous_pentasulfide"))
                .dust()
                .color(0xEBAD24)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("P4S10",true);

        Acetothienone = new Material.Builder(++id, gcylId("acetothieone"))
                .liquid()
                .color(0x79882a)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H6SO",true);

        TheonylTrifluoroacetate = new Material.Builder(++id, gcylId("theonyl_trifluoroacetate"))
                .liquid()
                .color(0x88882b)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C8H5F3O2S",true);

        ActiniumRadiumNitrateSolution = new Material.Builder(++id, gcylId("actinium_radium_nitrate_solution"))
                .liquid()
                .color(0xd2f0df)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build();

        ActiniumNitrate = new Material.Builder(++id, gcylId("actinium_nitrate"))
                .dust()
                .color(YttriumNitrate.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("Ac(NO3)3",true);

        RadiumNitrate = new Material.Builder(++id, gcylId("radium_nitrate"))
                .dust()
                .color(BariumNitrate.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("Ra(NO3)2",true);

        LithiumPeroxideSolution = new Material.Builder(++id, gcylId("lithium_peroxide"))
                .liquid()
                .color((Lithium.getMaterialRGB()+Oxygen.getMaterialRGB())/2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)Li2O2",true);

        NitrogenPentoxide = new Material.Builder(++id, gcylId("nitrogen_pentoxide"))
                .liquid()
                .color(0x0033C0)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("N2O5",true);

        TitaniumNitrate = new Material.Builder(++id, gcylId("titanium_nitrate"))
                .dust()
                .color(0xFF0066)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("TiNO3",true);

        Carbon12 = new Material.Builder(++id, gcylId("carbon_12"))
                .gas()
                .color(Carbon.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C_12",true);

        Carbon13 = new Material.Builder(++id, gcylId("carbon_13"))
                .gas()
                .color(Carbon.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C_13",true);

        Nitrogen14 = new Material.Builder(++id, gcylId("nitrogen_14"))
                .gas()
                .color(Nitrogen.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("N_14",true);

        NItrogen15 = new Material.Builder(++id, gcylId("nitrogen_15"))
                .liquid()
                .color(Nitrogen.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("N_15",true);

        CNOcatalyst = new Material.Builder(++id, gcylId("cno_catalyst"))
                .liquid()
                .color((Nitrogen.getMaterialRGB() + Carbon.getMaterialRGB())/2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(C_12)(C_13)(N_14)(N_15)",true);

        Calcium44 = new Material.Builder(++id, gcylId("calcium_44"))
                .liquid()
                .color(Calcium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Ca_44",true);

        QuassifissioningPlasma = new Material.Builder(++id, gcylId("quasifissioning_plasma"))
                .plasma()
                .color(0xD5CB54)
                .flags(DISABLE_REPLICATION, DISABLE_DECONSTRUCTION)
                .iconSet(FLUID)
                .build()
                .setFormula(TextFormatting.OBFUSCATED + "???",true);

        FlYbPlasma = new Material.Builder(++id, gcylId("flyb_plasma"))
                .plasma()
                .color((Ytterbium.getMaterialRGB() + 0x521973) / 2)
                .flags(DISABLE_REPLICATION, DISABLE_DECONSTRUCTION)
                .iconSet(FLUID)
                .build()
                .setFormula("FlYb",true);

        Ytterbium178 = new Material.Builder(++id, gcylId("ytterbium_178"))
                .liquid()
                .color(Ytterbium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Yb_178",true);

        TitaniumTetrafluoride = new Material.Builder(++id, gcylId("titanium_tetrafluoride"))
                .liquid()
                .color(Titanium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("TiF4",true);

        Titanium50Tetrafluoride = new Material.Builder(++id, gcylId("titanium_50_tetrafluoride"))
                .liquid()
                .color( Titanium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Ti_50F4",true);

        OgannesonBreedingBase = new Material.Builder(++id, gcylId("og_breeding_base"))
                .liquid()
                .color(((Titanium.getMaterialRGB() + 0xA85A12) / 2))
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(Ti_50)Cf_252",true);

        HotMetastableOganesson = new Material.Builder(++id, gcylId("hot_oganesson"))
                .liquid()
                .color(0x521973)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Og",true);

        HafniumCarbide = new Material.Builder(++id, gcylId("hafnium_carbide"))
                .dust()
                .color(0x2c2c2c)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("HfC",true);

        SeaborgiumCarbide = new Material.Builder(++id, gcylId("seaborgium_carbide"))
                .dust()
                .color(0x2c2c2c)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("SgC",true);

        Calcium44 = new Material.Builder(++id, gcylId("calcium_44"))
                .liquid()
                .color(Calcium.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Ca_44",true);

        Helium4 = new Material.Builder(++id, gcylId("helium_4"))
                .gas()
                .color(Helium.getMaterialRGB()-10)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("He_4",true);

        //TUNGSTEN & PLATINUM
        PalladiumAmmonia = new Material.Builder(++id, gcylId("palladium_enriched_ammonia"))
                .liquid()
                .color(Palladium.getMaterialRGB())
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .components(Palladium,1,Ammonia,1,RareEarth,1)
                .build();

        PlatinumConcentrate = new Material.Builder(++id, gcylId("platinum_concentrate"))
                .liquid()
                .color(Platinum.getMaterialRGB())
                .flags(DISABLE_REPLICATION,DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .components(Platinum,1,RareEarth,1)
                .build();

        RhodiumSulfateSolution = new Material.Builder(++id, gcylId("rhodium_sulfate_solution"))
                .liquid()
                .color(0xFFBB66)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .components(RhodiumSulfate,1,Water,1)
                .build();

        RhodiumSaltSolution = new Material.Builder(++id, gcylId("rhodium_salt_solution"))
                .liquid()
                .color(0x667788)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .components(Rhodium,1,Salt,2,Chlorine,1)
                .build();

        AcidicIridiumSolution = new Material.Builder(++id, gcylId("acidic_iridium_solution"))
                .liquid()
                .color(IridiumDioxide.getMaterialRGB()-20)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .components(HydrochloricAcid,2,IridiumDioxide,2)
                .build();

        RhodiumFilterCakeSolution = new Material.Builder(++id, gcylId("rhodium_filter_cake_solution"))
                .liquid()
                .color(0x667788)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .components(Rhodium,1,Water,2,RareEarth,1)
                .build();

        RutheniumTetroxideSolution = new Material.Builder(++id, gcylId("ruthenium_tetroxide_solution"))
                .liquid()
                .color(0xC7C7C7)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .components(Salt,2,Ruthenium,1,Oxygen,4)
                .build();

        HotRutheniumTetroxideSolution = new Material.Builder(++id, gcylId("hot_ruthenium_tetroxide_solution"))
                .liquid()
                .color(0xC7C7C7)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .components(RutheniumTetroxideSolution,1,Water,1)
                .build();

        OsmiumSolution = new Material.Builder(++id, gcylId("osmium_solution"))
                .liquid()
                .color((Osmium.getMaterialRGB() + Water.getMaterialRGB())/2)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .components(Osmium,1, Oxygen,4, Water,1)
                .build();

        LithiumHydroxide = new Material.Builder(++id, gcylId("lithium_hydroxide"))
                .dust()
                .color((Lithium.getMaterialRGB()+Oxygen.getMaterialRGB()+Hydrogen.getMaterialRGB())/3)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("LiOH",true);

        TungstenHexachloride = new Material.Builder(++id, gcylId("tungsten_hexachloride"))
                .dust()
                .color(0x533f75)
                .flags(DISABLE_REPLICATION,DISABLE_DECOMPOSITION)
                .iconSet(METALLIC)
                .components(Tungsten,1,Oxygen,3)
                .build();

        Bowieite = new Material.Builder(++id, gcylId("bowieite"))
                .dust()
                .color(0x8b8995)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(ROUGH)
                .components(Rhodium,1,Iridium,1,Platinum,1,Sulfur,3)
                .build();

        PlatinumMetallicPowder = new Material.Builder(++id, gcylId("platinum_metallic_powder"))
                .dust()
                .color(Platinum.getMaterialRGB())
                .flags(DISABLE_REPLICATION,DISABLE_DECOMPOSITION)
                .iconSet(SAND)
                .components(Platinum,1,RareEarth,1)
                .build();

        CrudeRhodiumMetal = new Material.Builder(++id, gcylId("crude_rhodium_metal"))
                .dust()
                .color(0x666666)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(DULL)
                .components(Rhodium,1,RareEarth,1)
                .build();

        PGSDResidue2 = new Material.Builder(++id, gcylId("metallic_sludge_dust_residue"))
                .dust()
                .color((Copper.getMaterialRGB()+Nickel.getMaterialRGB())/2)
                .flags(DISABLE_REPLICATION, DECOMPOSITION_BY_CENTRIFUGING)
                .iconSet(DULL)
                .components(Copper, 1, Nickel,1)
                .build();

        PlatinumResidue = new Material.Builder(++id, gcylId("platinum_residue"))
                .dust()
                .color(0x64632E)
                .flags(DISABLE_REPLICATION,DISABLE_DECOMPOSITION)
                .iconSet(ROUGH)
                .components(Iridium,2,RareEarth,1)
                .build();

        PotassiumDisulfate = new Material.Builder(++id, gcylId("potassium_disulfate"))
                .dust()
                .color(0xFBBB66)
                .flags(DISABLE_REPLICATION, EXCLUDE_BLOCK_CRAFTING_RECIPES)
                .iconSet(DULL)
                .components(Potassium,2,Sulfur,2,Oxygen,7)
                .build();

        LeachResidue = new Material.Builder(++id, gcylId("leach_residue"))
                .dust()
                .color(0x644629)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(ROUGH)
                .components(Iridium,2,RareEarth,1)
                .build();

        PalladiumSalt = new Material.Builder(++id, gcylId("palladium_salt"))
                .dust()
                .color(Palladium.getMaterialRGB())
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(METALLIC)
                .components(Palladium,1,RareEarth,1)
                .build();

        PalladiumRawPowder = new Material.Builder(++id, gcylId("reprecipitated_palladium"))
                .dust()
                .color(Palladium.getMaterialRGB())
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(METALLIC)
                .components(Palladium,1,Ammonia,1)
                .build();

        RhodiumNitrate = new Material.Builder(++id, gcylId("rhodium_nitrate"))
                .dust()
                .color((SodiumNitrate.getMaterialRGB()+Rhodium.getMaterialRGB())/2)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(QUARTZ)
                .components(Rhodium,1,Ammonia,3)
                .build();

        RhodiumFilterCake = new Material.Builder(++id, gcylId("rhodium_filter_cake"))
                .dust()
                .color(RhodiumNitrate.getMaterialRGB()-10)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(QUARTZ)
                .components(Rhodium,1,RareEarth,1)
                .build();

        RhodiumSalt = new Material.Builder(++id, gcylId("rhodium_salt"))
                .gem()
                .color(0x848484)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(GEM_VERTICAL)
                .components(Rhodium,1,Salt,2)
                .build();

        PalladiumMetallicPowder = new Material.Builder(++id, gcylId("palladium_metallic_powder"))
                .dust()
                .color(Palladium.getMaterialRGB())
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(SAND)
                .components(Palladium,1,RareEarth,1)
                .build();

        MicaPulp = new Material.Builder(++id, gcylId("mica_based"))
                .dust()
                .color(0x917445)
                .flags(DISABLE_REPLICATION, DISABLE_DECONSTRUCTION)
                .iconSet(SAND)
                .components(Mica,1,RareEarth,1)
                .build();

        NickelOxideHydroxide = new Material.Builder(++id, gcylId("nickel_oxide_hydroxide"))
                .dust()
                .color(0xa2f2a2)
                .flags(DISABLE_REPLICATION)
                .iconSet(METALLIC)
                .build()
                .setFormula("NiO(OH)",true);

        LithiumCobaltOxide = new Material.Builder(++id, gcylId("lithium_cobalt_oxide"))
                .dust()
                .color(0xd2a4f3)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("LiCoO",true);

        LithiumTriflate = new Material.Builder(++id, gcylId("lithium_triflate"))
                .dust()
                .color(0xe2dae3)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("LiCSO3F3",true);

        Xylose = new Material.Builder(++id, gcylId("xylose"))
                .dust()
                .color(Glucose.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("C5H10O5",true);

        SodiumAlginateSolution = new Material.Builder(++id, gcylId("sodium_alginate_solution"))
                .liquid()
                .color(0xca8642)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("NaC6H7O6",true);

        CalciumAlginate = new Material.Builder(++id, gcylId("calcium_alginate"))
                .dust()
                .color(0x654321)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("CaC12H14O12",true);

        SiliconNanoparticles = new Material.Builder(++id, gcylId("silicon_nanoparticles"))
                .dust()
                .color(Silicon.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Si?",true);

        Sorbose = new Material.Builder(++id, gcylId("sorbose"))
                .dust()
                .color(Glucose.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("C6H12O6",true);

        AscorbicAcid = new Material.Builder(++id, gcylId("ascorbic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0xe6cd00)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H8O6",true);

        DehydroascorbicAcid = new Material.Builder(++id, gcylId("dehydroascorbic_acid"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(0xe6cd00)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C6H6O6",true);

        GalliumChloride = new Material.Builder(++id, gcylId("gallium_chloride"))
                .dust()
                .color(0x92867a)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("GaCl3",true);

        Halloysite = new Material.Builder(++id, gcylId("halloysite"))
                .dust()
                .color(0x23423a)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("Al9Si10O50Ga",true);

        SulfurCoatedHalloysite = new Material.Builder(++id, gcylId("sulfur_coated_halloysite"))
                .dust()
                .color(0x23973a)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("S2C2(Al9Si10O50Ga)",true);

        FluorideBatteryElectrolyte = new Material.Builder(++id, gcylId("fluoride_battery_electrolyte"))
                .dust()
                .color(0x9a628a)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("La9BaF29(C8H7F)",true);

        LanthanumNickelOxide = new Material.Builder(++id, gcylId("lanthanum_nickel_oxide"))
                .dust()
                .color(Garnierite.getMaterialRGB()/2+Lanthanum.getMaterialRGB()/2)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("La2NiO4",true);

        HG1223 = new Material.Builder(++id, gcylId("hg_alloy"))
                .ingot().liquid()
                .color(0x245397)
                .iconSet(METALLIC)
                .flags(DISABLE_REPLICATION, GENERATE_FRAME, GENERATE_DENSE)
                .components(Mercury,1,Barium,2,Calcium,2,Copper,3,Oxygen,8)
                .blast(5325)
                .build();

        Indalloy140 = new Material.Builder(++id,gcylId("indalloy_140"))
                .liquid(new FluidBuilder().temperature(5475))
                .color(0x59536E)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .components(Bismuth, 47, Lead, 25, Tin, 13, Cadmium, 10, Indium, 5)
                .build();

        Seabohrgium = new Material.Builder(++id, gcylId("seabohrgium"))
                .liquid()
                .color((Seaborgium.getMaterialRGB()+Bohrium.getMaterialRGB())/2)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .components(Seaborgium, 1, Bohrium, 1)
                .build();

        Fordnium = new Material.Builder(++id, gcylId("fordnium"))
                .liquid()
                .color((Rutherfordium.getMaterialRGB()+Dubnium.getMaterialRGB())/2)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .iconSet(FLUID)
                .components(Rutherfordium, 1, Dubnium, 1)
                .build();

        Enargite = new Material.Builder(++id, gcylId("enargite"))
                .dust(2)
                .color(12303291)
                .flags(DISABLE_REPLICATION)
                .components(Copper,3,Arsenic,1,Sulfur,4)
                .iconSet(METALLIC)
                .build();

        Dolomite = new Material.Builder(++id, gcylId("dolomite"))
                .dust(1)
                .color(14798285)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLINT)
                .components(Calcium,1,Magnesium,1,Carbon,2,Oxygen,6)
                .build();

        Wollastonite = new Material.Builder(++id, gcylId("wollastonite"))
                .dust(2)
                .color(15790320)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .components(Calcium,1,Silicon,1,Oxygen,3)
                .build();

        Kaolinite = new Material.Builder(++id, gcylId("kaolinite"))
                .dust(2)
                .color(16116715)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .components(Aluminium,2,Silicon,2,Hydrogen,4,Oxygen,9)
                .build();

        Tenorite = new Material.Builder(++id, gcylId("tenorite"))
                .dust(1)
                .color(6316128)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .components(Copper,1,Oxygen,1)
                .build();

        Tennantite = new Material.Builder(++id, gcylId("tennantite"))
                .dust(2)
                .color(9474192)
                .flags(DISABLE_REPLICATION)
                .iconSet(METALLIC)
                .components(Copper,12,Arsenic,4,Sulfur,13)
                .build();

        Cuprite = new Material.Builder(++id, gcylId("cuprite"))
                .dust(2)
                .color(7798784)
                .flags(DISABLE_REPLICATION)
                .iconSet(RUBY)
                .components(Copper,2,Oxygen,1)
                .build();

        Zirkelite = new Material.Builder(++id, gcylId("zirkelite"))
                .dust(2)
                .color(0x6B5E6A)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .components(Calcium,2,Thorium,2,Cerium,1,Zirconium,7,Rutile,6,Niobium,4,Oxygen,10)
                .build();

        Arsenopyrite = new Material.Builder(++id, gcylId("arsenopyrite"))
                .dust(2)
                .color(0xaa9663)
                .flags(DISABLE_REPLICATION)
                .iconSet(METALLIC)
                .components(Iron,1,Arsenic,1,Sulfur,1)
                .build();

        BrightSteel = new Material.Builder(++id, gcylId("bright_steel"))
                .ingot().liquid()
                .color(0XD3D3D3)
                .iconSet(DULL)
                .flags(DISABLE_REPLICATION)
                .components(Steel, 4, Bismuth, 2, Caesium, 2, Europium, 1)
                .cableProperties(8192, 3,2)
                .blast(2900)
                .build();

        Gallite = new Material.Builder(++id, gcylId("gallite"))
                .dust(2)
                .color(0x7f7b9e)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .components(Copper,1,Gallium,1,Sulfur,2)
                .build();

        BabbittAlloy = new Material.Builder(++id, gcylId("babbitt_alloy"))
                .ingot(4).liquid()
                .color(0xA19CA4)
                .iconSet(METALLIC)
                .flags(DISABLE_REPLICATION)
                .components(Tin, 5, Lead,36, Antimony,8,Arsenic,1)
                .blast(737)
                .build();

        Inconel625 = new Material.Builder(++id, gcylId("inconel_a"))
                .ingot(6).liquid()
                .color(0x80C880)
                .iconSet(METALLIC)
                .flags(DISABLE_REPLICATION)
                .components(Nickel,3,Chrome,7,Molybdenum,10,Invar,10,Nichrome,13)
                .blast(2425)
                .build();

        ApatiteAcidicLeach = new Material.Builder(++id, gcylId("apatite_acidic_leach"))
                .liquid()
                .color(PhosphoricAcid.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H10P3O12Cl??",true);

        PhosphorousArsenicSolution = new Material.Builder(++id, gcylId("phosphorous_arsenic_solution"))
                .liquid()
                .color(PhosphoricAcid.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("AsCd(HPO4)10",true);

        ApatiteSolidResidue = new Material.Builder(++id, gcylId("apatite_solid_residue"))
                .dust()
                .color(0x3cb290)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("Ca6PO4SiO3",true);

        FluoroApatite = new Material.Builder(++id, gcylId("fluoroapatite"))
                .dust(2)
                .color(Apatite.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .components(Calcium,5,Phosphate,3,Fluorine,1)
                .build();

        FluoroapatiteAcidicLeach = new Material.Builder(++id, gcylId("fluoroapatite_acidic_leach"))
                .liquid(new FluidBuilder().attribute(ACID))
                .color(PhosphoricAcid.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("H10P3O12F??",true);

        FluoroapatiteSolidResidue = new Material.Builder(++id, gcylId("fluoroapatite_solid_residue"))
                .dust()
                .color(0x3cb290)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("Ca6PO4SiO3F",true);

        SodiumArsenate = new Material.Builder(++id, gcylId("sodium_arsenate"))
                .dust()
                .color(0xbffabf)
                .flags(DISABLE_REPLICATION)
                .iconSet(METALLIC)
                .build()
                .setFormula("Na3AsO4",true);

        FinelyPowderedRutile = new Material.Builder(++id, gcylId("finely_powdered_rutile"))
                .dust()
                .color(0xffffff)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("TiO2",true);

        RawSienna = new Material.Builder(++id, gcylId("raw_siena"))
                .dust()
                .color(0x663300)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("SiO2(MnO2)(FeO2)",true);

        BurnedSienna = new Material.Builder(++id, gcylId("burned_siena"))
                .dust()
                .color(0xff0000)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("SiO2(MnO2)(FeO2)",true);

        MercuryNitrate = new Material.Builder(++id, gcylId("mercury_nitrate"))
                .liquid()
                .color(0xd6b8ad)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("Hg(NO3)2",true);

        MercuryChloride = new Material.Builder(++id, gcylId("mercury_chloride"))
                .dust()
                .color(0xd6b8ad)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("HgCl2",true);

        MercuryIodide = new Material.Builder(++id, gcylId("mercury_iodide"))
                .liquid()
                .color(0xff0000)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("HgI2",true);

        BismuthVanadateSolution  = new Material.Builder(++id, gcylId("bismuth_vanadate_solution"))
                .liquid()
                .color(0xffff00)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("(H2O)BiVO4",true);

        BismuthVanadate = new Material.Builder(++id, gcylId("bismuth_vanadate"))
                .dust()
                .color(0xffff00)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("BiVO4",true);

        CopperArsenite = new Material.Builder(++id, gcylId("copper_arsenite"))
                .dust()
                .color(0x66ff66)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("Cu3(AsO4)2",true);

        ScheelesGreen = new Material.Builder(++id, gcylId("scheeles_green"))
                .dust()
                .color(0x00ff00)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("AsCuHO3",true);

        CobaltZincOxide = new Material.Builder(++id, gcylId("cobalt_zinc_oxide"))
                .dust()
                .color(0x00ffff)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("CoZn4O5",true);

        CobaltAluminate = new Material.Builder(++id, gcylId("cobalt_aluminate"))
                .dust()
                .color(0x0000ff)
                .flags(DISABLE_REPLICATION)
                .iconSet(FINE)
                .build()
                .setFormula("Al2Co2O5",true);

        PotassiumFerrocyanide = new Material.Builder(++id, gcylId("potassium_ferrocyanide"))
                .dust()
                .color(0x0000ff)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("K4Fe(CN)6(H2O)3",true);

        PrussianBlue = new Material.Builder(++id, gcylId("prussian_blue"))
                .dust()
                .color(0x0000ff)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("Fe4(Fe(CN)6)3",true);

        TitaniumYellow = new Material.Builder(++id, gcylId("titanium_yellow"))
                .dust()
                .color(0xffff00)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("NiO(Sb2O3)(TiO2)20",true);

        ManganeseIIIOxide = new Material.Builder(++id, gcylId("manganese_iii_oxide"))
                .dust()
                .color(Pyrolusite.getMaterialRGB())
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("Mn2O3",true);

        AmmoniumManganesePhosphate = new Material.Builder(++id, gcylId("ammonium_manganese_phosphate"))
                .dust()
                .color(0x660066)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("NH4MnPO4",true);

        HanPurple = new Material.Builder(++id, gcylId("hans_purple"))
                .dust()
                .color(0x660066)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("BaCuSi2O6",true);

        ChromeYellow = new Material.Builder(++id, gcylId("chrome_yellow"))
                .dust()
                .color(0xffff00)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("PbCrO4",true);

        ChromeOrange = new Material.Builder(++id, gcylId("chrome_orange"))
                .dust()
                .color(0xff6600)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("Pb2CrO5",true);

        Nitrotoluene = new Material.Builder(++id, gcylId("nitrotoluene"))
                .liquid()
                .color(0xfcca00)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C7H7NO2",true);

        DiaminostilbenedisulfonicAcid = new Material.Builder(++id, gcylId("diaminostilbenedisulfonic_acid"))
                .dust()
                .color(0xffffff)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("C14H14N2O6S2",true);

        Nigrosin = new Material.Builder(++id, gcylId("nigrosin"))
                .dust()
                .color(0x000000)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("C36H26N5ClNa2S2O6",true);

        SodiumSulfanilate = new Material.Builder(++id, gcylId("sodium_sulfanilate"))
                .dust()
                .color(0xe49879)
                .flags(DISABLE_REPLICATION)
                .iconSet(SHINY)
                .build()
                .setFormula("C6H6NNaO3S",true);

        Naphthylamine = new Material.Builder(++id, gcylId("naphthylamine"))
                .liquid()
                .color(0xe3e81c)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C10H9N",true);

        DirectBrown = new Material.Builder(++id, gcylId("direct_brown"))
                .dust()
                .color(0x663300)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("C26H19N6NaO3S",true);

        DianilineterephthalicAcid = new Material.Builder(++id, gcylId("dianilineterephthalic_acid"))
                .dust()
                .color(0xff0000)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("C20H16N2O4",true);

        Quinacridone = new Material.Builder(++id, gcylId("quinacridone"))
                .dust()
                .color(0xff0000)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("C20H12N2O2",true);

        Acetoacetanilide = new Material.Builder(++id, gcylId("acetoacetanilide"))
                .liquid()
                .color(0xffffc2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C10H11NO2",true);

        DiarylideYellow = new Material.Builder(++id, gcylId("diarylide_yellow"))
                .liquid()
                .color(0xffff00)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("C32H26Cl2N6O4",true);

        Quinizarin = new Material.Builder(++id, gcylId("quinizarin"))
                .liquid()
                .color(0x3c5a2c0)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C14H8O4",true);

        Toluidine = new Material.Builder(++id, gcylId("toluidine"))
                .liquid()
                .color((Toluene.getMaterialRGB()+ Aniline.getMaterialRGB())/2)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C7H9N",true);

        AlizarineCyanineGreen = new Material.Builder(++id, gcylId("alizarine_cyanine_green"))
                .dust()
                .color(0x00ff00)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("C28H20N2Na2O8S2",true);

        Anthraquinone = new Material.Builder(++id, gcylId("anthraquinone"))
                .dust()
                .color(0xfff782)
                .flags(DISABLE_REPLICATION)
                .iconSet(ROUGH)
                .build()
                .setFormula("C14H8O2",true);

        Aminoanthraquinone = new Material.Builder(++id, gcylId("aminoanthraquinone"))
                .dust()
                .color(0x0000ff)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("C14H9NO2",true);

        IndanthroneBlue = new Material.Builder(++id, gcylId("indanthrone_blue"))
                .dust()
                .color(0x0000ff)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C28H14N2O2",true);

        Mauveine = new Material.Builder(++id, gcylId("mauveine"))
                .dust()
                .color(0x660066)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("C26H23N4",true);

        Isopropylsuccinate = new Material.Builder(++id, gcylId("isopropylsuccinate"))
                .liquid()
                .color(0xb26680)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C7H12O4",true);

        Benzonitrile = new Material.Builder(++id, gcylId("benzonitrile"))
                .liquid()
                .color(0x2c2c9c)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("C7H5N",true);

        Diketopyrrolopyrrole = new Material.Builder(++id, gcylId("diketopyrrolopyrrole"))
                .dust()
                .color(0xff6600)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("C18H12N2O2",true);

        Indigo = new Material.Builder(++id, gcylId("indigo"))
                .dust()
                .color(0x0000ff)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("C16H10N2O2",true);

        Tetrabromoindigo = new Material.Builder(++id, gcylId("tetrabromoindigo"))
                .dust()
                .color(0x00ff00)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("C16H6Br2N2O2",true);

        CyanIndigoDye = new Material.Builder(++id, gcylId("cyan_indigo_dye"))
                .dust()
                .color(0x009999)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("(C16H10N2O2)2Br2",true);

        Erythrosine = new Material.Builder(++id, gcylId("erythrosine"))
                .dust()
                .color(0xff00ff)
                .flags(DISABLE_REPLICATION)
                .iconSet(DULL)
                .build()
                .setFormula("C20H6I4Na2O5",true);

        DeepOverworldGas = new Material.Builder(++id, gcylId("deep_overworld_gas"))
                .gas()
                .color(Air.getMaterialRGB() - 10)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?",true);

        DeepNetherGas = new Material.Builder(++id, gcylId("deep_nether_gas"))
                .gas()
                .color(NetherAir.getMaterialRGB()-10)
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("?",true);

    }

    private static void registerSuperconductors() {

        LVSuperconductorBase = new Material.Builder(++id, gcylId("lv_superconductor_base"))
                .ingot().liquid()
                .color(0xffffff)
                .iconSet(DULL)
                .flags(DISABLE_REPLICATION)
                .components(SolderingAlloy,10,Gallium,2)
                .cableProperties(GTValues.V[GTValues.LV],4,4)
                .blast(1000)
                .build();

        MVSuperconductorBase = new Material.Builder(++id, gcylId("mv_superconductor_base"))
                .ingot().liquid()
                .color(0x535353)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .components(Cadmium,5,Magnesium,1,Oxygen,6)
                .cableProperties(GTValues.V[GTValues.MV],4,16)
                .blast(1200)
                .build();

        HVSuperconductorBase = new Material.Builder(++id, gcylId("hv_superconductor_base"))
                .ingot().liquid()
                .color(0x4a2400)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .components(Titanium,1,Barium,9,Copper,10,Oxygen,20)
                .cableProperties(GTValues.V[GTValues.HV],4,64)
                .blast(3300)
                .build();

        EVSuperconductorBase = new Material.Builder(++id, gcylId("ev_superconductor_base"))
                .ingot().liquid()
                .color(0x005800)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .components(Uranium,1,Platinum,3)
                .cableProperties(GTValues.V[GTValues.EV],4,256)
                .blast(4400)
                .build();

        IVSuperconductorBase = new Material.Builder(++id, gcylId("iv_superconductor_base"))
                .ingot().liquid()
                .color(0x300030)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .components(Vanadium,1,Indium,3)
                .blast(5200)
                .cableProperties(GTValues.V[GTValues.IV],6,1024)
                .build();

        LuVSuperconductorBase = new Material.Builder(++id, gcylId("luv_superconductor_base"))
                .ingot().liquid()
                .color(0x7a3c00)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION)
                .components(Indium,4,Bronze,8,Barium,4,Titanium,1,Oxygen,14)
                .cableProperties(GTValues.V[GTValues.LuV],6, 4096)
                .blast(6000)
                .build();

        ZPMSuperconductorBase = new Material.Builder(++id, gcylId("zpm_superconductor_base"))
                .ingot().liquid()
                .color(0x111111)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .components(Naquadah,4,Indium,2,Palladium,6,Osmium,1)
                .cableProperties(GTValues.V[GTValues.ZPM],6,16384)
                .blast(8100)
                .build();

        UVSuperconductorBase = new Material.Builder(++id, gcylId("uv_superconductor_base"))
                .ingot().liquid()
                .color(0xe0d207)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION,DISABLE_DECOMPOSITION)
                .components(Naquadria,4,Osmiridium,3,Rutherfordium,1,Samarium,1)
                .cableProperties(GTValues.V[UV], 8, 65536)
                .blast(8900)
                .build();

        UHVSuperconductorBase = new Material.Builder(++id, gcylId("uhv_superconductor_base"))
                .ingot().liquid()
                .color(0x359ffc)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION,DISABLE_DECOMPOSITION)
                .components(TBCCODust,4,StrontiumSuperconductorDust,4,Amethyst,1)
                .cableProperties(GTValues.V[GTValues.UHV], 8, 262144)
                .blast(10000)
                .build();

        UEVSuperconductorBase = new Material.Builder(++id, gcylId("uev_superconductor_base"))
                .ingot().liquid()
                .color(0x954fe0)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION,DISABLE_DECOMPOSITION)
                .components(ActiniumSuperhydride,1,BETSPerrhenate,1,TriniumTitanium,2,Quantum,1,Vibranium,2)
                .cableProperties(GTValues.V[GTValues.UEV], 8, 1048576)
                .blast(11150)
                .build();

        UIVSuperconductorBase = new Material.Builder(++id, gcylId("uiv_superconductor_base"))
                .ingot().liquid()
                .color(0x8bf743)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION,DISABLE_DECOMPOSITION)
                .components(BorocarbideDust,2,FullereneSuperconductiveDust,1,MetastableOganesson,2,ProtoAdamantium,2)
                .cableProperties(GTValues.V[GTValues.UIV], 16, 4194304)
                .blast(11600)
                .build();

        UXVSuperconductorBase = new Material.Builder(++id, gcylId("uxv_superconductor_base"))
                .ingot().liquid()
                .color(0x883afc)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION,DISABLE_DECOMPOSITION)
                .cableProperties(GTValues.V[GTValues.UXV], 16, 16777216)
                .components(BlackTitanium,3,SuperheavyHAlloy,2, ChargedCaesiumCeriumCobaltIndium,3,RheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate,6)
                .blast(12000)
                .build();

        OpVSuperconductorBase = new Material.Builder(++id, gcylId("opv_superconductor_base"))
                .ingot().liquid()
                .color(0xe34b5a)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION,DISABLE_DECOMPOSITION)
                .cableProperties(GTValues.V[OpV], 16, 67108864)
                .components(Neutronium,4,Legendarium,5,ActiniumSuperhydride,5,LanthanumFullereneNanotubes,4,RheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate,12)
                .blast(14000)
                .build();

        LVSuperconductor = new Material.Builder(++id, gcylId("lv_superconductor"))
                .ingot().liquid()
                .color(0xf8f8ff)
                .iconSet(DULL)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .components(SolderingAlloy,10,Gallium,2)
                .cableProperties(GTValues.V[GTValues.LV],4,0,true)
                .build();

        MVSuperconductor = new Material.Builder(++id, gcylId("mv_superconductor"))
                .ingot().liquid()
                .color(MVSuperconductorBase.getMaterialRGB())
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .cableProperties(GTValues.V[GTValues.MV], 4,0,true)
                .components(MVSuperconductorBase,1)
                .build();

        HVSuperconductor = new Material.Builder(++id, gcylId("hv_superconductor"))
                .ingot().liquid()
                .color(HVSuperconductorBase.getMaterialRGB())
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .cableProperties(GTValues.V[GTValues.HV], 4,0,true)
                .components(HVSuperconductorBase,1)
                .build();

        EVSuperconductor = new Material.Builder(++id, gcylId("ev_superconductor"))
                .ingot().liquid()
                .color(EVSuperconductorBase.getMaterialRGB())
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .cableProperties(GTValues.V[GTValues.EV], 4,0,true)
                .components(EVSuperconductorBase,1)
                .build();

        IVSuperconductor = new Material.Builder(++id, gcylId("iv_superconductor"))
                .ingot().liquid()
                .color(IVSuperconductorBase.getMaterialRGB())
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .cableProperties(GTValues.V[GTValues.IV], 6,0,true)
                .components(IVSuperconductorBase,1)
                .build();

        LuVSuperconductor = new Material.Builder(++id, gcylId("luv_superconductor"))
                .ingot().liquid()
                .color(LuVSuperconductorBase.getMaterialRGB())
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .components(LuVSuperconductorBase,1)
                .cableProperties(GTValues.V[GTValues.LuV], 6,0,true)
                .build();

        ZPMSuperconductor = new Material.Builder(++id, gcylId("zpm_superconductor"))
                .ingot().liquid()
                .color(ZPMSuperconductorBase.getMaterialRGB())
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .cableProperties(GTValues.V[GTValues.ZPM], 6,0,true)
                .components(ZPMSuperconductorBase,1)
                .build();

        UVSuperconductor = new Material.Builder(++id, gcylId("uv_superconductor"))
                .ingot().liquid()
                .color(UVSuperconductorBase.getMaterialRGB())
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .cableProperties(GTValues.V[UV], 8,0,true)
                .components(UVSuperconductorBase,1)
                .build();

        UHVSuperconductor = new Material.Builder(++id, gcylId("uhv_superconductor"))
                .ingot().liquid()
                .color(UHVSuperconductorBase.getMaterialRGB())
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .cableProperties(GTValues.V[GTValues.UHV], 8,0,true)
                .components(UHVSuperconductorBase,1)
                .build();

        UEVSuperconductor = new Material.Builder(++id, gcylId("uev_superconductor"))
                .ingot().liquid()
                .color(UEVSuperconductorBase.getMaterialRGB())
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .cableProperties(GTValues.V[GTValues.UEV], 8,0,true)
                .components(UEVSuperconductorBase,1)
                .build();

        UIVSuperconductor = new Material.Builder(++id, gcylId("uiv_superconductor"))
                .ingot().liquid()
                .color(UIVSuperconductorBase.getMaterialRGB())
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .cableProperties(GTValues.V[GTValues.UIV], 16,0,true)
                .components(UIVSuperconductorBase,1)
                .build();

        UXVSuperconductor = new Material.Builder(++id, gcylId("uxv_superconductor"))
                .ingot().liquid()
                .color(UXVSuperconductorBase.getMaterialRGB())
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .cableProperties(GTValues.V[GTValues.UXV], 16,0,true)
                .components(UXVSuperconductorBase,1)
                .build();

        OpVSuperconductor = new Material.Builder(++id, gcylId("opv_superconductor"))
                .ingot().liquid()
                .color(OpVSuperconductorBase.getMaterialRGB())
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .cableProperties(GTValues.V[OpV], 16,0,true)
                .components(OpVSuperconductorBase,1)
                .build();

        MAXSuperconductor = new Material.Builder(++id, gcylId("max_superconductor"))
                .ingot().liquid()
                .color(0XFFFFFF)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION, DISABLE_DECOMPOSITION)
                .cableProperties(GTValues.V[GTValues.MAX], 32,0,true)
                .components(OpVSuperconductorBase,1)
                .build();
    }

    private static void initDEMaterials() {

        Draconium = new Material.Builder(++id, gcylId("draconium"))
                .ingot().liquid()
                .toolStats(new ToolProperty(10f, 30f,12800,6))
                .color(0x573d85)
                .iconSet(DULL)
                .flags(DISABLE_REPLICATION, GENERATE_PLATE, GENERATE_DENSE, GENERATE_ROD, GENERATE_FRAME, GENERATE_BOLT_SCREW)
                .blast(9200)
                .build()
                .setFormula("*D*");

        AwakenDraconium = new Material.Builder(++id, gcylId("awaken_draconium"))
                .ingot().liquid().plasma()
                .toolStats(new ToolProperty(10f,40f,6,128000))
                .color(0xff571a)
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION, DISABLE_DECONSTRUCTION, GENERATE_PLATE, GENERATE_DENSE, GENERATE_ROD, GENERATE_FRAME, GENERATE_BOLT_SCREW)
                .blast(14000)
                .build()
                .setFormula(makeFancy("*D*"),true);

        Chaos = new Material.Builder(++id, gcylId("chaos"))
                .ingot().liquid().plasma()
                .color(0x696969)
                .toolStats(new ToolProperty(10f,50f,6,1280000))
                .iconSet(DULL)
                .flags(DISABLE_REPLICATION, DISABLE_DECONSTRUCTION, GENERATE_PLATE,  GENERATE_DENSE, GENERATE_ROD, GENERATE_FRAME, GENERATE_FINE_WIRE, GENERATE_BOLT_SCREW)
                .blast(25000)
                .build()
                .setFormula(TextFormatting.OBFUSCATED+"?????????");

        ChaosAlloy = new Material.Builder(++id, gcylId("chaosalloy"))
                .ingot().liquid().plasma()
                .color(0x696969)
                .toolStats(new ToolProperty(10f,60f,6,2560000))
                .iconSet(SHINY)
                .flags(DISABLE_REPLICATION, DISABLE_DECONSTRUCTION, GENERATE_PLATE,  GENERATE_DENSE, GENERATE_FINE_WIRE, GENERATE_BOLT_SCREW)
                .blast(50000)
                .build()
                .setFormula(makeFancy(TextFormatting.OBFUSCATED+"?????????"));
    }

    private static void register3() {
    /*
        = new Material.Builder(++id, gcylId("material"))
                .ingot().liquid()
                .color()
                .iconSet()
                .flags(DISABLE_REPLICATION)
                .components()
                .blast()
                .build();

        = new Material.Builder(++id, gcylId("material"))
                .liquid()
                .color()
                .flags(DISABLE_REPLICATION)
                .iconSet(FLUID)
                .build()
                .setFormula("",true);

        = new Material.Builder(++id, gcylId("material"))
                .dust()
                .color()
                .flags(DISABLE_REPLICATION)
                .iconSet()
                .build()
                .setFormula("",true);


         */
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