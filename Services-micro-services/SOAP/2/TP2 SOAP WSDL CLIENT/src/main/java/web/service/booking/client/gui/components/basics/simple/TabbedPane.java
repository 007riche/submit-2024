package web.service.booking.client.gui.components.basics.simple;

import web.service.booking.client.gui.components.utils.Theme;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class TabbedPane extends JTabbedPane {
    private int x;
    private int y;
    private int width;
    private int height;
    private Color backgroundColor;
    private Color foregroundColor;
    private Theme theme = new Theme();

    public TabbedPane(int tabPlacement,
                      int x, int y, int width,
                      int height,
                      Color backgroundColor,
                      Color foregroundColor) {
        super(tabPlacement);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
        initializeComponent();
    }

    private void initializeComponent() {
        setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        setBackground(getBackgroundColor());
        setForeground(getForegroundColor());
        getComponents();
        Arrays.stream(getComponents()).map(component -> {
            if (component.isEnabled()) {
                component.setBackground(getBackgroundColor());
                component.setForeground(getForegroundColor());
            }
            return null;
        });
        setBounds(getX(), getY(), getWidth(), getHeight());
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
}
