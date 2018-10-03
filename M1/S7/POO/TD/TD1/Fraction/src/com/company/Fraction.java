// CLEMENT A MODIFIE CE FICHIER

package com.company;

public class Fraction {
    private final int num;
    private final int denum;

    public Fraction(int pnum, int pdenum){
        this.num = pnum;
        this.denum = pdenum;
    }

    public Fraction (int pnum){
        this.num = pnum;
        this.denum = 1;
    }

    public Fraction(){
        this.num = 0;
        this.denum = 1;
    }

    public int getDenum() {
        return denum;
    }

    public int getNum() {
        return num;
    }

    public double FloatRes() {
        double res;
        return res=(double)this.num/this.denum;
    }

    @Override
    public String toString() {
        return this.num+" / "+this.denum+" = "+this.FloatRes();
    }

    public boolean isEqual(Fraction pFraction){
        int pFDenum = pFraction.getDenum();
        int pFNum = pFraction.getNum();
        if(pFDenum % this.denum == 0 && pFNum % this.num == 0)
            return true;
        return false;
    }

    public String BiggerOrLower (Fraction pFraction){
        if(pFraction.FloatRes() > this.FloatRes())
            return pFraction.toString() + " is bigger than "+ this.toString();
        else if (pFraction.FloatRes() < this.FloatRes())
            return pFraction.toString() + " is lower than "+ this.toString();
        else
            return pFraction.toString() + " is equal to "+ this.toString();
    }
}
