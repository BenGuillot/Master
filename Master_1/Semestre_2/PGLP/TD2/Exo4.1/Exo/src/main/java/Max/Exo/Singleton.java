package Max.Exo;
import java.time.LocalDate;
import java.util.ArrayList;


public enum Singleton {
	TEST;
	
	public static void main( String[] args )
    {
        TEST.run(args);
    }

	public void run(String[] args) {
	
		Numero num = new Numero(); num.add("Fixe", 33, 123456);
		Numero num2 = new Numero(); num2.add("Portable", 33, 987654);
		LocalDate Naissance = LocalDate.of(1997, 6, 27);
		LocalDate Naissance2 = LocalDate.of(1984, 5, 30);
		Personnel p = new Personnel.Builder("Toi","Moi","Prof",	Naissance)
		.Numero(num).build();

		Personnel p2 = new Personnel.Builder("Machin","truc","Employe",Naissance2)
		.Numero(num).Numero(num2).build();
		Groupe groupe = new Groupe();
		groupe.add(p);
		groupe.add(p2);
		groupe.affichageProfondeur();
				
			}

		}
