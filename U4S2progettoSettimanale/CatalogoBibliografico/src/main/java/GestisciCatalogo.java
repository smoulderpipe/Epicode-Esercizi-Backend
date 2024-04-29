import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;
import eccezioni.AttributiNonValidiException;
import eccezioni.ISBNDuplicatoException;
import org.apache.commons.io.FileUtils;

public class GestisciCatalogo {

    public void salvataggio(File file, Set<ElementoCatalogo> archivio) throws IOException {

        String archivioString = archivio.stream()
                .map(ElementoCatalogo::toString)
                .collect(Collectors.joining("\n"));

        try {
            FileUtils.writeStringToFile(file, archivioString, Charset.defaultCharset());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void caricamento(File file, Set<ElementoCatalogo> archivio) throws IOException {

        try {
            String str = FileUtils.readFileToString(file, Charset.defaultCharset());
            String[] archivioStr =  str.split("\n");

            Arrays.stream(archivioStr).forEach(s -> System.out.println(s));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void eseguiRicerca(Set<ElementoCatalogo> archivio, Scanner scanner) {
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


    public void eseguiRimozione(Set<ElementoCatalogo> archivio, Scanner scanner) {
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

    public void eseguiAggiunta(Set<ElementoCatalogo> archivio, Scanner scanner) {
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
                                if (ricercaElementoPerISBN(archivio, isbnLibro) != null) {
                                    throw new ISBNDuplicatoException("ISBN già presente in archivio. Inserisci un ISBN diverso.");
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
                            isValid = true;


                        } catch (InputMismatchException e) {
                            System.out.println("Errore nel formato di dato.");
                        } catch (AttributiNonValidiException e) {
                            System.out.println(e.getMessage());
                        } catch (ISBNDuplicatoException e) {
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

                            isValid = true;


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


    public ElementoCatalogo ricercaElementoPerISBN(Set<ElementoCatalogo> archivio, String isbn) {
        return archivio.stream()
                .filter(elemento -> elemento.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }

    public Set<ElementoCatalogo> ricercaElementoPerAutore(Set<ElementoCatalogo> archivio, String autore) {
        return archivio.stream()
                .filter(elemento -> elemento instanceof Libro)
                .filter(libro -> ((Libro) libro).getAutore().equalsIgnoreCase(autore))
                .collect(Collectors.toSet());
    }

    public Set<ElementoCatalogo> ricercaElementoPerAnno(Set<ElementoCatalogo> archivio, int anno) {
        return archivio.stream()
                .filter(elemento -> elemento.getAnno() == anno)
                .collect(Collectors.toSet());
    }

    public Set<ElementoCatalogo> rimuoviElementoPerISBN(Set<ElementoCatalogo> archivio, String isbn) {
        return archivio.stream()
                .filter(elemento -> !elemento.getIsbn().equals(isbn))
                .collect(Collectors.toSet());
    }


}
