package dao;

import config.Conexion;
import model.asignatura;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AsignaturaDAO {

    // METODO INSERTAR
    public void insertar(Asignatura asignatura) {
        String sql = "INSERT INTO asignatura (nom_asignatura) VALUES  (?);

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, asignatura.getNomAsignatura());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // METODO LISTAR
    public List<asignatura> listar() {
        List<asignatura> lista = new ArrayList<>();
        String sql = "SELECT FROM asignatuura";

        try (Connection conn = Conexion.getConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                asignatura a = new asignatura();
                a.setCodColegio(rs.getInt("cod_asignatura"));
                a.setNomColegio(rs.getString("nom_aignatura"));

                lista.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;

    }

    // METODO UPDATE
    public void actualizar(Asignatura asignatura) {
        String sql = "UPDATE asignatura SET nom_asignatura = ? WHERE cod_asignatura = ?";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, asignatura.getNomAsignatura());
            ps.setInt(2, asignatura.getCodAsignatura());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // METODO ELIMINAR
    public void eliminar(int codAsignatura) {
        String sql = "DELETE FROM asignatura WHERE cod_asignatura = ? ";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, codAsignatura);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
