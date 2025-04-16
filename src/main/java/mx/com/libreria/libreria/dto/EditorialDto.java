package mx.com.libreria.libreria.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class EditorialDto {
    @NotEmpty(message = "El nombre de la editorial no puede estar vacio")
    private String nombre;
    @NotEmpty(message = "El pais no puede estar vacio")
    private String pais;
}
