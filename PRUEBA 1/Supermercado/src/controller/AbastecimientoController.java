package controller;

import java.util.ArrayList;
import model.AbastecimientoModel;

public class AbastecimientoController {

    private AbastecimientoModel modelo;

    public AbastecimientoController() {
        modelo = new AbastecimientoModel();
    }

    public String insertar(int idRepuesto, int cantidad) {
        modelo = new AbastecimientoModel();
        modelo.setIdRepuesto(idRepuesto);
        modelo.setCantidad(cantidad);
        return modelo.insertar();
    }

    public ArrayList<AbastecimientoModel> listar() {
        return new AbastecimientoModel().listar();
    }
    
    public String modificar(int id, int cantidad) {
        modelo = new AbastecimientoModel();
        modelo.setIdAbastecimiento(id);
        modelo.setCantidad(cantidad);
        return modelo.modificar();
    }

    public String eliminar(int id) {
        modelo = new AbastecimientoModel();
        modelo.setIdAbastecimiento(id);
        return modelo.eliminar();
    }

}