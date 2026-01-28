package com.elias.editorial.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "asignatura", schema = "prestamo_libro_ej4")
public class Asignatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_asignatura", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "nom_asignatura", nullable = false)
    private String nomAsignatura;


}