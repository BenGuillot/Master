package srp;
// import java.io.*;

public class Affichage {
	Affichage(){
	}
	
	public void afficheCoordonnees(Employe e) {
		System.out.println(e.getNom() + " , ");
		System.out.println(e.getAdresse() + "\n");
	}
}
