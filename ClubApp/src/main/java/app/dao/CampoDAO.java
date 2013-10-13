
package app.dao;

import app.model.Campo;
import app.model.Local;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import org.omg.PortableInterceptor.LOCATION_FORWARD;
import sun.util.logging.resources.logging;

public class CampoDAO extends BaseDAO{
    
        public Collection<Campo> listar() throws DAOExcepcion {
        Collection<Campo> lista = new ArrayList<Campo>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionBD.obtenerConexion();
            String query = "SELECT * from campo order by descripcion;";
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Campo campo = new Campo();
                campo.setId(rs.getLong("id"));
                campo.setDescripcion(rs.getString("descripcion"));
                campo.setEstado(rs.getInt("estado"));
                campo.setTipo(rs.getInt("tipo"));
                campo.setCosto_hora(rs.getLong("costo_hora"));
                lista.add(campo);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DAOExcepcion(e.getMessage());
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return lista;
    }
        
    public Campo obtener(int idCampo) throws DAOExcepcion {
        Campo campo = new Campo();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String query = "SELECT cam.id,cam.descripcion,cam.estado,cam.tipo,cam.costo_hora,"
                    + "lo.descripcion FROM campo cam inner join local lo on (cam.id=lo.id) where cam.id=?;";
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setInt(1, idCampo);
            rs = stmt.executeQuery();
            if (rs.next()) {
                campo.setId(rs.getLong(1));
                campo.setDescripcion(rs.getString(2));
                campo.setEstado(rs.getInt(3));
                campo.setTipo(rs.getInt(4));
                campo.setCosto_hora(rs.getLong(5));
                
                Local local = new Local();
                local.setDireccion(rs.getString(6));
                campo.setLocal(local);
                
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DAOExcepcion(e.getMessage());
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return campo;
    }

    public void eliminar(int idLocal) throws DAOExcepcion {
        String query = "delete from campo WHERE id=?;";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setInt(1, idLocal);
            int i = stmt.executeUpdate();
            if (i != 1) {
                throw new SQLException("No se pudo eliminar");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DAOExcepcion(e.getMessage());
        } finally {
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
    }

    public Campo actualizar(Campo campo) throws DAOExcepcion {
        String query = "update campo set descripcion=?,estado=?,tipo=?,costo_hora=?,id_local=? where id=?;";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, campo.getDescripcion());
            stmt.setInt(2, campo.getEstado());
            stmt.setInt(3, campo.getTipo());
            stmt.setLong(4, campo.getCosto_hora());
            stmt.setLong(5, campo.getLocal().getId());
            stmt.setLong(6, campo.getId());
            int i = stmt.executeUpdate();
            if (i != 1) {
                throw new SQLException("No se pudo actualizar");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DAOExcepcion(e.getMessage());
        } finally {
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return campo;
    }

    public Collection<Campo> buscarPorNombre(String descripcion)
            throws DAOExcepcion {
        String query = "SELECT cam.id,cam.descripcion,cam.estado,cam.tipo,cam.costo_hora,lo.descripcion FROM campo cam" +
                        "inner join local lo on (cam.id=lo.id) where cam.descripcion like= ?";
        Collection<Campo> lista = new ArrayList<Campo>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, "%" + descripcion + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Campo campo = new Campo();
                campo.setId(rs.getLong("id"));
                campo.setDescripcion(rs.getString("descripcion"));
                campo.setEstado(rs.getInt("estado"));
                campo.setTipo(rs.getInt("tipo"));
                campo.setCosto_hora(rs.getLong("costo_hora"));
                
                Local local = new Local();
                local.setDireccion(rs.getString("lo.descripcion"));
                campo.setLocal(local);
                
                lista.add(campo);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DAOExcepcion(e.getMessage());
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        System.out.println(lista.size());
        return lista;
    }
}
