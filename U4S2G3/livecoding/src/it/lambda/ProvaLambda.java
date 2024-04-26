package it.lambda;

import java.util.TreeSet;

public class ProvaLambda {

    public static void main(String[] args) {

        //creo oggetto comparatore 1 sola volta e lo utilizzo nel costruttore del Treeset
        Comparatore comparatore = new Comparatore();

        TreeSet<String> parole = new TreeSet<>(comparatore);

        parole.add("java");
        parole.add("html");
        parole.add("css");
        parole.add("javascript");

        System.out.println(parole);

    }
}
