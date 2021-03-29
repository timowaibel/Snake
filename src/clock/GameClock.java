package clock;

import action.Collission;
import game.Snake;

public class GameClock extends Thread{
    public static boolean running = true ;
    //glaub fertig
    public void run(){
        while(running){
            try {
                sleep(200);
                Snake.move();
                Snake.waitToMove = false;

                Collission.collidePickUp();

                if(Collission.collideSelf()){
                    Snake.tails.clear();
                    //score
                }

                if(Collission.collideWall()){
                    Snake.tails.clear();
                    Snake.head.setX(7);
                    Snake.head.setY(7);
                    //score
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
