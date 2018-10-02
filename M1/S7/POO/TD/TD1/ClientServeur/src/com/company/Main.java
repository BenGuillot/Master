package com.company;

public class Main {

    public static void main(String[] args) {
		Client c1=new Client("Fahei");
		Client c2=new Client("Fei");
		Serveur s=new Serveur();
		c1.seConnecter(s);
		c2.seConnecter(s);
		c1.envoyer("For the Alliance");
    }
}
