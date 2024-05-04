import dao.ElementoCatalogoDao;
import dao.PrestitoDao;
import dao.UtenteDao;
import entities.*;
import enums.TipoPeriodicita;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogo_bibliografico");
        EntityManager em = emf.createEntityManager();
        ElementoCatalogoDao dao = new ElementoCatalogoDao(em);

        Libro l1 = new Libro();
        l1.setAnno(2020);
        l1.setPagine(200);
        l1.setTitolo("Il Processo");
        l1.setAutore("Franz Kafka");
        l1.setGenere("Romanzo");

        Libro l2 = new Libro();
        l2.setAnno(1967);
        l2.setPagine(467);
        l2.setTitolo("Cent'anni di solitudine");
        l2.setAutore("Gabriel García Márquez");
        l2.setGenere("Romanzo");

        Libro l3 = new Libro();
        l3.setAnno(1958);
        l3.setPagine(624);
        l3.setTitolo("Il Gattopardo");
        l3.setAutore("Giuseppe Tomasi di Lampedusa");
        l3.setGenere("Romanzo storico");

        Libro l4 = new Libro();
        l4.setAnno(1878);
        l4.setPagine(864);
        l4.setTitolo("Anna Karenina");
        l4.setAutore("Lev Tolstoy");
        l4.setGenere("Romanzo");

        Libro l5 = new Libro();
        l5.setAnno(1605);
        l5.setPagine(1056);
        l5.setTitolo("Don Chisciotte della Mancia");
        l5.setAutore("Miguel de Cervantes");
        l5.setGenere("Romanzo cavalleresco");

        Libro l6 = new Libro();
        l6.setAnno(1851);
        l6.setPagine(608);
        l6.setTitolo("Moby Dick");
        l6.setAutore("Herman Melville");
        l6.setGenere("Romanzo");

        Libro l7 = new Libro();
        l7.setAnno(1922);
        l7.setPagine(792);
        l7.setTitolo("Ulisse");
        l7.setAutore("James Joyce");
        l7.setGenere("Romanzo sperimentale");

        Libro l8 = new Libro();
        l8.setAnno(1948);
        l8.setPagine(228);
        l8.setTitolo("1984");
        l8.setAutore("George Orwell");
        l8.setGenere("Romanzo distopico");

        Libro l9 = new Libro();
        l9.setAnno(1967);
        l9.setPagine(328);
        l9.setTitolo("Il Maestro e Margherita");
        l9.setAutore("Mikhail Bulgakov");
        l9.setGenere("Romanzo satirico");

        Libro l10 = new Libro();
        l10.setAnno(1954);
        l10.setPagine(1178);
        l10.setTitolo("Il Signore degli Anelli");
        l10.setAutore("J.R.R. Tolkien");
        l10.setGenere("Fantasy");

        dao.save(l1);
        dao.save(l2);
        dao.save(l3);
        dao.save(l4);
        dao.save(l5);
        dao.save(l6);
        dao.save(l7);
        dao.save(l8);
        dao.save(l9);
        dao.save(l10);

        Rivista r1 = new Rivista();
        r1.setAnno(2019);
        r1.setPagine(57);
        r1.setTitolo("Cioè");
        r1.setTipoPeriodicita(TipoPeriodicita.MENSILE);

        Rivista r2 = new Rivista();
        r2.setAnno(2023);
        r2.setPagine(120);
        r2.setTitolo("National Geographic");
        r2.setTipoPeriodicita(TipoPeriodicita.MENSILE);

        Rivista r3 = new Rivista();
        r3.setAnno(2023);
        r3.setPagine(64);
        r3.setTitolo("The Economist");
        r3.setTipoPeriodicita(TipoPeriodicita.SETTIMANALE);

        Rivista r4 = new Rivista();
        r4.setAnno(2023);
        r4.setPagine(72);
        r4.setTitolo("Scientific American");
        r4.setTipoPeriodicita(TipoPeriodicita.MENSILE);

        Rivista r5 = new Rivista();
        r5.setAnno(2023);
        r5.setPagine(256);
        r5.setTitolo("Vogue");
        r5.setTipoPeriodicita(TipoPeriodicita.MENSILE);

        Rivista r6 = new Rivista();
        r6.setAnno(2023);
        r6.setPagine(48);
        r6.setTitolo("Time");
        r6.setTipoPeriodicita(TipoPeriodicita.SETTIMANALE);

        Rivista r7 = new Rivista();
        r7.setAnno(2023);
        r7.setPagine(128);
        r7.setTitolo("The New Yorker");
        r7.setTipoPeriodicita(TipoPeriodicita.SETTIMANALE);

        Rivista r8 = new Rivista();
        r8.setAnno(2023);
        r8.setPagine(60);
        r8.setTitolo("The New York Times Magazine");
        r8.setTipoPeriodicita(TipoPeriodicita.SETTIMANALE);

        Rivista r9 = new Rivista();
        r9.setAnno(2023);
        r9.setPagine(104);
        r9.setTitolo("Harvard Business Review");
        r9.setTipoPeriodicita(TipoPeriodicita.SEMESTRALE);

        dao.save(r1);
        dao.save(r2);
        dao.save(r3);
        dao.save(r4);
        dao.save(r5);
        dao.save(r6);
        dao.save(r7);
        dao.save(r8);
        dao.save(r9);

        UUID isbnElementoDaCercare = r8.getIsbn();
        ElementoCatalogo elementoTrovato = dao.getByISBN(isbnElementoDaCercare);
        System.out.println("RISULTATO RICERCA PER ISBN: " + isbnElementoDaCercare);
        System.out.println("Elemento trovato:");
        if(elementoTrovato != null) {
            System.out.println(elementoTrovato);
        } else {
            System.out.println("Nessun elemento trovato per l'ISBN specificato.");
        }

        System.out.println("\n");

        List<ElementoCatalogo> elementiPerAnno = dao.findByAnno(2019);
        System.out.println("RISULTATO RICERCA PER ANNO (2019)");
        System.out.println("Elementi trovati:");
        for (ElementoCatalogo elemento: elementiPerAnno) {
            System.out.println(elemento);
        }
        System.out.println("\n");

        List<Libro> libriPerAutore = dao.findByAutore("franz Kafka");
        System.out.println("RISULTATO RICERCA PER AUTORE (Franz Kafka)");
        System.out.println("Elementi trovati:");
        for (Libro libro : libriPerAutore) {
            System.out.println(libro);
        }
        System.out.println("\n");

        List<ElementoCatalogo> elementiPerTitolo = dao.findByTitolo("ne");
        System.out.println("RISULTATO RICERCA PER TITOLO (contenente 'ne')");
        System.out.println("Elementi trovati:");
        for (ElementoCatalogo elemento : elementiPerTitolo) {
            System.out.println(elemento);
        }
        System.out.println("\n");

        UtenteDao utenteDao = new UtenteDao(em);

        Utente u1 = new Utente();
        u1.setNome("Mario");
        u1.setCognome("Rossi");
        u1.setDataDiNascita(LocalDate.of(2000, 4, 5));

        Utente u2 = new Utente();
        u2.setNome("Lucia");
        u2.setCognome("Verdi");
        u2.setDataDiNascita(LocalDate.of(1990, 3, 16));

        Utente u3 = new Utente();
        u3.setNome("Benedetta");
        u3.setCognome("Neri");
        u3.setDataDiNascita(LocalDate.of(1980, 12, 2));

        Utente u4 = new Utente();
        u4.setNome("Alfredo");
        u4.setCognome("Bianchi");
        u4.setDataDiNascita(LocalDate.of(1975, 7, 29));

        Utente u5 = new Utente();
        u5.setNome("Francesca");
        u5.setCognome("Rosi");
        u5.setDataDiNascita(LocalDate.of(1997, 11, 7));

        Utente u6 = new Utente();
        u6.setNome("Giorgio");
        u6.setCognome("Viola");
        u6.setDataDiNascita(LocalDate.of(1982, 10, 25));

        Utente u7 = new Utente();
        u7.setNome("Settimio");
        u7.setCognome("Marrone");
        u7.setDataDiNascita(LocalDate.of(1964, 2, 21));

        utenteDao.save(u1);
        utenteDao.save(u2);
        utenteDao.save(u3);
        utenteDao.save(u4);
        utenteDao.save(u5);
        utenteDao.save(u6);
        utenteDao.save(u7);

        PrestitoDao prestitoDao = new PrestitoDao(em);
        Prestito p1 = new Prestito();
        p1.setUtente(u1);
        p1.setElementoPrestato(r1);
        p1.setDataInizioPrestito(LocalDate.of(2023, 7, 11));
        p1.setDataRestituzionePrevista(p1.getDataRestituzionePrevista());

        Prestito p2 = new Prestito();
        p2.setUtente(u2);
        p2.setElementoPrestato(l2);
        p2.setDataInizioPrestito(LocalDate.of(2024, 4, 21));
        p2.setDataRestituzionePrevista(p2.getDataRestituzionePrevista());

        Prestito p3 = new Prestito();
        p3.setUtente(u3);
        p3.setElementoPrestato(l3);
        p3.setDataInizioPrestito(LocalDate.of(2024, 2, 2));
        p3.setDataRestituzionePrevista(p3.getDataRestituzionePrevista());
        p3.setDataRestituzioneEffettiva(LocalDate.of(2024, 2, 16));

        Prestito p4 = new Prestito();
        p4.setUtente(u4);
        p4.setElementoPrestato(l4);
        p4.setDataInizioPrestito(LocalDate.of(2022, 12, 7));
        p4.setDataRestituzionePrevista(p4.getDataRestituzionePrevista());
        p4.setDataRestituzioneEffettiva(LocalDate.of(2022, 12, 27));

        Prestito p5 = new Prestito();
        p5.setUtente(u4);
        p5.setElementoPrestato(r1);
        p5.setDataInizioPrestito(LocalDate.of(2023, 6, 17));
        p5.setDataRestituzionePrevista(p5.getDataRestituzionePrevista());
        p5.setDataRestituzioneEffettiva(LocalDate.of(2023, 7, 8));

        Prestito p6 = new Prestito();
        p6.setUtente(u4);
        p6.setElementoPrestato(r2);
        p6.setDataInizioPrestito(LocalDate.of(2021, 3, 12));
        p6.setDataRestituzionePrevista(p6.getDataRestituzionePrevista());
        p6.setDataRestituzioneEffettiva(LocalDate.of(2021, 5, 1));

        Prestito p7 = new Prestito();
        p7.setUtente(u5);
        p7.setElementoPrestato(l5);
        p7.setDataInizioPrestito(LocalDate.of(2024, 4, 20));
        p7.setDataRestituzionePrevista(p7.getDataRestituzionePrevista());
        p7.setDataRestituzioneEffettiva(LocalDate.of(2024, 5, 17));

        prestitoDao.save(p1);
        prestitoDao.save(p2);
        prestitoDao.save(p3);
        prestitoDao.save(p4);
        prestitoDao.save(p5);
        prestitoDao.save(p6);
        prestitoDao.save(p7);

        int numeroTesseraUtente = u4.getNumeroTessera();
        List<Prestito> prestitiUtente = prestitoDao.findByNumeroTesseraUtente(numeroTesseraUtente);
        System.out.println("RISULTATO RICERCA PER NUMERO TESSERA " + numeroTesseraUtente);
        System.out.println("Elementi attualmente in prestito per l'utente con numero di tessera " + numeroTesseraUtente + ":");
        if (!prestitiUtente.isEmpty()) {
            for (Prestito prestito : prestitiUtente) {
                System.out.println(prestito.getElementoPrestato());
            }
        } else {
            System.out.println("Nessun elemento trovato.");
        }

        System.out.println("\n");

        List<Prestito> prestitiScaduti = prestitoDao.findScaduti();
        System.out.println("RISULTATO RICERCA PRESTITI SCADUTI");
        System.out.println("Elementi prestati e non ancora restituiti, o restituiti in ritardo:");
        if (!prestitiScaduti.isEmpty()){
            for (Prestito prestito : prestitiScaduti) {
                System.out.println(prestito);
            }
        } else {
            System.out.println("Nessun elemento trovato.");
        }
        System.out.println("\n");

        UUID isbnElementoDaRimuovere = l10.getIsbn();
        System.out.println("RISULTATO ELIMINAZIONE ELEMENTO TRAMITE ISBN");
        System.out.println("E' stato rimosso l'elemento del catalogo con ISBN: " + isbnElementoDaRimuovere + " intitolato: " + l10.getTitolo());
        dao.rimuoviPerISBN(l10.getIsbn());

    }

}

