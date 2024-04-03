package Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.component.Button;

import Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.Utility.Theme;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class VerticalMenuIconButton extends JButton  {
    private boolean selected;
    private String text;
    private String iconPathString;
    private Theme theme = new Theme();
    static ClassLoader fileLoader = VerticalMenuIconButton.class.getClassLoader();

    public void setSeleceted(boolean seleceted) {
        this.selected = seleceted;
        updateButtonAppearance();
        repaint();
    }

    public VerticalMenuIconButton(String text, String iconPathString) {
        super(text);
        this.text = text;
        this.iconPathString = iconPathString;
        setSeleceted(false);
        initComponent();
    }

    private void initComponent() {
        addMouseListener(new MouseAdapter() {

//            @Override
//            public void mouseClicked(MouseEvent e) {
////                super.mouseClicked(e);
////                System.out.println("clicked on "+getText()+" button");
//
//            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(theme.getBlack49());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(theme.getBlack29());
            }
        });

        if (this.iconPathString != null && !this.iconPathString.isEmpty()) {
            setIcon(new ImageIcon(fileLoader.getResource("icons/"+this.iconPathString)));
        }

        setMargin(new Insets(2, 100, 2, 14));
        setIconTextGap(25);
        setForeground(theme.getGrey175());
        setFont(new Font("Tahoma", Font.PLAIN, 18));
        setBorder(null);
        setRequestFocusEnabled(false);
        setBackground(theme.getBlack29());
        updateButtonAppearance();
    }

    private void updateButtonAppearance() {
        if (this.selected) {
            setBorder(new MatteBorder(0, 7, 0, 0, theme.getRed()));
            System.out.println("Selected: <<"+getText()+">>");
        } else {
            setBorder(null);
        }
    }


}
