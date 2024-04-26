package it.lambda;

import java.util.ArrayList;
import java.util.List;

public class ProvaLambda4 {

    public static void main(String[] args) {
        List<Integer> numeriImmutabile = List.of(2, 4, 7, 12, 5, 91);
        ArrayList<Integer> numeri = new ArrayList<>(numeriImmutabile);
        numeri.sort((i1, i2)-> i1.compareTo(i2));

        numeri.forEach(integer -> System.out.println(integer));

        numeri.replaceAll(integer -> integer + 10);
        numeri.forEach(integer -> System.out.println(integer));
    }
}

//ricevuta medico chirurgo walter resta pagobancomat