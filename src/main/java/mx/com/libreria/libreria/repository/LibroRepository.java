package mx.com.libreria.libreria.repository;

import mx.com.libreria.libreria.modelo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {
}
