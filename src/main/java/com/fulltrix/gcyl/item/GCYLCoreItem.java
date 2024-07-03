package com.fulltrix.gcyl.item;

import com.fulltrix.gcyl.api.items.WirelessElectricStats;
import com.fulltrix.gcyl.item.behaviors.MinerDataItemBehavior;
import gregtech.api.GTValues;
import gregtech.api.items.metaitem.StandardMetaItem;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.MarkerMaterials.Tier;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.behaviors.DataItemBehavior;
import net.minecraft.item.ItemStack;

import static com.fulltrix.gcyl.item.GCYLCoreItems.*;
import static gregtech.api.unification.material.MarkerMaterials.Tier.*;

public class GCYLCoreItem extends StandardMetaItem {

    @Override
    public void registerSubItems() {

        //BASIC_CIRCUIT_LV = addItem(199, "circuit.basic").setUnificationData(OrePrefix.circuit, Tier.LV);
       // PRIMITIVE_ASSEMBLY = addItem(200, "circuit.assembly.primitive").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.MV);
        //ELECTRONIC_ASSEMBLY = addItem(201, "circuit.assembly.electronic").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.MV);
        //ELECTRONIC_COMPUTER = addItem(202, "circuit.computer.electronic").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.HV);
        REFINED_PROCESSOR = addItem(203, "circuit.processor.refined").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.LV);
        REFINED_ASSEMBLY = addItem(204, "circuit.assembly.refined").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.MV);
        REFINED_COMPUTER = addItem(205, "circuit.computer.refined").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.HV);
        REFINED_MAINFRAME = addItem(206, "circuit.mainframe.refined").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.EV);
        MICRO_PROCESSOR = addItem(207, "circuit.processor.micro").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.MV);
        MICRO_COMPUTER = addItem(209, "circuit.computer.micro").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.EV);

        CIRCUIT_MAGNETIC_ULV = addItem(220, "circuit.resonatic.ulv").setUnificationData(OrePrefix.circuit, ULV);
        CIRCUIT_MAGNETIC_LV = addItem(221, "circuit.resonatic.lv").setUnificationData(OrePrefix.circuit, LV);
        CIRCUIT_MAGNETIC_MV = addItem(222, "circuit.resonatic.mv").setUnificationData(OrePrefix.circuit, MV);
        CIRCUIT_MAGNETIC_HV = addItem(223, "circuit.resonatic.hv").setUnificationData(OrePrefix.circuit, HV);
        CIRCUIT_MAGNETIC_EV = addItem(224, "circuit.resonatic.ev").setUnificationData(OrePrefix.circuit, EV);
        CIRCUIT_MAGNETIC_IV = addItem(225, "circuit.resonatic.iv").setUnificationData(OrePrefix.circuit, IV);
        CIRCUIT_MAGNETIC_LUV = addItem(226, "circuit.resonatic.luv").setUnificationData(OrePrefix.circuit, LuV);
        CIRCUIT_MAGNETIC_ZPM = addItem(227, "circuit.resonatic.zpm").setUnificationData(OrePrefix.circuit, ZPM);
        CIRCUIT_MAGNETIC_UV = addItem(228, "circuit.resonatic.uv").setUnificationData(OrePrefix.circuit, UV);
        CIRCUIT_MAGNETIC_UHV = addItem(229, "circuit.resonatic.uhv").setUnificationData(OrePrefix.circuit, UHV);
        CIRCUIT_MAGNETIC_UEV = addItem(232, "circuit.resonatic.uev").setUnificationData(OrePrefix.circuit, UEV);
        RAW_IMPRINT_SUPPORTED_BOARD = addItem(230, "board.raw.magnetic");
        IMPRINT_SUPPORTED_BOARD = addItem(231, "board.magnetic");

        SMD_TRANSISTOR_REFINED = addItem(240, "component.smd.transistor.refined");
        SMD_RESISTOR_REFINED = addItem(241, "component.smd.resistor.refined");
        SMD_CAPACITOR_REFINED = addItem(242, "component.smd.capacitor.refined");
        SMD_DIODE_REFINED = addItem(243, "component.smd.diode.refined");

        SMD_TRANSISTOR_NANO = addItem(244, "component.smd.transistor.nano");
        SMD_RESISTOR_NANO = addItem(245, "component.smd.resistor.nano");
        SMD_CAPACITOR_NANO = addItem(246, "component.smd.capacitor.nano");
        SMD_DIODE_NANO = addItem(247, "component.smd.diode.nano");

        SMD_TRANSISTOR_CRYSTAL = addItem(248, "component.smd.transistor.crystal");
        SMD_CAPACITOR_CRYSTAL = addItem(249, "component.smd.capacitor.crystal");
        SMD_RESISTOR_CRYSTAL = addItem(250, "component.smd.resistor.crystal");
        SMD_DIODE_CRYSTAL = addItem(251, "component.smd.diode.crystal");

        SMD_TRANSISTOR_QUANTUM = addItem(252, "component.smd.transistor.quantum");
        SMD_CAPACITOR_QUANTUM = addItem(253, "component.smd.capacitor.quantum");
        SMD_RESISTOR_QUANTUM = addItem(254, "component.smd.resistor.quantum");
        SMD_DIODE_QUANTUM = addItem(255, "component.smd.diode.quantum");

        SMD_TRANSISTOR_WETWARE = addItem(256, "component.smd.transistor.wetware");
        SMD_CAPACITOR_WETWARE = addItem(257, "component.smd.capacitor.wetware");
        SMD_RESISTOR_WETWARE = addItem(258, "component.smd.resistor.wetware");
        SMD_DIODE_WETWARE = addItem(259, "component.smd.diode.wetware");

        /*
        PROTACTINIUM_WASTE = addItem(311, "waste.nuclear").addComponents(new WasteBehavior(Protactinium.getMaterial()));
        NUCLEAR_WASTE = addItem(312, "waste.nuclear").addComponents(new WasteBehavior("waste.nuclear.global", 0xDEDEDE));
        THORIUM_WASTE = addItem(313, "waste.nuclear").addComponents(new WasteBehavior(Thorium));
        URANIUM_WASTE = addItem(314, "waste.nuclear").addComponents(new WasteBehavior(UraniumRadioactive.getMaterial()));
        NEPTUNIUM_WASTE = addItem(315, "waste.nuclear").addComponents(new WasteBehavior(Neptunium.getMaterial()));
        PLUTONIUM_WASTE = addItem(316, "waste.nuclear").addComponents(new WasteBehavior(PlutoniumRadioactive.getMaterial()));
        AMERICIUM_WASTE = addItem(317, "waste.nuclear").addComponents(new WasteBehavior(Americium));
        CURIUM_WASTE = addItem(318, "waste.nuclear").addComponents(new WasteBehavior(Curium.getMaterial()));
        BERKELIUM_WASTE = addItem(319, "waste.nuclear").addComponents(new WasteBehavior(Berkelium.getMaterial()));
        CALIFORNIUM_WASTE = addItem(320, "waste.nuclear").addComponents(new WasteBehavior(Californium.getMaterial()));
        EINSTEINIUM_WASTE = addItem(321, "waste.nuclear").addComponents(new WasteBehavior(Einsteinium.getMaterial()));
        FERMIUM_WASTE = addItem(322, "waste.nuclear").addComponents(new WasteBehavior(Fermium.getMaterial()));
        MENDELEVIUM_WASTE = addItem(323, "waste.nuclear").addComponents(new WasteBehavior(Mendelevium.getMaterial()));
        NUCLEAR_WASTE_LANTHANIDE_A = addItem(324, "waste.nuclear").addComponents(new WasteBehavior("waste.nuclear.lanthanide.a", 0xC9CBCF));
        NUCLEAR_WASTE_LANTHANIDE_B = addItem(325, "waste.nuclear").addComponents(new WasteBehavior("waste.nuclear.lanthanide.b", 0xA9A8AA));
        NUCLEAR_WASTE_ALKALINE = addItem(326, "waste.nuclear").addComponents(new WasteBehavior("waste.nuclear.alkaline", 0xDEDEDE));
        NUCLEAR_WASTE_METAL_A = addItem(327, "waste.nuclear").addComponents(new WasteBehavior("waste.nuclear.metal.a", 0x48484D));
        NUCLEAR_WASTE_METAL_B = addItem(328, "waste.nuclear").addComponents(new WasteBehavior("waste.nuclear.metal.b", 0x626065));
        NUCLEAR_WASTE_METAL_C = addItem(329, "waste.nuclear").addComponents(new WasteBehavior("waste.nuclear.metal.c", 0x828485));
        NUCLEAR_WASTE_HEAVY_METAL = addItem(330, "waste.nuclear").addComponents(new WasteBehavior("waste.nuclear.heavy_metal", 0x738198));
        NUCLEAR_WASTE_METALOID = addItem(331, "waste.nuclear").addComponents(new WasteBehavior("waste.nuclear.metaloid", 0xD16D4F));
        NUCLEAR_WASTE_REACTIVE_NONMETAL = addItem(332, "waste.nuclear").addComponents(new WasteBehavior("waste.nuclear.nonmetal", 0xD1CB4F));
        */

        WAFER_DUBNIUM = addItem(333, "wafer.dubnium");
        WAFER_RUTHERFORDIUM = addItem(334, "wafer.rutherfordium");

        COSMIC_PROCESSOR = addItem(336, "circuit.processor.cosmic").setUnificationData(OrePrefix.circuit, UEV);
        COSMIC_ASSEMBLY = addItem(337, "circuit.assembly.cosmic").setUnificationData(OrePrefix.circuit, Tier.UIV);
        COSMIC_COMPUTER = addItem(338, "circuit.computer.cosmic").setUnificationData(OrePrefix.circuit, Tier.UXV);
        COSMIC_MAINFRAME = addItem(339, "circuit.mainframe.cosmic").setUnificationData(OrePrefix.circuit, Tier.OpV);

        BOULE_DUBNIUM = addItem(340, "boule.dubnium");
        BOULE_RUTHERFORDIUM = addItem(341, "boule.rutherfordium");

        UNSTABLE_STAR = addItem(343, "unstable.star");
        NUCLEAR_STAR = addItem(344, "nuclear.star");

        CONVEYOR_MODULE_MAX = addItem(353, "conveyor.module.max");

        ELECTRIC_MOTOR_MAX = addItem(359, "electric.motor.max");

        ELECTRIC_PISTON_MAX = addItem(365, "electric.piston.max");

        ELECTRIC_PUMP_MAX = addItem(371, "electric.pump.max");

        EMITTER_MAX = addItem(377, "emitter.max");

        FIELD_GENERATOR_MAX = addItem(383, "field.generator.max");

        ROBOT_ARM_MAX = addItem(389, "robot.arm.max");

        SENSOR_MAX = addItem(395, "sensor.max");

        ULTRASONIC_HOMOGENIZER = addItem(396, "ultrasonic.homogenizer");
        STERILIZED_PETRI_DISH = addItem(397, "sterilized.petri.dish");
        CONTAMINATED_PETRI_DISH = addItem(398, "contaminated.petri.dish");
        CLEAN_CULTURE = addItem(399, "clean.culture");
        PIEZOELECTRIC_CRYSTAL = addItem(400, "piezoelectric.crystal");

        BIOWARE_PROCESSOR = addItem(401, "circuit.processor.bioware").setUnificationData(OrePrefix.circuit, Tier.ZPM);
        BIOWARE_ASSEMBLY = addItem(402, "circuit.assembly.bioware").setUnificationData(OrePrefix.circuit, Tier.UV);
        BIOWARE_COMPUTER = addItem(403, "circuit.computer.bioware").setUnificationData(OrePrefix.circuit, Tier.UHV);
        BIOWARE_MAINFRAME = addItem(404, "circuit.mainframe.bioware").setUnificationData(OrePrefix.circuit, UEV);

        SMD_TRANSISTOR_BIOWARE = addItem(405, "component.smd.transistor.bioware");
        SMD_CAPACITOR_BIOWARE = addItem(406, "component.smd.capacitor.bioware");
        SMD_RESISTOR_BIOWARE = addItem(407, "component.smd.resistor.bioware");
        SMD_DIODE_BIOWARE = addItem(408, "component.smd.diode.bioware");

        ELECTRICALLY_WIRED_PETRI_DISH = addItem(409, "electrically.wired.petri.dish");
        NEURO_SUPPORT_UNIT = addItem(410, "neuro.support.unit");
        CYBER_PROCESSING_UNIT = addItem(411, "cyber.processing.unit");

        BATTERY_NIMH_EMPTY = addItem(412, "nickel.metal.hydride.battery.empty");
        BATTERY_SMALL_LITHIUM_ION_EMPTY = addItem(414, "small.lithium.ion.battery.empty");
        BATTERY_MEDIUM_LITHIUM_ION_EMPTY = addItem(417, "medium.lithium.ion.battery.empty");
        BATTERY_LARGE_LITHIUM_ION_EMPTY = addItem(415, "large.lithium.ion.battery.empty");
        BATTERY_SMALL_LIS_EMPTY = addItem(418, "small.lithium.sulfide.battery.empty");
        BATTERY_MEDIUM_LIS_EMPTY = addItem(413, "medium.lithium.sulfide.battery.empty");
        BATTERY_LARGE_LIS_EMPTY = addItem(416, "large.lithium.sulfide.battery.empty");
        BATTERY_SMALL_FLUORIDE_EMPTY = addItem(419, "small.fluoride.battery.empty");

        UHASOC = addItem(422, "uhasoc");
        UHASOC_WAFER = addItem(423, "uhasoc.wafer");
        ARAM = addItem(426, "plate.aram");
        ARAM_WAFER = addItem(427, "wafer.aram");

        PLASMA_CONTAINMENT_CELL = addItem(428, "plasma.containment.cell");
        RHENIUM_PLASMA_CONTAINMENT_CELL = addItem(429, "rhenium.plasma.containment.cell");
        DEGENERATE_RHENIUM_PLATE = addItem(430, "degenerate.rhenium.plate");
        DEGENERATE_RHENIUM_DUST = addItem(432, "degenerate.rhenium.dust");
        PLATE_FIELD_SHAPE = addItem(431, "plate.field.shape");

        ZBLAN = addItem(433, "zblan");
        ZBLAN_INGOT = addItem(434, "zblan_ingot");
        HOT_ANNEALED_ZBLAN_INGOT = addItem(435, "hot_annealed_zblan_ingot");
        ZBLAN_FIBER = addItem(436, "zblan_fiber");
        ERBIUM_DOPED_ZBLAN = addItem(437, "erbium_doped_zblan");
        CLADDED_OPTICAL_FIBER_CORE = addItem(438, "cladded_optical_fiber_core");

        RAPIDLY_ROTATING_CRUCIBLE = addItem(440, "rapidly_rotating_crucible");
        HEAVY_METAL_ABSORBING_YARN = addItem(441, "heavy_metal_absorbing_yarn");
        URANIUM_SATURATED_YARN = addItem(442, "uranium_saturated_yarn");
        BORON_RETAINING_YARN = addItem(443, "boron_retaining_yarn");
        BORON_SATURATED_YARN = addItem(444, "boron_saturated_yarn");
        LITHIUM_SIEVE = addItem(445, "lithium_sieve");
        LITHIUM_SATURATED_LITHIUM_SIEVE = addItem(446, "lithium_saturated_lithium_sieve");
        NANOTOME = addItem(447, "nanotome");
        ACRYLIC_YARN = addItem(448, "acrylic_yarn");
        NEUTRON_PLASMA_CONTAINMENT_CELL = addItem(449, "neutron.plasma.containment.cell");
        INGOT_FIELD_SHAPE = addItem(450, "ingot.field.shape");
        MEMORY_FOAM_BLOCK = addItem(454,"memory_foam_block");
        LASER_DIODE = addItem(455,"laser_diode");
        LASER_COOLING_UNIT = addItem(456,"laser_cooling_unit");
        MAGNETIC_TRAP = addItem(457,"magnetic_trap");
        EMPTY_LASER_COOLING_CONTAINER = addItem(458,"empty_laser_cooling_container");
        BOSE_EINSTEIN_COOLING_CONTAINER = addItem(459,"bose_einstein_cooling_container");

        ALUMINO_SILICATE_GLASS_TUBE = addItem(460,"alumino_silicate_glass_tube");
        INDUCTOR = addItem(461,"inductor");
        BALLAST = addItem(462,"ballast");
        UVA_HALIDE_LAMP = addItem(463,"uva_halide_lamp");
        WHITE_HALIDE_LAMP = addItem(464,"white_halide_lamp");
        RED_HALIDE_LAMP = addItem(465,"red_halide_lamp");
        BLUE_HALIDE_LAMP = addItem(466,"blue_halide_lamp");
        GREEN_HALIDE_LAMP = addItem(467,"green_halide_lamp");
        UVA_LAMP_CORE = addItem(468,"uva_lamp_core");
        WHITE_LAMP_CORE = addItem(469,"white_lamp_core");
        RED_LAMP_CORE = addItem(470,"red_lamp_core");
        BLUE_LAMP_CORE = addItem(471,"blue_lamp_core");
        GREEN_LAMP_CORE = addItem(472,"green_lamp_core");
        ACTINIUM_PLASMA_CONTAINMENT_CELL = addItem(473, "actinium.plasma.containment.cell");

        SHEWANELLA_CULTURE = addItem(475, "shewanella.culture");
        STREPTOCOCCUS_CULTURE = addItem(476, "streptococcus.culture");
        ESCHERICHIA_CULTURE = addItem(477, "eschericia.culture");
        BIFIDOBACTERIUM_CULTURE = addItem(478, "bifidobacterium.culture");
        BREVIBACTERIUM_CULTURE = addItem(479, "brevibacterium.culture");

        NDYAG_BOULE = addItem(480,"ndyag_boule");
        PRHOYLF_BOULE = addItem(481,"prhoylf_boule");
        LUTMYVO_BOULE = addItem(482,"lutmyvo_boule");
        NDYAG_ROD = addItem(483,"ndyag_rod");
        PRHOYLF_ROD = addItem(484,"prhoylf_rod");
        LUTMYVO_ROD = addItem(485,"lutmyvo_rod");
        LOW_FREQUENCY_LASER = addItem(486,"low_frequency_laser");
        MEDIUM_FREQUENCY_LASER = addItem(487,"medium_frequency_laser");
        HIGH_FREQUENCY_LASER = addItem(488,"high_frequency_laser");
        PERIODICALLY_POLED_LITHIUM_NIOBATE_BOULE = addItem(489,"periodically_poled_lithium_niobate_boule");
        HIGHLY_REFLECTIVE_MIRROR = addItem(490,"highly_reflective_mirror");
        NON_LINEAR_OPTICAL_LENS = addItem(491,"non_linear_optical_lens");
        ROTATING_TRANSPARENT_SURFACE = addItem(492,"rotating_transparent_surface");
        ELECTRON_SOURCE = addItem(493,"electron_source");

        SMD_CAPACITOR_OPTICAL = addItem(494,"smd_capacitor_optical");
        SMD_DIODE_OPTICAL = addItem(495,"smd_diode_optical");
        SMD_RESISTOR_OPTICAL = addItem(496,"smd_resistor_optical");
        SMD_TRANSISTOR_OPTICAL = addItem(497,"smd_transistor_optical");
        OPTICAL_SOC_WAFER = addItem(498,"optical_soc_wafer");
        OPTICAL_SOC = addItem(499,"optical_soc");
        OPTICAL_PROCESSING_CORE = addItem(500,"optical_processing_core");
        OPTICAL_PROCESSOR = addItem(501,"circuit.processor.optical").setUnificationData(OrePrefix.circuit, Tier.UV);
        OPTICAL_ASSEMBLY = addItem(502,"circuit.assembly.optical").setUnificationData(OrePrefix.circuit, Tier.UHV);
        OPTICAL_COMPUTER = addItem(503,"circuit.computer.optical").setUnificationData(OrePrefix.circuit, UEV);
        OPTICAL_MAINFRAME = addItem(504,"circuit.mainframe.optical").setUnificationData(OrePrefix.circuit, Tier.UIV);

        PEEK_POLYAMIDE_FOIL = addItem(505,"peek_polyamide_foil");
        HIGHLY_INSULATING_FOIL = addItem(506,"highly_insulating_foil");
        INSULATION_WIRE_ASSEMBLY = addItem(507,"insulation_wire_assembly");

        NEUTRONIUM_SPHERE = addItem(508, "neutronium.sphere");
        TRIPLET_NEUTRONIUM_SPHERE = addItem(509, "triplet.neutronium.sphere");
        CHARGED_TRIPLET_NEUTRONIUM_SPHERE = addItem(510, "charged.triplet.neutronium.sphere");
        CONTAINED_REISSNER_NORDSTROM_SINGULARITY = addItem(511, "contained.reissner.nordstrom.singularity");
        CONTAINED_KERR_NEWMANN_SINGULARITY = addItem(512, "contained.kerr.newmann.singularity");
        CONTAINED_KERR_SINGULARITY = addItem(513, "contained.kerr.singularity");
        CONTAINED_HIGH_DENSITY_PROTONIC_MATTER = addItem(514, "contained.high.density.protonic.matter");
        TIME_DILATION_CONTAINMENT_UNIT = addItem(515, "time.dilation.containment.unit");
        MICROWORMHOLE_GENERATOR = addItem(516, "microwormhole.generator");
        MACROWORMHOLE_GENERATOR = addItem(517, "macrowormhole.generator");
        STABILIZED_WORMHOLE_GENERATOR = addItem(518, "stabilized.wormhole.generator");
        CONTAINED_EXOTIC_MATTER = addItem(519, "contained.exotic.matter");
        SEPARATION_ELECTROMAGNET = addItem(521, "separation.electromagnet");
        SPHERE_FIELD_SHAPE = addItem(522, "sphere.field.shape");

        AEROGRAPHENE = addItem(523,"aerographene");
        SCINTILLATOR_CRYSTAL = addItem(524,"scintillator_crystal");
        SCINTILLATOR = addItem(525,"scintillator");
        LEPTON_TRAP_CRYSTAL =  addItem(526,"lepton_trap_crystal");
        HASSIUM_SEED_CRYSTAL = addItem(527, "hassium_seed_crystal");
        HASSIUM_BOULE = addItem(520,"hassium_boule");
        HASSIUM_WAFER = addItem(528,"hassium_wafer");
        COATED_HASSIUM_WAFER = addItem(529, "coated_hassium_wafer");
        PHOTOCOATED_HASSIUM_WAFER = addItem(530,"photocoated_hassium_wafer");
        GRATING_LITHOGRAPHY_MASK = addItem(531, "grating_lithography_mask");
        DIFFRACTOR_GRATING_MIRROR = addItem(532,"diffractor_grating_mirror");
        ULTRASHORT_PULSE_LASER = addItem(533,"ultrashort_pulse_laser");
        SMD_CAPACITOR_COSMIC = addItem(534,"smd_capacitor_cosmic");
        SMD_DIODE_COSMIC = addItem(535,"smd_diode_cosmic");
        SMD_RESISTOR_COSMIC = addItem(536,"smd_resistor_cosmic");
        SMD_TRANSISTOR_COSMIC = addItem(537,"smd_transistor_cosmic");
        COSMIC_COMPUTE_UNIT = addItem(538,"cosmic_compute_unit");
        COSMIC_PROCESSING_CORE = addItem(539,"cosmic_processing_core");
        GRAPHENE_IRON_PLATE = addItem(540,"graphene_iron_plate");
        CUPRIVADUS_CULTURE = addItem(541, "cupriavidus.culture");

        SMD_CAPACITOR_SUPRACAUSAL = addItem(542, "smd.capacitor.supracausal");
        SMD_RESISTOR_SUPRACAUSAL = addItem(543, "smd.resistor.supracausal");
        SMD_DIODE_SUPRACAUSAL = addItem(544, "smd.diode.supracausal");
        SMD_TRANSISTOR_SUPRACAUSAL = addItem(545, "smd.transistor.supracausal");

        SUPRACAUSAL_PROCESSOR = addItem(546, "circuit.processor.supracausal").setUnificationData(OrePrefix.circuit, Tier.UIV);
        SUPRACAUSAL_ASSEMBLY = addItem(547, "circuit.assembly.supracausal").setUnificationData(OrePrefix.circuit, Tier.UXV);
        SUPRACAUSAL_COMPUTER = addItem(548, "circuit.computer.supracausal").setUnificationData(OrePrefix.circuit, Tier.OpV);
        SUPRACAUSAL_MAINFRAME = addItem(549, "circuit.mainframe.supracausal").setUnificationData(OrePrefix.circuit, Tier.MAX);

        TOPOLOGICAL_MANIPULATOR_UNIT = addItem(550, "topological.manipulator.unit");
        RELATIVISTIC_SPINORIAL_MEMORY_SYSTEM = addItem(551, "relativistic.spinorial.memory.system");
        GRAVITON_TRANSDUCER = addItem(552, "graviton.transducer");
        NUCLEAR_CLOCK = addItem(553, "nuclear.clock");
        SUPRACAUSAL_PROCESSING_CORE = addItem(554, "supracausal.processing.core");
        MANIFOLD_OSCILLATORY_POWER_CELL = addItem(555, "manifold.oscillatory.power.cell");
        QCD_PROTECTIVE_PLATING = addItem(556, "qcd.protective.plating");
        CTC_GUIDANCE_UNIT = addItem(557, "ctc.guidance.unit");
        CTC_COMPUTATIONAL_UNIT_CONTAINER = addItem(558, "ctc.computational.unit.container");
        EIGENFOLDED_KERR_MANIFOLD = addItem(559, "eigenfolded.kerr.manifold");
        CTC_COMPUTATIONAL_UNIT = addItem(560, "ctc.computational.unit");
        RECURSIVELY_FOLDED_NEGATIVE_SPACE = addItem(561, "recursively.folded.negative.space");

        WIRE_FIELD_SHAPE = addItem(562,"wire.field.shape");
        EXTREMELY_DURABLE_PLASMA_CELL = addItem(563,"extremely.durable.plasma.cell");
        DENSE_NEUTRON_PLASMA_CELL = addItem(564,"dense.neutron.plasma.cell");
        COSMIC_NEUTRON_PLASMA_CELL = addItem(565,"cosmic.neutron.plasma.cell");
        HIGHLY_DENSE_POLYMER_PLATE = addItem(566,"highly.dense.polymer.plate");
        COSMIC_MESH_CONTAINMENT_UNIT = addItem(567,"cosmic.mesh.containment.unit");
        COSMIC_MESH = addItem(568,"cosmic.mesh");
        COSMIC_FABRIC = addItem(569,"cosmic.fabric");
        ROD_FIELD_SHAPE = addItem(570, "rod.field.shape");

        /*
        ThoriumRadioactive.waste = THORIUM_WASTE;
        Protactinium.waste = PROTACTINIUM_WASTE;
        UraniumRadioactive.waste = URANIUM_WASTE;
        Neptunium.waste = NEPTUNIUM_WASTE;
        PlutoniumRadioactive.waste = PLUTONIUM_WASTE;
        AmericiumRadioactive.waste = AMERICIUM_WASTE;
        Curium.waste = CURIUM_WASTE;
        Berkelium.waste = BERKELIUM_WASTE;
        Californium.waste = CALIFORNIUM_WASTE;
        Einsteinium.waste = EINSTEINIUM_WASTE;
        Fermium.waste = FERMIUM_WASTE;
        Mendelevium.waste = MENDELEVIUM_WASTE
         */

        MV_INFINITE_WATER_SOURCE = addItem(571, "infinite.water.mv");
        HV_INFINITE_WATER_SOURCE = addItem(572, "infinite.water.hv");
        EV_INFINITE_WATER_SOURCE = addItem(573, "infinite.water.ev");
        IV_INFINITE_WATER_SOURCE = addItem(574, "infinite.water.iv");
        LuV_INFINITE_WATER_SOURCE = addItem(575, "infinite.water.luv");
        ZPM_INFINITE_WATER_SOURCE = addItem(576, "infinite.water.zpm");
        UV_INFINITE_WATER_SOURCE = addItem(577, "infinite.water.uv");

        COLOURED_LEDS = addItem(578, "coloured.leds");
        DISPLAY = addItem(579, "display");

        CHARGED_LEPTON_TRAP_CRYSTAL = addItem(580,"charged.lepton.trap.crystal");
        NANOSILICON_CATHODE = addItem(581,"nanosilicon.cathode");
        BATTERY_MEDIUM_FLUORIDE_EMPTY = addItem(582,"medium.fluoride.battery.empty");
        BATTERY_LARGE_FLUORIDE_EMPTY = addItem(583,"large.fluoride.battery.empty");

        KAPTON_BOARD = addItem(584, "board.kapton");
        KAPTON_CIRCUIT_BOARD = addItem(585, "board_circuit.kapton");

        FULLERENE_POLYMER_MATRIX_SOFT_TUBING = addItem(588, "fullerene.polymer.matrix.soft.tubing");
        FULLERENE_POLYMER_MATRIX_FINE_TUBING = addItem(589, "fullerene.polymer.matrix.fine.tubing");
        X_RAY_WAVEGUIDE = addItem(590, "xray.waveguide");
        MICROFOCUS_X_RAY_TUBE = addItem(591, "microfocus.xray.tube");
        X_RAY_MIRROR_PLATE = addItem(592, "xray.mirror.plate");
        UNTREATED_EXOTIC_WAFER = addItem(593, "untreated.exotic.wafer");
        EXOTIC_WAFER = addItem(594, "exotic.wafer");
        EXOTIC_CHIP = addItem(595, "exotic.chip");
        EXCITATION_MAINTAINER = addItem(596, "excitation.maintainer");
        CRYOGENIC_INTERFACE = addItem(597, "cryogenic.interface");
        RYDBERG_SPINORIAL_ASSEMBLY = addItem(598, "rydberg.spinorial.assembly");
        X_RAY_LASER = addItem(599, "xray.laser");
        EXOTIC_PROCESSING_CORE = addItem(600, "exotic.processing.core");

        SMD_CAPACITOR_EXOTIC = addItem(601, "component.smd.capacitor.exotic");
        SMD_RESISTOR_EXOTIC = addItem(602, "component.smd.resistor.exotic");
        SMD_DIODE_EXOTIC = addItem(603, "component.smd.diode.exotic");
        SMD_TRANSISTOR_EXOTIC = addItem(604, "component.smd.transistor.exotic");

        EXOTIC_PROCESSOR = addItem(605, "circuit.processor.exotic").setUnificationData(OrePrefix.circuit, Tier.UHV);
        EXOTIC_ASSEMBLY = addItem(606, "circuit.assembly.exotic").setUnificationData(OrePrefix.circuit, UEV);
        EXOTIC_COMPUTER = addItem(607, "circuit.computer.exotic").setUnificationData(OrePrefix.circuit, Tier.UIV);
        EXOTIC_MAINFRAME = addItem(608, "circuit.mainframe.exotic").setUnificationData(OrePrefix.circuit, Tier.UXV);

        PROTONATED_FULLERENE_SIEVING_MATRIX = addItem(609, "protonated.fullerene.sieving.matrix");
        SATURATED_FULLERENE_SIEVING_MATRIX =addItem(610, "saturated.fullerene.sieving.matrix");

        TOOL_DATA_MODULE_CLUSTER = addItem(613, "tool.data.module_cluster").addComponents(new DataItemBehavior(true));
        TOOL_DATA_ULTIMATE = addItem(614, "tool.data.ultimate").addComponents(new DataItemBehavior(true));
        TOOL_DATA_SUPRACAUSAL = addItem(615, "tool.data.supracausal").addComponents(new DataItemBehavior(true));

        TOOL_DATA_DEEP_MINER = addItem(616, "tool.data.deep_miner").addComponents(new MinerDataItemBehavior(false));

        COVER_ENDER_ITEM_LINK = addItem(620, "cover.ender.item_link");

        VOLTAGE_COIL_UHV = addItem(621, "voltage_coil.uhv");
        VOLTAGE_COIL_UEV = addItem(622, "voltage_coil.uev");
        VOLTAGE_COIL_UIV = addItem(623, "voltage_coil.uiv");
        VOLTAGE_COIL_UXV = addItem(624, "voltage_coil.uxv");
        VOLTAGE_COIL_OpV = addItem(625, "voltage_coil.opv");
        VOLTAGE_COIL_MAX = addItem(626, "voltage_coil.max");

        BIO_CELLS = addItem(627, "bio_cells");
        BIO_BOARD = addItem(628, "board.bio");
        BIO_CIRCUIT_BOARD = addItem(629, "board_circuit.bio");
        ADVANCED_CRYSTAL_SOC = addItem(630, "crystal.asoc");
        LIVING_SOC = addItem(631, "living.soc");
        LIVING_BIO_SOC = addItem(632, "living_bio.soc");
        BIO_WAFER = addItem(633, "wafer.bio");

        NPIC_WAFER = addItem(634,"wafer.npic");
        PPIC_WAFER = addItem(635,"wafer.ppic");
        QPIC_WAFER = addItem(636,"wafer.qpic");
        NPIC = addItem(637,"npic");
        PPIC = addItem(638,"ppic");
        QPIC = addItem(639,"qpic");

        WIRELESS_BATTERY_UV = addItem(640, "wireless_battery_uv").addComponents(WirelessElectricStats.createRechargeableBattery(20_000_000_000L, GTValues.UV)).setModelAmount(8);
        /*
        641
        642
        643
        644
        645
        646
         */

        MINING_DRONE_1 = addItem(647, "mining_drone.1");
        MINING_DRONE_2 = addItem(648, "mining_drone.2");
        MINING_DRONE_3 = addItem(649, "mining_drone.3");
        MINING_DRONE_4 = addItem(650, "mining_drone.4");
        MINING_DRONE_5 = addItem(651, "mining_drone.5");
        MINING_DRONE_6 = addItem(652, "mining_drone.6");
        MINING_DRONE_7 = addItem(653, "mining_drone.7");
        MINING_DRONE_8 = addItem(654, "mining_drone.8");
        MINING_DRONE_9 = addItem(655, "mining_drone.9");
        MINING_DRONE_10 = addItem(656, "mining_drone.10");
        MINING_DRONE_11 = addItem(657, "mining_drone.11");
        MINING_DRONE_12 = addItem(658, "mining_drone.12");
        MINING_DRONE_13 = addItem(659, "mining_drone.13");
        MINING_DRONE_14 = addItem(660, "mining_drone.14");


        GENERIC_CIRCUIT_ULV = addItem(661, "generic_circuit.ulv").setUnificationData(OrePrefix.circuit, Tier.ULV);
        GENERIC_CIRCUIT_LV = addItem(662, "generic_circuit.lv").setUnificationData(OrePrefix.circuit, Tier.LV);
        GENERIC_CIRCUIT_MV = addItem(663, "generic_circuit.mv").setUnificationData(OrePrefix.circuit, Tier.MV);
        GENERIC_CIRCUIT_HV = addItem(664, "generic_circuit.hv").setUnificationData(OrePrefix.circuit, Tier.HV);
        GENERIC_CIRCUIT_EV = addItem(665, "generic_circuit.ev").setUnificationData(OrePrefix.circuit, Tier.EV);
        GENERIC_CIRCUIT_IV = addItem(666, "generic_circuit.iv").setUnificationData(OrePrefix.circuit, Tier.IV);
        GENERIC_CIRCUIT_LuV = addItem(667, "generic_circuit.luv").setUnificationData(OrePrefix.circuit, Tier.LuV);
        GENERIC_CIRCUIT_ZPM = addItem(668, "generic_circuit.zpm").setUnificationData(OrePrefix.circuit, Tier.ZPM);
        GENERIC_CIRCUIT_UV = addItem(669, "generic_circuit.uv").setUnificationData(OrePrefix.circuit, Tier.UV);
        GENERIC_CIRCUIT_UHV = addItem(670, "generic_circuit.uhv").setUnificationData(OrePrefix.circuit, Tier.UHV);
        GENERIC_CIRCUIT_UEV = addItem(671, "generic_circuit.uev").setUnificationData(OrePrefix.circuit, Tier.UEV);
        GENERIC_CIRCUIT_UIV = addItem(672, "generic_circuit.uiv").setUnificationData(OrePrefix.circuit, Tier.UIV);
        GENERIC_CIRCUIT_UXV = addItem(673, "generic_circuit.uxv").setUnificationData(OrePrefix.circuit, Tier.UXV);
        GENERIC_CIRCUIT_OpV = addItem(674, "generic_circuit.opv").setUnificationData(OrePrefix.circuit, Tier.OpV);
        GENERIC_CIRCUIT_MAX = addItem(675, "generic_circuit.max").setUnificationData(OrePrefix.circuit, Tier.MAX);




        PYROLYTIC_CARBON = addItem(16, "pyrolytic_carbon");
        MICA_SHEET = addItem(26, "mica_sheet");
        MICA_INSULATOR_SHEET = addItem(27, "mica_insulator_sheet");
        MICA_INSULATOR_FOIL = addItem(28, "mica_insulator_foil");

    }
    @Override
    public boolean hasEffect(ItemStack itemStack) {
        return super.hasEffect(itemStack)
                || itemStack.getMetadata() == UNSTABLE_STAR.getStackForm().getMetadata()
                || itemStack.getMetadata() == NUCLEAR_STAR.getStackForm().getMetadata()
                || itemStack.getMetadata() == WIRELESS_BATTERY_UV.getStackForm().getMetadata();
    }
}
