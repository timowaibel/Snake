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
    BufferedImage pickUp;
    BufferedImage pickUpG;
    BufferedImage headR;
    BufferedImage headU;
    BufferedImage headL;
    BufferedImage headD;
    BufferedImage tail;
    Font heading = new Font("Arial", Font.BOLD, 50);
    Font text = new Font("Arial", Font.BOLD, 35);
    Color textC = new Color(51,204,51);
    URL apfel = getClass().getResource("apfel.png");
    URL gApfel = getClass().getResource("goldenerApfel.png");
    URL HeadR = getClass().getResource("HeadR.png");
    URL HeadU = getClass().getResource("HeadU.png");
    URL HeadL = getClass().getResource("HeadL.png");
    URL HeadD = getClass().getResource("HeadD.png");
    URL Tail = getClass().getResource("Tail.png");

    public Draw() {
        try {
            pickUp = ImageIO.read(apfel);
            pickUpG = ImageIO.read(gApfel);
            headR = ImageIO.read(HeadR);
            headU = ImageIO.read(HeadU);
            headL = ImageIO.read(HeadL);
            headD = ImageIO.read(HeadD);
            tail = ImageIO.read(Tail);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d= (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_OFF);

        switch (Main.screen) {
            case Start -> drawStart(g);
            case Game -> drawGame(g);
            case Death -> drawDeath(g);
        }
        repaint();
    }

    public void drawGame(Graphics g){
        //draw background
        g.setColor(Color.BLACK);
        g.fillRect(0,0,gui.width,gui.height);

        //Draw Snake Tails
        //g.setColor(new Color(30,200,30));
        for(int i = 0; i<Snake.tails.size(); i++){
            p = Snake.ptc(Snake.tails.get(i).getX(),Snake.tails.get(i).getY());
            //g.fillRect(p.x,p.y,32,32);
            drawTail(p.x, p.y, g);
        }

        //Draw Snake Head
        p = Snake.ptc(Snake.head.getX(), Snake.head.getY());
        /*g.setColor(new Color(0,153,0));
        g.fillRect(p.x,p.y,32,32);*/
        drawHead(p.x, p.y, g);

        // Draw PickUp
        p = Snake.ptc(Snake.pickup.getX(), Snake.pickup.getY());

        if(Snake.isGolden()){
            g.drawImage(pickUpG, p.x, p.y, this);
        }else {
            g.drawImage(pickUp, p.x, p.y, this);
        }

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
        g.setColor(textC);
        g.setFont(new Font("Arial",Font.BOLD,20));
        g.drawString("Score "+Snake.score, 5,25);
        g.drawString("Highscore "+Snake.highscore,647,25);
    }

    public void drawStart(Graphics g){
        //draw background
        g.setColor(Color.BLACK);
        g.fillRect(0,0,gui.width,gui.height);

        //draw Text
        g.setColor(textC);
        g.setFont(heading);
        g.drawString("Snake",320,50);
        g.setFont(text);
        g.drawString("Highscore "+Snake.highscore,279,350);
        g.drawString("Press Space to start", 230,500);
        g.setFont(new Font("Arial", Font.BOLD, 10));
        g.drawString("created by Sven & Timo", 10, 550);

        /*//draw Picture
        URL resource = getClass().getResource("snakeNeu.png");
        try {
            snake = ImageIO.read(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(snake, 145, 90, this);*/

        //Draw Snake Tails
        Point p1 = new Point();
        //g.setColor(new Color(30,200,30));
        for(int i = 0; i<Snake.tails.size(); i++){
            p1.setLocation(Snake.tails.get(i).getX(),Snake.tails.get(i).getY());
            //g.fillRect(p1.x,p1.y,32,32);
            drawTail(p1.x, p1.y, g);
        }

        //Draw Snake Head
        g.setColor(new Color(0,153,0));
        p1.setLocation(Snake.head.getX(), Snake.head.getY());
        g.fillRect(p1.x,p1.y,32,32);
        drawHead(p1.x, p1.y, g);
    }

    public void drawDeath(Graphics g){
        //draw background
        g.setColor(Color.BLACK);
        g.fillRect(0,0,gui.width,gui.height);

        //draw Text
        g.setColor(textC);
        g.setFont(heading);
        g.drawString("GAME OVER",240,50);
        g.setFont(text);
        g.drawString("You're Score "+Snake.score,264,400);
        g.drawString("Highscore "+Snake.highscore,278,300);
        g.drawString("Press Space to play again", 180, 500);
    }

    public void drawHead(int x, int y, Graphics g){
        switch (Snake.head.getDirection()){
            case RIGHT -> g.drawImage(headR, x, y, this);
            case UP -> g.drawImage(headU, x, y-10, this);
            case LEFT -> g.drawImage(headL, x-10, y, this);
            case DOWN -> g.drawImage(headD, x, y, this);
        }
    }

    public void drawTail(int x, int y, Graphics g){
        g.drawImage(tail, x, y, this);
    }
}
