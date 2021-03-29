package clock;

import Gui.Draw;
import action.Collission;
import action.Main;
import game.Snake;

public class GameClock extends Thread{

    //glaub fertig
    public void run(){
        while(Main.isRunning()){
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
                System.out.println("Moin");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
