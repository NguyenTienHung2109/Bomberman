package Entity;

import main.GamePanel;
import org.w3c.dom.Node;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int changeNum = 0;
    public boolean collisionOn = false;
    public boolean collisionBombUp = false;
    public boolean collisionBombDown = false;
    public boolean collisionBombRight = false;
    public boolean collisionBombLeft = false;
    public boolean explodeRight = false;
    public boolean explodeLeft = false;
    public boolean explodeUp = false;
    public boolean explodeDown = false;
    public boolean playerOnBomb = false;
    public boolean isDead = false;
    GamePanel gp;
    public int worldX, worldY;
    public int endMapX;
    public double speed;
    public int brickXUp = 0, brickXDown = 0, brickXLeft = 0, brickXRight = 0, brickYUp = 0, brickYDown = 0, brickYLeft = 0, brickYRight = 0;
    public BufferedImage up, up1, up2, down, down1, down2, left, left1, left2, right, right1, right2, dead, dead1, dead2, dead3;
    public BufferedImage bomb1, bomb2, bomb3, bombExploded1, bombExploded2, bombExploded3;
    public BufferedImage horizontal, horizontal1, horizontal2, vertical, vertical1, vertical2;
    public BufferedImage left_last, left_last1, left_last2, right_last, right_last1, right_last2;
    public BufferedImage down_last, down_last1, down_last2, up_last, up_last1, up_last2;
    public BufferedImage brickExploded, brickExploded1, brickExploded2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int spriteNumDead = 1;

    public Rectangle solidArea = new Rectangle();
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
        gp.cChecker.checkPlayer(this);

        if(isDead) {
            System.out.println("dead");
            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNumDead == 1) {
                    spriteNumDead = 2;
                } else if (spriteNumDead == 2) {
                    spriteNumDead = 3;
                } else if (spriteNumDead == 3) {
                    spriteNumDead = 1;
                }
                spriteCounter = 0;
            }
        } else {
            if (collisionOn == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
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
    }
    public void draw (Graphics2D g2) {
        //int screenX = worldX - gp.player.worldX + gp.player.screenX;
        //int screenY = worldY - gp.player.worldY + gp.player.screenY;
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
        g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
    }
}
