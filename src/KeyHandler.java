import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, rightPressed, leftPressed;
   // private Labyrinth labyrinth;  // Reference to the Labyrinth class
   /* public KeyHandler(Labyrinth labyrinth) {
        this.labyrinth = labyrinth;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_UP:
                labyrinth.movePlayer(-1, 0);  // Move player up
                break;
            case KeyEvent.VK_DOWN:
                labyrinth.movePlayer(1, 0);  // Move player down
                break;
            case KeyEvent.VK_LEFT:
                labyrinth.movePlayer(0, -1);  // Move player left
                break;
            case KeyEvent.VK_RIGHT:
                labyrinth.movePlayer(0, 1);  // Move player right
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}*/

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_NUMPAD8:
                upPressed = true;
                break;
            case KeyEvent.VK_NUMPAD9:
                upPressed = true;
                rightPressed = true;
                break;
            case KeyEvent.VK_NUMPAD7:
                upPressed = true;
                leftPressed = true;
                break;
            case KeyEvent.VK_NUMPAD2:
                downPressed = true;
                break;
            case KeyEvent.VK_NUMPAD3:
                downPressed = true;
                rightPressed = true;
                break;
            case KeyEvent.VK_NUMPAD1:
                downPressed = true;
                leftPressed = true;
                break;
            case KeyEvent.VK_NUMPAD6:
                rightPressed = true;
                break;
            case KeyEvent.VK_NUMPAD4:
                leftPressed = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_NUMPAD8:
                upPressed = false;
                break;
            case KeyEvent.VK_NUMPAD9:
                upPressed = false;
                rightPressed = false;
                break;
            case KeyEvent.VK_NUMPAD7:
                upPressed = false;
                leftPressed = false;
                break;
            case KeyEvent.VK_NUMPAD2:
                downPressed = false;
                break;
            case KeyEvent.VK_NUMPAD3:
                downPressed = false;
                rightPressed = false;
                break;
            case KeyEvent.VK_NUMPAD1:
                downPressed = false;
                leftPressed = false;
                break;
            case KeyEvent.VK_NUMPAD6:
                rightPressed = false;
                break;
            case KeyEvent.VK_NUMPAD4:
                leftPressed = false;
                break;
        }
    }
}