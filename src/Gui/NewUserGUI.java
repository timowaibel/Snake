package Gui;

import action.Main;
import game.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class NewUserGUI{

    public static JFrame newFrame;
    JButton create;
    JButton cancel;
    JTextField name;
    JPanel panel;
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

        action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == create){
                    create();

                }else{
                    if(e.getSource() == cancel){
                        name.setText("");
                        error.setText("");
                        newFrame.setVisible(false);
                    }else{
                        if(e.getSource() == name){
                            create();
                        }
                    }
                }
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


        panel = new JPanel();
        panel.setLayout(null);

        panel.add(cancel);
        panel.add(create);
        panel.add(name);
        panel.add(error);

        newFrame.add(panel);


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
}
