package Gui;

import action.Main;
import clock.GameClock;
import game.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class NewUserGUI{

    public static JFrame newFrame;
    public static JButton create;
    JButton cancel;
    public static JButton delete;
    public static JTextField name;
    public static JLabel error;
    ActionListener action;
    KeyListener key;

    String errorLength = "Username is too long";
    String errorNoName = "You must enter a Name";
    String errorName = "Your name is not available";
    public static String tCreate = "Create new User";
    public static String tEdit = "Edit User";
    public static boolean edit = false;

    public NewUserGUI(){
        newFrame = new JFrame("Create new User");
        newFrame.setSize(400, 300);
        newFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        newFrame.setLocationRelativeTo(null);
        newFrame.setBackground(Color.BLACK);
        newFrame.setResizable(false);
        newFrame.setLayout(null);

        File imgURL = new File("src/res/images/iconNewUser.png");
        ImageIcon icon = new ImageIcon(String.valueOf(imgURL));
        Image image = icon.getImage();
        newFrame.setIconImage(image);

        action = e -> {
            if(e.getSource() == create || e.getSource() == name){
                create();
            }else{
                if(e.getSource() == cancel){
                    cancel();
                }else{
                    if(e.getSource() == delete){
                        delete();
                    }
                }
            }
            //save User
            try {
                GameClock.saveUser();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        };

        key = new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    cancel();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };

        //Button create
        create = new JButton();
        create.setText("Create new User");
        create.setBounds(50,200,150,40);
        create.addActionListener(action);
        create.addKeyListener(key);

        create.setVisible(true);


        //Button cancel
        cancel = new JButton();
        cancel.setText("Cancel");
        cancel.setBounds(200,200,150,40);
        cancel.addActionListener(action);
        cancel.addKeyListener(key);

        cancel.setVisible(true);


        //Button delete
        delete = new JButton();
        delete.setText("Delete User");
        delete.setBounds(100,150,200,40);
        delete.addActionListener(action);
        delete.addKeyListener(key);

        delete.setVisible(false);


        //TextField name
        name = new JTextField();
        name.setBounds(100, 100, 200,40);
        name.addActionListener(action);
        name.addKeyListener(key);

        name.setVisible(true);


        //Text for error massage
        error = new JLabel();
        error.setBounds(100, 140, 200, 40);
        error.setForeground(Color.red);

        error.setVisible(true);


        newFrame.add(cancel);
        newFrame.add(create);
        newFrame.add(delete);
        newFrame.add(name);
        newFrame.add(error);

        newFrame.setVisible(false);
    }

    public void create(){
        if(name.getText().length() == 0){
            error.setText(errorNoName);
        }else{
            if(name.getText().length() >= 12){
                error.setText(errorLength);
            }else{
                if(nameUnique()){
                    error.setText(errorName);
                }else{
                    if(edit){
                        Main.users.set(GameClock.getSelUser(), new User(name.getText(), Main.users.get(GameClock.getSelUser()).getHighscore()));
                    }else{
                        Main.users.add(new User(name.getText(), 0));
                    }
                    cancel();
                }
            }
        }
    }

    public void delete(){
        Main.users.remove(GameClock.getSelUser());
        GameClock.setSelUser(-1);
        cancel();
    }

    public static void cancel(){
        name.setText("");
        error.setText("");
        create.setText(tCreate);
        edit = false;
        newFrame.setVisible(false);
        delete.setVisible(false);
    }

    public boolean nameUnique(){
        for(int i = 0; i < Main.users.size(); i++){
            if(name.getText().equals(Main.users.get(i).getName())){
                return true;
            }
        }
        return false;
    }
}
