import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {

        GestisciCatalogo g = new GestisciCatalogo();

        Set<ElementoCatalogo> archivio = new HashSet<>();

        Libro libro1 = new Libro("ISBN001", "Guerra e Pace", 1983, 1415, "Lev Tolstoj", "Romanzo");
        Libro libro2 = new Libro("ISBN002", "Il Processo", 2022, 256, "Franz Kafka", "Romanzo");
        Libro libro3 = new Libro("ISBN003", "Orgoglio e Pregiudizio", 1813, 279, "Jane Austen", "Romanzo");
        Libro libro4 = new Libro("ISBN004", "1984", 1949, 328, "George Orwell", "Distopia");
        Libro libro5 = new Libro("ISBN005", "Il Piccolo Principe", 1999, 96, "Antoine de Saint-Exupéry", "Fantasy");

        Rivista rivista1 = new Rivista("ISBN006", "National Geographic", 2022, 150, new Periodicita("MENSILE"));
        Rivista rivista2 = new Rivista("ISBN007", "Time", 1999, 100, new Periodicita("SETTIMANALE"));
        Rivista rivista3 = new Rivista("ISBN008", "Wired", 2022, 120, new Periodicita("MENSILE"));


        archivio.add(libro1);
        archivio.add(libro2);
        archivio.add(libro3);
        archivio.add(libro4);
        archivio.add(libro5);
        archivio.add(rivista1);
        archivio.add(rivista2);
        archivio.add(rivista3);


        System.out.println("ARCHIVIO INIZIALE:");
        for (ElementoCatalogo elemento : archivio) {
            System.out.println(elemento.toString());
        }
        System.out.println("\n");

        Scanner scanner = new Scanner(System.in);

        boolean inputValido = false;

        while (!inputValido) {
            System.out.print("Che tipo di operazione vuoi fare? (ricerca, rimozione, aggiunta): ");
            String tipoOperazione = scanner.nextLine().toLowerCase();

            switch (tipoOperazione) {
                case "ricerca":
                    g.eseguiRicerca(archivio, scanner);
                    inputValido = true;
                    break;
                case "rimozione":
                    g.eseguiRimozione(archivio, scanner);
                    inputValido = true;
                    break;
                case "aggiunta":
                    g.eseguiAggiunta(archivio, scanner);
                    inputValido = true;
                    break;
                default:
                    System.out.println("Operazione non valida.");
                    break;
            }
        }

        scanner.close();

        System.out.println("L'archivio è stato salvato su file.");
        File fileScrittura = new File("./../persistence/scrittura.txt");
        g.salvataggio(fileScrittura, archivio);

        System.out.println("Sono stati caricati elementi da un file esterno su un nuovo archivio. Contenuto del nuovo archivio:");
        File fileLettura = new File("./../persistence/lettura.txt");
        Set<ElementoCatalogo> archivio2 = new HashSet<>();
        g.caricamento(fileLettura, archivio2);
        for (ElementoCatalogo elemento : archivio2) {
            System.out.println(elemento.toString());
        }
    }

}
