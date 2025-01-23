import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ScreenUI {
    private int iconX = 330; // Initial x-position of the icon
    private int iconY = 400; // Y-position of the icon (fixed)
    private int iconSpeed = 3; // Speed of movement
    private boolean movingRight = true; // Direction flag
    GamePanel gp;
    public BufferedImage image;

    public ScreenUI(GamePanel gp){
        this.gp=gp;
        getPlayerImage();
    }
   //------------------Start Panel------------------------------
   private boolean isJumping = false; // To track if the icon is in the "jump" phase
    private int jumpHeight = 150;       // How high the icon jumps
    private int jumpState = 0;         // To track the jump progression (0 = ground, 1 = up, 2 = down)
    private int initialY=400;
    public void updateTitleScreen() {
        if (isJumping) {
            // Handle the jump movement
            if (jumpState == 1) {
                iconY -= iconSpeed; // Move up
                if (iconY <= initialY - jumpHeight) { // Reached jump height
                    jumpState = 2; // Start moving down
                }
            } else if (jumpState == 2) {
                iconY += iconSpeed; // Move down
                if (iconY >= initialY) { // Back to original level
                    iconY = initialY; // Ensure exact alignment
                    isJumping = false; // End the jump
                    jumpState = 0; // Reset jump state
                }
            }
        } else {
            // Handle horizontal movement
            if (movingRight) {
                iconX += iconSpeed;
                if (iconX >= gp.screenWidth - 100) { // Right boundary
                    iconX = gp.screenWidth - 100; // Clamp
                    isJumping = true; // Start jump
                    jumpState = 1; // Move up
                    movingRight = false; // Reverse direction
                }
            } else {
                iconX -= iconSpeed;
                if (iconX <= 0) { // Left boundary
                    iconX = 0; // Clamp
                    isJumping = true; // Start jump
                    jumpState = 1; // Move up
                    movingRight = true; // Reverse direction
                }
            }
        }
    }


    public void drawTitleScreen(Graphics2D g2) {
        // Background
        g2.setColor(new Color(34, 36, 49)); // Dark theme background
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Title Text
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60));
        String text = "Labyrinth Adventure";

        g2.setColor(Color.BLACK); // Shadow for title
        g2.drawString(text, 100, 105);

        g2.setColor(Color.WHITE); // Main title color
        g2.drawString(text, 95, 100);

        // Moving Game Icon
        g2.drawImage(this.image, iconX, iconY, 60, 65, null);



        // Start Button
        int buttonWidth = 250;
        int buttonHeight = 70;
        int startX = 270;
        int startY = 200;
        drawButton(g2,"Start",startX,startY,new Color(77, 74, 232), new Color(55, 51, 229));



        // Exit Button
        int exitY = startY + buttonHeight + 10;
        drawButton(g2,"Exit",startX,exitY,new Color(77, 74, 232), new Color(55, 51, 229));

        // Mouse click detection (using MouseAdapter)
        gp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Check if mouse click is within the button bounds
                if (e.getX() >= startX && e.getX() <= startX + buttonWidth &&
                        e.getY() >= startY && e.getY() <= startY + buttonHeight) {
                    gp.setCurrentState(GameState.PLAYER_SELECTION);  // Change game state
                }
            }
        });
    }
    private void drawButton(Graphics2D g2, String label, int x, int y, Color baseColor, Color hoverColor) {
        int buttonWidth = 200;
        int buttonHeight = 50;
        int arcWidth = 20;
        int arcHeight = 20;

        // Draw button background
        g2.setPaint(new GradientPaint(0, y, baseColor.brighter(), 0, y + buttonHeight, baseColor));
        g2.fillRoundRect(x, y, buttonWidth, buttonHeight, arcWidth, arcHeight);

        // Draw border
        g2.setColor(baseColor.darker());
        g2.drawRoundRect(x, y, buttonWidth, buttonHeight, arcWidth, arcHeight);

        // Add shadow and glow
        g2.setColor(new Color(255, 255, 255, 40)); // Light glow
        g2.fillRoundRect(x + 2, y + 2, buttonWidth - 4, buttonHeight - 4, arcWidth, arcHeight);

        // Button Label
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 25));
        g2.setColor(Color.WHITE);
        int textWidth = g2.getFontMetrics().stringWidth(label);
        int textX = x + (buttonWidth - textWidth) / 2;
        int textY = y + (buttonHeight + g2.getFontMetrics().getAscent()) / 2 - 5;
        g2.drawString(label, textX, textY);
    }
    public void getPlayerImage(){
        try {
            this.image= ImageIO.read(getClass().getResourceAsStream("/assets/Player/alienGreen.png"));


        }catch (IOException e){
            e.printStackTrace();
        }
    }
    //------------------PLAYER_SELECTION------------------------------
    public void drawPlayerSelection(Graphics2D g2){
        // Background
        g2.setColor(new Color(34, 36, 49)); // Dark theme background
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Title Text
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60));
        String text = "Labyrinth Adventure";

        g2.setColor(Color.BLACK); // Shadow for title
        g2.drawString(text, 100, 105);

        g2.setColor(Color.WHITE); // Main title color
        g2.drawString(text, 95, 100);

        // Moving Game Icon
        g2.drawImage(this.image, iconX, iconY, 60, 65, null);
        // 1player Button
        int buttonWidth = 300;
        int buttonHeight = 70;
        int startX = 270;
        int startY = 200;
        drawButton(g2,"1 Player",startX,startY,new Color(77, 74, 232), new Color(55, 51, 229));
        gp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Check if mouse click is within the button bounds
                if (e.getX() >= startX && e.getX() <= startX + buttonWidth &&
                        e.getY() >= startY && e.getY() <= startY + buttonHeight) {
                    gp.setCurrentState(GameState.DIFFICULTY_SELECTION);  // Change game state
                }
            }
        });
        // 2Player Button
        int exitY = startY + buttonHeight + 10;
        drawButton(g2,"2 Player",startX,exitY,new Color(77, 74, 232), new Color(55, 51, 229));
    }
    //------------------DIFFICULTY_SELECTION------------------------------

    public void drawDifficultySelection(Graphics2D g2){
        // Background
        g2.setColor(new Color(34, 36, 49)); // Dark theme background
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Title Text
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60));
        String text = "Labyrinth Adventure";

        g2.setColor(Color.BLACK); // Shadow for title
        g2.drawString(text, 100, 105);

        g2.setColor(Color.WHITE); // Main title color
        g2.drawString(text, 95, 100);

        // Moving Game Icon
        g2.drawImage(this.image, iconX, iconY, 60, 65, null);
        // Easy Button
        int buttonWidth = 300;
        int buttonHeight = 70;
        int startX = 270;
        int startY = 200;
        drawButton(g2,"Easy",startX,startY,new Color(77, 74, 232), new Color(55, 51, 229));
        gp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Check if mouse click is within the button bounds
                if (e.getX() >= startX && e.getX() <= startX + buttonWidth &&
                        e.getY() >= startY && e.getY() <= startY + buttonHeight) {
                    gp.setCurrentState(GameState.PLAY);  // Change game state
                }
            }
        });
        // Medium Button
        int mediumY = startY + buttonHeight + 10;
        drawButton(g2,"Medium",startX,mediumY,new Color(77, 74, 232), new Color(55, 51, 229));

        // Hard Button
        int hardY = mediumY + buttonHeight + 10;
        drawButton(g2,"Hard",startX,hardY,new Color(77, 74, 232), new Color(55, 51, 229));
    }
    //------------------PLAY------------------------------

    public void drawPlay(Graphics2D g2){
        // Background
        g2.setColor(new Color(34, 36, 49)); // Dark theme background
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Title Text
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60));
        String text = "Labyrinth Adventure";

        g2.setColor(Color.BLACK); // Shadow for title
        g2.drawString(text, 100, 105);

        g2.setColor(Color.WHITE); // Main title color
        g2.drawString(text, 95, 100);

        // Moving Game Icon
        g2.drawImage(this.image, iconX, iconY, 60, 65, null);

        // play Button
        int buttonWidth = 300;
        int buttonHeight = 70;
        int startX = 270;
        int startY = (gp.getHeight() - buttonHeight) /2;
        drawButton(g2,"Play",startX,startY,new Color(77, 74, 232), new Color(55, 51, 229));
        gp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Check if mouse click is within the button bounds
                if (e.getX() >= startX && e.getX() <= startX + buttonWidth &&
                        e.getY() >= startY && e.getY() <= startY + buttonHeight) {
                    gp.setCurrentState(GameState.GAME);  // Change game state
                }
            }
        });



    }
    //------------------SCORE------------------------------

    public void drawScore(Graphics2D g2){
        // Background
        g2.setColor(new Color(34, 36, 49)); // Dark theme background
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Title Text
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60));
        String text = "Your Score is";

        g2.setColor(Color.BLACK); // Shadow for title
        g2.drawString(text, 170, 105);

        g2.setColor(Color.WHITE); // Main title color
        g2.drawString(text, 165, 100);
        // Moving Game Icon
        g2.drawImage(this.image, iconX, iconY, 60, 65, null);

        // Restart Button
        int buttonWidth = 300;
        int buttonHeight = 70;
        int startX = 270;
        int startY = (gp.getHeight() - buttonHeight) /2;
        drawButton(g2,"Restart",startX,startY,new Color(77, 74, 232), new Color(55, 51, 229));
        gp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Check if mouse click is within the button bounds
                if (e.getX() >= startX && e.getX() <= startX + buttonWidth &&
                        e.getY() >= startY && e.getY() <= startY + buttonHeight) {
                    gp.setCurrentState(GameState.START);  // Change game state
                }
            }
        });
        //Exit Button
        int exitY = startY + buttonHeight + 10;
        drawButton(g2,"Exit",startX,exitY,new Color(77, 74, 232), new Color(55, 51, 229));



    }
}
