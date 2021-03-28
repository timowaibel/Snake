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

                if(Collission.collideWall()){
                    Snake.head.setX(7);
                    Snake.head.setY(7);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
