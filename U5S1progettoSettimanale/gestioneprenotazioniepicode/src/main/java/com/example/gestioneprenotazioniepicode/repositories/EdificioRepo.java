package com.example.gestioneprenotazioniepicode.repositories;
import com.example.gestioneprenotazioniepicode.entities.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface EdificioRepo extends JpaRepository<Edificio, UUID> {
}
