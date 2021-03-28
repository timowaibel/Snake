package action;

import Gui.gui;
import clock.GameClock;

public class Main {

    public static void main(String[] args) {
        gui g= new gui();
        GameClock gameClock = new GameClock();
        // muss glaub nich rein zum prüfen auf kollision
        g.create();
        gameClock.start();
    }
}
