package com.example.gestioneprenotazioniepicode.repositories;
import com.example.gestioneprenotazioniepicode.entities.Postazione;
import com.example.gestioneprenotazioniepicode.enums.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface PostazioneRepo extends JpaRepository<Postazione, UUID> {
    @Query("SELECT p FROM Postazione p JOIN FETCH p.edificio e WHERE p.tipo = :tipo AND e.citta = :citta")
    List<Postazione> findByTipoAndEdificio_Citta(Tipo tipo, String citta);
}
