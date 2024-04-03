package Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.Button;

import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.Utility.Theme;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class RoundTitleBarButtons  extends JButton {

    private int diameter;
    private  Color base;
    private Color hoverColor;
    private int opacity;
    private String iconPathString;
    private Theme theme ;
    static ClassLoader fileLoader = RoundTitleBarButtons.class.getClassLoader();

    public RoundTitleBarButtons(int diameter, Color base,
                                Color hoverColor, int opacity,
                                String iconPathString) {
        this.diameter = diameter;
        this.base = base;
        this.hoverColor = hoverColor;
        this.opacity = opacity;
        this.iconPathString = iconPathString;
        this.theme = new Theme(opacity);
    }

    private void initComponent() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setContentAreaFilled(false);
//        setOpaque(false);
        setBorderPainted(false);
        setBorder(new EmptyBorder(0, 0, 0, 0));
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        Ellipse2D.Float circle = new Ellipse2D.Float(getWidth() - this.diameter,
                0, this.diameter, this.diameter);
        String imagePath = "icons/"+this.iconPathString;

        if (getModel().isArmed()) {
            g2.setColor(this.hoverColor);
            repaint();
        } else {
            g2.setColor(this.base);
            repaint();
        }
        g2.setBackground(theme.getBlack35());
        g2.fill(circle);
        try {
            BufferedImage icon1= ImageIO.read(new File(fileLoader.getResource(imagePath).toURI()));
            int x = (this.diameter - icon1.getWidth()) / 2;
            int y = (this.diameter - icon1.getHeight()) / 2;
            g2.drawImage(icon1, null, x, y);
        } catch (IOException e) {
        } catch (URISyntaxException e) {
        }

    }

}
