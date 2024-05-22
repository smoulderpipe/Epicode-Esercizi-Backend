package it.epicode.blogWebService.services;
import it.epicode.blogWebService.models.Autore;
import it.epicode.blogWebService.models.CreateAutoreRequestBody;
import it.epicode.blogWebService.models.EditAutoreRequestBody;
import it.epicode.blogWebService.repositories.AutoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AutoreService {

    @Autowired
    AutoreRepo autoreRepo;

    private List<Autore> autori = new ArrayList<>();

    public List<Autore> getAllAutori(){
        return this.autoreRepo.findAll();
    }

    public Autore getAutoreById(UUID id){
        return this.autoreRepo.findById(id).orElse(null);
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
