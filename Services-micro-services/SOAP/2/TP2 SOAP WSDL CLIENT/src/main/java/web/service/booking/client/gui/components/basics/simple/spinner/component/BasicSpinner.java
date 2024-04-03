package web.service.booking.client.gui.components.basics.simple.spinner.component;

import web.service.booking.client.gui.components.utils.Theme;

import javax.swing.*;
import java.awt.*;

public class BasicSpinner extends JSpinner {
    private int X;
    private int Y;
    private int Width;
    private int Height;
    private Color backGroundColor;
    private Color foreGroundColor;
    private Font font;

    Theme theme = new Theme();

    public BasicSpinner(SpinnerModel model,
                        int x, int y, int width, int height,
                        Color backGroundColor, Color foreGroundColor, Font font) {
        super(model);
        X = x;
        Y = y;
        Width = width;
        Height = height;
        this.font = font;
        this.backGroundColor = backGroundColor;
        this.foreGroundColor = foreGroundColor;
        initializeComponent();
    }

    private void initializeComponent() {
        setForeground(this.foreGroundColor);
        setBackground(this.backGroundColor);
        setWidth(getWidth());
        setHeight(getHeight());
        setFont(getFont());
        setRequestFocusEnabled(true);
        setBounds(getX(), getY(), getWidth(), getHeight());
        setPreferredSize(new Dimension(getWidth(), getHeight()));
    }

    @Override
    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    @Override
    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    @Override
    public int getWidth() {
        return Width;
    }

    public void setWidth(int width) {
        Width = width;
    }

    @Override
    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        Height = height;
    }

    public Color getBackGroundColor() {
        return backGroundColor;
    }

    public void setBackGroundColor(Color backGroundColor) {
        this.backGroundColor = backGroundColor;
    }

    public Color getForeGroundColor() {
        return foreGroundColor;
    }

    public void setForeGroundColor(Color foreGroundColor) {
        this.foreGroundColor = foreGroundColor;
    }
}
