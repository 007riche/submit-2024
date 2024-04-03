package Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.Button;

import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.Utility.Theme;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.util.List;

public class VerticalMenuButtonPanel extends JPanel {
    private JButton innerButton;
    private boolean selected;
    private Theme theme = new Theme();



    public VerticalMenuButtonPanel(JButton button) {
        this.innerButton = button;
        initComponent();
    }

    public JButton getInnerButton() {
        return this.innerButton;
    }
    private void initComponent() {
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 450, Short.MAX_VALUE)
                        .addComponent(this.innerButton, GroupLayout.Alignment.TRAILING,
                                GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE) // GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
                        .addComponent(this.innerButton, GroupLayout.DEFAULT_SIZE,
                               40, Short.MAX_VALUE) //  GroupLayout.DEFAULT_SIZE
        );
        setLayout(layout);
        repaint();
    }


    public void setSelected(boolean seleceted) {
        this.selected = seleceted;
        updateButtonAppearance();
        repaint();
    }

    private void updateButtonAppearance() {
        if (this.selected) {
            setBorder(new MatteBorder(0, 7, 0, 0, theme.getRed()));
//            System.out.println("Selected: <<"+getText()+">>");
        } else {
            setBorder(null);
        }
    }


}
