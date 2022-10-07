package main;

import Entity.Entity;

import java.time.temporal.Temporal;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public int convertTile(char tempTileNum) {
        if (tempTileNum == '#') {
            return 1;
        } else if (tempTileNum == ' ') {
            return 0;
        } else {
            return 2;
        }

    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.realX + entity.solidArea.x;
        int entityRightWorldX = entity.realX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize - 1;
        int entityBottomRow = entityBottomWorldY / gp.tileSize - 1;

        char tempTileNum1;
        char tempTileNum2;
        int tileNum1;
        int tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (int) ((entityTopWorldY - entity.speed) / gp.tileSize) - 1;
                tempTileNum1 = gp.tileM.mapTileChar[entityLeftCol][entityTopRow];
                tempTileNum2 = gp.tileM.mapTileChar[entityRightCol][entityTopRow];
                tileNum1 = convertTile(tempTileNum1);
                tileNum2 = convertTile(tempTileNum2);
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                    System.out.println("up");
                    System.out.println("up -- " + entityLeftWorldX + "***" + entityRightWorldX + "***" + entityTopWorldY + "***" + entityBottomWorldY);
                    System.out.println(tileNum1 + " " + tileNum2 + " " + entityLeftCol + " " + entityRightCol + " " + entityTopRow + " " + entityBottomRow);
                }

                break;
            case "down":
                entityBottomRow = (int) ((entityBottomWorldY + entity.speed) / gp.tileSize) - 1;
                tempTileNum1 = gp.tileM.mapTileChar[entityLeftCol][entityBottomRow];
                tempTileNum2 = gp.tileM.mapTileChar[entityRightCol][entityBottomRow];
                tileNum1 = convertTile(tempTileNum1);
                tileNum2 = convertTile(tempTileNum2);
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                    System.out.println("down -- " + entityLeftWorldX + "***" + entityRightWorldX + "***" + entityTopWorldY + "***" + entityBottomWorldY);
                    System.out.println(tileNum1 + " " + tileNum2 + " " + entityLeftCol + " " + entityRightCol + " " + entityTopRow + " " + entityBottomRow);
                }
                break;
            case "left":
                entityLeftCol = (int) ((entityLeftWorldX - entity.speed) / gp.tileSize);
                tempTileNum1 = gp.tileM.mapTileChar[entityLeftCol][entityTopRow];
                tempTileNum2 = gp.tileM.mapTileChar[entityLeftCol][entityBottomRow];
                tileNum1 = convertTile(tempTileNum1);
                tileNum2 = convertTile(tempTileNum2);
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                    System.out.println("left -- " + entityLeftWorldX + "***" + entityRightWorldX + "***" + entityTopWorldY + "***" + entityBottomWorldY);
                    System.out.println(tileNum1 + " " + tileNum2 + " " + entityLeftCol + " " + entityRightCol + " " + entityTopRow + " " + entityBottomRow);
                }
                break;
            case "right":
                entityRightCol = (int) ((entityRightWorldX + entity.speed) / gp.tileSize);
                tempTileNum1 = gp.tileM.mapTileChar[entityRightCol][entityTopRow];
                tempTileNum2 = gp.tileM.mapTileChar[entityRightCol][entityBottomRow];
                tileNum1 = convertTile(tempTileNum1);
                tileNum2 = convertTile(tempTileNum2);
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                    System.out.println("right -- " + entityLeftWorldX + "***" + entityRightWorldX + "***" + entityTopWorldY + "***" + entityBottomWorldY);
                    System.out.println(tileNum1 + " " + tileNum2 + " " + entityLeftCol + " " + entityRightCol + " " + entityTopRow + " " + entityBottomRow);
                }
                break;
        }
    }
    public void checkTileBomb(Entity entity) {
        int entityLeftWorldX = entity.realX + entity.solidArea.x;
        int entityRightWorldX = entity.realX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize - 1;
        int entityBottomRow = entityBottomWorldY / gp.tileSize - 1;

        char tempTileNum1;
        char tempTileNum2;
        int tileNum1;
        int tileNum2;

        // checkBombUp
        entityTopRow = (int) ((entityTopWorldY - entity.speed) / gp.tileSize) - 1;
        tempTileNum1 = gp.tileM.alterMap[entityLeftCol][entityTopRow - 1];
        tileNum1 = convertTile(tempTileNum1);

        if (gp.tileM.tile[tileNum1].collision == true) {
            entity.collisionBombUp = true;
            if(tileNum1 != 1 && tileNum1 != 0) {
                entity.explodeUp = true;
            }

        }

        //checkBombDown
        entityBottomRow = (int) ((entityBottomWorldY + entity.speed) / gp.tileSize) - 1;
        tempTileNum1 = gp.tileM.alterMap[entityLeftCol][entityBottomRow + 1];
        tileNum1 = convertTile(tempTileNum1);
        if (gp.tileM.tile[tileNum1].collision == true) {
            entity.collisionBombDown = true;
            if(tileNum1 != 1 && tileNum1 != 0) {
                entity.explodeDown = true;
            }
        }

        //checkBombLeft
        entityLeftCol = (int) ((entityLeftWorldX - entity.speed) / gp.tileSize);
        tempTileNum1 = gp.tileM.alterMap[entityLeftCol - 1][entityTopRow];
        tileNum1 = convertTile(tempTileNum1);
        if (gp.tileM.tile[tileNum1].collision == true) {
            entity.collisionBombLeft = true;
            if(tileNum1 != 1 && tileNum1 != 0) {
                entity.explodeLeft = true;
            }
        }

        //checkBombRight
        entityRightCol = (int) ((entityRightWorldX + entity.speed) / gp.tileSize);
        tempTileNum1 = gp.tileM.alterMap[entityRightCol + 1][entityTopRow];
        tileNum1 = convertTile(tempTileNum1);
        if (gp.tileM.tile[tileNum1].collision == true) {
            entity.collisionBombRight = true;
            if(tileNum1 != 1 && tileNum1 != 0) {
                entity.explodeRight = true;
            }
            //System.out.println(entity.explodeRight);
        }
    }

    public void checkBombOnPlayer(Entity bomb, Entity player) {
        int bombX = bomb.realX + bomb.solidArea.x;
        int bombY = bomb.worldY + bomb.solidArea.y;

        int bombMidCol = bombX/gp.tileSize;
        int bombMidRow = bombY/gp.tileSize;
        int bombLeftCol = bombMidCol - 1;
        int bombRightCol = bombMidCol + 1;
        int bombTopRow = bombMidRow - 1;
        int bombBottomRow = bombMidRow + 1;

        int playerLeftWorldX = player.realX + player.solidArea.x;
        int playerRightWorldX = player.realX + player.solidArea.x + player.solidArea.width;
        int playerTopWorldY = player.worldY + player.solidArea.y;
        int playerBottomWorldY = player.worldY + player.solidArea.y + player.solidArea.height;

        int playerLeftCol = playerLeftWorldX / gp.tileSize;
        int playerRightCol = playerRightWorldX / gp.tileSize;
        int playerTopRow = playerTopWorldY / gp.tileSize;
        int playerBottomRow = playerBottomWorldY / gp.tileSize;
        System.out.println(playerBottomRow + " " + bombY);
        if((playerLeftCol == bombRightCol || playerRightCol == bombLeftCol) && (playerBottomRow == bombMidRow || playerTopRow == bombMidRow)) {
            bomb.playerOnBomb = true;
        }
        if((playerLeftCol == bombMidCol || playerRightCol == bombMidCol) && (playerBottomRow == bombMidRow || playerTopRow == bombMidRow)) {
            bomb.playerOnBomb = true;
        }
        if((playerBottomRow == bombTopRow || playerTopRow == bombBottomRow) && (playerLeftCol == bombMidCol || playerRightCol == bombMidCol)) {
            bomb.playerOnBomb = true;
        }

    }
}


