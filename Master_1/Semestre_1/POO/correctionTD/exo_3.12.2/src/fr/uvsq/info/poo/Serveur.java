package fr.uvsq.info.poo;

import java.util.ArrayList;
import java.util.List;

public class Serveur {
	List<Client> clients;
	
	public Serveur() {
		this.clients = new ArrayList<Client>();
	}
	
	public boolean connecter(Client client) {
		this.clients.add(client);
		return true;
	}
	
	public void diffuser(String message) {
		System.out.println(this + " BROADCAST " + message);
		for (Client client : this.clients) {
			client.recevoir(message);
		}
	}
	
	public String toString() {
		return "Serveur";
	}
}
