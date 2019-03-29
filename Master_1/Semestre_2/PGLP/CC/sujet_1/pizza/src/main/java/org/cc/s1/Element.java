package org.cc.s1;

public abstract class Element {
    protected String nom;
    protected int cout;

    public Element(String pnom, int pcout){
        this.nom = pnom;
        this.cout = pcout;
    }

    public int getCout(){
        return this.cout;
    }

    public abstract void print();
    public abstract boolean contient(Element e);


}
