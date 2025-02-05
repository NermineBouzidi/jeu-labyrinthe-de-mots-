import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    final static int originalTileSize=16;
    final static int scale=3;
    public static final int tileSize= originalTileSize*scale;
    final static int maxScreenWidth=20;
    public static final int maxScreenHeight=14;
    public static final int screenWidth= tileSize*maxScreenWidth;
    public  static final int screenHeight= tileSize*maxScreenHeight;
    Thread gameThread;
    private GameState currentState = GameState.LOADING;
    int FPS=60;
    ScreenUI screenUI=new ScreenUI(this);
    Sound sound = new Sound();
    Playground grid;
    Player player;
    KeyHandler keys = new KeyHandler() ;


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        // Gradient colors
        Color color1 = new Color(11, 19, 43);  // #0B132B
        Color color5 = new Color(11, 19, 43);  // #0B132B
        Color color4 = new Color(91, 192, 190); // #5BC0BE
        GradientPaint gradient = new GradientPaint(0, 0, color1, 0, screenHeight, color5);

        this.setBackground(Color.darkGray);
        this.setDoubleBuffered(true);
        this.addKeyListener(keys);
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
        System.out.println(this.currentState);

        grid = new Playground() ;
        grid.generategrid();
        player = new Player(grid) ;

        while (gameThread!=null){
            currentTime=System.nanoTime();
            delta+=(currentTime -lastTime)/drawInerval;
            timer+=(currentTime-lastTime);
            lastTime=currentTime;
            //  System.out.println(this.currentState);

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
        switch (currentState) {
            case LOADING:
                break;
            case START:
                // Handle updates for the start screen
               // screenUI.updateTitleScreen();
                break;
            case PLAYER_SELECTION:
                // Handle updates for the player selection screen
                //screenUI.updateTitleScreen();

                break;
            case DIFFICULTY_SELECTION:
                // Handle updates for difficulty selection
              //  screenUI.updateTitleScreen();

                break;
            case GAME:
             //   labyrinth.player.update();
                player.updateplayer(this.keys, Playground.nodes);


                break;
            case SCORE:
                // Handle score screen updates
                break;
            case PLAY:
              //  screenUI.updateTitleScreen();
                break;
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D) g;
       /* g2.drawImage(background, 0, 0, screenWidth, screenHeight, null);

        g2.setColor(Color.red);
        g2.fillRect(100, 100, tileSize, tileSize);
        tileM.draw(g2);
        player.draw(g2);*/

        // player.drawTitleScreen(g2);
        switch (currentState) {
            case LOADING:
                screenUI.drawLoadingScreen(g2);

                break;
            case START:
                //// drawStartScreen(g2);
                screenUI.drawTitleScreen(g2);
               /* if (!startSoundPl ayed) {
                    playSE(4);
                    startSoundPlayed = true; // Mark the sound as played
                }*/
                break;
            case PLAYER_SELECTION:
                // drawPlayerSelectionScreen(g2);
                screenUI.drawPlayerSelection(g2);

                break;
            case DIFFICULTY_SELECTION:
                screenUI.drawDifficultySelection(g2);
                break;
            case GAME:
               // tileM.draw(g2);
               // labyrinth.drawLabyrinth(g2);
                //tileM.draw(g2);
                // player.draw(g2);
                 screenUI.drawGame(g2, this.grid, this.player);


                break;
            case SCORE:
                //drawScoreScreen(g2);
                screenUI.drawScore(g2);
                break;
            case PLAY:
                screenUI.drawPlay(g2);
                //screenUI.drawPlay(g2);

                break;
        }

        g2.dispose();
    }
    public void setCurrentState(GameState gameState) {
        this.currentState=gameState;
    }
    public void playMusic(int i ){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    public void stopMusic(int i ){
        sound.stop();
    }
    public void playSE(int i ){
        sound.setFile(i);
        sound.play();

    }

}
