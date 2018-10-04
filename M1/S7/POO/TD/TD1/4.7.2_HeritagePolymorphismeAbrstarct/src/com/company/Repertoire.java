package com.company;
import java.util.ArrayList;


public class Repertoire extends Basic{

    private ArrayList<Fichier> fichiers;
    private ArrayList<Repertoire> repertoires;

    public Repertoire(String pnom) {
        this.nom = pnom;
        this.taille = 0;
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
        //System.out.println("Il y a " + taille + " fichiers dans " + this.nom );
        return taille;
    }

    public int nbRepertoires ()
    {
        int taille = this.repertoires.size();
        //System.out.println("Il y a " + taille + " repertoires dans " + this.nom);
        return taille;
    }

    @Override
    public void getTaille() {

        if (this.repertoires.isEmpty()){
            this.taille += this.nbFichiers();
        }
        else {
            if (!this.repertoires.isEmpty()){
                this.taille += this.repertoires.size();
                for (int i = 0; i < this.repertoires.size(); i ++)
                   this.repertoires.get(i).getTaille();
            }
            if (!this.fichiers.isEmpty()){
                this.taille += this.nbFichiers();
            }
        }
        System.out.print(this.taille + "\n");
    }

    /*else {
            for(int j = 0; j<=this.repertoires.size(); j++) {
                return this.repertoires.get(j).nbRepertoires() +
                        this.repertoires.get(j).nbFichiers() +
                        this.repertoires.get(j).getTaille();
            }
        }*/
}
