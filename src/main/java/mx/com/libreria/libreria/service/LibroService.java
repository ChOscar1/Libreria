package mx.com.libreria.libreria.service;

import mx.com.libreria.libreria.dto.LibroDto;
import mx.com.libreria.libreria.modelo.Editorial;
import mx.com.libreria.libreria.modelo.Libro;
import mx.com.libreria.libreria.repository.EditorialRepository;
import mx.com.libreria.libreria.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private EditorialRepository editorialRepository;

    public List<Libro> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }

    @Transactional
    public Optional<Libro> actualizarLibro(Long id, LibroDto dto) {
        return libroRepository.findById(id).map(libro -> {
            libro.setTitulo(dto.getTitulo());
            libro.setAutor(dto.getAutor());
            libro.setAnioPublicacion(dto.getAnioPublicacion());

            //Actualizar editorial si se proporciona un ID
            if(dto.getIdEditorial() != null) {
                Editorial editorial = editorialRepository.findById(dto.getIdEditorial()).orElse(null);
                libro.setEditorial(editorial);
            }else{
                libro.setEditorial(null);
            }
            return libroRepository.save(libro);
        });
    }

    @Transactional
    public Libro guardarLibro(LibroDto dto) {
        Libro libro = new Libro();
        libro.setTitulo(dto.getTitulo());
        libro.setAutor(dto.getAutor());
        libro.setAnioPublicacion(dto.getAnioPublicacion());

        //Asignar editorial proporcionando un ID
        if(dto.getIdEditorial() != null) {
            Editorial editorial = editorialRepository.findById(dto.getIdEditorial()).orElse(null);
            libro.setEditorial(editorial);
        }
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
