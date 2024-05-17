package com.example.gestioneprenotazioniepicode.services;
import com.example.gestioneprenotazioniepicode.entities.Postazione;
import com.example.gestioneprenotazioniepicode.entities.Prenotazione;
import com.example.gestioneprenotazioniepicode.entities.Utente;
import com.example.gestioneprenotazioniepicode.repositories.PostazioneRepo;
import com.example.gestioneprenotazioniepicode.repositories.PrenotazioneRepo;
import com.example.gestioneprenotazioniepicode.repositories.UtenteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepo prenotazioneRepo;

    @Autowired
    private PostazioneRepo postazioneRepo;

    @Autowired
    private UtenteRepo utenteRepo;

    public List<Prenotazione> getAllPrenotazioni(){
        return this.prenotazioneRepo.findAll();
    }

    public Prenotazione savePrenotazione(Prenotazione prenotazione){

        Postazione postazione = postazioneRepo.findById(prenotazione.getPostazione().getId())
                .orElseThrow(() -> new IllegalArgumentException("Postazione non trovata"));

        Utente utente = utenteRepo.findById(prenotazione.getUtente().getId())
                .orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));

        if(prenotazioneRepo.existsByPostazioneAndData(prenotazione.getPostazione(), prenotazione.getData())) {
            throw new IllegalArgumentException("La postazione è già stata prenotata per questa data");
        }
        if(prenotazioneRepo.existsByUtenteAndData(prenotazione.getUtente(), prenotazione.getData())) {
            throw new IllegalArgumentException("L'utente ha già una prenotazione per questa data");
        }
        prenotazione.setPostazione(postazione);
        prenotazione.setUtente(utente);
        return this.prenotazioneRepo.save(prenotazione);

    }

    public Prenotazione getPrenotazioneById(UUID id){
        return this.prenotazioneRepo.findById(id).orElse(null);
    }

    public void deletePrenotazione(UUID id){
        this.prenotazioneRepo.deleteById(id);
    }

}