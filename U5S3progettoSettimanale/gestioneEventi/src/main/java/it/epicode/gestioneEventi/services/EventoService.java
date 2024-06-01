package it.epicode.gestioneEventi.services;
import it.epicode.gestioneEventi.dtos.EventoDto;
import it.epicode.gestioneEventi.exceptions.NotFoundException;
import it.epicode.gestioneEventi.exceptions.UnauthorizedException;
import it.epicode.gestioneEventi.models.Evento;
import it.epicode.gestioneEventi.repositories.EventoRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepo eventoRepo;

    @Autowired
    private HttpServletRequest request;

    public Page<Evento> getAllEventi(int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return eventoRepo.findAll(pageable);
    }

    public Optional<Evento> getEventoById(int id){
        return eventoRepo.findById(id);
    }

    public String saveEvento(EventoDto eventoDto){
        Evento eventoDaSalvare = new Evento();
        eventoDaSalvare.setTitolo(eventoDto.getTitolo());
        eventoDaSalvare.setLuogo(eventoDto.getLuogo());
        eventoDaSalvare.setNumeroPostiDisponibili(eventoDto.getNumeroPostiDisponibili());
        eventoDaSalvare.setDescrizione(eventoDto.getDescrizione());
        eventoDaSalvare.setData(eventoDto.getData());
        eventoRepo.save(eventoDaSalvare);
        return "Evento con id=" + eventoDaSalvare.getId() + " salvato correttamente";
    }

    public Evento updateEvento(int id, EventoDto eventoDto){
        Optional<Evento> eventoOptional = getEventoById(id);
        if(eventoOptional.isPresent()){
            Evento eventoDaAggiornare = eventoOptional.get();
            eventoDaAggiornare.setTitolo(eventoDto.getTitolo());
            eventoDaAggiornare.setLuogo(eventoDto.getLuogo());
            eventoDaAggiornare.setNumeroPostiDisponibili(eventoDto.getNumeroPostiDisponibili());
            eventoDaAggiornare.setDescrizione(eventoDto.getDescrizione());
            eventoDaAggiornare.setData(eventoDto.getData());
            return eventoRepo.save(eventoDaAggiornare);
        } else {
            throw new NotFoundException("Evento con id=" + id + " non trovato");
        }
    }

    public String deleteEvento(int id){
        Optional<Evento> eventoOptional = getEventoById(id);
        if(eventoOptional.isPresent()) {
            Evento eventoDaCancellare = eventoOptional.get();
            eventoRepo.delete(eventoDaCancellare);
            return "Evento con id=" + id + " cancellato correttamente";
        } else {
            throw new NotFoundException("Evento con id=" + id + " non trovato");
        }
    }

}
