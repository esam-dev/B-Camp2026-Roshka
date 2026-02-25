package com.elias.editorial.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "curso" )
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_curso", nullable = false)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "nom_curso", nullable = false, length = 50)
    private String nomCurso;

    @Size(max = 20)
    @Column(name = "aula", length = 20)
    private String aula;


}