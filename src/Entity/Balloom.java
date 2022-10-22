package Entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class Balloom extends Entity{


    public Balloom(GamePanel gp) {
        super(gp);
        direction = "left";
        speed = 2;
        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 40;
        getBallommImage();
    }

    public int convertTile(char tempTileNum) {
        if (tempTileNum == '#') {
            return 1;
        } else if (tempTileNum == ' ' || tempTileNum == '1' || tempTileNum == '2' || tempTileNum == 'p' || tempTileNum == '3') {
            return 0;
        } else if (tempTileNum == 'b') {
            return 3;
        } else if (tempTileNum == 'l') {
            return 4;
        } else if (tempTileNum == 'X') {
            return 5;
        } else if(tempTileNum == 'N') {
            return 6;
        } else if(tempTileNum == 'S') {
            return 7;
        } else {
            return 2;
        }

    }
    public void getBallommImage(){
            try {
                left = ImageIO.read(getClass().getResourceAsStream("/balloom/balloom_left1.png"));
                left1 = ImageIO.read(getClass().getResourceAsStream("/balloom/balloom_left2.png"));
                left2 = ImageIO.read(getClass().getResourceAsStream("/balloom/balloom_left3.png"));
                right = ImageIO.read(getClass().getResourceAsStream("/balloom/balloom_right1.png"));
                right1 = ImageIO.read(getClass().getResourceAsStream("/balloom/balloom_right2.png"));
                right2 = ImageIO.read(getClass().getResourceAsStream("/balloom/balloom_right3.png"));
                dead = ImageIO.read(getClass().getResourceAsStream("/balloom/balloom_dead.png"));
                dead1 = ImageIO.read(getClass().getResourceAsStream("/balloom/mob_dead1.png"));
                dead2 = ImageIO.read(getClass().getResourceAsStream("/balloom/mob_dead2.png"));
                dead3 = ImageIO.read(getClass().getResourceAsStream("/balloom/mob_dead3.png"));
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
        //gp.cChecker.checkPlayer(this);

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
                    gp.demMonsterKilled++;
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
            if (worldX % gp.tileSize == 0 && worldY % gp.tileSize == 0) {

                List<String> lead = new ArrayList<String>();
                char balloomChar = gp.tileM.mapTileChar[worldX / gp.tileSize - 1][worldY / gp.tileSize - 1];
                int balloomLeft = convertTile(balloomChar);
                balloomChar = gp.tileM.mapTileChar[worldX / gp.tileSize][worldY / gp.tileSize - 2];
                int balloomUp = convertTile(balloomChar);
                balloomChar = gp.tileM.mapTileChar[worldX / gp.tileSize][worldY / gp.tileSize];
                int balloomDown = convertTile(balloomChar);
                balloomChar = gp.tileM.mapTileChar[worldX / gp.tileSize + 1][worldY / gp.tileSize - 1];
                int balloomRight = convertTile(balloomChar);
                //System.out.println(gp.tileM.mapTileChar[worldX / gp.tileSize][worldY / gp.tileSize - 2] + " " + worldX / gp.tileSize + " " + (worldY / gp.tileSize - 2) + " " + gp.tileM.tile[balloomUp].collision);
                if (!gp.tileM.tile[balloomUp].collision) lead.add("up");
                if (!gp.tileM.tile[balloomDown].collision) lead.add("down");
                if (!gp.tileM.tile[balloomLeft].collision) lead.add("left");
                if (!gp.tileM.tile[balloomRight].collision) lead.add("right");

                if (lead.size() == 1) {
                    switch (direction) {
                        case "up":
                            direction = "down";
                            break;
                        case "down":
                            direction = "up";
                            break;
                        case "left":
                            direction = "right";
                            break;
                        case "right":
                            direction = "left";
                    }

                } else if (lead.size() == 2) {
                    if ((lead.get(0) == "up" && lead.get(1) == "down") || (lead.get(0) == "left" && lead.get(1) == "right")
                            || (lead.get(1) == "up" && lead.get(0) == "down") || (lead.get(1) == "left" && lead.get(0) == "right")) {

                    } else {
                        Random random = new Random();
                        int i = random.nextInt(100) + 1;
                        if (i <= 50) {
                            direction = lead.get(0);
                        } else {
                            direction = lead.get(1);
                        }
                    }
                } else if (lead.size() == 3) {
                    Random random = new Random();
                    int i = random.nextInt(100) + 1;
                    if (i <= 33) {
                        direction = lead.get(0);
                    } else if (i > 33 && i <= 66) {
                        direction = lead.get(1);
                    } else {
                        direction = lead.get(2);
                    }
                } else if (lead.size() == 4) {
                    Random random = new Random();
                    int i = random.nextInt(100) + 1;
                    if (i <= 25) {
                        direction = lead.get(0);
                    } else if (i > 25 && i <= 50) {
                        direction = lead.get(1);
                    } else if (i > 50 && i <= 75) {
                        direction = lead.get(2);
                    } else {
                        direction = lead.get(3);
                    }
                }

            }
        }
        //System.out.println(worldX/gp.tileSize + " " + worldY/gp.tileSize);
  }

}
