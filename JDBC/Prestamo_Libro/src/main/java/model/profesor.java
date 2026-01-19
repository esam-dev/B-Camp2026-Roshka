package model;

public class Profesor {

    private int codProfesor;
    private String nomProfesor;
    private int codColegio;

    public int getCodProfesor (){
        return codProfesor;
    }

    public void setCodProfesor(int codProfesor) {
        this.codProfesor = codProfesor;
    }

    public String  getNomProfesor(){
        return nomProfesor;
    }

    public void setNomProfesor(String nomProfesor){
        this.nomProfesor = nomProfesor;
    }

    public int getCodColegio (){
        return codColegio;
    }

    public void setCodColegio(int codColegio) {
        this.codColegio = codColegio;
    }
}
