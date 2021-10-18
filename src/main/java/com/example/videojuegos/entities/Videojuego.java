package com.example.videojuegos.entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "videojuego")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Videojuego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El título no debe estar vacio.")
    private String titulo;

    private String imagen;

    @Min(value = 5, message = "El precio mínimo debe ser de 5.")
    @Max(value = 10000, message = "El precio debe ser menor a 10000.")
    private float precio;

    @Size(min = 5, max = 100, message = "La descripcion debe tener entre 5 y 100 caracteres.")
    private String descripcion;

    @Min(value = 1, message = "El stock debe tener un mínimo de 1.")
    @Max(value = 10000, message = "El stock debe ser menor a 10000.")
    private int stock;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "La fecha no puede ser nula.")
    @PastOrPresent(message = "La fecha debe ser igual o anterior a la fecha de hoy.")
    private Date fechaLanzamiento;

    private boolean activo = true;

    @NotNull(message = "Es requerido el estudio.")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_estudio", nullable = false)
    private Estudio estudio;

    @NotNull(message = "Es requerida la categoria.")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_categoria", nullable = false)
    private Categoria categoria;

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
