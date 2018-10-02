package com.company;


import java.util.ArrayList;


public class Serveur {

    private ArrayList<Client> clients;

    public Serveur(){
        clients = new ArrayList<Client>();
    }

    public boolean connecter (Client pclient){
        for(int i=0;i<this.clients.size();i++){
            if(this.clients.get(i)==pclient) return false;
        }
        clients.add(pclient);
        return true;
    }


    public void diffuser(String message){
        for(int i=0;i<this.clients.size();i++){
            this.clients.get(i).recevoir(message);
            System.out.print(this.clients.get(i).recevoir(message));
        }
    }
}


