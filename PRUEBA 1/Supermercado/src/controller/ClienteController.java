package controller;

import java.util.ArrayList;
import model.ClienteModel;

public class ClienteController {

    private ClienteModel modelo;

    public ClienteController() {
        modelo = new ClienteModel();
    }
    
    public String insertar(String nombre, String direccion, String telefono) {
        modelo = new ClienteModel();
        modelo.setNombre(nombre);
        modelo.setDireccion(direccion);
        modelo.setTelefono(telefono);
        return modelo.insertar();
    }

    public ArrayList<ClienteModel> listar() {
        return new ClienteModel().listar();
    }

    public String modificar(int id, String nombre, String direccion, String telefono) {
        modelo = new ClienteModel();
        modelo.setId(id);
        modelo.setNombre(nombre);
        modelo.setDireccion(direccion);
        modelo.setTelefono(telefono);
        return modelo.modificar();
    }

    public String eliminar(int id) {
        modelo = new ClienteModel();
        modelo.setId(id);
        return modelo.eliminar();
    }

}
