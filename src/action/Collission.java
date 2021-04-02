package action;

import game.Snake;

public class Collission {

    public static boolean collideWall(){
        return ( Snake.head.getX()<0 || Snake.head.getX()>15 || Snake.head.getY()<0 || Snake.head.getY()>15);
    }

    public static boolean collideSelf(){
        for(int i = 0; i < Snake.tails.size(); i++){
            if(Snake.head.getX() == Snake.tails.get(i).getX() && Snake.head.getY() == Snake.tails.get(i).getY() && !Snake.tails.get(i).isWait()){
                return true;
            }
        }
        return false;
    }

    public static void collidePickUp(){
        if(Snake.pickup.getX() == Snake.head.getX() && Snake.pickup.getY() == Snake.head.getY()) {
            Snake.pickup.reset();
            Snake.addTail();
            //Score
            Snake.score+=10;
            if(Snake.score>Snake.highscore){
                Snake.highscore=Snake.score;
            }

            if(Snake.pickup.getX() == Snake.head.getX() && Snake.pickup.getY() == Snake.head.getY()){
                Snake.pickup.reset();
            }
            for(int i = 0; i < Snake.tails.size(); i++){
                if(Snake.pickup.getX() == Snake.tails.get(i).getX() && Snake.pickup.getY() == Snake.tails.get(i).getY()){
                    Snake.pickup.reset();
                }
            }
        }
    }
}
