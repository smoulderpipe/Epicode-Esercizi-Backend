package it.lambda;

import java.util.TreeSet;

public class ProvaLambda3 {

    public static void main(String[] args) {

        TreeSet<String> parole = new TreeSet<>((o1, o2) -> (o2.compareTo(o1)));

        parole.add("java");
        parole.add("html");
        parole.add("css");
        parole.add("javascript");

        System.out.println(parole);

        parole.forEach(p -> System.out.println(p));

        for(String s:parole) {
            System.out.println(s);
        }

        parole.removeIf(s -> s.contains("j"));

        parole.forEach(s -> {
            s=s+"!";
            System.out.println(s);
        });

    }

}
