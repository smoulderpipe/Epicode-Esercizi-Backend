package it.epicode.gestionedispositivi.repositories;
import it.epicode.gestionedispositivi.models.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DispositivoRepo extends JpaRepository<Dispositivo, UUID> {
}
