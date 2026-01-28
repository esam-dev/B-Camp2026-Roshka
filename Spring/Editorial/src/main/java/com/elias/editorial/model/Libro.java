package com.elias.editorial.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "libro", schema = "prestamo_libro_ej4")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_libro", nullable = false)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "titulo", nullable = false, length = 50)
    private String titulo;

    @Size(max = 100)
    @Column(name = "editorial", length = 100)
    private String editorial;

    @Size(max = 100)
    @Column(name = "editoriol", length = 100)
    private String editoriol;


}