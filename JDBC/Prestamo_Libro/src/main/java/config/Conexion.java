package config;

import java.util.sql.Connection;
import java.util.sql.DriverManager;

public class Conexion {

    private static finanl URL = "jdbc:postgresql://localhost:5432/norm_ejer_sql";
    private static final USER = "postgres" ;
    private static final PASS = "Es0703";

    public static Connection getConexion (){
        try {
            return DriverManager.getConection(URL,USER,PASS);
        }catch (Exception e ) {
            System.out.println("Error de conexion a la DB");
            e.printStackTrace();
            return null;
        }
    }
}
