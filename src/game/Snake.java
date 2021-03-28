package game;

import Gui.gui;

import java.awt.*;
import java.util.ArrayList;

public class Snake {


    public static boolean waitToMove = false;

    public static Head head = new Head(7,7);



    public static void move(){
        //move Tails
        //move first to Head
        //move Head
        switch (head.getDirection()){
            case RIGHT:
                head.setX(head.getX()+1);
                break;
            case UP:
                head.setY(head.getY()-1);
                break;
            case LEFT:
                head.setX(head.getX()-1);
                break;
            case DOWN:
                head.setY(head.getY() +1);
                break;
        }
    }

    //Position to Coordinate
    public static Point ptc(int x,int y){
        Point p = new Point(0,0);
        p.x= x * 32 + gui.xoff;
        p.y= y * 32 + gui.yoff;
        return p;
    }
}
