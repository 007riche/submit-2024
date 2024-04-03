package Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.panel.base;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DropDown extends JComboBox<String> {



    private Color backgroundColor;
    private Color foregroundColor;
    private int width;
    private int height;

    public DropDown( List<String> options,
                     Color backgroundColor, Color foregroundColor,
                     int width, int height) {

        super(options.toArray(new String[0]));
        System.out.println(options.toString());
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
        this.width = width;
        this.height = height;
        initialize();
    }

    private void initialize() {
        setForeground(this.getForegroundColor());
        setBackground(this.getBackgroundColor());
        setPreferredSize(new Dimension(this.width, this.height));

    }

    public String getSelected() {
       return (String) getSelectedItem();
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

}
