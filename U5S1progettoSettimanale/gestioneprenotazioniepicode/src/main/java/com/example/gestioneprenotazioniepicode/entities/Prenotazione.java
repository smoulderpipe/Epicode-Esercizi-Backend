package com.example.gestioneprenotazioniepicode.entities;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
public class Prenotazione {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Postazione postazione;

    @ManyToOne
    private Utente utente;

    private LocalDate data;

    @Override
    public String toString() {
        return "Prenotazione{" +
                "id=" + id +
                ", postazione=" + postazione +
                ", utente=" + utente +
                ", data=" + data +
                '}';
    }
}