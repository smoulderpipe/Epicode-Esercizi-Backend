package it.epicode.esercizio1.services;
import it.epicode.esercizio1.entities.Pizza;
import it.epicode.esercizio1.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;


@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository){
        this.pizzaRepository = pizzaRepository;
    }

    public List<Pizza> getAllPizzas(){
        return this.pizzaRepository.findAll();
    }

    public Pizza savePizza(Pizza pizza){
        return this.pizzaRepository.save(pizza);
    }

    public Pizza getPizzaById(UUID id) {
        return this.pizzaRepository.findById(id).orElse(null);
    }

    public void deleteDrink(UUID id){
        this.pizzaRepository.deleteById(id);
    }
}
