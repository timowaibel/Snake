package Gui;

import game.Snake;

import javax.swing.*;
import java.awt.*;

public class Draw extends JLabel {

    Point p;

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d= (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_OFF);

        //draw background
        g.setColor(Color.BLACK);
        g.fillRect(0,0,gui.width,gui.height);

        //Draw Snake Tails
        //Hululul
        //Draw Snake Head
        g.setColor(new Color(0,153,0));
        p = Snake.ptc(Snake.head.getX(), Snake.head.getY());
        g.fillRect(p.x,p.y,32,32);

        // Draw PickUp
        //draw Grid
        g.setColor(Color.lightGray);
        for(int x=0; x<16;x++){
            for(int y=0; y<16;y++){
                g.drawRect(x*32+gui.xoff,y*32+gui.yoff,32,32);
            }
        }

        //Draw Border
        g.setColor(Color.lightGray);
        g.drawRect(gui.xoff,gui.yoff,512,512);

        // Draw Score
        g.setColor(new Color(51,204,51));
        g.setFont(new Font("Arial",Font.BOLD,20));
        g.drawString("Score", 5,25);
        g.drawString("Best",655,25);

        repaint();
    }
}
