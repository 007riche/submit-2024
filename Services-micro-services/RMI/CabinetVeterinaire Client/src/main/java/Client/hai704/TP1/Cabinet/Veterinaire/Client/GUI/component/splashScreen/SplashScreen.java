package Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.splashScreen;

import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.Utility.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class SplashScreen extends JFrame {
    private JPanel contentPane;
    final int FRAME_WIDTH = 1000;
    final int FRAME_HEIGHT = 700;
    private static JProgressBar loadingBar;
    private static JLabel loadingText;
    public

    static ClassLoader fileLoader = SplashScreen.class.getClassLoader();

    private Theme theme = new Theme(0);

    public SplashScreen() throws HeadlessException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);

        InputStream inputStream = fileLoader.getResourceAsStream("icons/app/logo.png");


        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        contentPane =new JPanel();
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        loadingBar = new JProgressBar();
        loadingBar.setForeground(theme.getBlack51());       //new Color(0, 128, 128));
//        loadingBar.setBounds((FRAME_WIDTH/2) - 256,0,(FRAME_WIDTH/2) + 256,(FRAME_HEIGHT/2) + 256);

        loadingText = new JLabel("0%");
        loadingText.setForeground(theme.getGrey205());
        loadingText.setFont(new Font("Tahoma", Font.PLAIN, 50));


        try {

            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            Image icon = Toolkit.getDefaultToolkit().createImage(buffer);
            setIconImage(icon);

            JLabel label = new JLabel("");
            label.setOpaque(true);
            setBackground(theme.getGrey205a()); //new Color(255, 255, 255, 0));
            label.setBackground(theme.getGrey205a());  //new Color(255, 255, 255, 0));
            ImageIcon splash = new ImageIcon(new ImageIcon(new File(fileLoader.getResource("icons/app/splash.png").toURI()).toURL()).getImage());
            label.setIcon(splash);
            label.setBounds((FRAME_WIDTH/2) - 256,0,(FRAME_WIDTH/2) + 256,(FRAME_HEIGHT/2) + 256);

            SplashScreen.loadingBar.setBounds(100,550,750,20);

            SplashScreen.loadingText.setBounds(250,570, 500,70);

            contentPane.add(label);
            contentPane.add(loadingBar);
            contentPane.add(loadingText);

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        int posWith = (device.getDisplayMode().getWidth() / 2) - (FRAME_WIDTH/2);
        int posHeight = (device.getDisplayMode().getHeight()/ 2) - (FRAME_HEIGHT/2) -100;
//        setBackground(new Color(36, 31, 49));
        setBounds(posWith, posHeight, FRAME_WIDTH, FRAME_HEIGHT);

    }


    public void close() {

    }

    public static void loading(){
        int percent;

        for (percent = 0; percent<=100; percent++) {
            try {
                Thread.sleep(10);
                loadingBar.setValue(percent);
                loadingText.setText("Chargement à: "+Integer.toString(percent)+"%");
            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
                e.printStackTrace();
            }
        }
//        return percent;
    }

    public static boolean main() {
        SplashScreen splashScreen = new SplashScreen();
        splashScreen.setVisible(true);
        loading();
        splashScreen.dispose();
        return true;
    }

}
