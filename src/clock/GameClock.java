package clock;

import Gui.Draw;
import Gui.Screen;
import action.Collission;
import action.Main;
import game.Direction;
import game.PickUp;
import game.Snake;

public class GameClock extends Thread{
    public void run(){
        while(true){
            if(Main.isRunning()){
                try {
                    sleep(200);
                    Snake.move();
                    Snake.waitToMove = false;

                    Collission.collidePickUp();

                    if(Collission.collideSelf()||Collission.collideWall()){
                        Main.setRunning(false);
                        Main.setScreen(Screen.Death);
                        //Snake zurücksetzen
                        Snake.tails.clear();
                        Snake.head.setX(7);
                        Snake.head.setY(7);
                        Snake.head.setDirection(Direction.RIGHT);
                        Snake.pickup.reset();
                        //score
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
