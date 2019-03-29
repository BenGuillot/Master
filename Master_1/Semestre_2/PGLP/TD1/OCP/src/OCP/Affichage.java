package OCP;

public class Affichage {
	Affichage(){
	}
	
	public void afficheCoordonnees(Employe e) {
		System.out.println(e.getNom() + " , ");
		System.out.println(e.getAdresse() + "\n");
	}
	
	public void afficheSalaire(Employe e) {
		System.out.println(e.getSalaire());
	}
}

