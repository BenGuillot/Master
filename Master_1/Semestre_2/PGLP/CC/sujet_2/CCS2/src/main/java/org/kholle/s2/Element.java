package org.kholle.s2;

public abstract class Element { //classe de base servant a la création des wagons simples et des convois

        protected String nom;

        protected int poids;

        public Element(String p_nom, int p_poids) {
            this.nom=p_nom;
            this.poids=p_poids;
        }

        public int getPoids() {
            return poids;
        }

        public String getNom(){
            return nom;
        }


        public abstract boolean contient(Element e);

        public abstract void print();
}

