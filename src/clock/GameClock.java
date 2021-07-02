package clock;

import Gui.Draw;
import Gui.Hover;
import Gui.Screen;
import Gui.gui;
import action.Collision;
import action.Main;
import action.Sounds;
import game.*;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GameClock extends Thread {
    public static boolean folded = true;
    public static int selUser = -1;
    public static Hover hover = Hover.Empty;

    public static Sounds sounds;


    public void run(){
        sounds = new Sounds();
        sounds.loopSound(Sounds.theme);
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

                    Collision.collidePickUp();

                    if(Collision.collideSelf() || Collision.collideWall()){
                        sounds.playSound(Sounds.death);
                        Main.setRunning(false);
                        Main.setScreen(Screen.Death);

                        //reset Snake
                        Snake.resetSnake(true);

                        //save User
                        if(getSelUser() >= 0){
                            User i = Main.users.get(getSelUser());
                            saveUser();
                            setSelUser(Main.users.indexOf(i));
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
                            hoverListener();
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
                    hoverListener();
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
        return switch (Snake.head.getDirection()) {
            case RIGHT -> Snake.tails.get(Snake.tails.size() - 1).getX() == gui.width;
            case UP -> Snake.tails.get(Snake.tails.size() - 1).getY() == -32;
            case LEFT -> Snake.tails.get(Snake.tails.size() - 1).getX() == -32;
            case DOWN -> Snake.tails.get(Snake.tails.size() - 1).getY() == gui.height;
        };
    }

    public static void saveUser() throws IOException {
        File file = new File("src/game/Highscore.txt");
        if(file.exists()){
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            //sort Array
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

            //write user in File
            for(int i = 0; i < Main.users.size(); i++) {
                writer.write(Main.users.get(i).getName());
                writer.newLine();
                writer.write(String.valueOf(Main.users.get(i).getHighscore()));
                writer.newLine();
            }
            writer.close();

            if(Main.users.size() != 0){
                Snake.highscore = Main.users.get(0).getHighscore();
            }
        }
    }

    public void hoverListener(){
        Point point = MouseInfo.getPointerInfo().getLocation();
        Point pointF = gui.getFrameLocation();
        int x = (int) (point.getX() - pointF.getX() - 8);
        int y = (int) (point.getY() - pointF.getY() - 30);
        if(x > 240 && x < 550 && y > 10 && y < 50 && Main.getScreen() == Screen.Death){
            hover = Hover.Jebaited;

        }else{
            if(x > 320 && x < 470 && y > 10 && y < 50 && Main.getScreen() == Screen.Start){
                hover = Hover.Jebaited;
            }else{
                if(x > 0 && x < 130 && y > 530 && y < gui.height){
                    hover = Hover.Rickroll;
                }else{
                    if(y > Draw.difficulties[3] - 30 && y < Draw.difficulties[3]){
                        if(x > Draw.difficulties[0] && x < Draw.difficulties[0] + 95){
                            hover = Hover.Easy;
                        }else{
                            if(x > Draw.difficulties[1] && x < Draw.difficulties[1] + 140 && GameClock.isFolded()){
                                hover = Hover.Medium;
                            }else{
                                if(x > Draw.difficulties[2] && x < Draw.difficulties[2] + 100){
                                    hover = Hover.Hard;
                                }
                            }
                        }
                    }else{
                        hover = Hover.Empty;
                    }
                }
            }
        }
    }

    public static boolean isFolded() {
        return folded;
    }

    public static void setFolded(boolean folded) {
        GameClock.folded = folded;
    }

    public static int getSelUser() {
        return selUser;
    }

    public static void setSelUser(int selUser) {
        GameClock.selUser = selUser;
    }

    public static Hover getHover() {
        return hover;
    }

    public static void setHover(Hover hover) {
        GameClock.hover = hover;
    }
}
