package controller;

import java.util.ArrayList;
import model.PedidoModel;

public class PedidoController {

    private PedidoModel modelo;

    public PedidoController() {
        modelo = new PedidoModel();
    }

    public String insertar(int idCliente, String placa, String marca, String modeloAuto, int anio, String color) {
        modelo = new PedidoModel();
        modelo.setIdCliente(idCliente);
        modelo.setPlaca(placa);
        modelo.setMarca(marca);
        modelo.setModelo(modeloAuto);
        modelo.setAnio(anio);
        modelo.setColor(color);
        return modelo.insertar();
    }

    public ArrayList<PedidoModel> listar() {
        return new PedidoModel().listar();
    }
    
    public String modificar(int id, int idCliente, String placa, String marca, String modeloAuto, int anio, String color) {
        modelo = new PedidoModel();
        modelo.setIdVehiculo(id);
        modelo.setIdCliente(idCliente);
        modelo.setPlaca(placa);
        modelo.setMarca(marca);
        modelo.setModelo(modeloAuto);
        modelo.setAnio(anio);
        modelo.setColor(color);
        return modelo.modificar();
    }

    public String eliminar(int id) {
        modelo = new PedidoModel();
        modelo.setIdVehiculo(id);
        return modelo.eliminar();
    }
    
}
