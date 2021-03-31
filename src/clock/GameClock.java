package clock;

import Gui.Draw;
import Gui.Screen;
import Gui.gui;
import action.Collission;
import action.Main;
import game.Direction;
import game.PickUp;
import game.Snake;
import game.Tail;

import java.awt.*;

public class GameClock extends Thread{

    int count = 0;

    public void run(){
        while(true){
            if(Main.isRunning()){
                try {
                    sleep(200);
                    Snake.move();
                    Snake.waitToMove = false;

                    Collission.collidePickUp();

                    if(Collission.collideSelf()||Collission.collideWall()){
                        Main.setRunning(false);
                        Main.setScreen(Screen.Death);
                        //Snake zurücksetzen
                        Snake.tails.clear();
                        Snake.head.setX(7);
                        Snake.head.setY(7);
                        Snake.head.setDirection(Direction.RIGHT);
                        Snake.pickup.reset();
                        //score
                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                if(Main.screen == Screen.Start){
                    Snake.moveStart();
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    try {
                        sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void startScreenSnake(){
        int dir = (int) Math.round(Math.random()*4);
        int start;
        switch (dir) {
            case 1 -> {
                Snake.head.setDirection(Direction.UP);
                start = (int) Math.abs(Math.round(Math.random() * gui.width - 32));
                Snake.head.setX(start);
                Snake.head.setY(gui.height);
                for (int i = 1; i < 8; i++) {
                    Snake.tails.add(new Tail(start, gui.height-1+i*32));
                }
            }
            case 2 -> {
                Snake.head.setDirection(Direction.LEFT);
                start = (int) Math.abs(Math.round(Math.random() * gui.height - 32));
                Snake.head.setX(gui.width);
                Snake.head.setY(start);
                for (int i = 1; i < 8; i++) {
                    Snake.tails.add(new Tail(gui.width-1+i*32, start));
                }
            }

            case 3 -> {
                Snake.head.setDirection(Direction.DOWN);
                start = (int) Math.abs(Math.round(Math.random() * gui.width - 32));
                Snake.head.setX(start);
                Snake.head.setY(-32);
                for (int i = -2; i > -10; i--) {
                    Snake.tails.add(new Tail(start, i*32+1));
                }
            }

            default -> {
                Snake.head.setDirection(Direction.RIGHT);
                start = (int) Math.abs(Math.round(Math.random() * gui.height - 32));
                Snake.head.setX(-32);
                Snake.head.setY(start);
                for (int i = -2; i > -10; i--) {
                    Snake.tails.add(new Tail(i*32+1, start));
                }
            }
        }
    }
}
