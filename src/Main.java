import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Labyrinth");

       /* // Create the menu panel
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setPreferredSize(new Dimension(GamePanel.screenWidth, GamePanel.screenHeight));
        menuPanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Welcome to Labyrinth");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuPanel.add(titleLabel);

        // Load the player button image
        ImageIcon playerButtonIcon = new ImageIcon(Main.class.getResource("/assets/Menu/play.png"));

        JButton playButton = new JButton(playerButtonIcon);
        playButton.setPreferredSize(new Dimension(1, 1));  // Smaller button size
        playButton.setFont(new Font("Arial", Font.BOLD, 2));
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.remove(menuPanel); // Rdemove the menu panel

                GamePanel gamePanel = new GamePanel();
                gamePanel.setFocusable(true);   // Make sure the game panel can receive focus
                window.add(gamePanel);          // Add the game panel
                gamePanel.startGameThread();    // Start the game loop

                // Now set the window size and other settings
                window.pack();                    // Adjust window size
                window.setLocationRelativeTo(null); // Center the window
                window.revalidate();              // Refresh the window
                window.repaint();                 // Redraw window

                // Give focus to the game panel so it listens to key events
                gamePanel.requestFocus();
            }
        });
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);  // Center the button
        menuPanel.add(playButton);*/

        GamePanel gamePanel = new GamePanel();
        gamePanel.setFocusable(true);   // Make sure the game panel can receive focus
        window.add(gamePanel);          // Add the game panel
        gamePanel.startGameThread();    // Start the game loop
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
