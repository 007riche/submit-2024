package Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.panel.table;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

public class CellActionButton extends JButton {
    private boolean mousePressed;
    private String iconImgPath;
    private Color unPressedColor;
    private Color pressedColor;

    static ClassLoader fileLoader = CellActionButton.class.getClassLoader();


    public CellActionButton(String iconImgPath) {
        this.iconImgPath = iconImgPath;
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(3, 3, 3, 3));
        setIcon(new ImageIcon(fileLoader.getResource("icons/"+this.iconImgPath)));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                mousePressed = true;
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                mousePressed = false;
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();

        int minSize = Math.min(width, height);
        int x= (width- minSize) /2;
        int y=(height - minSize) /2;

        if (mousePressed) {
            g2.setColor(this.pressedColor);
        } else {
            g2.setColor(this.unPressedColor);
        }
        g2.fill(new Ellipse2D.Double(x, y, minSize, minSize));
        g2.dispose();
        super.paintComponent(g);
    }

    public Color getUnPressedColor() {
        return unPressedColor;

    }

    public void setUnPressedColor(Color unPressedColor) {
        this.unPressedColor = unPressedColor;
    }

    public Color getPressedColor() {
        return pressedColor;
    }

    public void setPressedColor(Color pressedColor) {
        this.pressedColor = pressedColor;
    }
}
