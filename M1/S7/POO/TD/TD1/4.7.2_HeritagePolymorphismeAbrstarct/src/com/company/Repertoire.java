package com.company;
import java.util.ArrayList;


public class Repertoire extends Basic{

    private ArrayList<Fichier> fichiers;
    private ArrayList<Repertoire> Repertoires;

    public Repertoire(String pnom, int ptaille) {
        this.nom = pnom;
        this.taille = ptaille;
    }

    @Override
    public int getTaille() {
        return 0;
    }


}
