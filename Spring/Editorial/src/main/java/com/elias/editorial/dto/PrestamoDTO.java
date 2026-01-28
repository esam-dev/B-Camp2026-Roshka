package com.elias.editorial.dto;

import java.time.LocalDate;

public class PrestamoDTO {
    private LocalDate  fechaPrestamo;
    private Long profesorId;
    private Long libroId;
    private Long cursoId;
    private Long asignaturaId;

    // GETTERS Y SETTERS
    public LocalDate getFechaPrestamo() {
        return  fechaPrestamo;
    }

    public void setFechaPrestamo (LocalDate fechaPrestamo){
        this.fechaPrestamo = fechaPrestamo;
    }

    public Long getProfesorId (){
        return profesorId;
    }

    public void setProfesorId(Long profesorId){
        this.profesorId = profesorId;
    }

    public Long getLibroId(){
        return libroId;
    }

    public void setLibroId(Long libroId) {
        this.libroId = libroId;
    }

    public Long getCursoId (){
        return cursoId;
    }

    public void setCursoId(Long cursoId){
        this.cursoId = cursoId;
    }

    public Long getAsignaturaId(){
        return asignaturaId;
    }

    public void setAsignaturaId(Long asignaturaId){
        this.asignaturaId = asignaturaId;
    }

}
