package swingx;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class RoundButton extends JButton {

    private int radius = 50;

    public RoundButton() {
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        repaint();
    }

   public void setIconPath(String rutaImagen, int lado) {
        ImageIcon icono = new ImageIcon(getClass().getResource(rutaImagen));
        setIcon(new ImageIcon(icono.getImage().getScaledInstance(lado, lado, Image.SCALE_SMOOTH)));
    }
   
   private void desvanecer(Runnable alTerminar) {
        Window ventana = SwingUtilities.getWindowAncestor(this);
        Timer timer = new Timer(10, null);
        timer.addActionListener(e -> {
            float opacidad = ventana.getOpacity() - 0.05f;
            if (opacidad <= 0f) {
                timer.stop();
                alTerminar.run();
                ventana.setOpacity(1f);
            } else {
                ventana.setOpacity(opacidad);
            }
        });
        timer.start();
    }

    public void minimizar() {
        Frame ventana = (Frame) SwingUtilities.getWindowAncestor(this);
        desvanecer(() -> ventana.setState(Frame.ICONIFIED));
    }

    public void cerrar() {
        desvanecer(() -> System.exit(0));
    }

    public void cambiarA(JFrame nueva) {
        Window ventana = SwingUtilities.getWindowAncestor(this);
        desvanecer(() -> {
            nueva.setVisible(true);
            ventana.dispose();
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        super.paintComponent(g);

        g2.dispose();
    }
}