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
    public boolean done = true;
    public boolean bombUnExploded = false;
    KeyHolder keyH;

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

    public void updateBombPosition(int worldX, int worldY) {
        if (keyH.bombPlaced == true) {
            placed = true;
            bombUnExploded = true;

            this.worldX = worldX;
            this.worldY = worldY;

        }
    }
    public void update(Entity player, Balloom[] balloom){
        if(placed == true) {
            spriteCounter++;
            if((player.worldX + player.solidArea.x)/gp.tileSize != worldX/gp.tileSize || (player.worldY + player.solidArea.y)/gp.tileSize != worldY/gp.tileSize) {
                gp.tileM.setMaxTileChar(worldX / gp.tileSize, worldY / gp.tileSize - 1, 'b');
                gp.tileM.setAlterMap(worldX / gp.tileSize, worldY / gp.tileSize - 1, 'b');

            }
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
                        if(explodeRight) {
                            if(gp.tileM.mapTileChar[brickXRight/gp.tileSize][brickYRight/gp.tileSize - 1] == 'f') {
                                gp.tileM.setMaxTileChar(brickXRight/gp.tileSize , brickYRight/gp.tileSize - 1 , 'l');
                            } else if (gp.tileM.mapTileChar[brickXRight/gp.tileSize][brickYRight/gp.tileSize - 1] == 'x'){
                                gp.tileM.setMaxTileChar(brickXRight/gp.tileSize , brickYRight/gp.tileSize - 1 , 'X');
                            } else {
                                gp.tileM.setMaxTileChar(brickXRight/gp.tileSize , brickYRight/gp.tileSize - 1 , ' ');
                            }
                        }
                        if(explodeLeft) {
                            if(gp.tileM.mapTileChar[brickXLeft/gp.tileSize][brickYLeft/ gp.tileSize - 1] == 'f') {
                                gp.tileM.setMaxTileChar(brickXLeft/gp.tileSize , brickYLeft/ gp.tileSize - 1, 'l');
                            } else if (gp.tileM.mapTileChar[brickXLeft/gp.tileSize][brickYLeft/ gp.tileSize - 1] == 'x'){
                                gp.tileM.setMaxTileChar(brickXLeft/gp.tileSize , brickYLeft/ gp.tileSize - 1, 'X');
                            } else {
                                gp.tileM.setMaxTileChar(brickXLeft/gp.tileSize , brickYLeft/ gp.tileSize - 1, ' ');
                            }
                        }
                        if(explodeUp)
                        {
                            if(gp.tileM.mapTileChar[brickXUp/gp.tileSize][brickYUp/ gp.tileSize - 1] == 'f') {
                                gp.tileM.setMaxTileChar(brickXUp/gp.tileSize, brickYUp/ gp.tileSize - 1, 'l');
                            } else if(gp.tileM.mapTileChar[brickXUp/gp.tileSize][brickYUp/ gp.tileSize - 1] == 'x') {
                                gp.tileM.setMaxTileChar(brickXUp/gp.tileSize, brickYUp/ gp.tileSize - 1, 'X');
                            } else {
                                gp.tileM.setMaxTileChar(brickXUp/gp.tileSize, brickYUp/ gp.tileSize - 1, ' ');
                            }
                        }
                        if(explodeDown) {
                            if(gp.tileM.mapTileChar[brickXDown/gp.tileSize][brickYDown/ gp.tileSize - 1] == 'f') {
                                gp.tileM.setMaxTileChar(brickXDown/gp.tileSize, brickYDown/ gp.tileSize - 1, 'l');
                            } else if( gp.tileM.mapTileChar[brickXDown/gp.tileSize][brickYDown/ gp.tileSize - 1] == 'x'){
                                gp.tileM.setMaxTileChar(brickXDown/gp.tileSize, brickYDown/ gp.tileSize - 1, 'X');
                            }else {
                                gp.tileM.setMaxTileChar(brickXDown/gp.tileSize, brickYDown/ gp.tileSize - 1, ' ');
                            }
                        }
                        gp.cChecker.checkBombOnPlayer(this, player);
                        for(int i = 0; i < balloom.length; i++) {
                            if(balloom[i] != null) {
                                gp.cChecker.checkBombOnPlayer(this, balloom[i]);
                            }
                        }
                        spriteNum = 5;
                    } else if (spriteNum == 5) {
                        gp.cChecker.checkBombOnPlayer(this, player);
                        for(int i = 0; i < balloom.length; i++) {
                            if(balloom[i] != null) {
                                gp.cChecker.checkBombOnPlayer(this, balloom[i]);
                            }
                        }
                        spriteNum = 6;
                    } else if (spriteNum == 6) {
                        gp.cChecker.checkBombOnPlayer(this, player);
                        for(int i = 0; i < balloom.length; i++) {
                            if(balloom[i] != null) {
                                gp.cChecker.checkBombOnPlayer(this, balloom[i]);
                            }
                        }
                        keyH.unExploded = false;
                        bombUnExploded = false;
                        spriteNum = 1;
                        changeNum = 0;
                        keyH.bombPresent = false;
                        placed = false;
                        done = false;
                        if (explodeRight) {

                            gp.tileM.setAlterMap(brickXRight / gp.tileSize, brickYRight / gp.tileSize - 1, ' ');
                        }
                        if (explodeLeft)
                            gp.tileM.setAlterMap(brickXLeft / gp.tileSize, brickYLeft / gp.tileSize - 1, ' ');
                        if (explodeUp) gp.tileM.setAlterMap(brickXUp / gp.tileSize, brickYUp / gp.tileSize - 1, ' ');
                        if (explodeDown)
                            gp.tileM.setAlterMap(brickXDown / gp.tileSize, brickYDown / gp.tileSize - 1, ' ');
                        brickYRight = 0;
                        brickXRight = 0;
                        brickXDown = 0;
                        brickYDown = 0;
                        brickYUp = 0;
                        brickXUp = 0;
                        brickXLeft = 0;
                        brickYLeft = 0;
                        gp.tileM.setMaxTileChar(worldX / gp.tileSize, worldY / gp.tileSize - 1, ' ');
                        gp.tileM.setAlterMap(worldX / gp.tileSize, worldY / gp.tileSize - 1, ' ');
                        gp.tileM.setNewBombMap(worldX / gp.tileSize, worldY / gp.tileSize - 1, ' ');

                        gp.tileM.setMaxTileChar(worldX/gp.tileSize , worldY/gp.tileSize - 1 , ' ');
                        player.playerOnBomb = false;
                        playerOnBomb = false;
                        //System.out.println(realX/gp.tileSize + " " + worldY/gp.tileSize);

                    }
                }
                spriteCounter = 0;
            }
        }
    }


    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        BufferedImage brick = null;
        BufferedImage[] up = new BufferedImage[gp.bombLength];
        BufferedImage[] left = new BufferedImage[gp.bombLength];
        BufferedImage[] down = new BufferedImage[gp.bombLength];
        BufferedImage[] right = new BufferedImage[gp.bombLength];
        if (spriteNum == 1) {
            image = bomb1;
        } else if (spriteNum == 2) {
            image = bomb2;
        } else if (spriteNum == 3) {
            image = bomb3;
        } else if (spriteNum == 4) {

            image = bombExploded1;
            brick = brickExploded;
            for(int i = 0; i < gp.bombLength; i++) {
                if(i == gp.bombLength - 1) {
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
            for(int i = 0; i < gp.bombLength; i++) {
                if (i == gp.bombLength - 1) {
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
            for(int i = 0; i < gp.bombLength; i++) {
                if (i == gp.bombLength - 1) {
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
        g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
        for(int i = 0; i < gp.bombLength; i++) {
            gp.cChecker.checkTileBombUp(this, worldX, worldY - (i+1) * gp.tileSize);
            if(!collisionBombUp) {
                g2.drawImage(up[i], worldX, worldY - (i+1) * gp.tileSize, gp.tileSize, gp.tileSize, null);
            } else {
                brickXUp = worldX;
                brickYUp = worldY - (i+1) * gp.tileSize;
                if(explodeUp) {

                    brickXUp = worldX;
                    brickYUp = worldY - (i+1) * gp.tileSize;
                    g2.drawImage(brick, worldX, worldY - (i+1) * gp.tileSize, gp.tileSize, gp.tileSize, null);
                }
                break;
            }
        }

        //checkTileDown
        for(int i = 0; i < gp.bombLength; i++) {
            gp.cChecker.checkTileBombDown(this, worldX, worldY + (i+1) * gp.tileSize);
            if(!collisionBombDown) {
                g2.drawImage(down[i], worldX, worldY + (i+1) * gp.tileSize, gp.tileSize, gp.tileSize, null);
            } else {
                brickXDown = worldX;
                brickYDown = worldY + (i+1) * gp.tileSize;
                if(explodeDown) {

                    brickXDown = worldX;
                    brickYDown = worldY + (i+1) * gp.tileSize;
                    g2.drawImage(brick, worldX, worldY + (i+1) * gp.tileSize, gp.tileSize, gp.tileSize, null);
                }
                break;
            }
        }

        //checkTileRight
        for(int i = 0; i < gp.bombLength; i++) {
            gp.cChecker.checkTileBombRight(this,worldX + (i+1) * gp.tileSize, worldY);
            if(!collisionBombRight) {
                g2.drawImage(right[i], worldX + (i+1) * gp.tileSize, worldY ,gp.tileSize, gp.tileSize, null);
            } else {
                brickXRight = worldX + (i+1) * gp.tileSize;
                brickYRight = worldY;
                if(explodeRight) {

                    brickXRight = worldX + (i+1) * gp.tileSize;
                    brickYRight = worldY;

                    g2.drawImage(brick,worldX + (i+1) * gp.tileSize, worldY ,gp.tileSize, gp.tileSize, null);
                }
                break;
            }
        }

        // checkTileLeft
        for(int i = 0; i < gp.bombLength; i++) {
            gp.cChecker.checkTileBombLeft(this,worldX - (i+1) * gp.tileSize, worldY);
            if(!collisionBombLeft) {
                g2.drawImage(left[i], worldX - (i+1) * gp.tileSize, worldY ,gp.tileSize, gp.tileSize, null);
            } else {
                brickXLeft = worldX - (i+1) * gp.tileSize;
                brickYLeft = worldY;
                if(explodeLeft) {

                    brickXLeft = worldX - (i+1) * gp.tileSize;
                    brickYLeft = worldY;

                    g2.drawImage(brick,worldX - (i+1) * gp.tileSize, worldY ,gp.tileSize, gp.tileSize, null);
                }
                break;
            }
        }

    }

}