package swingx;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

public class RoundPanel extends JPanel {

    private boolean shadow = false;
    private int shadowSize = 5;
    private int shadowOffset = 1;
    private int roundTopLeft = 0;
    private int roundTopRight = 0;
    private int roundBottomLeft = 0;
    private int roundBottomRight = 0;

    public RoundPanel() {
        setOpaque(false);
    }

    public boolean isShadow() {
        return shadow;
    }

    public void setShadow(boolean shadow) {
        this.shadow = shadow;
        repaint();
    }

    public int getShadowSize() {
        return shadowSize;
    }

    public void setShadowSize(int shadowSize) {
        this.shadowSize = Math.max(0, shadowSize);
        repaint();
    }

    public int getRoundTopLeft() {
        return roundTopLeft;
    }

    public void setRoundTopLeft(int roundTopLeft) {
        this.roundTopLeft = roundTopLeft;
        repaint();
    }

    public int getRoundTopRight() {
        return roundTopRight;
    }

    public void setRoundTopRight(int roundTopRight) {
        this.roundTopRight = roundTopRight;
        repaint();
    }

    public int getRoundBottomLeft() {
        return roundBottomLeft;
    }

    public void setRoundBottomLeft(int roundBottomLeft) {
        this.roundBottomLeft = roundBottomLeft;
        repaint();
    }

    public int getRoundBottomRight() {
        return roundBottomRight;
    }

    public void setRoundBottomRight(int roundBottomRight) {
        this.roundBottomRight = roundBottomRight;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int margin = shadow ? shadowSize : 0;
        int bodyWidth = getWidth() - margin * 2;
        int bodyHeight = getHeight() - margin * 2;

        if (shadow && bodyWidth > 0 && bodyHeight > 0) {
            paintShadow(g2, margin, bodyWidth, bodyHeight);
        }

        if (bodyWidth > 0 && bodyHeight > 0) {
            Area area = new Area(createRoundTopLeft(bodyWidth, bodyHeight));
            if (roundTopRight > 0) {
                area.intersect(new Area(createRoundTopRight(bodyWidth, bodyHeight)));
            }
            if (roundBottomLeft > 0) {
                area.intersect(new Area(createRoundBottomLeft(bodyWidth, bodyHeight)));
            }
            if (roundBottomRight > 0) {
                area.intersect(new Area(createRoundBottomRight(bodyWidth, bodyHeight)));
            }
            area.transform(AffineTransform.getTranslateInstance(margin, margin));
            g2.setColor(getBackground());
            g2.fill(area);
        }

        g2.dispose();
        super.paintComponent(grphcs);
    }

    private void paintShadow(Graphics2D g2, int margin, int bodyWidth, int bodyHeight) {
        int layers = shadowSize - shadowOffset;
        if (layers <= 0) {
            return;
        }
        int maxRound = Math.max(Math.max(roundTopLeft, roundTopRight), Math.max(roundBottomLeft, roundBottomRight));
        int alpha = Math.max(1, 80 / layers);
        for (int e = layers; e >= 1; e--) {
            g2.setColor(new Color(0, 0, 0, 10));
            g2.fill(new RoundRectangle2D.Double(
                    margin - e,
                    margin - e + shadowOffset,
                    bodyWidth + e * 2,
                    bodyHeight + e * 2,
                    maxRound + e * 2,
                    maxRound + e * 2));
        }
    }

    private Shape createRoundTopLeft(int width, int height) {
        int roundX = Math.min(width, roundTopLeft);
        int roundY = Math.min(height, roundTopLeft);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(roundX / 2, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, roundY / 2, width, height - roundY / 2)));
        return area;
    }

    private Shape createRoundTopRight(int width, int height) {
        int roundX = Math.min(width, roundTopRight);
        int roundY = Math.min(height, roundTopRight);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(0, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, roundY / 2, width, height - roundY / 2)));
        return area;
    }

    private Shape createRoundBottomLeft(int width, int height) {
        int roundX = Math.min(width, roundBottomLeft);
        int roundY = Math.min(height, roundBottomLeft);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(roundX / 2, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY / 2)));
        return area;
    }

    private Shape createRoundBottomRight(int width, int height) {
        int roundX = Math.min(width, roundBottomRight);
        int roundY = Math.min(height, roundBottomRight);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(0, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY / 2)));
        return area;
    }
}
