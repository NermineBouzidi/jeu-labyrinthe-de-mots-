import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tile;
    public TileManager(GamePanel gamePanel){
        this.gamePanel=gamePanel;
        tile=new Tile[10];
        getTileImage();

    }
    public void getTileImage(){
        try {
            tile[0]=new Tile();
            tile[0].image= ImageIO.read(getClass().getResourceAsStream("/assets/preview.jpg"));
            tile[1]=new Tile();
            tile[1].image= ImageIO.read(getClass().getResourceAsStream("/assets/tile_0007.png"));

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

        //g2.drawImage(tile[0].image,0,0,gamePanel.tileSize,gamePanel.tileSize,null);

    }
    /*public void setTile(Graphics2D g2, int x,int y){
        g2.drawImage(tile[0].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);

    }*/
}
