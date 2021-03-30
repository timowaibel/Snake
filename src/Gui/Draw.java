package Gui;

import action.Main;
import game.Snake;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Draw extends JLabel {

    Point p;
    BufferedImage snake;
    Font heading = new Font("Arial", Font.BOLD, 50);
    Font text = new Font("Arial", Font.BOLD, 35);

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d= (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_OFF);

        switch (Main.screen){
            case Start: drawStart(g);
                break;
            case Game: drawGame(g);
                break;
            case Death: drawDeath(g);
                break;
        }
        repaint();
    }

    public void drawGame(Graphics g){
        //draw background
        g.setColor(Color.BLACK);
        g.fillRect(0,0,gui.width,gui.height);

        //Draw Snake Tails

        g.setColor(new Color(30,200,30));
        for(int i = 0; i<Snake.tails.size(); i++){
            p = Snake.ptc(Snake.tails.get(i).getX(),Snake.tails.get(i).getY());
            g.fillRect(p.x,p.y,32,32);
        }

        //Draw Snake Head
        g.setColor(new Color(0,153,0));
        p = Snake.ptc(Snake.head.getX(), Snake.head.getY());
        g.fillRect(p.x,p.y,32,32);

        // Draw PickUp
        g.setColor(Color.red);
        p = Snake.ptc(Snake.pickup.getX(), Snake.pickup.getY());
        g.fillRect(p.x,p.y,32,32);

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
    }

    public void drawStart(Graphics g){
        //draw background
        g.setColor(Color.BLACK);
        g.fillRect(0,0,gui.width,gui.height);

        //draw Text
        g.setColor(new Color(51,204,51));
        g.setFont(heading);
        g.drawString("Snake",320,50);
        //Your Highscore
        g.setFont(text);
        g.drawString("Press Space to start", 230,500);
        g.setFont(new Font("Arial", Font.BOLD, 10));
        g.drawString("created by Sven & Timo", 10, 550);

        //draw Picture
        URL resource = getClass().getResource("snakeNeu.png");
        try {
            snake = ImageIO.read(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(snake, 145, 90, this);
    }

    public void drawDeath(Graphics g){
        //draw background
        g.setColor(Color.BLACK);
        g.fillRect(0,0,gui.width,gui.height);

        //draw Text
        g.setColor(new Color(51,204,51));
        g.setFont(heading);
        g.drawString("GAME OVER",240,50);
        //Your Score
        g.setFont(text);
        g.drawString("Press Space to play again", 180, 500);
    }
}
