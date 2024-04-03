package trash;

import javax.swing.*;
import java.awt.*;

public class ScrollableCardsExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Scrollable Cards Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Container panel with GridLayout for 3 columns and variable rows
            JPanel containerPanel = new JPanel(new GridLayout(0, 3, 10, 10)); // 0 means any number of rows, 10 is the gap size

            // Example: Creating and adding 15 card panels to the container panel
            for (int i = 0; i < 45; i++) {
                JPanel cardPanel = createCardPanel(i); // Create a new card panel
                containerPanel.add(cardPanel); // Add the card panel to the container
            }

            // Create the scroll pane and add the container panel to it
            JScrollPane scrollPane = new JScrollPane(containerPanel);
            scrollPane.setPreferredSize(new Dimension(400, 300)); // Optional: Set the preferred size of the scrollPane

            frame.add(scrollPane);
            frame.pack(); // Adjusts frame size to fit the components
            frame.setLocationRelativeTo(null); // Centers the frame on the screen
            frame.setVisible(true);
        });
    }

    private static JPanel createCardPanel(int index) {
        // Method to create a card panel with some example content
        JPanel cardPanel = new JPanel();
        cardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Set a border for visual separation
        cardPanel.add(new JLabel("Card " + index)); // Add a label to the card
        // You can add more components to the card panel here

        return cardPanel;
    }
}
