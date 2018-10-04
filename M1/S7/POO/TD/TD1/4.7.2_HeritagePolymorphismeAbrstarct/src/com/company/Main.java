package com.company;

public class Main {

    public static void main(String[] args) {

        System.out.println("test");
        Fichier A = new Fichier("f1", 2);
        Repertoire B = new Repertoire("R1", 0);
        Fichier C = new Fichier("f2", 2);
        B.ajoutFichier(A);
        B.ajoutFichier(C);
        B.nbFichiers();
        Repertoire D = new Repertoire("R2", 0);
        B.ajoutRepertoire(D);
        
        
        Repertoire E = new Repertoire ("R3", 0);
        Repertoire F = new Repertoire ("R1", 0);
        B.ajoutRepertoire(E);
        E.ajoutRepertoire(F);
    }
}
