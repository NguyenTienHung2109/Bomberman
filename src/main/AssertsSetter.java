package main;

import Entity.Balloom;

public class AssertsSetter {
    GamePanel gp;
    public AssertsSetter(GamePanel gp) {
        this.gp = gp;
    }
    public  void setBalloom() {
        gp.balloom[0] = new Balloom(gp);
        gp.balloom[0].worldX = gp.tileSize*11;
        gp.balloom[0].worldY = gp.tileSize*2;
    }
}
