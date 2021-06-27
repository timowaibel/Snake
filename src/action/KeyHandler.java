package action;

import Gui.Screen;
import clock.GameClock;
import game.Difficulties;
import game.Direction;
import game.Snake;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


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
            if(Main.getScreen() == Screen.Death && e.getKeyCode() == KeyEvent.VK_ESCAPE){
                Main.setScreen(Screen.Start);
                GameClock.startScreenSnake();
            }

            switch (e.getKeyCode()){
                case KeyEvent.VK_1:
                    Main.setDifficulties(Difficulties.EASY);
                    break;
                case KeyEvent.VK_2:
                    Main.setDifficulties(Difficulties.MEDIUM);
                    break;
                case KeyEvent.VK_3:
                    Main.setDifficulties(Difficulties.HARD);
                    break;
                case KeyEvent.VK_SPACE:
                    if(Main.getScreen() == Screen.Start){
                        Snake.resetSnake(true);
                        Main.setRunning(true);
                        Main.setScreen(Screen.Game);
                        //Score
                        Snake.score=0;
                        //GameClock.sounds.stopSound();
                        //GameClock.sounds.loopSound(Sounds.theme);
                    }else{
                        if(Main.getScreen() == Screen.Death){
                            GameClock.sounds.stopSound();
                            Main.setRunning(true);
                            Main.setScreen(Screen.Game);
                            Snake.score=0;
                            //GameClock.sounds.loopSound(Sounds.theme);
                        }
                    }
                    break;
            }
        }
    }


    @Override
    public void keyReleased(KeyEvent e) { }
}
