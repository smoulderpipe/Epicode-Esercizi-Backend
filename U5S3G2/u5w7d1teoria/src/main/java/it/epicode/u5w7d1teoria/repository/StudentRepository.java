package it.epicode.u5w7d1teoria.repository;

import it.epicode.u5w7d1teoria.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
