package it.epicode.esercizio1.repositories;
import it.epicode.esercizio1.entities.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PizzaRepository extends JpaRepository<Pizza, UUID> {
}
