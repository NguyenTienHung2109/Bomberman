package Entity.AI;

import Entity.AI.AI;

public class AILow extends AI {

    @Override
    public int calculateDirection() {
        // TODO: cài đặt thuật toán tìm đường đi
        return random.nextInt(4);
    }

}