package com.company;
import java.util.ArrayList;


public class Repertoire extends Basic{

    private ArrayList<Fichier> fichiers;
    private ArrayList<Repertoire> repertoires;

    public Repertoire(String pnom, int ptaille) {
        this.nom = pnom;
        this.taille = ptaille;
        this.fichiers = new ArrayList<Fichier>();
        this.repertoires = new ArrayList<Repertoire>();
    }

    

    public boolean ajoutFichier (Fichier f)
    {
        fichiers.add(f);
        return true;
    }

    public boolean ajoutRepertoire (Repertoire r)
    {
        if (r == this)
            return false;
        else {
            repertoires.add(r);
            return true;
        }
    }

    public int nbFichiers ()
    {
        int taille = this.fichiers.size();
        System.out.println("Il y a " + taille + " fichiers");
        return taille;
    }

    public int nbRepertoires ()
    {
        int taille = this.repertoires.size();
        System.out.println("Il y a " + taille + " repertoires");
        return taille;
    }

    @Override
    public int getTaille() {

        if (this.repertoires.isEmpty())
            return 1;
        for(int j = 0; j<this.repertoires.size(); j++)
            return 1 + this.repertoires.get(j).getTaille();

        return 0;
    }
}
