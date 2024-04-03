package Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.panel;

import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.Utility.Theme;
import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.panel.base.InputField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchUISection extends JPanel {

    private Theme theme = new Theme();
    static ClassLoader fileLoader = SearchUISection.class.getClassLoader();

    public SearchUISection() {
        initComponent();
    }


    private void initComponent() {
        setPreferredSize(new Dimension(550, 400));
        setBackground(new Color(35, 35, 35));
        setBounds(new Rectangle(0, 0, 0, 550));
        setLayout(null);

        JLabel patientNameLabel = new JLabel("Nom du patient");
        patientNameLabel.setIcon(new ImageIcon(fileLoader.getResource("icons/24x24/livestock.png")));
        patientNameLabel.setForeground(new Color(175, 177, 179));
        patientNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        patientNameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        patientNameLabel.setBounds(36, 94, 165, 32);
        add(patientNameLabel);

        JTextField searchPatientNameTextField = new InputField(211, 90, 275, 32,
                10, "Ex: Crocs", theme.getBlack51(), theme.getGrey175());
        add(searchPatientNameTextField);

        JLabel searchMasterNameLabel = new JLabel("Nom du ma\u00EEtre");
        searchMasterNameLabel.setIcon(new ImageIcon(fileLoader.getResource("icons/24x24/man.png")));
        searchMasterNameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
        searchMasterNameLabel.setForeground(new Color(175, 177, 179));
        searchMasterNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        searchMasterNameLabel.setBounds(36, 160, 165, 32);
        add(searchMasterNameLabel);

        JTextField searchMasterNameTextField = new InputField(211, 156, 275, 32,
                10, "Ex: Rock", theme.getBlack51(), theme.getGrey175());
        add(searchMasterNameTextField);

        JButton searchButton = new JButton("Rechercher");
        searchButton.setRequestFocusEnabled(false);
        searchButton.setIcon(new ImageIcon(fileLoader.getResource("icons/24x24/magnifying-glass.png")));
        searchButton.setSize(new Dimension(32, 32));
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        searchButton.setIconTextGap(10);
        searchButton.setBackground(new Color(35, 35, 35));
        searchButton.setForeground(new Color(175, 177, 179));
        searchButton.setBounds(185, 240, 200, 32);
        add(searchButton);
    }
}
