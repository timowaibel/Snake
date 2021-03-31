package action;

import Gui.Screen;
import clock.GameClock;
import game.Direction;
import game.Snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ScheduledExecutorService;

public class KeyHandler implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(Main.getScreen() == Screen.Game){
            switch (e.getKeyCode()){
                case KeyEvent.VK_W:
                case KeyEvent.VK_UP:
                    if(!(Snake.head.getDirection()==Direction.DOWN) && !Snake.waitToMove){
                        Snake.head.setDirection(Direction.UP);
                        Snake.waitToMove=true;
                    }
                    break;
                case KeyEvent.VK_A:
                case KeyEvent.VK_LEFT:
                    if(!(Snake.head.getDirection()==Direction.RIGHT) && !Snake.waitToMove){
                        Snake.head.setDirection(Direction.LEFT);
                        Snake.waitToMove=true;
                    }
                    break;
                case KeyEvent.VK_S:
                case KeyEvent.VK_DOWN:
                    if(!(Snake.head.getDirection()==Direction.UP) && !Snake.waitToMove){
                        Snake.head.setDirection(Direction.DOWN);
                        Snake.waitToMove=true;
                    }
                    break;
                case KeyEvent.VK_D:
                case KeyEvent.VK_RIGHT:
                    if(!(Snake.head.getDirection()==Direction.LEFT) && !Snake.waitToMove){
                        Snake.head.setDirection(Direction.RIGHT);
                        Snake.waitToMove=true;
                    }
                    break;
            }
        }else {
            switch (e.getKeyCode()){
                case KeyEvent.VK_SPACE:
                        if(Main.getScreen() == Screen.Start){
                        Snake.resetSnake(true);
                        Main.setRunning(true);
                        Main.setScreen(Screen.Game);
                    }else{
                        if(Main.getScreen() == Screen.Death){
                            Main.setRunning(true);
                            Main.setScreen(Screen.Game);
                    }
                }
                break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }
}
