package web.service.booking.client.gui.components.application;


import web.service.booking.client.gui.components.basics.simple.LabeledIcon;
import web.service.booking.client.gui.components.utils.ResourceLoader;
import web.service.booking.client.gui.components.utils.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

public class SplashScreen extends JFrame {
    private JPanel contentPane;
    final int FRAME_WIDTH = 800;
    final int FRAME_HEIGHT = 800;
    final private Color backgroungColr = new Color(0, 0, 15);
    private ResourceLoader resourceLoader = new ResourceLoader();
    private Theme theme = new Theme(0);

    public SplashScreen() throws HeadlessException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);

        InputStream inputStream = resourceLoader.getResourceAsStream("application/logo/agence-de-voyage.png");
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        contentPane =new JPanel();
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(backgroungColr);

        try {
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            Image icon = Toolkit.getDefaultToolkit().createImage(buffer);
            setIconImage(icon);
            LabeledIcon load = new LabeledIcon("", 0,
                    "application/logo/chargement.gif",
                    (FRAME_WIDTH/2)-200,375,(FRAME_WIDTH/2) ,(FRAME_HEIGHT/2) , // (FRAME_WIDTH/2) - 100
                    backgroungColr, theme.getYelloowAccent(), null);
            LabeledIcon keys = new LabeledIcon("", 0,
                    "application/logo/keys.gif",
                    (FRAME_WIDTH/2) - 240,-20,480,(FRAME_HEIGHT/2) ,
                    backgroungColr, theme.getYelloowAccent(), null);
            load.setOpaque(true);
            contentPane.add(load);
            contentPane.add(keys);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        int posWith = (device.getDisplayMode().getWidth() / 2) - (FRAME_WIDTH/2);
        int posHeight = (device.getDisplayMode().getHeight()/ 2) - (FRAME_HEIGHT/2) ;
        setBounds(posWith, posHeight, FRAME_WIDTH, FRAME_HEIGHT);
    }


    public void close() {

    }

    public static void loading(){
        int percent;

        for (percent = 0; percent<=350; percent++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static boolean main() {
        SplashScreen splashScreen = new SplashScreen();
        splashScreen.setVisible(true);
        loading();
        splashScreen.dispose();
        return true;
    }

}
