package it.epicode.esercizio1.services;
import it.epicode.esercizio1.entities.Ingrediente;
import it.epicode.esercizio1.repositories.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class IngredienteService {
    @Autowired
    private IngredienteRepository ingredienteRepository;

    public List<Ingrediente> getAllIngredienti(){
        return this.ingredienteRepository.findAll();
    }

    public Ingrediente saveIngrediente(Ingrediente ingrediente){
        return this.ingredienteRepository.save(ingrediente);
    }

    public Ingrediente getIngredienteById(UUID id){
        return this.ingredienteRepository.findById(id).orElse(null);
    }

    public void deleteIngrediente(UUID id){
        this.ingredienteRepository.deleteById(id);
    }
}
