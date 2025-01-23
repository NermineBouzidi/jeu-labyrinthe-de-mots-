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
        this.playerNode=labyrinth.playerNode;
        this.direction="down";
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
            left1= ImageIO.read(getClass().getResourceAsStream("/assets/Player/alienGreen.png"));
            left2= ImageIO.read(getClass().getResourceAsStream("/assets/Player/alienGreen.png"));
            right1= ImageIO.read(getClass().getResourceAsStream("/assets/Player/alienGreen.png"));
            right2= ImageIO.read(getClass().getResourceAsStream("/assets/Player/alienGreen.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void update() {
       // x = labyrinth.playerNode.col * labyrinth.NODE_SIZE;
        //y = labyrinth.playerNode.row * labyrinth.NODE_SIZE;
        if (keyH.upPressed) {
            direction="up";
            labyrinth.movePlayer(-1, 0);  // Move player up

        }  if (keyH.downPressed) {
            direction="down";
            labyrinth.movePlayer(1, 0);  // Move player down
        }  if (keyH.leftPressed) {
            direction="left";
            labyrinth.movePlayer(0, -1);  // Move player left
        }  if (keyH.rightPressed) {
            direction="right";
            labyrinth.movePlayer(0, 1);  // Move player right
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
        }
        int x = labyrinth.playerNode.col * labyrinth.NODE_SIZE;
       int y = labyrinth.playerNode.row * labyrinth.NODE_SIZE;
        g2.drawImage(image,x,y,gp.tileSize,gp.tileSize,null);
    }



}
