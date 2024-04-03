package web.service.booking.client.gui.components.utils;

import java.awt.*;

public class Theme {
    private int opacity;
    private Color black=new Color(0, 0, 0);
    private Color yelloowAccent=new Color(252, 163, 17);
    private Color indigo =new Color(20, 33, 61);
    private Color grey =new Color(187, 187, 187);
    private Color white =new Color(255, 255, 255);

    private Color blacka;
    private Color yelloowAccenta;
    private Color indigoa;
    private Color greya;
    private Color whitea;

    public Theme() {
    }

    public Theme(int opacity) {
        this.opacity = opacity;
        this.blacka=new Color(0, 0, 0, this.opacity);
        this.yelloowAccenta=new Color(252, 163, 17, this.opacity);
        this.indigoa =new Color(20, 33, 61, this.opacity);
        this.greya =new Color(187, 187, 187, this.opacity);
        this.whitea =new Color(255, 255, 255, this.opacity);
    }

    public int getOpacity() {
        return opacity;
    }

    public void setOpacity(int opacity) {
        this.opacity = opacity;
    }

    public Color getBlack() {
        return black;
    }

    public void setBlack(Color black) {
        this.black = black;
    }

    public Color getYelloowAccent() {
        return yelloowAccent;
    }

    public void setYelloowAccent(Color yelloowAccent) {
        this.yelloowAccent = yelloowAccent;
    }

    public Color getIndigo() {
        return indigo;
    }

    public void setIndigo(Color indigo) {
        this.indigo = indigo;
    }

    public Color getGrey() {
        return grey;
    }

    public void setGrey(Color grey) {
        this.grey = grey;
    }

    public Color getWhite() {
        return white;
    }

    public void setWhite(Color white) {
        this.white = white;
    }

    public Color getBlacka() {
        return blacka;
    }

    public void setBlacka(Color blacka) {
        this.blacka = blacka;
    }

    public Color getYelloowAccenta() {
        return yelloowAccenta;
    }

    public void setYelloowAccenta(Color yelloowAccenta) {
        this.yelloowAccenta = yelloowAccenta;
    }

    public Color getIndigoa() {
        return indigoa;
    }

    public void setIndigoa(Color indigoa) {
        this.indigoa = indigoa;
    }

    public Color getGreya() {
        return greya;
    }

    public void setGreya(Color greya) {
        this.greya = greya;
    }

    public Color getWhitea() {
        return whitea;
    }

    public void setWhitea(Color whitea) {
        this.whitea = whitea;
    }
}
