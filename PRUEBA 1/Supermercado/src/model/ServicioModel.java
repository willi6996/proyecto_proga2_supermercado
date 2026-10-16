package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import supermercado.Conexion;

public class ServicioModel {

    private int idServicio;
    private String descripcion;
    private double costoManoObra;

    public ServicioModel() {
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCostoManoObra() {
        return costoManoObra;
    }

    public void setCostoManoObra(double costoManoObra) {
        this.costoManoObra = costoManoObra;
    }
    
    public String insertar() {
        if (costoManoObra < 0) {
            return "El costo no puede ser negativo";
        }
        Connection cx = Conexion.conectar();
        if (cx == null) {
            return Conexion.SIN_CONEXION;
        }
        String sql = "insert into servicio (descripcion, costo_mano_obra) values (?, ?)";
        try (PreparedStatement ps = cx.prepareStatement(sql)) {
            ps.setString(1, descripcion);
            ps.setDouble(2, costoManoObra);
            ps.executeUpdate();
            return "Guardado exitosamente";
        } catch (SQLException ex) {
            return Conexion.mensaje(ex);
        } finally {
            Conexion.desconectar(cx);
        }
    }

    public ArrayList<ServicioModel> listar() {
        ArrayList<ServicioModel> lista = new ArrayList<>();
        Connection cx = Conexion.conectar();
        if (cx == null) {
            return lista;
        }
        String sql = "select id_servicio, descripcion, costo_mano_obra from servicio order by id_servicio";
        try (PreparedStatement ps = cx.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ServicioModel m = new ServicioModel();
                m.setIdServicio(rs.getInt("id_servicio"));
                m.setDescripcion(rs.getString("descripcion"));
                m.setCostoManoObra(rs.getDouble("costo_mano_obra"));
                lista.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicioModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.desconectar(cx);
        }
        return lista;
    }

    public String modificar() {
        if (costoManoObra < 0) {
            return "El costo no puede ser negativo";
        }
        Connection cx = Conexion.conectar();
        if (cx == null) {
            return Conexion.SIN_CONEXION;
        }
        String sql = "update servicio set descripcion = ?, costo_mano_obra = ? where id_servicio = ?";
        try (PreparedStatement ps = cx.prepareStatement(sql)) {
            ps.setString(1, descripcion);
            ps.setDouble(2, costoManoObra);
            ps.setInt(3, idServicio);
            if (ps.executeUpdate() == 0) return "El registro no existe";
            return "Modificado exitosamente";
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
        String sql = "delete from servicio where id_servicio = ?";
        try (PreparedStatement ps = cx.prepareStatement(sql)) {
            ps.setInt(1, idServicio);
            if (ps.executeUpdate() == 0) return "El registro no existe";
            return "Eliminado exitosamente";
        } catch (SQLException ex) {
            return Conexion.mensaje(ex);
        } finally {
            Conexion.desconectar(cx);
        }
    }

}
