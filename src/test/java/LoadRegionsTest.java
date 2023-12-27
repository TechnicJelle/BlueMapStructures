import com.flowpowered.math.vector.Vector2i;
import com.technicjelle.bluemapstructures.common.MCA;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;


public class LoadRegionsTest {
	@Test
	public void test_MC_1_20_4() {
		testRegionFolder("MC_1_20_4");
	}

	private void testRegionFolder(final String regionFolderName) {
		Path regionFolder = Paths.get("").resolve("src/test/resources/" + regionFolderName);
		try (final Stream<Path> stream = Files.list(regionFolder)) {
			stream.filter(path -> path.toString().endsWith(".mca")).forEach(this::testMCAFile);
		} catch (IOException e) {
			System.err.println("Error reading region folder");
			e.printStackTrace();
		}
	}

	private void testMCAFile(Path regionFile) {
		System.out.println("Processing region " + regionFile.getFileName().toString());
		final MCA mca = new MCA(regionFile);

		try {
			for (Map.Entry<Vector2i, Set<String>> structureReference : mca.getStructureReferences().entrySet()) {
				System.out.println(structureReference.getKey() + ": " + Arrays.toString(structureReference.getValue().toArray()));
			}
		} catch (IOException e) {
			System.err.println("Error reading region file");
			e.printStackTrace();
		}
	}
}

