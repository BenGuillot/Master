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
       this.pere = null;
    }

    public boolean ajoutFichier (Fichier f)
    {
        fichiers.add(f);
        return true;
    }

    public boolean ajoutRepertoire (Repertoire r)
    {
    	
    	r.pere = this;
    	
        if (r == this) {return false; }
        
        boolean a = testNom (r, r.pere);
       if (a == true) {
        		repertoires.add(r);
        		System.out.println("Repertoire ajouté");
        		return true;
        	}
       else { 
    	   System.out.println("Nom de repertoire pere : erreur");
    	   return false; }
    }
    

    
    public boolean testNom (Repertoire r, Repertoire p)
    {
    	 while (p == null)
    	 {
    		 
    		 if (r.nom == p.nom) 
    		 {System.out.println("Nom de repertoire pere : erreur");
    		 return false; }
    		 
    		testNom (r,r.pere);
    	
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
