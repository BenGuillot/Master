package com.company;

import com.sun.net.httpserver.Filter;

public class Main {

    public static void main(String[] args) {
        ChaineCryptee test = ChaineCryptee.DeEnClair("For The Alliance", 3);
        System.out.print(test.Crypte()+"\n");
        System.out.print(test.Decrypte());

    }
}
