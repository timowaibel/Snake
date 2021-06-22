package Gui;

import action.KeyHandler;
import action.Main;
import action.MouseListener;

import javax.swing.*;
import java.awt.*;

public class gui {
    JFrame jf;
    Draw d;

    public static int width=810,height=600;
    public static int xoff=130,yoff=20;

    public void create(){
        jf= new JFrame("Sanke");
        jf.setSize(width,height);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.setLayout(null);
        jf.setResizable(false);
        jf.addKeyListener(new KeyHandler( ));
        jf.addMouseListener(new MouseListener());

        java.net.URL imgURL = getClass().getResource("icon.png");
        ImageIcon icon = new ImageIcon(imgURL);
        Image image = icon.getImage();
        jf.setIconImage(image);

        d= new Draw();
        d.setBounds(0,0,width,height);
        d.setVisible(true);
        jf.add(d);

        jf.requestFocus();
        jf.setVisible(true);
    }
}
