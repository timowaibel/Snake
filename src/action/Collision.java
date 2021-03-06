package action;

import clock.GameClock;
import game.Snake;

public class Collision {

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
            if(Snake.isGolden()){
                Snake.score+=50;
            }else{
                switch (Main.getDifficulties()){
                    case EASY -> Snake.score+=10;
                    case MEDIUM -> Snake.score+=15;
                    case HARD -> Snake.score+=20;
                }
            }

            if(GameClock.getSelUser()>=0){
                if(Snake.score > Main.users.get(GameClock.getSelUser()).getHighscore()){
                    Main.users.get(GameClock.getSelUser()).setHighscore(Snake.score);
                }

                if(Snake.score > Snake.highscore){
                    Snake.highscore = Snake.score;
                }
            }

            if(Snake.pickup.getX() == Snake.head.getX() && Snake.pickup.getY() == Snake.head.getY()){
                Snake.pickup.reset();
            }
            for(int i = 0; i < Snake.tails.size(); i++){
                if(Snake.pickup.getX() == Snake.tails.get(i).getX() && Snake.pickup.getY() == Snake.tails.get(i).getY()){
                    Snake.pickup.reset();
                }
            }


            //Sound
            if(Snake.score == 420 || Snake.score == 690){
                GameClock.sounds.playSound(Sounds.nice);
            }else {
                if(Snake.isGolden()){
                    GameClock.sounds.playSound(Sounds.collectG);
                }else{
                    GameClock.sounds.playSound(Sounds.collect);
                }
            }
            Snake.collectedPickUp++;
        }
    }
}
