package org.kholle.s2;

public class Wagon extends Element {
    public Wagon(String p_nom, int p_poids) {
        super(p_nom,p_poids);
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
