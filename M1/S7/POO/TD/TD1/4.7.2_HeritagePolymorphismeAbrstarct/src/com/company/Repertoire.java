package com.company;
import java.util.ArrayList;


public class Repertoire extends Basic{

    private ArrayList<Fichier> fichiers;
    private ArrayList<Repertoire> repertoires;
    private Repertoire pere;

    public Repertoire(String pnom, int ptaille) {
        this.nom = pnom;
        this.taille = ptaille;
        this.fichiers = new ArrayList<Fichier>();
        this.repertoires = new ArrayList<Repertoire>();
        this.pere = this;
    }

    public boolean ajoutFichier (Fichier f)
    {
        fichiers.add(f);
        return true;
    }

    public boolean ajoutRepertoire (Repertoire r)
    {
        if (r == this) {return false; }
        if (r.nom == this.nom) {return false; }
        if (r.nom == this.pere.nom) { return false; }
        while ()
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
        int taille = 1;
        int i = 0;
      
        System.out.println("Il y a " + taille + " repertoires");
        return taille;
    }

    @Override
    public int getTaille() {
        return this.taille;
    }


}
