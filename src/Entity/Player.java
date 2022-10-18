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
    public boolean message = false;
    long createdMillis = 0;
    public long deadCounter = 0;
    long deadCounter2 = 0;
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

        if(gp.tileM.mapTileChar[(worldX + 24)/gp.tileSize][(worldY + 24)/ gp.tileSize - 1] == 'l') {
            gp.bombLength++;
            gp.tileM.setMaxTileChar((worldX + 24)/gp.tileSize, (worldY + 24)/ gp.tileSize - 1, ' ');
        }
        if(gp.tileM.mapTileChar[(worldX + 24)/gp.tileSize][(worldY + 24)/ gp.tileSize - 1] == 'N') {
            gp.bombMax++;
            gp.tileM.setMaxTileChar((worldX + 24)/gp.tileSize, (worldY + 24)/ gp.tileSize - 1, ' ');
        }
        if(gp.tileM.mapTileChar[(worldX + 24)/gp.tileSize][(worldY + 24)/ gp.tileSize - 1] == 'S') {
            speed += 1;
            createdMillis = System.currentTimeMillis();
            gp.tileM.setMaxTileChar((worldX + 24)/gp.tileSize, (worldY + 24)/ gp.tileSize - 1, ' ');
        }
        if(gp.tileM.mapTileChar[(worldX + 24)/gp.tileSize][(worldY + 24)/ gp.tileSize - 1] == 'X'  && gp.demMonsterKilled == gp.aSetter.demBalloom) {
            if(gp.currentLevel == 1) {
                gp.scoreLevel2 = score;
            } else if(gp.currentLevel == 2) {
                gp.scoreLevel3 = score;
            }
            gp.currentLevel++;
            gp.tileM.loadMap(gp.currentLevel);
            gp.aSetter.setBalloom();
            gp.demMonsterKilled = 0;
            gp.aSetter.demBalloom = 0;
            gp.bombMax = 1;
            gp.bombLength = 1;
        }
        if(speed == 3) {
            long nowMillis = System.currentTimeMillis();
            if((nowMillis - createdMillis)/1000 == 5) {
                speed = 2;
            }
        }
        int npcIndex = gp.cChecker.checkEntity(this, gp.balloom);
        interactNPC(npcIndex);
        if(isDead) {
            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNumDead == 1) {
                    spriteNumDead = 2;
                } else if (spriteNumDead == 2) {
                    spriteNumDead = 3;
                } else if (spriteNumDead == 3) {
                    spriteNumDead = 4;
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

                // CHECK NPC COLLISION


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

    public void interactNPC(int i) {
        if (i != 999) {
            System.out.println("hitting");
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        if(!isDead) {
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
            } else if (spriteNumDead == 4) {
                image = null;
                deadCounter++;
                if(deadCounter == 50) {

                    message = true;
                    for(int i = 0; i < gp.balloom.length; i++) {
                        gp.balloom[i] = null;
                    }
                    deadCounter = 0;
                }
                if(message == true) {
                    gp.menu.drawLevel(g2);
                    deadCounter2++;
                    if(deadCounter2 == 100) {
                        gp.tileM.loadMap(gp.currentLevel);
                        gp.aSetter.demBalloom = 0;
                        gp.demMonsterKilled = 0;
                        gp.aSetter.setBalloom();
                        message = false;
                        isDead = false;
                        deadCounter2 = 0;
                        spriteNumDead = 1;
                        deadCounter = 0;
                        gp.bombLength = 1;
                        gp.bombMax = 1;
                    }
                }
            }
        }
        g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
    }
}
