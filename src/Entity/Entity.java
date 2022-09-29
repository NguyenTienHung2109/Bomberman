package Entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    GamePanel gp;
    public int worldX, worldY;
    public int realX, realY;
    public int endMapX;
    public double speed;
    public BufferedImage up, up1, up2, down, down1, down2, left, left1, left2, right, right1, right2, defaultImage;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle();
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    public Entity(GamePanel gp) {
        this.gp = gp;
    }
    public void setAction() {

    }
    public void update() {
    setAction();

    collisionOn = false;
    gp.cChecker.checkTile(this);

        if (collisionOn == false) {
            switch (direction) {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left":
                    realX -= speed;
                    if (realX < gp.screenWidth / 2 - gp.tileSize || realX >= endMapX) {
                        worldX -= speed;
                    }
                    break;
                case "right":
                    realX += speed;
                    if (realX < gp.screenWidth / 2 - gp.tileSize || realX >= endMapX) {
                        worldX += speed;
                    }
                    break;
            }
        }

        spriteCounter++;
        if (spriteCounter > 10) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 3;
            } else if (spriteNum == 3) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
    public void draw (Graphics2D g2) {
        /*int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up;
                } else if (spriteNum == 2) {
                    image = up1;
                } else if (spriteNum == 3) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down;
                } else if (spriteNum == 2) {
                    image = down1;
                } else if (spriteNum == 3) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left;
                } else if (spriteNum == 2) {
                    image = left1;
                } else if (spriteNum == 3) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right;
                } else if (spriteNum == 2) {
                    image = right1;
                } else if (spriteNum == 3) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);*/
    }

}
