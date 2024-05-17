package it.epicode.esercizio1.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Pizza {
    @Id
    @GeneratedValue
    private UUID id;
    private int calorie;
    @OneToMany(mappedBy = "pizza")
    private List<Ingrediente> ingredienti;
}
