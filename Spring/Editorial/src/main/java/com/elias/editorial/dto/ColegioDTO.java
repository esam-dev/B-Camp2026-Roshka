package com.elias.editorial.dto;


public class ColegioDTO {
    private Long codColegio;
    private String  nomColegio;

    // GETTERS Y SETTERS
    public Long getCodColegio (){
        return codColegio;
    }
    public void setCodColegio (Long codColegio){
        this.codColegio = codColegio;
    }
    public String getNomColegio(){
        return nomColegio;
    }
    public void setNomColegio (String nomColegio){
        this.nomColegio = nomColegio;
    }
}
