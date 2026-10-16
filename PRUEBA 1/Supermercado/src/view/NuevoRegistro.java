package view;

import controller.AbastecimientoController;
import controller.ClienteController;
import controller.EmpleadoController;
import controller.VentaController;
import controller.ProductoController;
import controller.DetalleVentaController;
import controller.ServicioController;
import controller.ServicioVentaController;
import controller.PedidoController;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import swingx.RoundButton;
import swingx.RoundPanel;

public class NuevoRegistro extends javax.swing.JFrame {

    private int moverX, moverY, x, y;
    private RoundButton[] botones;
    private RoundPanel[] paneles;
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
    
    
    public NuevoRegistro() {
        initComponents();
        setLocationRelativeTo(null);
        aparecer();
        panels();
        botones();
        iniciarMenu();
    }

    
     public void botones(){
         btnCliente.setIconPath("/icon/cliente.png", 35);
         btnVehiculo.setIconPath("/icon/pedido.png", 35);
         btnMecanico.setIconPath("/icon/empleado.png", 35);
         btnOrden.setIconPath("/icon/orden.png", 35);
         btnRepuesto.setIconPath("/icon/producto.png", 35);
         btnServicio.setIconPath("/icon/servicio_supermercado.png", 35);
         btnRegresar.setIconPath("/icon/regresar.png", 35);
         
         btnUtilizado.setVisible(false);
         btnAbastecimiento.setVisible(false);
         btnRealizado.setVisible(false);
         
        labelLogo.setIcon("/icon/logo_supermercado.png", 90);
    }
     
    public void panels() {
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
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
    
    private void iniciarMenu() {
        botones = new RoundButton[]{btnCliente, btnVehiculo, btnMecanico, btnOrden, btnRepuesto,
            btnUtilizado, btnAbastecimiento, btnServicio, btnRealizado};
        paneles = new RoundPanel[]{panelCliente, panelVehiculo, panelMecanico, panelOrden, panelRepuesto,
            panelUtilizado, panelAbastecimiento, panelServicio, panelRealizado};
    }
    
    private void mostrar(int indice) {
        int activo = indice;

        for (int i = 0; i < botones.length; i++) {
            botones[i].setBackground(i == indice ? new Color(0, 0, 51) : new Color(8, 29, 86));
            paneles[i].setVisible(i == indice);
        }

        boolean grupoRepuesto = indice == 4 || indice == 5 || indice == 6;
        boolean grupoServicio = indice == 7 || indice == 8;

        btnUtilizado.setVisible(grupoRepuesto);
        btnAbastecimiento.setVisible(grupoRepuesto);
        btnRealizado.setVisible(grupoServicio);
    }

     private void limpiar(JTextField... campos) {
        for (JTextField campo : campos) {
            campo.setText("");
        }
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
        panelCard = new swingx.RoundPanel();
        panelCliente = new swingx.RoundPanel();
        labelCliente = new swingx.RoundLabel();
        formularioCliente = new swingx.RoundPanel();
        labelNombreCli = new swingx.RoundLabel();
        txtNombreCli = new javax.swing.JTextField();
        labelDireccionCli = new swingx.RoundLabel();
        txtDireccionCli = new javax.swing.JTextField();
        labelTelefonoCli = new swingx.RoundLabel();
        txtTelefonoCli = new javax.swing.JTextField();
        btnGuardarCli = new swingx.RoundButton();
        btnLimpiarCli = new swingx.RoundButton();
        panelVehiculo = new swingx.RoundPanel();
        labelVehiculo = new swingx.RoundLabel();
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
        btnGuardarVeh = new swingx.RoundButton();
        btnLimpiarVeh = new swingx.RoundButton();
        panelMecanico = new swingx.RoundPanel();
        labelMecanico = new swingx.RoundLabel();
        formularioMecanico = new swingx.RoundPanel();
        labelNombreMec = new swingx.RoundLabel();
        txtNombreMec = new javax.swing.JTextField();
        labelEspecialidadMec = new swingx.RoundLabel();
        txtEspecialidadMec = new javax.swing.JTextField();
        labelTelefonoMec = new swingx.RoundLabel();
        txtTelefonoMec = new javax.swing.JTextField();
        btnGuardarMec = new swingx.RoundButton();
        btnLimpiarMec = new swingx.RoundButton();
        panelOrden = new swingx.RoundPanel();
        labelOrden = new swingx.RoundLabel();
        formularioOrden = new swingx.RoundPanel();
        labelIdVehiculoOrd = new swingx.RoundLabel();
        txtIdVehiculoOrd = new javax.swing.JTextField();
        labelIdMecanicoOrd = new swingx.RoundLabel();
        txtIdMecanicoOrd = new javax.swing.JTextField();
        labelDescripcionOrd = new swingx.RoundLabel();
        txtDescripcionOrd = new javax.swing.JTextField();
        btnGuardarOrd = new swingx.RoundButton();
        btnLimpiarOrd = new swingx.RoundButton();
        panelRepuesto = new swingx.RoundPanel();
        labelRepuesto = new swingx.RoundLabel();
        formularioRepuesto = new swingx.RoundPanel();
        labelNombreRep = new swingx.RoundLabel();
        txtNombreRep = new javax.swing.JTextField();
        labelDescripcionRep = new swingx.RoundLabel();
        txtDescripcionRep = new javax.swing.JTextField();
        labelPrecioRep = new swingx.RoundLabel();
        txtPrecioRep = new javax.swing.JTextField();
        btnGuardarRep = new swingx.RoundButton();
        btnLimpiarRep = new swingx.RoundButton();
        panelUtilizado = new swingx.RoundPanel();
        labelUtilizado = new swingx.RoundLabel();
        formularioUtilizado = new swingx.RoundPanel();
        labelIdOrdenUti = new swingx.RoundLabel();
        txtIdOrdenUti = new javax.swing.JTextField();
        labelIdRepuestoUti = new swingx.RoundLabel();
        txtIdRepuestoUti = new javax.swing.JTextField();
        labelCantidadUti = new swingx.RoundLabel();
        txtCantidadUti = new javax.swing.JTextField();
        btnGuardarUti = new swingx.RoundButton();
        btnLimpiarUti = new swingx.RoundButton();
        panelAbastecimiento = new swingx.RoundPanel();
        labelAbastecimiento = new swingx.RoundLabel();
        formularioAbastecimiento = new swingx.RoundPanel();
        labelIdRepuestoAba = new swingx.RoundLabel();
        txtIdRepuestoAba = new javax.swing.JTextField();
        labelCantidadAba = new swingx.RoundLabel();
        txtCantidadAba = new javax.swing.JTextField();
        btnGuardarAba = new swingx.RoundButton();
        btnLimpiarAba = new swingx.RoundButton();
        panelServicio = new swingx.RoundPanel();
        labelServicio = new swingx.RoundLabel();
        formularioServicio = new swingx.RoundPanel();
        labelDescripcionSer = new swingx.RoundLabel();
        txtDescripcionSer = new javax.swing.JTextField();
        labelCostoSer = new swingx.RoundLabel();
        txtCostoSer = new javax.swing.JTextField();
        btnGuardarSer = new swingx.RoundButton();
        btnLimpiarSer = new swingx.RoundButton();
        panelRealizado = new swingx.RoundPanel();
        labelRealizado = new swingx.RoundLabel();
        formularioRealizado = new swingx.RoundPanel();
        labelIdOrdenRea = new swingx.RoundLabel();
        txtIdOrdenRea = new javax.swing.JTextField();
        labelIdServicioRea = new swingx.RoundLabel();
        txtIdServicioRea = new javax.swing.JTextField();
        labelCantidadRea = new swingx.RoundLabel();
        txtCantidadRea = new javax.swing.JTextField();
        btnGuardarRea = new swingx.RoundButton();
        btnLimpiarRea = new swingx.RoundButton();

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
        btnCliente.setText("   CLIENTE");
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
        btnVehiculo.setText("   PEDIDO");
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
        btnMecanico.setText("   EMPLEADO");
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
        btnOrden.setText("   VENTA");
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
        btnRepuesto.setText("   PRODUCTO");
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
        btnUtilizado.setText("| UTILIZADO");
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
        btnServicio.setText("   SERVICIO");
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
        btnRealizado.setText("| REALIZADO");
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelCard.setBackground(new java.awt.Color(255, 255, 255));
        panelCard.setRoundBottomLeft(30);
        panelCard.setRoundBottomRight(30);
        panelCard.setRoundTopLeft(30);
        panelCard.setRoundTopRight(30);
        panelCard.setShadow(true);
        panelCard.setShadowSize(3);
        panelCard.setLayout(new java.awt.CardLayout());

        panelCliente.setBackground(new java.awt.Color(255, 255, 255));
        panelCliente.setRoundBottomLeft(30);
        panelCliente.setRoundBottomRight(30);
        panelCliente.setRoundTopLeft(30);
        panelCliente.setRoundTopRight(30);
        panelCliente.setShadow(true);
        panelCliente.setShadowSize(3);

        labelCliente.setBackground(new java.awt.Color(0, 0, 153));
        labelCliente.setForeground(new java.awt.Color(255, 255, 255));
        labelCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCliente.setText("NUEVO CLIENTE");
        labelCliente.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        labelCliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelCliente.setRoundBottomRight(30);
        labelCliente.setRoundTopLeft(30);

        formularioCliente.setBackground(new java.awt.Color(255, 255, 255));
        formularioCliente.setRoundBottomLeft(20);
        formularioCliente.setRoundBottomRight(20);
        formularioCliente.setRoundTopLeft(20);
        formularioCliente.setRoundTopRight(20);
        formularioCliente.setShadow(true);

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

        javax.swing.GroupLayout formularioClienteLayout = new javax.swing.GroupLayout(formularioCliente);
        formularioCliente.setLayout(formularioClienteLayout);
        formularioClienteLayout.setHorizontalGroup(
            formularioClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formularioClienteLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(formularioClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDireccionCli, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreCli, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefonoCli, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTelefonoCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDireccionCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNombreCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        formularioClienteLayout.setVerticalGroup(
            formularioClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formularioClienteLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(labelNombreCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombreCli, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(labelDireccionCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDireccionCli, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelTelefonoCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTelefonoCli, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );

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

        btnLimpiarCli.setBackground(new java.awt.Color(255, 204, 0));
        btnLimpiarCli.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiarCli.setText("LIMPIAR");
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

        javax.swing.GroupLayout panelClienteLayout = new javax.swing.GroupLayout(panelCliente);
        panelCliente.setLayout(panelClienteLayout);
        panelClienteLayout.setHorizontalGroup(
            panelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClienteLayout.createSequentialGroup()
                .addGap(207, 207, 207)
                .addGroup(panelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelClienteLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(formularioCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelClienteLayout.createSequentialGroup()
                                .addComponent(btnLimpiarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnGuardarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59)))))
                .addGap(201, 201, 201))
        );
        panelClienteLayout.setVerticalGroup(
            panelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelClienteLayout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addComponent(labelCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(formularioCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiarCli, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79))
        );

        panelCard.add(panelCliente, "card1");

        panelVehiculo.setBackground(new java.awt.Color(255, 255, 255));
        panelVehiculo.setRoundBottomLeft(30);
        panelVehiculo.setRoundBottomRight(30);
        panelVehiculo.setRoundTopLeft(30);
        panelVehiculo.setRoundTopRight(30);
        panelVehiculo.setShadow(true);
        panelVehiculo.setShadowSize(3);

        labelVehiculo.setBackground(new java.awt.Color(0, 0, 153));
        labelVehiculo.setForeground(new java.awt.Color(255, 255, 255));
        labelVehiculo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelVehiculo.setText("NUEVO PEDIDO");
        labelVehiculo.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        labelVehiculo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelVehiculo.setRoundBottomRight(30);
        labelVehiculo.setRoundTopLeft(30);

        formularioVehiculo.setBackground(new java.awt.Color(255, 255, 255));
        formularioVehiculo.setRoundBottomLeft(20);
        formularioVehiculo.setRoundBottomRight(20);
        formularioVehiculo.setRoundTopLeft(20);
        formularioVehiculo.setRoundTopRight(20);
        formularioVehiculo.setShadow(true);

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

        javax.swing.GroupLayout formularioVehiculoLayout = new javax.swing.GroupLayout(formularioVehiculo);
        formularioVehiculo.setLayout(formularioVehiculoLayout);
        formularioVehiculoLayout.setHorizontalGroup(
            formularioVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formularioVehiculoLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(formularioVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelAnioVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMarcaVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelIdClienteVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMarcaVeh)
                    .addComponent(txtIdClienteVeh)
                    .addComponent(txtAnioVeh, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(formularioVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelColorVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPlacaVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtModeloVeh)
                    .addComponent(txtPlacaVeh)
                    .addComponent(txtColorVeh, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );
        formularioVehiculoLayout.setVerticalGroup(
            formularioVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formularioVehiculoLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(formularioVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(formularioVehiculoLayout.createSequentialGroup()
                        .addComponent(labelPlacaVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPlacaVeh, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(labelModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtModeloVeh, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelColorVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtColorVeh, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(formularioVehiculoLayout.createSequentialGroup()
                        .addComponent(labelIdClienteVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdClienteVeh, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(labelMarcaVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMarcaVeh, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelAnioVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAnioVeh, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
        );

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
        btnLimpiarVeh.setText("LIMPIAR");
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

        javax.swing.GroupLayout panelVehiculoLayout = new javax.swing.GroupLayout(panelVehiculo);
        panelVehiculo.setLayout(panelVehiculoLayout);
        panelVehiculoLayout.setHorizontalGroup(
            panelVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVehiculoLayout.createSequentialGroup()
                .addContainerGap(89, Short.MAX_VALUE)
                .addGroup(panelVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVehiculoLayout.createSequentialGroup()
                        .addGroup(panelVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(formularioVehiculo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 699, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(85, 85, 85))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVehiculoLayout.createSequentialGroup()
                        .addComponent(btnLimpiarVeh, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardarVeh, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(263, 263, 263))))
        );
        panelVehiculoLayout.setVerticalGroup(
            panelVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVehiculoLayout.createSequentialGroup()
                .addContainerGap(84, Short.MAX_VALUE)
                .addComponent(labelVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(formularioVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarVeh, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiarVeh, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79))
        );

        panelCard.add(panelVehiculo, "card2");

        panelMecanico.setBackground(new java.awt.Color(255, 255, 255));
        panelMecanico.setRoundBottomLeft(30);
        panelMecanico.setRoundBottomRight(30);
        panelMecanico.setRoundTopLeft(30);
        panelMecanico.setRoundTopRight(30);
        panelMecanico.setShadow(true);
        panelMecanico.setShadowSize(3);

        labelMecanico.setBackground(new java.awt.Color(0, 0, 153));
        labelMecanico.setForeground(new java.awt.Color(255, 255, 255));
        labelMecanico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMecanico.setText("NUEVO EMPLEADO");
        labelMecanico.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        labelMecanico.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelMecanico.setRoundBottomRight(30);
        labelMecanico.setRoundTopLeft(30);

        formularioMecanico.setBackground(new java.awt.Color(255, 255, 255));
        formularioMecanico.setRoundBottomLeft(20);
        formularioMecanico.setRoundBottomRight(20);
        formularioMecanico.setRoundTopLeft(20);
        formularioMecanico.setRoundTopRight(20);
        formularioMecanico.setShadow(true);

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

        javax.swing.GroupLayout formularioMecanicoLayout = new javax.swing.GroupLayout(formularioMecanico);
        formularioMecanico.setLayout(formularioMecanicoLayout);
        formularioMecanicoLayout.setHorizontalGroup(
            formularioMecanicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formularioMecanicoLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(formularioMecanicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEspecialidadMec, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreMec, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefonoMec, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTelefonoMec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelEspecialidadMec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNombreMec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        formularioMecanicoLayout.setVerticalGroup(
            formularioMecanicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formularioMecanicoLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(labelNombreMec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombreMec, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(labelEspecialidadMec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEspecialidadMec, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelTelefonoMec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTelefonoMec, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );

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

        btnLimpiarMec.setBackground(new java.awt.Color(255, 204, 0));
        btnLimpiarMec.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiarMec.setText("LIMPIAR");
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

        javax.swing.GroupLayout panelMecanicoLayout = new javax.swing.GroupLayout(panelMecanico);
        panelMecanico.setLayout(panelMecanicoLayout);
        panelMecanicoLayout.setHorizontalGroup(
            panelMecanicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMecanicoLayout.createSequentialGroup()
                .addGap(207, 207, 207)
                .addGroup(panelMecanicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelMecanico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelMecanicoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panelMecanicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(formularioMecanico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMecanicoLayout.createSequentialGroup()
                                .addComponent(btnLimpiarMec, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnGuardarMec, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59)))))
                .addGap(201, 201, 201))
        );
        panelMecanicoLayout.setVerticalGroup(
            panelMecanicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMecanicoLayout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addComponent(labelMecanico, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(formularioMecanico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelMecanicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarMec, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiarMec, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79))
        );

        panelCard.add(panelMecanico, "card3");

        panelOrden.setBackground(new java.awt.Color(255, 255, 255));
        panelOrden.setRoundBottomLeft(30);
        panelOrden.setRoundBottomRight(30);
        panelOrden.setRoundTopLeft(30);
        panelOrden.setRoundTopRight(30);
        panelOrden.setShadow(true);
        panelOrden.setShadowSize(3);

        labelOrden.setBackground(new java.awt.Color(0, 0, 153));
        labelOrden.setForeground(new java.awt.Color(255, 255, 255));
        labelOrden.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelOrden.setText("NUEVA VENTA");
        labelOrden.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        labelOrden.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelOrden.setRoundBottomRight(30);
        labelOrden.setRoundTopLeft(30);

        formularioOrden.setBackground(new java.awt.Color(255, 255, 255));
        formularioOrden.setRoundBottomLeft(20);
        formularioOrden.setRoundBottomRight(20);
        formularioOrden.setRoundTopLeft(20);
        formularioOrden.setRoundTopRight(20);
        formularioOrden.setShadow(true);

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

        javax.swing.GroupLayout formularioOrdenLayout = new javax.swing.GroupLayout(formularioOrden);
        formularioOrden.setLayout(formularioOrdenLayout);
        formularioOrdenLayout.setHorizontalGroup(
            formularioOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formularioOrdenLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(formularioOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIdMecanicoOrd, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdVehiculoOrd, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcionOrd, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDescripcionOrd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelIdMecanicoOrd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelIdVehiculoOrd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        formularioOrdenLayout.setVerticalGroup(
            formularioOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formularioOrdenLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(labelIdVehiculoOrd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIdVehiculoOrd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(labelIdMecanicoOrd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIdMecanicoOrd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelDescripcionOrd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescripcionOrd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );

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

        btnLimpiarOrd.setBackground(new java.awt.Color(255, 204, 0));
        btnLimpiarOrd.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiarOrd.setText("LIMPIAR");
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

        javax.swing.GroupLayout panelOrdenLayout = new javax.swing.GroupLayout(panelOrden);
        panelOrden.setLayout(panelOrdenLayout);
        panelOrdenLayout.setHorizontalGroup(
            panelOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOrdenLayout.createSequentialGroup()
                .addGap(207, 207, 207)
                .addGroup(panelOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelOrden, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelOrdenLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panelOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(formularioOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOrdenLayout.createSequentialGroup()
                                .addComponent(btnLimpiarOrd, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnGuardarOrd, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59)))))
                .addGap(201, 201, 201))
        );
        panelOrdenLayout.setVerticalGroup(
            panelOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOrdenLayout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addComponent(labelOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(formularioOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelOrdenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarOrd, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiarOrd, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79))
        );

        panelCard.add(panelOrden, "card4");

        panelRepuesto.setBackground(new java.awt.Color(255, 255, 255));
        panelRepuesto.setRoundBottomLeft(30);
        panelRepuesto.setRoundBottomRight(30);
        panelRepuesto.setRoundTopLeft(30);
        panelRepuesto.setRoundTopRight(30);
        panelRepuesto.setShadow(true);
        panelRepuesto.setShadowSize(3);

        labelRepuesto.setBackground(new java.awt.Color(0, 0, 153));
        labelRepuesto.setForeground(new java.awt.Color(255, 255, 255));
        labelRepuesto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRepuesto.setText("NUEVO PRODUCTO");
        labelRepuesto.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        labelRepuesto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelRepuesto.setRoundBottomRight(30);
        labelRepuesto.setRoundTopLeft(30);

        formularioRepuesto.setBackground(new java.awt.Color(255, 255, 255));
        formularioRepuesto.setRoundBottomLeft(20);
        formularioRepuesto.setRoundBottomRight(20);
        formularioRepuesto.setRoundTopLeft(20);
        formularioRepuesto.setRoundTopRight(20);
        formularioRepuesto.setShadow(true);

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

        javax.swing.GroupLayout formularioRepuestoLayout = new javax.swing.GroupLayout(formularioRepuesto);
        formularioRepuesto.setLayout(formularioRepuestoLayout);
        formularioRepuestoLayout.setHorizontalGroup(
            formularioRepuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formularioRepuestoLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(formularioRepuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDescripcionRep, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreRep, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecioRep, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPrecioRep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDescripcionRep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNombreRep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        formularioRepuestoLayout.setVerticalGroup(
            formularioRepuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formularioRepuestoLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(labelNombreRep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombreRep, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(labelDescripcionRep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescripcionRep, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelPrecioRep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPrecioRep, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );

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

        btnLimpiarRep.setBackground(new java.awt.Color(255, 204, 0));
        btnLimpiarRep.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiarRep.setText("LIMPIAR");
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

        javax.swing.GroupLayout panelRepuestoLayout = new javax.swing.GroupLayout(panelRepuesto);
        panelRepuesto.setLayout(panelRepuestoLayout);
        panelRepuestoLayout.setHorizontalGroup(
            panelRepuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRepuestoLayout.createSequentialGroup()
                .addGap(207, 207, 207)
                .addGroup(panelRepuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelRepuesto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRepuestoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(formularioRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(201, 201, 201))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRepuestoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLimpiarRep, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGuardarRep, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(265, 265, 265))
        );
        panelRepuestoLayout.setVerticalGroup(
            panelRepuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRepuestoLayout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addComponent(labelRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(formularioRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelRepuestoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarRep, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiarRep, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79))
        );

        panelCard.add(panelRepuesto, "card5");

        panelUtilizado.setBackground(new java.awt.Color(255, 255, 255));
        panelUtilizado.setRoundBottomLeft(30);
        panelUtilizado.setRoundBottomRight(30);
        panelUtilizado.setRoundTopLeft(30);
        panelUtilizado.setRoundTopRight(30);
        panelUtilizado.setShadow(true);
        panelUtilizado.setShadowSize(3);

        labelUtilizado.setBackground(new java.awt.Color(0, 0, 153));
        labelUtilizado.setForeground(new java.awt.Color(255, 255, 255));
        labelUtilizado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelUtilizado.setText("PRODUCTO VENDIDO");
        labelUtilizado.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        labelUtilizado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelUtilizado.setRoundBottomRight(30);
        labelUtilizado.setRoundTopLeft(30);

        formularioUtilizado.setBackground(new java.awt.Color(255, 255, 255));
        formularioUtilizado.setRoundBottomLeft(20);
        formularioUtilizado.setRoundBottomRight(20);
        formularioUtilizado.setRoundTopLeft(20);
        formularioUtilizado.setRoundTopRight(20);
        formularioUtilizado.setShadow(true);

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

        javax.swing.GroupLayout formularioUtilizadoLayout = new javax.swing.GroupLayout(formularioUtilizado);
        formularioUtilizado.setLayout(formularioUtilizadoLayout);
        formularioUtilizadoLayout.setHorizontalGroup(
            formularioUtilizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formularioUtilizadoLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(formularioUtilizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIdRepuestoUti, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdOrdenUti, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantidadUti, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCantidadUti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelIdRepuestoUti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelIdOrdenUti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        formularioUtilizadoLayout.setVerticalGroup(
            formularioUtilizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formularioUtilizadoLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(labelIdOrdenUti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIdOrdenUti, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(labelIdRepuestoUti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIdRepuestoUti, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelCantidadUti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCantidadUti, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );

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
        btnLimpiarUti.setText("LIMPIAR");
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

        javax.swing.GroupLayout panelUtilizadoLayout = new javax.swing.GroupLayout(panelUtilizado);
        panelUtilizado.setLayout(panelUtilizadoLayout);
        panelUtilizadoLayout.setHorizontalGroup(
            panelUtilizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUtilizadoLayout.createSequentialGroup()
                .addGap(207, 207, 207)
                .addGroup(panelUtilizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelUtilizado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelUtilizadoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panelUtilizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(formularioUtilizado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelUtilizadoLayout.createSequentialGroup()
                                .addComponent(btnLimpiarUti, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnGuardarUti, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59)))))
                .addGap(201, 201, 201))
        );
        panelUtilizadoLayout.setVerticalGroup(
            panelUtilizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelUtilizadoLayout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addComponent(labelUtilizado, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(formularioUtilizado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelUtilizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarUti, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiarUti, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79))
        );

        panelCard.add(panelUtilizado, "card6");

        panelAbastecimiento.setBackground(new java.awt.Color(255, 255, 255));
        panelAbastecimiento.setRoundBottomLeft(30);
        panelAbastecimiento.setRoundBottomRight(30);
        panelAbastecimiento.setRoundTopLeft(30);
        panelAbastecimiento.setRoundTopRight(30);
        panelAbastecimiento.setShadow(true);
        panelAbastecimiento.setShadowSize(3);

        labelAbastecimiento.setBackground(new java.awt.Color(0, 0, 153));
        labelAbastecimiento.setForeground(new java.awt.Color(255, 255, 255));
        labelAbastecimiento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelAbastecimiento.setText("ABASTECIMIENTO DE PRODUCTO");
        labelAbastecimiento.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        labelAbastecimiento.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelAbastecimiento.setRoundBottomRight(30);
        labelAbastecimiento.setRoundTopLeft(30);

        formularioAbastecimiento.setBackground(new java.awt.Color(255, 255, 255));
        formularioAbastecimiento.setRoundBottomLeft(20);
        formularioAbastecimiento.setRoundBottomRight(20);
        formularioAbastecimiento.setRoundTopLeft(20);
        formularioAbastecimiento.setRoundTopRight(20);
        formularioAbastecimiento.setShadow(true);

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

        javax.swing.GroupLayout formularioAbastecimientoLayout = new javax.swing.GroupLayout(formularioAbastecimiento);
        formularioAbastecimiento.setLayout(formularioAbastecimientoLayout);
        formularioAbastecimientoLayout.setHorizontalGroup(
            formularioAbastecimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formularioAbastecimientoLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(formularioAbastecimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCantidadAba, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdRepuestoAba, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCantidadAba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelIdRepuestoAba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        formularioAbastecimientoLayout.setVerticalGroup(
            formularioAbastecimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formularioAbastecimientoLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(labelIdRepuestoAba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIdRepuestoAba, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(labelCantidadAba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCantidadAba, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

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
        btnLimpiarAba.setText("LIMPIAR");
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

        javax.swing.GroupLayout panelAbastecimientoLayout = new javax.swing.GroupLayout(panelAbastecimiento);
        panelAbastecimiento.setLayout(panelAbastecimientoLayout);
        panelAbastecimientoLayout.setHorizontalGroup(
            panelAbastecimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAbastecimientoLayout.createSequentialGroup()
                .addGap(211, 211, 211)
                .addGroup(panelAbastecimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAbastecimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(labelAbastecimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(formularioAbastecimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAbastecimientoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLimpiarAba, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardarAba, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)))
                .addGap(197, 197, 197))
        );
        panelAbastecimientoLayout.setVerticalGroup(
            panelAbastecimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAbastecimientoLayout.createSequentialGroup()
                .addContainerGap(121, Short.MAX_VALUE)
                .addComponent(labelAbastecimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(formularioAbastecimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelAbastecimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarAba, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiarAba, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(108, 108, 108))
        );

        panelCard.add(panelAbastecimiento, "card7");

        panelServicio.setBackground(new java.awt.Color(255, 255, 255));
        panelServicio.setRoundBottomLeft(30);
        panelServicio.setRoundBottomRight(30);
        panelServicio.setRoundTopLeft(30);
        panelServicio.setRoundTopRight(30);
        panelServicio.setShadow(true);
        panelServicio.setShadowSize(3);

        labelServicio.setBackground(new java.awt.Color(0, 0, 153));
        labelServicio.setForeground(new java.awt.Color(255, 255, 255));
        labelServicio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelServicio.setText("NUEVO SERVICIO");
        labelServicio.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        labelServicio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelServicio.setRoundBottomRight(30);
        labelServicio.setRoundTopLeft(30);

        formularioServicio.setBackground(new java.awt.Color(255, 255, 255));
        formularioServicio.setRoundBottomLeft(20);
        formularioServicio.setRoundBottomRight(20);
        formularioServicio.setRoundTopLeft(20);
        formularioServicio.setRoundTopRight(20);
        formularioServicio.setShadow(true);

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

        javax.swing.GroupLayout formularioServicioLayout = new javax.swing.GroupLayout(formularioServicio);
        formularioServicio.setLayout(formularioServicioLayout);
        formularioServicioLayout.setHorizontalGroup(
            formularioServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formularioServicioLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(formularioServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCostoSer, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcionSer, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCostoSer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDescripcionSer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        formularioServicioLayout.setVerticalGroup(
            formularioServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formularioServicioLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(labelDescripcionSer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescripcionSer, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(labelCostoSer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCostoSer, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );

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

        btnLimpiarSer.setBackground(new java.awt.Color(255, 204, 0));
        btnLimpiarSer.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiarSer.setText("LIMPIAR");
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

        javax.swing.GroupLayout panelServicioLayout = new javax.swing.GroupLayout(panelServicio);
        panelServicio.setLayout(panelServicioLayout);
        panelServicioLayout.setHorizontalGroup(
            panelServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelServicioLayout.createSequentialGroup()
                .addGap(204, 204, 204)
                .addGroup(panelServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(labelServicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(formularioServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelServicioLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLimpiarSer, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardarSer, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)))
                .addGap(204, 204, 204))
        );
        panelServicioLayout.setVerticalGroup(
            panelServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelServicioLayout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(labelServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(formularioServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarSer, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiarSer, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(120, Short.MAX_VALUE))
        );

        panelCard.add(panelServicio, "card8");

        panelRealizado.setBackground(new java.awt.Color(255, 255, 255));
        panelRealizado.setRoundBottomLeft(30);
        panelRealizado.setRoundBottomRight(30);
        panelRealizado.setRoundTopLeft(30);
        panelRealizado.setRoundTopRight(30);
        panelRealizado.setShadow(true);
        panelRealizado.setShadowSize(3);

        labelRealizado.setBackground(new java.awt.Color(0, 0, 153));
        labelRealizado.setForeground(new java.awt.Color(255, 255, 255));
        labelRealizado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRealizado.setText("SERVICIO REALIZADO");
        labelRealizado.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        labelRealizado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        labelRealizado.setRoundBottomRight(30);
        labelRealizado.setRoundTopLeft(30);

        formularioRealizado.setBackground(new java.awt.Color(255, 255, 255));
        formularioRealizado.setRoundBottomLeft(20);
        formularioRealizado.setRoundBottomRight(20);
        formularioRealizado.setRoundTopLeft(20);
        formularioRealizado.setRoundTopRight(20);
        formularioRealizado.setShadow(true);

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

        javax.swing.GroupLayout formularioRealizadoLayout = new javax.swing.GroupLayout(formularioRealizado);
        formularioRealizado.setLayout(formularioRealizadoLayout);
        formularioRealizadoLayout.setHorizontalGroup(
            formularioRealizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formularioRealizadoLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(formularioRealizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIdServicioRea, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdOrdenRea, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantidadRea, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCantidadRea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelIdServicioRea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelIdOrdenRea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        formularioRealizadoLayout.setVerticalGroup(
            formularioRealizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formularioRealizadoLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(labelIdOrdenRea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIdOrdenRea, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(labelIdServicioRea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIdServicioRea, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelCantidadRea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCantidadRea, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );

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

        btnLimpiarRea.setBackground(new java.awt.Color(255, 204, 0));
        btnLimpiarRea.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiarRea.setText("LIMPIAR");
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

        javax.swing.GroupLayout panelRealizadoLayout = new javax.swing.GroupLayout(panelRealizado);
        panelRealizado.setLayout(panelRealizadoLayout);
        panelRealizadoLayout.setHorizontalGroup(
            panelRealizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRealizadoLayout.createSequentialGroup()
                .addGap(207, 207, 207)
                .addGroup(panelRealizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelRealizado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRealizadoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panelRealizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(formularioRealizado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRealizadoLayout.createSequentialGroup()
                                .addComponent(btnLimpiarRea, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnGuardarRea, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59)))))
                .addGap(201, 201, 201))
        );
        panelRealizadoLayout.setVerticalGroup(
            panelRealizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRealizadoLayout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addComponent(labelRealizado, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(formularioRealizado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelRealizadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarRea, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiarRea, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79))
        );

        panelCard.add(panelRealizado, "card9");

        javax.swing.GroupLayout panelFondoLayout = new javax.swing.GroupLayout(panelFondo);
        panelFondo.setLayout(panelFondoLayout);
        panelFondoLayout.setHorizontalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addGap(0, 848, Short.MAX_VALUE)
                        .addComponent(btnMinimizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addComponent(panelCard, javax.swing.GroupLayout.PREFERRED_SIZE, 873, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panelFondoLayout.setVerticalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addComponent(btnMinimizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelCard, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1100, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 655, Short.MAX_VALUE)
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
        String nombre = txtNombreCli.getText().trim(), direccion = txtDireccionCli.getText().trim(), telefono = txtTelefonoCli.getText().trim();
        
        if (nombre.isEmpty() || direccion.isEmpty() || telefono.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Algunos campos están vacíos");
            } else {
                String mensaje = controladorCli.insertar(nombre, direccion, telefono);
                JOptionPane.showMessageDialog(this, mensaje);
                limpiar(txtNombreCli, txtDireccionCli, txtTelefonoCli);
            }
    }//GEN-LAST:event_btnGuardarCliActionPerformed

    private void btnLimpiarCliMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarCliMouseEntered
        btnLimpiarCli.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_btnLimpiarCliMouseEntered

    private void btnLimpiarCliMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarCliMouseExited
        btnLimpiarCli.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_btnLimpiarCliMouseExited

    private void btnLimpiarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarCliActionPerformed
        limpiar(txtNombreCli, txtDireccionCli, txtTelefonoCli);
    }//GEN-LAST:event_btnLimpiarCliActionPerformed

    private void btnGuardarVehMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarVehMouseEntered
        btnGuardarVeh.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_btnGuardarVehMouseEntered

    private void btnGuardarVehMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarVehMouseExited
        btnGuardarVeh.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_btnGuardarVehMouseExited

    private void btnGuardarVehActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarVehActionPerformed
        String idClienteTexto = txtIdClienteVeh.getText().trim(), placa = txtPlacaVeh.getText().trim().toUpperCase(), marca = txtMarcaVeh.getText().trim(),
                modelo = txtModeloVeh.getText().trim(), anioTexto = txtAnioVeh.getText().trim(), color = txtColorVeh.getText().trim();

        if (idClienteTexto.isEmpty() || placa.isEmpty() || marca.isEmpty() || modelo.isEmpty() || anioTexto.isEmpty() || color.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Algunos campos están vacíos");
        } else if (!placa.matches("[A-Z0-9-]+")) {
            JOptionPane.showMessageDialog(this, "La código no tiene un formato válido");
        } else {
            try {
                int idClienteNum = Integer.parseInt(idClienteTexto);
                int anioNum = Integer.parseInt(anioTexto);
                String mensaje = controladorVeh.insertar(idClienteNum, placa, marca, modelo, anioNum, color);
                JOptionPane.showMessageDialog(this, mensaje);
                limpiar(txtIdClienteVeh, txtPlacaVeh, txtMarcaVeh, txtModeloVeh, txtAnioVeh, txtColorVeh);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El ID de cliente y el año deben ser números");
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
        limpiar(txtIdClienteVeh, txtPlacaVeh, txtMarcaVeh, txtModeloVeh, txtAnioVeh, txtColorVeh);
    }//GEN-LAST:event_btnLimpiarVehActionPerformed

    private void btnGuardarMecMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMecMouseEntered
        btnGuardarMec.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_btnGuardarMecMouseEntered

    private void btnGuardarMecMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMecMouseExited
        btnGuardarMec.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_btnGuardarMecMouseExited

    private void btnGuardarMecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarMecActionPerformed
        String nombre = txtNombreMec.getText().trim(), especialidad = txtEspecialidadMec.getText().trim(), telefono = txtTelefonoMec.getText().trim();

        if (nombre.isEmpty() || especialidad.isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Algunos campos están vacíos");
        } else {
            String mensaje = controladorMec.insertar(nombre, especialidad, telefono);
            JOptionPane.showMessageDialog(this, mensaje);
            limpiar(txtNombreMec, txtEspecialidadMec, txtTelefonoMec);
        }
    }//GEN-LAST:event_btnGuardarMecActionPerformed

    private void btnLimpiarMecMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarMecMouseEntered
        btnLimpiarMec.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_btnLimpiarMecMouseEntered

    private void btnLimpiarMecMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarMecMouseExited
        btnLimpiarMec.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_btnLimpiarMecMouseExited

    private void btnLimpiarMecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarMecActionPerformed
        limpiar(txtNombreMec, txtEspecialidadMec, txtTelefonoMec);
    }//GEN-LAST:event_btnLimpiarMecActionPerformed

    private void btnGuardarOrdMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarOrdMouseEntered
        btnGuardarOrd.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_btnGuardarOrdMouseEntered

    private void btnGuardarOrdMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarOrdMouseExited
        btnGuardarOrd.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_btnGuardarOrdMouseExited

    private void btnGuardarOrdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarOrdActionPerformed
        String idVehiculoTexto = txtIdVehiculoOrd.getText().trim(), idMecanicoTexto = txtIdMecanicoOrd.getText().trim(), descripcion = txtDescripcionOrd.getText().trim();

        if (idVehiculoTexto.isEmpty() || idMecanicoTexto.isEmpty() || descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Algunos campos están vacíos");
        } else {
            try {
                int idVehiculoNum = Integer.parseInt(idVehiculoTexto);
                int idMecanicoNum = Integer.parseInt(idMecanicoTexto);
                String mensaje = controladorOrd.insertar(idVehiculoNum, idMecanicoNum, descripcion);
                JOptionPane.showMessageDialog(this, mensaje);
                limpiar(txtIdVehiculoOrd, txtIdMecanicoOrd, txtDescripcionOrd);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Los ID de pedido y empleado deben ser números");
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
        limpiar(txtIdVehiculoOrd, txtIdMecanicoOrd, txtDescripcionOrd);
    }//GEN-LAST:event_btnLimpiarOrdActionPerformed

    private void btnGuardarRepMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarRepMouseEntered
        btnGuardarRep.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_btnGuardarRepMouseEntered

    private void btnGuardarRepMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarRepMouseExited
        btnGuardarRep.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_btnGuardarRepMouseExited

    private void btnGuardarRepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarRepActionPerformed
        String nombre = txtNombreRep.getText().trim(), descripcion = txtDescripcionRep.getText().trim(), precioTexto = txtPrecioRep.getText().trim();

        if (nombre.isEmpty() || descripcion.isEmpty() || precioTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Algunos campos están vacíos");
        } else {
            try {
                double precioNum = Double.parseDouble(precioTexto);
                String mensaje = controladorRep.insertar(nombre, descripcion, precioNum);
                JOptionPane.showMessageDialog(this, mensaje);
                limpiar(txtNombreRep, txtDescripcionRep, txtPrecioRep);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El precio debe ser un número");
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
        limpiar(txtNombreRep, txtDescripcionRep, txtPrecioRep);
    }//GEN-LAST:event_btnLimpiarRepActionPerformed

    private void btnGuardarUtiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarUtiMouseEntered
        btnGuardarUti.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_btnGuardarUtiMouseEntered

    private void btnGuardarUtiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarUtiMouseExited
        btnGuardarUti.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_btnGuardarUtiMouseExited

    private void btnGuardarUtiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarUtiActionPerformed
        String idOrdenTexto = txtIdOrdenUti.getText().trim(), idRepuestoTexto = txtIdRepuestoUti.getText().trim(), cantidadTexto = txtCantidadUti.getText().trim();

        if (idOrdenTexto.isEmpty() || idRepuestoTexto.isEmpty() || cantidadTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Algunos campos están vacíos");
        } else {
            try {
                int idOrdenNum = Integer.parseInt(idOrdenTexto);
                int idRepuestoNum = Integer.parseInt(idRepuestoTexto);
                int cantidadNum = Integer.parseInt(cantidadTexto);
                String mensaje = controladorUti.insertar(idOrdenNum, idRepuestoNum, cantidadNum);
                JOptionPane.showMessageDialog(this, mensaje);
                limpiar(txtIdOrdenUti, txtIdRepuestoUti, txtCantidadUti);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "La venta, el producto y la cantidad deben ser números");
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
        limpiar(txtIdOrdenUti, txtIdRepuestoUti, txtCantidadUti);
    }//GEN-LAST:event_btnLimpiarUtiActionPerformed

    private void btnGuardarAbaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarAbaMouseEntered
        btnGuardarAba.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_btnGuardarAbaMouseEntered

    private void btnGuardarAbaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarAbaMouseExited
        btnGuardarAba.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_btnGuardarAbaMouseExited

    private void btnGuardarAbaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarAbaActionPerformed
        String idRepuestoTexto = txtIdRepuestoAba.getText().trim(), cantidadTexto = txtCantidadAba.getText().trim();

        if (idRepuestoTexto.isEmpty() || cantidadTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Algunos campos están vacíos");
        } else {
            try {
                int idRepuestoNum = Integer.parseInt(idRepuestoTexto);
                int cantidadNum = Integer.parseInt(cantidadTexto);
                String mensaje = controladorAba.insertar(idRepuestoNum, cantidadNum);
                JOptionPane.showMessageDialog(this, mensaje);
                limpiar(txtIdRepuestoAba, txtCantidadAba);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El producto y la cantidad deben ser números");
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
        limpiar(txtIdRepuestoAba, txtCantidadAba);
    }//GEN-LAST:event_btnLimpiarAbaActionPerformed

    private void btnGuardarSerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarSerMouseEntered
        btnGuardarSer.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_btnGuardarSerMouseEntered

    private void btnGuardarSerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarSerMouseExited
        btnGuardarSer.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_btnGuardarSerMouseExited

    private void btnGuardarSerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarSerActionPerformed
        String descripcion = txtDescripcionSer.getText().trim(), costoTexto = txtCostoSer.getText().trim();

        if (descripcion.isEmpty() || costoTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Algunos campos están vacíos");
        } else {
            try {
                double costoNum = Double.parseDouble(costoTexto);
                String mensaje = controladorSer.insertar(descripcion, costoNum);
                JOptionPane.showMessageDialog(this, mensaje);
                limpiar(txtDescripcionSer, txtCostoSer);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El costo debe ser un número");
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
        limpiar(txtDescripcionSer, txtCostoSer);
    }//GEN-LAST:event_btnLimpiarSerActionPerformed

    private void btnGuardarReaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarReaMouseEntered
        btnGuardarRea.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_btnGuardarReaMouseEntered

    private void btnGuardarReaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarReaMouseExited
        btnGuardarRea.setBackground(new Color(255, 204, 0));
    }//GEN-LAST:event_btnGuardarReaMouseExited

    private void btnGuardarReaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarReaActionPerformed
        String idOrdenTexto = txtIdOrdenRea.getText().trim(), idServicioTexto = txtIdServicioRea.getText().trim(), cantidadTexto = txtCantidadRea.getText().trim();

        if (idOrdenTexto.isEmpty() || idServicioTexto.isEmpty() || cantidadTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Algunos campos están vacíos");
        } else {
            try {
                int idOrdenNum = Integer.parseInt(idOrdenTexto);
                int idServicioNum = Integer.parseInt(idServicioTexto);
                int cantidadNum = Integer.parseInt(cantidadTexto);
                String mensaje = controladorRea.insertar(idOrdenNum, idServicioNum, cantidadNum);
                JOptionPane.showMessageDialog(this, mensaje);
                limpiar(txtIdOrdenRea, txtIdServicioRea, txtCantidadRea);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "La venta, el servicio y la cantidad deben ser números");
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
        limpiar(txtIdOrdenRea, txtIdServicioRea, txtCantidadRea);
    }//GEN-LAST:event_btnLimpiarReaActionPerformed


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
            java.util.logging.Logger.getLogger(NuevoRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevoRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevoRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevoRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NuevoRegistro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swingx.RoundButton btnAbastecimiento;
    private swingx.RoundButton btnCliente;
    private swingx.RoundButton btnGuardarAba;
    private swingx.RoundButton btnGuardarCli;
    private swingx.RoundButton btnGuardarMec;
    private swingx.RoundButton btnGuardarOrd;
    private swingx.RoundButton btnGuardarRea;
    private swingx.RoundButton btnGuardarRep;
    private swingx.RoundButton btnGuardarSer;
    private swingx.RoundButton btnGuardarUti;
    private swingx.RoundButton btnGuardarVeh;
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
    private swingx.RoundLabel labelOrden;
    private swingx.RoundLabel labelPlacaVeh;
    private swingx.RoundLabel labelPrecioRep;
    private swingx.RoundLabel labelRealizado;
    private swingx.RoundLabel labelRepuesto;
    private swingx.RoundLabel labelServicio;
    private swingx.RoundLabel labelTelefonoCli;
    private swingx.RoundLabel labelTelefonoMec;
    private swingx.RoundLabel labelUtilizado;
    private swingx.RoundLabel labelVehiculo;
    private swingx.RoundPanel panelAbastecimiento;
    private swingx.RoundPanel panelCard;
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
    private javax.swing.JTextField txtAnioVeh;
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
