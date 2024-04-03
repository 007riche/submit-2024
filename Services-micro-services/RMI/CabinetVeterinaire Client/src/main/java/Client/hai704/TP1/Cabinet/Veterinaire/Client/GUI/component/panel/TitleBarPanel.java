package Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.panel;

import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.Utility.Theme;

import javax.swing.*;
import java.awt.*;

public class TitleBarPanel extends JPanel implements ApplicationSectionEvent {


     static private final String titleInitialText = "Cabinet v\u00E9t\u00E9rinaire";
    static private String changedTitleText =  titleInitialText ;
    private JLabel dynamicTitleBarText;
    static ClassLoader fileLoader = TitleBarPanel.class.getClassLoader();
    Theme theme = new Theme(0);

    public TitleBarPanel() {
        initComponent();
    }

    private void initComponent() {
        dynamicTitleBarText = new JLabel(changedTitleText);
        dynamicTitleBarText.setIcon(new ImageIcon(fileLoader.getResource("icons/24x24/veterinary.png")));
        dynamicTitleBarText.setIconTextGap(10);
        dynamicTitleBarText.setBackground(theme.getBlack35a());  //new Color(29, 29, 29, 0)
        dynamicTitleBarText.setForeground(theme.getGrey205()); // new Color(205, 207, 209)
        dynamicTitleBarText.setFont(new Font("Tahoma", Font.PLAIN, 16));
        dynamicTitleBarText.setBounds(250, 0, 350, 32);
        setDoubleBuffered(true);
        setBackground(theme.getBlack35a());  //new Color(29, 29, 29, 0)
//        setFocusCycleRoot(true);
        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        setAlignmentY(0.0f);
        setAlignmentX(0.0f);
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        setSize(new Dimension(1000, 35));
        setMaximumSize(new Dimension(32767, 35));
        setPreferredSize(new Dimension(1000, 35));
        setMinimumSize(new Dimension(1000, 35));
        setBounds(new Rectangle(0, 0, 1000, 35));
        setLayout(null);
        add(dynamicTitleBarText);
    }


    @Override
    public void onChange(String section) {
        changedTitleText = titleInitialText + " - " + section;
        dynamicTitleBarText.setText(changedTitleText);
        dynamicTitleBarText.repaint();
    }
}
