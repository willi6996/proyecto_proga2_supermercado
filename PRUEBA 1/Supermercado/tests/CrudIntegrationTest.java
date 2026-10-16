import controller.*;
import model.*;
import supermercado.Conexion;
import java.nio.file.*;
import java.sql.*;
import java.util.*;

/** Prueba de integracion: usa controladores y modelos reales, sin mocks. */
public class CrudIntegrationTest {
    static int checks;
    static final ClienteController clientes = new ClienteController();
    static final EmpleadoController empleados = new EmpleadoController();
    static final PedidoController pedidos = new PedidoController();
    static final ProductoController productos = new ProductoController();
    static final ServicioController servicios = new ServicioController();
    static final VentaController ventas = new VentaController();
    static final AbastecimientoController abastos = new AbastecimientoController();
    static final DetalleVentaController detalles = new DetalleVentaController();
    static final ServicioVentaController extras = new ServicioVentaController();
    static void check(String name, boolean ok) {
        if (!ok) throw new AssertionError("FALLO: " + name);
        checks++;
        System.out.println("OK " + checks + " - " + name);
    }
    static void result(String name, String value, String prefix) {
        check(name + " [" + value.replace('\n', ' ') + "]", value.startsWith(prefix));
    }
    static double scalar(String sql) throws Exception {
        try (Connection cx = Conexion.conectar(); Statement st = cx.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            if (!rs.next()) throw new AssertionError("Consulta sin resultado: " + sql);
            return rs.getDouble(1);
        }
    }
    static int id(String table, String col) throws Exception {return (int)scalar("select max("+col+") from "+table);}
    static double stock(int id) throws Exception {return scalar("select existencia from repuesto where id_repuesto="+id);}
    static double total(int id) throws Exception {return scalar("select total from orden where id_orden="+id);}
    static void init(boolean oracle) throws Exception {
        if (!oracle) {
            System.setProperty("db.url", "jdbc:h2:mem:crud;MODE=Oracle;DB_CLOSE_DELAY=-1;DEFAULT_NULL_ORDERING=HIGH");
            System.setProperty("db.user", "sa");
            System.setProperty("db.password", "");
        } else {
            if (!System.getProperty("db.url", "").startsWith("jdbc:oracle:") ||
                !System.getProperty("db.user", "").equalsIgnoreCase("SUPERMERCADO_TEST"))
                throw new IllegalArgumentException("Oracle requiere -Ddb.url y -Ddb.user=SUPERMERCADO_TEST (usuario aislado). No usar el usuario de la aplicacion.");
        }
        try (Connection cx = Conexion.conectar()) {
            if (cx == null) throw new IllegalStateException("No hay conexion");
            System.out.println("MOTOR: " + cx.getMetaData().getDatabaseProductName() + " " + cx.getMetaData().getDatabaseProductVersion());
            if (oracle) {
                try (Statement s = cx.createStatement(); ResultSet r = s.executeQuery("select count(*) from user_tables")) {
                    r.next(); if (r.getInt(1) != 0) throw new IllegalStateException("El esquema de pruebas debe estar vacio. No se modifica un esquema existente.");
                }
            }
            String ddl = Files.readString(Path.of("database/02_tablas.sql")).replaceAll("(?m)--.*$", "");
            for (String sql : ddl.split(";")) if (!sql.isBlank()) try (Statement st=cx.createStatement()) {st.execute(sql);}
            check("Creacion de las 9 tablas y sus indices", true);
        }
    }
    public static void main(String[] args) throws Exception {
        init(args.length > 0 && args[0].equals("--oracle"));
        // Clientes: C, R, U. D se prueba despues de retirar dependencias.
        result("Cliente crear", clientes.insertar("Cliente prueba", "Zona 1", "55550001"), "Guardado");
        int c=id("cliente","id_cliente");
        check("Cliente leer", clientes.listar().stream().anyMatch(x->x.getId()==c && x.getDireccion().equals("Zona 1")));
        result("Cliente modificar", clientes.modificar(c,"Cliente editado","Zona 2","55550002"),"Modificado");
        check("Cliente cambios persistidos",clientes.listar().get(0).getNombre().equals("Cliente editado"));
        result("Empleado crear",empleados.insertar("Cajero prueba","Caja","55550003"),"Guardado");
        int e=id("mecanico","id_mecanico");
        check("Empleado leer",empleados.listar().stream().anyMatch(x->x.getId()==e));
        result("Empleado modificar",empleados.modificar(e,"Empleado editado","Supervisor","55550004"),"Modificado");
        check("Empleado cambios persistidos",empleados.listar().get(0).getEspecialidad().equals("Supervisor"));
        int year=java.time.Year.now().getValue();
        result("Pedido crear",pedidos.insertar(c,"PED-001","Central","Domicilio",year,"Zona 2"),"Guardado");
        int p=id("vehiculo","id_vehiculo");
        check("Pedido leer",pedidos.listar().stream().anyMatch(x->x.getIdVehiculo()==p));
        result("Pedido modificar",pedidos.modificar(p,c,"PED-002","Norte","Recogida",year,"Caja 1"),"Modificado");
        check("Pedido cambios persistidos",pedidos.listar().get(0).getPlaca().equals("PED-002"));
        result("Cliente relacionado protegido",clientes.eliminar(c),"No se puede eliminar");
        result("Producto crear",productos.insertar("Arroz","Bolsa 1 kg",10),"Guardado");
        int r=id("repuesto","id_repuesto");
        check("Producto leer",productos.listar().get(0).getIdRepuesto()==r && productos.listar().get(0).getExistencia()==0);
        result("Producto modificar",productos.modificar(r,"Arroz premium","Bolsa 1 kg",12.50),"Modificado");
        check("Producto cambios persistidos",productos.listar().get(0).getPrecio()==12.50);
        result("Servicio crear",servicios.insertar("Entrega",5),"Guardado");
        int s=id("servicio","id_servicio");
        check("Servicio leer",servicios.listar().stream().anyMatch(x->x.getIdServicio()==s));
        result("Servicio modificar",servicios.modificar(s,"Entrega a domicilio",7.50),"Modificado");
        check("Servicio cambios persistidos",servicios.listar().get(0).getCostoManoObra()==7.50);
        result("Abastecimiento crear",abastos.insertar(r,20),"Abastecimiento registrado");
        int a=id("abastecimiento","id_abastecimiento");
        check("Abastecimiento leer y stock",abastos.listar().size()==1 && stock(r)==20);
        result("Abastecimiento modificar",abastos.modificar(a,30),"Modificado");
        check("Abastecimiento cambios persistidos y diferencia de stock",abastos.listar().get(0).getCantidad()==30 && stock(r)==30);
        result("Venta crear",ventas.insertar(p,e,"Compra prueba"),"Venta creada");
        int v=id("orden","id_orden");
        check("Venta leer",ventas.listar().get(0).getIdOrden()==v && ventas.listar().get(0).getEstado().equals("PENDIENTE"));
        result("Venta modificar",ventas.modificar(v,p,e,"Compra editada"),"Modificado");
        check("Venta cambios persistidos",ventas.listar().get(0).getDescripcion().equals("Compra editada"));
        result("Venta eliminar pendiente",ventas.eliminar(v),"Eliminado");
        check("Venta eliminacion persistida",ventas.listar().isEmpty());
        result("Venta nueva para detalles",ventas.insertar(p,e,"Compra completa"),"Venta creada");
        v=id("orden","id_orden");
        result("Detalle rechazado en venta pendiente",detalles.insertar(v,r,1),"La venta debe estar EN PROCESO");
        check("Rechazo sin cambios de stock y total",stock(r)==30 && total(v)==0);
        result("Iniciar venta",ventas.cambiarEstado(v,"INICIAR"),"Modificado");
        result("Venta sin productos no finaliza",ventas.cambiarEstado(v,"FINALIZAR"),"No se puede finalizar");
        result("Producto vendido crear",detalles.insertar(v,r,2),"Producto agregado");
        int d=id("repuesto_utilizado","id_repuesto_utilizado");
        check("Producto vendido leer y efectos",detalles.listar().size()==1 && stock(r)==28 && total(v)==25);
        result("Producto vendido modificar",detalles.modificar(d,3),"Modificado");
        check("Detalle cambios persistidos, stock y total",detalles.listar().get(0).getCantidad()==3 && stock(r)==27 && total(v)==37.50);
        result("Servicio realizado crear",extras.insertar(v,s,2),"Servicio agregado");
        int sr=id("servicio_realizado","id_servicio_realizado");
        check("Servicio realizado leer y total",extras.listar().size()==1 && total(v)==52.50);
        result("Servicio realizado modificar",extras.modificar(sr,1),"Modificado");
        check("Servicio realizado cambios persistidos y total",extras.listar().get(0).getCantidad()==1 && total(v)==45);
        result("Servicio realizado eliminar",extras.eliminar(sr),"Eliminado");
        check("Servicio realizado eliminacion persistida y total",extras.listar().isEmpty() && total(v)==37.50);
        result("Stock insuficiente al crear",detalles.insertar(v,r,100),"No hay existencia suficiente");
        result("Stock insuficiente al editar",detalles.modificar(d,100),"No hay existencia suficiente");
        check("Rollback de detalle sin alterar cantidades",stock(r)==27 && total(v)==37.50 && detalles.listar().get(0).getCantidad()==3);
        result("Abastecimiento no se borra si stock quedaria negativo",abastos.eliminar(a),"No se puede eliminar");
        result("Abastecimiento no se reduce bajo consumo",abastos.modificar(a,1),"No se puede modificar");
        check("Rollback de abastecimiento",stock(r)==27 && abastos.listar().get(0).getCantidad()==30);
        result("Producto vendido eliminar",detalles.eliminar(d),"Eliminado");
        check("Detalle eliminacion persistida y devolucion de stock",detalles.listar().isEmpty() && stock(r)==30 && total(v)==0);
        result("Servicio eliminar",servicios.eliminar(s),"Eliminado");
        check("Servicio eliminacion persistida",servicios.listar().isEmpty());
        result("Abastecimiento eliminar",abastos.eliminar(a),"Eliminado");
        check("Abastecimiento eliminacion persistida y stock",abastos.listar().isEmpty() && stock(r)==0);
        result("Producto eliminar",productos.eliminar(r),"Eliminado");
        check("Producto eliminacion persistida",productos.listar().isEmpty());
        // Venta en proceso no puede borrarse: se conserva trazabilidad.
        result("Venta iniciada protegida frente a borrado",ventas.eliminar(v),"Solo se pueden eliminar");
        result("Cancelar venta vacia",ventas.cambiarEstado(v,"CANCELAR"),"Modificado");
        // Nuevas entidades independientes para comprobar eliminaciones de padres.
        result("Pedido independiente crear",pedidos.insertar(c,"PED-DEL","Central","Recogida",year,"Caja"),"Guardado");
        int pd=id("vehiculo","id_vehiculo");
        result("Pedido eliminar",pedidos.eliminar(pd),"Eliminado");
        check("Pedido eliminacion persistida",pedidos.listar().stream().noneMatch(x->x.getIdVehiculo()==pd));
        result("Empleado independiente crear",empleados.insertar("Temporal","Caja","55550005"),"Guardado");
        int ed=id("mecanico","id_mecanico");
        result("Empleado eliminar",empleados.eliminar(ed),"Eliminado");
        check("Empleado eliminacion persistida",empleados.listar().stream().noneMatch(x->x.getId()==ed));
        result("Cliente independiente crear",clientes.insertar("Temporal","Zona 3","55550006"),"Guardado");
        int cd=id("cliente","id_cliente");
        result("Cliente eliminar",clientes.eliminar(cd),"Eliminado");
        check("Cliente eliminacion persistida",clientes.listar().stream().noneMatch(x->x.getId()==cd));
        // No confirmar operaciones sobre IDs inexistentes.
        result("Cliente inexistente modificar",clientes.modificar(-1,"X","X","X"),"El registro no existe");
        result("Cliente inexistente eliminar",clientes.eliminar(-1),"El registro no existe");
        result("Empleado inexistente modificar",empleados.modificar(-1,"X","X","X"),"El registro no existe");
        result("Empleado inexistente eliminar",empleados.eliminar(-1),"El registro no existe");
        result("Pedido inexistente modificar",pedidos.modificar(-1,c,"XX","X","X",year,"X"),"El registro no existe");
        result("Pedido inexistente eliminar",pedidos.eliminar(-1),"El registro no existe");
        result("Producto inexistente modificar",productos.modificar(-1,"X","X",1),"El registro no existe");
        result("Producto inexistente eliminar",productos.eliminar(-1),"El registro no existe");
        result("Servicio inexistente modificar",servicios.modificar(-1,"X",1),"El registro no existe");
        result("Servicio inexistente eliminar",servicios.eliminar(-1),"El registro no existe");
        result("Venta inexistente eliminar",ventas.eliminar(-1),"La venta no existe");
        result("Cantidad cero rechazada",abastos.insertar(-1,0),"La cantidad debe ser mayor");
        result("Precio negativo rechazado",productos.insertar("X","X",-1),"El precio no puede");
        result("Costo negativo rechazado",servicios.insertar("X",-1),"El costo no puede");
        // FK y restricciones: deben rechazar la operacion y no dejar inserciones parciales.
        int before=abastos.listar().size();
        check("FK abastecimiento rechaza producto inexistente",!abastos.insertar(-1,1).startsWith("Abastecimiento registrado"));
        check("FK fallida no deja abastecimiento",abastos.listar().size()==before);
        check("FK impide borrar empleado con venta",!empleados.eliminar(e).startsWith("Eliminado"));
        check("FK impide borrar pedido con venta",!pedidos.eliminar(p).startsWith("Eliminado"));
        check("Codigo pedido duplicado rechazado",!pedidos.insertar(c,"PED-002","X","X",year,"X").startsWith("Guardado"));
        // Cancelacion devuelve existencias exactamente una vez.
        result("Producto para cancelacion",productos.insertar("Leche","Litro",8),"Guardado");
        r=id("repuesto","id_repuesto");
        result("Stock para cancelacion",abastos.insertar(r,10),"Abastecimiento registrado");
        result("Venta para cancelacion",ventas.insertar(p,e,"Cancelar"),"Venta creada");
        v=id("orden","id_orden");
        result("Iniciar venta cancelable",ventas.cambiarEstado(v,"INICIAR"),"Modificado");
        result("Detalle cancelable",detalles.insertar(v,r,4),"Producto agregado");
        result("Cancelar con productos",ventas.cambiarEstado(v,"CANCELAR"),"Modificado");
        check("Cancelacion devuelve stock",stock(r)==10);
        result("Cancelacion repetida rechazada",ventas.cambiarEstado(v,"CANCELAR"),"Esta venta ya no");
        check("No se duplica devolucion",stock(r)==10);
        // Finalizacion sin servicios complementarios.
        result("Venta para finalizar",ventas.insertar(p,e,"Finalizar"),"Venta creada");
        v=id("orden","id_orden");
        result("Iniciar venta final",ventas.cambiarEstado(v,"INICIAR"),"Modificado");
        result("Detalle venta final",detalles.insertar(v,r,2),"Producto agregado");
        d=id("repuesto_utilizado","id_repuesto_utilizado");
        result("Finalizar sin servicios",ventas.cambiarEstado(v,"FINALIZAR"),"Modificado");
        check("Finalizacion guarda fecha, stock y total",ventas.listar().get(ventas.listar().size()-1).getFechaFinal()!=null && stock(r)==8 && total(v)==16);
        result("Detalle finalizado no editable",detalles.modificar(d,1),"La venta debe estar EN PROCESO");
        result("Detalle finalizado no eliminable",detalles.eliminar(d),"La venta debe estar EN PROCESO");
        result("Venta finalizada no editable",ventas.modificar(v,p,e,"X"),"La venta ya no");
        check("Datos finales conservados",stock(r)==8 && total(v)==16);
        System.out.println("RESULTADO: " + checks + " comprobaciones aprobadas; CRUD de las 9 entidades probado. Sin prueba visual de Swing ni del servidor HTTP de reportes.");
    }
}
