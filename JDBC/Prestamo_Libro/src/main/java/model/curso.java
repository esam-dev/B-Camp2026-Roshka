package model;

public class Curso {

    private int codCurso;
    private String nomCurso;
    private String aula;


    public int getCodCurso(){
        return codCurso;
    }

    public void setCodCurso(int codCurso){
        this.codCurso = codCurso;
    }

    public String getNomCurso(){
        return nomCurso;
    }

    public void setNomCurso(String nomCurso){
        this.nomCurso = nomCurso;
    }

    public String getAula (){
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }
}
