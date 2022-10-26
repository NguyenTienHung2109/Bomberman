package Entity.AI;

import Entity.AI.AI;

public class AILow extends AI {

    @Override
    public int calculateDirection() {
        // TODO: cài đặt thuật toán tìm đường đi
        return random.nextInt(4);
    }
    public void bfs(int i, int i1) {
        System.out.println("hello");
    }


}