package Entity.AI;

import Entity.AI.AI;
import Entity.Oneal;
import Entity.Player;

public class AIMedium extends AI {
    Player player;
    Oneal oneal;

    public AIMedium(Player player, Oneal oneal) {
        this.player = player;
        this.oneal = oneal;
    }

    @Override
    public int calculateDirection() {
        // TODO: cài đặt thuật toán tìm đường đi
        if(player == null)
            return random.nextInt(4);

        int vertical = random.nextInt(2);

        if(vertical == 1) {
            int v = calculateRowDirection();
            if(v != -1)
                return v;
            else
                return calculateColDirection();

        } else {
            int h = calculateColDirection();

            if(h != -1)
                return h;
            else
                return calculateRowDirection();
        }
    }
    protected int calculateColDirection() {
        if(player.worldX < oneal.worldX)
            return 3;
        else if(player.worldX > player.worldX)
            return 1;

        return -1;
    }

    protected int calculateRowDirection() {
        if(player.worldY < oneal.worldY)
            return 0;
        else if(player.worldY > oneal.worldY)
            return 2;
        return -1;
    }
    public void bfs(int i, int i1) {
        System.out.println("hello");
    }

}
