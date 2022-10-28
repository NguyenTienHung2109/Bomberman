package main;


import Entity.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Menu {
    GamePanel gp;
    Graphics2D g2;
    Font fip;
    BufferedImage image = null;
    public int commandNumber= 0;
    public int pcommandNumber = 0;
    public int winCommandNumber = 0;
    public  Menu(GamePanel gp){
        this.gp = gp;
        try {
            InputStream is = getClass().getResourceAsStream("/font/Minecraft.ttf");
            fip = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(fip);
        g2.setColor(Color.white);

        if(gp.gameState == gp.menuState) {
            drawMenuScreen();
        }
        if(gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
    }

    public void getScore(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(fip);
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(30F));
        String text = "Score: " + gp.player.score;
        int x = gp.tileSize;
        int y = gp.tileSize - 10;
        g2.drawString(text,x,y);
    }
    public void getTime(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(fip);
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(30F));
        String text = "Time: " + gp.time.second;
        int x = gp.tileSize * 15;
        int y = gp.tileSize - 10;
        g2.drawString(text, x, y);
    }
    public void drawLevel(Graphics2D g2) {
        this.g2 = g2;
        g2.setColor(new Color(70, 120, 80));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setFont(fip);
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
        String text = "";
        if(gp.currentLevel == 1) {
            text = "STAGE 1";
        } else if(gp.currentLevel == 2){
            text = "STAGE 2";
        } else {
            text = "STAGE 3";
        }
        int x = getXforCenter(text);
        int y = gp.screenHeight / 2;
        g2.drawString(text, x, y);
    }

    public void drawPauseScreen() {

        g2.setColor(new Color(70, 120, 80));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,70F));
        g2.setColor(Color.white);
        String text = "PAUSED";
        int x = getXforCenter(text);
        int y = gp.tileSize * 4;
        g2.drawString(text,x,y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
        text = "CONTINUE";
        x = getXforCenter(text);
        y+=3.5*gp.tileSize;
        g2.drawString(text,x,y);
        if(pcommandNumber ==0) {
            g2.drawString(">",x - gp.tileSize,y);
        }
        text = "QUIT";
        x = getXforCenter(text);
        y+=gp.tileSize *3;
        g2.drawString(text,x,y);
        if(pcommandNumber ==1) {
            g2.drawString(">",x - gp.tileSize,y);
        }
    }
    public void drawWinScreen() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/map/Win.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g2.drawImage(image, 0,0, gp.screenWidth, gp.screenHeight, null);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
        g2.setColor(Color.white);
        String text = "YOU WIN";
        int x = getXforCenter(text);
        int y = gp.screenHeight/2 - gp.tileSize * 2;
        text = "YOUR SCORE: " + gp.player.score;
        x = getXforCenter(text);
        y = gp.tileSize*7;
        g2.drawString(text,x,y);

        text = "YOUR TIME: " + gp.time.second;
        x = getXforCenter(text);
        y += gp.tileSize;
        g2.drawString(text,x,y);


        text = "RESTART";
        x = getXforCenter(text);
        y+= 2* gp.tileSize;
        g2.drawString(text,x,y);
        if(winCommandNumber ==0) {
            g2.drawString(">",x - gp.tileSize,y);
        }
        text = "QUIT";
        x = getXforCenter(text);
        y+=gp.tileSize *2.2;
        g2.drawString(text,x,y);
        if(winCommandNumber ==1) {
            g2.drawString(">",x - gp.tileSize,y);
        }

    }
    public void drawMenuScreen() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/map/BG.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g2.drawImage(image, 0,0, gp.screenWidth, gp.screenHeight, null);
        String text = "Bomberman";
        int x = getXforCenter(text);
        int y = gp.tileSize*3;
        g2.setColor(Color.white);
        //menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
        text = "NEW GAME";
        x = getXforCenter(text);
        y+=gp.tileSize* 4;
        g2.drawString(text,x,y);
        if(commandNumber ==0) {
            g2.drawString(">",x - gp.tileSize,y);
        }
        text = "QUIT";
        x = getXforCenter(text);
        y+=gp.tileSize * 3;
        g2.drawString(text,x,y);
        if(commandNumber ==1) {
            g2.drawString(">",x - gp.tileSize,y);
        }
    }
    public int getXforCenter(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }

}
