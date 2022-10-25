package Entity;
import com.sun.nio.sctp.SctpChannel;
import main.GamePanel;

import java.util.*;

public class point {
    GamePanel gp;
    private int x;
    private int y;
    public int [][] d = new int [14][31];
    public boolean [][] visit = new boolean[10][10];
    static int[] moveX = {0, 0, 1, -1};
    static int[] moveY = {1, -1, 0, 0};
    public point [][]par= new point[10][10];

    public point() {

    }
    public point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void bfs(int sx, int sy) {
        for(int i = 0; i < gp.maxScreenRow; i++) {
            for(int j = 0; j < gp.maxScreenCol; j++) {
                d[i][j] = 0;
                visit[i][j] = false;
            }
        }
        Queue<point> q = new ArrayDeque<>();
        q.add(new point(sx,sy));
        visit[sx][sy] = true;
        while(!q.isEmpty()) {
            int x = q.peek().x;
            int y = q.peek().y;
            q.poll();

            if(x == gp.player.worldX && y==gp.player.worldY) {
                return;
            }
            for(int i = 0; i < 4; i++) {
                int u = x + moveX[i];
                int v = y + moveY[i];
                point cur = new point(u,v);
                if(u > gp.maxScreenRow || u < 1) continue;
                if(v > gp.maxScreenCol || v < 1) continue;
                if(gp.tileM.mapTileChar[u][v] == '*' || gp.tileM.mapTileChar[u][v] == '#') continue;

                if(!visit[u][v]) {
                    d[u][v] = d[x][y] + 1;
                    par[u][v] = new point(x,y);
                    visit[u][v] = true;
                    q.add(new point(u,v));
                }
            }
        }
    }
}