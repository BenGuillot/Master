package Annuaire;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

public final class Personne extends AbstractGroupe {
	private String Nom;
	private String Prenom;
	private String Fonction;
	private LocalDate dateNaissance;
	private ArrayList<Annuaire.Numero> Numero;
	
	public static class Builder{
		private String Nom;
		private String Prenom;
		private String Fonction;
		private java.time.LocalDate dateNaissance;	
		private ArrayList<Numero> Numero = new ArrayList<Numero>();	
		
		public Builder(String Nom, String Prenom, String Fonction, LocalDate dateNaissance) {
			this.Nom = Nom;
			this.Prenom = Prenom;
			this.Fonction = Fonction;
			this.dateNaissance = dateNaissance;
		}
		
		public Builder Numero(Numero Numero) {
			this.Numero.add(Numero);
			return this	;
		}
		
		public Personne build() {
			return new Personne(this);
		}
	}
	
	public Personne(Builder builder) {
		this.Nom = builder.Nom;
		this.Prenom = builder.Prenom;
		this.dateNaissance = builder.dateNaissance;
		this.Numero = builder.Numero;
	}

	@Override
	public void affichageProfondeur() {
		System.out.println("Nom : "+Nom);
		System.out.println("Prenom : "+Prenom);
		System.out.println("Fonction : "+Fonction);
		System.out.println("Date de Naissance : "+dateNaissance);
		for(Numero num : this.Numero) {
			num.afficher();}
	}
	
	@Override
	public void affichageLargeur() {	
	}

}
