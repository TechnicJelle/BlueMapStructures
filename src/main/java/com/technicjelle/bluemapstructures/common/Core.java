package com.technicjelle.bluemapstructures.common;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3d;
import de.bluecolored.bluemap.api.BlueMapAPI;
import de.bluecolored.bluemap.api.BlueMapMap;
import de.bluecolored.bluemap.api.markers.MarkerSet;
import de.bluecolored.bluemap.api.markers.POIMarker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class Core {
	public static void loadMarkers(Logger logger, BlueMapAPI api) {
		for (BlueMapMap map : api.getMaps()) {
			final Path saveFolder = map.getWorld().getSaveFolder();
			logger.info("Map: " + map.getId() + " " + saveFolder);
			final Path regionFolder = saveFolder.resolve("region");

			try (final Stream<Path> stream = Files.list(regionFolder)) {
				stream.filter(path -> path.toString().endsWith(".mca")).forEach(path -> processMCA(logger, map, path));
			} catch (IOException e) {
				logger.log(Level.SEVERE, "Error reading region folder", e);
			}
		}

		logger.info("Finished loading signs into markers");
	}

	private static void processMCA(Logger logger, BlueMapMap map, Path regionFile) {
		logger.info("Processing region " + regionFile.getFileName().toString());

		final MCA mca = new MCA(regionFile);
		try {
			for (Map.Entry<Vector2i, Set<String>> structureReference : mca.getStructureReferences().entrySet()) {
				final Vector2i structureChunk = structureReference.getKey();
				final String structureNames = Arrays.toString(structureReference.getValue().toArray());
				logger.info(structureChunk + ": " + structureNames);

				final POIMarker poiMarker = POIMarker.builder()
						.label(structureNames)
						.position(new Vector3d(structureChunk.getX() * 16, 64, structureChunk.getY() * 16))
						.build();

				final MarkerSet markerSet = map.getMarkerSets().computeIfAbsent("structures", id -> MarkerSet.builder().label("Structures").toggleable(true).defaultHidden(false).build());

				markerSet.put(structureChunk.toString(), poiMarker);
			}
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Error reading region file", e);
		}
	}
}
