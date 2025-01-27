import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tile;
    public TileManager(GamePanel gamePanel){
        this.gamePanel=gamePanel;
        tile=new Tile[20];
        getTileImage();

    }
    public void getTileImage(){
        try {
            tile[0]=new Tile();
            tile[0].image= ImageIO.read(getClass().getResourceAsStream("/assets/Tiles/preview.jpg"));
            tile[1]=new Tile();
            tile[1].image= ImageIO.read(getClass().getResourceAsStream("/assets/Tiles/Meteor_03.png"));
            tile[2]=new Tile();
            tile[2].image= ImageIO.read(getClass().getResourceAsStream("/assets/Tiles/Bones_shadow1_1.png"));
            tile[3]=new Tile();
            tile[3].image= ImageIO.read(getClass().getResourceAsStream("/assets/Tiles/Bones_shadow1_2.png"));
            //space
            tile[4]=new Tile();
            tile[4].image= ImageIO.read(getClass().getResourceAsStream("/assets/Tiles/tile002.png"));
            tile[5]=new Tile();
            tile[5].image= ImageIO.read(getClass().getResourceAsStream("/assets/Tiles/tile003.png"));
            tile[6]=new Tile();
            tile[6].image= ImageIO.read(getClass().getResourceAsStream("/assets/Tiles/tile004.png"));
            tile[7]=new Tile();
            tile[7].image= ImageIO.read(getClass().getResourceAsStream("/assets/Tiles/tile005.png"));
            tile[8]=new Tile();
            tile[8].image= ImageIO.read(getClass().getResourceAsStream("/assets/Tiles/tile006.png"));
            tile[8]=new Tile();
            tile[8].image= ImageIO.read(getClass().getResourceAsStream("/assets/Tiles/tile006.png"));
            tile[8]=new Tile();
            tile[8].image= ImageIO.read(getClass().getResourceAsStream("/assets/Tiles/tile006.png"));

            tile[9]=new Tile();
            tile[9].image= ImageIO.read(getClass().getResourceAsStream("/assets/Tiles/Bomb_3_Idle_000.png"));
            tile[10]=new Tile();
            tile[10].image= ImageIO.read(getClass().getResourceAsStream("/assets/Tiles/Crystal_02.png"));
            tile[11]=new Tile();
            tile[11].image= ImageIO.read(getClass().getResourceAsStream("/assets/Tiles/Crystal_03.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        for (int i=0; i<17 ;i++){
            for (int j=0;j<17; j++) {

                g2.drawImage(tile[0].image, gamePanel.tileSize * i, gamePanel.tileSize * j, gamePanel.tileSize, gamePanel.tileSize, null);
            }
        }

        g2.drawImage(tile[4].image,0,0,gamePanel.tileSize,gamePanel.tileSize,null);
        g2.drawImage(tile[5].image,gamePanel.tileSize,0,gamePanel.tileSize,gamePanel.tileSize,null);
        g2.drawImage(tile[6].image,gamePanel.tileSize*2,0,gamePanel.tileSize,gamePanel.tileSize,null);
        g2.drawImage(tile[7].image,gamePanel.tileSize*3,0,gamePanel.tileSize,gamePanel.tileSize,null);
        g2.drawImage(tile[8].image,gamePanel.tileSize*4,0,gamePanel.tileSize,gamePanel.tileSize,null);
        g2.drawImage(tile[1].image,gamePanel.tileSize*4,gamePanel.tileSize*2,gamePanel.tileSize,gamePanel.tileSize,null);
        g2.drawImage(tile[9].image,gamePanel.tileSize*8,gamePanel.tileSize*2,gamePanel.tileSize,gamePanel.tileSize,null);
        g2.drawImage(tile[10].image,gamePanel.tileSize*2,gamePanel.tileSize*4,gamePanel.tileSize,gamePanel.tileSize,null);
        g2.drawImage(tile[11].image,gamePanel.tileSize,gamePanel.tileSize*7,gamePanel.tileSize,gamePanel.tileSize,null);


    }
    /*public void setTile(Graphics2D g2, int x,int y){
        g2.drawImage(tile[0].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);

    }*/
}
