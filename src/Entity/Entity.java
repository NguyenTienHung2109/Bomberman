package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY;
    public int realX, realY;
    public int endMapX;
    public double speed;
    public BufferedImage up, up1, up2, down, down1, down2, left, left1, left2, right, right1, right2, defaultImage;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;
    public boolean collisionOn = false;


}
