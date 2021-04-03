package game;

import Gui.gui;

import java.awt.*;
import java.util.ArrayList;

public class Snake {


    public static boolean waitToMove = false;

    public static Head head = new Head(7,7);

    public static ArrayList<Tail> tails = new ArrayList<>();

    public static PickUp pickup = new PickUp();
    //Score
    public static int score=0, highscore=0;

    public static void addTail(){
        if(tails.size()<1){
            tails.add(new Tail(head.getX(), head.getY()));
        }else{
            tails.add(new Tail(tails.get(tails.size()-1).getX(),tails.get(tails.size()-1).getY()));
        }
    }

    public static void move(){
        //move Tails
        if(tails.size()>=2){
            for(int i = tails.size()-1; i>0; i--){
                if(tails.get(i).isWait()){
                    tails.get(i).setWait(false);
                }else{
                    tails.get(i).setX(tails.get(i-1).getX());
                    tails.get(i).setY(tails.get(i-1).getY());
                }
            }
        }

        //move first to Head
        if(tails.size()>=1){
            if(tails.get(0).isWait()){
                tails.get(0).setWait(false);
            }else{
                tails.get(0).setX(head.getX());
                tails.get(0).setY(head.getY());
            }
        }

        //move Head
        switch (head.getDirection()) {
            case RIGHT -> head.setX(head.getX() + 1);
            case UP -> head.setY(head.getY() - 1);
            case LEFT -> head.setX(head.getX() - 1);
            case DOWN -> head.setY(head.getY() + 1);
        }
    }

    public static void moveStart(){
        switch (head.getDirection()) {
            case RIGHT -> {
                head.setX(head.getX() + 1);
                for (int i = 0; i < Snake.tails.size(); i++) {
                    Snake.tails.get(i).setX(Snake.tails.get(i).getX() + 1);
                }
            }
            case UP -> {
                head.setY(head.getY() - 1);
                for (int i = 0; i < Snake.tails.size(); i++) {
                    Snake.tails.get(i).setY(Snake.tails.get(i).getY() - 1);
                }
            }
            case LEFT -> {
                head.setX(head.getX() - 1);
                for (int i = 0; i < Snake.tails.size(); i++) {
                    Snake.tails.get(i).setX(Snake.tails.get(i).getX() - 1);
                }
            }
            case DOWN -> {
                head.setY(head.getY() + 1);
                for (int i = 0; i < Snake.tails.size(); i++) {
                    Snake.tails.get(i).setY(Snake.tails.get(i).getY() + 1);
                }
            }
        }
    }

    public static void resetSnake(boolean game){
        if (game){
            Snake.head.setX(7);
            Snake.head.setY(7);
            Snake.head.setDirection(Direction.RIGHT);
            Snake.pickup.reset();
        }
        Snake.tails.clear();
    }

    //Position to Coordinate
    public static Point ptc(int x,int y){
        Point p = new Point(0,0);
        p.x= x * 32 + gui.xoff;
        p.y= y * 32 + gui.yoff;
        return p;
    }
}
