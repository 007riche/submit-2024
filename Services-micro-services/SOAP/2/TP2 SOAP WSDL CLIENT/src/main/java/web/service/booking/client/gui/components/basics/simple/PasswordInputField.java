package web.service.booking.client.gui.components.basics.simple;

import web.service.booking.client.gui.components.utils.Theme;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;

public class PasswordInputField extends JPasswordField {
    private int X;
    private int Y;
    private int Width;
    private int Height;
    private int columns=10;
    private int minimumLength=0;
    private int maximumLength=0;
    private String placeholder;

    private Color backGroundColor;
    private Color foreGroundColor;
    private Color cursorColor;
    Theme theme = new Theme();

//    public PasswordInputField(int x, int y, int width, int height,
//                              int columns, String placeholder,
//                              Color backGroundColor, Color foreGroundColor) {
//        X = x;
//        Y = y;
//        Width = width;
//        Height = height;
//        this.columns = columns;
//        this.placeholder = placeholder;
//        this.backGroundColor = backGroundColor;
//        this.foreGroundColor = foreGroundColor;
//        initializeComponent();
//    }

    public PasswordInputField(  int x, int y, int width, int height, int columns, int minimumLength, int maximumLength, String placeholder, Color backGroundColor, Color foreGroundColor) {
        super( columns);
        X = x;
        Y = y;
        Width = width;
        Height = height;
        this.columns = columns;
        this.minimumLength = minimumLength;
        this.maximumLength = maximumLength;
        this.placeholder = placeholder;
        this.backGroundColor = backGroundColor;
        this.foreGroundColor = foreGroundColor;
        initializeComponent();
    }

    private void initializeComponent() {
        setForeground(this.foreGroundColor);
        setBackground(this.backGroundColor);
        setFont(new Font("Tahoma", Font.PLAIN, 14));
        setColumns(this.columns != 0 ? getColumns() : 10);
        this.setCaretColor(this.cursorColor != null ? this.cursorColor : theme.getYelloowAccent());
        this.getCaret().setBlinkRate(300);
        setRequestFocusEnabled(true);
        setBounds(getX(), getY(), getWidth(), getHeight());
        setPreferredSize(new Dimension(getWidth(), getHeight()));
        setEchoChar('O');
        this.setPlaceholder(this.placeholder);
        ((AbstractDocument) getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                int currentLength = fb.getDocument().getLength();
                int futureLength = currentLength - length + text.length();
                if (futureLength>= getMinimumLength() || futureLength <= getMaximumLength()) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
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

    public int getMinimumLength() {
        return minimumLength;
    }

    public void setMinimumLength(int minimumLength) {
        this.minimumLength = minimumLength;
    }

    public int getMaximumLength() {
        return maximumLength;
    }

    public void setMaximumLength(int maximumLength) {
        this.maximumLength = maximumLength;
    }
}
