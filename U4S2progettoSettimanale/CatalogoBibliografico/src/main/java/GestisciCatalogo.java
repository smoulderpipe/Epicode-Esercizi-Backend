import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;

public class GestisciCatalogo {

    public static void main(String[] args) throws IOException {

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
                    eseguiRicerca(archivio, scanner);
                    inputValido = true;
                    break;
                case "rimozione":
                    eseguiRimozione(archivio, scanner);
                    inputValido = true;
                    break;
                case "aggiunta":
                    eseguiAggiunta(archivio, scanner);
                    inputValido = true;
                    break;
                default:
                    System.out.println("Operazione non valida.");
                    break;
            }
        }

        scanner.close();

        System.out.println("L'archivio è stato salvato su file.");
        File fileScrittura = new File("./../persitence/scrittura.txt");
        GestisciCatalogo gestoreCatalogo = new GestisciCatalogo();
        gestoreCatalogo.salvataggio(fileScrittura, archivio);

        System.out.println("Sono stati caricati elementi da un file esterno su un nuovo archivio. Contenuto del nuovo archivio:");
        File fileLettura = new File("./../persitence/lettura.txt");
        Set<ElementoCatalogo> archivio2 = new HashSet<>();
        GestisciCatalogo.caricamento(fileLettura, archivio2);
        for (ElementoCatalogo elemento : archivio2) {
            System.out.println(elemento.toString());
        }
    }

    public static void salvataggio(File file, Set<ElementoCatalogo> archivio) throws IOException {

        String archivioString = archivio.stream()
                .map(ElementoCatalogo::toString)
                .collect(Collectors.joining("\n"));

        try {
            FileUtils.writeStringToFile(file, archivioString, Charset.defaultCharset());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void caricamento(File file, Set<ElementoCatalogo> archivio) throws IOException {

        try {
            String str = FileUtils.readFileToString(file, Charset.defaultCharset());
            String[] archivioStr =  str.split("\n");

            Arrays.stream(archivioStr).forEach(s -> System.out.println(s));
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
    }

    public static void eseguiRicerca(Set<ElementoCatalogo> archivio, Scanner scanner) {
        boolean inputCorretto = false;

        while (!inputCorretto) {
            System.out.print("Cosa vuoi cercare? (isbn, autore, anno): ");
            String tipoRicerca = scanner.nextLine().toLowerCase();

            switch (tipoRicerca) {
                case "isbn":
                    boolean inputValidoISBN = false;
                    while (!inputValidoISBN) {
                        try {
                            System.out.print("Inserisci l'ISBN da cercare: ");
                            String isbnDaCercare = scanner.nextLine();

                            if(isbnDaCercare.isEmpty()) {
                                throw new AttributiNonValidiException("Il campo 'ISBN' non può essere vuoto.");
                            }
                            ElementoCatalogo corrispondenzaISBN = ricercaElementoPerISBN(archivio, isbnDaCercare);
                            if (corrispondenzaISBN != null) {
                                System.out.println("ELEMENTO TROVATO (RICERCA PER ISBN):");
                                System.out.println(corrispondenzaISBN);
                                inputValidoISBN = true;
                            } else {
                                System.out.println("Nessun elemento trovato.");
                            }
                            inputCorretto = true;
                        } catch (AttributiNonValidiException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    break;
                case "autore":
                    System.out.print("Inserisci l'autore da cercare: ");
                    String autoreDaCercare = scanner.nextLine();
                    Set<ElementoCatalogo> risultatiPerAutore = ricercaElementoPerAutore(archivio, autoreDaCercare);
                    if (!risultatiPerAutore.isEmpty()) {
                        System.out.println("LIBRI TROVATI (RICERCA PER AUTORE):");
                        for (ElementoCatalogo libro : risultatiPerAutore) {
                            System.out.println(libro.toString());
                        }
                    } else {
                        System.out.println("Nessun libro trovato per l'autore specificato.");
                    }
                    inputCorretto = true;
                    break;
                case "anno":
                    System.out.print("Inserisci l'anno da cercare: ");
                    int annoDaCercare = scanner.nextInt();
                    Set<ElementoCatalogo> risultatiPerAnno = ricercaElementoPerAnno(archivio, annoDaCercare);
                    if (!risultatiPerAnno.isEmpty()) {
                        System.out.println("ELEMENTI TROVATI (RICERCA PER ANNO):");
                        for (ElementoCatalogo elemento : risultatiPerAnno) {
                            System.out.println(elemento.toString());
                        }
                    } else {
                        System.out.println("Nessun elemento trovato per l'anno specificato.");
                    }
                    inputCorretto = true;
                    break;
                default:
                    System.out.println("Tipo di ricerca non valido.");
                    break;
            }
        }
    }


    public static void eseguiRimozione(Set<ElementoCatalogo> archivio, Scanner scanner) {
        boolean inputCorretto = false;
        while (!inputCorretto) {
            System.out.print("Inserisci l'ISBN dell'elemento da rimuovere: ");
            String isbnDaRimuovere = scanner.nextLine();

            ElementoCatalogo elementoDaRimuovere = ricercaElementoPerISBN(archivio, isbnDaRimuovere);
            if (elementoDaRimuovere != null) {
                archivio = rimuoviElementoPerISBN(archivio, isbnDaRimuovere);
                System.out.println("E' stato rimosso l'elemento: " + elementoDaRimuovere.getTitolo());
                System.out.println("ARCHIVIO AGGIORNATO DOPO LA RIMOZIONE:");
                for (ElementoCatalogo elemento : archivio) {
                    System.out.println(elemento.toString());
                }
                inputCorretto = true;
            } else {
                System.out.println("ISBN non trovato nell'archivio. Inserisci un ISBN valido.");
            }

        }

    }

    public static void eseguiAggiunta(Set<ElementoCatalogo> archivio, Scanner scanner) {
        boolean isValid = false;
        while (!isValid) {
            System.out.print("Che tipo di elemento vuoi aggiungere? (libro, rivista): ");
            String tipoElemento = scanner.nextLine().toLowerCase();

            switch (tipoElemento) {
                case "libro":
                    boolean inputValidoLibro = false;
                    while (!inputValidoLibro) {
                        try {
                            String isbnLibro = null;
                            while (isbnLibro == null) {
                                System.out.print("Inserisci l'ISBN del libro: ");
                                isbnLibro = scanner.nextLine();
                                if (isbnLibro.isEmpty()) {
                                    throw new AttributiNonValidiException("Il campo 'ISBN' non può essere vuoto.");
                                }
                            }

                            String titoloLibro = null;
                            while (titoloLibro == null || titoloLibro.isEmpty()) {
                                System.out.print("Inserisci il titolo del libro: ");
                                String inputTitoloLibro = scanner.nextLine();

                                try {
                                    titoloLibro = inputTitoloLibro;
                                    if (titoloLibro.isEmpty()) {
                                        throw new AttributiNonValidiException("Il campo 'TITOLO LIBRO' non può essere vuoto.");
                                    }
                                } catch (AttributiNonValidiException e) {
                                    System.out.println(e.getMessage());
                                }
                            }

                            Integer annoLibro = null;
                            while (annoLibro == null || annoLibro <= 0) {
                                System.out.print("Inserisci l'anno di pubblicazione del libro: ");
                                String inputAnnoLibro = scanner.nextLine();

                                try {
                                    annoLibro = Integer.parseInt(inputAnnoLibro);
                                    if (inputAnnoLibro.isEmpty()) {
                                        throw new AttributiNonValidiException("Il campo 'ANNO' non può essere vuoto.");
                                    }

                                    if (annoLibro <= 0) {
                                        throw new NumberFormatException();
                                    }
                                } catch (NumberFormatException e) {

                                    System.out.println("Errore: l'anno deve essere un numero intero positivo.");

                                }
                            }


                            Integer pagineLibro = null;
                            while (pagineLibro == null || pagineLibro <= 0) {

                                System.out.print("Inserisci il numero di pagine del libro: ");
                                String inputPagineLibro = scanner.nextLine().trim();

                                try {
                                    pagineLibro = Integer.parseInt(inputPagineLibro);
                                    if (inputPagineLibro.isEmpty()) {
                                        throw new AttributiNonValidiException("Il campo 'PAGINE' non può essere vuoto.");
                                    }
                                    if (pagineLibro <= 0) {
                                        throw new NumberFormatException();
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Errore: il numero di pagine deve essere un numero intero positivo.");
                                }
                            }

                            String autoreLibro = null;
                            while (autoreLibro == null || autoreLibro.isEmpty()) {
                                System.out.print("Inserisci l'autore del libro: ");
                                String inputAutoreLibro = scanner.nextLine();

                                try {
                                    autoreLibro = inputAutoreLibro;
                                    if (autoreLibro.isEmpty()) {
                                        throw new AttributiNonValidiException("Il campo 'AUTORE LIBRO' non può essere vuoto.");
                                    }
                                } catch (AttributiNonValidiException e) {
                                    System.out.println(e.getMessage());
                                }
                            }

                            String genereLibro = null;
                            while (genereLibro == null || genereLibro.isEmpty()) {
                                System.out.print("Inserisci il genere del libro: ");
                                String inputGenereLibro = scanner.nextLine();

                                try {
                                    genereLibro = inputGenereLibro;
                                    if (genereLibro.isEmpty()) {
                                        throw new AttributiNonValidiException("Il campo 'GENERE LIBRO' non può essere vuoto.");
                                    }
                                } catch (AttributiNonValidiException e) {
                                    System.out.println(e.getMessage());
                                }
                            }


                            Libro nuovoLibro = new Libro(isbnLibro, titoloLibro, annoLibro, pagineLibro, autoreLibro, genereLibro);
                            archivio.add(nuovoLibro);
                            System.out.println("Libro aggiunto con successo al catalogo.");
                            inputValidoLibro = true;

                            System.out.println("ARCHIVIO AGGIORNATO:");
                            for (ElementoCatalogo elemento : archivio) {
                                System.out.println(elemento.toString());
                            }


                        } catch (InputMismatchException e) {
                            System.out.println("Errore nel formato di dato.");
                        } catch (AttributiNonValidiException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;

                case "rivista":

                    boolean inputValidoRivista = false;
                    while (!inputValidoRivista) {
                        try {
                            String isbnRivista = null;
                            while (isbnRivista == null) {
                                System.out.print("Inserisci l'ISBN della rivista: ");
                                isbnRivista = scanner.nextLine();
                                if (isbnRivista.isEmpty()) {
                                    throw new AttributiNonValidiException("Il campo 'ISBN' non può essere vuoto.");
                                }
                            }

                            String titoloRivista = null;
                            while (titoloRivista == null || titoloRivista.isEmpty()) {
                                System.out.print("Inserisci il titolo della rivista: ");
                                String inputTitoloRivista = scanner.nextLine();

                                try {
                                    titoloRivista = inputTitoloRivista;
                                    if (titoloRivista.isEmpty()) {
                                        throw new AttributiNonValidiException("Il campo 'TITOLO RIVISTA' non può essere vuoto.");
                                    }
                                } catch (AttributiNonValidiException e) {
                                    System.out.println(e.getMessage());
                                }
                            }

                            Integer annoRivista = null;
                            while (annoRivista == null || annoRivista <= 0) {
                                System.out.print("Inserisci l'anno di pubblicazione della rivista: ");
                                String inputAnnoRivista = scanner.nextLine();

                                try {
                                    annoRivista = Integer.parseInt(inputAnnoRivista);
                                    if (inputAnnoRivista.isEmpty()) {
                                        throw new AttributiNonValidiException("Il campo 'ANNO' non può essere vuoto.");
                                    }

                                    if (annoRivista <= 0) {
                                        throw new NumberFormatException();
                                    }
                                } catch (NumberFormatException e) {

                                    System.out.println("Errore: l'anno deve essere un numero intero positivo.");

                                }
                            }


                            Integer pagineRivista = null;
                            while (pagineRivista == null || pagineRivista <= 0) {

                                System.out.print("Inserisci il numero di pagine della rivista: ");
                                String inputPagineRivista = scanner.nextLine().trim();

                                try {
                                    pagineRivista = Integer.parseInt(inputPagineRivista);
                                    if (inputPagineRivista.isEmpty()) {
                                        throw new AttributiNonValidiException("Il campo 'PAGINE' non può essere vuoto.");
                                    }
                                    if (pagineRivista <= 0) {
                                        throw new NumberFormatException();
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Errore: il numero di pagine deve essere un numero intero positivo.");
                                }
                            }

                            Periodicita periodicitaRivista = null;
                            while (periodicitaRivista == null || periodicitaRivista.getPeriodicita() == null) {
                                System.out.print("Inserisci la periodicità della rivista (SETTIMANALE, MENSILE, SEMESTRALE): ");
                                String inputPeriodicitaRivista = scanner.nextLine();

                                try {
                                    periodicitaRivista = new Periodicita(inputPeriodicitaRivista);
                                    if (periodicitaRivista.getPeriodicita() == null) {
                                        throw new AttributiNonValidiException("Il campo 'PERIODICITA' RIVISTA' non può essere vuoto.");
                                    }

                                } catch (AttributiNonValidiException e) {
                                    System.out.println(e.getMessage());

                                } catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                }
                            }


                            Rivista nuovaRivista = new Rivista(isbnRivista, titoloRivista, annoRivista, pagineRivista, periodicitaRivista);
                            archivio.add(nuovaRivista);
                            System.out.println("Rivista aggiunta con successo al catalogo.");
                            inputValidoRivista = true;

                            System.out.println("ARCHIVIO AGGIORNATO:");
                            for (ElementoCatalogo elemento : archivio) {
                                System.out.println(elemento.toString());
                            }


                        } catch (InputMismatchException e) {
                            System.out.println("Errore nel formato di dato.");
                        } catch (AttributiNonValidiException e) {
                            System.out.println(e.getMessage());
                        }

                    }

                    break;
            }
        }


    }


    public static ElementoCatalogo ricercaElementoPerISBN(Set<ElementoCatalogo> archivio, String isbn) {
        return archivio.stream()
                .filter(elemento -> elemento.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }

    public static Set<ElementoCatalogo> ricercaElementoPerAutore(Set<ElementoCatalogo> archivio, String autore) {
        return archivio.stream()
                .filter(elemento -> elemento instanceof Libro)
                .filter(libro -> ((Libro) libro).getAutore().equalsIgnoreCase(autore))
                .collect(Collectors.toSet());
    }

    public static Set<ElementoCatalogo> ricercaElementoPerAnno(Set<ElementoCatalogo> archivio, int anno) {
        return archivio.stream()
                .filter(elemento -> elemento.getAnno() == anno)
                .collect(Collectors.toSet());
    }

    public static Set<ElementoCatalogo> rimuoviElementoPerISBN(Set<ElementoCatalogo> archivio, String isbn) {
        return archivio.stream()
                .filter(elemento -> !elemento.getIsbn().equals(isbn))
                .collect(Collectors.toSet());
    }


}
