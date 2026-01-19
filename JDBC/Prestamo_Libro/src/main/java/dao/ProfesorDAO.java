package dao;

import config.Conexion;
import model.Profesor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfesorDAO {
    // METODO INSERTAR
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

    // METODO LISTAR
    public List<Profesor> Listar (){
        List<Profesor> lista = new ArrayList<>();

        String sql = "SELECT * FROM profesor ";

        try(Connection conn = Conexion.getConexion();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql)){

            while (rs.next()){
                p.setCodProfesor(rs.getInt("cod_profesor"));
                p.setNomProfesror(rs.getString("nom_profesor"));
                p.setCodColegio(rs.getInt("cod_colegio"));

                lista.add(p);
            }

        }catch (SQLException e  ){
            e.printStackTrace();
        }

        return lista;

    }

    // METODO  ACTUALIZAR
    public void actualizar (Profesor profesor){
        String sql = "UPDATE profesor SET nom_profesor = ? , cod_colegio = ? WHERE  cod_colegio = ?";

        try(Connection conn = Conexion.getConexion();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1,profesor.getNomProfesor());
            ps.setInt(2,profesor.getCodColegio());
            ps.setInt(3,profesor.CodColegio());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // METODO ELIMINAR
    public void eliminar (int codProfesor){
        String sql = "DELETE FROM profesor WHERE cod_profesor";

        try(Connection conn = Conexion.getConexion();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps .setInt(1,codProfesor);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}



