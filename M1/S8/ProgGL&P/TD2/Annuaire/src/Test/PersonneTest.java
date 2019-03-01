package Test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Annuaire.Personne;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.*;

public class PersonneTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAffichageProfondeur() {
		fail("Not yet implemented");
	}

	@Test
	public void testAffichageLargeur() {
		fail("Not yet implemented");
	}

	@Test
	public void testPersonne() throws FileNotFoundException, IOException {
		String dataFile = "";
		try( ObjectOutputStream out = new ObjectOutputStream(
						new BufferedOutputStream(
								new FileOutputStream(dataFile)))){
			out.writeObject(new Personne.Builder("M","L","Etu", null));
		}
	}

}
