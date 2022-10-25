package Entity;

import Entity.AI.AILow;
import Entity.AI.AIMedium;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.List;

public class Oneal extends Entity{
   // public point point = new point();
    public Oneal(GamePanel gp) {
        super(gp);
        direction = "left";
        speed = 1;
        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 40;
        ai = new AIMedium(gp.player, this);
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
        gp.cChecker.checkPlayer(this);

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
            int dir = ai.calculateDirection();
            if(dir == 0) {
                direction = "down";
            }
            if(dir == 1) {
                direction = "right";
            }
            if(dir == 2) {
                direction = "left";
            }
            if(dir == 3) {
                direction = "up";
            }
            System.out.println(dir);
        }
           /* int sx = 0, sy= 0;
            int tx = gp.player.worldX;
            int ty = gp.player.worldY;

            point.bfs(1,1);

            List<point> path = new ArrayList<>();
            if(!point.visit[tx][ty]) {
                System.out.println("No path!");
            }
            else {
                point target = new point(tx,ty);
                while(target!= null) {
                    int cx = target.getX();
                    int cy = target.getY();
                    path.add(new point(cx,cy));
                    target = point.par[cx][cy];
                }
            }
            Collections.reverse(path);
            for(point i : path) {
                int curx = i.getX();
                int cury = i.getY();
                if(curx == worldX - 1 && cury == worldY) {
                    direction = "up";
                }
                if(curx == worldX && cury == worldY - 1) {
                    direction = "left";
                }
                if(curx == worldX && cury == worldY + 1) {
                    direction = "right";
                }
                if(curx == worldX + 1 && cury == worldY) {
                    direction = "down";
                }
            }
        }*/
    }

}
