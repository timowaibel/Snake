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
                    Snake.move();
                    try {
                        if(count%3 == 0 && count <= 160 && count!=0){
                            startscreenAddTail();
                        }
                        sleep(10);
                        count++;
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
            case 0 -> {
                Snake.head.setDirection(Direction.RIGHT);
                start = (int) Math.abs(Math.round(Math.random() * gui.height - 32));
                Snake.head.setX(-32);
                Snake.head.setY(start);
            }
            case 1 -> {
                Snake.head.setDirection(Direction.UP);
                start = (int) Math.abs(Math.round(Math.random() * gui.width - 32));
                Snake.head.setX(start);
                Snake.head.setY(gui.height);
            }
            case 2 -> {
                Snake.head.setDirection(Direction.LEFT);
                start = (int) Math.abs(Math.round(Math.random() * gui.height - 32));
                Snake.head.setX(gui.width);
                Snake.head.setY(start);
            }
            case 3 -> {
                Snake.head.setDirection(Direction.DOWN);
                start = (int) Math.abs(Math.round(Math.random() * gui.width - 32));
                Snake.head.setX(start);
                Snake.head.setY(-32);
            }
        }
    }

    public void startscreenAddTail(){
        switch (Snake.head.getDirection()) {
            case RIGHT -> {
                Snake.tails.add(new Tail(-32, Snake.head.getY()));
                System.out.println("Hallo");
            }
            case UP -> Snake.tails.add(new Tail(Snake.head.getX(), gui.width));
            case LEFT -> Snake.tails.add(new Tail(gui.height, Snake.head.getY()));
            case DOWN -> Snake.tails.add(new Tail(Snake.head.getX(), -32));
        }
    }
}
