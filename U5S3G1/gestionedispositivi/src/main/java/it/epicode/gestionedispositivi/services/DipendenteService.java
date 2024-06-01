package it.epicode.gestionedispositivi.services;
import it.epicode.gestionedispositivi.dtos.DipendenteDto;
import it.epicode.gestionedispositivi.exceptions.DipendenteNonTrovatoException;
import it.epicode.gestionedispositivi.models.Dipendente;
import it.epicode.gestionedispositivi.repositories.DipendenteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepo dipendenteRepo;

    @Autowired
    private CloudinaryService cloudinaryService;

    public Page<Dipendente> getAllDipendenti(int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return dipendenteRepo.findAll(pageable);
    }

    public Optional<Dipendente> getDipendenteById(UUID id){
        return dipendenteRepo.findById(id);
    }

    public String saveDipendente(DipendenteDto dipendenteDto){
        Dipendente dipendenteDaSalvare = new Dipendente();
        dipendenteDaSalvare.setNome(dipendenteDto.getNome());
        dipendenteDaSalvare.setUsername(dipendenteDto.getUsername());
        dipendenteDaSalvare.setEmail(dipendenteDto.getEmail());
        dipendenteDaSalvare.setPassword(dipendenteDto.getPassword());
        dipendenteRepo.save(dipendenteDaSalvare);
        return "Dipendente con id=" + dipendenteDaSalvare.getId() + " salvato correttamente";
    }

    public Dipendente updateDipendente(UUID id, DipendenteDto dipendenteDto){
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

    public String deleteDipendente(UUID id){
        Optional<Dipendente> dipendenteOptional = getDipendenteById(id);

        if(dipendenteOptional.isPresent()){
            dipendenteRepo.delete(dipendenteOptional.get());
            return "Dipendente con id=" + id + " cancellato correttamente";
        }
        else{
            throw new DipendenteNonTrovatoException("Dipendente con id=" + id + " non trovato");
        }
    }

    /*public String uploadProfileImage(UUID id, MultipartFile file) {
        try {
            String imageUrl = cloudinaryService.uploadProfileImage(file); // Implementa questo metodo per caricare l'immagine su Cloudinary
            Dipendente dipendente = dipendenteRepo.findById(id).orElseThrow(() -> new DipendenteNonTrovatoException("Dipendente con id=" + id + " non trovato"));
            dipendente.setAvatar(imageUrl);
            dipendenteRepo.save(dipendente);
            return "Immagine del profilo caricata correttamente";
        } catch (IOException e) {
            throw new RuntimeException("Errore durante il caricamento dell'immagine del profilo", e);
        }
    }*/

    public Dipendente getDipendenteByEmail(String email){
        Optional<Dipendente> dipendenteOptional = dipendenteRepo.findByEmail(email);
        if(dipendenteOptional.isPresent()){
            return dipendenteOptional.get();
        } else {
            throw new DipendenteNonTrovatoException("Il dipendente con l'email=" + email + " non è stato trovato");
        }
    }
}
