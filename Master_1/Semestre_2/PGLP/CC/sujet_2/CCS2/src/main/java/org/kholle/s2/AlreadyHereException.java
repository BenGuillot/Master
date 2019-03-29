package org.kholle.s2;

public class AlreadyHereException extends RuntimeException {
    public  AlreadyHereException(){
        super("Wagon ou convoi déjà présent dans le convoi");
    }
}
