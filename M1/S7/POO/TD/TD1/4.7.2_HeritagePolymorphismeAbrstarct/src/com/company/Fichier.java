package com.company;

public class Fichier extends Basic {

    public Fichier(String pnom, int ptaille) {
        this.nom = pnom;
        this.taille = ptaille;
    }

    @Override
    public int getTaille(){
        return 0;
    }

}
