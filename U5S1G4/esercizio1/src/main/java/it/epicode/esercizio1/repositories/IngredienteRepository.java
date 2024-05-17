package it.epicode.esercizio1.repositories;
import it.epicode.esercizio1.entities.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface IngredienteRepository extends JpaRepository<Ingrediente, UUID> {
}
