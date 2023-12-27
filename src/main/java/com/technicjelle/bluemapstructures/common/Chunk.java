package com.technicjelle.bluemapstructures.common;

import java.util.Map;

public interface Chunk {
	Map<String, long[]> getStructureReferences();

	int getDataVersion();
}
