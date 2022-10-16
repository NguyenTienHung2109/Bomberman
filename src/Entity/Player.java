package Entity;

import main.GamePanel;
import main.KeyHolder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    KeyHolder KeyH;
    public int score = 0;
    public Player(GamePanel gp, KeyHolder keyH) {
        super(gp);
        this.KeyH = keyH;

        solidArea = new Rectangle();
        solidArea.x = 4;
        solidArea.y = 8;
        solidArea.width = 24;
        solidArea.height = 32;

        setDefaultValue();
        getPlayerImage();
    }


    public void setDefaultValue() {
        endMapX = 1080;
        worldX = gp.tileSize;
        worldY = gp.tileSize * 2;
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
            dead = ImageIO.read(getClass().getResourceAsStream("/player/player_dead1.png"));
            dead1 = ImageIO.read(getClass().getResourceAsStream("/player/player_dead2.png"));
            dead2 = ImageIO.read(getClass().getResourceAsStream("/player/player_dead3.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
       // System.out.println((worldX + 24)/gp.tileSize + " " + ((worldY + 24)/ gp.tileSize -1));
        if(gp.tileM.mapTileChar[(worldX + 24)/gp.tileSize][(worldY + 24)/ gp.tileSize - 1] == 'l') {
            gp.bombLength++;
            gp.tileM.setMaxTileChar((worldX + 24)/gp.tileSize, (worldY + 24)/ gp.tileSize - 1, ' ');
        }
        if(playerOnBomb) {
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
            if (KeyH.upPressed == true || KeyH.downPressed == true
                    || KeyH.leftPressed == true || KeyH.rightPressed == true) {
                if (KeyHolder.upPressed == true) {
                    direction = "up";
                } else if (KeyHolder.downPressed == true) {
                    direction = "down";
                } else if (KeyHolder.leftPressed == true) {
                    direction = "left";
                } else if (KeyHolder.rightPressed == true) {
                    direction = "right";
                }

                //CHECK TILE COLLISION

                collisionOn = false;
                gp.cChecker.checkTile(this);

                // IF COLLISION IS FALSE, PLAYER CAN MOVE
                if (collisionOn == false) {
                    switch (direction) {
                        case "up": worldY -= speed; break;
                        case "down": worldY += speed; break;
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
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        if(!playerOnBomb) {
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
        } else {
            if (spriteNumDead == 1) {
                image = dead;
            } else if (spriteNumDead == 2) {
                image = dead1;
            } else if (spriteNumDead == 3) {
                image = dead2;
            }
        }
        g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
    }
}
