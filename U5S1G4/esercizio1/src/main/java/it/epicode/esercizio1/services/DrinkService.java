package it.epicode.esercizio1.services;
import it.epicode.esercizio1.entities.Drink;
import it.epicode.esercizio1.repositories.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class DrinkService {
    @Autowired
    private DrinkRepository drinkRepo;

    public List<Drink> getAllDrinks(){
        return this.drinkRepo.findAll();
    }

    public Drink saveDrink(Drink drink){
        return this.drinkRepo.save(drink);
    }

    public Drink getDrinkById(UUID id){
        return this.drinkRepo.findById(id).orElse(null);
    }

    public void deleteDrink(UUID id){
        this.drinkRepo.deleteById(id);
    }

    public List<Drink> getDrinkByName(String nome){
        return this.drinkRepo.findByNome(nome);
    }
}
