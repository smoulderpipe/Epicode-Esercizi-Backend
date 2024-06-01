package it.epicode.gestioneEventi.services;
import it.epicode.gestioneEventi.dtos.UtenteDto;
import it.epicode.gestioneEventi.exceptions.NotFoundException;
import it.epicode.gestioneEventi.models.Ruolo;
import it.epicode.gestioneEventi.models.Utente;
import it.epicode.gestioneEventi.repositories.UtenteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepo utenteRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<Utente> getAllUtenti(int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return utenteRepo.findAll(pageable);
    }

    public Optional<Utente> getUtenteById(int id){
        return utenteRepo.findById(id);
    }

    public String saveUtente(UtenteDto utenteDto){
        Utente utenteDaSalvare = new Utente();
        utenteDaSalvare.setUsername(utenteDto.getUsername());
        utenteDaSalvare.setEmail(utenteDto.getEmail());
        utenteDaSalvare.setPassword(passwordEncoder.encode(utenteDto.getPassword()));
        utenteDaSalvare.setNomeCompleto(utenteDto.getNomeCompleto());
        utenteDaSalvare.setRuolo(Ruolo.UTENTE_NORMALE);
        utenteRepo.save(utenteDaSalvare);
        return "Utente con id=" + utenteDaSalvare.getId() + " salvato correttamente";
    }

    public Utente updateUtente(int id, UtenteDto utenteDto){
        Optional<Utente> utenteOptional = getUtenteById(id);
        if(utenteOptional.isPresent()){
            Utente utenteDaAggiornare = utenteOptional.get();
            utenteDaAggiornare.setUsername(utenteDto.getUsername());
            utenteDaAggiornare.setEmail(utenteDto.getEmail());
            utenteDaAggiornare.setNomeCompleto(utenteDto.getNomeCompleto());
            return utenteRepo.save(utenteDaAggiornare);
        } else {
            throw new NotFoundException("Utente con id=" + id + " non trovato");
        }
    }

    public String deleteUtente(int id){
        Optional<Utente> utenteOptional = getUtenteById(id);
        if(utenteOptional.isPresent()) {
            Utente utenteDaCancellare = utenteOptional.get();
            utenteRepo.delete(utenteDaCancellare);
            return "Utente con id=" + id + " cancellato correttamente";
        } else {
            throw new NotFoundException("Utente con id=" + id + " non trovato");
        }

    }

    public Utente getUtenteByEmail(String email){
        Optional<Utente> utenteOptional = utenteRepo.findByEmail(email);
        if(utenteOptional.isPresent()){
            return utenteOptional.get();
        } else {
            throw new NotFoundException("L'utente con l'email=" + email + " non è stato trovato");
        }
    }
}

