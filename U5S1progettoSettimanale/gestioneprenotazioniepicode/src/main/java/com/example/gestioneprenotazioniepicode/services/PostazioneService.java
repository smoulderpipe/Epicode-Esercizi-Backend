package com.example.gestioneprenotazioniepicode.services;
import com.example.gestioneprenotazioniepicode.entities.Postazione;
import com.example.gestioneprenotazioniepicode.enums.Tipo;
import com.example.gestioneprenotazioniepicode.repositories.PostazioneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostazioneService {
    @Autowired
    private PostazioneRepo postazioneRepo;

    public List<Postazione> getAllPostazioni(){
        return this.postazioneRepo.findAll();
    }

    public Postazione savePostazione(Postazione postazione){
        return this.postazioneRepo.save(postazione);
    }

    public Postazione findPostazioneById(UUID id){
        return this.postazioneRepo.findById(id).orElse(null);
    }

    public void deletePostazione(UUID id){
        this.postazioneRepo.deleteById(id);
    }

    public List<Postazione> getByTipoAndCitta(Tipo tipo, String citta) {
        return this.postazioneRepo.findByTipoAndEdificio_Citta(tipo, citta);
    }

}
