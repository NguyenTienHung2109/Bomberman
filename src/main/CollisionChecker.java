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
        } else if (tempTileNum == ' ' || tempTileNum == '1' || tempTileNum == '2' || tempTileNum == 'p') {
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

        if(bomb.brickXLeft == 0) {bombLeftCol = bombMidCol - gp.bombLength;}
        else { bombLeftCol = bomb.brickXLeft/gp.tileSize;}
        if(bomb.brickXRight == 0) {bombRightCol = bombMidCol + gp.bombLength;}
        else {bombRightCol = bomb.brickXRight/gp.tileSize;}
        if(bomb.brickYUp == 0) {bombTopRow = bombMidRow - gp.bombLength;}
        else {bombTopRow = bomb.brickYUp/gp.tileSize;}
        if(bomb.brickYDown == 0) {bombBottomRow = bombMidRow + gp.bombLength;}
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
            player.isDead = true;
        }
        if(((playerBottomRow >= bombTopRow && playerBottomRow <= bombMidRow) || (playerTopRow <= bombBottomRow && playerTopRow >= bombMidRow)) && (playerLeftCol == bombMidCol || playerRightCol == bombMidCol)) {
            player.playerOnBomb = true;
            player.isDead = true;
        }
    }

    public int checkEntity(Entity entity, Entity[] target) {
        int index = 999;

        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            entity.isDead = true;
                            index = i;
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            entity.isDead = true;
                            index = i;
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            entity.isDead = true;
                            index = i;
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            entity.isDead = true;
                            index = i;
                        }
                        break;
                }
                entity.solidArea.x = 4;
                entity.solidArea.y = 4;
                target[i].solidArea.x = 4;
                target[i].solidArea.y = 4;
            }
        }
        return index;
    }
    public void checkPlayer(Entity entity) {
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        switch (entity.direction) {
            case "up":
                entity.solidArea.y -= entity.speed;
                if (entity.solidArea.intersects(gp.player.solidArea)) {
                    entity.collisionOn = true;
                    entity.isDead = true;
                }
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                if (entity.solidArea.intersects(gp.player.solidArea)) {
                    entity.collisionOn = true;
                    entity.isDead = true;
                }
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                if (entity.solidArea.intersects(gp.player.solidArea)) {
                    entity.collisionOn = true;
                    entity.isDead = true;
                }
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                if (entity.solidArea.intersects(gp.player.solidArea)) {
                    entity.collisionOn = true;
                    entity.isDead = true;
                }
                break;
        }
        entity.solidArea.x = 4;
        entity.solidArea.y = 4;
        gp.player.solidArea.x = 4;
        gp.player.solidArea.y = 4;
    }
}


