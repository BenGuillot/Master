package com.company;
import java.util.ArrayList;


public class Repertoire extends Basic {

    private ArrayList<Fichier> fichiers;
    private ArrayList<Repertoire> repertoires;

    public Repertoire(String pnom) {
        this.nom = pnom;
        this.taille = 0;
        this.fichiers = new ArrayList<Fichier>();
        this.repertoires = new ArrayList<Repertoire>();
    }


    public boolean ajoutFichier(Fichier f) {
        fichiers.add(f);
        return true;
    }

    public boolean ajoutRepertoire(Repertoire r) {
        if (r == this)
            return false;
        else {
            repertoires.add(r);
            return true;
        }
    }

    public int nbFichiers() {
        int taille = this.fichiers.size();
        //System.out.println("Il y a " + taille + " fichiers dans " + this.nom );
        return taille;
    }

    public int nbRepertoires() {
        int taille = this.repertoires.size();
        //System.out.println("Il y a " + taille + " repertoires dans " + this.nom);
        return taille;
    }

    @Override
    public int getTaille() {

        if (this.repertoires.isEmpty())
            return 1 + this.fichiers.size();
        for (int i = 0; i < this.repertoires.size(); i++) {
            return 1 + this.fichiers.size() + this.repertoires.get(i).getTaille();
        }
        return 1;
    }
}

