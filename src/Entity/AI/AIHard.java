package Entity.AI;

import Entity.Oneal;
import Entity.Player;
import main.GamePanel;

import java.util.*;

public class AIHard extends AI {
    GamePanel gp;
    Player player;
    Oneal oneal;

    public AIHard(Player player, Oneal oneal, GamePanel gp) {
        this.player = player;
        this.oneal = oneal;
        this.gp = gp;
    }
    List<point> path = new ArrayList<>();
    static int[] moveX = {0, 0, 1, -1};
    static int[] moveY = {1, -1, 0, 0};

    public void bfs(int sx, int sy) {
        ArrayList<ArrayList<Boolean>> visit = new ArrayList<>(20);
        ArrayList<ArrayList<Integer>> d = new ArrayList<>(20);
        ArrayList<ArrayList<point>> par = new ArrayList<>(20);

        for (int i = 0; i < 14; i++) {
            d.add(new ArrayList());
            visit.add(new ArrayList());
            par.add(new ArrayList());
            for (int j = 0; j < 31; j++) {
                d.get(i).add(0);
                visit.get(i).add(false);
                par.get(i).add(new point(0, 0));
            }
        }
        Queue<Entity.AI.point> q = new ArrayDeque<>();
        q.add(new point(sx, sy));
        visit.get(sy).set(sx, true);
        while (!q.isEmpty()) {
            int x = q.peek().x;
            int y = q.peek().y;
            q.poll();
            if (x == (player.worldX + player.solidArea.x)/ gp.tileSize && y == (player.worldY + player.solidArea.y) / gp.tileSize - 1) {
                point k = par.get(y).get(x);
                path = new ArrayList<>();
                while (x != sx || y != sy) {
                    x = k.x;
                    y = k.y;
                    path.add(k);
                    k = par.get(k.y).get(k.x);
                }
                return;
            }
            for (int i = 0; i < 4; i++) {
                int u = x + moveX[i];
                int v = y + moveY[i];
                if (u > 31 || u < 1) continue;
                if (v > 14 || v < 1) continue;
                if (gp.tileM.mapTileChar[u][v] == '*' || gp.tileM.mapTileChar[u][v] == '#' || gp.tileM.mapTileChar[u][v] == 'n'
                || gp.tileM.mapTileChar[u][v] == 's' || gp.tileM.mapTileChar[u][v] == 'f' || gp.tileM.mapTileChar[u][v] == 'x')
                    continue;

                if (!visit.get(v).get(u)) {
                    d.get(v).set(u, d.get(y).get(x) + 1);
                    par.get(v).set(u, new point(x, y));
                    visit.get(v).set(u, true);
                    q.add(new point(u, v));
                }
            }
        }
    }

    @Override
    public int calculateDirection() {

        Collections.reverse(path);
        int curx = player.worldX/ gp.tileSize;
        int cury = player.worldY/ gp.tileSize - 1;
        if(path.size() > 1) {
            path.remove(0);
            curx = path.get(0).x;
            cury = path.get(0).y;
        }

        if (curx == oneal.worldX / gp.tileSize && cury == oneal.worldY / gp.tileSize - 2) {
            return 3;
        }
        if (curx == oneal.worldX / gp.tileSize && cury == oneal.worldY / gp.tileSize) {
            return 0;
        }
        if (curx == oneal.worldX / gp.tileSize - 1 && cury == oneal.worldY / gp.tileSize - 1) {
            return 2;
        }
        if (curx == oneal.worldX / gp.tileSize + 1 && cury == oneal.worldY / gp.tileSize - 1) {
            return 1;
        }
        return 0;

    }

}