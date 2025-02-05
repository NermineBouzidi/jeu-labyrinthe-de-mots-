import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean uppress,downpress,rightpress,leftpress ;
    public boolean uprightpress,downrightpress,downleftpress,upleftpress ;
    public boolean collectpress;

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
        int code = e.getKeyCode() ;

        if (code == KeyEvent.VK_NUMPAD8) {
            this.uppress = true ;
        }
        if (code == KeyEvent.VK_NUMPAD6) {
            this.rightpress = true ;
        }
        if (code == KeyEvent.VK_NUMPAD4) {
            this.leftpress = true ;
        }
        if (code == KeyEvent.VK_NUMPAD2) {
            this.downpress = true ;
        }
        if (code == KeyEvent.VK_NUMPAD9) {
            this.uprightpress = true ;
        }
        if (code == KeyEvent.VK_NUMPAD7) {
            this.upleftpress = true ;
        }
        if (code == KeyEvent.VK_NUMPAD1) {
            this.downleftpress = true ;
        }
        if (code == KeyEvent.VK_NUMPAD3) {
            this.downrightpress = true ;
        }
        if(code == KeyEvent.VK_ENTER) {
            this.collectpress = true ;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
        int code = e.getKeyCode() ;

        if (code == KeyEvent.VK_NUMPAD8) {
            this.uppress = false ;
        }
        if (code == KeyEvent.VK_NUMPAD6) {
            this.rightpress = false ;
        }
        if (code == KeyEvent.VK_NUMPAD4) {
            this.leftpress = false ;
        }
        if (code == KeyEvent.VK_NUMPAD2) {
            this.downpress = false ;
        }
        if (code == KeyEvent.VK_NUMPAD9) {
            this.uprightpress = false ;
        }
        if (code == KeyEvent.VK_NUMPAD7) {
            this.upleftpress = false ;
        }
        if (code == KeyEvent.VK_NUMPAD1) {
            this.downleftpress = false ;
        }
        if (code == KeyEvent.VK_NUMPAD3) {
            this.downrightpress = false ;
        }
        if(code == KeyEvent.VK_ENTER) {
            this.collectpress = false ;
        }
    }

}
