package dao;

import config.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO
  // INSERT
    public void insertar(Libro libro) {
        String sql = "INSERT INTO prestamo_libro_ej4.libro (titulo, editorial ) VALUES (?,?)";

        try (Conexion con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getEditorial());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
