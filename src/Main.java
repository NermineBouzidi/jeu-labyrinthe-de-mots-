import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Labyrinth");
        GamePanel gamePanel = new GamePanel();
        gamePanel.setFocusable(true);   // Make sure the game panel can receive focus
        window.add(gamePanel);          // Add the game panel
        gamePanel.startGameThread();    // Start the game loop
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);}
}