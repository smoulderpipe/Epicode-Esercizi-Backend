package it.epicode.gestioneEventi.repositories;
import it.epicode.gestioneEventi.models.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepo extends JpaRepository<Evento, Integer> {
}
