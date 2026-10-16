package controller;

import java.io.IOException;
import java.util.List;
import model.VentaModel;
import model.ReporteModel;
import model.ProductoModel;

public class ReporteController {

    private final ReporteModel modelo = new ReporteModel();

    public List<VentaModel> porEstado(String estado)
            throws IOException, InterruptedException {
        return modelo.porEstado(estado);
    }

    public List<VentaModel> porPlaca(String placa)
            throws IOException, InterruptedException {
        return modelo.porPlaca(placa);
    }

    public List<VentaModel> porMecanico(String nombre)
            throws IOException, InterruptedException {
        return modelo.porMecanico(nombre);
    }

    public List<VentaModel> porFechas(String inicio, String fin)
            throws IOException, InterruptedException {
        return modelo.porFechas(inicio, fin);
    }

    public List<ProductoModel> repuestosBajoInventario()
            throws IOException, InterruptedException {
        return modelo.repuestosBajoInventario();
    }
}
