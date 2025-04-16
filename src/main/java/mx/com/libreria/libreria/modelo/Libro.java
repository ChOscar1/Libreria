package mx.com.libreria.libreria.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


import java.io.Serializable;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Libro implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty(message = "El titulo no puede estar vacio")
    private String titulo;
    @NotEmpty(message = "El autor no puede estar vacio, en caso de ser desconocido indicarlo")
    private String autor;
    @NotEmpty(message = "El año no puede estar vacio")
    private String anioPublicacion;
}
