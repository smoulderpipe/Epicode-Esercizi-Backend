import java.util.*;
import java.util.stream.Collectors;

public class ProvaStream {

    public static void main(String[] args) {
        Prodotto libro1 = new Prodotto(100l, "Il signore degli anelli", "Libri", 101);
        Prodotto libro2 = new Prodotto(101L, "Il giorno nero", "Libri", 102);
        Prodotto libro3 = new Prodotto(102L, "Collision", "Libri", 10);
        Prodotto libro4 = new Prodotto(103L, "Marracash", "Libri", 13);
        Prodotto baby1 = new Prodotto(104L, "pannolini", "Baby", 7);
        Prodotto baby2 = new Prodotto(105L, "tutina", "Baby", 15);
        Prodotto baby3 = new Prodotto(106L, "cappello", "Baby", 10);
        Prodotto boys1 = new Prodotto(107L, "scarpe", "Boys", 70);
        Prodotto boys2 = new Prodotto(108L, "felpa", "Boys", 40);
        Prodotto boys3 = new Prodotto(109L, "pantalone", "Boys", 40);
        List<Prodotto> prodotti = List.of(libro1, libro2, libro3, libro4, boys1, boys2, boys3, baby1, baby2, baby3);

        //creo una lista con i prezzi dei prodotti
        List<Double> l1 = prodotti.stream().map(prodotto -> prodotto.getPrezzo()).collect(Collectors.toList());
        l1.forEach(n -> System.out.println(n));



        //creo un set di prezzi dei prodotti(elimino i duplicati dei prezzi rispetto all'esempio precedente)
        Set<Double> s1 = prodotti.stream().map(prodotto -> prodotto.getPrezzo()).collect(Collectors.toSet());
        s1.forEach(n -> System.out.println(n));

        //creo una mappa che contiene coppie costituite da id del prodotto e prezzo del prodotto
        //Prodotto::getId è un method reference e sostituisce l'espressione Lambda p->p.getId()
        Map<Long, Double> m1 = prodotti.stream().
                collect(Collectors.toMap(Prodotto::getId, Prodotto::getPrezzo));
        m1.forEach((id, valore) -> System.out.println(id + " " + valore));

        //creo una mappa usando il groupingby che raggruppa per categoria di prodotti
        Map<String, List<Prodotto>> m2 = prodotti.stream().
                collect(Collectors.groupingBy(Prodotto::getCategoria));
        m2.forEach((id, valore) -> System.out.println(id + " " + valore));


        Map<String, Long> m3 = prodotti.stream().collect(Collectors.groupingBy(Prodotto::getCategoria, Collectors.counting()));
        m3.forEach((id, valore) -> System.out.println(id + " " + valore));

        System.out.println();

        //creo una mappa del groupingby con cui raggruppo per categoria e per ogni categoria calcolo la media dei prezzi dei prodotti

        Map<String,Double> m4 = prodotti.stream().collect(Collectors.groupingBy(Prodotto::getCategoria, Collectors.averagingDouble(Prodotto::getPrezzo)));
        m4.forEach((id, valore) -> System.out.println(id + " " + valore));

        double somma = prodotti.stream().map(Prodotto::getPrezzo).collect(Collectors.summingDouble(Double::doubleValue));
        System.out.println(somma);

        //calcolo la media di tutti i prezzi dei prodotti usando averagingDouble di collect
        double media = prodotti.stream().map(Prodotto::getPrezzo).collect(Collectors.averagingDouble(Double::doubleValue));
        System.out.println(media);

        //calcolo la statistica di tutti i prezzi dei prodotti usando summarizingDouble di collect
        //dalla statistica otterrò vari dati tra cui somme, medie, max, min, count
        DoubleSummaryStatistics stat = prodotti.stream().map(Prodotto::getPrezzo).collect(Collectors.summarizingDouble(Double::doubleValue));
        System.out.println(media);

        //creo una mappa partizionando il contenuto dello stream in 2 partizioni
        //una partizione verifica la condizione del partitioning, l'altra no
        Map<Boolean, List<Prodotto>> m6 = prodotti.stream().collect(Collectors.partitioningBy(p -> p.getPrezzo()<50));
        m6.forEach((id, valore) -> System.out.println(id + " " + valore));

        //ordino lo stream di prodotti secondo l'ordine decrescente
        prodotti.stream().sorted(Comparator.comparingDouble(Prodotto::getPrezzo)).collect(Collectors.toList());

    }
}
