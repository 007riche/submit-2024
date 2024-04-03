//package web.service.booking.client.gui.components.basics.compounded.subsection;
//
//
//import web.service.booking.client.gui.components.utils.Theme;
//import web.service.booking.client.gui.events.ApplicationSectionEvent;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class TitleBarPanel extends JPanel implements ApplicationSectionEvent {
//
//
//     static private String titleInitialText = "";
//    static private String changedTitleText =  titleInitialText ;
//    private JLabel dynamicTitleBarText;
//    static ClassLoader fileLoader = TitleBarPanel.class.getClassLoader();
//    Theme theme = new Theme(0);
//
//    public TitleBarPanel(String titleInitialText) {
//        this.titleInitialText = titleInitialText;
//        changedTitleText =  titleInitialText ;
//        initComponent();
//    }
//
//    private void initComponent() {
//        dynamicTitleBarText = new JLabel(changedTitleText);
//        dynamicTitleBarText.setIcon(new ImageIcon(fileLoader.getResource("icons/24x24/veterinary.png")));
//        dynamicTitleBarText.setIconTextGap(10);
//        dynamicTitleBarText.setBackground(theme.getBlacka());  //new Color(29, 29, 29, 0)
//        dynamicTitleBarText.setForeground(theme.getWhite()); // new Color(205, 207, 209)
//        dynamicTitleBarText.setFont(new Font("Tahoma", Font.PLAIN, 16));
//        dynamicTitleBarText.setBounds(250, 0, 350, 32);
//        setDoubleBuffered(true);
//        setBackground(theme.getBlacka());  //new Color(29, 29, 29, 0)
////        setFocusCycleRoot(true);
//        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
//        setAlignmentY(0.0f);
//        setAlignmentX(0.0f);
//        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
//        setSize(new Dimension(1000, 35));
//        setMaximumSize(new Dimension(32767, 35));
//        setPreferredSize(new Dimension(1000, 35));
//        setMinimumSize(new Dimension(1000, 35));
//        setBounds(new Rectangle(0, 0, 1000, 35));
//        setLayout(null);
//        add(dynamicTitleBarText);
//    }
//
//
//    @Override
//    public void onChange(String section) {
//        changedTitleText = titleInitialText + " - " + section;
//        dynamicTitleBarText.setText(changedTitleText);
//        dynamicTitleBarText.repaint();
//    }
//}
