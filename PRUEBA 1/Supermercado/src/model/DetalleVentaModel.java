package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import supermercado.Conexion;

public class DetalleVentaModel {

    private int idRepuestoUtilizado;
    private int idOrden;
    private int idRepuesto;
    private int cantidad;
    private double precio;
    private double subtotal;

    public DetalleVentaModel() {
    }

    public int getIdRepuestoUtilizado() {
        return idRepuestoUtilizado;
    }

    public void setIdRepuestoUtilizado(int idRepuestoUtilizado) {
        this.idRepuestoUtilizado = idRepuestoUtilizado;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public int getIdRepuesto() {
        return idRepuesto;
    }

    public void setIdRepuesto(int idRepuesto) {
        this.idRepuesto = idRepuesto;
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
                    "select precio, existencia from repuesto where id_repuesto = ? for update")) {
                ps.setInt(1, idRepuesto);
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        Conexion.revertir(cx);
                        return "El producto no existe";
                    }
                    if (cantidad > rs.getInt("existencia")) {
                        Conexion.revertir(cx);
                        return "No hay existencia suficiente";
                    }
                    precioActual = rs.getDouble("precio");
                }
            }
            double subtotalActual = precioActual * cantidad;
            try (PreparedStatement ps = cx.prepareStatement(
                    "insert into repuesto_utilizado (id_orden, id_repuesto, cantidad, precio, subtotal) values (?, ?, ?, ?, ?)")) {
                ps.setInt(1, idOrden);
                ps.setInt(2, idRepuesto);
                ps.setInt(3, cantidad);
                ps.setDouble(4, precioActual);
                ps.setDouble(5, subtotalActual);
                ps.executeUpdate();
            }
            try (PreparedStatement ps = cx.prepareStatement(
                    "update repuesto set existencia = existencia - ? where id_repuesto = ?")) {
                ps.setInt(1, cantidad);
                ps.setInt(2, idRepuesto);
                ps.executeUpdate();
            }
            VentaModel.sumarTotal(cx, idOrden, subtotalActual);
            cx.commit();
            return "Producto agregado a la venta";
        } catch (SQLException ex) {
            Conexion.revertir(cx);
            return Conexion.mensaje(ex);
        } finally {
            Conexion.desconectar(cx);
        }
    }

    public ArrayList<DetalleVentaModel> listar() {
        ArrayList<DetalleVentaModel> lista = new ArrayList<>();
        Connection cx = Conexion.conectar();
        if (cx == null) {
            return lista;
        }
        String sql = "select id_repuesto_utilizado, id_orden, id_repuesto, cantidad, precio, subtotal from repuesto_utilizado order by id_repuesto_utilizado";
        try (PreparedStatement ps = cx.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                DetalleVentaModel m = new DetalleVentaModel();
                m.setIdRepuestoUtilizado(rs.getInt("id_repuesto_utilizado"));
                m.setIdOrden(rs.getInt("id_orden"));
                m.setIdRepuesto(rs.getInt("id_repuesto"));
                m.setCantidad(rs.getInt("cantidad"));
                m.setPrecio(rs.getDouble("precio"));
                m.setSubtotal(rs.getDouble("subtotal"));
                lista.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetalleVentaModel.class.getName()).log(Level.SEVERE, null, ex);
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
            int repuesto;
            int anterior;
            double precioGuardado;
            try (PreparedStatement ps = cx.prepareStatement("select id_orden, id_repuesto, cantidad, precio from repuesto_utilizado where id_repuesto_utilizado = ? for update")) {
                ps.setInt(1, idRepuestoUtilizado);
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        Conexion.revertir(cx);
                        return "El registro no existe";
                    }
                    orden = rs.getInt(1);
                    repuesto = rs.getInt(2);
                    anterior = rs.getInt(3);
                    precioGuardado = rs.getDouble(4);
                }
            }
            String error = VentaModel.validarEnProceso(cx, orden);
            if (error != null) {
                Conexion.revertir(cx);
                return error;
            }
            if (!ProductoModel.ajustarExistencia(cx, repuesto, anterior - cantidad)) {
                Conexion.revertir(cx);
                return "No hay existencia suficiente";
            }
            try (PreparedStatement ps = cx.prepareStatement("update repuesto_utilizado set cantidad = ?, subtotal = ? where id_repuesto_utilizado = ?")) {
                ps.setInt(1, cantidad);
                ps.setDouble(2, precioGuardado * cantidad);
                ps.setInt(3, idRepuestoUtilizado);
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
            int repuesto;
            int anterior;
            double subtotalGuardado;
            try (PreparedStatement ps = cx.prepareStatement("select id_orden, id_repuesto, cantidad, subtotal from repuesto_utilizado where id_repuesto_utilizado = ? for update")) {
                ps.setInt(1, idRepuestoUtilizado);
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        Conexion.revertir(cx);
                        return "El registro no existe";
                    }
                    orden = rs.getInt(1);
                    repuesto = rs.getInt(2);
                    anterior = rs.getInt(3);
                    subtotalGuardado = rs.getDouble(4);
                }
            }
            String error = VentaModel.validarEnProceso(cx, orden);
            if (error != null) {
                Conexion.revertir(cx);
                return error;
            }
            ProductoModel.ajustarExistencia(cx, repuesto, anterior);
            try (PreparedStatement ps = cx.prepareStatement("delete from repuesto_utilizado where id_repuesto_utilizado = ?")) {
                ps.setInt(1, idRepuestoUtilizado);
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
