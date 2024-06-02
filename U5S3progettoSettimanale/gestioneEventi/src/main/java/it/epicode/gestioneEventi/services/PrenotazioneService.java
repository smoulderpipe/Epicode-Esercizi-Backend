package it.epicode.gestioneEventi.services;
import it.epicode.gestioneEventi.dtos.PrenotazioneDto;
import it.epicode.gestioneEventi.exceptions.BadRequestException;
import it.epicode.gestioneEventi.exceptions.NotFoundException;
import it.epicode.gestioneEventi.models.Evento;
import it.epicode.gestioneEventi.models.Prenotazione;
import it.epicode.gestioneEventi.models.Utente;
import it.epicode.gestioneEventi.repositories.EventoRepo;
import it.epicode.gestioneEventi.repositories.PrenotazioneRepo;
import it.epicode.gestioneEventi.repositories.UtenteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepo prenotazioneRepo;

    @Autowired
    private EventoRepo eventoRepo;

    @Autowired
    private UtenteRepo utenteRepo;

    public Page<Prenotazione> getAllPrenotazioni(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return prenotazioneRepo.findAll(pageable);
    }

    public Optional<Prenotazione> getPrenotazioneById(int id) {
        return prenotazioneRepo.findById(id);
    }

    public List<Prenotazione> getPrenotazioniByUtente(int userId) {
        return prenotazioneRepo.findByUtenteId(userId);
    }


    public String savePrenotazione(PrenotazioneDto prenotazioneDto) {
        Optional<Evento> eventoOpt = eventoRepo.findById(prenotazioneDto.getEventoId());

        if (eventoOpt.isEmpty()) {
            throw new BadRequestException("Evento non trovato");
        }

        Evento evento = eventoOpt.get();

        if (evento.getNumeroPostiDisponibili() <= 0) {
            throw new BadRequestException("Nessun posto disponibile per questo evento");
        }

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }

        Optional<Utente> utenteOpt = utenteRepo.findByEmail(email);

        if (utenteOpt.isEmpty()) {
            throw new BadRequestException("Utente non trovato");
        }

        Utente utente = utenteOpt.get();

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setEvento(evento);
        prenotazione.setUtente(utente);
        prenotazioneRepo.save(prenotazione);

        evento.setNumeroPostiDisponibili(evento.getNumeroPostiDisponibili() - 1);
        eventoRepo.save(evento);

        return "Prenotazione creata con successo";
    }

    public Prenotazione updatePrenotazione(int id, PrenotazioneDto prenotazioneDto) {
        Optional<Prenotazione> prenotazioneOptional = getPrenotazioneById(id);

        if (prenotazioneOptional.isEmpty()) {
            throw new NotFoundException("Prenotazione con id=" + id + " non trovata");
        }

        Prenotazione prenotazioneDaAggiornare = prenotazioneOptional.get();

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }

        Optional<Utente> utenteOpt = utenteRepo.findByEmail(email);

        if (utenteOpt.isEmpty()) {
            throw new BadRequestException("Utente non trovato");
        }

        Utente utente = utenteOpt.get();

        if (prenotazioneDaAggiornare.getUtente().getId() != utente.getId()) {
            throw new BadRequestException("Non sei autorizzato ad aggiornare questa prenotazione");
        }

        int eventoId = prenotazioneDto.getEventoId();
        Optional<Evento> eventoOptional = eventoRepo.findById(eventoId);

        if (eventoOptional.isEmpty()) {
            throw new NotFoundException("Evento con id=" + eventoId + " non trovato");
        }

        Evento evento = eventoOptional.get();

        if (evento.getNumeroPostiDisponibili() <= 0) {
            throw new BadRequestException("Nessun posto disponibile per questo evento");
        }

        prenotazioneDaAggiornare.setEvento(evento);

        return prenotazioneRepo.save(prenotazioneDaAggiornare);
    }

    public String deletePrenotazione(int id){
        Optional<Prenotazione> prenotazioneOptional = getPrenotazioneById(id);
        if(prenotazioneOptional.isPresent()){
            Prenotazione prenotazioneDaCancellare = prenotazioneOptional.get();
            prenotazioneRepo.delete(prenotazioneDaCancellare);
            return "Prenotazione con id=" + id + " cancellata correttamente";
        } else {
            throw new NotFoundException("Prenotazione con id=" + id + " non trovata");
        }
    }

}
