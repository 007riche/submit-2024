package Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.panel.base;

import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.Utility.Theme;
import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.panel.AlertFromServerEvent;
import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.panel.DisplayAlertEvent;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class AlertBox extends JPanel implements AlertFromServerEvent {

    private int initThreshold=0;
    private JButton alertDisabledIconButton;
    private JButton closeAlertButton;
    static private final String initialAlertText = "Votre Palier actuel est de ";
    static private final String newAlertText = "Nouveau:Palier actuel: ";
    JLabel newLabel;

    List<DisplayAlertEvent> alertClosers = new ArrayList<DisplayAlertEvent>();

    static ClassLoader fileLoader = AlertBox.class.getClassLoader();
    Theme theme = new Theme();

    public AlertBox(int initThreshold)
    {
        this.initThreshold = initThreshold;
        initComponent();
    }

    private void initComponent() {
        setLayout(null);
        setForeground( theme.getGrey175() );
        setBorder(new LineBorder(theme.getGrey175(), 3, true));
        setBackground(theme.getBlack35());
        setBounds(240, 156, 300, 175);

        newLabel = new JLabel(initialAlertText+this.getInitThreshold());
        newLabel.setHorizontalAlignment(SwingConstants.CENTER);
        newLabel.setForeground(theme.getGrey175());
        newLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        newLabel.setBounds(90, 20, 200, 125);
        add(newLabel);

        closeAlertButton = new JButton("");
        closeAlertButton.setRequestFocusEnabled(false);
        closeAlertButton.setBorder(null);
        closeAlertButton.setIcon(new ImageIcon(fileLoader.getResource("icons/24x24/circle-xmark.png")));
        closeAlertButton.setBackground(theme.getBlack35());
        closeAlertButton.setForeground(theme.getGrey175());
        closeAlertButton.setBounds(265, 10, 26, 26);
        add(closeAlertButton);

        alertDisabledIconButton = new JButton("");
        alertDisabledIconButton.setEnabled(false);
        alertDisabledIconButton.setRequestFocusEnabled(false);
        alertDisabledIconButton.setIcon(new ImageIcon(fileLoader.getResource("icons/64x64/bells.png")));
        alertDisabledIconButton.setBackground(theme.getBlack35());
        alertDisabledIconButton.setForeground(theme.getGrey175());
        alertDisabledIconButton.setBorder(null);
        alertDisabledIconButton.setBounds(10, 50, 85, 80);
        alertDisabledIconButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                notifyCloseObservers();
            }
        });
        add(alertDisabledIconButton);
    }


    @Override
    public void onReceiveAlert(int threshold) {
        newLabel.setText(newAlertText+threshold);
        newLabel.setForeground(theme.getYellow252());
        newLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        setBorder(new LineBorder(theme.getYellow(), 3, true));
        notifyDisplayObservers();
    }

    public void attach(DisplayAlertEvent newObserver) {
        alertClosers.add(newObserver);
    }

    public void detach(DisplayAlertEvent observer) {
        alertClosers.remove(observer);
    }

    public void notifyCloseObservers() {
        for (DisplayAlertEvent o: alertClosers) {
          o.closeAlert();
        }
    }

    public void notifyDisplayObservers() {
        for (DisplayAlertEvent o: alertClosers) {
            o.displayAlert();
        }
    }

    public int getInitThreshold() {
        return initThreshold;
    }

    public void setInitThreshold(int initThreshold) {
        this.initThreshold = initThreshold;
    }
}
