package action;

import Gui.Draw;
import Gui.NewUserGUI;
import Gui.Screen;
import clock.GameClock;
import game.Difficulties;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseAdapter {


    int width = Draw.dropdown[0];
    int height = Draw.dropdown[1];
    int startX = Draw.dropdown[2];
    int startY = Draw.dropdown[3];

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX() - 8;
        int y = e.getY() - 30;

        if (Main.screen != Screen.Game) {
            dropdownMouseListener(x, y);
            difficultiesMouseListener(x, y);
            easterEggMouseListener(x, y);
        }
    }

    public void dropdownMouseListener(int x, int y){
        // open Dropdown menu
        if (x > startX && x < startX + width && y > startY && y < startY + height) {
            System.out.println("Fold/Open " + x + " " + y);
            GameClock.setFolded(!GameClock.isFolded());
        } else {
            if (!GameClock.isFolded()) {
                //User clicked
                if (x > startX && x < startX + width && y > startY + height && y < startY + ((Main.users.size() + 1) * height)) {
                    int index = (y - startY - height) / height;
                    //select or edit?
                    if(x > startX + width - 35 && x < startX + width){
                        //edit
                        GameClock.setSelUser(index);
                        //open Window for User edit
                        if(!NewUserGUI.newFrame.isVisible()){
                            NewUserGUI.newFrame.setVisible(true);
                        }
                        //open Window to Edit/Delete User
                        NewUserGUI.cancel();
                        NewUserGUI.newFrame.setVisible(true);
                        NewUserGUI.create.setText(NewUserGUI.tEdit);
                        NewUserGUI.delete.setVisible(true);
                        NewUserGUI.name.setText(Main.users.get(GameClock.getSelUser()).getName());
                        NewUserGUI.edit = true;
                        System.out.println("Edit/Delete " + x + " " + y + " "+ index + " "+ Main.users.get(index).getName());
                    }else{
                        if(GameClock.getSelUser() == index){
                            GameClock.setSelUser(-1);
                        }else{
                            GameClock.setSelUser(index);
                        }
                        System.out.println("Select " + x + " " + y + " " + index + " " + Main.users.get(index).getName());
                    }
                } else {
                    //new User clicked
                    if (x > startX && x < startX + width && y > startY + height && y > startY + ((Main.users.size() + 1) * height) && y < startY + ((Main.users.size() + 2) * height) && Main.users.size() <= Main.maxUser) {
                        System.out.println("Create " + x + " " + y);
                        //open Window to create new User
                        if (!NewUserGUI.newFrame.isVisible() || NewUserGUI.edit) {
                            NewUserGUI.cancel();
                            NewUserGUI.newFrame.setVisible(true);
                        }
                    } else {
                        System.out.println("Bye " + x + " " + y);
                        if (NewUserGUI.newFrame.isVisible()) {
                            NewUserGUI.newFrame.setVisible(false);
                        } else {
                            GameClock.setFolded(true);
                        }

                    }
                }
            } else {
                System.out.println("Bye " + x + " " + y);
            }
        }
    }

    public void difficultiesMouseListener(int x, int y){
        if(y > Draw.difficulties[3] - 30 && y < Draw.difficulties[3]){
            if(x > Draw.difficulties[0] && x < Draw.difficulties[0] + 95){
                System.out.println("Easy");
                Main.setDifficulties(Difficulties.EASY);
            }else{
                if(x > Draw.difficulties[1] && x < Draw.difficulties[1] + 140 && GameClock.isFolded()){
                    System.out.println("Medium");
                    Main.setDifficulties(Difficulties.MEDIUM);
                }else{
                    if(x > Draw.difficulties[2] && x < Draw.difficulties[2] + 100){
                        System.out.println("Hard");
                        Main.setDifficulties(Difficulties.HARD);
                    }
                }
            }
        }
    }

    public void easterEggMouseListener(int x, int y) {
        if(x < 130 && y > 530){
            try {
                String URL="https://www.youtube.com/watch?v=dQw4w9WgXcQ";
                java.awt.Desktop.getDesktop().browse(java.net.URI.create(URL));
            }
            catch (Exception ignored){ }
            System.out.println("Never gonna give you up");
        }else{
            if(x > 240 && x < 550 && y > 10 && y < 50 && Main.screen == Screen.Death){
                try {
                    String URL="https://www.youtube.com/watch?v=d1YBv2mWll0";
                    java.awt.Desktop.getDesktop().browse(java.net.URI.create(URL));
                }
                catch (Exception ignored){ }
                System.out.println("You got Jebaited");
            }else{
                if(x > 320 && x < 470 && y > 10 && y < 50 && Main.screen == Screen.Start){
                    try {
                        String URL="https://www.youtube.com/watch?v=d1YBv2mWll0";
                        java.awt.Desktop.getDesktop().browse(java.net.URI.create(URL));
                    }
                    catch (Exception ignored){ }
                    System.out.println("You got Jebaited");
                }
            }
        }
    }
}
