import java.util.*;
// import java.io.*;

public class SaisieRPN {
    private final int MIN_VALUE = -1000;
    private final int MAX_VALUE = 1000;

    public SaisieRPN(MoteurRPN MR){
    	System.out.print("INPUT : \n");
    	Scanner scanner = new Scanner(System.in); //prend pour valeur les données entrées par l'utilisateur

        while (scanner.hasNext()) { //tant qu'il y a des elements
            String s = scanner.next(); //s prend la valeur de l'élement
            System.out.print(s);
            switch(s){
                //On souhaite stocker les resultat des opérations dans la pile, de ce fait on
                //va effectuer les opération dès qu'on les rentre un opérande dans la pile
                // et on va stocker le resultat dans la pile
                case "":
                    scanner.next();
                case "+":
                    MR.calcul(Operation.PLUS); //appel d'une fonction de calcul de MoteurRPN sur l'opérateur PLUS
                    break;
                case "-":
                   MR.calcul(Operation.MOINS); //appel d'une fonction de calcul de MoteurRPN sur l'opérateur MOIN
                    break;
                case "*":
                    MR.calcul(Operation.MULT); //appel d'une fonction de calcul de MoteurRPN sur l'opérateur MULT
                    break;
                case "/":
                    MR.calcul(Operation.DIV); //appel d'une fonction de calcul de MoteurRPN sur l'opérateur DIV
                    break;
                default:
                    double Ds = Double.parseDouble(s); //converti la chaine de caractère s en double
                    if (Ds > MIN_VALUE && Ds < MAX_VALUE){
                        MR.push(Ds);
                        break;
                    }
                break;
            }
        }
        scanner.close();
    }



}
