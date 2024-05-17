package com.example.gestioneprenotazioniepicode.services;
import com.example.gestioneprenotazioniepicode.entities.Edificio;
import com.example.gestioneprenotazioniepicode.repositories.EdificioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class EdificioService {
    @Autowired
    private EdificioRepo edificioRepo;

    public List<Edificio> getAllEdifici(){
        return this.edificioRepo.findAll();
    }

    public Edificio saveEdificio(Edificio edificio){
        return this.edificioRepo.save(edificio);
    }

    public Edificio getEdificioById(UUID id){
        return this.edificioRepo.findById(id).orElse(null);
    }

    public void deleteEdificio (UUID id){
        this.edificioRepo.deleteById(id);
    }

}
