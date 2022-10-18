package main;

import Entity.Balloom;
import Entity.Bomb;

public class AssertsSetter {
    GamePanel gp;
    KeyHolder keyR;
    public int demBalloom = 0;
    public AssertsSetter(GamePanel gp, KeyHolder keyR) {
        this.gp = gp;
        this.keyR = keyR;


    }
    public  void setBalloom() {

        int col = 0;
        int row = 0;
        int x = 0;
        int y = gp.tileSize;
        while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
            char obj = gp.tileM.mapTileChar[col][row];
            if(obj == '1') {
                gp.balloom[demBalloom] = new Balloom(gp);
                gp.balloom[demBalloom].worldX = x - 1;
                gp.balloom[demBalloom].worldY = y;
                demBalloom++;
            }
            if(obj == 'p') {
                gp.player.worldX = x - 1;
                gp.player.worldY = y;
            }
            col++;
            x += gp.tileSize;

            if(col == gp.maxScreenCol) {
                col = 0;
                x = 1;
                row++;
                y += gp.tileSize;
            }
        }
    }

    public void setBomb() {
        Bomb b = new Bomb(gp, keyR);
        gp.bomb.add(b);

    }

}
