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
        } else if (tempTileNum == 'b') {
            return 3;
        } else {
            return 2;
        }

    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
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

                }
                break;
        }
    }
    public void checkTileBombUp(Entity entity, int x, int y) {
        char tempTileNum;
        int tileNum;
        int Row = (int) y / gp.tileSize - 1;
        int Col = (int) x / gp.tileSize;
        tempTileNum = gp.tileM.alterMap[Col][Row];
        tileNum = convertTile(tempTileNum);
        if (gp.tileM.tile[tileNum].collision == true) {
            entity.collisionBombUp = true;
            if (tileNum == 2) {
                entity.explodeUp = true;
            }
        }
    }
    public void checkTileBombDown(Entity entity, int x, int y) {
        char tempTileNum;
        int tileNum;
        int Row = (int) y / gp.tileSize - 1;
        int Col = (int) x / gp.tileSize;
        tempTileNum = gp.tileM.alterMap[Col][Row];
        tileNum = convertTile(tempTileNum);

        if (gp.tileM.tile[tileNum].collision == true) {
            entity.collisionBombDown = true;
            if (tileNum == 2) {
                entity.explodeDown = true;
            }
        }
    }

    public void checkTileBombRight(Entity entity, int x, int y) {
        char tempTileNum;
        int tileNum;
        int Row = (int) y / gp.tileSize - 1;
        int Col = (int) x / gp.tileSize;
        tempTileNum = gp.tileM.alterMap[Col][Row];
        tileNum = convertTile(tempTileNum);

        if (gp.tileM.tile[tileNum].collision == true) {
            entity.collisionBombRight = true;
            if (tileNum == 2) {
                entity.explodeRight = true;
            }
        }
    }
    public void checkTileBombLeft(Entity entity, int x, int y) {
        char tempTileNum;
        int tileNum;
        int Row = (int) y / gp.tileSize - 1;
        int Col = (int) x / gp.tileSize;
        tempTileNum = gp.tileM.alterMap[Col][Row];
        tileNum = convertTile(tempTileNum);

        if (gp.tileM.tile[tileNum].collision == true) {
            entity.collisionBombLeft = true;
            if (tileNum == 2) {
                entity.explodeLeft = true;
            }
        }
    }
    public void checkBombOnPlayer(Entity bomb, Entity player) {

        int bombX = bomb.worldX + bomb.solidArea.x;
        int bombY = bomb.worldY + bomb.solidArea.y;

        int bombMidCol = bombX/gp.tileSize;
        int bombMidRow = bombY/gp.tileSize - 1;
        int bombLeftCol, bombRightCol, bombTopRow, bombBottomRow;

        if(bomb.brickXLeft == 0) {bombLeftCol = bombMidCol - bomb.bombLength;}
        else { bombLeftCol = bomb.brickXLeft/gp.tileSize;}
        if(bomb.brickXRight == 0) {bombRightCol = bombMidCol + bomb.bombLength;}
        else {bombRightCol = bomb.brickXRight/gp.tileSize;}
        if(bomb.brickYUp == 0) {bombTopRow = bombMidRow - bomb.bombLength;}
        else {bombTopRow = bomb.brickYUp/gp.tileSize;}
        if(bomb.brickYDown == 0) {bombBottomRow = bombMidRow + bomb.bombLength;}
        else {bombBottomRow = bomb.brickYDown/gp.tileSize - 1;}

        int playerLeftWorldX = player.worldX + player.solidArea.x;
        int playerRightWorldX = player.worldX + player.solidArea.x + player.solidArea.width;
        int playerTopWorldY = player.worldY + player.solidArea.y;
        int playerBottomWorldY = player.worldY + player.solidArea.y + player.solidArea.height;

        int playerLeftCol = playerLeftWorldX / gp.tileSize;
        int playerRightCol = playerRightWorldX / gp.tileSize;
        int playerTopRow = playerTopWorldY / gp.tileSize - 1;
        int playerBottomRow = playerBottomWorldY / gp.tileSize - 1;


        if(((playerLeftCol <= bombRightCol && playerLeftCol >= bombMidCol) || (playerRightCol >= bombLeftCol && playerRightCol <= bombMidCol)) && (playerBottomRow == bombMidRow || playerTopRow == bombMidRow)) {
            player.playerOnBomb = true;
        }
        if(((playerBottomRow >= bombTopRow && playerBottomRow <= bombMidRow) || (playerTopRow <= bombBottomRow && playerTopRow >= bombMidRow)) && (playerLeftCol == bombMidCol || playerRightCol == bombMidCol)) {
            player.playerOnBomb = true;
        }

    }

}


