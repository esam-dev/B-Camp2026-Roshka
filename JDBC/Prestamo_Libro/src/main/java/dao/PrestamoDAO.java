package dao;

import config.Conexion;
import model.Prestamo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PrestamoDAO {

    // METODO INSERTAR
    public void insertar (Prestamo prestamo){
        String sql = """
            INSERT INTO prestamo
            (cod_libro, cod_profesor, cod_asignatura, cod_curso, fecha_prestamo)
            VALUES (?, ?, ?, ?, ?)
        """;
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)){


            ps.setInt(2,prestamo.getCodLibro());
            ps.setInt(1,prestamo.getCodProfesor());
            ps.setInt(3,prestamo.getCodAsignatura());
            ps.setInt(4,prestamo.getCodCurso());
            ps.setDate(5,prestamo.getFechaPrestamo());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // METODO LISTAR
    public List<Prestamo> listar(){
        List<Prestamo> lista = new ArrayList<>();
        String sql = "SELECT * FROM prestamo";

        try(Connection conn = Conexion.getConexion();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql)){

            while (rs.next()){
                Prestamo p =  new Prestamo();
                p.setCodPrestamo(rs.getInt("cod_prestamo"));
                p.setCodProfesor(rs.getInt("cod_profesor"));
                p.setCodLibro(rs.getInt("cod_libro"));
                p.setCodAsignatura(rs.getInt("cod_asignatura"));
                p.setCodCurso(rs.getInt("cod_curso"));
                p.setFechaPrestamo(rs.getDate("fecha_prestamo"));

                lista.add(p);

            }

        }catch (SQLException e ) {
            e.printStackTrace();
        }
        return lista;
    }

    // METODO UPDATE
    public void actualizar (int codPrestamo) {
        String sql = """
                UPDATE prestamo 
                SET cod_profesor = ? , cod_libro = ?, 
                cod_asignatura = ?, cod_curso = ? , fecha_prestamo = ?
                WHERE  cod_prestamo = ? 
                """;

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, Prestamo.getCodProfesor());
            ps.setInt(2, Prestamo.getCodCurso());
            ps.setInt(3, Prestamo.getCodLibro());
            ps.setInt(4, Prestamo.getCodAsignatura());
            ps.setDate(5,Prestamo.getFechaPrestamo());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // METODO ELIMINAR
    public void eliminar(int codPrestamo){
        String sql= "DELETE FROM prestamo WHERE cod_prestamo = ?";

        try(Connection conn = Conexion.getConexion();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1,codPrestamo);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
