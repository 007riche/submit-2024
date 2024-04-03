package Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.panel.base;

import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.Utility.Theme;

import javax.swing.*;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class InputField extends JTextField {
    private int X;
    private int Y;
    private int Width;
    private int Height;
    private int columns=0;

    private String placeholder;

    private Color backGroundColor;
    private Color foreGroundColor;
    private Color cursorColor;
    Theme theme = new Theme();

    public InputField(int x, int y, int width, int height, int columns, String placeholder, Color backGroundColor, Color foreGroundColor) {
        X = x;
        Y = y;
        Width = width;
        Height = height;
        this.columns = columns;
        this.placeholder = placeholder;
        this.backGroundColor = backGroundColor;
        this.foreGroundColor = foreGroundColor;

        initComponent();
        setBounds(getX(), getY(), getWidth(), getHeight());
        addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setText(placeholder);
                    setForeGroundColor(foreGroundColor);
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                if (getText().contentEquals(placeholder)) {
                    setText("");
                    setForeGroundColor(backGroundColor);
                }
            }

        });
        this.requestFocusInWindow();
    }

    public InputField( int columns, String placeholder, Color backGroundColor, Color foreGroundColor) {

        this.columns = columns;
        this.placeholder = placeholder;
        this.backGroundColor = backGroundColor;
        this.foreGroundColor = foreGroundColor;

        initComponent();
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getText().contentEquals(placeholder)) {
                    setText("");
                    setForeground(backGroundColor);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setText(placeholder);
                    setForeground(foreGroundColor);
                }
            }
        });
    }

    private void initComponent() {
        setForeground(this.foreGroundColor);
        setBackground(this.backGroundColor);
        setFont(new Font("Tahoma", Font.PLAIN, 14));
        setColumns(this.columns != 0 ? getColumns() : 10);
        this.setCaretColor(this.cursorColor != null ? this.cursorColor : theme.getYellow());
        this.getCaret().setBlinkRate(300);
        setRequestFocusEnabled(true);
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

    public Color getCursorColor() {
        return cursorColor;
    }

    public void setCursorColor(Color cursorColor) {
        this.cursorColor = cursorColor;
    }
}
