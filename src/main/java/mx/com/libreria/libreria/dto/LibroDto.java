package mx.com.libreria.libreria.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LibroDto {
    @NotEmpty(message = "El titulo no puede estar vacio")
    private String titulo;
    @NotEmpty(message = "El autor no puede estar vacio")
    private String autor;
    @NotEmpty(message = "El año no puede estar vacio")
    private String anioPublicacion;

    private Long idEditorial;

}
