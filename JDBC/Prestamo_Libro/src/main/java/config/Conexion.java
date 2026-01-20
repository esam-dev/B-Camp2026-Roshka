package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:postgresql://localhost:5432/norm_ejerc_sql?currentSchema=prestamo_libro_ej4";
    private static final String USER = "postgres";
    private static final String PASS = "es0703";

    // Método para obtener la conexión
    public static Connection getConexion() {
        Connection conn = null;
        try {
            // Cargar el driver de PostgreSQL
            Class.forName("org.postgresql.Driver");

            // Obtener la conexión
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            System.out.println("  Driver de PostgreSQL no encontrado. Asegurate de tener el JAR del driver en tu proyecto.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("  Error de conexión a la DB");
            e.printStackTrace();
        }
        return conn;
    }

    // Método de prueba opcional
    public static void main(String[] args) {
        Connection prueba = Conexion.getConexion();
        if(prueba != null){
            System.out.println(" Conexión exitosa a PostgreSQL");
        } else {
            System.out.println(" No se pudo conectar");
        }
    }
}
