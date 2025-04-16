package mx.com.libreria.libreria.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Editorial implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El nombre no puede estar vacio")
    private String nombre;
    @NotEmpty(message = "El pais no puede estar vacio")
    private String pais;

    @OneToMany(mappedBy = "editorial", fetch = FetchType.LAZY)
    @JsonManagedReference//evitar ciclos infintos
    private List<Libro> libros;
}
