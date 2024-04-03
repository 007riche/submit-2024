package Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.panel.base;

import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.Utility.Theme;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    private Color backgroungColor;
    private Color foregroungColor;
    private Dimension dimension;

    static ClassLoader fileLoader = Panel.class.getClassLoader();
    private Theme theme = new Theme();

    public Panel(Color backgroungColor, Color foregroungColor, Dimension dimension) {
        this.backgroungColor = backgroungColor;
        this.foregroungColor = foregroungColor;
        this.dimension = dimension;
        initComponent();
    }

    private void initComponent() {
        setBorder(null);
        setPreferredSize(this.getDimension());
        setForeground(this.getForegroungColor());
        setBackground(this.getBackgroungColor());
    }

    public Color getBackgroungColor() {
        return backgroungColor;
    }

    public void setBackgroungColor(Color backgroungColor) {
        this.backgroungColor = backgroungColor;
    }

    public Color getForegroungColor() {
        return foregroungColor;
    }

    public void setForegroungColor(Color foregroungColor) {
        this.foregroungColor = foregroungColor;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }
}
