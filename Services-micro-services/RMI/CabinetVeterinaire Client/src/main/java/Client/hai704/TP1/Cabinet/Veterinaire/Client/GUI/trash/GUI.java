//package Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class GUI extends JFrame {
//    final int FRAME_WIDTH = 1000;
//    final int FRAME_HEIGHT = 700;
//
//    public GUI() throws HeadlessException {
//
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//
//        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
//
//        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
//        int posWith = (device.getDisplayMode().getWidth() / 2) - (FRAME_WIDTH/2);
//        int posHeight = (device.getDisplayMode().getHeight()/ 2) - (FRAME_HEIGHT/2);
//        System.out.println("posW: "+posWith+" PosH: "+posHeight);
//        System.out.println("getWidth(): "+device.getDisplayMode().getWidth()+" getHeight(): "+device.getDisplayMode().getHeight());
////        setBackground(new Color(36, 31, 49));
//        setBounds(posWith, posHeight, FRAME_WIDTH, FRAME_HEIGHT);
//
//    }
//}
