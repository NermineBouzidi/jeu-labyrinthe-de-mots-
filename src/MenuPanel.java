import javax.swing.*;
import java.awt.*;

/*public class MenuPanel extends JPanel implements Runnable{
    final int originalTileSize=16;
    final int scale=3;
    public final int tileSize= originalTileSize*scale;
    final int maxScreenWidth=16;
    final int maxScreenHeight=12;
    final int screenWidth= tileSize*maxScreenWidth;
    final int screenHeight= tileSize*maxScreenHeight;
    KeyHandler keyH= new KeyHandler();
    Thread gameThread;

    int FPS=60;
    public MenuPanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.blue);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread=new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInerval=1000000000/FPS;
        double delta=0;
        long lastTime=System.nanoTime();
        long currentTime;
        long timer=0;
        int drawCont=0;
        while (gameThread!=null){
            currentTime=System.nanoTime();
            delta+=(currentTime -lastTime)/drawInerval;
            timer+=(currentTime-lastTime);
            lastTime=currentTime;
            if(delta>1){
                update();
                repaint();
                delta--;
                drawCont++;
            }
            if (timer>=1000000000){
                drawCont=0;
                timer=0;
            }

        }
    }

    public void update(){
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

    }
}*/
