package it.epicode.gestionedispositivi.services;
import it.epicode.gestionedispositivi.dtos.DipendenteDto;
import it.epicode.gestionedispositivi.exceptions.DipendenteNonTrovatoException;
import it.epicode.gestionedispositivi.models.Dipendente;
import it.epicode.gestionedispositivi.models.Role;
import it.epicode.gestionedispositivi.repositories.DipendenteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepo dipendenteRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<Dipendente> getAllDipendenti(int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return dipendenteRepo.findAll(pageable);
    }

    public Optional<Dipendente> getDipendenteById(int id){
        return dipendenteRepo.findById(id);
    }

    public String saveDipendente(DipendenteDto dipendenteDto){
        Dipendente dipendenteDaSalvare = new Dipendente();
        dipendenteDaSalvare.setNome(dipendenteDto.getNome());
        dipendenteDaSalvare.setUsername(dipendenteDto.getUsername());
        dipendenteDaSalvare.setEmail(dipendenteDto.getEmail());
        dipendenteDaSalvare.setRole(Role.USER);
        dipendenteDaSalvare.setPassword(passwordEncoder.encode(dipendenteDto.getPassword()));
        dipendenteRepo.save(dipendenteDaSalvare);
        return "Dipendente con id=" + dipendenteDaSalvare.getId() + " salvato correttamente";
    }

    public Dipendente updateDipendente(int id, DipendenteDto dipendenteDto){
        Optional<Dipendente> dipendenteOptional = getDipendenteById(id);

        if(dipendenteOptional.isPresent()){
            Dipendente dipendenteDaAggiornare = dipendenteOptional.get();
            dipendenteDaAggiornare.setUsername(dipendenteDto.getUsername());
            dipendenteDaAggiornare.setNome(dipendenteDto.getNome());
            dipendenteDaAggiornare.setEmail(dipendenteDto.getEmail());
            return dipendenteRepo.save(dipendenteDaAggiornare);
        }
        else {
            throw new DipendenteNonTrovatoException("Dipendente con id=" + id + " non trovato");
        }
    }

    public String deleteDipendente(int id){
        Optional<Dipendente> dipendenteOptional = getDipendenteById(id);

        if(dipendenteOptional.isPresent()){
            dipendenteRepo.delete(dipendenteOptional.get());
            return "Dipendente con id=" + id + " cancellato correttamente";
        }
        else{
            throw new DipendenteNonTrovatoException("Dipendente con id=" + id + " non trovato");
        }
    }

    public Dipendente getDipendenteByEmail(String email){
        Optional<Dipendente> dipendenteOptional = dipendenteRepo.findByEmail(email);
        if(dipendenteOptional.isPresent()){
            return dipendenteOptional.get();
        } else {
            throw new DipendenteNonTrovatoException("Il dipendente con l'email=" + email + " non è stato trovato");
        }
    }
}
