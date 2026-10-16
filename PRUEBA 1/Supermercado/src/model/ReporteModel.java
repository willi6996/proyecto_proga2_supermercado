package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ReporteModel {

    private final HttpClient cliente = HttpClient.newHttpClient();
    private final String baseUrl = "http://localhost:8080/api/reportes";
    private final Gson gson = new GsonBuilder()
        .registerTypeAdapter(
                java.time.LocalDate.class,
                (com.google.gson.JsonDeserializer<java.time.LocalDate>)
                (json, tipo, contexto) ->
                        json.isJsonNull()
                        ? null
                        : java.time.LocalDate.parse(json.getAsString())
        )
        .create();

    private String parametro(String valor) {
        return URLEncoder.encode(valor, StandardCharsets.UTF_8);
    }

    private String consultar(String ruta)
            throws IOException, InterruptedException {

        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + ruta))
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(
                solicitud,
                HttpResponse.BodyHandlers.ofString()
        );

        if (respuesta.statusCode() != 200) {
            throw new IOException(
                    "Error HTTP " + respuesta.statusCode()
                    + ": " + respuesta.body()
            );
        }

        return respuesta.body();
    }

    private List<VentaModel> convertirOrdenes(String json) {
        Type tipo = new TypeToken<List<VentaModel>>() {}.getType();
        return gson.fromJson(json, tipo);
    }

    public List<VentaModel> porEstado(String estado)
            throws IOException, InterruptedException {
        return convertirOrdenes(consultar(
                "/ordenes-por-estado?estado=" + parametro(estado)));
    }

    public List<VentaModel> porPlaca(String placa)
            throws IOException, InterruptedException {
        return convertirOrdenes(consultar(
                "/reparaciones-por-vehiculo?placa=" + parametro(placa)));
    }

    public List<VentaModel> porMecanico(String nombre)
            throws IOException, InterruptedException {
        return convertirOrdenes(consultar(
                "/reparaciones-por-mecanico?nombre=" + parametro(nombre)));
    }

    public List<VentaModel> porFechas(String inicio, String fin)
            throws IOException, InterruptedException {
        return convertirOrdenes(consultar(
                "/ingresos?inicio=" + parametro(inicio)
                + "&fin=" + parametro(fin)));
    }

    public List<ProductoModel> repuestosBajoInventario()
            throws IOException, InterruptedException {

        String json = consultar("/repuestos-bajo-inventario");

        Type tipo = new TypeToken<List<ProductoModel>>() {}.getType();
        return gson.fromJson(json, tipo);
    }
}
