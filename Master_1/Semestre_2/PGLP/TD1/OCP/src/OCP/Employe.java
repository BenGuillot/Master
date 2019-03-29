package OCP;

public class Employe {
	private String Nom;
	private String Adresse;
	private int Anciennete;
	private double Salaire = 1500;
	
	Employe(String nom, String adresse, int anciennete){
		this.setNom(nom);
		this.setAdresse(adresse);
		this.setAnciennete(anciennete);
	}
	
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}

	public String getAdresse() {
		return Adresse;
	}
	public void setAdresse(String adresse) {
		Adresse = adresse;
	}

	public double getSalaire() {
		return Salaire;
	}
	public void setSalaire(double salaire) {
		Salaire = salaire;
	}
	
	public int getAnciennete() {
		return Anciennete;
	}
	public void setAnciennete(int anciennete) {
		Anciennete = anciennete;
	}
	
}

