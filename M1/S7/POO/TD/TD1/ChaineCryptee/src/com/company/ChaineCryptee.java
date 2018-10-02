package com.company;

public class ChaineCryptee {

    private String chaineCryptee;
    private int shift;

    public ChaineCryptee(){
        this.chaineCryptee = "default";
        this.shift = 0;
    }

    public ChaineCryptee(String pchaineClaire, int pshift){
        this.chaineCryptee = pchaineClaire;
        this.shift = pshift;
    }

    private static char decaleCaractere(char c, int decalage) {
        if (c >= 'A' && c <= 'Z') {return(char)(((c - 'A' + decalage) % 26) + 'A');}
        if (c >= 'a' && c <= 'z') {return(char)(((c - 'a' + decalage) % 26) + 'a');}
        return c;
    }

    public String Crypte(){
        return chaineCryptee;
    }

    public String Decrypte(){
        if (chaineCryptee != null){
            String c = "";
            for(int i = 0; i<chaineCryptee.length(); i++){
                c = c + decaleCaractere(chaineCryptee.charAt(i), shift*(-1));
            }
            return c;
        }
        else return "NULL";
    }

    public static ChaineCryptee DeEnClair(String chaine, int pshift){
        String c = "";
        for(int i = 0; i<chaine.length(); i++) {
            c = c + decaleCaractere(chaine.charAt(i), pshift);
        }
        return new ChaineCryptee(c, pshift);
    }

    public static ChaineCryptee DeCryptee (String chaine, int pshift){
        return new ChaineCryptee(chaine, pshift);
    }
}
