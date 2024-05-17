package com.example.gestioneprenotazioniepicode.services;
import com.example.gestioneprenotazioniepicode.entities.Utente;
import com.example.gestioneprenotazioniepicode.repositories.UtenteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepo utenteRepo;

    public List<Utente> getAllUtenti(){
        return this.utenteRepo.findAll();
    }

    public Utente saveUtente(Utente utente){
        return this.utenteRepo.save(utente);
    }

    public Utente getUtenteById(UUID id){
        return this.utenteRepo.findById(id).orElse(null);
    }

    public void deleteUtente(UUID id){
        this.utenteRepo.deleteById(id);
    }
}
