package action;

import Gui.Draw;
import Gui.NewUserGUI;
import Gui.Screen;
import clock.GameClock;

import javax.swing.*;
import java.awt.*;
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

        switch (Main.screen) {
            case Start:
                break;
            case Game:
                break;
            case Death:
                break;
        }

        if (Main.screen != Screen.Game) {
            // öffnen des Dropdown Menu
            if (x > startX && x < startX + width && y > startY && y < startY + height) {
                System.out.println("Hallo " + x + " " + y);
                GameClock.setFolded(!GameClock.isFolded());
            } else {
                if (!GameClock.isFolded()) {
                    //click auf User
                    if (x > startX && x < startX + width && y > startY + height && y < startY + ((Main.users.size() + 1) * height)) {
                        int index = (y - startY - height) / height;
                        //Auswahl oder bearbeiten
                        if(x > startX + width - 35 && x < startX + width){
                            //bearbeiten
                            GameClock.setSelUser(index);
                            //neues Fenster um neuen User zu bearbeiten
                            if(Frame.getFrames().length == 1){
                                NewUserGUI newUserGUI = new NewUserGUI();
                            }else{
                                if(!NewUserGUI.newFrame.isVisible()){
                                    NewUserGUI.newFrame.setVisible(true);
                                }
                            }
                            NewUserGUI.create.setText(NewUserGUI.tEdit);
                            NewUserGUI.delete.setVisible(true);
                            NewUserGUI.name.setText(Main.users.get(GameClock.getSelUser()).getName());
                            NewUserGUI.edit = true;
                            System.out.println("Grüß dich " + x + " " + y + " "+ index + " "+ Main.users.get(index).getName());
                        }else{
                            if(GameClock.getSelUser() == index){
                                GameClock.setSelUser(-1);
                            }else{
                                GameClock.setSelUser(index);
                            }
                            System.out.println("Servus " + x + " " + y + " " + index + " " + Main.users.get(index).getName());
                        }
                    } else {
                        //click auf new User
                        if (x > startX && x < startX + width && y > startY + height && y > startY + ((Main.users.size() + 1) * height) && y < startY + ((Main.users.size() + 2) * height) && Main.users.size() <= Main.maxUser) {
                            System.out.println("Moin " + x + " " + y);
                            //neues Fenster um neuen User zu erstellen
                            if(Frame.getFrames().length == 1){
                                NewUserGUI newUserGUI = new NewUserGUI();
                            }else{
                                if(!NewUserGUI.newFrame.isVisible()){
                                    NewUserGUI.cancel(false);
                                    NewUserGUI.newFrame.setVisible(true);
                                }
                            }
                        } else {
                            System.out.println("Tschüss " + x + " " + y);
                            GameClock.setFolded(true);
                        }
                    }
                } else {
                    System.out.println("Tschüss " + x + " " + y);
                }
            }
        }
    }
}
