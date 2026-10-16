package view;

import controller.AbastecimientoController;
import controller.ClienteController;
import controller.EmpleadoController;
import controller.VentaController;
import controller.ReporteController;
import controller.ProductoController;
import controller.DetalleVentaController;
import controller.ServicioController;
import controller.ServicioVentaController;
import controller.PedidoController;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.AbastecimientoModel;
import model.ClienteModel;
import model.EmpleadoModel;
import model.VentaModel;
import model.ProductoModel;
import model.DetalleVentaModel;
import model.ServicioModel;
import model.ServicioVentaModel;
import model.PedidoModel;
import swingx.RoundButton;
import swingx.RoundPanel;

public class ConsultarRegistro extends javax.swing.JFrame {

    private int moverX, moverY, x, y;
    private RoundButton[] botones;
    private RoundPanel[] paneles;
    private RoundPanel[] buscadores;
    private Image icono;
    
    private ClienteController controladorCli = new ClienteController();
    private PedidoController controladorVeh = new PedidoController();
    private EmpleadoController controladorMec = new EmpleadoController();
    private VentaController controladorOrd = new VentaController();
    private ProductoController controladorRep = new ProductoController();
    private DetalleVentaController controladorUti = new DetalleVentaController();
    private AbastecimientoController controladorAba = new AbastecimientoController();
    private ServicioController controladorSer = new ServicioController();
    private ServicioVentaController controladorRea = new ServicioVentaController();
    private final ReporteController controladorReporte = new ReporteController();


    public ConsultarRegistro() {
        initComponents();
        setLocationRelativeTo(null);
        aparecer();
        tablas();
        panels();
        botones();
        iniciarMenu();
        mostrar(0);
    }
    
    private void aparecer() {
        icono = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/logo_supermercado.png"));
        setIconImage(icono);
        setOpacity(0f);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent e) {
                Timer timer = new Timer(10, null);
                timer.addActionListener(ev -> {
                    float opacidad = getOpacity() + 0.1f;
                    if (opacidad >= 1f) {
                        setOpacity(1f);
                        timer.stop();
                    } else {
                        setOpacity(opacidad);
                    }
                });
                timer.start();
            }
        });
    }
    
    private void tablas() {
        estilizar(tableClientes, scrollClientes);
        anchos(tableClientes, scrollClientes, 30, 100, 200, 80);
        
        estilizar(tableVehiculos, scrollVehiculos);
        anchos(tableVehiculos, scrollVehiculos, 30, 30, 100, 100, 100, 50, 100);
        
        estilizar(tableMecanicos, scrollMecanicos);
        anchos(tableMecanicos, scrollMecanicos, 30, 100, 200, 80);
        
        estilizar(tableOrdenes, scrollOrdenes);
        anchos(tableOrdenes, scrollOrdenes, 30, 30, 30, 150, 50, 50, 50, 30);
        estilizarCombo(comboEstado);
        estilizarCombo(comboFiltroOrden);
        estilizarCombo(comboBuscarEstado);
        
        estilizar(tableRepuestos, scrollRepuestos);
        anchos(tableRepuestos, scrollRepuestos, 30, 100, 150, 60, 60);
        estilizarCombo(comboFiltroRepuesto);
        
        estilizar(tableUtilizados, scrollUtilizados);
        anchos(tableUtilizados, scrollUtilizados, 60, 30, 30, 80, 80, 80);
        
        estilizar(tableAbastecimiento, scrollAbastecimiento);
        anchos(tableAbastecimiento, scrollAbastecimiento, 100, 100, 150, 200);
        
        estilizar(tableServicios, scrollServicios);
        anchos(tableServicios, scrollServicios, 50, 300, 200);
        
        estilizar(tableRealizados, scrollRealizados);
        anchos(tableRealizados, scrollRealizados, 60, 30, 30, 80, 80, 80);
    }
    
    public void botones(){
         btnCliente.setIconPath("/icon/cliente.png", 35);
         btnVehiculo.setIconPath("/icon/pedido.png", 35);
         btnMecanico.setIconPath("/icon/empleado.png", 35);
         btnOrden.setIconPath("/icon/orden.png", 35);
         btnRepuesto.setIconPath("/icon/producto.png", 35);
         btnServicio.setIconPath("/icon/servicio_supermercado.png", 35);
         btnRegresar.setIconPath("/icon/regresar.png", 35);
         
         btnImportarCSV.setIconPath("/icon/importar.png", 23);
         btnExportarRep.setIconPath("/icon/exportar.png", 23);
         
         btnExportarOrdEst.setIconPath("/icon/exportar.png", 23);
         
         btnBuscarPlaca.setIconPath("/icon/buscar.png", 25);
         btnExportarOrdPla.setIconPath("/icon/exportar.png", 23);
         
         btnBuscarMecanico.setIconPath("/icon/buscar.png", 25);
         btnExportarOrdMec.setIconPath("/icon/exportar.png", 23);
         
         btnBuscarFecha.setIconPath("/icon/buscar.png", 25);
         btnExportarOrdIng.setIconPath("/icon/exportar.png", 23);
         
         btnUtilizado.setVisible(false);
         btnAbastecimiento.setVisible(false);
         btnRealizado.setVisible(false);
         
        labelLogo.setIcon("/icon/logo_supermercado.png", 90);
    }
     
    public void panels() {
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
        
        formularioCliente.setVisible(false);
        formularioVehiculo.setVisible(false);
        formularioMecanico.setVisible(false);
        formularioOrden.setVisible(false);
        formularioRepuesto.setVisible(false);
        formularioUtilizado.setVisible(false);
        formularioAbastecimiento.setVisible(false);
        formularioServicio.setVisible(false);
        formularioRealizado.setVisible(false);
        txtIdOrdenUti.setEditable(false);
        txtIdRepuestoUti.setEditable(false);
        txtIdRepuestoAba.setEditable(false);
        txtIdOrdenRea.setEditable(false);
        txtIdServicioRea.setEditable(false);
    }
    
    private void estilizar(JTable tabla, JScrollPane scroll) {
        DefaultTableCellRenderer encabezado = new DefaultTableCellRenderer() {
            @Override
            public void setValue(Object value) {
                setBackground(Color.WHITE);
                setForeground(new Color(8, 29, 86));
                setHorizontalAlignment(LEFT);
                setVerticalAlignment(TOP);
                setFont(new Font("Arial", Font.BOLD, 13));
                super.setValue(value);
            }
        };

        tabla.getTableHeader().setDefaultRenderer(encabezado);
        tabla.getTableHeader().setPreferredSize(new Dimension(0, 40));

        scroll.setBorder(null);
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
        scroll.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                thumbColor = new Color(153, 204,255);
            }
            @Override
            protected JButton createDecreaseButton(int o) {
                return botonVacio();
            }
            @Override
            protected JButton createIncreaseButton(int o) {
                return botonVacio();
            }
            private JButton botonVacio() {
                JButton b = new JButton();
                b.setPreferredSize(new Dimension(0, 0));
                return b;
            }
        });
    }
    
        private void filtroOrden(int indice) {
            panelBuscaOrden.setVisible(indice == 0);
            panelBuscaOrdenEst.setVisible(indice == 1);
            panelBuscaOrdenPla.setVisible(indice == 2);
            panelBuscaOrdenMec.setVisible(indice == 3);
            panelBuscaOrdenIng.setVisible(indice == 4);
        }
    
    private void estilizarCombo(JComboBox<?> combo) {
        combo.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton b = new JButton("▾");
                b.setBorder(BorderFactory.createEmptyBorder());
                b.setContentAreaFilled(false);
                b.setFocusPainted(false);
                b.setForeground(new Color(8, 29, 86));
                return b;
            }
        });
        combo.setBackground(Color.WHITE);
        combo.setForeground(new Color(51, 51, 51));
        combo.setFont(new Font("Bahnschrift", Font.BOLD, 12));
        combo.setBorder(BorderFactory.createCompoundBorder(
        new javax.swing.border.LineBorder(new Color(229, 229, 229), 3, true), BorderFactory.createEmptyBorder(2, 8, 2, 4)));
        combo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));
                setBackground(isSelected ? new Color(153, 204,255) : Color.WHITE);
                setForeground(isSelected ? Color.WHITE : new Color(8, 29, 86));
                return this;
            }
        });
    }
    
    private void anchos(JTable tabla, JScrollPane scroll, int... anchos) {
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        for (int i = 0; i < anchos.length; i++) {
            tabla.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
    }
 
    private void iniciarMenu() {
        botones = new RoundButton[]{btnCliente, btnVehiculo, btnMecanico, btnOrden, btnRepuesto,
            btnUtilizado, btnAbastecimiento, btnServicio, btnRealizado};
        paneles = new RoundPanel[]{panelCliente, panelVehiculo, panelMecanico, panelOrden, panelRepuesto,
            panelUtilizado, panelAbastecimiento, panelServicio, panelRealizado};
         buscadores = new RoundPanel[]{panelBuscaCliente, panelBuscaVehiculo, panelBuscaMecanico, panelBuscaOrden,
            panelBuscaRepuesto, panelBuscaUtilizado, panelBuscaAbastecimiento, panelBuscaServicio, panelBuscaRealizado};
        filtroOrden(0);
    }

    private void mostrar(int indice) {
        int activo = indice;
        for (int i = 0; i < botones.length; i++) {
            botones[i].setBackground(i == indice ? new Color(0, 0, 51) : new Color(8, 29, 86));
            paneles[i].setVisible(i == indice);
            buscadores[i].setVisible(i == indice);
        }

        boolean grupoRepuesto = indice == 4 || indice == 5 || indice == 6;
        boolean grupoServicio = indice == 7 || indice == 8;
        btnUtilizado.setVisible(grupoRepuesto);
        btnAbastecimiento.setVisible(grupoRepuesto);
        btnRealizado.setVisible(grupoServicio);
        
        comboFiltroOrden.setVisible(indice == 3);
            if (indice == 3) {
                filtroOrden(comboFiltroOrden.getSelectedIndex());
            } else {
                filtroOrden(-1);
            }
            
         switch (indice) {
            case 0: mostrarClientes(); break;
            case 1: mostrarVehiculos(); break;
            case 2: mostrarMecanicos(); break;
            case 3: mostrarOrdenes(); break;
            case 4: mostrarRepuestos(); break;
            case 5: mostrarUtilizados(); break;
            case 6: mostrarAbastecimientos(); break;
            case 7: mostrarServicios(); break;
            case 8: mostrarRealizados(); break;
        }
    }
    
    
    private void mostrarClientes() {
        DefaultTableModel tabla = (DefaultTableModel) tableClientes.getModel();
        tabla.setRowCount(0);
        for (ClienteModel c : controladorCli.listar()) {
            tabla.addRow(new Object[]{
                c.getId(),
                c.getNombre(),
                c.getDireccion(),
                c.getTelefono()
            });
        }
    }

    private void mostrarVehiculos() {
        DefaultTableModel tabla = (DefaultTableModel) tableVehiculos.getModel();
        tabla.setRowCount(0);
        for (PedidoModel v : controladorVeh.listar()) {
            tabla.addRow(new Object[]{
                v.getIdVehiculo(),
                v.getIdCliente(),
                v.getPlaca(),
                v.getMarca(),
                v.getModelo(),
                v.getAnio(),
                v.getColor()
            });
        }
    }

    private void mostrarMecanicos() {
        DefaultTableModel tabla = (DefaultTableModel) tableMecanicos.getModel();
        tabla.setRowCount(0);
        for (EmpleadoModel m : controladorMec.listar()) {
            tabla.addRow(new Object[]{
                m.getId(),
                m.getNombre(),
                m.getEspecialidad(),
                m.getTelefono()
            });
        }
    }

    private void mostrarOrdenes() {
        DefaultTableModel tabla = (DefaultTableModel) tableOrdenes.getModel();
        tabla.setRowCount(0);
        for (VentaModel o : controladorOrd.listar()) {
            tabla.addRow(new Object[]{
                o.getIdOrden(),
                o.getIdVehiculo(),
                o.getIdMecanico(),
                o.getDescripcion(),
                o.getFechaCreacion(),
                o.getFechaFinal(),
                o.getEstado(),
                o.getTotal()
            });
        }
    }

    private void mostrarRepuestos() {
        DefaultTableModel tabla = (DefaultTableModel) tableRepuestos.getModel();
        tabla.setRowCount(0);
        for (ProductoModel r : controladorRep.listar()) {
            tabla.addRow(new Object[]{
                r.getIdRepuesto(),
                r.getNombre(),
                r.getDescripcion(),
                r.getPrecio(),
                r.getExistencia()
            });
        }
    }

    private void mostrarUtilizados() {
        DefaultTableModel tabla = (DefaultTableModel) tableUtilizados.getModel();
        tabla.setRowCount(0);
        for (DetalleVentaModel r : controladorUti.listar()) {
            tabla.addRow(new Object[]{
                r.getIdRepuestoUtilizado(),
                r.getIdOrden(),
                r.getIdRepuesto(),
                r.getCantidad(),
                r.getPrecio(),
                r.getSubtotal()
            });
        }
    }

    private void mostrarAbastecimientos() {
        DefaultTableModel tabla = (DefaultTableModel) tableAbastecimiento.getModel();
        tabla.setRowCount(0);
        for (AbastecimientoModel a : controladorAba.listar()) {
            tabla.addRow(new Object[]{
                a.getIdAbastecimiento(),
                a.getIdRepuesto(),
                a.getCantidad(),
                a.getFecha()
            });
        }
    }

    private void mostrarServicios() {
        DefaultTableModel tabla = (DefaultTableModel) tableServicios.getModel();
        tabla.setRowCount(0);
        for (ServicioModel s : controladorSer.listar()) {
            tabla.addRow(new Object[]{
                s.getIdServicio(),
                s.getDescripcion(),
                s.getCostoManoObra()
            });
        }
    }

    private void mostrarRealizados() {
        DefaultTableModel tabla = (DefaultTableModel) tableRealizados.getModel();
        tabla.setRowCount(0);
        for (ServicioVentaModel s : controladorRea.listar()) {
            tabla.addRow(new Object[]{
                s.getIdServicioRealizado(),
                s.getIdOrden(),
                s.getIdServicio(),
                s.getCantidad(),
                s.getPrecio(),
                s.getSubtotal()
            });
        }
    }
    
    
    private void aviso(String texto) {
        JOptionPane.showMessageDialog(this, texto, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
    
     private void limpiar(JTextField... campos) {
        for (JTextField campo : campos) {
            campo.setText("");
        }
    }

    private int idSeleccionado(JTable tabla) {
        return (int) tabla.getValueAt(tabla.getSelectedRow(), 0);
    }

    private void cancelarCliente() {
        formularioCliente.setVisible(false);
        tableClientes.clearSelection();
        limpiar(txtNombreCli, txtDireccionCli, txtTelefonoCli);
    }
    
    private void cancelarVehiculo() {
        formularioVehiculo.setVisible(false);
        tableVehiculos.clearSelection();
        limpiar(txtIdClienteVeh, txtPlacaVeh, txtMarcaVeh, txtModeloVeh, txtAnioVeh, txtColorVeh);
    }
    
    private void cancelarMecanico() {
        formularioMecanico.setVisible(false);
        tableMecanicos.clearSelection();
        limpiar(txtNombreMec, txtEspecialidadMec, txtTelefonoMec);
    }
    
    private void cancelarOrden() {
        formularioOrden.setVisible(false);
        tableOrdenes.clearSelection();
        limpiar(txtIdVehiculoOrd, txtIdMecanicoOrd, txtDescripcionOrd);
    }
    
    private void cancelarRepuesto() {
        formularioRepuesto.setVisible(false);
        tableRepuestos.clearSelection();
        limpiar(txtNombreRep, txtDescripcionRep, txtPrecioRep);
    }
    
    private void cancelarUtilizado() {
        formularioUtilizado.setVisible(false);
        tableUtilizados.clearSelection();
        limpiar(txtIdOrdenUti, txtIdRepuestoUti, txtCantidadUti);
    }
    
    private void cancelarAbastecimiento() {
        formularioAbastecimiento.setVisible(false);
        tableAbastecimiento.clearSelection();
        limpiar(txtIdRepuestoAba, txtCantidadAba);
    }
    
    private void cancelarServicio() {
        formularioServicio.setVisible(false);
        tableServicios.clearSelection();
        limpiar(txtDescripcionSer, txtCostoSer);
    }
    
    private void cancelarRealizado() {
        formularioRealizado.setVisible(false);
        tableRealizados.clearSelection();
        limpiar(txtIdOrdenRea, txtIdServicioRea, txtCantidadRea);
    }
    
    private void cargarOrdenes(
         java.util.List<VentaModel> ordenes) {

        DefaultTableModel tabla = (DefaultTableModel) tableOrdenes.getModel();
        tabla.setRowCount(0);
        for (VentaModel o : ordenes) {
            tabla.addRow(new Object[]{
                o.getIdOrden(),
                o.getIdVehiculo(),
                o.getIdMecanico(),
                o.getDescripcion(),
                o.getFechaCreacion(),
                o.getFechaFinal(),
                o.getEstado(),
                o.getTotal()
            });
        }
    }
    
    private void cargarRepuestos(
        java.util.List<ProductoModel> repuestos) {

        DefaultTableModel tabla = (DefaultTableModel) tableRepuestos.getModel();
        tabla.setRowCount(0);
        for (ProductoModel r : repuestos) {
            tabla.addRow(new Object[]{
                r.getIdRepuesto(),
                r.getNombre(),
                r.getDescripcion(),
                r.getPrecio(),
                r.getExistencia()
            });
        }
    }
    
    private void ejecutarConsulta(RunnableConsulta consulta) {
        new javax.swing.SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                consulta.ejecutar();
                return null;
            }

            @Override
            protected void done() {
                try {
                    get();
                } catch (Exception e) {
                    Throwable causa = e.getCause();
                    String mensaje = causa != null
                            ? causa.getMessage() : e.getMessage();

                    javax.swing.JOptionPane.showMessageDialog(
                            ConsultarRegistro.this,
                            "Error al consultar la API: " + mensaje);
                }
            }
        }.execute();
    }

    @FunctionalInterface
    private interface RunnableConsulta {
        void ejecutar() throws Exception;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFondo = new javax.swing.JPanel();
        btnMinimizar = new swingx.RoundButton();
        panelMenu = new swingx.RoundPanel();
        labelLogo = new swingx.RoundLabel();
        labelIniciales = new swingx.RoundLabel();
        btnRegresar = new swingx.RoundButton();
        btnCliente = new swingx.RoundButton();
        btnVehiculo = new swingx.RoundButton();
        btnMecanico = new swingx.RoundButton();
        btnOrden = new swingx.RoundButton();
        btnRepuesto = new swingx.RoundButton();
        btnUtilizado = new swingx.RoundButton();
        btnAbastecimiento = new swingx.RoundButton();
        btnServicio = new swingx.RoundButton();
        btnRealizado = new swingx.RoundButton();
        panelCardBuscador = new swingx.RoundPanel();
        panelBuscaCliente = new swingx.RoundPanel();
        labelTituloCli = new swingx.RoundLabel();
        panelBuscaVehiculo = new swingx.RoundPanel();
        labelTituloVeh = new swingx.RoundLabel();
        panelBuscaMecanico = new swingx.RoundPanel();
        labelTituloMec = new swingx.RoundLabel();
        panelBuscaOrden = new swingx.RoundPanel();
        labelTituloOrdEst1 = new swingx.RoundLabel();
        panelBuscaOrdenEst = new swingx.RoundPanel();
        labelTituloOrdEst = new swingx.RoundLabel();
        comboBuscarEstado = new javax.swing.JComboBox<>();
        btnExportarOrdEst = new swingx.RoundButton();
        panelBuscaOrdenPla = new swingx.RoundPanel();
        labelTituloOrdPla = new swingx.RoundLabel();
        txtBuscarPlaca = new javax.swing.JTextField();
        btnBuscarPlaca = new swingx.RoundButton();
        btnExportarOrdPla = new swingx.RoundButton();
        panelBuscaOrdenMec = new swingx.RoundPanel();
        labelTituloOrdMec = new swingx.RoundLabel();
        txtBuscarMecanico = new javax.swing.JTextField();
        btnBuscarMecanico = new swingx.RoundButton();
        btnExportarOrdMec = new swingx.RoundButton();
        panelBuscaOrdenIng = new swingx.RoundPanel();
        labelTituloOrdMec1 = new swingx.RoundLabel();
        txtFechaFinal = new javax.swing.JTextField();
        btnBuscarFecha = new swingx.RoundButton();
        btnExportarOrdIng = new swingx.RoundButton();
        txtFechaInicio = new javax.swing.JTextField();
        labelNombreRep1 = new swingx.RoundLabel();
        labelNombreRep2 = new swingx.RoundLabel();
        panelBuscaRepuesto = new swingx.RoundPanel();
        labelTituloRep = new swingx.RoundLabel();
        comboFiltroRepuesto = new javax.swing.JComboBox<>();
        btnImportarCSV = new swingx.RoundButton();
        btnExportarRep = new swingx.RoundButton();
        panelBuscaUtilizado = new swingx.RoundPanel();
        labelTituloUti = new swingx.RoundLabel();
        panelBuscaAbastecimiento = new swingx.RoundPanel();
        labelTituloAba = new swingx.RoundLabel();
        panelBuscaServicio = new swingx.RoundPanel();
        labelTituloSer = new swingx.RoundLabel();
        panelBuscaRealizado = new swingx.RoundPanel();
        labelTituloRea = new swingx.RoundLabel();
        panelCardTabla = new swingx.RoundPanel();
        panelCliente = new swingx.RoundPanel();
        scrollClientes = new javax.swing.JScrollPane();
        tableClientes = new javax.swing.JTable();
        formularioCliente = new swingx.RoundPanel();
        labelNombreCli = new swingx.RoundLabel();
        txtNombreCli = new javax.swing.JTextField();
        labelDireccionCli = new swingx.RoundLabel();
        txtDireccionCli = new javax.swing.JTextField();
        labelTelefonoCli = new swingx.RoundLabel();
        txtTelefonoCli = new javax.swing.JTextField();
        btnLimpiarCli = new swingx.RoundButton();
        btnGuardarCli = new swingx.RoundButton();
        labelCliente = new swingx.RoundLabel();
        btnEliminarCli = new swingx.RoundButton();
        panelVehiculo = new swingx.RoundPanel();
        scrollVehiculos = new javax.swing.JScrollPane();
        tableVehiculos = new javax.swing.JTable();
        formularioVehiculo = new swingx.RoundPanel();
        labelIdClienteVeh = new swingx.RoundLabel();
        txtIdClienteVeh = new javax.swing.JTextField();
        labelMarcaVeh = new swingx.RoundLabel();
        txtMarcaVeh = new javax.swing.JTextField();
        labelAnioVeh = new swingx.RoundLabel();
        txtAnioVeh = new javax.swing.JTextField();
        labelPlacaVeh = new swingx.RoundLabel();
        txtPlacaVeh = new javax.swing.JTextField();
        labelModelo = new swingx.RoundLabel();
        txtModeloVeh = new javax.swing.JTextField();
        labelColorVeh = new swingx.RoundLabel();
        txtColorVeh = new javax.swing.JTextField();
        labelVehiculo = new swingx.RoundLabel();
        btnGuardarVeh = new swingx.RoundButton();
        btnLimpiarVeh = new swingx.RoundButton();
        btnEliminarVeh = new swingx.RoundButton();
        panelMecanico = new swingx.RoundPanel();
        formularioMecanico = new swingx.RoundPanel();
        labelNombreMec = new swingx.RoundLabel();
        txtNombreMec = new javax.swing.JTextField();
        labelEspecialidadMec = new swingx.RoundLabel();
        txtEspecialidadMec = new javax.swing.JTextField();
        labelTelefonoMec = new swingx.RoundLabel();
        txtTelefonoMec = new javax.swing.JTextField();
        labelMecanico = new swingx.RoundLabel();
        btnLimpiarMec = new swingx.RoundButton();
        btnGuardarMec = new swingx.RoundButton();
        btnEliminarMec = new swingx.RoundButton();
        scrollMecanicos = new javax.swing.JScrollPane();
        tableMecanicos = new javax.swing.JTable();
        panelOrden = new swingx.RoundPanel();
        formularioOrden = new swingx.RoundPanel();
        labelOrden = new swingx.RoundLabel();
        labelIdVehiculoOrd = new swingx.RoundLabel();
        txtIdVehiculoOrd = new javax.swing.JTextField();
        labelIdMecanicoOrd = new swingx.RoundLabel();
        txtIdMecanicoOrd = new javax.swing.JTextField();
        labelDescripcionOrd = new swingx.RoundLabel();
        txtDescripcionOrd = new javax.swing.JTextField();
        labelEstado = new swingx.RoundLabel();
        comboEstado = new javax.swing.JComboBox<>();
        btnLimpiarOrd = new swingx.RoundButton();
        btnGuardarOrd = new swingx.RoundButton();
        scrollOrdenes = new javax.swing.JScrollPane();
        tableOrdenes = new javax.swing.JTable();
        comboFiltroOrden = new javax.swing.JComboBox<>();
        panelRepuesto = new swingx.RoundPanel();
        scrollRepuestos = new javax.swing.JScrollPane();
        tableRepuestos = new javax.swing.JTable();
        formularioRepuesto = new swingx.RoundPanel();
        labelNombreRep = new swingx.RoundLabel();
        txtNombreRep = new javax.swing.JTextField();
        labelDescripcionRep = new swingx.RoundLabel();
        txtDescripcionRep = new javax.swing.JTextField();
        labelPrecioRep = new swingx.RoundLabel();
        txtPrecioRep = new javax.swing.JTextField();
        labelRepuesto = new swingx.RoundLabel();
        btnLimpiarRep = new swingx.RoundButton();
        btnGuardarRep = new swingx.RoundButton();
        btnEliminarRep = new swingx.RoundButton();
        panelUtilizado = new swingx.RoundPanel();
        formularioUtilizado = new swingx.RoundPanel();
        labelIdOrdenUti = new swingx.RoundLabel();
        txtIdOrdenUti = new javax.swing.JTextField();
        labelIdRepuestoUti = new swingx.RoundLabel();
        txtIdRepuestoUti = new javax.swing.JTextField();
        labelCantidadUti = new swingx.RoundLabel();
        txtCantidadUti = new javax.swing.JTextField();
        labelUtilizado = new swingx.RoundLabel();
        btnGuardarUti = new swingx.RoundButton();
        btnLimpiarUti = new swingx.RoundButton();
        btnEliminarUti = new swingx.RoundButton();
        scrollUtilizados = new javax.swing.JScrollPane();
        tableUtilizados = new javax.swing.JTable();
        panelAbastecimiento = new swingx.RoundPanel();
        scrollAbastecimiento = new javax.swing.JScrollPane();
        tableAbastecimiento = new javax.swing.JTable();
        formularioAbastecimiento = new swingx.RoundPanel();
        labelIdRepuestoAba = new swingx.RoundLabel();
        txtIdRepuestoAba = new javax.swing.JTextField();
        labelCantidadAba = new swingx.RoundLabel();
        txtCantidadAba = new javax.swing.JTextField();
        labelAbastecimiento = new swingx.RoundLabel();
        btnGuardarAba = new swingx.RoundButton();
        btnLimpiarAba = new swingx.RoundButton();
        btnEliminarAba = new swingx.RoundButton();
        panelServicio = new swingx.RoundPanel();
        scrollServicios = new javax.swing.JScrollPane();
        tableServicios = new javax.swing.JTable();
        formularioServicio = new swingx.RoundPanel();
        labelDescripcionSer = new swingx.RoundLabel();
        txtDescripcionSer = new javax.swing.JTextField();
        labelCostoSer = new swingx.RoundLabel();
        txtCostoSer = new javax.swing.JTextField();
        labelServicio = new swingx.RoundLabel();
        btnLimpiarSer = new swingx.RoundButton();
        btnGuardarSer = new swingx.RoundButton();
        btnEliminarSer = new swingx.RoundButton();
        panelRealizado = new swingx.RoundPanel();
        formularioRealizado = new swingx.RoundPanel();
        labelIdOrdenRea = new swingx.RoundLabel();
        txtIdOrdenRea = new javax.swing.JTextField();
        labelIdServicioRea = new swingx.RoundLabel();
        txtIdServicioRea = new javax.swing.JTextField();
        labelCantidadRea = new swingx.RoundLabel();
        txtCantidadRea = new javax.swing.JTextField();
        labelRealizado = new swingx.RoundLabel();
        btnGuardarRea = new swingx.RoundButton();
        btnEliminarRea = new swingx.RoundButton();
        btnLimpiarRea = new swingx.RoundButton();
        scrollRealizados = new javax.swing.JScrollPane();
        tableRealizados = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);

        panelFondo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelFondoMouseDragged(evt);
            }
        });
        panelFondo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelFondoMousePressed(evt);
            }
        });

        btnMinimizar.setBackground(new java.awt.Color(237, 237, 237));
        btnMinimizar.setForeground(new java.awt.Color(51, 51, 51));
        btnMinimizar.setText("−");
        btnMinimizar.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnMinimizar.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btnMinimizar.setMaximumSize(new java.awt.Dimension(40, 40));
        btnMinimizar.setPreferredSize(new java.awt.Dimension(24, 24));
        btnMinimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMinimizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMinimizarMouseExited(evt);
            }
        });
        btnMinimizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinimizarActionPerformed(evt);
            }
        });

        panelMenu.setBackground(new java.awt.Color(8, 29, 86));
        panelMenu.setPreferredSize(new java.awt.Dimension(200, 650));
        panelMenu.setRoundBottomRight(10);
        panelMenu.setRoundTopRight(10);

        labelLogo.setBackground(new java.awt.Color(8, 29, 86));

        labelIniciales.setBackground(new java.awt.Color(0, 0, 153));
        labelIniciales.setForeground(new java.awt.Color(255, 255, 255));
        labelIniciales.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelIniciales.setText("S. M.");
        labelIniciales.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        labelIniciales.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelIniciales.setRoundBottomRight(20);
        labelIniciales.setRoundTopLeft(20);
        labelIniciales.setRoundTopRight(20);

        btnRegresar.setBackground(new java.awt.Color(8, 29, 86));
        btnRegresar.setForeground(new java.awt.Color(255, 204, 0));
        btnRegresar.setText("   REGRESAR");
        btnRegresar.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnRegresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegresarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegresarMouseExited(evt);
            }
        });
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        btnCliente.setBackground(new java.awt.Color(0, 0, 51));
        btnCliente.setForeground(new java.awt.Color(255, 204, 0));
        btnCliente.setText("   CLIENTES");
        btnCliente.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnCliente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnClienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnClienteMouseExited(evt);
            }
        });
        btnCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteActionPerformed(evt);
            }
        });

        btnVehiculo.setBackground(new java.awt.Color(8, 29, 86));
        btnVehiculo.setForeground(new java.awt.Color(255, 204, 0));
        btnVehiculo.setText("   PEDIDOS");
        btnVehiculo.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnVehiculo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnVehiculo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVehiculoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVehiculoMouseExited(evt);
            }
        });
        btnVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVehiculoActionPerformed(evt);
            }
        });

        btnMecanico.setBackground(new java.awt.Color(8, 29, 86));
        btnMecanico.setForeground(new java.awt.Color(255, 204, 0));
        btnMecanico.setText("   EMPLEADOS");
        btnMecanico.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnMecanico.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMecanico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMecanicoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMecanicoMouseExited(evt);
            }
        });
        btnMecanico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMecanicoActionPerformed(evt);
            }
        });

        btnOrden.setBackground(new java.awt.Color(8, 29, 86));
        btnOrden.setForeground(new java.awt.Color(255, 204, 0));
        btnOrden.setText("   VENTAS");
        btnOrden.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnOrden.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnOrden.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnOrdenMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnOrdenMouseExited(evt);
            }
        });
        btnOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenActionPerformed(evt);
            }
        });

        btnRepuesto.setBackground(new java.awt.Color(8, 29, 86));
        btnRepuesto.setForeground(new java.awt.Color(255, 204, 0));
        btnRepuesto.setText("   PRODUCTOS");
        btnRepuesto.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnRepuesto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnRepuesto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRepuestoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRepuestoMouseExited(evt);
            }
        });
        btnRepuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRepuestoActionPerformed(evt);
            }
        });

        btnUtilizado.setBackground(new java.awt.Color(8, 29, 86));
        btnUtilizado.setForeground(new java.awt.Color(255, 204, 0));
        btnUtilizado.setText("| VENDIDOS");
        btnUtilizado.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnUtilizado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnUtilizado.setRadius(40);
        btnUtilizado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnUtilizadoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnUtilizadoMouseExited(evt);
            }
        });
        btnUtilizado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUtilizadoActionPerformed(evt);
            }
        });

        btnAbastecimiento.setBackground(new java.awt.Color(8, 29, 86));
        btnAbastecimiento.setForeground(new java.awt.Color(255, 204, 0));
        btnAbastecimiento.setText("| ABASTECIMIENTO");
        btnAbastecimiento.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnAbastecimiento.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAbastecimiento.setRadius(40);
        btnAbastecimiento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAbastecimientoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAbastecimientoMouseExited(evt);
            }
        });
        btnAbastecimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbastecimientoActionPerformed(evt);
            }
        });

        btnServicio.setBackground(new java.awt.Color(8, 29, 86));
        btnServicio.setForeground(new java.awt.Color(255, 204, 0));
        btnServicio.setText("   SERVICIOS");
        btnServicio.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnServicio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnServicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnServicioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnServicioMouseExited(evt);
            }
        });
        btnServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnServicioActionPerformed(evt);
            }
        });

        btnRealizado.setBackground(new java.awt.Color(8, 29, 86));
        btnRealizado.setForeground(new java.awt.Color(255, 204, 0));
        btnRealizado.setText("| REALIZADOS");
        btnRealizado.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnRealizado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnRealizado.setRadius(40);
        btnRealizado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRealizadoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRealizadoMouseExited(evt);
            }
        });
        btnRealizado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRealizadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnServicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRegresar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVehiculo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRepuesto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnOrden, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMecanico, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenuLayout.createSequentialGroup()
                        .addGap(0, 29, Short.MAX_VALUE)
                        .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRealizado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAbastecimiento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUtilizado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(labelIniciales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelIniciales, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMecanico, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUtilizado, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAbastecimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRealizado, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelCardBuscador.setBackground(new java.awt.Color(255, 255, 255));
        panelCardBuscador.setRoundBottomLeft(20);
        panelCardBuscador.setRoundBottomRight(20);
        panelCardBuscador.setRoundTopLeft(20);
        panelCardBuscador.setRoundTopRight(20);
        panelCardBuscador.setShadow(true);
        panelCardBuscador.setShadowSize(2);
        panelCardBuscador.setLayout(new java.awt.CardLayout());

        panelBuscaCliente.setBackground(new java.awt.Color(255, 255, 255));
        panelBuscaCliente.setRoundBottomLeft(20);
        panelBuscaCliente.setRoundBottomRight(20);
        panelBuscaCliente.setRoundTopLeft(20);
        panelBuscaCliente.setRoundTopRight(20);
        panelBuscaCliente.setShadow(true);
        panelBuscaCliente.setShadowSize(2);

        labelTituloCli.setBackground(new java.awt.Color(255, 204, 0));
        labelTituloCli.setForeground(new java.awt.Color(255, 255, 255));
        labelTituloCli.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTituloCli.setText("Registros de Clientes   ");
        labelTituloCli.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        labelTituloCli.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelTituloCli.setRoundBottomRight(30);
        labelTituloCli.setRoundTopLeft(30);
        labelTituloCli.setRoundTopRight(30);

        javax.swing.GroupLayout panelBuscaClienteLayout = new javax.swing.GroupLayout(panelBuscaCliente);
        panelBuscaCliente.setLayout(panelBuscaClienteLayout);
        panelBuscaClienteLayout.setHorizontalGroup(
            panelBuscaClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscaClienteLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(labelTituloCli, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(601, Short.MAX_VALUE))
        );
        panelBuscaClienteLayout.setVerticalGroup(
            panelBuscaClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBuscaClienteLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(labelTituloCli, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        panelCardBuscador.add(panelBuscaCliente, "card1");

        panelBuscaVehiculo.setBackground(new java.awt.Color(255, 255, 255));
        panelBuscaVehiculo.setRoundBottomLeft(20);
        panelBuscaVehiculo.setRoundBottomRight(20);
        panelBuscaVehiculo.setRoundTopLeft(20);
        panelBuscaVehiculo.setRoundTopRight(20);
        panelBuscaVehiculo.setShadow(true);
        panelBuscaVehiculo.setShadowSize(2);

        labelTituloVeh.setBackground(new java.awt.Color(255, 204, 0));
        labelTituloVeh.setForeground(new java.awt.Color(255, 255, 255));
        labelTituloVeh.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTituloVeh.setText("Registros de Pedidos   ");
        labelTituloVeh.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        labelTituloVeh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelTituloVeh.setRoundBottomRight(30);
        labelTituloVeh.setRoundTopLeft(30);
        labelTituloVeh.setRoundTopRight(30);

        javax.swing.GroupLayout panelBuscaVehiculoLayout = new javax.swing.GroupLayout(panelBuscaVehiculo);
        panelBuscaVehiculo.setLayout(panelBuscaVehiculoLayout);
        panelBuscaVehiculoLayout.setHorizontalGroup(
            panelBuscaVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscaVehiculoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(labelTituloVeh, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(586, Short.MAX_VALUE))
        );
        panelBuscaVehiculoLayout.setVerticalGroup(
            panelBuscaVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBuscaVehiculoLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(labelTituloVeh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        panelCardBuscador.add(panelBuscaVehiculo, "card2");

        panelBuscaMecanico.setBackground(new java.awt.Color(255, 255, 255));
        panelBuscaMecanico.setRoundBottomLeft(20);
        panelBuscaMecanico.setRoundBottomRight(20);
        panelBuscaMecanico.setRoundTopLeft(20);
        panelBuscaMecanico.setRoundTopRight(20);
        panelBuscaMecanico.setShadow(true);
        panelBuscaMecanico.setShadowSize(2);

        labelTituloMec.setBackground(new java.awt.Color(255, 204, 0));
        labelTituloMec.setForeground(new java.awt.Color(255, 255, 255));
        labelTituloMec.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTituloMec.setText("Registros de Empleados   ");
        labelTituloMec.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        labelTituloMec.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelTituloMec.setRoundBottomRight(30);
        labelTituloMec.setRoundTopLeft(30);
        labelTituloMec.setRoundTopRight(30);

        javax.swing.GroupLayout panelBuscaMecanicoLayout = new javax.swing.GroupLayout(panelBuscaMecanico);
        panelBuscaMecanico.setLayout(panelBuscaMecanicoLayout);
        panelBuscaMecanicoLayout.setHorizontalGroup(
            panelBuscaMecanicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscaMecanicoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(labelTituloMec, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(581, Short.MAX_VALUE))
        );
        panelBuscaMecanicoLayout.setVerticalGroup(
            panelBuscaMecanicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBuscaMecanicoLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(labelTituloMec, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        panelCardBuscador.add(panelBuscaMecanico, "card3");

        panelBuscaOrden.setBackground(new java.awt.Color(255, 255, 255));
        panelBuscaOrden.setRoundBottomLeft(20);
        panelBuscaOrden.setRoundBottomRight(20);
        panelBuscaOrden.setRoundTopLeft(20);
        panelBuscaOrden.setRoundTopRight(20);
        panelBuscaOrden.setShadow(true);
        panelBuscaOrden.setShadowSize(2);

        labelTituloOrdEst1.setBackground(new java.awt.Color(255, 204, 0));
        labelTituloOrdEst1.setForeground(new java.awt.Color(255, 255, 255));
        labelTituloOrdEst1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTituloOrdEst1.setText("Registros de Ventas   ");
        labelTituloOrdEst1.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        labelTituloOrdEst1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelTituloOrdEst1.setRoundBottomRight(30);
        labelTituloOrdEst1.setRoundTopLeft(30);
        labelTituloOrdEst1.setRoundTopRight(30);

        javax.swing.GroupLayout panelBuscaOrdenLayout = new javax.swing.GroupLayout(panelBuscaOrden);
        panelBuscaOrden.setLayout(panelBuscaOrdenLayout);
        panelBuscaOrdenLayout.setHorizontalGroup(
            panelBuscaOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscaOrdenLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(labelTituloOrdEst1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(510, Short.MAX_VALUE))
        );
        panelBuscaOrdenLayout.setVerticalGroup(
            panelBuscaOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBuscaOrdenLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(labelTituloOrdEst1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        panelCardBuscador.add(panelBuscaOrden, "card4");

        panelBuscaOrdenEst.setBackground(new java.awt.Color(255, 255, 255));
        panelBuscaOrdenEst.setRoundBottomLeft(20);
        panelBuscaOrdenEst.setRoundBottomRight(20);
        panelBuscaOrdenEst.setRoundTopLeft(20);
        panelBuscaOrdenEst.setRoundTopRight(20);
        panelBuscaOrdenEst.setShadow(true);
        panelBuscaOrdenEst.setShadowSize(2);

        labelTituloOrdEst.setBackground(new java.awt.Color(255, 204, 0));
        labelTituloOrdEst.setForeground(new java.awt.Color(255, 255, 255));
        labelTituloOrdEst.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTituloOrdEst.setText("Registros de Ventas   ");
        labelTituloOrdEst.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        labelTituloOrdEst.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelTituloOrdEst.setRoundBottomRight(30);
        labelTituloOrdEst.setRoundTopLeft(30);
        labelTituloOrdEst.setRoundTopRight(30);

        comboBuscarEstado.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        comboBuscarEstado.setForeground(new java.awt.Color(51, 51, 51));
        comboBuscarEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PENDIENTES", "EN PROCESO", "FINALIZADAS", "CANCELADAS" }));
        comboBuscarEstado.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 229, 229), 3, true));
        comboBuscarEstado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        comboBuscarEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBuscarEstadoActionPerformed(evt);
            }
        });

        btnExportarOrdEst.setBackground(new java.awt.Color(0, 0, 153));
        btnExportarOrdEst.setForeground(new java.awt.Color(255, 255, 255));
        btnExportarOrdEst.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnExportarOrdEst.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExportarOrdEstMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExportarOrdEstMouseExited(evt);
            }
        });
        btnExportarOrdEst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarOrdEstActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBuscaOrdenEstLayout = new javax.swing.GroupLayout(panelBuscaOrdenEst);
        panelBuscaOrdenEst.setLayout(panelBuscaOrdenEstLayout);
        panelBuscaOrdenEstLayout.setHorizontalGroup(
            panelBuscaOrdenEstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscaOrdenEstLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(labelTituloOrdEst, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 179, Short.MAX_VALUE)
                .addComponent(comboBuscarEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExportarOrdEst, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        panelBuscaOrdenEstLayout.setVerticalGroup(
            panelBuscaOrdenEstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBuscaOrdenEstLayout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addGroup(panelBuscaOrdenEstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExportarOrdEst, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBuscaOrdenEstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(labelTituloOrdEst, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboBuscarEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );

        panelCardBuscador.add(panelBuscaOrdenEst, "card4-1");

        panelBuscaOrdenPla.setBackground(new java.awt.Color(255, 255, 255));
        panelBuscaOrdenPla.setRoundBottomLeft(20);
        panelBuscaOrdenPla.setRoundBottomRight(20);
        panelBuscaOrdenPla.setRoundTopLeft(20);
        panelBuscaOrdenPla.setRoundTopRight(20);
        panelBuscaOrdenPla.setShadow(true);
        panelBuscaOrdenPla.setShadowSize(2);

        labelTituloOrdPla.setBackground(new java.awt.Color(255, 204, 0));
        labelTituloOrdPla.setForeground(new java.awt.Color(255, 255, 255));
        labelTituloOrdPla.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTituloOrdPla.setText("Registros de Ventas   ");
        labelTituloOrdPla.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        labelTituloOrdPla.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelTituloOrdPla.setRoundBottomRight(30);
        labelTituloOrdPla.setRoundTopLeft(30);
        labelTituloOrdPla.setRoundTopRight(30);

        txtBuscarPlaca.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtBuscarPlaca.setForeground(new java.awt.Color(51, 51, 51));
        txtBuscarPlaca.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 229, 229), 3, true));

        btnBuscarPlaca.setBackground(new java.awt.Color(0, 0, 153));
        btnBuscarPlaca.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarPlaca.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnBuscarPlaca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBuscarPlacaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBuscarPlacaMouseExited(evt);
            }
        });
        btnBuscarPlaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPlacaActionPerformed(evt);
            }
        });

        btnExportarOrdPla.setBackground(new java.awt.Color(0, 0, 153));
        btnExportarOrdPla.setForeground(new java.awt.Color(255, 255, 255));
        btnExportarOrdPla.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnExportarOrdPla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExportarOrdPlaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExportarOrdPlaMouseExited(evt);
            }
        });
        btnExportarOrdPla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarOrdPlaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBuscaOrdenPlaLayout = new javax.swing.GroupLayout(panelBuscaOrdenPla);
        panelBuscaOrdenPla.setLayout(panelBuscaOrdenPlaLayout);
        panelBuscaOrdenPlaLayout.setHorizontalGroup(
            panelBuscaOrdenPlaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscaOrdenPlaLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(labelTituloOrdPla, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                .addComponent(txtBuscarPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscarPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExportarOrdPla, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        panelBuscaOrdenPlaLayout.setVerticalGroup(
            panelBuscaOrdenPlaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBuscaOrdenPlaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBuscaOrdenPlaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBuscaOrdenPlaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnExportarOrdPla, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscarPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBuscaOrdenPlaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(labelTituloOrdPla, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBuscarPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14))
        );

        panelCardBuscador.add(panelBuscaOrdenPla, "card4-2");

        panelBuscaOrdenMec.setBackground(new java.awt.Color(255, 255, 255));
        panelBuscaOrdenMec.setRoundBottomLeft(20);
        panelBuscaOrdenMec.setRoundBottomRight(20);
        panelBuscaOrdenMec.setRoundTopLeft(20);
        panelBuscaOrdenMec.setRoundTopRight(20);
        panelBuscaOrdenMec.setShadow(true);
        panelBuscaOrdenMec.setShadowSize(2);

        labelTituloOrdMec.setBackground(new java.awt.Color(255, 204, 0));
        labelTituloOrdMec.setForeground(new java.awt.Color(255, 255, 255));
        labelTituloOrdMec.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTituloOrdMec.setText("Registros de Ventas   ");
        labelTituloOrdMec.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        labelTituloOrdMec.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelTituloOrdMec.setRoundBottomRight(30);
        labelTituloOrdMec.setRoundTopLeft(30);
        labelTituloOrdMec.setRoundTopRight(30);

        txtBuscarMecanico.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtBuscarMecanico.setForeground(new java.awt.Color(51, 51, 51));
        txtBuscarMecanico.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 229, 229), 3, true));

        btnBuscarMecanico.setBackground(new java.awt.Color(0, 0, 153));
        btnBuscarMecanico.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarMecanico.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnBuscarMecanico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBuscarMecanicoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBuscarMecanicoMouseExited(evt);
            }
        });
        btnBuscarMecanico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarMecanicoActionPerformed(evt);
            }
        });

        btnExportarOrdMec.setBackground(new java.awt.Color(0, 0, 153));
        btnExportarOrdMec.setForeground(new java.awt.Color(255, 255, 255));
        btnExportarOrdMec.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnExportarOrdMec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExportarOrdMecMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExportarOrdMecMouseExited(evt);
            }
        });
        btnExportarOrdMec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarOrdMecActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBuscaOrdenMecLayout = new javax.swing.GroupLayout(panelBuscaOrdenMec);
        panelBuscaOrdenMec.setLayout(panelBuscaOrdenMecLayout);
        panelBuscaOrdenMecLayout.setHorizontalGroup(
            panelBuscaOrdenMecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscaOrdenMecLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(labelTituloOrdMec, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                .addComponent(txtBuscarMecanico, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscarMecanico, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExportarOrdMec, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        panelBuscaOrdenMecLayout.setVerticalGroup(
            panelBuscaOrdenMecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBuscaOrdenMecLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBuscaOrdenMecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBuscaOrdenMecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnExportarOrdMec, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscarMecanico, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBuscaOrdenMecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(labelTituloOrdMec, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBuscarMecanico, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14))
        );

        panelCardBuscador.add(panelBuscaOrdenMec, "card4-3");

        panelBuscaOrdenIng.setBackground(new java.awt.Color(255, 255, 255));
        panelBuscaOrdenIng.setRoundBottomLeft(20);
        panelBuscaOrdenIng.setRoundBottomRight(20);
        panelBuscaOrdenIng.setRoundTopLeft(20);
        panelBuscaOrdenIng.setRoundTopRight(20);
        panelBuscaOrdenIng.setShadow(true);
        panelBuscaOrdenIng.setShadowSize(2);

        labelTituloOrdMec1.setBackground(new java.awt.Color(255, 204, 0));
        labelTituloOrdMec1.setForeground(new java.awt.Color(255, 255, 255));
        labelTituloOrdMec1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTituloOrdMec1.setText("Registros de Ventas   ");
        labelTituloOrdMec1.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        labelTituloOrdMec1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelTituloOrdMec1.setRoundBottomRight(30);
        labelTituloOrdMec1.setRoundTopLeft(30);
        labelTituloOrdMec1.setRoundTopRight(30);

        txtFechaFinal.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtFechaFinal.setForeground(new java.awt.Color(51, 51, 51));
        txtFechaFinal.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 229, 229), 3, true));

        btnBuscarFecha.setBackground(new java.awt.Color(0, 0, 153));
        btnBuscarFecha.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarFecha.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnBuscarFecha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBuscarFechaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBuscarFechaMouseExited(evt);
            }
        });
        btnBuscarFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarFechaActionPerformed(evt);
            }
        });

        btnExportarOrdIng.setBackground(new java.awt.Color(0, 0, 153));
        btnExportarOrdIng.setForeground(new java.awt.Color(255, 255, 255));
        btnExportarOrdIng.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnExportarOrdIng.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExportarOrdIngMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExportarOrdIngMouseExited(evt);
            }
        });
        btnExportarOrdIng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarOrdIngActionPerformed(evt);
            }
        });

        txtFechaInicio.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtFechaInicio.setForeground(new java.awt.Color(51, 51, 51));
        txtFechaInicio.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 229, 229), 3, true));

        labelNombreRep1.setBackground(new java.awt.Color(255, 255, 255));
        labelNombreRep1.setForeground(new java.awt.Color(51, 51, 51));
        labelNombreRep1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelNombreRep1.setText("DE:");
        labelNombreRep1.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        labelNombreRep1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        labelNombreRep2.setBackground(new java.awt.Color(255, 255, 255));
        labelNombreRep2.setForeground(new java.awt.Color(51, 51, 51));
        labelNombreRep2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelNombreRep2.setText("HASTA:");
        labelNombreRep2.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        labelNombreRep2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout panelBuscaOrdenIngLayout = new javax.swing.GroupLayout(panelBuscaOrdenIng);
        panelBuscaOrdenIng.setLayout(panelBuscaOrdenIngLayout);
        panelBuscaOrdenIngLayout.setHorizontalGroup(
            panelBuscaOrdenIngLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscaOrdenIngLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(labelTituloOrdMec1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(labelNombreRep1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelNombreRep2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscarFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExportarOrdIng, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        panelBuscaOrdenIngLayout.setVerticalGroup(
            panelBuscaOrdenIngLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBuscaOrdenIngLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBuscaOrdenIngLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBuscaOrdenIngLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnExportarOrdIng, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscarFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBuscaOrdenIngLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(labelTituloOrdMec1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14))
            .addGroup(panelBuscaOrdenIngLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBuscaOrdenIngLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBuscaOrdenIngLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelNombreRep2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBuscaOrdenIngLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelNombreRep1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelCardBuscador.add(panelBuscaOrdenIng, "card4-5");

        panelBuscaRepuesto.setBackground(new java.awt.Color(255, 255, 255));
        panelBuscaRepuesto.setRoundBottomLeft(20);
        panelBuscaRepuesto.setRoundBottomRight(20);
        panelBuscaRepuesto.setRoundTopLeft(20);
        panelBuscaRepuesto.setRoundTopRight(20);
        panelBuscaRepuesto.setShadow(true);
        panelBuscaRepuesto.setShadowSize(2);

        labelTituloRep.setBackground(new java.awt.Color(255, 204, 0));
        labelTituloRep.setForeground(new java.awt.Color(255, 255, 255));
        labelTituloRep.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTituloRep.setText("Registros de Productos   ");
        labelTituloRep.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        labelTituloRep.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelTituloRep.setRoundBottomRight(30);
        labelTituloRep.setRoundTopLeft(30);
        labelTituloRep.setRoundTopRight(30);

        comboFiltroRepuesto.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        comboFiltroRepuesto.setForeground(new java.awt.Color(51, 51, 51));
        comboFiltroRepuesto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos los productos", "Productos con bajo inventario" }));
        comboFiltroRepuesto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 229, 229), 3, true));
        comboFiltroRepuesto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        comboFiltroRepuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFiltroRepuestoActionPerformed(evt);
            }
        });

        btnImportarCSV.setBackground(new java.awt.Color(0, 0, 153));
        btnImportarCSV.setForeground(new java.awt.Color(255, 255, 255));
        btnImportarCSV.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnImportarCSV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnImportarCSVMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnImportarCSVMouseExited(evt);
            }
        });
        btnImportarCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportarCSVActionPerformed(evt);
            }
        });

        btnExportarRep.setBackground(new java.awt.Color(0, 0, 153));
        btnExportarRep.setForeground(new java.awt.Color(255, 255, 255));
        btnExportarRep.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnExportarRep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExportarRepMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExportarRepMouseExited(evt);
            }
        });
        btnExportarRep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarRepActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBuscaRepuestoLayout = new javax.swing.GroupLayout(panelBuscaRepuesto);
        panelBuscaRepuesto.setLayout(panelBuscaRepuestoLayout);
        panelBuscaRepuestoLayout.setHorizontalGroup(
            panelBuscaRepuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscaRepuestoLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(labelTituloRep, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 199, Short.MAX_VALUE)
                .addComponent(comboFiltroRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnImportarCSV, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExportarRep, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        panelBuscaRepuestoLayout.setVerticalGroup(
            panelBuscaRepuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBuscaRepuestoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBuscaRepuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBuscaRepuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnExportarRep, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnImportarCSV, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBuscaRepuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(labelTituloRep, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboFiltroRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );

        panelCardBuscador.add(panelBuscaRepuesto, "card5");

        panelBuscaUtilizado.setBackground(new java.awt.Color(255, 255, 255));
        panelBuscaUtilizado.setRoundBottomLeft(20);
        panelBuscaUtilizado.setRoundBottomRight(20);
        panelBuscaUtilizado.setRoundTopLeft(20);
        panelBuscaUtilizado.setRoundTopRight(20);
        panelBuscaUtilizado.setShadow(true);
        panelBuscaUtilizado.setShadowSize(2);

        labelTituloUti.setBackground(new java.awt.Color(255, 204, 0));
        labelTituloUti.setForeground(new java.awt.Color(255, 255, 255));
        labelTituloUti.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTituloUti.setText("Registros de Productos Vendidos   ");
        labelTituloUti.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        labelTituloUti.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelTituloUti.setRoundBottomRight(30);
        labelTituloUti.setRoundTopLeft(30);
        labelTituloUti.setRoundTopRight(30);

        javax.swing.GroupLayout panelBuscaUtilizadoLayout = new javax.swing.GroupLayout(panelBuscaUtilizado);
        panelBuscaUtilizado.setLayout(panelBuscaUtilizadoLayout);
        panelBuscaUtilizadoLayout.setHorizontalGroup(
            panelBuscaUtilizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscaUtilizadoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(labelTituloUti, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(501, Short.MAX_VALUE))
        );
        panelBuscaUtilizadoLayout.setVerticalGroup(
            panelBuscaUtilizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBuscaUtilizadoLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(labelTituloUti, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        panelCardBuscador.add(panelBuscaUtilizado, "card6");

        panelBuscaAbastecimiento.setBackground(new java.awt.Color(255, 255, 255));
        panelBuscaAbastecimiento.setRoundBottomLeft(20);
        panelBuscaAbastecimiento.setRoundBottomRight(20);
        panelBuscaAbastecimiento.setRoundTopLeft(20);
        panelBuscaAbastecimiento.setRoundTopRight(20);
        panelBuscaAbastecimiento.setShadow(true);
        panelBuscaAbastecimiento.setShadowSize(2);

        labelTituloAba.setBackground(new java.awt.Color(255, 204, 0));
        labelTituloAba.setForeground(new java.awt.Color(255, 255, 255));
        labelTituloAba.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTituloAba.setText("Registros del Abastecimiento de Productos   ");
        labelTituloAba.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        labelTituloAba.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelTituloAba.setRoundBottomRight(30);
        labelTituloAba.setRoundTopLeft(30);
        labelTituloAba.setRoundTopRight(30);

        javax.swing.GroupLayout panelBuscaAbastecimientoLayout = new javax.swing.GroupLayout(panelBuscaAbastecimiento);
        panelBuscaAbastecimiento.setLayout(panelBuscaAbastecimientoLayout);
        panelBuscaAbastecimientoLayout.setHorizontalGroup(
            panelBuscaAbastecimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscaAbastecimientoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(labelTituloAba, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(433, Short.MAX_VALUE))
        );
        panelBuscaAbastecimientoLayout.setVerticalGroup(
            panelBuscaAbastecimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBuscaAbastecimientoLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(labelTituloAba, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        panelCardBuscador.add(panelBuscaAbastecimiento, "card7");

        panelBuscaServicio.setBackground(new java.awt.Color(255, 255, 255));
        panelBuscaServicio.setRoundBottomLeft(20);
        panelBuscaServicio.setRoundBottomRight(20);
        panelBuscaServicio.setRoundTopLeft(20);
        panelBuscaServicio.setRoundTopRight(20);
        panelBuscaServicio.setShadow(true);
        panelBuscaServicio.setShadowSize(2);

        labelTituloSer.setBackground(new java.awt.Color(255, 204, 0));
        labelTituloSer.setForeground(new java.awt.Color(255, 255, 255));
        labelTituloSer.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTituloSer.setText("Registros de Servicios   ");
        labelTituloSer.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        labelTituloSer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelTituloSer.setRoundBottomRight(30);
        labelTituloSer.setRoundTopLeft(30);
        labelTituloSer.setRoundTopRight(30);

        javax.swing.GroupLayout panelBuscaServicioLayout = new javax.swing.GroupLayout(panelBuscaServicio);
        panelBuscaServicio.setLayout(panelBuscaServicioLayout);
        panelBuscaServicioLayout.setHorizontalGroup(
            panelBuscaServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscaServicioLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(labelTituloSer, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(590, Short.MAX_VALUE))
        );
        panelBuscaServicioLayout.setVerticalGroup(
            panelBuscaServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBuscaServicioLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(labelTituloSer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        panelCardBuscador.add(panelBuscaServicio, "card8");

        panelBuscaRealizado.setBackground(new java.awt.Color(255, 255, 255));
        panelBuscaRealizado.setRoundBottomLeft(20);
        panelBuscaRealizado.setRoundBottomRight(20);
        panelBuscaRealizado.setRoundTopLeft(20);
        panelBuscaRealizado.setRoundTopRight(20);
        panelBuscaRealizado.setShadow(true);
        panelBuscaRealizado.setShadowSize(2);

        labelTituloRea.setBackground(new java.awt.Color(255, 204, 0));
        labelTituloRea.setForeground(new java.awt.Color(255, 255, 255));
        labelTituloRea.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTituloRea.setText("Registros de Servicios Realizados   ");
        labelTituloRea.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        labelTituloRea.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelTituloRea.setRoundBottomRight(30);
        labelTituloRea.setRoundTopLeft(30);
        labelTituloRea.setRoundTopRight(30);

        javax.swing.GroupLayout panelBuscaRealizadoLayout = new javax.swing.GroupLayout(panelBuscaRealizado);
        panelBuscaRealizado.setLayout(panelBuscaRealizadoLayout);
        panelBuscaRealizadoLayout.setHorizontalGroup(
            panelBuscaRealizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscaRealizadoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(labelTituloRea, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(501, Short.MAX_VALUE))
        );
        panelBuscaRealizadoLayout.setVerticalGroup(
            panelBuscaRealizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBuscaRealizadoLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(labelTituloRea, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        panelCardBuscador.add(panelBuscaRealizado, "card9");

        panelCardTabla.setBackground(new java.awt.Color(255, 255, 255));
        panelCardTabla.setRoundBottomLeft(30);
        panelCardTabla.setRoundBottomRight(30);
        panelCardTabla.setRoundTopLeft(30);
        panelCardTabla.setRoundTopRight(30);
        panelCardTabla.setShadow(true);
        panelCardTabla.setShadowSize(3);
        panelCardTabla.setLayout(new java.awt.CardLayout());

        panelCliente.setBackground(new java.awt.Color(255, 255, 255));
        panelCliente.setRoundBottomLeft(30);
        panelCliente.setRoundBottomRight(30);
        panelCliente.setRoundTopLeft(30);
        panelCliente.setRoundTopRight(30);
        panelCliente.setShadow(true);
        panelCliente.setShadowSize(3);

        scrollClientes.setBorder(null);

        tableClientes.setAutoCreateRowSorter(true);
        tableClientes.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        tableClientes.setForeground(new java.awt.Color(51, 51, 51));
        tableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Cliente", "Nombre", "Dirección", "Teléfono"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableClientes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        tableClientes.setAutoscrolls(false);
        tableClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tableClientes.setGridColor(new java.awt.Color(255, 255, 255));
        tableClientes.setMinimumSize(new java.awt.Dimension(820, 500));
        tableClientes.setPreferredSize(new java.awt.Dimension(820, 500));
        tableClientes.setRowHeight(40);
        tableClientes.setSelectionBackground(new java.awt.Color(255, 255, 204));
        tableClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableClientesMouseClicked(evt);
            }
        });
        scrollClientes.setViewportView(tableClientes);

        formularioCliente.setBackground(new java.awt.Color(255, 255, 255));
        formularioCliente.setRoundBottomLeft(20);
        formularioCliente.setRoundBottomRight(20);
        formularioCliente.setRoundTopLeft(20);
        formularioCliente.setRoundTopRight(20);
        formularioCliente.setShadow(true);
        formularioCliente.setShadowSize(3);

        labelNombreCli.setBackground(new java.awt.Color(255, 255, 255));
        labelNombreCli.setForeground(new java.awt.Color(51, 51, 51));
        labelNombreCli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelNombreCli.setText("Nombre");
        labelNombreCli.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        labelNombreCli.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtNombreCli.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtNombreCli.setForeground(new java.awt.Color(51, 51, 51));
        txtNombreCli.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 229, 229), 3, true));

        labelDireccionCli.setBackground(new java.awt.Color(255, 255, 255));
        labelDireccionCli.setForeground(new java.awt.Color(51, 51, 51));
        labelDireccionCli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelDireccionCli.setText("Dirección");
        labelDireccionCli.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        labelDireccionCli.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtDireccionCli.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtDireccionCli.setForeground(new java.awt.Color(51, 51, 51));
        txtDireccionCli.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 229, 229), 3, true));

        labelTelefonoCli.setBackground(new java.awt.Color(255, 255, 255));
        labelTelefonoCli.setForeground(new java.awt.Color(51, 51, 51));
        labelTelefonoCli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelTelefonoCli.setText("Teléfono");
        labelTelefonoCli.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        labelTelefonoCli.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtTelefonoCli.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtTelefonoCli.setForeground(new java.awt.Color(51, 51, 51));
        txtTelefonoCli.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 229, 229), 3, true));

        btnLimpiarCli.setBackground(new java.awt.Color(255, 204, 0));
        btnLimpiarCli.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiarCli.setText("CANCELAR");
        btnLimpiarCli.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnLimpiarCli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLimpiarCliMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLimpiarCliMouseExited(evt);
            }
        });
        btnLimpiarCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarCliActionPerformed(evt);
            }
        });

        btnGuardarCli.setBackground(new java.awt.Color(255, 204, 0));
        btnGuardarCli.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarCli.setText("GUARDAR");
        btnGuardarCli.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnGuardarCli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarCliMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarCliMouseExited(evt);
            }
        });
        btnGuardarCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCliActionPerformed(evt);
            }
        });

        labelCliente.setBackground(new java.awt.Color(0, 0, 153));
        labelCliente.setForeground(new java.awt.Color(255, 255, 255));
        labelCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCliente.setText("ID");
        labelCliente.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        labelCliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelCliente.setRoundTopLeft(20);
        labelCliente.setRoundTopRight(20);

        btnEliminarCli.setBackground(new java.awt.Color(255, 204, 0));
        btnEliminarCli.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarCli.setText("ELIMINAR");
        btnEliminarCli.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnEliminarCli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEliminarCliMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEliminarCliMouseExited(evt);
            }
        });
        btnEliminarCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout formularioClienteLayout = new javax.swing.GroupLayout(formularioCliente);
        formularioCliente.setLayout(formularioClienteLayout);
        formularioClienteLayout.setHorizontalGroup(
            formularioClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formularioClienteLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(formularioClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(formularioClienteLayout.createSequentialGroup()
                        .addComponent(btnLimpiarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(formularioClienteLayout.createSequentialGroup()
                        .addGroup(formularioClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNombreCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreCli, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(formularioClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDireccionCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDireccionCli, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(formularioClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTelefonoCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefonoCli, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(formularioClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        formularioClienteLayout.setVerticalGroup(
            formularioClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formularioClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(formularioClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(formularioClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(formularioClienteLayout.createSequentialGroup()
                            .addComponent(labelNombreCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtNombreCli, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formularioClienteLayout.createSequentialGroup()
                            .addComponent(labelTelefonoCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtTelefonoCli, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(formularioClienteLayout.createSequentialGroup()
                        .addComponent(labelDireccionCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDireccionCli, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(formularioClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelClienteLayout = new javax.swing.GroupLayout(panelCliente);
        panelCliente.setLayout(panelClienteLayout);
        panelClienteLayout.setHorizontalGroup(
            panelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelClienteLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(panelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(formularioCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );
        panelClienteLayout.setVerticalGroup(
            panelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelClienteLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(scrollClientes, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(formularioCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        panelCardTabla.add(panelCliente, "card1");

        panelVehiculo.setBackground(new java.awt.Color(255, 255, 255));
        panelVehiculo.setRoundBottomLeft(30);
        panelVehiculo.setRoundBottomRight(30);
        panelVehiculo.setRoundTopLeft(30);
        panelVehiculo.setRoundTopRight(30);
        panelVehiculo.setShadow(true);
        panelVehiculo.setShadowSize(3);

        scrollVehiculos.setBorder(null);

        tableVehiculos.setAutoCreateRowSorter(true);
        tableVehiculos.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        tableVehiculos.setForeground(new java.awt.Color(51, 51, 51));
        tableVehiculos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Pedido", "ID Cliente", "Código", "Sucursal", "Modalidad", "Año", "Entrega"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableVehiculos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        tableVehiculos.setAutoscrolls(false);
        tableVehiculos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tableVehiculos.setGridColor(new java.awt.Color(255, 255, 255));
        tableVehiculos.setMinimumSize(new java.awt.Dimension(820, 500));
        tableVehiculos.setPreferredSize(new java.awt.Dimension(820, 500));
        tableVehiculos.setRowHeight(40);
        tableVehiculos.setSelectionBackground(new java.awt.Color(255, 255, 204));
        tableVehiculos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableVehiculosMouseClicked(evt);
            }
        });
        scrollVehiculos.setViewportView(tableVehiculos);

        formularioVehiculo.setBackground(new java.awt.Color(255, 255, 255));
        formularioVehiculo.setRoundBottomLeft(20);
        formularioVehiculo.setRoundBottomRight(20);
        formularioVehiculo.setRoundTopLeft(20);
        formularioVehiculo.setRoundTopRight(20);
        formularioVehiculo.setShadow(true);
        formularioVehiculo.setShadowSize(3);

        labelIdClienteVeh.setBackground(new java.awt.Color(255, 255, 255));
        labelIdClienteVeh.setForeground(new java.awt.Color(51, 51, 51));
        labelIdClienteVeh.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelIdClienteVeh.setText("ID de Cliente");
        labelIdClienteVeh.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        labelIdClienteVeh.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtIdClienteVeh.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtIdClienteVeh.setForeground(new java.awt.Color(51, 51, 51));
        txtIdClienteVeh.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 229, 229), 3, true));

        labelMarcaVeh.setBackground(new java.awt.Color(255, 255, 255));
        labelMarcaVeh.setForeground(new java.awt.Color(51, 51, 51));
        labelMarcaVeh.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelMarcaVeh.setText("Sucursal");
        labelMarcaVeh.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        labelMarcaVeh.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtMarcaVeh.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtMarcaVeh.setForeground(new java.awt.Color(51, 51, 51));
        txtMarcaVeh.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 229, 229), 3, true));

        labelAnioVeh.setBackground(new java.awt.Color(255, 255, 255));
        labelAnioVeh.setForeground(new java.awt.Color(51, 51, 51));
        labelAnioVeh.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelAnioVeh.setText("Año");
        labelAnioVeh.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        labelAnioVeh.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtAnioVeh.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtAnioVeh.setForeground(new java.awt.Color(51, 51, 51));
        txtAnioVeh.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 229, 229), 3, true));

        labelPlacaVeh.setBackground(new java.awt.Color(255, 255, 255));
        labelPlacaVeh.setForeground(new java.awt.Color(51, 51, 51));
        labelPlacaVeh.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelPlacaVeh.setText("Código");
        labelPlacaVeh.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        labelPlacaVeh.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtPlacaVeh.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtPlacaVeh.setForeground(new java.awt.Color(51, 51, 51));
        txtPlacaVeh.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 229, 229), 3, true));

        labelModelo.setBackground(new java.awt.Color(255, 255, 255));
        labelModelo.setForeground(new java.awt.Color(51, 51, 51));
        labelModelo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelModelo.setText("Modalidad");
        labelModelo.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        labelModelo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtModeloVeh.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtModeloVeh.setForeground(new java.awt.Color(51, 51, 51));
        txtModeloVeh.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 229, 229), 3, true));

        labelColorVeh.setBackground(new java.awt.Color(255, 255, 255));
        labelColorVeh.setForeground(new java.awt.Color(51, 51, 51));
        labelColorVeh.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelColorVeh.setText("Entrega");
        labelColorVeh.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        labelColorVeh.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtColorVeh.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtColorVeh.setForeground(new java.awt.Color(51, 51, 51));
        txtColorVeh.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 229, 229), 3, true));

        labelVehiculo.setBackground(new java.awt.Color(0, 0, 153));
        labelVehiculo.setForeground(new java.awt.Color(255, 255, 255));
        labelVehiculo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelVehiculo.setText("ID");
        labelVehiculo.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        labelVehiculo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelVehiculo.setRoundTopLeft(20);
        labelVehiculo.setRoundTopRight(20);

        btnGuardarVeh.setBackground(new java.awt.Color(255, 204, 0));
        btnGuardarVeh.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarVeh.setText("GUARDAR");
        btnGuardarVeh.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnGuardarVeh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarVehMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarVehMouseExited(evt);
            }
        });
        btnGuardarVeh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarVehActionPerformed(evt);
            }
        });

        btnLimpiarVeh.setBackground(new java.awt.Color(255, 204, 0));
        btnLimpiarVeh.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiarVeh.setText("CANCELAR");
        btnLimpiarVeh.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnLimpiarVeh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLimpiarVehMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLimpiarVehMouseExited(evt);
            }
        });
        btnLimpiarVeh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarVehActionPerformed(evt);
            }
        });

        btnEliminarVeh.setBackground(new java.awt.Color(255, 204, 0));
        btnEliminarVeh.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarVeh.setText("ELIMINAR");
        btnEliminarVeh.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnEliminarVeh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEliminarVehMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEliminarVehMouseExited(evt);
            }
        });
        btnEliminarVeh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarVehActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout formularioVehiculoLayout = new javax.swing.GroupLayout(formularioVehiculo);
        formularioVehiculo.setLayout(formularioVehiculoLayout);
        formularioVehiculoLayout.setHorizontalGroup(
            formularioVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formularioVehiculoLayout.createSequentialGroup()
                .addGroup(formularioVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formularioVehiculoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelVehiculo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(formularioVehiculoLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(formularioVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelIdClienteVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdClienteVeh)
                            .addComponent(labelModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtModeloVeh, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(formularioVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formularioVehiculoLayout.createSequentialGroup()
                                .addGroup(formularioVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelPlacaVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPlacaVeh, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(formularioVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelMarcaVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMarcaVeh, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(formularioVehiculoLayout.createSequentialGroup()
                                .addGroup(formularioVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelAnioVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAnioVeh, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(formularioVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelColorVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtColorVeh, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formularioVehiculoLayout.createSequentialGroup()
                                .addComponent(btnLimpiarVeh, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminarVeh, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnGuardarVeh, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 15, Short.MAX_VALUE)))
                .addContainerGap())
        );
        formularioVehiculoLayout.setVerticalGroup(
            formularioVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formularioVehiculoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(formularioVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(formularioVehiculoLayout.createSequentialGroup()
                        .addComponent(labelIdClienteVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdClienteVeh, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(formularioVehiculoLayout.createSequentialGroup()
                        .addComponent(labelPlacaVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPlacaVeh, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(formularioVehiculoLayout.createSequentialGroup()
                        .addComponent(labelMarcaVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMarcaVeh, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(formularioVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(formularioVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(formularioVehiculoLayout.createSequentialGroup()
                            .addComponent(labelModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtModeloVeh, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formularioVehiculoLayout.createSequentialGroup()
                            .addComponent(labelColorVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtColorVeh, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(formularioVehiculoLayout.createSequentialGroup()
                        .addComponent(labelAnioVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAnioVeh, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(formularioVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarVeh, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiarVeh, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarVeh, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelVehiculoLayout = new javax.swing.GroupLayout(panelVehiculo);
        panelVehiculo.setLayout(panelVehiculoLayout);
        panelVehiculoLayout.setHorizontalGroup(
            panelVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVehiculoLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(formularioVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVehiculoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scrollVehiculos, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelVehiculoLayout.setVerticalGroup(
            panelVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVehiculoLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(scrollVehiculos, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(formularioVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        panelCardTabla.add(panelVehiculo, "card2");

        panelMecanico.setBackground(new java.awt.Color(255, 255, 255));
        panelMecanico.setRoundBottomLeft(30);
        panelMecanico.setRoundBottomRight(30);
        panelMecanico.setRoundTopLeft(30);
        panelMecanico.setRoundTopRight(30);
        panelMecanico.setShadow(true);
        panelMecanico.setShadowSize(3);

        formularioMecanico.setBackground(new java.awt.Color(255, 255, 255));
        formularioMecanico.setRoundBottomLeft(20);
        formularioMecanico.setRoundBottomRight(20);
        formularioMecanico.setRoundTopLeft(20);
        formularioMecanico.setRoundTopRight(20);
        formularioMecanico.setShadow(true);
        formularioMecanico.setShadowSize(3);

        labelNombreMec.setBackground(new java.awt.Color(255, 255, 255));
        labelNombreMec.setForeground(new java.awt.Color(51, 51, 51));
        labelNombreMec.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelNombreMec.setText("Nombre");
        labelNombreMec.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        labelNombreMec.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtNombreMec.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtNombreMec.setForeground(new java.awt.Color(51, 51, 51));
        txtNombreMec.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 229, 229), 3, true));

        labelEspecialidadMec.setBackground(new java.awt.Color(255, 255, 255));
        labelEspecialidadMec.setForeground(new java.awt.Color(51, 51, 51));
        labelEspecialidadMec.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelEspecialidadMec.setText("Puesto");
        labelEspecialidadMec.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        labelEspecialidadMec.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtEspecialidadMec.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtEspecialidadMec.setForeground(new java.awt.Color(51, 51, 51));
        txtEspecialidadMec.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 229, 229), 3, true));

        labelTelefonoMec.setBackground(new java.awt.Color(255, 255, 255));
        labelTelefonoMec.setForeground(new java.awt.Color(51, 51, 51));
        labelTelefonoMec.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelTelefonoMec.setText("Teléfono");
        labelTelefonoMec.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        labelTelefonoMec.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtTelefonoMec.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtTelefonoMec.setForeground(new java.awt.Color(51, 51, 51));
        txtTelefonoMec.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 229, 229), 3, true));

        labelMecanico.setBackground(new java.awt.Color(0, 0, 153));
        labelMecanico.setForeground(new java.awt.Color(255, 255, 255));
        labelMecanico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMecanico.setText("ID");
        labelMecanico.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        labelMecanico.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelMecanico.setRoundTopLeft(20);
        labelMecanico.setRoundTopRight(20);

        btnLimpiarMec.setBackground(new java.awt.Color(255, 204, 0));
        btnLimpiarMec.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiarMec.setText("CANCELAR");
        btnLimpiarMec.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnLimpiarMec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLimpiarMecMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLimpiarMecMouseExited(evt);
            }
        });
        btnLimpiarMec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarMecActionPerformed(evt);
            }
        });

        btnGuardarMec.setBackground(new java.awt.Color(255, 204, 0));
        btnGuardarMec.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarMec.setText("GUARDAR");
        btnGuardarMec.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnGuardarMec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarMecMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarMecMouseExited(evt);
            }
        });
        btnGuardarMec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarMecActionPerformed(evt);
            }
        });

        btnEliminarMec.setBackground(new java.awt.Color(255, 204, 0));
        btnEliminarMec.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarMec.setText("ELIMINAR");
        btnEliminarMec.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnEliminarMec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEliminarMecMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEliminarMecMouseExited(evt);
            }
        });
        btnEliminarMec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarMecActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout formularioMecanicoLayout = new javax.swing.GroupLayout(formularioMecanico);
        formularioMecanico.setLayout(formularioMecanicoLayout);
        formularioMecanicoLayout.setHorizontalGroup(
            formularioMecanicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formularioMecanicoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelMecanico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(formularioMecanicoLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(formularioMecanicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(formularioMecanicoLayout.createSequentialGroup()
                        .addComponent(btnLimpiarMec, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarMec, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardarMec, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(formularioMecanicoLayout.createSequentialGroup()
                        .addGroup(formularioMecanicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombreMec, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelNombreMec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(formularioMecanicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEspecialidadMec, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelEspecialidadMec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(formularioMecanicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTelefonoMec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefonoMec, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        formularioMecanicoLayout.setVerticalGroup(
            formularioMecanicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formularioMecanicoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelMecanico, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(formularioMecanicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(formularioMecanicoLayout.createSequentialGroup()
                        .addComponent(labelNombreMec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombreMec, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(formularioMecanicoLayout.createSequentialGroup()
                        .addComponent(labelEspecialidadMec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEspecialidadMec, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(formularioMecanicoLayout.createSequentialGroup()
                        .addComponent(labelTelefonoMec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTelefonoMec, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(formularioMecanicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiarMec, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardarMec, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarMec, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        scrollMecanicos.setBorder(null);

        tableMecanicos.setAutoCreateRowSorter(true);
        tableMecanicos.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        tableMecanicos.setForeground(new java.awt.Color(51, 51, 51));
        tableMecanicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Empleado", "Nombre", "Puesto", "Teléfono"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableMecanicos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        tableMecanicos.setAutoscrolls(false);
        tableMecanicos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tableMecanicos.setGridColor(new java.awt.Color(255, 255, 255));
        tableMecanicos.setMinimumSize(new java.awt.Dimension(820, 500));
        tableMecanicos.setPreferredSize(new java.awt.Dimension(820, 500));
        tableMecanicos.setRowHeight(40);
        tableMecanicos.setSelectionBackground(new java.awt.Color(255, 255, 204));
        tableMecanicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMecanicosMouseClicked(evt);
            }
        });
        scrollMecanicos.setViewportView(tableMecanicos);

        javax.swing.GroupLayout panelMecanicoLayout = new javax.swing.GroupLayout(panelMecanico);
        panelMecanico.setLayout(panelMecanicoLayout);
        panelMecanicoLayout.setHorizontalGroup(
            panelMecanicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMecanicoLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(panelMecanicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(formularioMecanico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollMecanicos, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );
        panelMecanicoLayout.setVerticalGroup(
            panelMecanicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMecanicoLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(scrollMecanicos, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(formularioMecanico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        panelCardTabla.add(panelMecanico, "card3");

        panelOrden.setBackground(new java.awt.Color(255, 255, 255));
        panelOrden.setRoundBottomLeft(30);
        panelOrden.setRoundBottomRight(30);
        panelOrden.setRoundTopLeft(30);
        panelOrden.setRoundTopRight(30);
        panelOrden.setShadow(true);
        panelOrden.setShadowSize(3);

        formularioOrden.setBackground(new java.awt.Color(255, 255, 255));
        formularioOrden.setRoundBottomLeft(20);
        formularioOrden.setRoundBottomRight(20);
        formularioOrden.setRoundTopLeft(20);
        formularioOrden.setRoundTopRight(20);
        formularioOrden.setShadow(true);
        formularioOrden.setShadowSize(3);

        labelOrden.setBackground(new java.awt.Color(0, 0, 153));
        labelOrden.setForeground(new java.awt.Color(255, 255, 255));
        labelOrden.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelOrden.setText("ID");
        labelOrden.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        labelOrden.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelOrden.setRoundTopLeft(20);
        labelOrden.setRoundTopRight(20);

        labelIdVehiculoOrd.setBackground(new java.awt.Color(255, 255, 255));
        labelIdVehiculoOrd.setForeground(new java.awt.Color(51, 51, 51));
        labelIdVehiculoOrd.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelIdVehiculoOrd.setText("ID Pedido");
        labelIdVehiculoOrd.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        labelIdVehiculoOrd.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtIdVehiculoOrd.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtIdVehiculoOrd.setForeground(new java.awt.Color(51, 51, 51));
        txtIdVehiculoOrd.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 229, 229), 3, true));

        labelIdMecanicoOrd.setBackground(new java.awt.Color(255, 255, 255));
        labelIdMecanicoOrd.setForeground(new java.awt.Color(51, 51, 51));
        labelIdMecanicoOrd.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelIdMecanicoOrd.setText("ID Empleado");
        labelIdMecanicoOrd.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        labelIdMecanicoOrd.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtIdMecanicoOrd.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtIdMecanicoOrd.setForeground(new java.awt.Color(51, 51, 51));
        txtIdMecanicoOrd.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 229, 229), 3, true));

        labelDescripcionOrd.setBackground(new java.awt.Color(255, 255, 255));
        labelDescripcionOrd.setForeground(new java.awt.Color(51, 51, 51));
        labelDescripcionOrd.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelDescripcionOrd.setText("Descripción");
        labelDescripcionOrd.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        labelDescripcionOrd.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtDescripcionOrd.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtDescripcionOrd.setForeground(new java.awt.Color(51, 51, 51));
        txtDescripcionOrd.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 229, 229), 3, true));

        labelEstado.setBackground(new java.awt.Color(255, 255, 255));
        labelEstado.setForeground(new java.awt.Color(51, 51, 51));
        labelEstado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelEstado.setText("Estado");
        labelEstado.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        labelEstado.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        comboEstado.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        comboEstado.setForeground(new java.awt.Color(51, 51, 51));
        comboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "INICIAR", "CANCELAR", "FINALIZAR" }));
        comboEstado.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 229, 229), 3, true));
        comboEstado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnLimpiarOrd.setBackground(new java.awt.Color(255, 204, 0));
        btnLimpiarOrd.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiarOrd.setText("CANCELAR");
        btnLimpiarOrd.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnLimpiarOrd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLimpiarOrdMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLimpiarOrdMouseExited(evt);
            }
        });
        btnLimpiarOrd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarOrdActionPerformed(evt);
            }
        });

        btnGuardarOrd.setBackground(new java.awt.Color(255, 204, 0));
        btnGuardarOrd.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarOrd.setText("GUARDAR");
        btnGuardarOrd.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnGuardarOrd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarOrdMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarOrdMouseExited(evt);
            }
        });
        btnGuardarOrd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarOrdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout formularioOrdenLayout = new javax.swing.GroupLayout(formularioOrden);
        formularioOrden.setLayout(formularioOrdenLayout);
        formularioOrdenLayout.setHorizontalGroup(
            formularioOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formularioOrdenLayout.createSequentialGroup()
                .addGroup(formularioOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formularioOrdenLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelOrden, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(formularioOrdenLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(formularioOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(formularioOrdenLayout.createSequentialGroup()
                                .addGroup(formularioOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIdVehiculoOrd, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelIdVehiculoOrd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(formularioOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelIdMecanicoOrd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIdMecanicoOrd, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(formularioOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDescripcionOrd, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelDescripcionOrd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(formularioOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(formularioOrdenLayout.createSequentialGroup()
                                .addComponent(btnLimpiarOrd, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnGuardarOrd, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 33, Short.MAX_VALUE)))
                .addContainerGap())
        );
        formularioOrdenLayout.setVerticalGroup(
            formularioOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formularioOrdenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(formularioOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(formularioOrdenLayout.createSequentialGroup()
                        .addComponent(labelIdVehiculoOrd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdVehiculoOrd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(formularioOrdenLayout.createSequentialGroup()
                        .addComponent(labelIdMecanicoOrd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdMecanicoOrd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(formularioOrdenLayout.createSequentialGroup()
                        .addComponent(labelDescripcionOrd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescripcionOrd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(formularioOrdenLayout.createSequentialGroup()
                        .addComponent(labelEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(formularioOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarOrd, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiarOrd, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        scrollOrdenes.setBorder(null);

        tableOrdenes.setAutoCreateRowSorter(true);
        tableOrdenes.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        tableOrdenes.setForeground(new java.awt.Color(51, 51, 51));
        tableOrdenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Venta", "ID Pedido", "ID Empleado", "Descripción", "Fecha Creación", "Fecha Final", "Estado", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableOrdenes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        tableOrdenes.setAutoscrolls(false);
        tableOrdenes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tableOrdenes.setGridColor(new java.awt.Color(255, 255, 255));
        tableOrdenes.setMinimumSize(new java.awt.Dimension(820, 500));
        tableOrdenes.setPreferredSize(new java.awt.Dimension(820, 500));
        tableOrdenes.setRowHeight(40);
        tableOrdenes.setSelectionBackground(new java.awt.Color(255, 255, 204));
        tableOrdenes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableOrdenesMouseClicked(evt);
            }
        });
        scrollOrdenes.setViewportView(tableOrdenes);

        comboFiltroOrden.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        comboFiltroOrden.setForeground(new java.awt.Color(51, 51, 51));
        comboFiltroOrden.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos los registros", "Filtrar por estado", "Buscar por código", "Buscar por empleado", "Ingresos por fecha" }));
        comboFiltroOrden.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 229, 229), 3, true));
        comboFiltroOrden.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        comboFiltroOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFiltroOrdenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelOrdenLayout = new javax.swing.GroupLayout(panelOrden);
        panelOrden.setLayout(panelOrdenLayout);
        panelOrdenLayout.setHorizontalGroup(
            panelOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOrdenLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(panelOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboFiltroOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelOrdenLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(formularioOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrollOrdenes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );
        panelOrdenLayout.setVerticalGroup(
            panelOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOrdenLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(comboFiltroOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scrollOrdenes, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(formularioOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        panelCardTabla.add(panelOrden, "card4");

        panelRepuesto.setBackground(new java.awt.Color(255, 255, 255));
        panelRepuesto.setRoundBottomLeft(30);
        panelRepuesto.setRoundBottomRight(30);
        panelRepuesto.setRoundTopLeft(30);
        panelRepuesto.setRoundTopRight(30);
        panelRepuesto.setShadow(true);
        panelRepuesto.setShadowSize(3);

        scrollRepuestos.setBorder(null);

        tableRepuestos.setAutoCreateRowSorter(true);
        tableRepuestos.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        tableRepuestos.setForeground(new java.awt.Color(51, 51, 51));
        tableRepuestos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Producto", "Nombre", "Descripción", "Precio", "Existencia"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableRepuestos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        tableRepuestos.setAutoscrolls(false);
        tableRepuestos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tableRepuestos.setGridColor(new java.awt.Color(255, 255, 255));
        tableRepuestos.setMinimumSize(new java.awt.Dimension(820, 500));
        tableRepuestos.setPreferredSize(new java.awt.Dimension(820, 500));
        tableRepuestos.setRowHeight(40);
        tableRepuestos.setSelectionBackground(new java.awt.Color(255, 255, 204));
        tableRepuestos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableRepuestosMouseClicked(evt);
            }
        });
        scrollRepuestos.setViewportView(tableRepuestos);

        formularioRepuesto.setBackground(new java.awt.Color(255, 255, 255));
        formularioRepuesto.setRoundBottomLeft(20);
        formularioRepuesto.setRoundBottomRight(20);
        formularioRepuesto.setRoundTopLeft(20);
        formularioRepuesto.setRoundTopRight(20);
        formularioRepuesto.setShadow(true);
        formularioRepuesto.setShadowSize(3);

        labelNombreRep.setBackground(new java.awt.Color(255, 255, 255));
        labelNombreRep.setForeground(new java.awt.Color(51, 51, 51));
        labelNombreRep.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelNombreRep.setText("Nombre");
        labelNombreRep.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        labelNombreRep.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtNombreRep.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtNombreRep.setForeground(new java.awt.Color(51, 51, 51));
        txtNombreRep.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 229, 229), 3, true));

        labelDescripcionRep.setBackground(new java.awt.Color(255, 255, 255));
        labelDescripcionRep.setForeground(new java.awt.Color(51, 51, 51));
        labelDescripcionRep.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelDescripcionRep.setText("Descripción");
        labelDescripcionRep.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        labelDescripcionRep.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtDescripcionRep.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtDescripcionRep.setForeground(new java.awt.Color(51, 51, 51));
        txtDescripcionRep.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 229, 229), 3, true));

        labelPrecioRep.setBackground(new java.awt.Color(255, 255, 255));
        labelPrecioRep.setForeground(new java.awt.Color(51, 51, 51));
        labelPrecioRep.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelPrecioRep.setText("Precio (Q)");
        labelPrecioRep.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        labelPrecioRep.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtPrecioRep.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtPrecioRep.setForeground(new java.awt.Color(51, 51, 51));
        txtPrecioRep.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 229, 229), 3, true));

        labelRepuesto.setBackground(new java.awt.Color(0, 0, 153));
        labelRepuesto.setForeground(new java.awt.Color(255, 255, 255));
        labelRepuesto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRepuesto.setText("ID");
        labelRepuesto.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        labelRepuesto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelRepuesto.setRoundTopLeft(20);
        labelRepuesto.setRoundTopRight(20);

        btnLimpiarRep.setBackground(new java.awt.Color(255, 204, 0));
        btnLimpiarRep.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiarRep.setText("CANCELAR");
        btnLimpiarRep.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnLimpiarRep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLimpiarRepMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLimpiarRepMouseExited(evt);
            }
        });
        btnLimpiarRep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarRepActionPerformed(evt);
            }
        });

        btnGuardarRep.setBackground(new java.awt.Color(255, 204, 0));
        btnGuardarRep.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarRep.setText("GUARDAR");
        btnGuardarRep.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnGuardarRep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarRepMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarRepMouseExited(evt);
            }
        });
        btnGuardarRep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarRepActionPerformed(evt);
            }
        });

        btnEliminarRep.setBackground(new java.awt.Color(255, 204, 0));
        btnEliminarRep.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarRep.setText("ELIMINAR");
        btnEliminarRep.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnEliminarRep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEliminarRepMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEliminarRepMouseExited(evt);
            }
        });
        btnEliminarRep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarRepActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout formularioRepuestoLayout = new javax.swing.GroupLayout(formularioRepuesto);
        formularioRepuesto.setLayout(formularioRepuestoLayout);
        formularioRepuestoLayout.setHorizontalGroup(
            formularioRepuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formularioRepuestoLayout.createSequentialGroup()
                .addGroup(formularioRepuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formularioRepuestoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelRepuesto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(formularioRepuestoLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(formularioRepuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombreRep, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelNombreRep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(formularioRepuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(formularioRepuestoLayout.createSequentialGroup()
                                .addComponent(btnLimpiarRep, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminarRep, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnGuardarRep, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(formularioRepuestoLayout.createSequentialGroup()
                                .addGroup(formularioRepuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDescripcionRep, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelDescripcionRep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(formularioRepuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPrecioRep, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelPrecioRep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 20, Short.MAX_VALUE)))
                .addContainerGap())
        );
        formularioRepuestoLayout.setVerticalGroup(
            formularioRepuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formularioRepuestoLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(labelRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(formularioRepuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(formularioRepuestoLayout.createSequentialGroup()
                        .addComponent(labelNombreRep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombreRep, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(formularioRepuestoLayout.createSequentialGroup()
                        .addComponent(labelDescripcionRep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescripcionRep, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(formularioRepuestoLayout.createSequentialGroup()
                        .addComponent(labelPrecioRep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrecioRep, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(formularioRepuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarRep, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiarRep, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarRep, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelRepuestoLayout = new javax.swing.GroupLayout(panelRepuesto);
        panelRepuesto.setLayout(panelRepuestoLayout);
        panelRepuestoLayout.setHorizontalGroup(
            panelRepuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRepuestoLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(panelRepuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(formularioRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrollRepuestos, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        panelRepuestoLayout.setVerticalGroup(
            panelRepuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRepuestoLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(scrollRepuestos, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(formularioRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        panelCardTabla.add(panelRepuesto, "card5");

        panelUtilizado.setBackground(new java.awt.Color(255, 255, 255));
        panelUtilizado.setRoundBottomLeft(30);
        panelUtilizado.setRoundBottomRight(30);
        panelUtilizado.setRoundTopLeft(30);
        panelUtilizado.setRoundTopRight(30);
        panelUtilizado.setShadow(true);
        panelUtilizado.setShadowSize(3);

        formularioUtilizado.setBackground(new java.awt.Color(255, 255, 255));
        formularioUtilizado.setRoundBottomLeft(20);
        formularioUtilizado.setRoundBottomRight(20);
        formularioUtilizado.setRoundTopLeft(20);
        formularioUtilizado.setRoundTopRight(20);
        formularioUtilizado.setShadow(true);
        formularioUtilizado.setShadowSize(3);

        labelIdOrdenUti.setBackground(new java.awt.Color(255, 255, 255));
        labelIdOrdenUti.setForeground(new java.awt.Color(51, 51, 51));
        labelIdOrdenUti.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelIdOrdenUti.setText("ID Venta");
        labelIdOrdenUti.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        labelIdOrdenUti.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtIdOrdenUti.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtIdOrdenUti.setForeground(new java.awt.Color(51, 51, 51));
        txtIdOrdenUti.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 229, 229), 3, true));

        labelIdRepuestoUti.setBackground(new java.awt.Color(255, 255, 255));
        labelIdRepuestoUti.setForeground(new java.awt.Color(51, 51, 51));
        labelIdRepuestoUti.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelIdRepuestoUti.setText("ID Producto");
        labelIdRepuestoUti.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        labelIdRepuestoUti.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtIdRepuestoUti.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtIdRepuestoUti.setForeground(new java.awt.Color(51, 51, 51));
        txtIdRepuestoUti.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 229, 229), 3, true));

        labelCantidadUti.setBackground(new java.awt.Color(255, 255, 255));
        labelCantidadUti.setForeground(new java.awt.Color(51, 51, 51));
        labelCantidadUti.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelCantidadUti.setText("Cantidad");
        labelCantidadUti.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        labelCantidadUti.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtCantidadUti.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtCantidadUti.setForeground(new java.awt.Color(51, 51, 51));
        txtCantidadUti.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 229, 229), 3, true));

        labelUtilizado.setBackground(new java.awt.Color(0, 0, 153));
        labelUtilizado.setForeground(new java.awt.Color(255, 255, 255));
        labelUtilizado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelUtilizado.setText("ID");
        labelUtilizado.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        labelUtilizado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelUtilizado.setRoundTopLeft(20);
        labelUtilizado.setRoundTopRight(20);

        btnGuardarUti.setBackground(new java.awt.Color(255, 204, 0));
        btnGuardarUti.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarUti.setText("GUARDAR");
        btnGuardarUti.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnGuardarUti.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarUtiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarUtiMouseExited(evt);
            }
        });
        btnGuardarUti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarUtiActionPerformed(evt);
            }
        });

        btnLimpiarUti.setBackground(new java.awt.Color(255, 204, 0));
        btnLimpiarUti.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiarUti.setText("CANCELAR");
        btnLimpiarUti.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnLimpiarUti.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLimpiarUtiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLimpiarUtiMouseExited(evt);
            }
        });
        btnLimpiarUti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarUtiActionPerformed(evt);
            }
        });

        btnEliminarUti.setBackground(new java.awt.Color(255, 204, 0));
        btnEliminarUti.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarUti.setText("ELIMINAR");
        btnEliminarUti.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnEliminarUti.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEliminarUtiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEliminarUtiMouseExited(evt);
            }
        });
        btnEliminarUti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUtiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout formularioUtilizadoLayout = new javax.swing.GroupLayout(formularioUtilizado);
        formularioUtilizado.setLayout(formularioUtilizadoLayout);
        formularioUtilizadoLayout.setHorizontalGroup(
            formularioUtilizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formularioUtilizadoLayout.createSequentialGroup()
                .addGroup(formularioUtilizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formularioUtilizadoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelUtilizado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(formularioUtilizadoLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(formularioUtilizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(formularioUtilizadoLayout.createSequentialGroup()
                                .addGroup(formularioUtilizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIdOrdenUti, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelIdOrdenUti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(formularioUtilizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIdRepuestoUti, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelIdRepuestoUti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(formularioUtilizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCantidadUti, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelCantidadUti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(formularioUtilizadoLayout.createSequentialGroup()
                                .addComponent(btnLimpiarUti, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminarUti, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnGuardarUti, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 15, Short.MAX_VALUE)))
                .addContainerGap())
        );
        formularioUtilizadoLayout.setVerticalGroup(
            formularioUtilizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formularioUtilizadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelUtilizado, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(formularioUtilizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(formularioUtilizadoLayout.createSequentialGroup()
                        .addComponent(labelIdOrdenUti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdOrdenUti, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(formularioUtilizadoLayout.createSequentialGroup()
                        .addComponent(labelIdRepuestoUti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdRepuestoUti, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(formularioUtilizadoLayout.createSequentialGroup()
                        .addComponent(labelCantidadUti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCantidadUti, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(formularioUtilizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEliminarUti, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(formularioUtilizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnGuardarUti, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLimpiarUti, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        scrollUtilizados.setBorder(null);

        tableUtilizados.setAutoCreateRowSorter(true);
        tableUtilizados.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        tableUtilizados.setForeground(new java.awt.Color(51, 51, 51));
        tableUtilizados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Producto Vendido", "ID Venta", "ID Producto", "Cantidad", "Precio", "Subtotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableUtilizados.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        tableUtilizados.setAutoscrolls(false);
        tableUtilizados.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tableUtilizados.setGridColor(new java.awt.Color(255, 255, 255));
        tableUtilizados.setMinimumSize(new java.awt.Dimension(820, 500));
        tableUtilizados.setPreferredSize(new java.awt.Dimension(820, 500));
        tableUtilizados.setRowHeight(40);
        tableUtilizados.setSelectionBackground(new java.awt.Color(255, 255, 204));
        tableUtilizados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableUtilizadosMouseClicked(evt);
            }
        });
        scrollUtilizados.setViewportView(tableUtilizados);

        javax.swing.GroupLayout panelUtilizadoLayout = new javax.swing.GroupLayout(panelUtilizado);
        panelUtilizado.setLayout(panelUtilizadoLayout);
        panelUtilizadoLayout.setHorizontalGroup(
            panelUtilizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUtilizadoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelUtilizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollUtilizados, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(formularioUtilizado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        panelUtilizadoLayout.setVerticalGroup(
            panelUtilizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelUtilizadoLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(scrollUtilizados, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(formularioUtilizado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        panelCardTabla.add(panelUtilizado, "card6");

        panelAbastecimiento.setBackground(new java.awt.Color(255, 255, 255));
        panelAbastecimiento.setRoundBottomLeft(30);
        panelAbastecimiento.setRoundBottomRight(30);
        panelAbastecimiento.setRoundTopLeft(30);
        panelAbastecimiento.setRoundTopRight(30);
        panelAbastecimiento.setShadow(true);
        panelAbastecimiento.setShadowSize(3);

        scrollAbastecimiento.setBorder(null);

        tableAbastecimiento.setAutoCreateRowSorter(true);
        tableAbastecimiento.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        tableAbastecimiento.setForeground(new java.awt.Color(51, 51, 51));
        tableAbastecimiento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Abastecimiento", "ID Producto", "Cantidad", "Fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableAbastecimiento.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        tableAbastecimiento.setAutoscrolls(false);
        tableAbastecimiento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tableAbastecimiento.setGridColor(new java.awt.Color(255, 255, 255));
        tableAbastecimiento.setMinimumSize(new java.awt.Dimension(820, 500));
        tableAbastecimiento.setPreferredSize(new java.awt.Dimension(820, 500));
        tableAbastecimiento.setRowHeight(40);
        tableAbastecimiento.setSelectionBackground(new java.awt.Color(255, 255, 204));
        tableAbastecimiento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableAbastecimientoMouseClicked(evt);
            }
        });
        scrollAbastecimiento.setViewportView(tableAbastecimiento);

        formularioAbastecimiento.setBackground(new java.awt.Color(255, 255, 255));
        formularioAbastecimiento.setRoundBottomLeft(20);
        formularioAbastecimiento.setRoundBottomRight(20);
        formularioAbastecimiento.setRoundTopLeft(20);
        formularioAbastecimiento.setRoundTopRight(20);
        formularioAbastecimiento.setShadow(true);
        formularioAbastecimiento.setShadowSize(3);

        labelIdRepuestoAba.setBackground(new java.awt.Color(255, 255, 255));
        labelIdRepuestoAba.setForeground(new java.awt.Color(51, 51, 51));
        labelIdRepuestoAba.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelIdRepuestoAba.setText("ID Producto");
        labelIdRepuestoAba.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        labelIdRepuestoAba.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtIdRepuestoAba.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtIdRepuestoAba.setForeground(new java.awt.Color(51, 51, 51));
        txtIdRepuestoAba.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 229, 229), 3, true));

        labelCantidadAba.setBackground(new java.awt.Color(255, 255, 255));
        labelCantidadAba.setForeground(new java.awt.Color(51, 51, 51));
        labelCantidadAba.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelCantidadAba.setText("Cantidad");
        labelCantidadAba.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        labelCantidadAba.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtCantidadAba.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtCantidadAba.setForeground(new java.awt.Color(51, 51, 51));
        txtCantidadAba.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 229, 229), 3, true));

        labelAbastecimiento.setBackground(new java.awt.Color(0, 0, 153));
        labelAbastecimiento.setForeground(new java.awt.Color(255, 255, 255));
        labelAbastecimiento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelAbastecimiento.setText("ID");
        labelAbastecimiento.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        labelAbastecimiento.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelAbastecimiento.setRoundTopLeft(20);
        labelAbastecimiento.setRoundTopRight(20);

        btnGuardarAba.setBackground(new java.awt.Color(255, 204, 0));
        btnGuardarAba.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarAba.setText("GUARDAR");
        btnGuardarAba.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnGuardarAba.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarAbaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarAbaMouseExited(evt);
            }
        });
        btnGuardarAba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarAbaActionPerformed(evt);
            }
        });

        btnLimpiarAba.setBackground(new java.awt.Color(255, 204, 0));
        btnLimpiarAba.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiarAba.setText("CANCELAR");
        btnLimpiarAba.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnLimpiarAba.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLimpiarAbaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLimpiarAbaMouseExited(evt);
            }
        });
        btnLimpiarAba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarAbaActionPerformed(evt);
            }
        });

        btnEliminarAba.setBackground(new java.awt.Color(255, 204, 0));
        btnEliminarAba.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarAba.setText("ELIMINAR");
        btnEliminarAba.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnEliminarAba.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEliminarAbaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEliminarAbaMouseExited(evt);
            }
        });
        btnEliminarAba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarAbaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout formularioAbastecimientoLayout = new javax.swing.GroupLayout(formularioAbastecimiento);
        formularioAbastecimiento.setLayout(formularioAbastecimientoLayout);
        formularioAbastecimientoLayout.setHorizontalGroup(
            formularioAbastecimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formularioAbastecimientoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelAbastecimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(formularioAbastecimientoLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(formularioAbastecimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(formularioAbastecimientoLayout.createSequentialGroup()
                        .addComponent(btnLimpiarAba, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarAba, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardarAba, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(formularioAbastecimientoLayout.createSequentialGroup()
                        .addGroup(formularioAbastecimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdRepuestoAba, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelIdRepuestoAba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(formularioAbastecimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCantidadAba, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelCantidadAba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        formularioAbastecimientoLayout.setVerticalGroup(
            formularioAbastecimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formularioAbastecimientoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelAbastecimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(formularioAbastecimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(formularioAbastecimientoLayout.createSequentialGroup()
                        .addComponent(labelIdRepuestoAba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdRepuestoAba, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(formularioAbastecimientoLayout.createSequentialGroup()
                        .addComponent(labelCantidadAba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCantidadAba, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(formularioAbastecimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarAba, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiarAba, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarAba, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelAbastecimientoLayout = new javax.swing.GroupLayout(panelAbastecimiento);
        panelAbastecimiento.setLayout(panelAbastecimientoLayout);
        panelAbastecimientoLayout.setHorizontalGroup(
            panelAbastecimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAbastecimientoLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(panelAbastecimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(formularioAbastecimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollAbastecimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 820, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        panelAbastecimientoLayout.setVerticalGroup(
            panelAbastecimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAbastecimientoLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(scrollAbastecimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(formularioAbastecimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        panelCardTabla.add(panelAbastecimiento, "card7");

        panelServicio.setBackground(new java.awt.Color(255, 255, 255));
        panelServicio.setRoundBottomLeft(30);
        panelServicio.setRoundBottomRight(30);
        panelServicio.setRoundTopLeft(30);
        panelServicio.setRoundTopRight(30);
        panelServicio.setShadow(true);
        panelServicio.setShadowSize(3);

        scrollServicios.setBorder(null);

        tableServicios.setAutoCreateRowSorter(true);
        tableServicios.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        tableServicios.setForeground(new java.awt.Color(51, 51, 51));
        tableServicios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Servicio", "Descripción", "Costo del Servicio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableServicios.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        tableServicios.setAutoscrolls(false);
        tableServicios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tableServicios.setGridColor(new java.awt.Color(255, 255, 255));
        tableServicios.setMinimumSize(new java.awt.Dimension(820, 500));
        tableServicios.setPreferredSize(new java.awt.Dimension(820, 500));
        tableServicios.setRowHeight(40);
        tableServicios.setSelectionBackground(new java.awt.Color(255, 255, 204));
        tableServicios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableServiciosMouseClicked(evt);
            }
        });
        scrollServicios.setViewportView(tableServicios);

        formularioServicio.setBackground(new java.awt.Color(255, 255, 255));
        formularioServicio.setRoundBottomLeft(20);
        formularioServicio.setRoundBottomRight(20);
        formularioServicio.setRoundTopLeft(20);
        formularioServicio.setRoundTopRight(20);
        formularioServicio.setShadow(true);
        formularioServicio.setShadowSize(3);

        labelDescripcionSer.setBackground(new java.awt.Color(255, 255, 255));
        labelDescripcionSer.setForeground(new java.awt.Color(51, 51, 51));
        labelDescripcionSer.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelDescripcionSer.setText("Descripción");
        labelDescripcionSer.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        labelDescripcionSer.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtDescripcionSer.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtDescripcionSer.setForeground(new java.awt.Color(51, 51, 51));
        txtDescripcionSer.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 229, 229), 3, true));

        labelCostoSer.setBackground(new java.awt.Color(255, 255, 255));
        labelCostoSer.setForeground(new java.awt.Color(51, 51, 51));
        labelCostoSer.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelCostoSer.setText("Costo del Servicio (Q)");
        labelCostoSer.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        labelCostoSer.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtCostoSer.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtCostoSer.setForeground(new java.awt.Color(51, 51, 51));
        txtCostoSer.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 229, 229), 3, true));

        labelServicio.setBackground(new java.awt.Color(0, 0, 153));
        labelServicio.setForeground(new java.awt.Color(255, 255, 255));
        labelServicio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelServicio.setText("ID");
        labelServicio.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        labelServicio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelServicio.setRoundTopLeft(20);
        labelServicio.setRoundTopRight(20);

        btnLimpiarSer.setBackground(new java.awt.Color(255, 204, 0));
        btnLimpiarSer.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiarSer.setText("CANCELAR");
        btnLimpiarSer.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnLimpiarSer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLimpiarSerMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLimpiarSerMouseExited(evt);
            }
        });
        btnLimpiarSer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarSerActionPerformed(evt);
            }
        });

        btnGuardarSer.setBackground(new java.awt.Color(255, 204, 0));
        btnGuardarSer.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarSer.setText("GUARDAR");
        btnGuardarSer.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnGuardarSer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarSerMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarSerMouseExited(evt);
            }
        });
        btnGuardarSer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarSerActionPerformed(evt);
            }
        });

        btnEliminarSer.setBackground(new java.awt.Color(255, 204, 0));
        btnEliminarSer.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarSer.setText("ELIMINAR");
        btnEliminarSer.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnEliminarSer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEliminarSerMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEliminarSerMouseExited(evt);
            }
        });
        btnEliminarSer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarSerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout formularioServicioLayout = new javax.swing.GroupLayout(formularioServicio);
        formularioServicio.setLayout(formularioServicioLayout);
        formularioServicioLayout.setHorizontalGroup(
            formularioServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formularioServicioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelServicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(formularioServicioLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(formularioServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(formularioServicioLayout.createSequentialGroup()
                        .addComponent(btnLimpiarSer, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarSer, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardarSer, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(formularioServicioLayout.createSequentialGroup()
                        .addGroup(formularioServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDescripcionSer, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelDescripcionSer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(formularioServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCostoSer, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelCostoSer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        formularioServicioLayout.setVerticalGroup(
            formularioServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formularioServicioLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(labelServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(formularioServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(formularioServicioLayout.createSequentialGroup()
                        .addComponent(labelDescripcionSer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescripcionSer, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(formularioServicioLayout.createSequentialGroup()
                        .addComponent(labelCostoSer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCostoSer, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(formularioServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarSer, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiarSer, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarSer, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelServicioLayout = new javax.swing.GroupLayout(panelServicio);
        panelServicio.setLayout(panelServicioLayout);
        panelServicioLayout.setHorizontalGroup(
            panelServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelServicioLayout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(panelServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollServicios, javax.swing.GroupLayout.PREFERRED_SIZE, 820, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(formularioServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );
        panelServicioLayout.setVerticalGroup(
            panelServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelServicioLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(scrollServicios, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(formularioServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        panelCardTabla.add(panelServicio, "card8");

        panelRealizado.setBackground(new java.awt.Color(255, 255, 255));
        panelRealizado.setRoundBottomLeft(30);
        panelRealizado.setRoundBottomRight(30);
        panelRealizado.setRoundTopLeft(30);
        panelRealizado.setRoundTopRight(30);
        panelRealizado.setShadow(true);
        panelRealizado.setShadowSize(3);

        formularioRealizado.setBackground(new java.awt.Color(255, 255, 255));
        formularioRealizado.setRoundBottomLeft(20);
        formularioRealizado.setRoundBottomRight(20);
        formularioRealizado.setRoundTopLeft(20);
        formularioRealizado.setRoundTopRight(20);
        formularioRealizado.setShadow(true);
        formularioRealizado.setShadowSize(3);

        labelIdOrdenRea.setBackground(new java.awt.Color(255, 255, 255));
        labelIdOrdenRea.setForeground(new java.awt.Color(51, 51, 51));
        labelIdOrdenRea.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelIdOrdenRea.setText("ID Venta");
        labelIdOrdenRea.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        labelIdOrdenRea.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtIdOrdenRea.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtIdOrdenRea.setForeground(new java.awt.Color(51, 51, 51));
        txtIdOrdenRea.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 229, 229), 3, true));

        labelIdServicioRea.setBackground(new java.awt.Color(255, 255, 255));
        labelIdServicioRea.setForeground(new java.awt.Color(51, 51, 51));
        labelIdServicioRea.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelIdServicioRea.setText("ID Servicio");
        labelIdServicioRea.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        labelIdServicioRea.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtIdServicioRea.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtIdServicioRea.setForeground(new java.awt.Color(51, 51, 51));
        txtIdServicioRea.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 229, 229), 3, true));

        labelCantidadRea.setBackground(new java.awt.Color(255, 255, 255));
        labelCantidadRea.setForeground(new java.awt.Color(51, 51, 51));
        labelCantidadRea.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelCantidadRea.setText("Cantidad");
        labelCantidadRea.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        labelCantidadRea.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        txtCantidadRea.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtCantidadRea.setForeground(new java.awt.Color(51, 51, 51));
        txtCantidadRea.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 229, 229), 3, true));

        labelRealizado.setBackground(new java.awt.Color(0, 0, 153));
        labelRealizado.setForeground(new java.awt.Color(255, 255, 255));
        labelRealizado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRealizado.setText("ID");
        labelRealizado.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        labelRealizado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelRealizado.setRoundTopLeft(20);
        labelRealizado.setRoundTopRight(20);

        btnGuardarRea.setBackground(new java.awt.Color(255, 204, 0));
        btnGuardarRea.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarRea.setText("GUARDAR");
        btnGuardarRea.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnGuardarRea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarReaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarReaMouseExited(evt);
            }
        });
        btnGuardarRea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarReaActionPerformed(evt);
            }
        });

        btnEliminarRea.setBackground(new java.awt.Color(255, 204, 0));
        btnEliminarRea.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarRea.setText("ELIMINAR");
        btnEliminarRea.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnEliminarRea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEliminarReaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEliminarReaMouseExited(evt);
            }
        });
        btnEliminarRea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarReaActionPerformed(evt);
            }
        });

        btnLimpiarRea.setBackground(new java.awt.Color(255, 204, 0));
        btnLimpiarRea.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiarRea.setText("CANCELAR");
        btnLimpiarRea.setFont(new java.awt.Font("Arial Black", 0, 10)); // NOI18N
        btnLimpiarRea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLimpiarReaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLimpiarReaMouseExited(evt);
            }
        });
        btnLimpiarRea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarReaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout formularioRealizadoLayout = new javax.swing.GroupLayout(formularioRealizado);
        formularioRealizado.setLayout(formularioRealizadoLayout);
        formularioRealizadoLayout.setHorizontalGroup(
            formularioRealizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formularioRealizadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelRealizado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(formularioRealizadoLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(formularioRealizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(formularioRealizadoLayout.createSequentialGroup()
                        .addComponent(btnLimpiarRea, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarRea, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardarRea, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(formularioRealizadoLayout.createSequentialGroup()
                        .addGroup(formularioRealizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdOrdenRea, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelIdOrdenRea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(formularioRealizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdServicioRea, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelIdServicioRea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(formularioRealizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCantidadRea, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelCantidadRea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        formularioRealizadoLayout.setVerticalGroup(
            formularioRealizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formularioRealizadoLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(labelRealizado, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(formularioRealizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(formularioRealizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(formularioRealizadoLayout.createSequentialGroup()
                            .addComponent(labelIdOrdenRea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtIdOrdenRea, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formularioRealizadoLayout.createSequentialGroup()
                            .addComponent(labelCantidadRea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtCantidadRea, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(formularioRealizadoLayout.createSequentialGroup()
                        .addComponent(labelIdServicioRea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdServicioRea, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(formularioRealizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarRea, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarRea, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiarRea, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        scrollRealizados.setBorder(null);

        tableRealizados.setAutoCreateRowSorter(true);
        tableRealizados.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        tableRealizados.setForeground(new java.awt.Color(51, 51, 51));
        tableRealizados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Servicio Realizado", "ID Venta", "ID Servicio", "Cantidad", "Precio", "Subtotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableRealizados.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        tableRealizados.setAutoscrolls(false);
        tableRealizados.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tableRealizados.setGridColor(new java.awt.Color(255, 255, 255));
        tableRealizados.setMinimumSize(new java.awt.Dimension(820, 500));
        tableRealizados.setPreferredSize(new java.awt.Dimension(820, 500));
        tableRealizados.setRowHeight(40);
        tableRealizados.setSelectionBackground(new java.awt.Color(255, 255, 204));
        tableRealizados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableRealizadosMouseClicked(evt);
            }
        });
        scrollRealizados.setViewportView(tableRealizados);

        javax.swing.GroupLayout panelRealizadoLayout = new javax.swing.GroupLayout(panelRealizado);
        panelRealizado.setLayout(panelRealizadoLayout);
        panelRealizadoLayout.setHorizontalGroup(
            panelRealizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRealizadoLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelRealizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollRealizados, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(formularioRealizado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        panelRealizadoLayout.setVerticalGroup(
            panelRealizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRealizadoLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(scrollRealizados, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(formularioRealizado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        panelCardTabla.add(panelRealizado, "card9");

        javax.swing.GroupLayout panelFondoLayout = new javax.swing.GroupLayout(panelFondo);
        panelFondo.setLayout(panelFondoLayout);
        panelFondoLayout.setHorizontalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addComponent(panelCardTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 873, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addComponent(panelCardBuscador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnMinimizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        panelFondoLayout.setVerticalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMinimizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelCardBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelCardTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1108, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 659, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMinimizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizarMouseEntered
        btnMinimizar.setBackground(new Color(216,216,216));
    }//GEN-LAST:event_btnMinimizarMouseEntered

    private void btnMinimizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizarMouseExited
        btnMinimizar.setBackground(new Color(237, 237, 237));
    }//GEN-LAST:event_btnMinimizarMouseExited

    private void btnMinimizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimizarActionPerformed
        btnMinimizar.minimizar();
    }//GEN-LAST:event_btnMinimizarActionPerformed

    private void btnRegresarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegresarMouseEntered
        btnRegresar.setBackground(new Color(228, 55, 68));
        btnRegresar.setForeground(Color.white);
        btnRegresar.setIconPath("/icon/regresar2.png", 35);
    }//GEN-LAST:event_btnRegresarMouseEntered

    private void btnRegresarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegresarMouseExited
        btnRegresar.setBackground(new Color(8, 29, 86));
        btnRegresar.setForeground(new Color(255, 204, 0));
        btnRegresar.setIconPath("/icon/regresar.png", 35);
    }//GEN-LAST:event_btnRegresarMouseExited

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        btnRegresar.setEnabled(false);
        btnRegresar.cambiarA(new MenuPrincipal());
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void panelFondoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelFondoMousePressed
        moverX = evt.getX();
        moverY = evt.getY();
    }//GEN-LAST:event_panelFondoMousePressed

    private void panelFondoMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelFondoMouseDragged
        x = evt.getXOnScreen();
        y = evt.getYOnScreen();
        this.setLocation(x - moverX, y - moverY);
    }//GEN-LAST:event_panelFondoMouseDragged

    private void btnClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClienteMouseEntered
        if (!btnCliente.getBackground().equals(new Color(0, 0, 51))) {
            btnCliente.setBackground(new Color(53 ,77, 143));
        }
    }//GEN-LAST:event_btnClienteMouseEntered

    private void btnClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClienteMouseExited
        if (!btnCliente.getBackground().equals(new Color(0, 0, 51))) {
            btnCliente.setBackground(new Color(8, 29, 86));
        }
    }//GEN-LAST:event_btnClienteMouseExited

    private void btnClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteActionPerformed
        mostrar(0);
    }//GEN-LAST:event_btnClienteActionPerformed

    private void btnVehiculoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVehiculoMouseEntered
        if (!btnVehiculo.getBackground().equals(new Color(0, 0, 51))) {
            btnVehiculo.setBackground(new Color(53 ,77, 143));
        }
    }//GEN-LAST:event_btnVehiculoMouseEntered

    private void btnVehiculoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVehiculoMouseExited
        if (!btnVehiculo.getBackground().equals(new Color(0, 0, 51))) {
            btnVehiculo.setBackground(new Color(8, 29, 86));
        }
    }//GEN-LAST:event_btnVehiculoMouseExited

    private void btnVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVehiculoActionPerformed
        mostrar(1);
    }//GEN-LAST:event_btnVehiculoActionPerformed

    private void btnMecanicoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMecanicoMouseEntered
        if (!btnMecanico.getBackground().equals(new Color(0, 0, 51))) {
            btnMecanico.setBackground(new Color(53 ,77, 143));
        }
    }//GEN-LAST:event_btnMecanicoMouseEntered

    private void btnMecanicoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMecanicoMouseExited
        if (!btnMecanico.getBackground().equals(new Color(0, 0, 51))) {
            btnMecanico.setBackground(new Color(8, 29, 86));
        }
    }//GEN-LAST:event_btnMecanicoMouseExited

    private void btnMecanicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMecanicoActionPerformed
        mostrar(2);
    }//GEN-LAST:event_btnMecanicoActionPerformed

    private void btnOrdenMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOrdenMouseEntered
        if (!btnOrden.getBackground().equals(new Color(0, 0, 51))) {
            btnOrden.setBackground(new Color(53 ,77, 143));
        }
    }//GEN-LAST:event_btnOrdenMouseEntered

    private void btnOrdenMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOrdenMouseExited
        if (!btnOrden.getBackground().equals(new Color(0, 0, 51))) {
            btnOrden.setBackground(new Color(8, 29, 86));
        }
    }//GEN-LAST:event_btnOrdenMouseExited

    private void btnOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenActionPerformed
       mostrar(3);
    }//GEN-LAST:event_btnOrdenActionPerformed

    private void btnRepuestoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRepuestoMouseEntered
        if (!btnRepuesto.getBackground().equals(new Color(0, 0, 51))) {
            btnRepuesto.setBackground(new Color(53 ,77, 143));
        }
    }//GEN-LAST:event_btnRepuestoMouseEntered

    private void btnRepuestoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRepuestoMouseExited
        if (!btnRepuesto.getBackground().equals(new Color(0, 0, 51))) {
            btnRepuesto.setBackground(new Color(8, 29, 86));
        }
    }//GEN-LAST:event_btnRepuestoMouseExited

    private void btnRepuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRepuestoActionPerformed
        mostrar(4);
    }//GEN-LAST:event_btnRepuestoActionPerformed

    private void btnUtilizadoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUtilizadoMouseEntered
        if (!btnUtilizado.getBackground().equals(new Color(0, 0, 51))) {
            btnUtilizado.setBackground(new Color(53 ,77, 143));
        }
    }//GEN-LAST:event_btnUtilizadoMouseEntered

    private void btnUtilizadoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUtilizadoMouseExited
        if (!btnUtilizado.getBackground().equals(new Color(0, 0, 51))) {
            btnUtilizado.setBackground(new Color(8, 29, 86));
        }
    }//GEN-LAST:event_btnUtilizadoMouseExited

    private void btnUtilizadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUtilizadoActionPerformed
        mostrar(5);
    }//GEN-LAST:event_btnUtilizadoActionPerformed

    private void btnAbastecimientoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAbastecimientoMouseEntered
        if (!btnAbastecimiento.getBackground().equals(new Color(0, 0, 51))) {
            btnAbastecimiento.setBackground(new Color(53 ,77, 143));
        }
    }//GEN-LAST:event_btnAbastecimientoMouseEntered

    private void btnAbastecimientoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAbastecimientoMouseExited
        if (!btnAbastecimiento.getBackground().equals(new Color(0, 0, 51))) {
            btnAbastecimiento.setBackground(new Color(8, 29, 86));
        }
    }//GEN-LAST:event_btnAbastecimientoMouseExited

    private void btnAbastecimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbastecimientoActionPerformed
        mostrar(6);
    }//GEN-LAST:event_btnAbastecimientoActionPerformed

    private void btnServicioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnServicioMouseEntered
        if (!btnServicio.getBackground().equals(new Color(0, 0, 51))) {
            btnServicio.setBackground(new Color(53 ,77, 143));
        }
    }//GEN-LAST:event_btnServicioMouseEntered

    private void btnServicioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnServicioMouseExited
        if (!btnServicio.getBackground().equals(new Color(0, 0, 51))) {
            btnServicio.setBackground(new Color(8, 29, 86));
        }
    }//GEN-LAST:event_btnServicioMouseExited

    private void btnServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnServicioActionPerformed
        mostrar(7);
    }//GEN-LAST:event_btnServicioActionPerformed

    private void btnRealizadoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRealizadoMouseEntered
        if (!btnRealizado.getBackground().equals(new Color(0, 0, 51))) {
            btnRealizado.setBackground(new Color(53 ,77, 143));
        }
    }//GEN-LAST:event_btnRealizadoMouseEntered

    private void btnRealizadoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRealizadoMouseExited
        if (!btnRealizado.getBackground().equals(new Color(0, 0, 51))) {
            btnRealizado.setBackground(new Color(8, 29, 86));
        }
    }//GEN-LAST:event_btnRealizadoMouseExited

    private void btnRealizadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRealizadoActionPerformed
        mostrar(8);
    }//GEN-LAST:event_btnRealizadoActionPerformed

    private void btnGuardarCliMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarCliMouseEntered
        btnGuardarCli.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_btnGuardarCliMouseEntered

    private void btnGuardarCliMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarCliMouseExited
        btnGuardarCli.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_btnGuardarCliMouseExited

    private void btnGuardarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCliActionPerformed
        if (tableClientes.getSelectedRow() == -1) {
            aviso("Seleccione una fila para modificar");
            return;
        }

        String nombre = txtNombreCli.getText().trim();
        String direccion = txtDireccionCli.getText().trim();
        String telefono = txtTelefonoCli.getText().trim();

        if (nombre.isEmpty() || direccion.isEmpty() || telefono.isEmpty()) {
            aviso("Complete los campos vacíos");
        } else {
            String mensaje = controladorCli.modificar(idSeleccionado(tableClientes), nombre, direccion, telefono);
            aviso(mensaje);
            if (mensaje.startsWith("Modificado")) {
                cancelarCliente();
                mostrarClientes();
            }
        }

    }//GEN-LAST:event_btnGuardarCliActionPerformed

    private void btnLimpiarCliMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarCliMouseEntered
        btnLimpiarCli.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_btnLimpiarCliMouseEntered

    private void btnLimpiarCliMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarCliMouseExited
        btnLimpiarCli.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_btnLimpiarCliMouseExited

    private void btnLimpiarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarCliActionPerformed
        cancelarCliente();
    }//GEN-LAST:event_btnLimpiarCliActionPerformed

    private void btnGuardarVehMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarVehMouseEntered
        btnGuardarVeh.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_btnGuardarVehMouseEntered

    private void btnGuardarVehMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarVehMouseExited
        btnGuardarVeh.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_btnGuardarVehMouseExited

    private void btnGuardarVehActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarVehActionPerformed
        if (tableVehiculos.getSelectedRow() == -1) {
            aviso("Seleccione una fila para modificar");
            return;
        }

        String idClienteTexto = txtIdClienteVeh.getText().trim();
        String placa = txtPlacaVeh.getText().trim();
        String marca = txtMarcaVeh.getText().trim();
        String modelo = txtModeloVeh.getText().trim();
        String anioTexto = txtAnioVeh.getText().trim();
        String color = txtColorVeh.getText().trim();

        if (idClienteTexto.isEmpty() || placa.isEmpty() || marca.isEmpty() || modelo.isEmpty() || anioTexto.isEmpty() || color.isEmpty()) {
            aviso("Complete los campos vacíos");
        } else {
            try {
                int idClienteNum = Integer.parseInt(idClienteTexto);
                int anioNum = Integer.parseInt(anioTexto);
                String mensaje = controladorVeh.modificar(idSeleccionado(tableVehiculos), idClienteNum, placa, marca, modelo, anioNum, color);
                aviso(mensaje);
                if (mensaje.startsWith("Modificado")) {
                    cancelarVehiculo();
                    mostrarVehiculos();
                }
            } catch (NumberFormatException ex) {
                aviso("El ID de cliente y el año deben ser números");
            }
        }

    }//GEN-LAST:event_btnGuardarVehActionPerformed

    private void btnLimpiarVehMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarVehMouseEntered
        btnLimpiarVeh.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_btnLimpiarVehMouseEntered

    private void btnLimpiarVehMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarVehMouseExited
        btnLimpiarVeh.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_btnLimpiarVehMouseExited

    private void btnLimpiarVehActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarVehActionPerformed
        cancelarVehiculo();
    }//GEN-LAST:event_btnLimpiarVehActionPerformed

    private void btnGuardarMecMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMecMouseEntered
        btnGuardarMec.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_btnGuardarMecMouseEntered

    private void btnGuardarMecMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMecMouseExited
        btnGuardarMec.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_btnGuardarMecMouseExited

    private void btnGuardarMecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarMecActionPerformed
        if (tableMecanicos.getSelectedRow() == -1) {
            aviso("Seleccione una fila para modificar");
            return;
        }

        String nombre = txtNombreMec.getText().trim();
        String especialidad = txtEspecialidadMec.getText().trim();
        String telefono = txtTelefonoMec.getText().trim();

        if (nombre.isEmpty() || especialidad.isEmpty() || telefono.isEmpty()) {
            aviso("Complete los campos vacíos");
        } else {
            String mensaje = controladorMec.modificar(idSeleccionado(tableMecanicos), nombre, especialidad, telefono);
            aviso(mensaje);
            if (mensaje.startsWith("Modificado")) {
                cancelarMecanico();
                mostrarMecanicos();
            }
        }

    }//GEN-LAST:event_btnGuardarMecActionPerformed

    private void btnLimpiarMecMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarMecMouseEntered
        btnLimpiarMec.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_btnLimpiarMecMouseEntered

    private void btnLimpiarMecMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarMecMouseExited
        btnLimpiarMec.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_btnLimpiarMecMouseExited

    private void btnLimpiarMecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarMecActionPerformed
        cancelarMecanico();
    }//GEN-LAST:event_btnLimpiarMecActionPerformed

    private void btnGuardarOrdMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarOrdMouseEntered
        btnGuardarOrd.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_btnGuardarOrdMouseEntered

    private void btnGuardarOrdMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarOrdMouseExited
        btnGuardarOrd.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_btnGuardarOrdMouseExited

    private void btnGuardarOrdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarOrdActionPerformed
        if (tableOrdenes.getSelectedRow() == -1) {
            aviso("Seleccione una fila para modificar");
            return;
        }

        if ("ELIMINAR".equals(comboEstado.getSelectedItem())) {
            if (JOptionPane.showConfirmDialog(this, "¿Eliminar esta venta pendiente?",
                    "Eliminar venta", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) return;
            String mensaje = controladorOrd.eliminar(idSeleccionado(tableOrdenes));
            aviso(mensaje);
            if (mensaje.startsWith("Eliminado")) {
                cancelarOrden();
                mostrarOrdenes();
            }
            return;
        }

        String idVehiculoTexto = txtIdVehiculoOrd.getText().trim();
        String idMecanicoTexto = txtIdMecanicoOrd.getText().trim();
        String descripcion = txtDescripcionOrd.getText().trim();

        if (idVehiculoTexto.isEmpty() || idMecanicoTexto.isEmpty() || descripcion.isEmpty()) {
            aviso("Complete los campos vacíos");
        } else {
            try {
                int idVehiculoNum = Integer.parseInt(idVehiculoTexto);
                int idMecanicoNum = Integer.parseInt(idMecanicoTexto);
                int id = idSeleccionado(tableOrdenes);
                String mensaje = controladorOrd.modificar(id, idVehiculoNum, idMecanicoNum, descripcion);
                if (mensaje.startsWith("Modificado") && comboEstado.getSelectedIndex() > 0) {
                    mensaje = controladorOrd.cambiarEstado(id, String.valueOf(comboEstado.getSelectedItem()));
                }
                aviso(mensaje);
                if (mensaje.startsWith("Modificado")) {
                    cancelarOrden();
                    mostrarOrdenes();
                }
            } catch (NumberFormatException ex) {
                aviso("Los ID de pedido y empleado deben ser números");
            }
        }
    }//GEN-LAST:event_btnGuardarOrdActionPerformed

    private void btnLimpiarOrdMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarOrdMouseEntered
        btnLimpiarOrd.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_btnLimpiarOrdMouseEntered

    private void btnLimpiarOrdMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarOrdMouseExited
        btnLimpiarOrd.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_btnLimpiarOrdMouseExited

    private void btnLimpiarOrdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarOrdActionPerformed
        cancelarOrden();
    }//GEN-LAST:event_btnLimpiarOrdActionPerformed

    private void btnGuardarRepMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarRepMouseEntered
        btnGuardarRep.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_btnGuardarRepMouseEntered

    private void btnGuardarRepMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarRepMouseExited
        btnGuardarRep.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_btnGuardarRepMouseExited

    private void btnGuardarRepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarRepActionPerformed
        if (tableRepuestos.getSelectedRow() == -1) {
            aviso("Seleccione una fila para modificar");
            return;
        }

        String nombre = txtNombreRep.getText().trim();
        String descripcion = txtDescripcionRep.getText().trim();
        String precioTexto = txtPrecioRep.getText().trim();

        if (nombre.isEmpty() || descripcion.isEmpty() || precioTexto.isEmpty()) {
            aviso("Complete los campos vacíos");
        } else {
            try {
                double precioNum = Double.parseDouble(precioTexto);
                String mensaje = controladorRep.modificar(idSeleccionado(tableRepuestos), nombre, descripcion, precioNum);
                aviso(mensaje);
                if (mensaje.startsWith("Modificado")) {
                    cancelarRepuesto();
                    mostrarRepuestos();
                }
            } catch (NumberFormatException ex) {
                aviso("El precio debe ser un número");
            }
        }

    }//GEN-LAST:event_btnGuardarRepActionPerformed

    private void btnLimpiarRepMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarRepMouseEntered
        btnLimpiarRep.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_btnLimpiarRepMouseEntered

    private void btnLimpiarRepMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarRepMouseExited
        btnLimpiarRep.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_btnLimpiarRepMouseExited

    private void btnLimpiarRepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarRepActionPerformed
        cancelarRepuesto();
    }//GEN-LAST:event_btnLimpiarRepActionPerformed

    private void btnGuardarUtiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarUtiMouseEntered
        btnGuardarUti.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_btnGuardarUtiMouseEntered

    private void btnGuardarUtiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarUtiMouseExited
        btnGuardarUti.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_btnGuardarUtiMouseExited

    private void btnGuardarUtiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarUtiActionPerformed
        if (tableUtilizados.getSelectedRow() == -1) {
            aviso("Seleccione una fila para modificar");
            return;
        }

        String cantidadTexto = txtCantidadUti.getText().trim();

        if (cantidadTexto.isEmpty()) {
            aviso("Complete los campos vacíos");
        } else {
            try {
                int cantidadNum = Integer.parseInt(cantidadTexto);
                String mensaje = controladorUti.modificar(idSeleccionado(tableUtilizados), cantidadNum);
                aviso(mensaje);
                if (mensaje.startsWith("Modificado")) {
                    cancelarUtilizado();
                    mostrarUtilizados();
                }
            } catch (NumberFormatException ex) {
                aviso("La cantidad debe ser un número");
            }
        }

    }//GEN-LAST:event_btnGuardarUtiActionPerformed

    private void btnLimpiarUtiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarUtiMouseEntered
        btnLimpiarUti.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_btnLimpiarUtiMouseEntered

    private void btnLimpiarUtiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarUtiMouseExited
        btnLimpiarUti.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_btnLimpiarUtiMouseExited

    private void btnLimpiarUtiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarUtiActionPerformed
        cancelarUtilizado();
    }//GEN-LAST:event_btnLimpiarUtiActionPerformed

    private void btnGuardarAbaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarAbaMouseEntered
        btnGuardarAba.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_btnGuardarAbaMouseEntered

    private void btnGuardarAbaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarAbaMouseExited
        btnGuardarAba.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_btnGuardarAbaMouseExited

    private void btnGuardarAbaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarAbaActionPerformed
        if (tableAbastecimiento.getSelectedRow() == -1) {
            aviso("Seleccione una fila para modificar");
            return;
        }

        String cantidadTexto = txtCantidadAba.getText().trim();

        if (cantidadTexto.isEmpty()) {
            aviso("Complete los campos vacíos");
        } else {
            try {
                int cantidadNum = Integer.parseInt(cantidadTexto);
                String mensaje = controladorAba.modificar(idSeleccionado(tableAbastecimiento), cantidadNum);
                aviso(mensaje);
                if (mensaje.startsWith("Modificado")) {
                    cancelarAbastecimiento();
                    mostrarAbastecimientos();
                }
            } catch (NumberFormatException ex) {
                aviso("La cantidad debe ser un número");
            }
        }

    }//GEN-LAST:event_btnGuardarAbaActionPerformed

    private void btnLimpiarAbaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarAbaMouseEntered
        btnLimpiarAba.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_btnLimpiarAbaMouseEntered

    private void btnLimpiarAbaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarAbaMouseExited
        btnLimpiarAba.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_btnLimpiarAbaMouseExited

    private void btnLimpiarAbaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarAbaActionPerformed
        cancelarAbastecimiento();
    }//GEN-LAST:event_btnLimpiarAbaActionPerformed

    private void btnGuardarSerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarSerMouseEntered
        btnGuardarSer.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_btnGuardarSerMouseEntered

    private void btnGuardarSerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarSerMouseExited
        btnGuardarSer.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_btnGuardarSerMouseExited

    private void btnGuardarSerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarSerActionPerformed
        if (tableServicios.getSelectedRow() == -1) {
            aviso("Seleccione una fila para modificar");
            return;
        }

        String descripcion = txtDescripcionSer.getText().trim();
        String costoTexto = txtCostoSer.getText().trim();

        if (descripcion.isEmpty() || costoTexto.isEmpty()) {
            aviso("Complete los campos vacíos");
        } else {
            try {
                double costoNum = Double.parseDouble(costoTexto);
                String mensaje = controladorSer.modificar(idSeleccionado(tableServicios), descripcion, costoNum);
                aviso(mensaje);
                if (mensaje.startsWith("Modificado")) {
                    cancelarServicio();
                    mostrarServicios();
                }
            } catch (NumberFormatException ex) {
                aviso("El costo debe ser un número");
            }
        }

    }//GEN-LAST:event_btnGuardarSerActionPerformed

    private void btnLimpiarSerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarSerMouseEntered
        btnLimpiarSer.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_btnLimpiarSerMouseEntered

    private void btnLimpiarSerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarSerMouseExited
        btnLimpiarSer.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_btnLimpiarSerMouseExited

    private void btnLimpiarSerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarSerActionPerformed
        cancelarServicio();
    }//GEN-LAST:event_btnLimpiarSerActionPerformed

    private void btnGuardarReaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarReaMouseEntered
        btnGuardarRea.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_btnGuardarReaMouseEntered

    private void btnGuardarReaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarReaMouseExited
        btnGuardarRea.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_btnGuardarReaMouseExited

    private void btnGuardarReaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarReaActionPerformed
        if (tableRealizados.getSelectedRow() == -1) {
            aviso("Seleccione una fila para modificar");
            return;
        }

        String cantidadTexto = txtCantidadRea.getText().trim();

        if (cantidadTexto.isEmpty()) {
            aviso("Complete los campos vacíos");
        } else {
            try {
                int cantidadNum = Integer.parseInt(cantidadTexto);
                String mensaje = controladorRea.modificar(idSeleccionado(tableRealizados), cantidadNum);
                aviso(mensaje);
                if (mensaje.startsWith("Modificado")) {
                    cancelarRealizado();
                    mostrarRealizados();
                }
            } catch (NumberFormatException ex) {
                aviso("La cantidad debe ser un número");
            }
        }

    }//GEN-LAST:event_btnGuardarReaActionPerformed

    private void btnLimpiarReaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarReaMouseEntered
        btnLimpiarRea.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_btnLimpiarReaMouseEntered

    private void btnLimpiarReaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarReaMouseExited
        btnLimpiarRea.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_btnLimpiarReaMouseExited

    private void btnLimpiarReaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarReaActionPerformed
        cancelarRealizado();
    }//GEN-LAST:event_btnLimpiarReaActionPerformed

    private void btnEliminarCliMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarCliMouseEntered
        btnEliminarCli.setBackground(new Color(228, 55, 68));
    }//GEN-LAST:event_btnEliminarCliMouseEntered

    private void btnEliminarCliMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarCliMouseExited
        btnEliminarCli.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_btnEliminarCliMouseExited

    private void btnEliminarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCliActionPerformed
        if (tableClientes.getSelectedRow() == -1) {
            aviso("Seleccione una fila para eliminar");
            return;
        }
        int confirmar = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este registro?", "Confirmar la eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmar == JOptionPane.YES_OPTION) {
            String mensaje = controladorCli.eliminar(idSeleccionado(tableClientes));
            aviso(mensaje);
            if (mensaje.startsWith("Eliminado")) {
                cancelarCliente();
                mostrarClientes();
            }
        } else {
            aviso("El registro no fue eliminado");
        }
    }//GEN-LAST:event_btnEliminarCliActionPerformed

    private void btnEliminarVehMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarVehMouseEntered
        btnEliminarVeh.setBackground(new Color(228, 55, 68));
    }//GEN-LAST:event_btnEliminarVehMouseEntered

    private void btnEliminarVehMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarVehMouseExited
        btnEliminarVeh.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_btnEliminarVehMouseExited

    private void btnEliminarVehActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarVehActionPerformed
        if (tableVehiculos.getSelectedRow() == -1) {
            aviso("Seleccione una fila para eliminar");
            return;
        }
        int confirmar = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este registro?", "Confirmar la eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmar == JOptionPane.YES_OPTION) {
            String mensaje = controladorVeh.eliminar(idSeleccionado(tableVehiculos));
            aviso(mensaje);
            if (mensaje.startsWith("Eliminado")) {
                cancelarVehiculo();
                mostrarVehiculos();
            }
        } else {
            aviso("El registro no fue eliminado");
        }

    }//GEN-LAST:event_btnEliminarVehActionPerformed

    private void btnEliminarMecMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMecMouseEntered
        btnEliminarMec.setBackground(new Color(228, 55, 68));
    }//GEN-LAST:event_btnEliminarMecMouseEntered

    private void btnEliminarMecMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMecMouseExited
        btnEliminarMec.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_btnEliminarMecMouseExited

    private void btnEliminarMecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarMecActionPerformed
        if (tableMecanicos.getSelectedRow() == -1) {
            aviso("Seleccione una fila para eliminar");
            return;
        }
        int confirmar = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este registro?", "Confirmar la eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmar == JOptionPane.YES_OPTION) {
            String mensaje = controladorMec.eliminar(idSeleccionado(tableMecanicos));
            aviso(mensaje);
            if (mensaje.startsWith("Eliminado")) {
                cancelarMecanico();
                mostrarMecanicos();
            }
        } else {
            aviso("El registro no fue eliminado");
        }

    }//GEN-LAST:event_btnEliminarMecActionPerformed

    private void comboFiltroOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFiltroOrdenActionPerformed
        int indice = comboFiltroOrden.getSelectedIndex();
        filtroOrden(indice);

        if (indice == 0) {
            mostrarOrdenes();
        } else if (indice == 1) {
            comboBuscarEstado.setSelectedIndex(0);

            String seleccion = comboBuscarEstado.getSelectedItem().toString();
            String estado = switch (seleccion) {
                case "PENDIENTES" -> "PENDIENTE";
                default -> null;
            };

            if (estado != null) {
                ejecutarConsulta(() ->
                    cargarOrdenes(controladorReporte.porEstado(estado))
                );
            }
        }
    }//GEN-LAST:event_comboFiltroOrdenActionPerformed

    private void comboFiltroRepuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFiltroRepuestoActionPerformed
        String seleccion = comboFiltroRepuesto.getSelectedItem().toString();
        if ("Todos los productos".equals(seleccion)) {
            mostrarRepuestos();
        } else if ("Productos con bajo inventario".equals(seleccion)) {
            ejecutarConsulta(() ->
                    cargarRepuestos(controladorReporte.repuestosBajoInventario()));
        }
    }//GEN-LAST:event_comboFiltroRepuestoActionPerformed

    private void btnImportarCSVMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImportarCSVMouseEntered
       btnImportarCSV.setBackground(new Color(8, 29, 86));
    }//GEN-LAST:event_btnImportarCSVMouseEntered

    private void btnImportarCSVMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImportarCSVMouseExited
        btnImportarCSV.setBackground(new Color(0, 0, 153));
    }//GEN-LAST:event_btnImportarCSVMouseExited

    private void btnImportarCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportarCSVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnImportarCSVActionPerformed

    private void btnExportarRepMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportarRepMouseEntered
        btnExportarRep.setBackground(new Color(8, 29, 86));
    }//GEN-LAST:event_btnExportarRepMouseEntered

    private void btnExportarRepMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportarRepMouseExited
         btnExportarRep.setBackground(new Color(0, 0, 153));
    }//GEN-LAST:event_btnExportarRepMouseExited

    private void btnExportarRepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarRepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExportarRepActionPerformed

    private void btnEliminarRepMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarRepMouseEntered
        btnEliminarRep.setBackground(new Color(228, 55, 68));
    }//GEN-LAST:event_btnEliminarRepMouseEntered

    private void btnEliminarRepMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarRepMouseExited
         btnEliminarRep.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_btnEliminarRepMouseExited

    private void btnEliminarRepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarRepActionPerformed
        if (tableRepuestos.getSelectedRow() == -1) {
            aviso("Seleccione una fila para eliminar");
            return;
        }
        int confirmar = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este registro?", "Confirmar la eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmar == JOptionPane.YES_OPTION) {
            String mensaje = controladorRep.eliminar(idSeleccionado(tableRepuestos));
            aviso(mensaje);
            if (mensaje.startsWith("Eliminado")) {
                cancelarRepuesto();
                mostrarRepuestos();
            }
        } else {
            aviso("El registro no fue eliminado");
        }

    }//GEN-LAST:event_btnEliminarRepActionPerformed

    private void btnExportarOrdEstMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportarOrdEstMouseEntered
        btnExportarOrdEst.setBackground(new Color(8, 29, 86));
    }//GEN-LAST:event_btnExportarOrdEstMouseEntered

    private void btnExportarOrdEstMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportarOrdEstMouseExited
        btnExportarOrdEst.setBackground(new Color(0, 0, 153));
    }//GEN-LAST:event_btnExportarOrdEstMouseExited

    private void btnExportarOrdEstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarOrdEstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExportarOrdEstActionPerformed

    private void btnBuscarPlacaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarPlacaMouseEntered
        btnBuscarPlaca.setBackground(new Color(8, 29, 86));
    }//GEN-LAST:event_btnBuscarPlacaMouseEntered

    private void btnBuscarPlacaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarPlacaMouseExited
        btnBuscarPlaca.setBackground(new Color(0, 0, 153));
    }//GEN-LAST:event_btnBuscarPlacaMouseExited

    private void btnBuscarPlacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPlacaActionPerformed
        String placa = txtBuscarPlaca.getText().trim();
        if (placa.isEmpty()) {
            return;
        }
        ejecutarConsulta(() -> cargarOrdenes(controladorReporte.porPlaca(placa)));
    }//GEN-LAST:event_btnBuscarPlacaActionPerformed

    private void btnExportarOrdPlaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportarOrdPlaMouseEntered
        btnExportarOrdPla.setBackground(new Color(8, 29, 86));
    }//GEN-LAST:event_btnExportarOrdPlaMouseEntered

    private void btnExportarOrdPlaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportarOrdPlaMouseExited
        btnExportarOrdPla.setBackground(new Color(0, 0, 153));
    }//GEN-LAST:event_btnExportarOrdPlaMouseExited

    private void btnExportarOrdPlaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarOrdPlaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExportarOrdPlaActionPerformed

    private void btnBuscarMecanicoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarMecanicoMouseEntered
        btnBuscarMecanico.setBackground(new Color(8, 29, 86));
    }//GEN-LAST:event_btnBuscarMecanicoMouseEntered

    private void btnBuscarMecanicoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarMecanicoMouseExited
        btnBuscarMecanico.setBackground(new Color(0, 0, 153));
    }//GEN-LAST:event_btnBuscarMecanicoMouseExited

    private void btnBuscarMecanicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarMecanicoActionPerformed
        String nombre = txtBuscarMecanico.getText().trim();
        if (nombre.isEmpty()) {
            return;
        }
        ejecutarConsulta(() -> cargarOrdenes(controladorReporte.porMecanico(nombre)));
    }//GEN-LAST:event_btnBuscarMecanicoActionPerformed

    private void btnExportarOrdMecMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportarOrdMecMouseEntered
        btnExportarOrdMec.setBackground(new Color(8, 29, 86));
    }//GEN-LAST:event_btnExportarOrdMecMouseEntered

    private void btnExportarOrdMecMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportarOrdMecMouseExited
        btnExportarOrdMec.setBackground(new Color(0, 0, 153));
    }//GEN-LAST:event_btnExportarOrdMecMouseExited

    private void btnExportarOrdMecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarOrdMecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExportarOrdMecActionPerformed

    private void btnBuscarFechaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarFechaMouseEntered
        btnBuscarFecha.setBackground(new Color(8, 29, 86));
    }//GEN-LAST:event_btnBuscarFechaMouseEntered

    private void btnBuscarFechaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarFechaMouseExited
        btnBuscarFecha.setBackground(new Color(0, 0, 153));
    }//GEN-LAST:event_btnBuscarFechaMouseExited

    private void btnBuscarFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarFechaActionPerformed
        String inicio = txtFechaInicio.getText().trim();
        String fin = txtFechaFinal.getText().trim();
        try {
            java.time.LocalDate.parse(inicio);
            java.time.LocalDate.parse(fin);
        } catch (java.time.format.DateTimeParseException e) {
            javax.swing.JOptionPane.showMessageDialog(
                    this, "Ingresa las fechas en formato yyyy-MM-dd.");
            return;
        }
        ejecutarConsulta(() -> cargarOrdenes(controladorReporte.porFechas(inicio, fin)));
    }//GEN-LAST:event_btnBuscarFechaActionPerformed

    private void btnExportarOrdIngMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportarOrdIngMouseEntered
        btnExportarOrdIng.setBackground(new Color(8, 29, 86));
    }//GEN-LAST:event_btnExportarOrdIngMouseEntered

    private void btnExportarOrdIngMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportarOrdIngMouseExited
        btnExportarOrdIng.setBackground(new Color(0, 0, 153));
    }//GEN-LAST:event_btnExportarOrdIngMouseExited

    private void btnExportarOrdIngActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarOrdIngActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExportarOrdIngActionPerformed

    private void btnEliminarUtiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarUtiMouseEntered
        btnEliminarUti.setBackground(new Color(228, 55, 68));
    }//GEN-LAST:event_btnEliminarUtiMouseEntered

    private void btnEliminarUtiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarUtiMouseExited
        btnEliminarUti.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_btnEliminarUtiMouseExited

    private void btnEliminarUtiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUtiActionPerformed
        if (tableUtilizados.getSelectedRow() == -1) {
            aviso("Seleccione una fila para eliminar");
            return;
        }
        int confirmar = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este registro?", "Confirmar la eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmar == JOptionPane.YES_OPTION) {
            String mensaje = controladorUti.eliminar(idSeleccionado(tableUtilizados));
            aviso(mensaje);
            if (mensaje.startsWith("Eliminado")) {
                cancelarUtilizado();
                mostrarUtilizados();
            }
        } else {
            aviso("El registro no fue eliminado");
        }
    }//GEN-LAST:event_btnEliminarUtiActionPerformed

    private void btnEliminarAbaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarAbaMouseEntered
        btnEliminarAba.setBackground(new Color(228, 55, 68));
    }//GEN-LAST:event_btnEliminarAbaMouseEntered

    private void btnEliminarAbaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarAbaMouseExited
        btnEliminarAba.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_btnEliminarAbaMouseExited

    private void btnEliminarAbaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarAbaActionPerformed
        if (tableAbastecimiento.getSelectedRow() == -1) {
            aviso("Seleccione una fila para eliminar");
            return;
        }
        int confirmar = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este registro?", "Confirmar la eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmar == JOptionPane.YES_OPTION) {
            String mensaje = controladorAba.eliminar(idSeleccionado(tableAbastecimiento));
            aviso(mensaje);
            if (mensaje.startsWith("Eliminado")) {
                cancelarAbastecimiento();
                mostrarAbastecimientos();
            }
        } else {
            aviso("El registro no fue eliminado");
        }
    }//GEN-LAST:event_btnEliminarAbaActionPerformed

    private void btnEliminarSerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarSerMouseEntered
        btnEliminarSer.setBackground(new Color(228, 55, 68));
    }//GEN-LAST:event_btnEliminarSerMouseEntered

    private void btnEliminarSerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarSerMouseExited
        btnEliminarSer.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_btnEliminarSerMouseExited

    private void btnEliminarSerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarSerActionPerformed
        if (tableServicios.getSelectedRow() == -1) {
            aviso("Seleccione una fila para eliminar");
            return;
        }
        int confirmar = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este registro?", "Confirmar la eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmar == JOptionPane.YES_OPTION) {
            String mensaje = controladorSer.eliminar(idSeleccionado(tableServicios));
            aviso(mensaje);
            if (mensaje.startsWith("Eliminado")) {
                cancelarServicio();
                mostrarServicios();
            }
        } else {
            aviso("El registro no fue eliminado");
        }

    }//GEN-LAST:event_btnEliminarSerActionPerformed

    private void btnEliminarReaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarReaMouseEntered
        btnEliminarRea.setBackground(new Color(228, 55, 68));
    }//GEN-LAST:event_btnEliminarReaMouseEntered

    private void btnEliminarReaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarReaMouseExited
        btnEliminarRea.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_btnEliminarReaMouseExited

    private void btnEliminarReaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarReaActionPerformed
    if (tableRealizados.getSelectedRow() == -1) {
        aviso("Seleccione una fila para eliminar");
        return;
    }
    int confirmar = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este registro?", "Confirmar la eliminación", JOptionPane.YES_NO_OPTION);
    if (confirmar == JOptionPane.YES_OPTION) {
        String mensaje = controladorRea.eliminar(idSeleccionado(tableRealizados));
        aviso(mensaje);
        if (mensaje.startsWith("Eliminado")) {
            cancelarRealizado();
            mostrarRealizados();
        }
    } else {
        aviso("El registro no fue eliminado");
    }

    }//GEN-LAST:event_btnEliminarReaActionPerformed

    private void tableClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableClientesMouseClicked
        int fila = tableClientes.getSelectedRow();
        if (fila == -1) {
            return;
        }
        labelCliente.setText("Cliente ID: " + tableClientes.getValueAt(fila, 0));
        txtNombreCli.setText(String.valueOf(tableClientes.getValueAt(fila, 1)));
        txtDireccionCli.setText(String.valueOf(tableClientes.getValueAt(fila, 2)));
        txtTelefonoCli.setText(String.valueOf(tableClientes.getValueAt(fila, 3)));
        formularioCliente.setVisible(true);
    }//GEN-LAST:event_tableClientesMouseClicked

    private void tableVehiculosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableVehiculosMouseClicked
        int fila = tableVehiculos.getSelectedRow();
            if (fila == -1) {
                return;
            }
            labelVehiculo.setText("Pedido ID: " + tableVehiculos.getValueAt(fila, 0));
            txtIdClienteVeh.setText(String.valueOf(tableVehiculos.getValueAt(fila, 1)));
            txtPlacaVeh.setText(String.valueOf(tableVehiculos.getValueAt(fila, 2)));
            txtMarcaVeh.setText(String.valueOf(tableVehiculos.getValueAt(fila, 3)));
            txtModeloVeh.setText(String.valueOf(tableVehiculos.getValueAt(fila, 4)));
            txtAnioVeh.setText(String.valueOf(tableVehiculos.getValueAt(fila, 5)));
            txtColorVeh.setText(String.valueOf(tableVehiculos.getValueAt(fila, 6)));
            formularioVehiculo.setVisible(true);
    }//GEN-LAST:event_tableVehiculosMouseClicked

    private void tableMecanicosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMecanicosMouseClicked
        int fila = tableMecanicos.getSelectedRow();
        if (fila == -1) {
            return;
        }
        labelMecanico.setText("Empleado ID: " + tableMecanicos.getValueAt(fila, 0));
        txtNombreMec.setText(String.valueOf(tableMecanicos.getValueAt(fila, 1)));
        txtEspecialidadMec.setText(String.valueOf(tableMecanicos.getValueAt(fila, 2)));
        txtTelefonoMec.setText(String.valueOf(tableMecanicos.getValueAt(fila, 3)));
        formularioMecanico.setVisible(true);
    }//GEN-LAST:event_tableMecanicosMouseClicked

    private void tableOrdenesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableOrdenesMouseClicked
        int fila = tableOrdenes.getSelectedRow();
        if (fila == -1) {
            return;
        }
        String estado = String.valueOf(tableOrdenes.getValueAt(fila, 6));
        boolean editable = estado.equals("PENDIENTE") || estado.equals("EN PROCESO");
        labelOrden.setText("Venta ID: " + tableOrdenes.getValueAt(fila, 0));
        txtIdVehiculoOrd.setText(String.valueOf(tableOrdenes.getValueAt(fila, 1)));
        txtIdMecanicoOrd.setText(String.valueOf(tableOrdenes.getValueAt(fila, 2)));
        txtDescripcionOrd.setText(String.valueOf(tableOrdenes.getValueAt(fila, 3)));
        txtIdVehiculoOrd.setEditable(estado.equals("PENDIENTE"));
        txtIdMecanicoOrd.setEditable(editable);
        txtDescripcionOrd.setEditable(editable);
        btnGuardarOrd.setEnabled(editable);
        if (estado.equals("PENDIENTE")) {
            comboEstado.setModel(new DefaultComboBoxModel<>(new String[]{"PENDIENTE", "INICIAR", "CANCELAR", "ELIMINAR"}));
        } else if (estado.equals("EN PROCESO")) {
            comboEstado.setModel(new DefaultComboBoxModel<>(new String[]{"EN PROCESO", "FINALIZAR", "CANCELAR"}));
        } else {
            comboEstado.setModel(new DefaultComboBoxModel<>(new String[]{estado}));
        }
        formularioOrden.setVisible(true);

    }//GEN-LAST:event_tableOrdenesMouseClicked

    private void tableRepuestosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableRepuestosMouseClicked
        int fila = tableRepuestos.getSelectedRow();
        if (fila == -1) {
            return;
        }
        labelRepuesto.setText("Producto ID: " + tableRepuestos.getValueAt(fila, 0));
        txtNombreRep.setText(String.valueOf(tableRepuestos.getValueAt(fila, 1)));
        txtDescripcionRep.setText(String.valueOf(tableRepuestos.getValueAt(fila, 2)));
        txtPrecioRep.setText(String.valueOf(tableRepuestos.getValueAt(fila, 3)));
        formularioRepuesto.setVisible(true);
    }//GEN-LAST:event_tableRepuestosMouseClicked

    private void tableUtilizadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableUtilizadosMouseClicked
        int fila = tableUtilizados.getSelectedRow();
        if (fila == -1) {
            return;
        }
        labelUtilizado.setText("Producto Vendido ID: " + tableUtilizados.getValueAt(fila, 0));
        txtIdOrdenUti.setText(String.valueOf(tableUtilizados.getValueAt(fila, 1)));
        txtIdRepuestoUti.setText(String.valueOf(tableUtilizados.getValueAt(fila, 2)));
        txtCantidadUti.setText(String.valueOf(tableUtilizados.getValueAt(fila, 3)));
        formularioUtilizado.setVisible(true);

    }//GEN-LAST:event_tableUtilizadosMouseClicked

    private void tableAbastecimientoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableAbastecimientoMouseClicked
        int fila = tableAbastecimiento.getSelectedRow();
        if (fila == -1) {
            return;
        }
        labelAbastecimiento.setText("Abastecimiento ID: " + tableAbastecimiento.getValueAt(fila, 0));
        txtIdRepuestoAba.setText(String.valueOf(tableAbastecimiento.getValueAt(fila, 1)));
        txtCantidadAba.setText(String.valueOf(tableAbastecimiento.getValueAt(fila, 2)));
        formularioAbastecimiento.setVisible(true);
    }//GEN-LAST:event_tableAbastecimientoMouseClicked

    private void tableServiciosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableServiciosMouseClicked
        int fila = tableServicios.getSelectedRow();
        if (fila == -1) {
            return;
        }
        labelServicio.setText("Servicio ID: " + tableServicios.getValueAt(fila, 0));
        txtDescripcionSer.setText(String.valueOf(tableServicios.getValueAt(fila, 1)));
        txtCostoSer.setText(String.valueOf(tableServicios.getValueAt(fila, 2)));
        formularioServicio.setVisible(true);
    }//GEN-LAST:event_tableServiciosMouseClicked

    private void tableRealizadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableRealizadosMouseClicked
        int fila = tableRealizados.getSelectedRow();
        if (fila == -1) {
            return;
        }
        labelRealizado.setText("Servicio Realizado ID: " + tableRealizados.getValueAt(fila, 0));
        txtIdOrdenRea.setText(String.valueOf(tableRealizados.getValueAt(fila, 1)));
        txtIdServicioRea.setText(String.valueOf(tableRealizados.getValueAt(fila, 2)));
        txtCantidadRea.setText(String.valueOf(tableRealizados.getValueAt(fila, 3)));
        formularioRealizado.setVisible(true);
    }//GEN-LAST:event_tableRealizadosMouseClicked

    private void comboBuscarEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBuscarEstadoActionPerformed
        String seleccion = comboBuscarEstado.getSelectedItem().toString();
        String estado = switch (seleccion) {
            case "PENDIENTES" -> "PENDIENTE";
            case "EN PROCESO" -> "EN PROCESO";
            case "FINALIZADAS" -> "FINALIZADA";
            case "CANCELADAS" -> "CANCELADA";
            default -> null;
        };

        if (estado != null) {
            ejecutarConsulta(() ->
                cargarOrdenes(controladorReporte.porEstado(estado))
            );
        }
    }//GEN-LAST:event_comboBuscarEstadoActionPerformed


    public static void main(String args[]) {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConsultarRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultarRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultarRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultarRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultarRegistro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swingx.RoundButton btnAbastecimiento;
    private swingx.RoundButton btnBuscarFecha;
    private swingx.RoundButton btnBuscarMecanico;
    private swingx.RoundButton btnBuscarPlaca;
    private swingx.RoundButton btnCliente;
    private swingx.RoundButton btnEliminarAba;
    private swingx.RoundButton btnEliminarCli;
    private swingx.RoundButton btnEliminarMec;
    private swingx.RoundButton btnEliminarRea;
    private swingx.RoundButton btnEliminarRep;
    private swingx.RoundButton btnEliminarSer;
    private swingx.RoundButton btnEliminarUti;
    private swingx.RoundButton btnEliminarVeh;
    private swingx.RoundButton btnExportarOrdEst;
    private swingx.RoundButton btnExportarOrdIng;
    private swingx.RoundButton btnExportarOrdMec;
    private swingx.RoundButton btnExportarOrdPla;
    private swingx.RoundButton btnExportarRep;
    private swingx.RoundButton btnGuardarAba;
    private swingx.RoundButton btnGuardarCli;
    private swingx.RoundButton btnGuardarMec;
    private swingx.RoundButton btnGuardarOrd;
    private swingx.RoundButton btnGuardarRea;
    private swingx.RoundButton btnGuardarRep;
    private swingx.RoundButton btnGuardarSer;
    private swingx.RoundButton btnGuardarUti;
    private swingx.RoundButton btnGuardarVeh;
    private swingx.RoundButton btnImportarCSV;
    private swingx.RoundButton btnLimpiarAba;
    private swingx.RoundButton btnLimpiarCli;
    private swingx.RoundButton btnLimpiarMec;
    private swingx.RoundButton btnLimpiarOrd;
    private swingx.RoundButton btnLimpiarRea;
    private swingx.RoundButton btnLimpiarRep;
    private swingx.RoundButton btnLimpiarSer;
    private swingx.RoundButton btnLimpiarUti;
    private swingx.RoundButton btnLimpiarVeh;
    private swingx.RoundButton btnMecanico;
    private swingx.RoundButton btnMinimizar;
    private swingx.RoundButton btnOrden;
    private swingx.RoundButton btnRealizado;
    private swingx.RoundButton btnRegresar;
    private swingx.RoundButton btnRepuesto;
    private swingx.RoundButton btnServicio;
    private swingx.RoundButton btnUtilizado;
    private swingx.RoundButton btnVehiculo;
    private javax.swing.JComboBox<String> comboBuscarEstado;
    private javax.swing.JComboBox<String> comboEstado;
    private javax.swing.JComboBox<String> comboFiltroOrden;
    private javax.swing.JComboBox<String> comboFiltroRepuesto;
    private swingx.RoundPanel formularioAbastecimiento;
    private swingx.RoundPanel formularioCliente;
    private swingx.RoundPanel formularioMecanico;
    private swingx.RoundPanel formularioOrden;
    private swingx.RoundPanel formularioRealizado;
    private swingx.RoundPanel formularioRepuesto;
    private swingx.RoundPanel formularioServicio;
    private swingx.RoundPanel formularioUtilizado;
    private swingx.RoundPanel formularioVehiculo;
    private swingx.RoundLabel labelAbastecimiento;
    private swingx.RoundLabel labelAnioVeh;
    private swingx.RoundLabel labelCantidadAba;
    private swingx.RoundLabel labelCantidadRea;
    private swingx.RoundLabel labelCantidadUti;
    private swingx.RoundLabel labelCliente;
    private swingx.RoundLabel labelColorVeh;
    private swingx.RoundLabel labelCostoSer;
    private swingx.RoundLabel labelDescripcionOrd;
    private swingx.RoundLabel labelDescripcionRep;
    private swingx.RoundLabel labelDescripcionSer;
    private swingx.RoundLabel labelDireccionCli;
    private swingx.RoundLabel labelEspecialidadMec;
    private swingx.RoundLabel labelEstado;
    private swingx.RoundLabel labelIdClienteVeh;
    private swingx.RoundLabel labelIdMecanicoOrd;
    private swingx.RoundLabel labelIdOrdenRea;
    private swingx.RoundLabel labelIdOrdenUti;
    private swingx.RoundLabel labelIdRepuestoAba;
    private swingx.RoundLabel labelIdRepuestoUti;
    private swingx.RoundLabel labelIdServicioRea;
    private swingx.RoundLabel labelIdVehiculoOrd;
    private swingx.RoundLabel labelIniciales;
    private swingx.RoundLabel labelLogo;
    private swingx.RoundLabel labelMarcaVeh;
    private swingx.RoundLabel labelMecanico;
    private swingx.RoundLabel labelModelo;
    private swingx.RoundLabel labelNombreCli;
    private swingx.RoundLabel labelNombreMec;
    private swingx.RoundLabel labelNombreRep;
    private swingx.RoundLabel labelNombreRep1;
    private swingx.RoundLabel labelNombreRep2;
    private swingx.RoundLabel labelOrden;
    private swingx.RoundLabel labelPlacaVeh;
    private swingx.RoundLabel labelPrecioRep;
    private swingx.RoundLabel labelRealizado;
    private swingx.RoundLabel labelRepuesto;
    private swingx.RoundLabel labelServicio;
    private swingx.RoundLabel labelTelefonoCli;
    private swingx.RoundLabel labelTelefonoMec;
    private swingx.RoundLabel labelTituloAba;
    private swingx.RoundLabel labelTituloCli;
    private swingx.RoundLabel labelTituloMec;
    private swingx.RoundLabel labelTituloOrdEst;
    private swingx.RoundLabel labelTituloOrdEst1;
    private swingx.RoundLabel labelTituloOrdMec;
    private swingx.RoundLabel labelTituloOrdMec1;
    private swingx.RoundLabel labelTituloOrdPla;
    private swingx.RoundLabel labelTituloRea;
    private swingx.RoundLabel labelTituloRep;
    private swingx.RoundLabel labelTituloSer;
    private swingx.RoundLabel labelTituloUti;
    private swingx.RoundLabel labelTituloVeh;
    private swingx.RoundLabel labelUtilizado;
    private swingx.RoundLabel labelVehiculo;
    private swingx.RoundPanel panelAbastecimiento;
    private swingx.RoundPanel panelBuscaAbastecimiento;
    private swingx.RoundPanel panelBuscaCliente;
    private swingx.RoundPanel panelBuscaMecanico;
    private swingx.RoundPanel panelBuscaOrden;
    private swingx.RoundPanel panelBuscaOrdenEst;
    private swingx.RoundPanel panelBuscaOrdenIng;
    private swingx.RoundPanel panelBuscaOrdenMec;
    private swingx.RoundPanel panelBuscaOrdenPla;
    private swingx.RoundPanel panelBuscaRealizado;
    private swingx.RoundPanel panelBuscaRepuesto;
    private swingx.RoundPanel panelBuscaServicio;
    private swingx.RoundPanel panelBuscaUtilizado;
    private swingx.RoundPanel panelBuscaVehiculo;
    private swingx.RoundPanel panelCardBuscador;
    private swingx.RoundPanel panelCardTabla;
    private swingx.RoundPanel panelCliente;
    private javax.swing.JPanel panelFondo;
    private swingx.RoundPanel panelMecanico;
    private swingx.RoundPanel panelMenu;
    private swingx.RoundPanel panelOrden;
    private swingx.RoundPanel panelRealizado;
    private swingx.RoundPanel panelRepuesto;
    private swingx.RoundPanel panelServicio;
    private swingx.RoundPanel panelUtilizado;
    private swingx.RoundPanel panelVehiculo;
    private javax.swing.JScrollPane scrollAbastecimiento;
    private javax.swing.JScrollPane scrollClientes;
    private javax.swing.JScrollPane scrollMecanicos;
    private javax.swing.JScrollPane scrollOrdenes;
    private javax.swing.JScrollPane scrollRealizados;
    private javax.swing.JScrollPane scrollRepuestos;
    private javax.swing.JScrollPane scrollServicios;
    private javax.swing.JScrollPane scrollUtilizados;
    private javax.swing.JScrollPane scrollVehiculos;
    private javax.swing.JTable tableAbastecimiento;
    private javax.swing.JTable tableClientes;
    private javax.swing.JTable tableMecanicos;
    private javax.swing.JTable tableOrdenes;
    private javax.swing.JTable tableRealizados;
    private javax.swing.JTable tableRepuestos;
    private javax.swing.JTable tableServicios;
    private javax.swing.JTable tableUtilizados;
    private javax.swing.JTable tableVehiculos;
    private javax.swing.JTextField txtAnioVeh;
    private javax.swing.JTextField txtBuscarMecanico;
    private javax.swing.JTextField txtBuscarPlaca;
    private javax.swing.JTextField txtCantidadAba;
    private javax.swing.JTextField txtCantidadRea;
    private javax.swing.JTextField txtCantidadUti;
    private javax.swing.JTextField txtColorVeh;
    private javax.swing.JTextField txtCostoSer;
    private javax.swing.JTextField txtDescripcionOrd;
    private javax.swing.JTextField txtDescripcionRep;
    private javax.swing.JTextField txtDescripcionSer;
    private javax.swing.JTextField txtDireccionCli;
    private javax.swing.JTextField txtEspecialidadMec;
    private javax.swing.JTextField txtFechaFinal;
    private javax.swing.JTextField txtFechaInicio;
    private javax.swing.JTextField txtIdClienteVeh;
    private javax.swing.JTextField txtIdMecanicoOrd;
    private javax.swing.JTextField txtIdOrdenRea;
    private javax.swing.JTextField txtIdOrdenUti;
    private javax.swing.JTextField txtIdRepuestoAba;
    private javax.swing.JTextField txtIdRepuestoUti;
    private javax.swing.JTextField txtIdServicioRea;
    private javax.swing.JTextField txtIdVehiculoOrd;
    private javax.swing.JTextField txtMarcaVeh;
    private javax.swing.JTextField txtModeloVeh;
    private javax.swing.JTextField txtNombreCli;
    private javax.swing.JTextField txtNombreMec;
    private javax.swing.JTextField txtNombreRep;
    private javax.swing.JTextField txtPlacaVeh;
    private javax.swing.JTextField txtPrecioRep;
    private javax.swing.JTextField txtTelefonoCli;
    private javax.swing.JTextField txtTelefonoMec;
    // End of variables declaration//GEN-END:variables
}
