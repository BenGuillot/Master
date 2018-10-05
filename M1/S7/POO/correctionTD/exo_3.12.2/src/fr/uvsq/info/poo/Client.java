package fr.uvsq.info.poo;

public class Client {
	String nom;
	Serveur serveur;
	
	public Client(String nom) {
		this.nom = nom;
	}
	
	public void envoyer(String message) {
		System.out.println(this  + " SEND " + message);
		this.serveur.diffuser(message);
	}
	
	public void recevoir(String message) {
		System.out.println(this  + " RECV " + message);
	}
	
	public boolean seConnecter(Serveur serveur) {
		boolean ok = serveur.connecter(this);
		if (ok) {
			this.serveur = serveur;
		} else {
			System.out.println(this + " cannot connect to " + serveur);
		}
		return ok;
	}
	
	@Override
	public String toString() {
		return "Client(" + this.nom + ")";
	}
}
