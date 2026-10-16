package controller;

import java.util.ArrayList;
import model.ProductoModel;

public class ProductoController {

    private ProductoModel modelo;

    public ProductoController() {
        modelo = new ProductoModel();
    }

    public String insertar(String nombre, String descripcion, double precio) {
        modelo = new ProductoModel();
        modelo.setNombre(nombre);
        modelo.setDescripcion(descripcion);
        modelo.setPrecio(precio);
        return modelo.insertar();
    }

    public ArrayList<ProductoModel> listar() {
        return new ProductoModel().listar();
    }
    
    public String modificar(int id, String nombre, String descripcion, double precio) {
        modelo = new ProductoModel();
        modelo.setIdRepuesto(id);
        modelo.setNombre(nombre);
        modelo.setDescripcion(descripcion);
        modelo.setPrecio(precio);
        return modelo.modificar();
    }

    public String eliminar(int id) {
        modelo = new ProductoModel();
        modelo.setIdRepuesto(id);
        return modelo.eliminar();
    }
}

