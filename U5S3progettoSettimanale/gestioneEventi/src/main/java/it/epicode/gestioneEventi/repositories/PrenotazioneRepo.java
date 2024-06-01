package it.epicode.gestioneEventi.repositories;
import it.epicode.gestioneEventi.models.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrenotazioneRepo extends JpaRepository<Prenotazione, Integer> {
    List<Prenotazione> findByUtenteId(int userId);
}
