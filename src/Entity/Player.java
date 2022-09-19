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

    public Player(GamePanel gp, KeyHolder keyH) {
        this.gp = gp;
        this.KeyH = keyH;
        setDefaultValue();
        getPlayerImage();
    }
    public void setDefaultValue() {
        x = 48;
        y = 48;
        speed = 4;
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
                y -= speed;
            } else if (KeyHolder.downPressed == true) {
                direction = "down";
                y += speed;
            } else if (KeyHolder.leftPressed == true) {
                direction = "left";
                x -= speed;
            } else if (KeyHolder.rightPressed == true) {
                direction = "right";
                x += speed;
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
     //   g2.setColor(Color.white);
     //   g2.fillRect(x, y, gp.tileSize, gp.tileSize);
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
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
