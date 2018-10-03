package com.company;

public class Main {

    public static void main(String[] args) {
        Fraction f1 = new Fraction(1,3);
        Fraction f2 = new Fraction(2, 6);
        //System.out.print(f1.BiggerOrLower(f2));
        boolean isIt = f1.equals(f2);
        System.out.print(isIt);
    }
}
