package it.epicode.esercizio1.runner;

import it.epicode.esercizio1.entities.Drink;
import it.epicode.esercizio1.entities.Ingrediente;
import it.epicode.esercizio1.entities.Pizza;
import it.epicode.esercizio1.services.DrinkService;
import it.epicode.esercizio1.services.IngredienteService;
import it.epicode.esercizio1.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {
    @Autowired
    private DrinkService drinkService;
    @Autowired
    private PizzaService pizzaService;
    @Autowired
    private IngredienteService ingredienteService;

    @Override
    public void run(String... args) throws Exception {
        Drink drink1 = new Drink();
        drink1.setCalorie(100);
        drink1.setMl(200);
        drink1.setNome("Coca Cola");

        Drink drink2 = new Drink();
        drink2.setCalorie(0);
        drink2.setMl(500);
        drink2.setNome("Acqua");

        drinkService.saveDrink(drink1);
        drinkService.saveDrink(drink2);

        Pizza pizza1 = new Pizza();
        pizza1.setCalorie(1000);
        pizzaService.savePizza(pizza1);

        Ingrediente mozzarella = new Ingrediente();
        mozzarella.setNome("Mozzarella");
        mozzarella.setPizza(pizza1);
        Ingrediente savedMozzarella = ingredienteService.saveIngrediente(mozzarella);
        System.out.println(savedMozzarella);

    }
}
