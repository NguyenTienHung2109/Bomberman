package Entity;

import Entity.AI.AIHard;
import Entity.AI.AILow;
import Entity.AI.AIMedium;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Oneal extends Entity{
   // public point point = new point();
    public Oneal(GamePanel gp) {
        super(gp);

        direction = "left";
        speed = 2;

        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 40;
        ai = new AIHard(gp.player, this, gp);
        getOnealImage();
    }
    public void getOnealImage(){
        try {
            left = ImageIO.read(getClass().getResourceAsStream("/oneal/oneal_left1.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/oneal/oneal_left2.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/oneal/oneal_left3.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/oneal/oneal_right1.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/oneal/oneal_right2.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/oneal/oneal_right3.png"));
            dead = ImageIO.read(getClass().getResourceAsStream("/oneal/oneal_dead.png"));
            dead1 = ImageIO.read(getClass().getResourceAsStream("/oneal/mob_dead1.png"));
            dead2 = ImageIO.read(getClass().getResourceAsStream("/oneal/mob_dead2.png"));
            dead3 = ImageIO.read(getClass().getResourceAsStream("/oneal/mob_dead3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw (Graphics2D g2) {

        BufferedImage image = null;
        if (!isDead) {
            switch (direction) {
                case "up":
                    if (spriteNum == 1) {
                        image = left;
                    } else if (spriteNum == 2) {
                        image = left;
                    } else if (spriteNum == 3) {
                        image = left;
                    }
                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = right;
                    } else if (spriteNum == 2) {
                        image = right;
                    } else if (spriteNum == 3) {
                        image = right;
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
            } else if (spriteNumDead == 4) {
                image = dead3;
            } else if (spriteNumDead == 5) {
                image = null;
                worldX = gp.tileSize;
                worldY = gp.tileSize;
            }
        }
        g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
    }

    @Override
    public void update() {
        setAction();
        collisionOn = false;
        gp.cChecker.checkTile(this);

        if(isDead) {
            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNumDead == 1) {
                    gp.player.score += 200;
                    spriteNumDead = 2;
                } else if (spriteNumDead == 2) {
                    spriteNumDead = 3;
                } else if (spriteNumDead == 3) {
                    spriteNumDead = 4;
                } else if (spriteNumDead == 4) {
                    spriteNumDead = 5;
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
    public void setAction() {
        if(!isDead) {

            if(worldX % gp.tileSize == 0 && worldY % gp.tileSize == 0) {
                ai.bfs(worldX / gp.tileSize, worldY / gp.tileSize - 1);
                int dir = ai.calculateDirection();
                if (dir == 0) {
                    direction = "down";
                }
                if (dir == 1) {
                    direction = "right";
                }
                if (dir == 2) {
                    direction = "left";
                }
                if (dir == 3) {
                    direction = "up";
                }
                //System.out.println(direction);
            }
        }

    }
}
