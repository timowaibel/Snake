package Gui;

import action.Main;
import clock.GameClock;
import game.Snake;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Draw extends JLabel {

    Point p;
    BufferedImage pickUp;
    BufferedImage pickUpG;
    BufferedImage headR;
    BufferedImage headU;
    BufferedImage headL;
    BufferedImage headD;
    BufferedImage tail1;
    BufferedImage tail2;
    BufferedImage dFolded;
    BufferedImage dOpen;
    BufferedImage edit;
    Font heading = new Font("Arial", Font.BOLD, 50);
    Font text = new Font("Arial", Font.BOLD, 35);
    Color textC = new Color(51,204,51);
    Color textC1 = new Color(221,51,51);
    URL apfel = getClass().getResource("apfel.png");
    URL gApfel = getClass().getResource("goldenerApfel.png");
    URL HeadR = getClass().getResource("HeadR.png");
    URL HeadU = getClass().getResource("HeadU.png");
    URL HeadL = getClass().getResource("HeadL.png");
    URL HeadD = getClass().getResource("HeadD.png");
    URL Tail1 = getClass().getResource("Tail1.png");
    URL Tail2 = getClass().getResource("Tail2.png");
    URL DFolded = getClass().getResource("Dropdown folded.png");
    URL DOpen = getClass().getResource("Dropdown open.png");
    URL Edit = getClass().getResource("edit.png");

    public static int[] dropdown = {170, 35, 300, 100};

    public Draw() {
        try {
            pickUp = ImageIO.read(apfel);
            pickUpG = ImageIO.read(gApfel);
            headR = ImageIO.read(HeadR);
            headU = ImageIO.read(HeadU);
            headL = ImageIO.read(HeadL);
            headD = ImageIO.read(HeadD);
            tail1 = ImageIO.read(Tail1);
            tail2 = ImageIO.read(Tail2);
            dFolded = ImageIO.read(DFolded);
            dOpen = ImageIO.read(DOpen);
            edit = ImageIO.read(Edit);
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
        for(int i = 0; i<Snake.tails.size(); i++){
            p = Snake.ptc(Snake.tails.get(i).getX(),Snake.tails.get(i).getY());
            drawTail(p.x, p.y, g, i%2);
        }

        //Draw Snake Head
        p = Snake.ptc(Snake.head.getX(), Snake.head.getY());
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
                g.drawRect(x*32+gui.xOff,y*32+gui.yOff,32,32);
            }
        }

        //Draw Border
        g.setColor(Color.lightGray);
        g.drawRect(gui.xOff,gui.yOff,512,512);

        // Draw Score
        g.setColor(textC);
        g.setFont(new Font("Arial",Font.BOLD,20));
        g.drawString("Score "+Snake.score, 5,25);
        g.drawString("Highscore "+Snake.highscore,647,25);
        if(GameClock.getSelUser() >= 0){
            g.drawString("Your",647,50);
            g.drawString("Highscore " + Main.users.get(GameClock.getSelUser()).getHighscore(),647, 72);
        }
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
        g.drawString("Global Highscore "+Snake.highscore,249,350);
        if(GameClock.getSelUser()>=0){
            g.drawString("Your Highscore "+Main.users.get(GameClock.getSelUser()).getHighscore(),259,390);
        }
        g.drawString("Press Space to start", 230,500);
        g.setFont(new Font("Arial", Font.BOLD, 10));
        g.drawString("created by Sven & Timo", 10, 550);

        //Difficulties
        switch(Main.getDifficulties()){
            case EASY -> {
                g.setFont(text);
                g.setColor(textC);
                g.drawString("MEDIUM",320,250);
                g.drawString("HARD",550,250);
                g.setColor(textC1);
                g.drawString("EASY",150,250);
            }
            case MEDIUM -> {
                g.setFont(text);
                g.setColor(textC1);
                g.drawString("MEDIUM",320,250);
                g.setColor(textC);
                g.drawString("HARD",550,250);
                g.drawString("EASY",150,250);
            }
            case HARD -> {
                g.setFont(text);
                g.setColor(textC);
                g.drawString("MEDIUM",320,250);
                g.setColor(textC1);
                g.drawString("HARD",550,250);
                g.setColor(textC);
                g.drawString("EASY",150,250);
            }
        }

        //Draw Snake Tails
        Point p1 = new Point();
        for(int i = 0; i<Snake.tails.size(); i++){
            p1.setLocation(Snake.tails.get(i).getX(),Snake.tails.get(i).getY());
            drawTail(p1.x, p1.y, g, i%2);
        }

        //Draw Snake Head
        g.setColor(new Color(0,153,0));
        p1.setLocation(Snake.head.getX(), Snake.head.getY());
        g.fillRect(p1.x,p1.y,32,32);
        drawHead(p1.x, p1.y, g);

        //Draw Dropdown menu
        drawDropdown(g);
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
        g.drawString("Your Score "+Snake.score,264,400);
        g.drawString("Highscore "+Snake.highscore,278,300);
        if(GameClock.getSelUser()>=0){
            g.drawString("Your Highscore "+ Main.users.get(GameClock.getSelUser()).getHighscore(), 230, 350);
        }
        g.drawString("Press Space to play again", 180, 500);

        //Difficulties
        switch(Main.getDifficulties()) {
            case EASY -> {
                g.setFont(text);
                g.setColor(textC);
                g.drawString("MEDIUM", 320, 250);
                g.drawString("HARD", 550, 250);
                g.setColor(textC1);
                g.drawString("EASY", 150, 250);
            }
            case MEDIUM -> {
                g.setFont(text);
                g.setColor(textC1);
                g.drawString("MEDIUM", 320, 250);
                g.setColor(textC);
                g.drawString("HARD", 550, 250);
                g.drawString("EASY", 150, 250);
            }
            case HARD -> {
                g.setFont(text);
                g.setColor(textC);
                g.drawString("MEDIUM", 320, 250);
                g.setColor(textC1);
                g.drawString("HARD", 550, 250);
                g.setColor(textC);
                g.drawString("EASY", 150, 250);
            }
        }

        drawDropdown(g);
    }

    public void drawHead(int x, int y, Graphics g){
        switch (Snake.head.getDirection()){
            case RIGHT -> g.drawImage(headR, x, y, this);
            case UP -> g.drawImage(headU, x, y-10, this);
            case LEFT -> g.drawImage(headL, x-10, y, this);
            case DOWN -> g.drawImage(headD, x, y, this);
        }
    }

    public void drawTail(int x, int y, Graphics g, int i){
        if(i==0){
            g.drawImage(tail1, x, y, this);
        }else{
            g.drawImage(tail2, x, y, this);
        }
    }

    public void drawDropdown(Graphics g){
        int width = dropdown[0];
        int height = dropdown[1];
        int startX = dropdown[2];
        int startY = dropdown[3];
        g.setColor(Color.YELLOW);
        g.fillRect(startX,startY, width,height);
        g.setColor(textC);
        g.drawRect(startX,startY, width,height);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial",Font.PLAIN,20));
        g.drawString("Select User",startX+5,startY+30);

        if(!GameClock.folded){
            for(int i = 0; i < Main.users.size()+1;i++){
                if(i==Main.users.size()){
                    if(Main.users.size() <= Main.maxUser){
                        g.setColor(Color.ORANGE);
                        g.fillRect(startX,((i+1)*height)+startY, width,height);
                        g.setColor(textC);
                        g.drawRect(startX,((i+1)*height)+startY, width,height);
                        g.setColor(Color.BLACK);
                        g.setFont(new Font("Arial",Font.PLAIN,20));
                        g.drawString("Create new User",startX+5,((i+1)*height)+startY+30);
                    }
                }else{
                    g.setColor(Color.white);
                    g.fillRect(startX,((i+1)*height)+startY, width,height);
                    if(GameClock.getSelUser() >= -1 && GameClock.getSelUser() == i){
                        g.setColor(Color.RED);
                        g.fillRect(startX, ((GameClock.getSelUser()+1)*height)+startY, width,height);
                    }
                    g.setColor(textC);
                    g.drawRect(startX,((i+1)*height)+startY, width,height);
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Arial",Font.PLAIN,20));
                    g.drawString(Main.users.get(i).getName(),startX+5,((i+1)*height)+startY+30);
                    g.drawImage(edit, (startX + width - 35), ((i+1)*height)+startY, this);
                }
            }
        }

        if(GameClock.folded){
            g.drawImage(dFolded, startX + width - 35, startY, this);
        }else{
            g.drawImage(dOpen, startX + width - 35, startY, this);
        }
    }
}
