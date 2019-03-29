package org.cc.s1;

import java.util.ArrayList;
import java.util.List;

public class Preparation extends Element {

    private List<Element> contenu = new ArrayList<Element>();

    public Preparation(String pnom){
        super(pnom,0);
    }

    public void add(Element e)throws Exception{
        if(e == this) throw new Exception(); //si on ajoute la preparation a elle même
        else if (contient(e) == true){
            throw new Exception();
        }
        else {
            contenu.add(e);
            cout+=e.getCout()
        }
    }

    @Override
    public boolean contient (Element e){
        if(contenu.contains(e)){
            return true;
        }
        else {
            boolean test=false;
            for (int i = 0; i<contenu.size(); i++){
                test=this.contenu.get(i).contient(e) | test;
            }
            return test;
        }
    }

    @Override
    public void print() {
        System.out.println("{");
        for (Element e : contenu){
            e.print();
        }
        System.out.println("}");

    }

}
