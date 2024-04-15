/*
Esercizio 1
Implementare i seguenti metodi:
- moltiplica: accetta due interi e ritorna il loro prodotto
- concatena: accetta una stringa e un intero e restituisce una stringa che concatena gli elementi
- inserisciInArray: accetta un array di stringhe di cinque elementi ed una stringa e restituisce un array di sei elementi, in cui la stringa passata sia al terzo posto e le stringhe precedentemente in quarta e quinta posizione siano rispettivamente in quinta e sesta.

Scrivi una main che invochi in sequenza i tre metodi realizzati
 */


package it.epicode;

import java.util.Scanner;

public class Variabili {

    public int moltiplica(int x, int y) {
        return x * y;
    }

    public String concatena(String s, int y) {
        return s + String.valueOf(y);
    }

    public String[] inserisciInArray(String[] array, String r) {
        String[] nuovoArray = new String[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            nuovoArray[i] = array[i];
        }
        nuovoArray[3] = array[2];
        nuovoArray[5] = array[4];
        nuovoArray[4] = array[3];
        nuovoArray[2] = r;

        return nuovoArray;
    }

    public static String[] inserisciTreStringhe() {
        Scanner scanner = new Scanner(System.in); // Crea lo Scanner all'interno del metodo

        System.out.print("Inserisci la prima stringa: ");
        String stringaUno = scanner.nextLine();

        System.out.print("Inserisci la seconda stringa: ");
        String stringaDue = scanner.nextLine();

        System.out.print("Inserisci la terza stringa: ");
        String stringaTre = scanner.nextLine();

        String[] newArray = new String[]{stringaUno, stringaDue, stringaTre};
        return newArray;
    }

    public static double perimetroRettangolo(double base, double altezza) {
        return 2 * (base + altezza);
    }

    public static int pariDispari(int number) {
        if (number % 2 == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public static double perimetroTriangolo(double latoA, double latoB, double latoC) {
        double semiperimetro = (latoA + latoB + latoC) / 2;
        double area = Math.sqrt(semiperimetro * (semiperimetro - latoA) * (semiperimetro - latoB) * (semiperimetro - latoC));
        return area;
    }

}


//        Scanner scanner = new Scanner(System.in);
//
//        int g = scanner.nextInt();
//        int x;
//        x = 4;
//        x = 6;
//
//        boolean b = true;
//
//        char c = 'c';
//
//        double d = 3.3;
//        String s = "benvenuti al corso epicode";
//        //String x = "ciaociao";
//        //String fraseConcatenata = s + x;
//        System.out.println(s);
//
//        int[] numeri = new int[6];
