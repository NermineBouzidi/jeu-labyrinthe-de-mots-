import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player {
    public Node playerNode;
    GamePanel gp;
    KeyHandler keyH;
    public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
    public String direction;
    private Labyrinth labyrinth;  // Reference to the Labyrinth class




    public Player(GamePanel gp, KeyHandler kh,Labyrinth labyrinth){
        this.gp=gp;
        this.keyH=kh;
        this.labyrinth=labyrinth;
        this.playerNode=labyrinth.labyrinth[1][1];
        this.direction="down";
        getPlayerImage();
    }
   /* public void setDefaultValues(int col, int row) {
        this.playerNode = col * labyrinth.NO;  // Convert column to actual pixel coordinates
        //this.y = row * labyrinth.NODE_SIZE;  // Convert row to actual pixel coordinates
        this.direction = "down";  // Default movement direction
    }*/

    public void getPlayerImage(){
        try {
            up1= ImageIO.read(getClass().getResourceAsStream("/assets/Player/alienGreen.png"));
            up2= ImageIO.read(getClass().getResourceAsStream("/assets/Player/alienGreen.png"));
            down1= ImageIO.read(getClass().getResourceAsStream("/assets/Player/alienGreen.png"));
            down2= ImageIO.read(getClass().getResourceAsStream("/assets/Player/alienGreen.png"));
            left1= ImageIO.read(getClass().getResourceAsStream("/assets/Player/alienGreen_walk1-left.png"));
            left2= ImageIO.read(getClass().getResourceAsStream("/assets/Player/alienGreen.png"));
            right1= ImageIO.read(getClass().getResourceAsStream("/assets/Player/alienGreen_walk1.png"));
            right2= ImageIO.read(getClass().getResourceAsStream("/assets/Player/alienGreen.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void update() {
        if (keyH.upPressed) {
            direction = "up";
            keyH.upPressed = false; // Prevent repeated movement
            movePlayer(-1, 0);
        } else if (keyH.downPressed) {
            direction = "down";
            keyH.downPressed = false;
            movePlayer(1, 0);
        } else if (keyH.leftPressed) {
            direction = "left";
            keyH.leftPressed = false;
            movePlayer(0, -1);
        } else if (keyH.rightPressed) {
            direction = "right";
            keyH.rightPressed = false;
            movePlayer(0, 1);
        }else if (keyH.upRightPressed) {
            direction = "upright";
            keyH.upRightPressed=false;
            movePlayer(-1, 1);
        }else if (keyH.upLeftPressed) {
            direction = "upleft";
            keyH.upLeftPressed=false;
            movePlayer(-1, -1);
        }else if (keyH.downRightPressed) {
            direction = "downright";
            keyH.downRightPressed=false;
            movePlayer(1, 1);
        }else if (keyH.downLeftPressed) {
            direction = "downleft";
            keyH.downLeftPressed=false;
            movePlayer(1, -1);
        }
    }

    public void movePlayer(int dx, int dy) {
        int newRow = playerNode.row + dx;
        int newCol = playerNode.col + dy;
        if (labyrinth.isWithinBounds(newRow, newCol) && labyrinth.labyrinth[newRow][newCol].value != '#') {
            playerNode = labyrinth.labyrinth[newRow][newCol];
            labyrinth.currentWord.append(String.valueOf(playerNode.value));
            playerNode.color = Color.YELLOW;
            checkForValidWords();
            gp.repaint();
        }
    }
    public void draw(Graphics2D g2){
        BufferedImage image=null;
        switch (direction){
            case "up":
                image=up1;
                break;
            case "down":
                image=down1;
                break;
            case "left":
                image=left1;
                break;
            case "right":
                image=right1;
                break;
            case "upright":
                image=right1;
                break;
            case "upleft":
                image=right1;
                break;
            case "downright":
                image=right1;
                break;
            case "downleft":
                image=right1;
                break;
        }
        int offsetX = (gp.getWidth() - (labyrinth.LABYRINTH_WIDTH * labyrinth.NODE_SIZE)) / 2;
        int offsetY = (gp.getHeight() - (labyrinth.LABYRINTH_HEIGHT * labyrinth.NODE_SIZE)) / 2;
        int playerX = offsetX + playerNode.col * labyrinth.NODE_SIZE;
        int playerY = offsetY + playerNode.row * labyrinth.NODE_SIZE;
        g2.drawImage(image,playerX,playerY,labyrinth.NODE_SIZE,labyrinth.NODE_SIZE,null);
    }

    private void colorWordInLabyrinth(String word) {
        int wordLength = word.length();
        int startRow = playerNode.row;
        int startCol = playerNode.col - wordLength + 1;

        if (startCol >= 0) {
            for (int i = 0; i < wordLength; i++) {
                labyrinth.labyrinth[startRow][startCol + i].color = Color.BLUE;
            }
        }
    }

    private void checkForValidWords() {
        String word = labyrinth.currentWord.toString();

        // Iterate through all valid words to check if they exist in the current word
        for (String validWord : labyrinth.validWords) {
            int index = word.indexOf(validWord); // Find the starting index of the valid word

            while (index != -1) {
                // Color the nodes corresponding to the valid word
                //colorNodesForWord(index, validWord.length());

                // Add score and remove the word from currentWord to avoid re-scoring
                labyrinth.score += 10;
               // colorWordInLabyrinth(word);

                // Remove the found word by marking it as processed (replace with '*')
                char[] wordChars = word.toCharArray();
                for (int i = 0; i < validWord.length(); i++) {
                    wordChars[index + i] = '*'; // Mark processed characters
                }
                labyrinth.currentWord = new StringBuilder(new String(wordChars).replace("*", ""));

                // Recheck for the same valid word in the remaining current word
                word = labyrinth.currentWord.toString();
                index = word.indexOf(validWord);
            }
        }
    }
    private void colorNodesForWord(int startIndex, int length) {
        // Start from the player's current position
        int row = playerNode.row;
        int col = playerNode.col;

        // Iterate through the word and color the corresponding nodes in the labyrinth
        for (int i = 0; i < length; i++) {
            if (labyrinth.isWithinBounds(row, col)) {
                // Color the node corresponding to this character
                labyrinth.labyrinth[row][col].color = Color.BLUE;
            }
            // Move to the next node based on how the word is laid out in the labyrinth
            col++; // This can be adjusted based on the word's direction (e.g., row++ for vertical, col++ for horizontal, etc.)
        }
    }




}
