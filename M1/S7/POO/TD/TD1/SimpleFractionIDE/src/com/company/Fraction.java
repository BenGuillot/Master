package com.company;

public class Fraction {

    private int num;
    private int denum;

    public Fraction(){
        this.num = 1;
        this.denum = 1;
    }

    public Fraction (int pnum, int pdenum){
        this.num = pnum;
        this.denum = pdenum;
    }

    public String toString(){
        return num + "/ " + denum;
    }
}

