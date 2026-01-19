package model;

public class Asignatura {
    private int codAsignatura;
    private String nomAsignatura;

    public int getCodColegio(){
        return codAsignatura;
    }

    public void setCodColegio(int codColegio){
        this.codAsignatura = codAsignatura;
    }

    public String getNomColegio(){
        return nomAsignatura;
    }

    public void setNomColegio(String nomColegio){
        this.nomAsignatura = nomColegio;
    }
}
