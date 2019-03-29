package srp;

public class Employe {
	private String Nom;
	private String Adresse;
	private double Salaire;
	
	Employe(String Nom, String Adresse){
		this.setNom(Nom);
		this.setAdresse(Adresse);
	}
	
	public String getNom() {
		return Nom;}
	public void setNom(String nom) {
		Nom = nom;}

	public String getAdresse() {
		return Adresse;}
	public void setAdresse(String adresse) {
		Adresse = adresse;}

	public double getSalaire() {
		return Salaire;}

	public void setSalaire(double salaire) {
		Salaire = salaire;}

}
