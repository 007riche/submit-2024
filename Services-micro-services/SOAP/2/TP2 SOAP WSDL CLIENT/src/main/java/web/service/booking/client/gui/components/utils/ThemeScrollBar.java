package web.service.booking.client.gui.components.utils;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class ThemeScrollBar extends BasicScrollBarUI {
    ResourceLoader resourceLoader = new  ResourceLoader();
    private Theme theme = new Theme(128);
    private Theme theme0 = new Theme(255);

    private Color color;
    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        g.setColor(theme0.getIndigo());
        g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        if (thumbBounds.isEmpty() || !scrollbar.isEnabled()) {
            return;
        }
        g.setColor(theme.getGreya());
        g.fillRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height);
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        JButton decreaseButton = new JButton();
        decreaseButton.setIcon(new ImageIcon(resourceLoader.getResource("application/icons/16x16/angle-small-up.png")));
        decreaseButton.setSize(new Dimension(16, 16));
        decreaseButton.setBackground(theme0.getIndigoa());
        decreaseButton.setForeground(theme.getGrey());
        return decreaseButton;
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        JButton increaseButton = new JButton();  // "\u2304"
        increaseButton.setSize(new Dimension(16, 16));
        increaseButton.setBackground(theme0.getIndigoa());
        increaseButton.setForeground(theme.getGrey());
        increaseButton.setIcon(new ImageIcon(resourceLoader.getResource("application/icons/16x16/angle-small-down.png")
        ));

        return increaseButton;
    }

}
