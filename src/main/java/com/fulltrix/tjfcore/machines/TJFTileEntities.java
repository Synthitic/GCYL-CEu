package com.fulltrix.tjfcore.machines;

import com.fulltrix.tjfcore.TJFConfig;
import com.fulltrix.tjfcore.TJFUtility;
import com.fulltrix.tjfcore.machines.multi.MetaTileEntityBioReactor;
import com.fulltrix.tjfcore.machines.multi.MetaTileEntityStellarForge;
import com.fulltrix.tjfcore.machines.multi.advance.MetaTileEntityAdvFusionReactor;
import com.fulltrix.tjfcore.machines.multi.miner.MetaTileEntityVoidMiner;
import com.fulltrix.tjfcore.recipes.TJFRecipeMaps;
import gregtech.api.GTValues;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.util.ResourceLocation;

import static com.fulltrix.tjfcore.TJFUtility.tjfId;

public class TJFTileEntities {
    public static MetaTileEntityBioReactor BIO_REACTOR;

    public static MetaTileEntityAdvFusionReactor[] ADVANCED_FUSION_REACTOR = new MetaTileEntityAdvFusionReactor[5];

    public static SimpleMachineMetaTileEntity[] DEHYDRATOR = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];

    public static MetaTileEntityVoidMiner[] VOID_MINER = new MetaTileEntityVoidMiner[3];

    public static MetaTileEntityStellarForge STELLAR_FORGE;

    public static int id = 32000;

    public static void init() {

        BIO_REACTOR = MetaTileEntities.registerMetaTileEntity(id, new MetaTileEntityBioReactor(new ResourceLocation("tjfcore", "bio_reactor")));

        ADVANCED_FUSION_REACTOR[0] = MetaTileEntities.registerMetaTileEntity(++id, new MetaTileEntityAdvFusionReactor(tjfId("adv_fusion_reactor.uhv"), GTValues.UHV));
        ADVANCED_FUSION_REACTOR[1] = MetaTileEntities.registerMetaTileEntity(++id, new MetaTileEntityAdvFusionReactor(tjfId("adv_fusion_reactor.uev"), GTValues.UEV));
        ADVANCED_FUSION_REACTOR[2] = MetaTileEntities.registerMetaTileEntity(++id, new MetaTileEntityAdvFusionReactor(tjfId("adv_fusion_reactor.uiv"), GTValues.UIV));
        ADVANCED_FUSION_REACTOR[3] = MetaTileEntities.registerMetaTileEntity(++id, new MetaTileEntityAdvFusionReactor(tjfId("adv_fusion_reactor.uxv"), GTValues.UXV));
        ADVANCED_FUSION_REACTOR[4] = MetaTileEntities.registerMetaTileEntity(++id, new MetaTileEntityAdvFusionReactor(tjfId("adv_fusion_reactor.opv"), GTValues.OpV));

        VOID_MINER[0] = MetaTileEntities.registerMetaTileEntity(++id, new MetaTileEntityVoidMiner(tjfId("void_miner"), GTValues.UV, TJFConfig.multis.voidMiner.maxTemp));
        VOID_MINER[1] = MetaTileEntities.registerMetaTileEntity(++id, new MetaTileEntityVoidMiner(tjfId("void_miner.uhv"), GTValues.UHV, TJFConfig.multis.voidMiner.maxTempUHV));
        VOID_MINER[2] = MetaTileEntities.registerMetaTileEntity(++id, new MetaTileEntityVoidMiner(tjfId("void_miner.uev"), GTValues.UEV, TJFConfig.multis.voidMiner.maxTempUEV));

        STELLAR_FORGE = MetaTileEntities.registerMetaTileEntity(++id, new MetaTileEntityStellarForge(tjfId("stellar_forge")));

        MetaTileEntities.registerSimpleMetaTileEntity(DEHYDRATOR, ++id, "dehydrator", TJFRecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES, Textures.SIFTER_OVERLAY, true, TJFUtility::tjfId, GTUtility.hvCappedTankSizeFunction);

    }
}