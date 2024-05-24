package it.epicode.blogWebService.services;
import it.epicode.blogWebService.exceptions.NotFoundException;
import it.epicode.blogWebService.models.Autore;
import it.epicode.blogWebService.models.CreateAutoreRequestBody;
import it.epicode.blogWebService.models.EditAutoreRequestBody;
import it.epicode.blogWebService.models.Post;
import it.epicode.blogWebService.repositories.AutoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class AutoreService {

    @Autowired
    private AutoreRepo autoreRepo;

    public Page<Autore> getAllAutori(int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return autoreRepo.findAll(pageable);
    }

    public Autore getAutoreById(UUID id){
        return autoreRepo.findById(id).orElseThrow(() -> new NotFoundException("Autore non trovato con id " + id));
    }

    public Autore addAutore(CreateAutoreRequestBody autoreRequestBody){
        try {
            Autore newAutore = new Autore(
                    autoreRequestBody.getNome(),
                    autoreRequestBody.getCognome(),
                    autoreRequestBody.getEmail(),
                    autoreRequestBody.getDataDiNascita()
            );
            return this.autoreRepo.save(newAutore);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante il salvataggio dell'autore", e);
        }

    }

    public Autore updateAutore(EditAutoreRequestBody autoreRequestBody, UUID id){
        Autore autoreDaAggiornare = this.autoreRepo.findById(id).orElse(null);
        if (autoreDaAggiornare != null) {
            autoreDaAggiornare.setNome(autoreRequestBody.getNome());
            autoreDaAggiornare.setCognome(autoreRequestBody.getCognome());
            autoreDaAggiornare.setEmail(autoreRequestBody.getEmail());
            autoreDaAggiornare.setDataDiNascita(autoreRequestBody.getDataDiNascita());
            return autoreRepo.save(autoreDaAggiornare);
        }
        return null;
    }

    public void deleteAutore(UUID id){
        this.autoreRepo.deleteById(id);
    }
}
