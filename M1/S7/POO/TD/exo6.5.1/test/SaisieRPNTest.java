import static org.junit.Assert.*;
import java.util.Scanner;

import org.junit.Test;

public class SaisieRPNTest {
	Scanner scanner1 = new Scanner("2");
	Scanner scanner2 = new Scanner("2 3 4 + *");
	
	
	@Test
	public void testLecture() {
		String s = scanner1.next();
		assertTrue((s).equals("2"));
	}

	

}
