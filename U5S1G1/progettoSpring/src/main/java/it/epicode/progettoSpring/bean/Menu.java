package it.epicode.progettoSpring.bean;

import lombok.Data;

import java.util.List;

@Data
public class Menu {

    private List<Ingrediente> ingredienti;
    private List<Bevanda> bevande;
    private List<Pizza> pizze;

    public void stampaMenu(){
        System.out.println("Menu");
        System.out.println("Bevande");

        bevande.forEach(bevanda -> System.out.println(bevanda.getNome()
                + " ml " + bevanda.getMl()
                + " Kcal: " + bevanda.getCalorie()
                + " prezzo: " + bevanda.getPrezzo()));

        System.out.println();
        System.out.println("Ingredienti");
        ingredienti.forEach(ingrediente -> System.out.println(ingrediente.getNome()
                + " Kcal: " + ingrediente.getCalorie()
                + " prezzo: " + ingrediente.getPrezzo()));

        System.out.println();
        System.out.println("Pizze");
        pizze.forEach(pizza -> System.out.println(pizza.getNome()
                + " Kcal: " + pizza.getCalorie()
                + " prezzo " + pizza.getPrezzo()));

    }
}