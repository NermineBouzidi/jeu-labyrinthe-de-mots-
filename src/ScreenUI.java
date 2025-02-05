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
    GamePanel gp;
    BufferedImage playerImage,circleImage,tableImage,cloudImage,scoreImage,replayImage;
    private boolean startSoundPlayed = false;
    private boolean buttonSoundPlayed = false;



    public ScreenUI(GamePanel gp){
        this.gp=gp;
        getBackgroundImage();
        getPlayerImage();
        getCircleImage();
        getCloudImage();

    }

    //------------------Draw Loading Screen-----------------
    private int progress = 0;
    private Timer timer;
    private boolean loadingComplete = false;

    public void drawLoadingScreen(Graphics2D g2) {
        // Background
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, gp.getWidth(), gp.getHeight()); // Adjust dimensions as needed

        g2.setColor(new Color(34, 36, 49)); // Dark theme background
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Gradient colors
        Color color1 = new Color(11, 19, 43);  // #0B132B
        Color color5 = new Color(11, 19, 43);  // #0B132B
        GradientPaint gradient = new GradientPaint(0, 0, color1, 0, gp.getHeight(), color5);
        g2.setPaint(gradient);
        g2.fillRect(0, 0, gp.getWidth(), gp.getHeight());

        int floatOffset = (int) (10 * Math.sin(System.currentTimeMillis() * 0.005)); // Smooth floating motion

        //draww circle
        g2.drawImage(circleImage, gp.getWidth()/2 -100, gp.getHeight()/2-200, 200, 200, gp);
        g2.drawImage(playerImage, gp.getWidth() / 2 - 30, gp.getHeight() / 2 - 150 + floatOffset, 60, 65, gp);

        //draw progess bar border
        g2.drawImage(tableImage, gp.getWidth()/2 -350, gp.getHeight()/2+30+50, 700, 20, gp);


        // Draw Progress Bar Fill
        g2.setColor(Color.CYAN);
        g2.fillRect(gp.getWidth()/2-333, gp.getHeight()/2+33+50, (int) (progress * 2.98), 15); // Scales 100% progress to 300px width

        // Start a Timer (only once)
        if (timer == null) {
            timer = new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    progress++;
                    if (progress >= 224) {
                        timer.stop();
                        loadingComplete = true; // Mark as finished
                    }
                }
            });
            timer.start();
        }
        if (loadingComplete) {
            if (!startSoundPlayed) {
                gp.playSE(4);
                startSoundPlayed = true; // Mark the sound as played
                gp.setCurrentState(GameState.START);
            }
        }
    }

    //-------------------Title Screen--------------------------
    public void drawTitleScreen(Graphics2D g2) {
        // Background
        g2.setColor(new Color(34, 36, 49)); // Dark theme background
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Gradient colors
        Color color1 = new Color(11, 19, 43);  // #0B132B
        Color color5 = new Color(11, 19, 43);  // #0B132B
        int width = gp.getWidth();
        int height = gp.getHeight();
        GradientPaint gradient = new GradientPaint(0, 0, color1, 0, height, color5);

        g2.setPaint(gradient);
        g2.fillRect(0, 0, width, height);

        //  g2.drawImage(background, 200, 150, width/2, height/2, null);
        int floatOffset = (int) (10 * Math.sin(System.currentTimeMillis() * 0.005)); // Smooth floating motion
        g2.drawImage(playerImage, gp.getWidth() / 2 - 30, gp.getHeight() / 2 - 150 + floatOffset, 60, 65, gp);

        // Title Text
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60));
        String text = "Labyrinth Adventure";

        g2.setColor(Color.BLACK); // Shadow for title
        g2.drawString(text, gp.getWidth()/2-300+5 , 105);

        g2.setColor(Color.WHITE); // Main title color
        g2.drawString(text, gp.getWidth()/2-300 , 100);

        // Moving Game Icon



        // Start Button
        int buttonWidth = 250;
        int buttonHeight = 70;
        int startX = gp.getWidth()/2 -100;
        int startY = gp.getHeight()/2;
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
                if (!buttonSoundPlayed) {
                    gp.playSE(8);
                    buttonSoundPlayed = true;
                }

            }
        });
        // Mouse click detection (using MouseAdapter)
        gp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Check if mouse click is within the button bounds
                if (e.getX() >= startX && e.getX() <= startX + buttonWidth &&
                        e.getY() >= exitY && e.getY() <= exitY + buttonHeight) {
                    gp.setCurrentState(GameState.PLAYER_SELECTION);  // Change game state
                }
                if (!buttonSoundPlayed) {
                    gp.playSE(8);
                    buttonSoundPlayed = true;
                }

            }
        });
    }

    //------------------PLAYER_SELECTION------------------------------
    public void drawPlayerSelection(Graphics2D g2){
// Background
        g2.setColor(new Color(34, 36, 49)); // Dark theme background
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Gradient colors
        Color color1 = new Color(11, 19, 43);  // #0B132B
        Color color5 = new Color(11, 19, 43);  // #0B132B
        int width = gp.getWidth();
        int height = gp.getHeight();
        GradientPaint gradient = new GradientPaint(0, 0, color1, 0, height, color5);

        g2.setPaint(gradient);
        g2.fillRect(0, 0, width, height);
        //  g2.drawImage(background, 200, 150, width/2, height/2, null);
        int floatOffset = (int) (10 * Math.sin(System.currentTimeMillis() * 0.005)); // Smooth floating motion
        g2.drawImage(playerImage, gp.getWidth() / 2 - 30, gp.getHeight() / 2 - 150 + floatOffset, 60, 65, gp);

        // Title Text
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60));
        String text = "Labyrinth Adventure";

        g2.setColor(Color.BLACK); // Shadow for title
        g2.drawString(text, gp.getWidth()/2-300+5 , 105);

        g2.setColor(Color.WHITE); // Main title color
        g2.drawString(text, gp.getWidth()/2-300 , 100);
        // 1player Button
        int buttonWidth = 300;
        int buttonHeight = 70;
        int startX = gp.getWidth()/2 -100;
        int startY = gp.getHeight()/2;
        drawButton(g2,"1 Player",startX,startY,new Color(77, 74, 232), new Color(55, 51, 229));
        gp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Check if mouse click is within the button bounds
                if (e.getX() >= startX && e.getX() <= startX + buttonWidth &&
                        e.getY() >= startY && e.getY() <= startY + buttonHeight) {
                    gp.setCurrentState(GameState.DIFFICULTY_SELECTION);  // Change game state
                    // gp.playSE(1);
                }
                if (buttonSoundPlayed) {
                    gp.playSE(8);
                    buttonSoundPlayed = false;
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

        // Gradient colors
        Color color1 = new Color(11, 19, 43);  // #0B132B
        Color color5 = new Color(11, 19, 43);  // #0B132B
        int width = gp.getWidth();
        int height = gp.getHeight();
        GradientPaint gradient = new GradientPaint(0, 0, color1, 0, height, color5);

        g2.setPaint(gradient);
        g2.fillRect(0, 0, width, height);
        //  g2.drawImage(background, 200, 150, width/2, height/2, null);
        int floatOffset = (int) (10 * Math.sin(System.currentTimeMillis() * 0.005)); // Smooth floating motion
        g2.drawImage(playerImage, gp.getWidth() / 2 - 30, gp.getHeight() / 2 - 150 + floatOffset, 60, 65, gp);

        // Title Text
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60));
        String text = "Labyrinth Adventure";

        g2.setColor(Color.BLACK); // Shadow for title
        g2.drawString(text, gp.getWidth()/2-300+5 , 105);

        g2.setColor(Color.WHITE); // Main title color
        g2.drawString(text, gp.getWidth()/2-300 , 100);
// Easy Button
        int buttonWidth = 300;
        int buttonHeight = 70;
        int startX = gp.getWidth()/2 -100;
        int startY = gp.getHeight()/2;
        drawButton(g2,"Easy",startX,startY,new Color(77, 74, 232), new Color(55, 51, 229));
        gp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Check if mouse click is within the button bounds
                if (e.getX() >= startX && e.getX() <= startX + buttonWidth &&
                        e.getY() >= startY && e.getY() <= startY + buttonHeight) {
                    gp.setCurrentState(GameState.PLAY);  // Change game state
                }
                if (!buttonSoundPlayed) {
                    gp.playSE(8);
                    buttonSoundPlayed = true;
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

        // Gradient colors
        Color color1 = new Color(11, 19, 43);  // #0B132B
        Color color5 = new Color(11, 19, 43);  // #0B132B
        int width = gp.getWidth();
        int height = gp.getHeight();
        GradientPaint gradient = new GradientPaint(0, 0, color1, 0, height, color5);

        g2.setPaint(gradient);
        g2.fillRect(0, 0, width, height);
        //  g2.drawImage(background, 200, 150, width/2, height/2, null);
        int floatOffset = (int) (10 * Math.sin(System.currentTimeMillis() * 0.005)); // Smooth floating motion
        g2.drawImage(playerImage, gp.getWidth() / 2 - 30, gp.getHeight() / 2 - 150 + floatOffset, 60, 65, gp);
// Title Text
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60));
        String text = "Labyrinth Adventure";

        g2.setColor(Color.BLACK); // Shadow for title
        g2.drawString(text, gp.getWidth()/2-300+5 , 105);

        g2.setColor(Color.WHITE); // Main title color
        g2.drawString(text, gp.getWidth()/2-300 , 100);
        // play Button
        int buttonWidth = 300;
        int buttonHeight = 70;
        int startX = gp.getWidth()/2 -100;
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
                if (buttonSoundPlayed) {
                    gp.playSE(8);
                    buttonSoundPlayed = false;
                }

            }
        });
    }

    //------------------SCORE------------------------------
    public void drawScore(Graphics2D g2){
        // Background
        g2.setColor(new Color(34, 36, 49)); // Dark theme background
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Gradient colors
        Color color1 = new Color(11, 19, 43);  // #0B132B
        Color color5 = new Color(11, 19, 43);  // #0B132B
        int width = gp.getWidth();
        int height = gp.getHeight();
        GradientPaint gradient = new GradientPaint(0, 0, color1, 0, height, color5);

        g2.setPaint(gradient);
        g2.fillRect(0, 0, width, height);
        g2.drawImage(cloudImage, gp.getWidth()/2 -300, gp.getHeight()/2-200, 600, 400, gp);
        int floatOffset = (int) (10 * Math.sin(System.currentTimeMillis() * 0.005)); // Smooth floating motion
        g2.drawImage(playerImage, gp.getWidth() / 2 - 30, gp.getHeight() / 2 - 150 + floatOffset, 60, 65, gp);
        g2.drawImage(scoreImage, gp.getWidth()/2 -70, gp.getHeight()/2-75, 140, 50, gp);
        g2.drawImage(replayImage, gp.getWidth()/2 -90, gp.getHeight()/2+70, 55, 55, gp);

    }
    //----------Game----
    public void drawGame(Graphics2D g2, Playground grid, Player player)
    {
        //this.player = new Player(this.grid) ;
        grid.shownodes(g2) ;
        player.drawplayer(g2);
    }

    //--------------Button---------------
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

    //---------PLayer Image---------------------
    public void getPlayerImage(){
        try {
            this.playerImage= ImageIO.read(getClass().getResourceAsStream("/assets/Player/alienGreen.png"));


        }catch (IOException e){
            e.printStackTrace();
        }
    }
    //---------Circle Image---------------------
    public void getCircleImage(){
        try {
            this.circleImage= ImageIO.read(getClass().getResourceAsStream("/assets/Tiles/Bonus_BTN_02.png"));


        }catch (IOException e){
            e.printStackTrace();
        }
    }
    //---------Circle Image---------------------
    public void getBackgroundImage(){
        try {
            this.tableImage= ImageIO.read(getClass().getResourceAsStream("/assets/Button/Boss_HP_Table.png"));


        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void getCloudImage(){
        try {
            this.cloudImage= ImageIO.read(getClass().getResourceAsStream("/assets/Button/Cloud_02.png"));
            this.scoreImage= ImageIO.read(getClass().getResourceAsStream("/assets/Button/Score.png"));
            this.replayImage= ImageIO.read(getClass().getResourceAsStream("/assets/Button/Replay_BTN.png"));


        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
