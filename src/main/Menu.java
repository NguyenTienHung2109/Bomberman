package main;


import Entity.Player;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class Menu {
    GamePanel gp;
    Graphics2D g2;
    Font fip;
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
        String text = "Time: " + (302 - gp.time.timeCount);
        int x = gp.tileSize * 10;
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
       // g2.setColor(new Color(70,120,80));
       // g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);
        g2.setColor(new Color(70, 120, 80));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,90F));
        g2.setColor(Color.white);
        String text = "PAUSED";
        int x = getXforCenter(text);
        int y = gp.screenHeight/2 - gp.tileSize * 2;
        g2.drawString(text,x,y);

        text = "CONTINUE";
        x = 3* gp.tileSize + getXforCenter(text);
        y+=3*gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
        g2.drawString(text,x,y);
        if(pcommandNumber ==0) {
            g2.drawString(">",x - gp.tileSize,y);
        }
        text = "QUIT";
        x = getXforCenter(text);
        y+=gp.tileSize *2;
        g2.drawString(text,x,y);
        if(pcommandNumber ==1) {
            g2.drawString(">",x - gp.tileSize,y);
        }
    }
    public void drawWinScreen() {
        g2.setColor(new Color(70,120,80));
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,90F));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setColor(Color.white);
        String text = "YOU WIN";
        int x = getXforCenter(text);
        int y = gp.screenHeight/2 - gp.tileSize * 2;
        g2.drawString(text,x,y);

        text = "RESTART";
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
        x = getXforCenter(text);
        y+= 3 * gp.tileSize;
        g2.drawString(text,x,y);
        if(winCommandNumber ==0) {
            g2.drawString(">",x - gp.tileSize,y);
        }
        text = "QUIT";
        x = getXforCenter(text);
        y+=gp.tileSize *2;
        g2.drawString(text,x,y);
        if(winCommandNumber ==1) {
            g2.drawString(">",x - gp.tileSize,y);
        }

    }
    public void drawMenuScreen() {
        g2.setColor(new Color(70,120,80));
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,90F));
        String text = "Bomberman";
        int x = getXforCenter(text);
        int y = gp.tileSize*3;
        //shadow
        g2.setColor(Color.gray);
        g2.drawString(text, x+5, y+5);
        //main
        g2.setColor(Color.white);
        g2.drawString(text,x,y);
        //menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));

        text = "NEW GAME";
        x = getXforCenter(text);
        y+=gp.tileSize* 3.5;
        g2.drawString(text,x,y);
        if(commandNumber ==0) {
            g2.drawString(">",x - gp.tileSize,y);
        }
        text = "QUIT";
        x = getXforCenter(text);
        y+=gp.tileSize * 4;
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
