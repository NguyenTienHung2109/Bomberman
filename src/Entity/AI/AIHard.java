package Entity.AI;

import Entity.Oneal;
import Entity.Player;
import main.GamePanel;

import java.util.*;

public class AIHard extends AI {
    GamePanel gp;
    Player player;
    Oneal oneal;

    public AIHard(Player player, Oneal oneal) {
        this.player = player;
        this.oneal = oneal;
    }

    public ArrayList<List<Integer>> d = new ArrayList<List<Integer>>();
    public ArrayList<List<boolean>> visit = new ArrayList<List<Integer>>();
    static int[] moveX = {0, 0, 1, -1};
    static int[] moveY = {1, -1, 0, 0};
    public Entity.AI.point[][] par = new Entity.AI.point[gp.maxScreenRow][gp.maxScreenCol];

    public void bfs(int sx, int sy) {
        for (int i = 0; i < gp.maxScreenRow; i++) {
            for (int j = 0; j < gp.maxScreenCol; j++) {
                d.get(i).set(j,0);
                visit.get(i).set(j,false);
            }
        }
        Queue<Entity.AI.point> q = new ArrayDeque<>();
        q.add(new Entity.AI.point(sx, sy));
        visit.get(sx).set(sy,true);
        while (!q.isEmpty()) {
            int x = q.peek().getX();
            int y = q.peek().getY();
            q.poll();

            if (x == player.worldX && y == player.worldY) {
                return;
            }
            for (int i = 0; i < 4; i++) {
                int u = x + moveX[i];
                int v = y + moveY[i];
                Entity.AI.point cur = new Entity.AI.point(u, v);
                if (u > gp.maxScreenRow || u < 1) continue;
                if (v > gp.maxScreenCol || v < 1) continue;
                if (gp.tileM.mapTileChar[u][v] == '*' || gp.tileM.mapTileChar[u][v] == '#') continue;

                if (!visit.get(u).get(v)) {
                    d[u][v] = d[x][y] + 1;
                    par[u][v] = new Entity.AI.point(x, y);
                    visit[u][v] = true;
                    q.add(new Entity.AI.point(u, v));
                }
            }
        }
    }

    @Override
    public int calculateDirection() {
        if (!visit[player.worldX][player.worldY]) {
            return random.nextInt(4);
        }
        point target = new point(player.worldX, player.worldY);
        List<point> path = new ArrayList<>();
        while (target != null) {
            int cx = target.getX();
            int cy = target.getY();
            path.add(new point(cx, cy));
            target = par[cx][cy];
        }
        Collections.reverse(path);
        for (point i : path) {
            int curx = i.getX();
            int cury = i.getY();
            if (curx == oneal.worldX - 1 && cury == oneal.worldY) {
                return 3;
            }
            if (curx == oneal.worldX + 1 && cury == oneal.worldY) {
                return 0;
            }
            if (curx == oneal.worldX && cury == oneal.worldY - 1) {
                return 2;
            }
            if (curx == oneal.worldX && cury == oneal.worldY + 1) {
                return 1;
            }
        }
        return 0;
    }

}