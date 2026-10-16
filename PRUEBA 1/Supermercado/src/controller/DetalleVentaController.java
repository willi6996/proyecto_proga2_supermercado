package controller;

import java.util.ArrayList;
import model.DetalleVentaModel;

public class DetalleVentaController {

    private DetalleVentaModel modelo;

    public DetalleVentaController() {
        modelo = new DetalleVentaModel();
    }

    public String insertar(int idOrden, int idRepuesto, int cantidad) {
        modelo = new DetalleVentaModel();
        modelo.setIdOrden(idOrden);
        modelo.setIdRepuesto(idRepuesto);
        modelo.setCantidad(cantidad);
        return modelo.insertar();
    }

    public ArrayList<DetalleVentaModel> listar() {
        return new DetalleVentaModel().listar();
    }
    
    public String modificar(int id, int cantidad) {
        modelo = new DetalleVentaModel();
        modelo.setIdRepuestoUtilizado(id);
        modelo.setCantidad(cantidad);
        return modelo.modificar();
    }

    public String eliminar(int id) {
        modelo = new DetalleVentaModel();
        modelo.setIdRepuestoUtilizado(id);
        return modelo.eliminar();
    }
}
