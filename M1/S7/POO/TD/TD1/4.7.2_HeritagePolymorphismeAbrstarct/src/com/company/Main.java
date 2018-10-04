package com.company;

public class Main {

    public static void main(String[] args) {

        System.out.println("test");
        Fichier f1 = new Fichier("f1", 2);
        Fichier f2 = new Fichier("f2", 2);
        Repertoire r1 = new Repertoire("R1");
        Repertoire r2 = new Repertoire("R2");
        Repertoire r3 = new Repertoire("R3");
        Repertoire r4 = new Repertoire("R4");
        r1.ajoutFichier(f1);
        r1.ajoutFichier(f2);
        r1.ajoutRepertoire(r2);
        r2.ajoutFichier(f1);
        r2.ajoutRepertoire(r3);
        r2.ajoutRepertoire(r4);
        r3.ajoutFichier(f1);
        System.out.print(r1.getTaille());
    }
}
