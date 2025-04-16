package mx.com.libreria.libreria.controllers;

import mx.com.libreria.libreria.dto.EditorialDto;
import mx.com.libreria.libreria.modelo.Editorial;
import mx.com.libreria.libreria.service.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/editorial")
public class EditorialController {
    @Autowired
    private EditorialService editorialService;

    @GetMapping
    public List<Editorial> obtenerEditoriales() {
        return editorialService.listarEditoriales();
    }

    @PostMapping("/agregar")
    public Editorial agregarEditorial(@RequestBody EditorialDto editorialDto) {
        return editorialService.guardarEditorial(editorialDto);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Editorial> actualizarEditorial(@PathVariable Long id, @RequestBody EditorialDto editorialDto) {
        return editorialService.actualizarEditorial(id, editorialDto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEditorial(@PathVariable Long id) {
        boolean eliminado = editorialService.eliminarEditorial(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
