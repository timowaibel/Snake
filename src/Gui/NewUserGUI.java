package Gui;

import action.Main;
import game.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewUserGUI{

    public static JFrame newFrame;
    JButton create;
    JButton cancel;
    JTextField name;
    JPanel panel;

    public NewUserGUI(){
        newFrame = new JFrame("Create new User");
        newFrame.setSize(400, 300);
        newFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        newFrame.setLocationRelativeTo(null);
        newFrame.setBackground(Color.BLACK);


        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == create){
                    if(name.getText().length() == 0){
                        //Nichts eingetragen
                    }else{
                        if(name.getText().length() >= 15){
                            //Zu Lang
                        }else{
                            newFrame.setVisible(false);
                            Main.users.add(new User(name.getText(), 0));
                            name.setText("");
                        }
                    }

                }else{
                    if(e.getSource() == cancel){
                        name.setText("");
                        newFrame.setVisible(false);
                    }
                }
            }
        };


        create = new JButton();
        create.setText("Create new User");
        create.setBounds(50,200,150,40);
        create.addActionListener(action);

        create.setVisible(true);




        cancel = new JButton();
        cancel.setText("cancel");
        cancel.setBounds(300,200,100,40);
        cancel.addActionListener(action);

        cancel.setVisible(true);


        name = new JTextField();
        name.setBounds(100, 100, 200,40);

        name.setVisible(true);


        panel = new JPanel();

        panel.setLayout(null);





        panel.add(cancel);
        panel.add(create);
        panel.add(name);

        newFrame.add(panel);


        newFrame.setVisible(true);
    }
}
