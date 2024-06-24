package com.fulltrix.gcyl.item;

import gregtech.api.items.armor.ArmorMetaItem;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.common.items.MetaItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class GCYLCoreItems {
    private static final List<MetaItem<?>> ITEMS = MetaItem.getMetaItems();

    public static MetaItem<?>.MetaValueItem COMPRESSED_COKE_CLAY;

    public static MetaItem<?>.MetaValueItem SMD_CAPACITOR_REFINED;
    public static MetaItem<?>.MetaValueItem SMD_CAPACITOR_NANO;
    public static MetaItem<?>.MetaValueItem SMD_CAPACITOR_QUANTUM;
    public static MetaItem<?>.MetaValueItem SMD_CAPACITOR_CRYSTAL;
    public static MetaItem<?>.MetaValueItem SMD_CAPACITOR_WETWARE;
    public static MetaItem<?>.MetaValueItem SMD_CAPACITOR_BIOWARE;
    public static MetaItem<?>.MetaValueItem SMD_CAPACITOR_OPTICAL;
    public static MetaItem<?>.MetaValueItem SMD_CAPACITOR_EXOTIC;
    public static MetaItem<?>.MetaValueItem SMD_CAPACITOR_COSMIC;
    public static MetaItem<?>.MetaValueItem SMD_CAPACITOR_SUPRACAUSAL;

    public static MetaItem<?>.MetaValueItem SMD_RESISTOR_REFINED;
    public static MetaItem<?>.MetaValueItem SMD_RESISTOR_NANO;
    public static MetaItem<?>.MetaValueItem SMD_RESISTOR_QUANTUM;
    public static MetaItem<?>.MetaValueItem SMD_RESISTOR_CRYSTAL;
    public static MetaItem<?>.MetaValueItem SMD_RESISTOR_WETWARE;
    public static MetaItem<?>.MetaValueItem SMD_RESISTOR_BIOWARE;
    public static MetaItem<?>.MetaValueItem SMD_RESISTOR_OPTICAL;
    public static MetaItem<?>.MetaValueItem SMD_RESISTOR_EXOTIC;
    public static MetaItem<?>.MetaValueItem SMD_RESISTOR_COSMIC;
    public static MetaItem<?>.MetaValueItem SMD_RESISTOR_SUPRACAUSAL;

    public static MetaItem<?>.MetaValueItem SMD_DIODE_REFINED;
    public static MetaItem<?>.MetaValueItem SMD_DIODE_NANO;
    public static MetaItem<?>.MetaValueItem SMD_DIODE_QUANTUM;
    public static MetaItem<?>.MetaValueItem SMD_DIODE_CRYSTAL;
    public static MetaItem<?>.MetaValueItem SMD_DIODE_WETWARE;
    public static MetaItem<?>.MetaValueItem SMD_DIODE_BIOWARE;
    public static MetaItem<?>.MetaValueItem SMD_DIODE_OPTICAL;
    public static MetaItem<?>.MetaValueItem SMD_DIODE_EXOTIC;
    public static MetaItem<?>.MetaValueItem SMD_DIODE_COSMIC;
    public static MetaItem<?>.MetaValueItem SMD_DIODE_SUPRACAUSAL;

    public static MetaItem<?>.MetaValueItem SMD_TRANSISTOR_REFINED;
    public static MetaItem<?>.MetaValueItem SMD_TRANSISTOR_NANO;
    public static MetaItem<?>.MetaValueItem SMD_TRANSISTOR_QUANTUM;
    public static MetaItem<?>.MetaValueItem SMD_TRANSISTOR_CRYSTAL;
    public static MetaItem<?>.MetaValueItem SMD_TRANSISTOR_WETWARE;
    public static MetaItem<?>.MetaValueItem SMD_TRANSISTOR_BIOWARE;
    public static MetaItem<?>.MetaValueItem SMD_TRANSISTOR_OPTICAL;
    public static MetaItem<?>.MetaValueItem SMD_TRANSISTOR_EXOTIC;
    public static MetaItem<?>.MetaValueItem SMD_TRANSISTOR_COSMIC;
    public static MetaItem<?>.MetaValueItem SMD_TRANSISTOR_SUPRACAUSAL;

    //circuits
    public static MetaItem<?>.MetaValueItem BASIC_CIRCUIT_LV;
    public static MetaItem<?>.MetaValueItem PRIMITIVE_ASSEMBLY;
    public static MetaItem<?>.MetaValueItem ELECTRONIC_ASSEMBLY;
    public static MetaItem<?>.MetaValueItem ELECTRONIC_COMPUTER;
    public static MetaItem<?>.MetaValueItem REFINED_PROCESSOR;
    public static MetaItem<?>.MetaValueItem REFINED_ASSEMBLY;
    public static MetaItem<?>.MetaValueItem REFINED_COMPUTER;
    public static MetaItem<?>.MetaValueItem REFINED_MAINFRAME;
    public static MetaItem<?>.MetaValueItem MICRO_PROCESSOR;
    public static MetaItem<?>.MetaValueItem MICRO_COMPUTER;


    //magneto circuit
    public static MetaItem<?>.MetaValueItem RAW_IMPRINT_SUPPORTED_BOARD;
    public static MetaItem<?>.MetaValueItem IMPRINT_SUPPORTED_BOARD;
    public static MetaItem<?>.MetaValueItem CIRCUIT_MAGNETIC_ULV;
    public static MetaItem<?>.MetaValueItem CIRCUIT_MAGNETIC_LV;
    public static MetaItem<?>.MetaValueItem CIRCUIT_MAGNETIC_MV;
    public static MetaItem<?>.MetaValueItem CIRCUIT_MAGNETIC_HV;
    public static MetaItem<?>.MetaValueItem CIRCUIT_MAGNETIC_EV;
    public static MetaItem<?>.MetaValueItem CIRCUIT_MAGNETIC_IV;
    public static MetaItem<?>.MetaValueItem CIRCUIT_MAGNETIC_LUV;
    public static MetaItem<?>.MetaValueItem CIRCUIT_MAGNETIC_ZPM;
    public static MetaItem<?>.MetaValueItem CIRCUIT_MAGNETIC_UV;
    public static MetaItem<?>.MetaValueItem CIRCUIT_MAGNETIC_UHV;
    public static MetaItem<?>.MetaValueItem CIRCUIT_MAGNETIC_UEV;

    public static MetaItem<?>.MetaValueItem MICA_SHEET;
    public static MetaItem<?>.MetaValueItem MICA_INSULATOR_SHEET;
    public static MetaItem<?>.MetaValueItem MICA_INSULATOR_FOIL;

    public static MetaItem<?>.MetaValueItem KAPTON_BOARD;
    public static MetaItem<?>.MetaValueItem KAPTON_CIRCUIT_BOARD;

    public static MetaItem<?>.MetaValueItem ELECTRODE_APATITE;
    public static MetaItem<?>.MetaValueItem ELECTRODE_BLAZE;
    public static MetaItem<?>.MetaValueItem ELECTRODE_BRONZE;
    public static MetaItem<?>.MetaValueItem ELECTRODE_COPPER;
    public static MetaItem<?>.MetaValueItem ELECTRODE_DIAMOND;
    public static MetaItem<?>.MetaValueItem ELECTRODE_EMERALD;
    public static MetaItem<?>.MetaValueItem ELECTRODE_ENDER;
    public static MetaItem<?>.MetaValueItem ELECTRODE_GOLD;
    public static MetaItem<?>.MetaValueItem ELECTRODE_IRON;
    public static MetaItem<?>.MetaValueItem ELECTRODE_LAPIS;
    public static MetaItem<?>.MetaValueItem ELECTRODE_OBSIDIAN;
    public static MetaItem<?>.MetaValueItem ELECTRODE_ORCHID;
    public static MetaItem<?>.MetaValueItem ELECTRODE_RUBBER;
    public static MetaItem<?>.MetaValueItem ELECTRODE_TIN;

    public static MetaItem<?>.MetaValueItem BENDING_CYLINDER;
    public static MetaItem<?>.MetaValueItem SMALL_BENDING_CYLINDER;
    public static MetaItem<?>.MetaValueItem PROSPECT_TOOL_MV;
    public static MetaItem<?>.MetaValueItem PROSPECT_TOOL_HV;
    public static MetaItem<?>.MetaValueItem PROSPECT_TOOL_LuV;
    public static MetaItem<?>.MetaValueItem PROSPECT_TOOL_ZPM;

    public static MetaItem<?>.MetaValueItem SCHEMATIC;
    public static MetaItem<?>.MetaValueItem SCHEMATIC_2X2;
    public static MetaItem<?>.MetaValueItem SCHEMATIC_3X3;
    public static MetaItem<?>.MetaValueItem SCHEMATIC_DUST;

    public static MetaItem<?>.MetaValueItem HUGE_TURBINE_ROTOR;
    public static MetaItem<?>.MetaValueItem LARGE_TURBINE_ROTOR;
    public static MetaItem<?>.MetaValueItem MEDIUM_TURBINE_ROTOR;
    public static MetaItem<?>.MetaValueItem SMALL_TURBINE_ROTOR;


    public static ArmorMetaItem<?>.ArmorMetaValueItem NIGHTVISION_GOGGLES;

    public static ArmorMetaItem<?>.ArmorMetaValueItem NANO_MUSCLE_SUITE_CHESTPLATE;
    public static ArmorMetaItem<?>.ArmorMetaValueItem NANO_MUSCLE_SUITE_LEGGINS;
    public static ArmorMetaItem<?>.ArmorMetaValueItem NANO_MUSCLE_SUITE_BOOTS;
    public static ArmorMetaItem<?>.ArmorMetaValueItem NANO_MUSCLE_SUITE_HELMET;

    public static ArmorMetaItem<?>.ArmorMetaValueItem QUARK_TECH_SUITE_CHESTPLATE;
    public static ArmorMetaItem<?>.ArmorMetaValueItem QUARK_TECH_SUITE_LEGGINS;
    public static ArmorMetaItem<?>.ArmorMetaValueItem QUARK_TECH_SUITE_BOOTS;
    public static ArmorMetaItem<?>.ArmorMetaValueItem QUARK_TECH_SUITE_HELMET;

    public static ArmorMetaItem<?>.ArmorMetaValueItem SEMIFLUID_JETPACK;
    public static ArmorMetaItem<?>.ArmorMetaValueItem IMPELLER_JETPACK;

    public static ArmorMetaItem<?>.ArmorMetaValueItem BATPACK_LV;
    public static ArmorMetaItem<?>.ArmorMetaValueItem BATPACK_MV;
    public static ArmorMetaItem<?>.ArmorMetaValueItem BATPACK_HV;

    public static ArmorMetaItem<?>.ArmorMetaValueItem ADVANCED_IMPELLER_JETPACK;
    public static ArmorMetaItem<?>.ArmorMetaValueItem ADVANCED_NANO_MUSCLE_CHESTPLATE;
    public static ArmorMetaItem<?>.ArmorMetaValueItem ADVANCED_QAURK_TECH_SUITE_CHESTPLATE;

    public static MetaItem<?>.MetaValueItem IMPELLER_MV;
    public static MetaItem<?>.MetaValueItem IMPELLER_HV;
    public static MetaItem<?>.MetaValueItem GRAVITATION_ENGINE;
    public static MetaItem<?>.MetaValueItem INSULATING_TAPE;

    public static MetaItem<?>.MetaValueItem HAND_PUMP;
    public static MetaItem<?>.MetaValueItem FREEDOM_WRENCH;


    public static MetaItem<?>.MetaValueItem NUCLEAR_WASTE;
    public static MetaItem<?>.MetaValueItem NUCLEAR_WASTE_LANTHANIDE_A;
    public static MetaItem<?>.MetaValueItem NUCLEAR_WASTE_LANTHANIDE_B;
    public static MetaItem<?>.MetaValueItem NUCLEAR_WASTE_ALKALINE;
    public static MetaItem<?>.MetaValueItem NUCLEAR_WASTE_METAL_A;
    public static MetaItem<?>.MetaValueItem NUCLEAR_WASTE_METAL_B;
    public static MetaItem<?>.MetaValueItem NUCLEAR_WASTE_METAL_C;
    public static MetaItem<?>.MetaValueItem NUCLEAR_WASTE_HEAVY_METAL;
    public static MetaItem<?>.MetaValueItem NUCLEAR_WASTE_METALOID;
    public static MetaItem<?>.MetaValueItem NUCLEAR_WASTE_REACTIVE_NONMETAL;
    public static MetaItem<?>.MetaValueItem THORIUM_WASTE;
    public static MetaItem<?>.MetaValueItem PROTACTINIUM_WASTE;
    public static MetaItem<?>.MetaValueItem URANIUM_WASTE;
    public static MetaItem<?>.MetaValueItem NEPTUNIUM_WASTE;
    public static MetaItem<?>.MetaValueItem PLUTONIUM_WASTE;
    public static MetaItem<?>.MetaValueItem AMERICIUM_WASTE;
    public static MetaItem<?>.MetaValueItem CURIUM_WASTE;
    public static MetaItem<?>.MetaValueItem BERKELIUM_WASTE;
    public static MetaItem<?>.MetaValueItem CALIFORNIUM_WASTE;
    public static MetaItem<?>.MetaValueItem EINSTEINIUM_WASTE;
    public static MetaItem<?>.MetaValueItem FERMIUM_WASTE;
    public static MetaItem<?>.MetaValueItem MENDELEVIUM_WASTE;

    public static MetaItem<?>.MetaValueItem COSMIC_PROCESSOR;
    public static MetaItem<?>.MetaValueItem COSMIC_ASSEMBLY;
    public static MetaItem<?>.MetaValueItem COSMIC_COMPUTER;
    public static MetaItem<?>.MetaValueItem COSMIC_MAINFRAME;

    public static MetaItem<?>.MetaValueItem BIOWARE_PROCESSOR;
    public static MetaItem<?>.MetaValueItem BIOWARE_ASSEMBLY;
    public static MetaItem<?>.MetaValueItem BIOWARE_COMPUTER;
    public static MetaItem<?>.MetaValueItem BIOWARE_MAINFRAME;

    public static MetaItem<?>.MetaValueItem OPTICAL_PROCESSOR;
    public static MetaItem<?>.MetaValueItem OPTICAL_ASSEMBLY;
    public static MetaItem<?>.MetaValueItem OPTICAL_COMPUTER;
    public static MetaItem<?>.MetaValueItem OPTICAL_MAINFRAME;

    public static MetaItem<?>.MetaValueItem EXOTIC_PROCESSOR;
    public static MetaItem<?>.MetaValueItem EXOTIC_ASSEMBLY;
    public static MetaItem<?>.MetaValueItem EXOTIC_COMPUTER;
    public static MetaItem<?>.MetaValueItem EXOTIC_MAINFRAME;

    public static MetaItem<?>.MetaValueItem SUPRACAUSAL_PROCESSOR;
    public static MetaItem<?>.MetaValueItem SUPRACAUSAL_ASSEMBLY;
    public static MetaItem<?>.MetaValueItem SUPRACAUSAL_COMPUTER;
    public static MetaItem<?>.MetaValueItem SUPRACAUSAL_MAINFRAME;

    public static MetaItem<?>.MetaValueItem WAFER_DUBNIUM;
    public static MetaItem<?>.MetaValueItem WAFER_RUTHERFORDIUM;

    public static MetaItem<?>.MetaValueItem BOULE_DUBNIUM;
    public static MetaItem<?>.MetaValueItem BOULE_RUTHERFORDIUM;

    public static MetaItem<?>.MetaValueItem UNSTABLE_STAR;
    public static MetaItem<?>.MetaValueItem NUCLEAR_STAR;
    public static MetaItem<?>.MetaValueItem CONVEYOR_MODULE_MAX;
    public static MetaItem<?>.MetaValueItem ELECTRIC_MOTOR_MAX;
    public static MetaItem<?>.MetaValueItem ELECTRIC_PISTON_MAX;
    public static MetaItem<?>.MetaValueItem ELECTRIC_PUMP_MAX;
    public static MetaItem<?>.MetaValueItem EMITTER_MAX;
    public static MetaItem<?>.MetaValueItem FIELD_GENERATOR_MAX;
    public static MetaItem<?>.MetaValueItem ROBOT_ARM_MAX;
    public static MetaItem<?>.MetaValueItem SENSOR_MAX;

    public static MetaItem<?>.MetaValueItem ULTRASONIC_HOMOGENIZER;
    public static MetaItem<?>.MetaValueItem STERILIZED_PETRI_DISH;
    public static MetaItem<?>.MetaValueItem CONTAMINATED_PETRI_DISH;
    public static MetaItem<?>.MetaValueItem ELECTRICALLY_WIRED_PETRI_DISH;
    public static MetaItem<?>.MetaValueItem CLEAN_CULTURE;
    public static MetaItem<?>.MetaValueItem PIEZOELECTRIC_CRYSTAL;
    public static MetaItem<?>.MetaValueItem NEURO_SUPPORT_UNIT;
    public static MetaItem<?>.MetaValueItem CYBER_PROCESSING_UNIT;
    public static MetaItem<?>.MetaValueItem SHEWANELLA_CULTURE;
    public static MetaItem<?>.MetaValueItem STREPTOCOCCUS_CULTURE;
    public static MetaItem<?>.MetaValueItem ESCHERICHIA_CULTURE;
    public static MetaItem<?>.MetaValueItem BIFIDOBACTERIUM_CULTURE;
    public static MetaItem<?>.MetaValueItem BREVIBACTERIUM_CULTURE;
    public static MetaItem<?>.MetaValueItem CUPRIVADUS_CULTURE;

    public static MetaItem<?>.MetaValueItem BATTERY_NIMH_EMPTY;
    public static MetaItem<?>.MetaValueItem BATTERY_NIMH;
    public static MetaItem<?>.MetaValueItem BATTERY_SMALL_LITHIUM_ION_EMPTY;
    public static MetaItem<?>.MetaValueItem BATTERY_SMALL_LITHIUM_ION;
    public static MetaItem<?>.MetaValueItem BATTERY_MEDIUM_LITHIUM_ION_EMPTY;
    public static MetaItem<?>.MetaValueItem BATTERY_MEDIUM_LITHIUM_ION;
    public static MetaItem<?>.MetaValueItem BATTERY_LARGE_LITHIUM_ION_EMPTY;
    public static MetaItem<?>.MetaValueItem BATTERY_LARGE_LITHIUM_ION;
    public static MetaItem<?>.MetaValueItem BATTERY_SMALL_LIS_EMPTY;
    public static MetaItem<?>.MetaValueItem BATTERY_SMALL_LIS;
    public static MetaItem<?>.MetaValueItem BATTERY_MEDIUM_LIS_EMPTY;
    public static MetaItem<?>.MetaValueItem BATTERY_MEDIUM_LIS;
    public static MetaItem<?>.MetaValueItem BATTERY_LARGE_LIS_EMPTY;
    public static MetaItem<?>.MetaValueItem BATTERY_LARGE_LIS;
    public static MetaItem<?>.MetaValueItem BATTERY_SMALL_FLUORIDE_EMPTY;
    public static MetaItem<?>.MetaValueItem BATTERY_SMALL_FLUORIDE;
    public static MetaItem<?>.MetaValueItem BATTERY_MEDIUM_FLUORIDE_EMPTY;
    public static MetaItem<?>.MetaValueItem BATTERY_MEDIUM_FLUORIDE;
    public static MetaItem<?>.MetaValueItem BATTERY_LARGE_FLUORIDE_EMPTY;
    public static MetaItem<?>.MetaValueItem BATTERY_LARGE_FLUORIDE;

    public static MetaItem<?>.MetaValueItem UHASOC;
    public static MetaItem<?>.MetaValueItem UHASOC_WAFER;
    public static MetaItem<?>.MetaValueItem ARAM;
    public static MetaItem<?>.MetaValueItem ARAM_WAFER;

    public static MetaItem<?>.MetaValueItem PLATE_FIELD_SHAPE;
    public static MetaItem<?>.MetaValueItem INGOT_FIELD_SHAPE;
    public static MetaItem<?>.MetaValueItem PLASMA_CONTAINMENT_CELL;
    public static MetaItem<?>.MetaValueItem RHENIUM_PLASMA_CONTAINMENT_CELL;
    public static MetaItem<?>.MetaValueItem NEUTRON_PLASMA_CONTAINMENT_CELL;
    public static MetaItem<?>.MetaValueItem DEGENERATE_RHENIUM_PLATE;
    public static MetaItem<?>.MetaValueItem DEGENERATE_RHENIUM_DUST;

    public static MetaItem<?>.MetaValueItem ZBLAN;
    public static MetaItem<?>.MetaValueItem ZBLAN_INGOT;
    public static MetaItem<?>.MetaValueItem HOT_ANNEALED_ZBLAN_INGOT;
    public static MetaItem<?>.MetaValueItem ZBLAN_FIBER;
    public static MetaItem<?>.MetaValueItem ERBIUM_DOPED_ZBLAN;
    public static MetaItem<?>.MetaValueItem CLADDED_OPTICAL_FIBER_CORE;

    public static MetaItem<?>.MetaValueItem PYROLYTIC_CARBON;
    public static MetaItem<?>.MetaValueItem HOT_IRON_INGOT;

    public static MetaItem<?>.MetaValueItem ACRYLIC_YARN;
    public static MetaItem<?>.MetaValueItem RAPIDLY_ROTATING_CRUCIBLE;
    public static MetaItem<?>.MetaValueItem HEAVY_METAL_ABSORBING_YARN;
    public static MetaItem<?>.MetaValueItem URANIUM_SATURATED_YARN;
    public static MetaItem<?>.MetaValueItem BORON_RETAINING_YARN;
    public static MetaItem<?>.MetaValueItem BORON_SATURATED_YARN;
    public static MetaItem<?>.MetaValueItem LITHIUM_SIEVE;
    public static MetaItem<?>.MetaValueItem LITHIUM_SATURATED_LITHIUM_SIEVE;
    public static MetaItem<?>.MetaValueItem NANOTOME;
    public static MetaItem<?>.MetaValueItem MEMORY_FOAM_BLOCK;
    public static MetaItem<?>.MetaValueItem LASER_DIODE;
    public static MetaItem<?>.MetaValueItem LASER_COOLING_UNIT;
    public static MetaItem<?>.MetaValueItem MAGNETIC_TRAP;
    public static MetaItem<?>.MetaValueItem EMPTY_LASER_COOLING_CONTAINER;
    public static MetaItem<?>.MetaValueItem BOSE_EINSTEIN_COOLING_CONTAINER;

    public static MetaItem<?>.MetaValueItem ALUMINO_SILICATE_GLASS_TUBE;
    public static MetaItem<?>.MetaValueItem INDUCTOR;
    public static MetaItem<?>.MetaValueItem BALLAST;
    public static MetaItem<?>.MetaValueItem UVA_LAMP_CORE;
    public static MetaItem<?>.MetaValueItem BLUE_LAMP_CORE;
    public static MetaItem<?>.MetaValueItem GREEN_LAMP_CORE;
    public static MetaItem<?>.MetaValueItem RED_LAMP_CORE;
    public static MetaItem<?>.MetaValueItem WHITE_LAMP_CORE;
    public static MetaItem<?>.MetaValueItem UVA_HALIDE_LAMP;
    public static MetaItem<?>.MetaValueItem BLUE_HALIDE_LAMP;
    public static MetaItem<?>.MetaValueItem GREEN_HALIDE_LAMP;
    public static MetaItem<?>.MetaValueItem RED_HALIDE_LAMP;
    public static MetaItem<?>.MetaValueItem WHITE_HALIDE_LAMP;
    public static MetaItem<?>.MetaValueItem ACTINIUM_PLASMA_CONTAINMENT_CELL;

    public static MetaItem<?>.MetaValueItem NDYAG_BOULE;
    public static MetaItem<?>.MetaValueItem PRHOYLF_BOULE;
    public static MetaItem<?>.MetaValueItem LUTMYVO_BOULE;
    public static MetaItem<?>.MetaValueItem NDYAG_ROD;
    public static MetaItem<?>.MetaValueItem PRHOYLF_ROD;
    public static MetaItem<?>.MetaValueItem LUTMYVO_ROD;
    public static MetaItem<?>.MetaValueItem LOW_FREQUENCY_LASER;
    public static MetaItem<?>.MetaValueItem MEDIUM_FREQUENCY_LASER;
    public static MetaItem<?>.MetaValueItem HIGH_FREQUENCY_LASER;
    public static MetaItem<?>.MetaValueItem PERIODICALLY_POLED_LITHIUM_NIOBATE_BOULE;
    public static MetaItem<?>.MetaValueItem NON_LINEAR_OPTICAL_LENS;
    public static MetaItem<?>.MetaValueItem HIGHLY_REFLECTIVE_MIRROR;
    public static MetaItem<?>.MetaValueItem ROTATING_TRANSPARENT_SURFACE;
    public static MetaItem<?>.MetaValueItem ELECTRON_SOURCE;
    public static MetaItem<?>.MetaValueItem OPTICAL_SOC;
    public static MetaItem<?>.MetaValueItem OPTICAL_SOC_WAFER;
    public static MetaItem<?>.MetaValueItem OPTICAL_PROCESSING_CORE;

    public static MetaItem<?>.MetaValueItem PEEK_POLYAMIDE_FOIL;
    public static MetaItem<?>.MetaValueItem HIGHLY_INSULATING_FOIL;
    public static MetaItem<?>.MetaValueItem INSULATION_WIRE_ASSEMBLY;

    public static MetaItem<?>.MetaValueItem NEUTRONIUM_SPHERE;
    public static MetaItem<?>.MetaValueItem TRIPLET_NEUTRONIUM_SPHERE;
    public static MetaItem<?>.MetaValueItem CHARGED_TRIPLET_NEUTRONIUM_SPHERE;
    public static MetaItem<?>.MetaValueItem CONTAINED_REISSNER_NORDSTROM_SINGULARITY;
    public static MetaItem<?>.MetaValueItem CONTAINED_KERR_NEWMANN_SINGULARITY;
    public static MetaItem<?>.MetaValueItem CONTAINED_KERR_SINGULARITY;
    public static MetaItem<?>.MetaValueItem CONTAINED_HIGH_DENSITY_PROTONIC_MATTER;
    public static MetaItem<?>.MetaValueItem TIME_DILATION_CONTAINMENT_UNIT;
    public static MetaItem<?>.MetaValueItem MICROWORMHOLE_GENERATOR;
    public static MetaItem<?>.MetaValueItem MACROWORMHOLE_GENERATOR;
    public static MetaItem<?>.MetaValueItem STABILIZED_WORMHOLE_GENERATOR;
    public static MetaItem<?>.MetaValueItem CONTAINED_EXOTIC_MATTER;
    public static MetaItem<?>.MetaValueItem SEPARATION_ELECTROMAGNET;
    public static MetaItem<?>.MetaValueItem SPHERE_FIELD_SHAPE;

    public static MetaItem<?>.MetaValueItem AEROGRAPHENE;
    public static MetaItem<?>.MetaValueItem SCINTILLATOR_CRYSTAL;
    public static MetaItem<?>.MetaValueItem SCINTILLATOR;
    public static MetaItem<?>.MetaValueItem LEPTON_TRAP_CRYSTAL;
    public static MetaItem<?>.MetaValueItem HASSIUM_SEED_CRYSTAL;
    public static MetaItem<?>.MetaValueItem HASSIUM_BOULE;
    public static MetaItem<?>.MetaValueItem HASSIUM_WAFER;
    public static MetaItem<?>.MetaValueItem COATED_HASSIUM_WAFER;
    public static MetaItem<?>.MetaValueItem PHOTOCOATED_HASSIUM_WAFER;
    public static MetaItem<?>.MetaValueItem GRATING_LITHOGRAPHY_MASK;
    public static MetaItem<?>.MetaValueItem DIFFRACTOR_GRATING_MIRROR;
    public static MetaItem<?>.MetaValueItem ULTRASHORT_PULSE_LASER;
    public static MetaItem<?>.MetaValueItem COSMIC_COMPUTE_UNIT;
    public static MetaItem<?>.MetaValueItem COSMIC_PROCESSING_CORE;
    public static MetaItem<?>.MetaValueItem GRAPHENE_IRON_PLATE;

    public static MetaItem<?>.MetaValueItem TOPOLOGICAL_MANIPULATOR_UNIT;
    public static MetaItem<?>.MetaValueItem RELATIVISTIC_SPINORIAL_MEMORY_SYSTEM;
    public static MetaItem<?>.MetaValueItem GRAVITON_TRANSDUCER;
    public static MetaItem<?>.MetaValueItem NUCLEAR_CLOCK;
    public static MetaItem<?>.MetaValueItem SUPRACAUSAL_PROCESSING_CORE;
    public static MetaItem<?>.MetaValueItem MANIFOLD_OSCILLATORY_POWER_CELL;
    public static MetaItem<?>.MetaValueItem QCD_PROTECTIVE_PLATING;
    public static MetaItem<?>.MetaValueItem CTC_GUIDANCE_UNIT;
    public static MetaItem<?>.MetaValueItem CTC_COMPUTATIONAL_UNIT_CONTAINER;
    public static MetaItem<?>.MetaValueItem EIGENFOLDED_KERR_MANIFOLD;
    public static MetaItem<?>.MetaValueItem CTC_COMPUTATIONAL_UNIT;
    public static MetaItem<?>.MetaValueItem RECURSIVELY_FOLDED_NEGATIVE_SPACE;

    public static MetaItem<?>.MetaValueItem WIRE_FIELD_SHAPE;
    public static MetaItem<?>.MetaValueItem EXTREMELY_DURABLE_PLASMA_CELL;
    public static MetaItem<?>.MetaValueItem DENSE_NEUTRON_PLASMA_CELL;
    public static MetaItem<?>.MetaValueItem COSMIC_NEUTRON_PLASMA_CELL;
    public static MetaItem<?>.MetaValueItem HIGHLY_DENSE_POLYMER_PLATE;
    public static MetaItem<?>.MetaValueItem COSMIC_MESH_CONTAINMENT_UNIT;
    public static MetaItem<?>.MetaValueItem COSMIC_FABRIC;
    public static MetaItem<?>.MetaValueItem COSMIC_MESH;
    public static MetaItem<?>.MetaValueItem ROD_FIELD_SHAPE;


    public static MetaItem<?>.MetaValueItem COLOURED_LEDS;
    public static MetaItem<?>.MetaValueItem DISPLAY;

    //infinite water source cover
//    public static MetaItem<?>.MetaValueItem LV_INFINITE_WATER_SOURCE;
    public static MetaItem<?>.MetaValueItem MV_INFINITE_WATER_SOURCE;
    public static MetaItem<?>.MetaValueItem HV_INFINITE_WATER_SOURCE;
    public static MetaItem<?>.MetaValueItem EV_INFINITE_WATER_SOURCE;
    public static MetaItem<?>.MetaValueItem IV_INFINITE_WATER_SOURCE;
    public static MetaItem<?>.MetaValueItem LuV_INFINITE_WATER_SOURCE;
    public static MetaItem<?>.MetaValueItem ZPM_INFINITE_WATER_SOURCE;
    public static MetaItem<?>.MetaValueItem UV_INFINITE_WATER_SOURCE;

    public static MetaItem<?>.MetaValueItem CHARGED_LEPTON_TRAP_CRYSTAL;
    public static MetaItem<?>.MetaValueItem NANOSILICON_CATHODE;

    public static MetaItem<?>.MetaValueItem FULLERENE_POLYMER_MATRIX_SOFT_TUBING;
    public static MetaItem<?>.MetaValueItem FULLERENE_POLYMER_MATRIX_FINE_TUBING;
    public static MetaItem<?>.MetaValueItem X_RAY_WAVEGUIDE;
    public static MetaItem<?>.MetaValueItem MICROFOCUS_X_RAY_TUBE;
    public static MetaItem<?>.MetaValueItem X_RAY_MIRROR_PLATE;
    public static MetaItem<?>.MetaValueItem UNTREATED_EXOTIC_WAFER;
    public static MetaItem<?>.MetaValueItem EXOTIC_WAFER;
    public static MetaItem<?>.MetaValueItem EXOTIC_CHIP;
    public static MetaItem<?>.MetaValueItem EXCITATION_MAINTAINER;
    public static MetaItem<?>.MetaValueItem CRYOGENIC_INTERFACE;
    public static MetaItem<?>.MetaValueItem RYDBERG_SPINORIAL_ASSEMBLY;
    public static MetaItem<?>.MetaValueItem X_RAY_LASER;
    public static MetaItem<?>.MetaValueItem EXOTIC_PROCESSING_CORE;

    public static MetaItem<?>.MetaValueItem PROTONATED_FULLERENE_SIEVING_MATRIX;
    public static MetaItem<?>.MetaValueItem SATURATED_FULLERENE_SIEVING_MATRIX;

    public static MetaItem<?>.MetaValueItem TOOL_DATA_MODULE_CLUSTER;
    public static MetaItem<?>.MetaValueItem TOOL_DATA_ULTIMATE;
    public static MetaItem<?>.MetaValueItem TOOL_DATA_SUPRACAUSAL;
    public static MetaItem<?>.MetaValueItem TOOL_DATA_DEEP_MINER;
    public static MetaItem<?>.MetaValueItem TOOL_DATA_DEEP_MINER_MODULE;
    public static MetaItem<?>.MetaValueItem TOOL_DATA_DEEP_MINER_COMPLEX;
    public static MetaItem<?>.MetaValueItem COVER_ENDER_ITEM_LINK;
    public static MetaItem<?>.MetaValueItem VOLTAGE_COIL_UHV;
    public static MetaItem<?>.MetaValueItem VOLTAGE_COIL_UEV;
    public static MetaItem<?>.MetaValueItem VOLTAGE_COIL_UIV;
    public static MetaItem<?>.MetaValueItem VOLTAGE_COIL_UXV;
    public static MetaItem<?>.MetaValueItem VOLTAGE_COIL_OpV;
    public static MetaItem<?>.MetaValueItem VOLTAGE_COIL_MAX;

    public static MetaItem<?>.MetaValueItem BIO_CELLS;
    public static MetaItem<?>.MetaValueItem BIO_BOARD;
    public static MetaItem<?>.MetaValueItem BIO_CIRCUIT_BOARD;
    public static MetaItem<?>.MetaValueItem ADVANCED_CRYSTAL_SOC;
    public static MetaItem<?>.MetaValueItem LIVING_SOC;
    public static MetaItem<?>.MetaValueItem LIVING_BIO_SOC;
    public static MetaItem<?>.MetaValueItem BIO_WAFER;

    public static MetaItem<?>.MetaValueItem NPIC_WAFER;
    public static MetaItem<?>.MetaValueItem PPIC_WAFER;
    public static MetaItem<?>.MetaValueItem QPIC_WAFER;
    public static MetaItem<?>.MetaValueItem NPIC;
    public static MetaItem<?>.MetaValueItem PPIC;
    public static MetaItem<?>.MetaValueItem QPIC;

    public static MetaItem<?>.MetaValueItem WIRELESS_BATTERY_UV;




    public static void init() {
        GCYLCoreItem item = new GCYLCoreItem();
        item.setRegistryName("gcyl_meta_item");

        GCYLCoreItem2 item2 = new GCYLCoreItem2((short) 0);
        item2.setRegistryName("gcyl_meta_item2");
        //GAMetaTool tool = new GAMetaTool();
        //tool.setRegistryName("ga_meta_tool");
        // GAMetaArmor armor = new GAMetaArmor();
        // armor.setRegistryName("ga_armor");
        //  GADustItem dustItem = new GADustItem((short) 0);
        // dustItem.setRegistryName("ga_dust");
        // GAMetaItem2 item2 = new GAMetaItem2((short) 0);
        // item2.setRegistryName("ga_meta_item2");
        //  GAOredictItem oreDictItem = new GAOredictItem((short) 0);
        // oreDictItem.setRegistryName("ga_oredict_item");
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels() {
        MinecraftForge.EVENT_BUS.register(MetaItems.class);
        for (MetaItem<?> item : ITEMS) {
            item.registerModels();
            item.registerTextureMesh();
        }
    }

}
