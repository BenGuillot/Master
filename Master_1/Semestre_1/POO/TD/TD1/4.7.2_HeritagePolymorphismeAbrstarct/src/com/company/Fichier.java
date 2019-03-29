package com.company;

public class Fichier extends Basic {

    public Fichier(String pnom) {
        this.nom = pnom;
    }

    @Override
    public int getTaille(){
        return 1;
    }

}
