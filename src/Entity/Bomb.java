package Entity;

import main.GamePanel;
import main.KeyHolder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bomb extends Entity{
    GamePanel gp;
    public boolean placed = false;
    public boolean bombUnExploded = false;
    KeyHolder keyH;
    int playerRealX;
    public Bomb(GamePanel gp, KeyHolder keyH) {
        this.gp = gp;
        this.keyH = keyH;
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 8;
        solidArea.width = 24;
        solidArea.height = 32;
        speed = 0;
        getBombImage();
    }
    public void getBombImage() {
        try {
            bomb1 = ImageIO.read(getClass().getResourceAsStream("/bomb/bomb.png"));
            bomb2 = ImageIO.read(getClass().getResourceAsStream("/bomb/bomb_1.png"));
            bomb3 = ImageIO.read(getClass().getResourceAsStream("/bomb/bomb_2.png"));
            bombExploded1 = ImageIO.read(getClass().getResourceAsStream("/bomb/bomb_exploded.png"));
            bombExploded2 = ImageIO.read(getClass().getResourceAsStream("/bomb/bomb_exploded1.png"));
            bombExploded3 = ImageIO.read(getClass().getResourceAsStream("/bomb/bomb_exploded2.png"));
            horizontal = ImageIO.read(getClass().getResourceAsStream("/bomb/explosion_horizontal.png"));
            horizontal1 = ImageIO.read(getClass().getResourceAsStream("/bomb/explosion_horizontal1.png"));
            horizontal2 = ImageIO.read(getClass().getResourceAsStream("/bomb/explosion_horizontal2.png"));
            vertical = ImageIO.read(getClass().getResourceAsStream("/bomb/explosion_vertical.png"));
            vertical1 = ImageIO.read(getClass().getResourceAsStream("/bomb/explosion_vertical1.png"));
            vertical2 = ImageIO.read(getClass().getResourceAsStream("/bomb/explosion_vertical2.png"));
            left_last = ImageIO.read(getClass().getResourceAsStream("/bomb/explosion_horizontal_left_last.png"));
            left_last1 = ImageIO.read(getClass().getResourceAsStream("/bomb/explosion_horizontal_left_last1.png"));
            left_last2 = ImageIO.read(getClass().getResourceAsStream("/bomb/explosion_horizontal_left_last2.png"));
            right_last = ImageIO.read(getClass().getResourceAsStream("/bomb/explosion_horizontal_right_last.png"));
            right_last1 = ImageIO.read(getClass().getResourceAsStream("/bomb/explosion_horizontal_right_last1.png"));
            right_last2 = ImageIO.read(getClass().getResourceAsStream("/bomb/explosion_horizontal_right_last2.png"));
            down_last = ImageIO.read(getClass().getResourceAsStream("/bomb/explosion_vertical_down_last.png"));
            down_last1 = ImageIO.read(getClass().getResourceAsStream("/bomb/explosion_vertical_down_last1.png"));
            down_last2 = ImageIO.read(getClass().getResourceAsStream("/bomb/explosion_vertical_down_last2.png"));
            up_last = ImageIO.read(getClass().getResourceAsStream("/bomb/explosion_vertical_top_last.png"));
            up_last1 = ImageIO.read(getClass().getResourceAsStream("/bomb/explosion_vertical_top_last1.png"));
            up_last2 = ImageIO.read(getClass().getResourceAsStream("/bomb/explosion_vertical_top_last2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(int realX, int realY) {
        if(keyH.bombPlaced == true){
            placed = true;
            bombUnExploded = true;
            this.realX = realX;
            this.worldY = realY;
        }
        if(placed == true) {
            spriteCounter++;
            if (spriteCounter > 10) {
                if(changeNum <= 3) {
                    if (spriteNum == 1) {
                        spriteNum = 2;
                    } else if (spriteNum == 2) {
                        spriteNum = 3;
                    } else if (spriteNum == 3) {
                        spriteNum = 1;
                        changeNum++;
                    }
                    if(changeNum == 3) {
                        spriteNum = 4;
                        changeNum++;
                    }
                } else {
                    if(spriteNum == 4) {
                        spriteNum = 5;
                    } else if (spriteNum == 5) {
                        spriteNum = 6;
                    } else if (spriteNum == 6) {
                        keyH.unExploded = false;
                        bombUnExploded = false;
                        spriteNum = 1;
                        changeNum = 0;
                        keyH.bombPresent = false;
                        placed = false;
                    }
                }
                spriteCounter = 0;
            }
        }
    }


    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        BufferedImage up = null;
        BufferedImage left = null;
        BufferedImage down = null;
        BufferedImage right = null;
        if (spriteNum == 1) {
            image = bomb1;
        } else if (spriteNum == 2) {
            image = bomb2;
        } else if (spriteNum == 3) {
            image = bomb3;
        } else if (spriteNum == 4) {
            image = bombExploded1;
            up = up_last;
            left = left_last;
            right = right_last;
            down = down_last;

        } else if (spriteNum == 5) {
            image = bombExploded2;

            up = up_last1;
            left = left_last1;
            right = right_last1;
            down = down_last1;

        } else if (spriteNum == 6) {
            image = bombExploded3;
            up = up_last2;
            left = left_last2;
            right = right_last2;
            down = down_last2;

        }

        collisionOn = false;
        collisionBombRight = false;
        collisionBombDown = false;
        collisionBombUp = false;
        collisionBombLeft = false;
        gp.cChecker.checkTileBomb(this);
        g2.drawImage(image, realX, worldY, gp.tileSize, gp.tileSize, null);
        if(collisionBombUp == false) {g2.drawImage(up, realX, worldY - gp.tileSize, gp.tileSize, gp.tileSize, null);}
        if(collisionBombDown == false) {g2.drawImage(down, realX, worldY + gp.tileSize, gp.tileSize, gp.tileSize, null);}
        if(collisionBombLeft == false) {g2.drawImage(left, realX - gp.tileSize, worldY, gp.tileSize, gp.tileSize, null);}
        if(collisionBombRight == false) {g2.drawImage(right, realX + gp.tileSize, worldY, gp.tileSize, gp.tileSize, null);}
    }
    public void setPlayerRealX(int playerRealX) {
        this.playerRealX = playerRealX;
    }

    public void drawMidMap(Graphics2D g2) {
        BufferedImage image = null;
        BufferedImage up = null;
        BufferedImage left = null;
        BufferedImage down = null;
        BufferedImage right = null;
        if (spriteNum == 1) {
            image = bomb1;
        } else if (spriteNum == 2) {
            image = bomb2;
        } else if (spriteNum == 3) {
            image = bomb3;
        } else if (spriteNum == 4) {
            image = bombExploded1;
            up = up_last;
            left = left_last;
            right = right_last;
            down = down_last;
        } else if (spriteNum == 5) {
            up = up_last1;
            left = left_last1;
            right = right_last1;
            down = down_last1;
            image = bombExploded2;
        } else if (spriteNum == 6) {
            up = up_last2;
            left = left_last2;
            right = right_last2;
            down = down_last2;
            image = bombExploded3;

        }
        int bX;
        bX = realX - playerRealX + 310;
        collisionOn = false;
        collisionBombRight = false;
        collisionBombDown = false;
        collisionBombUp = false;
        collisionBombLeft = false;
        gp.cChecker.checkTileBomb(this);
        g2.drawImage(image, bX, worldY, gp.tileSize, gp.tileSize, null);
        if(collisionBombUp == false)g2.drawImage(up, bX , worldY - gp.tileSize, gp.tileSize, gp.tileSize, null);
        if(collisionBombDown == false)g2.drawImage(down, bX , worldY + gp.tileSize, gp.tileSize, gp.tileSize, null);
        if(collisionBombLeft ==false)g2.drawImage(left, bX - gp.tileSize, worldY , gp.tileSize, gp.tileSize, null);
        if(collisionBombRight == false)g2.drawImage(right,bX + gp.tileSize, worldY , gp.tileSize, gp.tileSize, null);
    }
}
