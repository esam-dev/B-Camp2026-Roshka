package com.elias.editorial.dto;

public class ProfesorDTO {

    private String nombre;
    private Long codColegio;


    // Getters y setters
    public String  getNombre (){
        return nombre;
    }

    public void setNombre (String nombre){
        this.nombre = nombre;
    }

    public Long getCodColegio (){
        return codColegio;
    }

    public void setCodColegio(Long codColegio){
        this.codColegio = codColegio;
    }
}

