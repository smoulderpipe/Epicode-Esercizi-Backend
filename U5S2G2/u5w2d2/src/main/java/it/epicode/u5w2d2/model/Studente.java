package it.epicode.u5w2d2.model;

import lombok.Data;

import java.time.LocalDate;

//vogliamo creare un servizio per gli studenti, es che voglia cancellarli, estrarli, crearne di nuovi, etc...

@Data
public class Studente {
    private int matricola;
    private static int cont;
    private String nome;
    private String cognome;
    private LocalDate dataNascita;

    public Studente(String nome, String cognome, LocalDate dataNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        cont++;
        matricola = cont;
    }
}
