package org.kholle.s2;

import java.util.ArrayList;
import java.util.List;

public class Train {
    private String nom;
    private String dest;

    private List<Element> listWagonConvoi = new ArrayList<Element>();

    public List<Element> getListWagonConvoi() {
        return listWagonConvoi;
    }

    public String getNom(){
        return nom;
    }

    public String getDest(){
        return dest;
    }

    public int getPoids(){
        int poids = 0;
        for(int i = 0; i < listWagonConvoi.size(); i++){
            poids += listWagonConvoi.get(i).getPoids();
        }
        return poids;
    }

    public void add(Element e){
        listWagonConvoi.add(e);
    }

    public static class Builder {
        private String nom;
        private String dest;
        private List<Element> listWagonConvoi = new ArrayList<Element>();

        public Builder(String nom, String dest) {
            this.nom = nom;
            this.dest = dest;
        }

        public Builder lisWagonConvoi (ArrayList<Element> List){
            this.listWagonConvoi = List;
            return this;
        }

        public Builder addElement(Element e){
            this.listWagonConvoi.add(e);
            return this;
        }

        public Train build(){
            return new Train(this);
        }
    }

    public Train(Builder builder) {
        nom = builder.nom;
        dest = builder.dest;
        listWagonConvoi = builder.listWagonConvoi;
    }





}
