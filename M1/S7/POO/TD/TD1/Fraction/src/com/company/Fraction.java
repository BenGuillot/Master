package com.company;

import java.util.Objects;

public class Fraction implements java.lang.Comparable<Fraction> {

    public static final Fraction ZERO = new Fraction(0, 1);

    public static final Fraction UN = new Fraction(1, 1);

    private int num;
    private int denum;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fraction)) return false;
        Fraction fraction = (Fraction) o;
        int pnum, pdenum;
        if(fraction.getNum() > num)
             pnum = fraction.getNum() % num;
        else
            pnum = num % fraction.getNum();
        if(fraction.getDenum() > denum)
            pdenum = fraction.getDenum() % denum;
        else
            pdenum = denum % fraction.getDenum();
        return pdenum == 0 &&
               pnum == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, denum);
    }

    @Override
    public int compareTo(Fraction fraction) {
        long nOd = ((long) num) * fraction.denum;
        long dOn = ((long) denum) * fraction.num;
        return (nOd < dOn) ? -1 : ((nOd > dOn) ? +1 : 0);
    }
}
