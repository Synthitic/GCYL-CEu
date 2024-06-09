package com.fulltrix.gcyl.worldgen;

import gregtech.api.GTValues;
import gregtech.api.util.GTLog;
import gregtech.api.worldgen.config.WorldGenRegistry;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraftforge.fml.common.Loader;
import org.apache.commons.io.IOUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WorldGenRegister {
    private static final int FLUID_VEIN_VERSION = 2;
    private static final int ORE_VEIN_VERSION = 1;
    private final Int2ObjectMap<String> namedDimensions = new Int2ObjectOpenHashMap<>();

    public static void init() throws IOException {

        try {
            WorldGenRegister.removeGTConfigs();
        } catch (IOException e) {
            GTLog.logger.fatal("Failed to replace GT worldgen configs", e);
        }

        try {
            WorldGenRegister.copyCustomConfigs();
        } catch (IOException exception) {
            GTLog.logger.fatal("Failed to add GA worldgen", exception);
        }

        WorldGenRegistry.INSTANCE.reinitializeRegisteredVeins();

    }

    private static void removeGTConfigs() throws IOException {
        Path configPath = Loader.instance().getConfigDir().toPath().resolve(GTValues.MODID);
        Path worldgenVeinRootPath = configPath.resolve("worldgen/vein");
        Path worldgenFluidRootPath = configPath.resolve("worldgen/fluid");
        Path gtUnpacked = configPath.resolve("worldgen_extracted.json");
        Path extractedLock = configPath.resolve("worldgen_extracted_gcyl");
        String[] dims = new String[]{"end", "nether", "overworld"};
        if (Files.exists(gtUnpacked) && !Files.exists(extractedLock)) {
            for (String dim : dims) {
                Path currentDir = worldgenVeinRootPath.resolve(dim);
                List<Path> configs = Files.walk(currentDir)
                        .filter(file -> Files.isRegularFile(file))
                        .filter(file -> file.toString().endsWith(".json"))
                        .filter(file ->
                                file.getFileName().toString().startsWith("platinum_vein.json")
                                        || file.getFileName().toString().startsWith("olivine_vein.json")
                                        || file.getFileName().toString().startsWith("sulfur_vein.json")
                                        || file.getFileName().toString().startsWith("olivine_vien2.json")
                                        || file.getFileName().toString().startsWith("magnetite_vein.json")
                                        || file.getFileName().toString().startsWith("pitchblende_vein.json")
                                        || file.getFileName().toString().startsWith("pitchblende_vein2.json")
                                        || file.getFileName().toString().startsWith("naquadah_vein.json")
                                        || file.getFileName().toString().startsWith("tungstate_vein.json")
                                        || file.getFileName().toString().startsWith("bauxite_vein.json")
                                        || file.getFileName().toString().startsWith("scheelite_vein.json")
                                        || file.getFileName().toString().startsWith("sheldonite_vein.json")
                                        || file.getFileName().toString().startsWith("banded_iron_vein.json")
                                        || file.getFileName().toString().startsWith("beryllium_vein.json")
                                        || file.getFileName().toString().startsWith("certus_quartz_vein.json")
                                        || file.getFileName().toString().startsWith("manganese_vein.json")
                                        || file.getFileName().toString().startsWith("molybdenum_vein.json")
                                        || file.getFileName().toString().startsWith("monazite_vein.json")
                                        || file.getFileName().toString().startsWith("nether_quartz_vein.json")
                                        || file.getFileName().toString().startsWith("redstone_vein.json")
                                        || file.getFileName().toString().startsWith("saltpeter_vein.json")
                                        || file.getFileName().toString().startsWith("tetrahedrite_vein.json")
                                        || file.getFileName().toString().startsWith("topaz_vein.json")
                                        || file.getFileName().toString().startsWith("apatite_vein.json")
                                        || file.getFileName().toString().startsWith("basalt_sphere.json")
                                        || file.getFileName().toString().startsWith("black_granite_sphere.json")
                                        || file.getFileName().toString().startsWith("cassiterite_vein.json")
                                        || file.getFileName().toString().startsWith("coal_vein.json")
                                        || file.getFileName().toString().startsWith("copper_tin_vein.json")
                                        || file.getFileName().toString().startsWith("copper_vein.json")
                                        || file.getFileName().toString().startsWith("diamond_vein.json")
                                        || file.getFileName().toString().startsWith("galena_vein.json")
                                        || file.getFileName().toString().startsWith("garnet_vein.json")
                                        || file.getFileName().toString().startsWith("garnet_tin_vein.json")
                                        || file.getFileName().toString().startsWith("iron_vein.json")
                                        || file.getFileName().toString().startsWith("lapis_vein.json")
                                        || file.getFileName().toString().startsWith("lubricant_vein.json")
                                        || file.getFileName().toString().startsWith("marble_sphere.json")
                                        || file.getFileName().toString().startsWith("mica_vein.json")
                                        || file.getFileName().toString().startsWith("mineral_sand_vein.json")
                                        || file.getFileName().toString().startsWith("nickel_vein.json")
                                        || file.getFileName().toString().startsWith("oilsands_vein.json")
                                        || file.getFileName().toString().startsWith("raw_oil_sphere.json")
                                        || file.getFileName().toString().startsWith("red_granite_sphere.json")
                                        || file.getFileName().toString().startsWith("salts_vein.json")
                                        || file.getFileName().toString().startsWith("sapphire_vein.json")
                        )
                        .collect(Collectors.toList());
                for (Path config : configs) {
                    GTLog.logger.info(String.format("Removing GT worldgen/vein config %s", config.getFileName().toString()));
                    Files.delete(config);
                }
            }

            for (String dim : dims) {
                Path currentDir = worldgenFluidRootPath.resolve(dim);
                if (dim.equals("end")) {
                    continue;
                }
                List<Path> configs = Files.walk(currentDir)
                        .filter(file -> Files.isRegularFile(file))
                        .filter(file -> file.toString().endsWith(".json"))
                        .filter(file ->
                                file.getFileName().toString().startsWith("heavy_oil_deposit.json")
                                        || file.getFileName().toString().startsWith("light_oil_deposit.json")
                                        || file.getFileName().toString().startsWith("natural_gas_deposit.json")
                                        || file.getFileName().toString().startsWith("oil_deposit.json")
                                        || file.getFileName().toString().startsWith("raw_oil_deposit.json")
                                        || file.getFileName().toString().startsWith("salt_water_deposit.json")
                                        || file.getFileName().toString().startsWith("lava_deposit.json")
                        )
                        .collect(Collectors.toList());
                for (Path config : configs) {
                    GTLog.logger.info(String.format("Removing GT worldgen/fluid config %s", config.getFileName().toString()));
                    Files.delete(config);
                }
            }
        }
    }

    private static void copyCustomConfigs() throws IOException {
        Path configPath = Loader.instance().getConfigDir().toPath().resolve(GTValues.MODID);
        Path worldgenVeinRootPath = configPath.resolve("worldgen/vein");
        Path worldgenFluidRootPath = configPath.resolve("worldgen/fluid");
        Path jarFileExtractLock = configPath.resolve("worldgen_extracted_gcyl");
        if (!Files.exists(worldgenVeinRootPath)) {
            Files.createDirectories(worldgenVeinRootPath);
        }


        if (!Files.exists(jarFileExtractLock) || !Files.list(worldgenVeinRootPath).peek(path -> GTLog.logger.info(path)).findFirst().isPresent()) {
            if (!Files.exists(jarFileExtractLock)) {
                Files.createFile(jarFileExtractLock);
            }
            WorldGenRegister.extractJarVeinDefinitions(configPath, worldgenVeinRootPath);
            WorldGenRegister.extractJarVeinDefinitions(configPath, worldgenFluidRootPath);
        }

    }


    private static void extractJarVeinDefinitions(Path configPath, Path targetPath) throws IOException {
        // The path of the worldgen folder in the config folder
        Path worldgenRootPath = configPath.resolve("worldgen");
        // The path of the physical vein folder in the config folder
        Path oreVeinRootPath = worldgenRootPath.resolve("vein");
        // The path of the bedrock fluid vein folder in the config folder
        Path bedrockFluidVeinRootPath = worldgenRootPath.resolve("fluid");
        // The path of the named dimensions file in the config folder
        Path dimensionsRootPath = configPath.resolve("dimensions.json");
        // THe path of the lock file in the config folder
        Path extractLockPath = configPath.resolve("worldgen_extracted_gcyl.json");
        FileSystem zipFileSystem = null;
        try {
            URL sampleUrl = WorldGenRegistry.class.getResource("/assets/gcyl/");
            if (sampleUrl == null) throw new FileNotFoundException("Could not find .gtassetsroot");
            URI sampleUri = sampleUrl.toURI();
            // The Path for representing the worldgen folder in the assets folder in the Gregtech resources folder in
            // the jar
            Path worldgenJarRootPath;
            // The Path for representing the vein folder in the vein folder in the assets folder in the Gregtech
            // resources folder in the jar
            Path oreVeinJarRootPath;
            // The Path for representing the fluid folder in the vein folder in the assets folder in the Gregtech
            // resources folder in the jar
            Path bedrockFluidJarRootPath;
            if (sampleUri.getScheme().equals("jar") || sampleUri.getScheme().equals("zip")) {
                zipFileSystem = FileSystems.newFileSystem(sampleUri, Collections.emptyMap());
                worldgenJarRootPath = zipFileSystem.getPath("/assets/gcyl/worldgen");
                oreVeinJarRootPath = zipFileSystem.getPath("/assets/gcyl/worldgen/vein");
                bedrockFluidJarRootPath = zipFileSystem.getPath("/assets/gcyl/worldgen/fluid");
            } else if (sampleUri.getScheme().equals("file")) {
                URL url = WorldGenRegistry.class.getResource("/assets/gcyl/worldgen");
                if (url == null) throw new FileNotFoundException("Could not find /assets/gcyl/worldgen");
                worldgenJarRootPath = Paths.get(url.toURI());

                url = WorldGenRegistry.class.getResource("/assets/gcyl/worldgen/vein");
                if (url == null) throw new FileNotFoundException("Could not find /assets/gcyl/worldgen/vein");
                oreVeinJarRootPath = Paths.get(url.toURI());

                url = WorldGenRegistry.class.getResource("/assets/gcyl/worldgen/fluid");
                if (url == null) throw new FileNotFoundException("Could not find /assets/gcyl/worldgen/fluid");
                bedrockFluidJarRootPath = Paths.get(url.toURI());
            } else {
                throw new IllegalStateException(
                        "Unable to locate absolute path to worldgen root directory: " + sampleUri);
            }

            // Attempts to extract the worldgen definition jsons
            if (targetPath.compareTo(oreVeinRootPath) == 0) {
                GTLog.logger.info("Attempting extraction of standard worldgen definitions from {} to {}",
                        oreVeinJarRootPath, oreVeinRootPath);
                // Find all the default worldgen files in the assets folder
                List<Path> jarFiles;
                try (Stream<Path> stream = Files.walk(oreVeinJarRootPath)) {
                    jarFiles = stream.filter(Files::isRegularFile).collect(Collectors.toList());
                }

                // Replaces or creates the default worldgen files
                for (Path jarFile : jarFiles) {
                    Path worldgenPath = oreVeinRootPath.resolve(oreVeinJarRootPath.relativize(jarFile).toString());
                    Files.createDirectories(worldgenPath.getParent());
                    Files.copy(jarFile, worldgenPath, StandardCopyOption.REPLACE_EXISTING);
                }
                GTLog.logger.info("Extracted {} builtin worldgen vein definitions into vein folder", jarFiles.size());
            } else if (targetPath.compareTo(bedrockFluidVeinRootPath) == 0) {
                GTLog.logger.info("Attempting extraction of standard worldgen definitions from {} to {}",
                        bedrockFluidJarRootPath, bedrockFluidVeinRootPath);
                // Find all the default worldgen files in the assets folder
                List<Path> jarFiles;
                try (Stream<Path> stream = Files.walk(bedrockFluidJarRootPath)) {
                    jarFiles = stream.filter(Files::isRegularFile).collect(Collectors.toList());
                }

                // Replaces or creates the default worldgen files
                for (Path jarFile : jarFiles) {
                    Path worldgenPath = bedrockFluidVeinRootPath
                            .resolve(bedrockFluidJarRootPath.relativize(jarFile).toString());
                    Files.createDirectories(worldgenPath.getParent());
                    Files.copy(jarFile, worldgenPath, StandardCopyOption.REPLACE_EXISTING);
                }
                GTLog.logger.info("Extracted {} builtin worldgen bedrock fluid definitions into fluid folder",
                        jarFiles.size());
            }
            // Attempts to extract the named dimensions json folder
            else if (targetPath.compareTo(dimensionsRootPath) == 0) {
                GTLog.logger.info("Attempting extraction of standard dimension definitions from {} to {}",
                        worldgenJarRootPath, dimensionsRootPath);

                Path dimensionFile = worldgenJarRootPath.resolve("dimensions.json");

                Path worldgenPath = dimensionsRootPath
                        .resolve(worldgenJarRootPath.relativize(worldgenJarRootPath).toString());
                Files.copy(dimensionFile, worldgenPath, StandardCopyOption.REPLACE_EXISTING);

                GTLog.logger.info("Extracted builtin dimension definitions into worldgen folder");
            }
            // Attempts to extract lock txt file
            else if (targetPath.compareTo(extractLockPath) == 0) {
                Path extractLockFile = worldgenJarRootPath.resolve("worldgen_extracted_gcyl.json");

                Path worldgenPath = extractLockPath
                        .resolve(worldgenJarRootPath.relativize(worldgenJarRootPath).toString());
                Files.copy(extractLockFile, worldgenPath, StandardCopyOption.REPLACE_EXISTING);

                GTLog.logger.info("Extracted jar lock file into worldgen folder");
            }

        } catch (URISyntaxException impossible) {
            // this is impossible, since getResource always returns valid URI
            throw new RuntimeException(impossible);
        } finally {
            if (zipFileSystem != null) {
                // close zip file system to avoid issues
                IOUtils.closeQuietly(zipFileSystem);
            }
        }
    }


}
