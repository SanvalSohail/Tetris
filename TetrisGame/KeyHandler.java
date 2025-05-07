package TetrisGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public static boolean upPressed, downPressed, leftPressed, rightPressed, pausePressed;

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_W ^ keyCode == KeyEvent.VK_UP){
            upPressed  = true;
        }
        if(keyCode == KeyEvent.VK_S ^ keyCode == KeyEvent.VK_DOWN){
            downPressed  = true;
        }
        if(keyCode == KeyEvent.VK_A ^ keyCode == KeyEvent.VK_LEFT){
            leftPressed  = true;
        }
        if(keyCode == KeyEvent.VK_D ^ keyCode == KeyEvent.VK_RIGHT){
            rightPressed  = true;
        }
        if (keyCode == KeyEvent.VK_SPACE){
            if(pausePressed){
                pausePressed = false;
            }
            else pausePressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }
    
}
