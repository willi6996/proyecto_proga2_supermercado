
package supermercado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

    public static final String SIN_CONEXION = "No se pudo conectar a la base de datos";

    private static final java.util.Properties CONFIG = cargarConfiguracion();

    private static java.util.Properties cargarConfiguracion() {
        java.util.Properties config = new java.util.Properties();
        java.nio.file.Path ruta = java.nio.file.Path.of(
                System.getProperty("db.config", "database.properties"));
        if (java.nio.file.Files.exists(ruta)) {
            try (java.io.Reader reader = java.nio.file.Files.newBufferedReader(ruta)) {
                config.load(reader);
            } catch (java.io.IOException ex) {
                throw new IllegalStateException("No se pudo leer database.properties", ex);
            }
        }
        return config;
    }

    private static String configurar(String clave, String defecto) {
        return System.getProperty(clave, CONFIG.getProperty(clave, defecto));
    }

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(
                    configurar("db.url", "jdbc:oracle:thin:@localhost:1521/freepdb1"),
                    configurar("db.user", "supermercado"),
                    configurar("db.password", ""));
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static void desconectar(Connection cx) {
        try {
            if (cx != null) {
                cx.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void revertir(Connection cx) {
        try {
            if (cx != null) {
                cx.rollback();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String mensaje(SQLException ex) {
        Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        switch (ex.getErrorCode()) {
            case 1:
                return "Ya existe un registro con ese valor";
            case 2290:
                return "Valor no permitido";
            case 2291:
                return "El registro relacionado no existe";
            case 2292:
                return "No se puede eliminar: tiene registros asociados";
            default:
                return ex.getMessage();
        }
    }

    
}
