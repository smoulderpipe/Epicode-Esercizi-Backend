package it.lambda;
import java.util.Comparator;
import java.util.TreeSet;

public class ProvaLambda2 {

    public static void main(String[] args) {
        //utilizziamo una classe anonima per definire il compare senza creareclassi a parte e oggetti della classe Comparatore
        TreeSet<String> parole = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        parole.add("java");
        parole.add("html");
        parole.add("css");
        parole.add("javascript");

        System.out.println(parole);

    }
}
