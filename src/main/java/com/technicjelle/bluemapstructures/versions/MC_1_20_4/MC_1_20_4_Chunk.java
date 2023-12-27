package com.technicjelle.bluemapstructures.versions.MC_1_20_4;

import com.technicjelle.bluemapstructures.common.Chunk;
import com.technicjelle.bluemapstructures.common.ChunkWithVersion;
import de.bluecolored.bluenbt.NBTName;

import java.util.Map;

public class MC_1_20_4_Chunk extends ChunkWithVersion implements Chunk {
	private static class Structures {
		@NBTName("References")
		private Map<String, long[]> references;
	}

	@NBTName("structures")
	private Structures structures;


	@Override
	public Map<String, long[]> getStructureReferences() {
		return structures.references;
	}
}
