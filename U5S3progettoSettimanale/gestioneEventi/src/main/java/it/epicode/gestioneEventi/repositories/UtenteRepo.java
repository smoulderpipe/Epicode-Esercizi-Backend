package it.epicode.gestioneEventi.repositories;
import it.epicode.gestioneEventi.models.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UtenteRepo extends JpaRepository<Utente, Integer> {
    Optional<Utente> findByEmail(String email);
}
