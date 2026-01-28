package com.elias.editorial.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "profesor", schema = "prestamo_libro_ej4")
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_profesor", nullable = false)
    private Long id;

    @Size(max = 150)
    @NotNull
    @Column(name = "nom_profesor", nullable = false, length = 150)
    private String nomProfesor;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cod_colegio", nullable = false)
    private Colegio codColegio;


}