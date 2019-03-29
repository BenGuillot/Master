package fr.uvsq.info.poo;

public class Main {

	public static void main(String[] args) {
		System.out.println("BEGIN");
		Client c1 = new Client("C1");
		Client c2 = new Client("C2");
		Client c3 = new Client("C3");
		Serveur s = new Serveur();

		s.connecter(c1);
		s.connecter(c2);
		c3.seConnecter(s);
		c3.envoyer("Bonjour");
		System.out.println("END");
	}
}
