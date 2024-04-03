package Client.hai704.TP1.Cabinet.Veterinaire.Client.GUI.Utility;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class AdaptedScrollBar extends  BasicScrollBarUI {

    static ClassLoader fileLoader = AdaptedScrollBar.class.getClassLoader();
    private Theme theme = new Theme();

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        g.setColor(theme.getBlack35()); //new Color(35, 35, 35));
        g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        if (thumbBounds.isEmpty() || !scrollbar.isEnabled()) {
            return;
        }
        g.setColor(theme.getRed()); //new Color(245, 10, 10));
        g.fillRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height);
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        JButton decreaseButton = new JButton();
        decreaseButton.setIcon(new ImageIcon(fileLoader.getResource("icons/16x16/angle-small-up.png")));
        decreaseButton.setSize(new Dimension(16, 16));
        decreaseButton.setBackground(theme.getBlack29()); //new Color(29, 29, 29));
        decreaseButton.setForeground(theme.getGrey175());  //new Color(175, 177, 179));
        return decreaseButton;
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        JButton increaseButton = new JButton();  // "\u2304"
        increaseButton.setSize(new Dimension(16, 16));
        increaseButton.setBackground(theme.getBlack29());   //new Color(29, 29, 29));
        increaseButton.setForeground(theme.getGrey175());   //new Color(175, 177, 179));
        increaseButton.setIcon(new ImageIcon(fileLoader.getResource("icons/16x16/angle-small-down.png")
               ));

        return increaseButton;
    }
}
