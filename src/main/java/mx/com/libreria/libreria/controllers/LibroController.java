package mx.com.libreria.libreria.controllers;

import mx.com.libreria.libreria.dto.LibroDto;
import mx.com.libreria.libreria.modelo.Libro;
import mx.com.libreria.libreria.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/libros")
public class LibroController {
    @Autowired
    private LibroService libroService;

    @GetMapping //devuelve todos los libros
    public ResponseEntity<List<Libro>> getAllLibros(){
        List<Libro> libros = libroService.obtenerTodosLosLibros();
        return ResponseEntity.ok(libros);
    }

    //Post sencillo sin validaciones
    // @PostMapping("/agregar")
    //public Libro agregarLibro(@RequestBody Libro libro){
    //    return libroRepository.save(libro);
    //}

    //Post Con Validaciones
    @PostMapping("/agregar")
    public ResponseEntity<?> agregarLibro(@RequestBody LibroDto libroDto){ // ? nos permite devolver cualquier tipo de formato
        String anio = libroDto.getAnioPublicacion();
        if(!anio.matches("^\\d{1,4}(\\s*a\\.C)?$")){
            return ResponseEntity.badRequest().body("El año debe tener formato valido, Ej: 2023 o 350 a.C ");
        }

        Libro libroGuardado = libroService.guardarLibro(libroDto);
        return ResponseEntity.ok(libroGuardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id){
        boolean eliminado = libroService.eliminarLibro(id);
        return eliminado ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity <Libro> modificarLibro(@RequestBody LibroDto dto, @PathVariable Long id){
        return libroService.actualizarLibro(id, dto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

    }
}
