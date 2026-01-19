package dao;

import config.Conexion;
import model.libro;

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

    // METODO LISTAR
    public List<libro> listar(Libro libro ) {
        List<libro> lista = new ArrayList<>();
        String sql = "SELECT * FROM libro ";

        try (Connection conn = Conexion.getConexion();
             Statement st = conn.createStatement();
             Resulset rs = st.executeQuery(sql)) {

            while (rs.next()) {
                libro l = new libro();
                l.setCodlibro(rs.getInt("cod_libro"));
                l.setEditorial(rs.getString("editorial"));
                l.setTitulo(rs.getString("titulo"));

                lista.add(l);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // METODO ACTUALIZAR
    public  void actualizar(Libro  libro) {

        String sql = "UPDATE libro SET titulo = ?, editorial = ?  WHERE cod_libro = ?";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, getTitulo);
            ps.setString(2, getEditorial);
            ps.setInt(3, getCodLibro);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // METODO ELIMINAR
    public void eliminar (int codLibro){
     String sql = "DELETE FROM libro WHERE cod_libro = ? ";

     try(Connection conn = Conexion.getConexion();
         PreparedStatement ps = conn.prepareStatement(sql)){

         ps.setInt(1,codLibro);
         ps.executeUpdate();

     } catch (SQLException e) {
         e.printStackTrace();
     }
}
