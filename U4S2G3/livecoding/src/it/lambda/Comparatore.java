package it.lambda;

import java.util.Comparator;

public class Comparatore implements Comparator<String> {


    @Override
    public int compare(String o1, String o2) {
        //o1=o2 restituisce 0
        //se o1 viene prima di o2, restituisce un numero negativo
        //se o2 viene prima di o1, restituisce un numero positivo

        //per fare l'ordine crescente:
//        return o1.compareTo(o2);

        //per fare l'ordine inverso:
        return o2.compareTo(o1);
    }
}
