package com.todo.company.hai704.restapi.Client.gui.components.basics.simple;

import com.todo.company.hai704.restapi.Client.gui.components.utils.Theme;
import com.todo.company.hai704.restapi.Client.gui.components.utils.ThemeScrollBar;

import javax.swing.*;
import java.awt.*;

public class ScrollablePanel extends JScrollPane {
    private int x;
    private int y;
    private int width;
    private int height;
    private Color backgroundColor;
    private Color foregroundColor;
    private Theme theme = new Theme();

    public ScrollablePanel(int x, int y, int width, int height,
                           Color backgroundColor, Color foregroundColor) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
        initializeComponent();
    }

    private void initializeComponent() {
//        setBorder(new EmptyBorder(0, 0, 0, 0));
        setBorder(null);
        setBackground(this.backgroundColor);
        setForeground(this.foregroundColor);
        setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        setDoubleBuffered(true);
        setBounds(getX(), getY(), getWidth(), getHeight());
        setPreferredSize(new Dimension(getWidth(), getHeight()));
//        setLayout(new ScrollPaneLayout());
        getVerticalScrollBar().setUI(new ThemeScrollBar());
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
