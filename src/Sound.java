import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.IOException;
import java.net.URL;

public class Sound {
    Clip clip;
    URL soundURL[]= new URL[30];
    public Sound(){
        soundURL[0]=getClass().getResource("/assets/sounds/Apply.wav");
        soundURL[1]=getClass().getResource("/assets/sounds/gamestart.wav");
        soundURL[2]=getClass().getResource("/assets/sounds/Cursed.wav");
        soundURL[3]=getClass().getResource("/assets/sounds/Apply.wav");
        soundURL[4]=getClass().getResource("/assets/sounds/Apply.wav");

        soundURL[5]=getClass().getResource("/assets/sounds/menu-button-88360.mp3");

        //score
        soundURL[6]=getClass().getResource("/assets/sounds/mixkit-completion-of-a-level-2063.wav");

        //player moving
        soundURL[7]=getClass().getResource("/assets/sounds/mixkit-player-jumping-in-a-video-game-2043.wav");

        //button sound
        soundURL[8]=getClass().getResource("/assets/sounds/mixkit-video-game-retro-click-237.wav");

        //creating a wooord
        soundURL[9]=getClass().getResource("/assets/sounds/mixkit-video-game-treasure-2066.wav");

        soundURL[10]=getClass().getResource("/assets/sounds/mixkit-winning-a-coin-video-game-2069.wav");



    }

    public void setFile(int i){
        try {
            AudioInputStream ais= AudioSystem.getAudioInputStream(soundURL[i]);
            clip=AudioSystem.getClip();
            clip.open(ais);
        }catch (Exception e){

        }
    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }
    public void stop(){
        clip.stop();
    }

}
