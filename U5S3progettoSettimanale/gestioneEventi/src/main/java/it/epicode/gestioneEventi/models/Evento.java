package it.epicode.gestioneEventi.models;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "eventi")
public class Evento {
    @Id
    @GeneratedValue
    private int id;
    private String titolo;
    private String descrizione;
    private LocalDate data;
    private String luogo;
    @Column(name = "numero_posti_disponibili")
    private int numeroPostiDisponibili;

    @OneToMany(mappedBy = "evento")
    private List<Prenotazione> prenotazioni;


}
