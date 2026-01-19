package model;

import java.time.LocalDate;

public class Prestamo {
    private int codPrestamo;
    private int codProfesor;
    private int codLibro;
    private int codAsignatura;
    private int codCurso;
    private LocalDate fechaPrestamo;


    public int getCodPrestamo(){
        return codPrestamo;
    }

    public void setCodPrestamo(int codPrestamo){
        this.codPrestamo = codPrestamo;
    }

    public int getCodProfesor(){
        return codProfesor;
    }

    public void setCodProfesor(int codProfesor){
        this.codProfesor = codProfesor;
    }

    public int getCodLibro(){
        return codLibro;
    }

    public void setCodLibro(int codLibro){
        this.codLibro = codLibro;
    }

    public int getCodAsignatura(){
        return codAsignatura;
    }

    public void setCodAsignatura(int codAsignatura){
        this.codAsignatura = codAsignatura;
    }

    public int getCodCurso(){
        return codCurso;
    }

    public void setCodCurso(int codCurso){
        this.codCurso = codCurso;
    }

    public LocalDate getFechaPrestamo (){
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo){
        this.codPrestamo = codPrestamo;
    }
}
