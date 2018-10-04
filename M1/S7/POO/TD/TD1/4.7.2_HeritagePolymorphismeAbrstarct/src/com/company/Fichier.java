package com.company;

public class Fichier extends Basic {

    public Fichier(String pnom, int ptaille) {
        this.nom = pnom;
        this.taille = ptaille;
    }

    @Override
    public void getTaille(){
        System.out.print(this.taille + "\n");
    }

}
