package it.epicode.esercizio1.entities;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Ingrediente {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;

    private String nome;
}
