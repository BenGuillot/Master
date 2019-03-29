package org.kholle.s2;

import java.util.ArrayList;
import java.util.List;

public class Convoi extends Element{
    private List<Element> listWagon = new ArrayList<Element>();

    public Convoi(String p_nom) {
        super(p_nom, 0);
    }

    public void add(Element e) throws AlreadyHereException{
        if (e==this) throw new AlreadyHereException();

        else if (contient(e)==true) {
            throw new AlreadyHereException();
        }

        else{
            listWagon.add(e);
            poids+=e.getPoids();
        }

    }

    public List<Element> getListWagon(){
        return this.listWagon;
    }

    @Override
    public void print() {
        for (Element e : listWagon){
            e.print();
        }
        System.out.println("\n");
    }

    @Override
    public boolean contient(Element e) {
        boolean test =false;
        if (listWagon.contains(e)) {
            return true;
        }
        else {
            for (int i=0;i<listWagon.size();i++) {
                if(this.listWagon.get(i).contient(e) == true)
                    test = true;
            }
            return test;
        }
    }
}
