package model;

public class Libro {

    private int codlibro;
    private String titulo;
    private String editorial;


    public int getCodlibro(){
        return codlibro;
    }

    public void setCodlibro(int codlibro){
        this.codlibro = codlibro;
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
