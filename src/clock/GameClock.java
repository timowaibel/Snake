package clock;

import Gui.Screen;
import Gui.gui;
import action.Collission;
import action.KeyHandler;
import action.Main;
import game.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GameClock extends Thread{
    public static boolean easy=false;
    public static boolean medium=false;
    public static boolean hard=false;

    public void run(){
        while(true){
            if(Main.isRunning()){
                try {
                    if(Main.getDifficulties()==Difficulties.EASY){
                        sleep(200);
                    }
                    if(Main.getDifficulties()==Difficulties.MEDIUM){
                        sleep(170);
                    }
                    if(Main.getDifficulties()==Difficulties.HARD){
                        sleep(140);
                    }




                    Snake.move();
                    Snake.waitToMove = false;

                    Collission.collidePickUp();

                    if(Collission.collideSelf()||Collission.collideWall()){
                        Main.setRunning(false);
                        Main.setScreen(Screen.Death);

                        //Snake zurücksetzen
                        Snake.resetSnake(true);

                        //Highscore in File übertagen
                        File file = new File("src/game/Highscore.txt");
                        if(file.exists()){
                            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

                            //Array sortieren
                            boolean sorted = false;
                            while (!sorted){
                                sorted = true;
                                for (int i = 0; i < Main.users.size()-1; i++) {
                                    if(Main.users.get(i).getHighscore() < Main.users.get(i+1).getHighscore()){
                                        User user = Main.users.get(i);
                                        Main.users.set(i, Main.users.get(i+1));
                                        Main.users.set(i+1, user);
                                        sorted = false;
                                    }
                                }
                            }

                            //user in File übertragen
                            for(int i = 0; i < Main.users.size(); i++) {
                                writer.write(Main.users.get(i).getName());
                                writer.newLine();
                                writer.write(String.valueOf(Main.users.get(i).getHighscore()));
                                writer.newLine();
                            }
                            writer.close();

                            Snake.highscore = Main.users.get(0).getHighscore();
                        }
                    }
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }else{
                if(Main.screen == Screen.Start){
                    Snake.moveStart();
                    try {
                        sleep(10);
                        if(Snake.tails.size() != 0){
                            if(SnakeDisappeared()){
                                Snake.resetSnake(false);
                                startScreenSnake();
                            }
                        }
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
                for (int i = 1; i < 9; i++) {
                    Snake.tails.add(new Tail(start, gui.height-1+i*32));
                }
            }
            case 2 -> {
                Snake.head.setDirection(Direction.LEFT);
                start = (int) Math.abs(Math.round(Math.random() * gui.height - 32));
                Snake.head.setX(gui.width);
                Snake.head.setY(start);
                for (int i = 1; i < 9; i++) {
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

    public boolean SnakeDisappeared(){
        switch (Snake.head.getDirection()){
            case RIGHT:
                if(Snake.tails.get(Snake.tails.size()-1).getX() == gui.width){
                    return true;
                }else {
                    return false;
                }
            case UP:
                if(Snake.tails.get(Snake.tails.size()-1).getY() == -32){
                    return true;
                }else {
                    return false;
                }
            case LEFT:
                if(Snake.tails.get(Snake.tails.size()-1).getX() == -32){
                    return true;
                }else {
                    return false;
                }
            case DOWN:
                if(Snake.tails.get(Snake.tails.size()-1).getY() == gui.height){
                    return true;
                }else {
                    return false;
                }
        }
        return false;
    }
}
