import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestNoLoop {
	@Test
	void testNoLoopRemoveBetween() throws FileNotFoundException {
		File f = new File("src/ZoomaList.java");
		Scanner s = new Scanner(f);

		boolean foundLoop = false;
		String line = "";
		while (s.hasNextLine()) {
			line = s.nextLine();
			if (line.contains("removeBetween(DListIterator ")) {
				break;
			}
		}

		while (s.hasNextLine()) {
			if (line.contains("for(") || line.contains("while(")) {
				foundLoop = true;
				break;
			}
			line = s.nextLine();
		}
		s.close();
		assertFalse(foundLoop);

	}

}
