package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import supermercado.Conexion;

public class ServicioVentaModel {

    private int idServicioRealizado;
    private int idOrden;
    private int idServicio;
    private int cantidad;
    private double precio;
    private double subtotal;

    public ServicioVentaModel() {
    }

    public int getIdServicioRealizado() {
        return idServicioRealizado;
    }

    public void setIdServicioRealizado(int idServicioRealizado) {
        this.idServicioRealizado = idServicioRealizado;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    
    public String insertar() {
        if (cantidad <= 0) {
            return "La cantidad debe ser mayor que cero";
        }
        Connection cx = Conexion.conectar();
        if (cx == null) {
            return Conexion.SIN_CONEXION;
        }
        try {
            cx.setAutoCommit(false);
            String error = VentaModel.validarEnProceso(cx, idOrden);
            if (error != null) {
                Conexion.revertir(cx);
                return error;
            }
            double precioActual;
            try (PreparedStatement ps = cx.prepareStatement(
                    "select costo_mano_obra from servicio where id_servicio = ?")) {
                ps.setInt(1, idServicio);
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        Conexion.revertir(cx);
                        return "El servicio no existe";
                    }
                    precioActual = rs.getDouble(1);
                }
            }
            double subtotalActual = precioActual * cantidad;
            try (PreparedStatement ps = cx.prepareStatement(
                    "insert into servicio_realizado (id_orden, id_servicio, cantidad, precio, subtotal) values (?, ?, ?, ?, ?)")) {
                ps.setInt(1, idOrden);
                ps.setInt(2, idServicio);
                ps.setInt(3, cantidad);
                ps.setDouble(4, precioActual);
                ps.setDouble(5, subtotalActual);
                ps.executeUpdate();
            }
            VentaModel.sumarTotal(cx, idOrden, subtotalActual);
            cx.commit();
            return "Servicio agregado a la venta";
        } catch (SQLException ex) {
            Conexion.revertir(cx);
            return Conexion.mensaje(ex);
        } finally {
            Conexion.desconectar(cx);
        }
    }

    public ArrayList<ServicioVentaModel> listar() {
        ArrayList<ServicioVentaModel> lista = new ArrayList<>();
        Connection cx = Conexion.conectar();
        if (cx == null) {
            return lista;
        }
        String sql = "select id_servicio_realizado, id_orden, id_servicio, cantidad, precio, subtotal from servicio_realizado order by id_servicio_realizado";
        try (PreparedStatement ps = cx.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ServicioVentaModel m = new ServicioVentaModel();
                m.setIdServicioRealizado(rs.getInt("id_servicio_realizado"));
                m.setIdOrden(rs.getInt("id_orden"));
                m.setIdServicio(rs.getInt("id_servicio"));
                m.setCantidad(rs.getInt("cantidad"));
                m.setPrecio(rs.getDouble("precio"));
                m.setSubtotal(rs.getDouble("subtotal"));
                lista.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicioVentaModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.desconectar(cx);
        }
        return lista;
    }

    public String modificar() {
        if (cantidad <= 0) {
            return "La cantidad debe ser mayor que cero";
        }
        Connection cx = Conexion.conectar();
        if (cx == null) {
            return Conexion.SIN_CONEXION;
        }
        try {
            cx.setAutoCommit(false);
            int orden;
            int anterior;
            double precioGuardado;
            try (PreparedStatement ps = cx.prepareStatement("select id_orden, cantidad, precio from servicio_realizado where id_servicio_realizado = ? for update")) {
                ps.setInt(1, idServicioRealizado);
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        Conexion.revertir(cx);
                        return "El registro no existe";
                    }
                    orden = rs.getInt(1);
                    anterior = rs.getInt(2);
                    precioGuardado = rs.getDouble(3);
                }
            }
            String error = VentaModel.validarEnProceso(cx, orden);
            if (error != null) {
                Conexion.revertir(cx);
                return error;
            }
            try (PreparedStatement ps = cx.prepareStatement("update servicio_realizado set cantidad = ?, subtotal = ? where id_servicio_realizado = ?")) {
                ps.setInt(1, cantidad);
                ps.setDouble(2, precioGuardado * cantidad);
                ps.setInt(3, idServicioRealizado);
                ps.executeUpdate();
            }
            VentaModel.sumarTotal(cx, orden, precioGuardado * (cantidad - anterior));
            cx.commit();
            return "Modificado exitosamente";
        } catch (SQLException ex) {
            Conexion.revertir(cx);
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
            cx.setAutoCommit(false);
            int orden;
            double subtotalGuardado;
            try (PreparedStatement ps = cx.prepareStatement("select id_orden, subtotal from servicio_realizado where id_servicio_realizado = ? for update")) {
                ps.setInt(1, idServicioRealizado);
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        Conexion.revertir(cx);
                        return "El registro no existe";
                    }
                    orden = rs.getInt(1);
                    subtotalGuardado = rs.getDouble(2);
                }
            }
            String error = VentaModel.validarEnProceso(cx, orden);
            if (error != null) {
                Conexion.revertir(cx);
                return error;
            }
            try (PreparedStatement ps = cx.prepareStatement("delete from servicio_realizado where id_servicio_realizado = ?")) {
                ps.setInt(1, idServicioRealizado);
                ps.executeUpdate();
            }
            VentaModel.sumarTotal(cx, orden, -subtotalGuardado);
            cx.commit();
            return "Eliminado exitosamente";
        } catch (SQLException ex) {
            Conexion.revertir(cx);
            return Conexion.mensaje(ex);
        } finally {
            Conexion.desconectar(cx);
        }
    }
    
}
