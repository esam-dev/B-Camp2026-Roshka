package com.elias.editorial.dto;

public class AsignaturaDTO {

    private Long codAsignatura;
    private String nomAsignatura;

    public Long getCodAsignatura(){
        return codAsignatura;
    }
    public void setCodAsignatura(Long codAsignatura){
        this.codAsignatura = codAsignatura;
    }
    public String getNomAsignatura (){
        return  nomAsignatura;
    }
    public void setNomAsignatura(String nomAsignatura){
        this.nomAsignatura = nomAsignatura;
    }
}
