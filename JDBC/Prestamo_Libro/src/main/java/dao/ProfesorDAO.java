package dao;

import config.Conexion;
import model.profesor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfesorDAO {
    public void insertar(Profesor profesor) {
        String sql = "INSERT INTO profesor (nom_profesor , cod_colegio) VALUES ( ? ,? )";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, profesor.getNomProfesor);
            ps.setInt(2, profesor.getCodColegio);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}



