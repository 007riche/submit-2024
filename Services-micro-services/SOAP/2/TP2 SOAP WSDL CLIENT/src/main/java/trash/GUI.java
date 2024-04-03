//package trash;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import java.io.File;
//import java.io.IOException;
//import java.net.URL;
//
//public class GUI extends JFrame {
//    final int FRAME_WIDTH = 1000;
//    final int FRAME_HEIGHT = 700;
//    JMenuBar menuBar = new JMenuBar();
//    JMenu fichier = new JMenu("Fichier");
//    JMenu editer = new JMenu("Editer");
//    JMenu aide = new JMenu("Aide");
//
//    public GUI(URL proxy) {
//        super("Réservation");
//
//        // For Theming
//        Color mainContentPaneColor = new Color(252, 251, 245); // 33, 33, 32
//        Color mainContentPaneColorDark = new Color(32, 34, 66);
//
//
//        fichier.setForeground(mainContentPaneColor);
//        editer.setForeground(mainContentPaneColor);
//        aide.setForeground(mainContentPaneColor);
//        menuBar.add(fichier);
//        menuBar.add(editer);
//        menuBar.add(aide);
//        menuBar.setBorderPainted(false);
//        menuBar.add(Box.createRigidArea(new Dimension(FRAME_WIDTH, 32)));
//        menuBar.setBackground(mainContentPaneColorDark);
//        menuBar.setForeground(mainContentPaneColor);
//
//        // Main frame color
//        super.getContentPane().setBackground(mainContentPaneColorDark);
//        setJMenuBar(menuBar);
//
//        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
//        try {
//            Font gruppoRegular = Font.createFont(Font.TRUETYPE_FONT, new File("/home/richard/Documents/collab/M1/Architectures Logicielles Distribuées/Service Web SOAP-WSDL/TP-SW-SOAP/Exo 2/version1/ClientReservation/src/main/java/web/service/booking/client/gui/fonts/PoiretOne-Regular.ttf")).deriveFont(14);
//            GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
//                                                environment.registerFont(gruppoRegular);
//
//        } catch (FontFormatException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        int posWith = (device.getDisplayMode().getWidth() / 2) - (FRAME_WIDTH/2);
//        int posHeight = (device.getDisplayMode().getHeight()/ 2) - (FRAME_HEIGHT/2);
//        setSize(FRAME_WIDTH, FRAME_HEIGHT);
//        super.setLocation(posWith, posHeight);
//        addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                GUI.super.dispose();
//            }
//        });
//        setVisible(true);
//    }
//}
