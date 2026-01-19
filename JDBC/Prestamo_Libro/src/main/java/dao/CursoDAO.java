package dao;

import config.Conexion;
import model.curso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


   public class CursoDAO {

       // METODO INSERTAR
       public void insertar(Curso curso) {
           String sql = "INSERT INTO curso (nom_curso , aula ) VALUES (? ,?) ";

           try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

               ps.setString(1, curso.getNomCurso());
               ps.setString(2, curso.getAula());
               ps.executeUpdate();

           } catch (SQLException e) {
               e.printStackTrace();
           }
       }

       // METODO LISTAR
       public List<curso> listar() {
           List<curso> lista = new ArrayList<>();
           String sql = "SELECT * FROM curso";

           try (Connection conn = Conexion.getConexion();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql))

           while (rs.next()) {
               curso c = new curso();
               c.setCodCurso(rs.getInt("cod_curso"));
               c.setNomCurso(rs.getString("nom_curso"));
               c.setAula(rs.getString("Aula"));

               lista.add(c);
           }catch(SQLException e )
           e.printStackTrace();
       }
       return lista;
   }

      // METODO UPDATE
      public void actualizar (Curso curso ){
         String  sql = "UPDATE curso SET nom_curso = ?,  aula = ? WHERE  cod_curso = ?";

             try(Connection conn = Conexion.getConexion();
                        PreparedStatement ps = conn.prepareStatement(sql)){

                        ps.setString(1,curso.getCurso);
                        ps.setString(2,curso.getAula);
                        ps.setInt(3,curso.getCodCurso);

                        ps.executeUpdate();

             }catch (SQLException e ){
                 e.printStackTrace();
               }
      }

      // METODO ELIMINAR
       public void eliminar (int codCurso){
          String sql = "DELETE FROM curso WHERE cod_curso = ?";

          try (Connection conn = Conexion.getConexion();
               PreparedStatement ps = conn.prepareStatement(sql){

                   ps.setInt(1,codCurso);
                   ps.executeUpdate();
               } catch (SQLException e) {
                   e.printStackTrace();
                    }
       }
    // METODO LISTAR
    public List<curso> listar(){
        List<curso> lista = new ArrayList<>();
        String sql ="SELECT * FROM curso";

        try( Connection conn = Conexion.getConexion();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql))

        while(rs.next()){
            curso c = new curso();
            c.setCodCurso(rs.getInt ("cod_curso"));
            c.setNomCurso(rs.getString("nom_curso"));
            c.setAula(rs.getString("Aula"));

            lista.add(c);
        }catch(SQLException e )
         e.printStackTrace();
    }
    return lista;

    // METODO UPDATE
    public void actualizar (Curso curso ){
        String  sql = "UPDATE curso SET nom_curso = ?,  aula = ? WHERE  cod_curso = ?";

        try(Connection conn = Conexion.getConexion();
        PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1,curso.getCurso);
            ps.setString(2,curso.getAula);
            ps.setInt(3,curso.getCodCurso);

            ps.executeUpdate();

        }catch (SQLException e ){
            e.printStackTrace();
        }
    }

    // METODO ELIMINAR
    public void eliminar (int codCurso){
        String sql = "DELETE FROM curso WHERE cod_curso = ?";

        try (Connection conn = Conexion.getConexion();
        PreparedStatement ps = conn.prepareStatement(sql){

            ps.setInt(1,codCurso);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
