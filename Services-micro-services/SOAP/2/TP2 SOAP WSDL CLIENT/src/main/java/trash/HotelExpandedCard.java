//package trash;
//
//import javax.imageio.ImageIO;
//import javax.swing.*;
//import javax.swing.border.LineBorder;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//public class HotelExpandedCard extends JPanel {
//    private String hotelImgUrl;
//    private String hotelName;
//    private String hotelAdresse;
//    private double gpsLong;
//    private double gpsLat;
//    private double offerPrice;
//    private int numberBeds;
//
//    public HotelExpandedCard() {
//    }
//
//    public HotelExpandedCard(String hotelImgUrl, String hotelName, String hotelAdresse, double gpsLong, double gpsLat, double offerPrice, int numberBeds) {
//        this.hotelImgUrl = hotelImgUrl;
//        this.hotelName = hotelName;
//        this.hotelAdresse = hotelAdresse;
//        this.gpsLong = gpsLong;
//        this.gpsLat = gpsLat;
//        this.offerPrice = offerPrice;
//        this.numberBeds = numberBeds;
//
//
////        JPanel panel = new JPanel();
////        panel.setBackground(new Color(255, 255, 255));
////        panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
////        panel.setBounds(90, 116, 670, 200);
////        frame.getContentPane().add(panel);
////        panel.setLayout(null);
//
//        this.setBackground(new Color(255, 255, 255));
////        this.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
//        this.setBounds(0, 0, 670, 500);
//        this.setLayout(null);
//
//        JPanel panel_1 = new JPanel();
////        panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
//        panel_1.setBackground(new Color(255, 255, 255));
//        panel_1.setBounds(0, 0, 240, 500);
//        this.add(panel_1);
//        panel_1.setLayout(null);
//
//        JPanel panel_7 = new JPanel();
//        panel_7.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
//        panel_7.setBounds(12, 6, 216, 185);
//        panel_1.add(panel_7);
//        panel_7.setLayout(null);
//
//        JButton imgButton = new JButton(new ImageIcon("/home/richard/hotels.jpg"));
//        imgButton.setMargin(new Insets(0, 14, 0, 14));
//        imgButton.setBorder(new LineBorder(new Color(0, 0, 0), 2));
//        imgButton.setBounds(0, 0, 216, 185);
//        panel_7.add(imgButton);
//
//        BufferedImage myPicture = null;
//        try {
////	            URL url = new URL("https://cache.marriott.com/marriottassets/marriott/VIEIL/vieil-facade-9816-hor-feat.jpg");
//            myPicture = ImageIO.read(new File("/home/richard/hotels.jpg"));  // new File("/home/richard/hotels.jpg") // url
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//
//
//        JPanel panel_2 = new JPanel();
//        panel_2.setBackground(new Color(255, 255, 255));
//        panel_2.setBounds(239, 0, 430, 35);
//        this.add(panel_2);
//        panel_2.setLayout(null);
//
//        JTextPane hotelNameTextPane = new JTextPane();
//        hotelNameTextPane.setFont(new Font("Quicksand", Font.BOLD | Font.ITALIC, 18));
//        hotelNameTextPane.setText("Hotel Savoie Blanc");
//        hotelNameTextPane.setEditable(false);
//        hotelNameTextPane.setBounds(12, 5, 410, 23);
//        panel_2.add(hotelNameTextPane);
//
//        JPanel panel_3 = new JPanel();
//        panel_3.setBackground(new Color(255, 255, 255));
//        panel_3.setBounds(239, 36, 430, 29);
//        this.add(panel_3);
//        panel_3.setLayout(null);
//
//        JTextPane stars = new JTextPane();
//        stars.setMargin(new Insets(0, 3, 0, 3));
//        stars.setForeground(new Color(229, 165, 10));
//        stars.setFont(new Font("Dialog", Font.BOLD, 30));
//        stars.setText("\u2605\u2605\u2605\u2605\u2606");
//        stars.setEditable(false);
//        stars.setBounds(12, 2, 410, 27);
//        panel_3.add(stars);
//
//        JPanel panel_4 = new JPanel();
//        panel_4.setBackground(new Color(255, 255, 255));
//        panel_4.setBounds(239, 65, 217, 84);
//        this.add(panel_4);
//        panel_4.setLayout(null);
//
//        JTextPane address = new JTextPane();
//        address.setText("Adresse:\nGPS:\n");
//        address.setEditable(false);
//        address.setBounds(12, 12, 193, 60);
//        panel_4.add(address);
//
//        JPanel panel_5 = new JPanel();
//        panel_5.setBackground(new Color(255, 255, 255));
//        panel_5.setBounds(455, 65, 214, 83);
//        this.add(panel_5);
//        panel_5.setLayout(null);
//
//        JTextPane priceTextPane = new JTextPane();
//        priceTextPane.setText("A Partir de \nNom Du Type de chambre bjlhjjlgj gvgkho\n2 Lits\nNeds");
//        priceTextPane.setEditable(false);
//        priceTextPane.setBounds(12, 12, 190, 60);
//        panel_5.add(priceTextPane);
//
//        JPanel panel_6 = new JPanel();
//        panel_6.setBackground(new Color(255, 255, 255));
//        panel_6.setBounds(239, 165, 430, 37);
//        this.add(panel_6);
//        panel_6.setLayout(null);
//
//        JButton availaibilityButton = new JButton("Voir les disponibilites");
//        availaibilityButton.setForeground(new Color(255, 255, 255));
//        availaibilityButton.setBackground(new Color(26, 95, 180));
//        availaibilityButton.setBorder(null);
//        availaibilityButton.setBounds(12, 0, 410, 25);
//        panel_6.add(availaibilityButton);
//    }
//}