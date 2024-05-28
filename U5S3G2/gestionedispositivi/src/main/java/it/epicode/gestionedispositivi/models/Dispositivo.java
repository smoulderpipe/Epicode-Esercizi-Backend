package it.epicode.gestionedispositivi.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import it.epicode.gestionedispositivi.enums.StatoDispositivo;
import it.epicode.gestionedispositivi.enums.TipoDispositivo;
import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Entity
@Data
@Table(name = "dispositivi")
public class Dispositivo {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "dipendente_id")
    @JsonIgnore
    private Dipendente dipendente;

    private TipoDispositivo tipoDispositivo;
    private StatoDispositivo statoDispositivo;
}
