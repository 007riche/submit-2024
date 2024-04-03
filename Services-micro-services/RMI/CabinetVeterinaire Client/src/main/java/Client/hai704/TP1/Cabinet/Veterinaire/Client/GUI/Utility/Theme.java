package Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.Utility;

import java.awt.*;

public class Theme {

    private int opacity;

    private Color black29;
    private Color black35;
    private Color black49;
    private Color black51;
    private Color grey175;

    private Color grey205;
    private Color red;
    private Color red215;
    private Color green200;
    private Color green215;
    private Color green;

    private Color black29a;
    private Color black35a;
    private Color black49a;
    private Color black51a;
    private Color grey175a;

    private Color grey205a;
    private Color reda;
    private Color red215a;
    private Color green200a;
    private Color green215a;
    private Color greena;

    private  Color yellow; // 255, 214, 10 // 255, 195, 0
    private  Color yellow252; // rgb(252, 163, 17)

    private String  primaryFontName;

    private String  secondaryFontName;

    private int textSize;

    public Theme() {
        black29=new Color(29, 29, 29);
        black35=new Color(35, 35, 35);
        black49=new Color(49, 49, 49);
        black51=new Color(51, 51, 51);

        grey175=new Color(175, 177, 179);
        grey205=new Color(205, 207, 209);

        red=new Color(245, 10, 10);
        red215=new Color(215, 95, 87);

        green200=new Color(40, 200, 64);
        green215=new Color(40, 215, 64);
        green=new Color(0, 255, 0);

        yellow = new Color(255, 214, 10);
        yellow252 = new Color(252, 163, 17);
    }

    public Theme(int opacity) {
        this.opacity = opacity;

         black29=new Color(29, 29, 29);
         black35=new Color(35, 35, 35);
         black49=new Color(49, 49, 49);
         black51=new Color(51, 51, 51);

         grey175=new Color(175, 177, 179);
         grey205=new Color(205, 207, 209);

         red=new Color(245, 10, 10);
         red215=new Color(215, 95, 87);

         green200=new Color(40, 200, 64);
         green215=new Color(40, 215, 64);
         green=new Color(0, 255, 0);

        yellow = new Color(255, 214, 10);
        yellow252 = new Color(252, 163, 17);

         // with Opacity
         black29a=new Color(29, 29, 29, this.opacity);
         black35a=new Color(35, 35, 35, this.opacity);
         black49a=new Color(49, 49, 49, this.opacity);
         black51a=new Color(51, 51, 51, this.opacity);

         grey175a=new Color(175, 177, 179, this.opacity);
         grey205a=new Color(205, 207, 209, this.opacity);

         reda=new Color(245, 10, 10, this.opacity);
         red215a=new Color(215, 95, 87, this.opacity);

         green200a=new Color(40, 200, 64, this.opacity);
         green215a=new Color(40, 215, 64, this.opacity);
         greena=new Color(0, 255, 0, this.opacity);

        yellow = new Color(255, 214, 10, this.opacity);
        yellow252 = new Color(252, 163, 17, this.opacity);
    }


    public Color getBlack29() {
        return black29;
    }

    public Color getBlack35() {
        return black35;
    }

    public Color getBlack49() {
        return black49;
    }

    public Color getBlack51() {
        return black51;
    }

    public Color getGrey175() {
        return grey175;
    }

    public Color getRed() {
        return red;
    }

    public Color getRed215() {
        return red215;
    }

    public Color getGreen200() {
        return green200;
    }

    public Color getGreen215() {
        return green215;
    }

    public Color getGreen() {
        return green;
    }

    public Color getYellow() {
        return yellow;
    }

    public void setYellow(Color yellow) {
        this.yellow = yellow;
    }

    public Color getYellow252() {
        return yellow252;
    }

    public void setYellow252(Color yellow252) {
        this.yellow252 = yellow252;
    }

    public Color getBlack29a() {
        return black29a;
    }

    public Color getBlack35a() {
        return black35a;
    }

    public Color getBlack49a() {
        return black49a;
    }

    public Color getBlack51a() {
        return black51a;
    }

    public Color getGrey175a() {
        return grey175a;
    }

    public Color getReda() {
        return reda;
    }

    public Color getRed215a() {
        return red215a;
    }

    public Color getGreen200a() {
        return green200a;
    }

    public Color getGreen215a() {
        return green215a;
    }

    public Color getGreena() {
        return greena;
    }

    public Color getGrey205() {
        return grey205;
    }

    public Color getGrey205a() {
        return grey205a;
    }
}
