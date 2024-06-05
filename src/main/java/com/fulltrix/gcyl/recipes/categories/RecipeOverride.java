package com.fulltrix.gcyl.recipes.categories;

import com.fulltrix.gcyl.recipes.categories.circuits.MagnetoRecipes;
import gregtech.api.GTValues;
import gregtech.api.items.armor.ArmorMetaItem;
import gregtech.api.metatileentity.multiblock.CleanroomType;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.recipes.ingredients.nbtmatch.NBTCondition;
import gregtech.api.recipes.ingredients.nbtmatch.NBTMatcher;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.common.blocks.BlockComputerCasing;
import net.minecraft.block.BlockCauldron;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import static com.fulltrix.gcyl.GCYLMaterials.*;
import static com.fulltrix.gcyl.item.GCYLCoreItems.*;
import static gregicality.multiblocks.api.recipes.GCYMRecipeMaps.ALLOY_BLAST_RECIPES;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.GTRecipeHandler.removeRecipesByInputs;
import static gregtech.api.recipes.ModHandler.removeRecipeByName;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.recipes.ingredients.IntCircuitIngredient.getIntegratedCircuit;
import static gregtech.api.unification.material.MarkerMaterials.Color.Pink;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.blocks.BlockFusionCasing.CasingType.FUSION_COIL;
import static gregtech.common.blocks.BlockFusionCasing.CasingType.SUPERCONDUCTOR_COIL;
import static gregtech.common.blocks.MetaBlocks.*;
import static gregtech.common.items.MetaItems.*;
import static gregtech.common.items.MetaItems.ENERGY_CLUSTER;
import static gregtech.common.items.MetaItems.ENERGY_MODULE;
import static gregtech.common.items.MetaItems.GRAVITATION_ENGINE;
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
    }

    private static void recipeRemoval() {

        //removeRecipesByInputs(FLUID_SOLIDFICATION_RECIPES, new ItemStack[]{MetaItems.SHAPE_MOLD_CYLINDER.getStackForm()},  new FluidStack[]{Polytetrafluoroethylene.getFluid(36)});

        //remove all circuit assembler recipes
        removeAll(CIRCUIT_ASSEMBLER_RECIPES);
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

        removeRecipesByInputs(CHEMICAL_RECIPES, DinitrogenTetroxide.getFluid(1000), Dimethylhydrazine.getFluid(1000));
        removeRecipesByInputs(LARGE_CHEMICAL_RECIPES, DinitrogenTetroxide.getFluid(1000), Dimethylhydrazine.getFluid(1000));

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

        removeRecipesByInputs(LASER_ENGRAVER_RECIPES, OreDictUnifier.get(craftingLens,Pink), NEUTRONIUM_WAFER.getStackForm());

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
        removeRecipesByInputs(CENTRIFUGE_RECIPES, OreDictUnifier.get(dust,RarestMetalMixture,5));
        removeRecipesByInputs(CENTRIFUGE_RECIPES, OreDictUnifier.get(dust,PlatinumSludgeResidue,5));
        removeRecipesByInputs(DISTILLATION_RECIPES, AcidicOsmiumSolution.getFluid(2000));
        removeRecipesByInputs(DISTILLERY_RECIPES, new ItemStack[]{getIntegratedCircuit(1)}, new FluidStack[]{AcidicOsmiumSolution.getFluid(400)});
        removeRecipesByInputs(DISTILLERY_RECIPES, new ItemStack[]{getIntegratedCircuit(2)}, new FluidStack[]{AcidicOsmiumSolution.getFluid(400)});

        //Field generators
        removeRecipeByName("gregtech:field_generator_lv");
        removeRecipeByName("gregtech:field_generator_mv");
        removeRecipeByName("gregtech:field_generator_hv");
        removeRecipeByName("gregtech:field_generator_ev");
        removeRecipeByName("gregtech:field_generator_iv");

        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(wireGtQuadruple, ManganesePhosphide,4), OreDictUnifier.get(plate, Steel, 2), OreDictUnifier.get(gem, EnderPearl), OreDictUnifier.get(circuit, MarkerMaterials.Tier.LV,2));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(wireGtQuadruple, MagnesiumDiboride,4), OreDictUnifier.get(plate, Aluminium, 2), OreDictUnifier.get(gem, EnderEye), OreDictUnifier.get(circuit, MarkerMaterials.Tier.MV,2));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(wireGtQuadruple, MercuryBariumCalciumCuprate,4), OreDictUnifier.get(plate, StainlessSteel, 2), QUANTUM_EYE.getStackForm(), OreDictUnifier.get(circuit, MarkerMaterials.Tier.HV,2));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(wireGtQuadruple, UraniumTriplatinum,4), OreDictUnifier.get(plate, Titanium, 2), OreDictUnifier.get(gem, NetherStar), OreDictUnifier.get(circuit, MarkerMaterials.Tier.EV,2));
        removeRecipesByInputs(ASSEMBLER_RECIPES, OreDictUnifier.get(wireGtQuadruple, SamariumIronArsenicOxide,4), OreDictUnifier.get(plateDouble, TungstenSteel, 2), QUANTUM_STAR.getStackForm(), OreDictUnifier.get(circuit, MarkerMaterials.Tier.IV,2));


        //Remove old superconductor mixer recipes
        removeRecipesByInputs(MIXER_RECIPES, getIntegratedCircuit(4), OreDictUnifier.get(dust,Manganese), OreDictUnifier.get(dust,Phosphorus));
        removeRecipesByInputs(MIXER_RECIPES, getIntegratedCircuit(4), OreDictUnifier.get(dust,Magnesium), OreDictUnifier.get(dust,Boron,2));
        removeRecipesByInputs(MIXER_RECIPES, new ItemStack[]{getIntegratedCircuit(4), OreDictUnifier.get(dust, Barium,2), OreDictUnifier.get(dust,Calcium,2), OreDictUnifier.get(dust,Copper,3)},new FluidStack[]{Oxygen.getFluid(8000),Mercury.getFluid(1000)});
        removeRecipesByInputs(MIXER_RECIPES, getIntegratedCircuit(4), OreDictUnifier.get(dust,Uranium238), OreDictUnifier.get(dust,Platinum,3));
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

        /*NOT NEEDED ANYMORE BUT KEEPING
        //Fusion reactor computers
        removeRecipesByInputs(ASSEMBLY_LINE_RECIPES, new ItemStack[]{FUSION_CASING.getItemVariant(SUPERCONDUCTOR_COIL), OreDictUnifier.get(circuit, MarkerMaterials.Tier.ZPM,4),
                OreDictUnifier.get(plateDouble, Plutonium241), OreDictUnifier.get(plateDouble, Osmiridium), FIELD_GENERATOR_IV.getStackForm(2), ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64),
                OreDictUnifier.get(wireGtSingle, IndiumTinBariumTitaniumCuprate, 32)}, new FluidStack[]{SolderingAlloy.getFluid(L*8), NiobiumTitanium.getFluid(L*8)});
        removeRecipesByInputs(SCANNER_RECIPES, TOOL_DATA_STICK.getStackForm(), OreDictUnifier.get(wireGtSingle, IndiumTinBariumTitaniumCuprate));

        removeRecipesByInputs(ASSEMBLY_LINE_RECIPES, new ItemStack[]{FUSION_CASING.getItemVariant(FUSION_COIL), OreDictUnifier.get(circuit, MarkerMaterials.Tier.UV,4),
                OreDictUnifier.get(plateDouble, Naquadria), OreDictUnifier.get(plateDouble, Europium), FIELD_GENERATOR_LuV.getStackForm(2), ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64),
                ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(32), OreDictUnifier.get(wireGtSingle, UraniumRhodiumDinaquadide, 32)},
                new FluidStack[]{SolderingAlloy.getFluid(L*8), VanadiumGallium.getFluid(L*8)});
        removeRecipesByInputs(RESEARCH_STATION_RECIPES, TOOL_DATA_ORB.getStackForm(),FUSION_REACTOR[0].getStackForm());

        removeRecipesByInputs(ASSEMBLY_LINE_RECIPES, new ItemStack[]{FUSION_CASING.getItemVariant(FUSION_COIL), OreDictUnifier.get(circuit, MarkerMaterials.Tier.UHV,4),
                        QUANTUM_STAR.getStackForm(), OreDictUnifier.get(plateDouble, Americium), FIELD_GENERATOR_ZPM.getStackForm(2), ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64),
                        ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT.getStackForm(64), OreDictUnifier.get(wireGtSingle, EnrichedNaquadahTriniumEuropiumDuranide, 32)},
                new FluidStack[]{SolderingAlloy.getFluid(L*8), YttriumBariumCuprate.getFluid(L*8)});
        removeRecipesByInputs(RESEARCH_STATION_RECIPES, TOOL_DATA_MODULE.getStackForm(),FUSION_REACTOR[1].getStackForm());

        //luv-uv emitter
        removeRecipesByInputs(ASSEMBLY_LINE_RECIPES, new ItemStack[]{OreDictUnifier.get(frameGt, HSSS), ELECTRIC_MOTOR_LuV.getStackForm(), OreDictUnifier.get(stickLong, Ruridit,4), QUANTUM_STAR.getStackForm(),
                OreDictUnifier.get( circuit, MarkerMaterials.Tier.LuV, 2), OreDictUnifier.get(foil, Palladium, 64), OreDictUnifier.get(foil, Palladium, 32), OreDictUnifier.get(cableGtSingle, NiobiumTitanium, 4)},
                new FluidStack[]{SolderingAlloy.getFluid(L*2)});
        removeRecipesByInputs(SCANNER_RECIPES, TOOL_DATA_STICK.getStackForm(), EMITTER_IV.getStackForm());
        removeRecipesByInputs(ASSEMBLY_LINE_RECIPES, new ItemStack[]{OreDictUnifier.get(frameGt, NaquadahAlloy), ELECTRIC_MOTOR_ZPM.getStackForm(), OreDictUnifier.get(stickLong, Osmiridium,4), QUANTUM_STAR.getStackForm(2),
                        OreDictUnifier.get( circuit, MarkerMaterials.Tier.ZPM, 2), OreDictUnifier.get(foil, Trinium, 64), OreDictUnifier.get(foil, Trinium, 32), OreDictUnifier.get(cableGtSingle, VanadiumGallium, 4)},
                new FluidStack[]{SolderingAlloy.getFluid(L*4)});
        removeRecipesByInputs(RESEARCH_STATION_RECIPES, TOOL_DATA_ORB.getStackForm(), EMITTER_LuV.getStackForm());
        removeRecipesByInputs(ASSEMBLY_LINE_RECIPES, new ItemStack[]{OreDictUnifier.get(frameGt, Tritanium), ELECTRIC_MOTOR_UV.getStackForm(), OreDictUnifier.get(stickLong, Tritanium,4), GRAVI_STAR.getStackForm(),
                        OreDictUnifier.get( circuit, MarkerMaterials.Tier.UV, 2), OreDictUnifier.get(foil, Naquadria, 64), OreDictUnifier.get(foil, Naquadria, 32), OreDictUnifier.get(cableGtSingle, YttriumBariumCuprate, 4)},
                new FluidStack[]{SolderingAlloy.getFluid(L*8), Naquadria.getFluid(L*4)});
        removeRecipesByInputs(RESEARCH_STATION_RECIPES, TOOL_DATA_MODULE.getStackForm(), EMITTER_ZPM.getStackForm());

         */




        //gravi star
        removeRecipesByInputs(AUTOCLAVE_RECIPES, new ItemStack[]{QUANTUM_STAR.getStackForm()}, new FluidStack[]{Neutronium.getFluid(288)});


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
                .input(foil, Trinium, 32)
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

        //energy output hatches luv-uhv
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
                .input(spring, YttriumBariumCuprate, 4)
                .input(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 2)
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

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(HULL[UHV])
                .input(spring, Europium, 4)
                .input(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 2)
                .input(circuit, MarkerMaterials.Tier.UHV)
                .input(wireGtDouble, UHVSuperconductor, 2)
                .fluidInputs(SodiumPotassium.getFluid(12000))
                .fluidInputs(SolderingAlloy.getFluid(5760))
                .output(ENERGY_OUTPUT_HATCH[UHV])
                .stationResearch(b -> b
                        .researchStack(ENERGY_OUTPUT_HATCH[UV].getStackForm())
                        .CWUt(128)
                        .EUt(VA[UV]))
                .duration(1000).EUt(VA[UHV]).buildAndRegister();

        //energy input hatches luv-uhv
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
                .input(cableGtSingle, YttriumBariumCuprate, 4)
                .input(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 2)
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

        ASSEMBLY_LINE_RECIPES.recipeBuilder()
                .input(HULL[UHV])
                .input(cableGtSingle, Europium, 4)
                .input(ULTRA_HIGH_POWER_INTEGRATED_CIRCUIT, 2)
                .input(circuit, MarkerMaterials.Tier.UHV)
                .input(wireGtDouble, UHVSuperconductor, 2)
                .fluidInputs(SodiumPotassium.getFluid(12000))
                .fluidInputs(SolderingAlloy.getFluid(5760))
                .output(ENERGY_INPUT_HATCH[UHV])
                .stationResearch(b -> b
                        .researchStack(ENERGY_INPUT_HATCH[UV].getStackForm())
                        .CWUt(128)
                        .EUt(VA[UV]))
                .duration(1000).EUt(VA[UHV]).buildAndRegister();
    }
}
