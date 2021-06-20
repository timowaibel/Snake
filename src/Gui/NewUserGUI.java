package Gui;

import action.Main;
import clock.GameClock;
import game.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class NewUserGUI{

    public static JFrame newFrame;
    JButton create;
    JButton cancel;
    JTextField name;
    JLabel error;
    ActionListener action;

    String errorLenght = "Username is too long";
    String errorNoName = "You must enter a Name";

    public NewUserGUI(){
        newFrame = new JFrame("Create new User");
        newFrame.setSize(400, 300);
        newFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        newFrame.setLocationRelativeTo(null);
        newFrame.setBackground(Color.BLACK);
        newFrame.setResizable(false);
        newFrame.setLayout(null);

        action = e -> {
            if(e.getSource() == create || e.getSource() == name){
                create();
            }else{
                if(e.getSource() == cancel){
                    cancel();
                }
            }
            //save User
            try {
                GameClock.saveUser();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        };

        //Button create
        create = new JButton();
        create.setText("Create new User");
        create.setBounds(50,200,150,40);
        create.addActionListener(action);


        create.setVisible(true);


        //Button cancel
        cancel = new JButton();
        cancel.setText("cancel");
        cancel.setBounds(300,200,100,40);
        cancel.addActionListener(action);

        cancel.setVisible(true);


        //TextField name
        name = new JTextField();
        name.setBounds(100, 100, 200,40);
        name.addActionListener(action);

        name.setVisible(true);


        //Text for error massage
        error = new JLabel();
        error.setBounds(100, 140, 200, 40);
        error.setForeground(Color.red);

        error.setVisible(true);


        newFrame.add(cancel);
        newFrame.add(create);
        newFrame.add(name);
        newFrame.add(error);

        newFrame.setVisible(true);
    }

    public void create(){
        if(name.getText().length() == 0){
            error.setText(errorNoName);
        }else{
            if(name.getText().length() >= 12){
                error.setText(errorLenght);
            }else{
                newFrame.setVisible(false);
                Main.users.add(new User(name.getText(), 0));
                name.setText("");
                error.setText("");
            }
        }
    }

    public void cancel(){
        name.setText("");
        error.setText("");
        newFrame.setVisible(false);
    }
}
