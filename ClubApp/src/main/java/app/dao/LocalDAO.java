package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import app.model.Local;


public class LocalDAO extends BaseDAO {

    public Collection<Local> buscarPorNombre(String nombre) throws DAOExcepcion {
        String query = "SELECT id,direccion,descripcion,estado,maps,telefono FROM clubapp.local where direccion like ?";
        Collection<Local> lista = new ArrayList<Local>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConexionBD.obtenerConexion();
            stmt = con.prepareStatement(query);
            stmt.setString(1, "%" + nombre + "%");
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
