package com.example.gestioneprenotazioniepicode.entities;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Edificio {
    @Id
    @GeneratedValue
    private UUID id;

    private String nome;
    private String indirizzo;
    private String citta;

    @OneToMany(mappedBy = "edificio", fetch = FetchType.EAGER)
    private List<Postazione> postazioni;

    @Override
    public String toString() {
        return "Edificio{" +
                "nome='" + nome + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", citta='" + citta + '\'' +
                ", id=" + id +
                '}';
    }
}
