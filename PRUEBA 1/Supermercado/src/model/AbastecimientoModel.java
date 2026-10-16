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

public class AbastecimientoModel {

    private int idAbastecimiento;
    private int idRepuesto;
    private int cantidad;
    private LocalDate fecha;
    private Conexion conexion;

    public AbastecimientoModel() {
    }

    public int getIdAbastecimiento() {
        return idAbastecimiento;
    }

    public void setIdAbastecimiento(int idAbastecimiento) {
        this.idAbastecimiento = idAbastecimiento;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
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
            try (PreparedStatement ps = cx.prepareStatement(
                    "insert into abastecimiento (id_repuesto, cantidad, fecha) values (?, ?, sysdate)")) {
                ps.setInt(1, idRepuesto);
                ps.setInt(2, cantidad);
                ps.executeUpdate();
            }
            try (PreparedStatement ps = cx.prepareStatement(
                    "update repuesto set existencia = existencia + ? where id_repuesto = ?")) {
                ps.setInt(1, cantidad);
                ps.setInt(2, idRepuesto);
                ps.executeUpdate();
            }
            cx.commit();
            return "Abastecimiento registrado";
        } catch (SQLException ex) {
            Conexion.revertir(cx);
            return Conexion.mensaje(ex);
        } finally {
            Conexion.desconectar(cx);
        }
    }

    
    public ArrayList<AbastecimientoModel> listar() {
        ArrayList<AbastecimientoModel> lista = new ArrayList<>();
        Connection cx = Conexion.conectar();
        if (cx == null) {
            return lista;
        }
        String sql = "select id_abastecimiento, id_repuesto, cantidad, fecha from abastecimiento order by id_abastecimiento";
        try (PreparedStatement ps = cx.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                AbastecimientoModel m = new AbastecimientoModel();
                m.setIdAbastecimiento(rs.getInt("id_abastecimiento"));
                m.setIdRepuesto(rs.getInt("id_repuesto"));
                m.setCantidad(rs.getInt("cantidad"));
                Date f = rs.getDate("fecha");
                m.setFecha(f == null ? null : f.toLocalDate());
                lista.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AbastecimientoModel.class.getName()).log(Level.SEVERE, null, ex);
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
            int repuesto;
            int anterior;
            try (PreparedStatement ps = cx.prepareStatement("select id_repuesto, cantidad from abastecimiento where id_abastecimiento = ? for update")) {
                ps.setInt(1, idAbastecimiento);
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        Conexion.revertir(cx);
                        return "El abastecimiento no existe";
                    }
                    repuesto = rs.getInt(1);
                    anterior = rs.getInt(2);
                }
            }
            if (!ProductoModel.ajustarExistencia(cx, repuesto, cantidad - anterior)) {
                Conexion.revertir(cx);
                return "No se puede modificar: la existencia quedaria negativa";
            }
            try (PreparedStatement ps = cx.prepareStatement("update abastecimiento set cantidad = ? where id_abastecimiento = ?")) {
                ps.setInt(1, cantidad);
                ps.setInt(2, idAbastecimiento);
                ps.executeUpdate();
            }
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
            int repuesto;
            int anterior;
            try (PreparedStatement ps = cx.prepareStatement("select id_repuesto, cantidad from abastecimiento where id_abastecimiento = ? for update")) {
                ps.setInt(1, idAbastecimiento);
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        Conexion.revertir(cx);
                        return "El abastecimiento no existe";
                    }
                    repuesto = rs.getInt(1);
                    anterior = rs.getInt(2);
                }
            }
            if (!ProductoModel.ajustarExistencia(cx, repuesto, -anterior)) {
                Conexion.revertir(cx);
                return "No se puede eliminar: la existencia quedaria negativa";
            }
            try (PreparedStatement ps = cx.prepareStatement("delete from abastecimiento where id_abastecimiento = ?")) {
                ps.setInt(1, idAbastecimiento);
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
    
}
