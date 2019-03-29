package Annuaire;

import java.time.LocalDate;
import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Numero num = new Numero(); num.add("Fixe", 33, 123456);
		Numero num2 = new Numero(); num2.add("Portable", 33, 987654);
		LocalDate Naissance = LocalDate.of(1997, 6, 27);
		LocalDate Naissance2 = LocalDate.of(1984, 5, 30);
		Personne p =
			new Personne.Builder("Martin",
					"Laureline", 
					"Etudiante", 
					Naissance)
			.Numero(num)
			.build();
		Personne p2 = new Personne.Builder("Machin", 
				"truc", 
				"Employe",
				Naissance2)
				.Numero(num)
				.Numero(num2)
			.build();
		Groupe groupe = new Groupe();
		groupe.add(p);
		groupe.add(p2);
		
		groupe.affichageProfondeur();
		
	}

}
