package action;

import game.Snake;

public class Collission {

    public static boolean collideWall(){
        return ( Snake.head.getX()<0 || Snake.head.getX()>15 || Snake.head.getY()<0 || Snake.head.getY()>15);
    }
    // Self und mit pickup fehlt noch
}
