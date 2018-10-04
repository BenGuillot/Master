package com.company;

public abstract class Basic {

    protected String nom;
    protected int taille;

    abstract int getTaille();

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }
}
