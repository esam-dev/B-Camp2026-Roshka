package com.elias.editorial.dto;

public class LibroDTO {
    private Long codLibro;
    private String titulo;
    private String editorial;

    // GETTERS Y SETTERS
    public Long getCodLibro(){
        return codLibro;
    }
    public void setCodLibro(Long codLibro){
        this.codLibro = codLibro;
    }
    public String getTitulo (){
        return titulo;
    }
    public void setTitulo (String titulo){
        this.titulo = titulo;
    }
    public String getEditorial(){
        return editorial;
    }
    public void setEditorial(String editorial){
        this.editorial = editorial;
    }


}
