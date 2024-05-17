package it.epicode.esercizio1.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Drink {
    @Id
    @GeneratedValue
    private UUID id;

    private int calorie;
    private String nome;
    private int ml;
}
