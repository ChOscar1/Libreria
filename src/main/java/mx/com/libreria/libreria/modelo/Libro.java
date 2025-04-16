package mx.com.libreria.libreria.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


import java.io.Serializable;

@Entity
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "editorial_id")
    @JsonBackReference //evitar ciclos infinos
    private Editorial editorial;

    @Transient
    @JsonProperty("editorialId")
    public Long getEditorialId() {
        return editorial != null ? editorial.getId() : null;
    }
}
