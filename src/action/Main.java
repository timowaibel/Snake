package action;

import Gui.Screen;
import Gui.gui;
import clock.GameClock;

public class Main {
    public static boolean running = false ;
    public static Screen screen = Screen.Start;
    public static GameClock gameClock;

    public static void main(String[] args) {
        gui g= new gui();
        gameClock = new GameClock();
        g.create();
    }

    //Getter und Setter
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
