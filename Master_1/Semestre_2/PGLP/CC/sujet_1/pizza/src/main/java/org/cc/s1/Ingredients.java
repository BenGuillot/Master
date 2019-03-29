package org.cc.s1;

public class Ingredients extends Element {

    public Ingredients(String pnom, int pcout){
        super(pnom, pcout);
    }

    @Override
    public void print() {
        System.out.println(this.nom);
    }

    @Override
    public boolean contient(Element e) {
        return false;
    }
}
