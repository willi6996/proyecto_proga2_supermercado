package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import supermercado.Conexion;

public class PedidoModel {

    private int idVehiculo;
    private int idCliente;
    private String placa;
    private String marca;
    private String modelo;
    private int anio;
    private String color;

    public PedidoModel() {
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    public String insertar() {
        int actual = Year.now().getValue();
        if (anio < 1900 || anio > actual + 1) {
            return "El anio debe estar entre 1900 y " + (actual + 1);
        }
        Connection cx = Conexion.conectar();
        if (cx == null) {
            return Conexion.SIN_CONEXION;
        }
        String sql = "insert into vehiculo (id_cliente, placa, marca, modelo, anio, color) values (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = cx.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            ps.setString(2, placa);
            ps.setString(3, marca);
            ps.setString(4, modelo);
            ps.setInt(5, anio);
            ps.setString(6, color);
            ps.executeUpdate();
            return "Guardado exitosamente";
        } catch (SQLException ex) {
            return Conexion.mensaje(ex);
        } finally {
            Conexion.desconectar(cx);
        }
    }

    public ArrayList<PedidoModel> listar() {
        ArrayList<PedidoModel> lista = new ArrayList<>();
        Connection cx = Conexion.conectar();
        if (cx == null) {
            return lista;
        }
        String sql = "select id_vehiculo, id_cliente, placa, marca, modelo, anio, color from vehiculo order by id_vehiculo";
        try (PreparedStatement ps = cx.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PedidoModel m = new PedidoModel();
                m.setIdVehiculo(rs.getInt("id_vehiculo"));
                m.setIdCliente(rs.getInt("id_cliente"));
                m.setPlaca(rs.getString("placa"));
                m.setMarca(rs.getString("marca"));
                m.setModelo(rs.getString("modelo"));
                m.setAnio(rs.getInt("anio"));
                m.setColor(rs.getString("color"));
                lista.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidoModel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.desconectar(cx);
        }
        return lista;
    }

    public String modificar() {
        int actual = Year.now().getValue();
        if (anio < 1900 || anio > actual + 1) {
            return "El anio debe estar entre 1900 y " + (actual + 1);
        }
        Connection cx = Conexion.conectar();
        if (cx == null) {
            return Conexion.SIN_CONEXION;
        }
        String sql = "update vehiculo set id_cliente = ?, placa = ?, marca = ?, modelo = ?, anio = ?, color = ? where id_vehiculo = ?";
        try (PreparedStatement ps = cx.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            ps.setString(2, placa);
            ps.setString(3, marca);
            ps.setString(4, modelo);
            ps.setInt(5, anio);
            ps.setString(6, color);
            ps.setInt(7, idVehiculo);
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
        String sql = "delete from vehiculo where id_vehiculo = ?";
        try (PreparedStatement ps = cx.prepareStatement(sql)) {
            ps.setInt(1, idVehiculo);
            if (ps.executeUpdate() == 0) return "El registro no existe";
            return "Eliminado exitosamente";
        } catch (SQLException ex) {
            return Conexion.mensaje(ex);
        } finally {
            Conexion.desconectar(cx);
        }
    }

}
