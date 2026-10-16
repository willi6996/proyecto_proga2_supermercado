package controller;

import java.util.ArrayList;
import model.VentaModel;

public class VentaController {

    private VentaModel modelo;

    public VentaController() {
        modelo = new VentaModel();
    }

    public String insertar(int idVehiculo, int idMecanico, String descripcion) {
        modelo = new VentaModel();
        modelo.setIdVehiculo(idVehiculo);
        modelo.setIdMecanico(idMecanico);
        modelo.setDescripcion(descripcion);
        return modelo.insertar();
    }

    public ArrayList<VentaModel> listar() {
        return new VentaModel().listar();
    }
    
    public String modificar(int id, int idVehiculo, int idMecanico, String descripcion) {
        modelo = new VentaModel();
        modelo.setIdOrden(id);
        modelo.setIdVehiculo(idVehiculo);
        modelo.setIdMecanico(idMecanico);
        modelo.setDescripcion(descripcion);
        return modelo.modificar();
    }

    public String cambiarEstado(int id, String accion) {
        modelo = new VentaModel();
        modelo.setIdOrden(id);
        return modelo.cambiarEstado(accion);
    }
    
    public String eliminar(int id) {
        modelo = new VentaModel();
        modelo.setIdOrden(id);
        return modelo.eliminar();
    }
}
