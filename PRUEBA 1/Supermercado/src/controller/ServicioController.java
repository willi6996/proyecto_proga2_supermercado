package controller;

import java.util.ArrayList;
import model.ServicioModel;

public class ServicioController {

    private ServicioModel modelo;

    public ServicioController() {
        modelo = new ServicioModel();
    }

    public String insertar(String descripcion, double costoManoObra) {
        modelo = new ServicioModel();
        modelo.setDescripcion(descripcion);
        modelo.setCostoManoObra(costoManoObra);
        return modelo.insertar();
    }

    public ArrayList<ServicioModel> listar() {
        return new ServicioModel().listar();
    }
    
    public String modificar(int id, String descripcion, double costo) {
        modelo = new ServicioModel();
        modelo.setIdServicio(id);
        modelo.setDescripcion(descripcion);
        modelo.setCostoManoObra(costo);
        return modelo.modificar();
    }

    public String eliminar(int id) {
        modelo = new ServicioModel();
        modelo.setIdServicio(id);
        return modelo.eliminar();
    }
}
