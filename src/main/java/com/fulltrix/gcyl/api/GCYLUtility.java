package com.fulltrix.gcyl.api;

import com.fulltrix.gcyl.api.multi.GCYLCleanroomType;
import com.fulltrix.gcyl.blocks.fusion.GCYLFusionCoils;
import gregtech.api.GTValues;
import gregtech.api.fluids.store.FluidStorageKey;
import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.AssemblyLineRecipeBuilder;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import scala.MatchError;

import java.util.ArrayList;
import java.util.List;

import static com.fulltrix.gcyl.item.GCYLCoreItems.*;
import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static gregicality.multiblocks.api.unification.GCYMMaterials.Zeron100;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.common.items.MetaItems.*;

public class GCYLUtility {

    public static @NotNull ResourceLocation gcylId(@NotNull String path) {
        return new ResourceLocation("gcyl", path);
    }

    public static int setBetweenInclusive(int value, int start, int end) {
        if (value < start)
            return start;
        return Math.min(value, end);
    }

    public static FluidStorageKey getFluidStorageKeyByName(String name) {
        return switch (name) {
          case ("liquid") -> FluidStorageKeys.LIQUID;
          case ("gas") -> FluidStorageKeys.GAS;
          case ("plasma") -> FluidStorageKeys.PLASMA;
            default -> null;
        };
    }

    public static ItemStack getPowerICStack(int tier) {
        if (tier < 3)
            return ULTRA_LOW_POWER_INTEGRATED_CIRCUIT.getStackForm(64);
        if (tier < 4)
            return LOW_POWER_INTEGRATED_CIRCUIT.getStackForm(64);
        if (tier < 5)
            return POWER_INTEGRATED_CIRCUIT.getStackForm(64);
        if (tier < 7)
            return HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64);
        if (tier < 9)
            return ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64);
        if (tier < 11)

            return NPIC.getStackForm(64);
        if (tier < 13)
            return PPIC.getStackForm(64);
        else
            return QPIC.getStackForm(64);
    }

    public static MetaItem<?>.MetaValueItem getPowerIC(int tier) {
        if (tier < 3)
            return ULTRA_LOW_POWER_INTEGRATED_CIRCUIT;
        if (tier < 4)
            return LOW_POWER_INTEGRATED_CIRCUIT;
        if (tier < 5)
            return POWER_INTEGRATED_CIRCUIT;
        if (tier < 7)
            return HIGH_POWER_INTEGRATED_CIRCUIT;
        if (tier < 9)
            return ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT;
        if (tier < 11)
            return NPIC;
        if (tier < 13)
            return PPIC;
        else
            return QPIC;
    }

    public static Material getEmitterDustByTier(int tier) {
        return switch (tier) {
            case(6) -> ZincSelenide;
            case(7) -> Fluorescein;
            case(8) -> Stilbene;
            case(9) -> FranciumCaesiumCadmiumBromide;
            case(10) -> RhodamineB;
            case(11) -> null;
            case(12) -> null;
            case(13) -> ChargedCaesiumCeriumCobaltIndium;
            case(14) -> RheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate;
            default -> null;
        };
    }

    public static Material getSensorDustByTier(int tier) {
        return switch (tier) {
            case(6) -> Germanium;
            case(7) -> LeadSenenide;
            case(8) -> BariumStrontiumTitanate;
            case(9) -> BariumStrontiumTitanate;
            case(10) -> MagnetorestrictiveAlloy;
            case(11) -> null;
            case(12) -> null;
            case(13) -> null;
            case(14) -> null;
            default -> null;
        };
    }

    public static Material getSensorEmitterFoil(int tier) {
        return switch (tier) {
            case (6) -> Palladium;
            case (7) -> Platinum;
            case (8) -> Osmiridium;
            case(11) -> TriniumTitanium;
            case(12) -> ProtoAdamantium;
            default -> getMainComponentMaterialByTier(tier);
        };
    }

    public static Material getGemByTier(int tier) {
        return switch (tier) {
            case (6) -> Ruby;
            case (7) -> Emerald;
            case (8) -> Diamond;
            case (9) -> Diamond;
            case (10) -> Diamond;
            case (11) -> Diamond;
            case (12) -> Diamond;
            case (13) -> Jasper;
            default -> Diamond;
        };
    }

    public static int getCWUt(int tier) {
        return (int) Math.max(4, Math.pow(2, tier - 2));
    }

    public static ItemStack getDataStack(int CWUt) {
        if(CWUt < 128)
            return TOOL_DATA_ORB.getStackForm();
        else if(CWUt < 256)
            return TOOL_DATA_MODULE.getStackForm();
        else if(CWUt < 1024)
            return TOOL_DATA_MODULE_CLUSTER.getStackForm();
        else if(CWUt < 4096)
            return TOOL_DATA_ULTIMATE.getStackForm();
        else
            return TOOL_DATA_SUPRACAUSAL.getStackForm();

    }

    public static Material getSolderingAlloyByTier(int tier) {
        if(tier > 8)
            return Indalloy140;
        else
            return SolderingAlloy;
    }

    public static Material getFineWireByTier(int tier) {
        return switch (tier) {
            case(1) -> Copper;
            case(2) -> Cupronickel;
            case(3) -> Electrum;
            case(4) -> Kanthal;
            case(5) -> BrightSteel;
            case(6) -> Ruridit;
            case(7) -> Europium;
            case(8) -> Duranium;
            case(9) -> TungstenTitaniumCarbide;
            case(10) -> Pikyonium;
            case(11) -> CarbonNanotubes;
            case(12) -> LanthanumFullereneNanotubes;
            case(13) -> MetastableOganesson;
            case(14) -> HeavyQuarkDegenerateMatter; //TODO CHANGE THIS
            default -> Lead;
        };
    }

    public static Material getMaterialByTier(int tier) {
        return switch (tier) {
            case(1) -> Steel;
            case(2) -> Aluminium;
            case(3) -> StainlessSteel;
            case(4) -> Titanium;
            case(5) -> TungstenSteel;
            case(6) -> RhodiumPlatedPalladium;
            case(7) -> Duranium;
            case(8) -> Tritanium;
            case(9) -> Seaborgium;
            case(10) -> Bohrium;
            case(11) -> Quantum;
            case(12) -> BlackTitanium;
            case(13) -> HeavyQuarkDegenerateMatter;
            case(14) -> Neutronium;
            default -> Lead;
        };
    }

    public static MetaItem<?>.MetaValueItem getEmitterCrystalByTier(int tier) {
        return switch (tier) {
          case(11) -> LEPTON_TRAP_CRYSTAL;
          case(12) -> CHARGED_LEPTON_TRAP_CRYSTAL;
          case(13) -> SCINTILLATOR_CRYSTAL;
            default -> null;
        };
    }

    public static MetaItem<?>.MetaValueItem getVoltageCoilByTier(int tier) {
        return switch (tier) {
            case(1) -> VOLTAGE_COIL_LV;
            case(2) -> VOLTAGE_COIL_MV;
            case(3) -> VOLTAGE_COIL_HV;
            case(4) -> VOLTAGE_COIL_EV;
            case(5) -> VOLTAGE_COIL_IV;
            case(6) -> VOLTAGE_COIL_LuV;
            case(7) -> VOLTAGE_COIL_ZPM;
            case(8) -> VOLTAGE_COIL_UV;
            case(9) -> VOLTAGE_COIL_UHV;
            case(10) -> VOLTAGE_COIL_UEV;
            case(11) -> VOLTAGE_COIL_UIV;
            case(12) -> VOLTAGE_COIL_UXV;
            case(13) -> VOLTAGE_COIL_OpV;
            case(14) -> VOLTAGE_COIL_MAX;
            default -> VOLTAGE_COIL_ULV;

        };
    }

    public static Material getMagneticMaterialFluidByTier(int tier) {
        if(tier == 1)
            return IronMagnetic;
        if(tier < 4)
            return SteelMagnetic;
        if(tier < 10) {
            return NeodymiumMagnetic;
        }
        if(tier < 13)
            return SamariumMagnetic;
        else
            return NaquadriaticTaranium;
    }

    public static Material getCableByTier(int tier) {
        return switch (tier) {
            case(1) -> Tin;
            case(2) -> Copper;
            case(3) -> Silver;
            case(4) -> Aluminium;
            case(5) -> Tungsten;
            case(6) -> YttriumBariumCuprate;
            case(7) -> Naquadah;
            case(8) -> Duranium;
            case(9) -> TungstenTitaniumCarbide;
            case(10) -> Pikyonium;
            case(11) -> Cinobite;
            case(12) -> NaquadriaticTaranium;
            case(13) -> Neutronium;
            case(14) -> CosmicNeutronium;
            default -> Lead;
        };
    }

    public static Material getSuperconductorByTier(int tier) {
        if(tier < 1)
            return LVSuperconductor;

        return switch (tier) {
            case(1) -> LVSuperconductor;
            case(2) -> MVSuperconductor;
            case(3) -> HVSuperconductor;
            case(4) -> EVSuperconductor;
            case(5) -> IVSuperconductor;
            case(6) -> LuVSuperconductor;
            case(7) -> ZPMSuperconductor;
            case(8) -> UVSuperconductor;
            case(9) -> UHVSuperconductor;
            case(10) -> UEVSuperconductor;
            case(11) -> UIVSuperconductor;
            case(12) -> UXVSuperconductor;
            case(13) -> OpVSuperconductor;
            default -> MAXSuperconductor;
        };
    }

    public static MetaItem<?>.MetaValueItem getFieldGeneratorByTier(int tier) {
        if(tier < 1)
            return FIELD_GENERATOR_LV;

        return switch (tier) {
            case(1) -> FIELD_GENERATOR_LV;
            case(2) -> FIELD_GENERATOR_MV;
            case(3) -> FIELD_GENERATOR_HV;
            case(4) -> FIELD_GENERATOR_EV;
            case(5) -> FIELD_GENERATOR_IV;
            case(6) -> FIELD_GENERATOR_LuV;
            case(7) -> FIELD_GENERATOR_ZPM;
            case(8) -> FIELD_GENERATOR_UV;
            case(9) -> FIELD_GENERATOR_UHV;
            case(10) -> FIELD_GENERATOR_UEV;
            case(11) -> FIELD_GENERATOR_UIV;
            case(12) -> FIELD_GENERATOR_UXV;
            case(13) -> FIELD_GENERATOR_OpV;
            default -> FIELD_GENERATOR_MAX;
        };
    }

    public static MetaItem<?>.MetaValueItem getSensorByTier(int tier) {
        if(tier < 1)
            return SENSOR_LV;

        return switch (tier) {
            case(1) -> SENSOR_LV;
            case(2) -> SENSOR_MV;
            case(3) -> SENSOR_HV;
            case(4) -> SENSOR_EV;
            case(5) -> SENSOR_IV;
            case(6) -> SENSOR_LuV;
            case(7) -> SENSOR_ZPM;
            case(8) -> SENSOR_UV;
            case(9) -> SENSOR_UHV;
            case(10) -> SENSOR_UEV;
            case(11) -> SENSOR_UIV;
            case(12) -> SENSOR_UXV;
            case(13) -> SENSOR_OpV;
            default -> SENSOR_MAX;
        };
    }

    public static Material getMarkerMaterialByTier(int tier) {
        if(tier < 0)
            return MarkerMaterials.Tier.ULV;

        return switch (tier) {
            case(0) -> MarkerMaterials.Tier.ULV;
            case(1) -> MarkerMaterials.Tier.LV;
            case(2) -> MarkerMaterials.Tier.MV;
            case(3) -> MarkerMaterials.Tier.HV;
            case(4) -> MarkerMaterials.Tier.EV;
            case(5) -> MarkerMaterials.Tier.IV;
            case(6) -> MarkerMaterials.Tier.LuV;
            case(7) -> MarkerMaterials.Tier.ZPM;
            case(8) -> MarkerMaterials.Tier.UV;
            case(9) -> MarkerMaterials.Tier.UHV;
            case(10) -> MarkerMaterials.Tier.UEV;
            case(11) -> MarkerMaterials.Tier.UIV;
            case(12) -> MarkerMaterials.Tier.UXV;
            case(13) -> MarkerMaterials.Tier.OpV;
            default -> MarkerMaterials.Tier.MAX;
        };
    }

    public static CleanroomType getCleanroomTypeByTierNotV(int tier) {
        return switch (tier) {
            case(2)-> CleanroomType.STERILE_CLEANROOM;
            case(3)-> GCYLCleanroomType.ISO3;
            case(4)-> GCYLCleanroomType.ISO2;
            case(5)-> GCYLCleanroomType.ISO1;
            case(6)-> GCYLCleanroomType.ISO0;
            default -> CleanroomType.CLEANROOM;
        };
    }

    public static Material getMainComponentMaterialByTier(int tier) {
        return switch (tier) {
              case(6)->HSSS;
              case(7)->HSSE;
              case(8)->Tritanium;
              case(9)->HDCS;
              case(10)->EnrichedNaquadahAlloy;
              case(11)->HastelloyX78;
              case(12)->HastelloyK243;
              case(13)->Neutronium;
              case(14)->QCDMatter;
              case(15)->CosmicNeutronium;
            default -> Tin;
        };
    }

    public static Material getFluidMaterialByTier(int tier) {
        return switch (tier) {
            case (9) -> Seaborgium;
            case (10) -> Bohrium;
            case (11) -> MetastableHassium;
            case (12) -> HeavyQuarkDegenerateMatter;
            case (13) -> SuperheavyMix;
            case (14) -> Neutronium;
            default -> Naquadria;
        };
    }

    public static Material getFluidPipeMaterialByTier(int tier) {
        return switch (tier) {
            case(1) -> Bronze;
            case(2) -> Steel;
            case(3) -> StainlessSteel;
            case(4) -> Titanium;
            case(5) -> TungstenSteel;
            case (9) -> Zeron100;
            case (10) -> Lafium;
            case (11) -> TantalumHafniumSeaborgiumCarbide;
            case (12) -> EnrichedNaquadahAlloy;
            case (13) -> Neutronium;
            case (14) -> CosmicNeutronium;
            default -> Tin;
        };
    }

    public static Material getPolymerByTier(int tier) {
        return switch (tier) {
            case(8) -> Polybenzimidazole;
            case(9) -> Polyetheretherketone;
            case(10) -> Polyetheretherketone;
            case(11) -> Zylon;
            case(12) -> Zylon;
            case(13) -> FullerenePolymerMatrix;
            case(14) -> FullerenePolymerMatrix;
            default -> Polyethylene;
        };
    }

    public static MetaItem<?>.MetaValueItem getMotorByTier(int tier) {
        return switch (tier) {
            case(2) -> ELECTRIC_MOTOR_MV;
            case(3) -> ELECTRIC_MOTOR_HV;
            case(4) -> ELECTRIC_MOTOR_EV;
            case(5) -> ELECTRIC_MOTOR_IV;
            case(6) -> ELECTRIC_MOTOR_LuV;
            case(7) -> ELECTRIC_MOTOR_ZPM;
            case(8) -> ELECTRIC_MOTOR_UV;
            case(9) -> ELECTRIC_MOTOR_UHV;
            case(10) -> ELECTRIC_MOTOR_UEV;
            case(11) -> ELECTRIC_MOTOR_UIV;
            case(12) -> ELECTRIC_MOTOR_UXV;
            case(13) -> ELECTRIC_MOTOR_OpV;
            case(14) -> ELECTRIC_MOTOR_MAX;
            default -> ELECTRIC_MOTOR_LV;
        };
    }

    public static MetaItem<?>.MetaValueItem getConveyorByTier(int tier) {
        return switch (tier) {
            case(2) -> CONVEYOR_MODULE_MV;
            case(3) -> CONVEYOR_MODULE_HV;
            case(4) -> CONVEYOR_MODULE_EV;
            case(5) -> CONVEYOR_MODULE_IV;
            case(6) -> CONVEYOR_MODULE_LuV;
            case(7) -> CONVEYOR_MODULE_ZPM;
            case(8) -> CONVEYOR_MODULE_UV;
            case(9) -> CONVEYOR_MODULE_UHV;
            case(10) -> CONVEYOR_MODULE_UEV;
            case(11) -> CONVEYOR_MODULE_UIV;
            case(12) -> CONVEYOR_MODULE_UXV;
            case(13) -> CONVEYOR_MODULE_OpV;
            case(14) -> CONVEYOR_MODULE_MAX;
            default -> CONVEYOR_MODULE_LV;
        };
    }

    public static MetaItem<?>.MetaValueItem getPistonByTier(int tier) {
        return switch (tier) {
            case(2) -> ELECTRIC_PISTON_MV;
            case(3) -> ELECTRIC_PISTON_HV;
            case(4) -> ELECTRIC_PISTON_EV;
            case(5) -> ELECTRIC_PISTON_IV;
            case(6) -> ELECTRIC_PISTON_LUV;
            case(7) -> ELECTRIC_PISTON_ZPM;
            case(8) -> ELECTRIC_PISTON_UV;
            case(9) -> ELECTRIC_PISTON_UHV;
            case(10) -> ELECTRIC_PISTON_UEV;
            case(11) -> ELECTRIC_PISTON_UIV;
            case(12) -> ELECTRIC_PISTON_UXV;
            case(13) -> ELECTRIC_PISTON_OpV;
            case(14) -> ELECTRIC_PISTON_MAX;
            default -> ELECTRIC_PISTON_LV;
        };
    }

    public static MetaItem<?>.MetaValueItem getRobotArmByTier(int tier) {
        return switch (tier) {
            case(2) -> ROBOT_ARM_MV;
            case(3) -> ROBOT_ARM_HV;
            case(4) -> ROBOT_ARM_EV;
            case(5) -> ROBOT_ARM_IV;
            case(6) -> ROBOT_ARM_LuV;
            case(7) -> ROBOT_ARM_ZPM;
            case(8) -> ROBOT_ARM_UV;
            case(9) -> ROBOT_ARM_UHV;
            case(10) -> ROBOT_ARM_UEV;
            case(11) -> ROBOT_ARM_UIV;
            case(12) -> ROBOT_ARM_UXV;
            case(13) -> ROBOT_ARM_OpV;
            case(14) -> ROBOT_ARM_MAX;
            default -> ROBOT_ARM_LV;
        };
    }

    public static MetaItem<?>.MetaValueItem getEmitterByTier(int tier) {
        return switch (tier) {
            case(2) -> EMITTER_MV;
            case(3) -> EMITTER_HV;
            case(4) -> EMITTER_EV;
            case(5) -> EMITTER_IV;
            case(6) -> EMITTER_LuV;
            case(7) -> EMITTER_ZPM;
            case(8) -> EMITTER_UV;
            case(9) -> EMITTER_UHV;
            case(10) -> EMITTER_UEV;
            case(11) -> EMITTER_UIV;
            case(12) -> EMITTER_UXV;
            case(13) -> EMITTER_OpV;
            case(14) -> EMITTER_MAX;
            default -> EMITTER_LV;
        };
    }

    public static MetaItem<?>.MetaValueItem getPumpByTier(int tier) {
        return switch (tier) {
            case(2) -> ELECTRIC_PUMP_MV;
            case(3) -> ELECTRIC_PUMP_HV;
            case(4) -> ELECTRIC_PUMP_EV;
            case(5) -> ELECTRIC_PUMP_IV;
            case(6) -> ELECTRIC_PUMP_LuV;
            case(7) -> ELECTRIC_PUMP_ZPM;
            case(8) -> ELECTRIC_PUMP_UV;
            case(9) -> ELECTRIC_PUMP_UHV;
            case(10) -> ELECTRIC_PUMP_UEV;
            case(11) -> ELECTRIC_PUMP_UIV;
            case(12) -> ELECTRIC_PUMP_UXV;
            case(13) -> ELECTRIC_PUMP_OpV;
            case(14) -> ELECTRIC_PUMP_MAX;
            default -> ELECTRIC_PUMP_LV;
        };
    }

    public static MetaItem<?>.MetaValueItem getStarByTier(int tier) {
        if(tier == 4)
            return QUANTUM_EYE;
        if(tier < 8)
            return QUANTUM_STAR;
        if(tier < 10)
            return GRAVI_STAR;
        if(tier < 12)
            return UNSTABLE_STAR;
        else
            return NUCLEAR_STAR;
    }

    public static Material getLowEmitterSensorStarMaterial(int tier) {
        return switch (tier) {
            case(1) -> Quartzite;
            case(2) -> Emerald;
            case(3) -> EnderEye;
            default -> null;
        };
    }

    public static Material getLowEmitterSensorRodMaterial(int tier) {
        return switch (tier) {
            case(1) -> Brass;
            case(2) -> Electrum;
            case(3) -> Chrome;
            case(4) -> Platinum;
            case(5) -> Iridium;
            default -> null;
        };
    }

    public static GCYLFusionCoils.CasingType getAdvFusionCoilByAdvTier(int tier) {
        return switch (tier) {
            case(1) -> GCYLFusionCoils.CasingType.ADV_FUSION_COIL_2;
            case(2) -> GCYLFusionCoils.CasingType.ADV_FUSION_COIL_3;
            case(3) -> GCYLFusionCoils.CasingType.ADV_FUSION_COIL_4;
            case(4) -> GCYLFusionCoils.CasingType.ADV_FUSION_COIL_5;
            default -> GCYLFusionCoils.CasingType.ADV_FUSION_COIL_1;
        };
    }

    public static Material getAdvFusionMaterialByAdvTier1(int tier) {
        return switch (tier) {
            case(1) -> Bohrium;
            case(2) -> Vibranium;
            case(3) -> HeavyQuarkDegenerateMatter;
            case(4) -> QCDMatter;
            default -> TantalumHafniumSeaborgiumCarbide;
        };
    }

    public static Material getAdvFusionMaterialByAdvTier2(int tier) {
        return switch (tier) {
            case(1) -> Einsteinium;
            case(2) -> Mendelevium;
            case(3) -> MetastableFlerovium;
            case(4) -> SuperheavyLAlloy;
            default -> TantalumHafniumSeaborgiumCarbide;
        };
    }

    public static List<RecipeBuilder<?>> buildHigherYieldCleanroomRecipes(RecipeMap<?> recipeMap, MetaItem<?>.MetaValueItem output, int baseOutputAmount, int startCleanRoomTier, int startEUt) {
        List<RecipeBuilder<?>> recipeBuilderList = new ArrayList<>();

        for (int i = startCleanRoomTier; i < 7; i++) {
            recipeBuilderList.add(recipeMap.recipeBuilder()
                    .circuitMeta(i)
                    .cleanroom(getCleanroomTypeByTierNotV(i))
                    .EUt(i == startCleanRoomTier ? startEUt : (int) (startEUt * Math.pow(4, i - startCleanRoomTier)))
                    //.outputs(output.getStackForm((int) (baseOutputAmount * Math.pow(2, i - startCleanRoomTier)))) //TODO MAY NEED NERF
                    .outputs(output.getStackForm(i == startCleanRoomTier ? baseOutputAmount : 2 * baseOutputAmount * (i - startCleanRoomTier))));
        }
        return recipeBuilderList;
    }

    public static RecipeBuilder<AssemblyLineRecipeBuilder> getAssLineResearchBuilder(int tier, int duration, ItemStack researchStack, boolean upTierEUt, boolean upTierCWUt) {
            return ASSEMBLY_LINE_RECIPES.recipeBuilder().EUt(GTValues.VA[upTierEUt ? tier + 1 : tier]).duration(duration)
                    .stationResearch(b -> b
                            .dataStack(getDataStack(getCWUt(upTierCWUt ? tier + 1 : tier)))
                            .CWUt(getCWUt(upTierCWUt ? tier + 1 : tier))
                            .researchStack(researchStack)
                            .EUt(GTValues.VA[upTierEUt ? tier + 1 : tier]));
    }

    public static RecipeBuilder<AssemblyLineRecipeBuilder> getAssLineResearchBuilder(int tier, int duration, ItemStack researchStack, boolean upTierEUt, boolean upTierCWUt, double researchCWUTotalMulti) {
        return ASSEMBLY_LINE_RECIPES.recipeBuilder().EUt(GTValues.VA[upTierEUt ? tier + 1 : tier]).duration(duration)
                    .stationResearch(b -> b
                            .CWUt(getCWUt(upTierCWUt ? tier + 1 : tier), (int) (4000 * (GTValues.VA[upTierEUt ? tier + 1 : tier] * researchCWUTotalMulti)))
                            .dataStack(getDataStack(getCWUt(upTierCWUt ? tier + 1 : tier)))
                            .researchStack(researchStack)
                            .EUt(GTValues.VA[upTierEUt ? tier + 1 : tier]));
    }

    public static RecipeBuilder<AssemblyLineRecipeBuilder> getAssLineResearchBuilder(int tier, int duration, ItemStack researchStack, boolean upTierEUt, boolean upTierCWUt, CleanroomType cleanroomType) {
        return ASSEMBLY_LINE_RECIPES.recipeBuilder().EUt(GTValues.VA[upTierEUt ? tier + 1 : tier]).duration(duration).cleanroom(cleanroomType)
                .stationResearch(b -> b
                        .dataStack(getDataStack(getCWUt(upTierCWUt ? tier + 1 : tier)))
                        .CWUt(getCWUt(upTierCWUt ? tier + 1 : tier))
                        .researchStack(researchStack)
                        .EUt(GTValues.VA[upTierEUt ? tier + 1 : tier]));
    }

    public static RecipeBuilder<AssemblyLineRecipeBuilder> getAssLineResearchBuilder(int tier, int duration, ItemStack researchStack, boolean upTierEUt, boolean upTierCWUt, double researchCWUTotalMulti, CleanroomType cleanroomType) {
        return ASSEMBLY_LINE_RECIPES.recipeBuilder().EUt(GTValues.VA[upTierEUt ? tier + 1 : tier]).duration(duration).cleanroom(cleanroomType)
                .stationResearch(b -> b
                        .CWUt(getCWUt(upTierCWUt ? tier + 1 : tier), (int) (4000 * (GTValues.VA[upTierEUt ? tier + 1 : tier] * researchCWUTotalMulti)))
                        .dataStack(getDataStack(getCWUt(upTierCWUt ? tier + 1 : tier)))
                        .researchStack(researchStack)
                        .EUt(GTValues.VA[upTierEUt ? tier + 1 : tier]));
    }

    public static RecipeBuilder<AssemblyLineRecipeBuilder> getAssLineResearchBuilder(int tier, int duration, ItemStack researchStack, boolean upTierEUt, boolean upTierCWUt, CleanroomType cleanroomType, int recipeEUt) {
        return ASSEMBLY_LINE_RECIPES.recipeBuilder().EUt(recipeEUt).duration(duration).cleanroom(cleanroomType)
                .stationResearch(b -> b
                        .dataStack(getDataStack(getCWUt(upTierCWUt ? tier + 1 : tier)))
                        .CWUt(getCWUt(upTierCWUt ? tier + 1 : tier))
                        .researchStack(researchStack)
                        .EUt(GTValues.VA[upTierEUt ? tier + 1 : tier]));
    }

    public static RecipeBuilder<AssemblyLineRecipeBuilder> getAssLineResearchBuilder(int tier, int duration, ItemStack researchStack, boolean upTierEUt, boolean upTierCWUt, int recipeEUt) {
        return ASSEMBLY_LINE_RECIPES.recipeBuilder().EUt(recipeEUt).duration(duration)
                .stationResearch(b -> b
                        .dataStack(getDataStack(getCWUt(upTierCWUt ? tier + 1 : tier)))
                        .CWUt(getCWUt(upTierCWUt ? tier + 1 : tier))
                        .researchStack(researchStack)
                        .EUt(GTValues.VA[upTierEUt ? tier + 1 : tier]));
    }
}
