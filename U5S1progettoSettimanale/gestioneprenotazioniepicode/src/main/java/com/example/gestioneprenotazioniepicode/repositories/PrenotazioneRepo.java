package com.example.gestioneprenotazioniepicode.repositories;
import com.example.gestioneprenotazioniepicode.entities.Postazione;
import com.example.gestioneprenotazioniepicode.entities.Prenotazione;
import com.example.gestioneprenotazioniepicode.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface PrenotazioneRepo extends JpaRepository<Prenotazione, UUID> {
    boolean existsByPostazioneAndData(Postazione postazione, LocalDate data);

    boolean existsByUtenteAndData(Utente utente, LocalDate data);
}
