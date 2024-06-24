package com.fulltrix.gcyl.recipes.chain;

import com.fulltrix.gcyl.item.GCYLExplosive;
import com.fulltrix.gcyl.item.GCYLMetaBlocks;
import gregtech.api.fluids.store.FluidStorageKeys;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.material.Material;
import gregtech.common.items.MetaItems;
import net.minecraft.item.ItemStack;

import static com.fulltrix.gcyl.materials.GCYLMaterials.*;
import static com.fulltrix.gcyl.item.GCYLCoreItems.INDUCTOR;
import static com.fulltrix.gcyl.item.GCYLCoreItems.*;
import static com.fulltrix.gcyl.api.recipes.GCYLRecipeMaps.*;
import static gregicality.multiblocks.api.recipes.GCYMRecipeMaps.ALLOY_BLAST_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.MarkerMaterials.Color.Magenta;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

public class SuperconductorsSMDChain {
    public static void init() {

        // Cu + In + Ga -> CuGaIn
        MIXER_RECIPES.recipeBuilder().duration(240).EUt(120)
                .input(dust, Copper)
                .input(dust, Indium)
                .input(dust, Gallium)
                .output(dust, CopperGalliumIndiumMix, 3)
                .buildAndRegister();

        // 2H + Se -> H2Se
        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(120)
                .fluidInputs(Hydrogen.getFluid(2000))
                .input(dust, Selenium)
                .fluidOutputs(HydroselenicAcid.getFluid(1000))
                .buildAndRegister();

        // 2H2Se + CuInGa -> CuInGaSe2 + 4 H
        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(480)
                .fluidInputs(HydroselenicAcid.getFluid(2000))
                .input(dust, CopperGalliumIndiumMix, 3)
                .output(dust, CopperGalliumIndiumSelenide, 5)
                .fluidOutputs(Hydrogen.getFluid(4000))
                .buildAndRegister();

        // MnO2 + 2KOH + O -> K2MnO4 + H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(170).EUt(30)
                .input(dust, Pyrolusite, 3)
                .fluidInputs(PotassiumHydroxide.getFluid(2000))
                .fluidInputs(Oxygen.getFluid(1000))
                .output(dust, PotassiumManganate, 7)
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        // La2O3 + 2CaO + 2K2MnO4 -> 2LaCaMnO3 + 2K2O + 5O
        BLAST_RECIPES.recipeBuilder().duration(380).EUt(120).blastFurnaceTemp(900)
                .input(dust, LanthanumOxide, 5)
                .input(dust, Quicklime, 4)
                .input(dust, PotassiumManganate, 14)
                .output(dust, LanthanumCalciumManganate, 12)
                .output(dust, Potash, 6)
                .fluidOutputs(Oxygen.getFluid(5000))
                .buildAndRegister();

        // Fe + Pt -> FePt
        MIXER_RECIPES.recipeBuilder().duration(230).EUt(240)
                .input(dust, Iron)
                .input(dust, Platinum)
                .output(dust, IronPlatinumCatalyst, 2)
                .buildAndRegister();

        // C6H5OH + HNO3 + 6H -> 3H2O + C6H7NO
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(120)
                .fluidInputs(Phenol.getFluid(1000))
                .fluidInputs(NitricAcid.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(6000))
                .notConsumable(dust, IronPlatinumCatalyst, 1)
                .fluidOutputs(Water.getFluid(3000))
                .fluidOutputs(Aminophenol.getFluid(1000))
                .buildAndRegister();

        // C6H7NO + C3H8O3 + O -> C9H7NO + 4H2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(480)
                .fluidInputs(Aminophenol.getFluid(1000))
                .fluidInputs(Glycerol.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(1000))
                .notConsumable(Nitrobenzene.getFluid(1))
                .fluidOutputs(Hydroxyquinoline.getFluid(1000))
                .fluidOutputs(Water.getFluid(4000))
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder().duration(350).EUt(1920)
                .fluidInputs(Hydroxyquinoline.getFluid(1000))
                .input(dust, Aluminium)
                .output(dust, AluminiumComplex, 19)
                .buildAndRegister();

        // Na2O4Ru + 2H -> RuO2 + 2NaOH
        BLAST_RECIPES.recipeBuilder().duration(270).EUt(120).blastFurnaceTemp(1250)
                .input(dust, SodiumRuthenate, 7)
                .notConsumable(new IntCircuitIngredient(2))
                .fluidInputs(Hydrogen.getFluid(2000))
                .output(dust, RutheniumDioxide, 3)
                .output(dust, SodiumHydroxide, 6)
                .buildAndRegister();

        // Bi + 6HNO3 -> [Bi(NO3)3 + H2O] + 3NO2 + 2H2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(350).EUt(30)
                .input(dust, Bismuth)
                .fluidInputs(NitricAcid.getFluid(6000))
                .fluidOutputs(BismuthNitrateSoluton.getFluid(1000))
                .fluidOutputs(NitrogenDioxide.getFluid(3000))
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();

        // 2Na2O4Ru + 2[Bi(NO3)3 + H2O] -> Bi2Ru2O7 + 4NaNO3 + N2H4 + 9O
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(480)
                .input(dust, SodiumRuthenate, 14)
                .fluidInputs(BismuthNitrateSoluton.getFluid(2000))
                .fluidOutputs(Hydrazine.getFluid(1000))
                .fluidOutputs(Oxygen.getFluid(9000))
                .output(dust, BismuthRuthenate, 11)
                .output(dust, SodiumNitrate, 20)
                .buildAndRegister();

        // NaNO3 -> Na + N + 3O
        ELECTROLYZER_RECIPES.recipeBuilder().duration(270).EUt(30)
                .input(dust, SodiumNitrate, 5)
                .output(dust, Sodium)
                .fluidOutputs(Nitrogen.getFluid(1000))
                .fluidOutputs(Oxygen.getFluid(3000))
                .buildAndRegister();

        // 2IrO2 + 2[Bi(NO3)3 + H2O] + H -> Bi2Ir2O7 + 5HNO2 + NO2
        CHEMICAL_RECIPES.recipeBuilder().duration(300).EUt(1920)
                .input(dust, IridiumDioxide, 6)
                .fluidInputs(BismuthNitrateSoluton.getFluid(2000))
                .fluidInputs(Hydrogen.getFluid(1000))
                .output(dust, BismuthIridiate, 11)
                .fluidOutputs(NitricAcid.getFluid(5000))
                .fluidOutputs(NitrogenDioxide.getFluid(1000))
                .buildAndRegister();

        // BaCl3 + H2O -> [BaCl3 + H2O]
        MIXER_RECIPES.recipeBuilder().duration(230).EUt(120)
                .input(dust, BariumChloride, 3)
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidOutputs(BariumChlorideSolution.getFluid(1000))
                .buildAndRegister();

        // TiCl4 + [NaOH + H2O] + [BaCl2 + H2O] -> BaTiO3 + NaCl + 5HCl
        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(120)
                .fluidInputs(TitaniumTetrachloride.getFluid(1000))
                .fluidInputs(SodiumHydroxideSolution.getFluid(1000))
                .fluidInputs(BariumChlorideSolution.getFluid(1000))
                .output(dust, Salt, 2)
                .fluidOutputs(BariumTitanatePreparation.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(4000))
                .buildAndRegister();

        // 0.1Prep -> 0.2Titanate, 2:1 ratio
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().duration(58).EUt(1920)
                .fluidInputs(BariumTitanatePreparation.getFluid(100))
                .output(dust, BariumTitanate)
                .buildAndRegister();

        // H2S + C4H6O4 + 4Br -> C4Br4S + 4H2O
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(230).EUt(4960)
                .input(dust, SuccinicAcid, 14)
                .fluidInputs(HydrogenSulfide.getFluid(1000))
                .fluidInputs(Bromine.getFluid(4000))
                .fluidOutputs(Perbromothiophene.getFluid(1000))
                .fluidOutputs(Water.getFluid(4000))
                .buildAndRegister();

        // 2 C4Br4S + 4 C2H5ONa + C2H4O2 + 2H2O -> 2 C8H12O2S + 4 NaBr + 4 HBr + 2 CO2
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(480)
                .notConsumable(dust, Zinc)
                .fluidInputs(Perbromothiophene.getFluid(1000))
                .fluidInputs(AceticAcid.getFluid(500))
                .input(dust, SodiumEthoxide, 18)
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(Diethoxythiophene.getFluid(1000))
                .fluidOutputs(HydrobromicAcid.getFluid(2000))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .output(dust, SodiumBromide, 4)
                .buildAndRegister();

        // C2H6O2 + C6H8S -> C6H6O2S + 2CH4
        CHEMICAL_RECIPES.recipeBuilder().duration(140).EUt(120)
                .notConsumable(GELLED_TOLUENE)
                .fluidInputs(EthyleneGlycol.getFluid(1000))
                .fluidInputs(Diethoxythiophene.getFluid(1000))
                .fluidOutputs(EDOT.getFluid(1000))
                .fluidOutputs(Ethanol.getFluid(2000))
                .buildAndRegister();

        // (C8H8)n + H2SO4 + C6H6O2S (aka: EDOT) -> PEDOT + Dilute H2SO4
        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(320).EUt(1920)
                .fluidInputs(Polystyrene.getFluid(1000))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidInputs(EDOT.getFluid(1000))
                .notConsumable(SodiumPersulfate.getFluid(1))
                .notConsumable(dust, IronSulfateDust, 1)
                .fluidOutputs(DilutedSulfuricAcid.getFluid(1000))
                .output(dust, PEDOT, 3)
                .buildAndRegister();

        // Fe + H2SO4 -> FeSO4 + 2H
        CHEMICAL_RECIPES.recipeBuilder().duration(250).EUt(30)
                .input(dust, Iron)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .output(dust, IronSulfateDust, 6)
                .fluidOutputs(Hydrogen.getFluid(2000))
                .buildAndRegister();

        // ZrCl4 + 2H2O -> ZrOCl2 + 2HCl
        CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(120)
                .input(dust, ZirconiumTetrachloride, 5)
                .fluidInputs(Water.getFluid(2000))
                .output(dust, ZirconylChloride, 4)
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .buildAndRegister();

        // Pb + N2O4 + 2O -> Pb(NO3)2
        CHEMICAL_RECIPES.recipeBuilder().EUt(120).duration(150)
                .input(dust, Lead)
                .fluidInputs(DinitrogenTetroxide.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(2000))
                .output(dust, LeadNitrate, 9)
                .buildAndRegister();

        // ZrOCl2 + TiO2 + Pb(NO3)2 + 2H -> PbZrTiO3 + 2HNO3 + 2Cl
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(480)
                .input(dust, ZirconylChloride, 4)
                .input(dust, Rutile, 3)
                .input(dust, LeadNitrate, 9)
                .fluidInputs(Hydrogen.getFluid(2000))
                .fluidOutputs(NitricAcid.getFluid(2000))
                .fluidOutputs(Chlorine.getFluid(2000))
                .output(dust, LeadZirconateTitanate, 6)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(430).EUt(30720)
                .input(gemExquisite, LeadZirconateTitanate, 2)
                .input(wireFine, Gold)
                .fluidInputs(SolderingAlloy.getFluid(288))
                .outputs(PIEZOELECTRIC_CRYSTAL.getStackForm())
                .buildAndRegister();

        //TODO: nuclear
        /*
        BLAST_RECIPES.recipeBuilder().duration(500).EUt(120).blastFurnaceTemp(2400)
                .input(dust, Tungsten, 9)
                .input(GAEnums.GAOrePrefix.oxide, Thorium)
                .output(ingot, ThoriumDopedTungsten, 10)
                .buildAndRegister();

         */

        BLAST_RECIPES.recipeBuilder().duration(270).EUt(120).blastFurnaceTemp(1800)
                .input(dust, Quartzite)
                .input(dust, Alumina, 5)
                .notConsumable(SHAPE_MOLD_CYLINDER)
                .outputs(ALUMINO_SILICATE_GLASS_TUBE.getStackForm())
                .buildAndRegister();

        ALLOY_BLAST_RECIPES.recipeBuilder().duration(290).EUt(1920)
                .input(dust, SiliconDioxide, 3)
                .input(dust, BariumOxide, 2)
                .input(dust, Garnierite, 2)
                .input(dust, SodaAsh, 6)
                .fluidOutputs(WoodsGlass.getFluid(1296))
                .blastFurnaceTemp(1400)
                .buildAndRegister();

        // Fe + 2I -> FeI2
        CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(120)
                .input(dust, Iron)
                .input(dust, Iodine, 2)
                .output(dust, IronIodide, 3)
                .buildAndRegister();

        // Tl + I -> TlI
        CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(120)
                .input(dust, Thallium)
                .input(dust, Iodine)
                .output(dust, ThalliumIodide, 2)
                .buildAndRegister();

        // Rb + I -> RbI
        CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(120)
                .input(dust, Rubidium)
                .input(dust, Iodine)
                .output(dust, RubidiumIodide, 2)
                .buildAndRegister();

        // K + I -> KI
        CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(120)
                .input(dust, Potassium)
                .input(dust, Iodine)
                .output(dust, PotassiumIodide, 2)
                .buildAndRegister();

        // In + 3I -> InI3
        CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(120)
                .input(dust, Indium)
                .input(dust, Iodine, 3)
                .output(dust, IndiumIodide, 4)
                .buildAndRegister();

        // Ga + 3I -> GaI3
        CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(120)
                .input(dust, Gallium)
                .input(dust, Iodine, 3)
                .output(dust, GalliumIodide, 4)
                .buildAndRegister();

        // Sc + 3I -> ScI3
        CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(120)
                .input(dust, Scandium)
                .input(dust, Iodine, 3)
                .output(dust, ScandiumIodide, 4)
                .buildAndRegister();

        // FeI2 + 5CO -> 2I + Fe(CO)5 (all x5)
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(480)
                .input(dust, IronIodide, 15)
                .fluidInputs(CarbonMonoxide.getFluid(25000))
                .notConsumable(block, Copper)
                .output(dust, Iodine, 10)
                .output(dustSmall, Iron, 2)
                .fluidOutputs(IronCarbonyl.getFluid(5000))
                .buildAndRegister();

        FLUID_HEATER_RECIPES.recipeBuilder().duration(200).EUt(480)
                .circuitMeta(1)
                .fluidInputs(IronCarbonyl.getFluid(1000))
                .fluidOutputs(PurifiedIronCarbonyl.getFluid(900))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(340).EUt(480)
                .fluidInputs(PurifiedIronCarbonyl.getFluid(1000))
                .output(dust, CarbonylPurifiedIron, 1)
                .fluidOutputs(CarbonMonoxide.getFluid(5000))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(290).EUt(1920)
                .inputs(MetaItems.INDUCTOR.getStackForm(4))
                .input(dust, CarbonylPurifiedIron, 1)
                .input(wireFine, AnnealedCopper, 2)
                .fluidInputs(SolderingAlloy.getFluid(144))
                .outputs(INDUCTOR.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(290).EUt(1920)
                .inputs(RESISTOR.getStackForm())
                .inputs(INDUCTOR.getStackForm())
                .input(wireFine, Cupronickel, 2)
                .outputs(BALLAST.getStackForm())
                .buildAndRegister();

        // 2V + 5Na2CO3 + 4H2O -> 5CO + 2NaVO3 + 8NaOH
        BLAST_RECIPES.recipeBuilder().duration(180).EUt(120).blastFurnaceTemp(650)
                .input(dust, Vanadium, 2)
                .input(dust, SodaAsh, 30)
                .fluidInputs(Water.getFluid(4000))
                .fluidOutputs(CarbonMonoxide.getFluid(5000))
                .output(dust, SodiumMetavanadate, 10)
                .output(dust, SodiumHydroxide, 24)
                .buildAndRegister();

        // Y2O3 + Eu2O3 + V2O5 + 6H -> 2YEuVO4 + 3H2O
        BLAST_RECIPES.recipeBuilder().duration(340).EUt(120).blastFurnaceTemp(1200)
                .input(dust, YttriumOxide, 5)
                .input(dust, EuropiumOxide, 5)
                .input(dust, VanadiumOxide, 7)
                .fluidInputs(Hydrogen.getFluid(6000))
                .output(dust, YttriumEuropiumVanadate, 14)
                .fluidOutputs(Steam.getFluid(3000))
                .buildAndRegister();

        // SrCl2 + H2SO4 -> SrSO4 + 2HCl
        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(480)
                .input(dust, StrontiumChloride, 3)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .output(dust, Celestine, 6)
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .buildAndRegister();

        // 2SrO + Eu2O3 + 2Al2O3 + 6H -> 2SrEuAl2O4 + 3H2O
        BLAST_RECIPES.recipeBuilder().duration(340).EUt(120).blastFurnaceTemp(1200)
                .input(dust, StrontiumOxide, 4)
                .input(dust, EuropiumOxide, 5)
                .input(dust, Alumina, 10)
                .fluidInputs(Hydrogen.getFluid(6000))
                .output(dust, StrontiumEuropiumAluminate, 16)
                .fluidOutputs(Steam.getFluid(3000))
                .buildAndRegister();

        Material[] halides = {ThalliumIodide, RubidiumIodide, IndiumIodide, ScandiumIodide, GalliumIodide};
        Material[] mixtures = {GreenHalideMix, RedHalideMix, BlueHalideMix, WhiteHalideMix, UVAHalideMix};
        ItemStack[] lamp_cores = {GREEN_LAMP_CORE.getStackForm(), RED_LAMP_CORE.getStackForm(), BLUE_LAMP_CORE.getStackForm(), WHITE_LAMP_CORE.getStackForm(), UVA_LAMP_CORE.getStackForm()};
        MetaItem<?>.MetaValueItem[] halide_lamp = {GREEN_HALIDE_LAMP, RED_HALIDE_LAMP, BLUE_HALIDE_LAMP, WHITE_HALIDE_LAMP, UVA_HALIDE_LAMP};

        for (int i = 0; i < 5; i++) {
            MIXER_RECIPES.recipeBuilder().duration(320).EUt(30)
                    .input(dust, halides[i])
                    .input(dust, PotassiumIodide, 2)
                    .fluidInputs(Mercury.getFluid(1000))
                    .output(dust, mixtures[i], 2)
                    .buildAndRegister();

            ASSEMBLER_RECIPES.recipeBuilder().duration(240).EUt(1920)
                    .input(dust, mixtures[i], 1)
                    .input(foil, Molybdenum, 2)
                    .input(wireFine, ThoriumDopedTungsten, 4)
                    .inputs(ALUMINO_SILICATE_GLASS_TUBE.getStackForm())
                    .input(plate, CubicZirconia, 2)
                    .fluidInputs(Argon.getFluid(1000))
                    .outputs(lamp_cores[i])
                    .buildAndRegister();

            if (i == 4) {
                ASSEMBLER_RECIPES.recipeBuilder().duration(290).EUt(1920)
                        .inputs(lamp_cores[i])
                        .input(stick, MaragingSteel250, 4)
                        .inputs(BALLAST.getStackForm())
                        .input(foil, Electrum, 2)
                        .input(plate, WoodsGlass, 2)
                        .input(dust, YttriumEuropiumVanadate, 7)
                        .fluidInputs(Nitrogen.getFluid(1000))
                        .outputs(halide_lamp[i].getStackForm())
                        .buildAndRegister();

                ASSEMBLER_RECIPES.recipeBuilder().duration(290).EUt(1920)
                        .inputs(lamp_cores[i])
                        .input(stick, MaragingSteel250, 4)
                        .inputs(BALLAST.getStackForm())
                        .input(foil, Electrum, 2)
                        .input(plate, WoodsGlass, 2)
                        .input(dust, StrontiumEuropiumAluminate, 4)
                        .fluidInputs(Nitrogen.getFluid(1000))
                        .outputs(halide_lamp[i].getStackForm())
                        .buildAndRegister();

                ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(210).EUt(7680)
                        .input(dust, mixtures[i], 1)
                        .input(foil, Molybdenum, 2)
                        .input(wireFine, ThoriumDopedTungsten, 4)
                        .inputs(ALUMINO_SILICATE_GLASS_TUBE.getStackForm())
                        .input(plate, CubicZirconia, 2)
                        .fluidInputs(Xenon.getFluid(250))
                        .fluidInputs(SolderingAlloy.getFluid(432))
                        .input(stick, MaragingSteel250, 4)
                        .inputs(BALLAST.getStackForm())
                        .input(foil, Electrum, 2)
                        .input(plate, WoodsGlass, 2)
                        .input(dust, YttriumEuropiumVanadate, 7)
                        .fluidInputs(Nitrogen.getFluid(1000))
                        .outputs(halide_lamp[i].getStackForm(2))
                        .buildAndRegister();

                ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(210).EUt(7680)
                        .input(dust, mixtures[i], 1)
                        .input(foil, Molybdenum, 2)
                        .input(wireFine, ThoriumDopedTungsten, 4)
                        .inputs(ALUMINO_SILICATE_GLASS_TUBE.getStackForm())
                        .input(plate, CubicZirconia, 2)
                        .fluidInputs(Xenon.getFluid(250))
                        .fluidInputs(SolderingAlloy.getFluid(432))
                        .input(stick, MaragingSteel250, 4)
                        .inputs(BALLAST.getStackForm())
                        .input(foil, Electrum, 2)
                        .input(plate, WoodsGlass, 2)
                        .input(dust, StrontiumEuropiumAluminate, 4)
                        .fluidInputs(Nitrogen.getFluid(1000))
                        .outputs(halide_lamp[i].getStackForm(2))
                        .buildAndRegister();

            } else {
                ASSEMBLER_RECIPES.recipeBuilder().duration(290).EUt(1920)
                        .inputs(lamp_cores[i])
                        .input(stick, MaragingSteel250, 4)
                        .inputs(BALLAST.getStackForm())
                        .input(foil, Electrum, 2)
                        .input(plate, BorosilicateGlass, 2)
                        .input(dust, YttriumEuropiumVanadate, 7)
                        .fluidInputs(Nitrogen.getFluid(1000))
                        .outputs(halide_lamp[i].getStackForm())
                        .buildAndRegister();

                ASSEMBLER_RECIPES.recipeBuilder().duration(290).EUt(1920)
                        .inputs(lamp_cores[i])
                        .input(stick, MaragingSteel250, 4)
                        .inputs(BALLAST.getStackForm())
                        .input(foil, Electrum, 2)
                        .input(plate, BorosilicateGlass, 2)
                        .input(dust, StrontiumEuropiumAluminate, 4)
                        .fluidInputs(Nitrogen.getFluid(1000))
                        .outputs(halide_lamp[i].getStackForm())
                        .buildAndRegister();

                ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(210).EUt(7680)
                        .input(dust, mixtures[i], 1)
                        .input(foil, Molybdenum, 2)
                        .input(wireFine, ThoriumDopedTungsten, 4)
                        .inputs(ALUMINO_SILICATE_GLASS_TUBE.getStackForm())
                        .input(plate, CubicZirconia, 2)
                        .fluidInputs(Xenon.getFluid(250))
                        .fluidInputs(SolderingAlloy.getFluid(432))
                        .input(stick, MaragingSteel250, 4)
                        .inputs(BALLAST.getStackForm())
                        .input(foil, Electrum, 2)
                        .input(plate, BorosilicateGlass, 2)
                        .input(dust, YttriumEuropiumVanadate, 7)
                        .fluidInputs(Nitrogen.getFluid(1000))
                        .outputs(halide_lamp[i].getStackForm(2))
                        .buildAndRegister();

                ASSEMBLY_LINE_RECIPES.recipeBuilder().duration(210).EUt(7680)
                        .input(dust, mixtures[i], 1)
                        .input(foil, Molybdenum, 2)
                        .input(wireFine, ThoriumDopedTungsten, 4)
                        .inputs(ALUMINO_SILICATE_GLASS_TUBE.getStackForm())
                        .input(plate, CubicZirconia, 2)
                        .fluidInputs(Xenon.getFluid(250))
                        .fluidInputs(SolderingAlloy.getFluid(432))
                        .input(stick, MaragingSteel250, 4)
                        .inputs(BALLAST.getStackForm())
                        .input(foil, Electrum, 2)
                        .input(plate, BorosilicateGlass, 2)
                        .input(dust, StrontiumEuropiumAluminate, 4)
                        .fluidInputs(Nitrogen.getFluid(1000))
                        .outputs(halide_lamp[i].getStackForm(2))
                        .buildAndRegister();
            }
        }

        // 6F + 2CS2 -> C2F6S2 + 2S
        CHEMICAL_RECIPES.recipeBuilder().duration(250).EUt(120)
                .notConsumable(dust, Iodine)
                .fluidInputs(Fluorine.getFluid(6000))
                .fluidInputs(CarbonSulfide.getFluid(2000))
                .fluidOutputs(Biperfluoromethanedisulfide.getFluid(1000))
                .output(dust, Sulfur, 2)
                .buildAndRegister();

        // Hg + 3H2O2 + C2F6S2 + BaCO3 -> [C2BaF6O6S2 + 3H2O + Hg] + C
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(480)
                .fluidInputs(Mercury.getFluid(1000))
                .fluidInputs(Water.getFluid(3000))
                .fluidInputs(Biperfluoromethanedisulfide.getFluid(1000))
                .input(dust, BariumCarbonate, 5)
                .notConsumable(UVA_HALIDE_LAMP.getStackForm())
                .fluidOutputs(BariumTriflateSolution.getFluid(3000))
                .output(dust, Carbon)
                .buildAndRegister();

        // [C2BaF6O6S2 + 3H2O + Hg] -> C2BaF6O6S2 + 3H2O + Hg
        CENTRIFUGE_RECIPES.recipeBuilder().duration(320).EUt(1920)
                .fluidInputs(BariumTriflateSolution.getFluid(3000))
                .output(dust, BariumTriflate, 17)
                .fluidOutputs(Mercury.getFluid(1000))
                .fluidOutputs(Water.getFluid(3000))
                .buildAndRegister();

        // 3H2SO4 + 2Sc + 3C2BaF6O6S2 -> 3BaSO4 + 2C3F9O9S3Sc + 6H
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(480)
                .fluidInputs(SulfuricAcid.getFluid(3000))
                .input(dust, Scandium, 2)
                .input(dust, BariumTriflate, 51)
                .output(dust, Barite, 21)
                .output(dust, ScandiumTriflate, 50)
                .fluidOutputs(Hydrogen.getFluid(6000))
                .buildAndRegister();

        // 2HNO3 + BaS -> H2S + Ba(NO3)2
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(120)
                .fluidInputs(NitricAcid.getFluid(2000))
                .input(dust, BariumSulfide, 2)
                .fluidOutputs(HydrogenSulfide.getFluid(1000))
                .output(dust, BariumNitrate, 9)
                .buildAndRegister();

        // 2HNO3 + Cu -> 2H + Cu(NO3)2
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(120)
                .fluidInputs(NitricAcid.getFluid(2000))
                .input(dust, Copper)
                .fluidOutputs(NitrogenDioxide.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(2000))
                .output(dust, CopperNitrate, 9)
                .buildAndRegister();

        // 6HNO3 + Y2O3 -> 3H2O + 2Y(NO3)3
        CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(120)
                .fluidInputs(NitricAcid.getFluid(6000))
                .input(dust, YttriumOxide, 5)
                .notConsumable(new IntCircuitIngredient(0))
                .fluidOutputs(Water.getFluid(3000))
                .output(dust, YttriumNitrate, 26)
                .buildAndRegister();

        // C3H5ClO + 2H2O + Na2CO3 -> C3H8O3 + NaCl + NaHCO3
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(1024)
                .fluidInputs(Epichlorohydrin.getFluid(1000))
                .fluidInputs(Water.getFluid(2000))
                .input(dust, SodaAsh, 6)
                .circuitMeta(1)
                .fluidOutputs(Glycerol.getFluid(1000))
                .output(dust, Salt, 2)
                .output(dust, SodiumBicarbonate, 6)
                .buildAndRegister();

        // C3H8O3 + 2HCl + 3HCN + HClO + 3H2O -> C6H8O7 + 3NH4Cl
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(7680)
                .fluidInputs(Glycerol.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(2000))
                .fluidInputs(HypochlorousAcid.getFluid(1000))
                .fluidInputs(HydrogenCyanide.getFluid(3000))
                .fluidInputs(Water.getFluid(3000))
                .notConsumable(dust, PotassiumDichromate)
                .circuitMeta(2)
                .fluidOutputs(CitricAcid.getFluid(1000))
                .fluidOutputs(AmmoniumChloride.getFluid(3000))
                .buildAndRegister();

        // 3Cu(NO3)2 + 2Ba(NO3)2 + Y(NO3)3 + 2NH3 + C6H8O7 -> YBa2Cu3O6 + 15NO2 + 6CO + 4H2O + 6H
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(7680)
                .input(dust, CopperNitrate, 27)
                .input(dust, BariumNitrate, 18)
                .input(dust, YttriumNitrate, 13)
                .fluidInputs(Ammonia.getFluid(2000))
                .fluidInputs(CitricAcid.getFluid(1000))
                .output(dust, WellMixedYBCOxides, 12)
                .fluidOutputs(NitrogenDioxide.getFluid(15000))
                .fluidOutputs(CarbonMonoxide.getFluid(6000))
                .fluidOutputs(Water.getFluid(4000))
                .fluidOutputs(Hydrogen.getFluid(6000))
                .buildAndRegister();

        // 2Th + 2Ba + 3Cu + 2Ca -> TBCC
        ASSEMBLER_RECIPES.recipeBuilder().duration(360).EUt(122880)
                .input(foil, Thallium, 2)
                .input(foil, Barium, 2)
                .input(foil, Copper, 3)
                .input(foil, Calcium, 2)
                .output(dust, PiledTBCC, 9)
                .buildAndRegister();

        // [2Th + 2Ba + 3Cu + 2Ca] + 10O -> TBCCO
        ARC_FURNACE_RECIPES.recipeBuilder().duration(240).EUt(30720)
                .input(dust, PiledTBCC, 1)
                .fluidInputs(Oxygen.getFluid(1000))
                .output(dust, TBCCODust, 1)
                .buildAndRegister();

        // 9O + C6H12O6 -> 3C2H2O4 + 3H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(480)
                .notConsumable(dust, VanadiumOxide, 1)
                .fluidInputs(Oxygen.getFluid(9000))
                .input(dust, Glucose, 24)
                .fluidOutputs(Water.getFluid(3000))
                .fluidOutputs(OxalicAcid.getFluid(3000))
                .buildAndRegister();

        // 2C2H2O4 + 2O + Ac -> Ac(C2O4)2 + 2H2O
        CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(30720)
                .fluidInputs(OxalicAcid.getFluid(2000))
                .fluidInputs(Oxygen.getFluid(2000))
                .input(dust, Actinium)
                .fluidOutputs(Water.getFluid(2000))
                .output(dust, ActiniumOxalate, 13)
                .buildAndRegister();

        // C + 4Cl -> CCl4
        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(30)
                .input(dust, Carbon)
                .notConsumable(new IntCircuitIngredient(0))
                .fluidInputs(Chlorine.getFluid(4000))
                .fluidOutputs(CarbonTetrachloride.getFluid(1000))
                .buildAndRegister();

        // Ac(C2O4)2 + 3NaH + 13Na + 4CCl4 -> AcH3 + 16NaCl + 8CO
        BLAST_RECIPES.recipeBuilder().duration(530).EUt(120)
                .blastFurnaceTemp(10700)
                .input(dust, ActiniumOxalate, 13)
                .input(dust, SodiumHydride, 6)
                .input(dust, Sodium, 13)
                .fluidInputs(CarbonTetrachloride.getFluid(4000))
                .output(dust, ActiniumHydride, 4)
                .output(dust, Salt, 32)
                .fluidOutputs(CarbonDioxide.getFluid(8000))
                .buildAndRegister();

        // AcH3 + 9H -> AcH12 (multiplied by 18)
        STELLAR_FORGE_RECIPES.recipeBuilder().duration(260).EUt(7800000)
                .inputs(GCYLMetaBlocks.EXPLOSIVE.getItemVariant(GCYLExplosive.ExplosiveType.NAQUADRIA_CHARGE))
                .input(dust, ActiniumHydride, 72)
                .fluidInputs(Hydrogen.getFluid(162000))
                .fluidOutputs(ActiniumSuperhydridePlasma.getPlasma(72000))
                .buildAndRegister();

        CANNER_RECIPES.recipeBuilder()
                .inputs(PLASMA_CONTAINMENT_CELL.getStackForm())
                .fluidInputs(ActiniumSuperhydridePlasma.getPlasma(1000))
                .outputs(ACTINIUM_PLASMA_CONTAINMENT_CELL.getStackForm())
                .EUt(750000)
                .duration(20)
                .buildAndRegister();

        PLASMA_CONDENSER_RECIPES.recipeBuilder().duration(340).EUt(8380000)
                .inputs(ACTINIUM_PLASMA_CONTAINMENT_CELL.getStackForm())
                .fluidInputs(Helium.getFluid(FluidStorageKeys.LIQUID, 24000))
                .output(dust, ActiniumSuperhydride, 13)
                .outputs(PLASMA_CONTAINMENT_CELL.getStackForm())
                .fluidOutputs(Helium.getFluid(24000))
                .buildAndRegister();

        // 2La + 2 C60H30 -> La2(C60H30)2
        MIXER_RECIPES.recipeBuilder().duration(140).EUt(30720)
                .input(dust, Lanthanum, 2)
                .input(dust, UnfoldedFullerene, 2)
                .output(dust, LanthanumFullereneMix, 4)
                .buildAndRegister();

        //TODO: advanced laser engraver?
        //La2(C60H30)2 + 20 N -> La2(C60)2 + 20 NH3
        LASER_ENGRAVER_RECIPES.recipeBuilder().duration(320).EUt(1966080)
                .input(dust, LanthanumFullereneMix, 4)
                .fluidInputs(Nitrogen.getFluid(20000))
                .notConsumable(craftingLens, Magenta)
                .fluidOutputs(Ammonia.getFluid(20000))
                .output(dust, LanthanumEmbeddedFullerene, 4)
                .buildAndRegister();

        // 3Rb + 3Cs + La2(C60)2 -> La2Cs3Rb3(C60)2
        BLAST_RECIPES.recipeBuilder().duration(680).EUt(120).blastFurnaceTemp(2400)
                .input(dust, LanthanumEmbeddedFullerene, 4)
                .input(dust, Rubidium, 3)
                .input(dust, Caesium, 3)
                .output(dust, FullereneSuperconductiveDust, 10)
                .buildAndRegister();

        // (CH3)2SiHCl + CH4 -> (CH3)3SiCl + 2H
        CHEMICAL_RECIPES.recipeBuilder().duration(110).EUt(1920)
                .fluidInputs(Dimethyldichlorosilane.getFluid(1000))
                .fluidInputs(Methane.getFluid(1000))
                .fluidOutputs(Trimethylchlorosilane.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(2000))
                .buildAndRegister();

        // 2CH2O2 + 2Br + 4Na + 2H2O -> Br2C2H2O2 + 4NaOH + 2H
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(360).EUt(7680)
                .fluidInputs(FormicAcid.getFluid(2000))
                .fluidInputs(Bromine.getFluid(2000))
                .fluidInputs(Water.getFluid(2000))
                .notConsumable(Trimethylchlorosilane.getFluid(1))
                .input(dust, Sodium, 4)
                .output(dust, SodiumHydroxide, 12)
                .fluidOutputs(Dibromoacrolein.getFluid(1000))
                .fluidOutputs(Hydrogen.getFluid(2000))
                .buildAndRegister();

        // 6NaOH + 4S -> 2Na2S + Na2S2O3 + 3H2O
        BLAST_RECIPES.recipeBuilder().duration(210).EUt(120).blastFurnaceTemp(4500)
                .input(dust, SodiumHydroxide, 18)
                .input(dust, Sulfur, 4)
                .output(dust, SodiumSulfide, 6)
                .output(dust, SodiumThiosulfate, 7)
                .fluidOutputs(Steam.getFluid(3000))
                .buildAndRegister();

        // 2Cl + C2H6 -> C2H5Cl + HCl
        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(3340)
                .fluidInputs(Chlorine.getFluid(2000))
                .fluidInputs(Ethane.getFluid(1000))
                .fluidOutputs(Chloroethane.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .notConsumable(UVA_HALIDE_LAMP.getStackForm())
                .buildAndRegister();

        // C2H4Cl2 + 2Na2S2O3 + C2H2Br2O2 -> C4H4S2Br2 + 2NaHSO4 + 2NaCl
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(7680)
                .input(dust, SodiumThiosulfate, 14)
                .fluidInputs(Chloroethane.getFluid(1000))
                .fluidInputs(Dibromoacrolein.getFluid(1000))
                .output(dust, Salt, 4)
                .output(dust, SodiumBisulfate, 14)
                .fluidOutputs(Bromohydrothiine.getFluid(1000))
                .buildAndRegister();

        // C4H4S2Br2 + 2Se + 2C4H9Li -> C4H4S2Li2Se2 + 2C4H9Br
        CHEMICAL_RECIPES.recipeBuilder().duration(290).EUt(30720)
                .fluidInputs(Bromohydrothiine.getFluid(1000))
                .fluidInputs(ButylLithium.getFluid(2000))
                .input(dust, Selenium, 2)
                .fluidOutputs(Bromobutane.getFluid(2000))
                .output(dust, Lithiumthiinediselenide, 14)
                .buildAndRegister();

        // C3H6 + C4H8 -> C3H4 + C4H10
        CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(480)
                .fluidInputs(Propene.getFluid(1000))
                .fluidInputs(Butene.getFluid(1000))
                .notConsumable(dust, DehydrogenationCatalyst, 1)
                .fluidOutputs(Propadiene.getFluid(1000))
                .fluidOutputs(Butane.getFluid(1000))
                .buildAndRegister();

        // TiCl4 + 2 C3H4 + 2 C2H2 -> 2 HCl + (C5H5)2Cl2Ti
        CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(7680)
                .fluidInputs(TitaniumTetrachloride.getFluid(1000))
                .fluidInputs(Propadiene.getFluid(2000))
                .fluidInputs(Acetylene.getFluid(2000))
                .notConsumable(dust, ScandiumTriflate, 1)
                .fluidOutputs(HydrochloricAcid.getFluid(2000))
                .output(dust, TitaniumCyclopentadienyl, 23)
                .buildAndRegister();

        // C2F4 + 2C4H4S2Li2Se2 -> C10H8S4Se4 + 4LiF
        BLAST_RECIPES.recipeBuilder().duration(7920).EUt(120).blastFurnaceTemp(3500)
                .input(dust, Lithiumthiinediselenide, 28)
                .fluidInputs(Tetrafluoroethylene.getFluid(1000))
                .notConsumable(dust, TitaniumCyclopentadienyl, 1)
                .output(dust, LithiumFluoride, 8)
                .output(dust, BETS, 26)
                .buildAndRegister();

        // This is correct
        // > ok
        BLAST_RECIPES.recipeBuilder().duration(20250).EUt(120).blastFurnaceTemp(5000)
                .input(dust, BETS, 1)
                .fluidInputs(AmmoniumPerrhenate.getFluid(1000))
                .fluidOutputs(Ammonia.getFluid(1000))
                .output(dust, BETSPerrhenate, 1)
                .buildAndRegister();

        // C4H9Br + NaOH -> NaBr + C4H10O
        CHEMICAL_RECIPES.recipeBuilder().duration(40).EUt(120)
                .fluidInputs(Bromobutane.getFluid(1000))
                .input(dust, SodiumHydroxide, 3)
                .output(dust, SodiumBromide, 2)
                .fluidOutputs(Butanol.getFluid(1000))
                .buildAndRegister();

        // NaBr -> Na + Br
        ELECTROLYZER_RECIPES.recipeBuilder().duration(260).EUt(120)
                .input(dust, SodiumBromide, 2)
                .output(dust, Sodium)
                .fluidOutputs(Bromine.getFluid(1000))
                .buildAndRegister();

        // 2Fr + C2H2 -> Fr2C2 + 2H
        CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(480)
                .input(dust, Francium, 2)
                .fluidInputs(Acetylene.getFluid(1000))
                .output(dust, FranciumCarbide, 4)
                .fluidOutputs(Hydrogen.getFluid(2000))
                .buildAndRegister();

        // 4B + 3C -> B4C3
        BLAST_RECIPES.recipeBuilder().duration(550).EUt(120).blastFurnaceTemp(4000)
                .input(dust, Boron, 4)
                .input(dust, Carbon, 3)
                .output(dust, BoronCarbide, 7)
                .buildAndRegister();

        // 2Fr2C2 + B4C3 -> Fr4B4C7
        CHEMICAL_RECIPES.recipeBuilder().duration(260).EUt(1920)
                .input(dust, FranciumCarbide, 8)
                .input(dust, BoronCarbide, 7)
                .output(dust, BoronFranciumCarbide, 15)
                .buildAndRegister();

        // At + H2SO4 -> [At + H2O + SO3]
        MIXER_RECIPES.recipeBuilder().duration(140).EUt(7680)
                .input(dust, Astatine)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(AstatideSolution.getFluid(1000))
                .buildAndRegister();

        // 3[At + H2O + SO3] + Ho + Th + Cn + Fl -> 3H2SO4 + [3At + Ho + Th + Cn + Fl]
        MIXER_RECIPES.recipeBuilder().duration(380).EUt(30720)
                .fluidInputs(AstatideSolution.getFluid(3000))
                .input(dust, Holmium)
                .input(dust, Thulium)
                .input(dust, Copernicium)
                .input(dust, MetastableFlerovium)
                .fluidOutputs(SulfuricAcid.getFluid(3000))
                .output(dust, MixedAstatideSalts, 7)
                .buildAndRegister();

        // Fr4B4C7 + 2[3At + Ho + Th + Cn + Fl] = B4C7Fr4At6Ho2Th2Fl2Cn2
        BLAST_RECIPES.recipeBuilder().duration(15000).EUt(120).blastFurnaceTemp(11300)
                .input(dust, BoronFranciumCarbide, 15)
                .input(dust, MixedAstatideSalts, 14)
                .output(dust, BorocarbideDust, 29)
                .buildAndRegister();

        // 6I + 6[NaOH + H2O] -> NaIO3 + 5NaI + 9H2O
        ELECTROLYZER_RECIPES.recipeBuilder().duration(260).EUt(1920)
                .input(dust, Iodine, 6)
                .fluidInputs(SodiumHydroxideSolution.getFluid(6000))
                .output(dust, SodiumIodate, 5)
                .output(dust, SodiumIodide, 10)
                .fluidOutputs(Water.getFluid(9000))
                .buildAndRegister();

        // 3[Cu + H2SO4] + 6NaOH + NaI + 3SO3 -> NaIO3 + 3Cu + 3Na + 3NaSO4 + 6H2O + 3SO2
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(290).EUt(1920)
                .fluidInputs(CopperSulfateSolution.getFluid(3000))
                .fluidInputs(SulfurTrioxide.getFluid(3000))
                .input(dust, SodiumIodide, 2)
                .input(dust, SodiumHydroxide, 18)
                .output(dust, SodiumIodate, 5)
                .output(dust, Copper, 3)
                .output(dust, Sodium, 3)
                .fluidOutputs(SodiumSulfateSolution.getFluid(3000))
                .fluidOutputs(Water.getFluid(6000))
                .fluidOutputs(SulfurDioxide.getFluid(3000))
                .buildAndRegister();

        // NaIO3 + NaClO -> NaIO4 + NaCl
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(1920)
                .input(dust, SodiumIodate, 5)
                .input(dust, SodiumHypochlorite, 3)
                .output(dust, SodiumPeriodate, 6)
                .output(dust, Salt, 2)
                .buildAndRegister();

        // 3NaIO4 + 4Ru + 8NaOH -> 3NaI + 4Na2RuO4 + 4H2O
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(220).EUt(480)
                .input(dust, SodiumPeriodate, 18)
                .input(dust, Ruthenium, 4)
                .input(dust, SodiumHydroxide, 24)
                .output(dust, SodiumIodide, 6)
                .output(dust, SodiumRuthenate, 28)
                .fluidOutputs(Water.getFluid(4000))
                .buildAndRegister();

        // Sg + 2 NaOH + 6 F + 2H2O -> 6HF + Na2SgO4
        CHEMICAL_RECIPES.recipeBuilder().duration(820).EUt(30720)
                .input(dust, Seaborgium)
                .input(dust, SodiumHydroxide, 6)
                .fluidInputs(Fluorine.getFluid(6000))
                .fluidInputs(Water.getFluid(2000))
                .fluidOutputs(HydrofluoricAcid.getFluid(6000))
                .output(dust, SodiumSeaborgate, 7)
                .buildAndRegister();

        // Sr + 2Cl -> SrCl2
        CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(120)
                .input(dust, Strontium)
                .fluidInputs(Chlorine.getFluid(2000))
                .output(dust, StrontiumChloride, 3)
                .buildAndRegister();

        // Na2O4Ru + Na2SgO4 + 2SrCl2 -> 4NaCl + RuSgSr2O8
        BLAST_RECIPES.recipeBuilder().duration(1360).EUt(120).blastFurnaceTemp(4500)
                .input(dust, SodiumRuthenate, 7)
                .input(dust, SodiumSeaborgate, 5)
                .input(dust, StrontiumChloride, 6)
                .output(dust, Salt, 8)
                .output(dust, StrontiumSuperconductorDust, 12)
                .buildAndRegister();

        // Os + 4O -> OsO4
        ARC_FURNACE_RECIPES.recipeBuilder().duration(520).EUt(4800)
                .input(dust, Osmium)
                .fluidInputs(Oxygen.getFluid(4000))
                .output(dust, OsmiumTetroxide, 5)
                .buildAndRegister();

        // NaIO4 + C3H6O -> NaIO3 + CH2O + C2H4O
        CHEMICAL_RECIPES.recipeBuilder().duration(220).EUt(7680)
                .notConsumable(dust, OsmiumTetroxide, 1)
                .input(dust, SodiumPeriodate, 6)
                .fluidInputs(Acetone.getFluid(1000))
                .output(dust, SodiumIodate, 5)
                .fluidOutputs(Formaldehyde.getFluid(1000))
                .fluidOutputs(Acetaldehyde.getFluid(1000))
                .buildAndRegister();

        // C4H10 + 2Br -> C4H9Br + HBr
        CHEMICAL_RECIPES.recipeBuilder().duration(210).EUt(7680)
                .notConsumable(UVA_HALIDE_LAMP.getStackForm())
                .fluidInputs(Butane.getFluid(1000))
                .fluidInputs(Bromine.getFluid(2000))
                .fluidOutputs(Bromobutane.getFluid(1000))
                .fluidOutputs(HydrobromicAcid.getFluid(1000))
                .buildAndRegister();

        // Ir + 2O -> IrO2
        BLAST_RECIPES.recipeBuilder().duration(280).EUt(120).blastFurnaceTemp(700)
                .notConsumable(dust, Salt)
                .input(dust, Iridium)
                .fluidInputs(Oxygen.getFluid(2000))
                .output(dust, IridiumDioxide, 3)
                .buildAndRegister();

        // Kr + 2F -> KrF2
        CHEMICAL_RECIPES.recipeBuilder().duration(170).EUt(480)
                .notConsumable(UVA_HALIDE_LAMP.getStackForm())
                .fluidInputs(Krypton.getFluid(1000))
                .fluidInputs(Fluorine.getFluid(2000))
                .fluidOutputs(KryptonDifluoride.getFluid(1000))
                .buildAndRegister();

        // Mn + KrF2 -> MnF2 + Kr
        CHEMICAL_RECIPES.recipeBuilder().duration(170).EUt(480)
                .input(dust, Manganese)
                .fluidInputs(KryptonDifluoride.getFluid(1000))
                .output(dust, ManganeseFluoride, 3)
                .fluidOutputs(Krypton.getFluid(1000))
                .buildAndRegister();

        // MnF2 + O + H2O -> MnO2 + 2HF
        CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(120)
                .input(dust, ManganeseFluoride, 3)
                .fluidInputs(Water.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(1000))
                .output(dust, Pyrolusite, 3)
                .fluidOutputs(HydrofluoricAcid.getFluid(2000))
                .buildAndRegister();

        // C6H6O + H2O2 + H2O + 4Cl + 2C2H2O -> C10H10O6 + 4HCl
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(122880)
                .fluidInputs(Phenol.getFluid(1000))
                .fluidInputs(HydrogenPeroxide.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(4000))
                .fluidInputs(Ethenone.getFluid(2000))
                .fluidOutputs(PhenylenedioxydiaceticAcid.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(4000))
                .buildAndRegister();

        // NaSCN + HCl + 2CH3CH2NH2 -> NH3 + (C2H5NH)2CS + NaCl
        CHEMICAL_RECIPES.recipeBuilder().duration(210).EUt(30720)
                .fluidInputs(SodiumThiocyanate.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidInputs(Ethylamine.getFluid(2000))
                .fluidOutputs(Ammonia.getFluid(1000))
                .fluidOutputs(Diethylthiourea.getFluid(1000))
                .output(dust, Salt, 2)
                .buildAndRegister();

        // C10H10O6 + 2(C2H5NH)2CS + 2SOCl2 -> 2SO2 + 4HCl + C20H30N4O4S2
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(250).EUt(122880)
                .fluidInputs(Diethylthiourea.getFluid(2000))
                .fluidInputs(ThionylChloride.getFluid(2000))
                .fluidInputs(PhenylenedioxydiaceticAcid.getFluid(1000))
                .fluidOutputs(SulfurDioxide.getFluid(2000))
                .fluidOutputs(HydrochloricAcid.getFluid(4000))
                .fluidOutputs(Isophthaloylbisdiethylthiourea.getFluid(1000))
                .buildAndRegister();

        // Hs + 4Cl -> HsCl4
        BLAST_RECIPES.recipeBuilder().duration(930).EUt(120).blastFurnaceTemp(12000)
                .input(dust, MetastableHassium)
                .fluidInputs(Chlorine.getFluid(4000))
                .output(dust, HassiumChloride, 5)
                .buildAndRegister();

        // Re + 5Cl -> ReCl5
        BLAST_RECIPES.recipeBuilder().duration(930).EUt(120).blastFurnaceTemp(12500)
                .input(dust, Rhenium)
                .fluidInputs(Chlorine.getFluid(5000))
                .output(dust, RheniumChloride, 6)
                .buildAndRegister();

        // SbF5 + PCl3 + HF -> SbCl3 + HPF6
        CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(7680)
                .fluidInputs(AntimonyPentafluoride.getFluid(1000))
                .fluidInputs(PhosphorusTrichloride.getFluid(1000))
                .fluidInputs(HydrofluoricAcid.getFluid(1000))
                .output(dust, AntimonyTrichloride, 4)
                .fluidOutputs(FluorophosphoricAcid.getFluid(1000))
                .buildAndRegister();

        // SbCl3 + 3HF -> SbF3 + 3HCl
        CHEMICAL_RECIPES.recipeBuilder().duration(210).EUt(480)
                .input(dust, AntimonyTrichloride, 4)
                .fluidInputs(HydrofluoricAcid.getFluid(3000))
                .output(dust, AntimonyTrifluoride, 4)
                .fluidOutputs(HydrochloricAcid.getFluid(3000))
                .buildAndRegister();

        // 3C20H30N4O4S2 + ReCl5 + HsCl4 + TlCl + HPF6 -> ReHsTlC60PN12H84S6O12F6 + 7HCl + 3Cl
        LARGE_CHEMICAL_RECIPES.recipeBuilder().duration(620).EUt(845000)
                .input(dust, RheniumChloride, 6)
                .input(dust, HassiumChloride, 5)
                .input(dust, ThalliumChloride, 2)
                .fluidInputs(Isophthaloylbisdiethylthiourea.getFluid(3000))
                .fluidInputs(FluorophosphoricAcid.getFluid(1000))
                .output(dust, RheniumHassiumThalliumIsophtaloylbisdiethylthioureaHexafluorophosphate, 125)
                .fluidOutputs(HydrochloricAcid.getFluid(7000))
                .fluidOutputs(Chlorine.getFluid(3000))
                .buildAndRegister();

        ADVANCED_MIXER_RECIPES.recipeBuilder().duration(270).EUt(250000)
                .input(dust, Naquadah)
                .input(dust, NaquadahEnriched)
                .input(dust, Naquadria)
                .input(dust, Vibranium)
                .input(dust, Adamantium)
                .input(dust, Taranium)
                .input(dust, Trinium)
                .input(dust, Duranium)
                .input(dust, Tritanium)
                .output(dust, Legendarium, 9)
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().duration(520).EUt(445000)
                .input(dust, Cerium)
                .input(dust, Caesium)
                .input(dust, Cobalt, 2)
                .input(dust, Indium, 10)
                .fluidInputs(CosmicComputingMix.getFluid(1000))
                .output(dust, ChargedCaesiumCeriumCobaltIndium, 14)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().duration(290).EUt(320000)
                .fluidInputs(Cycloparaphenylene.getFluid(200))
                .fluidInputs(Methane.getFluid(800))
                .input(dust, LanthanumEmbeddedFullerene, 1)
                .notConsumable(plate, Rhenium)
                .output(dust, LanthanumFullereneNanotubes, 1)
                .buildAndRegister();

        // 2La + H2SO4 = La2O3 + H2S + O
        CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(120)
                .input(dust, Lanthanum, 2)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .output(dust, LanthanumOxide, 5)
                .fluidOutputs(HydrogenSulfide.getFluid(1000))
                .fluidOutputs(Oxygen.getFluid(1000))
                .buildAndRegister();
    }
}
