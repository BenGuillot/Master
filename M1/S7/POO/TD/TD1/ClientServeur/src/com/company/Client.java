package com.company;

public class Client {
    private String nom;
    private Serveur serveur;

    public Client(String name)
    {
        // initialise instance variables
        this.nom=name;
        this.serveur=null;
    }


    public boolean seConnecter(Serveur pserveur){
        if(pserveur.connecter(this)==true){
            this.serveur=pserveur;
            return true;
        }
        else return false;
    }

    public void envoyer(String message){

        if(this.serveur!=null) this.serveur.diffuser(message);
    }

    public String recevoir (String message){
        return message;
    }
}
