package action;

import Gui.NewUserGUI;
import Gui.Screen;
import Gui.gui;
import clock.GameClock;
import game.Difficulties;
import game.Snake;
import game.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static clock.GameClock.startScreenSnake;

public class Main {
    public static boolean running = false ;
    public static Screen screen = Screen.Start;
    public static GameClock gameClock;
    public static Difficulties difficulties = Difficulties.EASY;
    public static ArrayList<User> users = new ArrayList<>();
    public static int maxUser = 7;

    public static void main(String[] args) throws IOException {
        gui g= new gui();
        gameClock = new GameClock();
        gameClock.setName("Gameclock");
        gameClock.start();
        g.create();
        new NewUserGUI();
        startScreenSnake();

        File file = new File("src/game/Highscore.txt");
        if(file.exists()){
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()){
                String name = scanner.next();
                int highscore = scanner.nextInt();
                User user = new User(name, highscore);
                users.add(user);
            }

            if(users.size() != 0){
                Snake.highscore = users.get(0).getHighscore();
            }

            for (int i = 0; i < users.size(); i++) {
                System.out.println(users.get(i).getName());
                System.out.println(users.get(i).getHighscore());
            }
            scanner.close();
        }
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

    public static Difficulties getDifficulties() {
        return difficulties;
    }

    public static void setDifficulties(Difficulties difficulties) {
        Main.difficulties = difficulties;
    }


}
