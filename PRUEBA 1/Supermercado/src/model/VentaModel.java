package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import supermercado.Conexion;

public class VentaModel {

    private int idOrden;
    private int idVehiculo;
    private int idMecanico;
    private String descripcion;
    private LocalDate fechaCreacion;
    private LocalDate fechaFinal;
    private String estado;
    private double total;
    private ArrayList<ServicioVentaModel> servicios;
    private ArrayList<DetalleVentaModel> repuestos;

    public VentaModel() {
        servicios = new ArrayList<>();
        repuestos = new ArrayList<>();
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public int getIdMecanico() {
        return idMecanico;
    }

    public void setIdMecanico(int idMecanico) {
        this.idMecanico = idMecanico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public ArrayList<ServicioVentaModel> getServicios() {
        return servicios;
    }

    public void setServicios(ArrayList<ServicioVentaModel> servicios) {
        this.servicios = servicios;
    }

    public ArrayList<DetalleVentaModel> getRepuestos() {
        return repuestos;
    }

    public void setRepuestos(ArrayList<DetalleVentaModel> repuestos) {
        this.repuestos = repuestos;
    }
    
    
    public String insertar() {
        Connection cx = Conexion.conectar();
        if (cx == null) {
            return Conexion.SIN_CONEXION;
        }
        String sql = "insert into orden (id_vehiculo, id_mecanico, descripcion, fecha_creacion, estado, total) "
                + "values (?, ?, ?, sysdate, 'PENDIENTE', 0)";
        try (PreparedStatement ps = cx.prepareStatement(sql)) {
            ps.setInt(1, idVehiculo);
            ps.setInt(2, idMecanico);
            ps.setString(3, descripcion);
            ps.executeUpdate();
            return "Venta creada en estado PENDIENTE";
        } catch (SQLException ex) {
            return Conexion.mensaje(ex);
        } finally {
            Conexion.desconectar(cx);
        }
    }

    public ArrayList<VentaModel> listar() {
        ArrayList<VentaModel> lista = new ArrayList<>();
        Connection cx = Conexion.conectar();
        if (cx == null) {
            return lista;
        }
        String sql = "select id_orden, id_vehiculo, id_mecanico, descripcion, fecha_creacion, fecha_final, estado, total "
                + "from orden order by id_orden";
        try (PreparedStatement ps = cx.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                VentaModel m = new VentaModel();
                m.setIdOrden(rs.getInt("id_orden"));
                m.setIdVehiculo(rs.getInt("id_vehiculo"));
                m.setIdMecanico(rs.getInt("id_mecanico"));
                m.setDescripcion(rs.getString("descripcion"));
                Date creacion = rs.getDate("fecha_creacion");
                Date fin = rs.getDate("fecha_final");
                m.setFechaCreacion(creacion == null ? null : creacion.toLocalDate());
                m.setFechaFinal(fin == null ? null : fin.toLocalDate());
                m.setEstado(rs.getString("estado"));
                m.setTotal(rs.getDouble("total"));
                lista.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VentaModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.desconectar(cx);
        }
        return lista;
    }

    public static String validarEnProceso(Connection cx, int idOrden) throws SQLException {
        try (PreparedStatement ps = cx.prepareStatement("select estado from orden where id_orden = ? for update")) {
            ps.setInt(1, idOrden);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return "La venta no existe";
                }
                if (!"EN PROCESO".equals(rs.getString(1))) {
                    return "La venta debe estar EN PROCESO";
                }
            }
        }
        return null;
    }

    public static void sumarTotal(Connection cx, int idOrden, double monto) throws SQLException {
        try (PreparedStatement ps = cx.prepareStatement("update orden set total = total + ? where id_orden = ?")) {
            ps.setDouble(1, monto);
            ps.setInt(2, idOrden);
            ps.executeUpdate();
        }
    }

    public String modificar() {
        if (descripcion == null || descripcion.isEmpty()) {
            return "La descripcion es obligatoria";
        }
        Connection cx = Conexion.conectar();
        if (cx == null) {
            return Conexion.SIN_CONEXION;
        }
        String sql = "update orden set id_vehiculo = case when estado = 'PENDIENTE' then ? else id_vehiculo end, "
                + "id_mecanico = ?, descripcion = ? where id_orden = ? and estado in ('PENDIENTE', 'EN PROCESO')";
        try (PreparedStatement ps = cx.prepareStatement(sql)) {
            ps.setInt(1, idVehiculo);
            ps.setInt(2, idMecanico);
            ps.setString(3, descripcion);
            ps.setInt(4, idOrden);
            if (ps.executeUpdate() == 0) {
                return "La venta ya no se puede modificar";
            }
            return "Modificado exitosamente";
        } catch (SQLException ex) {
            return Conexion.mensaje(ex);
        } finally {
            Conexion.desconectar(cx);
        }
    }

    public String cambiarEstado(String accion) {
        Connection cx = Conexion.conectar();
        if (cx == null) {
            return Conexion.SIN_CONEXION;
        }
        try {
            cx.setAutoCommit(false);
            String actual;
            try (PreparedStatement ps = cx.prepareStatement("select estado from orden where id_orden = ? for update")) {
                ps.setInt(1, idOrden);
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        Conexion.revertir(cx);
                        return "La venta no existe";
                    }
                    actual = rs.getString(1);
                }
            }
            String nuevo;
            String sql;
            switch (accion) {
                case "INICIAR":
                    if (!"PENDIENTE".equals(actual)) {
                        Conexion.revertir(cx);
                        return "Solo se puede iniciar una venta PENDIENTE";
                    }
                    nuevo = "EN PROCESO";
                    sql = "update orden set estado = 'EN PROCESO' where id_orden = ?";
                    break;
                case "FINALIZAR":
                    if (!"EN PROCESO".equals(actual)) {
                        Conexion.revertir(cx);
                        return "Solo se puede finalizar una venta EN PROCESO";
                    }
                    if (contarProductos(cx) == 0) {
                        Conexion.revertir(cx);
                        return "No se puede finalizar una venta sin productos";
                    }
                    nuevo = "FINALIZADA";
                    sql = "update orden set estado = 'FINALIZADA', fecha_final = sysdate where id_orden = ?";
                    break;
                case "CANCELAR":
                    if (!"PENDIENTE".equals(actual) && !"EN PROCESO".equals(actual)) {
                        Conexion.revertir(cx);
                        return "Esta venta ya no se puede cancelar";
                    }
                    devolverRepuestos(cx);
                    nuevo = "CANCELADA";
                    sql = "update orden set estado = 'CANCELADA' where id_orden = ?";
                    break;
                default:
                    Conexion.revertir(cx);
                    return "Accion no valida";
            }
            try (PreparedStatement ps = cx.prepareStatement(sql)) {
                ps.setInt(1, idOrden);
                ps.executeUpdate();
            }
            cx.commit();
            return "Modificado exitosamente\nEstado: " + nuevo;
        } catch (SQLException ex) {
            Conexion.revertir(cx);
            return Conexion.mensaje(ex);
        } finally {
            Conexion.desconectar(cx);
        }
    }

    // Solo se borran ventas pendientes sin movimientos; las demas se cancelan.
    public String eliminar() {
        Connection cx = Conexion.conectar();
        if (cx == null) return Conexion.SIN_CONEXION;
        try {
            cx.setAutoCommit(false);
            try (PreparedStatement ps = cx.prepareStatement(
                    "select estado from orden where id_orden = ? for update")) {
                ps.setInt(1, idOrden);
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        Conexion.revertir(cx);
                        return "La venta no existe";
                    }
                    if (!"PENDIENTE".equals(rs.getString(1))) {
                        Conexion.revertir(cx);
                        return "Solo se pueden eliminar ventas PENDIENTES sin movimientos";
                    }
                }
            }
            try (PreparedStatement ps = cx.prepareStatement("delete from orden where id_orden = ?")) {
                ps.setInt(1, idOrden);
                ps.executeUpdate();
            }
            cx.commit();
            return "Eliminado exitosamente";
        } catch (SQLException ex) {
            Conexion.revertir(cx);
            return Conexion.mensaje(ex);
        } finally {
            Conexion.desconectar(cx);
        }
    }

    private int contarProductos(Connection cx) throws SQLException {
        try (PreparedStatement ps = cx.prepareStatement("select count(*) from repuesto_utilizado where id_orden = ?")) {
            ps.setInt(1, idOrden);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }

    private void devolverRepuestos(Connection cx) throws SQLException {
        String sql = "update repuesto r set existencia = existencia + "
                + "(select sum(u.cantidad) from repuesto_utilizado u where u.id_orden = ? and u.id_repuesto = r.id_repuesto) "
                + "where r.id_repuesto in (select id_repuesto from repuesto_utilizado where id_orden = ?)";
        try (PreparedStatement ps = cx.prepareStatement(sql)) {
            ps.setInt(1, idOrden);
            ps.setInt(2, idOrden);
            ps.executeUpdate();
        }
    }

}
