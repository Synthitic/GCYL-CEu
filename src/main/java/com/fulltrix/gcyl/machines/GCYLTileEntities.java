package com.fulltrix.gcyl.machines;

import com.fulltrix.gcyl.GCYLConfig;
import com.fulltrix.gcyl.GCYLUtility;
import com.fulltrix.gcyl.client.ClientHandler;
import com.fulltrix.gcyl.machines.multi.MetaTileEntityBioReactor;
import com.fulltrix.gcyl.machines.multi.MetaTileEntityCosmicRayDetector;
import com.fulltrix.gcyl.machines.multi.MetaTileEntityElectricImplosion;
import com.fulltrix.gcyl.machines.multi.MetaTileEntityStellarForge;
import com.fulltrix.gcyl.machines.multi.advance.*;
import com.fulltrix.gcyl.machines.multi.miner.MetaTileEntityDeepMiner;
import com.fulltrix.gcyl.machines.multi.miner.MetaTileEntityVoidMiner;
import com.fulltrix.gcyl.machines.multi.multiblockpart.MetaTileEntityHPCAComputationPlus;
import com.fulltrix.gcyl.machines.multi.multiblockpart.MetaTileEntityHPCACoolingPlus;
import com.fulltrix.gcyl.machines.multi.multiblockpart.MetaTileEntitySterileCleaningMaintenanceHatch;
import com.fulltrix.gcyl.machines.multi.simple.MetaTileEntityChemicalPlant;
import com.fulltrix.gcyl.machines.multi.simple.MetaTileEntityDecayChamber;
import com.fulltrix.gcyl.machines.multi.simple.MetaTileEntityGreenhouse;
import com.fulltrix.gcyl.machines.multi.simple.MetaTileEntityPlasmaCondenser;
import com.fulltrix.gcyl.recipes.GCYLRecipeMaps;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.SimpleGeneratorMetaTileEntity;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityEnergyHatch;
import gregtech.common.metatileentities.multi.multiblockpart.hpca.MetaTileEntityHPCAComputation;
import net.minecraft.util.ResourceLocation;

import static com.fulltrix.gcyl.GCYLUtility.gcylId;
import static com.fulltrix.gcyl.recipes.GCYLRecipeMaps.DEEP_MINER_RECIPES;
import static com.google.common.base.Ascii.toLowerCase;
import static com.ibm.icu.impl.locale.AsciiUtil.toLower;
import static gregtech.api.util.GTUtility.gregtechId;
import static gregtech.api.util.GTUtility.toLowerCaseUnderscore;
import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;

public class GCYLTileEntities {
    public static MetaTileEntityBioReactor BIO_REACTOR;
    public static MetaTileEntityAdvFusionReactor[] ADVANCED_FUSION_REACTOR = new MetaTileEntityAdvFusionReactor[5];
    public static SimpleMachineMetaTileEntity[] DEHYDRATOR = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static MetaTileEntityVoidMiner[] VOID_MINER = new MetaTileEntityVoidMiner[3];
    public static MetaTileEntityStellarForge STELLAR_FORGE;
    public static MetaTileEntityCosmicRayDetector COSMIC_RAY_DETECTOR;
    public static MetaTileEntityVolcanus VOLCANUS;
    public static MetaTileEntityHyperReactor[] HYPER_REACTOR = new MetaTileEntityHyperReactor[3];
    public static MetaTileEntityPlasmaCondenser PLASMA_CONDENSER;
    public static MetaTileEntityAdvMixer ADVANCED_MIXER;
    public static MetaTileEntityAdvCentrifuge ADVANCED_CENTRIFUGE;
    public static MetaTileEntityElectricImplosion ELECTRIC_IMPLOSION;
    public static MetaTileEntityLargeNaquadahReactor LARGE_NAQUADAH_REACTOR;
    public static MetaTileEntityChemicalPlant CHEMICAL_PLANT;
    public static MetaTileEntityLargeRocketEngine LARGE_ROCKET_ENGINE;
    public static MetaTileEntityCryogenicFreezer CRYOGENIC_FREEZER;
    public static MetaTileEntityOreFactory ORE_FACTORY;
    public static MetaTileEntityDeepMiner DEEP_MINER;
    public static MetaTileEntityDecayChamber DECAY_CHAMBER;
    public static MetaTileEntityGreenhouse[] GREEN_HOUSE = new MetaTileEntityGreenhouse[2];
    public static MetaTileEntityMegaCleanroom MEGA_CLEANROOM;

    public static MetaTileEntitySterileCleaningMaintenanceHatch STERILE_CLEANING_MAINTENANCE_HATCH;

    public static MetaTileEntityHPCACoolingPlus[] HPCA_COOLING_PLUS = new MetaTileEntityHPCACoolingPlus[7];
    public static MetaTileEntityHPCAComputationPlus[] HPCA_COMPUTATION_PLUS = new MetaTileEntityHPCAComputationPlus[7];
    public static SimpleGeneratorMetaTileEntity[] NAQUADAH_REACTOR = new SimpleGeneratorMetaTileEntity[8];
    public static SimpleGeneratorMetaTileEntity[] ROCKET_GENERATOR = new SimpleGeneratorMetaTileEntity[8];

    ///////////////////////////////////////////
    public static final MetaTileEntityEnergyHatch[] ENERGY_INPUT_HATCH_4A = new MetaTileEntityEnergyHatch[4]; // UEV, UIV, UXV, OPV
    public static final MetaTileEntityEnergyHatch[] ENERGY_INPUT_HATCH_16A = new MetaTileEntityEnergyHatch[4];
    public static final MetaTileEntityEnergyHatch[] ENERGY_OUTPUT_HATCH_4A = new MetaTileEntityEnergyHatch[4];
    public static final MetaTileEntityEnergyHatch[] ENERGY_OUTPUT_HATCH_16A = new MetaTileEntityEnergyHatch[4];

    public static int id = 32000;

    public static void init() {

        BIO_REACTOR = registerMetaTileEntity(id, new MetaTileEntityBioReactor(gcylId("bio_reactor")));

        ADVANCED_FUSION_REACTOR[0] = registerMetaTileEntity(++id, new MetaTileEntityAdvFusionReactor(gcylId("adv_fusion_reactor.uhv"), GTValues.UHV));
        ADVANCED_FUSION_REACTOR[1] = registerMetaTileEntity(++id, new MetaTileEntityAdvFusionReactor(gcylId("adv_fusion_reactor.uev"), GTValues.UEV));
        ADVANCED_FUSION_REACTOR[2] = registerMetaTileEntity(++id, new MetaTileEntityAdvFusionReactor(gcylId("adv_fusion_reactor.uiv"), GTValues.UIV));
        ADVANCED_FUSION_REACTOR[3] = registerMetaTileEntity(++id, new MetaTileEntityAdvFusionReactor(gcylId("adv_fusion_reactor.uxv"), GTValues.UXV));
        ADVANCED_FUSION_REACTOR[4] = registerMetaTileEntity(++id, new MetaTileEntityAdvFusionReactor(gcylId("adv_fusion_reactor.opv"), GTValues.OpV));

        VOID_MINER[0] = registerMetaTileEntity(++id, new MetaTileEntityVoidMiner(gcylId("void_miner"), GTValues.UV, GCYLConfig.multis.voidMiner.maxTemp));
        VOID_MINER[1] = registerMetaTileEntity(++id, new MetaTileEntityVoidMiner(gcylId("void_miner.uhv"), GTValues.UHV, GCYLConfig.multis.voidMiner.maxTempUHV));
        VOID_MINER[2] = registerMetaTileEntity(++id, new MetaTileEntityVoidMiner(gcylId("void_miner.uev"), GTValues.UEV, GCYLConfig.multis.voidMiner.maxTempUEV));

        STELLAR_FORGE = registerMetaTileEntity(++id, new MetaTileEntityStellarForge(gcylId("stellar_forge")));

        COSMIC_RAY_DETECTOR = registerMetaTileEntity(++id, new MetaTileEntityCosmicRayDetector(gcylId("cosmic_ray_detector")));

        VOLCANUS = registerMetaTileEntity(++id, new MetaTileEntityVolcanus(gcylId("volcanus")));

        HYPER_REACTOR[0] = registerMetaTileEntity(++id, new MetaTileEntityHyperReactor(gcylId("hyper_reactor.i"), GTUtility.getTierByVoltage(GCYLConfig.multis.hyperReactors.euGeneration[0])));
        HYPER_REACTOR[1] = registerMetaTileEntity(++id, new MetaTileEntityHyperReactor(gcylId("hyper_reactor.ii"), GTUtility.getTierByVoltage(GCYLConfig.multis.hyperReactors.euGeneration[1])));
        HYPER_REACTOR[2] = registerMetaTileEntity(++id, new MetaTileEntityHyperReactor(gcylId("hyper_reactor.iii"), GTUtility.getTierByVoltage(GCYLConfig.multis.hyperReactors.euGeneration[2])));

        PLASMA_CONDENSER = registerMetaTileEntity(++id, new MetaTileEntityPlasmaCondenser(gcylId("plasma_condenser"), false));

        ADVANCED_MIXER = registerMetaTileEntity(++id, new MetaTileEntityAdvMixer(gcylId("large_mixer")));

        ADVANCED_CENTRIFUGE = registerMetaTileEntity(++id, new MetaTileEntityAdvCentrifuge(gcylId("large_centrifuge")));

        ELECTRIC_IMPLOSION = registerMetaTileEntity(++id, new MetaTileEntityElectricImplosion(gcylId("electric_implosion")));

        LARGE_NAQUADAH_REACTOR = registerMetaTileEntity(++id, new MetaTileEntityLargeNaquadahReactor(gcylId("large_naquadah_reactor")));

        CHEMICAL_PLANT = registerMetaTileEntity(++id, new MetaTileEntityChemicalPlant(gcylId("chemical_plant"),false));

        LARGE_ROCKET_ENGINE = registerMetaTileEntity(++id, new MetaTileEntityLargeRocketEngine(gcylId("large_rocket_engine")));

        CRYOGENIC_FREEZER = registerMetaTileEntity(++id, new MetaTileEntityCryogenicFreezer(gcylId("cryogenic_freezer")));

        ORE_FACTORY = registerMetaTileEntity(++id, new MetaTileEntityOreFactory(gcylId("ore_factory")));

        DEEP_MINER = registerMetaTileEntity(++id, new MetaTileEntityDeepMiner(gcylId("deep_miner"), DEEP_MINER_RECIPES, false));

        STERILE_CLEANING_MAINTENANCE_HATCH = registerMetaTileEntity(++id, new MetaTileEntitySterileCleaningMaintenanceHatch(gcylId("maintenance_hatch_sterile_cleanroom_auto")));

        DECAY_CHAMBER = registerMetaTileEntity(++id, new MetaTileEntityDecayChamber(gcylId("decay_chamber"), true));

        GREEN_HOUSE[0] = registerMetaTileEntity(++id, new MetaTileEntityGreenhouse(gcylId("greenhouse_mv"), 2));
        GREEN_HOUSE[1] = registerMetaTileEntity(++id, new MetaTileEntityGreenhouse(gcylId("greenhouse_uv"), 8));

        MEGA_CLEANROOM = registerMetaTileEntity(++id, new MetaTileEntityMegaCleanroom(gcylId("mega_cleanroom")));

        for(int i = 0; i < 7; i++) {
            HPCA_COOLING_PLUS[i] = registerMetaTileEntity(++id, new MetaTileEntityHPCACoolingPlus(gcylId("hpca.cooling_component." + toLowerCase(GTValues.VN[i+8])), i+8));
            HPCA_COMPUTATION_PLUS[i] = registerMetaTileEntity(++id, new MetaTileEntityHPCAComputationPlus(gcylId("hpca.computation_component." + toLowerCase(GTValues.VN[i+8])), i+8));

        }
        //TODO: configurable efficiency for naq reactors, efficiency implementation in general

        /*
        if (TJFConfig.Misc.naqEfficieny) {
            NAQUADAH_REACTOR[4] = registerMetaTileEntity(++id, new SimpleGeneratorWithLossMetaTileEntity(tjfId("naquadah_reactor.mk2"), TJFRecipeMaps.NAQUADAH_REACTOR_FUELS, ClientHandler.NAQADAH_OVERLAY, 6, 100));
            NAQUADAH_REACTOR[5] = registerMetaTileEntity(++id, new SimpleGeneratorWithLossMetaTileEntity(tjfId("naquadah_reactor.mk3"), TJFRecipeMaps.NAQUADAH_REACTOR_FUELS, ClientHandler.NAQADAH_OVERLAY, 7, 80));
            NAQUADAH_REACTOR[6] = registerMetaTileEntity(++id, new SimpleGeneratorWithLossMetaTileEntity(tjfId("naquadah_reactor.mk4"), TJFRecipeMaps.NAQUADAH_REACTOR_FUELS, ClientHandler.NAQADAH_OVERLAY, 8, 50));
        } else {

         */
            NAQUADAH_REACTOR[4] = registerMetaTileEntity(++id, new SimpleGeneratorMetaTileEntity(gcylId("naquadah_reactor.mk2"), GCYLRecipeMaps.NAQUADAH_REACTOR_FUELS, ClientHandler.NAQADAH_OVERLAY, 6,GTUtility.genericGeneratorTankSizeFunction));
            NAQUADAH_REACTOR[5] = registerMetaTileEntity(++id, new SimpleGeneratorMetaTileEntity(gcylId("naquadah_reactor.mk3"), GCYLRecipeMaps.NAQUADAH_REACTOR_FUELS, ClientHandler.NAQADAH_OVERLAY, 7, GTUtility.genericGeneratorTankSizeFunction));
            NAQUADAH_REACTOR[6] = registerMetaTileEntity(++id, new SimpleGeneratorMetaTileEntity(gcylId("naquadah_reactor.mk4"), GCYLRecipeMaps.NAQUADAH_REACTOR_FUELS, ClientHandler.NAQADAH_OVERLAY, 8, GTUtility.genericGeneratorTankSizeFunction));
        /*}

        if (TJFConfig.Misc.rocketEfficiency) {
            ROCKET_GENERATOR[3] = registerMetaTileEntity(++id, new SimpleGeneratorWithLossMetaTileEntity(tjfId("rocket_generator.mk1"), TJFRecipeMaps.ROCKET_FUEL_RECIPES, ClientHandler.ROCKET_OVERLAY, 4, TJFConfig.Misc.EVRocketEfficiency));
            ROCKET_GENERATOR[4] = registerMetaTileEntity(++id, new SimpleGeneratorWithLossMetaTileEntity(tjfId("rocket_generator.mk2"), TJFRecipeMaps.ROCKET_FUEL_RECIPES, ClientHandler.ROCKET_OVERLAY, 5,  TJFConfig.Misc.IVRocketEfficiency));
            ROCKET_GENERATOR[5] = registerMetaTileEntity(++id, new SimpleGeneratorWithLossMetaTileEntity(tjfId("rocket_generator.mk3"), TJFRecipeMaps.ROCKET_FUEL_RECIPES, ClientHandler.ROCKET_OVERLAY, 6,  TJFConfig.Misc.LuVRocketEfficiency));
        } else {

         */
            ROCKET_GENERATOR[3] = registerMetaTileEntity(++id, new SimpleGeneratorMetaTileEntity(gcylId("rocket_generator.mk1"), GCYLRecipeMaps.ROCKET_FUEL_RECIPES, ClientHandler.ROCKET_OVERLAY, 4, GTUtility.genericGeneratorTankSizeFunction));
            ROCKET_GENERATOR[4] = registerMetaTileEntity(++id, new SimpleGeneratorMetaTileEntity(gcylId("rocket_generator.mk2"), GCYLRecipeMaps.ROCKET_FUEL_RECIPES, ClientHandler.ROCKET_OVERLAY, 5, GTUtility.genericGeneratorTankSizeFunction));
            ROCKET_GENERATOR[5] = registerMetaTileEntity(++id, new SimpleGeneratorMetaTileEntity(gcylId("rocket_generator.mk3"), GCYLRecipeMaps.ROCKET_FUEL_RECIPES, ClientHandler.ROCKET_OVERLAY, 6, GTUtility.genericGeneratorTankSizeFunction));
        //}


        MetaTileEntities.registerSimpleMetaTileEntity(DEHYDRATOR, ++id, "dehydrator", GCYLRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES, Textures.SIFTER_OVERLAY, true, GCYLUtility::gcylId, GTUtility.hvCappedTankSizeFunction);
    }
}