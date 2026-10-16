package controller;

import java.util.ArrayList;
import model.EmpleadoModel;

public class EmpleadoController {

    private EmpleadoModel modelo;

    public EmpleadoController() {
        modelo = new EmpleadoModel();
    }

    public String insertar(String nombre, String especialidad, String telefono) {
        modelo = new EmpleadoModel();
        modelo.setNombre(nombre);
        modelo.setEspecialidad(especialidad);
        modelo.setTelefono(telefono);
        return modelo.insertar();
    }

    public ArrayList<EmpleadoModel> listar() {
        return new EmpleadoModel().listar();
    }
    
    public String modificar(int id, String nombre, String especialidad, String telefono) {
        modelo = new EmpleadoModel();
        modelo.setId(id);
        modelo.setNombre(nombre);
        modelo.setEspecialidad(especialidad);
        modelo.setTelefono(telefono);
        return modelo.modificar();
    }

    public String eliminar(int id) {
        modelo = new EmpleadoModel();
        modelo.setId(id);
        return modelo.eliminar();
    }
    
}
