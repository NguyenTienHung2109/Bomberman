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
        gp.balloom[0].worldX = gp.tileSize*11;
        gp.balloom[0].worldY = gp.tileSize*2;
    }

    public void setBomb() {
        Bomb b = new Bomb(gp, keyR);
        gp.bomb.add(b);

    }
}
