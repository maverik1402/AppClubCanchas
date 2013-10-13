package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import app.model.Local;

public class LocalDAO extends BaseDAO {

    
        public Collection<Local> listar() throws DAOExcepcion {
        Collection<Local> lista = new ArrayList<Local>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionBD.obtenerConexion();
            String query = "SELECT * from local order by descripcion;";
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Local local = new Local();
                local.setId(rs.getLong("id"));
                local.setDireccion(rs.getString("direccion"));
                local.setDescripcion(rs.getString("descripcion"));
                local.setEstado(rs.getInt("estado"));
                local.setMaps(rs.getString("maps"));
                local.setTelefono(rs.getString("telefono"));
                lista.add(local);
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
        
    public Local obtener(Long idLocal) throws DAOExcepcion {
        Local local = new Local();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String query = "SELECT id,direccion,descripcion,estado,maps,telefono FROM local where id=?;";
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setLong(1, idLocal);
            rs = stmt.executeQuery();
            if (rs.next()) {
                local.setId(rs.getLong(1));
                local.setDireccion(rs.getString(2));
                local.setDescripcion(rs.getString(3));
                local.setEstado(rs.getInt(4));
                local.setMaps(rs.getString(5));
                local.setTelefono(rs.getString(6));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new DAOExcepcion(e.getMessage());
        } finally {
            this.cerrarResultSet(rs);
            this.cerrarStatement(stmt);
            this.cerrarConexion(con);
        }
        return local;
    }

    public void eliminar(Local local) throws DAOExcepcion {
        String query = "delete from local WHERE id=?;";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setLong(1, local.getId());
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

    public Local actualizar(Local local) throws DAOExcepcion {
        String query = "update local set direccion=?,descripcion=?,estado=?,maps=?,telefono=? where id=?;";
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, local.getDireccion());
            stmt.setString(2, local.getDescripcion());
            stmt.setInt(3, local.getEstado());
            stmt.setString(4, local.getMaps());
            stmt.setString(5, local.getTelefono());
            stmt.setLong(5, local.getId());
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
        return local;
    }

    public Collection<Local> buscarPorNombre(String descripcion)
            throws DAOExcepcion {
        String query = "select id, nombre, descripcion from categoria where descripcion like ?";
        Collection<Local> lista = new ArrayList<Local>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, "%" + descripcion + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Local local = new Local();
                local.setId(rs.getLong("id"));
                local.setDireccion(rs.getString("direccion"));
                local.setDescripcion(rs.getString("descripcion"));
                local.setEstado(rs.getInt("descripcion"));
                local.setMaps(rs.getString("maps"));
                local.setTelefono(rs.getString("telefono"));
                lista.add(local);
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
