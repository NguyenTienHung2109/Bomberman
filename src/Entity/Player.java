package Entity;

import main.GamePanel;
import main.KeyHolder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gp;
    KeyHolder KeyH;
    public final int screenX;

    public Player(GamePanel gp, KeyHolder keyH) {
        this.gp = gp;
        this.KeyH = keyH;
        screenX = gp.screenWidth/2;
        setDefaultValue();
        getPlayerImage();
    }
    public void setDefaultValue() {
        endMapX = 1080;
        worldX = gp.tileSize;
        worldY = gp.tileSize * 2;
        realX = gp.tileSize;
        realY = gp.tileSize*2;
        speed = 2;
        direction = "down";
    }
    public void getPlayerImage() {
        try {
            up = ImageIO.read(getClass().getResourceAsStream("/player/player_up.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_2.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/player/player_down.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_2.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/player/player_left.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_2.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/player/player_right.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if(KeyH.upPressed == true || KeyH.downPressed == true
                || KeyH.leftPressed == true || KeyH.rightPressed == true) {
            if (KeyHolder.upPressed == true) {
                direction = "up";
                worldY -= speed;
            } else if (KeyHolder.downPressed == true) {
                direction = "down";
                worldY += speed;
            } else if (KeyHolder.leftPressed == true) {
                direction = "left";
                realX -=speed;
                if(realX < gp.screenWidth/2 - gp.tileSize || realX >= endMapX) {
                    worldX -= speed;
                }
            } else if (KeyHolder.rightPressed == true) {
                direction = "right";
                realX +=speed;
                if(realX < gp.screenWidth/2 - gp.tileSize || realX >= endMapX) {
                    worldX += speed;
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
    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if(spriteNum == 1) {
                    image = up;
                } else if(spriteNum == 2) {
                    image = up1;
                } else if(spriteNum == 3) {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down;
                } else if(spriteNum == 2) {
                    image = down1;
                } else if(spriteNum == 3) {
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left;
                } else if(spriteNum == 2) {
                    image = left1;
                } else if(spriteNum == 3) {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right;
                } else if(spriteNum == 2) {
                    image = right1;
                } else if(spriteNum == 3) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
    }
}
