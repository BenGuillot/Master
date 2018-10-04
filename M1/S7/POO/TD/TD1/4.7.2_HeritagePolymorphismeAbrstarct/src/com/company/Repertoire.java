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
    	System.out.println("Repertoire ajouté");
    	r.pere = this;
        if (r == this) {return false; }
        System.out.println("Repertoire ajouté");
        boolean a = testNom (r);
       if (a == true) {
        		repertoires.add(r);
        		System.out.println("Repertoire ajouté");
        		return true;
        	}
       else { 
    	   System.out.println("Nom de repertoire pere : erreur");
    	   return false; }
    }
    
    public boolean testNom (Repertoire r)
    {
    	 while (this.pere != null)
    	 {
    		 if (r.nom == this.nom) {return false; }
    		 
    		testNom (r.pere);
    	 }
    	 return true;
    }

    public int nbFichiers ()
    {
        int taille = this.fichiers.size();
        System.out.println("Il y a " + taille + " fichier");
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
