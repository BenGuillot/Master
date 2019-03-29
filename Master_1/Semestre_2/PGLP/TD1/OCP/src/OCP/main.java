package OCP;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employe e = new Employe("Lau", "ChezMoi", 4);
		Employe v = new Vendeur("Toto", "ChezLui", 6, 50.2);
		Affichage a = new Affichage();
		a.afficheCoordonnees(e);
		a.afficheSalaire(e);
		a.afficheSalaire(v);
	}
}

