package it.epicode.gestionedispositivi.repositories;
import it.epicode.gestionedispositivi.models.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DipendenteRepo extends JpaRepository<Dipendente, UUID> {
}
