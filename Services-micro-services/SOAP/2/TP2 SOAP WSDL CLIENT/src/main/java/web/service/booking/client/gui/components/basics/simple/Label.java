package web.service.booking.client.gui.components.basics.simple;

import web.service.booking.client.gui.components.utils.Theme;

import javax.swing.*;
import java.awt.*;

public class Label extends JLabel {
    private String labelText;
    private int x;
    private int y;
    private int width;
    private int height;
    private Color backgroundColor;
    private Color foregroundColor;
    private Font font;

    private Theme theme = new Theme();



    public Label(String text, int horizontalAlignment,  int x, int y, int width, int height,
                 Color backgroundColor, Color foregroundColor,
                 Font font) {
        super(text, horizontalAlignment);
        this.labelText = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
        this.font = font;
        initializeComponent();
    }

    private void initializeComponent() {
        setLayout(null);
        setBackground(getBackgroundColor());
        setForeground(getForegroundColor());
        setBounds(getX(), getY(), getWidth(), getHeight());
        setFont(getFont());
        setPreferredSize(new Dimension(getWidth(), getHeight()));
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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

    @Override
    public Font getFont() {
        return font;
    }

    @Override
    public void setFont(Font font) {
        this.font = font;
    }
}
