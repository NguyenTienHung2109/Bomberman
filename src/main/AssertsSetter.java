package main;

import Entity.Balloom;
import Entity.Bomb;

public class AssertsSetter {
    GamePanel gp;
    KeyHolder keyR;
    public AssertsSetter(GamePanel gp, KeyHolder keyR) {
        this.gp = gp;
        this.keyR = keyR;


    }
    public  void setBalloom() {
        gp.balloom[0] = new Balloom(gp);
        gp.balloom[0].worldX = gp.tileSize*7;
        gp.balloom[0].worldY = gp.tileSize*4;
        
        gp.balloom[1] = new Balloom(gp);
        gp.balloom[1].worldX = gp.tileSize*29;
        gp.balloom[1].worldY = gp.tileSize*8;

        gp.balloom[2] = new Balloom(gp);
        gp.balloom[2].worldX = gp.tileSize*11;
        gp.balloom[2].worldY = gp.tileSize*10;

        gp.balloom[3] = new Balloom(gp);
        gp.balloom[3].worldX = gp.tileSize*25;
        gp.balloom[3].worldY = gp.tileSize*8;;

        gp.balloom[4] = new Balloom(gp);
        gp.balloom[4].worldX = gp.tileSize*21;
        gp.balloom[4].worldY = gp.tileSize*6;

        gp.balloom[5] = new Balloom(gp);
        gp.balloom[5].worldX = gp.tileSize*15;
        gp.balloom[5].worldY = gp.tileSize*8;


    }


    public void setBomb() {
        Bomb b = new Bomb(gp, keyR);
        gp.bomb.add(b);

    }

}
