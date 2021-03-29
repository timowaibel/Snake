package action;

import Gui.Screen;
import Gui.gui;
import clock.GameClock;

public class Main {
    public static boolean running = true ;

    public static Screen screen = Screen.Start;

    public static void main(String[] args) {
        gui g= new gui();
        GameClock gameClock = new GameClock();

        g.create();

        //keine ahnung wieso das Funktioniert
        if(isRunning()){
            gameClock.start();
        }
    }

    public static Screen getScreen() {
        return screen;
    }

    public static void setScreen(Screen screen) {
        Main.screen = screen;
    }

    public static boolean isRunning() {
        return running;
    }

    public static void setRunning(boolean running) {
        Main.running = running;
    }
}
