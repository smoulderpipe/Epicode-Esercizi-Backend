package it.epicode.progettoSpring.bean;

import lombok.Data;

@Data
public class Bevanda extends VoceDiMenu {
    private int ml;
    private String nome;
    private int gradoAlcohol;
}
