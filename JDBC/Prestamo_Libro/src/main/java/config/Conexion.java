
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Properties;

public class Conexion {

    // Leer variables de entorno, con fallback opcional (útil local)
    private static final String URL  = System.getenv().getOrDefault("DB_URL",
            "jdbc:postgresql://localhost:5432/norm_ejerc_sql?currentSchema=prestamo_libro_ej4");
    private static final String USER = System.getenv().getOrDefault("DB_USER", "postgres");
    private static final String PASS = System.getenv().getOrDefault("DB_PASS", "postgres");

    // Timeouts (en ms)
    private static final int LOGIN_TIMEOUT_SECONDS = 5;
    private static final int SOCKET_TIMEOUT_MS = (int) Duration.ofSeconds(10).toMillis();

    public static Connection getConexion() throws SQLException {
        DriverManager.setLoginTimeout(LOGIN_TIMEOUT_SECONDS);

        Properties props = new Properties();
        props.setProperty("user", USER);
        props.setProperty("password", PASS);
        // Opcionales:
        props.setProperty("sslmode", "disable"); // "require" si vamos  a cloud con SSL
        props.setProperty("socketTimeout", String.valueOf(SOCKET_TIMEOUT_MS)); // timeout de lectura

        Connection conn = DriverManager.getConnection(URL, props);
        return conn;
    }

    // Test
    public static void main(String[] args) {
        try (Connection c = getConexion()) {
            System.out.println("Conexión exitosa a la base de datos");
        } catch (SQLException e) {
            System.err.println("No se pudo conectar: " + e.getMessage());
        }
    }
}
