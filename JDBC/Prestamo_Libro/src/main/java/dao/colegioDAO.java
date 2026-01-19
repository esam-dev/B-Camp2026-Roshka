package dao;

import config.Conexion;
import model.Colegio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ColegioDAO {

    // INSERT
    public void insertar(Colegio colegio) {
        String sql = "INSERT INTO colegio (nom_colegio) VALUES (?)";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, colegio.getNomColegio());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // LISTAR
    public List<Colegio> listar() {
        List<Colegio> lista = new ArrayList<>();
        String sql = "SELECT * FROM colegio";

        try (Connection conn = Conexion.getConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Colegio c = new Colegio();
                c.setCodColegio(rs.getInt("cod_colegio"));
                c.setNomColegio(rs.getString("nom_colegio"));

                lista.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // UPDATE
    public void actualizar(Colegio colegio) {
        String sql = "UPDATE colegio SET nom_colegio = ? WHERE cod_colegio = ?";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, colegio.getNomColegio());
            ps.setInt(2, colegio.getCodColegio());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void eliminar(int codColegio) {
        String sql = "DELETE FROM colegio WHERE cod_colegio = ?";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, codColegio);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
