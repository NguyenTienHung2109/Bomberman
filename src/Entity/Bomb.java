package Entity;

import main.CollisionChecker;
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
        super(gp);
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
            brickExploded = ImageIO.read(getClass().getResourceAsStream("/map/brick_exploded.png"));
            brickExploded1 = ImageIO.read(getClass().getResourceAsStream("/map/brick_exploded1.png"));
            brickExploded2 = ImageIO.read(getClass().getResourceAsStream("/map/brick_exploded2.png"));
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

    public void updateBombPosition(int realX, int realY) {
        if (keyH.bombPlaced == true) {
            placed = true;
            bombUnExploded = true;
            this.realX = realX;
            this.worldY = realY;
        }
    }
    public void update(Player player){
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
                        //System.out.println(brickX/gp.tileSize + " " + brickY/gp.tileSize);
                        if(explodeRight) gp.tileM.setMaxTileChar(brickXRight/gp.tileSize , brickYRight/gp.tileSize - 1 , ' ');
                        if(explodeLeft) gp.tileM.setMaxTileChar(brickXLeft/gp.tileSize , brickYLeft/ gp.tileSize - 1, ' ');
                        if(explodeUp) gp.tileM.setMaxTileChar(brickXUp/gp.tileSize, brickYUp/ gp.tileSize - 1, ' ');
                        if(explodeDown) gp.tileM.setMaxTileChar(brickXDown/gp.tileSize, brickYDown/ gp.tileSize - 1, ' ');
                        gp.cChecker.checkBombOnPlayer(this, player);
                        spriteNum = 5;
                    } else if (spriteNum == 5) {
                        gp.cChecker.checkBombOnPlayer(this, player);
                        spriteNum = 6;
                    } else if (spriteNum == 6) {
                        gp.cChecker.checkBombOnPlayer(this, player);
                        keyH.unExploded = false;
                        bombUnExploded = false;
                        spriteNum = 1;
                        changeNum = 0;
                        keyH.bombPresent = false;
                        placed = false;
                        if(explodeRight) gp.tileM.setAlterMap(brickXRight/gp.tileSize , brickYRight/gp.tileSize - 1 , ' ');
                        if(explodeLeft) gp.tileM.setAlterMap(brickXLeft/gp.tileSize , brickYLeft/ gp.tileSize - 1, ' ');
                        if(explodeUp) gp.tileM.setAlterMap(brickXUp/gp.tileSize, brickYUp/ gp.tileSize - 1, ' ');
                        if(explodeDown) gp.tileM.setAlterMap(brickXDown/gp.tileSize, brickYDown/ gp.tileSize - 1, ' ');
                        brickYRight = 0; brickXRight = 0; brickXDown = 0; brickYDown = 0; brickYUp = 0; brickXUp = 0; brickXLeft = 0; brickYLeft = 0;
                        playerOnBomb = false;
                    }
                }
                spriteCounter = 0;
            }
        }
    }


    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        BufferedImage brick = null;
        BufferedImage[] up = new BufferedImage[bombLength];
        BufferedImage[] left = new BufferedImage[bombLength];
        BufferedImage[] down = new BufferedImage[bombLength];
        BufferedImage[] right = new BufferedImage[bombLength];
        if (spriteNum == 1) {
            image = bomb1;
        } else if (spriteNum == 2) {
            image = bomb2;
        } else if (spriteNum == 3) {
            image = bomb3;
        } else if (spriteNum == 4) {

            image = bombExploded1;
            brick = brickExploded;
            for(int i = 0; i < bombLength; i++) {
                if(i == bombLength - 1) {
                    up[i] = up_last;
                    left[i] = left_last;
                    right[i] = right_last;
                    down[i] = down_last;
                } else {
                    up[i] = vertical;
                    left[i] = horizontal;
                    right[i] = horizontal;
                    down[i] = vertical;
                }
            }
        } else if (spriteNum == 5) {
            image = bombExploded2;
            brick = brickExploded1;
            for(int i = 0; i < bombLength; i++) {
                if (i == bombLength - 1) {
                    up[i] = up_last1;
                    left[i] = left_last1;
                    right[i] = right_last1;
                    down[i] = down_last1;
                } else {
                    up[i] = vertical1;
                    left[i] = horizontal1;
                    right[i] = horizontal1;
                    down[i] = vertical1;
                }
            }

        } else if (spriteNum == 6) {
            image = bombExploded3;
            brick = brickExploded2;
            for(int i = 0; i < bombLength; i++) {
                if (i == bombLength - 1) {
                    up[i] = up_last2;
                    left[i] = left_last2;
                    right[i] = right_last2;
                    down[i] = down_last2;
                } else {
                    up[i] = vertical2;
                    left[i] = horizontal2;
                    right[i] = horizontal2;
                    down[i] = vertical2;
                }
            }

        }

        collisionOn = false;
        collisionBombRight = false;
        collisionBombDown = false;
        collisionBombUp = false;
        collisionBombLeft = false;
        explodeUp = false;
        explodeLeft = false;
        explodeDown = false;
        explodeRight = false;

        //checkTileUp
        g2.drawImage(image, realX, worldY, gp.tileSize, gp.tileSize, null);
        for(int i = 0; i < bombLength; i++) {
            gp.cChecker.checkTileBombUp(this, realX, worldY - (i+1) * gp.tileSize);
            if(!collisionBombUp) {
                g2.drawImage(up[i], realX, worldY - (i+1) * gp.tileSize, gp.tileSize, gp.tileSize, null);
            } else {
                if(explodeUp) {
                    brickXUp = realX;
                    brickYUp = worldY - (i+1) * gp.tileSize;
                    g2.drawImage(brick, realX, worldY - (i+1) * gp.tileSize, gp.tileSize, gp.tileSize, null);
                }
                break;
            }
        }

        //checkTileDown
        for(int i = 0; i < bombLength; i++) {
            gp.cChecker.checkTileBombDown(this, realX, worldY + (i+1) * gp.tileSize);
            if(!collisionBombDown) {
                g2.drawImage(down[i], realX, worldY + (i+1) * gp.tileSize, gp.tileSize, gp.tileSize, null);
            } else {
                if(explodeDown) {
                    brickXDown = realX;
                    brickYDown = worldY + (i+1) * gp.tileSize;
                    g2.drawImage(brick, realX, worldY + (i+1) * gp.tileSize, gp.tileSize, gp.tileSize, null);
                }
                break;
            }
        }

        //checkTileRight
        for(int i = 0; i < bombLength; i++) {
            gp.cChecker.checkTileBombRight(this,realX + (i+1) * gp.tileSize, worldY);
            if(!collisionBombRight) {
                g2.drawImage(right[i], realX + (i+1) * gp.tileSize, worldY ,gp.tileSize, gp.tileSize, null);
            } else {
                if(explodeRight) {
                    brickXRight = realX + (i+1) * gp.tileSize;
                    brickYRight = worldY;
                    g2.drawImage(brick,realX + (i+1) * gp.tileSize, worldY ,gp.tileSize, gp.tileSize, null);
                }
                break;
            }
        }

        // checkTileLeft
        for(int i = 0; i < bombLength; i++) {
            gp.cChecker.checkTileBombLeft(this,realX - (i+1) * gp.tileSize, worldY);
            if(!collisionBombLeft) {
                g2.drawImage(left[i], realX - (i+1) * gp.tileSize, worldY ,gp.tileSize, gp.tileSize, null);
            } else {
                if(explodeLeft) {
                    brickXLeft = realX - (i+1) * gp.tileSize;
                    brickYLeft = worldY;
                    g2.drawImage(brick,realX - (i+1) * gp.tileSize, worldY ,gp.tileSize, gp.tileSize, null);
                }
                break;
            }
        }

    }

}
