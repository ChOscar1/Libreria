package mx.com.libreria.libreria.service;

import mx.com.libreria.libreria.dto.LibroDto;
import mx.com.libreria.libreria.modelo.Libro;
import mx.com.libreria.libreria.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {
    @Autowired
    private LibroRepository libroRepository;

    public List<Libro> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }

    public Optional<Libro> actualizarLibro(Long id, LibroDto dto) {
        return libroRepository.findById(id).map(libro -> {
            libro.setTitulo(dto.getTitulo());
            libro.setAutor(dto.getAutor());
            libro.setAnioPublicacion(dto.getAnioPublicacion());
            return libroRepository.save(libro);
        });
    }

    public Libro guardarLibro(LibroDto dto) {
        Libro libro = new Libro();
        libro.setTitulo(dto.getTitulo());
        libro.setAutor(dto.getAutor());
        libro.setAnioPublicacion(dto.getAnioPublicacion());
        return libroRepository.save(libro);
    }

    public boolean eliminarLibro(Long id) {
        if (libroRepository.existsById(id)) {
            libroRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
