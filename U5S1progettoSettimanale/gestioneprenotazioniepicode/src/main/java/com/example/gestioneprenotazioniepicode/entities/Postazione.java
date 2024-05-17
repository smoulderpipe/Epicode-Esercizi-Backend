package com.example.gestioneprenotazioniepicode.entities;
import com.example.gestioneprenotazioniepicode.enums.Tipo;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Postazione {
    @Id
    @GeneratedValue
    private UUID id;

    private String descrizione;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @Column(name = "numero_max_occupanti")
    private int numeroMaxOccupanti;

    @ManyToOne
    @JoinColumn(name = "edificio_id")
    private Edificio edificio;

    @OneToMany(mappedBy = "postazione")
    private List<Prenotazione> prenotazioni;

    @Override
    public String toString() {
        return "Postazione{" +
                "id=" + id +
                ", descrizione='" + descrizione + '\'' +
                ", tipo=" + tipo +
                ", numeroMaxOccupanti=" + numeroMaxOccupanti +
                ", edificio=" + edificio +
                '}';
    }
}
