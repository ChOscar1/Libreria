package mx.com.libreria.libreria.service;

import mx.com.libreria.libreria.dto.EditorialDto;
import mx.com.libreria.libreria.dto.LibroDto;
import mx.com.libreria.libreria.modelo.Editorial;
import mx.com.libreria.libreria.modelo.Libro;
import mx.com.libreria.libreria.repository.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EditorialService {
    @Autowired
    private EditorialRepository editorialRepository;

    public List<Editorial> listarEditoriales() {
        return editorialRepository.findAll();
    }

    @Transactional
    public Editorial guardarEditorial(EditorialDto editorialDto) {
        Editorial editorial = new Editorial();
        editorial.setNombre(editorialDto.getNombre());
        editorial.setPais(editorialDto.getPais());
        return editorialRepository.save(editorial);
    }

    public boolean eliminarEditorial(Long id) {
        if(editorialRepository.existsById(id)) {
            editorialRepository.deleteById(id);
            return true;
        }
        return false;
    }
    @Transactional
    public Optional<Editorial> actualizarEditorial(Long id, EditorialDto dto) {
        return editorialRepository.findById(id).map(editorial -> {
            editorial.setNombre(dto.getNombre());
            editorial.setPais(dto.getPais());
            return editorialRepository.save(editorial);
        });
    }
}
