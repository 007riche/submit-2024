package web.service.booking.client.gui.components.basics.simple;

import web.service.booking.client.gui.components.utils.Theme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TextInputField extends JTextField {
    private int X;
    private int Y;
    private int Width;
    private int Height;
    private int columns=10;

    private String placeholder;

    private Color backGroundColor;
    private Color foreGroundColor;
    private Font font;
    private Color cursorColor;
    Theme theme = new Theme();

    public TextInputField(int x, int y, int width, int height,
                          int columns, String placeholder,
                          Color backGroundColor, Color foreGroundColor, Font font) {
        X = x;
        Y = y;
        Width = width;
        Height = height;
        this.columns = columns;
        this.placeholder = placeholder;
        this.backGroundColor = backGroundColor;
        this.foreGroundColor = foreGroundColor;
        this.font = font;
        initializeComponent();
        setBounds(getX(), getY(), getWidth(), getHeight());
        addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setText(placeholder);
                    setForeground(foreGroundColor);
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                if (getText().contentEquals(placeholder)) {
                    setText("");
//                    setForeground(backGroundColor);
                    setForeground(foreGroundColor);
                }
            }

        });
    }

    private void initializeComponent() {
        setForeground(this.foreGroundColor);
        setBackground(this.backGroundColor);
        setFont(getFont());
        setColumns(this.columns != 0 ? getColumns() : 10);
        this.setCaretColor(this.cursorColor != null ? this.cursorColor : theme.getYelloowAccent());
        this.getCaret().setBlinkRate(300);
        setRequestFocusEnabled(true);
        setBounds(getX(), getY(), getWidth(), getHeight());
        setPreferredSize(new Dimension(getWidth(), getHeight()));
        this.setPlaceholder(this.placeholder);
    }

    public String getInputedText() {
        return getText().trim();
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

    @Override
    public int getColumns() {
        return columns;
    }

    @Override
    public void setColumns(int columns) {
        this.columns = columns;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
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

    @Override
    public Font getFont() {
        return font;
    }

    @Override
    public void setFont(Font font) {
        this.font = font;
    }
}
