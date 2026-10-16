# Verificacion del CRUD

Resultado local: **109 comprobaciones aprobadas** con H2 2.3.232 en modo Oracle,
Java 17 y compilador Eclipse ECJ 3.33.0. Se compilaron los 29 archivos Java fuente
del proyecto y la prueba de integracion (ver conteo del proyecto).
La prueba ejecuta los controladores, modelos, sentencias SQL y transacciones reales.
No utiliza mocks ni respuestas simuladas. El mismo archivo `database/02_tablas.sql`
crea el esquema de pruebas. Las lecturas comprueban los valores persistidos,
no solamente los mensajes devueltos por los metodos.

| Entidad de la interfaz | Tabla interna | Crear | Consultar | Modificar | Eliminar |
|---|---|---|---|---|---|
| Clientes | cliente | OK | OK | OK | OK |
| Empleados | mecanico | OK | OK | OK | OK |
| Pedidos | vehiculo | OK | OK | OK | OK |
| Productos | repuesto | OK | OK | OK | OK |
| Servicios | servicio | OK | OK | OK | OK |
| Ventas | orden | OK | OK | OK | OK, pendientes |
| Abastecimientos | abastecimiento | OK | OK | OK | OK |
| Productos vendidos | repuesto_utilizado | OK | OK | OK | OK, en proceso |
| Servicios realizados | servicio_realizado | OK | OK | OK | OK, en proceso |

El borrado de ventas pendientes se ofrece en la lista Estado como ELIMINAR;
se aplica con GUARDAR y una confirmacion. Las ventas iniciadas se cancelan,
no se borran. Las finalizadas conservan sus movimientos.

Tambien se comprobaron: precios/cantidades invalidas, codigos duplicados,
claves foraneas, registros inexistentes, stock insuficiente, rollback,
recalculo de totales, devolucion de stock al cancelar, bloqueo de doble
cancelacion y finalizacion sin servicios complementarios.
`resultado_h2.txt` contiene la salida real. `errores_esperados_h2.txt` contiene
cuatro excepciones registradas por los modelos durante pruebas negativas de
claves foraneas y unicidad; son rechazos esperados, no fallos de la suite.

## Limites de lo verificado

- Oracle NO se ejecuto en este entorno: no hay servidor Oracle disponible.
- H2 en modo Oracle no es el motor Oracle y no acredita compatibilidad total.
- No se automatizaron clics ni renderizado visual de Swing.
- Los reportes HTTP dependen del servidor original en localhost:8080, que no
  fue entregado; no estan incluidos en esta verificacion CRUD.
- No se hicieron pruebas de carga ni concurrencia.

## Repetir en Oracle

Crear un usuario SUPERMERCADO_TEST vacio con `database/04_usuario_pruebas.sql`.
Configurar la clave en `database-test.properties` y ejecutar
`probar_crud_oracle.bat`. El ejecutable exige ese nombre de usuario, rechaza
esquemas con tablas y no contiene DROP, TRUNCATE ni limpieza de datos ajenos.
Deja sus datos de prueba en el esquema aislado. Para repetir, un administrador
debe preparar nuevamente un esquema de pruebas vacio; nunca usar el esquema
que contiene los datos reales del supermercado.

Referencias de sintaxis:
- https://docs.oracle.com/en/database/oracle/oracle-database/21/sqlrf/CREATE-TABLE.html
- https://h2database.com/html/features.html

Verificacion repetida despues del renombrado de clases y paquete principal: 109/109 aprobadas.
