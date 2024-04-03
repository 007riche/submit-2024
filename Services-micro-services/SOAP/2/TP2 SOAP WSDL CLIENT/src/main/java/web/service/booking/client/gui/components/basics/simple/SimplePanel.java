package web.service.booking.client.gui.components.basics.simple;

import web.service.booking.client.gui.components.utils.Theme;

import javax.swing.*;
import java.awt.*;

public class SimplePanel extends JPanel {
    private Color backgroundColor;
    private Color foregroundColor;
    private Theme theme = new Theme();

    public SimplePanel(Color backgroundColor, Color foregroundColor) {
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
        initializeComponent();
    }

    private void initializeComponent() {
        setBackground(getBackgroundColor());
        setForeground(getForegroundColor());
        setLayout(null);
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
    }
}
