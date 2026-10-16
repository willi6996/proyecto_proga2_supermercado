package controller;

import java.util.ArrayList;
import model.ServicioVentaModel;

public class ServicioVentaController {

    private ServicioVentaModel modelo;

    public ServicioVentaController() {
        modelo = new ServicioVentaModel();
    }

    public String insertar(int idOrden, int idServicio, int cantidad) {
        modelo = new ServicioVentaModel();
        modelo.setIdOrden(idOrden);
        modelo.setIdServicio(idServicio);
        modelo.setCantidad(cantidad);
        return modelo.insertar();
    }

    public ArrayList<ServicioVentaModel> listar() {
        return new ServicioVentaModel().listar();
    }
    
    public String modificar(int id, int cantidad) {
        modelo = new ServicioVentaModel();
        modelo.setIdServicioRealizado(id);
        modelo.setCantidad(cantidad);
        return modelo.modificar();
    }

    public String eliminar(int id) {
        modelo = new ServicioVentaModel();
        modelo.setIdServicioRealizado(id);
        return modelo.eliminar();
    }
    
}
