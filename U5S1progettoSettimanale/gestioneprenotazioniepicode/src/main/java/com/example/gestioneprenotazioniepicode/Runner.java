package com.example.gestioneprenotazioniepicode;
import com.example.gestioneprenotazioniepicode.entities.Edificio;
import com.example.gestioneprenotazioniepicode.entities.Postazione;
import com.example.gestioneprenotazioniepicode.entities.Prenotazione;
import com.example.gestioneprenotazioniepicode.entities.Utente;
import com.example.gestioneprenotazioniepicode.enums.Tipo;
import com.example.gestioneprenotazioniepicode.services.EdificioService;
import com.example.gestioneprenotazioniepicode.services.PostazioneService;
import com.example.gestioneprenotazioniepicode.services.PrenotazioneService;
import com.example.gestioneprenotazioniepicode.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;

@Component
public class Runner implements CommandLineRunner {
    @Autowired
    private EdificioService edificioService;

    @Autowired
    private PostazioneService postazioneService;

    @Autowired
    private PrenotazioneService prenotazioneService;

    @Autowired
    private UtenteService utenteService;

    @Override
    public void run(String... args) throws Exception {
        Utente utente1 = new Utente();
        utente1.setUsername("dennis");
        utente1.setNomeCompleto("Danilo Rossi");
        utente1.setEmail("danilorossi@gmail.com");
        utente1 = utenteService.saveUtente(utente1);

        Edificio edificio1 = new Edificio();
        edificio1.setCitta("Roma");
        edificio1.setNome("Parioli Coworking Spaces");
        edificio1.setIndirizzo("Via Lazio 12");
        edificioService.saveEdificio(edificio1);

        Postazione postazione1 = new Postazione();
        postazione1.setTipo(Tipo.SALA_RIUNIONI);
        postazione1.setEdificio(edificio1);
        postazione1.setNumeroMaxOccupanti(20);
        postazione1.setDescrizione("Lounge con vista sulla città.");
        postazioneService.savePostazione(postazione1);

        Postazione postazione2 = new Postazione();
        postazione2.setTipo(Tipo.PRIVATO);
        postazione2.setEdificio(edificio1);
        postazione2.setNumeroMaxOccupanti(5);
        postazione2.setDescrizione("Studio arredato.");
        postazioneService.savePostazione(postazione2);

        LocalDate data1 = LocalDate.of(2020, 1, 8);
        LocalDate data2 = LocalDate.of(2020,1,9);
        LocalDate data3 = LocalDate.of(2020,1,10);

        Prenotazione prenotazione1 = new Prenotazione();
        prenotazione1.setUtente(utente1);
        prenotazione1.setPostazione(postazione1);
        prenotazione1.setData(data1);
        prenotazioneService.savePrenotazione(prenotazione1);

        Prenotazione prenotazione2 = new Prenotazione();
        prenotazione2.setUtente(utente1);
        prenotazione2.setPostazione(postazione1);
        prenotazione2.setData(data2);
        prenotazioneService.savePrenotazione(prenotazione2);

        String tipo = Tipo.PRIVATO.toString();
        String citta = "Roma";
        List<Postazione> postazioni = postazioneService.getByTipoAndCitta(Tipo.PRIVATO, citta);
        System.out.println("Postazioni trovate per tipo " + tipo + " e città " + citta);
        if(postazioni.isEmpty()) {
            System.out.println("Nessuna postazione trovata.");
        } else {
            for(Postazione postazione: postazioni) {
                System.out.println(postazione);
            }
        }

        Prenotazione prenotazione3 = new Prenotazione();
        prenotazione3.setUtente(utente1);
        prenotazione3.setPostazione(postazione2);
        prenotazione3.setData(data1);

        try {
            prenotazioneService.savePrenotazione(prenotazione3);
            System.out.println("Prenotazione aggiunta con successo.");
        } catch(IllegalArgumentException e) {
            System.out.println("Errore durante la prenotazione: " + e.getMessage());
        }

        Prenotazione prenotazione4 = new Prenotazione();
        prenotazione4.setUtente(utente1);
        prenotazione4.setPostazione(postazione2);
        prenotazione4.setData(data3);

        try {
            prenotazioneService.savePrenotazione(prenotazione4);
            System.out.println("Prenotazione aggiunta con successo.");
        } catch(IllegalArgumentException e) {
            System.out.println("Errore durante la prenotazione: " + e.getMessage());
        }


    }
}