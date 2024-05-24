package it.epicode.gestionedispositivi.models;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "dipendenti")
public class Dipendente {
    @Id
    @GeneratedValue
    private UUID id;
    private String username;
    private String nome;
    private String email;

    @OneToMany(mappedBy = "dipendente")
    private List<Dispositivo> dispositivi;

    private String avatar;

    @Override
    public String toString() {
        return "Dipendente{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
