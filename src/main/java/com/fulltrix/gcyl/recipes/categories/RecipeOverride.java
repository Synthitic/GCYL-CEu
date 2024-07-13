package com.fulltrix.gcyl.recipes.categories;

import gregicality.multiblocks.common.block.GCYMMetaBlocks;
import gregicality.multiblocks.common.block.blocks.BlockLargeMultiblockCasing;
import gregicality.multiblocks.common.metatileentities.GCYMMetaTileEntities;
import gregtech.api.GTValues;
import gregtech.api.items.armor.ArmorMetaItem;
import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.ingredients.nbtmatch.NBTCondition;
import gregtech.api.recipes.ingredients.nbtmatch.NBTMatcher;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.*;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import static com.fulltrix.gcyl.api.GCYLUtility.getAssLineResearchBuilder;
import static com.fulltrix.gcyl.item.GCYLCoreItems.*;
import static com.fulltrix.gcyl.machines.GCYLTileEntities.CRYOGENIC_FREEZER;
import static com.fulltrix.gcyl.machines.GCYLTileEntities.VOLCANUS;
import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static gregicality.multiblocks.api.recipes.GCYMRecipeMaps.ALLOY_BLAST_RECIPES;
import static gregicality.multiblocks.api.unification.GCYMMaterials.*;
import static gregicality.multiblocks.common.metatileentities.GCYMMetaTileEntities.MEGA_BLAST_FURNACE;
import static gregicality.multiblocks.common.metatileentities.GCYMMetaTileEntities.MEGA_VACUUM_FREEZER;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.GTRecipeHandler.removeRecipesByInputs;
import static gregtech.api.recipes.ModHandler.removeRecipeByName;
import static gregtech.api.recipes.ModHandler.removeTieredRecipeByName;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.recipes.ingredients.IntCircuitIngredient.getIntegratedCircuit;
import static gregtech.api.unification.material.MarkerMaterials.Color.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.blocks.BlockFusionCasing.CasingType.FUSION_COIL;
import static gregtech.common.blocks.BlockFusionCasing.CasingType.SUPERCONDUCTOR_COIL;
import static gregtech.common.blocks.BlockMetalCasing.MetalCasingType.ALUMINIUM_FROSTPROOF;
import static gregtech.common.blocks.MetaBlocks.*;
import static gregtech.common.items.MetaItems.GRAVITATION_ENGINE;
import static gregtech.common.items.MetaItems.*;
import static gregtech.common.metatileentities.MetaTileEntities.*;
import static gregtech.integration.crafttweaker.recipe.CTRecipeUtils.removeAll;

public class RecipeOverride {
    public static void init() {

        /*
        vanillaOverride();
        brewingOverride();
        configCircuitOverride();

         */

        recipeRemoval();
        chemistryOverride();
        gregtechOverride();
        waferRecipeFixes();
    }

    private static void recipeRemoval() {

        //removeRecipesByInputs(FLUID_SOLIDFICATION_RECIPES, new ItemStack[]{MetaItems.SHAPE_MOLD_CYLINDER.getStackForm()},  new FluidStack[]{Polytetrafluoroethylene.getFluid(36)});

        //remove all circuit assembler recipes
        removeAll(CIRCUIT_ASSEMBLER_RECIPES);

        //remove all assembly line recipes
        removeAll(ASSEMBLY_LINE_RECIPES);
        removeAll(SCANNER_RECIPES);
        removeAll(RESEARCH_STATION_RECIPES);

        //conflict removal
        removeRecipesByInputs(DISTILLERY_RECIPES, new ItemStack[]{getIntegratedCircuit(1)}, new FluidStack[]{CoalTar.getFluid(100)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new ItemStack[]{getIntegratedCircuit(2)}, new FluidStack[]{CoalTar.getFluid(100)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new ItemStack[]{getIntegratedCircuit(3)}, new FluidStack[]{CoalTar.getFluid(200)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new ItemStack[]{getIntegratedCircuit(4)}, new FluidStack[]{CoalTar.getFluid(500)});
        removeRecipesByInputs(DISTILLATION_RECIPES, CoalTar.getFluid(1000));

        removeRecipesByInputs(CHEMICAL_RECIPES, Ethylbenzene.getFluid(1000));
        removeRecipesByInputs(LARGE_CHEMICAL_RECIPES, Ethylbenzene.getFluid(1000));

        removeRecipesByInputs(CHEMICAL_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, SodiumHydroxide, 3)}, new FluidStack[]{SulfuricAcid.getFluid(1000)});
        removeRecipesByInputs(LARGE_CHEMICAL_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, SodiumHydroxide, 3)}, new FluidStack[]{SulfuricAcid.getFluid(1000)});

        removeRecipesByInputs(CENTRIFUGE_RECIPES, new ItemStack[]{OreDictUnifier.get(dust,PlatinumGroupSludge,6)}, new FluidStack[]{AquaRegia.getFluid(1200)});

        removeRecipesByInputs(CHEMICAL_RECIPES, new ItemStack[]{OreDictUnifier.get(dust,PhosphorusPentoxide,14)}, new FluidStack[]{Water.getFluid(6000)});
        removeRecipesByInputs(LARGE_CHEMICAL_RECIPES, new ItemStack[]{OreDictUnifier.get(dust,PhosphorusPentoxide,14)}, new FluidStack[]{Water.getFluid(6000)});

        removeRecipesByInputs(CHEMICAL_RECIPES, Ethylene.getFluid(1000), Benzene.getFluid(1000));
        removeRecipesByInputs(LARGE_CHEMICAL_RECIPES, Ethylene.getFluid(1000), Benzene.getFluid(1000));

        removeRecipesByInputs(CHEMICAL_RECIPES, Chlorine.getFluid(4000), Ethane.getFluid(1000));
        removeRecipesByInputs(LARGE_CHEMICAL_RECIPES, Chlorine.getFluid(4000), Ethane.getFluid(1000));

        removeRecipesByInputs(CHEMICAL_RECIPES, Ethanol.getFluid(1000), SulfuricAcid.getFluid(1000));
        removeRecipesByInputs(LARGE_CHEMICAL_RECIPES, Ethanol.getFluid(1000), SulfuricAcid.getFluid(1000));

        removeRecipesByInputs(MIXER_RECIPES, DinitrogenTetroxide.getFluid(1000),Dimethylhydrazine.getFluid(1000));

        removeRecipesByInputs(BLAST_RECIPES, OreDictUnifier.get(dust,SiliconDioxide,3), OreDictUnifier.get(dust,Carbon,2));

        //Neutronium wafers,boule,cutting stuff //TODO Replace recipes: soc, pic, nand, nor, simple soc, lpic, ulpic, integrated logic circuit
        removeRecipesByInputs(LASER_ENGRAVER_RECIPES, OreDictUnifier.get(craftingLens,Pink), NEUTRONIUM_WAFER.getStackForm()); //nor
        removeRecipesByInputs(LASER_ENGRAVER_RECIPES, OreDictUnifier.get(craftingLens,Red), NEUTRONIUM_WAFER.getStackForm()); //integrated logic circuit
        removeRecipesByInputs(LASER_ENGRAVER_RECIPES, OreDictUnifier.get(craftingLens,Green), NEUTRONIUM_WAFER.getStackForm()); //ram
        removeRecipesByInputs(LASER_ENGRAVER_RECIPES, OreDictUnifier.get(craftingLens,Blue), NEUTRONIUM_WAFER.getStackForm()); //cpu
        removeRecipesByInputs(LASER_ENGRAVER_RECIPES, OreDictUnifier.get(craftingLens,LightBlue), NEUTRONIUM_WAFER.getStackForm()); //ulpic
        removeRecipesByInputs(LASER_ENGRAVER_RECIPES, OreDictUnifier.get(craftingLens,Orange), NEUTRONIUM_WAFER.getStackForm()); //lpic
        removeRecipesByInputs(LASER_ENGRAVER_RECIPES, OreDictUnifier.get(craftingLens,Cyan), NEUTRONIUM_WAFER.getStackForm()); //simple soc
        removeRecipesByInputs(LASER_ENGRAVER_RECIPES, OreDictUnifier.get(craftingLens,Gray), NEUTRONIUM_WAFER.getStackForm()); //nand
        removeRecipesByInputs(LASER_ENGRAVER_RECIPES, OreDictUnifier.get(craftingLens,Brown), NEUTRONIUM_WAFER.getStackForm()); //pic
        removeRecipesByInputs(LASER_ENGRAVER_RECIPES, OreDictUnifier.get(craftingLens,Yellow), NEUTRONIUM_WAFER.getStackForm()); //soc
        removeRecipesByInputs(LASER_ENGRAVER_RECIPES, OreDictUnifier.get(craftingLens,Purple), NEUTRONIUM_WAFER.getStackForm()); //asoc
        removeRecipesByInputs(LASER_ENGRAVER_RECIPES, OreDictUnifier.get(craftingLens,Black), NEUTRONIUM_WAFER.getStackForm()); //hasoc
        removeRecipesByInputs(CUTTER_RECIPES, new ItemStack[]{NEUTRONIUM_BOULE.getStackForm()}, new FluidStack[]{Water.getFluid(1000)});
        removeRecipesByInputs(CUTTER_RECIPES, new ItemStack[]{NEUTRONIUM_BOULE.getStackForm()}, new FluidStack[]{DistilledWater.getFluid(750)});
        removeRecipesByInputs(CUTTER_RECIPES, new ItemStack[]{NEUTRONIUM_BOULE.getStackForm()}, new FluidStack[]{Lubricant.getFluid(250)});

        //Coil removals
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireGtDouble, Trinium,8), OreDictUnifier.get(foil,NaquadahEnriched,8)}, new FluidStack[]{Naquadah.getFluid(144)});
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireGtDouble, Tritanium,8), OreDictUnifier.get(foil,Naquadria,8)}, new FluidStack[]{Trinium.getFluid(144)});

        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireGtDouble, Cupronickel,8), OreDictUnifier.get(foil,Bronze,8)}, new FluidStack[]{TinAlloy.getFluid(144)});
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireGtDouble, Kanthal,8), OreDictUnifier.get(foil,Aluminium,8)}, new FluidStack[]{Copper.getFluid(144)});
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireGtDouble, Nichrome,8), OreDictUnifier.get(foil,StainlessSteel,8)}, new FluidStack[]{Aluminium.getFluid(144)});
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireGtDouble, RTMAlloy,8), OreDictUnifier.get(foil,VanadiumSteel,8)}, new FluidStack[]{Nichrome.getFluid(144)});
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireGtDouble, HSSG,8), OreDictUnifier.get(foil,TungstenCarbide,8)}, new FluidStack[]{Tungsten.getFluid(144)});
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireGtDouble, Naquadah,8), OreDictUnifier.get(foil,Osmium,8)}, new FluidStack[]{TungstenSteel.getFluid(144)});

        //infinite water cover
        removeRecipesByInputs(ASSEMBLER_RECIPES, ELECTRIC_PUMP_HV.getStackForm(2), new ItemStack(Items.CAULDRON), OreDictUnifier.get(circuit, MarkerMaterials.Tier.HV));

        //reservoir hatch
        removeRecipesByInputs(ASSEMBLER_RECIPES, COVER_INFINITE_WATER.getStackForm(), FLUID_IMPORT_HATCH[EV].getStackForm(), ELECTRIC_PUMP_EV.getStackForm());

        //Plat line fixes TODO: remove and replace the recipes that turn ore into platinum group sludge
        removeRecipesByInputs(LARGE_CHEMICAL_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, RarestMetalMixture, 7)}, new FluidStack[]{HydrochloricAcid.getFluid(4000)});
        removeRecipesByInputs(CENTRIFUGE_RECIPES, OreDictUnifier.get(dust,PlatinumSludgeResidue,5));
        removeRecipesByInputs(DISTILLATION_RECIPES, AcidicOsmiumSolution.getFluid(2000));
        removeRecipesByInputs(DISTILLERY_RECIPES, new ItemStack[]{getIntegratedCircuit(1)}, new FluidStack[]{AcidicOsmiumSolution.getFluid(400)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new ItemStack[]{getIntegratedCircuit(2)}, new FluidStack[]{AcidicOsmiumSolution.getFluid(400)});
        removeRecipesByInputs(CHEMICAL_RECIPES, OreDictUnifier.get(dust, RutheniumTetroxide,5), OreDictUnifier.get(dust,Carbon,2));
        removeRecipesByInputs(LARGE_CHEMICAL_RECIPES, OreDictUnifier.get(dust, RutheniumTetroxide,5), OreDictUnifier.get(dust,Carbon,2));
        removeRecipesByInputs(CHEMICAL_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, InertMetalMixture, 6)}, new FluidStack[]{SulfuricAcid.getFluid(1500)});
        removeRecipesByInputs(LARGE_CHEMICAL_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, InertMetalMixture, 6)}, new FluidStack[]{SulfuricAcid.getFluid(1500)});

        //Field generators
        removeRecipeByName("gregtech:field_generator_lv");
        removeRecipeByName("gregtech:field_generator_mv");
        removeRecipeByName("gregtech:field_generator_hv");
        removeRecipeByName("gregtech:field_generator_ev");
        removeRecipeByName("gregtech:field_generator_iv");

        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(wireGtQuadruple, ManganesePhosphide,4), OreDictUnifier.get(plate, Steel, 2), OreDictUnifier.get(gem, EnderPearl), OreDictUnifier.get(circuit, MarkerMaterials.Tier.LV,2));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(wireGtQuadruple, MagnesiumDiboride,4), OreDictUnifier.get(plate, Aluminium, 2), OreDictUnifier.get(gem, EnderEye), OreDictUnifier.get(circuit, MarkerMaterials.Tier.MV,2));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(wireGtQuadruple, MercuryBariumCalciumCuprate,4), OreDictUnifier.get(plate, StainlessSteel, 2), QUANTUM_EYE.getStackForm(), OreDictUnifier.get(circuit, MarkerMaterials.Tier.HV,2));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(wireGtQuadruple, UraniumTriplatinum,4), OreDictUnifier.get(plateDouble, Titanium, 2), OreDictUnifier.get(gem, NetherStar), OreDictUnifier.get(circuit, MarkerMaterials.Tier.EV,2));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(wireGtQuadruple, SamariumIronArsenicOxide,4), OreDictUnifier.get(plateDouble, TungstenSteel, 2), QUANTUM_STAR.getStackForm(), OreDictUnifier.get(circuit, MarkerMaterials.Tier.IV,2));


        //Remove old superconductor mixer recipes
        removeRecipesByInputs(MIXER_RECIPES, getIntegratedCircuit(4), OreDictUnifier.get(dust,Manganese), OreDictUnifier.get(dust,Phosphorus));
        removeRecipesByInputs(MIXER_RECIPES, getIntegratedCircuit(4), OreDictUnifier.get(dust,Magnesium), OreDictUnifier.get(dust,Boron,2));
        removeRecipesByInputs(MIXER_RECIPES, new ItemStack[]{getIntegratedCircuit(4), OreDictUnifier.get(dust, Barium,2), OreDictUnifier.get(dust,Calcium,2), OreDictUnifier.get(dust,Copper,3)},new FluidStack[]{Oxygen.getFluid(8000),Mercury.getFluid(1000)});
        removeRecipesByInputs(MIXER_RECIPES, getIntegratedCircuit(4), OreDictUnifier.get(dust,Uranium238), OreDictUnifier.get(dust,Platinum,3));
        removeRecipesByInputs(MIXER_RECIPES, getIntegratedCircuit(4), OreDictUnifier.get(dust,Uranium), OreDictUnifier.get(dust,Platinum,3));
        removeRecipesByInputs(MIXER_RECIPES, new ItemStack[]{getIntegratedCircuit(4), OreDictUnifier.get(dust, Samarium), OreDictUnifier.get(dust,Iron,1), OreDictUnifier.get(dust,Arsenic,1)},new FluidStack[]{Oxygen.getFluid(1000)});
        removeRecipesByInputs(MIXER_RECIPES, new ItemStack[]{getIntegratedCircuit(4), OreDictUnifier.get(dust, Indium,4), OreDictUnifier.get(dust,Tin,2), OreDictUnifier.get(dust,Barium,2), OreDictUnifier.get(dust,Titanium,1), OreDictUnifier.get(dust,Copper,7)},new FluidStack[]{Oxygen.getFluid(14000)});
        removeRecipesByInputs(MIXER_RECIPES, getIntegratedCircuit(4), OreDictUnifier.get(dust,Uranium238), OreDictUnifier.get(dust,Rhodium,1), OreDictUnifier.get(dust, Naquadah,2));
        removeRecipesByInputs(MIXER_RECIPES, getIntegratedCircuit(4), OreDictUnifier.get(dust,NaquadahEnriched,4), OreDictUnifier.get(dust,Trinium,3), OreDictUnifier.get(dust, Europium,2), OreDictUnifier.get(dust, Duranium));
        removeRecipesByInputs(MIXER_RECIPES, new ItemStack[]{getIntegratedCircuit(4), OreDictUnifier.get(dust, Ruthenium,1), OreDictUnifier.get(dust,Trinium,2), OreDictUnifier.get(dust,Americium,1), OreDictUnifier.get(dust,Neutronium,2)},new FluidStack[]{Oxygen.getFluid(8000)});

        //naquadah alloy
        removeRecipesByInputs(MIXER_RECIPES, getIntegratedCircuit(2), OreDictUnifier.get(dust, Naquadah, 2), OreDictUnifier.get(dust, Osmiridium), OreDictUnifier.get(dust, Trinium));
        removeRecipesByInputs(CENTRIFUGE_RECIPES, OreDictUnifier.get(dust, NaquadahAlloy, 4)); //DOESNT WORK

        //Severely steam cracked naphtha
        removeRecipesByInputs(DISTILLATION_RECIPES, SeverelySteamCrackedNaphtha.getFluid(1000));
        removeRecipesByInputs(DISTILLERY_RECIPES, new ItemStack[]{getIntegratedCircuit(1)}, new FluidStack[]{SeverelySteamCrackedNaphtha.getFluid(1000)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new ItemStack[]{getIntegratedCircuit(2)}, new FluidStack[]{SeverelySteamCrackedNaphtha.getFluid(500)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new ItemStack[]{getIntegratedCircuit(3)}, new FluidStack[]{SeverelySteamCrackedNaphtha.getFluid(1000)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new ItemStack[]{getIntegratedCircuit(4)}, new FluidStack[]{SeverelySteamCrackedNaphtha.getFluid(500)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new ItemStack[]{getIntegratedCircuit(5)}, new FluidStack[]{SeverelySteamCrackedNaphtha.getFluid(500)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new ItemStack[]{getIntegratedCircuit(6)}, new FluidStack[]{SeverelySteamCrackedNaphtha.getFluid(500)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new ItemStack[]{getIntegratedCircuit(7)}, new FluidStack[]{SeverelySteamCrackedNaphtha.getFluid(1000)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new ItemStack[]{getIntegratedCircuit(8)}, new FluidStack[]{SeverelySteamCrackedNaphtha.getFluid(100)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new ItemStack[]{getIntegratedCircuit(9)}, new FluidStack[]{SeverelySteamCrackedNaphtha.getFluid(1000)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new ItemStack[]{getIntegratedCircuit(10)}, new FluidStack[]{SeverelySteamCrackedNaphtha.getFluid(100)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new ItemStack[]{getIntegratedCircuit(11)}, new FluidStack[]{SeverelySteamCrackedNaphtha.getFluid(100)});

        //sticky resin extract
        removeRecipesByInputs(EXTRACTOR_RECIPES, STICKY_RESIN.getStackForm());

        //useless gcym electric implosion compressor
        removeRecipeByName("gcym:electric_implosion_compressor");


        //gravi star
        removeRecipesByInputs(AUTOCLAVE_RECIPES, new ItemStack[]{QUANTUM_STAR.getStackForm()}, new FluidStack[]{Neutronium.getFluid(288)});

        //rare earth
        removeRecipesByInputs(CENTRIFUGE_RECIPES, OreDictUnifier.get(dust,RareEarth));

        //graphene dust
        removeRecipesByInputs(MIXER_RECIPES,getIntegratedCircuit(1), OreDictUnifier.get(dust, Graphite), OreDictUnifier.get(dust, Silicon), OreDictUnifier.get(dust, Carbon,4));

        //vaccuum freezer
        removeRecipeByName("gregtech:vacuum_freezer");

        //iv motor
        removeRecipeByName("gregtech:electric_motor_iv");
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(cableGtDouble, Tungsten,2), OreDictUnifier.get(stick, TungstenSteel, 2), OreDictUnifier.get(stick, NeodymiumMagnetic), OreDictUnifier.get(wireGtDouble, Graphene, 4));

        //neutronium
        removeRecipesByInputs(FUSION_RECIPES,Americium.getFluid(128),Naquadria.getFluid(128));

        //sterilized growth medium
        removeRecipesByInputs(FLUID_HEATER_RECIPES, new ItemStack[]{getIntegratedCircuit(1)}, new FluidStack[]{RawGrowthMedium.getFluid(100)});
        //raw growth medium
        removeRecipesByInputs(MIXER_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, Meat, 4),OreDictUnifier.get(dust, Salt,4),OreDictUnifier.get(dust,Calcium,4),OreDictUnifier.get(dust,Agar,4)}, new FluidStack[]{Mutagen.getFluid(4000)});
        //engraved crystal chip
        removeRecipesByInputs(BLAST_RECIPES, new ItemStack[]{OreDictUnifier.get(plate, Emerald), RAW_CRYSTAL_CHIP.getStackForm()}, new FluidStack[]{Helium.getFluid(1000)});
        removeRecipesByInputs(BLAST_RECIPES, new ItemStack[]{OreDictUnifier.get(plate, Olivine), RAW_CRYSTAL_CHIP.getStackForm()}, new FluidStack[]{Helium.getFluid(1000)});
        //raw crystal chip
        removeRecipesByInputs(AUTOCLAVE_RECIPES, new ItemStack[]{OreDictUnifier.get(gemExquisite, Emerald)},new FluidStack[]{Europium.getFluid(16)});
        removeRecipesByInputs(AUTOCLAVE_RECIPES, new ItemStack[]{OreDictUnifier.get(gemExquisite, Olivine)},new FluidStack[]{Europium.getFluid(16)});
        removeRecipesByInputs(AUTOCLAVE_RECIPES, new ItemStack[]{RAW_CRYSTAL_CHIP_PART.getStackForm()},new FluidStack[]{Mutagen.getFluid(250)});
        removeRecipesByInputs(AUTOCLAVE_RECIPES, new ItemStack[]{RAW_CRYSTAL_CHIP_PART.getStackForm()},new FluidStack[]{BacterialSludge.getFluid(250)});
        removeRecipesByInputs(AUTOCLAVE_RECIPES, new ItemStack[]{RAW_CRYSTAL_CHIP_PART.getStackForm()},new FluidStack[]{Europium.getFluid(16)});
        //raw crystal chip parts
        removeRecipesByInputs(FORGE_HAMMER_RECIPES, RAW_CRYSTAL_CHIP.getStackForm());

        //MICRO SMDS
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(dust,Carbon), OreDictUnifier.get(wireFine, Electrum, 4)}, new FluidStack[]{Polyethylene.getFluid(L * 2)});
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(dust,Carbon), OreDictUnifier.get(wireFine, Tantalum, 4)}, new FluidStack[]{Polyethylene.getFluid(L * 2)});

        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(dust,GalliumArsenide), OreDictUnifier.get(wireFine, Platinum, 8)}, new FluidStack[]{Polyethylene.getFluid(L * 2)});

        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(foil,Gallium), OreDictUnifier.get(wireFine, AnnealedCopper, 8)}, new FluidStack[]{Polyethylene.getFluid(L)});
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(foil,Gallium), OreDictUnifier.get(wireFine, Tantalum, 8)}, new FluidStack[]{Polyethylene.getFluid(L)});

        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(foil,SiliconeRubber), OreDictUnifier.get(foil, Aluminium)}, new FluidStack[]{Polyethylene.getFluid(L / 2)});
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(foil,PolyvinylChloride,2), OreDictUnifier.get(foil, Aluminium)}, new FluidStack[]{Polyethylene.getFluid(L / 2)});
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(foil,SiliconeRubber), OreDictUnifier.get(foil, Tantalum)}, new FluidStack[]{Polyethylene.getFluid(L / 2)});
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(foil,PolyvinylChloride,2), OreDictUnifier.get(foil, Tantalum)}, new FluidStack[]{Polyethylene.getFluid(L / 2)});

        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(ring,NickelZincFerrite), OreDictUnifier.get(wireFine, Cupronickel, 4)}, new FluidStack[]{Polyethylene.getFluid(L)});
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(foil,NickelZincFerrite), OreDictUnifier.get(wireFine, Tantalum, 4)}, new FluidStack[]{Polyethylene.getFluid(L)});

        //ADVANCED SMDS
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(dust,Graphene), OreDictUnifier.get(wireFine, Platinum, 4)}, new FluidStack[]{Polybenzimidazole.getFluid(L * 2)});
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(dust,IndiumGalliumPhosphide), OreDictUnifier.get(wireFine, NiobiumTitanium, 16)}, new FluidStack[]{Polybenzimidazole.getFluid(L * 2)});
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(foil,VanadiumGallium), OreDictUnifier.get(wireFine, HSSG, 8)}, new FluidStack[]{Polybenzimidazole.getFluid(L)});
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(foil,Polybenzimidazole), OreDictUnifier.get(foil, HSSS)}, new FluidStack[]{Polybenzimidazole.getFluid(L * 2)});

        //Tungstic Acid
        removeRecipesByInputs(CHEMICAL_BATH_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, Scheelite,6)}, new FluidStack[]{HydrochloricAcid.getFluid(2000)});
        removeRecipesByInputs(CHEMICAL_BATH_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, Tungstate,7)}, new FluidStack[]{HydrochloricAcid.getFluid(2000)});

        //indium concentrate
        removeRecipesByInputs(MIXER_RECIPES, new ItemStack[]{OreDictUnifier.get(crushedPurified, Sphalerite), OreDictUnifier.get(crushedPurified, Galena)}, new FluidStack[]{SulfuricAcid.getFluid(4000)});

        //indium from concentrate
        removeRecipesByInputs(CHEMICAL_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, Aluminium, 4), getIntegratedCircuit(1)}, new FluidStack[]{IndiumConcentrate.getFluid(1000)});
        removeRecipesByInputs(LARGE_CHEMICAL_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, Aluminium, 4), getIntegratedCircuit(1)}, new FluidStack[]{IndiumConcentrate.getFluid(1000)});
        removeRecipesByInputs(CHEMICAL_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, Aluminium, 16), getIntegratedCircuit(4)}, new FluidStack[]{IndiumConcentrate.getFluid(4000)});
        removeRecipesByInputs(LARGE_CHEMICAL_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, Aluminium, 16), getIntegratedCircuit(4)}, new FluidStack[]{IndiumConcentrate.getFluid(4000)});

        //superconductor coil block
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireGtDouble, IndiumTinBariumTitaniumCuprate, 32), OreDictUnifier.get(foil, NiobiumTitanium, 32)}, new FluidStack[]{Trinium.getFluid(3456)});
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireGtDouble, UraniumRhodiumDinaquadide, 16), OreDictUnifier.get(foil, NiobiumTitanium, 16)}, new FluidStack[]{Trinium.getFluid(2304)});
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireGtDouble, EnrichedNaquadahTriniumEuropiumDuranide, 8), OreDictUnifier.get(foil, NiobiumTitanium, 8)}, new FluidStack[]{Trinium.getFluid(1152)});
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{OreDictUnifier.get(wireGtDouble, RutheniumTriniumAmericiumNeutronate, 4), OreDictUnifier.get(foil, NiobiumTitanium, 4)}, new FluidStack[]{Trinium.getFluid(576)});

        //fusion casings
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.LuV), FUSION_CASING.getItemVariant(SUPERCONDUCTOR_COIL), NEUTRON_REFLECTOR.getStackForm(), ELECTRIC_PUMP_LuV.getStackForm(), OreDictUnifier.get(plate, TungstenSteel, 6)}, new FluidStack[]{Polybenzimidazole.getFluid(144)});
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.ZPM), FUSION_CASING.getItemVariant(FUSION_COIL), VOLTAGE_COIL_ZPM.getStackForm(2), FIELD_GENERATOR_LuV.getStackForm(), OreDictUnifier.get(plate, Europium, 6)}, new FluidStack[]{Polybenzimidazole.getFluid(288)});
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.UV), FUSION_CASING.getItemVariant(FUSION_COIL), VOLTAGE_COIL_UV.getStackForm(2), FIELD_GENERATOR_ZPM.getStackForm(), OreDictUnifier.get(plate, Americium, 6)}, new FluidStack[]{Polybenzimidazole.getFluid(576)});

        //lv solar
        removeRecipeByName("gregtech:solar_panel_lv");

        //yttrium barium cuprate dust
        removeRecipesByInputs(MIXER_RECIPES, new ItemStack[]{getIntegratedCircuit(2), OreDictUnifier.get(dust, Yttrium), OreDictUnifier.get(dust, Barium, 2), OreDictUnifier.get(dust, Copper,3)}, new FluidStack[]{Oxygen.getFluid(7000)});

        //naquadah
        removeRecipesByInputs(BLAST_RECIPES, new ItemStack[]{OreDictUnifier.get(dust,NaquadriaSulfate, 6)}, new FluidStack[]{Hydrogen.getFluid(2000)});
        removeRecipesByInputs(BLAST_RECIPES, new ItemStack[]{OreDictUnifier.get(dust,EnrichedNaquadahSulfate, 6)}, new FluidStack[]{Hydrogen.getFluid(2000)});
        removeRecipesByInputs(BLAST_RECIPES, OreDictUnifier.get(dust, TriniumSulfide, 2), OreDictUnifier.get(dust, Zinc));
        removeRecipesByInputs(CENTRIFUGE_RECIPES, AcidicNaquadriaSolution.getFluid(3000));
        removeRecipesByInputs(MIXER_RECIPES, SulfuricAcid.getFluid(2000), NaquadriaSolution.getFluid(1000));
        removeRecipesByInputs(MIXER_RECIPES, SulfuricAcid.getFluid(2000), EnrichedNaquadahSolution.getFluid(1000));
        removeRecipesByInputs(LARGE_CHEMICAL_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, Naquadah,6)}, new FluidStack[]{FluoroantimonicAcid.getFluid(1000)});
        removeRecipesByInputs(CENTRIFUGE_RECIPES, ImpureNaquadriaSolution.getFluid(2000));
        removeRecipesByInputs(CENTRIFUGE_RECIPES, ImpureEnrichedNaquadahSolution.getFluid(2000));
        removeRecipesByInputs(DISTILLATION_RECIPES, NaquadriaWaste.getFluid(2000));
        removeRecipesByInputs(DISTILLERY_RECIPES, new ItemStack[]{getIntegratedCircuit(1)}, new FluidStack[]{NaquadriaWaste.getFluid(200)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new ItemStack[]{getIntegratedCircuit(2)}, new FluidStack[]{NaquadriaWaste.getFluid(200)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new ItemStack[]{getIntegratedCircuit(3)}, new FluidStack[]{NaquadriaWaste.getFluid(400)});
        removeRecipesByInputs(DISTILLATION_RECIPES, EnrichedNaquadahWaste.getFluid(2000));
        removeRecipesByInputs(DISTILLERY_RECIPES, new ItemStack[]{getIntegratedCircuit(1)}, new FluidStack[]{EnrichedNaquadahWaste.getFluid(200)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new ItemStack[]{getIntegratedCircuit(2)}, new FluidStack[]{EnrichedNaquadahWaste.getFluid(200)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new ItemStack[]{getIntegratedCircuit(3)}, new FluidStack[]{EnrichedNaquadahWaste.getFluid(400)});

        //circuit assemblers
        removeTieredRecipeByName("gregtech:gregtech.machine.circuit_assembler.",IV,OpV);

        //gcym circuit assembler
        removeRecipeByName("gcym:large_circuit_assembler");

        //osmium dust
        removeRecipesByInputs(CHEMICAL_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, OsmiumTetroxide, 5)}, new FluidStack[]{Hydrogen.getFluid(8000)});
        removeRecipesByInputs(LARGE_CHEMICAL_RECIPES, new ItemStack[]{OreDictUnifier.get(dust, OsmiumTetroxide, 5)}, new FluidStack[]{Hydrogen.getFluid(8000)});

        //distillation tower
        removeRecipeByName("gregtech:distillation_tower");

        //QCD matter plate
        removeRecipesByInputs(FLUID_SOLIDFICATION_RECIPES, new ItemStack[]{SHAPE_MOLD_PLATE.getStackForm()}, new FluidStack[]{QCDMatter.getFluid(144)});

        //Advanced computer casing
        removeRecipesByInputs(ASSEMBLER_RECIPES, COMPUTER_CASING.getItemVariant(BlockComputerCasing.CasingType.COMPUTER_CASING), OreDictUnifier.get(circuit, MarkerMaterials.Tier.ZPM), OreDictUnifier.get(wireFine, Cobalt, 64), OreDictUnifier.get(wireFine, Electrum, 64), OreDictUnifier.get(wireGtSingle, IndiumTinBariumTitaniumCuprate, 4));

        //computer heat vent
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(frameGt, StainlessSteel), ELECTRIC_MOTOR_IV.getStackForm(2), OreDictUnifier.get(rotor, StainlessSteel, 2), OreDictUnifier.get(pipeTinyFluid, StainlessSteel, 16), OreDictUnifier.get(plate, Copper, 16), OreDictUnifier.get(wireGtSingle, SamariumIronArsenicOxide));

        //active transformer
        removeRecipesByInputs(ASSEMBLER_RECIPES, new ItemStack[]{POWER_TRANSFORMER[GTValues.LuV].getStackForm(), OreDictUnifier.get(circuit, MarkerMaterials.Tier.LuV, 2),OreDictUnifier.get(wireGtSingle, IndiumTinBariumTitaniumCuprate, 8), ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(2)}, new FluidStack[]{PCBCoolant.getFluid(1000)});

        //bulk blast chiller
        removeRecipeByName("gcym:mega_vacuum_freezer");

        //rotary hearth furnace
        removeRecipeByName("gcym:mega_blast_furnace");

        //epichlorohydrin
        removeRecipesByInputs(CHEMICAL_RECIPES, Glycerol.getFluid(1000), HydrochloricAcid.getFluid(1000));
        removeRecipesByInputs(LARGE_CHEMICAL_RECIPES, Glycerol.getFluid(1000), HydrochloricAcid.getFluid(1000));

        //large scale assembler casing
        removeRecipeByName("gcym:casing_large_assembler");

        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(plate, Stellite100,6), OreDictUnifier.get(frameGt, Tungsten, 1), getIntegratedCircuit(6));

        //assembly line
        removeRecipeByName("gregtech:assembly_line");

        //air centrifuge
        removeRecipesByInputs(CENTRIFUGE_RECIPES, Air.getFluid(10000));

    }

    public static void chemistryOverride() {

        CHEMICAL_RECIPES.recipeBuilder().duration(60).EUt(30)
                .input(dust, SodiumHydroxide, 3)
                .circuitMeta(1)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .output(dust, SodiumBisulfate, 7)
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(60).EUt(30)
                .input(dust, PhosphorusPentoxide,14)
                .circuitMeta(2)
                .fluidInputs(Water.getFluid(6000))
                .fluidOutputs(PhosphoricAcid.getFluid(4000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(30)
                .circuitMeta(2)
                .fluidInputs(Ethylene.getFluid(1000))
                .fluidInputs(Benzene.getFluid(1000))
                .fluidOutputs(Styrene.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(2000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30)
                .circuitMeta(1)
                .fluidInputs(Chlorine.getFluid(4000))
                .fluidInputs(Ethane.getFluid(1000))
                .fluidOutputs(VinylChloride.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(3000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(1200).EUt(120)
                .circuitMeta(1)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(Ethylene.getFluid(1000))
                .fluidOutputs(DilutedSulfuricAcid.getFluid(1000))
                .fluidInputs(Ethanol.getFluid(1000))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().duration(240).EUt(VA[MV]).blastFurnaceTemp(2273)
                .circuitMeta(2)
                .input(dust, SiliconDioxide, 3)
                .input(dust, Carbon, 2)
                .output(ingotHot, Silicon)
                .chancedOutput(dust, Ash, 1111, 0)
                .fluidOutputs(CarbonMonoxide.getFluid(2000))
                .buildAndRegister();

        //Severely steam cracked naphtha
        DISTILLATION_RECIPES.recipeBuilder().duration(120).EUt(120)
                .fluidInputs(SeverelySteamCrackedNaphtha.getFluid(1000))
                .fluidOutputs(HeavyFuel.getFluid(25))
                .fluidOutputs(LightFuel.getFluid(50))
                .fluidOutputs(Toluene.getFluid(20))
                .fluidOutputs(Benzene.getFluid(100))
                .fluidOutputs(Butene.getFluid(50))
                .fluidOutputs(Butadiene.getFluid(50))
                .fluidOutputs(Propane.getFluid(15))
                .fluidOutputs(Propene.getFluid(300))
                .fluidOutputs(Ethane.getFluid(65))
                .fluidOutputs(Ethylene.getFluid(500))
                .fluidOutputs(Methane.getFluid(500))
                .fluidOutputs(Cyclopentadiene.getFluid(75))
                .buildAndRegister();

        //Glowstone
        CENTRIFUGE_RECIPES.recipeBuilder().duration(1000).EUt(80)
                .input(dust, Glowstone)
                .output(dust, Redstone)
                .output(dust, PreciousMetal)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder()
                .circuitMeta(1)
                .fluidInputs(Glycerol.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .fluidOutputs(Epichlorohydrin.getFluid(1000))
                .duration(480).EUt(VA[LV]).buildAndRegister();

        //AIR
        CENTRIFUGE_RECIPES.recipeBuilder().EUt(7).duration(1600)
                .circuitMeta(0)
                .fluidInputs(Air.getFluid(10000))
                .fluidOutputs(Nitrogen.getFluid(3900))
                .fluidOutputs(Oxygen.getFluid(1000))
                .buildAndRegister();

    }

    public static void gregtechOverride() {

        //mica stuff
        MIXER_RECIPES.recipeBuilder().duration(400).EUt(8)
                .input(dust, Mica, 3)
                .input(dust, RawRubber, 2)
                .output(dust, MicaPulp, 5)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(400).EUt(8)
                .input(dust, Mica, 3)
                .inputs(STICKY_RESIN.getStackForm())
                .output(dust, MicaPulp, 4)
                .buildAndRegister();

        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(1200).EUt(30)
                .input(dust, Sapphire)
                .input(dust, SiliconDioxide, 3)
                .output(dust, AluminoSilicateWool, 2)
                .buildAndRegister();

        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(1200).EUt(30)
                .input(dust, GreenSapphire)
                .input(dust, SiliconDioxide, 3)
                .output(dust, AluminoSilicateWool, 2)
                .buildAndRegister();

        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(1200).EUt(30)
                .input(dust, Ruby)
                .input(dust, SiliconDioxide, 3)
                .output(dust, AluminoSilicateWool, 2)
                .buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().duration(400).EUt(28)
                .input(dust, MicaPulp, 4)
                .input(dust, Asbestos)
                .outputs(MICA_SHEET.getStackForm(5))
                .buildAndRegister();

        ALLOY_SMELTER_RECIPES.recipeBuilder().duration(400).EUt(30)
                .inputs(MICA_SHEET.getStackForm(5))
                .input(dust, SiliconDioxide, 3)
                .outputs(MICA_INSULATOR_SHEET.getStackForm(8))
                .buildAndRegister();

        BENDER_RECIPES.recipeBuilder().duration(100).EUt(30)
                .inputs(MICA_INSULATOR_SHEET.getStackForm())
                .circuitMeta(1)
                .outputs(MICA_INSULATOR_FOIL.getStackForm(4))
                .buildAndRegister();

        //reservoir hatch
        ASSEMBLER_RECIPES.recipeBuilder()
                .input(EV_INFINITE_WATER_SOURCE)
                .input(FLUID_IMPORT_HATCH[EV])
                .input(ELECTRIC_PUMP_EV)
                .output(RESERVOIR_HATCH)
                .duration(300).EUt(VA[EV]).buildAndRegister();

        //infinite water covers
        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(VA[MV])
                .input(ELECTRIC_PUMP_MV)
                .input(circuit, MarkerMaterials.Tier.MV)
                .inputs(new ItemStack(Items.CAULDRON))
                .output(MV_INFINITE_WATER_SOURCE)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(VA[HV])
                .input(ELECTRIC_PUMP_HV)
                .input(circuit, MarkerMaterials.Tier.HV)
                .input(MV_INFINITE_WATER_SOURCE)
                .output(HV_INFINITE_WATER_SOURCE)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(VA[EV])
                .input(ELECTRIC_PUMP_EV)
                .input(circuit, MarkerMaterials.Tier.EV)
                .input(HV_INFINITE_WATER_SOURCE)
                .output(EV_INFINITE_WATER_SOURCE)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(VA[IV])
                .input(ELECTRIC_PUMP_IV)
                .input(circuit, MarkerMaterials.Tier.IV)
                .input(EV_INFINITE_WATER_SOURCE)
                .output(IV_INFINITE_WATER_SOURCE)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(VA[LuV])
                .input(ELECTRIC_PUMP_LuV)
                .input(circuit, MarkerMaterials.Tier.LuV)
                .input(IV_INFINITE_WATER_SOURCE)
                .output(LuV_INFINITE_WATER_SOURCE)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(VA[ZPM])
                .input(ELECTRIC_PUMP_ZPM)
                .input(circuit, MarkerMaterials.Tier.ZPM)
                .input(LuV_INFINITE_WATER_SOURCE)
                .output(ZPM_INFINITE_WATER_SOURCE)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(VA[UV])
                .input(ELECTRIC_PUMP_UV)
                .input(circuit, MarkerMaterials.Tier.UV)
                .input(ZPM_INFINITE_WATER_SOURCE)
                .output(UV_INFINITE_WATER_SOURCE)
                .buildAndRegister();

        // naquadah alloy
        MIXER_RECIPES.recipeBuilder().duration(400).EUt(VA[IV])
                .input(dust, Naquadah)
                .input(dust, Osmiridium)
                .output(dust, NaquadahAlloy, 2)
                .circuitMeta(2)
                .buildAndRegister();

        //sticky resin extract
        EXTRACTOR_RECIPES.recipeBuilder()
                .input(STICKY_RESIN)
                .circuitMeta(1)
                .output(dust, RawRubber, 3)
                .EUt(2)
                .duration(150)
                .buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder()
                .input(STICKY_RESIN)
                .circuitMeta(2)
                .fluidOutputs(Resin.getFluid(100))
                .EUt(30)
                .duration(150)
                .buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder()
                .input(STICKY_RESIN)
                .circuitMeta(3)
                .output(dust, RawRubber, 3)
                .fluidOutputs(Resin.getFluid(100))
                .EUt(480)
                .duration(150)
                .buildAndRegister();

        //fusion reactor computers
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(FUSION_CASING.getItemVariant(SUPERCONDUCTOR_COIL))
                .input(circuit, MarkerMaterials.Tier.ZPM, 4)
                .input(plateDouble, Plutonium241)
                .input(plateDouble, Osmiridium)
                .input(FIELD_GENERATOR_IV, 2)
                .input(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 64)
                .input(wireGtSingle, LuVSuperconductor, 32)
                .fluidInputs(SolderingAlloy.getFluid(L * 8))
                .fluidInputs(NiobiumTitanium.getFluid(L * 8))
                .outputs(FUSION_REACTOR[0].getStackForm())
                .scannerResearch(b -> b
                        .researchStack(OreDictUnifier.get(wireGtSingle, LuVSuperconductor))
                        .duration(1200)
                        .EUt(VA[IV]))
                .duration(800).EUt(VA[LuV]).buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(FUSION_CASING.getItemVariant(FUSION_COIL))
                .input(circuit, MarkerMaterials.Tier.UV, 4)
                .input(plateDouble, Naquadria)
                .input(plateDouble, Europium)
                .input(FIELD_GENERATOR_LuV, 2)
                .input(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 64)
                .input(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 32)
                .input(wireGtSingle, ZPMSuperconductor, 32)
                .fluidInputs(SolderingAlloy.getFluid(L * 8))
                .fluidInputs(VanadiumGallium.getFluid(L * 8))
                .outputs(FUSION_REACTOR[1].getStackForm())
                .stationResearch(b -> b
                        .researchStack(FUSION_REACTOR[0].getStackForm())
                        .CWUt(16)
                        .EUt(VA[ZPM]))
                .duration(1000).EUt(61440).buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(FUSION_CASING.getItemVariant(FUSION_COIL))
                .input(circuit, MarkerMaterials.Tier.UHV, 4)
                .input(QUANTUM_STAR)
                .input(plateDouble, Americium)
                .input(FIELD_GENERATOR_ZPM, 2)
                .input(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 64)
                .input(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 64)
                .input(wireGtSingle, UVSuperconductor, 32)
                .fluidInputs(SolderingAlloy.getFluid(L * 8))
                .fluidInputs(YttriumBariumCuprate.getFluid(L * 8))
                .outputs(FUSION_REACTOR[2].getStackForm())
                .stationResearch(b -> b
                        .researchStack(FUSION_REACTOR[1].getStackForm())
                        .CWUt(96)
                        .EUt(VA[UV]))
                .duration(1000).EUt(VA[ZPM]).buildAndRegister();

        //Lapotronic energy orb
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(512).EUt(1024)
                .input(EXTREME_CIRCUIT_BOARD)
                .input(POWER_INTEGRATED_CIRCUIT, 4)
                .input(ENGRAVED_LAPOTRON_CHIP, 24)
                .input(NANO_CENTRAL_PROCESSING_UNIT, 2)
                .input(wireFine, Platinum, 16)
                .input(plate, Platinum, 8)
                .output(ENERGY_LAPOTRONIC_ORB)
                .solderMultiplier(2)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

        //Nand chip
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().EUt(VA[MV]).duration(300)
                .input(GOOD_CIRCUIT_BOARD)
                .input(SIMPLE_SYSTEM_ON_CHIP)
                .input(bolt, RedAlloy, 2)
                .input(wireFine, Tin, 2)
                .output(NAND_CHIP_ULV, 8)
                .buildAndRegister();

        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().EUt(VA[MV]).duration(300)
                .input(PLASTIC_CIRCUIT_BOARD)
                .input(SIMPLE_SYSTEM_ON_CHIP)
                .input(bolt, RedAlloy, 2)
                .input(wireFine, Tin, 2)
                .output(NAND_CHIP_ULV, 12)
                .buildAndRegister();

        //Wetware life support
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(1200).EUt(VA[LuV])
                .input(MULTILAYER_FIBER_BOARD, 16)
                .input(PETRI_DISH)
                .input(ELECTRIC_PUMP_LuV)
                .input(SENSOR_IV)
                .input(circuit, MarkerMaterials.Tier.IV)
                .input(foil, NiobiumTitanium, 16)
                .fluidInputs(SterileGrowthMedium.getFluid(4000))
                .output(WETWARE_BOARD, 16)
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

        //advanced quarktech
        ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(1000).EUt(GTValues.VA[GTValues.LuV])
                .inputNBT(((ArmorMetaItem<?>) QUANTUM_CHESTPLATE.getStackForm().getItem())
                        .getItem(QUANTUM_CHESTPLATE.getStackForm()), NBTMatcher.ANY, NBTCondition.ANY)
                .inputs(HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(2))
                .input(wireFine, NiobiumTitanium, 64)
                .input(wireGtQuadruple, Osmium, 6)
                .input(plateDouble, Iridium, 4)
                .inputs(GRAVITATION_ENGINE.getStackForm(2))
                .input(circuit, MarkerMaterials.Tier.ZPM)
                .input(plateDense, RhodiumPlatedPalladium, 2)
                .inputNBT(ENERGY_LAPOTRONIC_ORB_CLUSTER, NBTMatcher.ANY, NBTCondition.ANY)
                .inputs(FIELD_GENERATOR_LuV.getStackForm(2))
                .inputs(ELECTRIC_MOTOR_LuV.getStackForm(2))
                .input(screw, HSSS, 8)
                .outputs(QUANTUM_CHESTPLATE_ADVANCED.getStackForm())
                .scannerResearch(GRAVITATION_ENGINE.getStackForm())
                .buildAndRegister();

        // Lapotronic Energy Cluster
        ASSEMBLY_LINE_RECIPES.recipeBuilder().EUt(80000).duration(1000)
                .input(EXTREME_CIRCUIT_BOARD)
                .input(plate, Europium, 8)
                .input(circuit, MarkerMaterials.Tier.LuV, 8)
                .inputNBT(ENERGY_LAPOTRONIC_ORB, NBTMatcher.ANY, NBTCondition.ANY)
                .input(FIELD_GENERATOR_IV)
                .input(HIGH_POWER_INTEGRATED_CIRCUIT, 16)
                .input(SMD_DIODE_CRYSTAL, 8)
                .input(SMD_CAPACITOR_CRYSTAL, 8)
                .input(SMD_RESISTOR_CRYSTAL, 8)
                .input(SMD_TRANSISTOR_CRYSTAL, 8)
                .input(wireFine, Platinum, 64)
                .input(bolt, Naquadah, 16)
                .fluidInputs(SolderingAlloy.getFluid(L * 5))
                .output(ENERGY_LAPOTRONIC_ORB_CLUSTER)
                .scannerResearch(ENERGY_LAPOTRONIC_ORB.getStackForm())
                .buildAndRegister();

        // Energy Module
        ASSEMBLY_LINE_RECIPES.recipeBuilder().EUt(100000).duration(1200)
                .input(ELITE_CIRCUIT_BOARD)
                .input(plateDouble, Europium, 8)
                .input(circuit, MarkerMaterials.Tier.ZPM, 4)
                .inputNBT(ENERGY_LAPOTRONIC_ORB_CLUSTER, NBTMatcher.ANY, NBTCondition.ANY)
                .input(FIELD_GENERATOR_LuV)
                .input(HIGH_POWER_INTEGRATED_CIRCUIT, 32)
                .input(SMD_DIODE_WETWARE, 12)
                .input(SMD_CAPACITOR_WETWARE, 12)
                .input(SMD_RESISTOR_WETWARE, 12)
                .input(SMD_TRANSISTOR_WETWARE, 12)
                .input(wireFine, Ruridit, 64)
                .input(bolt, Naquadria, 16)
                .fluidInputs(SolderingAlloy.getFluid(L * 10))
                .output(ENERGY_MODULE)
                .stationResearch(b -> b
                        .researchStack(ENERGY_LAPOTRONIC_ORB_CLUSTER.getStackForm())
                        .CWUt(16))
                .buildAndRegister();

        // Energy Cluster
        ASSEMBLY_LINE_RECIPES.recipeBuilder().EUt(200000).duration(1400)
                .input(WETWARE_CIRCUIT_BOARD)
                .input(plate, Americium, 16)
                .input(WETWARE_SUPER_COMPUTER_UV, 4)
                .input(ENERGY_MODULE)
                .input(FIELD_GENERATOR_ZPM)
                .input(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 32)
                .input(SMD_DIODE_BIOWARE, 16)
                .input(SMD_CAPACITOR_BIOWARE, 16)
                .input(SMD_RESISTOR_BIOWARE, 16)
                .input(SMD_TRANSISTOR_BIOWARE, 16)
                .input(wireFine, Osmiridium, 64)
                .input(bolt, Naquadria, 16)
                .fluidInputs(SolderingAlloy.getFluid(L * 20))
                .fluidInputs(Polybenzimidazole.getFluid(L * 4))
                .output(ENERGY_CLUSTER)
                .stationResearch(b -> b
                        .researchStack(ENERGY_MODULE.getStackForm())
                        .CWUt(96)
                        .EUt(VA[ZPM]))
                .buildAndRegister();

        // Ultimate Battery
        ASSEMBLY_LINE_RECIPES.recipeBuilder().EUt(600000).duration(2000)
                .input(plateDouble, Darmstadtium, 16)
                .input(circuit, MarkerMaterials.Tier.UHV, 4)
                .input(ENERGY_CLUSTER, 16)
                .input(FIELD_GENERATOR_UV, 4)
                .input(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT_WAFER, 64)
                .input(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT_WAFER, 64)
                .input(SMD_DIODE_OPTICAL, 64)
                .input(SMD_CAPACITOR_OPTICAL, 64)
                .input(SMD_RESISTOR_OPTICAL, 64)
                .input(SMD_TRANSISTOR_OPTICAL, 64)
                .input(wireGtSingle, UHVSuperconductor, 64)
                .input(bolt, Neutronium, 64)
                .fluidInputs(SolderingAlloy.getFluid(L * 40))
                .fluidInputs(Polybenzimidazole.getFluid(2304))
                .fluidInputs(Naquadria.getFluid(L * 18))
                .output(ULTIMATE_BATTERY)
                .stationResearch(b -> b
                        .researchStack(ENERGY_CLUSTER.getStackForm())
                        .CWUt(144)
                        .EUt(VA[UHV]))
                .buildAndRegister();

        //Advanced data access hatch
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(ITEM_IMPORT_BUS[LuV])
                .inputNBT(TOOL_DATA_ORB, 4, NBTMatcher.ANY, NBTCondition.ANY)
                .input(circuit, MarkerMaterials.Tier.ZPM, 4)
                .output(ADVANCED_DATA_ACCESS_HATCH)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .fluidInputs(Polybenzimidazole.getFluid(L * 4))
                .stationResearch(b -> b.researchStack(DATA_BANK.getStackForm()).CWUt(4))
                .duration(400).EUt(6000).buildAndRegister();

        //DATA BANK
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(COMPUTER_CASING.getItemVariant(BlockComputerCasing.CasingType.COMPUTER_CASING))
                .input(circuit, MarkerMaterials.Tier.LuV, 8)
                .inputNBT(TOOL_DATA_ORB, NBTMatcher.ANY, NBTCondition.ANY)
                .input(wireFine, Cobalt, 64)
                .input(wireFine, Copper, 64)
                .input(OPTICAL_PIPES[0], 4)
                .input(wireGtDouble, LuVSuperconductor, 16)
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .fluidInputs(Lubricant.getFluid(500))
                .output(DATA_BANK)
                .scannerResearch(b -> b
                        .researchStack(DATA_ACCESS_HATCH.getStackForm())
                        .duration(2400)
                        .EUt(VA[EV]))
                .duration(1200).EUt(6000).buildAndRegister();

        //Research station
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(DATA_BANK)
                .input(SENSOR_LuV, 8)
                .input(circuit, MarkerMaterials.Tier.ZPM, 8)
                .input(FIELD_GENERATOR_LuV, 2)
                .input(ELECTRIC_MOTOR_ZPM, 2)
                .input(wireGtDouble, ZPMSuperconductor, 32)
                .input(foil, Rutherfordium, 32)
                .input(OPTICAL_PIPES[0], 16)
                .fluidInputs(SolderingAlloy.getFluid(L * 8))
                .fluidInputs(VanadiumGallium.getFluid(L * 8))
                .output(RESEARCH_STATION)
                .scannerResearch(b -> b
                        .researchStack(SCANNER[LuV].getStackForm())
                        .duration(2400)
                        .EUt(VA[IV]))
                .duration(1200).EUt(100000).buildAndRegister();

        //object holder
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(ITEM_IMPORT_BUS[ZPM])
                .input(EMITTER_LuV, 8)
                .input(circuit, MarkerMaterials.Tier.ZPM)
                .input(ROBOT_ARM_ZPM, 2)
                .input(ELECTRIC_MOTOR_ZPM, 2)
                .input(wireGtDouble, ZPMSuperconductor, 16)
                .input(OPTICAL_PIPES[0], 2)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .fluidInputs(Polybenzimidazole.getFluid(L * 2))
                .output(OBJECT_HOLDER)
                .scannerResearch(b -> b
                        .researchStack(ITEM_IMPORT_BUS[ZPM].getStackForm())
                        .duration(2400)
                        .EUt(VA[IV]))
                .duration(1200).EUt(100000).buildAndRegister();

        //network switch
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(COMPUTER_CASING.getItemVariant(BlockComputerCasing.CasingType.COMPUTER_CASING))
                .input(EMITTER_ZPM, 4)
                .input(SENSOR_ZPM, 4)
                .input(circuit, MarkerMaterials.Tier.UV, 4)
                .input(wireGtDouble, UVSuperconductor, 32)
                .input(foil, Tritanium, 64)
                .input(foil, Tritanium, 64)
                .input(OPTICAL_PIPES[0], 8)
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .fluidInputs(Polybenzimidazole.getFluid(L * 4))
                .output(NETWORK_SWITCH)
                .stationResearch(b -> b
                        .researchStack(new ItemStack(OPTICAL_PIPES[0]))
                        .CWUt(32)
                        .EUt(VA[ZPM]))
                .duration(1200).EUt(100000).buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(DATA_BANK)
                .input(circuit, MarkerMaterials.Tier.ZPM, 4)
                .input(FIELD_GENERATOR_LuV, 8)
                .inputNBT(TOOL_DATA_ORB, NBTMatcher.ANY, NBTCondition.ANY)
                .input(COVER_SCREEN)
                .input(wireGtDouble, ZPMSuperconductor, 64)
                .input(OPTICAL_PIPES[0], 16)
                .fluidInputs(SolderingAlloy.getFluid(L * 8))
                .fluidInputs(VanadiumGallium.getFluid(L * 8))
                .fluidInputs(PCBCoolant.getFluid(4000))
                .output(HIGH_PERFORMANCE_COMPUTING_ARRAY)
                .scannerResearch(b -> b
                        .researchStack(COVER_SCREEN.getStackForm())
                        .duration(2400)
                        .EUt(VA[IV]))
                .duration(1200).EUt(100000).buildAndRegister();

        //energy output hatches luv-uv
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(HULL[LuV])
                .input(spring, NiobiumTitanium, 4)
                .input(HIGH_POWER_INTEGRATED_CIRCUIT, 2)
                .input(circuit, MarkerMaterials.Tier.LuV)
                .input(VOLTAGE_COIL_LuV, 2)
                .fluidInputs(SodiumPotassium.getFluid(6000))
                .fluidInputs(SolderingAlloy.getFluid(720))
                .output(ENERGY_OUTPUT_HATCH[LuV])
                .scannerResearch(b -> b
                        .researchStack(ENERGY_OUTPUT_HATCH[IV].getStackForm())
                        .EUt(VA[EV]))
                .duration(400).EUt(VA[LuV]).buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(HULL[ZPM])
                .input(spring, VanadiumGallium, 4)
                .input(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 2)
                .input(circuit, MarkerMaterials.Tier.ZPM)
                .input(VOLTAGE_COIL_ZPM, 2)
                .fluidInputs(SodiumPotassium.getFluid(8000))
                .fluidInputs(SolderingAlloy.getFluid(1440))
                .output(ENERGY_OUTPUT_HATCH[ZPM])
                .stationResearch(b -> b
                        .researchStack(ENERGY_OUTPUT_HATCH[LuV].getStackForm())
                        .CWUt(8))
                .duration(600).EUt(VA[ZPM]).buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(HULL[UV])
                .input(spring, Duranium, 4)
                .input(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 4)
                .input(circuit, MarkerMaterials.Tier.UV)
                .input(VOLTAGE_COIL_UV, 2)
                .fluidInputs(SodiumPotassium.getFluid(10000))
                .fluidInputs(SolderingAlloy.getFluid(2880))
                .output(ENERGY_OUTPUT_HATCH[UV])
                .stationResearch(b -> b
                        .researchStack(ENERGY_OUTPUT_HATCH[ZPM].getStackForm())
                        .CWUt(64)
                        .EUt(VA[ZPM]))
                .duration(800).EUt(VA[UV]).buildAndRegister();

        //energy input hatches luv-uv
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(HULL[LuV])
                .input(cableGtSingle, NiobiumTitanium, 4)
                .input(HIGH_POWER_INTEGRATED_CIRCUIT, 2)
                .input(circuit, MarkerMaterials.Tier.LuV)
                .input(VOLTAGE_COIL_LuV, 2)
                .fluidInputs(SodiumPotassium.getFluid(6000))
                .fluidInputs(SolderingAlloy.getFluid(720))
                .output(ENERGY_INPUT_HATCH[LuV])
                .scannerResearch(b -> b
                        .researchStack(ENERGY_INPUT_HATCH[IV].getStackForm())
                        .EUt(VA[EV]))
                .duration(400).EUt(VA[LuV]).buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(HULL[ZPM])
                .input(cableGtSingle, VanadiumGallium, 4)
                .input(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 2)
                .input(circuit, MarkerMaterials.Tier.ZPM)
                .input(VOLTAGE_COIL_ZPM, 2)
                .fluidInputs(SodiumPotassium.getFluid(8000))
                .fluidInputs(SolderingAlloy.getFluid(1440))
                .output(ENERGY_INPUT_HATCH[ZPM])
                .stationResearch(b -> b
                        .researchStack(ENERGY_INPUT_HATCH[LuV].getStackForm())
                        .CWUt(8))
                .duration(600).EUt(VA[ZPM]).buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(HULL[UV])
                .input(cableGtSingle, Duranium, 4)
                .input(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 4)
                .input(circuit, MarkerMaterials.Tier.UV)
                .input(VOLTAGE_COIL_UV, 2)
                .fluidInputs(SodiumPotassium.getFluid(10000))
                .fluidInputs(SolderingAlloy.getFluid(2880))
                .output(ENERGY_INPUT_HATCH[UV])
                .stationResearch(b -> b
                        .researchStack(ENERGY_INPUT_HATCH[ZPM].getStackForm())
                        .CWUt(64)
                        .EUt(VA[ZPM]))
                .duration(800).EUt(VA[UV]).buildAndRegister();


        //indalloy_140
        ALLOY_BLAST_RECIPES.recipeBuilder().duration(12000).EUt(7680).blastFurnaceTemp(9000)
                .input(dust, Bismuth,47)
                .input(dust, Lead, 25)
                .input(dust, Tin, 13)
                .input(dust, Cadmium, 10)
                .input(dust, Indium, 5)
                .circuitMeta(20)
                .fluidOutputs(Indalloy140.getFluid(14400))
                .buildAndRegister();

        // Data Stick
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(ADVANCED_CIRCUIT_BOARD)
                .input(circuit, MarkerMaterials.Tier.HV, 2)
                .input(RANDOM_ACCESS_MEMORY, 4)
                .input(NOR_MEMORY_CHIP, 16)
                .input(NAND_MEMORY_CHIP, 32)
                .input(wireFine, HVSuperconductor, 32)
                .output(TOOL_DATA_STICK)
                .solderMultiplier(2)
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(400).EUt(1200).buildAndRegister();

        // Data Orb
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(EXTREME_CIRCUIT_BOARD)
                .input(circuit, MarkerMaterials.Tier.IV, 2)
                .input(RANDOM_ACCESS_MEMORY, 8)
                .input(NOR_MEMORY_CHIP, 32)
                .input(NAND_MEMORY_CHIP, 48)
                .input(wireFine, IVSuperconductor, 32)
                .output(TOOL_DATA_ORB)
                .solderMultiplier(2)
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(400).EUt(9600).buildAndRegister();

        // Data Module
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(WETWARE_CIRCUIT_BOARD)
                .input(circuit, MarkerMaterials.Tier.ZPM, 2)
                .input(RANDOM_ACCESS_MEMORY, 32)
                .input(NOR_MEMORY_CHIP, 64)
                .input(NAND_MEMORY_CHIP, 64)
                .input(wireFine, ZPMSuperconductor, 32)
                .output(TOOL_DATA_MODULE)
                .solderMultiplier(2)
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .duration(400).EUt(38400).buildAndRegister();


        //TODO: make these require research, but a bugfix needs to happen, since assigning these to one another will cause other research recipes to fail.

        // Data Module Cluster
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(NEURO_PROCESSOR.getStackForm(4))
                .input(circuit, MarkerMaterials.Tier.UHV, 2)
                .input(RANDOM_ACCESS_MEMORY, 64)
                .input(RANDOM_ACCESS_MEMORY, 64)
                .input(NOR_MEMORY_CHIP, 64)
                .input(NAND_MEMORY_CHIP, 64)
                .input(wireFine, UHVSuperconductor, 32)
                .fluidInputs(Indalloy140.getFluid(L*8))
                .cleanroom(CleanroomType.STERILE_CLEANROOM)
                .outputs(TOOL_DATA_MODULE_CLUSTER.getStackForm())
                .duration(400).EUt(614400).buildAndRegister();

        //Ultimate Data Module
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(EXOTIC_PROCESSING_CORE.getStackForm(16))
                .input(circuit, MarkerMaterials.Tier.UIV, 4)
                .input(ARAM, 64)
                .input(NOR_MEMORY_CHIP, 64)
                .input(NAND_MEMORY_CHIP, 64)
                .input(wireFine, UIVSuperconductor, 32)
                .fluidInputs(Indalloy140.getFluid(L*16))
                .outputs(TOOL_DATA_ULTIMATE.getStackForm())
                .duration(400).EUt(9830400).buildAndRegister();

        //Supracausal Data Module
        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .inputs(COSMIC_PROCESSING_CORE.getStackForm(64))
                .input(circuit, MarkerMaterials.Tier.OpV, 8)
                .input(ARAM, 64)
                .input(ARAM, 64)
                .input(NOR_MEMORY_CHIP, 64)
                .input(NAND_MEMORY_CHIP, 64)
                .input(wireFine, OpVSuperconductor, 32)
                .fluidInputs(Indalloy140.getFluid(L*32))
                .outputs(TOOL_DATA_SUPRACAUSAL.getStackForm())
                .duration(400).EUt(157286400).buildAndRegister();

        //Vacuum freezer
        ModHandler.addShapedRecipe("gcyl_vacuum_freezer", MetaTileEntities.VACUUM_FREEZER.getStackForm(),
                "PPP", "CMC", "WCW",
                'M', MetaBlocks.METAL_CASING.getItemVariant(ALUMINIUM_FROSTPROOF),
                'P', MetaItems.ELECTRIC_PUMP_HV,
                'C', new UnificationEntry(circuit, MarkerMaterials.Tier.HV),
                'W', new UnificationEntry(cableGtSingle, Gold));

        //iv motor
        ModHandler.addShapedRecipe(true, "electric_motor_iv", ELECTRIC_MOTOR_IV.getStackForm(), "CWR", "WMW", "RWC",
                'C', new UnificationEntry(cableGtDouble, Tungsten), 'W', new UnificationEntry(wireGtQuadruple, BrightSteel),
                'R', new UnificationEntry(stick, TungstenSteel), 'M', new UnificationEntry(stick, NeodymiumMagnetic));
        ASSEMBLER_RECIPES.recipeBuilder().duration(100).EUt(30)
                        .input(cableGtDouble, Tungsten, 2)
                                .input(stick, TungstenSteel, 2)
                                        .input(stick, NeodymiumMagnetic)
                                                .input(wireGtQuadruple, BrightSteel,4)
                .outputs(ELECTRIC_MOTOR_IV.getStackForm())
                                                        .buildAndRegister();

        //Superconductor Coil Block //TODO change these to use liquid version of gas instead
        ASSEMBLER_RECIPES.recipeBuilder().EUt(GTValues.VA[GTValues.LuV]).duration(100)
                .input(wireGtSingle, LuVSuperconductor, 16)
                .input(plate, Osmiridium, 16)
                .fluidInputs(Helium.getFluid( 8000))
                .cleanroom(CleanroomType.CLEANROOM)
                .outputs(FUSION_CASING.getItemVariant(SUPERCONDUCTOR_COIL))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(GTValues.VA[GTValues.ZPM]).duration(100)
                .input(wireGtSingle, ZPMSuperconductor, 8)
                .input(plate, Rutherfordium, 8)
                .fluidInputs(Argon.getFluid(4000))
                .cleanroom(CleanroomType.CLEANROOM)
                .outputs(FUSION_CASING.getItemVariant(SUPERCONDUCTOR_COIL))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(GTValues.VA[GTValues.UV]).duration(100)
                .input(wireGtSingle, UVSuperconductor, 4)
                .input(plate, Dubnium, 4)
                .fluidInputs(Krypton.getFluid(2000))
                .cleanroom(CleanroomType.CLEANROOM)
                .outputs(FUSION_CASING.getItemVariant(SUPERCONDUCTOR_COIL))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(GTValues.VA[GTValues.UHV]).duration(100)
                .input(wireGtSingle, UHVSuperconductor, 2)
                .input(plate, Tennessine, 2)
                .fluidInputs(Radon.getFluid(1000))
                .cleanroom(CleanroomType.CLEANROOM)
                .outputs(FUSION_CASING.getItemVariant(SUPERCONDUCTOR_COIL))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(GTValues.VA[GTValues.UEV]).duration(100)
                .input(wireGtSingle, UEVSuperconductor, 1)
                .input(plate, Bohrium, 1)
                .fluidInputs(Xenon.getFluid( 500))
                .cleanroom(CleanroomType.CLEANROOM)
                .outputs(FUSION_CASING.getItemVariant(SUPERCONDUCTOR_COIL))
                .buildAndRegister();

        //Fusion casings //TODO: MIGHT BE TOO EXPENSIVE, change output count to ConfigHolder.recipes.casingsPerCraft
        ASSEMBLY_LINE_RECIPES.recipeBuilder().EUt(GTValues.VA[GTValues.LuV]).duration(100)
                .inputs(HULL[GTValues.LuV].getStackForm())
                .inputs(FUSION_CASING.getItemVariant(SUPERCONDUCTOR_COIL))
                .inputs(NEUTRON_REFLECTOR.getStackForm())
                .inputs(FIELD_GENERATOR_LuV.getStackForm())
                .inputs(VOLTAGE_COIL_LuV.getStackForm(2))
                .input(plate, Osmiridium, 2)
                .fluidInputs(Polybenzimidazole.getFluid(L * 4))
                .fluidInputs(SolderingAlloy.getFluid(L * 2))
                .outputs(FUSION_CASING.getItemVariant(BlockFusionCasing.CasingType.FUSION_CASING, ConfigHolder.recipes.casingsPerCraft))
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().EUt(GTValues.VA[GTValues.ZPM]).duration(100)
                .inputs(HULL[GTValues.ZPM].getStackForm())
                .inputs(FUSION_CASING.getItemVariant(FUSION_COIL))
                .inputs(FIELD_GENERATOR_ZPM.getStackForm())
                .inputs(VOLTAGE_COIL_ZPM.getStackForm(2))
                .input(plate, Rutherfordium, 2)
                .fluidInputs(Polybenzimidazole.getFluid(L * 8))
                .fluidInputs(SolderingAlloy.getFluid(L * 4))
                .outputs(FUSION_CASING.getItemVariant(BlockFusionCasing.CasingType.FUSION_CASING_MK2, ConfigHolder.recipes.casingsPerCraft))
                .cleanroom(CleanroomType.CLEANROOM)
                .buildAndRegister();

        ASSEMBLY_LINE_RECIPES.recipeBuilder().EUt(GTValues.VA[GTValues.UV]).duration(100)
                .inputs(HULL[GTValues.UV].getStackForm())
                .inputs(FUSION_CASING.getItemVariant(FUSION_COIL))
                .inputs(FIELD_GENERATOR_UV.getStackForm(2))
                .inputs(VOLTAGE_COIL_UV.getStackForm(2))
                .input(plate, Tritanium, 2)
                .fluidInputs(Polybenzimidazole.getFluid(L * 16))
                .fluidInputs(Indalloy140.getFluid(L * 8))
                .outputs(FUSION_CASING.getItemVariant(BlockFusionCasing.CasingType.FUSION_CASING_MK3, ConfigHolder.recipes.casingsPerCraft))
                .cleanroom(CleanroomType.CLEANROOM)
                .stationResearch(b->b
                        .researchStack(FUSION_CASING.getItemVariant(BlockFusionCasing.CasingType.FUSION_CASING_MK2))
                        .EUt(GTValues.VA[GTValues.UV])
                        .CWUt(32))
                .buildAndRegister();

        //large circuit assembler
        ModHandler.addShapedRecipe("gcyl_circuit_assembler", GCYMMetaTileEntities.LARGE_CIRCUIT_ASSEMBLER.getStackForm(),
                "ARE", "CHC", "ARA",
                'A', GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING.getItemVariant(BlockLargeMultiblockCasing.CasingType.ASSEMBLING_CASING),
                'R', ROBOT_ARM_LuV,
                'C', CONVEYOR_MODULE_LuV,
                'H', HULL[6].getStackForm(),
                'E', EMITTER_LuV);

        //large scale assembler casing
        ASSEMBLER_RECIPES.recipeBuilder().EUt(GTValues.VA[IV]).duration(200)
                .circuitMeta(6)
                .input(plate, Stellite100, 12)
                .input(frameGt, Tungsten, 2)
                .inputs(MULTIBLOCK_CASING.getItemVariant(BlockMultiblockCasing.MultiblockCasingType.ASSEMBLY_CONTROL))
                .fluidInputs(HastelloyN.getFluid(L * 8))
                .outputs(GCYMMetaBlocks.LARGE_MULTIBLOCK_CASING.getItemVariant(BlockLargeMultiblockCasing.CasingType.ASSEMBLING_CASING, ConfigHolder.recipes.casingsPerCraft))
                .buildAndRegister();

        //distillation tower
        ModHandler.addShapedRecipe(true, "gcyl_distillation_tower", MetaTileEntities.DISTILLATION_TOWER.getStackForm(),
                "CBC", "FMF", "CBC", 'M', MetaTileEntities.HULL[GTValues.EV].getStackForm(), 'B',
                new UnificationEntry(OrePrefix.pipeLargeFluid, Materials.StainlessSteel), 'C',
                new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.EV), 'F', MetaItems.ELECTRIC_PUMP_EV);

        //advanced computer casing
        ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(COMPUTER_CASING.getItemVariant(BlockComputerCasing.CasingType.COMPUTER_CASING))
                .input(circuit, MarkerMaterials.Tier.ZPM)
                .input(wireFine, Cobalt, 64)
                .input(wireFine, Electrum, 64)
                .input(wireGtSingle, LuVSuperconductor, 4)
                .outputs(COMPUTER_CASING.getItemVariant(BlockComputerCasing.CasingType.ADVANCED_COMPUTER_CASING))
                .duration(200).EUt(VA[LuV]).buildAndRegister();

        //computer heat vent
        ASSEMBLER_RECIPES.recipeBuilder()
                .input(frameGt, StainlessSteel)
                .input(ELECTRIC_MOTOR_IV, 2)
                .input(rotor, StainlessSteel, 2)
                .input(pipeTinyFluid, StainlessSteel, 16)
                .input(plate, Copper, 16)
                .input(wireGtSingle, IVSuperconductor)
                .outputs(COMPUTER_CASING.getItemVariant(BlockComputerCasing.CasingType.COMPUTER_HEAT_VENT,
                        ConfigHolder.recipes.casingsPerCraft))
                .duration(100).EUt(VA[EV]).buildAndRegister();

        //active transformer
        ASSEMBLER_RECIPES.recipeBuilder()
                .input(POWER_TRANSFORMER[LuV])
                .input(circuit, MarkerMaterials.Tier.LuV, 2)
                .input(wireGtSingle, LuVSuperconductor, 8)
                .input(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 2)
                .fluidInputs(PCBCoolant.getFluid(1000))
                .output(ACTIVE_TRANSFORMER)
                .duration(300).EUt(VA[LuV]).buildAndRegister();

        //bulk blast chiller
        getAssLineResearchBuilder(GTValues.UV, 2400, CRYOGENIC_FREEZER.getStackForm(), false, false)
                .outputs(MEGA_VACUUM_FREEZER.getStackForm())
                .inputs(CRYOGENIC_FREEZER.getStackForm(64))
                .input(pipeSmallFluid, Zeron100, 64)
                .input(foil, Rutherfordium, 64)
                .input(wireFine, UVSuperconductor, 64)
                .input(gearSmall, Duranium, 32)
                .input(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 32)
                .input(gear, AbyssalAlloy, 16)
                .input(plateDense, IncoloyMA956, 8)
                .inputs(FIELD_GENERATOR_UV.getStackForm(2))
                .input(circuit, MarkerMaterials.Tier.UHV)
                .input(circuit, MarkerMaterials.Tier.UHV)
                .input(circuit, MarkerMaterials.Tier.UHV)
                .input(circuit, MarkerMaterials.Tier.UHV)
                .fluidInputs(Indalloy140.getFluid(9216))
                .fluidInputs(Cryotheum.getFluid(32000))
                .fluidInputs(Polybenzimidazole.getFluid(9216))
                .buildAndRegister();


        //rotary hearth furnace
        getAssLineResearchBuilder(GTValues.UV, 2400, VOLCANUS.getStackForm(), false, false)
                .outputs(MEGA_BLAST_FURNACE.getStackForm())
                .inputs(VOLCANUS.getStackForm(64))
                .input(wireFine, UVSuperconductor, 64)
                .input(foil, Dubnium, 64)
                .input(bolt, Rutherfordium, 64)
                .input(gearSmall, Seaborgium, 32)
                .input(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 32)
                .input(gear, TitanSteel, 16)
                .input(plateDense, HastelloyN, 8)
                .inputs(FIELD_GENERATOR_UV.getStackForm(2))
                .input(circuit, MarkerMaterials.Tier.UHV)
                .input(circuit, MarkerMaterials.Tier.UHV)
                .input(circuit, MarkerMaterials.Tier.UHV)
                .input(circuit, MarkerMaterials.Tier.UHV)
                .fluidInputs(Indalloy140.getFluid(9216))
                .fluidInputs(Pyrotheum.getFluid(32000))
                .fluidInputs(Polybenzimidazole.getFluid(9216))
                .buildAndRegister();

        //assembly line
        ModHandler.addShapedRecipe(true, "assembly_line", MetaTileEntities.ASSEMBLY_LINE.getStackForm(), "CRC", "SAS",
                "CRC", 'A', MetaTileEntities.HULL[GTValues.IV].getStackForm(), 'R', MetaItems.ROBOT_ARM_IV, 'C',
                MetaBlocks.MULTIBLOCK_CASING
                        .getItemVariant(BlockMultiblockCasing.MultiblockCasingType.ASSEMBLY_CONTROL),
                'S', new UnificationEntry(OrePrefix.circuit, MarkerMaterials.Tier.LuV));

    }

    private static void waferRecipeFixes() {
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(900).EUt(VA[MV]).input(SILICON_WAFER)
                .notConsumable(lens, Ruby).output(INTEGRATED_LOGIC_CIRCUIT_WAFER).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(500).EUt(VA[HV]).input(PHOSPHORUS_WAFER)
                .notConsumable(lens, Ruby).output(INTEGRATED_LOGIC_CIRCUIT_WAFER, 4)
                .cleanroom(CleanroomType.CLEANROOM).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(200).EUt(VA[EV]).input(NAQUADAH_WAFER)
                .notConsumable(lens, Ruby).output(INTEGRATED_LOGIC_CIRCUIT_WAFER, 8)
                .cleanroom(CleanroomType.CLEANROOM).buildAndRegister();


        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(900).EUt(VA[MV]).input(SILICON_WAFER)
                .notConsumable(lens, Emerald).output(RANDOM_ACCESS_MEMORY_WAFER).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(500).EUt(VA[HV]).input(PHOSPHORUS_WAFER)
                .notConsumable(lens, Emerald).output(RANDOM_ACCESS_MEMORY_WAFER, 4)
                .cleanroom(CleanroomType.CLEANROOM).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(200).EUt(VA[EV]).input(NAQUADAH_WAFER)
                .notConsumable(lens, Emerald).output(RANDOM_ACCESS_MEMORY_WAFER, 8)
                .cleanroom(CleanroomType.CLEANROOM).buildAndRegister();


        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(900).EUt(VA[MV]).input(SILICON_WAFER)
                .notConsumable(lens, Diamond).output(CENTRAL_PROCESSING_UNIT_WAFER).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(500).EUt(VA[HV]).input(PHOSPHORUS_WAFER)
                .notConsumable(lens, Diamond).output(CENTRAL_PROCESSING_UNIT_WAFER, 4)
                .cleanroom(CleanroomType.CLEANROOM).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(200).EUt(VA[EV]).input(NAQUADAH_WAFER)
                .notConsumable(lens, Diamond).output(CENTRAL_PROCESSING_UNIT_WAFER, 8)
                .cleanroom(CleanroomType.CLEANROOM).buildAndRegister();


        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(900).EUt(VA[MV]).input(SILICON_WAFER)
                .notConsumable(lens, Sapphire).output(ULTRA_LOW_POWER_INTEGRATED_CIRCUIT_WAFER)
                .buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(500).EUt(VA[HV]).input(PHOSPHORUS_WAFER)
                .notConsumable(lens, Sapphire).output(ULTRA_LOW_POWER_INTEGRATED_CIRCUIT_WAFER, 4)
                .cleanroom(CleanroomType.CLEANROOM).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(200).EUt(VA[EV]).input(NAQUADAH_WAFER)
                .notConsumable(lens, Sapphire).output(ULTRA_LOW_POWER_INTEGRATED_CIRCUIT_WAFER, 8)
                .cleanroom(CleanroomType.CLEANROOM).buildAndRegister();


        /*

        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(900).EUt(VA[MV]).input(SILICON_WAFER)
                .notConsumable(craftingLens, MarkerMaterials.Color.Orange).output(LOW_POWER_INTEGRATED_CIRCUIT_WAFER)
                .buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(500).EUt(VA[HV]).input(PHOSPHORUS_WAFER)
                .notConsumable(craftingLens, MarkerMaterials.Color.Orange).output(LOW_POWER_INTEGRATED_CIRCUIT_WAFER, 4)
                .cleanroom(CleanroomType.CLEANROOM).buildAndRegister();
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(200).EUt(VA[EV]).input(NAQUADAH_WAFER)
                .notConsumable(craftingLens, MarkerMaterials.Color.Orange).output(LOW_POWER_INTEGRATED_CIRCUIT_WAFER, 8)
                .cleanroom(CleanroomType.CLEANROOM).buildAndRegister();

         */


    }
}
