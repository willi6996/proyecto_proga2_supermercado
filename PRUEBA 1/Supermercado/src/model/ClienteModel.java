package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import supermercado.Conexion;

public class ClienteModel extends PersonaModel {

    private String direccion;

    public ClienteModel() {
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public String insertar() {
        Connection cx = Conexion.conectar();
        if (cx == null) {
            return Conexion.SIN_CONEXION;
        }
        String sql = "insert into cliente (nombre, direccion, telefono) values (?, ?, ?)";
        try (PreparedStatement ps = cx.prepareStatement(sql)) {
            ps.setString(1, getNombre());
            ps.setString(2, direccion);
            ps.setString(3, getTelefono());
            ps.executeUpdate();
            return "Guardado exitosamente\n" + mostrarInformacion();
        } catch (SQLException ex) {
            return Conexion.mensaje(ex);
        } finally {
            Conexion.desconectar(cx);
        }
    }

    public ArrayList<ClienteModel> listar() {
        ArrayList<ClienteModel> lista = new ArrayList<>();
        Connection cx = Conexion.conectar();
        if (cx == null) {
            return lista;
        }
        String sql = "select id_cliente, nombre, direccion, telefono from cliente order by id_cliente";
        try (PreparedStatement ps = cx.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ClienteModel m = new ClienteModel();
                m.setId(rs.getInt("id_cliente"));
                m.setNombre(rs.getString("nombre"));
                m.setDireccion(rs.getString("direccion"));
                m.setTelefono(rs.getString("telefono"));
                lista.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.desconectar(cx);
        }
        return lista;
    }

    public String modificar() {
        Connection cx = Conexion.conectar();
        if (cx == null) {
            return Conexion.SIN_CONEXION;
        }
        String sql = "update cliente set nombre = ?, direccion = ?, telefono = ? where id_cliente = ?";
        try (PreparedStatement ps = cx.prepareStatement(sql)) {
            ps.setString(1, getNombre());
            ps.setString(2, direccion);
            ps.setString(3, getTelefono());
            ps.setInt(4, getId());
            if (ps.executeUpdate() == 0) return "El registro no existe";
            return "Modificado exitosamente\n" + mostrarInformacion();
        } catch (SQLException ex) {
            return Conexion.mensaje(ex);
        } finally {
            Conexion.desconectar(cx);
        }
    }

    public String eliminar() {
        Connection cx = Conexion.conectar();
        if (cx == null) {
            return Conexion.SIN_CONEXION;
        }
        try {
            try (PreparedStatement ps = cx.prepareStatement("select count(*) from vehiculo where id_cliente = ?")) {
                ps.setInt(1, getId());
                try (ResultSet rs = ps.executeQuery()) {
                    rs.next();
                    if (rs.getInt(1) > 0) {
                        return "No se puede eliminar: el cliente tiene pedidos asociados";
                    }
                }
            }
            try (PreparedStatement ps = cx.prepareStatement("delete from cliente where id_cliente = ?")) {
                ps.setInt(1, getId());
                if (ps.executeUpdate() == 0) return "El registro no existe";
                return "Eliminado exitosamente";
            }
        } catch (SQLException ex) {
            return Conexion.mensaje(ex);
        } finally {
            Conexion.desconectar(cx);
        }
    }

    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() + " | Direccion: " + direccion;
    }

}
