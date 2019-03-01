package Test;

import java.io.IOException;

public class PersonneDAOTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void createTest() throws IOException {
		Personne p = new Personne.Builder("Nom", "Prenom", "Fonction", null).build();
		PersonneDAO persDAO = new PersonneDAO();
		persDAO.create(p);
	}

}
