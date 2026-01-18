package dao;
import config.Conexion;
import model.colegio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ColegioDAO {

    // INSERT
    public void insertar(ColegioDAO colegio) {
        String sql = "INSERT INTO colegio (nom_colegio) VALUES (?) ";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, colegio.getNomColegio());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // LISTAR
    public List<colegio> Listar() {
        List<colegio> lista = new ArrayList<>();
        String sql = "SELECT * FROM colegio";

        try (Connection conn = Conexion.getConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                colegio c = new colegio();
                c.setCodColegio(rs.getInt("cod_colegio"));
                c.setNomColegio(rs.getString("nom_colegio"));

                lista.add(c);
            }
        } catch (Exception e ){
            e.printStackTrace();
        }
        return lista;
    }

    // UPDATE
    public void actualizar(){
        String sql = "UPDATE colegio SET nom_colegio = ?  WHERE  cod_colegio = ?";

        try (Connection conn = Conexion.getConexion());
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, colegiogetNomColegio());
            ps.setInt(2, colegiogetCodColegio());
            ps.executeUpdate();

        } catch(SQLException e ) {
           e.printStackTrace();
        }

    }

    // DELETE
    public void eliminar (int codColegio){
        String sql = "DELETE FROM colegio WHERE cod_colegio = ?";

        try(Connection conn = Conexion.getConexion();
        PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1,codColegio);
            ps.executeUpdate();

        }catch (SQLException e ){
            ps.printStackTrace();
        }
    }
}