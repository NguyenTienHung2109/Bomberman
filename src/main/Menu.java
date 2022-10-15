package main;


import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class Menu {
    GamePanel gp;
    Graphics2D g2;
    Font fip, retro, minecratf;
    public int commandNumber= 0;
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
    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,90F));
        String text = "PAUSED";
        int x = getXforCenter(text);
        int y = gp.screenHeight/2;
        g2.drawString(text,x,y);
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
