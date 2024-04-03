package Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.Button;

import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.Utility.Theme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class AppTitlePanel extends JPanel {
    private JButton titleButton;

    private Theme theme = new Theme();
    public AppTitlePanel(String titleText) {
        setBorder(null);
        setBackground(theme.getGrey205a());
        setForeground(theme.getGrey175());
        setEnabled(false);
        setRequestFocusEnabled(false);

        titleButton = new VerticalMenuIconButton(titleText,"64x64/veterinary.png");
        titleButton.setFocusPainted(false);
        titleButton.setBorderPainted(false);
        titleButton.setContentAreaFilled(false);
        titleButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
        titleButton.setLayout(null);

        GroupLayout layout = new GroupLayout(this);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(titleButton, GroupLayout.PREFERRED_SIZE,
                                        450, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(5)
                                .addComponent(titleButton,
                                        GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE))
        );
        MouseListener[] currentListeners = titleButton.getMouseListeners();
        for (MouseListener listener : currentListeners) {
            removeMouseListener(listener);
        }
        setLayout(layout);
    }

    public JButton getTitleButton() {
        return titleButton;
    }

}
