package com.fulltrix.gcyl.recipes.categories;

import com.fulltrix.gcyl.recipes.categories.circuits.MagnetoRecipes;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
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
import static gregtech.common.items.MetaItems.*;
import static gregtech.common.metatileentities.MetaTileEntities.FLUID_IMPORT_HATCH;
import static gregtech.common.metatileentities.MetaTileEntities.RESERVOIR_HATCH;

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
        removeRecipesByInputs(CENTRIFUGE_RECIPES, OreDictUnifier.get(dust, NaquadahAlloy, 4));
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



    }
}
