package dao;

import config.Conexion;
import model.curso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    // METODO INSERTAR
    public void insertar(Curso curso){
        String sql ="INSERT INTO curso (nom_curso , aula ) VALUES (? ,?) ";

        try (Connection conn = Conexion.getConexion();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,curso.getNomCurso());
            ps.setString(2,curso.getAula());
            ps.executeUpdate();

        } catch (SQLException e) {
           e.printStackTrace();
        }
    }
}
